// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import javax.annotation.Nullable;

/**
 * URL component parts.
 * <p>Definition generated from internal/cef_types.h
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
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">internal/cef_types.h:762</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public final class CefUrlParts {

    // Native struct size, populated by the JNI layer with sizeof(struct) as required by CEF. Not user-modifiable.
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    private volatile long size = -1;

        /**
         * The complete URL specification. 
         */    public final @Nullable String spec;
        /**
         * Scheme component not including the colon (e.g., "http"). 
         */    public final @Nullable String scheme;
        /**
         * User name component. 
         */    public final @Nullable String username;
        /**
         * Password component. 
         */    public final @Nullable String password;
        /**
         * Host component. This may be a hostname, an IPv4 address or an IPv6 literal surrounded by square brackets (e.g., "[2001:db8::1]"). 
         */    public final @Nullable String host;
        /**
         * Port number component. 
         */    public final @Nullable String port;
        /**
         * Origin contains just the scheme, host, and port from a URL. Equivalent to clearing any username and password, replacing the path with a slash, and clearing everything after that. This value will be empty for non-standard URLs. 
         */    public final @Nullable String origin;
        /**
         * Path component including the first slash following the host. 
         */    public final @Nullable String path;
        /**
         * Query string component (i.e., everything following the '?'). 
         */    public final @Nullable String query;
        /**
         * Fragment (hash) identifier component (i.e., the string following the '#'). 
         */    public final @Nullable String fragment;

    public CefUrlParts(@Nullable String spec, @Nullable String scheme, @Nullable String username, @Nullable String password, @Nullable String host, @Nullable String port, @Nullable String origin, @Nullable String path, @Nullable String query, @Nullable String fragment) {
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

    /** Create a mutable copy of this instance. */
    public Mutable toMutable() {
        return new Mutable(this.spec, this.scheme, this.username, this.password, this.host, this.port, this.origin, this.path, this.query, this.fragment);
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
        return "CefUrlParts{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", " + "spec=" + spec + ", " + "scheme=" + scheme + ", " + "username=" + username + ", " + "password=" + password + ", " + "host=" + host + ", " + "port=" + port + ", " + "origin=" + origin + ", " + "path=" + path + ", " + "query=" + query + ", " + "fragment=" + fragment + "}";
    }

    /**
     * Mutable variant of {@link CefUrlParts}. URL component parts.
     * <p>Definition generated from internal/cef_types.h
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">internal/cef_types.h:762</a>
     */
    public static final class Mutable {

    // Native struct size, populated by the JNI layer with sizeof(struct) as required by CEF. Not user-modifiable.
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    private volatile long size = -1;

            /**
             * The complete URL specification. 
             */        public @Nullable String spec;
            /**
             * Scheme component not including the colon (e.g., "http"). 
             */        public @Nullable String scheme;
            /**
             * User name component. 
             */        public @Nullable String username;
            /**
             * Password component. 
             */        public @Nullable String password;
            /**
             * Host component. This may be a hostname, an IPv4 address or an IPv6 literal surrounded by square brackets (e.g., "[2001:db8::1]"). 
             */        public @Nullable String host;
            /**
             * Port number component. 
             */        public @Nullable String port;
            /**
             * Origin contains just the scheme, host, and port from a URL. Equivalent to clearing any username and password, replacing the path with a slash, and clearing everything after that. This value will be empty for non-standard URLs. 
             */        public @Nullable String origin;
            /**
             * Path component including the first slash following the host. 
             */        public @Nullable String path;
            /**
             * Query string component (i.e., everything following the '?'). 
             */        public @Nullable String query;
            /**
             * Fragment (hash) identifier component (i.e., the string following the '#'). 
             */        public @Nullable String fragment;

        public Mutable() {}

        public Mutable(@Nullable String spec, @Nullable String scheme, @Nullable String username, @Nullable String password, @Nullable String host, @Nullable String port, @Nullable String origin, @Nullable String path, @Nullable String query, @Nullable String fragment) {
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

        /** Create an immutable snapshot of this instance. */
        public CefUrlParts toImmutable() {
            return new CefUrlParts(this.spec, this.scheme, this.username, this.password, this.host, this.port, this.origin, this.path, this.query, this.fragment);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Mutable)) return false;
            Mutable other = (Mutable) obj;
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
            return "CefUrlParts.Mutable{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", " + "spec=" + spec + ", " + "scheme=" + scheme + ", " + "username=" + username + ", " + "password=" + password + ", " + "host=" + host + ", " + "port=" + port + ", " + "origin=" + origin + ", " + "path=" + path + ", " + "query=" + query + ", " + "fragment=" + fragment + "}";
        }
    }
}
