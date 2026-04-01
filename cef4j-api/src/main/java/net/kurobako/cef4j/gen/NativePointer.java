// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Opaque native pointer wrapper. Prevents accidental misuse of raw pointer values. */
public final class NativePointer {

    public final long address;

    public NativePointer(long address) {
        this.address = address;
    }

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
}
