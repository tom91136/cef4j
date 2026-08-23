package net.kurobako.cef4j.webdriver;

import javax.annotation.Nonnull;
import net.kurobako.cef4j.webdriver.jackson.JacksonWebDriverJsonCodec;

final class JacksonWebDriverServerContractTest extends WebDriverServerContract {
    @Override
    @Nonnull
    protected WebDriverJsonCodec codec() {
        return new JacksonWebDriverJsonCodec();
    }
}
