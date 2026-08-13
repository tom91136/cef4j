package net.kurobako.cef4j.webdriver;

import java.util.Objects;

/** JSON string, number, or boolean. */
public final class JsonPrimitive extends JsonElement {
    private final Object value;

    public JsonPrimitive(String value) {
        this.value = Objects.requireNonNull(value, "value");
    }

    public JsonPrimitive(Number value) {
        this.value = Objects.requireNonNull(value, "value");
    }

    public JsonPrimitive(Boolean value) {
        this.value = Objects.requireNonNull(value, "value");
    }

    public boolean isString() {
        return value instanceof String;
    }

    public boolean isNumber() {
        return value instanceof Number;
    }

    public boolean isBoolean() {
        return value instanceof Boolean;
    }

    public Object value() {
        return value;
    }

    @Override
    public String string() {
        return String.valueOf(value);
    }

    @Override
    public int intValue() {
        return isNumber() ? ((Number) value).intValue() : Integer.parseInt(string());
    }

    @Override
    public long longValue() {
        return isNumber() ? ((Number) value).longValue() : Long.parseLong(string());
    }

    @Override
    public double doubleValue() {
        return isNumber() ? ((Number) value).doubleValue() : Double.parseDouble(string());
    }

    @Override
    public boolean booleanValue() {
        return isBoolean() ? (Boolean) value : Boolean.parseBoolean(string());
    }

    @Override
    public JsonElement deepCopy() {
        return this;
    }
}
