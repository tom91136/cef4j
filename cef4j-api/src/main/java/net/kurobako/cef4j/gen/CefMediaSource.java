// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;

/**
 * Represents a source from which media can be routed. Instances of this object are retrieved via
 * CefMediaRouter::GetSource. The methods of this class may be called on any browser process thread unless otherwise
 * indicated.
 */
public interface CefMediaSource {

    /** Returns the unique identifier for this download. */
    Optional<String> getId();

    /** Returns true if this source outputs its content via Cast. */
    boolean isCastSource();

    /** Returns true if this source outputs its content via DIAL. */
    boolean isDialSource();

    static class NativePeer implements CefMediaSource {
        private volatile long nativePtr;

        @Override
        public Optional<String> getId() {
            return Optional.ofNullable(N_GetId(nativePtr));
        }

        @Override
        public boolean isCastSource() {
            return N_IsCastSource(nativePtr);
        }

        @Override
        public boolean isDialSource() {
            return N_IsDialSource(nativePtr);
        }

        private native String N_GetId(long self);

        private native boolean N_IsCastSource(long self);

        private native boolean N_IsDialSource(long self);

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
            return "CefMediaSource{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
