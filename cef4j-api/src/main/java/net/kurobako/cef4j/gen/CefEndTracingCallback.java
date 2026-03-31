// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;

/**
 * Implement this interface to receive notification when tracing has completed. The methods of this class will be called
 * on the browser process UI thread.
 */
public interface CefEndTracingCallback {

    /**
     * Called after all processes have sent their trace data. |tracing_file| is the path at which tracing data was
     * written. The client is responsible for deleting |tracing_file|.
     */
    default void onEndTracingComplete(@Nonnull String tracingFile) {}
}
