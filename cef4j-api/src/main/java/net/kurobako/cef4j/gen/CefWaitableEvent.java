// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * WaitableEvent is a thread synchronization tool that allows one thread to wait for another thread to finish some work.
 * This is equivalent to using a Lock+ConditionVariable to protect a simple boolean value. However, using WaitableEvent
 * in conjunction with a Lock to wait for a more complex state change (e.g., for an item to be added to a queue) is not
 * recommended. In that case consider using a ConditionVariable instead of a WaitableEvent. It is safe to create and/or
 * signal a WaitableEvent from any thread. Blocking on a WaitableEvent by calling the *Wait() methods is not allowed on
 * the browser process UI or IO threads.
 */
public interface CefWaitableEvent {

    /** Put the event in the un-signaled state. */
    void reset();

    /** Put the event in the signaled state. This causes any thread blocked on Wait to be woken up. */
    void signal();

    /**
     * Returns true if the event is in the signaled state, else false. If the event was created with |automatic_reset|
     * set to true then calling this method will also cause a reset.
     */
    boolean isSignaled();

    /**
     * Wait indefinitely for the event to be signaled. This method will not return until after the call to Signal() has
     * completed. This method cannot be called on the browser process UI or IO threads.
     */
    void cefWait();

    /**
     * Wait up to |max_ms| milliseconds for the event to be signaled. Returns true if the event was signaled. A return
     * value of false does not necessarily mean that |max_ms| was exceeded. This method will not return until after the
     * call to Signal() has completed. This method cannot be called on the browser process UI or IO threads.
     */
    boolean timedWait(long maxMs);

    static class NativePeer implements CefWaitableEvent {
        private volatile long nativePtr;

        @Override
        public void reset() {
            N_Reset(nativePtr);
        }

        @Override
        public void signal() {
            N_Signal(nativePtr);
        }

        @Override
        public boolean isSignaled() {
            return N_IsSignaled(nativePtr);
        }

        @Override
        public void cefWait() {
            N_Wait(nativePtr);
        }

        @Override
        public boolean timedWait(long maxMs) {
            return N_TimedWait(nativePtr, maxMs);
        }

        private native void N_Reset(long self);

        private native void N_Signal(long self);

        private native boolean N_IsSignaled(long self);

        private native void N_Wait(long self);

        private native boolean N_TimedWait(long self, long maxMs);

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
            return "CefWaitableEvent{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
