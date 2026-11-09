package net.kurobako.cef4j.ipc.session;

import java.util.concurrent.CompletableFuture;
import org.jspecify.annotations.Nullable;

/**
 * A typed wrapper around a {@link RemoteHandle} that points at a runtime-server-side ref-counted CEF object.
 *
 * <p>Closing releases the handle at most once. Every close, whether through {@link #close()} or {@link #closeAsync()},
 * shares the one release future.
 */
public abstract class RemoteFacade implements AutoCloseable {
    protected final CefSession session;
    protected final RemoteHandle handle;
    private final Object closeLock = new Object();

    @Nullable
    private CompletableFuture<Void> closeFuture;

    protected RemoteFacade(CefSession session, RemoteHandle handle) {
        this.session = session;
        this.handle = handle;
    }

    public final RemoteHandle handle() {
        return handle;
    }

    /** Sends the message that releases {@link #handle()} on the server. */
    protected abstract CompletableFuture<Void> release();

    public final CompletableFuture<Void> closeAsync() {
        synchronized (closeLock) {
            CompletableFuture<Void> future = closeFuture;
            if (future == null) {
                future = release();
                closeFuture = future;
            }
            return future;
        }
    }

    public final CompletableFuture<Void> releaseHandle() {
        return closeAsync();
    }

    @Override
    @SuppressWarnings("FutureReturnValueIgnored")
    public final void close() {
        closeAsync();
    }
}
