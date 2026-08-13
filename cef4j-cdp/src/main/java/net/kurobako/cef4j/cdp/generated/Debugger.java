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
 * Debugger domain exposes JavaScript debugging capabilities. It allows setting and removing breakpoints, stepping through execution, exploring stack traces, etc.
 * @see <a href="https://chromium.googlesource.com/v8/v8/+/0e999a528db40a3ef6fa917adf96370a18b87d70/include/js_protocol.pdl">Pinned protocol source</a>
 */
@SuppressWarnings({"JavaLangClash", "UnusedMethod"})
public final class Debugger {
    private Debugger() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * Location in the source code.
     */
    public static final class Location extends CdpObject {
        private Location(Map<String, Object> values) { super(values); }
        @Nullable public static Location fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new Location(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Script identifier as reported in the {@code Debugger.scriptParsed}.
         * @return the protocol field value
         */
        @Nullable public String scriptId() {
            return (String) value("scriptId");
        }
        /**
         * Line number in the script (0-based).
         * @return the protocol field value
         */
        @Nullable public Long lineNumber() {
            return numberAsLong(value("lineNumber"));
        }
        /**
         * Column number in the script (0-based).
         * @return the protocol field value
         */
        @Nullable public Long columnNumber() {
            return numberAsLong(value("columnNumber"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Script identifier as reported in the {@code Debugger.scriptParsed}.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scriptId(@Nullable String value) {
                if (value == null) values.remove("scriptId");
                else values.put("scriptId", jsonValue(value));
                return this;
            }
            /**
             * Line number in the script (0-based).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder lineNumber(@Nullable Long value) {
                if (value == null) values.remove("lineNumber");
                else values.put("lineNumber", jsonValue(value));
                return this;
            }
            /**
             * Column number in the script (0-based).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder columnNumber(@Nullable Long value) {
                if (value == null) values.remove("columnNumber");
                else values.put("columnNumber", jsonValue(value));
                return this;
            }
            public Location build() {
                if (!values.containsKey("scriptId")) throw new IllegalStateException("Missing required CDP field: scriptId");
                if (!values.containsKey("lineNumber")) throw new IllegalStateException("Missing required CDP field: lineNumber");
                return new Location(values);
            }
        }
    }
    /**
     * Location in the source code.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ScriptPosition extends CdpObject {
        private ScriptPosition(Map<String, Object> values) { super(values); }
        @Nullable public static ScriptPosition fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ScriptPosition(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the lineNumber field.
         * @return the protocol field value
         */
        @Nullable public Long lineNumber() {
            return numberAsLong(value("lineNumber"));
        }
        /**
         * Returns the columnNumber field.
         * @return the protocol field value
         */
        @Nullable public Long columnNumber() {
            return numberAsLong(value("columnNumber"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the lineNumber field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder lineNumber(@Nullable Long value) {
                if (value == null) values.remove("lineNumber");
                else values.put("lineNumber", jsonValue(value));
                return this;
            }
            /**
             * Sets the columnNumber field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder columnNumber(@Nullable Long value) {
                if (value == null) values.remove("columnNumber");
                else values.put("columnNumber", jsonValue(value));
                return this;
            }
            public ScriptPosition build() {
                if (!values.containsKey("lineNumber")) throw new IllegalStateException("Missing required CDP field: lineNumber");
                if (!values.containsKey("columnNumber")) throw new IllegalStateException("Missing required CDP field: columnNumber");
                return new ScriptPosition(values);
            }
        }
    }
    /**
     * Location range within one script.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class LocationRange extends CdpObject {
        private LocationRange(Map<String, Object> values) { super(values); }
        @Nullable public static LocationRange fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new LocationRange(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the scriptId field.
         * @return the protocol field value
         */
        @Nullable public String scriptId() {
            return (String) value("scriptId");
        }
        /**
         * Returns the start field.
         * @return the protocol field value
         */
        @Nullable public Debugger.ScriptPosition start() {
            return Debugger.ScriptPosition.fromMap(objectMap(value("start")));
        }
        /**
         * Returns the end field.
         * @return the protocol field value
         */
        @Nullable public Debugger.ScriptPosition end() {
            return Debugger.ScriptPosition.fromMap(objectMap(value("end")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the scriptId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scriptId(@Nullable String value) {
                if (value == null) values.remove("scriptId");
                else values.put("scriptId", jsonValue(value));
                return this;
            }
            /**
             * Sets the start field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder start(@Nullable Debugger.ScriptPosition value) {
                if (value == null) values.remove("start");
                else values.put("start", jsonValue(value));
                return this;
            }
            /**
             * Sets the end field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder end(@Nullable Debugger.ScriptPosition value) {
                if (value == null) values.remove("end");
                else values.put("end", jsonValue(value));
                return this;
            }
            public LocationRange build() {
                if (!values.containsKey("scriptId")) throw new IllegalStateException("Missing required CDP field: scriptId");
                if (!values.containsKey("start")) throw new IllegalStateException("Missing required CDP field: start");
                if (!values.containsKey("end")) throw new IllegalStateException("Missing required CDP field: end");
                return new LocationRange(values);
            }
        }
    }
    /**
     * JavaScript call frame. Array of call frames form the call stack.
     */
    public static final class CallFrame extends CdpObject {
        private CallFrame(Map<String, Object> values) { super(values); }
        @Nullable public static CallFrame fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CallFrame(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Call frame identifier. This identifier is only valid while the virtual machine is paused.
         * @return the protocol field value
         */
        @Nullable public String callFrameId() {
            return (String) value("callFrameId");
        }
        /**
         * Name of the JavaScript function called on this call frame.
         * @return the protocol field value
         */
        @Nullable public String functionName() {
            return (String) value("functionName");
        }
        /**
         * Location in the source code.
         * @return the protocol field value
         */
        @Nullable public Debugger.Location functionLocation() {
            return Debugger.Location.fromMap(objectMap(value("functionLocation")));
        }
        /**
         * Location in the source code.
         * @return the protocol field value
         */
        @Nullable public Debugger.Location location() {
            return Debugger.Location.fromMap(objectMap(value("location")));
        }
        /**
         * JavaScript script name or url. Deprecated in favor of using the {@code location.scriptId} to resolve the URL via a previously sent {@code Debugger.scriptParsed} event.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * Scope chain for this call frame.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Debugger.Scope> scopeChain() {
            return list(value("scopeChain"), element0 -> Debugger.Scope.fromMap(objectMap(element0)));
        }
        /**
         * {@code this} object for this call frame.
         * @return the protocol field value
         */
        @Nullable public Runtime.RemoteObject thisValue() {
            return Runtime.RemoteObject.fromMap(objectMap(value("this")));
        }
        /**
         * The value being returned, if the function is at return point.
         * @return the protocol field value
         */
        @Nullable public Runtime.RemoteObject returnValue() {
            return Runtime.RemoteObject.fromMap(objectMap(value("returnValue")));
        }
        /**
         * Valid only while the VM is paused and indicates whether this frame can be restarted or not. Note that a {@code true} value here does not guarantee that Debugger#restartFrame with this CallFrameId will be successful, but it is very likely.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean canBeRestarted() {
            return (Boolean) value("canBeRestarted");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Call frame identifier. This identifier is only valid while the virtual machine is paused.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder callFrameId(@Nullable String value) {
                if (value == null) values.remove("callFrameId");
                else values.put("callFrameId", jsonValue(value));
                return this;
            }
            /**
             * Name of the JavaScript function called on this call frame.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder functionName(@Nullable String value) {
                if (value == null) values.remove("functionName");
                else values.put("functionName", jsonValue(value));
                return this;
            }
            /**
             * Location in the source code.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder functionLocation(@Nullable Debugger.Location value) {
                if (value == null) values.remove("functionLocation");
                else values.put("functionLocation", jsonValue(value));
                return this;
            }
            /**
             * Location in the source code.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder location(@Nullable Debugger.Location value) {
                if (value == null) values.remove("location");
                else values.put("location", jsonValue(value));
                return this;
            }
            /**
             * JavaScript script name or url. Deprecated in favor of using the {@code location.scriptId} to resolve the URL via a previously sent {@code Debugger.scriptParsed} event.
             * @param value field value; null removes an optional value
             * @return this builder
             * @deprecated Deprecated by the Chromium DevTools Protocol.
             */
            @Deprecated
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * Scope chain for this call frame.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scopeChain(@Nullable java.util.List<Debugger.Scope> value) {
                if (value == null) values.remove("scopeChain");
                else values.put("scopeChain", jsonValue(value));
                return this;
            }
            /**
             * {@code this} object for this call frame.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder thisValue(@Nullable Runtime.RemoteObject value) {
                if (value == null) values.remove("this");
                else values.put("this", jsonValue(value));
                return this;
            }
            /**
             * The value being returned, if the function is at return point.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder returnValue(@Nullable Runtime.RemoteObject value) {
                if (value == null) values.remove("returnValue");
                else values.put("returnValue", jsonValue(value));
                return this;
            }
            /**
             * Valid only while the VM is paused and indicates whether this frame can be restarted or not. Note that a {@code true} value here does not guarantee that Debugger#restartFrame with this CallFrameId will be successful, but it is very likely.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder canBeRestarted(@Nullable Boolean value) {
                if (value == null) values.remove("canBeRestarted");
                else values.put("canBeRestarted", jsonValue(value));
                return this;
            }
            public CallFrame build() {
                if (!values.containsKey("callFrameId")) throw new IllegalStateException("Missing required CDP field: callFrameId");
                if (!values.containsKey("functionName")) throw new IllegalStateException("Missing required CDP field: functionName");
                if (!values.containsKey("location")) throw new IllegalStateException("Missing required CDP field: location");
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                if (!values.containsKey("scopeChain")) throw new IllegalStateException("Missing required CDP field: scopeChain");
                if (!values.containsKey("this")) throw new IllegalStateException("Missing required CDP field: this");
                return new CallFrame(values);
            }
        }
    }
    /**
     * Scope description.
     */
    public static final class Scope extends CdpObject {
        private Scope(Map<String, Object> values) { super(values); }
        @Nullable public static Scope fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new Scope(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Scope type.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * Scope type.
         */
        public static final class TypeValues {
            private TypeValues() {}
            public static final String GLOBAL = "global";
            public static final String LOCAL = "local";
            public static final String WITH = "with";
            public static final String CLOSURE = "closure";
            public static final String CATCH = "catch";
            public static final String BLOCK = "block";
            public static final String SCRIPT = "script";
            public static final String EVAL = "eval";
            public static final String MODULE = "module";
            public static final String WASM_EXPRESSION_STACK = "wasm-expression-stack";
        }
        /**
         * Object representing the scope. For {@code global} and {@code with} scopes it represents the actual object; for the rest of the scopes, it is artificial transient object enumerating scope variables as its properties.
         * @return the protocol field value
         */
        @Nullable public Runtime.RemoteObject object() {
            return Runtime.RemoteObject.fromMap(objectMap(value("object")));
        }
        /**
         * Returns the name field.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * Location in the source code where scope starts
         * @return the protocol field value
         */
        @Nullable public Debugger.Location startLocation() {
            return Debugger.Location.fromMap(objectMap(value("startLocation")));
        }
        /**
         * Location in the source code where scope ends
         * @return the protocol field value
         */
        @Nullable public Debugger.Location endLocation() {
            return Debugger.Location.fromMap(objectMap(value("endLocation")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Scope type.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            /**
             * Object representing the scope. For {@code global} and {@code with} scopes it represents the actual object; for the rest of the scopes, it is artificial transient object enumerating scope variables as its properties.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder object(@Nullable Runtime.RemoteObject value) {
                if (value == null) values.remove("object");
                else values.put("object", jsonValue(value));
                return this;
            }
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
             * Location in the source code where scope starts
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder startLocation(@Nullable Debugger.Location value) {
                if (value == null) values.remove("startLocation");
                else values.put("startLocation", jsonValue(value));
                return this;
            }
            /**
             * Location in the source code where scope ends
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder endLocation(@Nullable Debugger.Location value) {
                if (value == null) values.remove("endLocation");
                else values.put("endLocation", jsonValue(value));
                return this;
            }
            public Scope build() {
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                if (!values.containsKey("object")) throw new IllegalStateException("Missing required CDP field: object");
                return new Scope(values);
            }
        }
    }
    /**
     * Search match for resource.
     */
    public static final class SearchMatch extends CdpObject {
        private SearchMatch(Map<String, Object> values) { super(values); }
        @Nullable public static SearchMatch fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SearchMatch(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Line number in resource content.
         * @return the protocol field value
         */
        @Nullable public Double lineNumber() {
            return numberAsDouble(value("lineNumber"));
        }
        /**
         * Line with match content.
         * @return the protocol field value
         */
        @Nullable public String lineContent() {
            return (String) value("lineContent");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Line number in resource content.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder lineNumber(@Nullable Double value) {
                if (value == null) values.remove("lineNumber");
                else values.put("lineNumber", jsonValue(value));
                return this;
            }
            /**
             * Line with match content.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder lineContent(@Nullable String value) {
                if (value == null) values.remove("lineContent");
                else values.put("lineContent", jsonValue(value));
                return this;
            }
            public SearchMatch build() {
                if (!values.containsKey("lineNumber")) throw new IllegalStateException("Missing required CDP field: lineNumber");
                if (!values.containsKey("lineContent")) throw new IllegalStateException("Missing required CDP field: lineContent");
                return new SearchMatch(values);
            }
        }
    }
    /**
     */
    public static final class BreakLocation extends CdpObject {
        private BreakLocation(Map<String, Object> values) { super(values); }
        @Nullable public static BreakLocation fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new BreakLocation(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Script identifier as reported in the {@code Debugger.scriptParsed}.
         * @return the protocol field value
         */
        @Nullable public String scriptId() {
            return (String) value("scriptId");
        }
        /**
         * Line number in the script (0-based).
         * @return the protocol field value
         */
        @Nullable public Long lineNumber() {
            return numberAsLong(value("lineNumber"));
        }
        /**
         * Column number in the script (0-based).
         * @return the protocol field value
         */
        @Nullable public Long columnNumber() {
            return numberAsLong(value("columnNumber"));
        }
        /**
         * Returns the type field.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * Wire values for TypeValues.
         */
        public static final class TypeValues {
            private TypeValues() {}
            public static final String DEBUGGERSTATEMENT = "debuggerStatement";
            public static final String CALL = "call";
            public static final String RETURN = "return";
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Script identifier as reported in the {@code Debugger.scriptParsed}.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scriptId(@Nullable String value) {
                if (value == null) values.remove("scriptId");
                else values.put("scriptId", jsonValue(value));
                return this;
            }
            /**
             * Line number in the script (0-based).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder lineNumber(@Nullable Long value) {
                if (value == null) values.remove("lineNumber");
                else values.put("lineNumber", jsonValue(value));
                return this;
            }
            /**
             * Column number in the script (0-based).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder columnNumber(@Nullable Long value) {
                if (value == null) values.remove("columnNumber");
                else values.put("columnNumber", jsonValue(value));
                return this;
            }
            /**
             * Sets the type field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            public BreakLocation build() {
                if (!values.containsKey("scriptId")) throw new IllegalStateException("Missing required CDP field: scriptId");
                if (!values.containsKey("lineNumber")) throw new IllegalStateException("Missing required CDP field: lineNumber");
                return new BreakLocation(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class WasmDisassemblyChunk extends CdpObject {
        private WasmDisassemblyChunk(Map<String, Object> values) { super(values); }
        @Nullable public static WasmDisassemblyChunk fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new WasmDisassemblyChunk(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The next chunk of disassembled lines.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> lines() {
            return list(value("lines"), element0 -> (String) element0);
        }
        /**
         * The bytecode offsets describing the start of each line.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Long> bytecodeOffsets() {
            return list(value("bytecodeOffsets"), element0 -> numberAsLong(element0));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The next chunk of disassembled lines.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder lines(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("lines");
                else values.put("lines", jsonValue(value));
                return this;
            }
            /**
             * The bytecode offsets describing the start of each line.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder bytecodeOffsets(@Nullable java.util.List<Long> value) {
                if (value == null) values.remove("bytecodeOffsets");
                else values.put("bytecodeOffsets", jsonValue(value));
                return this;
            }
            public WasmDisassemblyChunk build() {
                if (!values.containsKey("lines")) throw new IllegalStateException("Missing required CDP field: lines");
                if (!values.containsKey("bytecodeOffsets")) throw new IllegalStateException("Missing required CDP field: bytecodeOffsets");
                return new WasmDisassemblyChunk(values);
            }
        }
    }
    /**
     * Enum of possible script languages.
     */
    public static final class ScriptLanguage {
        private ScriptLanguage() {}
        public static final String JAVASCRIPT = "JavaScript";
        public static final String WEBASSEMBLY = "WebAssembly";
    }
    /**
     * Debug symbols available for a wasm script.
     */
    public static final class DebugSymbols extends CdpObject {
        private DebugSymbols(Map<String, Object> values) { super(values); }
        @Nullable public static DebugSymbols fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DebugSymbols(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Type of the debug symbols.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * Type of the debug symbols.
         */
        public static final class TypeValues {
            private TypeValues() {}
            public static final String SOURCEMAP = "SourceMap";
            public static final String EMBEDDEDDWARF = "EmbeddedDWARF";
            public static final String EXTERNALDWARF = "ExternalDWARF";
        }
        /**
         * URL of the external symbol source.
         * @return the protocol field value
         */
        @Nullable public String externalURL() {
            return (String) value("externalURL");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Type of the debug symbols.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            /**
             * URL of the external symbol source.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder externalURL(@Nullable String value) {
                if (value == null) values.remove("externalURL");
                else values.put("externalURL", jsonValue(value));
                return this;
            }
            public DebugSymbols build() {
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                return new DebugSymbols(values);
            }
        }
    }
    /**
     */
    public static final class ResolvedBreakpoint extends CdpObject {
        private ResolvedBreakpoint(Map<String, Object> values) { super(values); }
        @Nullable public static ResolvedBreakpoint fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ResolvedBreakpoint(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Breakpoint unique identifier.
         * @return the protocol field value
         */
        @Nullable public String breakpointId() {
            return (String) value("breakpointId");
        }
        /**
         * Actual breakpoint location.
         * @return the protocol field value
         */
        @Nullable public Debugger.Location location() {
            return Debugger.Location.fromMap(objectMap(value("location")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Breakpoint unique identifier.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder breakpointId(@Nullable String value) {
                if (value == null) values.remove("breakpointId");
                else values.put("breakpointId", jsonValue(value));
                return this;
            }
            /**
             * Actual breakpoint location.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder location(@Nullable Debugger.Location value) {
                if (value == null) values.remove("location");
                else values.put("location", jsonValue(value));
                return this;
            }
            public ResolvedBreakpoint build() {
                if (!values.containsKey("breakpointId")) throw new IllegalStateException("Missing required CDP field: breakpointId");
                if (!values.containsKey("location")) throw new IllegalStateException("Missing required CDP field: location");
                return new ResolvedBreakpoint(values);
            }
        }
    }
    /**
     * Continues execution until specific location is reached.
     */
    public static final class ContinueToLocationParams extends CdpObject {
        private ContinueToLocationParams(Map<String, Object> values) { super(values); }
        @Nullable public static ContinueToLocationParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ContinueToLocationParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Location to continue to.
         * @return the protocol field value
         */
        @Nullable public Debugger.Location location() {
            return Debugger.Location.fromMap(objectMap(value("location")));
        }
        /**
         * Returns the targetCallFrames field.
         * @return the protocol field value
         */
        @Nullable public String targetCallFrames() {
            return (String) value("targetCallFrames");
        }
        /**
         * Wire values for TargetCallFramesValues.
         */
        public static final class TargetCallFramesValues {
            private TargetCallFramesValues() {}
            public static final String ANY = "any";
            public static final String CURRENT = "current";
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Location to continue to.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder location(@Nullable Debugger.Location value) {
                if (value == null) values.remove("location");
                else values.put("location", jsonValue(value));
                return this;
            }
            /**
             * Sets the targetCallFrames field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder targetCallFrames(@Nullable String value) {
                if (value == null) values.remove("targetCallFrames");
                else values.put("targetCallFrames", jsonValue(value));
                return this;
            }
            public ContinueToLocationParams build() {
                if (!values.containsKey("location")) throw new IllegalStateException("Missing required CDP field: location");
                return new ContinueToLocationParams(values);
            }
        }
    }
    /**
     * Continues execution until specific location is reached.
     */
    public static final class ContinueToLocationResult extends CdpObject {
        private ContinueToLocationResult(Map<String, Object> values) { super(values); }
        @Nullable public static ContinueToLocationResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ContinueToLocationResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ContinueToLocationResult build() {
                return new ContinueToLocationResult(values);
            }
        }
    }
    /**
     * Disables debugger for given page.
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
     * Disables debugger for given page.
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
     * Enables debugger for the given page. Clients should not assume that the debugging has been enabled until the result for this command is received.
     */
    public static final class EnableParams extends CdpObject {
        private EnableParams(Map<String, Object> values) { super(values); }
        @Nullable public static EnableParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EnableParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The maximum size in bytes of collected scripts (not referenced by other heap objects) the debugger can hold. Puts no limit if parameter is omitted.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Double maxScriptsCacheSize() {
            return numberAsDouble(value("maxScriptsCacheSize"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The maximum size in bytes of collected scripts (not referenced by other heap objects) the debugger can hold. Puts no limit if parameter is omitted.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder maxScriptsCacheSize(@Nullable Double value) {
                if (value == null) values.remove("maxScriptsCacheSize");
                else values.put("maxScriptsCacheSize", jsonValue(value));
                return this;
            }
            public EnableParams build() {
                return new EnableParams(values);
            }
        }
    }
    /**
     * Enables debugger for the given page. Clients should not assume that the debugging has been enabled until the result for this command is received.
     */
    public static final class EnableResult extends CdpObject {
        private EnableResult(Map<String, Object> values) { super(values); }
        @Nullable public static EnableResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EnableResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Unique identifier of the debugger.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String debuggerId() {
            return (String) value("debuggerId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Unique identifier of the debugger.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder debuggerId(@Nullable String value) {
                if (value == null) values.remove("debuggerId");
                else values.put("debuggerId", jsonValue(value));
                return this;
            }
            public EnableResult build() {
                if (!values.containsKey("debuggerId")) throw new IllegalStateException("Missing required CDP field: debuggerId");
                return new EnableResult(values);
            }
        }
    }
    /**
     * Evaluates expression on a given call frame.
     */
    public static final class EvaluateOnCallFrameParams extends CdpObject {
        private EvaluateOnCallFrameParams(Map<String, Object> values) { super(values); }
        @Nullable public static EvaluateOnCallFrameParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EvaluateOnCallFrameParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Call frame identifier to evaluate on.
         * @return the protocol field value
         */
        @Nullable public String callFrameId() {
            return (String) value("callFrameId");
        }
        /**
         * Expression to evaluate.
         * @return the protocol field value
         */
        @Nullable public String expression() {
            return (String) value("expression");
        }
        /**
         * String object group name to put result into (allows rapid releasing resulting object handles using {@code releaseObjectGroup}).
         * @return the protocol field value
         */
        @Nullable public String objectGroup() {
            return (String) value("objectGroup");
        }
        /**
         * Specifies whether command line API should be available to the evaluated expression, defaults to false.
         * @return the protocol field value
         */
        @Nullable public Boolean includeCommandLineAPI() {
            return (Boolean) value("includeCommandLineAPI");
        }
        /**
         * In silent mode exceptions thrown during evaluation are not reported and do not pause execution. Overrides {@code setPauseOnException} state.
         * @return the protocol field value
         */
        @Nullable public Boolean silent() {
            return (Boolean) value("silent");
        }
        /**
         * Whether the result is expected to be a JSON object that should be sent by value.
         * @return the protocol field value
         */
        @Nullable public Boolean returnByValue() {
            return (Boolean) value("returnByValue");
        }
        /**
         * Whether preview should be generated for the result.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean generatePreview() {
            return (Boolean) value("generatePreview");
        }
        /**
         * Whether to throw an exception if side effect cannot be ruled out during evaluation.
         * @return the protocol field value
         */
        @Nullable public Boolean throwOnSideEffect() {
            return (Boolean) value("throwOnSideEffect");
        }
        /**
         * Terminate execution after timing out (number of milliseconds).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Double timeout() {
            return numberAsDouble(value("timeout"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Call frame identifier to evaluate on.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder callFrameId(@Nullable String value) {
                if (value == null) values.remove("callFrameId");
                else values.put("callFrameId", jsonValue(value));
                return this;
            }
            /**
             * Expression to evaluate.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder expression(@Nullable String value) {
                if (value == null) values.remove("expression");
                else values.put("expression", jsonValue(value));
                return this;
            }
            /**
             * String object group name to put result into (allows rapid releasing resulting object handles using {@code releaseObjectGroup}).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder objectGroup(@Nullable String value) {
                if (value == null) values.remove("objectGroup");
                else values.put("objectGroup", jsonValue(value));
                return this;
            }
            /**
             * Specifies whether command line API should be available to the evaluated expression, defaults to false.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder includeCommandLineAPI(@Nullable Boolean value) {
                if (value == null) values.remove("includeCommandLineAPI");
                else values.put("includeCommandLineAPI", jsonValue(value));
                return this;
            }
            /**
             * In silent mode exceptions thrown during evaluation are not reported and do not pause execution. Overrides {@code setPauseOnException} state.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder silent(@Nullable Boolean value) {
                if (value == null) values.remove("silent");
                else values.put("silent", jsonValue(value));
                return this;
            }
            /**
             * Whether the result is expected to be a JSON object that should be sent by value.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder returnByValue(@Nullable Boolean value) {
                if (value == null) values.remove("returnByValue");
                else values.put("returnByValue", jsonValue(value));
                return this;
            }
            /**
             * Whether preview should be generated for the result.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder generatePreview(@Nullable Boolean value) {
                if (value == null) values.remove("generatePreview");
                else values.put("generatePreview", jsonValue(value));
                return this;
            }
            /**
             * Whether to throw an exception if side effect cannot be ruled out during evaluation.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder throwOnSideEffect(@Nullable Boolean value) {
                if (value == null) values.remove("throwOnSideEffect");
                else values.put("throwOnSideEffect", jsonValue(value));
                return this;
            }
            /**
             * Terminate execution after timing out (number of milliseconds).
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timeout(@Nullable Double value) {
                if (value == null) values.remove("timeout");
                else values.put("timeout", jsonValue(value));
                return this;
            }
            public EvaluateOnCallFrameParams build() {
                if (!values.containsKey("callFrameId")) throw new IllegalStateException("Missing required CDP field: callFrameId");
                if (!values.containsKey("expression")) throw new IllegalStateException("Missing required CDP field: expression");
                return new EvaluateOnCallFrameParams(values);
            }
        }
    }
    /**
     * Evaluates expression on a given call frame.
     */
    public static final class EvaluateOnCallFrameResult extends CdpObject {
        private EvaluateOnCallFrameResult(Map<String, Object> values) { super(values); }
        @Nullable public static EvaluateOnCallFrameResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EvaluateOnCallFrameResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Object wrapper for the evaluation result.
         * @return the protocol field value
         */
        @Nullable public Runtime.RemoteObject result() {
            return Runtime.RemoteObject.fromMap(objectMap(value("result")));
        }
        /**
         * Exception details.
         * @return the protocol field value
         */
        @Nullable public Runtime.ExceptionDetails exceptionDetails() {
            return Runtime.ExceptionDetails.fromMap(objectMap(value("exceptionDetails")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Object wrapper for the evaluation result.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder result(@Nullable Runtime.RemoteObject value) {
                if (value == null) values.remove("result");
                else values.put("result", jsonValue(value));
                return this;
            }
            /**
             * Exception details.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder exceptionDetails(@Nullable Runtime.ExceptionDetails value) {
                if (value == null) values.remove("exceptionDetails");
                else values.put("exceptionDetails", jsonValue(value));
                return this;
            }
            public EvaluateOnCallFrameResult build() {
                if (!values.containsKey("result")) throw new IllegalStateException("Missing required CDP field: result");
                return new EvaluateOnCallFrameResult(values);
            }
        }
    }
    /**
     * Returns possible locations for breakpoint. scriptId in start and end range locations should be the same.
     */
    public static final class GetPossibleBreakpointsParams extends CdpObject {
        private GetPossibleBreakpointsParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetPossibleBreakpointsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetPossibleBreakpointsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Start of range to search possible breakpoint locations in.
         * @return the protocol field value
         */
        @Nullable public Debugger.Location start() {
            return Debugger.Location.fromMap(objectMap(value("start")));
        }
        /**
         * End of range to search possible breakpoint locations in (excluding). When not specified, end of scripts is used as end of range.
         * @return the protocol field value
         */
        @Nullable public Debugger.Location end() {
            return Debugger.Location.fromMap(objectMap(value("end")));
        }
        /**
         * Only consider locations which are in the same (non-nested) function as start.
         * @return the protocol field value
         */
        @Nullable public Boolean restrictToFunction() {
            return (Boolean) value("restrictToFunction");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Start of range to search possible breakpoint locations in.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder start(@Nullable Debugger.Location value) {
                if (value == null) values.remove("start");
                else values.put("start", jsonValue(value));
                return this;
            }
            /**
             * End of range to search possible breakpoint locations in (excluding). When not specified, end of scripts is used as end of range.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder end(@Nullable Debugger.Location value) {
                if (value == null) values.remove("end");
                else values.put("end", jsonValue(value));
                return this;
            }
            /**
             * Only consider locations which are in the same (non-nested) function as start.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder restrictToFunction(@Nullable Boolean value) {
                if (value == null) values.remove("restrictToFunction");
                else values.put("restrictToFunction", jsonValue(value));
                return this;
            }
            public GetPossibleBreakpointsParams build() {
                if (!values.containsKey("start")) throw new IllegalStateException("Missing required CDP field: start");
                return new GetPossibleBreakpointsParams(values);
            }
        }
    }
    /**
     * Returns possible locations for breakpoint. scriptId in start and end range locations should be the same.
     */
    public static final class GetPossibleBreakpointsResult extends CdpObject {
        private GetPossibleBreakpointsResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetPossibleBreakpointsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetPossibleBreakpointsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * List of the possible breakpoint locations.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Debugger.BreakLocation> locations() {
            return list(value("locations"), element0 -> Debugger.BreakLocation.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * List of the possible breakpoint locations.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder locations(@Nullable java.util.List<Debugger.BreakLocation> value) {
                if (value == null) values.remove("locations");
                else values.put("locations", jsonValue(value));
                return this;
            }
            public GetPossibleBreakpointsResult build() {
                if (!values.containsKey("locations")) throw new IllegalStateException("Missing required CDP field: locations");
                return new GetPossibleBreakpointsResult(values);
            }
        }
    }
    /**
     * Returns source for the script with given id.
     */
    public static final class GetScriptSourceParams extends CdpObject {
        private GetScriptSourceParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetScriptSourceParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetScriptSourceParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the script to get source for.
         * @return the protocol field value
         */
        @Nullable public String scriptId() {
            return (String) value("scriptId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the script to get source for.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scriptId(@Nullable String value) {
                if (value == null) values.remove("scriptId");
                else values.put("scriptId", jsonValue(value));
                return this;
            }
            public GetScriptSourceParams build() {
                if (!values.containsKey("scriptId")) throw new IllegalStateException("Missing required CDP field: scriptId");
                return new GetScriptSourceParams(values);
            }
        }
    }
    /**
     * Returns source for the script with given id.
     */
    public static final class GetScriptSourceResult extends CdpObject {
        private GetScriptSourceResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetScriptSourceResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetScriptSourceResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Script source (empty in case of Wasm bytecode).
         * @return the protocol field value
         */
        @Nullable public String scriptSource() {
            return (String) value("scriptSource");
        }
        /**
         * Wasm bytecode. (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value
         */
        @Nullable public String bytecode() {
            return (String) value("bytecode");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Script source (empty in case of Wasm bytecode).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scriptSource(@Nullable String value) {
                if (value == null) values.remove("scriptSource");
                else values.put("scriptSource", jsonValue(value));
                return this;
            }
            /**
             * Wasm bytecode. (Encoded as a base64 string when passed over JSON)
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder bytecode(@Nullable String value) {
                if (value == null) values.remove("bytecode");
                else values.put("bytecode", jsonValue(value));
                return this;
            }
            public GetScriptSourceResult build() {
                if (!values.containsKey("scriptSource")) throw new IllegalStateException("Missing required CDP field: scriptSource");
                return new GetScriptSourceResult(values);
            }
        }
    }
    /**
     * Parameters for Debugger.disassembleWasmModule.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DisassembleWasmModuleParams extends CdpObject {
        private DisassembleWasmModuleParams(Map<String, Object> values) { super(values); }
        @Nullable public static DisassembleWasmModuleParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DisassembleWasmModuleParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the script to disassemble
         * @return the protocol field value
         */
        @Nullable public String scriptId() {
            return (String) value("scriptId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the script to disassemble
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scriptId(@Nullable String value) {
                if (value == null) values.remove("scriptId");
                else values.put("scriptId", jsonValue(value));
                return this;
            }
            public DisassembleWasmModuleParams build() {
                if (!values.containsKey("scriptId")) throw new IllegalStateException("Missing required CDP field: scriptId");
                return new DisassembleWasmModuleParams(values);
            }
        }
    }
    /**
     * Result of Debugger.disassembleWasmModule.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DisassembleWasmModuleResult extends CdpObject {
        private DisassembleWasmModuleResult(Map<String, Object> values) { super(values); }
        @Nullable public static DisassembleWasmModuleResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DisassembleWasmModuleResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * For large modules, return a stream from which additional chunks of disassembly can be read successively.
         * @return the protocol field value
         */
        @Nullable public String streamId() {
            return (String) value("streamId");
        }
        /**
         * The total number of lines in the disassembly text.
         * @return the protocol field value
         */
        @Nullable public Long totalNumberOfLines() {
            return numberAsLong(value("totalNumberOfLines"));
        }
        /**
         * The offsets of all function bodies, in the format [start1, end1, start2, end2, ...] where all ends are exclusive.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Long> functionBodyOffsets() {
            return list(value("functionBodyOffsets"), element0 -> numberAsLong(element0));
        }
        /**
         * The first chunk of disassembly.
         * @return the protocol field value
         */
        @Nullable public Debugger.WasmDisassemblyChunk chunk() {
            return Debugger.WasmDisassemblyChunk.fromMap(objectMap(value("chunk")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * For large modules, return a stream from which additional chunks of disassembly can be read successively.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder streamId(@Nullable String value) {
                if (value == null) values.remove("streamId");
                else values.put("streamId", jsonValue(value));
                return this;
            }
            /**
             * The total number of lines in the disassembly text.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder totalNumberOfLines(@Nullable Long value) {
                if (value == null) values.remove("totalNumberOfLines");
                else values.put("totalNumberOfLines", jsonValue(value));
                return this;
            }
            /**
             * The offsets of all function bodies, in the format [start1, end1, start2, end2, ...] where all ends are exclusive.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder functionBodyOffsets(@Nullable java.util.List<Long> value) {
                if (value == null) values.remove("functionBodyOffsets");
                else values.put("functionBodyOffsets", jsonValue(value));
                return this;
            }
            /**
             * The first chunk of disassembly.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder chunk(@Nullable Debugger.WasmDisassemblyChunk value) {
                if (value == null) values.remove("chunk");
                else values.put("chunk", jsonValue(value));
                return this;
            }
            public DisassembleWasmModuleResult build() {
                if (!values.containsKey("totalNumberOfLines")) throw new IllegalStateException("Missing required CDP field: totalNumberOfLines");
                if (!values.containsKey("functionBodyOffsets")) throw new IllegalStateException("Missing required CDP field: functionBodyOffsets");
                if (!values.containsKey("chunk")) throw new IllegalStateException("Missing required CDP field: chunk");
                return new DisassembleWasmModuleResult(values);
            }
        }
    }
    /**
     * Disassemble the next chunk of lines for the module corresponding to the stream. If disassembly is complete, this API will invalidate the streamId and return an empty chunk. Any subsequent calls for the now invalid stream will return errors.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class NextWasmDisassemblyChunkParams extends CdpObject {
        private NextWasmDisassemblyChunkParams(Map<String, Object> values) { super(values); }
        @Nullable public static NextWasmDisassemblyChunkParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new NextWasmDisassemblyChunkParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the streamId field.
         * @return the protocol field value
         */
        @Nullable public String streamId() {
            return (String) value("streamId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the streamId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder streamId(@Nullable String value) {
                if (value == null) values.remove("streamId");
                else values.put("streamId", jsonValue(value));
                return this;
            }
            public NextWasmDisassemblyChunkParams build() {
                if (!values.containsKey("streamId")) throw new IllegalStateException("Missing required CDP field: streamId");
                return new NextWasmDisassemblyChunkParams(values);
            }
        }
    }
    /**
     * Disassemble the next chunk of lines for the module corresponding to the stream. If disassembly is complete, this API will invalidate the streamId and return an empty chunk. Any subsequent calls for the now invalid stream will return errors.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class NextWasmDisassemblyChunkResult extends CdpObject {
        private NextWasmDisassemblyChunkResult(Map<String, Object> values) { super(values); }
        @Nullable public static NextWasmDisassemblyChunkResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new NextWasmDisassemblyChunkResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The next chunk of disassembly.
         * @return the protocol field value
         */
        @Nullable public Debugger.WasmDisassemblyChunk chunk() {
            return Debugger.WasmDisassemblyChunk.fromMap(objectMap(value("chunk")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The next chunk of disassembly.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder chunk(@Nullable Debugger.WasmDisassemblyChunk value) {
                if (value == null) values.remove("chunk");
                else values.put("chunk", jsonValue(value));
                return this;
            }
            public NextWasmDisassemblyChunkResult build() {
                if (!values.containsKey("chunk")) throw new IllegalStateException("Missing required CDP field: chunk");
                return new NextWasmDisassemblyChunkResult(values);
            }
        }
    }
    /**
     * This command is deprecated. Use getScriptSource instead.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class GetWasmBytecodeParams extends CdpObject {
        private GetWasmBytecodeParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetWasmBytecodeParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetWasmBytecodeParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the Wasm script to get source for.
         * @return the protocol field value
         */
        @Nullable public String scriptId() {
            return (String) value("scriptId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the Wasm script to get source for.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scriptId(@Nullable String value) {
                if (value == null) values.remove("scriptId");
                else values.put("scriptId", jsonValue(value));
                return this;
            }
            public GetWasmBytecodeParams build() {
                if (!values.containsKey("scriptId")) throw new IllegalStateException("Missing required CDP field: scriptId");
                return new GetWasmBytecodeParams(values);
            }
        }
    }
    /**
     * This command is deprecated. Use getScriptSource instead.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class GetWasmBytecodeResult extends CdpObject {
        private GetWasmBytecodeResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetWasmBytecodeResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetWasmBytecodeResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Script source. (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value
         */
        @Nullable public String bytecode() {
            return (String) value("bytecode");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Script source. (Encoded as a base64 string when passed over JSON)
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder bytecode(@Nullable String value) {
                if (value == null) values.remove("bytecode");
                else values.put("bytecode", jsonValue(value));
                return this;
            }
            public GetWasmBytecodeResult build() {
                if (!values.containsKey("bytecode")) throw new IllegalStateException("Missing required CDP field: bytecode");
                return new GetWasmBytecodeResult(values);
            }
        }
    }
    /**
     * Returns stack trace with given {@code stackTraceId}.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetStackTraceParams extends CdpObject {
        private GetStackTraceParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetStackTraceParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetStackTraceParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the stackTraceId field.
         * @return the protocol field value
         */
        @Nullable public Runtime.StackTraceId stackTraceId() {
            return Runtime.StackTraceId.fromMap(objectMap(value("stackTraceId")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the stackTraceId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder stackTraceId(@Nullable Runtime.StackTraceId value) {
                if (value == null) values.remove("stackTraceId");
                else values.put("stackTraceId", jsonValue(value));
                return this;
            }
            public GetStackTraceParams build() {
                if (!values.containsKey("stackTraceId")) throw new IllegalStateException("Missing required CDP field: stackTraceId");
                return new GetStackTraceParams(values);
            }
        }
    }
    /**
     * Returns stack trace with given {@code stackTraceId}.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetStackTraceResult extends CdpObject {
        private GetStackTraceResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetStackTraceResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetStackTraceResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the stackTrace field.
         * @return the protocol field value
         */
        @Nullable public Runtime.StackTrace stackTrace() {
            return Runtime.StackTrace.fromMap(objectMap(value("stackTrace")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the stackTrace field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder stackTrace(@Nullable Runtime.StackTrace value) {
                if (value == null) values.remove("stackTrace");
                else values.put("stackTrace", jsonValue(value));
                return this;
            }
            public GetStackTraceResult build() {
                if (!values.containsKey("stackTrace")) throw new IllegalStateException("Missing required CDP field: stackTrace");
                return new GetStackTraceResult(values);
            }
        }
    }
    /**
     * Stops on the next JavaScript statement.
     */
    public static final class PauseParams extends CdpObject {
        private PauseParams(Map<String, Object> values) { super(values); }
        @Nullable public static PauseParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PauseParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public PauseParams build() {
                return new PauseParams(values);
            }
        }
    }
    /**
     * Stops on the next JavaScript statement.
     */
    public static final class PauseResult extends CdpObject {
        private PauseResult(Map<String, Object> values) { super(values); }
        @Nullable public static PauseResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PauseResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public PauseResult build() {
                return new PauseResult(values);
            }
        }
    }
    /**
     * Parameters for Debugger.pauseOnAsyncCall.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class PauseOnAsyncCallParams extends CdpObject {
        private PauseOnAsyncCallParams(Map<String, Object> values) { super(values); }
        @Nullable public static PauseOnAsyncCallParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PauseOnAsyncCallParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Debugger will pause when async call with given stack trace is started.
         * @return the protocol field value
         */
        @Nullable public Runtime.StackTraceId parentStackTraceId() {
            return Runtime.StackTraceId.fromMap(objectMap(value("parentStackTraceId")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Debugger will pause when async call with given stack trace is started.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder parentStackTraceId(@Nullable Runtime.StackTraceId value) {
                if (value == null) values.remove("parentStackTraceId");
                else values.put("parentStackTraceId", jsonValue(value));
                return this;
            }
            public PauseOnAsyncCallParams build() {
                if (!values.containsKey("parentStackTraceId")) throw new IllegalStateException("Missing required CDP field: parentStackTraceId");
                return new PauseOnAsyncCallParams(values);
            }
        }
    }
    /**
     * Result of Debugger.pauseOnAsyncCall.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class PauseOnAsyncCallResult extends CdpObject {
        private PauseOnAsyncCallResult(Map<String, Object> values) { super(values); }
        @Nullable public static PauseOnAsyncCallResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PauseOnAsyncCallResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public PauseOnAsyncCallResult build() {
                return new PauseOnAsyncCallResult(values);
            }
        }
    }
    /**
     * Removes JavaScript breakpoint.
     */
    public static final class RemoveBreakpointParams extends CdpObject {
        private RemoveBreakpointParams(Map<String, Object> values) { super(values); }
        @Nullable public static RemoveBreakpointParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RemoveBreakpointParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the breakpointId field.
         * @return the protocol field value
         */
        @Nullable public String breakpointId() {
            return (String) value("breakpointId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the breakpointId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder breakpointId(@Nullable String value) {
                if (value == null) values.remove("breakpointId");
                else values.put("breakpointId", jsonValue(value));
                return this;
            }
            public RemoveBreakpointParams build() {
                if (!values.containsKey("breakpointId")) throw new IllegalStateException("Missing required CDP field: breakpointId");
                return new RemoveBreakpointParams(values);
            }
        }
    }
    /**
     * Removes JavaScript breakpoint.
     */
    public static final class RemoveBreakpointResult extends CdpObject {
        private RemoveBreakpointResult(Map<String, Object> values) { super(values); }
        @Nullable public static RemoveBreakpointResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RemoveBreakpointResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public RemoveBreakpointResult build() {
                return new RemoveBreakpointResult(values);
            }
        }
    }
    /**
     * Restarts particular call frame from the beginning. The old, deprecated behavior of {@code restartFrame} is to stay paused and allow further CDP commands after a restart was scheduled. This can cause problems with restarting, so we now continue execution immediatly after it has been scheduled until we reach the beginning of the restarted frame.
     * <p>To stay back-wards compatible, {@code restartFrame} now expects a {@code mode} parameter to be present. If the {@code mode} parameter is missing, {@code restartFrame} errors out.
     * <p>The various return values are deprecated and {@code callFrames} is always empty. Use the call frames from the {@code Debugger#paused} events instead, that fires once V8 pauses at the beginning of the restarted function.
     */
    public static final class RestartFrameParams extends CdpObject {
        private RestartFrameParams(Map<String, Object> values) { super(values); }
        @Nullable public static RestartFrameParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RestartFrameParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Call frame identifier to evaluate on.
         * @return the protocol field value
         */
        @Nullable public String callFrameId() {
            return (String) value("callFrameId");
        }
        /**
         * The {@code mode} parameter must be present and set to &#x27;StepInto&#x27;, otherwise {@code restartFrame} will error out.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String mode() {
            return (String) value("mode");
        }
        /**
         * The {@code mode} parameter must be present and set to &#x27;StepInto&#x27;, otherwise {@code restartFrame} will error out.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         */
        public static final class ModeValues {
            private ModeValues() {}
            public static final String STEPINTO = "StepInto";
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Call frame identifier to evaluate on.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder callFrameId(@Nullable String value) {
                if (value == null) values.remove("callFrameId");
                else values.put("callFrameId", jsonValue(value));
                return this;
            }
            /**
             * The {@code mode} parameter must be present and set to &#x27;StepInto&#x27;, otherwise {@code restartFrame} will error out.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder mode(@Nullable String value) {
                if (value == null) values.remove("mode");
                else values.put("mode", jsonValue(value));
                return this;
            }
            public RestartFrameParams build() {
                if (!values.containsKey("callFrameId")) throw new IllegalStateException("Missing required CDP field: callFrameId");
                return new RestartFrameParams(values);
            }
        }
    }
    /**
     * Restarts particular call frame from the beginning. The old, deprecated behavior of {@code restartFrame} is to stay paused and allow further CDP commands after a restart was scheduled. This can cause problems with restarting, so we now continue execution immediatly after it has been scheduled until we reach the beginning of the restarted frame.
     * <p>To stay back-wards compatible, {@code restartFrame} now expects a {@code mode} parameter to be present. If the {@code mode} parameter is missing, {@code restartFrame} errors out.
     * <p>The various return values are deprecated and {@code callFrames} is always empty. Use the call frames from the {@code Debugger#paused} events instead, that fires once V8 pauses at the beginning of the restarted function.
     */
    public static final class RestartFrameResult extends CdpObject {
        private RestartFrameResult(Map<String, Object> values) { super(values); }
        @Nullable public static RestartFrameResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RestartFrameResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * New stack trace.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        @Nullable public java.util.List<Debugger.CallFrame> callFrames() {
            return list(value("callFrames"), element0 -> Debugger.CallFrame.fromMap(objectMap(element0)));
        }
        /**
         * Async stack trace, if any.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        @Nullable public Runtime.StackTrace asyncStackTrace() {
            return Runtime.StackTrace.fromMap(objectMap(value("asyncStackTrace")));
        }
        /**
         * Async stack trace, if any.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        @Nullable public Runtime.StackTraceId asyncStackTraceId() {
            return Runtime.StackTraceId.fromMap(objectMap(value("asyncStackTraceId")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * New stack trace.
             * @param value field value; null removes an optional value
             * @return this builder
             * @deprecated Deprecated by the Chromium DevTools Protocol.
             */
            @Deprecated
            public Builder callFrames(@Nullable java.util.List<Debugger.CallFrame> value) {
                if (value == null) values.remove("callFrames");
                else values.put("callFrames", jsonValue(value));
                return this;
            }
            /**
             * Async stack trace, if any.
             * @param value field value; null removes an optional value
             * @return this builder
             * @deprecated Deprecated by the Chromium DevTools Protocol.
             */
            @Deprecated
            public Builder asyncStackTrace(@Nullable Runtime.StackTrace value) {
                if (value == null) values.remove("asyncStackTrace");
                else values.put("asyncStackTrace", jsonValue(value));
                return this;
            }
            /**
             * Async stack trace, if any.
             * @param value field value; null removes an optional value
             * @return this builder
             * @deprecated Deprecated by the Chromium DevTools Protocol.
             */
            @Deprecated
            public Builder asyncStackTraceId(@Nullable Runtime.StackTraceId value) {
                if (value == null) values.remove("asyncStackTraceId");
                else values.put("asyncStackTraceId", jsonValue(value));
                return this;
            }
            public RestartFrameResult build() {
                if (!values.containsKey("callFrames")) throw new IllegalStateException("Missing required CDP field: callFrames");
                return new RestartFrameResult(values);
            }
        }
    }
    /**
     * Resumes JavaScript execution.
     */
    public static final class ResumeParams extends CdpObject {
        private ResumeParams(Map<String, Object> values) { super(values); }
        @Nullable public static ResumeParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ResumeParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Set to true to terminate execution upon resuming execution. In contrast to Runtime.terminateExecution, this will allows to execute further JavaScript (i.e. via evaluation) until execution of the paused code is actually resumed, at which point termination is triggered. If execution is currently not paused, this parameter has no effect.
         * @return the protocol field value
         */
        @Nullable public Boolean terminateOnResume() {
            return (Boolean) value("terminateOnResume");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Set to true to terminate execution upon resuming execution. In contrast to Runtime.terminateExecution, this will allows to execute further JavaScript (i.e. via evaluation) until execution of the paused code is actually resumed, at which point termination is triggered. If execution is currently not paused, this parameter has no effect.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder terminateOnResume(@Nullable Boolean value) {
                if (value == null) values.remove("terminateOnResume");
                else values.put("terminateOnResume", jsonValue(value));
                return this;
            }
            public ResumeParams build() {
                return new ResumeParams(values);
            }
        }
    }
    /**
     * Resumes JavaScript execution.
     */
    public static final class ResumeResult extends CdpObject {
        private ResumeResult(Map<String, Object> values) { super(values); }
        @Nullable public static ResumeResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ResumeResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ResumeResult build() {
                return new ResumeResult(values);
            }
        }
    }
    /**
     * Searches for given string in script content.
     */
    public static final class SearchInContentParams extends CdpObject {
        private SearchInContentParams(Map<String, Object> values) { super(values); }
        @Nullable public static SearchInContentParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SearchInContentParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the script to search in.
         * @return the protocol field value
         */
        @Nullable public String scriptId() {
            return (String) value("scriptId");
        }
        /**
         * String to search for.
         * @return the protocol field value
         */
        @Nullable public String query() {
            return (String) value("query");
        }
        /**
         * If true, search is case sensitive.
         * @return the protocol field value
         */
        @Nullable public Boolean caseSensitive() {
            return (Boolean) value("caseSensitive");
        }
        /**
         * If true, treats string parameter as regex.
         * @return the protocol field value
         */
        @Nullable public Boolean isRegex() {
            return (Boolean) value("isRegex");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the script to search in.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scriptId(@Nullable String value) {
                if (value == null) values.remove("scriptId");
                else values.put("scriptId", jsonValue(value));
                return this;
            }
            /**
             * String to search for.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder query(@Nullable String value) {
                if (value == null) values.remove("query");
                else values.put("query", jsonValue(value));
                return this;
            }
            /**
             * If true, search is case sensitive.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder caseSensitive(@Nullable Boolean value) {
                if (value == null) values.remove("caseSensitive");
                else values.put("caseSensitive", jsonValue(value));
                return this;
            }
            /**
             * If true, treats string parameter as regex.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isRegex(@Nullable Boolean value) {
                if (value == null) values.remove("isRegex");
                else values.put("isRegex", jsonValue(value));
                return this;
            }
            public SearchInContentParams build() {
                if (!values.containsKey("scriptId")) throw new IllegalStateException("Missing required CDP field: scriptId");
                if (!values.containsKey("query")) throw new IllegalStateException("Missing required CDP field: query");
                return new SearchInContentParams(values);
            }
        }
    }
    /**
     * Searches for given string in script content.
     */
    public static final class SearchInContentResult extends CdpObject {
        private SearchInContentResult(Map<String, Object> values) { super(values); }
        @Nullable public static SearchInContentResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SearchInContentResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * List of search matches.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Debugger.SearchMatch> result() {
            return list(value("result"), element0 -> Debugger.SearchMatch.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * List of search matches.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder result(@Nullable java.util.List<Debugger.SearchMatch> value) {
                if (value == null) values.remove("result");
                else values.put("result", jsonValue(value));
                return this;
            }
            public SearchInContentResult build() {
                if (!values.containsKey("result")) throw new IllegalStateException("Missing required CDP field: result");
                return new SearchInContentResult(values);
            }
        }
    }
    /**
     * Enables or disables async call stacks tracking.
     */
    public static final class SetAsyncCallStackDepthParams extends CdpObject {
        private SetAsyncCallStackDepthParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetAsyncCallStackDepthParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetAsyncCallStackDepthParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Maximum depth of async call stacks. Setting to {@code 0} will effectively disable collecting async call stacks (default).
         * @return the protocol field value
         */
        @Nullable public Long maxDepth() {
            return numberAsLong(value("maxDepth"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Maximum depth of async call stacks. Setting to {@code 0} will effectively disable collecting async call stacks (default).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder maxDepth(@Nullable Long value) {
                if (value == null) values.remove("maxDepth");
                else values.put("maxDepth", jsonValue(value));
                return this;
            }
            public SetAsyncCallStackDepthParams build() {
                if (!values.containsKey("maxDepth")) throw new IllegalStateException("Missing required CDP field: maxDepth");
                return new SetAsyncCallStackDepthParams(values);
            }
        }
    }
    /**
     * Enables or disables async call stacks tracking.
     */
    public static final class SetAsyncCallStackDepthResult extends CdpObject {
        private SetAsyncCallStackDepthResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetAsyncCallStackDepthResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetAsyncCallStackDepthResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetAsyncCallStackDepthResult build() {
                return new SetAsyncCallStackDepthResult(values);
            }
        }
    }
    /**
     * Replace previous blackbox execution contexts with passed ones. Forces backend to skip stepping/pausing in scripts in these execution contexts. VM will try to leave blackboxed script by performing &#x27;step in&#x27; several times, finally resorting to &#x27;step out&#x27; if unsuccessful.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetBlackboxExecutionContextsParams extends CdpObject {
        private SetBlackboxExecutionContextsParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetBlackboxExecutionContextsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetBlackboxExecutionContextsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Array of execution context unique ids for the debugger to ignore.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> uniqueIds() {
            return list(value("uniqueIds"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Array of execution context unique ids for the debugger to ignore.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder uniqueIds(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("uniqueIds");
                else values.put("uniqueIds", jsonValue(value));
                return this;
            }
            public SetBlackboxExecutionContextsParams build() {
                if (!values.containsKey("uniqueIds")) throw new IllegalStateException("Missing required CDP field: uniqueIds");
                return new SetBlackboxExecutionContextsParams(values);
            }
        }
    }
    /**
     * Replace previous blackbox execution contexts with passed ones. Forces backend to skip stepping/pausing in scripts in these execution contexts. VM will try to leave blackboxed script by performing &#x27;step in&#x27; several times, finally resorting to &#x27;step out&#x27; if unsuccessful.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetBlackboxExecutionContextsResult extends CdpObject {
        private SetBlackboxExecutionContextsResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetBlackboxExecutionContextsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetBlackboxExecutionContextsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetBlackboxExecutionContextsResult build() {
                return new SetBlackboxExecutionContextsResult(values);
            }
        }
    }
    /**
     * Replace previous blackbox patterns with passed ones. Forces backend to skip stepping/pausing in scripts with url matching one of the patterns. VM will try to leave blackboxed script by performing &#x27;step in&#x27; several times, finally resorting to &#x27;step out&#x27; if unsuccessful.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetBlackboxPatternsParams extends CdpObject {
        private SetBlackboxPatternsParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetBlackboxPatternsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetBlackboxPatternsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Array of regexps that will be used to check script url for blackbox state.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> patterns() {
            return list(value("patterns"), element0 -> (String) element0);
        }
        /**
         * If true, also ignore scripts with no source url.
         * @return the protocol field value
         */
        @Nullable public Boolean skipAnonymous() {
            return (Boolean) value("skipAnonymous");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Array of regexps that will be used to check script url for blackbox state.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder patterns(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("patterns");
                else values.put("patterns", jsonValue(value));
                return this;
            }
            /**
             * If true, also ignore scripts with no source url.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder skipAnonymous(@Nullable Boolean value) {
                if (value == null) values.remove("skipAnonymous");
                else values.put("skipAnonymous", jsonValue(value));
                return this;
            }
            public SetBlackboxPatternsParams build() {
                if (!values.containsKey("patterns")) throw new IllegalStateException("Missing required CDP field: patterns");
                return new SetBlackboxPatternsParams(values);
            }
        }
    }
    /**
     * Replace previous blackbox patterns with passed ones. Forces backend to skip stepping/pausing in scripts with url matching one of the patterns. VM will try to leave blackboxed script by performing &#x27;step in&#x27; several times, finally resorting to &#x27;step out&#x27; if unsuccessful.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetBlackboxPatternsResult extends CdpObject {
        private SetBlackboxPatternsResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetBlackboxPatternsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetBlackboxPatternsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetBlackboxPatternsResult build() {
                return new SetBlackboxPatternsResult(values);
            }
        }
    }
    /**
     * Makes backend skip steps in the script in blackboxed ranges. VM will try leave blacklisted scripts by performing &#x27;step in&#x27; several times, finally resorting to &#x27;step out&#x27; if unsuccessful. Positions array contains positions where blackbox state is changed. First interval isn&#x27;t blackboxed. Array should be sorted.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetBlackboxedRangesParams extends CdpObject {
        private SetBlackboxedRangesParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetBlackboxedRangesParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetBlackboxedRangesParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the script.
         * @return the protocol field value
         */
        @Nullable public String scriptId() {
            return (String) value("scriptId");
        }
        /**
         * Returns the positions field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Debugger.ScriptPosition> positions() {
            return list(value("positions"), element0 -> Debugger.ScriptPosition.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the script.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scriptId(@Nullable String value) {
                if (value == null) values.remove("scriptId");
                else values.put("scriptId", jsonValue(value));
                return this;
            }
            /**
             * Sets the positions field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder positions(@Nullable java.util.List<Debugger.ScriptPosition> value) {
                if (value == null) values.remove("positions");
                else values.put("positions", jsonValue(value));
                return this;
            }
            public SetBlackboxedRangesParams build() {
                if (!values.containsKey("scriptId")) throw new IllegalStateException("Missing required CDP field: scriptId");
                if (!values.containsKey("positions")) throw new IllegalStateException("Missing required CDP field: positions");
                return new SetBlackboxedRangesParams(values);
            }
        }
    }
    /**
     * Makes backend skip steps in the script in blackboxed ranges. VM will try leave blacklisted scripts by performing &#x27;step in&#x27; several times, finally resorting to &#x27;step out&#x27; if unsuccessful. Positions array contains positions where blackbox state is changed. First interval isn&#x27;t blackboxed. Array should be sorted.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetBlackboxedRangesResult extends CdpObject {
        private SetBlackboxedRangesResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetBlackboxedRangesResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetBlackboxedRangesResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetBlackboxedRangesResult build() {
                return new SetBlackboxedRangesResult(values);
            }
        }
    }
    /**
     * Sets JavaScript breakpoint at a given location.
     */
    public static final class SetBreakpointParams extends CdpObject {
        private SetBreakpointParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetBreakpointParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetBreakpointParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Location to set breakpoint in.
         * @return the protocol field value
         */
        @Nullable public Debugger.Location location() {
            return Debugger.Location.fromMap(objectMap(value("location")));
        }
        /**
         * Expression to use as a breakpoint condition. When specified, debugger will only stop on the breakpoint if this expression evaluates to true.
         * @return the protocol field value
         */
        @Nullable public String condition() {
            return (String) value("condition");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Location to set breakpoint in.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder location(@Nullable Debugger.Location value) {
                if (value == null) values.remove("location");
                else values.put("location", jsonValue(value));
                return this;
            }
            /**
             * Expression to use as a breakpoint condition. When specified, debugger will only stop on the breakpoint if this expression evaluates to true.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder condition(@Nullable String value) {
                if (value == null) values.remove("condition");
                else values.put("condition", jsonValue(value));
                return this;
            }
            public SetBreakpointParams build() {
                if (!values.containsKey("location")) throw new IllegalStateException("Missing required CDP field: location");
                return new SetBreakpointParams(values);
            }
        }
    }
    /**
     * Sets JavaScript breakpoint at a given location.
     */
    public static final class SetBreakpointResult extends CdpObject {
        private SetBreakpointResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetBreakpointResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetBreakpointResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the created breakpoint for further reference.
         * @return the protocol field value
         */
        @Nullable public String breakpointId() {
            return (String) value("breakpointId");
        }
        /**
         * Location this breakpoint resolved into.
         * @return the protocol field value
         */
        @Nullable public Debugger.Location actualLocation() {
            return Debugger.Location.fromMap(objectMap(value("actualLocation")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the created breakpoint for further reference.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder breakpointId(@Nullable String value) {
                if (value == null) values.remove("breakpointId");
                else values.put("breakpointId", jsonValue(value));
                return this;
            }
            /**
             * Location this breakpoint resolved into.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder actualLocation(@Nullable Debugger.Location value) {
                if (value == null) values.remove("actualLocation");
                else values.put("actualLocation", jsonValue(value));
                return this;
            }
            public SetBreakpointResult build() {
                if (!values.containsKey("breakpointId")) throw new IllegalStateException("Missing required CDP field: breakpointId");
                if (!values.containsKey("actualLocation")) throw new IllegalStateException("Missing required CDP field: actualLocation");
                return new SetBreakpointResult(values);
            }
        }
    }
    /**
     * Sets instrumentation breakpoint.
     */
    public static final class SetInstrumentationBreakpointParams extends CdpObject {
        private SetInstrumentationBreakpointParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetInstrumentationBreakpointParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetInstrumentationBreakpointParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Instrumentation name.
         * @return the protocol field value
         */
        @Nullable public String instrumentation() {
            return (String) value("instrumentation");
        }
        /**
         * Instrumentation name.
         */
        public static final class InstrumentationValues {
            private InstrumentationValues() {}
            public static final String BEFORESCRIPTEXECUTION = "beforeScriptExecution";
            public static final String BEFORESCRIPTWITHSOURCEMAPEXECUTION = "beforeScriptWithSourceMapExecution";
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Instrumentation name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder instrumentation(@Nullable String value) {
                if (value == null) values.remove("instrumentation");
                else values.put("instrumentation", jsonValue(value));
                return this;
            }
            public SetInstrumentationBreakpointParams build() {
                if (!values.containsKey("instrumentation")) throw new IllegalStateException("Missing required CDP field: instrumentation");
                return new SetInstrumentationBreakpointParams(values);
            }
        }
    }
    /**
     * Sets instrumentation breakpoint.
     */
    public static final class SetInstrumentationBreakpointResult extends CdpObject {
        private SetInstrumentationBreakpointResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetInstrumentationBreakpointResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetInstrumentationBreakpointResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the created breakpoint for further reference.
         * @return the protocol field value
         */
        @Nullable public String breakpointId() {
            return (String) value("breakpointId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the created breakpoint for further reference.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder breakpointId(@Nullable String value) {
                if (value == null) values.remove("breakpointId");
                else values.put("breakpointId", jsonValue(value));
                return this;
            }
            public SetInstrumentationBreakpointResult build() {
                if (!values.containsKey("breakpointId")) throw new IllegalStateException("Missing required CDP field: breakpointId");
                return new SetInstrumentationBreakpointResult(values);
            }
        }
    }
    /**
     * Sets JavaScript breakpoint at given location specified either by URL or URL regex. Once this command is issued, all existing parsed scripts will have breakpoints resolved and returned in {@code locations} property. Further matching script parsing will result in subsequent {@code breakpointResolved} events issued. This logical breakpoint will survive page reloads.
     */
    public static final class SetBreakpointByUrlParams extends CdpObject {
        private SetBreakpointByUrlParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetBreakpointByUrlParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetBreakpointByUrlParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Line number to set breakpoint at.
         * @return the protocol field value
         */
        @Nullable public Long lineNumber() {
            return numberAsLong(value("lineNumber"));
        }
        /**
         * URL of the resources to set breakpoint on.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * Regex pattern for the URLs of the resources to set breakpoints on. Either {@code url} or {@code urlRegex} must be specified.
         * @return the protocol field value
         */
        @Nullable public String urlRegex() {
            return (String) value("urlRegex");
        }
        /**
         * Script hash of the resources to set breakpoint on.
         * @return the protocol field value
         */
        @Nullable public String scriptHash() {
            return (String) value("scriptHash");
        }
        /**
         * Offset in the line to set breakpoint at.
         * @return the protocol field value
         */
        @Nullable public Long columnNumber() {
            return numberAsLong(value("columnNumber"));
        }
        /**
         * Expression to use as a breakpoint condition. When specified, debugger will only stop on the breakpoint if this expression evaluates to true.
         * @return the protocol field value
         */
        @Nullable public String condition() {
            return (String) value("condition");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Line number to set breakpoint at.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder lineNumber(@Nullable Long value) {
                if (value == null) values.remove("lineNumber");
                else values.put("lineNumber", jsonValue(value));
                return this;
            }
            /**
             * URL of the resources to set breakpoint on.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * Regex pattern for the URLs of the resources to set breakpoints on. Either {@code url} or {@code urlRegex} must be specified.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder urlRegex(@Nullable String value) {
                if (value == null) values.remove("urlRegex");
                else values.put("urlRegex", jsonValue(value));
                return this;
            }
            /**
             * Script hash of the resources to set breakpoint on.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scriptHash(@Nullable String value) {
                if (value == null) values.remove("scriptHash");
                else values.put("scriptHash", jsonValue(value));
                return this;
            }
            /**
             * Offset in the line to set breakpoint at.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder columnNumber(@Nullable Long value) {
                if (value == null) values.remove("columnNumber");
                else values.put("columnNumber", jsonValue(value));
                return this;
            }
            /**
             * Expression to use as a breakpoint condition. When specified, debugger will only stop on the breakpoint if this expression evaluates to true.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder condition(@Nullable String value) {
                if (value == null) values.remove("condition");
                else values.put("condition", jsonValue(value));
                return this;
            }
            public SetBreakpointByUrlParams build() {
                if (!values.containsKey("lineNumber")) throw new IllegalStateException("Missing required CDP field: lineNumber");
                return new SetBreakpointByUrlParams(values);
            }
        }
    }
    /**
     * Sets JavaScript breakpoint at given location specified either by URL or URL regex. Once this command is issued, all existing parsed scripts will have breakpoints resolved and returned in {@code locations} property. Further matching script parsing will result in subsequent {@code breakpointResolved} events issued. This logical breakpoint will survive page reloads.
     */
    public static final class SetBreakpointByUrlResult extends CdpObject {
        private SetBreakpointByUrlResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetBreakpointByUrlResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetBreakpointByUrlResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the created breakpoint for further reference.
         * @return the protocol field value
         */
        @Nullable public String breakpointId() {
            return (String) value("breakpointId");
        }
        /**
         * List of the locations this breakpoint resolved into upon addition.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Debugger.Location> locations() {
            return list(value("locations"), element0 -> Debugger.Location.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the created breakpoint for further reference.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder breakpointId(@Nullable String value) {
                if (value == null) values.remove("breakpointId");
                else values.put("breakpointId", jsonValue(value));
                return this;
            }
            /**
             * List of the locations this breakpoint resolved into upon addition.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder locations(@Nullable java.util.List<Debugger.Location> value) {
                if (value == null) values.remove("locations");
                else values.put("locations", jsonValue(value));
                return this;
            }
            public SetBreakpointByUrlResult build() {
                if (!values.containsKey("breakpointId")) throw new IllegalStateException("Missing required CDP field: breakpointId");
                if (!values.containsKey("locations")) throw new IllegalStateException("Missing required CDP field: locations");
                return new SetBreakpointByUrlResult(values);
            }
        }
    }
    /**
     * Sets JavaScript breakpoint before each call to the given function. If another function was created from the same source as a given one, calling it will also trigger the breakpoint.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetBreakpointOnFunctionCallParams extends CdpObject {
        private SetBreakpointOnFunctionCallParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetBreakpointOnFunctionCallParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetBreakpointOnFunctionCallParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Function object id.
         * @return the protocol field value
         */
        @Nullable public String objectId() {
            return (String) value("objectId");
        }
        /**
         * Expression to use as a breakpoint condition. When specified, debugger will stop on the breakpoint if this expression evaluates to true.
         * @return the protocol field value
         */
        @Nullable public String condition() {
            return (String) value("condition");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Function object id.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder objectId(@Nullable String value) {
                if (value == null) values.remove("objectId");
                else values.put("objectId", jsonValue(value));
                return this;
            }
            /**
             * Expression to use as a breakpoint condition. When specified, debugger will stop on the breakpoint if this expression evaluates to true.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder condition(@Nullable String value) {
                if (value == null) values.remove("condition");
                else values.put("condition", jsonValue(value));
                return this;
            }
            public SetBreakpointOnFunctionCallParams build() {
                if (!values.containsKey("objectId")) throw new IllegalStateException("Missing required CDP field: objectId");
                return new SetBreakpointOnFunctionCallParams(values);
            }
        }
    }
    /**
     * Sets JavaScript breakpoint before each call to the given function. If another function was created from the same source as a given one, calling it will also trigger the breakpoint.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetBreakpointOnFunctionCallResult extends CdpObject {
        private SetBreakpointOnFunctionCallResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetBreakpointOnFunctionCallResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetBreakpointOnFunctionCallResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the created breakpoint for further reference.
         * @return the protocol field value
         */
        @Nullable public String breakpointId() {
            return (String) value("breakpointId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the created breakpoint for further reference.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder breakpointId(@Nullable String value) {
                if (value == null) values.remove("breakpointId");
                else values.put("breakpointId", jsonValue(value));
                return this;
            }
            public SetBreakpointOnFunctionCallResult build() {
                if (!values.containsKey("breakpointId")) throw new IllegalStateException("Missing required CDP field: breakpointId");
                return new SetBreakpointOnFunctionCallResult(values);
            }
        }
    }
    /**
     * Activates / deactivates all breakpoints on the page.
     */
    public static final class SetBreakpointsActiveParams extends CdpObject {
        private SetBreakpointsActiveParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetBreakpointsActiveParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetBreakpointsActiveParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * New value for breakpoints active state.
         * @return the protocol field value
         */
        @Nullable public Boolean active() {
            return (Boolean) value("active");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * New value for breakpoints active state.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder active(@Nullable Boolean value) {
                if (value == null) values.remove("active");
                else values.put("active", jsonValue(value));
                return this;
            }
            public SetBreakpointsActiveParams build() {
                if (!values.containsKey("active")) throw new IllegalStateException("Missing required CDP field: active");
                return new SetBreakpointsActiveParams(values);
            }
        }
    }
    /**
     * Activates / deactivates all breakpoints on the page.
     */
    public static final class SetBreakpointsActiveResult extends CdpObject {
        private SetBreakpointsActiveResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetBreakpointsActiveResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetBreakpointsActiveResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetBreakpointsActiveResult build() {
                return new SetBreakpointsActiveResult(values);
            }
        }
    }
    /**
     * Defines pause on exceptions state. Can be set to stop on all exceptions, uncaught exceptions, or caught exceptions, no exceptions. Initial pause on exceptions state is {@code none}.
     */
    public static final class SetPauseOnExceptionsParams extends CdpObject {
        private SetPauseOnExceptionsParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetPauseOnExceptionsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetPauseOnExceptionsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Pause on exceptions mode.
         * @return the protocol field value
         */
        @Nullable public String state() {
            return (String) value("state");
        }
        /**
         * Pause on exceptions mode.
         */
        public static final class StateValues {
            private StateValues() {}
            public static final String NONE = "none";
            public static final String CAUGHT = "caught";
            public static final String UNCAUGHT = "uncaught";
            public static final String ALL = "all";
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Pause on exceptions mode.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder state(@Nullable String value) {
                if (value == null) values.remove("state");
                else values.put("state", jsonValue(value));
                return this;
            }
            public SetPauseOnExceptionsParams build() {
                if (!values.containsKey("state")) throw new IllegalStateException("Missing required CDP field: state");
                return new SetPauseOnExceptionsParams(values);
            }
        }
    }
    /**
     * Defines pause on exceptions state. Can be set to stop on all exceptions, uncaught exceptions, or caught exceptions, no exceptions. Initial pause on exceptions state is {@code none}.
     */
    public static final class SetPauseOnExceptionsResult extends CdpObject {
        private SetPauseOnExceptionsResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetPauseOnExceptionsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetPauseOnExceptionsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetPauseOnExceptionsResult build() {
                return new SetPauseOnExceptionsResult(values);
            }
        }
    }
    /**
     * Changes return value in top frame. Available only at return break position.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetReturnValueParams extends CdpObject {
        private SetReturnValueParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetReturnValueParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetReturnValueParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * New return value.
         * @return the protocol field value
         */
        @Nullable public Runtime.CallArgument newValue() {
            return Runtime.CallArgument.fromMap(objectMap(value("newValue")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * New return value.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder newValue(@Nullable Runtime.CallArgument value) {
                if (value == null) values.remove("newValue");
                else values.put("newValue", jsonValue(value));
                return this;
            }
            public SetReturnValueParams build() {
                if (!values.containsKey("newValue")) throw new IllegalStateException("Missing required CDP field: newValue");
                return new SetReturnValueParams(values);
            }
        }
    }
    /**
     * Changes return value in top frame. Available only at return break position.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetReturnValueResult extends CdpObject {
        private SetReturnValueResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetReturnValueResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetReturnValueResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetReturnValueResult build() {
                return new SetReturnValueResult(values);
            }
        }
    }
    /**
     * Edits JavaScript source live.
     * <p>In general, functions that are currently on the stack can not be edited with a single exception: If the edited function is the top-most stack frame and that is the only activation of that function on the stack. In this case the live edit will be successful and a {@code Debugger.restartFrame} for the top-most function is automatically triggered.
     */
    public static final class SetScriptSourceParams extends CdpObject {
        private SetScriptSourceParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetScriptSourceParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetScriptSourceParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the script to edit.
         * @return the protocol field value
         */
        @Nullable public String scriptId() {
            return (String) value("scriptId");
        }
        /**
         * New content of the script.
         * @return the protocol field value
         */
        @Nullable public String scriptSource() {
            return (String) value("scriptSource");
        }
        /**
         * If true the change will not actually be applied. Dry run may be used to get result description without actually modifying the code.
         * @return the protocol field value
         */
        @Nullable public Boolean dryRun() {
            return (Boolean) value("dryRun");
        }
        /**
         * If true, then {@code scriptSource} is allowed to change the function on top of the stack as long as the top-most stack frame is the only activation of that function.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean allowTopFrameEditing() {
            return (Boolean) value("allowTopFrameEditing");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the script to edit.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scriptId(@Nullable String value) {
                if (value == null) values.remove("scriptId");
                else values.put("scriptId", jsonValue(value));
                return this;
            }
            /**
             * New content of the script.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scriptSource(@Nullable String value) {
                if (value == null) values.remove("scriptSource");
                else values.put("scriptSource", jsonValue(value));
                return this;
            }
            /**
             * If true the change will not actually be applied. Dry run may be used to get result description without actually modifying the code.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder dryRun(@Nullable Boolean value) {
                if (value == null) values.remove("dryRun");
                else values.put("dryRun", jsonValue(value));
                return this;
            }
            /**
             * If true, then {@code scriptSource} is allowed to change the function on top of the stack as long as the top-most stack frame is the only activation of that function.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder allowTopFrameEditing(@Nullable Boolean value) {
                if (value == null) values.remove("allowTopFrameEditing");
                else values.put("allowTopFrameEditing", jsonValue(value));
                return this;
            }
            public SetScriptSourceParams build() {
                if (!values.containsKey("scriptId")) throw new IllegalStateException("Missing required CDP field: scriptId");
                if (!values.containsKey("scriptSource")) throw new IllegalStateException("Missing required CDP field: scriptSource");
                return new SetScriptSourceParams(values);
            }
        }
    }
    /**
     * Edits JavaScript source live.
     * <p>In general, functions that are currently on the stack can not be edited with a single exception: If the edited function is the top-most stack frame and that is the only activation of that function on the stack. In this case the live edit will be successful and a {@code Debugger.restartFrame} for the top-most function is automatically triggered.
     */
    public static final class SetScriptSourceResult extends CdpObject {
        private SetScriptSourceResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetScriptSourceResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetScriptSourceResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * New stack trace in case editing has happened while VM was stopped.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        @Nullable public java.util.List<Debugger.CallFrame> callFrames() {
            return list(value("callFrames"), element0 -> Debugger.CallFrame.fromMap(objectMap(element0)));
        }
        /**
         * Whether current call stack was modified after applying the changes.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        @Nullable public Boolean stackChanged() {
            return (Boolean) value("stackChanged");
        }
        /**
         * Async stack trace, if any.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        @Nullable public Runtime.StackTrace asyncStackTrace() {
            return Runtime.StackTrace.fromMap(objectMap(value("asyncStackTrace")));
        }
        /**
         * Async stack trace, if any.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        @Nullable public Runtime.StackTraceId asyncStackTraceId() {
            return Runtime.StackTraceId.fromMap(objectMap(value("asyncStackTraceId")));
        }
        /**
         * Whether the operation was successful or not. Only {@code Ok} denotes a successful live edit while the other enum variants denote why the live edit failed.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String status() {
            return (String) value("status");
        }
        /**
         * Whether the operation was successful or not. Only {@code Ok} denotes a successful live edit while the other enum variants denote why the live edit failed.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         */
        public static final class StatusValues {
            private StatusValues() {}
            public static final String OK = "Ok";
            public static final String COMPILEERROR = "CompileError";
            public static final String BLOCKEDBYACTIVEGENERATOR = "BlockedByActiveGenerator";
            public static final String BLOCKEDBYACTIVEFUNCTION = "BlockedByActiveFunction";
            public static final String BLOCKEDBYTOPLEVELESMODULECHANGE = "BlockedByTopLevelEsModuleChange";
        }
        /**
         * Exception details if any. Only present when {@code status} is {@code CompileError}.
         * @return the protocol field value
         */
        @Nullable public Runtime.ExceptionDetails exceptionDetails() {
            return Runtime.ExceptionDetails.fromMap(objectMap(value("exceptionDetails")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * New stack trace in case editing has happened while VM was stopped.
             * @param value field value; null removes an optional value
             * @return this builder
             * @deprecated Deprecated by the Chromium DevTools Protocol.
             */
            @Deprecated
            public Builder callFrames(@Nullable java.util.List<Debugger.CallFrame> value) {
                if (value == null) values.remove("callFrames");
                else values.put("callFrames", jsonValue(value));
                return this;
            }
            /**
             * Whether current call stack was modified after applying the changes.
             * @param value field value; null removes an optional value
             * @return this builder
             * @deprecated Deprecated by the Chromium DevTools Protocol.
             */
            @Deprecated
            public Builder stackChanged(@Nullable Boolean value) {
                if (value == null) values.remove("stackChanged");
                else values.put("stackChanged", jsonValue(value));
                return this;
            }
            /**
             * Async stack trace, if any.
             * @param value field value; null removes an optional value
             * @return this builder
             * @deprecated Deprecated by the Chromium DevTools Protocol.
             */
            @Deprecated
            public Builder asyncStackTrace(@Nullable Runtime.StackTrace value) {
                if (value == null) values.remove("asyncStackTrace");
                else values.put("asyncStackTrace", jsonValue(value));
                return this;
            }
            /**
             * Async stack trace, if any.
             * @param value field value; null removes an optional value
             * @return this builder
             * @deprecated Deprecated by the Chromium DevTools Protocol.
             */
            @Deprecated
            public Builder asyncStackTraceId(@Nullable Runtime.StackTraceId value) {
                if (value == null) values.remove("asyncStackTraceId");
                else values.put("asyncStackTraceId", jsonValue(value));
                return this;
            }
            /**
             * Whether the operation was successful or not. Only {@code Ok} denotes a successful live edit while the other enum variants denote why the live edit failed.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder status(@Nullable String value) {
                if (value == null) values.remove("status");
                else values.put("status", jsonValue(value));
                return this;
            }
            /**
             * Exception details if any. Only present when {@code status} is {@code CompileError}.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder exceptionDetails(@Nullable Runtime.ExceptionDetails value) {
                if (value == null) values.remove("exceptionDetails");
                else values.put("exceptionDetails", jsonValue(value));
                return this;
            }
            public SetScriptSourceResult build() {
                if (!values.containsKey("status")) throw new IllegalStateException("Missing required CDP field: status");
                return new SetScriptSourceResult(values);
            }
        }
    }
    /**
     * Makes page not interrupt on any pauses (breakpoint, exception, dom exception etc).
     */
    public static final class SetSkipAllPausesParams extends CdpObject {
        private SetSkipAllPausesParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetSkipAllPausesParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetSkipAllPausesParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * New value for skip pauses state.
         * @return the protocol field value
         */
        @Nullable public Boolean skip() {
            return (Boolean) value("skip");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * New value for skip pauses state.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder skip(@Nullable Boolean value) {
                if (value == null) values.remove("skip");
                else values.put("skip", jsonValue(value));
                return this;
            }
            public SetSkipAllPausesParams build() {
                if (!values.containsKey("skip")) throw new IllegalStateException("Missing required CDP field: skip");
                return new SetSkipAllPausesParams(values);
            }
        }
    }
    /**
     * Makes page not interrupt on any pauses (breakpoint, exception, dom exception etc).
     */
    public static final class SetSkipAllPausesResult extends CdpObject {
        private SetSkipAllPausesResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetSkipAllPausesResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetSkipAllPausesResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetSkipAllPausesResult build() {
                return new SetSkipAllPausesResult(values);
            }
        }
    }
    /**
     * Changes value of variable in a callframe. Object-based scopes are not supported and must be mutated manually.
     */
    public static final class SetVariableValueParams extends CdpObject {
        private SetVariableValueParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetVariableValueParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetVariableValueParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * 0-based number of scope as was listed in scope chain. Only &#x27;local&#x27;, &#x27;closure&#x27; and &#x27;catch&#x27; scope types are allowed. Other scopes could be manipulated manually.
         * @return the protocol field value
         */
        @Nullable public Long scopeNumber() {
            return numberAsLong(value("scopeNumber"));
        }
        /**
         * Variable name.
         * @return the protocol field value
         */
        @Nullable public String variableName() {
            return (String) value("variableName");
        }
        /**
         * New variable value.
         * @return the protocol field value
         */
        @Nullable public Runtime.CallArgument newValue() {
            return Runtime.CallArgument.fromMap(objectMap(value("newValue")));
        }
        /**
         * Id of callframe that holds variable.
         * @return the protocol field value
         */
        @Nullable public String callFrameId() {
            return (String) value("callFrameId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * 0-based number of scope as was listed in scope chain. Only &#x27;local&#x27;, &#x27;closure&#x27; and &#x27;catch&#x27; scope types are allowed. Other scopes could be manipulated manually.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scopeNumber(@Nullable Long value) {
                if (value == null) values.remove("scopeNumber");
                else values.put("scopeNumber", jsonValue(value));
                return this;
            }
            /**
             * Variable name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder variableName(@Nullable String value) {
                if (value == null) values.remove("variableName");
                else values.put("variableName", jsonValue(value));
                return this;
            }
            /**
             * New variable value.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder newValue(@Nullable Runtime.CallArgument value) {
                if (value == null) values.remove("newValue");
                else values.put("newValue", jsonValue(value));
                return this;
            }
            /**
             * Id of callframe that holds variable.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder callFrameId(@Nullable String value) {
                if (value == null) values.remove("callFrameId");
                else values.put("callFrameId", jsonValue(value));
                return this;
            }
            public SetVariableValueParams build() {
                if (!values.containsKey("scopeNumber")) throw new IllegalStateException("Missing required CDP field: scopeNumber");
                if (!values.containsKey("variableName")) throw new IllegalStateException("Missing required CDP field: variableName");
                if (!values.containsKey("newValue")) throw new IllegalStateException("Missing required CDP field: newValue");
                if (!values.containsKey("callFrameId")) throw new IllegalStateException("Missing required CDP field: callFrameId");
                return new SetVariableValueParams(values);
            }
        }
    }
    /**
     * Changes value of variable in a callframe. Object-based scopes are not supported and must be mutated manually.
     */
    public static final class SetVariableValueResult extends CdpObject {
        private SetVariableValueResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetVariableValueResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetVariableValueResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetVariableValueResult build() {
                return new SetVariableValueResult(values);
            }
        }
    }
    /**
     * Steps into the function call.
     */
    public static final class StepIntoParams extends CdpObject {
        private StepIntoParams(Map<String, Object> values) { super(values); }
        @Nullable public static StepIntoParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StepIntoParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Debugger will pause on the execution of the first async task which was scheduled before next pause.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean breakOnAsyncCall() {
            return (Boolean) value("breakOnAsyncCall");
        }
        /**
         * The skipList specifies location ranges that should be skipped on step into.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Debugger.LocationRange> skipList() {
            return list(value("skipList"), element0 -> Debugger.LocationRange.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Debugger will pause on the execution of the first async task which was scheduled before next pause.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder breakOnAsyncCall(@Nullable Boolean value) {
                if (value == null) values.remove("breakOnAsyncCall");
                else values.put("breakOnAsyncCall", jsonValue(value));
                return this;
            }
            /**
             * The skipList specifies location ranges that should be skipped on step into.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder skipList(@Nullable java.util.List<Debugger.LocationRange> value) {
                if (value == null) values.remove("skipList");
                else values.put("skipList", jsonValue(value));
                return this;
            }
            public StepIntoParams build() {
                return new StepIntoParams(values);
            }
        }
    }
    /**
     * Steps into the function call.
     */
    public static final class StepIntoResult extends CdpObject {
        private StepIntoResult(Map<String, Object> values) { super(values); }
        @Nullable public static StepIntoResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StepIntoResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public StepIntoResult build() {
                return new StepIntoResult(values);
            }
        }
    }
    /**
     * Steps out of the function call.
     */
    public static final class StepOutParams extends CdpObject {
        private StepOutParams(Map<String, Object> values) { super(values); }
        @Nullable public static StepOutParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StepOutParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public StepOutParams build() {
                return new StepOutParams(values);
            }
        }
    }
    /**
     * Steps out of the function call.
     */
    public static final class StepOutResult extends CdpObject {
        private StepOutResult(Map<String, Object> values) { super(values); }
        @Nullable public static StepOutResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StepOutResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public StepOutResult build() {
                return new StepOutResult(values);
            }
        }
    }
    /**
     * Steps over the statement.
     */
    public static final class StepOverParams extends CdpObject {
        private StepOverParams(Map<String, Object> values) { super(values); }
        @Nullable public static StepOverParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StepOverParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The skipList specifies location ranges that should be skipped on step over.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Debugger.LocationRange> skipList() {
            return list(value("skipList"), element0 -> Debugger.LocationRange.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The skipList specifies location ranges that should be skipped on step over.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder skipList(@Nullable java.util.List<Debugger.LocationRange> value) {
                if (value == null) values.remove("skipList");
                else values.put("skipList", jsonValue(value));
                return this;
            }
            public StepOverParams build() {
                return new StepOverParams(values);
            }
        }
    }
    /**
     * Steps over the statement.
     */
    public static final class StepOverResult extends CdpObject {
        private StepOverResult(Map<String, Object> values) { super(values); }
        @Nullable public static StepOverResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StepOverResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public StepOverResult build() {
                return new StepOverResult(values);
            }
        }
    }
    /**
     * Fired when breakpoint is resolved to an actual script and location. Deprecated in favor of {@code resolvedBreakpoints} in the {@code scriptParsed} event.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class BreakpointResolvedEvent extends CdpObject {
        private BreakpointResolvedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static BreakpointResolvedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new BreakpointResolvedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Breakpoint unique identifier.
         * @return the protocol field value
         */
        @Nullable public String breakpointId() {
            return (String) value("breakpointId");
        }
        /**
         * Actual breakpoint location.
         * @return the protocol field value
         */
        @Nullable public Debugger.Location location() {
            return Debugger.Location.fromMap(objectMap(value("location")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Breakpoint unique identifier.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder breakpointId(@Nullable String value) {
                if (value == null) values.remove("breakpointId");
                else values.put("breakpointId", jsonValue(value));
                return this;
            }
            /**
             * Actual breakpoint location.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder location(@Nullable Debugger.Location value) {
                if (value == null) values.remove("location");
                else values.put("location", jsonValue(value));
                return this;
            }
            public BreakpointResolvedEvent build() {
                if (!values.containsKey("breakpointId")) throw new IllegalStateException("Missing required CDP field: breakpointId");
                if (!values.containsKey("location")) throw new IllegalStateException("Missing required CDP field: location");
                return new BreakpointResolvedEvent(values);
            }
        }
    }
    /**
     * Fired when the virtual machine stopped on breakpoint or exception or any other stop criteria.
     */
    public static final class PausedEvent extends CdpObject {
        private PausedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static PausedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PausedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Call stack the virtual machine stopped on.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Debugger.CallFrame> callFrames() {
            return list(value("callFrames"), element0 -> Debugger.CallFrame.fromMap(objectMap(element0)));
        }
        /**
         * Pause reason.
         * @return the protocol field value
         */
        @Nullable public String reason() {
            return (String) value("reason");
        }
        /**
         * Pause reason.
         */
        public static final class ReasonValues {
            private ReasonValues() {}
            public static final String AMBIGUOUS = "ambiguous";
            public static final String ASSERT = "assert";
            public static final String CSPVIOLATION = "CSPViolation";
            public static final String DEBUGCOMMAND = "debugCommand";
            public static final String DOM = "DOM";
            public static final String EVENTLISTENER = "EventListener";
            public static final String EXCEPTION = "exception";
            public static final String INSTRUMENTATION = "instrumentation";
            public static final String OOM = "OOM";
            public static final String OTHER = "other";
            public static final String PROMISEREJECTION = "promiseRejection";
            public static final String XHR = "XHR";
            public static final String STEP = "step";
        }
        /**
         * Object containing break-specific auxiliary properties.
         * @return the protocol field value
         */
        @Nullable public java.util.Map<String, Object> data() {
            return objectMap(value("data"));
        }
        /**
         * Hit breakpoints IDs
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> hitBreakpoints() {
            return list(value("hitBreakpoints"), element0 -> (String) element0);
        }
        /**
         * Async stack trace, if any.
         * @return the protocol field value
         */
        @Nullable public Runtime.StackTrace asyncStackTrace() {
            return Runtime.StackTrace.fromMap(objectMap(value("asyncStackTrace")));
        }
        /**
         * Async stack trace, if any.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Runtime.StackTraceId asyncStackTraceId() {
            return Runtime.StackTraceId.fromMap(objectMap(value("asyncStackTraceId")));
        }
        /**
         * Never present, will be removed.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        @Nullable public Runtime.StackTraceId asyncCallStackTraceId() {
            return Runtime.StackTraceId.fromMap(objectMap(value("asyncCallStackTraceId")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Call stack the virtual machine stopped on.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder callFrames(@Nullable java.util.List<Debugger.CallFrame> value) {
                if (value == null) values.remove("callFrames");
                else values.put("callFrames", jsonValue(value));
                return this;
            }
            /**
             * Pause reason.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder reason(@Nullable String value) {
                if (value == null) values.remove("reason");
                else values.put("reason", jsonValue(value));
                return this;
            }
            /**
             * Object containing break-specific auxiliary properties.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder data(@Nullable java.util.Map<String, Object> value) {
                if (value == null) values.remove("data");
                else values.put("data", jsonValue(value));
                return this;
            }
            /**
             * Hit breakpoints IDs
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder hitBreakpoints(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("hitBreakpoints");
                else values.put("hitBreakpoints", jsonValue(value));
                return this;
            }
            /**
             * Async stack trace, if any.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder asyncStackTrace(@Nullable Runtime.StackTrace value) {
                if (value == null) values.remove("asyncStackTrace");
                else values.put("asyncStackTrace", jsonValue(value));
                return this;
            }
            /**
             * Async stack trace, if any.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder asyncStackTraceId(@Nullable Runtime.StackTraceId value) {
                if (value == null) values.remove("asyncStackTraceId");
                else values.put("asyncStackTraceId", jsonValue(value));
                return this;
            }
            /**
             * Never present, will be removed.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             * @deprecated Deprecated by the Chromium DevTools Protocol.
             */
            @Deprecated
            public Builder asyncCallStackTraceId(@Nullable Runtime.StackTraceId value) {
                if (value == null) values.remove("asyncCallStackTraceId");
                else values.put("asyncCallStackTraceId", jsonValue(value));
                return this;
            }
            public PausedEvent build() {
                if (!values.containsKey("callFrames")) throw new IllegalStateException("Missing required CDP field: callFrames");
                if (!values.containsKey("reason")) throw new IllegalStateException("Missing required CDP field: reason");
                return new PausedEvent(values);
            }
        }
    }
    /**
     * Fired when the virtual machine resumed execution.
     */
    public static final class ResumedEvent extends CdpObject {
        private ResumedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static ResumedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ResumedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ResumedEvent build() {
                return new ResumedEvent(values);
            }
        }
    }
    /**
     * Fired when virtual machine fails to parse the script.
     */
    public static final class ScriptFailedToParseEvent extends CdpObject {
        private ScriptFailedToParseEvent(Map<String, Object> values) { super(values); }
        @Nullable public static ScriptFailedToParseEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ScriptFailedToParseEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Identifier of the script parsed.
         * @return the protocol field value
         */
        @Nullable public String scriptId() {
            return (String) value("scriptId");
        }
        /**
         * URL or name of the script parsed (if any).
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * Line offset of the script within the resource with given URL (for script tags).
         * @return the protocol field value
         */
        @Nullable public Long startLine() {
            return numberAsLong(value("startLine"));
        }
        /**
         * Column offset of the script within the resource with given URL.
         * @return the protocol field value
         */
        @Nullable public Long startColumn() {
            return numberAsLong(value("startColumn"));
        }
        /**
         * Last line of the script.
         * @return the protocol field value
         */
        @Nullable public Long endLine() {
            return numberAsLong(value("endLine"));
        }
        /**
         * Length of the last line of the script.
         * @return the protocol field value
         */
        @Nullable public Long endColumn() {
            return numberAsLong(value("endColumn"));
        }
        /**
         * Specifies script creation context.
         * @return the protocol field value
         */
        @Nullable public Long executionContextId() {
            return numberAsLong(value("executionContextId"));
        }
        /**
         * Content hash of the script, SHA-256.
         * @return the protocol field value
         */
        @Nullable public String hash() {
            return (String) value("hash");
        }
        /**
         * For Wasm modules, the content of the {@code build_id} custom section. For JavaScript the {@code debugId} magic comment.
         * @return the protocol field value
         */
        @Nullable public String buildId() {
            return (String) value("buildId");
        }
        /**
         * Embedder-specific auxiliary data likely matching {isDefault: boolean, type: &#x27;default&#x27;|&#x27;isolated&#x27;|&#x27;worker&#x27;, frameId: string}
         * @return the protocol field value
         */
        @Nullable public java.util.Map<String, Object> executionContextAuxData() {
            return objectMap(value("executionContextAuxData"));
        }
        /**
         * URL of source map associated with script (if any).
         * @return the protocol field value
         */
        @Nullable public String sourceMapURL() {
            return (String) value("sourceMapURL");
        }
        /**
         * True, if this script has sourceURL.
         * @return the protocol field value
         */
        @Nullable public Boolean hasSourceURL() {
            return (Boolean) value("hasSourceURL");
        }
        /**
         * True, if this script is ES6 module.
         * @return the protocol field value
         */
        @Nullable public Boolean isModule() {
            return (Boolean) value("isModule");
        }
        /**
         * This script length.
         * @return the protocol field value
         */
        @Nullable public Long length() {
            return numberAsLong(value("length"));
        }
        /**
         * JavaScript top stack frame of where the script parsed event was triggered if available.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Runtime.StackTrace stackTrace() {
            return Runtime.StackTrace.fromMap(objectMap(value("stackTrace")));
        }
        /**
         * If the scriptLanguage is WebAssembly, the code section offset in the module.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Long codeOffset() {
            return numberAsLong(value("codeOffset"));
        }
        /**
         * The language of the script.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String scriptLanguage() {
            return (String) value("scriptLanguage");
        }
        /**
         * The name the embedder supplied for this script.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String embedderName() {
            return (String) value("embedderName");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Identifier of the script parsed.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scriptId(@Nullable String value) {
                if (value == null) values.remove("scriptId");
                else values.put("scriptId", jsonValue(value));
                return this;
            }
            /**
             * URL or name of the script parsed (if any).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * Line offset of the script within the resource with given URL (for script tags).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder startLine(@Nullable Long value) {
                if (value == null) values.remove("startLine");
                else values.put("startLine", jsonValue(value));
                return this;
            }
            /**
             * Column offset of the script within the resource with given URL.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder startColumn(@Nullable Long value) {
                if (value == null) values.remove("startColumn");
                else values.put("startColumn", jsonValue(value));
                return this;
            }
            /**
             * Last line of the script.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder endLine(@Nullable Long value) {
                if (value == null) values.remove("endLine");
                else values.put("endLine", jsonValue(value));
                return this;
            }
            /**
             * Length of the last line of the script.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder endColumn(@Nullable Long value) {
                if (value == null) values.remove("endColumn");
                else values.put("endColumn", jsonValue(value));
                return this;
            }
            /**
             * Specifies script creation context.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder executionContextId(@Nullable Long value) {
                if (value == null) values.remove("executionContextId");
                else values.put("executionContextId", jsonValue(value));
                return this;
            }
            /**
             * Content hash of the script, SHA-256.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder hash(@Nullable String value) {
                if (value == null) values.remove("hash");
                else values.put("hash", jsonValue(value));
                return this;
            }
            /**
             * For Wasm modules, the content of the {@code build_id} custom section. For JavaScript the {@code debugId} magic comment.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder buildId(@Nullable String value) {
                if (value == null) values.remove("buildId");
                else values.put("buildId", jsonValue(value));
                return this;
            }
            /**
             * Embedder-specific auxiliary data likely matching {isDefault: boolean, type: &#x27;default&#x27;|&#x27;isolated&#x27;|&#x27;worker&#x27;, frameId: string}
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder executionContextAuxData(@Nullable java.util.Map<String, Object> value) {
                if (value == null) values.remove("executionContextAuxData");
                else values.put("executionContextAuxData", jsonValue(value));
                return this;
            }
            /**
             * URL of source map associated with script (if any).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sourceMapURL(@Nullable String value) {
                if (value == null) values.remove("sourceMapURL");
                else values.put("sourceMapURL", jsonValue(value));
                return this;
            }
            /**
             * True, if this script has sourceURL.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder hasSourceURL(@Nullable Boolean value) {
                if (value == null) values.remove("hasSourceURL");
                else values.put("hasSourceURL", jsonValue(value));
                return this;
            }
            /**
             * True, if this script is ES6 module.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isModule(@Nullable Boolean value) {
                if (value == null) values.remove("isModule");
                else values.put("isModule", jsonValue(value));
                return this;
            }
            /**
             * This script length.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder length(@Nullable Long value) {
                if (value == null) values.remove("length");
                else values.put("length", jsonValue(value));
                return this;
            }
            /**
             * JavaScript top stack frame of where the script parsed event was triggered if available.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder stackTrace(@Nullable Runtime.StackTrace value) {
                if (value == null) values.remove("stackTrace");
                else values.put("stackTrace", jsonValue(value));
                return this;
            }
            /**
             * If the scriptLanguage is WebAssembly, the code section offset in the module.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder codeOffset(@Nullable Long value) {
                if (value == null) values.remove("codeOffset");
                else values.put("codeOffset", jsonValue(value));
                return this;
            }
            /**
             * The language of the script.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scriptLanguage(@Nullable String value) {
                if (value == null) values.remove("scriptLanguage");
                else values.put("scriptLanguage", jsonValue(value));
                return this;
            }
            /**
             * The name the embedder supplied for this script.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder embedderName(@Nullable String value) {
                if (value == null) values.remove("embedderName");
                else values.put("embedderName", jsonValue(value));
                return this;
            }
            public ScriptFailedToParseEvent build() {
                if (!values.containsKey("scriptId")) throw new IllegalStateException("Missing required CDP field: scriptId");
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                if (!values.containsKey("startLine")) throw new IllegalStateException("Missing required CDP field: startLine");
                if (!values.containsKey("startColumn")) throw new IllegalStateException("Missing required CDP field: startColumn");
                if (!values.containsKey("endLine")) throw new IllegalStateException("Missing required CDP field: endLine");
                if (!values.containsKey("endColumn")) throw new IllegalStateException("Missing required CDP field: endColumn");
                if (!values.containsKey("executionContextId")) throw new IllegalStateException("Missing required CDP field: executionContextId");
                if (!values.containsKey("hash")) throw new IllegalStateException("Missing required CDP field: hash");
                if (!values.containsKey("buildId")) throw new IllegalStateException("Missing required CDP field: buildId");
                return new ScriptFailedToParseEvent(values);
            }
        }
    }
    /**
     * Fired when virtual machine parses script. This event is also fired for all known and uncollected scripts upon enabling debugger.
     */
    public static final class ScriptParsedEvent extends CdpObject {
        private ScriptParsedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static ScriptParsedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ScriptParsedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Identifier of the script parsed.
         * @return the protocol field value
         */
        @Nullable public String scriptId() {
            return (String) value("scriptId");
        }
        /**
         * URL or name of the script parsed (if any).
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * Line offset of the script within the resource with given URL (for script tags).
         * @return the protocol field value
         */
        @Nullable public Long startLine() {
            return numberAsLong(value("startLine"));
        }
        /**
         * Column offset of the script within the resource with given URL.
         * @return the protocol field value
         */
        @Nullable public Long startColumn() {
            return numberAsLong(value("startColumn"));
        }
        /**
         * Last line of the script.
         * @return the protocol field value
         */
        @Nullable public Long endLine() {
            return numberAsLong(value("endLine"));
        }
        /**
         * Length of the last line of the script.
         * @return the protocol field value
         */
        @Nullable public Long endColumn() {
            return numberAsLong(value("endColumn"));
        }
        /**
         * Specifies script creation context.
         * @return the protocol field value
         */
        @Nullable public Long executionContextId() {
            return numberAsLong(value("executionContextId"));
        }
        /**
         * Content hash of the script, SHA-256.
         * @return the protocol field value
         */
        @Nullable public String hash() {
            return (String) value("hash");
        }
        /**
         * For Wasm modules, the content of the {@code build_id} custom section. For JavaScript the {@code debugId} magic comment.
         * @return the protocol field value
         */
        @Nullable public String buildId() {
            return (String) value("buildId");
        }
        /**
         * Embedder-specific auxiliary data likely matching {isDefault: boolean, type: &#x27;default&#x27;|&#x27;isolated&#x27;|&#x27;worker&#x27;, frameId: string}
         * @return the protocol field value
         */
        @Nullable public java.util.Map<String, Object> executionContextAuxData() {
            return objectMap(value("executionContextAuxData"));
        }
        /**
         * True, if this script is generated as a result of the live edit operation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean isLiveEdit() {
            return (Boolean) value("isLiveEdit");
        }
        /**
         * URL of source map associated with script (if any).
         * @return the protocol field value
         */
        @Nullable public String sourceMapURL() {
            return (String) value("sourceMapURL");
        }
        /**
         * True, if this script has sourceURL.
         * @return the protocol field value
         */
        @Nullable public Boolean hasSourceURL() {
            return (Boolean) value("hasSourceURL");
        }
        /**
         * True, if this script is ES6 module.
         * @return the protocol field value
         */
        @Nullable public Boolean isModule() {
            return (Boolean) value("isModule");
        }
        /**
         * This script length.
         * @return the protocol field value
         */
        @Nullable public Long length() {
            return numberAsLong(value("length"));
        }
        /**
         * JavaScript top stack frame of where the script parsed event was triggered if available.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Runtime.StackTrace stackTrace() {
            return Runtime.StackTrace.fromMap(objectMap(value("stackTrace")));
        }
        /**
         * If the scriptLanguage is WebAssembly, the code section offset in the module.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Long codeOffset() {
            return numberAsLong(value("codeOffset"));
        }
        /**
         * The language of the script.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String scriptLanguage() {
            return (String) value("scriptLanguage");
        }
        /**
         * If the scriptLanguage is WebAssembly, the source of debug symbols for the module.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Debugger.DebugSymbols> debugSymbols() {
            return list(value("debugSymbols"), element0 -> Debugger.DebugSymbols.fromMap(objectMap(element0)));
        }
        /**
         * The name the embedder supplied for this script.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String embedderName() {
            return (String) value("embedderName");
        }
        /**
         * The list of set breakpoints in this script if calls to {@code setBreakpointByUrl} matches this script&#x27;s URL or hash. Clients that use this list can ignore the {@code breakpointResolved} event. They are equivalent.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Debugger.ResolvedBreakpoint> resolvedBreakpoints() {
            return list(value("resolvedBreakpoints"), element0 -> Debugger.ResolvedBreakpoint.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Identifier of the script parsed.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scriptId(@Nullable String value) {
                if (value == null) values.remove("scriptId");
                else values.put("scriptId", jsonValue(value));
                return this;
            }
            /**
             * URL or name of the script parsed (if any).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * Line offset of the script within the resource with given URL (for script tags).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder startLine(@Nullable Long value) {
                if (value == null) values.remove("startLine");
                else values.put("startLine", jsonValue(value));
                return this;
            }
            /**
             * Column offset of the script within the resource with given URL.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder startColumn(@Nullable Long value) {
                if (value == null) values.remove("startColumn");
                else values.put("startColumn", jsonValue(value));
                return this;
            }
            /**
             * Last line of the script.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder endLine(@Nullable Long value) {
                if (value == null) values.remove("endLine");
                else values.put("endLine", jsonValue(value));
                return this;
            }
            /**
             * Length of the last line of the script.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder endColumn(@Nullable Long value) {
                if (value == null) values.remove("endColumn");
                else values.put("endColumn", jsonValue(value));
                return this;
            }
            /**
             * Specifies script creation context.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder executionContextId(@Nullable Long value) {
                if (value == null) values.remove("executionContextId");
                else values.put("executionContextId", jsonValue(value));
                return this;
            }
            /**
             * Content hash of the script, SHA-256.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder hash(@Nullable String value) {
                if (value == null) values.remove("hash");
                else values.put("hash", jsonValue(value));
                return this;
            }
            /**
             * For Wasm modules, the content of the {@code build_id} custom section. For JavaScript the {@code debugId} magic comment.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder buildId(@Nullable String value) {
                if (value == null) values.remove("buildId");
                else values.put("buildId", jsonValue(value));
                return this;
            }
            /**
             * Embedder-specific auxiliary data likely matching {isDefault: boolean, type: &#x27;default&#x27;|&#x27;isolated&#x27;|&#x27;worker&#x27;, frameId: string}
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder executionContextAuxData(@Nullable java.util.Map<String, Object> value) {
                if (value == null) values.remove("executionContextAuxData");
                else values.put("executionContextAuxData", jsonValue(value));
                return this;
            }
            /**
             * True, if this script is generated as a result of the live edit operation.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isLiveEdit(@Nullable Boolean value) {
                if (value == null) values.remove("isLiveEdit");
                else values.put("isLiveEdit", jsonValue(value));
                return this;
            }
            /**
             * URL of source map associated with script (if any).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sourceMapURL(@Nullable String value) {
                if (value == null) values.remove("sourceMapURL");
                else values.put("sourceMapURL", jsonValue(value));
                return this;
            }
            /**
             * True, if this script has sourceURL.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder hasSourceURL(@Nullable Boolean value) {
                if (value == null) values.remove("hasSourceURL");
                else values.put("hasSourceURL", jsonValue(value));
                return this;
            }
            /**
             * True, if this script is ES6 module.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isModule(@Nullable Boolean value) {
                if (value == null) values.remove("isModule");
                else values.put("isModule", jsonValue(value));
                return this;
            }
            /**
             * This script length.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder length(@Nullable Long value) {
                if (value == null) values.remove("length");
                else values.put("length", jsonValue(value));
                return this;
            }
            /**
             * JavaScript top stack frame of where the script parsed event was triggered if available.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder stackTrace(@Nullable Runtime.StackTrace value) {
                if (value == null) values.remove("stackTrace");
                else values.put("stackTrace", jsonValue(value));
                return this;
            }
            /**
             * If the scriptLanguage is WebAssembly, the code section offset in the module.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder codeOffset(@Nullable Long value) {
                if (value == null) values.remove("codeOffset");
                else values.put("codeOffset", jsonValue(value));
                return this;
            }
            /**
             * The language of the script.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scriptLanguage(@Nullable String value) {
                if (value == null) values.remove("scriptLanguage");
                else values.put("scriptLanguage", jsonValue(value));
                return this;
            }
            /**
             * If the scriptLanguage is WebAssembly, the source of debug symbols for the module.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder debugSymbols(@Nullable java.util.List<Debugger.DebugSymbols> value) {
                if (value == null) values.remove("debugSymbols");
                else values.put("debugSymbols", jsonValue(value));
                return this;
            }
            /**
             * The name the embedder supplied for this script.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder embedderName(@Nullable String value) {
                if (value == null) values.remove("embedderName");
                else values.put("embedderName", jsonValue(value));
                return this;
            }
            /**
             * The list of set breakpoints in this script if calls to {@code setBreakpointByUrl} matches this script&#x27;s URL or hash. Clients that use this list can ignore the {@code breakpointResolved} event. They are equivalent.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder resolvedBreakpoints(@Nullable java.util.List<Debugger.ResolvedBreakpoint> value) {
                if (value == null) values.remove("resolvedBreakpoints");
                else values.put("resolvedBreakpoints", jsonValue(value));
                return this;
            }
            public ScriptParsedEvent build() {
                if (!values.containsKey("scriptId")) throw new IllegalStateException("Missing required CDP field: scriptId");
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                if (!values.containsKey("startLine")) throw new IllegalStateException("Missing required CDP field: startLine");
                if (!values.containsKey("startColumn")) throw new IllegalStateException("Missing required CDP field: startColumn");
                if (!values.containsKey("endLine")) throw new IllegalStateException("Missing required CDP field: endLine");
                if (!values.containsKey("endColumn")) throw new IllegalStateException("Missing required CDP field: endColumn");
                if (!values.containsKey("executionContextId")) throw new IllegalStateException("Missing required CDP field: executionContextId");
                if (!values.containsKey("hash")) throw new IllegalStateException("Missing required CDP field: hash");
                if (!values.containsKey("buildId")) throw new IllegalStateException("Missing required CDP field: buildId");
                return new ScriptParsedEvent(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Continues execution until specific location is reached.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ContinueToLocationResult> continueToLocation(ContinueToLocationParams params) {
            return client.call("Debugger.continueToLocation", params, ContinueToLocationResult::fromMap);
        }
        /**
         * Disables debugger for given page.
         * @return a stage completing with the command result
         */
        public CompletionStage<DisableResult> disable() {
            return client.call("Debugger.disable", null, DisableResult::fromMap);
        }
        /**
         * Enables debugger for the given page. Clients should not assume that the debugging has been enabled until the result for this command is received.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<EnableResult> enable(EnableParams params) {
            return client.call("Debugger.enable", params, EnableResult::fromMap);
        }
        /**
         * Evaluates expression on a given call frame.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<EvaluateOnCallFrameResult> evaluateOnCallFrame(EvaluateOnCallFrameParams params) {
            return client.call("Debugger.evaluateOnCallFrame", params, EvaluateOnCallFrameResult::fromMap);
        }
        /**
         * Returns possible locations for breakpoint. scriptId in start and end range locations should be the same.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetPossibleBreakpointsResult> getPossibleBreakpoints(GetPossibleBreakpointsParams params) {
            return client.call("Debugger.getPossibleBreakpoints", params, GetPossibleBreakpointsResult::fromMap);
        }
        /**
         * Returns source for the script with given id.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetScriptSourceResult> getScriptSource(GetScriptSourceParams params) {
            return client.call("Debugger.getScriptSource", params, GetScriptSourceResult::fromMap);
        }
        /**
         * Invokes Debugger.disassembleWasmModule.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DisassembleWasmModuleResult> disassembleWasmModule(DisassembleWasmModuleParams params) {
            return client.call("Debugger.disassembleWasmModule", params, DisassembleWasmModuleResult::fromMap);
        }
        /**
         * Disassemble the next chunk of lines for the module corresponding to the stream. If disassembly is complete, this API will invalidate the streamId and return an empty chunk. Any subsequent calls for the now invalid stream will return errors.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<NextWasmDisassemblyChunkResult> nextWasmDisassemblyChunk(NextWasmDisassemblyChunkParams params) {
            return client.call("Debugger.nextWasmDisassemblyChunk", params, NextWasmDisassemblyChunkResult::fromMap);
        }
        /**
         * This command is deprecated. Use getScriptSource instead.
         * @param params command parameters
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<GetWasmBytecodeResult> getWasmBytecode(GetWasmBytecodeParams params) {
            return client.call("Debugger.getWasmBytecode", params, GetWasmBytecodeResult::fromMap);
        }
        /**
         * Returns stack trace with given {@code stackTraceId}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetStackTraceResult> getStackTrace(GetStackTraceParams params) {
            return client.call("Debugger.getStackTrace", params, GetStackTraceResult::fromMap);
        }
        /**
         * Stops on the next JavaScript statement.
         * @return a stage completing with the command result
         */
        public CompletionStage<PauseResult> pause() {
            return client.call("Debugger.pause", null, PauseResult::fromMap);
        }
        /**
         * Invokes Debugger.pauseOnAsyncCall.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<PauseOnAsyncCallResult> pauseOnAsyncCall(PauseOnAsyncCallParams params) {
            return client.call("Debugger.pauseOnAsyncCall", params, PauseOnAsyncCallResult::fromMap);
        }
        /**
         * Removes JavaScript breakpoint.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<RemoveBreakpointResult> removeBreakpoint(RemoveBreakpointParams params) {
            return client.call("Debugger.removeBreakpoint", params, RemoveBreakpointResult::fromMap);
        }
        /**
         * Restarts particular call frame from the beginning. The old, deprecated behavior of {@code restartFrame} is to stay paused and allow further CDP commands after a restart was scheduled. This can cause problems with restarting, so we now continue execution immediatly after it has been scheduled until we reach the beginning of the restarted frame.
         * <p>To stay back-wards compatible, {@code restartFrame} now expects a {@code mode} parameter to be present. If the {@code mode} parameter is missing, {@code restartFrame} errors out.
         * <p>The various return values are deprecated and {@code callFrames} is always empty. Use the call frames from the {@code Debugger#paused} events instead, that fires once V8 pauses at the beginning of the restarted function.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<RestartFrameResult> restartFrame(RestartFrameParams params) {
            return client.call("Debugger.restartFrame", params, RestartFrameResult::fromMap);
        }
        /**
         * Resumes JavaScript execution.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ResumeResult> resume(ResumeParams params) {
            return client.call("Debugger.resume", params, ResumeResult::fromMap);
        }
        /**
         * Searches for given string in script content.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SearchInContentResult> searchInContent(SearchInContentParams params) {
            return client.call("Debugger.searchInContent", params, SearchInContentResult::fromMap);
        }
        /**
         * Enables or disables async call stacks tracking.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetAsyncCallStackDepthResult> setAsyncCallStackDepth(SetAsyncCallStackDepthParams params) {
            return client.call("Debugger.setAsyncCallStackDepth", params, SetAsyncCallStackDepthResult::fromMap);
        }
        /**
         * Replace previous blackbox execution contexts with passed ones. Forces backend to skip stepping/pausing in scripts in these execution contexts. VM will try to leave blackboxed script by performing &#x27;step in&#x27; several times, finally resorting to &#x27;step out&#x27; if unsuccessful.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetBlackboxExecutionContextsResult> setBlackboxExecutionContexts(SetBlackboxExecutionContextsParams params) {
            return client.call("Debugger.setBlackboxExecutionContexts", params, SetBlackboxExecutionContextsResult::fromMap);
        }
        /**
         * Replace previous blackbox patterns with passed ones. Forces backend to skip stepping/pausing in scripts with url matching one of the patterns. VM will try to leave blackboxed script by performing &#x27;step in&#x27; several times, finally resorting to &#x27;step out&#x27; if unsuccessful.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetBlackboxPatternsResult> setBlackboxPatterns(SetBlackboxPatternsParams params) {
            return client.call("Debugger.setBlackboxPatterns", params, SetBlackboxPatternsResult::fromMap);
        }
        /**
         * Makes backend skip steps in the script in blackboxed ranges. VM will try leave blacklisted scripts by performing &#x27;step in&#x27; several times, finally resorting to &#x27;step out&#x27; if unsuccessful. Positions array contains positions where blackbox state is changed. First interval isn&#x27;t blackboxed. Array should be sorted.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetBlackboxedRangesResult> setBlackboxedRanges(SetBlackboxedRangesParams params) {
            return client.call("Debugger.setBlackboxedRanges", params, SetBlackboxedRangesResult::fromMap);
        }
        /**
         * Sets JavaScript breakpoint at a given location.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetBreakpointResult> setBreakpoint(SetBreakpointParams params) {
            return client.call("Debugger.setBreakpoint", params, SetBreakpointResult::fromMap);
        }
        /**
         * Sets instrumentation breakpoint.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetInstrumentationBreakpointResult> setInstrumentationBreakpoint(SetInstrumentationBreakpointParams params) {
            return client.call("Debugger.setInstrumentationBreakpoint", params, SetInstrumentationBreakpointResult::fromMap);
        }
        /**
         * Sets JavaScript breakpoint at given location specified either by URL or URL regex. Once this command is issued, all existing parsed scripts will have breakpoints resolved and returned in {@code locations} property. Further matching script parsing will result in subsequent {@code breakpointResolved} events issued. This logical breakpoint will survive page reloads.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetBreakpointByUrlResult> setBreakpointByUrl(SetBreakpointByUrlParams params) {
            return client.call("Debugger.setBreakpointByUrl", params, SetBreakpointByUrlResult::fromMap);
        }
        /**
         * Sets JavaScript breakpoint before each call to the given function. If another function was created from the same source as a given one, calling it will also trigger the breakpoint.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetBreakpointOnFunctionCallResult> setBreakpointOnFunctionCall(SetBreakpointOnFunctionCallParams params) {
            return client.call("Debugger.setBreakpointOnFunctionCall", params, SetBreakpointOnFunctionCallResult::fromMap);
        }
        /**
         * Activates / deactivates all breakpoints on the page.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetBreakpointsActiveResult> setBreakpointsActive(SetBreakpointsActiveParams params) {
            return client.call("Debugger.setBreakpointsActive", params, SetBreakpointsActiveResult::fromMap);
        }
        /**
         * Defines pause on exceptions state. Can be set to stop on all exceptions, uncaught exceptions, or caught exceptions, no exceptions. Initial pause on exceptions state is {@code none}.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetPauseOnExceptionsResult> setPauseOnExceptions(SetPauseOnExceptionsParams params) {
            return client.call("Debugger.setPauseOnExceptions", params, SetPauseOnExceptionsResult::fromMap);
        }
        /**
         * Changes return value in top frame. Available only at return break position.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetReturnValueResult> setReturnValue(SetReturnValueParams params) {
            return client.call("Debugger.setReturnValue", params, SetReturnValueResult::fromMap);
        }
        /**
         * Edits JavaScript source live.
         * <p>In general, functions that are currently on the stack can not be edited with a single exception: If the edited function is the top-most stack frame and that is the only activation of that function on the stack. In this case the live edit will be successful and a {@code Debugger.restartFrame} for the top-most function is automatically triggered.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetScriptSourceResult> setScriptSource(SetScriptSourceParams params) {
            return client.call("Debugger.setScriptSource", params, SetScriptSourceResult::fromMap);
        }
        /**
         * Makes page not interrupt on any pauses (breakpoint, exception, dom exception etc).
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetSkipAllPausesResult> setSkipAllPauses(SetSkipAllPausesParams params) {
            return client.call("Debugger.setSkipAllPauses", params, SetSkipAllPausesResult::fromMap);
        }
        /**
         * Changes value of variable in a callframe. Object-based scopes are not supported and must be mutated manually.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetVariableValueResult> setVariableValue(SetVariableValueParams params) {
            return client.call("Debugger.setVariableValue", params, SetVariableValueResult::fromMap);
        }
        /**
         * Steps into the function call.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<StepIntoResult> stepInto(StepIntoParams params) {
            return client.call("Debugger.stepInto", params, StepIntoResult::fromMap);
        }
        /**
         * Steps out of the function call.
         * @return a stage completing with the command result
         */
        public CompletionStage<StepOutResult> stepOut() {
            return client.call("Debugger.stepOut", null, StepOutResult::fromMap);
        }
        /**
         * Steps over the statement.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<StepOverResult> stepOver(StepOverParams params) {
            return client.call("Debugger.stepOver", params, StepOverResult::fromMap);
        }
        /**
         * Fired when breakpoint is resolved to an actual script and location. Deprecated in favor of {@code resolvedBreakpoints} in the {@code scriptParsed} event.
         * @param handler event callback
         * @return a removable subscription
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CdpSubscription onBreakpointResolved(Consumer<BreakpointResolvedEvent> handler) {
            return client.on("Debugger.breakpointResolved", BreakpointResolvedEvent::fromMap, handler);
        }
        /**
         * Fired when the virtual machine stopped on breakpoint or exception or any other stop criteria.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onPaused(Consumer<PausedEvent> handler) {
            return client.on("Debugger.paused", PausedEvent::fromMap, handler);
        }
        /**
         * Fired when the virtual machine resumed execution.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onResumed(Consumer<ResumedEvent> handler) {
            return client.on("Debugger.resumed", ResumedEvent::fromMap, handler);
        }
        /**
         * Fired when virtual machine fails to parse the script.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onScriptFailedToParse(Consumer<ScriptFailedToParseEvent> handler) {
            return client.on("Debugger.scriptFailedToParse", ScriptFailedToParseEvent::fromMap, handler);
        }
        /**
         * Fired when virtual machine parses script. This event is also fired for all known and uncollected scripts upon enabling debugger.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onScriptParsed(Consumer<ScriptParsedEvent> handler) {
            return client.on("Debugger.scriptParsed", ScriptParsedEvent::fromMap, handler);
        }
    }
}
