// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;

/**
 * Represents the route between a media source and sink. Instances of this object are created via
 * CefMediaRouter::CreateRoute and retrieved via CefMediaObserver::OnRoutes. Contains the status and metadata of a
 * routing operation. The methods of this class may be called on any browser process thread unless otherwise indicated.
 */
public interface CefMediaRoute {

    /** Returns the unique identifier for this download. */
    Optional<String> getId();

    /** Retrieve this frame's HTML source as a string sent to the specified visitor. */
    long getSource();

    /** Returns the sink associated with this route. */
    long getSink();

    /** Send a message over this route. |message| will be copied if necessary. */
    void sendRouteMessage(long message, long messageSize);

    /** Terminate the unresponsive process. */
    void terminate();

    static class NativePeer implements CefMediaRoute {
        private volatile long nativePtr;

        @Override
        public Optional<String> getId() {
            return Optional.ofNullable(N_GetId(nativePtr));
        }

        @Override
        public long getSource() {
            return N_GetSource(nativePtr);
        }

        @Override
        public long getSink() {
            return N_GetSink(nativePtr);
        }

        @Override
        public void sendRouteMessage(long message, long messageSize) {
            N_SendRouteMessage(nativePtr, message, messageSize);
        }

        @Override
        public void terminate() {
            N_Terminate(nativePtr);
        }

        private native String N_GetId(long self);

        private native long N_GetSource(long self);

        private native long N_GetSink(long self);

        private native void N_SendRouteMessage(long self, long message, long messageSize);

        private native void N_Terminate(long self);

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
            return "CefMediaRoute{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
