// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Class used to create and/or parse command line arguments. Arguments with "--", "-" and, on Windows, "/" prefixes are considered switches. Switches will always precede any arguments without switch prefixes. Switches can optionally have a value specified using the "=" delimiter (e.g. "-switch=value"). An argument of "--" will terminate switch parsing with all subsequent tokens, regardless of prefix, being interpreted as non-switch arguments. Switch names should be lowercase ASCII and will be converted to such if necessary. Switch values will retain the original case and UTF8 encoding. This class can be used before CefInitialize() is called.
 * <p>Definition generated from cef_command_line_capi.h
 * <pre>typedef struct _cef_command_line_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_command_line_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__command__line_8h.html">cef_command_line.h:46</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused", "NullableForbidden"})
public interface CefCommandLine extends CefLibraryObject {

    /**
     * Returns {@code true} if this object is valid. Do not call any other methods if this function returns {@code false}.
     * <p>Definition generated from cef_command_line_capi.h
     * <pre>int (CEF_CALLBACK* is_valid)(struct _cef_command_line_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__command__line_8h.html">cef_command_line.h:76</a>
     */
    boolean isValid();

    /**
     * Returns {@code true} if the values of this object are read-only. Some APIs may expose read-only objects.
     * <p>Definition generated from cef_command_line_capi.h
     * <pre>int (CEF_CALLBACK* is_read_only)(struct _cef_command_line_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__command__line_8h.html">cef_command_line.h:83</a>
     */
    boolean isReadOnly();

    /**
     * Returns a writable copy of this object.
     * <p>Definition generated from cef_command_line_capi.h
     * <pre>cef_command_line_t* (CEF_CALLBACK* copy)(struct _cef_command_line_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__command__line_8h.html">cef_command_line.h:90</a>
     */
    Optional<CefCommandLine> copy();

    /**
     * Initialize the command line with the specified {@code argv} and {@code argc} values. The first argument must be the name of the program. This method is only supported on non-Windows platforms.
     * <p>Definition generated from cef_command_line_capi.h
     * <pre>void (CEF_CALLBACK* init_from_argv)(struct _cef_command_line_t* self, int argc, const char* const* argv);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__command__line_8h.html">cef_command_line.h:96</a>
     */
    void initFromArgv(int argc, @Nonnull List<String> argv);

    /**
     * Initialize the command line with the string returned by calling GetCommandLineW(). This method is only supported on Windows.
     * <p>Definition generated from cef_command_line_capi.h
     * <pre>void (CEF_CALLBACK* init_from_string)(struct _cef_command_line_t* self, const cef_string_t* command_line);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__command__line_8h.html">cef_command_line.h:104</a>
     */
    void initFromString(@Nullable String commandLine);

    /**
     * Reset the command-line switches and arguments but leave the program component unchanged.
     * <p>Definition generated from cef_command_line_capi.h
     * <pre>void (CEF_CALLBACK* reset)(struct _cef_command_line_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__command__line_8h.html">cef_command_line.h:111</a>
     */
    void reset();

    /**
     * Retrieve the original command line string as a vector of strings. The argv array: `{ program, [(--{@code -}/)switch[=value]]*, [--], [argument]* }`
     * <p>Definition generated from cef_command_line_capi.h
     * <pre>void (CEF_CALLBACK* get_argv)(struct _cef_command_line_t* self, cef_string_list_t argv);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__command__line_8h.html">cef_command_line.h:118</a>
     */
    void getArgv(@Nonnull List<String> argv);

    /**
     * Constructs and returns the represented command line string. Use this method cautiously because quoting behavior is unclear.
     * <p>Definition generated from cef_command_line_capi.h
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_command_line_string)(struct _cef_command_line_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__command__line_8h.html">cef_command_line.h:126</a>
     */
    Optional<String> getCommandLineString();

    /**
     * Get the program part of the command line string (the first item).
     * <p>Definition generated from cef_command_line_capi.h
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_program)(struct _cef_command_line_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__command__line_8h.html">cef_command_line.h:133</a>
     */
    Optional<String> getProgram();

    /**
     * Set the program part of the command line string (the first item).
     * <p>Definition generated from cef_command_line_capi.h
     * <pre>void (CEF_CALLBACK* set_program)(struct _cef_command_line_t* self, const cef_string_t* program);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__command__line_8h.html">cef_command_line.h:139</a>
     */
    void setProgram(@Nullable String program);

    /**
     * Returns {@code true} if the command line has switches.
     * <p>Definition generated from cef_command_line_capi.h
     * <pre>int (CEF_CALLBACK* has_switches)(struct _cef_command_line_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__command__line_8h.html">cef_command_line.h:145</a>
     */
    boolean hasSwitches();

