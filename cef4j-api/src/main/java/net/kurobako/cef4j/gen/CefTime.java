// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Time information. Values should always be in UTC. */
public final class CefTime {

    public final int year;
    public final int month;
    public final int dayOfWeek;
    public final int dayOfMonth;
    public final int hour;
    public final int minute;
    public final int second;
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
