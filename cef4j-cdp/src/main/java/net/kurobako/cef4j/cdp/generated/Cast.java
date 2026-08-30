// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalLong;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.CdpObject;
import net.kurobako.cef4j.cdp.CdpSubscription;
import net.kurobako.cef4j.cdp.CdpValue;

/**
 * A domain for interacting with Cast, Presentation API, and Remote Playback API functionalities.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Cast.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
public final class Cast {
    private Cast() {}
    /**
     */
    public static final class Sink extends CdpObject {
        public Sink() {}
        private Sink(Map<String, Object> values) { super(values); }
        public static Sink fromMap(Map<String, Object> values) {
            return new Sink(values);
        }
        /**
         * Returns the name field.
         * @return the protocol field value
         */
        public String name() {
            return (String) require("name");
        }
        /**
         * Returns the id field.
         * @return the protocol field value
         */
        public String id() {
            return (String) require("id");
        }
        /**
         * Text describing the current session. Present only if there is an active session on the sink.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> session() {
            return Optional.ofNullable((String) raw("session"));
        }
        /**
         * Sets the name field.
         * @param name field value
         * @return this model
         */
        public Sink name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Sets the id field.
         * @param id field value
         * @return this model
         */
        public Sink id(String id) {
            set("id", id);
            return this;
        }
        /**
         * Text describing the current session. Present only if there is an active session on the sink.
         * @param session field value; empty omits the value
         * @return this model
         */
        public Sink session(Optional<String> session) {
            set("session", session.orElse(null));
            return this;
        }
        /**
         * Text describing the current session. Present only if there is an active session on the sink.
         * @param session field value; null removes the value
         * @return this model
         */
        public Sink session(String session) {
            set("session", session);
            return this;
        }
    }
    /**
     * Starts observing for sinks that can be used for tab mirroring, and if set, sinks compatible with |presentationUrl| as well. When sinks are found, a |sinksUpdated| event is fired. Also starts observing for issue messages. When an issue is added or removed, an |issueUpdated| event is fired.
     */
    public static final class EnableRequest extends CdpObject {
        public EnableRequest() {}
        public static EnableRequest fromMap(Map<String, Object> values) {
            EnableRequest instance_ = new EnableRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the presentationUrl field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> presentationUrl() {
            return Optional.ofNullable((String) raw("presentationUrl"));
        }
        /**
         * Sets the presentationUrl field.
         * @param presentationUrl field value; empty omits the value
         * @return this model
         */
        public EnableRequest presentationUrl(Optional<String> presentationUrl) {
            set("presentationUrl", presentationUrl.orElse(null));
            return this;
        }
        /**
         * Sets the presentationUrl field.
         * @param presentationUrl field value; null removes the value
         * @return this model
         */
        public EnableRequest presentationUrl(String presentationUrl) {
            set("presentationUrl", presentationUrl);
            return this;
        }
    }
    /**
     * Sets a sink to be used when the web page requests the browser to choose a sink via Presentation API, Remote Playback API, or Cast SDK.
     */
    public static final class SetSinkToUseRequest extends CdpObject {
        public SetSinkToUseRequest() {}
        /**
         * Sets a sink to be used when the web page requests the browser to choose a sink via Presentation API, Remote Playback API, or Cast SDK.
         * @param sinkName protocol value
         */
        public SetSinkToUseRequest(String sinkName) {
            set("sinkName", sinkName);
        }
        public static SetSinkToUseRequest fromMap(Map<String, Object> values) {
            SetSinkToUseRequest instance_ = new SetSinkToUseRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the sinkName field.
         * @return the protocol field value
         */
        public String sinkName() {
            return (String) require("sinkName");
        }
        /**
         * Sets the sinkName field.
         * @param sinkName field value
         * @return this model
         */
        public SetSinkToUseRequest sinkName(String sinkName) {
            set("sinkName", sinkName);
            return this;
        }
    }
    /**
     * Starts mirroring the desktop to the sink.
     */
    public static final class StartDesktopMirroringRequest extends CdpObject {
        public StartDesktopMirroringRequest() {}
        /**
         * Starts mirroring the desktop to the sink.
         * @param sinkName protocol value
         */
        public StartDesktopMirroringRequest(String sinkName) {
            set("sinkName", sinkName);
        }
        public static StartDesktopMirroringRequest fromMap(Map<String, Object> values) {
            StartDesktopMirroringRequest instance_ = new StartDesktopMirroringRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the sinkName field.
         * @return the protocol field value
         */
        public String sinkName() {
            return (String) require("sinkName");
        }
        /**
         * Sets the sinkName field.
         * @param sinkName field value
         * @return this model
         */
        public StartDesktopMirroringRequest sinkName(String sinkName) {
            set("sinkName", sinkName);
            return this;
        }
    }
    /**
     * Starts mirroring the tab to the sink.
     */
    public static final class StartTabMirroringRequest extends CdpObject {
        public StartTabMirroringRequest() {}
        /**
         * Starts mirroring the tab to the sink.
         * @param sinkName protocol value
         */
        public StartTabMirroringRequest(String sinkName) {
            set("sinkName", sinkName);
        }
        public static StartTabMirroringRequest fromMap(Map<String, Object> values) {
            StartTabMirroringRequest instance_ = new StartTabMirroringRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the sinkName field.
         * @return the protocol field value
         */
        public String sinkName() {
            return (String) require("sinkName");
        }
        /**
         * Sets the sinkName field.
         * @param sinkName field value
         * @return this model
         */
        public StartTabMirroringRequest sinkName(String sinkName) {
            set("sinkName", sinkName);
            return this;
        }
    }
    /**
     * Stops the active Cast session on the sink.
     */
    public static final class StopCastingRequest extends CdpObject {
        public StopCastingRequest() {}
        /**
         * Stops the active Cast session on the sink.
         * @param sinkName protocol value
         */
        public StopCastingRequest(String sinkName) {
            set("sinkName", sinkName);
        }
        public static StopCastingRequest fromMap(Map<String, Object> values) {
            StopCastingRequest instance_ = new StopCastingRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the sinkName field.
         * @return the protocol field value
         */
        public String sinkName() {
            return (String) require("sinkName");
        }
        /**
         * Sets the sinkName field.
         * @param sinkName field value
         * @return this model
         */
        public StopCastingRequest sinkName(String sinkName) {
            set("sinkName", sinkName);
            return this;
        }
    }
    /**
     * This is fired whenever the list of available sinks changes. A sink is a device or a software surface that you can cast to.
     */
    public static final class SinksUpdatedEvent extends CdpObject {
        public SinksUpdatedEvent() {}
        private SinksUpdatedEvent(Map<String, Object> values) { super(values); }
        public static SinksUpdatedEvent fromMap(Map<String, Object> values) {
            return new SinksUpdatedEvent(values);
        }
        /**
         * Returns the sinks field.
         * @return the protocol field value
         */
        public java.util.List<Cast.Sink> sinks() {
            return CdpObject.requireList(require("sinks"), element0 -> java.util.Objects.requireNonNull(Cast.Sink.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * Sets the sinks field.
         * @param sinks field value
         * @return this model
         */
        public SinksUpdatedEvent sinks(java.util.List<Cast.Sink> sinks) {
            set("sinks", sinks);
            return this;
        }
    }
    /**
     * This is fired whenever the outstanding issue/error message changes. |issueMessage| is empty if there is no issue.
     */
    public static final class IssueUpdatedEvent extends CdpObject {
        public IssueUpdatedEvent() {}
        private IssueUpdatedEvent(Map<String, Object> values) { super(values); }
        public static IssueUpdatedEvent fromMap(Map<String, Object> values) {
            return new IssueUpdatedEvent(values);
        }
        /**
         * Returns the issueMessage field.
         * @return the protocol field value
         */
        public String issueMessage() {
            return (String) require("issueMessage");
        }
        /**
         * Sets the issueMessage field.
         * @param issueMessage field value
         * @return this model
         */
        public IssueUpdatedEvent issueMessage(String issueMessage) {
            set("issueMessage", issueMessage);
            return this;
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Starts observing for sinks that can be used for tab mirroring, and if set, sinks compatible with |presentationUrl| as well. When sinks are found, a |sinksUpdated| event is fired. Also starts observing for issue messages. When an issue is added or removed, an |issueUpdated| event is fired.
         * @param presentationUrl protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable(Optional<String> presentationUrl) {
            Map<String, Object> params = new LinkedHashMap<>();
            presentationUrl.ifPresent(value_ -> params.put("presentationUrl", CdpObject.json(value_)));
            return client.call("Cast.enable", params, result_ -> null);
        }
        /**
         * Starts observing for sinks that can be used for tab mirroring, and if set, sinks compatible with |presentationUrl| as well. When sinks are found, a |sinksUpdated| event is fired. Also starts observing for issue messages. When an issue is added or removed, an |issueUpdated| event is fired.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return enable(Optional.empty());
        }
        /**
         * Starts observing for sinks that can be used for tab mirroring, and if set, sinks compatible with |presentationUrl| as well. When sinks are found, a |sinksUpdated| event is fired. Also starts observing for issue messages. When an issue is added or removed, an |issueUpdated| event is fired.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable(EnableRequest request) {
            return client.call("Cast.enable", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Stops observing for sinks and issues.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return client.call("Cast.disable", null, result_ -> null);
        }
        /**
         * Sets a sink to be used when the web page requests the browser to choose a sink via Presentation API, Remote Playback API, or Cast SDK.
         * @param sinkName protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSinkToUse(String sinkName) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("sinkName", CdpObject.json(sinkName));
            return client.call("Cast.setSinkToUse", params, result_ -> null);
        }
        /**
         * Sets a sink to be used when the web page requests the browser to choose a sink via Presentation API, Remote Playback API, or Cast SDK.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSinkToUse(SetSinkToUseRequest request) {
            return client.call("Cast.setSinkToUse", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Starts mirroring the desktop to the sink.
         * @param sinkName protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> startDesktopMirroring(String sinkName) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("sinkName", CdpObject.json(sinkName));
            return client.call("Cast.startDesktopMirroring", params, result_ -> null);
        }
        /**
         * Starts mirroring the desktop to the sink.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> startDesktopMirroring(StartDesktopMirroringRequest request) {
            return client.call("Cast.startDesktopMirroring", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Starts mirroring the tab to the sink.
         * @param sinkName protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> startTabMirroring(String sinkName) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("sinkName", CdpObject.json(sinkName));
            return client.call("Cast.startTabMirroring", params, result_ -> null);
        }
        /**
         * Starts mirroring the tab to the sink.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> startTabMirroring(StartTabMirroringRequest request) {
            return client.call("Cast.startTabMirroring", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Stops the active Cast session on the sink.
         * @param sinkName protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> stopCasting(String sinkName) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("sinkName", CdpObject.json(sinkName));
            return client.call("Cast.stopCasting", params, result_ -> null);
        }
        /**
         * Stops the active Cast session on the sink.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> stopCasting(StopCastingRequest request) {
            return client.call("Cast.stopCasting", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * This is fired whenever the list of available sinks changes. A sink is a device or a software surface that you can cast to.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onSinksUpdated(Consumer<SinksUpdatedEvent> handler) {
            return client.on("Cast.sinksUpdated", SinksUpdatedEvent::fromMap, handler);
        }
        /**
         * This is fired whenever the outstanding issue/error message changes. |issueMessage| is empty if there is no issue.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onIssueUpdated(Consumer<IssueUpdatedEvent> handler) {
            return client.on("Cast.issueUpdated", IssueUpdatedEvent::fromMap, handler);
        }
    }
}
