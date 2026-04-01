// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Type-safe wrapper for CEF C enum values.
 *
 * <p>Known constants are pre-allocated as {@code static final} fields. Unknown or composite values can be created via
 * the {@code of(long)} factory without data loss.
 *
 * @param <T> the concrete enum type (self-referential bound for type safety)
 */
public interface CefEnum<T extends CefEnum<T>> {

    /** The underlying C enum numeric value. */
    long value();

    /** The original C expression (e.g., {@code "1 << 3"}), or the numeric string for unknown values. */
    String expr();

    /** The constant name (e.g., {@code "CEF_FOO"}), or a descriptive name for unknown values. */
    String name();
}
