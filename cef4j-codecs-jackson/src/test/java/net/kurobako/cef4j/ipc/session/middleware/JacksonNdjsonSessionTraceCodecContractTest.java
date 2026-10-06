package net.kurobako.cef4j.ipc.session.middleware;

import javax.annotation.Nonnull;

final class JacksonNdjsonSessionTraceCodecContractTest extends SessionTraceCodecContract {
    @Override
    @Nonnull
    protected SessionTraceCodec codec() {
        return JacksonNdjsonSessionTraceCodec.INSTANCE;
    }
}
