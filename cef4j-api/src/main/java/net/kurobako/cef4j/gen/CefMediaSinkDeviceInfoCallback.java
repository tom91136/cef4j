// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Callback interface for CefMediaSink::GetDeviceInfo. The methods of this class will be called on the browser process
 * UI thread.
 */
public interface CefMediaSinkDeviceInfoCallback {

    /** Method that will be executed asyncronously once device information has been retrieved. */
    default void onMediaSinkDeviceInfo(long deviceInfo) {}
}
