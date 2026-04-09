package net.kurobako.cef4j;

/** Shared utilities for building the CEF input-event modifier bitmask. */
@SuppressWarnings("unused")
public final class CefInputEventFlags {
    public static final int SHIFT_DOWN = 1 << 1;
    public static final int CONTROL_DOWN = 1 << 2;
    public static final int ALT_DOWN = 1 << 3;
    public static final int LEFT_MOUSE_BUTTON = 1 << 4;
    public static final int MIDDLE_MOUSE_BUTTON = 1 << 5;
    public static final int RIGHT_MOUSE_BUTTON = 1 << 6;
    public static final int COMMAND_DOWN = 1 << 7;
    public static final int IS_LEFT = 1 << 10;
    public static final int IS_RIGHT = 1 << 11;

    private CefInputEventFlags() {}

    public static int baseModifiers(boolean shift, boolean control, boolean alt, boolean meta) {
        int modifiers = 0;
        if (shift) modifiers |= SHIFT_DOWN;
        if (control) modifiers |= CONTROL_DOWN;
        if (alt) modifiers |= ALT_DOWN;
        if (meta) modifiers |= COMMAND_DOWN;
        return modifiers;
    }

    public static int withMouseButtons(int modifiers, boolean leftDown, boolean middleDown, boolean rightDown) {
        if (leftDown) modifiers |= LEFT_MOUSE_BUTTON;
        if (middleDown) modifiers |= MIDDLE_MOUSE_BUTTON;
        if (rightDown) modifiers |= RIGHT_MOUSE_BUTTON;
        return modifiers;
    }

    public static int withKeyLocation(int modifiers, boolean leftLocation, boolean rightLocation) {
        if (leftLocation) modifiers |= IS_LEFT;
        if (rightLocation) modifiers |= IS_RIGHT;
        return modifiers;
    }
}
