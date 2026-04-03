// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

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
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public interface CefTask extends CefClientHandler {

    /**
     * Method that will be executed on the target thread.
     *
     * <p>Definition generated from cef_task_capi.h
     *
     * <pre>void (CEF_CALLBACK* execute)(struct _cef_task_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__task_8h.html">cef_task.h:55</a>
     */
    default void execute() {}
}