    /**
     * Returns {@code true} if the command line contains the given switch.
     * <p>Definition generated from cef_command_line_capi.h
     * <pre>int (CEF_CALLBACK* has_switch)(struct _cef_command_line_t* self, const cef_string_t* name);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__command__line_8h.html">cef_command_line.h:151</a>
     */
    boolean hasSwitch(@Nullable String name);

    /**
     * Returns the value associated with the given switch. If the switch has no value or isn't present this method returns the empty string.
     * <p>Definition generated from cef_command_line_capi.h
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_switch_value)(struct _cef_command_line_t* self, const cef_string_t* name);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__command__line_8h.html">cef_command_line.h:157</a>
     */
    Optional<String> getSwitchValue(@Nullable String name);

    /**
     * Returns the map of switch names and values. If a switch has no value an empty string is returned.
     * <p>Definition generated from cef_command_line_capi.h
     * <pre>void (CEF_CALLBACK* get_switches)(struct _cef_command_line_t* self, cef_string_map_t switches);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__command__line_8h.html">cef_command_line.h:164</a>
     */
    void getSwitches(@Nonnull Map<String, String> switches);

    /**
     * Add a switch to the end of the command line.
     * <p>Definition generated from cef_command_line_capi.h
     * <pre>void (CEF_CALLBACK* append_switch)(struct _cef_command_line_t* self, const cef_string_t* name);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__command__line_8h.html">cef_command_line.h:171</a>
     */
    void appendSwitch(@Nullable String name);

    /**
     * Add a switch with the specified value to the end of the command line. If the switch has no value pass an empty value string.
     * <p>Definition generated from cef_command_line_capi.h
     * <pre>void (CEF_CALLBACK* append_switch_with_value)(struct _cef_command_line_t* self, const cef_string_t* name, const cef_string_t* value);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__command__line_8h.html">cef_command_line.h:177</a>
     */
    void appendSwitchWithValue(@Nullable String name, @Nullable String value);

    /**
     * True if there are remaining command line arguments.
     * <p>Definition generated from cef_command_line_capi.h
     * <pre>int (CEF_CALLBACK* has_arguments)(struct _cef_command_line_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__command__line_8h.html">cef_command_line.h:194</a>
     */
    boolean hasArguments();

    /**
     * Get the remaining command line arguments.
     * <p>Definition generated from cef_command_line_capi.h
     * <pre>void (CEF_CALLBACK* get_arguments)(struct _cef_command_line_t* self, cef_string_list_t arguments);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__command__line_8h.html">cef_command_line.h:200</a>
     */
    void getArguments(@Nonnull List<String> arguments);

    /**
     * Add an argument to the end of the command line.
     * <p>Definition generated from cef_command_line_capi.h
     * <pre>void (CEF_CALLBACK* append_argument)(struct _cef_command_line_t* self, const cef_string_t* argument);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__command__line_8h.html">cef_command_line.h:206</a>
     */
    void appendArgument(@Nullable String argument);

    /**
     * Insert a command before the current command. Common for debuggers, like "valgrind" or "gdb --args".
     * <p>Definition generated from cef_command_line_capi.h
     * <pre>void (CEF_CALLBACK* prepend_wrapper)(struct _cef_command_line_t* self, const cef_string_t* wrapper);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__command__line_8h.html">cef_command_line.h:212</a>
     */
    void prependWrapper(@Nullable String wrapper);

    /**
     * Remove a switch from the command line. If no such switch is present, this has no effect.
     * <p>Added in CEF API version 14100.
     * <p>Definition generated from cef_command_line_capi.h
     * <pre>void (CEF_CALLBACK* remove_switch)(struct _cef_command_line_t* self, const cef_string_t* name);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__command__line_8h.html">cef_command_line.h:186</a>
     */
    void removeSwitch(@Nullable String name);
    /**
     * Create a new CefCommandLine instance.
     * <p>Definition generated from cef_command_line_capi.h
     * <pre>CEF_EXPORT cef_command_line_t* cef_command_line_create(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__command__line_8h.html">cef_command_line.h:63</a>
     */
    static Optional<CefCommandLine> create() {
      return Optional.ofNullable(NativePeer.create0());
  }

