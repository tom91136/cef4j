package net.kurobako.cef4j.ipc.session.middleware;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import javax.annotation.Nonnull;

/**
 * Pluggable serialization for transport-independent session traces. The SPI contains no JSON-library-specific types.
 */
public interface SessionTraceCodec {
    @Nonnull
    String format();

    @Nonnull
    String fileExtension();

    @Nonnull
    SessionTraceWriter openWriter(@Nonnull OutputStream destination, @Nonnull Map<String, String> metadata)
            throws IOException;

    @Nonnull
    SessionTrace.Recording read(@Nonnull InputStream source) throws IOException;
}
