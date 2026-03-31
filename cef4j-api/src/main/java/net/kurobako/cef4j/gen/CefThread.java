// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * A simple thread abstraction that establishes a message loop on a new thread. The consumer uses CefTaskRunner to
 * execute code on the thread's message loop. The thread is terminated when the CefThread object is destroyed or Stop()
 * is called. All pending tasks queued on the thread's message loop will run to completion before the thread is
 * terminated. CreateThread() can be called on any valid CEF thread in either the browser or render process. This class
 * should only be used for tasks that require a dedicated thread. In most cases you can post tasks to an existing CEF
 * thread instead of creating a new one; see cef_task.h for details.
 */
public interface CefThread {

    /**
     * Returns the CefTaskRunner that will execute code on this thread's message loop. This method is safe to call from
     * any thread.
     */
    long getTaskRunner();

    /**
     * Returns the platform thread ID. It will return the same value after Stop() is called. This method is safe to call
     * from any thread.
     *
     * @return the result, or {@code kInvalidPlatformThreadId} for default handling
     */
    long getPlatformThreadId();

    /**
     * Stop and join the thread. This method must be called from the same thread that called CreateThread(). Do not call
     * this method if CreateThread() was called with a |stoppable| value of false.
     */
    void stop();

    /**
     * Returns true if the thread is currently running. This method must be called from the same thread that called
     * CreateThread().
     */
    boolean isRunning();

    static class NativePeer implements CefThread {
        private volatile long nativePtr;

        @Override
        public long getTaskRunner() {
            return N_GetTaskRunner(nativePtr);
        }

        @Override
        public long getPlatformThreadId() {
            return N_GetPlatformThreadId(nativePtr);
        }

        @Override
        public void stop() {
            N_Stop(nativePtr);
        }

        @Override
        public boolean isRunning() {
            return N_IsRunning(nativePtr);
        }

        private native long N_GetTaskRunner(long self);

        private native long N_GetPlatformThreadId(long self);

        private native void N_Stop(long self);

        private native boolean N_IsRunning(long self);

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
