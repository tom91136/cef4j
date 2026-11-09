// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-types
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Generic callback interface used for managing the lifespan of a registration.
 *
 * <p>Definition generated from cef_registration_capi.h
 *
 * <pre>typedef struct _cef_registration_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_registration_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__registration_8h.html">cef_registration.h:43</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-types -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@NullableBoundary("generated external ABI")
public interface CefRegistration extends CefLibraryObject {

    final class NativePeer extends net.kurobako.cef4j.peer.NativeObject implements CefRegistration {
        NativePeer(long ptr) {
            super("CefRegistration", ptr, NativePeer::release0);
        }

        private static native void release0(long ptr);
    }
}
