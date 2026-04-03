// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;

/**
 * A simple thread abstraction that establishes a message loop on a new thread. The consumer uses CefTaskRunner to
 * execute code on the thread's message loop. The thread is terminated when the CefThread object is destroyed or Stop()
 * is called. All pending tasks queued on the thread's message loop will run to completion before the thread is
 * terminated. CreateThread() can be called on any valid CEF thread in either the browser or render process. This class
 * should only be used for tasks that require a dedicated thread. In most cases you can post tasks to an existing CEF
 * thread instead of creating a new one; see cef_task.h for details.
 *
 * <p>Definition generated from cef_thread_capi.h
 *
 * <pre>typedef struct _cef_thread_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_thread_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__thread_8h.html">cef_thread.h:44</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public interface CefThread extends CefLibraryObject {

    /**
     * Returns the CefTaskRunner that will execute code on this thread's message loop. This method is safe to call from
     * any thread.
     *
     * <p>Definition generated from cef_thread_capi.h
     *
     * <pre>cef_task_runner_t* (CEF_CALLBACK* get_task_runner)(struct _cef_thread_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__thread_8h.html">cef_thread.h:87</a>
     */
    Optional<CefTaskRunner> getTaskRunner();

    /**
     * Returns the platform thread ID. It will return the same value after Stop() is called. This method is safe to call
     * from any thread.
     *
     * <p>Definition generated from cef_thread_capi.h
     *
     * <pre>int64_t (CEF_CALLBACK* get_platform_thread_id)(struct _cef_thread_t* self);</pre>
     *
     * @return the result, or {@code kInvalidPlatformThreadId} for default handling
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__thread_8h.html">cef_thread.h:94</a>
     */
    long getPlatformThreadId();

    /**
     * Stop and join the thread. This method must be called from the same thread that called CreateThread(). Do not call
     * this method if CreateThread() was called with a {@code stoppable} value of {@code false}.
     *
     * <p>Definition generated from cef_thread_capi.h
     *
     * <pre>void (CEF_CALLBACK* stop)(struct _cef_thread_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__thread_8h.html">cef_thread.h:101</a>
     */
    void stop();

    /**
     * Returns {@code true} if the thread is currently running. This method must be called from the same thread that
     * called CreateThread().
     *
     * <p>Definition generated from cef_thread_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_running)(struct _cef_thread_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__thread_8h.html">cef_thread.h:109</a>
     */
    boolean isRunning();
    /**
     * Create a new backing store with allocated memory of {@code byte_length} bytes. The memory is uninitialized. This
     * method must be called on a thread with a valid V8 isolate. The returned object can safely be passed to other
     * threads. Returns {@code null} on failure.
     *
     * <p>Definition generated from cef_thread_capi.h
     *
     * <pre>
     * CEF_EXPORT cef_thread_t* cef_thread_create(const cef_string_t* display_name, cef_thread_priority_t priority, cef_message_loop_type_t message_loop_type, int stoppable, cef_com_init_mode_t com_init_mode);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:445</a>
     */
    static Optional<CefThread> create(
            @Nullable String displayName,
            @Nonnull CefThreadPriority priority,
            @Nonnull CefMessageLoopType messageLoopType,
            int stoppable,
            @Nonnull CefComInitMode comInitMode) {
        return Optional.ofNullable(NativePeer.create0(displayName, priority, messageLoopType, stoppable, comInitMode));
    }

    final class NativePeer implements CefThread, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefThread has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefThread.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefThread 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
        public Optional<CefTaskRunner> getTaskRunner() {
            checkNotClosed();
            return Optional.ofNullable(getTaskRunner0(nativePtr));
        }

        @Override
        public long getPlatformThreadId() {
            checkNotClosed();
            return getPlatformThreadId0(nativePtr);
        }

        @Override
        public void stop() {
            checkNotClosed();
            stop0(nativePtr);
        }

        @Override
        public boolean isRunning() {
            checkNotClosed();
            return isRunning0(nativePtr);
        }

        private static native CefTaskRunner getTaskRunner0(long self);

        private static native long getPlatformThreadId0(long self);

        private static native void stop0(long self);

        private static native boolean isRunning0(long self);

        static native CefThread create0(
                String displayName,
                CefThreadPriority priority,
                CefMessageLoopType messageLoopType,
                int stoppable,
                CefComInitMode comInitMode);

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
            return "CefThread{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
