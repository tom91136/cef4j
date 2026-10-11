package net.kurobako.cef4j.ipc.devtools.gson;

import net.kurobako.cef4j.cdp.CdpCodec;
import net.kurobako.cef4j.cdp.gson.GsonCdpCodec;
import net.kurobako.cef4j.test.contract.DevToolsSessionContract;

final class GsonDevToolsSessionContractTest extends DevToolsSessionContract {
    @Override
    protected CdpCodec codec() {
        return new GsonCdpCodec();
    }
}
