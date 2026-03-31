// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** All scoped framework structures must include this structure first. */
public final class CefBaseScoped {

    public final long size;

    public CefBaseScoped(long size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefBaseScoped)) return false;
        CefBaseScoped other = (CefBaseScoped) obj;
        return this.size == other.size;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(size);
    }

    @Override
    public String toString() {
        return "CefBaseScoped{" + "size=" + size + "}";
    }
}
