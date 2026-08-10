package net.kurobako.cef4j.ipc.frame;

/**
 * Pixel format produced by the helper's OSR paint pipeline. CEF emits BGRA on all platforms; the enum exists so the
 * frame-transport API is forward-compatible if we ever support alternative formats (e.g. a hardware-accelerated YUV
 * path) without breaking the consumer signature.
 */
public enum PixelFormat {
    /** 32-bit BGRA, packed (stride = width * 4). The CEF default for OSR. */
    BGRA
}
