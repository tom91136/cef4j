package net.kurobako.cef4j.peer;

import java.lang.ref.Cleaner;
import java.util.function.LongConsumer;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Lifetime and identity of a native CEF object, shared by all generated NativePeer classes. */
public abstract class NativeObject {
    private static final Logger log = LoggerFactory.getLogger(NativeObject.class);

    protected final long nativePtr;
    private final String typeName;
    private final Cleaner.Cleanable cleanable;
    private volatile boolean closed = false;

    @SuppressWarnings("this-escape")
    protected NativeObject(String typeName, long ptr, LongConsumer release) {
        this.typeName = typeName;
        this.nativePtr = ptr;
        this.cleanable = NativeCleaner.INSTANCE.register(this, new Release(typeName, ptr, release));
    }

    public void peerClose() {
        closed = true;
        cleanable.clean();
    }

    public boolean peerIsClosed() {
        return closed;
    }

    protected final void checkNotClosed() {
        if (closed) throw new IllegalStateException(typeName + " has been closed");
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof NativeObject)) return false;
        final NativeObject other = (NativeObject) obj;
        return nativePtr == other.nativePtr && typeName.equals(other.typeName);
    }

    @Override
    public int hashCode() {
        return Long.hashCode(nativePtr);
    }

    @Override
    public String toString() {
        return typeName + "{0x" + Long.toHexString(nativePtr) + "}";
    }

    private static final class Release implements Runnable {
        private final String typeName;
        private final long ptr;
        private final LongConsumer release;

        Release(String typeName, long ptr, LongConsumer release) {
            this.typeName = typeName;
            this.ptr = ptr;
            this.release = release;
        }

        @Override
        public void run() {
            if (log.isTraceEnabled()) log.trace("release {} 0x{}", typeName, Long.toHexString(ptr));
            release.accept(ptr);
        }
    }
}
