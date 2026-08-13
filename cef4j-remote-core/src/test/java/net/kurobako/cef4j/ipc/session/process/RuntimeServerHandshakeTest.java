package net.kurobako.cef4j.ipc.session.process;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;

class RuntimeServerHandshakeTest {
    @Test
    void parsesVersionedRemoteCefApiHandshake() {
        RuntimeServerHandshake handshake = RuntimeServerHandshake.parse(
                "CEF4J_RUNTIME_SERVER protocol=1 api=remote-cef cef-api=14600 transport=uds frame=inline "
                        + "endpoint=unix:///tmp/cef4j.sock capabilities=remote-cef-api,devtools,osr,input");

        assertThat(handshake.protocolVersion()).isEqualTo(1);
        assertThat(handshake.api()).isEqualTo("remote-cef");
        assertThat(handshake.cefApiVersion()).isEqualTo(14600);
        assertThat(handshake.transport()).isEqualTo("uds");
        assertThat(handshake.frameTransport()).isEqualTo("inline");
        assertThat(handshake.endpoint()).isEqualTo("unix:///tmp/cef4j.sock");
        assertThat(handshake.capabilities()).containsExactly("remote-cef-api", "devtools", "osr", "input");
    }

    @Test
    void rejectsUnsupportedProtocolAndApi() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> RuntimeServerHandshake.parse(
                        "CEF4J_RUNTIME_SERVER protocol=2 api=remote-cef cef-api=14600 transport=zmq frame=mmap "
                                + "endpoint=tcp://127.0.0.1:1 capabilities=remote-cef-api"));
        assertThatIllegalArgumentException()
                .isThrownBy(() -> RuntimeServerHandshake.parse(
                        "CEF4J_RUNTIME_SERVER protocol=1 api=webdriver cef-api=14600 transport=zmq frame=mmap "
                                + "endpoint=tcp://127.0.0.1:1 capabilities=remote-cef-api"));
        assertThatIllegalArgumentException()
                .isThrownBy(() -> RuntimeServerHandshake.parse(
                        "CEF4J_RUNTIME_SERVER protocol=1 api=remote-cef cef-api=0 transport=zmq frame=mmap "
                                + "endpoint=tcp://127.0.0.1:1 capabilities=remote-cef-api"));
        assertThatIllegalArgumentException()
                .isThrownBy(() -> RuntimeServerHandshake.parse(
                        "CEF4J_RUNTIME_SERVER protocol=1 api=remote-cef cef-api=14600 transport=zmq frame=mmap "
                                + "endpoint=tcp://127.0.0.1:1 capabilities=devtools,osr,input"));
    }
}
