package net.kurobako.cef4j.ipc.session;

/**
 * A typed wrapper around an int32 handle id that identifies a ref-counted CEF object owned by the runtime server. The
 * server maintains a {@code HandleTable} mapping ids back to native CEF struct pointers; this is the client's view.
 *
 * <p>Equality is by id. The server keeps the native object alive until release; handles become invalid when their
 * owning server exits.
 */
public final class RemoteHandle {

    /** Sentinel returned by server-side decoders when a CEF call yielded no handle. */
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
