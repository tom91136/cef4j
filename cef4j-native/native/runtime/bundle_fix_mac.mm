// macOS: fix Mach port rendezvous bundle ID mismatch.
//
// The JVM runs inside a JDK bundle (e.g. "net.java.openjdk.jdk"), so
// Chromium's BaseBundleID() returns the JDK bundle identifier.  The server
// therefore registers the rendezvous service as:
//
//   "net.java.openjdk.jdk.MachPortRendezvousServer.<token>"
//
// But cef4j_launcher is a bare binary with no bundle, so BaseBundleID()
// returns "" there, and the subprocess looks up:
//
//   ".MachPortRendezvousServer.<token>"
//
// These never match -> bootstrap_look_up returns BOOTSTRAP_UNKNOWN_SERVICE.
// Additionally, a service name starting with "." is invalid on macOS.
//
// Fix: swizzle -[NSBundle bundleIdentifier] so the main bundle returns
// "cef4j" — a fixed, non-empty identifier that both the browser process
// (JVM) and subprocess (cef4j_launcher) agree on. This produces a valid
// service name: "cef4j.MachPortRendezvousServer.<token>".

#import <Foundation/Foundation.h>
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

extern "C" void cef4j_fix_main_bundle_id(void) {
    static bool applied = false;
    if (applied) return;
    applied = true;

    Method original = class_getInstanceMethod([NSBundle class], @selector(bundleIdentifier));
    Method swizzled =
            class_getInstanceMethod([NSBundle class], @selector(cef4j_bundleIdentifier));
    if (original && swizzled) {
        method_exchangeImplementations(original, swizzled);
    }
}
