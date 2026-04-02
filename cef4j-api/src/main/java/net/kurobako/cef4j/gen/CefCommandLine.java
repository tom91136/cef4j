// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Class used to create and/or parse command line arguments. Arguments with "--", "-" and, on Windows, "/" prefixes are
 * considered switches. Switches will always precede any arguments without switch prefixes. Switches can optionally have
 * a value specified using the "=" delimiter (e.g. "-switch=value"). An argument of "--" will terminate switch parsing
 * with all subsequent tokens, regardless of prefix, being interpreted as non-switch arguments. Switch names should be
 * lowercase ASCII and will be converted to such if necessary. Switch values will retain the original case and UTF8
 * encoding. This class can be used before CefInitialize() is called.
 *
 * <p>Definition generated from cef_command_line_capi.h
 *
 * <pre>typedef struct _cef_command_line_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_command_line_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__command__line_8h.html">cef_command_line.h:46</a>
 */
public interface CefCommandLine extends CefLibraryObject {

    /**
     * Returns {@code true} if this object is valid. Do not call any other methods if this function returns
     * {@code false}.
     *
     * <p>Definition generated from cef_command_line_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_valid)(struct _cef_command_line_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__command__line_8h.html">cef_command_line.h:76</a>
     */
    boolean isValid();

    /**
     * Returns {@code true} if the values of this object are read-only. Some APIs may expose read-only objects.
     *
     * <p>Definition generated from cef_command_line_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_read_only)(struct _cef_command_line_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__command__line_8h.html">cef_command_line.h:83</a>
     */
    boolean isReadOnly();

    /**
     * Returns a writable copy of this object.
     *
     * <p>Definition generated from cef_command_line_capi.h
     *
     * <pre>cef_command_line_t* (CEF_CALLBACK* copy)(struct _cef_command_line_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__command__line_8h.html">cef_command_line.h:90</a>
     */
    Optional<CefCommandLine> copy();

    /**
     * Initialize the command line with the specified {@code argc} and {@code argv} values. The first argument must be
     * the name of the program. This method is only supported on non-Windows platforms.
     *
     * <p>Definition generated from cef_command_line_capi.h
     *
     * <pre>void (CEF_CALLBACK* init_from_argv)(struct _cef_command_line_t* self, int argc, const char* const* argv);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__command__line_8h.html">cef_command_line.h:96</a>
     */
    void initFromArgv(int argc, @Nullable NativePointer argv);

    /**
     * Initialize the command line with the string returned by calling GetCommandLineW(). This method is only supported
     * on Windows.
     *
     * <p>Definition generated from cef_command_line_capi.h
     *
     * <pre>void (CEF_CALLBACK* init_from_string)(struct _cef_command_line_t* self, const cef_string_t* command_line);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__command__line_8h.html">cef_command_line.h:104</a>
     */
    void initFromString(@Nullable String commandLine);

    /**
     * Reset the command-line switches and arguments but leave the program component unchanged.
     *
     * <p>Definition generated from cef_command_line_capi.h
     *
     * <pre>void (CEF_CALLBACK* reset)(struct _cef_command_line_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__command__line_8h.html">cef_command_line.h:111</a>
     */
    void reset();

    /**
     * Retrieve the original command line string as a vector of strings. The argv array: `{ program,
     * [(--{@code -}/)switch[=value]]*, [--], [argument]* }`
     *
     * <p>Definition generated from cef_command_line_capi.h
     *
     * <pre>void (CEF_CALLBACK* get_argv)(struct _cef_command_line_t* self, cef_string_list_t argv);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__command__line_8h.html">cef_command_line.h:118</a>
     */
    void getArgv(@Nonnull List<String> argv);

    /**
     * Constructs and returns the represented command line string. Use this method cautiously because quoting behavior
     * is unclear.
     *
     * <p>Definition generated from cef_command_line_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_command_line_string)(struct _cef_command_line_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__command__line_8h.html">cef_command_line.h:126</a>
     */
    Optional<String> getCommandLineString();

    /**
     * Get the program part of the command line string (the first item).
     *
     * <p>Definition generated from cef_command_line_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_program)(struct _cef_command_line_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__command__line_8h.html">cef_command_line.h:133</a>
     */
    Optional<String> getProgram();

    /**
     * Set the program part of the command line string (the first item).
     *
     * <p>Definition generated from cef_command_line_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_program)(struct _cef_command_line_t* self, const cef_string_t* program);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__command__line_8h.html">cef_command_line.h:139</a>
     */
    void setProgram(@Nullable String program);

    /**
     * Returns {@code true} if the command line has switches.
     *
     * <p>Definition generated from cef_command_line_capi.h
     *
     * <pre>int (CEF_CALLBACK* has_switches)(struct _cef_command_line_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__command__line_8h.html">cef_command_line.h:145</a>
     */
    boolean hasSwitches();

    /**
     * Returns {@code true} if the command line contains the given switch.
     *
     * <p>Definition generated from cef_command_line_capi.h
     *
     * <pre>int (CEF_CALLBACK* has_switch)(struct _cef_command_line_t* self, const cef_string_t* name);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__command__line_8h.html">cef_command_line.h:151</a>
     */
    boolean hasSwitch(@Nullable String name);

