package net.kurobako.cef4j.ipc.devtools.jackson;

import net.kurobako.cef4j.cdp.CdpCodec;
import net.kurobako.cef4j.cdp.jackson.JacksonCdpCodec;
import net.kurobako.cef4j.test.contract.DevToolsSessionContract;

final class JacksonDevToolsSessionContractTest extends DevToolsSessionContract {
    @Override
    protected CdpCodec codec() {
        return new JacksonCdpCodec();
    }
}
