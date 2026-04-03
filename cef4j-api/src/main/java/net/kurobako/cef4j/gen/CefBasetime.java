// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Represents a wall clock time in UTC. Values are not guaranteed to be monotonically non-decreasing and are subject to
 * large amounts of skew. Time is stored internally as microseconds since the Windows epoch (1601). This is equivalent
 * of Chromium `base::Time` (see base/time/time.h).
 *
 * <p>Definition generated from internal/cef_time.h
 *
 * <pre>typedef struct _cef_basetime_t {
 *   int64_t val;
 * } cef_basetime_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__time_8h.html">internal/cef_time.h:46</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
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
