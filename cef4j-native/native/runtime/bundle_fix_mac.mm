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
