// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import javax.annotation.Nullable;

/**
 * CEF string type definitions. Whomever allocates {@code str} is responsible for providing an appropriate {@code dtor} implementation that will free the string in the same memory space. When reusing an existing string structure make sure to call {@code dtor} for the old value before assigning new {@code str} and {@code dtor} values. Static strings will have a {@code null} {@code dtor} value. Using the below functions if you want this managed for you.
 * <p>Definition generated from internal/cef_string_types.h
 * <pre>typedef struct _cef_string_wide_t {
 *   void* str;
 *   size_t length;
 * } cef_string_wide_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__string__types_8h.html">internal/cef_string_types.h:62</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public final class CefStringWide {

    public final @Nullable NativePointer str;
    public final long length;

    public CefStringWide(@Nullable NativePointer str, long length) {
        this.str = str;
        this.length = length;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefStringWide)) return false;
        CefStringWide other = (CefStringWide) obj;
        return java.util.Objects.equals(this.str, other.str)
                    && this.length == other.length;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(str, length);
    }

    @Override
    public String toString() {
        return "CefStringWide{" + "str=" + str + ", " + "length=" + length + "}";
    }
}
