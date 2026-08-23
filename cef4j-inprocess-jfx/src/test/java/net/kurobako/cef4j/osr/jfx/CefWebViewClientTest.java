package net.kurobako.cef4j.osr.jfx;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CefWebViewClientTest {

    @Test
    void convertsCefWindowsEpochToStableJavaDate() {
        assertThat(CefWebViewClient.completionDate(0)).hasTime(0);
        assertThat(CefWebViewClient.completionDate(11_644_473_600_000_000L)).hasTime(0);
        assertThat(CefWebViewClient.completionDate(11_644_473_601_234_000L)).hasTime(1_234);
    }
}
