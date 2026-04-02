// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Device information for a MediaSink object.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef struct _cef_media_sink_device_info_t {
 *   size_t size;
 *   cef_string_t* ip_address;
 *   int port;
 *   cef_string_t* model_name;
 * } cef_media_sink_device_info_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h:3600</a>
 */
public final class CefMediaSinkDeviceInfo {

    // Native struct size, populated by the JNI layer with sizeof(struct) as required by CEF. Not user-modifiable.
    @SuppressWarnings("FieldMayBeFinal")
    private volatile long size = -1;

    public final String ipAddress;
    public final int port;
    public final String modelName;

    public CefMediaSinkDeviceInfo(String ipAddress, int port, String modelName) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.modelName = modelName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefMediaSinkDeviceInfo)) return false;
        CefMediaSinkDeviceInfo other = (CefMediaSinkDeviceInfo) obj;
        return java.util.Objects.equals(this.ipAddress, other.ipAddress)
                && this.port == other.port
                && java.util.Objects.equals(this.modelName, other.modelName);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(ipAddress, port, modelName);
    }

    @Override
    public String toString() {
        return "CefMediaSinkDeviceInfo{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", "
                + "ipAddress=" + ipAddress + ", " + "port=" + port + ", " + "modelName=" + modelName + "}";
    }
}
