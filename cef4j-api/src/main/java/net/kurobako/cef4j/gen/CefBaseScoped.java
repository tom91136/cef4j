// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * All scoped framework structures must include this structure first.
 *
 * <p>Definition generated from cef_base_capi.h
 *
 * <pre>typedef struct _cef_base_scoped_t {
 *   size_t size;
 * } cef_base_scoped_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__base__capi_8h.html">cef_base_capi.h</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
@NullableBoundary("generated external ABI")
public final class CefBaseScoped {

    // Native struct size, populated by the JNI layer with sizeof(struct) as required by CEF. Not user-modifiable.
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    private volatile long size = -1;

    public CefBaseScoped() {}

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj instanceof CefBaseScoped;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash();
    }

    @Override
    public String toString() {
        return "CefBaseScoped{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + "}";
    }
}
