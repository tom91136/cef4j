package net.kurobako.cef4j.remote;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class RemoteApiCompatibilityTest {
    @Test
    void exposesTheCefApiVersionUsedForGeneratedSources() {
        assertThat(RemoteApiCompatibility.cefApiVersion()).isPositive();
        assertThat(RemoteApiCompatibility.cefApiVersion() % 100).isZero();
    }
}
