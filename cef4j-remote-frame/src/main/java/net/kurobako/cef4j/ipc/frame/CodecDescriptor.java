package net.kurobako.cef4j.ipc.frame;

import java.util.Objects;
import javax.annotation.Nonnull;

/** Stable wire identity and media type for a frame codec. */
public final class CodecDescriptor {
    private final String id;
    private final String mediaType;
    private final boolean interFrame;

    public CodecDescriptor(@Nonnull String id, @Nonnull String mediaType, boolean interFrame) {
        this.id = requireToken(id, "id");
        this.mediaType = Objects.requireNonNull(mediaType, "mediaType");
        this.interFrame = interFrame;
    }

    @Nonnull
    public String id() {
        return id;
    }

    @Nonnull
    public String mediaType() {
        return mediaType;
    }

    public boolean interFrame() {
        return interFrame;
    }

    private static String requireToken(String value, String name) {
        Objects.requireNonNull(value, name);
        if (!value.matches("[a-z0-9][a-z0-9._-]*")) throw new IllegalArgumentException("invalid codec " + name);
        return value;
    }
}
