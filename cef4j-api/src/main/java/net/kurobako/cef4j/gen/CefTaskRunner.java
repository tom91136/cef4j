// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Class that asynchronously executes tasks on the associated thread. It is safe to call the methods of this class on any thread. CEF maintains multiple internal threads that are used for handling different types of tasks in different processes. The cef_thread_id_t definitions in cef_types.h list the common CEF threads. Task runners are also available for other CEF threads as appropriate (for example, V8 WebWorker threads).
 * <p>Definition generated from cef_task_capi.h
 * <pre>typedef struct _cef_task_runner_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_task_runner_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__task_8h.html">cef_task.h:62</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused", "NullableForbidden"})
public interface CefTaskRunner extends CefLibraryObject {

    /**
     * Returns {@code true} if this object is pointing to the same task runner as {@code that} object.
     * <p>Definition generated from cef_task_capi.h
     * <pre>int (CEF_CALLBACK* is_same)(struct _cef_task_runner_t* self, struct _cef_task_runner_t* that);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__task_8h.html">cef_task.h:88</a>
     */
    boolean isSame(@Nullable CefTaskRunner that);

    /**
     * Returns {@code true} if this task runner belongs to the current thread.
     * <p>Definition generated from cef_task_capi.h
     * <pre>int (CEF_CALLBACK* belongs_to_current_thread)(struct _cef_task_runner_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__task_8h.html">cef_task.h:95</a>
     */
    boolean belongsToCurrentThread();

    /**
     * Returns {@code true} if this task runner is for the specified CEF thread.
     * <p>Definition generated from cef_task_capi.h
     * <pre>int (CEF_CALLBACK* belongs_to_thread)(struct _cef_task_runner_t* self, cef_thread_id_t threadId);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__task_8h.html">cef_task.h:101</a>
     */
    boolean belongsToThread(@Nonnull CefThreadId threadId);

    /**
     * Post a task for execution on the thread associated with this task runner. Execution will occur asynchronously.
     * <p>Definition generated from cef_task_capi.h
     * <pre>int (CEF_CALLBACK* post_task)(struct _cef_task_runner_t* self, struct _cef_task_t* task);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__task_8h.html">cef_task.h:107</a>
     */
    boolean postTask(@Nullable CefTask task);

    /**
     * Post a task for delayed execution on the thread associated with this task runner. Execution will occur asynchronously. Delayed tasks are not supported on V8 WebWorker threads and will be executed without the specified delay.
     * <p>Definition generated from cef_task_capi.h
     * <pre>int (CEF_CALLBACK* post_delayed_task)(struct _cef_task_runner_t* self, struct _cef_task_t* task, int64_t delay_ms);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__task_8h.html">cef_task.h:114</a>
     */
    boolean postDelayedTask(@Nullable CefTask task, long delayMs);
    /**
     * Returns the task runner for the current thread. Only CEF threads will have task runners. An empty reference will be returned if this method is called on an invalid thread.
     * <p>Definition generated from cef_task_capi.h
     * <pre>CEF_EXPORT cef_task_runner_t* cef_task_runner_get_for_current_thread(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__task_8h.html">cef_task.h:74</a>
     */
    static Optional<CefTaskRunner> getForCurrentThread() {
      return Optional.ofNullable(NativePeer.getForCurrentThread0());
  }

    /**
     * Returns the task runner for the specified CEF thread.
     * <p>Definition generated from cef_task_capi.h
     * <pre>CEF_EXPORT cef_task_runner_t* cef_task_runner_get_for_thread(cef_thread_id_t threadId);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__task_8h.html">cef_task.h:82</a>
     */
    static Optional<CefTaskRunner> getForThread(@Nonnull CefThreadId threadId) {
      return Optional.ofNullable(NativePeer.getForThread0(threadId));
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
        public void peerClose() {
            closed = true;
            cleanable.clean();
        }

        @Override
        public boolean peerIsClosed() {
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
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
      public boolean isSame(@Nullable CefTaskRunner that) {
          checkNotClosed();
            CefLibraryObject.requireOpen(that, "CefTaskRunner");
          return isSame0(nativePtr, that);
      }

        @Override
      public boolean belongsToCurrentThread() {
          checkNotClosed();
          return belongsToCurrentThread0(nativePtr);
      }

        @Override
      public boolean belongsToThread(@Nonnull CefThreadId threadId) {
          checkNotClosed();
          return belongsToThread0(nativePtr, threadId);
      }

        @Override
      public boolean postTask(@Nullable CefTask task) {
          checkNotClosed();
          return postTask0(nativePtr, task);
      }

        @Override
      public boolean postDelayedTask(@Nullable CefTask task, long delayMs) {
          checkNotClosed();
          return postDelayedTask0(nativePtr, task, delayMs);
      }


        static native boolean isSame0(long self, @Nullable CefTaskRunner that);

        static native boolean belongsToCurrentThread0(long self);

        static native boolean belongsToThread0(long self, @Nonnull CefThreadId threadId);

        static native boolean postTask0(long self, @Nullable CefTask task);

        static native boolean postDelayedTask0(long self, @Nullable CefTask task, long delayMs);

        static native CefTaskRunner getForCurrentThread0();
        static native CefTaskRunner getForThread0(@Nonnull CefThreadId threadId);

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
