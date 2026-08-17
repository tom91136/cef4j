// macOS: fix Mach port rendezvous bundle ID mismatch.
//
// The JVM runs inside a JDK bundle (e.g. "net.java.openjdk.java"), so
// Chromium's BaseBundleID() returns the JDK bundle identifier.  The server
// therefore registers the rendezvous service as:
//
//   "net.java.openjdk.java.MachPortRendezvousServer.<pid>"
//
// But cef4j_launcher is a bare binary with no bundle, so BaseBundleID()
// returns "" there, and the subprocess looks up:
//
//   ".MachPortRendezvousServer.<pid>"
//
// These never match -> bootstrap_look_up returns BOOTSTRAP_UNKNOWN_SERVICE.
// Additionally, a service name starting with "." is invalid on macOS.
//
// Fix: patch the main bundle's info dictionary at the CoreFoundation level
// so CFBundleGetIdentifier() returns "cef4j".  Chromium's BaseBundleID()
// calls CFBundleGetIdentifier() (not the ObjC -[NSBundle bundleIdentifier]),
// so we must modify the CFBundle info dictionary directly.  Also swizzle
// -[NSBundle bundleIdentifier] for any code paths that go through ObjC.
//
// This produces a valid, matching service name in both browser and subprocess:
//   "cef4j.MachPortRendezvousServer.<pid>"

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
    // After swizzling, this selector points to the original implementation.
    return [self cef4j_bundleIdentifier];
}
@end

// Force-stop [NSApp run] by calling [NSApp stop:] and posting a dummy event to
// wake the run loop.  Used as a fallback when cef_quit_message_loop()'s internal
// task runner cannot deliver the quit task (e.g. because AWT/Glass event sources
// interfere with CEF's CFRunLoop sources).
extern "C" void cef4j_stop_nsapp(void) {
    NSApplication* app = [NSApplication sharedApplication];
    [app stop:nil];
    // [NSApp stop:] only takes effect after the current event finishes and the
    // run loop dequeues the next event.  Post a dummy event to ensure there IS
    // a next event to dequeue.
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

// Bring the Java process to the foreground.  Called via dispatch_after from
// the init block so the activation happens after [NSApp run] is servicing
// events and after the caller has had time to create AWT/JFX windows.
extern "C" void cef4j_activate_app(void) {
    NSApplication* app = [NSApplication sharedApplication];
    // Ensure we're a regular app that can own the menubar and appear in Dock.
    // Without this, a bare JVM process may have NSApplicationActivationPolicyProhibited
    // and activateIgnoringOtherApps: will be silently ignored.
    if ([app activationPolicy] != NSApplicationActivationPolicyRegular) {
        [app setActivationPolicy:NSApplicationActivationPolicyRegular];
    }
    [app activateIgnoringOtherApps:YES];
}

extern "C" void cef4j_fix_main_bundle_id(void) {
    static bool applied = false;
    if (applied) return;
    applied = true;

    // 1. Patch CFBundle info dictionary so CFBundleGetIdentifier() returns "cef4j".
    //    This is what Chromium's BaseBundleID() actually reads.
    CFBundleRef mainBundle = CFBundleGetMainBundle();
    if (mainBundle) {
        CFMutableDictionaryRef infoDict = (CFMutableDictionaryRef)CFBundleGetInfoDictionary(mainBundle);
        if (infoDict) {
            CFDictionarySetValue(infoDict, kCFBundleIdentifierKey, CFSTR("cef4j"));
        }
    }

    // 2. Also swizzle ObjC -[NSBundle bundleIdentifier] for any code paths
    //    that use the Foundation API instead of CoreFoundation.
    Method original = class_getInstanceMethod([NSBundle class], @selector(bundleIdentifier));
    Method swizzled =
            class_getInstanceMethod([NSBundle class], @selector(cef4j_bundleIdentifier));
    if (original && swizzled) {
        method_exchangeImplementations(original, swizzled);
    }
}
