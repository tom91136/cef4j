// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Class used to represent post data for a web request. The methods of this class may be called on any thread. */
public interface CefPostData {

    /** Returns true if the values of this object are read-only. Some APIs may expose read-only objects. */
    boolean isReadOnly();

    /**
     * Returns true if the underlying POST data includes elements that are not represented by this CefPostData object
     * (for example, multi-part file upload data). Modifying CefPostData objects with excluded elements may result in
     * the request failing.
     */
    boolean hasExcludedElements();

    /** Returns the number of existing post data elements. */
    long getElementCount();

    /**
     * Retrieve the post data elements.
     *
     * <p>The size of {@code elements} is determined by {@code GetElementCount()}.
     */
    void getElements(long elementsCount, long elements);

    /** Remove the specified post data element. Returns true if the removal succeeds. */
    boolean removeElement(long element);

    /** Add the specified post data element. Returns true if the add succeeds. */
    boolean addElement(long element);

    /** Remove all existing post data elements. */
    void removeElements();

    static class NativePeer implements CefPostData {
        private volatile long nativePtr;

        @Override
        public boolean isReadOnly() {
            return N_IsReadOnly(nativePtr);
        }

        @Override
        public boolean hasExcludedElements() {
            return N_HasExcludedElements(nativePtr);
        }

        @Override
        public long getElementCount() {
            return N_GetElementCount(nativePtr);
        }

        @Override
        public void getElements(long elementsCount, long elements) {
            N_GetElements(nativePtr, elementsCount, elements);
        }

        @Override
        public boolean removeElement(long element) {
            return N_RemoveElement(nativePtr, element);
        }

        @Override
        public boolean addElement(long element) {
            return N_AddElement(nativePtr, element);
        }

        @Override
        public void removeElements() {
            N_RemoveElements(nativePtr);
        }

        private native boolean N_IsReadOnly(long self);

        private native boolean N_HasExcludedElements(long self);

        private native long N_GetElementCount(long self);

        private native void N_GetElements(long self, long elementsCount, long elements);

        private native boolean N_RemoveElement(long self, long element);

        private native boolean N_AddElement(long self, long element);

        private native void N_RemoveElements(long self);

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
            return "CefPostData{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
