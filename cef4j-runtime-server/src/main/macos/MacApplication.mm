// Copyright (c) 2026 Tom Lin. All rights reserved.

#import <AppKit/AppKit.h>

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

extern "C" void cef4jInitializeMacApplication() {
    [Cef4jRuntimeApplication sharedApplication];
}
