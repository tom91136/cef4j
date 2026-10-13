// macOS-only JNI hooks for MacShutdownNotificationPurgeTest. Compiled by the test into a throwaway dylib so the
// production bridge stays free of test-only entry points.

#import <AppKit/AppKit.h>
#import <Foundation/Foundation.h>

#include <jni.h>

static int gHostNotificationCount = 0;
static id gHostObserver = nil;

JNIEXPORT void JNICALL Java_net_kurobako_cef4j_MacShutdownObserverProbe_installHostObserver(JNIEnv *env, jclass clazz) {
  (void)env;
  (void)clazz;
  if (gHostObserver != nil) return;
  gHostObserver = [[NSWorkspace sharedWorkspace].notificationCenter
      addObserverForName:NSWorkspaceAccessibilityDisplayOptionsDidChangeNotification
                  object:nil
                   queue:nil
              usingBlock:^(NSNotification *notification) {
                (void)notification;
                gHostNotificationCount += 1;
              }];
  [gHostObserver retain];
}

JNIEXPORT jint JNICALL Java_net_kurobako_cef4j_MacShutdownObserverProbe_takeHostNotificationCount(JNIEnv *env, jclass clazz) {
  (void)env;
  (void)clazz;
  int count = gHostNotificationCount;
  gHostNotificationCount = 0;
  return (jint)count;
}

JNIEXPORT void JNICALL Java_net_kurobako_cef4j_MacShutdownObserverProbe_postWorkspaceAccessibilityNotification(
    JNIEnv *env, jclass clazz) {
  (void)env;
  (void)clazz;
  [[NSWorkspace sharedWorkspace].notificationCenter
      postNotificationName:NSWorkspaceAccessibilityDisplayOptionsDidChangeNotification
                    object:nil];
}
