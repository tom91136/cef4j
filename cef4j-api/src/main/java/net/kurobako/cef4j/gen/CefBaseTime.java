// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

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
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__time_8h.html">internal/cef_time.h:46</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
@NullableBoundary("generated external ABI")
public final class CefBaseTime {

    public final long val;

    public CefBaseTime(long val) {
        this.val = val;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefBaseTime)) return false;
        CefBaseTime other = (CefBaseTime) obj;
        return this.val == other.val;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(val);
    }

    @Override
    public String toString() {
        return "CefBaseTime{" + "val=" + val + "}";
    }
}
