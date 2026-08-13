package net.kurobako.cef4j.ipc.session;

import javax.annotation.Nullable;

/**
 * Polymorphic JavaScript evaluation result. Mirrors the {@code valueKind} discriminator from the V8 eval wire response:
 * null/undefined, bool, int, double, string, or error. Objects, arrays, and functions arrive as a JSON-stringified
 * payload in the string slot — the renderer-side eval wraps complex values with {@code JSON.stringify} so callers know
 * to {@code JSON.parse} when they need structured data.
 *
 * <p>Use the type predicates ({@link #isInt}, {@link #isString}, etc.) before calling the typed accessors; the typed
 * accessors throw {@link IllegalStateException} on a wrong-kind read so callers can't silently miss a type mismatch.
 */
public final class JsResult {

    public enum Kind {
        NULL_OR_UNDEFINED,
        BOOL,
        INT,
        DOUBLE,
        STRING,
        ERROR
    }

    private final Kind kind;
    private final boolean boolValue;
    private final int intValue;
    private final double doubleValue;

    @Nullable
    private final String stringValue;

    @Nullable
    private final String errorMessage;

    private JsResult(
            Kind kind,
            boolean boolValue,
            int intValue,
            double doubleValue,
            @Nullable String stringValue,
            @Nullable String errorMessage) {
        this.kind = kind;
        this.boolValue = boolValue;
        this.intValue = intValue;
        this.doubleValue = doubleValue;
        this.stringValue = stringValue;
        this.errorMessage = errorMessage;
    }

    /**
     * Constructs from the wire fields. Matches the order/encoding of {@code EvaluateJavascriptResponse}: the server
     * packs a single {@code int64} for the double bits via {@link Double#longBitsToDouble} so the value survives intact
     * across both encodings.
     */
    public static JsResult fromWire(
            int valueKind,
            boolean boolValue,
            int intValue,
            long doubleBits,
            @Nullable String stringValue,
            @Nullable String errorMessage) {
        Kind kind;
        switch (valueKind) {
            case 0:
                kind = Kind.NULL_OR_UNDEFINED;
                break;
            case 1:
                kind = Kind.BOOL;
                break;
            case 2:
                kind = Kind.INT;
                break;
            case 3:
                kind = Kind.DOUBLE;
                break;
            case 4:
                kind = Kind.STRING;
                break;
            case 5:
                kind = Kind.ERROR;
                break;
            default:
                throw new IllegalArgumentException("unknown valueKind: " + valueKind);
        }
        return new JsResult(kind, boolValue, intValue, Double.longBitsToDouble(doubleBits), stringValue, errorMessage);
    }

    public Kind kind() {
        return kind;
    }

    public boolean isNull() {
        return kind == Kind.NULL_OR_UNDEFINED;
    }

    public boolean isBool() {
        return kind == Kind.BOOL;
    }

    public boolean isInt() {
        return kind == Kind.INT;
    }

    public boolean isDouble() {
        return kind == Kind.DOUBLE;
    }

    public boolean isString() {
        return kind == Kind.STRING;
    }

    public boolean isError() {
        return kind == Kind.ERROR;
    }

    public boolean asBool() {
        if (kind != Kind.BOOL) throw new IllegalStateException("not a bool: " + kind);
        return boolValue;
    }

    public int asInt() {
        if (kind != Kind.INT) throw new IllegalStateException("not an int: " + kind);
        return intValue;
    }

    public double asDouble() {
        if (kind != Kind.DOUBLE) throw new IllegalStateException("not a double: " + kind);
        return doubleValue;
    }

    /**
     * String value. For complex JS values (objects, arrays, functions) this is a {@code JSON.stringify} of the result —
     * caller can {@code JSON.parse} or use a JSON library to reconstruct.
     */
    public String asString() {
        if (kind != Kind.STRING) throw new IllegalStateException("not a string: " + kind);
        if (stringValue == null) throw new IllegalStateException("string slot was null");
        return stringValue;
    }

    public String errorMessage() {
        if (kind != Kind.ERROR) throw new IllegalStateException("not an error: " + kind);
        if (errorMessage == null) throw new IllegalStateException("error slot was null");
        return errorMessage;
    }

    /**
     * Untyped accessor — returns Boolean/Integer/Double/String/null based on {@link #kind}, or throws if the result is
     * an error. Useful for one-off callers that don't care about strict typing.
     */
    @Nullable
    public Object value() {
        switch (kind) {
            case NULL_OR_UNDEFINED:
                return null;
            case BOOL:
                return boolValue;
            case INT:
                return intValue;
            case DOUBLE:
                return doubleValue;
            case STRING:
                return stringValue;
            case ERROR:
                throw new IllegalStateException("eval failed: " + errorMessage);
            default:
                throw new IllegalStateException("unhandled kind: " + kind);
        }
    }

    /**
     * Best-effort coercion to a single string. Mirrors what most callers want from {@code evaluateJavascript}:
     * primitives become their {@code toString} form, null becomes empty, errors throw. Centralises the
     * {@code valueKind} switch so wrappers don't reimplement it (and silently diverge — see e.g. the intentional but
     * documented string-quoting divergence between the in-process and IPC backends).
     */
    public String coerceToString() {
        switch (kind) {
            case NULL_OR_UNDEFINED:
                return "";
            case BOOL:
                return boolValue ? "true" : "false";
            case INT:
                return Integer.toString(intValue);
            case DOUBLE:
                return Double.toString(doubleValue);
            case STRING:
                return stringValue == null ? "" : stringValue;
            case ERROR:
                throw new RuntimeException("JS error: " + errorMessage);
            default:
                return "";
        }
    }

    @Override
    public String toString() {
        switch (kind) {
            case NULL_OR_UNDEFINED:
                return "JsResult.null";
            case BOOL:
                return "JsResult.bool(" + boolValue + ")";
            case INT:
                return "JsResult.int(" + intValue + ")";
            case DOUBLE:
                return "JsResult.double(" + doubleValue + ")";
            case STRING:
                return "JsResult.string(" + stringValue + ")";
            case ERROR:
                return "JsResult.error(" + errorMessage + ")";
            default:
                return "JsResult.<unknown>";
        }
    }
}
