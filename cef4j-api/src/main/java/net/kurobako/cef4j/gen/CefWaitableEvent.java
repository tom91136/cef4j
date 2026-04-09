// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import java.util.Optional;

/**
 * WaitableEvent is a thread synchronization tool that allows one thread to wait for another thread to finish some work. This is equivalent to using a Lock+ConditionVariable to protect a simple boolean value. However, using WaitableEvent in conjunction with a Lock to wait for a more complex state change (e.g., for an item to be added to a queue) is not recommended. In that case consider using a ConditionVariable instead of a WaitableEvent. It is safe to create and/or signal a WaitableEvent from any thread. Blocking on a WaitableEvent by calling the *Wait() methods is not allowed on the browser process UI or IO threads.
 * <p>Definition generated from cef_waitable_event_capi.h
 * <pre>typedef struct _cef_waitable_event_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_waitable_event_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__waitable__event_8h.html">cef_waitable_event.h:43</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefWaitableEvent extends CefLibraryObject {

    /**
     * Put the event in the un-signaled state.
     * <p>Definition generated from cef_waitable_event_capi.h
     * <pre>void (CEF_CALLBACK* reset)(struct _cef_waitable_event_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__waitable__event_8h.html">cef_waitable_event.h:69</a>
     */
    void reset();

    /**
     * Put the event in the signaled state. This causes any thread blocked on Wait to be woken up.
     * <p>Definition generated from cef_waitable_event_capi.h
     * <pre>void (CEF_CALLBACK* signal)(struct _cef_waitable_event_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__waitable__event_8h.html">cef_waitable_event.h:75</a>
     */
    void signal();

    /**
     * Returns {@code true} if the event is in the signaled state, else {@code false}. If the event was created with {@code automatic_reset} set to {@code true} then calling this method will also cause a reset.
     * <p>Definition generated from cef_waitable_event_capi.h
     * <pre>int (CEF_CALLBACK* is_signaled)(struct _cef_waitable_event_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__waitable__event_8h.html">cef_waitable_event.h:82</a>
     */
    boolean isSignaled();

    /**
     * Wait indefinitely for the event to be signaled. This method will not return until after the call to Signal() has completed. This method cannot be called on the browser process UI or IO threads.
     * <p>Definition generated from cef_waitable_event_capi.h
     * <pre>void (CEF_CALLBACK* wait)(struct _cef_waitable_event_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__waitable__event_8h.html">cef_waitable_event.h:90</a>
     */
    void cefWait();

    /**
     * Wait up to {@code max_ms} milliseconds for the event to be signaled. Returns {@code true} if the event was signaled. A return value of {@code false} does not necessarily mean that {@code max_ms} was exceeded. This method will not return until after the call to Signal() has completed. This method cannot be called on the browser process UI or IO threads.
     * <p>Definition generated from cef_waitable_event_capi.h
     * <pre>int (CEF_CALLBACK* timed_wait)(struct _cef_waitable_event_t* self, int64_t max_ms);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__waitable__event_8h.html">cef_waitable_event.h:98</a>
     */
    boolean timedWait(long maxMs);
    /**
     * Create a new waitable event. If {@code automatic_reset} is {@code true} then the event state is automatically reset to un-signaled after a single waiting thread has been released; otherwise, the state remains signaled until Reset() is called manually. If {@code initially_signaled} is {@code true} then the event will start in the signaled state.
     * <p>Definition generated from cef_waitable_event_capi.h
     * <pre>CEF_EXPORT cef_waitable_event_t* cef_waitable_event_create(int automatic_reset, int initially_signaled);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__waitable__event_8h.html">cef_waitable_event.h:57</a>
     */
    static Optional<CefWaitableEvent> create(int automaticReset, int initiallySignaled) {
      return Optional.ofNullable(NativePeer.create0(automaticReset, initiallySignaled));
  }

    final class NativePeer implements CefWaitableEvent, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefWaitableEvent has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefWaitableEvent.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefWaitableEvent 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
      public void reset() {
          checkNotClosed();
          reset0(nativePtr);
      }

        @Override
      public void signal() {
          checkNotClosed();
          signal0(nativePtr);
      }

        @Override
      public boolean isSignaled() {
          checkNotClosed();
          return isSignaled0(nativePtr);
      }

        @Override
      public void cefWait() {
          checkNotClosed();
          cefWait0(nativePtr);
      }

        @Override
      public boolean timedWait(long maxMs) {
          checkNotClosed();
          return timedWait0(nativePtr, maxMs);
      }


        static native void reset0(long self);

        static native void signal0(long self);

        static native boolean isSignaled0(long self);

        static native void cefWait0(long self);

        static native boolean timedWait0(long self, long maxMs);

        static native CefWaitableEvent create0(int automaticReset, int initiallySignaled);

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
