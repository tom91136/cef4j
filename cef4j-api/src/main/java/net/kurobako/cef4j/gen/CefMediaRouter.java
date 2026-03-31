// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;

/**
 * Supports discovery of and communication with media devices on the local network via the Cast and DIAL protocols. The
 * methods of this class may be called on any browser process thread unless otherwise indicated.
 */
public interface CefMediaRouter {

    /**
     * Add an observer for MediaRouter events. The observer will remain registered until the returned Registration
     * object is destroyed.
     */
    long addObserver(long observer);

    /** Retrieve this frame's HTML source as a string sent to the specified visitor. */
    long getSource(@Nonnull String urn);

    /** Trigger an asynchronous call to CefMediaObserver::OnSinks on all registered observers. */
    void notifyCurrentSinks();

    /**
     * Create a new route between |source| and |sink|. Source and sink must be valid, compatible (as reported by
     * CefMediaSink::IsCompatibleWith), and a route between them must not already exist. |callback| will be executed on
     * success or failure. If route creation succeeds it will also trigger an asynchronous call to
     * CefMediaObserver::OnRoutes on all registered observers.
     */
    void createRoute(long source, long sink, long callback);

    /** Trigger an asynchronous call to CefMediaObserver::OnRoutes on all registered observers. */
    void notifyCurrentRoutes();

    static class NativePeer implements CefMediaRouter {
        private volatile long nativePtr;

        @Override
        public long addObserver(long observer) {
            return N_AddObserver(nativePtr, observer);
        }

        @Override
        public long getSource(String urn) {
            return N_GetSource(nativePtr, urn);
        }

        @Override
        public void notifyCurrentSinks() {
            N_NotifyCurrentSinks(nativePtr);
        }

        @Override
        public void createRoute(long source, long sink, long callback) {
            N_CreateRoute(nativePtr, source, sink, callback);
        }

        @Override
        public void notifyCurrentRoutes() {
            N_NotifyCurrentRoutes(nativePtr);
        }

        private native long N_AddObserver(long self, long observer);

        private native long N_GetSource(long self, String urn);

        private native void N_NotifyCurrentSinks(long self);

        private native void N_CreateRoute(long self, long source, long sink, long callback);

        private native void N_NotifyCurrentRoutes(long self);

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
            return "CefMediaRouter{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
