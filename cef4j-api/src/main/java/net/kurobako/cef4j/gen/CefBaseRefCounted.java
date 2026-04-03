// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * All ref-counted framework structures must include this structure first.
 *
 * <p>Definition generated from cef_base_capi.h
 *
 * <pre>typedef struct _cef_base_ref_counted_t {
 *   size_t size;
 * } cef_base_ref_counted_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__base__capi_8h.html">cef_base_capi.h</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public final class CefBaseRefCounted {

    // Native struct size, populated by the JNI layer with sizeof(struct) as required by CEF. Not user-modifiable.
    @SuppressWarnings("FieldMayBeFinal")
    private volatile long size = -1;

    public CefBaseRefCounted() {}

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj instanceof CefBaseRefCounted;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash();
    }

    @Override
    public String toString() {
        return "CefBaseRefCounted{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + "}";
    }
}
