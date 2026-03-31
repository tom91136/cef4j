// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Structure representing keyboard event information. */
public final class CefKeyEvent {

    public final long size;
    public final CefKeyEventType type;
    public final int modifiers;
    public final int windowsKeyCode;
    public final int nativeKeyCode;
    public final int isSystemKey;
    public final int character;
    public final int unmodifiedCharacter;
    public final int focusOnEditableField;

    public CefKeyEvent(
            long size,
            CefKeyEventType type,
            int modifiers,
            int windowsKeyCode,
            int nativeKeyCode,
            int isSystemKey,
            int character,
            int unmodifiedCharacter,
            int focusOnEditableField) {
        this.size = size;
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
        return this.size == other.size
                && java.util.Objects.equals(this.type, other.type)
                && this.modifiers == other.modifiers
                && this.windowsKeyCode == other.windowsKeyCode
                && this.nativeKeyCode == other.nativeKeyCode
                && this.isSystemKey == other.isSystemKey
                && this.character == other.character
                && this.unmodifiedCharacter == other.unmodifiedCharacter
                && this.focusOnEditableField == other.focusOnEditableField;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(
                size,
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
        return "CefKeyEvent{" + "size=" + size + ", " + "type=" + type + ", " + "modifiers=" + modifiers + ", "
                + "windowsKeyCode=" + windowsKeyCode + ", " + "nativeKeyCode=" + nativeKeyCode + ", " + "isSystemKey="
                + isSystemKey + ", " + "character=" + character + ", " + "unmodifiedCharacter=" + unmodifiedCharacter
                + ", " + "focusOnEditableField=" + focusOnEditableField + "}";
    }
}
