// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Represents a wall clock time in UTC. Values are not guaranteed to be monotonically non-decreasing and are subject to
 * large amounts of skew. Time is stored internally as microseconds since the Windows epoch (1601). This is equivalent
 * of Chromium `base::Time` (see base/time/time.h).
 */
public final class CefBasetime {

    public final long val;

    public CefBasetime(long val) {
        this.val = val;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefBasetime)) return false;
        CefBasetime other = (CefBasetime) obj;
        return this.val == other.val;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(val);
    }

    @Override
    public String toString() {
        return "CefBasetime{" + "val=" + val + "}";
    }
}
