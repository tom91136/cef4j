package net.kurobako.cef4j.ipc.session;

/**
 * A typed wrapper around an int32 handle id that identifies a ref-counted CEF object owned by the helper. The helper
 * maintains a {@code HandleTable} mapping ids back to native CEF struct pointers; this class is the JVM side's view.
 *
 * <p>Equality is by id. The class is intentionally minimal — adding lifecycle (retain/release across IPC) is a planned
 * follow-up; for now handles are valid until the helper process exits.
 */
public final class RemoteHandle {

    /** Sentinel returned by helper-side decoders when a CEF call yielded no handle. */
    public static final RemoteHandle NULL = new RemoteHandle(0);

    private final int id;

    public RemoteHandle(int id) {
        this.id = id;
    }

    public int id() {
        return id;
    }

    public boolean isNull() {
        return id == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RemoteHandle)) return false;
        return id == ((RemoteHandle) o).id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "RemoteHandle(" + id + ")";
    }
}
