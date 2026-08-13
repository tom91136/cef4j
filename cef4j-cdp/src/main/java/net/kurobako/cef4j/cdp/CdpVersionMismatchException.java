package net.kurobako.cef4j.cdp;

import javax.annotation.Nullable;

public final class CdpVersionMismatchException extends IllegalStateException {
    private static final long serialVersionUID = 1L;

    private final String expectedChromiumVersion;

    @Nullable
    private final String actualProduct;

    public CdpVersionMismatchException(String expectedChromiumVersion, @Nullable String actualProduct) {
        super("CDP schema expects Chromium " + expectedChromiumVersion + " but Browser.getVersion returned "
                + actualProduct);
        this.expectedChromiumVersion = expectedChromiumVersion;
        this.actualProduct = actualProduct;
    }

    public String expectedChromiumVersion() {
        return expectedChromiumVersion;
    }

    @Nullable
    public String actualProduct() {
        return actualProduct;
    }
}
