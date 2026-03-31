// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Policy for how the Referrer HTTP header value will be sent during navigation. If the `--no-referrers` command-line
 * flag is specified then the policy value will be ignored and the Referrer value will never be sent. Must be kept
 * synchronized with net::URLRequest::ReferrerPolicy from Chromium.
 */
public enum CefReferrerPolicy {

    /**
     * Clear the referrer header if the header value is HTTPS but the request destination is HTTP. This is the default
     * behavior.
     */
    REFERRER_POLICY_CLEAR_REFERRER_ON_TRANSITION_FROM_SECURE_TO_INSECURE(0L),
    /**
     * A slight variant on CLEAR_REFERRER_ON_TRANSITION_FROM_SECURE_TO_INSECURE: If the request destination is HTTP, an
     * HTTPS referrer will be cleared. If the request's destination is cross-origin with the referrer (but does not
     * downgrade), the referrer's granularity will be stripped down to an origin rather than a full URL. Same-origin
     * requests will send the full referrer.
     */
    REFERRER_POLICY_REDUCE_REFERRER_GRANULARITY_ON_TRANSITION_CROSS_ORIGIN(2L),
    /**
     * Strip the referrer down to an origin when the origin of the referrer is different from the destination's origin.
     */
    REFERRER_POLICY_ORIGIN_ONLY_ON_TRANSITION_CROSS_ORIGIN(3L),
    /** Never change the referrer. */
    REFERRER_POLICY_NEVER_CLEAR_REFERRER(4L),
    /** Strip the referrer down to the origin regardless of the redirect location. */
    REFERRER_POLICY_ORIGIN(5L),
    /** Clear the referrer when the request's referrer is cross-origin with the request's destination. */
    REFERRER_POLICY_CLEAR_REFERRER_ON_TRANSITION_CROSS_ORIGIN(6L),
    /**
     * Strip the referrer down to the origin, but clear it entirely if the referrer value is HTTPS and the destination
     * is HTTP.
     */
    REFERRER_POLICY_ORIGIN_CLEAR_ON_TRANSITION_FROM_SECURE_TO_INSECURE(7L),
    /** Always clear the referrer regardless of the request destination. */
    REFERRER_POLICY_NO_REFERRER(8L),
    /** Always the last value in this enumeration. */
    REFERRER_POLICY_NUM_VALUES(9L),
    UNKNOWN(-1L);

    public final long value;

    CefReferrerPolicy(long v) {
        this.value = v;
    }

    public static CefReferrerPolicy fromLong(long v) {
        for (CefReferrerPolicy e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
