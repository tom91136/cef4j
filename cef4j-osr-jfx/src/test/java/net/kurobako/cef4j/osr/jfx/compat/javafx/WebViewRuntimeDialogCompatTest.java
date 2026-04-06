package net.kurobako.cef4j.osr.jfx.compat.javafx;

import static net.kurobako.cef4j.osr.jfx.compat.javafx.FxWebViewRuntimeTestSupport.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.atomic.AtomicReference;
import javafx.concurrent.Worker;
import javafx.scene.web.WebView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(30)
class WebViewRuntimeDialogCompatTest extends WebViewRuntimeCompatTestBase {

    @Test
    void alertHandlerFires() throws Exception {
        WebView view = createAttachedWebView();
        AtomicReference<String> alertText = new AtomicReference<>();

        onFxThread(() -> view.getEngine().setOnAlert(event -> alertText.set(event.getData())));
        onFxThread(
                () -> view.getEngine().loadContent("<html><body><script>alert('compat-alert')</script></body></html>"));

        assertThat(waitUntil(() -> "compat-alert".equals(alertText.get()), 3_000))
                .isTrue();
    }

    @Test
    void confirmHandlerReceivesMessageAndReturnsResult() throws Exception {
        WebView view = createAttachedWebView();
        AtomicReference<String> receivedMessage = new AtomicReference<>();

        onFxThread(() -> view.getEngine().setConfirmHandler(message -> {
            receivedMessage.set(message);
            return true;
        }));
        onFxThread(
                () -> view.getEngine()
                        .loadContent(
                                "<html><body><script>document.title = confirm('compat-confirm') ? 'yes' : 'no';</script></body></html>"));

        assertThat(waitForWorkerState(view.getEngine(), Worker.State.SUCCEEDED, 3_000))
                .isTrue();
        assertThat(waitUntil(() -> "compat-confirm".equals(receivedMessage.get()), 3_000))
                .isTrue();
        assertThat(onFxThread(() -> view.getEngine().getTitle())).isEqualTo("yes");
    }

    @Test
    void confirmHandlerDenialReturnsCorrectResult() throws Exception {
        WebView view = createAttachedWebView();

        onFxThread(() -> view.getEngine().setConfirmHandler(message -> false));
        onFxThread(
                () -> view.getEngine()
                        .loadContent(
                                "<html><body><script>document.title = confirm('deny-me') ? 'yes' : 'no';</script></body></html>"));

        assertThat(waitForWorkerState(view.getEngine(), Worker.State.SUCCEEDED, 3_000))
                .isTrue();
        assertThat(waitUntilOnFx(() -> "no".equals(view.getEngine().getTitle()), 3_000))
                .isTrue();
    }

    @Test
    void promptHandlerReceivesMessageAndDefaultValue() throws Exception {
        WebView view = createAttachedWebView();
        AtomicReference<String> receivedMessage = new AtomicReference<>();
        AtomicReference<String> receivedDefault = new AtomicReference<>();

        onFxThread(() -> view.getEngine().setPromptHandler(promptData -> {
            receivedMessage.set(promptData.getMessage());
            receivedDefault.set(promptData.getDefaultValue());
            return "user-input";
        }));
        onFxThread(
                () -> view.getEngine()
                        .loadContent(
                                "<html><body><script>document.title = prompt('compat-prompt', 'default-val');</script></body></html>"));

        assertThat(waitForWorkerState(view.getEngine(), Worker.State.SUCCEEDED, 3_000))
                .isTrue();
        assertThat(waitUntil(() -> "compat-prompt".equals(receivedMessage.get()), 3_000))
                .isTrue();
        assertThat(receivedDefault.get()).isEqualTo("default-val");
        assertThat(onFxThread(() -> view.getEngine().getTitle())).isEqualTo("user-input");
    }

    @Test
    void promptHandlerReturningNullActsAsCancel() throws Exception {
        WebView view = createAttachedWebView();

        onFxThread(() -> view.getEngine().setPromptHandler(promptData -> null));
        onFxThread(
                () -> view.getEngine()
                        .loadContent(
                                "<html><body><script>document.title = prompt('cancel-me') === null ? 'cancelled' : 'not-cancelled';</script></body></html>"));

        assertThat(waitForWorkerState(view.getEngine(), Worker.State.SUCCEEDED, 3_000))
                .isTrue();
        assertThat(waitUntilOnFx(() -> "cancelled".equals(view.getEngine().getTitle()), 3_000))
                .isTrue();
    }
}
