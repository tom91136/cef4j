// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

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
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__task_8h.html">cef_task.h:44</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
@NullableBoundary("generated external ABI")
public interface CefTask extends CefClientHandler {

    /**
     * Method that will be executed on the target thread.
     *
     * <p>Definition generated from cef_task_capi.h
     *
     * <pre>void (CEF_CALLBACK* execute)(struct _cef_task_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__task_8h.html">cef_task.h:55</a>
     */
    default void execute() {}
    /**
     * Composite that fans callbacks out to every registered delegate. {@code void} methods invoke all delegates in
     * order; {@code boolean} methods short-circuit on the first {@code true}; handler-returning {@code Optional}s
     * collect every non-empty delegate and wrap them in the handler's own {@code Delegating} wrapper; other
     * {@code Optional}s pick the first non-empty; any other return type yields the first delegate's value.
     */
    class Delegating implements CefTask {
        private final java.util.List<CefTask> delegates;

        public Delegating(java.util.List<CefTask> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public void execute() {
            for (CefTask d : delegates) d.execute();
        }
    }
}
