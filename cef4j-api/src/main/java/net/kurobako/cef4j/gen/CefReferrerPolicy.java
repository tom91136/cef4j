// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Policy for how the Referrer HTTP header value will be sent during navigation. If the `--no-referrers` command-line flag is specified then the policy value will be ignored and the Referrer value will never be sent. Must be kept synchronized with net::URLRequest::ReferrerPolicy from Chromium.
 * <p>Definition generated from cef_types.h
 * <pre>typedef enum {
 *   REFERRER_POLICY_CLEAR_REFERRER_ON_TRANSITION_FROM_SECURE_TO_INSECURE = 0,
 *   REFERRER_POLICY_DEFAULT = REFERRER_POLICY_CLEAR_REFERRER_ON_TRANSITION_FROM_SECURE_TO_INSECURE,
 *   REFERRER_POLICY_REDUCE_REFERRER_GRANULARITY_ON_TRANSITION_CROSS_ORIGIN = 1,
 *   REFERRER_POLICY_ORIGIN_ONLY_ON_TRANSITION_CROSS_ORIGIN = 2,
 *   REFERRER_POLICY_NEVER_CLEAR_REFERRER = 3,
 *   ...
 * } cef_referrer_policy_t;</pre>
 * <p>Possible values: {@link Kind#CLEAR_REFERRER_ON_TRANSITION_FROM_SECURE_TO_INSECURE}, {@link Kind#DEFAULT}, {@link Kind#REDUCE_REFERRER_GRANULARITY_ON_TRANSITION_CROSS_ORIGIN}, {@link Kind#ORIGIN_ONLY_ON_TRANSITION_CROSS_ORIGIN}, {@link Kind#NEVER_CLEAR_REFERRER}, {@link Kind#ORIGIN}, {@link Kind#CLEAR_REFERRER_ON_TRANSITION_CROSS_ORIGIN}, {@link Kind#ORIGIN_CLEAR_ON_TRANSITION_FROM_SECURE_TO_INSECURE}, {@link Kind#NO_REFERRER}, {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public final class CefReferrerPolicy implements CefEnum<CefReferrerPolicy> {

    /** Known constants for {@link CefReferrerPolicy}. */
    public enum Kind {
        /** Clear the referrer header if the header value is HTTPS but the request destination is HTTP. This is the default behavior.  */
        CLEAR_REFERRER_ON_TRANSITION_FROM_SECURE_TO_INSECURE(0, "0", "REFERRER_POLICY_CLEAR_REFERRER_ON_TRANSITION_FROM_SECURE_TO_INSECURE"),
        DEFAULT(0L, "REFERRER_POLICY_CLEAR_REFERRER_ON_TRANSITION_FROM_SECURE_TO_INSECURE", "REFERRER_POLICY_DEFAULT"),
        /** A slight variant on CLEAR_REFERRER_ON_TRANSITION_FROM_SECURE_TO_INSECURE: If the request destination is HTTP, an HTTPS referrer will be cleared. If the request's destination is cross-origin with the referrer (but does not downgrade), the referrer's granularity will be stripped down to an origin rather than a full URL. Same-origin requests will send the full referrer.  */
        REDUCE_REFERRER_GRANULARITY_ON_TRANSITION_CROSS_ORIGIN(1, "1", "REFERRER_POLICY_REDUCE_REFERRER_GRANULARITY_ON_TRANSITION_CROSS_ORIGIN"),
        /** Strip the referrer down to an origin when the origin of the referrer is different from the destination's origin.  */
        ORIGIN_ONLY_ON_TRANSITION_CROSS_ORIGIN(2, "2", "REFERRER_POLICY_ORIGIN_ONLY_ON_TRANSITION_CROSS_ORIGIN"),
        /** Never change the referrer.  */
        NEVER_CLEAR_REFERRER(3, "3", "REFERRER_POLICY_NEVER_CLEAR_REFERRER"),
        /** Strip the referrer down to the origin regardless of the redirect location.  */
        ORIGIN(4, "4", "REFERRER_POLICY_ORIGIN"),
        /** Clear the referrer when the request's referrer is cross-origin with the request's destination.  */
        CLEAR_REFERRER_ON_TRANSITION_CROSS_ORIGIN(5, "5", "REFERRER_POLICY_CLEAR_REFERRER_ON_TRANSITION_CROSS_ORIGIN"),
        /** Strip the referrer down to the origin, but clear it entirely if the referrer value is HTTPS and the destination is HTTP.  */
        ORIGIN_CLEAR_ON_TRANSITION_FROM_SECURE_TO_INSECURE(6, "6", "REFERRER_POLICY_ORIGIN_CLEAR_ON_TRANSITION_FROM_SECURE_TO_INSECURE"),
        /** Always clear the referrer regardless of the request destination.  */
        NO_REFERRER(7, "7", "REFERRER_POLICY_NO_REFERRER"),
        /** Always the last value in this enumeration. */
        NUM_VALUES(8, "8", "REFERRER_POLICY_NUM_VALUES");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_referrer_policy_t"}). */
        public final String name;

        Kind(long value, String expr, String name) {
            this.value = value;
            this.expr = expr;
            this.name = name;
        }

        @Override
        public String toString() {
            return name + "(expr=" + expr + ", value=" + value + ")";
        }
    }

    /** The underlying C enum numeric value. This may not correspond to any known {@link Kind}. */
    public final long value;

    private CefReferrerPolicy(long value) {
        this.value = value;
    }

    /** {@inheritDoc} */
    @Override
    public long value() {
        return value;
    }

    /** {@inheritDoc} */
    @Override
    public String expr() {
        return kind().map(k -> k.expr).orElse(String.valueOf(value));
    }

    /** {@inheritDoc} */
    @Override
    public String name() {
        return kind().map(k -> k.name).orElse("UNKNOWN(" + value + ")");
    }

    /**
     * Returns the {@link Kind} matching this value, or empty for unknown/composite values.
     * Use this for exhaustive switch over known constants.
     */
    public java.util.Optional<Kind> kind() {
        for (Kind k : Kind.VALUES) {
            if (k.value == value) return java.util.Optional.of(k);
        }
        return java.util.Optional.empty();
    }

    /** Returns an instance for the given raw value, use {@link #kind} to resolve to a concrete enum. */
    public static CefReferrerPolicy of(long v) {
        return new CefReferrerPolicy(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefReferrerPolicy of(Kind k) {
        return new CefReferrerPolicy(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefReferrerPolicy)) return false;
        return this.value == ((CefReferrerPolicy) obj).value;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(value);
    }

    @Override
    public String toString() {
        return kind().map(Kind::toString).orElse("UNKNOWN(value=" + value + ")");
    }
}
