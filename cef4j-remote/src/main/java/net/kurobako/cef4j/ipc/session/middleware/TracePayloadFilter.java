package net.kurobako.cef4j.ipc.session.middleware;

import net.kurobako.cef4j.ipc.session.middleware.SessionTrace.Kind;

/**
 * Transforms payloads before they are written to a session trace. Filtering secrets can make a trace unsuitable for
 * strict replay; use it for diagnostic-only recordings or transformations that preserve the wire shape.
 */
@FunctionalInterface
public interface TracePayloadFilter {
    byte[] filter(Kind kind, int messageId, byte[] payload);

    static TracePayloadFilter identity() {
        return (kind, messageId, payload) -> payload;
    }
}
