// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;

/**
 * Class representing a snapshot of a component's state at the time of retrieval. To get updated information, retrieve a
 * new CefComponent object via CefComponentUpdater::GetComponentByID or GetComponents. The methods of this class may be
 * called on any thread.
 */
public interface CefComponent {

    /** Returns the unique identifier for this download. */
    Optional<String> getId();

    /** Returns the name of this node. */
    Optional<String> getName();

    /**
     * Returns the version of this component as a string (e.g., "1.2.3.4"). Returns an empty string if the component is
     * not installed.
     */
    Optional<String> getVersion();

    /**
     * Returns the state of this component at the time this object was created. A component is considered installed when
     * its state is one of: CEF_COMPONENT_STATE_UPDATED, CEF_COMPONENT_STATE_UP_TO_DATE, or CEF_COMPONENT_STATE_RUN.
     *
     * @return the result, or {@code CEF_COMPONENT_STATE_NEW} for default handling
     */
    CefComponentState getState();

    static class NativePeer implements CefComponent {
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
        public Optional<String> getVersion() {
            return Optional.ofNullable(N_GetVersion(nativePtr));
        }

        @Override
        public CefComponentState getState() {
            return N_GetState(nativePtr);
        }

        private native String N_GetId(long self);

        private native String N_GetName(long self);

        private native String N_GetVersion(long self);

        private native CefComponentState N_GetState(long self);

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
            return "CefComponent{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
