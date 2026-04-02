// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.Nullable;

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
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__task__manager_8h.html">cef_task_manager.h:45</a>
 */
public interface CefTaskManager extends CefLibraryObject {

    /**
     * Returns the number of tasks currently tracked by the task manager. Returns 0 if the method was called from the
     * incorrect thread.
     *
     * <p>Definition generated from cef_task_manager_capi.h
     *
     * <pre>size_t (CEF_CALLBACK* get_tasks_count)(struct _cef_task_manager_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__task__manager_8h.html">cef_task_manager.h:62</a>
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
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__task__manager_8h.html">cef_task_manager.h:69</a>
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
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__task__manager_8h.html">cef_task_manager.h:82</a>
     */
    boolean getTaskInfo(long taskId, @Nullable NativePointer info);

    /**
     * Attempts to terminate a task with {@code task_id}. Returns {@code false} if the {@code task_id} is invalid, the
     * call is made from an incorrect thread, or if the task cannot be terminated.
     *
     * <p>Definition generated from cef_task_manager_capi.h
     *
     * <pre>int (CEF_CALLBACK* kill_task)(struct _cef_task_manager_t* self, int64_t task_id);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__task__manager_8h.html">cef_task_manager.h:91</a>
     */
    boolean killTask(long taskId);

    /**
     * Returns the task ID associated with the main task for {@code browser_id} (value from
     * {@link CefBrowser#getIdentifier()}). Returns -1 if {@code browser_id} is invalid, does not currently have an
     * associated task, or the method was called from the incorrect thread.
     *
     * <p>Definition generated from cef_task_manager_capi.h
     *
     * <pre>int64_t (CEF_CALLBACK* get_task_id_for_browser_id)(struct _cef_task_manager_t* self, int browser_id);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__task__manager_8h.html">cef_task_manager.h:99</a>
     */
    long getTaskIdForBrowserId(int browserId);
    /**
     * Handle retrieval of the interceptor value identified by {@code index}. {@code object} is the receiver ('this'
     * object) of the interceptor. If retrieval succeeds, set {@code retval} to the return value. If the requested value
     * does not exist, don't set either {@code retval} or {@code exception}. If retrieval fails, set {@code exception}
     * to the exception that will be thrown. Return {@code true} if interceptor retrieval was handled, {@code false}
     * otherwise.
     *
     * <p>Definition generated from cef_task_manager_capi.h
     *
     * <pre>CEF_EXPORT cef_task_manager_t* cef_task_manager_get(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:310</a>
     */
    static Optional<CefTaskManager> get() {
        return Optional.ofNullable(NativePeer.N_Get());
    }

    final class NativePeer implements CefTaskManager, AutoCloseable {
        private final long nativePtr;
        private final java.lang.ref.Cleaner.Cleanable cleanable;
        private volatile boolean closed;

        NativePeer(long ptr) {
            this.nativePtr = ptr;
            this.cleanable = net.kurobako.cef4j.NativeCleaner.INSTANCE.register(this, new Release(ptr));
        }

        @Override
        public void close() {
            closed = true;
            cleanable.clean();
        }

        @Override
        public boolean isClosed() {
            return closed;
        }

        private void checkNotClosed() {
            if (closed) throw new IllegalStateException("CefTaskManager has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefTaskManager.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefTaskManager 0x{}", Long.toHexString(ptr));
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

        @Override
        public long getTasksCount() {
            checkNotClosed();
            return N_GetTasksCount(nativePtr);
        }

        @Override
        public long[] getTaskIdsList() {
            checkNotClosed();
            return N_GetTaskIdsList(nativePtr);
        }

        @Override
        public boolean getTaskInfo(long taskId, @Nullable NativePointer info) {
            checkNotClosed();
            return N_GetTaskInfo(nativePtr, taskId, info);
        }

        @Override
        public boolean killTask(long taskId) {
            checkNotClosed();
            return N_KillTask(nativePtr, taskId);
        }

        @Override
        public long getTaskIdForBrowserId(int browserId) {
            checkNotClosed();
            return N_GetTaskIdForBrowserId(nativePtr, browserId);
        }

        private static native long N_GetTasksCount(long self);

        private static native long[] N_GetTaskIdsList(long self);

        private static native boolean N_GetTaskInfo(long self, long taskId, NativePointer info);

        private static native boolean N_KillTask(long self, long taskId);

        private static native long N_GetTaskIdForBrowserId(long self, int browserId);

        static native CefTaskManager N_Get();

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof NativePeer)) return false;
            return this.nativePtr == ((NativePeer) obj).nativePtr;
        }

        @Override
        public int hashCode() {
            return Long.hashCode(nativePtr);
        }

        @Override
        public String toString() {
            return "CefTaskManager{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
