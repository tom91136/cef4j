package net.kurobako.cef4j.webdriver;

/** JSON null singleton. */
public final class JsonNull extends JsonElement {
    public static final JsonNull INSTANCE = new JsonNull();

    private JsonNull() {}

    @Override
    public JsonElement deepCopy() {
        return this;
    }
}
