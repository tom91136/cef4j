// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Existing thread IDs.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   TID_UI = 0,
 *   TID_FILE_BACKGROUND = 1,
 *   TID_FILE_USER_VISIBLE = 2,
 *   TID_FILE_USER_BLOCKING = 3,
 *   TID_PROCESS_LAUNCHER = 4,
 *   ...
 * } cef_thread_id_t;</pre>
 *
 * <p>Possible values: {@link Kind#UI}, {@link Kind#FILE_BACKGROUND}, {@link Kind#FILE_USER_VISIBLE},
 * {@link Kind#FILE_USER_BLOCKING}, {@link Kind#PROCESS_LAUNCHER}, {@link Kind#IO}, {@link Kind#RENDERER},
 * {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h</a>
 */
public final class CefThreadId implements CefEnum<CefThreadId> {

    /** Known constants for {@link CefThreadId}. */
    public enum Kind {
        /**
         * The main thread in the browser. This will be the same as the main application thread if CefInitialize() is
         * called with a CefSettings.multi_threaded_message_loop value of {@code false}. Do not perform blocking tasks
         * on this thread. All tasks posted after {@link CefBrowserProcessHandler#onContextInitialized()} and before
         * CefShutdown() are guaranteed to run. This thread will outlive all other CEF threads.
         */
        UI(0, "0", "TID_UI"),
        /**
         * Used for blocking tasks like file system access where the user won't notice if the task takes an arbitrarily
         * long time to complete. All tasks posted after {@link CefBrowserProcessHandler#onContextInitialized()} and
         * before CefShutdown() are guaranteed to run.
         */
        FILE_BACKGROUND(1, "1", "TID_FILE_BACKGROUND"),
        /**
         * Used for blocking tasks like file system access that affect UI or responsiveness of future user interactions.
         * Do not use if an immediate response to a user interaction is expected. All tasks posted after
         * {@link CefBrowserProcessHandler#onContextInitialized()} and before CefShutdown() are guaranteed to run.
         * Examples: - Updating the UI to reflect progress on a long task. - Loading data that might be shown in the UI
         * after a future user interaction.
         */
        FILE_USER_VISIBLE(2, "2", "TID_FILE_USER_VISIBLE"),
        /**
         * Used for blocking tasks like file system access that affect UI immediately after a user interaction. All
         * tasks posted after {@link CefBrowserProcessHandler#onContextInitialized()} and before CefShutdown() are
         * guaranteed to run. Example: Generating data shown in the UI immediately after a click.
         */
        FILE_USER_BLOCKING(3, "3", "TID_FILE_USER_BLOCKING"),
        /** Used to launch and terminate browser processes. */
        PROCESS_LAUNCHER(4, "4", "TID_PROCESS_LAUNCHER"),
        /**
         * Used to process IPC and network messages. Do not perform blocking tasks on this thread. All tasks posted
         * after {@link CefBrowserProcessHandler#onContextInitialized()} and before CefShutdown() are guaranteed to run.
         */
        IO(5, "5", "TID_IO"),
        /**
         * The main thread in the renderer. Used for all WebKit and V8 interaction. Tasks may be posted to this thread
         * after {@link CefRenderProcessHandler#onWebKitInitialized()} but are not guaranteed to run before sub-process
         * termination (sub-processes may be killed at any time without warning).
         */
        RENDERER(6, "6", "TID_RENDERER"),
        NUM_VALUES(7, "7", "TID_NUM_VALUES");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression (e.g., {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_thread_id_t"}). */
        public final String name;

        Kind(long value, String expr, String name) {
            this.value = value;
            this.expr = expr;
            this.name = name;
        }

        @Override
        public String toString() {
            return name + "(expr=" + expr + ", value=" + value + ")";
        }
    }

    /** The underlying C enum numeric value. May not correspond to any known {@link Kind}. */
    public final long value;

    private CefThreadId(long value) {
        this.value = value;
    }

    @Override
    public long value() {
        return value;
    }

    @Override
    public String expr() {
        return kind().map(k -> k.expr).orElse(String.valueOf(value));
    }

    @Override
    public String name() {
        return kind().map(k -> k.name).orElse("UNKNOWN(" + value + ")");
    }

    /**
     * Returns the {@link Kind} matching this value, or empty for unknown/composite values. Use this for exhaustive
     * switch over known constants.
     */
    public java.util.Optional<Kind> kind() {
        for (Kind k : Kind.VALUES) {
            if (k.value == value) return java.util.Optional.of(k);
        }
        return java.util.Optional.empty();
    }

    /** Returns an instance for the given raw value. No data is lost — unknown or composite values are preserved. */
    public static CefThreadId of(long v) {
        return new CefThreadId(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefThreadId of(Kind k) {
        return new CefThreadId(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefThreadId)) return false;
        return this.value == ((CefThreadId) obj).value;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(value);
    }

    @Override
    public String toString() {
        return kind().map(Kind::toString).orElse("UNKNOWN(value=" + value + ")");
    }
}
