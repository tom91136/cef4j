// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
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
 * Debugger domain exposes JavaScript debugging capabilities. It allows setting and removing breakpoints, stepping through execution, exploring stack traces, etc.
 * @see <a href="https://chromium.googlesource.com/v8/v8/+/3063ea3a0737a3fc4d4ed3babd595f1cace1e6ac/include/js_protocol.pdl">Pinned protocol source</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
public final class Debugger {
    private Debugger() {}
    /**
     * Breakpoint identifier.
     */
    public static final class BreakpointId implements CdpValue<String> {
        public final String value;
        public BreakpointId(@Nonnull String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Nonnull public String value() { return value; }
        @Override public boolean equals(Object other) {
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
        public CallFrameId(@Nonnull String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Nonnull public String value() { return value; }
        @Override public boolean equals(Object other) {
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
            Long value = CdpObject.numberAsLong(raw("columnNumber"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
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
         * @param columnNumber field value; empty omits the value
         * @return this model
         */
        public Location columnNumber(OptionalLong columnNumber) {
            set("columnNumber", columnNumber.isPresent() ? columnNumber.getAsLong() : null);
            return this;
        }
        /**
         * Column number in the script (0-based).
         * @param columnNumber field value; null removes the value
         * @return this model
         */
        public Location columnNumber(Long columnNumber) {
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
            return java.util.Objects.requireNonNull(Debugger.ScriptPosition.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("start")))));
        }
        /**
         * Returns the end field.
         * @return the protocol field value
         */
        public Debugger.ScriptPosition end() {
            return java.util.Objects.requireNonNull(Debugger.ScriptPosition.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("end")))));
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
            return Optional.ofNullable(raw("functionLocation") == null ? null : Debugger.Location.fromMap(java.util.Objects.requireNonNull(objectMap(raw("functionLocation")))));
        }
        /**
         * Location in the source code.
         * @return the protocol field value
         */
        public Debugger.Location location() {
            return java.util.Objects.requireNonNull(Debugger.Location.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("location")))));
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
            return CdpObject.requireList(require("scopeChain"), element0 -> java.util.Objects.requireNonNull(Debugger.Scope.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * {@code this} object for this call frame.
         * @return the protocol field value
         */
        public Runtime.RemoteObject thisValue() {
            return java.util.Objects.requireNonNull(Runtime.RemoteObject.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("this")))));
        }
        /**
         * The value being returned, if the function is at return point.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObject> returnValue() {
            return Optional.ofNullable(raw("returnValue") == null ? null : Runtime.RemoteObject.fromMap(java.util.Objects.requireNonNull(objectMap(raw("returnValue")))));
        }
        /**
         * Valid only while the VM is paused and indicates whether this frame can be restarted or not. Note that a {@code true} value here does not guarantee that Debugger#restartFrame with this CallFrameId will be successful, but it is very likely.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> canBeRestarted() {
            return Optional.ofNullable((Boolean) raw("canBeRestarted"));
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
         * @param functionLocation field value; empty omits the value
         * @return this model
         */
        public CallFrame functionLocation(Optional<Debugger.Location> functionLocation) {
            set("functionLocation", functionLocation.orElse(null));
            return this;
        }
        /**
         * Location in the source code.
         * @param functionLocation field value; null removes the value
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
         * @param returnValue field value; empty omits the value
         * @return this model
         */
        public CallFrame returnValue(Optional<Runtime.RemoteObject> returnValue) {
            set("returnValue", returnValue.orElse(null));
            return this;
        }
        /**
         * The value being returned, if the function is at return point.
         * @param returnValue field value; null removes the value
         * @return this model
         */
        public CallFrame returnValue(Runtime.RemoteObject returnValue) {
            set("returnValue", returnValue);
            return this;
        }
        /**
         * Valid only while the VM is paused and indicates whether this frame can be restarted or not. Note that a {@code true} value here does not guarantee that Debugger#restartFrame with this CallFrameId will be successful, but it is very likely.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param canBeRestarted field value; empty omits the value
         * @return this model
         */
        public CallFrame canBeRestarted(Optional<Boolean> canBeRestarted) {
            set("canBeRestarted", canBeRestarted.orElse(null));
            return this;
        }
        /**
         * Valid only while the VM is paused and indicates whether this frame can be restarted or not. Note that a {@code true} value here does not guarantee that Debugger#restartFrame with this CallFrameId will be successful, but it is very likely.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param canBeRestarted field value; null removes the value
         * @return this model
         */
        public CallFrame canBeRestarted(Boolean canBeRestarted) {
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
            @Nonnull public String value() { return value; }
            public static TypeValues of(@Nonnull String value) {
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
            return java.util.Objects.requireNonNull(Runtime.RemoteObject.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("object")))));
        }
        /**
         * Returns the name field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> name() {
            return Optional.ofNullable((String) raw("name"));
        }
        /**
         * Location in the source code where scope starts
         * @return the protocol field value, empty when absent
         */
        public Optional<Debugger.Location> startLocation() {
            return Optional.ofNullable(raw("startLocation") == null ? null : Debugger.Location.fromMap(java.util.Objects.requireNonNull(objectMap(raw("startLocation")))));
        }
        /**
         * Location in the source code where scope ends
         * @return the protocol field value, empty when absent
         */
        public Optional<Debugger.Location> endLocation() {
            return Optional.ofNullable(raw("endLocation") == null ? null : Debugger.Location.fromMap(java.util.Objects.requireNonNull(objectMap(raw("endLocation")))));
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
         * @param name field value; empty omits the value
         * @return this model
         */
        public Scope name(Optional<String> name) {
            set("name", name.orElse(null));
            return this;
        }
        /**
         * Sets the name field.
         * @param name field value; null removes the value
         * @return this model
         */
        public Scope name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Location in the source code where scope starts
         * @param startLocation field value; empty omits the value
         * @return this model
         */
        public Scope startLocation(Optional<Debugger.Location> startLocation) {
            set("startLocation", startLocation.orElse(null));
            return this;
        }
        /**
         * Location in the source code where scope starts
         * @param startLocation field value; null removes the value
         * @return this model
         */
        public Scope startLocation(Debugger.Location startLocation) {
            set("startLocation", startLocation);
            return this;
        }
        /**
         * Location in the source code where scope ends
         * @param endLocation field value; empty omits the value
         * @return this model
         */
        public Scope endLocation(Optional<Debugger.Location> endLocation) {
            set("endLocation", endLocation.orElse(null));
            return this;
        }
        /**
         * Location in the source code where scope ends
         * @param endLocation field value; null removes the value
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
            @Nonnull public String value() { return value; }
            public static TypeValues of(@Nonnull String value) {
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
            Long value = CdpObject.numberAsLong(raw("columnNumber"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Returns the type field.
         * @return the protocol field value, empty when absent
         */
        public Optional<BreakLocation.TypeValues> type() {
            return Optional.ofNullable(raw("type") == null ? null : BreakLocation.TypeValues.of((String) raw("type")));
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
         * @param columnNumber field value; empty omits the value
         * @return this model
         */
        public BreakLocation columnNumber(OptionalLong columnNumber) {
            set("columnNumber", columnNumber.isPresent() ? columnNumber.getAsLong() : null);
            return this;
        }
        /**
         * Column number in the script (0-based).
         * @param columnNumber field value; null removes the value
         * @return this model
         */
        public BreakLocation columnNumber(Long columnNumber) {
            set("columnNumber", columnNumber);
            return this;
        }
        /**
         * Sets the type field.
         * @param type field value; empty omits the value
         * @return this model
         */
        public BreakLocation type(Optional<BreakLocation.TypeValues> type) {
            set("type", type.orElse(null));
            return this;
        }
        /**
         * Sets the type field.
         * @param type field value; null removes the value
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
        @Nonnull public String value() { return value; }
        public static ScriptLanguage of(@Nonnull String value) {
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
            @Nonnull public String value() { return value; }
            public static TypeValues of(@Nonnull String value) {
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
            return Optional.ofNullable((String) raw("externalURL"));
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
         * @param externalURL field value; empty omits the value
         * @return this model
         */
        public DebugSymbols externalURL(Optional<String> externalURL) {
            set("externalURL", externalURL.orElse(null));
            return this;
        }
        /**
         * URL of the external symbol source.
         * @param externalURL field value; null removes the value
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
            return java.util.Objects.requireNonNull(Debugger.Location.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("location")))));
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
            return java.util.Objects.requireNonNull(Runtime.RemoteObject.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("result")))));
        }
        /**
         * Exception details.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.ExceptionDetails> exceptionDetails() {
            return Optional.ofNullable(raw("exceptionDetails") == null ? null : Runtime.ExceptionDetails.fromMap(java.util.Objects.requireNonNull(objectMap(raw("exceptionDetails")))));
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
         * @param exceptionDetails field value; empty omits the value
         * @return this model
         */
        public EvaluateOnCallFrameResult exceptionDetails(Optional<Runtime.ExceptionDetails> exceptionDetails) {
            set("exceptionDetails", exceptionDetails.orElse(null));
            return this;
        }
        /**
         * Exception details.
         * @param exceptionDetails field value; null removes the value
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
            return Optional.ofNullable((String) raw("bytecode"));
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
         * @param bytecode field value; empty omits the value
         * @return this model
         */
        public GetScriptSourceResult bytecode(Optional<String> bytecode) {
            set("bytecode", bytecode.orElse(null));
            return this;
        }
        /**
         * Wasm bytecode. (Encoded as a base64 string when passed over JSON)
         * @param bytecode field value; null removes the value
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
            return Optional.ofNullable((String) raw("streamId"));
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
            return java.util.Objects.requireNonNull(Debugger.WasmDisassemblyChunk.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("chunk")))));
        }
        /**
         * For large modules, return a stream from which additional chunks of disassembly can be read successively.
         * @param streamId field value; empty omits the value
         * @return this model
         */
        public DisassembleWasmModuleResult streamId(Optional<String> streamId) {
            set("streamId", streamId.orElse(null));
            return this;
        }
        /**
         * For large modules, return a stream from which additional chunks of disassembly can be read successively.
         * @param streamId field value; null removes the value
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
            return CdpObject.requireList(require("callFrames"), element0 -> java.util.Objects.requireNonNull(Debugger.CallFrame.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * Async stack trace, if any.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<Runtime.StackTrace> asyncStackTrace() {
            return Optional.ofNullable(raw("asyncStackTrace") == null ? null : Runtime.StackTrace.fromMap(java.util.Objects.requireNonNull(objectMap(raw("asyncStackTrace")))));
        }
        /**
         * Async stack trace, if any.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<Runtime.StackTraceId> asyncStackTraceId() {
            return Optional.ofNullable(raw("asyncStackTraceId") == null ? null : Runtime.StackTraceId.fromMap(java.util.Objects.requireNonNull(objectMap(raw("asyncStackTraceId")))));
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
         * @param asyncStackTrace field value; empty omits the value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public RestartFrameResult asyncStackTrace(Optional<Runtime.StackTrace> asyncStackTrace) {
            set("asyncStackTrace", asyncStackTrace.orElse(null));
            return this;
        }
        /**
         * Async stack trace, if any.
         * @param asyncStackTrace field value; null removes the value
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
         * @param asyncStackTraceId field value; empty omits the value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public RestartFrameResult asyncStackTraceId(Optional<Runtime.StackTraceId> asyncStackTraceId) {
            set("asyncStackTraceId", asyncStackTraceId.orElse(null));
            return this;
        }
        /**
         * Async stack trace, if any.
         * @param asyncStackTraceId field value; null removes the value
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
            return java.util.Objects.requireNonNull(Debugger.Location.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("actualLocation")))));
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
            return CdpObject.requireList(require("locations"), element0 -> java.util.Objects.requireNonNull(Debugger.Location.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
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
            @Nonnull public String value() { return value; }
            public static StatusValues of(@Nonnull String value) {
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
            return Optional.ofNullable(list(raw("callFrames"), element0 -> java.util.Objects.requireNonNull(Debugger.CallFrame.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Whether current call stack was modified after applying the changes.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<Boolean> stackChanged() {
            return Optional.ofNullable((Boolean) raw("stackChanged"));
        }
        /**
         * Async stack trace, if any.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<Runtime.StackTrace> asyncStackTrace() {
            return Optional.ofNullable(raw("asyncStackTrace") == null ? null : Runtime.StackTrace.fromMap(java.util.Objects.requireNonNull(objectMap(raw("asyncStackTrace")))));
        }
        /**
         * Async stack trace, if any.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<Runtime.StackTraceId> asyncStackTraceId() {
            return Optional.ofNullable(raw("asyncStackTraceId") == null ? null : Runtime.StackTraceId.fromMap(java.util.Objects.requireNonNull(objectMap(raw("asyncStackTraceId")))));
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
            return Optional.ofNullable(raw("exceptionDetails") == null ? null : Runtime.ExceptionDetails.fromMap(java.util.Objects.requireNonNull(objectMap(raw("exceptionDetails")))));
        }
        /**
         * New stack trace in case editing has happened while VM was stopped.
         * @param callFrames field value; empty omits the value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public SetScriptSourceResult callFrames(Optional<java.util.List<Debugger.CallFrame>> callFrames) {
            set("callFrames", callFrames.orElse(null));
            return this;
        }
        /**
         * New stack trace in case editing has happened while VM was stopped.
         * @param callFrames field value; null removes the value
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
         * @param stackChanged field value; empty omits the value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public SetScriptSourceResult stackChanged(Optional<Boolean> stackChanged) {
            set("stackChanged", stackChanged.orElse(null));
            return this;
        }
        /**
         * Whether current call stack was modified after applying the changes.
         * @param stackChanged field value; null removes the value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public SetScriptSourceResult stackChanged(Boolean stackChanged) {
            set("stackChanged", stackChanged);
            return this;
        }
        /**
         * Async stack trace, if any.
         * @param asyncStackTrace field value; empty omits the value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public SetScriptSourceResult asyncStackTrace(Optional<Runtime.StackTrace> asyncStackTrace) {
            set("asyncStackTrace", asyncStackTrace.orElse(null));
            return this;
        }
        /**
         * Async stack trace, if any.
         * @param asyncStackTrace field value; null removes the value
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
         * @param asyncStackTraceId field value; empty omits the value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public SetScriptSourceResult asyncStackTraceId(Optional<Runtime.StackTraceId> asyncStackTraceId) {
            set("asyncStackTraceId", asyncStackTraceId.orElse(null));
            return this;
        }
        /**
         * Async stack trace, if any.
         * @param asyncStackTraceId field value; null removes the value
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
         * @param exceptionDetails field value; empty omits the value
         * @return this model
         */
        public SetScriptSourceResult exceptionDetails(Optional<Runtime.ExceptionDetails> exceptionDetails) {
            set("exceptionDetails", exceptionDetails.orElse(null));
            return this;
        }
        /**
         * Exception details if any. Only present when {@code status} is {@code CompileError}.
         * @param exceptionDetails field value; null removes the value
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
            return java.util.Objects.requireNonNull(Debugger.Location.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("location")))));
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
            @Nonnull public String value() { return value; }
            public static ReasonValues of(@Nonnull String value) {
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
            return CdpObject.requireList(require("callFrames"), element0 -> java.util.Objects.requireNonNull(Debugger.CallFrame.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
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
            return Optional.ofNullable(objectMap(raw("data")));
        }
        /**
         * Hit breakpoints IDs
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<String>> hitBreakpoints() {
            return Optional.ofNullable(list(raw("hitBreakpoints"), element0 -> (String) element0));
        }
        /**
         * Async stack trace, if any.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.StackTrace> asyncStackTrace() {
            return Optional.ofNullable(raw("asyncStackTrace") == null ? null : Runtime.StackTrace.fromMap(java.util.Objects.requireNonNull(objectMap(raw("asyncStackTrace")))));
        }
        /**
         * Async stack trace, if any.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.StackTraceId> asyncStackTraceId() {
            return Optional.ofNullable(raw("asyncStackTraceId") == null ? null : Runtime.StackTraceId.fromMap(java.util.Objects.requireNonNull(objectMap(raw("asyncStackTraceId")))));
        }
        /**
         * Never present, will be removed.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<Runtime.StackTraceId> asyncCallStackTraceId() {
            return Optional.ofNullable(raw("asyncCallStackTraceId") == null ? null : Runtime.StackTraceId.fromMap(java.util.Objects.requireNonNull(objectMap(raw("asyncCallStackTraceId")))));
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
         * @param data field value; empty omits the value
         * @return this model
         */
        public PausedEvent data(Optional<java.util.Map<String, Object>> data) {
            set("data", data.orElse(null));
            return this;
        }
        /**
         * Object containing break-specific auxiliary properties.
         * @param data field value; null removes the value
         * @return this model
         */
        public PausedEvent data(java.util.Map<String, Object> data) {
            set("data", data);
            return this;
        }
        /**
         * Hit breakpoints IDs
         * @param hitBreakpoints field value; empty omits the value
         * @return this model
         */
        public PausedEvent hitBreakpoints(Optional<java.util.List<String>> hitBreakpoints) {
            set("hitBreakpoints", hitBreakpoints.orElse(null));
            return this;
        }
        /**
         * Hit breakpoints IDs
         * @param hitBreakpoints field value; null removes the value
         * @return this model
         */
        public PausedEvent hitBreakpoints(java.util.List<String> hitBreakpoints) {
            set("hitBreakpoints", hitBreakpoints);
            return this;
        }
        /**
         * Async stack trace, if any.
         * @param asyncStackTrace field value; empty omits the value
         * @return this model
         */
        public PausedEvent asyncStackTrace(Optional<Runtime.StackTrace> asyncStackTrace) {
            set("asyncStackTrace", asyncStackTrace.orElse(null));
            return this;
        }
        /**
         * Async stack trace, if any.
         * @param asyncStackTrace field value; null removes the value
         * @return this model
         */
        public PausedEvent asyncStackTrace(Runtime.StackTrace asyncStackTrace) {
            set("asyncStackTrace", asyncStackTrace);
            return this;
        }
        /**
         * Async stack trace, if any.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param asyncStackTraceId field value; empty omits the value
         * @return this model
         */
        public PausedEvent asyncStackTraceId(Optional<Runtime.StackTraceId> asyncStackTraceId) {
            set("asyncStackTraceId", asyncStackTraceId.orElse(null));
            return this;
        }
        /**
         * Async stack trace, if any.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param asyncStackTraceId field value; null removes the value
         * @return this model
         */
        public PausedEvent asyncStackTraceId(Runtime.StackTraceId asyncStackTraceId) {
            set("asyncStackTraceId", asyncStackTraceId);
            return this;
        }
        /**
         * Never present, will be removed.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param asyncCallStackTraceId field value; empty omits the value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public PausedEvent asyncCallStackTraceId(Optional<Runtime.StackTraceId> asyncCallStackTraceId) {
            set("asyncCallStackTraceId", asyncCallStackTraceId.orElse(null));
            return this;
        }
        /**
         * Never present, will be removed.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param asyncCallStackTraceId field value; null removes the value
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
            return Optional.ofNullable(objectMap(raw("executionContextAuxData")));
        }
        /**
         * URL of source map associated with script (if any).
         * @return the protocol field value, empty when absent
         */
        public Optional<String> sourceMapURL() {
            return Optional.ofNullable((String) raw("sourceMapURL"));
        }
        /**
         * True, if this script has sourceURL.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> hasSourceURL() {
            return Optional.ofNullable((Boolean) raw("hasSourceURL"));
        }
        /**
         * True, if this script is ES6 module.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> isModule() {
            return Optional.ofNullable((Boolean) raw("isModule"));
        }
        /**
         * This script length.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong length() {
            Long value = CdpObject.numberAsLong(raw("length"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * JavaScript top stack frame of where the script parsed event was triggered if available.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.StackTrace> stackTrace() {
            return Optional.ofNullable(raw("stackTrace") == null ? null : Runtime.StackTrace.fromMap(java.util.Objects.requireNonNull(objectMap(raw("stackTrace")))));
        }
        /**
         * If the scriptLanguage is WebAssembly, the code section offset in the module.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong codeOffset() {
            Long value = CdpObject.numberAsLong(raw("codeOffset"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * The language of the script.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Debugger.ScriptLanguage> scriptLanguage() {
            return Optional.ofNullable(raw("scriptLanguage") == null ? null : Debugger.ScriptLanguage.of((String) raw("scriptLanguage")));
        }
        /**
         * The name the embedder supplied for this script.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> embedderName() {
            return Optional.ofNullable((String) raw("embedderName"));
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
         * @param executionContextAuxData field value; empty omits the value
         * @return this model
         */
        public ScriptFailedToParseEvent executionContextAuxData(Optional<java.util.Map<String, Object>> executionContextAuxData) {
            set("executionContextAuxData", executionContextAuxData.orElse(null));
            return this;
        }
        /**
         * Embedder-specific auxiliary data likely matching {isDefault: boolean, type: &#x27;default&#x27;|&#x27;isolated&#x27;|&#x27;worker&#x27;, frameId: string}
         * @param executionContextAuxData field value; null removes the value
         * @return this model
         */
        public ScriptFailedToParseEvent executionContextAuxData(java.util.Map<String, Object> executionContextAuxData) {
            set("executionContextAuxData", executionContextAuxData);
            return this;
        }
        /**
         * URL of source map associated with script (if any).
         * @param sourceMapURL field value; empty omits the value
         * @return this model
         */
        public ScriptFailedToParseEvent sourceMapURL(Optional<String> sourceMapURL) {
            set("sourceMapURL", sourceMapURL.orElse(null));
            return this;
        }
        /**
         * URL of source map associated with script (if any).
         * @param sourceMapURL field value; null removes the value
         * @return this model
         */
        public ScriptFailedToParseEvent sourceMapURL(String sourceMapURL) {
            set("sourceMapURL", sourceMapURL);
            return this;
        }
        /**
         * True, if this script has sourceURL.
         * @param hasSourceURL field value; empty omits the value
         * @return this model
         */
        public ScriptFailedToParseEvent hasSourceURL(Optional<Boolean> hasSourceURL) {
            set("hasSourceURL", hasSourceURL.orElse(null));
            return this;
        }
        /**
         * True, if this script has sourceURL.
         * @param hasSourceURL field value; null removes the value
         * @return this model
         */
        public ScriptFailedToParseEvent hasSourceURL(Boolean hasSourceURL) {
            set("hasSourceURL", hasSourceURL);
            return this;
        }
        /**
         * True, if this script is ES6 module.
         * @param isModule field value; empty omits the value
         * @return this model
         */
        public ScriptFailedToParseEvent isModule(Optional<Boolean> isModule) {
            set("isModule", isModule.orElse(null));
            return this;
        }
        /**
         * True, if this script is ES6 module.
         * @param isModule field value; null removes the value
         * @return this model
         */
        public ScriptFailedToParseEvent isModule(Boolean isModule) {
            set("isModule", isModule);
            return this;
        }
        /**
         * This script length.
         * @param length field value; empty omits the value
         * @return this model
         */
        public ScriptFailedToParseEvent length(OptionalLong length) {
            set("length", length.isPresent() ? length.getAsLong() : null);
            return this;
        }
        /**
         * This script length.
         * @param length field value; null removes the value
         * @return this model
         */
        public ScriptFailedToParseEvent length(Long length) {
            set("length", length);
            return this;
        }
        /**
         * JavaScript top stack frame of where the script parsed event was triggered if available.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param stackTrace field value; empty omits the value
         * @return this model
         */
        public ScriptFailedToParseEvent stackTrace(Optional<Runtime.StackTrace> stackTrace) {
            set("stackTrace", stackTrace.orElse(null));
            return this;
        }
        /**
         * JavaScript top stack frame of where the script parsed event was triggered if available.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param stackTrace field value; null removes the value
         * @return this model
         */
        public ScriptFailedToParseEvent stackTrace(Runtime.StackTrace stackTrace) {
            set("stackTrace", stackTrace);
            return this;
        }
        /**
         * If the scriptLanguage is WebAssembly, the code section offset in the module.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param codeOffset field value; empty omits the value
         * @return this model
         */
        public ScriptFailedToParseEvent codeOffset(OptionalLong codeOffset) {
            set("codeOffset", codeOffset.isPresent() ? codeOffset.getAsLong() : null);
            return this;
        }
        /**
         * If the scriptLanguage is WebAssembly, the code section offset in the module.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param codeOffset field value; null removes the value
         * @return this model
         */
        public ScriptFailedToParseEvent codeOffset(Long codeOffset) {
            set("codeOffset", codeOffset);
            return this;
        }
        /**
         * The language of the script.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param scriptLanguage field value; empty omits the value
         * @return this model
         */
        public ScriptFailedToParseEvent scriptLanguage(Optional<Debugger.ScriptLanguage> scriptLanguage) {
            set("scriptLanguage", scriptLanguage.orElse(null));
            return this;
        }
        /**
         * The language of the script.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param scriptLanguage field value; null removes the value
         * @return this model
         */
        public ScriptFailedToParseEvent scriptLanguage(Debugger.ScriptLanguage scriptLanguage) {
            set("scriptLanguage", scriptLanguage);
            return this;
        }
        /**
         * The name the embedder supplied for this script.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param embedderName field value; empty omits the value
         * @return this model
         */
        public ScriptFailedToParseEvent embedderName(Optional<String> embedderName) {
            set("embedderName", embedderName.orElse(null));
            return this;
        }
        /**
         * The name the embedder supplied for this script.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param embedderName field value; null removes the value
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
            return Optional.ofNullable(objectMap(raw("executionContextAuxData")));
        }
        /**
         * True, if this script is generated as a result of the live edit operation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> isLiveEdit() {
            return Optional.ofNullable((Boolean) raw("isLiveEdit"));
        }
        /**
         * URL of source map associated with script (if any).
         * @return the protocol field value, empty when absent
         */
        public Optional<String> sourceMapURL() {
            return Optional.ofNullable((String) raw("sourceMapURL"));
        }
        /**
         * True, if this script has sourceURL.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> hasSourceURL() {
            return Optional.ofNullable((Boolean) raw("hasSourceURL"));
        }
        /**
         * True, if this script is ES6 module.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> isModule() {
            return Optional.ofNullable((Boolean) raw("isModule"));
        }
        /**
         * This script length.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong length() {
            Long value = CdpObject.numberAsLong(raw("length"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * JavaScript top stack frame of where the script parsed event was triggered if available.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.StackTrace> stackTrace() {
            return Optional.ofNullable(raw("stackTrace") == null ? null : Runtime.StackTrace.fromMap(java.util.Objects.requireNonNull(objectMap(raw("stackTrace")))));
        }
        /**
         * If the scriptLanguage is WebAssembly, the code section offset in the module.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong codeOffset() {
            Long value = CdpObject.numberAsLong(raw("codeOffset"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * The language of the script.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Debugger.ScriptLanguage> scriptLanguage() {
            return Optional.ofNullable(raw("scriptLanguage") == null ? null : Debugger.ScriptLanguage.of((String) raw("scriptLanguage")));
        }
        /**
         * If the scriptLanguage is WebAssembly, the source of debug symbols for the module.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Debugger.DebugSymbols>> debugSymbols() {
            return Optional.ofNullable(list(raw("debugSymbols"), element0 -> java.util.Objects.requireNonNull(Debugger.DebugSymbols.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * The name the embedder supplied for this script.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> embedderName() {
            return Optional.ofNullable((String) raw("embedderName"));
        }
        /**
         * The list of set breakpoints in this script if calls to {@code setBreakpointByUrl} matches this script&#x27;s URL or hash. Clients that use this list can ignore the {@code breakpointResolved} event. They are equivalent.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Debugger.ResolvedBreakpoint>> resolvedBreakpoints() {
            return Optional.ofNullable(list(raw("resolvedBreakpoints"), element0 -> java.util.Objects.requireNonNull(Debugger.ResolvedBreakpoint.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
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
         * @param executionContextAuxData field value; empty omits the value
         * @return this model
         */
        public ScriptParsedEvent executionContextAuxData(Optional<java.util.Map<String, Object>> executionContextAuxData) {
            set("executionContextAuxData", executionContextAuxData.orElse(null));
            return this;
        }
        /**
         * Embedder-specific auxiliary data likely matching {isDefault: boolean, type: &#x27;default&#x27;|&#x27;isolated&#x27;|&#x27;worker&#x27;, frameId: string}
         * @param executionContextAuxData field value; null removes the value
         * @return this model
         */
        public ScriptParsedEvent executionContextAuxData(java.util.Map<String, Object> executionContextAuxData) {
            set("executionContextAuxData", executionContextAuxData);
            return this;
        }
        /**
         * True, if this script is generated as a result of the live edit operation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param isLiveEdit field value; empty omits the value
         * @return this model
         */
        public ScriptParsedEvent isLiveEdit(Optional<Boolean> isLiveEdit) {
            set("isLiveEdit", isLiveEdit.orElse(null));
            return this;
        }
        /**
         * True, if this script is generated as a result of the live edit operation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param isLiveEdit field value; null removes the value
         * @return this model
         */
        public ScriptParsedEvent isLiveEdit(Boolean isLiveEdit) {
            set("isLiveEdit", isLiveEdit);
            return this;
        }
        /**
         * URL of source map associated with script (if any).
         * @param sourceMapURL field value; empty omits the value
         * @return this model
         */
        public ScriptParsedEvent sourceMapURL(Optional<String> sourceMapURL) {
            set("sourceMapURL", sourceMapURL.orElse(null));
            return this;
        }
        /**
         * URL of source map associated with script (if any).
         * @param sourceMapURL field value; null removes the value
         * @return this model
         */
        public ScriptParsedEvent sourceMapURL(String sourceMapURL) {
            set("sourceMapURL", sourceMapURL);
            return this;
        }
        /**
         * True, if this script has sourceURL.
         * @param hasSourceURL field value; empty omits the value
         * @return this model
         */
        public ScriptParsedEvent hasSourceURL(Optional<Boolean> hasSourceURL) {
            set("hasSourceURL", hasSourceURL.orElse(null));
            return this;
        }
        /**
         * True, if this script has sourceURL.
         * @param hasSourceURL field value; null removes the value
         * @return this model
         */
        public ScriptParsedEvent hasSourceURL(Boolean hasSourceURL) {
            set("hasSourceURL", hasSourceURL);
            return this;
        }
        /**
         * True, if this script is ES6 module.
         * @param isModule field value; empty omits the value
         * @return this model
         */
        public ScriptParsedEvent isModule(Optional<Boolean> isModule) {
            set("isModule", isModule.orElse(null));
            return this;
        }
        /**
         * True, if this script is ES6 module.
         * @param isModule field value; null removes the value
         * @return this model
         */
        public ScriptParsedEvent isModule(Boolean isModule) {
            set("isModule", isModule);
            return this;
        }
        /**
         * This script length.
         * @param length field value; empty omits the value
         * @return this model
         */
        public ScriptParsedEvent length(OptionalLong length) {
            set("length", length.isPresent() ? length.getAsLong() : null);
            return this;
        }
        /**
         * This script length.
         * @param length field value; null removes the value
         * @return this model
         */
        public ScriptParsedEvent length(Long length) {
            set("length", length);
            return this;
        }
        /**
         * JavaScript top stack frame of where the script parsed event was triggered if available.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param stackTrace field value; empty omits the value
         * @return this model
         */
        public ScriptParsedEvent stackTrace(Optional<Runtime.StackTrace> stackTrace) {
            set("stackTrace", stackTrace.orElse(null));
            return this;
        }
        /**
         * JavaScript top stack frame of where the script parsed event was triggered if available.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param stackTrace field value; null removes the value
         * @return this model
         */
        public ScriptParsedEvent stackTrace(Runtime.StackTrace stackTrace) {
            set("stackTrace", stackTrace);
            return this;
        }
        /**
         * If the scriptLanguage is WebAssembly, the code section offset in the module.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param codeOffset field value; empty omits the value
         * @return this model
         */
        public ScriptParsedEvent codeOffset(OptionalLong codeOffset) {
            set("codeOffset", codeOffset.isPresent() ? codeOffset.getAsLong() : null);
            return this;
        }
        /**
         * If the scriptLanguage is WebAssembly, the code section offset in the module.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param codeOffset field value; null removes the value
         * @return this model
         */
        public ScriptParsedEvent codeOffset(Long codeOffset) {
            set("codeOffset", codeOffset);
            return this;
        }
        /**
         * The language of the script.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param scriptLanguage field value; empty omits the value
         * @return this model
         */
        public ScriptParsedEvent scriptLanguage(Optional<Debugger.ScriptLanguage> scriptLanguage) {
            set("scriptLanguage", scriptLanguage.orElse(null));
            return this;
        }
        /**
         * The language of the script.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param scriptLanguage field value; null removes the value
         * @return this model
         */
        public ScriptParsedEvent scriptLanguage(Debugger.ScriptLanguage scriptLanguage) {
            set("scriptLanguage", scriptLanguage);
            return this;
        }
        /**
         * If the scriptLanguage is WebAssembly, the source of debug symbols for the module.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param debugSymbols field value; empty omits the value
         * @return this model
         */
        public ScriptParsedEvent debugSymbols(Optional<java.util.List<Debugger.DebugSymbols>> debugSymbols) {
            set("debugSymbols", debugSymbols.orElse(null));
            return this;
        }
        /**
         * If the scriptLanguage is WebAssembly, the source of debug symbols for the module.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param debugSymbols field value; null removes the value
         * @return this model
         */
        public ScriptParsedEvent debugSymbols(java.util.List<Debugger.DebugSymbols> debugSymbols) {
            set("debugSymbols", debugSymbols);
            return this;
        }
        /**
         * The name the embedder supplied for this script.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param embedderName field value; empty omits the value
         * @return this model
         */
        public ScriptParsedEvent embedderName(Optional<String> embedderName) {
            set("embedderName", embedderName.orElse(null));
            return this;
        }
        /**
         * The name the embedder supplied for this script.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param embedderName field value; null removes the value
         * @return this model
         */
        public ScriptParsedEvent embedderName(String embedderName) {
            set("embedderName", embedderName);
            return this;
        }
        /**
         * The list of set breakpoints in this script if calls to {@code setBreakpointByUrl} matches this script&#x27;s URL or hash. Clients that use this list can ignore the {@code breakpointResolved} event. They are equivalent.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param resolvedBreakpoints field value; empty omits the value
         * @return this model
         */
        public ScriptParsedEvent resolvedBreakpoints(Optional<java.util.List<Debugger.ResolvedBreakpoint>> resolvedBreakpoints) {
            set("resolvedBreakpoints", resolvedBreakpoints.orElse(null));
            return this;
        }
        /**
         * The list of set breakpoints in this script if calls to {@code setBreakpointByUrl} matches this script&#x27;s URL or hash. Clients that use this list can ignore the {@code breakpointResolved} event. They are equivalent.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param resolvedBreakpoints field value; null removes the value
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
        @Nonnull public String value() { return value; }
        public static ContinueToLocationTargetCallFramesValues of(@Nonnull String value) {
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
        @Nonnull public String value() { return value; }
        public static RestartFrameModeValues of(@Nonnull String value) {
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
        @Nonnull public String value() { return value; }
        public static SetInstrumentationBreakpointInstrumentationValues of(@Nonnull String value) {
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
        @Nonnull public String value() { return value; }
        public static SetPauseOnExceptionsStateValues of(@Nonnull String value) {
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
        public CompletionStage<Void> continueToLocation(Debugger.Location location, Optional<ContinueToLocationTargetCallFramesValues> targetCallFrames) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("location", CdpObject.json(location));
            targetCallFrames.ifPresent(value_ -> params.put("targetCallFrames", CdpObject.json(value_)));
            return client.call("Debugger.continueToLocation", params, result_ -> null);
        }
        /**
         * Continues execution until specific location is reached.
         * @param location protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> continueToLocation(Debugger.Location location) {
            return continueToLocation(location, Optional.empty());
        }
        /**
         * Disables debugger for given page.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return client.call("Debugger.disable", null, result_ -> null);
        }
        /**
         * Enables debugger for the given page. Clients should not assume that the debugging has been enabled until the result for this command is received.
         * @param maxScriptsCacheSize protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Runtime.UniqueDebuggerId> enable(OptionalDouble maxScriptsCacheSize) {
            Map<String, Object> params = new LinkedHashMap<>();
            maxScriptsCacheSize.ifPresent(value_ -> params.put("maxScriptsCacheSize", value_));
            return client.call("Debugger.enable", params, result_ -> new Runtime.UniqueDebuggerId((String) java.util.Objects.requireNonNull(result_.get("debuggerId"))));
        }
        /**
         * Enables debugger for the given page. Clients should not assume that the debugging has been enabled until the result for this command is received.
         * @return a stage completing with the command result
         */
        public CompletionStage<Runtime.UniqueDebuggerId> enable() {
            return enable(OptionalDouble.empty());
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
        public CompletionStage<EvaluateOnCallFrameResult> evaluateOnCallFrame(Debugger.CallFrameId callFrameId, String expression, Optional<String> objectGroup, Optional<Boolean> includeCommandLineAPI, Optional<Boolean> silent, Optional<Boolean> returnByValue, Optional<Boolean> generatePreview, Optional<Boolean> throwOnSideEffect, Optional<Runtime.TimeDelta> timeout) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("callFrameId", CdpObject.json(callFrameId));
            params.put("expression", CdpObject.json(expression));
            objectGroup.ifPresent(value_ -> params.put("objectGroup", CdpObject.json(value_)));
            includeCommandLineAPI.ifPresent(value_ -> params.put("includeCommandLineAPI", value_));
            silent.ifPresent(value_ -> params.put("silent", value_));
            returnByValue.ifPresent(value_ -> params.put("returnByValue", value_));
            generatePreview.ifPresent(value_ -> params.put("generatePreview", value_));
            throwOnSideEffect.ifPresent(value_ -> params.put("throwOnSideEffect", value_));
            timeout.ifPresent(value_ -> params.put("timeout", CdpObject.json(value_)));
            return client.call("Debugger.evaluateOnCallFrame", params, result_ -> new EvaluateOnCallFrameResult(result_));
        }
        /**
         * Evaluates expression on a given call frame.
         * @param callFrameId protocol value
         * @param expression protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<EvaluateOnCallFrameResult> evaluateOnCallFrame(Debugger.CallFrameId callFrameId, String expression) {
            return evaluateOnCallFrame(callFrameId, expression, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
        }
        /**
         * Returns possible locations for breakpoint. scriptId in start and end range locations should be the same.
         * @param start protocol value
         * @param end protocol value
         * @param restrictToFunction protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Debugger.BreakLocation>> getPossibleBreakpoints(Debugger.Location start, Optional<Debugger.Location> end, Optional<Boolean> restrictToFunction) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("start", CdpObject.json(start));
            end.ifPresent(value_ -> params.put("end", CdpObject.json(value_)));
            restrictToFunction.ifPresent(value_ -> params.put("restrictToFunction", value_));
            return client.call("Debugger.getPossibleBreakpoints", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("locations")), element0 -> java.util.Objects.requireNonNull(Debugger.BreakLocation.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Returns possible locations for breakpoint. scriptId in start and end range locations should be the same.
         * @param start protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Debugger.BreakLocation>> getPossibleBreakpoints(Debugger.Location start) {
            return getPossibleBreakpoints(start, Optional.empty(), Optional.empty());
        }
        /**
         * Returns source for the script with given id.
         * @param scriptId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<GetScriptSourceResult> getScriptSource(Runtime.ScriptId scriptId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("scriptId", CdpObject.json(scriptId));
            return client.call("Debugger.getScriptSource", params, result_ -> new GetScriptSourceResult(result_));
        }
        /**
         * Invokes Debugger.disassembleWasmModule.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param scriptId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<DisassembleWasmModuleResult> disassembleWasmModule(Runtime.ScriptId scriptId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("scriptId", CdpObject.json(scriptId));
            return client.call("Debugger.disassembleWasmModule", params, result_ -> new DisassembleWasmModuleResult(result_));
        }
        /**
         * Disassemble the next chunk of lines for the module corresponding to the stream. If disassembly is complete, this API will invalidate the streamId and return an empty chunk. Any subsequent calls for the now invalid stream will return errors.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param streamId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Debugger.WasmDisassemblyChunk> nextWasmDisassemblyChunk(String streamId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("streamId", CdpObject.json(streamId));
            return client.call("Debugger.nextWasmDisassemblyChunk", params, result_ -> java.util.Objects.requireNonNull(Debugger.WasmDisassemblyChunk.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("chunk")))))));
        }
        /**
         * This command is deprecated. Use getScriptSource instead.
         * @param scriptId protocol value
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<String> getWasmBytecode(Runtime.ScriptId scriptId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("scriptId", CdpObject.json(scriptId));
            return client.call("Debugger.getWasmBytecode", params, result_ -> (String) java.util.Objects.requireNonNull(result_.get("bytecode")));
        }
        /**
         * Returns stack trace with given {@code stackTraceId}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param stackTraceId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Runtime.StackTrace> getStackTrace(Runtime.StackTraceId stackTraceId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("stackTraceId", CdpObject.json(stackTraceId));
            return client.call("Debugger.getStackTrace", params, result_ -> java.util.Objects.requireNonNull(Runtime.StackTrace.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("stackTrace")))))));
        }
        /**
         * Stops on the next JavaScript statement.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> pause() {
            return client.call("Debugger.pause", null, result_ -> null);
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
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("parentStackTraceId", CdpObject.json(parentStackTraceId));
            return client.call("Debugger.pauseOnAsyncCall", params, result_ -> null);
        }
        /**
         * Removes JavaScript breakpoint.
         * @param breakpointId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeBreakpoint(Debugger.BreakpointId breakpointId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("breakpointId", CdpObject.json(breakpointId));
            return client.call("Debugger.removeBreakpoint", params, result_ -> null);
        }
        /**
         * Restarts particular call frame from the beginning. The old, deprecated behavior of {@code restartFrame} is to stay paused and allow further CDP commands after a restart was scheduled. This can cause problems with restarting, so we now continue execution immediatly after it has been scheduled until we reach the beginning of the restarted frame.
         * <p>To stay back-wards compatible, {@code restartFrame} now expects a {@code mode} parameter to be present. If the {@code mode} parameter is missing, {@code restartFrame} errors out.
         * <p>The various return values are deprecated and {@code callFrames} is always empty. Use the call frames from the {@code Debugger#paused} events instead, that fires once V8 pauses at the beginning of the restarted function.
         * @param callFrameId protocol value
         * @param mode protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<RestartFrameResult> restartFrame(Debugger.CallFrameId callFrameId, Optional<RestartFrameModeValues> mode) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("callFrameId", CdpObject.json(callFrameId));
            mode.ifPresent(value_ -> params.put("mode", CdpObject.json(value_)));
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
            return restartFrame(callFrameId, Optional.empty());
        }
        /**
         * Resumes JavaScript execution.
         * @param terminateOnResume protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> resume(Optional<Boolean> terminateOnResume) {
            Map<String, Object> params = new LinkedHashMap<>();
            terminateOnResume.ifPresent(value_ -> params.put("terminateOnResume", value_));
            return client.call("Debugger.resume", params, result_ -> null);
        }
        /**
         * Resumes JavaScript execution.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> resume() {
            return resume(Optional.empty());
        }
        /**
         * Searches for given string in script content.
         * @param scriptId protocol value
         * @param query protocol value
         * @param caseSensitive protocol value
         * @param isRegex protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Debugger.SearchMatch>> searchInContent(Runtime.ScriptId scriptId, String query, Optional<Boolean> caseSensitive, Optional<Boolean> isRegex) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("scriptId", CdpObject.json(scriptId));
            params.put("query", CdpObject.json(query));
            caseSensitive.ifPresent(value_ -> params.put("caseSensitive", value_));
            isRegex.ifPresent(value_ -> params.put("isRegex", value_));
            return client.call("Debugger.searchInContent", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("result")), element0 -> java.util.Objects.requireNonNull(Debugger.SearchMatch.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Searches for given string in script content.
         * @param scriptId protocol value
         * @param query protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Debugger.SearchMatch>> searchInContent(Runtime.ScriptId scriptId, String query) {
            return searchInContent(scriptId, query, Optional.empty(), Optional.empty());
        }
        /**
         * Enables or disables async call stacks tracking.
         * @param maxDepth protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAsyncCallStackDepth(long maxDepth) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("maxDepth", CdpObject.json(maxDepth));
            return client.call("Debugger.setAsyncCallStackDepth", params, result_ -> null);
        }
        /**
         * Replace previous blackbox execution contexts with passed ones. Forces backend to skip stepping/pausing in scripts in these execution contexts. VM will try to leave blackboxed script by performing &#x27;step in&#x27; several times, finally resorting to &#x27;step out&#x27; if unsuccessful.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param uniqueIds protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setBlackboxExecutionContexts(java.util.List<String> uniqueIds) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("uniqueIds", CdpObject.json(uniqueIds));
            return client.call("Debugger.setBlackboxExecutionContexts", params, result_ -> null);
        }
        /**
         * Replace previous blackbox patterns with passed ones. Forces backend to skip stepping/pausing in scripts with url matching one of the patterns. VM will try to leave blackboxed script by performing &#x27;step in&#x27; several times, finally resorting to &#x27;step out&#x27; if unsuccessful.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param patterns protocol value
         * @param skipAnonymous protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setBlackboxPatterns(java.util.List<String> patterns, Optional<Boolean> skipAnonymous) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("patterns", CdpObject.json(patterns));
            skipAnonymous.ifPresent(value_ -> params.put("skipAnonymous", value_));
            return client.call("Debugger.setBlackboxPatterns", params, result_ -> null);
        }
        /**
         * Replace previous blackbox patterns with passed ones. Forces backend to skip stepping/pausing in scripts with url matching one of the patterns. VM will try to leave blackboxed script by performing &#x27;step in&#x27; several times, finally resorting to &#x27;step out&#x27; if unsuccessful.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param patterns protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setBlackboxPatterns(java.util.List<String> patterns) {
            return setBlackboxPatterns(patterns, Optional.empty());
        }
        /**
         * Makes backend skip steps in the script in blackboxed ranges. VM will try leave blacklisted scripts by performing &#x27;step in&#x27; several times, finally resorting to &#x27;step out&#x27; if unsuccessful. Positions array contains positions where blackbox state is changed. First interval isn&#x27;t blackboxed. Array should be sorted.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param scriptId protocol value
         * @param positions protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setBlackboxedRanges(Runtime.ScriptId scriptId, java.util.List<Debugger.ScriptPosition> positions) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("scriptId", CdpObject.json(scriptId));
            params.put("positions", CdpObject.json(positions));
            return client.call("Debugger.setBlackboxedRanges", params, result_ -> null);
        }
        /**
         * Sets JavaScript breakpoint at a given location.
         * @param location protocol value
         * @param condition protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<SetBreakpointResult> setBreakpoint(Debugger.Location location, Optional<String> condition) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("location", CdpObject.json(location));
            condition.ifPresent(value_ -> params.put("condition", CdpObject.json(value_)));
            return client.call("Debugger.setBreakpoint", params, result_ -> new SetBreakpointResult(result_));
        }
        /**
         * Sets JavaScript breakpoint at a given location.
         * @param location protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<SetBreakpointResult> setBreakpoint(Debugger.Location location) {
            return setBreakpoint(location, Optional.empty());
        }
        /**
         * Sets instrumentation breakpoint.
         * @param instrumentation protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Debugger.BreakpointId> setInstrumentationBreakpoint(SetInstrumentationBreakpointInstrumentationValues instrumentation) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("instrumentation", CdpObject.json(instrumentation));
            return client.call("Debugger.setInstrumentationBreakpoint", params, result_ -> new Debugger.BreakpointId((String) java.util.Objects.requireNonNull(result_.get("breakpointId"))));
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
        public CompletionStage<SetBreakpointByUrlResult> setBreakpointByUrl(long lineNumber, Optional<String> url, Optional<String> urlRegex, Optional<String> scriptHash, OptionalLong columnNumber, Optional<String> condition) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("lineNumber", CdpObject.json(lineNumber));
            url.ifPresent(value_ -> params.put("url", CdpObject.json(value_)));
            urlRegex.ifPresent(value_ -> params.put("urlRegex", CdpObject.json(value_)));
            scriptHash.ifPresent(value_ -> params.put("scriptHash", CdpObject.json(value_)));
            columnNumber.ifPresent(value_ -> params.put("columnNumber", value_));
            condition.ifPresent(value_ -> params.put("condition", CdpObject.json(value_)));
            return client.call("Debugger.setBreakpointByUrl", params, result_ -> new SetBreakpointByUrlResult(result_));
        }
        /**
         * Sets JavaScript breakpoint at given location specified either by URL or URL regex. Once this command is issued, all existing parsed scripts will have breakpoints resolved and returned in {@code locations} property. Further matching script parsing will result in subsequent {@code breakpointResolved} events issued. This logical breakpoint will survive page reloads.
         * @param lineNumber protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<SetBreakpointByUrlResult> setBreakpointByUrl(long lineNumber) {
            return setBreakpointByUrl(lineNumber, Optional.empty(), Optional.empty(), Optional.empty(), OptionalLong.empty(), Optional.empty());
        }
        /**
         * Sets JavaScript breakpoint before each call to the given function. If another function was created from the same source as a given one, calling it will also trigger the breakpoint.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param objectId protocol value
         * @param condition protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Debugger.BreakpointId> setBreakpointOnFunctionCall(Runtime.RemoteObjectId objectId, Optional<String> condition) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("objectId", CdpObject.json(objectId));
            condition.ifPresent(value_ -> params.put("condition", CdpObject.json(value_)));
            return client.call("Debugger.setBreakpointOnFunctionCall", params, result_ -> new Debugger.BreakpointId((String) java.util.Objects.requireNonNull(result_.get("breakpointId"))));
        }
        /**
         * Sets JavaScript breakpoint before each call to the given function. If another function was created from the same source as a given one, calling it will also trigger the breakpoint.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param objectId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Debugger.BreakpointId> setBreakpointOnFunctionCall(Runtime.RemoteObjectId objectId) {
            return setBreakpointOnFunctionCall(objectId, Optional.empty());
        }
        /**
         * Activates / deactivates all breakpoints on the page.
         * @param active protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setBreakpointsActive(boolean active) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("active", CdpObject.json(active));
            return client.call("Debugger.setBreakpointsActive", params, result_ -> null);
        }
        /**
         * Defines pause on exceptions state. Can be set to stop on all exceptions, uncaught exceptions, or caught exceptions, no exceptions. Initial pause on exceptions state is {@code none}.
         * @param state protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPauseOnExceptions(SetPauseOnExceptionsStateValues state) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("state", CdpObject.json(state));
            return client.call("Debugger.setPauseOnExceptions", params, result_ -> null);
        }
        /**
         * Changes return value in top frame. Available only at return break position.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param newValue protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setReturnValue(Runtime.CallArgument newValue) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("newValue", CdpObject.json(newValue));
            return client.call("Debugger.setReturnValue", params, result_ -> null);
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
        public CompletionStage<SetScriptSourceResult> setScriptSource(Runtime.ScriptId scriptId, String scriptSource, Optional<Boolean> dryRun, Optional<Boolean> allowTopFrameEditing) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("scriptId", CdpObject.json(scriptId));
            params.put("scriptSource", CdpObject.json(scriptSource));
            dryRun.ifPresent(value_ -> params.put("dryRun", value_));
            allowTopFrameEditing.ifPresent(value_ -> params.put("allowTopFrameEditing", value_));
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
            return setScriptSource(scriptId, scriptSource, Optional.empty(), Optional.empty());
        }
        /**
         * Makes page not interrupt on any pauses (breakpoint, exception, dom exception etc).
         * @param skip protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSkipAllPauses(boolean skip) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("skip", CdpObject.json(skip));
            return client.call("Debugger.setSkipAllPauses", params, result_ -> null);
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
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("scopeNumber", CdpObject.json(scopeNumber));
            params.put("variableName", CdpObject.json(variableName));
            params.put("newValue", CdpObject.json(newValue));
            params.put("callFrameId", CdpObject.json(callFrameId));
            return client.call("Debugger.setVariableValue", params, result_ -> null);
        }
        /**
         * Steps into the function call.
         * @param breakOnAsyncCall protocol value
         * @param skipList protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> stepInto(Optional<Boolean> breakOnAsyncCall, Optional<java.util.List<Debugger.LocationRange>> skipList) {
            Map<String, Object> params = new LinkedHashMap<>();
            breakOnAsyncCall.ifPresent(value_ -> params.put("breakOnAsyncCall", value_));
            skipList.ifPresent(value_ -> params.put("skipList", CdpObject.json(value_)));
            return client.call("Debugger.stepInto", params, result_ -> null);
        }
        /**
         * Steps into the function call.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> stepInto() {
            return stepInto(Optional.empty(), Optional.empty());
        }
        /**
         * Steps out of the function call.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> stepOut() {
            return client.call("Debugger.stepOut", null, result_ -> null);
        }
        /**
         * Steps over the statement.
         * @param skipList protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> stepOver(Optional<java.util.List<Debugger.LocationRange>> skipList) {
            Map<String, Object> params = new LinkedHashMap<>();
            skipList.ifPresent(value_ -> params.put("skipList", CdpObject.json(value_)));
            return client.call("Debugger.stepOver", params, result_ -> null);
        }
        /**
         * Steps over the statement.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> stepOver() {
            return stepOver(Optional.empty());
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
