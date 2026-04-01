// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Implement this interface for asynchronous task execution. If the task is posted successfully and if the associated
 * message loop is still running then the Execute() method will be called on the target thread. If the task fails to
 * post then the task object may be destroyed on the source thread instead of the target thread. For this reason be
 * cautious when performing work in the task object destructor.
 *
 * <p>Definition generated from cef_task_capi.h
 *
 * <pre>typedef struct _cef_task_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_task_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__task_8h.html">cef_task.h:44</a>
 */
public interface CefTask extends CefClientHandler {

    /**
     * Handle execution of the function identified by {@code name}. {@code object} is the receiver ('this' object) of
     * the function. {@code arguments} is the list of arguments passed to the function. If execution succeeds set
     * {@code retval} to the function return value. If execution fails set {@code exception} to the exception that will
     * be thrown. Return {@code true} if execution was handled.
     *
     * <p>Definition generated from cef_task_capi.h
     *
     * <pre>void (CEF_CALLBACK* execute)(struct _cef_task_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:234</a>
     */
    default void execute() {}
}
