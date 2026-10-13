// XXX: Chromium registers AppKit observers from deliberately leaked singletons and never removes them, assuming the
// browser lives as long as the process. After cef_shutdown a later NSNotification or KVO change runs those callbacks
// against torn-down browser state (e.g. a lazy instance needing the destroyed AtExitManager) and aborts. Record
// CEF-originated registrations and remove them right after cef_shutdown; remove when CEF unregisters them itself.

#include <cstring>
#include <mutex>
#include <vector>

#include <dlfcn.h>
#include <execinfo.h>

#import <AppKit/AppKit.h>
#import <Foundation/Foundation.h>
#include <objc/runtime.h>

#if __has_feature(ptrauth_calls)
  #include <ptrauth.h>
#endif

#if !__has_feature(objc_arc)
  #error "appkit_observer_fix_mac.mm must be compiled with -fobjc-arc"
#endif

namespace {

struct NotificationBlockRegistration {
  NSNotificationCenter *center;
  id token;
};

// Observers and observed objects are weak: retaining them would keep a released Chromium object alive and still
// registered, so it would keep receiving callbacks after its owner is gone.
struct NotificationSelectorRegistration {
  NSNotificationCenter *center;
  __weak id observer;
  NSString *name;
};

struct KeyValueRegistration {
  __weak id object;
  __weak id observer;
  NSString *keyPath;
  void *context;
};

struct BlockLayout {
  void *isa;
  int flags;
  int reserved;
  void *invoke;
};

std::mutex g_registrationsMutex;
std::vector<NotificationBlockRegistration> g_blockRegistrations;
std::vector<NotificationSelectorRegistration> g_selectorRegistrations;
std::vector<KeyValueRegistration> g_keyValueRegistrations;
bool g_hooksInstalled = false;

bool AddressIsInCef(void *address) {
  if (address == nullptr) return false;
  Dl_info info{};
  if (dladdr(address, &info) == 0 || info.dli_fname == nullptr) return false;
  return std::strstr(info.dli_fname, "Chromium Embedded Framework") != nullptr;
}

bool DirectCallerIsCef() {
  void *address = __builtin_return_address(0);
#if __has_feature(ptrauth_calls)
  address = ptrauth_strip(address, ptrauth_key_return_address);
#endif
  return AddressIsInCef(address);
}

// Any object whose class Chromium defines can only originate from the framework, even when AppKit's super call hides
// the caller.
bool ClassImageIsInCef(id object) {
  if (object == nil) return false;
  const char *image = class_getImageName(object_getClass(object));
  return image != nullptr && std::strstr(image, "Chromium Embedded Framework") != nullptr;
}

// AppKit subclasses such as NSWorkspace register through a super call, so the hook's immediate caller is AppKit even
// when Chromium started the registration. Scan a few frames to find the CEF frame.
bool StackContainsCefFrame() {
  void *frames[8];
  int count = backtrace(frames, 8);
  for (int i = 0; i < count; ++i) {
    if (AddressIsInCef(frames[i])) return true;
  }
  return false;
}

// Blocks carry their implementation pointer, which is more precise than the caller for block observers.
bool BlockInvokesIntoCef(id block) {
  if (block == nil) return false;
  return AddressIsInCef(reinterpret_cast<BlockLayout *>((__bridge void *)block)->invoke);
}

// Chromium monitors VoiceOver by registering NSApp on the shared NSWorkspace, a pair only the stack identifies as
// CEF-originated. A host registering the same pair keeps its registration.
bool ChromiumVoiceOverRegistration(id object, id observer, NSString *keyPath) {
  if (![keyPath isEqualToString:@"voiceOverEnabled"]) return false;
  if (![object isKindOfClass:[NSWorkspace class]]) return false;
  if (observer != NSApp) return false;
  return StackContainsCefFrame();
}

void RecordBlockRegistration(NSNotificationCenter *center, id token) {
  if (center == nil || token == nil) return;
  std::lock_guard<std::mutex> lock(g_registrationsMutex);
  g_blockRegistrations.push_back({center, token});
}

void RecordSelectorRegistration(NSNotificationCenter *center, id observer, NSString *name) {
  if (center == nil || observer == nil) return;
  std::lock_guard<std::mutex> lock(g_registrationsMutex);
  g_selectorRegistrations.push_back({center, observer, [name copy]});
}

void RecordKeyValueRegistration(id object, id observer, NSString *keyPath, void *context) {
  if (object == nil || observer == nil || keyPath == nil) return;
  std::lock_guard<std::mutex> lock(g_registrationsMutex);
  g_keyValueRegistrations.push_back({object, observer, [keyPath copy], context});
}

Method DirectInstanceMethod(Class cls, SEL selector) {
  unsigned int count = 0;
  Method *methods = class_copyMethodList(cls, &count);
  Method direct = nullptr;
  for (unsigned int i = 0; i < count; ++i) {
    if (method_getName(methods[i]) == selector) {
      direct = methods[i];
      break;
    }
  }
  free(methods);
  return direct;
}

// Hooks the selector on the class that defines it. Subclasses such as NSWorkspaceNotificationCenter funnel their public
// registrations through a super call, so hooking the root class catches them without wrapping the subclass method:
// wrapping both levels would recurse when the subclass forwards to super and the hook calls the subclass replacement.
void HookImplementation(Class cls, SEL original, SEL replacement) {
  if (cls == nullptr) return;
  Method direct = DirectInstanceMethod(cls, original);
  if (direct == nullptr) return;
  Method hook = class_getInstanceMethod(cls, replacement);
  if (hook == nullptr) return;
  IMP hookImp = method_getImplementation(hook);
  IMP originalImp = method_getImplementation(direct);
  const char *types = method_getTypeEncoding(direct);
  if (!class_addMethod(cls, replacement, originalImp, types)) {
    class_replaceMethod(cls, replacement, originalImp, types);
  }
  method_setImplementation(direct, hookImp);
}

void InstallHooks() {
  if (g_hooksInstalled) return;
  g_hooksInstalled = true;
  HookImplementation([NSNotificationCenter class], @selector(addObserverForName:object:queue:usingBlock:),
                     @selector(cef4j_addObserverForName:object:queue:usingBlock:));
  HookImplementation([NSNotificationCenter class], @selector(addObserver:selector:name:object:),
                     @selector(cef4j_addObserver:selector:name:object:));
  HookImplementation([NSObject class], @selector(addObserver:forKeyPath:options:context:),
                     @selector(cef4j_addObserver:forKeyPath:options:context:));
}

void RemoveRecordedRegistrations() {
  std::vector<NotificationBlockRegistration> blocks;
  std::vector<NotificationSelectorRegistration> selectors;
  std::vector<KeyValueRegistration> keyValues;
  {
    std::lock_guard<std::mutex> lock(g_registrationsMutex);
    blocks.swap(g_blockRegistrations);
    selectors.swap(g_selectorRegistrations);
    keyValues.swap(g_keyValueRegistrations);
  }
  for (const auto &registration : blocks) {
    @try {
      [registration.center removeObserver:registration.token];
    } @catch (NSException *ignored) {
    }
  }
  // A cleared weak reference means the observer was released and NSNotificationCenter dropped it already.
  for (const auto &registration : selectors) {
    id observer = registration.observer;
    if (observer == nil) continue;
    @try {
      [registration.center removeObserver:observer name:registration.name object:nil];
    } @catch (NSException *ignored) {
    }
  }
  // A released observer or object removed its own KVO registration, or the registration died with the object.
  for (const auto &registration : keyValues) {
    id object = registration.object;
    id observer = registration.observer;
    if (object == nil || observer == nil) continue;
    @try {
      [object removeObserver:observer forKeyPath:registration.keyPath context:registration.context];
    } @catch (NSException *ignored) {
    }
  }
}

} // namespace

