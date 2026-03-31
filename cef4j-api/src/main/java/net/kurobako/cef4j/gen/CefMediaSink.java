// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;

/**
 * Represents a sink to which media can be routed. Instances of this object are retrieved via CefMediaObserver::OnSinks.
 * The methods of this class may be called on any browser process thread unless otherwise indicated.
 */
public interface CefMediaSink {

    /** Returns the unique identifier for this download. */
    Optional<String> getId();

    /** Returns the name of this node. */
    Optional<String> getName();

    /**
     * Returns the icon type for this sink.
     *
     * @return the result, or {@code CEF_MSIT_GENERIC} for default handling
     */
    CefMediaSinkIconType getIconType();

    /** Asynchronously retrieves device info. */
    void getDeviceInfo(long callback);

    /** Returns true if this sink accepts content via Cast. */
    boolean isCastSink();

    /** Returns true if this sink accepts content via DIAL. */
    boolean isDialSink();

    /** Returns true if this sink is compatible with |source|. */
    boolean isCompatibleWith(long source);

    static class NativePeer implements CefMediaSink {
        private volatile long nativePtr;

        @Override
        public Optional<String> getId() {
            return Optional.ofNullable(N_GetId(nativePtr));
        }

        @Override
        public Optional<String> getName() {
            return Optional.ofNullable(N_GetName(nativePtr));
        }

        @Override
        public CefMediaSinkIconType getIconType() {
            return N_GetIconType(nativePtr);
        }

        @Override
        public void getDeviceInfo(long callback) {
            N_GetDeviceInfo(nativePtr, callback);
        }

        @Override
        public boolean isCastSink() {
            return N_IsCastSink(nativePtr);
        }

        @Override
        public boolean isDialSink() {
            return N_IsDialSink(nativePtr);
        }

        @Override
        public boolean isCompatibleWith(long source) {
            return N_IsCompatibleWith(nativePtr, source);
        }

        private native String N_GetId(long self);

        private native String N_GetName(long self);

        private native CefMediaSinkIconType N_GetIconType(long self);

        private native void N_GetDeviceInfo(long self, long callback);

        private native boolean N_IsCastSink(long self);

        private native boolean N_IsDialSink(long self);

        private native boolean N_IsCompatibleWith(long self, long source);

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
            return "CefMediaSink{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
