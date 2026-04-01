// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * URL component parts.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef struct _cef_urlparts_t {
 *   size_t size;
 *   cef_string_t* spec;
 *   cef_string_t* scheme;
 *   cef_string_t* username;
 *   cef_string_t* password;
 *   cef_string_t* host;
 *   cef_string_t* port;
 *   cef_string_t* origin;
 *   cef_string_t* path;
 *   cef_string_t* query;
 *   cef_string_t* fragment;
 * } cef_urlparts_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h:762</a>
 */
public final class CefUrlParts {

    // Native struct size — set by JNI, not user-modifiable.
    @SuppressWarnings("FieldMayBeFinal")
    private volatile long size = -1;

    public final String spec;
    public final String scheme;
    public final String username;
    public final String password;
    public final String host;
    public final String port;
    public final String origin;
    public final String path;
    public final String query;
    public final String fragment;

    public CefUrlParts(
            String spec,
            String scheme,
            String username,
            String password,
            String host,
            String port,
            String origin,
            String path,
            String query,
            String fragment) {
        this.spec = spec;
        this.scheme = scheme;
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = port;
        this.origin = origin;
        this.path = path;
        this.query = query;
        this.fragment = fragment;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefUrlParts)) return false;
        CefUrlParts other = (CefUrlParts) obj;
        return java.util.Objects.equals(this.spec, other.spec)
                && java.util.Objects.equals(this.scheme, other.scheme)
                && java.util.Objects.equals(this.username, other.username)
                && java.util.Objects.equals(this.password, other.password)
                && java.util.Objects.equals(this.host, other.host)
                && java.util.Objects.equals(this.port, other.port)
                && java.util.Objects.equals(this.origin, other.origin)
                && java.util.Objects.equals(this.path, other.path)
                && java.util.Objects.equals(this.query, other.query)
                && java.util.Objects.equals(this.fragment, other.fragment);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(spec, scheme, username, password, host, port, origin, path, query, fragment);
    }

    @Override
    public String toString() {
        return "CefUrlParts{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", " + "spec=" + spec + ", "
                + "scheme=" + scheme + ", " + "username=" + username + ", " + "password=" + password + ", " + "host="
                + host + ", " + "port=" + port + ", " + "origin=" + origin + ", " + "path=" + path + ", " + "query="
                + query + ", " + "fragment=" + fragment + "}";
    }
}
