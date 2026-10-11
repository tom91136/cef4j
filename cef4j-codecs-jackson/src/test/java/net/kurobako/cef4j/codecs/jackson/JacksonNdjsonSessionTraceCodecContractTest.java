package net.kurobako.cef4j.codecs.jackson;

import net.kurobako.cef4j.ipc.session.middleware.SessionTraceCodec;
import net.kurobako.cef4j.test.contract.SessionTraceCodecContract;

final class JacksonNdjsonSessionTraceCodecContractTest extends SessionTraceCodecContract {
    @Override
    protected SessionTraceCodec codec() {
        return JacksonNdjsonSessionTraceCodec.INSTANCE;
    }
}
