// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;

/**
 * Class that creates CefResourceHandler instances for handling scheme requests. The methods of this class will always
 * be called on the IO thread.
 */
public interface CefSchemeHandlerFactory {

    /**
     * Create a new backing store with allocated memory of |byte_length| bytes. The memory is uninitialized. This method
     * must be called on a thread with a valid V8 isolate. The returned object can safely be passed to other threads.
     * Returns nullptr on failure.
     */
    default long create(long browser, long frame, @Nonnull String schemeName, long request) {
        return 0L;
    }
}
