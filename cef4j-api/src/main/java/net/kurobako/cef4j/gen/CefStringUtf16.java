// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import javax.annotation.Nullable;

@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public final class CefStringUtf16 {

    public final @Nullable NativePointer str;
    public final long length;

    public CefStringUtf16(@Nullable NativePointer str, long length) {
        this.str = str;
        this.length = length;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefStringUtf16)) return false;
        CefStringUtf16 other = (CefStringUtf16) obj;
        return java.util.Objects.equals(this.str, other.str)
                    && this.length == other.length;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(str, length);
    }

    @Override
    public String toString() {
        return "CefStringUtf16{" + "str=" + str + ", " + "length=" + length + "}";
    }
}
