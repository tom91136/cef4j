package net.kurobako.cef4j.ipc.frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ServiceLoader;
import javax.annotation.Nonnull;

/** Codec discovery independent of every control transport and frame publisher. */
public final class FrameCodecs {
    private FrameCodecs() {}

    @Nonnull
    public static FrameCodecProvider find(@Nonnull String id) {
        for (FrameCodecProvider provider : providers()) {
            if (provider.descriptor().id().equalsIgnoreCase(id)) return provider;
        }
        throw new IllegalArgumentException("No frame codec named '" + id + "'; available: " + available());
    }

    @Nonnull
    public static List<String> available() {
        List<String> codecs = new ArrayList<>();
        for (FrameCodecProvider provider : providers()) {
            codecs.add(provider.descriptor().id());
        }
        Collections.sort(codecs);
        return Collections.unmodifiableList(codecs);
    }

    @Nonnull
    public static List<FrameCodecProvider> providers() {
        List<FrameCodecProvider> providers = new ArrayList<>();
        for (FrameCodecProvider provider : ServiceLoader.load(FrameCodecProvider.class)) providers.add(provider);
        return Collections.unmodifiableList(providers);
    }
}
