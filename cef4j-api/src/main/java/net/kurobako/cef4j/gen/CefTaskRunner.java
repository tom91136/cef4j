// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;

/**
 * Class that asynchronously executes tasks on the associated thread. It is safe to call the methods of this class on
 * any thread. CEF maintains multiple internal threads that are used for handling different types of tasks in different
 * processes. The cef_thread_id_t definitions in cef_types.h list the common CEF threads. Task runners are also
 * available for other CEF threads as appropriate (for example, V8 WebWorker threads).
 */
public interface CefTaskRunner {

    /** Returns true if this object is pointing to the same handle as |that| object. */
    boolean isSame(long that);

    /** Returns true if this task runner belongs to the current thread. */
    boolean belongsToCurrentThread();

    /** Returns true if this task runner is for the specified CEF thread. */
    boolean belongsToThread(@Nonnull CefThreadId threadId);

    /**
     * Post a task for execution on the thread associated with this task runner. Execution will occur asynchronously.
     */
    boolean postTask(long task);

    /**
     * Post a task for delayed execution on the thread associated with this task runner. Execution will occur
     * asynchronously. Delayed tasks are not supported on V8 WebWorker threads and will be executed without the
     * specified delay.
     */
    boolean postDelayedTask(long task, long delayMs);

    static class NativePeer implements CefTaskRunner {
        private volatile long nativePtr;

        @Override
        public boolean isSame(long that) {
            return N_IsSame(nativePtr, that);
        }

        @Override
        public boolean belongsToCurrentThread() {
            return N_BelongsToCurrentThread(nativePtr);
        }

        @Override
        public boolean belongsToThread(CefThreadId threadId) {
            return N_BelongsToThread(nativePtr, threadId);
        }

        @Override
        public boolean postTask(long task) {
            return N_PostTask(nativePtr, task);
        }

        @Override
        public boolean postDelayedTask(long task, long delayMs) {
            return N_PostDelayedTask(nativePtr, task, delayMs);
        }

        private native boolean N_IsSame(long self, long that);

        private native boolean N_BelongsToCurrentThread(long self);

        private native boolean N_BelongsToThread(long self, CefThreadId threadId);

        private native boolean N_PostTask(long self, long task);

        private native boolean N_PostDelayedTask(long self, long task, long delayMs);

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