    /**
     * Returns the value associated with the given switch. If the switch has no value or isn't present this method
     * returns the empty string.
     *
     * <p>Definition generated from cef_command_line_capi.h
     *
     * <pre>
     * cef_string_userfree_t (CEF_CALLBACK* get_switch_value)(struct _cef_command_line_t* self, const cef_string_t* name);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__command__line_8h.html">cef_command_line.h:157</a>
     */
    Optional<String> getSwitchValue(@Nullable String name);

    /**
     * Returns the map of switch names and values. If a switch has no value an empty string is returned.
     *
     * <p>Definition generated from cef_command_line_capi.h
     *
     * <pre>void (CEF_CALLBACK* get_switches)(struct _cef_command_line_t* self, cef_string_map_t switches);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__command__line_8h.html">cef_command_line.h:164</a>
     */
    void getSwitches(@Nonnull Map<String, String> switches);

    /**
     * Add a switch to the end of the command line.
     *
     * <p>Definition generated from cef_command_line_capi.h
     *
     * <pre>void (CEF_CALLBACK* append_switch)(struct _cef_command_line_t* self, const cef_string_t* name);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__command__line_8h.html">cef_command_line.h:171</a>
     */
    void appendSwitch(@Nullable String name);

    /**
     * Add a switch with the specified value to the end of the command line. If the switch has no value pass an empty
     * value string.
     *
     * <p>Definition generated from cef_command_line_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* append_switch_with_value)(struct _cef_command_line_t* self, const cef_string_t* name, const cef_string_t* value);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__command__line_8h.html">cef_command_line.h:177</a>
     */
    void appendSwitchWithValue(@Nullable String name, @Nullable String value);

    /**
     * True if there are remaining command line arguments.
     *
     * <p>Definition generated from cef_command_line_capi.h
     *
     * <pre>int (CEF_CALLBACK* has_arguments)(struct _cef_command_line_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__command__line_8h.html">cef_command_line.h:194</a>
     */
    boolean hasArguments();

    /**
     * Get the remaining command line arguments.
     *
     * <p>Definition generated from cef_command_line_capi.h
     *
     * <pre>void (CEF_CALLBACK* get_arguments)(struct _cef_command_line_t* self, cef_string_list_t arguments);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__command__line_8h.html">cef_command_line.h:200</a>
     */
    void getArguments(@Nonnull List<String> arguments);

    /**
     * Add an argument to the end of the command line.
     *
     * <p>Definition generated from cef_command_line_capi.h
     *
     * <pre>void (CEF_CALLBACK* append_argument)(struct _cef_command_line_t* self, const cef_string_t* argument);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__command__line_8h.html">cef_command_line.h:206</a>
     */
    void appendArgument(@Nullable String argument);

    /**
     * Insert a command before the current command. Common for debuggers, like "valgrind" or "gdb --args".
     *
     * <p>Definition generated from cef_command_line_capi.h
     *
     * <pre>void (CEF_CALLBACK* prepend_wrapper)(struct _cef_command_line_t* self, const cef_string_t* wrapper);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__command__line_8h.html">cef_command_line.h:212</a>
     */
    void prependWrapper(@Nullable String wrapper);

    /**
     * Remove a switch from the command line. If no such switch is present, this has no effect.
     *
     * <p>Added in CEF API version 14100.
     *
     * <p>Definition generated from cef_command_line_capi.h
     *
     * <pre>void (CEF_CALLBACK* remove_switch)(struct _cef_command_line_t* self, const cef_string_t* name);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__command__line_8h.html">cef_command_line.h:186</a>
     */
    void removeSwitch(@Nullable String name);
    /**
     * Create a new backing store with allocated memory of {@code byte_length} bytes. The memory is uninitialized. This
     * method must be called on a thread with a valid V8 isolate. The returned object can safely be passed to other
     * threads. Returns {@code null} on failure.
     *
     * <p>Definition generated from cef_command_line_capi.h
     *
     * <pre>CEF_EXPORT cef_command_line_t* cef_command_line_create(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:445</a>
     */
    static Optional<CefCommandLine> create() {
        return Optional.ofNullable(NativePeer.N_Create());
    }

    /**
     * Returns the global object for this context. The context must be entered before calling this method.
     *
     * <p>Definition generated from cef_command_line_capi.h
     *
     * <pre>CEF_EXPORT cef_command_line_t* cef_command_line_get_global(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:177</a>
     */
    static Optional<CefCommandLine> getGlobal() {
        return Optional.ofNullable(NativePeer.N_GetGlobal());
    }

    final class NativePeer implements CefCommandLine, AutoCloseable {
        private final long nativePtr;
        private final java.lang.ref.Cleaner.Cleanable cleanable;
        private volatile boolean closed;

        NativePeer(long ptr) {
            this.nativePtr = ptr;
            this.cleanable = net.kurobako.cef4j.NativeCleaner.INSTANCE.register(this, new Release(ptr));
        }

