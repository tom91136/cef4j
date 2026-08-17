package net.kurobako.cef4j.codegen.passes

import java.nio.file.Path

import net.kurobako.cef4j.codegen.AtomicFiles
import net.kurobako.cef4j.codegen.Banners

object EmitNativePointer {
  def apply(outJava: Path, javaPackage: String)(using Banners): Unit = {
    val content =
      s"""${Banners.java}
package $javaPackage;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.LongConsumer;
import ${Banners.javaAnnotationClass};

/** Opaque native pointer wrapper. Raw pointers without a known bound is lowered to this representation, use with caution. */
${Banners.javaAnnotation}
public class NativePointer {

    public final long address;

    /** Creates a native pointer from the given address. The address is not checked in any way, use with caution. */
    public NativePointer(long address) {
        this.address = address;
    }

    /** Represents a nullptr (0), for use where CEF accepts NULL. */
    public static NativePointer NULL = new NativePointer(0);

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof NativePointer)) return false;
        return this.address == ((NativePointer) obj).address;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(address);
    }

    @Override
    public String toString() {
        return "NativePointer{0x" + Long.toHexString(address) + "}";
    }

    /**
     * A {@link NativePointer} that owns the native memory it points to. Implements {@link AutoCloseable}
     * for use in try-with-resources - when {@link #close()} is called, the destructor is invoked to free
     * the native memory.
     */
    public static final class Managed extends NativePointer implements AutoCloseable {

        private final AtomicBoolean closed = new AtomicBoolean(false);
        private final LongConsumer destructor;

        /**
         * Creates an owned native pointer with a custom destructor.
         *
         * @param address the native memory address
         * @param destructor called with the address when {@link #close()} is invoked; may be null for no-op
         */
        public Managed(long address, LongConsumer destructor) {
            super(address);
            this.destructor = destructor;
        }

        @Override
        public void close() {
            if (closed.compareAndSet(false, true) && address != 0 && destructor != null) {
                destructor.accept(address);
            }
        }

        @Override
        public String toString() {
            return "NativePointer.Managed{0x" + Long.toHexString(address) + (closed.get() ? ", closed" : "") + "}";
        }
    }
}
"""
    AtomicFiles.writeString(outJava.resolve("NativePointer.java"), content)
  }
}
