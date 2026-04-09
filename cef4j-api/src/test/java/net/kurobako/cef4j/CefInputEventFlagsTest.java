package net.kurobako.cef4j;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CefInputEventFlagsTest {
    @Test
    void baseModifiersSetsExpectedBits() {
        int modifiers = CefInputEventFlags.baseModifiers(true, true, false, true);

        assertThat(modifiers)
                .isEqualTo(CefInputEventFlags.SHIFT_DOWN
                        | CefInputEventFlags.CONTROL_DOWN
                        | CefInputEventFlags.COMMAND_DOWN);
    }

    @Test
    void withMouseButtonsAddsOnlyPressedButtons() {
        int modifiers = CefInputEventFlags.withMouseButtons(CefInputEventFlags.SHIFT_DOWN, true, false, true);

        assertThat(modifiers)
                .isEqualTo(CefInputEventFlags.SHIFT_DOWN
                        | CefInputEventFlags.LEFT_MOUSE_BUTTON
                        | CefInputEventFlags.RIGHT_MOUSE_BUTTON);
    }

    @Test
    void withKeyLocationAddsLeftAndRightBits() {
        int modifiers = CefInputEventFlags.withKeyLocation(CefInputEventFlags.ALT_DOWN, true, true);

        assertThat(modifiers)
                .isEqualTo(CefInputEventFlags.ALT_DOWN | CefInputEventFlags.IS_LEFT | CefInputEventFlags.IS_RIGHT);
    }
}