        @Override
        public void close() {
            closed = true;
            cleanable.clean();
        }

        @Override
        public boolean isClosed() {
            return closed;
        }

        private void checkNotClosed() {
            if (closed) throw new IllegalStateException("CefCommandLine has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefCommandLine.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefCommandLine 0x{}", Long.toHexString(ptr));
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

        @Override
        public boolean isValid() {
            checkNotClosed();
            return N_IsValid(nativePtr);
        }

        @Override
        public boolean isReadOnly() {
            checkNotClosed();
            return N_IsReadOnly(nativePtr);
        }

        @Override
        public Optional<CefCommandLine> copy() {
            checkNotClosed();
            return Optional.ofNullable(N_Copy(nativePtr));
        }

        @Override
        public void initFromArgv(int argc, @Nullable NativePointer argv) {
            checkNotClosed();
            N_InitFromArgv(nativePtr, argc, argv);
        }

        @Override
        public void initFromString(@Nullable String commandLine) {
            checkNotClosed();
            N_InitFromString(nativePtr, commandLine);
        }

        @Override
        public void reset() {
            checkNotClosed();
            N_Reset(nativePtr);
        }

        @Override
        public void getArgv(@Nonnull List<String> argv) {
            checkNotClosed();
            N_GetArgv(nativePtr, argv);
        }

        @Override
        public Optional<String> getCommandLineString() {
            checkNotClosed();
            return Optional.ofNullable(N_GetCommandLineString(nativePtr));
        }

        @Override
        public Optional<String> getProgram() {
            checkNotClosed();
            return Optional.ofNullable(N_GetProgram(nativePtr));
        }

        @Override
        public void setProgram(@Nullable String program) {
            checkNotClosed();
            N_SetProgram(nativePtr, program);
        }

        @Override
        public boolean hasSwitches() {
            checkNotClosed();
            return N_HasSwitches(nativePtr);
        }

        @Override
        public boolean hasSwitch(@Nullable String name) {
            checkNotClosed();
            return N_HasSwitch(nativePtr, name);
        }

        @Override
        public Optional<String> getSwitchValue(@Nullable String name) {
            checkNotClosed();
            return Optional.ofNullable(N_GetSwitchValue(nativePtr, name));
        }

        @Override
        public void getSwitches(@Nonnull Map<String, String> switches) {
            checkNotClosed();
            N_GetSwitches(nativePtr, switches);
        }

        @Override
        public void appendSwitch(@Nullable String name) {
            checkNotClosed();
            N_AppendSwitch(nativePtr, name);
        }

        @Override
        public void appendSwitchWithValue(@Nullable String name, @Nullable String value) {
            checkNotClosed();
            N_AppendSwitchWithValue(nativePtr, name, value);
        }

        @Override
        public boolean hasArguments() {
            checkNotClosed();
            return N_HasArguments(nativePtr);
        }

        @Override
        public void getArguments(@Nonnull List<String> arguments) {
            checkNotClosed();
            N_GetArguments(nativePtr, arguments);
        }

        @Override
        public void appendArgument(@Nullable String argument) {
            checkNotClosed();
            N_AppendArgument(nativePtr, argument);
        }

        @Override
        public void prependWrapper(@Nullable String wrapper) {
            checkNotClosed();
            N_PrependWrapper(nativePtr, wrapper);
        }

        @Override
        public void removeSwitch(@Nullable String name) {
            checkNotClosed();
            N_RemoveSwitch(nativePtr, name);
        }

        private static native boolean N_IsValid(long self);

        private static native boolean N_IsReadOnly(long self);

        private static native CefCommandLine N_Copy(long self);

        private static native void N_InitFromArgv(long self, int argc, NativePointer argv);

        private static native void N_InitFromString(long self, String commandLine);

        private static native void N_Reset(long self);

        private static native void N_GetArgv(long self, List<String> argv);

        private static native String N_GetCommandLineString(long self);

        private static native String N_GetProgram(long self);

        private static native void N_SetProgram(long self, String program);

        private static native boolean N_HasSwitches(long self);

        private static native boolean N_HasSwitch(long self, String name);

        private static native String N_GetSwitchValue(long self, String name);

        private static native void N_GetSwitches(long self, Map<String, String> switches);

        private static native void N_AppendSwitch(long self, String name);

        private static native void N_AppendSwitchWithValue(long self, String name, String value);

        private static native boolean N_HasArguments(long self);

        private static native void N_GetArguments(long self, List<String> arguments);

        private static native void N_AppendArgument(long self, String argument);

        private static native void N_PrependWrapper(long self, String wrapper);

        private static native void N_RemoveSwitch(long self, String name);

        static native CefCommandLine N_Create();

        static native CefCommandLine N_GetGlobal();

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof NativePeer)) return false;
            return this.nativePtr == ((NativePeer) obj).nativePtr;
        }

        @Override
        public int hashCode() {
            return Long.hashCode(nativePtr);
        }

        @Override
        public String toString() {
            return "CefCommandLine{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
