package net.kurobako.cef4j.webdriver;

/** JSON-library-neutral value used by the WebDriver engine and extension SPI. */
public abstract class JsonElement {
    public final boolean isObject() {
        return this instanceof JsonObject;
    }

    public final boolean isArray() {
        return this instanceof JsonArray;
    }

    public final boolean isPrimitive() {
        return this instanceof JsonPrimitive;
    }

    public final boolean isNull() {
        return this == JsonNull.INSTANCE;
    }

    public final JsonObject asObject() {
        return (JsonObject) this;
    }

    public final JsonArray asArray() {
        return (JsonArray) this;
    }

    public final JsonPrimitive asPrimitive() {
        return (JsonPrimitive) this;
    }

    public String string() {
        return asPrimitive().string();
    }

    public int intValue() {
        return asPrimitive().intValue();
    }

    public long longValue() {
        return asPrimitive().longValue();
    }

    public double doubleValue() {
        return asPrimitive().doubleValue();
    }

    public boolean booleanValue() {
        return asPrimitive().booleanValue();
    }

    public abstract JsonElement deepCopy();
}
