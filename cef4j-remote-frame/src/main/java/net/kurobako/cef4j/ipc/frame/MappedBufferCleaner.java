package net.kurobako.cef4j.ipc.frame;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;

/** Java 11-compatible access to the JDK's mapped-buffer cleaner without adding a native client dependency. */
final class MappedBufferCleaner {

    @Nullable
    private static final Object UNSAFE;

    @Nullable
    private static final Method INVOKE_CLEANER;

    static {
        Object unsafe = null;
        Method invokeCleaner = null;
        try {
            Class<?> unsafeClass = Class.forName("sun.misc.Unsafe");
            Field theUnsafe = unsafeClass.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = theUnsafe.get(null);
            invokeCleaner = unsafeClass.getMethod("invokeCleaner", ByteBuffer.class);
        } catch (ReflectiveOperationException | RuntimeException ignored) {
            // A reduced/custom runtime may omit jdk.unsupported. The caller retains the normal GC fallback.
        }
        UNSAFE = unsafe;
        INVOKE_CLEANER = invokeCleaner;
    }

    /** Returns whether the direct buffer was explicitly cleaned. */
    static boolean clean(@Nullable ByteBuffer buffer) {
        if (buffer == null || !buffer.isDirect() || UNSAFE == null || INVOKE_CLEANER == null) return false;
        try {
            INVOKE_CLEANER.invoke(UNSAFE, buffer);
            return true;
        } catch (IllegalAccessException | InvocationTargetException | RuntimeException ignored) {
            return false;
        }
    }

    private MappedBufferCleaner() {}
}
