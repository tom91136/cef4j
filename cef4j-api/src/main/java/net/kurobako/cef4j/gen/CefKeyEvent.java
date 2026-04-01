// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Structure representing keyboard event information.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef struct _cef_key_event_t {
 *   size_t size;
 *   cef_key_event_type_t type;
 *   unsigned int modifiers;
 *   int windows_key_code;
 *   int native_key_code;
 *   int is_system_key;
 *   char16_t character;
 *   char16_t unmodified_character;
 *   int focus_on_editable_field;
 * } cef_key_event_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h:2354</a>
 */
public final class CefKeyEvent {

    // Native struct size — set by JNI, not user-modifiable.
    @SuppressWarnings("FieldMayBeFinal")
    private volatile long size = -1;

    public final CefKeyEventType type;
    public final int modifiers;
    public final int windowsKeyCode;
    public final int nativeKeyCode;
    public final int isSystemKey;
    public final char character;
    public final char unmodifiedCharacter;
    public final int focusOnEditableField;

    public CefKeyEvent(
            CefKeyEventType type,
            int modifiers,
            int windowsKeyCode,
            int nativeKeyCode,
            int isSystemKey,
            char character,
            char unmodifiedCharacter,
            int focusOnEditableField) {
        this.type = type;
        this.modifiers = modifiers;
        this.windowsKeyCode = windowsKeyCode;
        this.nativeKeyCode = nativeKeyCode;
        this.isSystemKey = isSystemKey;
        this.character = character;
        this.unmodifiedCharacter = unmodifiedCharacter;
        this.focusOnEditableField = focusOnEditableField;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefKeyEvent)) return false;
        CefKeyEvent other = (CefKeyEvent) obj;
        return java.util.Objects.equals(this.type, other.type)
                && this.modifiers == other.modifiers
                && this.windowsKeyCode == other.windowsKeyCode
                && this.nativeKeyCode == other.nativeKeyCode
                && this.isSystemKey == other.isSystemKey
                && java.util.Objects.equals(this.character, other.character)
                && java.util.Objects.equals(this.unmodifiedCharacter, other.unmodifiedCharacter)
                && this.focusOnEditableField == other.focusOnEditableField;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(
                type,
                modifiers,
                windowsKeyCode,
                nativeKeyCode,
                isSystemKey,
                character,
                unmodifiedCharacter,
                focusOnEditableField);
    }

    @Override
    public String toString() {
        return "CefKeyEvent{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", " + "type=" + type + ", "
                + "modifiers=" + modifiers + ", " + "windowsKeyCode=" + windowsKeyCode + ", " + "nativeKeyCode="
                + nativeKeyCode + ", " + "isSystemKey=" + isSystemKey + ", " + "character=" + character + ", "
                + "unmodifiedCharacter=" + unmodifiedCharacter + ", " + "focusOnEditableField=" + focusOnEditableField
                + "}";
    }
}
