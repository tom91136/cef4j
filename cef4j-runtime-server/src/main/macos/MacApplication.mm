// Copyright (c) 2026 Tom Lin. All rights reserved.

#import <AppKit/AppKit.h>

#include <atomic>
#include <cstdio>

extern "C" void cef_do_message_loop_work(void);

namespace {
std::atomic<bool> g_quitMessageLoop{false};
}

// XXX: CEF 150 cef_application_mac.h pulls wrapper headers above this target's C++17 floor; remove when the target
// adopts that wrapper language level.
@protocol CrAppProtocol
- (BOOL)isHandlingSendEvent;
@end

@protocol CrAppControlProtocol <CrAppProtocol>
- (void)setHandlingSendEvent:(BOOL)handlingSendEvent;
@end

@protocol CefAppProtocol <CrAppControlProtocol>
@end

// XXX: CEF 150 on macOS requires CefAppProtocol before cef_initialize; remove when CEF documents a C-API bootstrap.
@interface Cef4jRuntimeApplication : NSApplication <CefAppProtocol> {
@private
    BOOL handlingSendEvent_;
}
@end

@implementation Cef4jRuntimeApplication
- (BOOL)isHandlingSendEvent {
    return handlingSendEvent_;
}

- (void)setHandlingSendEvent:(BOOL)handlingSendEvent {
    handlingSendEvent_ = handlingSendEvent;
}

- (void)sendEvent:(NSEvent*)event {
    BOOL wasHandlingSendEvent = handlingSendEvent_;
    handlingSendEvent_ = YES;
    @try {
        [super sendEvent:event];
    } @finally {
        handlingSendEvent_ = wasHandlingSendEvent;
    }
}
@end

extern "C" void* cef4jInitializeMacApplication() {
    NSAutoreleasePool* autoreleasePool = [[NSAutoreleasePool alloc] init];
    [Cef4jRuntimeApplication sharedApplication];
    if (![NSApp isKindOfClass:[Cef4jRuntimeApplication class]]) {
        std::fprintf(stderr, "[cef4j-runtime-server] macOS application bootstrap: unexpected NSApp class %s\n",
                     NSStringFromClass([NSApp class]).UTF8String);
        [autoreleasePool drain];
        return nullptr;
    }
    return autoreleasePool;
}

extern "C" void cef4jReleaseMacApplication(void* autoreleasePool) {
    [static_cast<NSAutoreleasePool*>(autoreleasePool) drain];
}

extern "C" void cef4jRunMacMessageLoop() {
    g_quitMessageLoop.store(false, std::memory_order_release);

    // XXX: Direct ProcessBuilder launches bypass LaunchServices on macOS; remove if startup uses LaunchServices.
    if (![NSApp isRunning]) [NSApp finishLaunching];
    while (!g_quitMessageLoop.load(std::memory_order_acquire)) {
        @autoreleasepool {
            cef_do_message_loop_work();
            CFRunLoopRunInMode(kCFRunLoopDefaultMode, 0.01, true);
        }
    }

    // XXX: CEF 150 posts AppKit teardown after the final browser callback; remove when shutdown is synchronous.
    for (int i = 0; i < 10; ++i) {
        @autoreleasepool {
            cef_do_message_loop_work();
            CFRunLoopRunInMode(kCFRunLoopDefaultMode, 0.001, true);
        }
    }
}

extern "C" void cef4jQuitMacMessageLoop() {
    g_quitMessageLoop.store(true, std::memory_order_release);
    CFRunLoopWakeUp(CFRunLoopGetMain());
}
