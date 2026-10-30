package net.kurobako.cef4j.ipc.session.process;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;

/** Versioned bootstrap description published by a cef4j runtime server after it has bound its endpoint. */
public final class RuntimeServerHandshake {
    public static final String PREFIX = "CEF4J_RUNTIME_SERVER ";
    public static final int CURRENT_PROTOCOL_VERSION = 1;
    public static final String REMOTE_CEF_API = "remote-cef";

    private final int protocolVersion;
    private final String api;
    private final int cefApiVersion;
    private final String transport;
    private final String frameTransport;
    private final String endpoint;
    private final Set<String> capabilities;

    private RuntimeServerHandshake(
            int protocolVersion,
            String api,
            int cefApiVersion,
            String transport,
            String frameTransport,
            String endpoint,
            Set<String> capabilities) {
        this.protocolVersion = protocolVersion;
        this.api = api;
        this.cefApiVersion = cefApiVersion;
        this.transport = transport;
        this.frameTransport = frameTransport;
        this.endpoint = endpoint;
        this.capabilities = Collections.unmodifiableSet(new LinkedHashSet<>(capabilities));
    }

    public static RuntimeServerHandshake parse(String line) {
        Objects.requireNonNull(line, "line");
        if (!line.startsWith(PREFIX)) throw new IllegalArgumentException("not a cef4j runtime server handshake");
        final Map<String, String> fields = new LinkedHashMap<>();
        final StringTokenizer tokens = new StringTokenizer(line.substring(PREFIX.length()), " ");
        while (tokens.hasMoreTokens()) {
            final String token = tokens.nextToken();
            final int equals = token.indexOf('=');
            if (equals <= 0 || equals == token.length() - 1) {
                throw new IllegalArgumentException("malformed handshake field: " + token);
            }
            final String previous = fields.put(token.substring(0, equals), token.substring(equals + 1));
            if (previous != null) throw new IllegalArgumentException("duplicate handshake field: " + token);
        }

        final int protocol = requiredInt(fields, "protocol", "invalid protocol version");
        if (protocol != CURRENT_PROTOCOL_VERSION) {
            throw new IllegalArgumentException("unsupported runtime server protocol version: " + protocol);
        }
        final String api = required(fields, "api");
        if (!REMOTE_CEF_API.equals(api)) throw new IllegalArgumentException("unsupported runtime server API: " + api);
        final int cefApiVersion = requiredInt(fields, "cef-api", "invalid CEF API version");
        if (cefApiVersion <= 0) throw new IllegalArgumentException("invalid CEF API version: " + cefApiVersion);
        final Set<String> capabilities = new LinkedHashSet<>();
        final String capabilityList = required(fields, "capabilities");
        final StringTokenizer capabilityTokens = new StringTokenizer(capabilityList, ",");
        while (capabilityTokens.hasMoreTokens()) {
            capabilities.add(capabilityTokens.nextToken());
        }
        if (!capabilities.contains("remote-cef-api")) {
            throw new IllegalArgumentException("runtime server does not advertise remote-cef-api");
        }
        return new RuntimeServerHandshake(
                protocol,
                api,
                cefApiVersion,
                required(fields, "transport"),
                required(fields, "frame"),
                required(fields, "endpoint"),
                capabilities);
    }

    /** The number of the field, which the handshake has to hold. */
    private static int requiredInt(Map<String, String> fields, String name, String invalidMessage) {
        try {
            return Integer.parseInt(required(fields, name));
        } catch (NumberFormatException failure) {
            throw new IllegalArgumentException(invalidMessage, failure);
        }
    }

    private static String required(Map<String, String> fields, String name) {
        final String value = fields.get(name);
        if (value == null || value.isEmpty()) throw new IllegalArgumentException("missing handshake field: " + name);
        return value;
    }

    public int protocolVersion() {
        return protocolVersion;
    }

    public String api() {
        return api;
    }

    /** Numeric CEF API version used to generate the exported Remote CEF API. */
    public int cefApiVersion() {
        return cefApiVersion;
    }

    public String transport() {
        return transport;
    }

    public String frameTransport() {
        return frameTransport;
    }

    public String endpoint() {
        return endpoint;
    }

    public Set<String> capabilities() {
        return capabilities;
    }

    public boolean hasCapability(String capability) {
        return capabilities.contains(Objects.requireNonNull(capability, "capability"));
    }

    @Override
    public String toString() {
        return "RuntimeServerHandshake{protocol=" + protocolVersion + ", api='" + api + "', cefApi=" + cefApiVersion
                + ", transport='" + transport + "', frame='" + frameTransport + "', endpoint='" + endpoint
                + "', capabilities=" + capabilities + "}";
    }
}
