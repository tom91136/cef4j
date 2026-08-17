package net.kurobako.cef4j.ipc.session.middleware;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ServiceLoader;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/** Versioned, transport-independent recording of logical {@code CefSession} operations. */
public final class SessionTrace {
    public static final String FORMAT = "cef4j-session-api";
    public static final int VERSION = 1;

    /** Trace entry type. Registration entries are replay barriers; delivery entries are driven by the replay clock. */
    public enum Kind {
        REQUEST,
        RESPONSE,
        FAILURE,
        SUBSCRIBE,
        SUBSCRIBE_LATEST,
        UNSUBSCRIBE,
        EVENT,
        INTERCEPT_REGISTER,
        INTERCEPT_UNREGISTER,
        INTERCEPT,
        INTERCEPT_RESPONSE,
        CLOSE
    }

    /** Immutable trace entry. Payload is copied at construction and when exposed. */
    public static final class Entry {
        public final long sequence;
        public final long elapsedNanos;
        public final Kind kind;
        public final long operationId;
        public final int messageId;

        @Nullable
        private final byte[] payload;

        @Nullable
        public final String detailType;

        @Nullable
        public final String detailMessage;

        public Entry(
                long sequence,
                long elapsedNanos,
                @Nonnull Kind kind,
                long operationId,
                int messageId,
                @Nullable byte[] payload,
                @Nullable String detailType,
                @Nullable String detailMessage) {
            if (sequence < 1) throw new IllegalArgumentException("sequence must be positive");
            if (elapsedNanos < 0) throw new IllegalArgumentException("elapsedNanos must not be negative");
            this.sequence = sequence;
            this.elapsedNanos = elapsedNanos;
            this.kind = Objects.requireNonNull(kind, "kind");
            this.operationId = operationId;
            this.messageId = messageId;
            this.payload = payload == null ? null : payload.clone();
            this.detailType = detailType;
            this.detailMessage = detailMessage;
        }

        @Nullable
        public byte[] payload() {
            return payload == null ? null : payload.clone();
        }
    }

    /** Complete in-memory trace plus descriptive metadata. */
    public static final class Recording {
        private final Map<String, String> metadata;
        private final List<Entry> entries;

        public Recording(@Nonnull Map<String, String> metadata, @Nonnull List<Entry> entries) {
            this.metadata = Collections.unmodifiableMap(new LinkedHashMap<>(metadata));
            this.entries = Collections.unmodifiableList(new ArrayList<>(entries));
        }

        public Map<String, String> metadata() {
            return metadata;
        }

        public List<Entry> entries() {
            return entries;
        }
    }

    private SessionTrace() {}

    /**
     * The installed codec used by convenience methods. Add a codec adapter such as {@code cef4j-codecs-gson} or
     * {@code cef4j-codecs-jackson} to the runtime class path, or call an overload that accepts a codec.
     */
    public static SessionTraceCodec defaultCodec() {
        return ServiceLoader.load(SessionTraceCodec.class)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No SessionTraceCodec installed; add cef4j-codecs-gson or "
                        + "cef4j-codecs-jackson, or supply a codec"));
    }

    public static SessionTraceWriter writer(@Nonnull Path file) throws IOException {
        return writer(file, defaultMetadata(), defaultCodec());
    }

    public static SessionTraceWriter writer(@Nonnull Path file, @Nonnull Map<String, String> metadata)
            throws IOException {
        return writer(file, metadata, defaultCodec());
    }

    public static SessionTraceWriter writer(
            @Nonnull Path file, @Nonnull Map<String, String> metadata, @Nonnull SessionTraceCodec codec)
            throws IOException {
        Objects.requireNonNull(file, "file");
        Objects.requireNonNull(metadata, "metadata");
        Objects.requireNonNull(codec, "codec");
        OutputStream output = Files.newOutputStream(
                file, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
        try {
            return codec.openWriter(output, metadata);
        } catch (IOException | RuntimeException failure) {
            try {
                output.close();
            } catch (IOException closeFailure) {
                failure.addSuppressed(closeFailure);
            }
            throw failure;
        }
    }

    public static Recording read(@Nonnull Path file) throws IOException {
        return read(file, defaultCodec());
    }

    public static Recording read(@Nonnull Path file, @Nonnull SessionTraceCodec codec) throws IOException {
        Objects.requireNonNull(file, "file");
        Objects.requireNonNull(codec, "codec");
        try (InputStream input = Files.newInputStream(file)) {
            return codec.read(input);
        }
    }

    static Map<String, String> defaultMetadata() {
        Map<String, String> metadata = new LinkedHashMap<>();
        metadata.put("java.version", System.getProperty("java.version", "unknown"));
        metadata.put("os.name", System.getProperty("os.name", "unknown"));
        metadata.put("os.arch", System.getProperty("os.arch", "unknown"));
        return metadata;
    }
}
