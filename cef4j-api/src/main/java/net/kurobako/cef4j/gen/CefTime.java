// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Time information. Values should always be in UTC.
 *
 * <p>Definition generated from internal/cef_time.h
 *
 * <pre>typedef struct _cef_time_t {
 *   int year;
 *   int month;
 *   int day_of_week;
 *   int day_of_month;
 *   int hour;
 *   int minute;
 *   int second;
 *   int millisecond;
 * } cef_time_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__time_8h.html">internal/cef_time.h:57</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
@NullableBoundary("generated external ABI")
public final class CefTime {

    /** Four or five digit year "2007" (1601 to 30827 on Windows, 1970 to 2038 on 32-bit POSIX) */
    public final int year;
    /** 1-based month (values 1 = January, etc.) */
    public final int month;
    /** 0-based day of week (0 = Sunday, etc.) */
    public final int dayOfWeek;
    /** 1-based day of month (1-31) */
    public final int dayOfMonth;
    /** Hour within the current day (0-23) */
    public final int hour;
    /** Minute within the current hour (0-59) */
    public final int minute;
    /** Second within the current minute (0-59 plus leap seconds which may take it up to 60). */
    public final int second;
    /** Milliseconds within the current second (0-999) */
    public final int millisecond;

    public CefTime(
            int year, int month, int dayOfWeek, int dayOfMonth, int hour, int minute, int second, int millisecond) {
        this.year = year;
        this.month = month;
        this.dayOfWeek = dayOfWeek;
        this.dayOfMonth = dayOfMonth;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.millisecond = millisecond;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefTime)) return false;
        CefTime other = (CefTime) obj;
        return this.year == other.year
                && this.month == other.month
                && this.dayOfWeek == other.dayOfWeek
                && this.dayOfMonth == other.dayOfMonth
                && this.hour == other.hour
                && this.minute == other.minute
                && this.second == other.second
                && this.millisecond == other.millisecond;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(year, month, dayOfWeek, dayOfMonth, hour, minute, second, millisecond);
    }

    @Override
    public String toString() {
        return "CefTime{" + "year=" + year + ", " + "month=" + month + ", " + "dayOfWeek=" + dayOfWeek + ", "
                + "dayOfMonth=" + dayOfMonth + ", " + "hour=" + hour + ", " + "minute=" + minute + ", " + "second="
                + second + ", " + "millisecond=" + millisecond + "}";
    }
}
