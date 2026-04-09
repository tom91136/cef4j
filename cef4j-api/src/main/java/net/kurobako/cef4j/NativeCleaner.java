package net.kurobako.cef4j;

import java.lang.ref.Cleaner;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Singleton {@link Cleaner} shared by all generated NativePeer instances. */
@SuppressWarnings("unused")
public enum NativeCleaner {
    INSTANCE;

    private static final Logger log = LoggerFactory.getLogger(NativeCleaner.class);

    private final Cleaner cleaner = Cleaner.create();
    private final Set<Cleaner.Cleanable> active = ConcurrentHashMap.newKeySet();

    public Cleaner.Cleanable register(Object obj, Runnable action) {
        if (log.isTraceEnabled()) {
            Class<?> enclosing = obj.getClass().getEnclosingClass();
            long result;
            try {
                var f = obj.getClass().getDeclaredField("nativePtr");
                f.setAccessible(true);
                result = f.getLong(obj);
            } catch (ReflectiveOperationException e) {
                result = 0;
            }
            log.trace(
                    "alloc   {} 0x{}",
                    enclosing != null
                            ? enclosing.getSimpleName()
                            : obj.getClass().getSimpleName(),
                    Long.toHexString(result));
        }
        Cleaner.Cleanable c = cleaner.register(obj, action);
        Cleaner.Cleanable[] holder = new Cleaner.Cleanable[1];
        holder[0] = () -> {
            active.remove(holder[0]);
            c.clean();
        };
        active.add(holder[0]);
        return holder[0];
    }

    /** Force-release all outstanding NativePeers. Call before cef_shutdown. Returns the total ref counts released. */
    public int releaseAll() {
        Set<Cleaner.Cleanable> snapshot = Set.copyOf(active);
        if (log.isTraceEnabled() && !snapshot.isEmpty()) {
            log.trace("releaseAll: {} outstanding peers", snapshot.size());
        }
        for (Cleaner.Cleanable c : snapshot) {
            c.clean();
        }
        active.clear();
        return snapshot.size();
    }
}
