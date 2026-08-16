// GENERATED - do not edit. Run scripts/update-cdp-schema.sh.
package net.kurobako.cef4j.cdp.generated;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.CdpObject;
import net.kurobako.cef4j.cdp.CdpSubscription;

/**
 * A domain for interacting with Cast, Presentation API, and Remote Playback API functionalities.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/146.0.7680.165/third_party/blink/public/devtools_protocol/domains/Cast.pdl">Pinned protocol source</a>
 */
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class Cast {
    private Cast() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     */
    public static final class Sink extends CdpObject {
        private Sink(Map<String, Object> values) { super(values); }
        @Nullable public static Sink fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new Sink(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the name field.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * Returns the id field.
         * @return the protocol field value
         */
        @Nullable public String id() {
            return (String) value("id");
        }
        /**
         * Text describing the current session. Present only if there is an active session on the sink.
         * @return the protocol field value
         */
        @Nullable public String session() {
            return (String) value("session");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the name field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * Sets the id field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder id(@Nullable String value) {
                if (value == null) values.remove("id");
                else values.put("id", jsonValue(value));
                return this;
            }
            /**
             * Text describing the current session. Present only if there is an active session on the sink.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder session(@Nullable String value) {
                if (value == null) values.remove("session");
                else values.put("session", jsonValue(value));
                return this;
            }
            public Sink build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("id")) throw new IllegalStateException("Missing required CDP field: id");
                return new Sink(values);
            }
        }
    }
    /**
     * Starts observing for sinks that can be used for tab mirroring, and if set, sinks compatible with |presentationUrl| as well. When sinks are found, a |sinksUpdated| event is fired. Also starts observing for issue messages. When an issue is added or removed, an |issueUpdated| event is fired.
     */
    public static final class EnableParams extends CdpObject {
        private EnableParams(Map<String, Object> values) { super(values); }
        @Nullable public static EnableParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EnableParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the presentationUrl field.
         * @return the protocol field value
         */
        @Nullable public String presentationUrl() {
            return (String) value("presentationUrl");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the presentationUrl field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder presentationUrl(@Nullable String value) {
                if (value == null) values.remove("presentationUrl");
                else values.put("presentationUrl", jsonValue(value));
                return this;
            }
            public EnableParams build() {
                return new EnableParams(values);
            }
        }
    }
    /**
     * Starts observing for sinks that can be used for tab mirroring, and if set, sinks compatible with |presentationUrl| as well. When sinks are found, a |sinksUpdated| event is fired. Also starts observing for issue messages. When an issue is added or removed, an |issueUpdated| event is fired.
     */
    public static final class EnableResult extends CdpObject {
        private EnableResult(Map<String, Object> values) { super(values); }
        @Nullable public static EnableResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EnableResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public EnableResult build() {
                return new EnableResult(values);
            }
        }
    }
    /**
     * Stops observing for sinks and issues.
     */
    public static final class DisableParams extends CdpObject {
        private DisableParams(Map<String, Object> values) { super(values); }
        @Nullable public static DisableParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DisableParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public DisableParams build() {
                return new DisableParams(values);
            }
        }
    }
    /**
     * Stops observing for sinks and issues.
     */
    public static final class DisableResult extends CdpObject {
        private DisableResult(Map<String, Object> values) { super(values); }
        @Nullable public static DisableResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DisableResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public DisableResult build() {
                return new DisableResult(values);
            }
        }
    }
    /**
     * Sets a sink to be used when the web page requests the browser to choose a sink via Presentation API, Remote Playback API, or Cast SDK.
     */
    public static final class SetSinkToUseParams extends CdpObject {
        private SetSinkToUseParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetSinkToUseParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetSinkToUseParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the sinkName field.
         * @return the protocol field value
         */
        @Nullable public String sinkName() {
            return (String) value("sinkName");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the sinkName field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sinkName(@Nullable String value) {
                if (value == null) values.remove("sinkName");
                else values.put("sinkName", jsonValue(value));
                return this;
            }
            public SetSinkToUseParams build() {
                if (!values.containsKey("sinkName")) throw new IllegalStateException("Missing required CDP field: sinkName");
                return new SetSinkToUseParams(values);
            }
        }
    }
    /**
     * Sets a sink to be used when the web page requests the browser to choose a sink via Presentation API, Remote Playback API, or Cast SDK.
     */
    public static final class SetSinkToUseResult extends CdpObject {
        private SetSinkToUseResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetSinkToUseResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetSinkToUseResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetSinkToUseResult build() {
                return new SetSinkToUseResult(values);
            }
        }
    }
    /**
     * Starts mirroring the desktop to the sink.
     */
    public static final class StartDesktopMirroringParams extends CdpObject {
        private StartDesktopMirroringParams(Map<String, Object> values) { super(values); }
        @Nullable public static StartDesktopMirroringParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StartDesktopMirroringParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the sinkName field.
         * @return the protocol field value
         */
        @Nullable public String sinkName() {
            return (String) value("sinkName");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the sinkName field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sinkName(@Nullable String value) {
                if (value == null) values.remove("sinkName");
                else values.put("sinkName", jsonValue(value));
                return this;
            }
            public StartDesktopMirroringParams build() {
                if (!values.containsKey("sinkName")) throw new IllegalStateException("Missing required CDP field: sinkName");
                return new StartDesktopMirroringParams(values);
            }
        }
    }
    /**
     * Starts mirroring the desktop to the sink.
     */
    public static final class StartDesktopMirroringResult extends CdpObject {
        private StartDesktopMirroringResult(Map<String, Object> values) { super(values); }
        @Nullable public static StartDesktopMirroringResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StartDesktopMirroringResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public StartDesktopMirroringResult build() {
                return new StartDesktopMirroringResult(values);
            }
        }
    }
    /**
     * Starts mirroring the tab to the sink.
     */
    public static final class StartTabMirroringParams extends CdpObject {
        private StartTabMirroringParams(Map<String, Object> values) { super(values); }
        @Nullable public static StartTabMirroringParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StartTabMirroringParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the sinkName field.
         * @return the protocol field value
         */
        @Nullable public String sinkName() {
            return (String) value("sinkName");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the sinkName field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sinkName(@Nullable String value) {
                if (value == null) values.remove("sinkName");
                else values.put("sinkName", jsonValue(value));
                return this;
            }
            public StartTabMirroringParams build() {
                if (!values.containsKey("sinkName")) throw new IllegalStateException("Missing required CDP field: sinkName");
                return new StartTabMirroringParams(values);
            }
        }
    }
    /**
     * Starts mirroring the tab to the sink.
     */
    public static final class StartTabMirroringResult extends CdpObject {
        private StartTabMirroringResult(Map<String, Object> values) { super(values); }
        @Nullable public static StartTabMirroringResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StartTabMirroringResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public StartTabMirroringResult build() {
                return new StartTabMirroringResult(values);
            }
        }
    }
    /**
     * Stops the active Cast session on the sink.
     */
    public static final class StopCastingParams extends CdpObject {
        private StopCastingParams(Map<String, Object> values) { super(values); }
        @Nullable public static StopCastingParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StopCastingParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the sinkName field.
         * @return the protocol field value
         */
        @Nullable public String sinkName() {
            return (String) value("sinkName");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the sinkName field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sinkName(@Nullable String value) {
                if (value == null) values.remove("sinkName");
                else values.put("sinkName", jsonValue(value));
                return this;
            }
            public StopCastingParams build() {
                if (!values.containsKey("sinkName")) throw new IllegalStateException("Missing required CDP field: sinkName");
                return new StopCastingParams(values);
            }
        }
    }
    /**
     * Stops the active Cast session on the sink.
     */
    public static final class StopCastingResult extends CdpObject {
        private StopCastingResult(Map<String, Object> values) { super(values); }
        @Nullable public static StopCastingResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StopCastingResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public StopCastingResult build() {
                return new StopCastingResult(values);
            }
        }
    }
    /**
     * This is fired whenever the list of available sinks changes. A sink is a device or a software surface that you can cast to.
     */
    public static final class SinksUpdatedEvent extends CdpObject {
        private SinksUpdatedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static SinksUpdatedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SinksUpdatedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the sinks field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Cast.Sink> sinks() {
            return list(value("sinks"), element0 -> Cast.Sink.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the sinks field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sinks(@Nullable java.util.List<Cast.Sink> value) {
                if (value == null) values.remove("sinks");
                else values.put("sinks", jsonValue(value));
                return this;
            }
            public SinksUpdatedEvent build() {
                if (!values.containsKey("sinks")) throw new IllegalStateException("Missing required CDP field: sinks");
                return new SinksUpdatedEvent(values);
            }
        }
    }
    /**
     * This is fired whenever the outstanding issue/error message changes. |issueMessage| is empty if there is no issue.
     */
    public static final class IssueUpdatedEvent extends CdpObject {
        private IssueUpdatedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static IssueUpdatedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new IssueUpdatedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the issueMessage field.
         * @return the protocol field value
         */
        @Nullable public String issueMessage() {
            return (String) value("issueMessage");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the issueMessage field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder issueMessage(@Nullable String value) {
                if (value == null) values.remove("issueMessage");
                else values.put("issueMessage", jsonValue(value));
                return this;
            }
            public IssueUpdatedEvent build() {
                if (!values.containsKey("issueMessage")) throw new IllegalStateException("Missing required CDP field: issueMessage");
                return new IssueUpdatedEvent(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Starts observing for sinks that can be used for tab mirroring, and if set, sinks compatible with |presentationUrl| as well. When sinks are found, a |sinksUpdated| event is fired. Also starts observing for issue messages. When an issue is added or removed, an |issueUpdated| event is fired.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<EnableResult> enable(EnableParams params) {
            return client.call("Cast.enable", params, EnableResult::fromMap);
        }
        /**
         * Stops observing for sinks and issues.
         * @return a stage completing with the command result
         */
        public CompletionStage<DisableResult> disable() {
            return client.call("Cast.disable", null, DisableResult::fromMap);
        }
        /**
         * Sets a sink to be used when the web page requests the browser to choose a sink via Presentation API, Remote Playback API, or Cast SDK.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetSinkToUseResult> setSinkToUse(SetSinkToUseParams params) {
            return client.call("Cast.setSinkToUse", params, SetSinkToUseResult::fromMap);
        }
        /**
         * Starts mirroring the desktop to the sink.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<StartDesktopMirroringResult> startDesktopMirroring(StartDesktopMirroringParams params) {
            return client.call("Cast.startDesktopMirroring", params, StartDesktopMirroringResult::fromMap);
        }
        /**
         * Starts mirroring the tab to the sink.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<StartTabMirroringResult> startTabMirroring(StartTabMirroringParams params) {
            return client.call("Cast.startTabMirroring", params, StartTabMirroringResult::fromMap);
        }
        /**
         * Stops the active Cast session on the sink.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<StopCastingResult> stopCasting(StopCastingParams params) {
            return client.call("Cast.stopCasting", params, StopCastingResult::fromMap);
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
