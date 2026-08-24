package net.kurobako.cef4j.ipc.frame;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import javax.annotation.Nullable;

final class MappedBufferCleaner {

    @Nullable
    private static final ForeignMapper FOREIGN_MAPPER = ForeignMapper.load();

    static Mapping map(FileChannel channel, long size) throws IOException {
        ForeignMapper foreignMapper = FOREIGN_MAPPER;
        if (foreignMapper != null) return foreignMapper.map(channel, size);
        ByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, size);
        return new Mapping(buffer, false, () -> UnsafeCleaner.clean(buffer));
    }

    static final class Mapping {
        private final ByteBuffer buffer;
        private final boolean scoped;
        private final Releaser releaser;
        private boolean closed;

        private Mapping(ByteBuffer buffer, boolean scoped, Releaser releaser) {
            this.buffer = buffer;
            this.scoped = scoped;
            this.releaser = releaser;
        }

        ByteBuffer buffer() {
            return buffer;
        }

        boolean isScoped() {
            return scoped;
        }

        synchronized boolean close() {
            if (closed) return true;
            closed = true;
            return releaser.release();
        }
    }

    @FunctionalInterface
    private interface Releaser {
        boolean release();
    }

    private static final class ForeignMapper {
        private final Class<?> arenaClass;
        private final Method createArena;
        private final Method map;
        private final Method asByteBuffer;
        private final Method closeArena;

        private ForeignMapper(
                Class<?> arenaClass, Method createArena, Method map, Method asByteBuffer, Method closeArena) {
            this.arenaClass = arenaClass;
            this.createArena = createArena;
            this.map = map;
            this.asByteBuffer = asByteBuffer;
            this.closeArena = closeArena;
        }

        @Nullable
        private static ForeignMapper load() {
            try {
                Class<?> arenaClass = Class.forName("java.lang.foreign.Arena");
                Class<?> segmentClass = Class.forName("java.lang.foreign.MemorySegment");
                return new ForeignMapper(
                        arenaClass,
                        arenaClass.getMethod("ofShared"),
                        FileChannel.class.getMethod(
                                "map", FileChannel.MapMode.class, long.class, long.class, arenaClass),
                        segmentClass.getMethod("asByteBuffer"),
                        arenaClass.getMethod("close"));
            } catch (ReflectiveOperationException | RuntimeException unavailable) {
                return null;
            }
        }

        private Mapping map(FileChannel channel, long size) throws IOException {
            Object arena;
            try {
                arena = createArena.invoke(null);
            } catch (ReflectiveOperationException | RuntimeException e) {
                throw new IOException("failed to create a shared foreign-memory arena", e);
            }
            try {
                Object segment = map.invoke(channel, FileChannel.MapMode.READ_ONLY, 0L, size, arena);
                ByteBuffer buffer = (ByteBuffer) asByteBuffer.invoke(segment);
                return new Mapping(buffer, true, () -> close(arena));
            } catch (InvocationTargetException e) {
                close(arena);
                Throwable cause = e.getCause();
                if (cause instanceof IOException) throw (IOException) cause;
                if (cause instanceof RuntimeException) throw (RuntimeException) cause;
                if (cause instanceof Error) throw (Error) cause;
                throw new IOException("failed to map a shared frame", cause);
            } catch (ReflectiveOperationException | RuntimeException e) {
                close(arena);
                throw new IOException("failed to map a shared frame", e);
            }
        }

        private boolean close(Object arena) {
            if (!arenaClass.isInstance(arena)) return false;
            try {
                closeArena.invoke(arena);
                return true;
            } catch (ReflectiveOperationException | RuntimeException e) {
                return false;
            }
        }
    }

    private static final class UnsafeCleaner {
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
            } catch (ReflectiveOperationException | RuntimeException unavailable) {
                unsafe = null;
                invokeCleaner = null;
            }
            UNSAFE = unsafe;
            INVOKE_CLEANER = invokeCleaner;
        }

        private static boolean clean(ByteBuffer buffer) {
            if (!buffer.isDirect() || UNSAFE == null || INVOKE_CLEANER == null) return false;
            try {
                INVOKE_CLEANER.invoke(UNSAFE, buffer);
                return true;
            } catch (IllegalAccessException | InvocationTargetException | RuntimeException failure) {
                return false;
            }
        }
    }

    private MappedBufferCleaner() {}
}
