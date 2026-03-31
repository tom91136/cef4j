// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

public final class CefStringUtf8 {

    public final long str;
    public final long length;

    public CefStringUtf8(long str, long length) {
        this.str = str;
        this.length = length;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefStringUtf8)) return false;
        CefStringUtf8 other = (CefStringUtf8) obj;
        return java.util.Objects.equals(this.str, other.str) && this.length == other.length;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(str, length);
    }

    @Override
    public String toString() {
        return "CefStringUtf8{" + "str=" + str + ", " + "length=" + length + "}";
    }
}
