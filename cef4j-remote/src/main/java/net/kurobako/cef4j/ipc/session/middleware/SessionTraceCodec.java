package net.kurobako.cef4j.ipc.session.middleware;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * Pluggable serialization for transport-independent session traces. The SPI contains no JSON-library-specific types.
 */
public interface SessionTraceCodec {
    String format();

    String fileExtension();

    SessionTraceWriter openWriter(OutputStream destination, Map<String, String> metadata) throws IOException;

    SessionTrace.Recording read(InputStream source) throws IOException;
}