@interface NSNotificationCenter (Cef4jObserverFix)
- (id)cef4j_addObserverForName:(NSNotificationName)name
                        object:(nullable id)object
                         queue:(nullable NSOperationQueue *)queue
                    usingBlock:(void (^)(NSNotification *notification))block;
- (void)cef4j_addObserver:(id)observer selector:(SEL)selector name:(nullable NSNotificationName)name object:(nullable id)object;
@end

@implementation NSNotificationCenter (Cef4jObserverFix)
- (id)cef4j_addObserverForName:(NSNotificationName)name
                        object:(nullable id)object
                         queue:(nullable NSOperationQueue *)queue
                    usingBlock:(void (^)(NSNotification *notification))block {
  bool cefCaller = BlockInvokesIntoCef(block);
  id token = [self cef4j_addObserverForName:name object:object queue:queue usingBlock:block];
  if (cefCaller) RecordBlockRegistration(self, token);
  return token;
}

- (void)cef4j_addObserver:(id)observer selector:(SEL)selector name:(nullable NSNotificationName)name object:(nullable id)object {
  bool cefCaller = DirectCallerIsCef() || ClassImageIsInCef(observer);
  [self cef4j_addObserver:observer selector:selector name:name object:object];
  if (cefCaller) RecordSelectorRegistration(self, observer, name);
}
@end

@interface NSObject (Cef4jObserverFix)
- (void)cef4j_addObserver:(NSObject *)observer
               forKeyPath:(NSString *)keyPath
                  options:(NSKeyValueObservingOptions)options
                  context:(nullable void *)context;
@end

@implementation NSObject (Cef4jObserverFix)
- (void)cef4j_addObserver:(NSObject *)observer
               forKeyPath:(NSString *)keyPath
                  options:(NSKeyValueObservingOptions)options
                  context:(nullable void *)context {
  bool cefCaller = DirectCallerIsCef() || ClassImageIsInCef(observer) || ClassImageIsInCef(self)
                   || ChromiumVoiceOverRegistration(self, observer, keyPath);
  [self cef4j_addObserver:observer forKeyPath:keyPath options:options context:context];
  if (cefCaller) RecordKeyValueRegistration(self, observer, keyPath, context);
}
@end

extern "C" void cef4j_appkit_observer_fix_install(void) { InstallHooks(); }

extern "C" void cef4j_appkit_observer_fix_purge(void) { RemoveRecordedRegistrations(); }
