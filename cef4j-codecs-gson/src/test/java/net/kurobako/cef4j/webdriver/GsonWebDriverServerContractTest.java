package net.kurobako.cef4j.webdriver;

import net.kurobako.cef4j.cdp.gson.GsonCdpCodec;
import net.kurobako.cef4j.test.contract.WebDriverServerContract;

final class GsonWebDriverServerContractTest extends WebDriverServerContract {
    @Override
    protected WebDriverJsonCodec codec() {
        return new WebDriverJsonCodec(new GsonCdpCodec());
    }
}
