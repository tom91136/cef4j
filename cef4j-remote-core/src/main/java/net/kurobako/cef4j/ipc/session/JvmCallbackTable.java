package net.kurobako.cef4j.ipc.session;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Client-side handle table for callback objects (e.g. visitors) that the client owns and the server invokes.
 *
 * <p>Inverse of {@code HandleTable<T>} on the server: server-owned CEF objects get an int32 id the client can
 * reference. Here, client-owned Java callbacks get an int32 id the server can reference.
 *
 * <p>Lifecycle:
 *
 * <ul>
 *   <li>{@link #register} stores the callback under a fresh id (returned for the caller to ship to the server as a
 *       method parameter). Until {@link #release} is called the entry stays live and dispatches into the Java object.
 *   <li>{@link #lookup} retrieves the callback when an inbound event names this id. Returns {@code null} for
 *       already-released or unknown ids — the caller decides whether to ignore or log.
 *   <li>{@link #release} drops the entry. Used after a one-shot callback has fired (most CEF visitors are one-shot) or
 *       at session shutdown to avoid retaining the Java side longer than the server.
 * </ul>
 *
 * <p>Thread-safety: backed by {@link ConcurrentHashMap}, so register/lookup/release are safe across the transport
 * reader thread (where lookup happens) and arbitrary caller threads (where register/release usually happen).
 */
public final class JvmCallbackTable<T> {

    private final ConcurrentHashMap<Integer, T> entries = new ConcurrentHashMap<>();
    // Start at 1 so 0 can serve as a "no callback" sentinel on the wire (mirrors RemoteHandle.NULL).
    private final AtomicInteger next = new AtomicInteger(1);

    /** Registers {@code callback} and returns its int32 id. */
    public int register(@Nonnull T callback) {
        // updateAndGet keeps the wrap atomic — naive check-then-reset lets two racers both observe a wrapped
        // negative id, both reset to 2, and both return id=1, silently overwriting each other in `entries`.
        // The lambda yields the new "next" value; the id we hand out is one less.
        int updated = next.updateAndGet(prev -> (prev <= 0 ? 1 : prev) + 1);
        int id = updated - 1;
        entries.put(id, callback);
        return id;
    }

    /** Looks up a callback by id, or {@code null} if it's unknown / already released. */
    // null means no callback registered under the id (consumed by generated routing code)
    @SuppressWarnings("NullableForbidden")
    @Nullable
    public T lookup(int id) {
        if (id == 0) return null;
        return entries.get(id);
    }

    /** Drops the entry for {@code id}; subsequent {@link #lookup} calls return {@code null}. */
    public void release(int id) {
        if (id == 0) return;
        entries.remove(id);
    }
}
