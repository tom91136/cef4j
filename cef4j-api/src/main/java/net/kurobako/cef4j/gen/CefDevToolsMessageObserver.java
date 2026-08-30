// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import java.nio.ByteBuffer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Callback interface for
 * {@link net.kurobako.cef4j.gen.CefBrowserHost#addDevToolsMessageObserver(CefDevToolsMessageObserver)}. The methods of
 * this class will be called on the browser process UI thread.
 *
 * <p>Definition generated from cef_devtools_message_observer_capi.h
 *
 * <pre>typedef struct _cef_dev_tools_message_observer_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_dev_tools_message_observer_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__devtools__message__observer_8h.html">cef_devtools_message_observer.h:45</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
@NullableBoundary("generated external ABI")
public interface CefDevToolsMessageObserver extends CefClientHandler {

    /**
     * Method that will be called on receipt of a DevTools protocol message. {@code browser} is the originating browser
     * instance. {@code message} is a UTF8-encoded JSON dictionary representing either a method result or an event.
     * {@code message} is only valid for the scope of this callback and should be copied if necessary. Return
     * {@code true} if the message was handled or {@code false} if the message should be further processed and passed to
     * the OnDevToolsMethodResult or OnDevToolsEvent methods as appropriate.
     *
     * <p>Method result dictionaries include an "id" (int) value that identifies the orginating method call sent from
     * {@link net.kurobako.cef4j.gen.CefBrowserHost#sendDevToolsMessage(java.nio.ByteBuffer)}, and optionally either a
     * "result" (dictionary) or "error" (dictionary) value. The "error" dictionary will contain "code" (int) and
     * "message" (string) values. Event dictionaries include a "method" (string) value and optionally a "params"
     * (dictionary) value. See the DevTools protocol documentation at <a
     * href="https://chromedevtools.github.io/devtools-protocol/">https://chromedevtools.github.io/devtools-protocol/</a>
     * for details of supported method calls and the expected "result" or "params" dictionary contents. JSON
     * dictionaries can be parsed using the CefParseJSON function if desired, however be aware of performance
     * considerations when parsing large messages (some of which may exceed 1MB in size).
     *
     * <p><b>The C API {@code void*} buffer parameter has been converted to {@link java.nio.ByteBuffer}; the hidden
     * {@code message_size} parameter is derived from the buffer's capacity.</b>
     *
     * <p>Definition generated from cef_devtools_message_observer_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* on_dev_tools_message)(struct _cef_dev_tools_message_observer_t* self, struct _cef_browser_t* browser, const void* message, size_t message_size);
     * </pre>
     *
     * @param message <b>a direct {@link java.nio.ByteBuffer} whose capacity is the buffer size. This buffer is not
     *     reference-counted; its lifetime is not predictable beyond the scope of this callback. Storing a reference to
     *     it is unsafe unless explicitly permitted by the CEF documentation and may lead to native crashes.</b>
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__devtools__message__observer_8h.html">cef_devtools_message_observer.h:52</a>
     */
    default boolean onDevToolsMessage(@Nullable CefBrowser browser, @Nonnull ByteBuffer message) {
        return false;
    }

    /**
     * Method that will be called after attempted execution of a DevTools protocol method. {@code browser} is the
     * originating browser instance. {@code message_id} is the "id" value that identifies the originating method call
     * message. If the method succeeded {@code success} will be {@code true} and {@code result} will be the UTF8-encoded
     * JSON "result" dictionary value (which may be empty). If the method failed {@code success} will be {@code false}
     * and {@code result} will be the UTF8-encoded JSON "error" dictionary value. {@code result} is only valid for the
     * scope of this callback and should be copied if necessary. See the OnDevToolsMessage documentation for additional
     * details on {@code result} contents.
     *
     * <p><b>The C API {@code void*} buffer parameter has been converted to {@link java.nio.ByteBuffer}; the hidden
     * {@code result_size} parameter is derived from the buffer's capacity.</b>
     *
     * <p>Definition generated from cef_devtools_message_observer_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_dev_tools_method_result)(struct _cef_dev_tools_message_observer_t* self, struct _cef_browser_t* browser, int message_id, int success, const void* result, size_t result_size);
     * </pre>
     *
     * @param result may be null, <b>a direct {@link java.nio.ByteBuffer} whose capacity is the buffer size. This buffer
     *     is not reference-counted; its lifetime is not predictable beyond the scope of this callback. Storing a
     *     reference to it is unsafe unless explicitly permitted by the CEF documentation and may lead to native
     *     crashes.</b>
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__devtools__message__observer_8h.html">cef_devtools_message_observer.h:81</a>
     */
    default void onDevToolsMethodResult(
            @Nullable CefBrowser browser, int messageId, boolean success, @Nullable ByteBuffer result) {}

    /**
     * Method that will be called on receipt of a DevTools protocol event. {@code browser} is the originating browser
     * instance. {@code method} is the "method" value. {@code params} is the UTF8-encoded JSON "params" dictionary value
     * (which may be empty). {@code params} is only valid for the scope of this callback and should be copied if
     * necessary. See the OnDevToolsMessage documentation for additional details on {@code params} contents.
     *
     * <p><b>The C API {@code void*} buffer parameter has been converted to {@link java.nio.ByteBuffer}; the hidden
     * {@code params_size} parameter is derived from the buffer's capacity.</b>
     *
     * <p>Definition generated from cef_devtools_message_observer_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_dev_tools_event)(struct _cef_dev_tools_message_observer_t* self, struct _cef_browser_t* browser, const cef_string_t* method, const void* params, size_t params_size);
     * </pre>
     *
     * @param params may be null, <b>a direct {@link java.nio.ByteBuffer} whose capacity is the buffer size. This buffer
     *     is not reference-counted; its lifetime is not predictable beyond the scope of this callback. Storing a
     *     reference to it is unsafe unless explicitly permitted by the CEF documentation and may lead to native
     *     crashes.</b>
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__devtools__message__observer_8h.html">cef_devtools_message_observer.h:100</a>
     */
    default void onDevToolsEvent(@Nullable CefBrowser browser, @Nullable String method, @Nullable ByteBuffer params) {}

    /**
     * Method that will be called when the DevTools agent has attached. {@code browser} is the originating browser
     * instance. This will generally occur in response to the first message sent while the agent is detached.
     *
     * <p>Definition generated from cef_devtools_message_observer_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_dev_tools_agent_attached)(struct _cef_dev_tools_message_observer_t* self, struct _cef_browser_t* browser);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__devtools__message__observer_8h.html">cef_devtools_message_observer.h:114</a>
     */
    default void onDevToolsAgentAttached(@Nullable CefBrowser browser) {}

    /**
     * Method that will be called when the DevTools agent has detached. {@code browser} is the originating browser
     * instance. Any method results that were pending before the agent became detached will not be delivered, and any
     * active event subscriptions will be canceled.
     *
     * <p>Definition generated from cef_devtools_message_observer_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_dev_tools_agent_detached)(struct _cef_dev_tools_message_observer_t* self, struct _cef_browser_t* browser);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__devtools__message__observer_8h.html">cef_devtools_message_observer.h:122</a>
     */
    default void onDevToolsAgentDetached(@Nullable CefBrowser browser) {}
    /**
     * Composite that fans callbacks out to every registered delegate. {@code void} methods invoke all delegates in
     * order; {@code boolean} methods short-circuit on the first {@code true}; handler-returning {@code Optional}s
     * collect every non-empty delegate and wrap them in the handler's own {@code Delegating} wrapper; other
     * {@code Optional}s pick the first non-empty; any other return type yields the first delegate's value.
     */
    class Delegating implements CefDevToolsMessageObserver {
        private final java.util.List<CefDevToolsMessageObserver> delegates;

        public Delegating(java.util.List<CefDevToolsMessageObserver> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public boolean onDevToolsMessage(@Nullable CefBrowser browser, @Nonnull ByteBuffer message) {
            for (CefDevToolsMessageObserver d : delegates) {
                if (d.onDevToolsMessage(browser, message)) return true;
            }
            if (!delegates.isEmpty()) return false;
            return false;
        }

        @Override
        public void onDevToolsMethodResult(
                @Nullable CefBrowser browser, int messageId, boolean success, @Nullable ByteBuffer result) {
            for (CefDevToolsMessageObserver d : delegates)
                d.onDevToolsMethodResult(browser, messageId, success, result);
        }

        @Override
        public void onDevToolsEvent(
                @Nullable CefBrowser browser, @Nullable String method, @Nullable ByteBuffer params) {
            for (CefDevToolsMessageObserver d : delegates) d.onDevToolsEvent(browser, method, params);
        }

        @Override
        public void onDevToolsAgentAttached(@Nullable CefBrowser browser) {
            for (CefDevToolsMessageObserver d : delegates) d.onDevToolsAgentAttached(browser);
        }

        @Override
        public void onDevToolsAgentDetached(@Nullable CefBrowser browser) {
            for (CefDevToolsMessageObserver d : delegates) d.onDevToolsAgentDetached(browser);
        }
    }
}
