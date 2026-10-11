package net.kurobako.cef4j.codecs.gson;

import net.kurobako.cef4j.ipc.session.middleware.SessionTraceCodec;
import net.kurobako.cef4j.test.contract.SessionTraceCodecContract;

final class GsonNdjsonSessionTraceCodecContractTest extends SessionTraceCodecContract {
    @Override
    protected SessionTraceCodec codec() {
        return GsonNdjsonSessionTraceCodec.INSTANCE;
    }
}
