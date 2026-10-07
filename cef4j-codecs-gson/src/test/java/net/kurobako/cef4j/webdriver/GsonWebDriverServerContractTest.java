package net.kurobako.cef4j.webdriver;

import javax.annotation.Nonnull;
import net.kurobako.cef4j.cdp.gson.GsonCdpCodec;

final class GsonWebDriverServerContractTest extends WebDriverServerContract {
    @Override
    @Nonnull
    protected WebDriverJsonCodec codec() {
        return new WebDriverJsonCodec(new GsonCdpCodec());
    }
}
