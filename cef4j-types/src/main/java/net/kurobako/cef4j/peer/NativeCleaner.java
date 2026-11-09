package net.kurobako.cef4j.peer;

import java.lang.ref.Cleaner;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Singleton {@link Cleaner} shared by all generated NativePeer instances. */
@SuppressWarnings({"unused", "ImmutableEnumChecker"})
public enum NativeCleaner {
    INSTANCE;

    private static final Logger log = LoggerFactory.getLogger(NativeCleaner.class);

    private final Cleaner cleaner = Cleaner.create();
    private final Set<Cleaner.Cleanable> active = ConcurrentHashMap.newKeySet();
    private final Object lifecycleLock = new Object();
    private boolean shutdown;

    public Cleaner.Cleanable register(Object obj, Runnable action) {
        if (log.isTraceEnabled()) {
            final Class<?> enclosing = obj.getClass().getEnclosingClass();
            final long result = obj instanceof NativeObject ? ((NativeObject) obj).nativePtr : 0;
            log.trace(
                    "alloc   {} 0x{}",
                    enclosing != null
                            ? enclosing.getSimpleName()
                            : obj.getClass().getSimpleName(),
                    Long.toHexString(result));
        }
        synchronized (lifecycleLock) {
            if (shutdown) {
                // CEF has already shut down. A late Cleaner registration must never call back
                // into native code whose process-global state no longer exists.
                return () -> {};
            }
            // Register the prune-and-release as the clean action so the active set is pruned both when the peer is
            // collected by GC and when releaseAll() cleans it explicitly.
            final Cleaner.Cleanable[] holder = new Cleaner.Cleanable[1];
            final Cleaner.Cleanable c = cleaner.register(obj, () -> {
                active.remove(holder[0]);
                action.run();
            });
            holder[0] = c;
            active.add(c);
            return c;
        }
    }

    /** Force-release all outstanding NativePeers. Call before cef_shutdown. Returns the total ref counts released. */
    public int releaseAll() {
        int released = 0;
        Cleaner.Cleanable[] snapshot = snapshotOfActive();
        while (snapshot.length > 0) {
            if (log.isTraceEnabled()) {
                log.trace("releaseAll: {} outstanding peers", snapshot.length);
            }
            for (Cleaner.Cleanable c : snapshot) {
                c.clean();
                released++;
            }
            snapshot = snapshotOfActive();
        }
        return released;
    }

    /** The cleanables that are registered, which are empty when there is nothing left to release. */
    private Cleaner.Cleanable[] snapshotOfActive() {
        synchronized (lifecycleLock) {
            final Cleaner.Cleanable[] snapshot = active.toArray(new Cleaner.Cleanable[0]);
            // CEF has shut down once there is nothing left to release, so a late registration is refused.
            if (snapshot.length == 0) shutdown = true;
            return snapshot;
        }
    }
}
