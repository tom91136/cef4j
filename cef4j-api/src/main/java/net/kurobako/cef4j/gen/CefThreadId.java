// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Existing thread IDs. */
public enum CefThreadId {

    /**
     * The main thread in the browser. This will be the same as the main application thread if CefInitialize() is called
     * with a CefSettings.multi_threaded_message_loop value of false. Do not perform blocking tasks on this thread. All
     * tasks posted after CefBrowserProcessHandler::OnContextInitialized() and before CefShutdown() are guaranteed to
     * run. This thread will outlive all other CEF threads.
     */
    TID_UI(0L),
    /**
     * Used for blocking tasks like file system access where the user won't notice if the task takes an arbitrarily long
     * time to complete. All tasks posted after CefBrowserProcessHandler::OnContextInitialized() and before
     * CefShutdown() are guaranteed to run.
     */
    TID_FILE_BACKGROUND(1L),
    /**
     * Used for blocking tasks like file system access that affect UI or responsiveness of future user interactions. Do
     * not use if an immediate response to a user interaction is expected. All tasks posted after
     * CefBrowserProcessHandler::OnContextInitialized() and before CefShutdown() are guaranteed to run. Examples: -
     * Updating the UI to reflect progress on a long task. - Loading data that might be shown in the UI after a future
     * user interaction.
     */
    TID_FILE_USER_VISIBLE(2L),
    /**
     * Used for blocking tasks like file system access that affect UI immediately after a user interaction. All tasks
     * posted after CefBrowserProcessHandler::OnContextInitialized() and before CefShutdown() are guaranteed to run.
     * Example: Generating data shown in the UI immediately after a click.
     */
    TID_FILE_USER_BLOCKING(3L),
    /** Used to launch and terminate browser processes. */
    TID_PROCESS_LAUNCHER(4L),
    /**
     * Used to process IPC and network messages. Do not perform blocking tasks on this thread. All tasks posted after
     * CefBrowserProcessHandler::OnContextInitialized() and before CefShutdown() are guaranteed to run.
     */
    TID_IO(5L),
    /**
     * The main thread in the renderer. Used for all WebKit and V8 interaction. Tasks may be posted to this thread after
     * CefRenderProcessHandler::OnWebKitInitialized but are not guaranteed to run before sub-process termination
     * (sub-processes may be killed at any time without warning).
     */
    TID_RENDERER(6L),
    TID_NUM_VALUES(7L),
    UNKNOWN(-1L);

    public final long value;

    CefThreadId(long v) {
        this.value = v;
    }

    public static CefThreadId fromLong(long v) {
        for (CefThreadId e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
