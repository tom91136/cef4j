// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Device information for a MediaSink object. */
public final class CefMediaSinkDeviceInfo {

    public final long size;
    public final int ipAddress;
    public final int port;
    public final int modelName;

    public CefMediaSinkDeviceInfo(long size, int ipAddress, int port, int modelName) {
        this.size = size;
        this.ipAddress = ipAddress;
        this.port = port;
        this.modelName = modelName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefMediaSinkDeviceInfo)) return false;
        CefMediaSinkDeviceInfo other = (CefMediaSinkDeviceInfo) obj;
        return this.size == other.size
                && this.ipAddress == other.ipAddress
                && this.port == other.port
                && this.modelName == other.modelName;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(size, ipAddress, port, modelName);
    }

    @Override
    public String toString() {
        return "CefMediaSinkDeviceInfo{" + "size=" + size + ", " + "ipAddress=" + ipAddress + ", " + "port=" + port
                + ", " + "modelName=" + modelName + "}";
    }
}
