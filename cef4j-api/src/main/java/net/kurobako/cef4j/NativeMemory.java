package net.kurobako.cef4j;

/**
 * JNI helper for writing bytes to raw native memory addresses. Used by {@link UrlResourceHandler} to copy data into
 * CEF's {@code void* data_out} buffers passed via {@link net.kurobako.cef4j.gen.NativePointer}.
 */
@SuppressWarnings("unused")
public final class NativeMemory {

    private NativeMemory() {}

    /**
     * Copy bytes from a Java byte array into a native memory address.
     *
     * @param address destination native pointer (must be valid and have at least {@code length} bytes available)
     * @param src source byte array
     * @param offset start offset in {@code src}
     * @param length number of bytes to copy
     */
    public static native void putBytes(long address, byte[] src, int offset, int length);
}
