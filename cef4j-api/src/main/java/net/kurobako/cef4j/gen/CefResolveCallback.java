// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/** Callback interface for CefRequestContext::ResolveHost. */
public interface CefResolveCallback {

    /**
     * Called on the UI thread after the ResolveHost request has completed. |result| will be the result code.
     * |resolved_ips| will be the list of resolved IP addresses or empty if the resolution failed.
     *
     * @param resolvedIps may be null
     */
    default void onResolveCompleted(@Nonnull CefErrorcode result, @Nullable java.util.List<String> resolvedIps) {}
}
