// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Class that asynchronously executes tasks on the associated thread. It is safe to call the methods of this class on
 * any thread. CEF maintains multiple internal threads that are used for handling different types of tasks in different
 * processes. The cef_thread_id_t definitions in cef_types.h list the common CEF threads. Task runners are also
 * available for other CEF threads as appropriate (for example, V8 WebWorker threads).
 *
 * <p>Definition generated from cef_task_capi.h
 *
 * <pre>typedef struct _cef_task_runner_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_task_runner_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__task_8h.html">cef_task.h:62</a>
 */
public interface CefTaskRunner extends CefLibraryObject {

    /**
     * Returns {@code true} if this object is pointing to the same task runner as {@code that} object.
     *
     * <p>Definition generated from cef_task_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_same)(struct _cef_task_runner_t* self, struct _cef_task_runner_t* that);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__task_8h.html">cef_task.h:88</a>
     */
    boolean isSame(@Nullable CefTaskRunner that);

    /**
     * Returns {@code true} if this task runner belongs to the current thread.
     *
     * <p>Definition generated from cef_task_capi.h
     *
     * <pre>int (CEF_CALLBACK* belongs_to_current_thread)(struct _cef_task_runner_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__task_8h.html">cef_task.h:95</a>
     */
    boolean belongsToCurrentThread();

    /**
     * Returns {@code true} if this task runner is for the specified CEF thread.
     *
     * <p>Definition generated from cef_task_capi.h
     *
     * <pre>int (CEF_CALLBACK* belongs_to_thread)(struct _cef_task_runner_t* self, cef_thread_id_t threadId);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__task_8h.html">cef_task.h:101</a>
     */
    boolean belongsToThread(@Nonnull CefThreadId threadId);

    /**
     * Post a task for execution on the thread associated with this task runner. Execution will occur asynchronously.
     *
     * <p>Definition generated from cef_task_capi.h
     *
     * <pre>int (CEF_CALLBACK* post_task)(struct _cef_task_runner_t* self, struct _cef_task_t* task);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__task_8h.html">cef_task.h:107</a>
     */
    boolean postTask(@Nullable CefTask task);

    /**
     * Post a task for delayed execution on the thread associated with this task runner. Execution will occur
     * asynchronously. Delayed tasks are not supported on V8 WebWorker threads and will be executed without the
     * specified delay.
     *
     * <p>Definition generated from cef_task_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* post_delayed_task)(struct _cef_task_runner_t* self, struct _cef_task_t* task, int64_t delay_ms);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__task_8h.html">cef_task.h:114</a>
     */
    boolean postDelayedTask(@Nullable CefTask task, long delayMs);
    /**
     * Returns the task runner for the current thread. Only CEF threads will have task runners. An empty reference will
     * be returned if this method is called on an invalid thread.
     *
     * <p>Definition generated from cef_task_capi.h
     *
     * <pre>CEF_EXPORT cef_task_runner_t* cef_task_runner_get_for_current_thread(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__task_8h.html">cef_task.h:74</a>
     */
    static Optional<CefTaskRunner> getForCurrentThread() {
        return Optional.ofNullable(NativePeer.N_GetForCurrentThread());
    }

    /**
     * Returns the task runner for the specified CEF thread.
     *
     * <p>Definition generated from cef_task_capi.h
     *
     * <pre>CEF_EXPORT cef_task_runner_t* cef_task_runner_get_for_thread(cef_thread_id_t threadId);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__task_8h.html">cef_task.h:82</a>
     */
    static Optional<CefTaskRunner> getForThread(@Nonnull CefThreadId threadId) {
        return Optional.ofNullable(NativePeer.N_GetForThread(threadId));
    }

    final class NativePeer implements CefTaskRunner, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefTaskRunner has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefTaskRunner.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefTaskRunner 0x{}", Long.toHexString(ptr));
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

        @Override
        public boolean isSame(@Nullable CefTaskRunner that) {
            checkNotClosed();
            CefLibraryObject.requireOpen(that, "CefTaskRunner");
            return N_IsSame(nativePtr, that);
        }

        @Override
        public boolean belongsToCurrentThread() {
            checkNotClosed();
            return N_BelongsToCurrentThread(nativePtr);
        }

        @Override
        public boolean belongsToThread(@Nonnull CefThreadId threadId) {
            checkNotClosed();
            return N_BelongsToThread(nativePtr, threadId);
        }

        @Override
        public boolean postTask(@Nullable CefTask task) {
            checkNotClosed();
            return N_PostTask(nativePtr, task);
        }

        @Override
        public boolean postDelayedTask(@Nullable CefTask task, long delayMs) {
            checkNotClosed();
            return N_PostDelayedTask(nativePtr, task, delayMs);
        }

        private static native boolean N_IsSame(long self, CefTaskRunner that);

        private static native boolean N_BelongsToCurrentThread(long self);

        private static native boolean N_BelongsToThread(long self, CefThreadId threadId);

        private static native boolean N_PostTask(long self, CefTask task);

        private static native boolean N_PostDelayedTask(long self, CefTask task, long delayMs);

        static native CefTaskRunner N_GetForCurrentThread();

        static native CefTaskRunner N_GetForThread(CefThreadId threadId);

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
            return "CefTaskRunner{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
