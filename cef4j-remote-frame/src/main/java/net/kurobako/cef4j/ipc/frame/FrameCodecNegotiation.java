package net.kurobako.cef4j.ipc.frame;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import javax.annotation.Nonnull;

/** Transport-neutral codec offer/selection used during stream setup or reconnection. */
public final class FrameCodecNegotiation {
    private FrameCodecNegotiation() {}

    /**
     * Selects the first local preference with an exactly compatible peer descriptor. An exact media type and
     * inter-frame flag match prevents two independently installed providers with the same id from miscommunicating.
     */
    @Nonnull
    public static Optional<CodecDescriptor> select(
            @Nonnull List<CodecDescriptor> localPreference, @Nonnull Collection<CodecDescriptor> peerOffer) {
        Objects.requireNonNull(localPreference, "localPreference");
        Objects.requireNonNull(peerOffer, "peerOffer");
        Map<String, CodecDescriptor> peerById = new LinkedHashMap<>();
        for (CodecDescriptor descriptor : peerOffer) {
            CodecDescriptor previous = peerById.put(descriptor.id(), descriptor);
            if (previous != null) throw new IllegalArgumentException("duplicate peer codec id: " + descriptor.id());
        }
        for (CodecDescriptor local : localPreference) {
            CodecDescriptor peer = peerById.get(local.id());
            if (peer != null
                    && peer.mediaType().equalsIgnoreCase(local.mediaType())
                    && peer.interFrame() == local.interFrame()) return Optional.of(local);
        }
        return Optional.empty();
    }

    /** Returns installed descriptors in preference order, with the requested ids first. */
    @Nonnull
    public static List<CodecDescriptor> installed(@Nonnull List<String> preferredIds) {
        Objects.requireNonNull(preferredIds, "preferredIds");
        Map<String, CodecDescriptor> installed = new LinkedHashMap<>();
        for (FrameCodecProvider provider : FrameCodecs.providers()) {
            CodecDescriptor descriptor = provider.descriptor();
            if (installed.put(descriptor.id(), descriptor) != null)
                throw new IllegalStateException("duplicate installed codec id: " + descriptor.id());
        }
        List<CodecDescriptor> result = new ArrayList<>();
        for (String id : preferredIds) {
            CodecDescriptor descriptor = installed.remove(id);
            if (descriptor != null) result.add(descriptor);
        }
        result.addAll(installed.values());
        return Collections.unmodifiableList(result);
    }
}
