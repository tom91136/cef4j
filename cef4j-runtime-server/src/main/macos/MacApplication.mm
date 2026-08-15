// Copyright (c) 2026 Tom Lin. All rights reserved.

#import <AppKit/AppKit.h>

#include <cstdio>

// Keep these protocol declarations in C/Objective-C territory. Including
// cef_application_mac.h also pulls the C++ wrapper API, whose language-level
// requirement is newer than the C API runtime server's C++17 floor in recent
// CEF distributions.
@protocol CrAppProtocol
- (BOOL)isHandlingSendEvent;
@end

@protocol CrAppControlProtocol <CrAppProtocol>
- (void)setHandlingSendEvent:(BOOL)handlingSendEvent;
@end

@protocol CefAppProtocol <CrAppControlProtocol>
@end

// CEF's macOS message pump integrates with NSApplication. Creating the required
// CefAppProtocol implementation before cef_initialize keeps UI-thread tasks,
// renderer callbacks and orderly shutdown moving even when the application is
// launched directly by a non-GUI parent such as Maven or a service supervisor.
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
    // Match CEF's C API sample by keeping an Objective-C autorelease pool alive
    // for the complete browser-process lifetime, including CEF initialization,
    // the native message loop and shutdown.
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
