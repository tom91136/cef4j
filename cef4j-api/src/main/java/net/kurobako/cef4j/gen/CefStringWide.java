// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * CEF string type definitions. Whomever allocates |str| is responsible for providing an appropriate |dtor|
 * implementation that will free the string in the same memory space. When reusing an existing string structure make
 * sure to call |dtor| for the old value before assigning new |str| and |dtor| values. Static strings will have a NULL
 * |dtor| value. Using the below functions if you want this managed for you.
 */
public final class CefStringWide {

    public final long str;
    public final long length;

    public CefStringWide(long str, long length) {
        this.str = str;
        this.length = length;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefStringWide)) return false;
        CefStringWide other = (CefStringWide) obj;
        return java.util.Objects.equals(this.str, other.str) && this.length == other.length;
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
