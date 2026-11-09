// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-types
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Class that facilitates managing the browser-related tasks. The methods of this class may only be called on the UI
 * thread.
 *
 * <p>Definition generated from cef_task_manager_capi.h
 *
 * <pre>typedef struct _cef_task_manager_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_task_manager_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__task__manager_8h.html">cef_task_manager.h:45</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-types -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@NullableBoundary("generated external ABI")
public interface CefTaskManager extends CefLibraryObject {

    /**
     * Returns the number of tasks currently tracked by the task manager. Returns 0 if the method was called from the
     * incorrect thread.
     *
     * <p>Definition generated from cef_task_manager_capi.h
     *
     * <pre>size_t (CEF_CALLBACK* get_tasks_count)(struct _cef_task_manager_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__task__manager_8h.html">cef_task_manager.h:62</a>
     */
    long getTasksCount();

    /**
     * Gets the list of task IDs currently tracked by the task manager. Tasks that share the same process id will always
     * be consecutive. The list will be sorted in a way that reflects the process tree: the browser process will be
     * first, followed by the gpu process if it exists. Related processes (e.g., a subframe process and its parent) will
     * be kept together if possible. Callers can expect this ordering to be stable when a process is added or removed.
     * The task IDs are unique within the application lifespan. Returns {@code false} if the method was called from the
     * incorrect thread.
     *
     * <p><b>The C API exposes this as a two-pass pattern: first call {@link #getTasksCount()} to obtain the count, then
     * allocate and populate the array. This method performs both steps and returns the result directly.</b>
     *
     * <p>Definition generated from cef_task_manager_capi.h
     *
     * <pre>int64_t* (CEF_CALLBACK* get_task_ids_list)(struct _cef_task_manager_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__task__manager_8h.html">cef_task_manager.h:69</a>
     */
    long[] getTaskIdsList();

    /**
     * Gets information about the task with {@code task_id}. Returns {@code true} if the information about the task was
     * successfully retrieved and {@code false} if the {@code task_id} is invalid or the method was called from the
     * incorrect thread.
     *
     * <p>Definition generated from cef_task_manager_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* get_task_info)(struct _cef_task_manager_t* self, int64_t task_id, struct _cef_task_info_t* info);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__task__manager_8h.html">cef_task_manager.h:82</a>
     */
    boolean getTaskInfo(long taskId, CefTaskInfo.Mutable info);

    /**
     * Attempts to terminate a task with {@code task_id}. Returns {@code false} if the {@code task_id} is invalid, the
     * call is made from an incorrect thread, or if the task cannot be terminated.
     *
     * <p>Definition generated from cef_task_manager_capi.h
     *
     * <pre>int (CEF_CALLBACK* kill_task)(struct _cef_task_manager_t* self, int64_t task_id);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__task__manager_8h.html">cef_task_manager.h:91</a>
     */
    boolean killTask(long taskId);

    /**
     * Returns the task ID associated with the main task for {@code browser_id} (value from
     * {@link net.kurobako.cef4j.gen.CefBrowser#getIdentifier()}). Returns -1 if {@code browser_id} is invalid, does not
     * currently have an associated task, or the method was called from the incorrect thread.
     *
     * <p>Definition generated from cef_task_manager_capi.h
     *
     * <pre>int64_t (CEF_CALLBACK* get_task_id_for_browser_id)(struct _cef_task_manager_t* self, int browser_id);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__task__manager_8h.html">cef_task_manager.h:99</a>
     */
    long getTaskIdForBrowserId(int browserId);
    /**
     * Returns the global task manager object. Returns {@code null} if the method was called from the incorrect thread.
     *
     * <p>Definition generated from cef_task_manager_capi.h
     *
     * <pre>CEF_EXPORT cef_task_manager_t* cef_task_manager_get(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__task__manager_8h.html">cef_task_manager.h:55</a>
     */
    static Optional<CefTaskManager> get() {
        return Optional.ofNullable(NativePeer.get0());
    }

    final class NativePeer extends net.kurobako.cef4j.peer.NativeObject implements CefTaskManager {
        NativePeer(long ptr) {
            super("CefTaskManager", ptr, NativePeer::release0);
        }

        private static native void release0(long ptr);

        @Override
        public long getTasksCount() {
            checkNotClosed();
            return getTasksCount0(nativePtr);
        }

        @Override
        public long[] getTaskIdsList() {
            checkNotClosed();
            return getTaskIdsList0(nativePtr);
        }

        @Override
        public boolean getTaskInfo(long taskId, CefTaskInfo.Mutable info) {
            checkNotClosed();
            return getTaskInfo0(nativePtr, taskId, info);
        }

        @Override
        public boolean killTask(long taskId) {
            checkNotClosed();
            return killTask0(nativePtr, taskId);
        }

        @Override
        public long getTaskIdForBrowserId(int browserId) {
            checkNotClosed();
            return getTaskIdForBrowserId0(nativePtr, browserId);
        }

        static native long getTasksCount0(long self);

        static native long[] getTaskIdsList0(long self);

        static native boolean getTaskInfo0(long self, long taskId, CefTaskInfo.Mutable info);

        static native boolean killTask0(long self, long taskId);

        static native long getTaskIdForBrowserId0(long self, int browserId);

        static native CefTaskManager get0();
    }
}
