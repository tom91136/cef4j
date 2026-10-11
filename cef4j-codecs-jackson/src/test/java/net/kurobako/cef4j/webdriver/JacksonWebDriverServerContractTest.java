package net.kurobako.cef4j.webdriver;

import net.kurobako.cef4j.cdp.jackson.JacksonCdpCodec;
import net.kurobako.cef4j.test.contract.WebDriverServerContract;

final class JacksonWebDriverServerContractTest extends WebDriverServerContract {
    @Override
    protected WebDriverJsonCodec codec() {
        return new WebDriverJsonCodec(new JacksonCdpCodec());
    }
}
