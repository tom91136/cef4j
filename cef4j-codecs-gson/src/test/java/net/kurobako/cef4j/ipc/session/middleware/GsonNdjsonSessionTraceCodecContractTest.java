package net.kurobako.cef4j.ipc.session.middleware;

import javax.annotation.Nonnull;

final class GsonNdjsonSessionTraceCodecContractTest extends SessionTraceCodecContract {
    @Override
    @Nonnull
    protected SessionTraceCodec codec() {
        return GsonNdjsonSessionTraceCodec.INSTANCE;
    }
}
