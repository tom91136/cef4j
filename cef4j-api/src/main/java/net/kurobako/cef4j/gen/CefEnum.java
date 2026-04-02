// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Common interface for generated wrappers around CEF enum values.
 *
 * <p>Each generated enum wrapper preserves the original numeric C value while exposing known constants through a nested
 * {@code Kind} enum. Unknown or composite values are still represented losslessly.
 *
 * <p>This allows flag sets and forward-compatible values to round-trip through the Java API without collapsing them to
 * only the known named constants.
 *
 * @param <T> the concrete generated enum wrapper type
 */
public interface CefEnum<T extends CefEnum<T>> {

    /** The underlying C enum numeric value. */
    long value();

    /** The original C expression (e.g. {@code "1 << 3"}), or the numeric string for unknown values. */
    String expr();

    /** The constant name (e.g. {@code "CEF_FOO"}), or a descriptive name for unknown values. */
    String name();
}
