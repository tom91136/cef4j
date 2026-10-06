package net.kurobako.cef4j.ipc.devtools.jackson;

import javax.annotation.Nonnull;
import net.kurobako.cef4j.cdp.CdpCodec;
import net.kurobako.cef4j.cdp.jackson.JacksonCdpCodec;
import net.kurobako.cef4j.ipc.devtools.DevToolsSessionContract;

final class JacksonDevToolsSessionContractTest extends DevToolsSessionContract {
    @Override
    @Nonnull
    protected CdpCodec codec() {
        return new JacksonCdpCodec();
    }
}
