// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Implement this interface to receive accessibility notification when accessibility events have been registered. The
 * methods of this class will be called on the UI thread.
 */
public interface CefAccessibilityHandler {

    /** Called after renderer process sends accessibility tree changes to the browser process. */
    default void onAccessibilityTreeChange(long value) {}

    /** Called after renderer process sends accessibility location changes to the browser process. */
    default void onAccessibilityLocationChange(long value) {}
}
