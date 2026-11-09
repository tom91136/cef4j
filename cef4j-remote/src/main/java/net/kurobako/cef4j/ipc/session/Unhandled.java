package net.kurobako.cef4j.ipc.session;

import java.util.Optional;
import java.util.OptionalInt;

/** The answers of a handler method that has no opinion, which CEF reads as its default behaviour. */
public final class Unhandled {
    private Unhandled() {}

    public static Optional<Boolean> bool() {
        return Optional.empty();
    }

    public static OptionalInt integer() {
        return OptionalInt.empty();
    }
}