    /**
     * Returns the singleton global CefCommandLine object. The returned object will be read-only.
     * <p>Definition generated from cef_command_line_capi.h
     * <pre>CEF_EXPORT cef_command_line_t* cef_command_line_get_global(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__command__line_8h.html">cef_command_line.h:69</a>
     */
    static Optional<CefCommandLine> getGlobal() {
      return Optional.ofNullable(NativePeer.getGlobal0());
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
        public void peerClose() {
            closed = true;
            cleanable.clean();
        }

        @Override
        public boolean peerIsClosed() {
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
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
      public boolean isValid() {
          checkNotClosed();
          return isValid0(nativePtr);
      }

        @Override
      public boolean isReadOnly() {
          checkNotClosed();
          return isReadOnly0(nativePtr);
      }

        @Override
      public Optional<CefCommandLine> copy() {
          checkNotClosed();
          return Optional.ofNullable(copy0(nativePtr));
      }

        @Override
      public void initFromArgv(int argc, @Nonnull List<String> argv) {
          checkNotClosed();
          initFromArgv0(nativePtr, argc, argv);
      }

        @Override
      public void initFromString(@Nullable String commandLine) {
          checkNotClosed();
          initFromString0(nativePtr, commandLine);
      }

        @Override
      public void reset() {
          checkNotClosed();
          reset0(nativePtr);
      }

        @Override
      public void getArgv(@Nonnull List<String> argv) {
          checkNotClosed();
          getArgv0(nativePtr, argv);
      }

        @Override
      public Optional<String> getCommandLineString() {
          checkNotClosed();
          return Optional.ofNullable(getCommandLineString0(nativePtr));
      }

        @Override
      public Optional<String> getProgram() {
          checkNotClosed();
          return Optional.ofNullable(getProgram0(nativePtr));
      }

        @Override
      public void setProgram(@Nullable String program) {
          checkNotClosed();
          setProgram0(nativePtr, program);
      }

        @Override
      public boolean hasSwitches() {
          checkNotClosed();
          return hasSwitches0(nativePtr);
      }

        @Override
      public boolean hasSwitch(@Nullable String name) {
          checkNotClosed();
          return hasSwitch0(nativePtr, name);
      }

        @Override
      public Optional<String> getSwitchValue(@Nullable String name) {
          checkNotClosed();
          return Optional.ofNullable(getSwitchValue0(nativePtr, name));
      }

        @Override
      public void getSwitches(@Nonnull Map<String, String> switches) {
          checkNotClosed();
          getSwitches0(nativePtr, switches);
      }

        @Override
      public void appendSwitch(@Nullable String name) {
          checkNotClosed();
          appendSwitch0(nativePtr, name);
      }

        @Override
      public void appendSwitchWithValue(@Nullable String name, @Nullable String value) {
          checkNotClosed();
          appendSwitchWithValue0(nativePtr, name, value);
      }

        @Override
      public boolean hasArguments() {
          checkNotClosed();
          return hasArguments0(nativePtr);
      }

        @Override
      public void getArguments(@Nonnull List<String> arguments) {
          checkNotClosed();
          getArguments0(nativePtr, arguments);
      }

        @Override
      public void appendArgument(@Nullable String argument) {
          checkNotClosed();
          appendArgument0(nativePtr, argument);
      }

        @Override
      public void prependWrapper(@Nullable String wrapper) {
          checkNotClosed();
          prependWrapper0(nativePtr, wrapper);
      }

        @Override
      public void removeSwitch(@Nullable String name) {
          checkNotClosed();
          removeSwitch0(nativePtr, name);
      }


        static native boolean isValid0(long self);

        static native boolean isReadOnly0(long self);

        static native CefCommandLine copy0(long self);

        static native void initFromArgv0(long self, int argc, @Nonnull List<String> argv);

        static native void initFromString0(long self, @Nullable String commandLine);

        static native void reset0(long self);

        static native void getArgv0(long self, @Nonnull List<String> argv);

        static native String getCommandLineString0(long self);

        static native String getProgram0(long self);

        static native void setProgram0(long self, @Nullable String program);

        static native boolean hasSwitches0(long self);

        static native boolean hasSwitch0(long self, @Nullable String name);

        static native String getSwitchValue0(long self, @Nullable String name);

        static native void getSwitches0(long self, @Nonnull Map<String, String> switches);

        static native void appendSwitch0(long self, @Nullable String name);

        static native void appendSwitchWithValue0(long self, @Nullable String name, @Nullable String value);

        static native boolean hasArguments0(long self);

        static native void getArguments0(long self, @Nonnull List<String> arguments);

        static native void appendArgument0(long self, @Nullable String argument);

        static native void prependWrapper0(long self, @Nullable String wrapper);

        static native void removeSwitch0(long self, @Nullable String name);

        static native CefCommandLine create0();
        static native CefCommandLine getGlobal0();

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
