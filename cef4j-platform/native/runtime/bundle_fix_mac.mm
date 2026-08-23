// XXX: CEF 109-150 derives macOS Mach rendezvous names from CFBundle identifiers, while a JVM and bare helper expose
// different identifiers; keep both CoreFoundation and NSBundle patched to "cef4j" until upstream stops doing so.

#import <Foundation/Foundation.h>
#import <AppKit/AppKit.h>
#include <CoreFoundation/CoreFoundation.h>
#include <objc/runtime.h>

@interface NSBundle (Cef4jBundleFix)
- (NSString *)cef4j_bundleIdentifier;
@end

@implementation NSBundle (Cef4jBundleFix)
- (NSString *)cef4j_bundleIdentifier {
    if (self == [NSBundle mainBundle]) {
        return @"cef4j";
    }
    return [self cef4j_bundleIdentifier];
}
@end

// XXX: CEF 109-150 can leave [NSApp run] asleep after a quit under AWT/Glass; keep the dummy event until the minimum
// supported CEF exceeds 150 or upstream guarantees the wake-up.
extern "C" void cef4j_stop_nsapp(void) {
    NSApplication* app = [NSApplication sharedApplication];
    [app stop:nil];
    NSEvent* dummy = [NSEvent otherEventWithType:NSEventTypeApplicationDefined
                                        location:NSZeroPoint
                                   modifierFlags:0
                                       timestamp:0
                                    windowNumber:0
                                         context:nil
                                         subtype:0
                                           data1:0
                                           data2:0];
    [app postEvent:dummy atStart:YES];
}

extern "C" void cef4j_activate_app(void) {
    NSApplication* app = [NSApplication sharedApplication];
    if ([app activationPolicy] != NSApplicationActivationPolicyRegular) {
        [app setActivationPolicy:NSApplicationActivationPolicyRegular];
    }
    [app activateIgnoringOtherApps:YES];
}

extern "C" void cef4j_fix_main_bundle_id(void) {
    static bool applied = false;
    if (applied) return;
    applied = true;

    CFBundleRef mainBundle = CFBundleGetMainBundle();
    if (mainBundle) {
        CFMutableDictionaryRef infoDict = (CFMutableDictionaryRef)CFBundleGetInfoDictionary(mainBundle);
        if (infoDict) {
            CFDictionarySetValue(infoDict, kCFBundleIdentifierKey, CFSTR("cef4j"));
        }
    }

    Method original = class_getInstanceMethod([NSBundle class], @selector(bundleIdentifier));
    Method swizzled =
            class_getInstanceMethod([NSBundle class], @selector(cef4j_bundleIdentifier));
    if (original && swizzled) {
        method_exchangeImplementations(original, swizzled);
    }
}
