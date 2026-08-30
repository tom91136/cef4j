package net.kurobako.cef4j.ipc.frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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
        Map<String, FrameCodecProvider> providers = new LinkedHashMap<>();
        for (FrameCodecProvider provider : ServiceLoader.load(FrameCodecProvider.class)) {
            String id = provider.descriptor().id().toLowerCase(Locale.ROOT);
            FrameCodecProvider previous = providers.putIfAbsent(id, provider);
            if (previous != null) {
                throw new IllegalStateException("Duplicate frame codec id '" + id + "': "
                        + previous.getClass().getName() + ", "
                        + provider.getClass().getName());
            }
        }
        return Collections.unmodifiableList(new ArrayList<>(providers.values()));
    }
}
