package net.kurobako.cef4j.ipc.devtools.gson;

import javax.annotation.Nonnull;
import net.kurobako.cef4j.cdp.CdpCodec;
import net.kurobako.cef4j.cdp.gson.GsonCdpCodec;
import net.kurobako.cef4j.ipc.devtools.DevToolsSessionContract;

final class GsonDevToolsSessionContractTest extends DevToolsSessionContract {
    @Override
    @Nonnull
    protected CdpCodec codec() {
        return new GsonCdpCodec();
    }
}
