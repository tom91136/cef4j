package net.kurobako.cef4j.ipc.transport;

import java.util.ArrayDeque;

final class PendingFrames {
    static final int DEFAULT_MAX_FRAMES = 4096;
    static final long DEFAULT_MAX_BYTES = 128L * 1024L * 1024L;

    private final int maxFrames;
    private final long maxBytes;
    private final ArrayDeque<byte[]> frames = new ArrayDeque<>();
    private long bytes;

    PendingFrames() {
        this(DEFAULT_MAX_FRAMES, DEFAULT_MAX_BYTES);
    }

    PendingFrames(int maxFrames, long maxBytes) {
        if (maxFrames <= 0) throw new IllegalArgumentException("maxFrames must be positive");
        if (maxBytes <= 0) throw new IllegalArgumentException("maxBytes must be positive");
        this.maxFrames = maxFrames;
        this.maxBytes = maxBytes;
    }

    boolean offer(byte[] frame) {
        if (frames.size() >= maxFrames || frame.length > maxBytes - bytes) return false;
        frames.add(frame);
        bytes += frame.length;
        return true;
    }

    byte[] poll() {
        byte[] frame = frames.poll();
        if (frame != null) bytes -= frame.length;
        return frame;
    }

    boolean isEmpty() {
        return frames.isEmpty();
    }
}
