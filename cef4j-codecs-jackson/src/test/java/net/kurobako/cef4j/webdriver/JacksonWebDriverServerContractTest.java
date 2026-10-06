package net.kurobako.cef4j.webdriver;

import javax.annotation.Nonnull;
import net.kurobako.cef4j.cdp.jackson.JacksonCdpCodec;

final class JacksonWebDriverServerContractTest extends WebDriverServerContract {
    @Override
    @Nonnull
    protected WebDriverJsonCodec codec() {
        return new WebDriverJsonCodec(new JacksonCdpCodec());
    }
}
