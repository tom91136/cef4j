// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalLong;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.CdpObject;
import net.kurobako.cef4j.cdp.CdpSubscription;
import net.kurobako.cef4j.cdp.CdpValue;
import net.kurobako.cef4j.policy.NullableBoundary;
import org.jspecify.annotations.Nullable;

/**
 * Debugger domain exposes JavaScript debugging capabilities. It allows setting and removing breakpoints, stepping through execution, exploring stack traces, etc.
 * @see <a href="https://chromium.googlesource.com/v8/v8/+/3063ea3a0737a3fc4d4ed3babd595f1cace1e6ac/include/js_protocol.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
public final class Debugger {
    private Debugger() {}
    /**
     * Breakpoint identifier.
     */
    public static final class BreakpointId implements CdpValue<String> {
        public final String value;
        public BreakpointId(String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Override public String value() { return value; }
        @Override public boolean equals(@org.jspecify.annotations.Nullable Object other) {
            if (this == other) return true;
            if (!(other instanceof BreakpointId)) return false;
            return value.equals(((BreakpointId) other).value);
        }
        @Override public int hashCode() { return value.hashCode(); }
        @Override public String toString() { return "BreakpointId(" + value + ")"; }
    }
    /**
     * Call frame identifier.
     */
    public static final class CallFrameId implements CdpValue<String> {
        public final String value;
        public CallFrameId(String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Override public String value() { return value; }
        @Override public boolean equals(@org.jspecify.annotations.Nullable Object other) {
            if (this == other) return true;
            if (!(other instanceof CallFrameId)) return false;
            return value.equals(((CallFrameId) other).value);
        }
        @Override public int hashCode() { return value.hashCode(); }
        @Override public String toString() { return "CallFrameId(" + value + ")"; }
    }
    /**
     * Location in the source code.
     */
    public static final class Location extends CdpObject {
        public Location() {}
        private Location(Map<String, Object> values) { super(values); }
        public static Location fromMap(Map<String, Object> values) {
            return new Location(values);
        }
        /**
         * Script identifier as reported in the {@code Debugger.scriptParsed}.
         * @return the protocol field value
         */
        public Runtime.ScriptId scriptId() {
            return new Runtime.ScriptId((String) require("scriptId"));
        }
        /**
         * Line number in the script (0-based).
         * @return the protocol field value
         */
        public long lineNumber() {
            return ((Number) require("lineNumber")).longValue();
        }
        /**
         * Column number in the script (0-based).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong columnNumber() {
            return raw("columnNumber").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Script identifier as reported in the {@code Debugger.scriptParsed}.
         * @param scriptId field value
         * @return this model
         */
        public Location scriptId(Runtime.ScriptId scriptId) {
            set("scriptId", scriptId);
            return this;
        }
        /**
         * Line number in the script (0-based).
         * @param lineNumber field value
         * @return this model
         */
        public Location lineNumber(long lineNumber) {
            set("lineNumber", lineNumber);
            return this;
        }
        /**
         * Column number in the script (0-based).
         * @param columnNumber field value
         * @return this model
         */
        public Location columnNumber(long columnNumber) {
            set("columnNumber", columnNumber);
            return this;
        }
    }
    /**
     * Location in the source code.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ScriptPosition extends CdpObject {
        public ScriptPosition() {}
        private ScriptPosition(Map<String, Object> values) { super(values); }
        public static ScriptPosition fromMap(Map<String, Object> values) {
            return new ScriptPosition(values);
        }
        /**
         * Returns the lineNumber field.
         * @return the protocol field value
         */
        public long lineNumber() {
            return ((Number) require("lineNumber")).longValue();
        }
        /**
         * Returns the columnNumber field.
         * @return the protocol field value
         */
        public long columnNumber() {
            return ((Number) require("columnNumber")).longValue();
        }
        /**
         * Sets the lineNumber field.
         * @param lineNumber field value
         * @return this model
         */
        public ScriptPosition lineNumber(long lineNumber) {
            set("lineNumber", lineNumber);
            return this;
        }
        /**
         * Sets the columnNumber field.
         * @param columnNumber field value
         * @return this model
         */
        public ScriptPosition columnNumber(long columnNumber) {
            set("columnNumber", columnNumber);
            return this;
        }
    }
    /**
     * Location range within one script.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class LocationRange extends CdpObject {
        public LocationRange() {}
        private LocationRange(Map<String, Object> values) { super(values); }
        public static LocationRange fromMap(Map<String, Object> values) {
            return new LocationRange(values);
        }
        /**
         * Returns the scriptId field.
         * @return the protocol field value
         */
        public Runtime.ScriptId scriptId() {
            return new Runtime.ScriptId((String) require("scriptId"));
        }
        /**
         * Returns the start field.
         * @return the protocol field value
         */
        public Debugger.ScriptPosition start() {
            return java.util.Objects.requireNonNull(Debugger.ScriptPosition.fromMap(CdpObject.requireObjectMap(require("start"))));
        }
        /**
         * Returns the end field.
         * @return the protocol field value
         */
        public Debugger.ScriptPosition end() {
            return java.util.Objects.requireNonNull(Debugger.ScriptPosition.fromMap(CdpObject.requireObjectMap(require("end"))));
        }
        /**
         * Sets the scriptId field.
         * @param scriptId field value
         * @return this model
         */
        public LocationRange scriptId(Runtime.ScriptId scriptId) {
            set("scriptId", scriptId);
            return this;
        }
        /**
         * Sets the start field.
         * @param start field value
         * @return this model
         */
        public LocationRange start(Debugger.ScriptPosition start) {
            set("start", start);
            return this;
        }
        /**
         * Sets the end field.
         * @param end field value
         * @return this model
         */
        public LocationRange end(Debugger.ScriptPosition end) {
            set("end", end);
            return this;
        }
    }
    /**
     * JavaScript call frame. Array of call frames form the call stack.
     */
    public static final class CallFrame extends CdpObject {
        public CallFrame() {}
        private CallFrame(Map<String, Object> values) { super(values); }
        public static CallFrame fromMap(Map<String, Object> values) {
            return new CallFrame(values);
        }
        /**
         * Call frame identifier. This identifier is only valid while the virtual machine is paused.
         * @return the protocol field value
         */
        public Debugger.CallFrameId callFrameId() {
            return new Debugger.CallFrameId((String) require("callFrameId"));
        }
        /**
         * Name of the JavaScript function called on this call frame.
         * @return the protocol field value
         */
        public String functionName() {
            return (String) require("functionName");
        }
        /**
         * Location in the source code.
         * @return the protocol field value, empty when absent
         */
        public Optional<Debugger.Location> functionLocation() {
            return raw("functionLocation").flatMap(CdpObject::objectMap).map(Debugger.Location::fromMap);
        }
        /**
         * Location in the source code.
         * @return the protocol field value
         */
        public Debugger.Location location() {
            return java.util.Objects.requireNonNull(Debugger.Location.fromMap(CdpObject.requireObjectMap(require("location"))));
        }
        /**
         * JavaScript script name or url. Deprecated in favor of using the {@code location.scriptId} to resolve the URL via a previously sent {@code Debugger.scriptParsed} event.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public String url() {
            return (String) require("url");
        }
        /**
         * Scope chain for this call frame.
         * @return the protocol field value
         */
        public java.util.List<Debugger.Scope> scopeChain() {
            return CdpObject.requireList(require("scopeChain"), element0 -> java.util.Objects.requireNonNull(Debugger.Scope.fromMap(CdpObject.requireObjectMap(element0))));
        }
        /**
         * {@code this} object for this call frame.
         * @return the protocol field value
         */
        public Runtime.RemoteObject thisValue() {
            return java.util.Objects.requireNonNull(Runtime.RemoteObject.fromMap(CdpObject.requireObjectMap(require("this"))));
        }
        /**
         * The value being returned, if the function is at return point.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObject> returnValue() {
            return raw("returnValue").flatMap(CdpObject::objectMap).map(Runtime.RemoteObject::fromMap);
        }
        /**
         * Valid only while the VM is paused and indicates whether this frame can be restarted or not. Note that a {@code true} value here does not guarantee that Debugger#restartFrame with this CallFrameId will be successful, but it is very likely.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> canBeRestarted() {
            return raw("canBeRestarted").map(value -> (Boolean) value);
        }
        /**
         * Call frame identifier. This identifier is only valid while the virtual machine is paused.
         * @param callFrameId field value
         * @return this model
         */
        public CallFrame callFrameId(Debugger.CallFrameId callFrameId) {
            set("callFrameId", callFrameId);
            return this;
        }
        /**
         * Name of the JavaScript function called on this call frame.
         * @param functionName field value
         * @return this model
         */
        public CallFrame functionName(String functionName) {
            set("functionName", functionName);
            return this;
        }
        /**
         * Location in the source code.
         * @param functionLocation field value
         * @return this model
         */
        public CallFrame functionLocation(Debugger.Location functionLocation) {
            set("functionLocation", functionLocation);
            return this;
        }
        /**
         * Location in the source code.
         * @param location field value
         * @return this model
         */
        public CallFrame location(Debugger.Location location) {
            set("location", location);
            return this;
        }
        /**
         * JavaScript script name or url. Deprecated in favor of using the {@code location.scriptId} to resolve the URL via a previously sent {@code Debugger.scriptParsed} event.
         * @param url field value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CallFrame url(String url) {
            set("url", url);
            return this;
        }
        /**
         * Scope chain for this call frame.
         * @param scopeChain field value
         * @return this model
         */
        public CallFrame scopeChain(java.util.List<Debugger.Scope> scopeChain) {
            set("scopeChain", scopeChain);
            return this;
        }
        /**
         * {@code this} object for this call frame.
         * @param thisValue field value
         * @return this model
         */
        public CallFrame thisValue(Runtime.RemoteObject thisValue) {
            set("this", thisValue);
            return this;
        }
        /**
         * The value being returned, if the function is at return point.
         * @param returnValue field value
         * @return this model
         */
        public CallFrame returnValue(Runtime.RemoteObject returnValue) {
            set("returnValue", returnValue);
            return this;
        }
        /**
         * Valid only while the VM is paused and indicates whether this frame can be restarted or not. Note that a {@code true} value here does not guarantee that Debugger#restartFrame with this CallFrameId will be successful, but it is very likely.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param canBeRestarted field value
         * @return this model
         */
        public CallFrame canBeRestarted(boolean canBeRestarted) {
            set("canBeRestarted", canBeRestarted);
            return this;
        }
    }
    /**
     * Scope description.
     */
    public static final class Scope extends CdpObject {
        public Scope() {}
        private Scope(Map<String, Object> values) { super(values); }
        public static Scope fromMap(Map<String, Object> values) {
            return new Scope(values);
        }
        /**
         * Scope type.
         */
        public enum TypeValues implements CdpValue<String> {
            GLOBAL("global"),
            LOCAL("local"),
            WITH("with"),
            CLOSURE("closure"),
            CATCH("catch"),
            BLOCK("block"),
            SCRIPT("script"),
            EVAL("eval"),
            MODULE("module"),
            WASM_EXPRESSION_STACK("wasm-expression-stack");
            public final String value;
            TypeValues(String value) { this.value = value; }
            @Override public String value() { return value; }
            public static TypeValues of(String value) {
                for (TypeValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown TypeValues value: " + value);
            }
        }
        /**
         * Scope type.
         * @return the protocol field value
         */
        public Scope.TypeValues type() {
            return Scope.TypeValues.of((String) require("type"));
        }
        /**
         * Object representing the scope. For {@code global} and {@code with} scopes it represents the actual object; for the rest of the scopes, it is artificial transient object enumerating scope variables as its properties.
         * @return the protocol field value
         */
        public Runtime.RemoteObject object() {
            return java.util.Objects.requireNonNull(Runtime.RemoteObject.fromMap(CdpObject.requireObjectMap(require("object"))));
        }
        /**
         * Returns the name field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> name() {
            return raw("name").map(value -> (String) value);
        }
        /**
         * Location in the source code where scope starts
         * @return the protocol field value, empty when absent
         */
        public Optional<Debugger.Location> startLocation() {
            return raw("startLocation").flatMap(CdpObject::objectMap).map(Debugger.Location::fromMap);
        }
        /**
         * Location in the source code where scope ends
         * @return the protocol field value, empty when absent
         */
        public Optional<Debugger.Location> endLocation() {
            return raw("endLocation").flatMap(CdpObject::objectMap).map(Debugger.Location::fromMap);
        }
        /**
         * Scope type.
         * @param type field value
         * @return this model
         */
        public Scope type(Scope.TypeValues type) {
            set("type", type);
            return this;
        }
        /**
         * Object representing the scope. For {@code global} and {@code with} scopes it represents the actual object; for the rest of the scopes, it is artificial transient object enumerating scope variables as its properties.
         * @param object field value
         * @return this model
         */
        public Scope object(Runtime.RemoteObject object) {
            set("object", object);
            return this;
        }
        /**
         * Sets the name field.
         * @param name field value
         * @return this model
         */
        public Scope name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Location in the source code where scope starts
         * @param startLocation field value
         * @return this model
         */
        public Scope startLocation(Debugger.Location startLocation) {
            set("startLocation", startLocation);
            return this;
        }
        /**
         * Location in the source code where scope ends
         * @param endLocation field value
         * @return this model
         */
        public Scope endLocation(Debugger.Location endLocation) {
            set("endLocation", endLocation);
            return this;
        }
    }
    /**
     * Search match for resource.
     */
    public static final class SearchMatch extends CdpObject {
        public SearchMatch() {}
        private SearchMatch(Map<String, Object> values) { super(values); }
        public static SearchMatch fromMap(Map<String, Object> values) {
            return new SearchMatch(values);
        }
        /**
         * Line number in resource content.
         * @return the protocol field value
         */
        public double lineNumber() {
            return ((Number) require("lineNumber")).doubleValue();
        }
        /**
         * Line with match content.
         * @return the protocol field value
         */
        public String lineContent() {
            return (String) require("lineContent");
        }
        /**
         * Line number in resource content.
         * @param lineNumber field value
         * @return this model
         */
        public SearchMatch lineNumber(double lineNumber) {
            set("lineNumber", lineNumber);
            return this;
        }
        /**
         * Line with match content.
         * @param lineContent field value
         * @return this model
         */
        public SearchMatch lineContent(String lineContent) {
            set("lineContent", lineContent);
            return this;
        }
    }
    /**
     */
    public static final class BreakLocation extends CdpObject {
        public BreakLocation() {}
        private BreakLocation(Map<String, Object> values) { super(values); }
        public static BreakLocation fromMap(Map<String, Object> values) {
            return new BreakLocation(values);
        }
        /**
         * Wire values for TypeValues.
         */
        public enum TypeValues implements CdpValue<String> {
            DEBUGGERSTATEMENT("debuggerStatement"),
            CALL("call"),
            RETURN("return");
            public final String value;
            TypeValues(String value) { this.value = value; }
            @Override public String value() { return value; }
            public static TypeValues of(String value) {
                for (TypeValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown TypeValues value: " + value);
            }
        }
        /**
         * Script identifier as reported in the {@code Debugger.scriptParsed}.
         * @return the protocol field value
         */
        public Runtime.ScriptId scriptId() {
            return new Runtime.ScriptId((String) require("scriptId"));
        }
        /**
         * Line number in the script (0-based).
         * @return the protocol field value
         */
        public long lineNumber() {
            return ((Number) require("lineNumber")).longValue();
        }
        /**
         * Column number in the script (0-based).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong columnNumber() {
            return raw("columnNumber").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Returns the type field.
         * @return the protocol field value, empty when absent
         */
        public Optional<BreakLocation.TypeValues> type() {
            return raw("type").map(value -> BreakLocation.TypeValues.of((String) value));
        }
        /**
         * Script identifier as reported in the {@code Debugger.scriptParsed}.
         * @param scriptId field value
         * @return this model
         */
        public BreakLocation scriptId(Runtime.ScriptId scriptId) {
            set("scriptId", scriptId);
            return this;
        }
        /**
         * Line number in the script (0-based).
         * @param lineNumber field value
         * @return this model
         */
        public BreakLocation lineNumber(long lineNumber) {
            set("lineNumber", lineNumber);
            return this;
        }
        /**
         * Column number in the script (0-based).
         * @param columnNumber field value
         * @return this model
         */
        public BreakLocation columnNumber(long columnNumber) {
            set("columnNumber", columnNumber);
            return this;
        }
        /**
         * Sets the type field.
         * @param type field value
         * @return this model
         */
        public BreakLocation type(BreakLocation.TypeValues type) {
            set("type", type);
            return this;
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class WasmDisassemblyChunk extends CdpObject {
        public WasmDisassemblyChunk() {}
        private WasmDisassemblyChunk(Map<String, Object> values) { super(values); }
        public static WasmDisassemblyChunk fromMap(Map<String, Object> values) {
            return new WasmDisassemblyChunk(values);
        }
        /**
         * The next chunk of disassembled lines.
         * @return the protocol field value
         */
        public java.util.List<String> lines() {
            return CdpObject.requireList(require("lines"), element0 -> (String) element0);
        }
        /**
         * The bytecode offsets describing the start of each line.
         * @return the protocol field value
         */
        public java.util.List<Long> bytecodeOffsets() {
            return CdpObject.requireList(require("bytecodeOffsets"), element0 -> ((Number) element0).longValue());
        }
        /**
         * The next chunk of disassembled lines.
         * @param lines field value
         * @return this model
         */
        public WasmDisassemblyChunk lines(java.util.List<String> lines) {
            set("lines", lines);
            return this;
        }
        /**
         * The bytecode offsets describing the start of each line.
         * @param bytecodeOffsets field value
         * @return this model
         */
        public WasmDisassemblyChunk bytecodeOffsets(java.util.List<Long> bytecodeOffsets) {
            set("bytecodeOffsets", bytecodeOffsets);
            return this;
        }
    }
    /**
     * Enum of possible script languages.
     */
    public enum ScriptLanguage implements CdpValue<String> {
        JAVASCRIPT("JavaScript"),
        WEBASSEMBLY("WebAssembly");
        public final String value;
        ScriptLanguage(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static ScriptLanguage of(String value) {
            for (ScriptLanguage constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown ScriptLanguage value: " + value);
        }
    }
    /**
     * Debug symbols available for a wasm script.
     */
    public static final class DebugSymbols extends CdpObject {
        public DebugSymbols() {}
        private DebugSymbols(Map<String, Object> values) { super(values); }
        public static DebugSymbols fromMap(Map<String, Object> values) {
            return new DebugSymbols(values);
        }
        /**
         * Type of the debug symbols.
         */
        public enum TypeValues implements CdpValue<String> {
            SOURCEMAP("SourceMap"),
            EMBEDDEDDWARF("EmbeddedDWARF"),
            EXTERNALDWARF("ExternalDWARF");
            public final String value;
            TypeValues(String value) { this.value = value; }
            @Override public String value() { return value; }
            public static TypeValues of(String value) {
                for (TypeValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown TypeValues value: " + value);
            }
        }
        /**
         * Type of the debug symbols.
         * @return the protocol field value
         */
        public DebugSymbols.TypeValues type() {
            return DebugSymbols.TypeValues.of((String) require("type"));
        }
        /**
         * URL of the external symbol source.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> externalURL() {
            return raw("externalURL").map(value -> (String) value);
        }
        /**
         * Type of the debug symbols.
         * @param type field value
         * @return this model
         */
        public DebugSymbols type(DebugSymbols.TypeValues type) {
            set("type", type);
            return this;
        }
        /**
         * URL of the external symbol source.
         * @param externalURL field value
         * @return this model
         */
        public DebugSymbols externalURL(String externalURL) {
            set("externalURL", externalURL);
            return this;
        }
    }
    /**
     */
    public static final class ResolvedBreakpoint extends CdpObject {
        public ResolvedBreakpoint() {}
        private ResolvedBreakpoint(Map<String, Object> values) { super(values); }
        public static ResolvedBreakpoint fromMap(Map<String, Object> values) {
            return new ResolvedBreakpoint(values);
        }
        /**
         * Breakpoint unique identifier.
         * @return the protocol field value
         */
        public Debugger.BreakpointId breakpointId() {
            return new Debugger.BreakpointId((String) require("breakpointId"));
        }
        /**
         * Actual breakpoint location.
         * @return the protocol field value
         */
        public Debugger.Location location() {
            return java.util.Objects.requireNonNull(Debugger.Location.fromMap(CdpObject.requireObjectMap(require("location"))));
        }
        /**
         * Breakpoint unique identifier.
         * @param breakpointId field value
         * @return this model
         */
        public ResolvedBreakpoint breakpointId(Debugger.BreakpointId breakpointId) {
            set("breakpointId", breakpointId);
            return this;
        }
        /**
         * Actual breakpoint location.
         * @param location field value
         * @return this model
         */
        public ResolvedBreakpoint location(Debugger.Location location) {
            set("location", location);
            return this;
        }
    }
    /**
     * Continues execution until specific location is reached.
     */
    public static final class ContinueToLocationRequest extends CdpObject {
        public ContinueToLocationRequest() {}
        /**
         * Continues execution until specific location is reached.
         * @param location protocol value
         */
        public ContinueToLocationRequest(Debugger.Location location) {
            location(location);
        }
        public static ContinueToLocationRequest fromMap(Map<String, Object> values) {
            final ContinueToLocationRequest instance_ = new ContinueToLocationRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Location to continue to.
         * @return the protocol field value
         */
        public Debugger.Location location() {
            return java.util.Objects.requireNonNull(Debugger.Location.fromMap(CdpObject.requireObjectMap(require("location"))));
        }
        /**
         * Returns the targetCallFrames field.
         * @return the protocol field value, empty when absent
         */
        public Optional<ContinueToLocationTargetCallFramesValues> targetCallFrames() {
            return raw("targetCallFrames").map(value -> ContinueToLocationTargetCallFramesValues.of((String) value));
        }
        /**
         * Location to continue to.
         * @param location field value
         * @return this model
         */
        public ContinueToLocationRequest location(Debugger.Location location) {
            set("location", location);
            return this;
        }
        /**
         * Sets the targetCallFrames field.
         * @param targetCallFrames field value
         * @return this model
         */
        public ContinueToLocationRequest targetCallFrames(ContinueToLocationTargetCallFramesValues targetCallFrames) {
            set("targetCallFrames", targetCallFrames);
            return this;
        }
    }
    /**
     * Enables debugger for the given page. Clients should not assume that the debugging has been enabled until the result for this command is received.
     */
    public static final class EnableRequest extends CdpObject {
        public EnableRequest() {}
        public static EnableRequest fromMap(Map<String, Object> values) {
            final EnableRequest instance_ = new EnableRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * The maximum size in bytes of collected scripts (not referenced by other heap objects) the debugger can hold. Puts no limit if parameter is omitted.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble maxScriptsCacheSize() {
            return raw("maxScriptsCacheSize").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * The maximum size in bytes of collected scripts (not referenced by other heap objects) the debugger can hold. Puts no limit if parameter is omitted.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param maxScriptsCacheSize field value
         * @return this model
         */
        public EnableRequest maxScriptsCacheSize(double maxScriptsCacheSize) {
            set("maxScriptsCacheSize", maxScriptsCacheSize);
            return this;
        }
    }
    /**
     * Evaluates expression on a given call frame.
     */
    public static final class EvaluateOnCallFrameRequest extends CdpObject {
        public EvaluateOnCallFrameRequest() {}
        /**
         * Evaluates expression on a given call frame.
         * @param callFrameId protocol value
         * @param expression protocol value
         */
        public EvaluateOnCallFrameRequest(Debugger.CallFrameId callFrameId, String expression) {
            callFrameId(callFrameId).expression(expression);
        }
        public static EvaluateOnCallFrameRequest fromMap(Map<String, Object> values) {
            final EvaluateOnCallFrameRequest instance_ = new EvaluateOnCallFrameRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Call frame identifier to evaluate on.
         * @return the protocol field value
         */
        public Debugger.CallFrameId callFrameId() {
            return new Debugger.CallFrameId((String) require("callFrameId"));
        }
        /**
         * Expression to evaluate.
         * @return the protocol field value
         */
        public String expression() {
            return (String) require("expression");
        }
        /**
         * String object group name to put result into (allows rapid releasing resulting object handles using {@code releaseObjectGroup}).
         * @return the protocol field value, empty when absent
         */
        public Optional<String> objectGroup() {
            return raw("objectGroup").map(value -> (String) value);
        }
        /**
         * Specifies whether command line API should be available to the evaluated expression, defaults to false.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> includeCommandLineAPI() {
            return raw("includeCommandLineAPI").map(value -> (Boolean) value);
        }
        /**
         * In silent mode exceptions thrown during evaluation are not reported and do not pause execution. Overrides {@code setPauseOnException} state.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> silent() {
            return raw("silent").map(value -> (Boolean) value);
        }
        /**
         * Whether the result is expected to be a JSON object that should be sent by value.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> returnByValue() {
            return raw("returnByValue").map(value -> (Boolean) value);
        }
        /**
         * Whether preview should be generated for the result.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> generatePreview() {
            return raw("generatePreview").map(value -> (Boolean) value);
        }
        /**
         * Whether to throw an exception if side effect cannot be ruled out during evaluation.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> throwOnSideEffect() {
            return raw("throwOnSideEffect").map(value -> (Boolean) value);
        }
        /**
         * Terminate execution after timing out (number of milliseconds).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.TimeDelta> timeout() {
            return raw("timeout").map(value -> new Runtime.TimeDelta(((Number) value).doubleValue()));
        }
        /**
         * Call frame identifier to evaluate on.
         * @param callFrameId field value
         * @return this model
         */
        public EvaluateOnCallFrameRequest callFrameId(Debugger.CallFrameId callFrameId) {
            set("callFrameId", callFrameId);
            return this;
        }
        /**
         * Expression to evaluate.
         * @param expression field value
         * @return this model
         */
        public EvaluateOnCallFrameRequest expression(String expression) {
            set("expression", expression);
            return this;
        }
        /**
         * String object group name to put result into (allows rapid releasing resulting object handles using {@code releaseObjectGroup}).
         * @param objectGroup field value
         * @return this model
         */
        public EvaluateOnCallFrameRequest objectGroup(String objectGroup) {
            set("objectGroup", objectGroup);
            return this;
        }
        /**
         * Specifies whether command line API should be available to the evaluated expression, defaults to false.
         * @param includeCommandLineAPI field value
         * @return this model
         */
        public EvaluateOnCallFrameRequest includeCommandLineAPI(boolean includeCommandLineAPI) {
            set("includeCommandLineAPI", includeCommandLineAPI);
            return this;
        }
        /**
         * In silent mode exceptions thrown during evaluation are not reported and do not pause execution. Overrides {@code setPauseOnException} state.
         * @param silent field value
         * @return this model
         */
        public EvaluateOnCallFrameRequest silent(boolean silent) {
            set("silent", silent);
            return this;
        }
        /**
         * Whether the result is expected to be a JSON object that should be sent by value.
         * @param returnByValue field value
         * @return this model
         */
        public EvaluateOnCallFrameRequest returnByValue(boolean returnByValue) {
            set("returnByValue", returnByValue);
            return this;
        }
        /**
         * Whether preview should be generated for the result.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param generatePreview field value
         * @return this model
         */
        public EvaluateOnCallFrameRequest generatePreview(boolean generatePreview) {
            set("generatePreview", generatePreview);
            return this;
        }
        /**
         * Whether to throw an exception if side effect cannot be ruled out during evaluation.
         * @param throwOnSideEffect field value
         * @return this model
         */
        public EvaluateOnCallFrameRequest throwOnSideEffect(boolean throwOnSideEffect) {
            set("throwOnSideEffect", throwOnSideEffect);
            return this;
        }
        /**
         * Terminate execution after timing out (number of milliseconds).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param timeout field value
         * @return this model
         */
        public EvaluateOnCallFrameRequest timeout(Runtime.TimeDelta timeout) {
            set("timeout", timeout);
            return this;
        }
    }
    /**
     * Returns possible locations for breakpoint. scriptId in start and end range locations should be the same.
     */
    public static final class GetPossibleBreakpointsRequest extends CdpObject {
        public GetPossibleBreakpointsRequest() {}
        /**
         * Returns possible locations for breakpoint. scriptId in start and end range locations should be the same.
         * @param start protocol value
         */
        public GetPossibleBreakpointsRequest(Debugger.Location start) {
            start(start);
        }
        public static GetPossibleBreakpointsRequest fromMap(Map<String, Object> values) {
            final GetPossibleBreakpointsRequest instance_ = new GetPossibleBreakpointsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Start of range to search possible breakpoint locations in.
         * @return the protocol field value
         */
        public Debugger.Location start() {
            return java.util.Objects.requireNonNull(Debugger.Location.fromMap(CdpObject.requireObjectMap(require("start"))));
        }
        /**
         * End of range to search possible breakpoint locations in (excluding). When not specified, end of scripts is used as end of range.
         * @return the protocol field value, empty when absent
         */
        public Optional<Debugger.Location> end() {
            return raw("end").flatMap(CdpObject::objectMap).map(Debugger.Location::fromMap);
        }
        /**
         * Only consider locations which are in the same (non-nested) function as start.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> restrictToFunction() {
            return raw("restrictToFunction").map(value -> (Boolean) value);
        }
        /**
         * Start of range to search possible breakpoint locations in.
         * @param start field value
         * @return this model
         */
        public GetPossibleBreakpointsRequest start(Debugger.Location start) {
            set("start", start);
            return this;
        }
        /**
         * End of range to search possible breakpoint locations in (excluding). When not specified, end of scripts is used as end of range.
         * @param end field value
         * @return this model
         */
        public GetPossibleBreakpointsRequest end(Debugger.Location end) {
            set("end", end);
            return this;
        }
        /**
         * Only consider locations which are in the same (non-nested) function as start.
         * @param restrictToFunction field value
         * @return this model
         */
        public GetPossibleBreakpointsRequest restrictToFunction(boolean restrictToFunction) {
            set("restrictToFunction", restrictToFunction);
            return this;
        }
    }
    /**
     * Returns source for the script with given id.
     */
    public static final class GetScriptSourceRequest extends CdpObject {
        public GetScriptSourceRequest() {}
        /**
         * Returns source for the script with given id.
         * @param scriptId protocol value
         */
        public GetScriptSourceRequest(Runtime.ScriptId scriptId) {
            scriptId(scriptId);
        }
        public static GetScriptSourceRequest fromMap(Map<String, Object> values) {
            final GetScriptSourceRequest instance_ = new GetScriptSourceRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the script to get source for.
         * @return the protocol field value
         */
        public Runtime.ScriptId scriptId() {
            return new Runtime.ScriptId((String) require("scriptId"));
        }
        /**
         * Id of the script to get source for.
         * @param scriptId field value
         * @return this model
         */
        public GetScriptSourceRequest scriptId(Runtime.ScriptId scriptId) {
            set("scriptId", scriptId);
            return this;
        }
    }
    /**
     * Request parameters for Debugger.disassembleWasmModule.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DisassembleWasmModuleRequest extends CdpObject {
        public DisassembleWasmModuleRequest() {}
        /**
         * Creates a new DisassembleWasmModuleRequest with all required parameters.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param scriptId protocol value
         */
        public DisassembleWasmModuleRequest(Runtime.ScriptId scriptId) {
            scriptId(scriptId);
        }
        public static DisassembleWasmModuleRequest fromMap(Map<String, Object> values) {
            final DisassembleWasmModuleRequest instance_ = new DisassembleWasmModuleRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the script to disassemble
         * @return the protocol field value
         */
        public Runtime.ScriptId scriptId() {
            return new Runtime.ScriptId((String) require("scriptId"));
        }
        /**
         * Id of the script to disassemble
         * @param scriptId field value
         * @return this model
         */
        public DisassembleWasmModuleRequest scriptId(Runtime.ScriptId scriptId) {
            set("scriptId", scriptId);
            return this;
        }
    }
    /**
     * Disassemble the next chunk of lines for the module corresponding to the stream. If disassembly is complete, this API will invalidate the streamId and return an empty chunk. Any subsequent calls for the now invalid stream will return errors.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class NextWasmDisassemblyChunkRequest extends CdpObject {
        public NextWasmDisassemblyChunkRequest() {}
        /**
         * Disassemble the next chunk of lines for the module corresponding to the stream. If disassembly is complete, this API will invalidate the streamId and return an empty chunk. Any subsequent calls for the now invalid stream will return errors.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param streamId protocol value
         */
        public NextWasmDisassemblyChunkRequest(String streamId) {
            streamId(streamId);
        }
        public static NextWasmDisassemblyChunkRequest fromMap(Map<String, Object> values) {
            final NextWasmDisassemblyChunkRequest instance_ = new NextWasmDisassemblyChunkRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the streamId field.
         * @return the protocol field value
         */
        public String streamId() {
            return (String) require("streamId");
        }
        /**
         * Sets the streamId field.
         * @param streamId field value
         * @return this model
         */
        public NextWasmDisassemblyChunkRequest streamId(String streamId) {
            set("streamId", streamId);
            return this;
        }
    }
    /**
     * This command is deprecated. Use getScriptSource instead.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class GetWasmBytecodeRequest extends CdpObject {
        public GetWasmBytecodeRequest() {}
        /**
         * This command is deprecated. Use getScriptSource instead.
         * @param scriptId protocol value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public GetWasmBytecodeRequest(Runtime.ScriptId scriptId) {
            scriptId(scriptId);
        }
        public static GetWasmBytecodeRequest fromMap(Map<String, Object> values) {
            final GetWasmBytecodeRequest instance_ = new GetWasmBytecodeRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the Wasm script to get source for.
         * @return the protocol field value
         */
        public Runtime.ScriptId scriptId() {
            return new Runtime.ScriptId((String) require("scriptId"));
        }
        /**
         * Id of the Wasm script to get source for.
         * @param scriptId field value
         * @return this model
         */
        public GetWasmBytecodeRequest scriptId(Runtime.ScriptId scriptId) {
            set("scriptId", scriptId);
            return this;
        }
    }
    /**
     * Returns stack trace with given {@code stackTraceId}.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetStackTraceRequest extends CdpObject {
        public GetStackTraceRequest() {}
        /**
         * Returns stack trace with given {@code stackTraceId}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param stackTraceId protocol value
         */
        public GetStackTraceRequest(Runtime.StackTraceId stackTraceId) {
            stackTraceId(stackTraceId);
        }
        public static GetStackTraceRequest fromMap(Map<String, Object> values) {
            final GetStackTraceRequest instance_ = new GetStackTraceRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the stackTraceId field.
         * @return the protocol field value
         */
        public Runtime.StackTraceId stackTraceId() {
            return java.util.Objects.requireNonNull(Runtime.StackTraceId.fromMap(CdpObject.requireObjectMap(require("stackTraceId"))));
        }
        /**
         * Sets the stackTraceId field.
         * @param stackTraceId field value
         * @return this model
         */
        public GetStackTraceRequest stackTraceId(Runtime.StackTraceId stackTraceId) {
            set("stackTraceId", stackTraceId);
            return this;
        }
    }
    /**
     * Request parameters for Debugger.pauseOnAsyncCall.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class PauseOnAsyncCallRequest extends CdpObject {
        public PauseOnAsyncCallRequest() {}
        /**
         * Creates a new PauseOnAsyncCallRequest with all required parameters.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param parentStackTraceId protocol value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public PauseOnAsyncCallRequest(Runtime.StackTraceId parentStackTraceId) {
            parentStackTraceId(parentStackTraceId);
        }
        public static PauseOnAsyncCallRequest fromMap(Map<String, Object> values) {
            final PauseOnAsyncCallRequest instance_ = new PauseOnAsyncCallRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Debugger will pause when async call with given stack trace is started.
         * @return the protocol field value
         */
        public Runtime.StackTraceId parentStackTraceId() {
            return java.util.Objects.requireNonNull(Runtime.StackTraceId.fromMap(CdpObject.requireObjectMap(require("parentStackTraceId"))));
        }
        /**
         * Debugger will pause when async call with given stack trace is started.
         * @param parentStackTraceId field value
         * @return this model
         */
        public PauseOnAsyncCallRequest parentStackTraceId(Runtime.StackTraceId parentStackTraceId) {
            set("parentStackTraceId", parentStackTraceId);
            return this;
        }
    }
    /**
     * Removes JavaScript breakpoint.
     */
    public static final class RemoveBreakpointRequest extends CdpObject {
        public RemoveBreakpointRequest() {}
        /**
         * Removes JavaScript breakpoint.
         * @param breakpointId protocol value
         */
        public RemoveBreakpointRequest(Debugger.BreakpointId breakpointId) {
            breakpointId(breakpointId);
        }
        public static RemoveBreakpointRequest fromMap(Map<String, Object> values) {
            final RemoveBreakpointRequest instance_ = new RemoveBreakpointRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the breakpointId field.
         * @return the protocol field value
         */
        public Debugger.BreakpointId breakpointId() {
            return new Debugger.BreakpointId((String) require("breakpointId"));
        }
        /**
         * Sets the breakpointId field.
         * @param breakpointId field value
         * @return this model
         */
        public RemoveBreakpointRequest breakpointId(Debugger.BreakpointId breakpointId) {
            set("breakpointId", breakpointId);
            return this;
        }
    }
    /**
     * Restarts particular call frame from the beginning. The old, deprecated behavior of {@code restartFrame} is to stay paused and allow further CDP commands after a restart was scheduled. This can cause problems with restarting, so we now continue execution immediatly after it has been scheduled until we reach the beginning of the restarted frame.
     * <p>To stay back-wards compatible, {@code restartFrame} now expects a {@code mode} parameter to be present. If the {@code mode} parameter is missing, {@code restartFrame} errors out.
     * <p>The various return values are deprecated and {@code callFrames} is always empty. Use the call frames from the {@code Debugger#paused} events instead, that fires once V8 pauses at the beginning of the restarted function.
     */
    public static final class RestartFrameRequest extends CdpObject {
        public RestartFrameRequest() {}
        /**
         * Restarts particular call frame from the beginning. The old, deprecated behavior of {@code restartFrame} is to stay paused and allow further CDP commands after a restart was scheduled. This can cause problems with restarting, so we now continue execution immediatly after it has been scheduled until we reach the beginning of the restarted frame.
         * <p>To stay back-wards compatible, {@code restartFrame} now expects a {@code mode} parameter to be present. If the {@code mode} parameter is missing, {@code restartFrame} errors out.
         * <p>The various return values are deprecated and {@code callFrames} is always empty. Use the call frames from the {@code Debugger#paused} events instead, that fires once V8 pauses at the beginning of the restarted function.
         * @param callFrameId protocol value
         */
        public RestartFrameRequest(Debugger.CallFrameId callFrameId) {
            callFrameId(callFrameId);
        }
        public static RestartFrameRequest fromMap(Map<String, Object> values) {
            final RestartFrameRequest instance_ = new RestartFrameRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Call frame identifier to evaluate on.
         * @return the protocol field value
         */
        public Debugger.CallFrameId callFrameId() {
            return new Debugger.CallFrameId((String) require("callFrameId"));
        }
        /**
         * The {@code mode} parameter must be present and set to &#x27;StepInto&#x27;, otherwise {@code restartFrame} will error out.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<RestartFrameModeValues> mode() {
            return raw("mode").map(value -> RestartFrameModeValues.of((String) value));
        }
        /**
         * Call frame identifier to evaluate on.
         * @param callFrameId field value
         * @return this model
         */
        public RestartFrameRequest callFrameId(Debugger.CallFrameId callFrameId) {
            set("callFrameId", callFrameId);
            return this;
        }
        /**
         * The {@code mode} parameter must be present and set to &#x27;StepInto&#x27;, otherwise {@code restartFrame} will error out.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param mode field value
         * @return this model
         */
        public RestartFrameRequest mode(RestartFrameModeValues mode) {
            set("mode", mode);
            return this;
        }
    }
    /**
     * Resumes JavaScript execution.
     */
    public static final class ResumeRequest extends CdpObject {
        public ResumeRequest() {}
        public static ResumeRequest fromMap(Map<String, Object> values) {
            final ResumeRequest instance_ = new ResumeRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Set to true to terminate execution upon resuming execution. In contrast to Runtime.terminateExecution, this will allows to execute further JavaScript (i.e. via evaluation) until execution of the paused code is actually resumed, at which point termination is triggered. If execution is currently not paused, this parameter has no effect.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> terminateOnResume() {
            return raw("terminateOnResume").map(value -> (Boolean) value);
        }
        /**
         * Set to true to terminate execution upon resuming execution. In contrast to Runtime.terminateExecution, this will allows to execute further JavaScript (i.e. via evaluation) until execution of the paused code is actually resumed, at which point termination is triggered. If execution is currently not paused, this parameter has no effect.
         * @param terminateOnResume field value
         * @return this model
         */
        public ResumeRequest terminateOnResume(boolean terminateOnResume) {
            set("terminateOnResume", terminateOnResume);
            return this;
        }
    }
    /**
     * Searches for given string in script content.
     */
    public static final class SearchInContentRequest extends CdpObject {
        public SearchInContentRequest() {}
        /**
         * Searches for given string in script content.
         * @param scriptId protocol value
         * @param query protocol value
         */
        public SearchInContentRequest(Runtime.ScriptId scriptId, String query) {
            scriptId(scriptId).query(query);
        }
        public static SearchInContentRequest fromMap(Map<String, Object> values) {
            final SearchInContentRequest instance_ = new SearchInContentRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the script to search in.
         * @return the protocol field value
         */
        public Runtime.ScriptId scriptId() {
            return new Runtime.ScriptId((String) require("scriptId"));
        }
        /**
         * String to search for.
         * @return the protocol field value
         */
        public String query() {
            return (String) require("query");
        }
        /**
         * If true, search is case sensitive.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> caseSensitive() {
            return raw("caseSensitive").map(value -> (Boolean) value);
        }
        /**
         * If true, treats string parameter as regex.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> isRegex() {
            return raw("isRegex").map(value -> (Boolean) value);
        }
        /**
         * Id of the script to search in.
         * @param scriptId field value
         * @return this model
         */
        public SearchInContentRequest scriptId(Runtime.ScriptId scriptId) {
            set("scriptId", scriptId);
            return this;
        }
        /**
         * String to search for.
         * @param query field value
         * @return this model
         */
        public SearchInContentRequest query(String query) {
            set("query", query);
            return this;
        }
        /**
         * If true, search is case sensitive.
         * @param caseSensitive field value
         * @return this model
         */
        public SearchInContentRequest caseSensitive(boolean caseSensitive) {
            set("caseSensitive", caseSensitive);
            return this;
        }
        /**
         * If true, treats string parameter as regex.
         * @param isRegex field value
         * @return this model
         */
        public SearchInContentRequest isRegex(boolean isRegex) {
            set("isRegex", isRegex);
            return this;
        }
    }
    /**
     * Enables or disables async call stacks tracking.
     */
    public static final class SetAsyncCallStackDepthRequest extends CdpObject {
        public SetAsyncCallStackDepthRequest() {}
        /**
         * Enables or disables async call stacks tracking.
         * @param maxDepth protocol value
         */
        public SetAsyncCallStackDepthRequest(long maxDepth) {
            maxDepth(maxDepth);
        }
        public static SetAsyncCallStackDepthRequest fromMap(Map<String, Object> values) {
            final SetAsyncCallStackDepthRequest instance_ = new SetAsyncCallStackDepthRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Maximum depth of async call stacks. Setting to {@code 0} will effectively disable collecting async call stacks (default).
         * @return the protocol field value
         */
        public long maxDepth() {
            return ((Number) require("maxDepth")).longValue();
        }
        /**
         * Maximum depth of async call stacks. Setting to {@code 0} will effectively disable collecting async call stacks (default).
         * @param maxDepth field value
         * @return this model
         */
        public SetAsyncCallStackDepthRequest maxDepth(long maxDepth) {
            set("maxDepth", maxDepth);
            return this;
        }
    }
    /**
     * Replace previous blackbox execution contexts with passed ones. Forces backend to skip stepping/pausing in scripts in these execution contexts. VM will try to leave blackboxed script by performing &#x27;step in&#x27; several times, finally resorting to &#x27;step out&#x27; if unsuccessful.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetBlackboxExecutionContextsRequest extends CdpObject {
        public SetBlackboxExecutionContextsRequest() {}
        /**
         * Replace previous blackbox execution contexts with passed ones. Forces backend to skip stepping/pausing in scripts in these execution contexts. VM will try to leave blackboxed script by performing &#x27;step in&#x27; several times, finally resorting to &#x27;step out&#x27; if unsuccessful.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param uniqueIds protocol value
         */
        public SetBlackboxExecutionContextsRequest(java.util.List<String> uniqueIds) {
            uniqueIds(uniqueIds);
        }
        public static SetBlackboxExecutionContextsRequest fromMap(Map<String, Object> values) {
            final SetBlackboxExecutionContextsRequest instance_ = new SetBlackboxExecutionContextsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Array of execution context unique ids for the debugger to ignore.
         * @return the protocol field value
         */
        public java.util.List<String> uniqueIds() {
            return CdpObject.requireList(require("uniqueIds"), element0 -> (String) element0);
        }
        /**
         * Array of execution context unique ids for the debugger to ignore.
         * @param uniqueIds field value
         * @return this model
         */
        public SetBlackboxExecutionContextsRequest uniqueIds(java.util.List<String> uniqueIds) {
            set("uniqueIds", uniqueIds);
            return this;
        }
    }
    /**
     * Replace previous blackbox patterns with passed ones. Forces backend to skip stepping/pausing in scripts with url matching one of the patterns. VM will try to leave blackboxed script by performing &#x27;step in&#x27; several times, finally resorting to &#x27;step out&#x27; if unsuccessful.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetBlackboxPatternsRequest extends CdpObject {
        public SetBlackboxPatternsRequest() {}
        /**
         * Replace previous blackbox patterns with passed ones. Forces backend to skip stepping/pausing in scripts with url matching one of the patterns. VM will try to leave blackboxed script by performing &#x27;step in&#x27; several times, finally resorting to &#x27;step out&#x27; if unsuccessful.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param patterns protocol value
         */
        public SetBlackboxPatternsRequest(java.util.List<String> patterns) {
            patterns(patterns);
        }
        public static SetBlackboxPatternsRequest fromMap(Map<String, Object> values) {
            final SetBlackboxPatternsRequest instance_ = new SetBlackboxPatternsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Array of regexps that will be used to check script url for blackbox state.
         * @return the protocol field value
         */
        public java.util.List<String> patterns() {
            return CdpObject.requireList(require("patterns"), element0 -> (String) element0);
        }
        /**
         * If true, also ignore scripts with no source url.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> skipAnonymous() {
            return raw("skipAnonymous").map(value -> (Boolean) value);
        }
        /**
         * Array of regexps that will be used to check script url for blackbox state.
         * @param patterns field value
         * @return this model
         */
        public SetBlackboxPatternsRequest patterns(java.util.List<String> patterns) {
            set("patterns", patterns);
            return this;
        }
        /**
         * If true, also ignore scripts with no source url.
         * @param skipAnonymous field value
         * @return this model
         */
        public SetBlackboxPatternsRequest skipAnonymous(boolean skipAnonymous) {
            set("skipAnonymous", skipAnonymous);
            return this;
        }
    }
    /**
     * Makes backend skip steps in the script in blackboxed ranges. VM will try leave blacklisted scripts by performing &#x27;step in&#x27; several times, finally resorting to &#x27;step out&#x27; if unsuccessful. Positions array contains positions where blackbox state is changed. First interval isn&#x27;t blackboxed. Array should be sorted.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetBlackboxedRangesRequest extends CdpObject {
        public SetBlackboxedRangesRequest() {}
        /**
         * Makes backend skip steps in the script in blackboxed ranges. VM will try leave blacklisted scripts by performing &#x27;step in&#x27; several times, finally resorting to &#x27;step out&#x27; if unsuccessful. Positions array contains positions where blackbox state is changed. First interval isn&#x27;t blackboxed. Array should be sorted.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param scriptId protocol value
         * @param positions protocol value
         */
        public SetBlackboxedRangesRequest(Runtime.ScriptId scriptId, java.util.List<Debugger.ScriptPosition> positions) {
            scriptId(scriptId).positions(positions);
        }
        public static SetBlackboxedRangesRequest fromMap(Map<String, Object> values) {
            final SetBlackboxedRangesRequest instance_ = new SetBlackboxedRangesRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the script.
         * @return the protocol field value
         */
        public Runtime.ScriptId scriptId() {
            return new Runtime.ScriptId((String) require("scriptId"));
        }
        /**
         * Returns the positions field.
         * @return the protocol field value
         */
        public java.util.List<Debugger.ScriptPosition> positions() {
            return CdpObject.requireList(require("positions"), element0 -> java.util.Objects.requireNonNull(Debugger.ScriptPosition.fromMap(CdpObject.requireObjectMap(element0))));
        }
        /**
         * Id of the script.
         * @param scriptId field value
         * @return this model
         */
        public SetBlackboxedRangesRequest scriptId(Runtime.ScriptId scriptId) {
            set("scriptId", scriptId);
            return this;
        }
        /**
         * Sets the positions field.
         * @param positions field value
         * @return this model
         */
        public SetBlackboxedRangesRequest positions(java.util.List<Debugger.ScriptPosition> positions) {
            set("positions", positions);
            return this;
        }
    }
    /**
     * Sets JavaScript breakpoint at a given location.
     */
    public static final class SetBreakpointRequest extends CdpObject {
        public SetBreakpointRequest() {}
        /**
         * Sets JavaScript breakpoint at a given location.
         * @param location protocol value
         */
        public SetBreakpointRequest(Debugger.Location location) {
            location(location);
        }
        public static SetBreakpointRequest fromMap(Map<String, Object> values) {
            final SetBreakpointRequest instance_ = new SetBreakpointRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Location to set breakpoint in.
         * @return the protocol field value
         */
        public Debugger.Location location() {
            return java.util.Objects.requireNonNull(Debugger.Location.fromMap(CdpObject.requireObjectMap(require("location"))));
        }
        /**
         * Expression to use as a breakpoint condition. When specified, debugger will only stop on the breakpoint if this expression evaluates to true.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> condition() {
            return raw("condition").map(value -> (String) value);
        }
        /**
         * Location to set breakpoint in.
         * @param location field value
         * @return this model
         */
        public SetBreakpointRequest location(Debugger.Location location) {
            set("location", location);
            return this;
        }
        /**
         * Expression to use as a breakpoint condition. When specified, debugger will only stop on the breakpoint if this expression evaluates to true.
         * @param condition field value
         * @return this model
         */
        public SetBreakpointRequest condition(String condition) {
            set("condition", condition);
            return this;
        }
    }
    /**
     * Sets instrumentation breakpoint.
     */
    public static final class SetInstrumentationBreakpointRequest extends CdpObject {
        public SetInstrumentationBreakpointRequest() {}
        /**
         * Sets instrumentation breakpoint.
         * @param instrumentation protocol value
         */
        public SetInstrumentationBreakpointRequest(SetInstrumentationBreakpointInstrumentationValues instrumentation) {
            instrumentation(instrumentation);
        }
        public static SetInstrumentationBreakpointRequest fromMap(Map<String, Object> values) {
            final SetInstrumentationBreakpointRequest instance_ = new SetInstrumentationBreakpointRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Instrumentation name.
         * @return the protocol field value
         */
        public SetInstrumentationBreakpointInstrumentationValues instrumentation() {
            return SetInstrumentationBreakpointInstrumentationValues.of((String) require("instrumentation"));
        }
        /**
         * Instrumentation name.
         * @param instrumentation field value
         * @return this model
         */
        public SetInstrumentationBreakpointRequest instrumentation(SetInstrumentationBreakpointInstrumentationValues instrumentation) {
            set("instrumentation", instrumentation);
            return this;
        }
    }
    /**
     * Sets JavaScript breakpoint at given location specified either by URL or URL regex. Once this command is issued, all existing parsed scripts will have breakpoints resolved and returned in {@code locations} property. Further matching script parsing will result in subsequent {@code breakpointResolved} events issued. This logical breakpoint will survive page reloads.
     */
    public static final class SetBreakpointByUrlRequest extends CdpObject {
        public SetBreakpointByUrlRequest() {}
        /**
         * Sets JavaScript breakpoint at given location specified either by URL or URL regex. Once this command is issued, all existing parsed scripts will have breakpoints resolved and returned in {@code locations} property. Further matching script parsing will result in subsequent {@code breakpointResolved} events issued. This logical breakpoint will survive page reloads.
         * @param lineNumber protocol value
         */
        public SetBreakpointByUrlRequest(long lineNumber) {
            lineNumber(lineNumber);
        }
        public static SetBreakpointByUrlRequest fromMap(Map<String, Object> values) {
            final SetBreakpointByUrlRequest instance_ = new SetBreakpointByUrlRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Line number to set breakpoint at.
         * @return the protocol field value
         */
        public long lineNumber() {
            return ((Number) require("lineNumber")).longValue();
        }
        /**
         * URL of the resources to set breakpoint on.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> url() {
            return raw("url").map(value -> (String) value);
        }
        /**
         * Regex pattern for the URLs of the resources to set breakpoints on. Either {@code url} or {@code urlRegex} must be specified.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> urlRegex() {
            return raw("urlRegex").map(value -> (String) value);
        }
        /**
         * Script hash of the resources to set breakpoint on.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> scriptHash() {
            return raw("scriptHash").map(value -> (String) value);
        }
        /**
         * Offset in the line to set breakpoint at.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong columnNumber() {
            return raw("columnNumber").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Expression to use as a breakpoint condition. When specified, debugger will only stop on the breakpoint if this expression evaluates to true.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> condition() {
            return raw("condition").map(value -> (String) value);
        }
        /**
         * Line number to set breakpoint at.
         * @param lineNumber field value
         * @return this model
         */
        public SetBreakpointByUrlRequest lineNumber(long lineNumber) {
            set("lineNumber", lineNumber);
            return this;
        }
        /**
         * URL of the resources to set breakpoint on.
         * @param url field value
         * @return this model
         */
        public SetBreakpointByUrlRequest url(String url) {
            set("url", url);
            return this;
        }
        /**
         * Regex pattern for the URLs of the resources to set breakpoints on. Either {@code url} or {@code urlRegex} must be specified.
         * @param urlRegex field value
         * @return this model
         */
        public SetBreakpointByUrlRequest urlRegex(String urlRegex) {
            set("urlRegex", urlRegex);
            return this;
        }
        /**
         * Script hash of the resources to set breakpoint on.
         * @param scriptHash field value
         * @return this model
         */
        public SetBreakpointByUrlRequest scriptHash(String scriptHash) {
            set("scriptHash", scriptHash);
            return this;
        }
        /**
         * Offset in the line to set breakpoint at.
         * @param columnNumber field value
         * @return this model
         */
        public SetBreakpointByUrlRequest columnNumber(long columnNumber) {
            set("columnNumber", columnNumber);
            return this;
        }
        /**
         * Expression to use as a breakpoint condition. When specified, debugger will only stop on the breakpoint if this expression evaluates to true.
         * @param condition field value
         * @return this model
         */
        public SetBreakpointByUrlRequest condition(String condition) {
            set("condition", condition);
            return this;
        }
    }
    /**
     * Sets JavaScript breakpoint before each call to the given function. If another function was created from the same source as a given one, calling it will also trigger the breakpoint.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetBreakpointOnFunctionCallRequest extends CdpObject {
        public SetBreakpointOnFunctionCallRequest() {}
        /**
         * Sets JavaScript breakpoint before each call to the given function. If another function was created from the same source as a given one, calling it will also trigger the breakpoint.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param objectId protocol value
         */
        public SetBreakpointOnFunctionCallRequest(Runtime.RemoteObjectId objectId) {
            objectId(objectId);
        }
        public static SetBreakpointOnFunctionCallRequest fromMap(Map<String, Object> values) {
            final SetBreakpointOnFunctionCallRequest instance_ = new SetBreakpointOnFunctionCallRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Function object id.
         * @return the protocol field value
         */
        public Runtime.RemoteObjectId objectId() {
            return new Runtime.RemoteObjectId((String) require("objectId"));
        }
        /**
         * Expression to use as a breakpoint condition. When specified, debugger will stop on the breakpoint if this expression evaluates to true.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> condition() {
            return raw("condition").map(value -> (String) value);
        }
        /**
         * Function object id.
         * @param objectId field value
         * @return this model
         */
        public SetBreakpointOnFunctionCallRequest objectId(Runtime.RemoteObjectId objectId) {
            set("objectId", objectId);
            return this;
        }
        /**
         * Expression to use as a breakpoint condition. When specified, debugger will stop on the breakpoint if this expression evaluates to true.
         * @param condition field value
         * @return this model
         */
        public SetBreakpointOnFunctionCallRequest condition(String condition) {
            set("condition", condition);
            return this;
        }
    }
    /**
     * Activates / deactivates all breakpoints on the page.
     */
    public static final class SetBreakpointsActiveRequest extends CdpObject {
        public SetBreakpointsActiveRequest() {}
        /**
         * Activates / deactivates all breakpoints on the page.
         * @param active protocol value
         */
        public SetBreakpointsActiveRequest(boolean active) {
            active(active);
        }
        public static SetBreakpointsActiveRequest fromMap(Map<String, Object> values) {
            final SetBreakpointsActiveRequest instance_ = new SetBreakpointsActiveRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * New value for breakpoints active state.
         * @return the protocol field value
         */
        public boolean active() {
            return (Boolean) require("active");
        }
        /**
         * New value for breakpoints active state.
         * @param active field value
         * @return this model
         */
        public SetBreakpointsActiveRequest active(boolean active) {
            set("active", active);
            return this;
        }
    }
    /**
     * Defines pause on exceptions state. Can be set to stop on all exceptions, uncaught exceptions, or caught exceptions, no exceptions. Initial pause on exceptions state is {@code none}.
     */
    public static final class SetPauseOnExceptionsRequest extends CdpObject {
        public SetPauseOnExceptionsRequest() {}
        /**
         * Defines pause on exceptions state. Can be set to stop on all exceptions, uncaught exceptions, or caught exceptions, no exceptions. Initial pause on exceptions state is {@code none}.
         * @param state protocol value
         */
        public SetPauseOnExceptionsRequest(SetPauseOnExceptionsStateValues state) {
            state(state);
        }
        public static SetPauseOnExceptionsRequest fromMap(Map<String, Object> values) {
            final SetPauseOnExceptionsRequest instance_ = new SetPauseOnExceptionsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Pause on exceptions mode.
         * @return the protocol field value
         */
        public SetPauseOnExceptionsStateValues state() {
            return SetPauseOnExceptionsStateValues.of((String) require("state"));
        }
        /**
         * Pause on exceptions mode.
         * @param state field value
         * @return this model
         */
        public SetPauseOnExceptionsRequest state(SetPauseOnExceptionsStateValues state) {
            set("state", state);
            return this;
        }
    }
    /**
     * Changes return value in top frame. Available only at return break position.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetReturnValueRequest extends CdpObject {
        public SetReturnValueRequest() {}
        /**
         * Changes return value in top frame. Available only at return break position.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param newValue protocol value
         */
        public SetReturnValueRequest(Runtime.CallArgument newValue) {
            newValue(newValue);
        }
        public static SetReturnValueRequest fromMap(Map<String, Object> values) {
            final SetReturnValueRequest instance_ = new SetReturnValueRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * New return value.
         * @return the protocol field value
         */
        public Runtime.CallArgument newValue() {
            return java.util.Objects.requireNonNull(Runtime.CallArgument.fromMap(CdpObject.requireObjectMap(require("newValue"))));
        }
        /**
         * New return value.
         * @param newValue field value
         * @return this model
         */
        public SetReturnValueRequest newValue(Runtime.CallArgument newValue) {
            set("newValue", newValue);
            return this;
        }
    }
    /**
     * Edits JavaScript source live.
     * <p>In general, functions that are currently on the stack can not be edited with a single exception: If the edited function is the top-most stack frame and that is the only activation of that function on the stack. In this case the live edit will be successful and a {@code Debugger.restartFrame} for the top-most function is automatically triggered.
     */
    public static final class SetScriptSourceRequest extends CdpObject {
        public SetScriptSourceRequest() {}
        /**
         * Edits JavaScript source live.
         * <p>In general, functions that are currently on the stack can not be edited with a single exception: If the edited function is the top-most stack frame and that is the only activation of that function on the stack. In this case the live edit will be successful and a {@code Debugger.restartFrame} for the top-most function is automatically triggered.
         * @param scriptId protocol value
         * @param scriptSource protocol value
         */
        public SetScriptSourceRequest(Runtime.ScriptId scriptId, String scriptSource) {
            scriptId(scriptId).scriptSource(scriptSource);
        }
        public static SetScriptSourceRequest fromMap(Map<String, Object> values) {
            final SetScriptSourceRequest instance_ = new SetScriptSourceRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the script to edit.
         * @return the protocol field value
         */
        public Runtime.ScriptId scriptId() {
            return new Runtime.ScriptId((String) require("scriptId"));
        }
        /**
         * New content of the script.
         * @return the protocol field value
         */
        public String scriptSource() {
            return (String) require("scriptSource");
        }
        /**
         * If true the change will not actually be applied. Dry run may be used to get result description without actually modifying the code.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> dryRun() {
            return raw("dryRun").map(value -> (Boolean) value);
        }
        /**
         * If true, then {@code scriptSource} is allowed to change the function on top of the stack as long as the top-most stack frame is the only activation of that function.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> allowTopFrameEditing() {
            return raw("allowTopFrameEditing").map(value -> (Boolean) value);
        }
        /**
         * Id of the script to edit.
         * @param scriptId field value
         * @return this model
         */
        public SetScriptSourceRequest scriptId(Runtime.ScriptId scriptId) {
            set("scriptId", scriptId);
            return this;
        }
        /**
         * New content of the script.
         * @param scriptSource field value
         * @return this model
         */
        public SetScriptSourceRequest scriptSource(String scriptSource) {
            set("scriptSource", scriptSource);
            return this;
        }
        /**
         * If true the change will not actually be applied. Dry run may be used to get result description without actually modifying the code.
         * @param dryRun field value
         * @return this model
         */
        public SetScriptSourceRequest dryRun(boolean dryRun) {
            set("dryRun", dryRun);
            return this;
        }
        /**
         * If true, then {@code scriptSource} is allowed to change the function on top of the stack as long as the top-most stack frame is the only activation of that function.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param allowTopFrameEditing field value
         * @return this model
         */
        public SetScriptSourceRequest allowTopFrameEditing(boolean allowTopFrameEditing) {
            set("allowTopFrameEditing", allowTopFrameEditing);
            return this;
        }
    }
    /**
     * Makes page not interrupt on any pauses (breakpoint, exception, dom exception etc).
     */
    public static final class SetSkipAllPausesRequest extends CdpObject {
        public SetSkipAllPausesRequest() {}
        /**
         * Makes page not interrupt on any pauses (breakpoint, exception, dom exception etc).
         * @param skip protocol value
         */
        public SetSkipAllPausesRequest(boolean skip) {
            skip(skip);
        }
        public static SetSkipAllPausesRequest fromMap(Map<String, Object> values) {
            final SetSkipAllPausesRequest instance_ = new SetSkipAllPausesRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * New value for skip pauses state.
         * @return the protocol field value
         */
        public boolean skip() {
            return (Boolean) require("skip");
        }
        /**
         * New value for skip pauses state.
         * @param skip field value
         * @return this model
         */
        public SetSkipAllPausesRequest skip(boolean skip) {
            set("skip", skip);
            return this;
        }
    }
    /**
     * Changes value of variable in a callframe. Object-based scopes are not supported and must be mutated manually.
     */
    public static final class SetVariableValueRequest extends CdpObject {
        public SetVariableValueRequest() {}
        /**
         * Changes value of variable in a callframe. Object-based scopes are not supported and must be mutated manually.
         * @param scopeNumber protocol value
         * @param variableName protocol value
         * @param newValue protocol value
         * @param callFrameId protocol value
         */
        public SetVariableValueRequest(long scopeNumber, String variableName, Runtime.CallArgument newValue, Debugger.CallFrameId callFrameId) {
            scopeNumber(scopeNumber).variableName(variableName).newValue(newValue).callFrameId(callFrameId);
        }
        public static SetVariableValueRequest fromMap(Map<String, Object> values) {
            final SetVariableValueRequest instance_ = new SetVariableValueRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * 0-based number of scope as was listed in scope chain. Only &#x27;local&#x27;, &#x27;closure&#x27; and &#x27;catch&#x27; scope types are allowed. Other scopes could be manipulated manually.
         * @return the protocol field value
         */
        public long scopeNumber() {
            return ((Number) require("scopeNumber")).longValue();
        }
        /**
         * Variable name.
         * @return the protocol field value
         */
        public String variableName() {
            return (String) require("variableName");
        }
        /**
         * New variable value.
         * @return the protocol field value
         */
        public Runtime.CallArgument newValue() {
            return java.util.Objects.requireNonNull(Runtime.CallArgument.fromMap(CdpObject.requireObjectMap(require("newValue"))));
        }
        /**
         * Id of callframe that holds variable.
         * @return the protocol field value
         */
        public Debugger.CallFrameId callFrameId() {
            return new Debugger.CallFrameId((String) require("callFrameId"));
        }
        /**
         * 0-based number of scope as was listed in scope chain. Only &#x27;local&#x27;, &#x27;closure&#x27; and &#x27;catch&#x27; scope types are allowed. Other scopes could be manipulated manually.
         * @param scopeNumber field value
         * @return this model
         */
        public SetVariableValueRequest scopeNumber(long scopeNumber) {
            set("scopeNumber", scopeNumber);
            return this;
        }
        /**
         * Variable name.
         * @param variableName field value
         * @return this model
         */
        public SetVariableValueRequest variableName(String variableName) {
            set("variableName", variableName);
            return this;
        }
        /**
         * New variable value.
         * @param newValue field value
         * @return this model
         */
        public SetVariableValueRequest newValue(Runtime.CallArgument newValue) {
            set("newValue", newValue);
            return this;
        }
        /**
         * Id of callframe that holds variable.
         * @param callFrameId field value
         * @return this model
         */
        public SetVariableValueRequest callFrameId(Debugger.CallFrameId callFrameId) {
            set("callFrameId", callFrameId);
            return this;
        }
    }
    /**
     * Steps into the function call.
     */
    public static final class StepIntoRequest extends CdpObject {
        public StepIntoRequest() {}
        public static StepIntoRequest fromMap(Map<String, Object> values) {
            final StepIntoRequest instance_ = new StepIntoRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Debugger will pause on the execution of the first async task which was scheduled before next pause.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> breakOnAsyncCall() {
            return raw("breakOnAsyncCall").map(value -> (Boolean) value);
        }
        /**
         * The skipList specifies location ranges that should be skipped on step into.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Debugger.LocationRange>> skipList() {
            return raw("skipList").flatMap(value -> list(value, element0 -> java.util.Objects.requireNonNull(Debugger.LocationRange.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Debugger will pause on the execution of the first async task which was scheduled before next pause.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param breakOnAsyncCall field value
         * @return this model
         */
        public StepIntoRequest breakOnAsyncCall(boolean breakOnAsyncCall) {
            set("breakOnAsyncCall", breakOnAsyncCall);
            return this;
        }
        /**
         * The skipList specifies location ranges that should be skipped on step into.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param skipList field value
         * @return this model
         */
        public StepIntoRequest skipList(java.util.List<Debugger.LocationRange> skipList) {
            set("skipList", skipList);
            return this;
        }
    }
    /**
     * Steps over the statement.
     */
    public static final class StepOverRequest extends CdpObject {
        public StepOverRequest() {}
        public static StepOverRequest fromMap(Map<String, Object> values) {
            final StepOverRequest instance_ = new StepOverRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * The skipList specifies location ranges that should be skipped on step over.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Debugger.LocationRange>> skipList() {
            return raw("skipList").flatMap(value -> list(value, element0 -> java.util.Objects.requireNonNull(Debugger.LocationRange.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * The skipList specifies location ranges that should be skipped on step over.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param skipList field value
         * @return this model
         */
        public StepOverRequest skipList(java.util.List<Debugger.LocationRange> skipList) {
            set("skipList", skipList);
            return this;
        }
    }
    /**
     * Evaluates expression on a given call frame.
     */
    public static final class EvaluateOnCallFrameResult extends CdpObject {
        public EvaluateOnCallFrameResult() {}
        private EvaluateOnCallFrameResult(Map<String, Object> values) { super(values); }
        public static EvaluateOnCallFrameResult fromMap(Map<String, Object> values) {
            return new EvaluateOnCallFrameResult(values);
        }
        /**
         * Object wrapper for the evaluation result.
         * @return the protocol field value
         */
        public Runtime.RemoteObject result() {
            return java.util.Objects.requireNonNull(Runtime.RemoteObject.fromMap(CdpObject.requireObjectMap(require("result"))));
        }
        /**
         * Exception details.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.ExceptionDetails> exceptionDetails() {
            return raw("exceptionDetails").flatMap(CdpObject::objectMap).map(Runtime.ExceptionDetails::fromMap);
        }
        /**
         * Object wrapper for the evaluation result.
         * @param result field value
         * @return this model
         */
        public EvaluateOnCallFrameResult result(Runtime.RemoteObject result) {
            set("result", result);
            return this;
        }
        /**
         * Exception details.
         * @param exceptionDetails field value
         * @return this model
         */
        public EvaluateOnCallFrameResult exceptionDetails(Runtime.ExceptionDetails exceptionDetails) {
            set("exceptionDetails", exceptionDetails);
            return this;
        }
    }
    /**
     * Returns source for the script with given id.
     */
    public static final class GetScriptSourceResult extends CdpObject {
        public GetScriptSourceResult() {}
        private GetScriptSourceResult(Map<String, Object> values) { super(values); }
        public static GetScriptSourceResult fromMap(Map<String, Object> values) {
            return new GetScriptSourceResult(values);
        }
        /**
         * Script source (empty in case of Wasm bytecode).
         * @return the protocol field value
         */
        public String scriptSource() {
            return (String) require("scriptSource");
        }
        /**
         * Wasm bytecode. (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value, empty when absent
         */
        public Optional<String> bytecode() {
            return raw("bytecode").map(value -> (String) value);
        }
        /**
         * Script source (empty in case of Wasm bytecode).
         * @param scriptSource field value
         * @return this model
         */
        public GetScriptSourceResult scriptSource(String scriptSource) {
            set("scriptSource", scriptSource);
            return this;
        }
        /**
         * Wasm bytecode. (Encoded as a base64 string when passed over JSON)
         * @param bytecode field value
         * @return this model
         */
        public GetScriptSourceResult bytecode(String bytecode) {
            set("bytecode", bytecode);
            return this;
        }
    }
    /**
     * Result of Debugger.disassembleWasmModule.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DisassembleWasmModuleResult extends CdpObject {
        public DisassembleWasmModuleResult() {}
        private DisassembleWasmModuleResult(Map<String, Object> values) { super(values); }
        public static DisassembleWasmModuleResult fromMap(Map<String, Object> values) {
            return new DisassembleWasmModuleResult(values);
        }
        /**
         * For large modules, return a stream from which additional chunks of disassembly can be read successively.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> streamId() {
            return raw("streamId").map(value -> (String) value);
        }
        /**
         * The total number of lines in the disassembly text.
         * @return the protocol field value
         */
        public long totalNumberOfLines() {
            return ((Number) require("totalNumberOfLines")).longValue();
        }
        /**
         * The offsets of all function bodies, in the format [start1, end1, start2, end2, ...] where all ends are exclusive.
         * @return the protocol field value
         */
        public java.util.List<Long> functionBodyOffsets() {
            return CdpObject.requireList(require("functionBodyOffsets"), element0 -> ((Number) element0).longValue());
        }
        /**
         * The first chunk of disassembly.
         * @return the protocol field value
         */
        public Debugger.WasmDisassemblyChunk chunk() {
            return java.util.Objects.requireNonNull(Debugger.WasmDisassemblyChunk.fromMap(CdpObject.requireObjectMap(require("chunk"))));
        }
        /**
         * For large modules, return a stream from which additional chunks of disassembly can be read successively.
         * @param streamId field value
         * @return this model
         */
        public DisassembleWasmModuleResult streamId(String streamId) {
            set("streamId", streamId);
            return this;
        }
        /**
         * The total number of lines in the disassembly text.
         * @param totalNumberOfLines field value
         * @return this model
         */
        public DisassembleWasmModuleResult totalNumberOfLines(long totalNumberOfLines) {
            set("totalNumberOfLines", totalNumberOfLines);
            return this;
        }
        /**
         * The offsets of all function bodies, in the format [start1, end1, start2, end2, ...] where all ends are exclusive.
         * @param functionBodyOffsets field value
         * @return this model
         */
        public DisassembleWasmModuleResult functionBodyOffsets(java.util.List<Long> functionBodyOffsets) {
            set("functionBodyOffsets", functionBodyOffsets);
            return this;
        }
        /**
         * The first chunk of disassembly.
         * @param chunk field value
         * @return this model
         */
        public DisassembleWasmModuleResult chunk(Debugger.WasmDisassemblyChunk chunk) {
            set("chunk", chunk);
            return this;
        }
    }
    /**
     * Restarts particular call frame from the beginning. The old, deprecated behavior of {@code restartFrame} is to stay paused and allow further CDP commands after a restart was scheduled. This can cause problems with restarting, so we now continue execution immediatly after it has been scheduled until we reach the beginning of the restarted frame.
     * <p>To stay back-wards compatible, {@code restartFrame} now expects a {@code mode} parameter to be present. If the {@code mode} parameter is missing, {@code restartFrame} errors out.
     * <p>The various return values are deprecated and {@code callFrames} is always empty. Use the call frames from the {@code Debugger#paused} events instead, that fires once V8 pauses at the beginning of the restarted function.
     */
    public static final class RestartFrameResult extends CdpObject {
        public RestartFrameResult() {}
        private RestartFrameResult(Map<String, Object> values) { super(values); }
        public static RestartFrameResult fromMap(Map<String, Object> values) {
            return new RestartFrameResult(values);
        }
        /**
         * New stack trace.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public java.util.List<Debugger.CallFrame> callFrames() {
            return CdpObject.requireList(require("callFrames"), element0 -> java.util.Objects.requireNonNull(Debugger.CallFrame.fromMap(CdpObject.requireObjectMap(element0))));
        }
        /**
         * Async stack trace, if any.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<Runtime.StackTrace> asyncStackTrace() {
            return raw("asyncStackTrace").flatMap(CdpObject::objectMap).map(Runtime.StackTrace::fromMap);
        }
        /**
         * Async stack trace, if any.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<Runtime.StackTraceId> asyncStackTraceId() {
            return raw("asyncStackTraceId").flatMap(CdpObject::objectMap).map(Runtime.StackTraceId::fromMap);
        }
        /**
         * New stack trace.
         * @param callFrames field value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public RestartFrameResult callFrames(java.util.List<Debugger.CallFrame> callFrames) {
            set("callFrames", callFrames);
            return this;
        }
        /**
         * Async stack trace, if any.
         * @param asyncStackTrace field value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public RestartFrameResult asyncStackTrace(Runtime.StackTrace asyncStackTrace) {
            set("asyncStackTrace", asyncStackTrace);
            return this;
        }
        /**
         * Async stack trace, if any.
         * @param asyncStackTraceId field value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public RestartFrameResult asyncStackTraceId(Runtime.StackTraceId asyncStackTraceId) {
            set("asyncStackTraceId", asyncStackTraceId);
            return this;
        }
    }
    /**
     * Sets JavaScript breakpoint at a given location.
     */
    public static final class SetBreakpointResult extends CdpObject {
        public SetBreakpointResult() {}
        private SetBreakpointResult(Map<String, Object> values) { super(values); }
        public static SetBreakpointResult fromMap(Map<String, Object> values) {
            return new SetBreakpointResult(values);
        }
        /**
         * Id of the created breakpoint for further reference.
         * @return the protocol field value
         */
        public Debugger.BreakpointId breakpointId() {
            return new Debugger.BreakpointId((String) require("breakpointId"));
        }
        /**
         * Location this breakpoint resolved into.
         * @return the protocol field value
         */
        public Debugger.Location actualLocation() {
            return java.util.Objects.requireNonNull(Debugger.Location.fromMap(CdpObject.requireObjectMap(require("actualLocation"))));
        }
        /**
         * Id of the created breakpoint for further reference.
         * @param breakpointId field value
         * @return this model
         */
        public SetBreakpointResult breakpointId(Debugger.BreakpointId breakpointId) {
            set("breakpointId", breakpointId);
            return this;
        }
        /**
         * Location this breakpoint resolved into.
         * @param actualLocation field value
         * @return this model
         */
        public SetBreakpointResult actualLocation(Debugger.Location actualLocation) {
            set("actualLocation", actualLocation);
            return this;
        }
    }
    /**
     * Sets JavaScript breakpoint at given location specified either by URL or URL regex. Once this command is issued, all existing parsed scripts will have breakpoints resolved and returned in {@code locations} property. Further matching script parsing will result in subsequent {@code breakpointResolved} events issued. This logical breakpoint will survive page reloads.
     */
    public static final class SetBreakpointByUrlResult extends CdpObject {
        public SetBreakpointByUrlResult() {}
        private SetBreakpointByUrlResult(Map<String, Object> values) { super(values); }
        public static SetBreakpointByUrlResult fromMap(Map<String, Object> values) {
            return new SetBreakpointByUrlResult(values);
        }
        /**
         * Id of the created breakpoint for further reference.
         * @return the protocol field value
         */
        public Debugger.BreakpointId breakpointId() {
            return new Debugger.BreakpointId((String) require("breakpointId"));
        }
        /**
         * List of the locations this breakpoint resolved into upon addition.
         * @return the protocol field value
         */
        public java.util.List<Debugger.Location> locations() {
            return CdpObject.requireList(require("locations"), element0 -> java.util.Objects.requireNonNull(Debugger.Location.fromMap(CdpObject.requireObjectMap(element0))));
        }
        /**
         * Id of the created breakpoint for further reference.
         * @param breakpointId field value
         * @return this model
         */
        public SetBreakpointByUrlResult breakpointId(Debugger.BreakpointId breakpointId) {
            set("breakpointId", breakpointId);
            return this;
        }
        /**
         * List of the locations this breakpoint resolved into upon addition.
         * @param locations field value
         * @return this model
         */
        public SetBreakpointByUrlResult locations(java.util.List<Debugger.Location> locations) {
            set("locations", locations);
            return this;
        }
    }
    /**
     * Edits JavaScript source live.
     * <p>In general, functions that are currently on the stack can not be edited with a single exception: If the edited function is the top-most stack frame and that is the only activation of that function on the stack. In this case the live edit will be successful and a {@code Debugger.restartFrame} for the top-most function is automatically triggered.
     */
    public static final class SetScriptSourceResult extends CdpObject {
        public SetScriptSourceResult() {}
        private SetScriptSourceResult(Map<String, Object> values) { super(values); }
        public static SetScriptSourceResult fromMap(Map<String, Object> values) {
            return new SetScriptSourceResult(values);
        }
        /**
         * Whether the operation was successful or not. Only {@code Ok} denotes a successful live edit while the other enum variants denote why the live edit failed.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         */
        public enum StatusValues implements CdpValue<String> {
            OK("Ok"),
            COMPILEERROR("CompileError"),
            BLOCKEDBYACTIVEGENERATOR("BlockedByActiveGenerator"),
            BLOCKEDBYACTIVEFUNCTION("BlockedByActiveFunction"),
            BLOCKEDBYTOPLEVELESMODULECHANGE("BlockedByTopLevelEsModuleChange");
            public final String value;
            StatusValues(String value) { this.value = value; }
            @Override public String value() { return value; }
            public static StatusValues of(String value) {
                for (StatusValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown StatusValues value: " + value);
            }
        }
        /**
         * New stack trace in case editing has happened while VM was stopped.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<java.util.List<Debugger.CallFrame>> callFrames() {
            return raw("callFrames").flatMap(value -> list(value, element0 -> java.util.Objects.requireNonNull(Debugger.CallFrame.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Whether current call stack was modified after applying the changes.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<Boolean> stackChanged() {
            return raw("stackChanged").map(value -> (Boolean) value);
        }
        /**
         * Async stack trace, if any.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<Runtime.StackTrace> asyncStackTrace() {
            return raw("asyncStackTrace").flatMap(CdpObject::objectMap).map(Runtime.StackTrace::fromMap);
        }
        /**
         * Async stack trace, if any.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<Runtime.StackTraceId> asyncStackTraceId() {
            return raw("asyncStackTraceId").flatMap(CdpObject::objectMap).map(Runtime.StackTraceId::fromMap);
        }
        /**
         * Whether the operation was successful or not. Only {@code Ok} denotes a successful live edit while the other enum variants denote why the live edit failed.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        public SetScriptSourceResult.StatusValues status() {
            return SetScriptSourceResult.StatusValues.of((String) require("status"));
        }
        /**
         * Exception details if any. Only present when {@code status} is {@code CompileError}.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.ExceptionDetails> exceptionDetails() {
            return raw("exceptionDetails").flatMap(CdpObject::objectMap).map(Runtime.ExceptionDetails::fromMap);
        }
        /**
         * New stack trace in case editing has happened while VM was stopped.
         * @param callFrames field value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public SetScriptSourceResult callFrames(java.util.List<Debugger.CallFrame> callFrames) {
            set("callFrames", callFrames);
            return this;
        }
        /**
         * Whether current call stack was modified after applying the changes.
         * @param stackChanged field value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public SetScriptSourceResult stackChanged(boolean stackChanged) {
            set("stackChanged", stackChanged);
            return this;
        }
        /**
         * Async stack trace, if any.
         * @param asyncStackTrace field value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public SetScriptSourceResult asyncStackTrace(Runtime.StackTrace asyncStackTrace) {
            set("asyncStackTrace", asyncStackTrace);
            return this;
        }
        /**
         * Async stack trace, if any.
         * @param asyncStackTraceId field value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public SetScriptSourceResult asyncStackTraceId(Runtime.StackTraceId asyncStackTraceId) {
            set("asyncStackTraceId", asyncStackTraceId);
            return this;
        }
        /**
         * Whether the operation was successful or not. Only {@code Ok} denotes a successful live edit while the other enum variants denote why the live edit failed.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param status field value
         * @return this model
         */
        public SetScriptSourceResult status(SetScriptSourceResult.StatusValues status) {
            set("status", status);
            return this;
        }
        /**
         * Exception details if any. Only present when {@code status} is {@code CompileError}.
         * @param exceptionDetails field value
         * @return this model
         */
        public SetScriptSourceResult exceptionDetails(Runtime.ExceptionDetails exceptionDetails) {
            set("exceptionDetails", exceptionDetails);
            return this;
        }
    }
    /**
     * Fired when breakpoint is resolved to an actual script and location. Deprecated in favor of {@code resolvedBreakpoints} in the {@code scriptParsed} event.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class BreakpointResolvedEvent extends CdpObject {
        public BreakpointResolvedEvent() {}
        private BreakpointResolvedEvent(Map<String, Object> values) { super(values); }
        public static BreakpointResolvedEvent fromMap(Map<String, Object> values) {
            return new BreakpointResolvedEvent(values);
        }
        /**
         * Breakpoint unique identifier.
         * @return the protocol field value
         */
        public Debugger.BreakpointId breakpointId() {
            return new Debugger.BreakpointId((String) require("breakpointId"));
        }
        /**
         * Actual breakpoint location.
         * @return the protocol field value
         */
        public Debugger.Location location() {
            return java.util.Objects.requireNonNull(Debugger.Location.fromMap(CdpObject.requireObjectMap(require("location"))));
        }
        /**
         * Breakpoint unique identifier.
         * @param breakpointId field value
         * @return this model
         */
        public BreakpointResolvedEvent breakpointId(Debugger.BreakpointId breakpointId) {
            set("breakpointId", breakpointId);
            return this;
        }
        /**
         * Actual breakpoint location.
         * @param location field value
         * @return this model
         */
        public BreakpointResolvedEvent location(Debugger.Location location) {
            set("location", location);
            return this;
        }
    }
    /**
     * Fired when the virtual machine stopped on breakpoint or exception or any other stop criteria.
     */
    public static final class PausedEvent extends CdpObject {
        public PausedEvent() {}
        private PausedEvent(Map<String, Object> values) { super(values); }
        public static PausedEvent fromMap(Map<String, Object> values) {
            return new PausedEvent(values);
        }
        /**
         * Pause reason.
         */
        public enum ReasonValues implements CdpValue<String> {
            AMBIGUOUS("ambiguous"),
            ASSERT("assert"),
            CSPVIOLATION("CSPViolation"),
            DEBUGCOMMAND("debugCommand"),
            DOM("DOM"),
            EVENTLISTENER("EventListener"),
            EXCEPTION("exception"),
            INSTRUMENTATION("instrumentation"),
            OOM("OOM"),
            OTHER("other"),
            PROMISEREJECTION("promiseRejection"),
            XHR("XHR"),
            STEP("step");
            public final String value;
            ReasonValues(String value) { this.value = value; }
            @Override public String value() { return value; }
            public static ReasonValues of(String value) {
                for (ReasonValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown ReasonValues value: " + value);
            }
        }
        /**
         * Call stack the virtual machine stopped on.
         * @return the protocol field value
         */
        public java.util.List<Debugger.CallFrame> callFrames() {
            return CdpObject.requireList(require("callFrames"), element0 -> java.util.Objects.requireNonNull(Debugger.CallFrame.fromMap(CdpObject.requireObjectMap(element0))));
        }
        /**
         * Pause reason.
         * @return the protocol field value
         */
        public PausedEvent.ReasonValues reason() {
            return PausedEvent.ReasonValues.of((String) require("reason"));
        }
        /**
         * Object containing break-specific auxiliary properties.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.Map<String, Object>> data() {
            return raw("data").flatMap(CdpObject::objectMap);
        }
        /**
         * Hit breakpoints IDs
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<String>> hitBreakpoints() {
            return raw("hitBreakpoints").flatMap(value -> list(value, element0 -> (String) element0));
        }
        /**
         * Async stack trace, if any.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.StackTrace> asyncStackTrace() {
            return raw("asyncStackTrace").flatMap(CdpObject::objectMap).map(Runtime.StackTrace::fromMap);
        }
        /**
         * Async stack trace, if any.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.StackTraceId> asyncStackTraceId() {
            return raw("asyncStackTraceId").flatMap(CdpObject::objectMap).map(Runtime.StackTraceId::fromMap);
        }
        /**
         * Never present, will be removed.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<Runtime.StackTraceId> asyncCallStackTraceId() {
            return raw("asyncCallStackTraceId").flatMap(CdpObject::objectMap).map(Runtime.StackTraceId::fromMap);
        }
        /**
         * Call stack the virtual machine stopped on.
         * @param callFrames field value
         * @return this model
         */
        public PausedEvent callFrames(java.util.List<Debugger.CallFrame> callFrames) {
            set("callFrames", callFrames);
            return this;
        }
        /**
         * Pause reason.
         * @param reason field value
         * @return this model
         */
        public PausedEvent reason(PausedEvent.ReasonValues reason) {
            set("reason", reason);
            return this;
        }
        /**
         * Object containing break-specific auxiliary properties.
         * @param data field value
         * @return this model
         */
        public PausedEvent data(java.util.Map<String, Object> data) {
            set("data", data);
            return this;
        }
        /**
         * Hit breakpoints IDs
         * @param hitBreakpoints field value
         * @return this model
         */
        public PausedEvent hitBreakpoints(java.util.List<String> hitBreakpoints) {
            set("hitBreakpoints", hitBreakpoints);
            return this;
        }
        /**
         * Async stack trace, if any.
         * @param asyncStackTrace field value
         * @return this model
         */
        public PausedEvent asyncStackTrace(Runtime.StackTrace asyncStackTrace) {
            set("asyncStackTrace", asyncStackTrace);
            return this;
        }
        /**
         * Async stack trace, if any.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param asyncStackTraceId field value
         * @return this model
         */
        public PausedEvent asyncStackTraceId(Runtime.StackTraceId asyncStackTraceId) {
            set("asyncStackTraceId", asyncStackTraceId);
            return this;
        }
        /**
         * Never present, will be removed.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param asyncCallStackTraceId field value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public PausedEvent asyncCallStackTraceId(Runtime.StackTraceId asyncCallStackTraceId) {
            set("asyncCallStackTraceId", asyncCallStackTraceId);
            return this;
        }
    }
    /**
     * Fired when the virtual machine resumed execution.
     */
    public static final class ResumedEvent extends CdpObject {
        public ResumedEvent() {}
        private ResumedEvent(Map<String, Object> values) { super(values); }
        public static ResumedEvent fromMap(Map<String, Object> values) {
            return new ResumedEvent(values);
        }
    }
    /**
     * Fired when virtual machine fails to parse the script.
     */
    public static final class ScriptFailedToParseEvent extends CdpObject {
        public ScriptFailedToParseEvent() {}
        private ScriptFailedToParseEvent(Map<String, Object> values) { super(values); }
        public static ScriptFailedToParseEvent fromMap(Map<String, Object> values) {
            return new ScriptFailedToParseEvent(values);
        }
        /**
         * Identifier of the script parsed.
         * @return the protocol field value
         */
        public Runtime.ScriptId scriptId() {
            return new Runtime.ScriptId((String) require("scriptId"));
        }
        /**
         * URL or name of the script parsed (if any).
         * @return the protocol field value
         */
        public String url() {
            return (String) require("url");
        }
        /**
         * Line offset of the script within the resource with given URL (for script tags).
         * @return the protocol field value
         */
        public long startLine() {
            return ((Number) require("startLine")).longValue();
        }
        /**
         * Column offset of the script within the resource with given URL.
         * @return the protocol field value
         */
        public long startColumn() {
            return ((Number) require("startColumn")).longValue();
        }
        /**
         * Last line of the script.
         * @return the protocol field value
         */
        public long endLine() {
            return ((Number) require("endLine")).longValue();
        }
        /**
         * Length of the last line of the script.
         * @return the protocol field value
         */
        public long endColumn() {
            return ((Number) require("endColumn")).longValue();
        }
        /**
         * Specifies script creation context.
         * @return the protocol field value
         */
        public Runtime.ExecutionContextId executionContextId() {
            return new Runtime.ExecutionContextId(((Number) require("executionContextId")).longValue());
        }
        /**
         * Content hash of the script, SHA-256.
         * @return the protocol field value
         */
        public String hash() {
            return (String) require("hash");
        }
        /**
         * For Wasm modules, the content of the {@code build_id} custom section. For JavaScript the {@code debugId} magic comment.
         * @return the protocol field value
         */
        public String buildId() {
            return (String) require("buildId");
        }
        /**
         * Embedder-specific auxiliary data likely matching {isDefault: boolean, type: &#x27;default&#x27;|&#x27;isolated&#x27;|&#x27;worker&#x27;, frameId: string}
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.Map<String, Object>> executionContextAuxData() {
            return raw("executionContextAuxData").flatMap(CdpObject::objectMap);
        }
        /**
         * URL of source map associated with script (if any).
         * @return the protocol field value, empty when absent
         */
        public Optional<String> sourceMapURL() {
            return raw("sourceMapURL").map(value -> (String) value);
        }
        /**
         * True, if this script has sourceURL.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> hasSourceURL() {
            return raw("hasSourceURL").map(value -> (Boolean) value);
        }
        /**
         * True, if this script is ES6 module.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> isModule() {
            return raw("isModule").map(value -> (Boolean) value);
        }
        /**
         * This script length.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong length() {
            return raw("length").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * JavaScript top stack frame of where the script parsed event was triggered if available.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.StackTrace> stackTrace() {
            return raw("stackTrace").flatMap(CdpObject::objectMap).map(Runtime.StackTrace::fromMap);
        }
        /**
         * If the scriptLanguage is WebAssembly, the code section offset in the module.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong codeOffset() {
            return raw("codeOffset").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * The language of the script.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Debugger.ScriptLanguage> scriptLanguage() {
            return raw("scriptLanguage").map(value -> Debugger.ScriptLanguage.of((String) value));
        }
        /**
         * The name the embedder supplied for this script.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> embedderName() {
            return raw("embedderName").map(value -> (String) value);
        }
        /**
         * Identifier of the script parsed.
         * @param scriptId field value
         * @return this model
         */
        public ScriptFailedToParseEvent scriptId(Runtime.ScriptId scriptId) {
            set("scriptId", scriptId);
            return this;
        }
        /**
         * URL or name of the script parsed (if any).
         * @param url field value
         * @return this model
         */
        public ScriptFailedToParseEvent url(String url) {
            set("url", url);
            return this;
        }
        /**
         * Line offset of the script within the resource with given URL (for script tags).
         * @param startLine field value
         * @return this model
         */
        public ScriptFailedToParseEvent startLine(long startLine) {
            set("startLine", startLine);
            return this;
        }
        /**
         * Column offset of the script within the resource with given URL.
         * @param startColumn field value
         * @return this model
         */
        public ScriptFailedToParseEvent startColumn(long startColumn) {
            set("startColumn", startColumn);
            return this;
        }
        /**
         * Last line of the script.
         * @param endLine field value
         * @return this model
         */
        public ScriptFailedToParseEvent endLine(long endLine) {
            set("endLine", endLine);
            return this;
        }
        /**
         * Length of the last line of the script.
         * @param endColumn field value
         * @return this model
         */
        public ScriptFailedToParseEvent endColumn(long endColumn) {
            set("endColumn", endColumn);
            return this;
        }
        /**
         * Specifies script creation context.
         * @param executionContextId field value
         * @return this model
         */
        public ScriptFailedToParseEvent executionContextId(Runtime.ExecutionContextId executionContextId) {
            set("executionContextId", executionContextId);
            return this;
        }
        /**
         * Content hash of the script, SHA-256.
         * @param hash field value
         * @return this model
         */
        public ScriptFailedToParseEvent hash(String hash) {
            set("hash", hash);
            return this;
        }
        /**
         * For Wasm modules, the content of the {@code build_id} custom section. For JavaScript the {@code debugId} magic comment.
         * @param buildId field value
         * @return this model
         */
        public ScriptFailedToParseEvent buildId(String buildId) {
            set("buildId", buildId);
            return this;
        }
        /**
         * Embedder-specific auxiliary data likely matching {isDefault: boolean, type: &#x27;default&#x27;|&#x27;isolated&#x27;|&#x27;worker&#x27;, frameId: string}
         * @param executionContextAuxData field value
         * @return this model
         */
        public ScriptFailedToParseEvent executionContextAuxData(java.util.Map<String, Object> executionContextAuxData) {
            set("executionContextAuxData", executionContextAuxData);
            return this;
        }
        /**
         * URL of source map associated with script (if any).
         * @param sourceMapURL field value
         * @return this model
         */
        public ScriptFailedToParseEvent sourceMapURL(String sourceMapURL) {
            set("sourceMapURL", sourceMapURL);
            return this;
        }
        /**
         * True, if this script has sourceURL.
         * @param hasSourceURL field value
         * @return this model
         */
        public ScriptFailedToParseEvent hasSourceURL(boolean hasSourceURL) {
            set("hasSourceURL", hasSourceURL);
            return this;
        }
        /**
         * True, if this script is ES6 module.
         * @param isModule field value
         * @return this model
         */
        public ScriptFailedToParseEvent isModule(boolean isModule) {
            set("isModule", isModule);
            return this;
        }
        /**
         * This script length.
         * @param length field value
         * @return this model
         */
        public ScriptFailedToParseEvent length(long length) {
            set("length", length);
            return this;
        }
        /**
         * JavaScript top stack frame of where the script parsed event was triggered if available.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param stackTrace field value
         * @return this model
         */
        public ScriptFailedToParseEvent stackTrace(Runtime.StackTrace stackTrace) {
            set("stackTrace", stackTrace);
            return this;
        }
        /**
         * If the scriptLanguage is WebAssembly, the code section offset in the module.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param codeOffset field value
         * @return this model
         */
        public ScriptFailedToParseEvent codeOffset(long codeOffset) {
            set("codeOffset", codeOffset);
            return this;
        }
        /**
         * The language of the script.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param scriptLanguage field value
         * @return this model
         */
        public ScriptFailedToParseEvent scriptLanguage(Debugger.ScriptLanguage scriptLanguage) {
            set("scriptLanguage", scriptLanguage);
            return this;
        }
        /**
         * The name the embedder supplied for this script.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param embedderName field value
         * @return this model
         */
        public ScriptFailedToParseEvent embedderName(String embedderName) {
            set("embedderName", embedderName);
            return this;
        }
    }
    /**
     * Fired when virtual machine parses script. This event is also fired for all known and uncollected scripts upon enabling debugger.
     */
    public static final class ScriptParsedEvent extends CdpObject {
        public ScriptParsedEvent() {}
        private ScriptParsedEvent(Map<String, Object> values) { super(values); }
        public static ScriptParsedEvent fromMap(Map<String, Object> values) {
            return new ScriptParsedEvent(values);
        }
        /**
         * Identifier of the script parsed.
         * @return the protocol field value
         */
        public Runtime.ScriptId scriptId() {
            return new Runtime.ScriptId((String) require("scriptId"));
        }
        /**
         * URL or name of the script parsed (if any).
         * @return the protocol field value
         */
        public String url() {
            return (String) require("url");
        }
        /**
         * Line offset of the script within the resource with given URL (for script tags).
         * @return the protocol field value
         */
        public long startLine() {
            return ((Number) require("startLine")).longValue();
        }
        /**
         * Column offset of the script within the resource with given URL.
         * @return the protocol field value
         */
        public long startColumn() {
            return ((Number) require("startColumn")).longValue();
        }
        /**
         * Last line of the script.
         * @return the protocol field value
         */
        public long endLine() {
            return ((Number) require("endLine")).longValue();
        }
        /**
         * Length of the last line of the script.
         * @return the protocol field value
         */
        public long endColumn() {
            return ((Number) require("endColumn")).longValue();
        }
        /**
         * Specifies script creation context.
         * @return the protocol field value
         */
        public Runtime.ExecutionContextId executionContextId() {
            return new Runtime.ExecutionContextId(((Number) require("executionContextId")).longValue());
        }
        /**
         * Content hash of the script, SHA-256.
         * @return the protocol field value
         */
        public String hash() {
            return (String) require("hash");
        }
        /**
         * For Wasm modules, the content of the {@code build_id} custom section. For JavaScript the {@code debugId} magic comment.
         * @return the protocol field value
         */
        public String buildId() {
            return (String) require("buildId");
        }
        /**
         * Embedder-specific auxiliary data likely matching {isDefault: boolean, type: &#x27;default&#x27;|&#x27;isolated&#x27;|&#x27;worker&#x27;, frameId: string}
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.Map<String, Object>> executionContextAuxData() {
            return raw("executionContextAuxData").flatMap(CdpObject::objectMap);
        }
        /**
         * True, if this script is generated as a result of the live edit operation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> isLiveEdit() {
            return raw("isLiveEdit").map(value -> (Boolean) value);
        }
        /**
         * URL of source map associated with script (if any).
         * @return the protocol field value, empty when absent
         */
        public Optional<String> sourceMapURL() {
            return raw("sourceMapURL").map(value -> (String) value);
        }
        /**
         * True, if this script has sourceURL.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> hasSourceURL() {
            return raw("hasSourceURL").map(value -> (Boolean) value);
        }
        /**
         * True, if this script is ES6 module.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> isModule() {
            return raw("isModule").map(value -> (Boolean) value);
        }
        /**
         * This script length.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong length() {
            return raw("length").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * JavaScript top stack frame of where the script parsed event was triggered if available.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.StackTrace> stackTrace() {
            return raw("stackTrace").flatMap(CdpObject::objectMap).map(Runtime.StackTrace::fromMap);
        }
        /**
         * If the scriptLanguage is WebAssembly, the code section offset in the module.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong codeOffset() {
            return raw("codeOffset").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * The language of the script.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Debugger.ScriptLanguage> scriptLanguage() {
            return raw("scriptLanguage").map(value -> Debugger.ScriptLanguage.of((String) value));
        }
        /**
         * If the scriptLanguage is WebAssembly, the source of debug symbols for the module.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Debugger.DebugSymbols>> debugSymbols() {
            return raw("debugSymbols").flatMap(value -> list(value, element0 -> java.util.Objects.requireNonNull(Debugger.DebugSymbols.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * The name the embedder supplied for this script.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> embedderName() {
            return raw("embedderName").map(value -> (String) value);
        }
        /**
         * The list of set breakpoints in this script if calls to {@code setBreakpointByUrl} matches this script&#x27;s URL or hash. Clients that use this list can ignore the {@code breakpointResolved} event. They are equivalent.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Debugger.ResolvedBreakpoint>> resolvedBreakpoints() {
            return raw("resolvedBreakpoints").flatMap(value -> list(value, element0 -> java.util.Objects.requireNonNull(Debugger.ResolvedBreakpoint.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Identifier of the script parsed.
         * @param scriptId field value
         * @return this model
         */
        public ScriptParsedEvent scriptId(Runtime.ScriptId scriptId) {
            set("scriptId", scriptId);
            return this;
        }
        /**
         * URL or name of the script parsed (if any).
         * @param url field value
         * @return this model
         */
        public ScriptParsedEvent url(String url) {
            set("url", url);
            return this;
        }
        /**
         * Line offset of the script within the resource with given URL (for script tags).
         * @param startLine field value
         * @return this model
         */
        public ScriptParsedEvent startLine(long startLine) {
            set("startLine", startLine);
            return this;
        }
        /**
         * Column offset of the script within the resource with given URL.
         * @param startColumn field value
         * @return this model
         */
        public ScriptParsedEvent startColumn(long startColumn) {
            set("startColumn", startColumn);
            return this;
        }
        /**
         * Last line of the script.
         * @param endLine field value
         * @return this model
         */
        public ScriptParsedEvent endLine(long endLine) {
            set("endLine", endLine);
            return this;
        }
        /**
         * Length of the last line of the script.
         * @param endColumn field value
         * @return this model
         */
        public ScriptParsedEvent endColumn(long endColumn) {
            set("endColumn", endColumn);
            return this;
        }
        /**
         * Specifies script creation context.
         * @param executionContextId field value
         * @return this model
         */
        public ScriptParsedEvent executionContextId(Runtime.ExecutionContextId executionContextId) {
            set("executionContextId", executionContextId);
            return this;
        }
        /**
         * Content hash of the script, SHA-256.
         * @param hash field value
         * @return this model
         */
        public ScriptParsedEvent hash(String hash) {
            set("hash", hash);
            return this;
        }
        /**
         * For Wasm modules, the content of the {@code build_id} custom section. For JavaScript the {@code debugId} magic comment.
         * @param buildId field value
         * @return this model
         */
        public ScriptParsedEvent buildId(String buildId) {
            set("buildId", buildId);
            return this;
        }
        /**
         * Embedder-specific auxiliary data likely matching {isDefault: boolean, type: &#x27;default&#x27;|&#x27;isolated&#x27;|&#x27;worker&#x27;, frameId: string}
         * @param executionContextAuxData field value
         * @return this model
         */
        public ScriptParsedEvent executionContextAuxData(java.util.Map<String, Object> executionContextAuxData) {
            set("executionContextAuxData", executionContextAuxData);
            return this;
        }
        /**
         * True, if this script is generated as a result of the live edit operation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param isLiveEdit field value
         * @return this model
         */
        public ScriptParsedEvent isLiveEdit(boolean isLiveEdit) {
            set("isLiveEdit", isLiveEdit);
            return this;
        }
        /**
         * URL of source map associated with script (if any).
         * @param sourceMapURL field value
         * @return this model
         */
        public ScriptParsedEvent sourceMapURL(String sourceMapURL) {
            set("sourceMapURL", sourceMapURL);
            return this;
        }
        /**
         * True, if this script has sourceURL.
         * @param hasSourceURL field value
         * @return this model
         */
        public ScriptParsedEvent hasSourceURL(boolean hasSourceURL) {
            set("hasSourceURL", hasSourceURL);
            return this;
        }
        /**
         * True, if this script is ES6 module.
         * @param isModule field value
         * @return this model
         */
        public ScriptParsedEvent isModule(boolean isModule) {
            set("isModule", isModule);
            return this;
        }
        /**
         * This script length.
         * @param length field value
         * @return this model
         */
        public ScriptParsedEvent length(long length) {
            set("length", length);
            return this;
        }
        /**
         * JavaScript top stack frame of where the script parsed event was triggered if available.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param stackTrace field value
         * @return this model
         */
        public ScriptParsedEvent stackTrace(Runtime.StackTrace stackTrace) {
            set("stackTrace", stackTrace);
            return this;
        }
        /**
         * If the scriptLanguage is WebAssembly, the code section offset in the module.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param codeOffset field value
         * @return this model
         */
        public ScriptParsedEvent codeOffset(long codeOffset) {
            set("codeOffset", codeOffset);
            return this;
        }
        /**
         * The language of the script.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param scriptLanguage field value
         * @return this model
         */
        public ScriptParsedEvent scriptLanguage(Debugger.ScriptLanguage scriptLanguage) {
            set("scriptLanguage", scriptLanguage);
            return this;
        }
        /**
         * If the scriptLanguage is WebAssembly, the source of debug symbols for the module.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param debugSymbols field value
         * @return this model
         */
        public ScriptParsedEvent debugSymbols(java.util.List<Debugger.DebugSymbols> debugSymbols) {
            set("debugSymbols", debugSymbols);
            return this;
        }
        /**
         * The name the embedder supplied for this script.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param embedderName field value
         * @return this model
         */
        public ScriptParsedEvent embedderName(String embedderName) {
            set("embedderName", embedderName);
            return this;
        }
        /**
         * The list of set breakpoints in this script if calls to {@code setBreakpointByUrl} matches this script&#x27;s URL or hash. Clients that use this list can ignore the {@code breakpointResolved} event. They are equivalent.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param resolvedBreakpoints field value
         * @return this model
         */
        public ScriptParsedEvent resolvedBreakpoints(java.util.List<Debugger.ResolvedBreakpoint> resolvedBreakpoints) {
            set("resolvedBreakpoints", resolvedBreakpoints);
            return this;
        }
    }
    /**
     * Wire values for ContinueToLocationTargetCallFramesValues.
     */
    public enum ContinueToLocationTargetCallFramesValues implements CdpValue<String> {
        ANY("any"),
        CURRENT("current");
        public final String value;
        ContinueToLocationTargetCallFramesValues(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static ContinueToLocationTargetCallFramesValues of(String value) {
            for (ContinueToLocationTargetCallFramesValues constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown ContinueToLocationTargetCallFramesValues value: " + value);
        }
    }
    /**
     * The {@code mode} parameter must be present and set to &#x27;StepInto&#x27;, otherwise {@code restartFrame} will error out.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum RestartFrameModeValues implements CdpValue<String> {
        STEPINTO("StepInto");
        public final String value;
        RestartFrameModeValues(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static RestartFrameModeValues of(String value) {
            for (RestartFrameModeValues constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown RestartFrameModeValues value: " + value);
        }
    }
    /**
     * Instrumentation name.
     */
    public enum SetInstrumentationBreakpointInstrumentationValues implements CdpValue<String> {
        BEFORESCRIPTEXECUTION("beforeScriptExecution"),
        BEFORESCRIPTWITHSOURCEMAPEXECUTION("beforeScriptWithSourceMapExecution");
        public final String value;
        SetInstrumentationBreakpointInstrumentationValues(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static SetInstrumentationBreakpointInstrumentationValues of(String value) {
            for (SetInstrumentationBreakpointInstrumentationValues constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown SetInstrumentationBreakpointInstrumentationValues value: " + value);
        }
    }
    /**
     * Pause on exceptions mode.
     */
    public enum SetPauseOnExceptionsStateValues implements CdpValue<String> {
        NONE("none"),
        CAUGHT("caught"),
        UNCAUGHT("uncaught"),
        ALL("all");
        public final String value;
        SetPauseOnExceptionsStateValues(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static SetPauseOnExceptionsStateValues of(String value) {
            for (SetPauseOnExceptionsStateValues constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown SetPauseOnExceptionsStateValues value: " + value);
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Continues execution until specific location is reached.
         * @param location protocol value
         * @param targetCallFrames protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> continueToLocation(Debugger.Location location, ContinueToLocationTargetCallFramesValues targetCallFrames) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("location", CdpObject.json(location));
            params.put("targetCallFrames", CdpObject.json(targetCallFrames));
            return client.call("Debugger.continueToLocation", params);
        }
        /**
         * Continues execution until specific location is reached.
         * @param location protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> continueToLocation(Debugger.Location location) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("location", CdpObject.json(location));
            return client.call("Debugger.continueToLocation", params);
        }
        /**
         * Continues execution until specific location is reached.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> continueToLocation(ContinueToLocationRequest request) {
            return client.call("Debugger.continueToLocation", request == null ? null : request.toMap());
        }
        /**
         * Disables debugger for given page.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return client.call("Debugger.disable", null);
        }
        /**
         * Enables debugger for the given page. Clients should not assume that the debugging has been enabled until the result for this command is received.
         * @param maxScriptsCacheSize protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Runtime.UniqueDebuggerId> enable(double maxScriptsCacheSize) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("maxScriptsCacheSize", CdpObject.json(maxScriptsCacheSize));
            return client.call("Debugger.enable", params, result_ -> new Runtime.UniqueDebuggerId((String) java.util.Objects.requireNonNull(result_.get("debuggerId"))));
        }
        /**
         * Enables debugger for the given page. Clients should not assume that the debugging has been enabled until the result for this command is received.
         * @return a stage completing with the command result
         */
        public CompletionStage<Runtime.UniqueDebuggerId> enable() {
            return client.call("Debugger.enable", null, result_ -> new Runtime.UniqueDebuggerId((String) java.util.Objects.requireNonNull(result_.get("debuggerId"))));
        }
        /**
         * Enables debugger for the given page. Clients should not assume that the debugging has been enabled until the result for this command is received.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Runtime.UniqueDebuggerId> enable(EnableRequest request) {
            return client.call("Debugger.enable", request == null ? null : request.toMap(), result_ -> new Runtime.UniqueDebuggerId((String) java.util.Objects.requireNonNull(result_.get("debuggerId"))));
        }
        /**
         * Evaluates expression on a given call frame.
         * @param callFrameId protocol value
         * @param expression protocol value
         * @param objectGroup protocol value
         * @param includeCommandLineAPI protocol value
         * @param silent protocol value
         * @param returnByValue protocol value
         * @param generatePreview protocol value
         * @param throwOnSideEffect protocol value
         * @param timeout protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<EvaluateOnCallFrameResult> evaluateOnCallFrame(Debugger.CallFrameId callFrameId, String expression, @Nullable String objectGroup, @Nullable Boolean includeCommandLineAPI, @Nullable Boolean silent, @Nullable Boolean returnByValue, @Nullable Boolean generatePreview, @Nullable Boolean throwOnSideEffect, Runtime.@Nullable TimeDelta timeout) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("callFrameId", CdpObject.json(callFrameId));
            params.put("expression", CdpObject.json(expression));
            if (objectGroup != null) params.put("objectGroup", CdpObject.json(objectGroup));
            if (includeCommandLineAPI != null) params.put("includeCommandLineAPI", CdpObject.json(includeCommandLineAPI));
            if (silent != null) params.put("silent", CdpObject.json(silent));
            if (returnByValue != null) params.put("returnByValue", CdpObject.json(returnByValue));
            if (generatePreview != null) params.put("generatePreview", CdpObject.json(generatePreview));
            if (throwOnSideEffect != null) params.put("throwOnSideEffect", CdpObject.json(throwOnSideEffect));
            if (timeout != null) params.put("timeout", CdpObject.json(timeout));
            return client.call("Debugger.evaluateOnCallFrame", params, result_ -> new EvaluateOnCallFrameResult(result_));
        }
        /**
         * Evaluates expression on a given call frame.
         * @param callFrameId protocol value
         * @param expression protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<EvaluateOnCallFrameResult> evaluateOnCallFrame(Debugger.CallFrameId callFrameId, String expression) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("callFrameId", CdpObject.json(callFrameId));
            params.put("expression", CdpObject.json(expression));
            return client.call("Debugger.evaluateOnCallFrame", params, result_ -> new EvaluateOnCallFrameResult(result_));
        }
        /**
         * Evaluates expression on a given call frame.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<EvaluateOnCallFrameResult> evaluateOnCallFrame(EvaluateOnCallFrameRequest request) {
            return client.call("Debugger.evaluateOnCallFrame", request == null ? null : request.toMap(), result_ -> new EvaluateOnCallFrameResult(result_));
        }
        /**
         * Returns possible locations for breakpoint. scriptId in start and end range locations should be the same.
         * @param start protocol value
         * @param end protocol value
         * @param restrictToFunction protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Debugger.BreakLocation>> getPossibleBreakpoints(Debugger.Location start, Debugger.@Nullable Location end, @Nullable Boolean restrictToFunction) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("start", CdpObject.json(start));
            if (end != null) params.put("end", CdpObject.json(end));
            if (restrictToFunction != null) params.put("restrictToFunction", CdpObject.json(restrictToFunction));
            return client.call("Debugger.getPossibleBreakpoints", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("locations")), element0 -> java.util.Objects.requireNonNull(Debugger.BreakLocation.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Returns possible locations for breakpoint. scriptId in start and end range locations should be the same.
         * @param start protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Debugger.BreakLocation>> getPossibleBreakpoints(Debugger.Location start) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("start", CdpObject.json(start));
            return client.call("Debugger.getPossibleBreakpoints", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("locations")), element0 -> java.util.Objects.requireNonNull(Debugger.BreakLocation.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Returns possible locations for breakpoint. scriptId in start and end range locations should be the same.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Debugger.BreakLocation>> getPossibleBreakpoints(GetPossibleBreakpointsRequest request) {
            return client.call("Debugger.getPossibleBreakpoints", request == null ? null : request.toMap(), result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("locations")), element0 -> java.util.Objects.requireNonNull(Debugger.BreakLocation.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Returns source for the script with given id.
         * @param scriptId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<GetScriptSourceResult> getScriptSource(Runtime.ScriptId scriptId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("scriptId", CdpObject.json(scriptId));
            return client.call("Debugger.getScriptSource", params, result_ -> new GetScriptSourceResult(result_));
        }
        /**
         * Returns source for the script with given id.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetScriptSourceResult> getScriptSource(GetScriptSourceRequest request) {
            return client.call("Debugger.getScriptSource", request == null ? null : request.toMap(), result_ -> new GetScriptSourceResult(result_));
        }
        /**
         * Invokes Debugger.disassembleWasmModule.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param scriptId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<DisassembleWasmModuleResult> disassembleWasmModule(Runtime.ScriptId scriptId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("scriptId", CdpObject.json(scriptId));
            return client.call("Debugger.disassembleWasmModule", params, result_ -> new DisassembleWasmModuleResult(result_));
        }
        /**
         * Invokes Debugger.disassembleWasmModule with a request object.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DisassembleWasmModuleResult> disassembleWasmModule(DisassembleWasmModuleRequest request) {
            return client.call("Debugger.disassembleWasmModule", request == null ? null : request.toMap(), result_ -> new DisassembleWasmModuleResult(result_));
        }
        /**
         * Disassemble the next chunk of lines for the module corresponding to the stream. If disassembly is complete, this API will invalidate the streamId and return an empty chunk. Any subsequent calls for the now invalid stream will return errors.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param streamId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Debugger.WasmDisassemblyChunk> nextWasmDisassemblyChunk(String streamId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("streamId", CdpObject.json(streamId));
            return client.call("Debugger.nextWasmDisassemblyChunk", params, result_ -> java.util.Objects.requireNonNull(Debugger.WasmDisassemblyChunk.fromMap(CdpObject.requireObjectMap(java.util.Objects.requireNonNull(result_.get("chunk"))))));
        }
        /**
         * Disassemble the next chunk of lines for the module corresponding to the stream. If disassembly is complete, this API will invalidate the streamId and return an empty chunk. Any subsequent calls for the now invalid stream will return errors.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Debugger.WasmDisassemblyChunk> nextWasmDisassemblyChunk(NextWasmDisassemblyChunkRequest request) {
            return client.call("Debugger.nextWasmDisassemblyChunk", request == null ? null : request.toMap(), result_ -> java.util.Objects.requireNonNull(Debugger.WasmDisassemblyChunk.fromMap(CdpObject.requireObjectMap(java.util.Objects.requireNonNull(result_.get("chunk"))))));
        }
        /**
         * This command is deprecated. Use getScriptSource instead.
         * @param scriptId protocol value
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<String> getWasmBytecode(Runtime.ScriptId scriptId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("scriptId", CdpObject.json(scriptId));
            return client.call("Debugger.getWasmBytecode", params, result_ -> (String) java.util.Objects.requireNonNull(result_.get("bytecode")));
        }
        /**
         * This command is deprecated. Use getScriptSource instead.
         * @param request request parameters
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<String> getWasmBytecode(GetWasmBytecodeRequest request) {
            return client.call("Debugger.getWasmBytecode", request == null ? null : request.toMap(), result_ -> (String) java.util.Objects.requireNonNull(result_.get("bytecode")));
        }
        /**
         * Returns stack trace with given {@code stackTraceId}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param stackTraceId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Runtime.StackTrace> getStackTrace(Runtime.StackTraceId stackTraceId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("stackTraceId", CdpObject.json(stackTraceId));
            return client.call("Debugger.getStackTrace", params, result_ -> java.util.Objects.requireNonNull(Runtime.StackTrace.fromMap(CdpObject.requireObjectMap(java.util.Objects.requireNonNull(result_.get("stackTrace"))))));
        }
        /**
         * Returns stack trace with given {@code stackTraceId}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Runtime.StackTrace> getStackTrace(GetStackTraceRequest request) {
            return client.call("Debugger.getStackTrace", request == null ? null : request.toMap(), result_ -> java.util.Objects.requireNonNull(Runtime.StackTrace.fromMap(CdpObject.requireObjectMap(java.util.Objects.requireNonNull(result_.get("stackTrace"))))));
        }
        /**
         * Stops on the next JavaScript statement.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> pause() {
            return client.call("Debugger.pause", null);
        }
        /**
         * Invokes Debugger.pauseOnAsyncCall.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param parentStackTraceId protocol value
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> pauseOnAsyncCall(Runtime.StackTraceId parentStackTraceId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("parentStackTraceId", CdpObject.json(parentStackTraceId));
            return client.call("Debugger.pauseOnAsyncCall", params);
        }
        /**
         * Invokes Debugger.pauseOnAsyncCall with a request object.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> pauseOnAsyncCall(PauseOnAsyncCallRequest request) {
            return client.call("Debugger.pauseOnAsyncCall", request == null ? null : request.toMap());
        }
        /**
         * Removes JavaScript breakpoint.
         * @param breakpointId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeBreakpoint(Debugger.BreakpointId breakpointId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("breakpointId", CdpObject.json(breakpointId));
            return client.call("Debugger.removeBreakpoint", params);
        }
        /**
         * Removes JavaScript breakpoint.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeBreakpoint(RemoveBreakpointRequest request) {
            return client.call("Debugger.removeBreakpoint", request == null ? null : request.toMap());
        }
        /**
         * Restarts particular call frame from the beginning. The old, deprecated behavior of {@code restartFrame} is to stay paused and allow further CDP commands after a restart was scheduled. This can cause problems with restarting, so we now continue execution immediatly after it has been scheduled until we reach the beginning of the restarted frame.
         * <p>To stay back-wards compatible, {@code restartFrame} now expects a {@code mode} parameter to be present. If the {@code mode} parameter is missing, {@code restartFrame} errors out.
         * <p>The various return values are deprecated and {@code callFrames} is always empty. Use the call frames from the {@code Debugger#paused} events instead, that fires once V8 pauses at the beginning of the restarted function.
         * @param callFrameId protocol value
         * @param mode protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<RestartFrameResult> restartFrame(Debugger.CallFrameId callFrameId, RestartFrameModeValues mode) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("callFrameId", CdpObject.json(callFrameId));
            params.put("mode", CdpObject.json(mode));
            return client.call("Debugger.restartFrame", params, result_ -> new RestartFrameResult(result_));
        }
        /**
         * Restarts particular call frame from the beginning. The old, deprecated behavior of {@code restartFrame} is to stay paused and allow further CDP commands after a restart was scheduled. This can cause problems with restarting, so we now continue execution immediatly after it has been scheduled until we reach the beginning of the restarted frame.
         * <p>To stay back-wards compatible, {@code restartFrame} now expects a {@code mode} parameter to be present. If the {@code mode} parameter is missing, {@code restartFrame} errors out.
         * <p>The various return values are deprecated and {@code callFrames} is always empty. Use the call frames from the {@code Debugger#paused} events instead, that fires once V8 pauses at the beginning of the restarted function.
         * @param callFrameId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<RestartFrameResult> restartFrame(Debugger.CallFrameId callFrameId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("callFrameId", CdpObject.json(callFrameId));
            return client.call("Debugger.restartFrame", params, result_ -> new RestartFrameResult(result_));
        }
        /**
         * Restarts particular call frame from the beginning. The old, deprecated behavior of {@code restartFrame} is to stay paused and allow further CDP commands after a restart was scheduled. This can cause problems with restarting, so we now continue execution immediatly after it has been scheduled until we reach the beginning of the restarted frame.
         * <p>To stay back-wards compatible, {@code restartFrame} now expects a {@code mode} parameter to be present. If the {@code mode} parameter is missing, {@code restartFrame} errors out.
         * <p>The various return values are deprecated and {@code callFrames} is always empty. Use the call frames from the {@code Debugger#paused} events instead, that fires once V8 pauses at the beginning of the restarted function.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<RestartFrameResult> restartFrame(RestartFrameRequest request) {
            return client.call("Debugger.restartFrame", request == null ? null : request.toMap(), result_ -> new RestartFrameResult(result_));
        }
        /**
         * Resumes JavaScript execution.
         * @param terminateOnResume protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> resume(boolean terminateOnResume) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("terminateOnResume", CdpObject.json(terminateOnResume));
            return client.call("Debugger.resume", params);
        }
        /**
         * Resumes JavaScript execution.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> resume() {
            return client.call("Debugger.resume", null);
        }
        /**
         * Resumes JavaScript execution.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> resume(ResumeRequest request) {
            return client.call("Debugger.resume", request == null ? null : request.toMap());
        }
        /**
         * Searches for given string in script content.
         * @param scriptId protocol value
         * @param query protocol value
         * @param caseSensitive protocol value
         * @param isRegex protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Debugger.SearchMatch>> searchInContent(Runtime.ScriptId scriptId, String query, @Nullable Boolean caseSensitive, @Nullable Boolean isRegex) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("scriptId", CdpObject.json(scriptId));
            params.put("query", CdpObject.json(query));
            if (caseSensitive != null) params.put("caseSensitive", CdpObject.json(caseSensitive));
            if (isRegex != null) params.put("isRegex", CdpObject.json(isRegex));
            return client.call("Debugger.searchInContent", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("result")), element0 -> java.util.Objects.requireNonNull(Debugger.SearchMatch.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Searches for given string in script content.
         * @param scriptId protocol value
         * @param query protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Debugger.SearchMatch>> searchInContent(Runtime.ScriptId scriptId, String query) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("scriptId", CdpObject.json(scriptId));
            params.put("query", CdpObject.json(query));
            return client.call("Debugger.searchInContent", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("result")), element0 -> java.util.Objects.requireNonNull(Debugger.SearchMatch.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Searches for given string in script content.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Debugger.SearchMatch>> searchInContent(SearchInContentRequest request) {
            return client.call("Debugger.searchInContent", request == null ? null : request.toMap(), result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("result")), element0 -> java.util.Objects.requireNonNull(Debugger.SearchMatch.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Enables or disables async call stacks tracking.
         * @param maxDepth protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAsyncCallStackDepth(long maxDepth) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("maxDepth", CdpObject.json(maxDepth));
            return client.call("Debugger.setAsyncCallStackDepth", params);
        }
        /**
         * Enables or disables async call stacks tracking.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAsyncCallStackDepth(SetAsyncCallStackDepthRequest request) {
            return client.call("Debugger.setAsyncCallStackDepth", request == null ? null : request.toMap());
        }
        /**
         * Replace previous blackbox execution contexts with passed ones. Forces backend to skip stepping/pausing in scripts in these execution contexts. VM will try to leave blackboxed script by performing &#x27;step in&#x27; several times, finally resorting to &#x27;step out&#x27; if unsuccessful.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param uniqueIds protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setBlackboxExecutionContexts(java.util.List<String> uniqueIds) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("uniqueIds", CdpObject.json(uniqueIds));
            return client.call("Debugger.setBlackboxExecutionContexts", params);
        }
        /**
         * Replace previous blackbox execution contexts with passed ones. Forces backend to skip stepping/pausing in scripts in these execution contexts. VM will try to leave blackboxed script by performing &#x27;step in&#x27; several times, finally resorting to &#x27;step out&#x27; if unsuccessful.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setBlackboxExecutionContexts(SetBlackboxExecutionContextsRequest request) {
            return client.call("Debugger.setBlackboxExecutionContexts", request == null ? null : request.toMap());
        }
        /**
         * Replace previous blackbox patterns with passed ones. Forces backend to skip stepping/pausing in scripts with url matching one of the patterns. VM will try to leave blackboxed script by performing &#x27;step in&#x27; several times, finally resorting to &#x27;step out&#x27; if unsuccessful.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param patterns protocol value
         * @param skipAnonymous protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setBlackboxPatterns(java.util.List<String> patterns, boolean skipAnonymous) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("patterns", CdpObject.json(patterns));
            params.put("skipAnonymous", CdpObject.json(skipAnonymous));
            return client.call("Debugger.setBlackboxPatterns", params);
        }
        /**
         * Replace previous blackbox patterns with passed ones. Forces backend to skip stepping/pausing in scripts with url matching one of the patterns. VM will try to leave blackboxed script by performing &#x27;step in&#x27; several times, finally resorting to &#x27;step out&#x27; if unsuccessful.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param patterns protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setBlackboxPatterns(java.util.List<String> patterns) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("patterns", CdpObject.json(patterns));
            return client.call("Debugger.setBlackboxPatterns", params);
        }
        /**
         * Replace previous blackbox patterns with passed ones. Forces backend to skip stepping/pausing in scripts with url matching one of the patterns. VM will try to leave blackboxed script by performing &#x27;step in&#x27; several times, finally resorting to &#x27;step out&#x27; if unsuccessful.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setBlackboxPatterns(SetBlackboxPatternsRequest request) {
            return client.call("Debugger.setBlackboxPatterns", request == null ? null : request.toMap());
        }
        /**
         * Makes backend skip steps in the script in blackboxed ranges. VM will try leave blacklisted scripts by performing &#x27;step in&#x27; several times, finally resorting to &#x27;step out&#x27; if unsuccessful. Positions array contains positions where blackbox state is changed. First interval isn&#x27;t blackboxed. Array should be sorted.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param scriptId protocol value
         * @param positions protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setBlackboxedRanges(Runtime.ScriptId scriptId, java.util.List<Debugger.ScriptPosition> positions) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("scriptId", CdpObject.json(scriptId));
            params.put("positions", CdpObject.json(positions));
            return client.call("Debugger.setBlackboxedRanges", params);
        }
        /**
         * Makes backend skip steps in the script in blackboxed ranges. VM will try leave blacklisted scripts by performing &#x27;step in&#x27; several times, finally resorting to &#x27;step out&#x27; if unsuccessful. Positions array contains positions where blackbox state is changed. First interval isn&#x27;t blackboxed. Array should be sorted.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setBlackboxedRanges(SetBlackboxedRangesRequest request) {
            return client.call("Debugger.setBlackboxedRanges", request == null ? null : request.toMap());
        }
        /**
         * Sets JavaScript breakpoint at a given location.
         * @param location protocol value
         * @param condition protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<SetBreakpointResult> setBreakpoint(Debugger.Location location, String condition) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("location", CdpObject.json(location));
            params.put("condition", CdpObject.json(condition));
            return client.call("Debugger.setBreakpoint", params, result_ -> new SetBreakpointResult(result_));
        }
        /**
         * Sets JavaScript breakpoint at a given location.
         * @param location protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<SetBreakpointResult> setBreakpoint(Debugger.Location location) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("location", CdpObject.json(location));
            return client.call("Debugger.setBreakpoint", params, result_ -> new SetBreakpointResult(result_));
        }
        /**
         * Sets JavaScript breakpoint at a given location.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetBreakpointResult> setBreakpoint(SetBreakpointRequest request) {
            return client.call("Debugger.setBreakpoint", request == null ? null : request.toMap(), result_ -> new SetBreakpointResult(result_));
        }
        /**
         * Sets instrumentation breakpoint.
         * @param instrumentation protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Debugger.BreakpointId> setInstrumentationBreakpoint(SetInstrumentationBreakpointInstrumentationValues instrumentation) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("instrumentation", CdpObject.json(instrumentation));
            return client.call("Debugger.setInstrumentationBreakpoint", params, result_ -> new Debugger.BreakpointId((String) java.util.Objects.requireNonNull(result_.get("breakpointId"))));
        }
        /**
         * Sets instrumentation breakpoint.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Debugger.BreakpointId> setInstrumentationBreakpoint(SetInstrumentationBreakpointRequest request) {
            return client.call("Debugger.setInstrumentationBreakpoint", request == null ? null : request.toMap(), result_ -> new Debugger.BreakpointId((String) java.util.Objects.requireNonNull(result_.get("breakpointId"))));
        }
        /**
         * Sets JavaScript breakpoint at given location specified either by URL or URL regex. Once this command is issued, all existing parsed scripts will have breakpoints resolved and returned in {@code locations} property. Further matching script parsing will result in subsequent {@code breakpointResolved} events issued. This logical breakpoint will survive page reloads.
         * @param lineNumber protocol value
         * @param url protocol value
         * @param urlRegex protocol value
         * @param scriptHash protocol value
         * @param columnNumber protocol value
         * @param condition protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<SetBreakpointByUrlResult> setBreakpointByUrl(long lineNumber, @Nullable String url, @Nullable String urlRegex, @Nullable String scriptHash, @Nullable Long columnNumber, @Nullable String condition) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("lineNumber", CdpObject.json(lineNumber));
            if (url != null) params.put("url", CdpObject.json(url));
            if (urlRegex != null) params.put("urlRegex", CdpObject.json(urlRegex));
            if (scriptHash != null) params.put("scriptHash", CdpObject.json(scriptHash));
            if (columnNumber != null) params.put("columnNumber", CdpObject.json(columnNumber));
            if (condition != null) params.put("condition", CdpObject.json(condition));
            return client.call("Debugger.setBreakpointByUrl", params, result_ -> new SetBreakpointByUrlResult(result_));
        }
        /**
         * Sets JavaScript breakpoint at given location specified either by URL or URL regex. Once this command is issued, all existing parsed scripts will have breakpoints resolved and returned in {@code locations} property. Further matching script parsing will result in subsequent {@code breakpointResolved} events issued. This logical breakpoint will survive page reloads.
         * @param lineNumber protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<SetBreakpointByUrlResult> setBreakpointByUrl(long lineNumber) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("lineNumber", CdpObject.json(lineNumber));
            return client.call("Debugger.setBreakpointByUrl", params, result_ -> new SetBreakpointByUrlResult(result_));
        }
        /**
         * Sets JavaScript breakpoint at given location specified either by URL or URL regex. Once this command is issued, all existing parsed scripts will have breakpoints resolved and returned in {@code locations} property. Further matching script parsing will result in subsequent {@code breakpointResolved} events issued. This logical breakpoint will survive page reloads.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetBreakpointByUrlResult> setBreakpointByUrl(SetBreakpointByUrlRequest request) {
            return client.call("Debugger.setBreakpointByUrl", request == null ? null : request.toMap(), result_ -> new SetBreakpointByUrlResult(result_));
        }
        /**
         * Sets JavaScript breakpoint before each call to the given function. If another function was created from the same source as a given one, calling it will also trigger the breakpoint.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param objectId protocol value
         * @param condition protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Debugger.BreakpointId> setBreakpointOnFunctionCall(Runtime.RemoteObjectId objectId, String condition) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("objectId", CdpObject.json(objectId));
            params.put("condition", CdpObject.json(condition));
            return client.call("Debugger.setBreakpointOnFunctionCall", params, result_ -> new Debugger.BreakpointId((String) java.util.Objects.requireNonNull(result_.get("breakpointId"))));
        }
        /**
         * Sets JavaScript breakpoint before each call to the given function. If another function was created from the same source as a given one, calling it will also trigger the breakpoint.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param objectId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Debugger.BreakpointId> setBreakpointOnFunctionCall(Runtime.RemoteObjectId objectId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("objectId", CdpObject.json(objectId));
            return client.call("Debugger.setBreakpointOnFunctionCall", params, result_ -> new Debugger.BreakpointId((String) java.util.Objects.requireNonNull(result_.get("breakpointId"))));
        }
        /**
         * Sets JavaScript breakpoint before each call to the given function. If another function was created from the same source as a given one, calling it will also trigger the breakpoint.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Debugger.BreakpointId> setBreakpointOnFunctionCall(SetBreakpointOnFunctionCallRequest request) {
            return client.call("Debugger.setBreakpointOnFunctionCall", request == null ? null : request.toMap(), result_ -> new Debugger.BreakpointId((String) java.util.Objects.requireNonNull(result_.get("breakpointId"))));
        }
        /**
         * Activates / deactivates all breakpoints on the page.
         * @param active protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setBreakpointsActive(boolean active) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("active", CdpObject.json(active));
            return client.call("Debugger.setBreakpointsActive", params);
        }
        /**
         * Activates / deactivates all breakpoints on the page.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setBreakpointsActive(SetBreakpointsActiveRequest request) {
            return client.call("Debugger.setBreakpointsActive", request == null ? null : request.toMap());
        }
        /**
         * Defines pause on exceptions state. Can be set to stop on all exceptions, uncaught exceptions, or caught exceptions, no exceptions. Initial pause on exceptions state is {@code none}.
         * @param state protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPauseOnExceptions(SetPauseOnExceptionsStateValues state) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("state", CdpObject.json(state));
            return client.call("Debugger.setPauseOnExceptions", params);
        }
        /**
         * Defines pause on exceptions state. Can be set to stop on all exceptions, uncaught exceptions, or caught exceptions, no exceptions. Initial pause on exceptions state is {@code none}.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPauseOnExceptions(SetPauseOnExceptionsRequest request) {
            return client.call("Debugger.setPauseOnExceptions", request == null ? null : request.toMap());
        }
        /**
         * Changes return value in top frame. Available only at return break position.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param newValue protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setReturnValue(Runtime.CallArgument newValue) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("newValue", CdpObject.json(newValue));
            return client.call("Debugger.setReturnValue", params);
        }
        /**
         * Changes return value in top frame. Available only at return break position.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setReturnValue(SetReturnValueRequest request) {
            return client.call("Debugger.setReturnValue", request == null ? null : request.toMap());
        }
        /**
         * Edits JavaScript source live.
         * <p>In general, functions that are currently on the stack can not be edited with a single exception: If the edited function is the top-most stack frame and that is the only activation of that function on the stack. In this case the live edit will be successful and a {@code Debugger.restartFrame} for the top-most function is automatically triggered.
         * @param scriptId protocol value
         * @param scriptSource protocol value
         * @param dryRun protocol value
         * @param allowTopFrameEditing protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<SetScriptSourceResult> setScriptSource(Runtime.ScriptId scriptId, String scriptSource, @Nullable Boolean dryRun, @Nullable Boolean allowTopFrameEditing) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("scriptId", CdpObject.json(scriptId));
            params.put("scriptSource", CdpObject.json(scriptSource));
            if (dryRun != null) params.put("dryRun", CdpObject.json(dryRun));
            if (allowTopFrameEditing != null) params.put("allowTopFrameEditing", CdpObject.json(allowTopFrameEditing));
            return client.call("Debugger.setScriptSource", params, result_ -> new SetScriptSourceResult(result_));
        }
        /**
         * Edits JavaScript source live.
         * <p>In general, functions that are currently on the stack can not be edited with a single exception: If the edited function is the top-most stack frame and that is the only activation of that function on the stack. In this case the live edit will be successful and a {@code Debugger.restartFrame} for the top-most function is automatically triggered.
         * @param scriptId protocol value
         * @param scriptSource protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<SetScriptSourceResult> setScriptSource(Runtime.ScriptId scriptId, String scriptSource) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("scriptId", CdpObject.json(scriptId));
            params.put("scriptSource", CdpObject.json(scriptSource));
            return client.call("Debugger.setScriptSource", params, result_ -> new SetScriptSourceResult(result_));
        }
        /**
         * Edits JavaScript source live.
         * <p>In general, functions that are currently on the stack can not be edited with a single exception: If the edited function is the top-most stack frame and that is the only activation of that function on the stack. In this case the live edit will be successful and a {@code Debugger.restartFrame} for the top-most function is automatically triggered.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetScriptSourceResult> setScriptSource(SetScriptSourceRequest request) {
            return client.call("Debugger.setScriptSource", request == null ? null : request.toMap(), result_ -> new SetScriptSourceResult(result_));
        }
        /**
         * Makes page not interrupt on any pauses (breakpoint, exception, dom exception etc).
         * @param skip protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSkipAllPauses(boolean skip) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("skip", CdpObject.json(skip));
            return client.call("Debugger.setSkipAllPauses", params);
        }
        /**
         * Makes page not interrupt on any pauses (breakpoint, exception, dom exception etc).
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSkipAllPauses(SetSkipAllPausesRequest request) {
            return client.call("Debugger.setSkipAllPauses", request == null ? null : request.toMap());
        }
        /**
         * Changes value of variable in a callframe. Object-based scopes are not supported and must be mutated manually.
         * @param scopeNumber protocol value
         * @param variableName protocol value
         * @param newValue protocol value
         * @param callFrameId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setVariableValue(long scopeNumber, String variableName, Runtime.CallArgument newValue, Debugger.CallFrameId callFrameId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("scopeNumber", CdpObject.json(scopeNumber));
            params.put("variableName", CdpObject.json(variableName));
            params.put("newValue", CdpObject.json(newValue));
            params.put("callFrameId", CdpObject.json(callFrameId));
            return client.call("Debugger.setVariableValue", params);
        }
        /**
         * Changes value of variable in a callframe. Object-based scopes are not supported and must be mutated manually.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setVariableValue(SetVariableValueRequest request) {
            return client.call("Debugger.setVariableValue", request == null ? null : request.toMap());
        }
        /**
         * Steps into the function call.
         * @param breakOnAsyncCall protocol value
         * @param skipList protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> stepInto(@Nullable Boolean breakOnAsyncCall, java.util.@Nullable List<Debugger.LocationRange> skipList) {
            final Map<String, Object> params = new LinkedHashMap<>();
            if (breakOnAsyncCall != null) params.put("breakOnAsyncCall", CdpObject.json(breakOnAsyncCall));
            if (skipList != null) params.put("skipList", CdpObject.json(skipList));
            return client.call("Debugger.stepInto", params);
        }
        /**
         * Steps into the function call.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> stepInto() {
            return client.call("Debugger.stepInto", null);
        }
        /**
         * Steps into the function call.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> stepInto(StepIntoRequest request) {
            return client.call("Debugger.stepInto", request == null ? null : request.toMap());
        }
        /**
         * Steps out of the function call.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> stepOut() {
            return client.call("Debugger.stepOut", null);
        }
        /**
         * Steps over the statement.
         * @param skipList protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> stepOver(java.util.List<Debugger.LocationRange> skipList) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("skipList", CdpObject.json(skipList));
            return client.call("Debugger.stepOver", params);
        }
        /**
         * Steps over the statement.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> stepOver() {
            return client.call("Debugger.stepOver", null);
        }
        /**
         * Steps over the statement.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> stepOver(StepOverRequest request) {
            return client.call("Debugger.stepOver", request == null ? null : request.toMap());
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
