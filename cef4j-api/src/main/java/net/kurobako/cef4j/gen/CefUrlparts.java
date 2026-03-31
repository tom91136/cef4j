// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** URL component parts. */
public final class CefUrlparts {

    public final long size;
    public final int spec;
    public final int scheme;
    public final int username;
    public final int password;
    public final int host;
    public final int port;
    public final int origin;
    public final int path;
    public final int query;
    public final int fragment;

    public CefUrlparts(
            long size,
            int spec,
            int scheme,
            int username,
            int password,
            int host,
            int port,
            int origin,
            int path,
            int query,
            int fragment) {
        this.size = size;
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
        if (!(obj instanceof CefUrlparts)) return false;
        CefUrlparts other = (CefUrlparts) obj;
        return this.size == other.size
                && this.spec == other.spec
                && this.scheme == other.scheme
                && this.username == other.username
                && this.password == other.password
                && this.host == other.host
                && this.port == other.port
                && this.origin == other.origin
                && this.path == other.path
                && this.query == other.query
                && this.fragment == other.fragment;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(
                size, spec, scheme, username, password, host, port, origin, path, query, fragment);
    }

    @Override
    public String toString() {
        return "CefUrlparts{" + "size=" + size + ", " + "spec=" + spec + ", " + "scheme=" + scheme + ", " + "username="
                + username + ", " + "password=" + password + ", " + "host=" + host + ", " + "port=" + port + ", "
                + "origin=" + origin + ", " + "path=" + path + ", " + "query=" + query + ", " + "fragment=" + fragment
                + "}";
    }
}
