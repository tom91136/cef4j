// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.Nonnull;

/**
 * Class used to create and/or parse command line arguments. Arguments with "--", "-" and, on Windows, "/" prefixes are
 * considered switches. Switches will always precede any arguments without switch prefixes. Switches can optionally have
 * a value specified using the "=" delimiter (e.g. "-switch=value"). An argument of "--" will terminate switch parsing
 * with all subsequent tokens, regardless of prefix, being interpreted as non-switch arguments. Switch names should be
 * lowercase ASCII and will be converted to such if necessary. Switch values will retain the original case and UTF8
 * encoding. This class can be used before CefInitialize() is called.
 */
public interface CefCommandLine {

    /** Returns true if this object is valid. Do not call any other methods if this function returns false. */
    boolean isValid();

    /** Returns true if the values of this object are read-only. Some APIs may expose read-only objects. */
    boolean isReadOnly();

    /** Returns a writable copy of this object. */
    long copy();

    /**
     * Initialize the command line with the specified |argc| and |argv| values. The first argument must be the name of
     * the program. This method is only supported on non-Windows platforms.
     */
    void initFromArgv(int argc, long argv);

    /**
     * Initialize the command line with the string returned by calling GetCommandLineW(). This method is only supported
     * on Windows.
     */
    void initFromString(@Nonnull String commandLine);

    /** Put the event in the un-signaled state. */
    void reset();

    /**
     * Retrieve the original command line string as a vector of strings. The argv array: `{ program,
     * [(--|-|/)switch[=value]]*, [--], [argument]* }`
     */
    void getArgv(@Nonnull java.util.List<String> argv);

    /**
     * Constructs and returns the represented command line string. Use this method cautiously because quoting behavior
     * is unclear.
     */
    Optional<String> getCommandLineString();

    /** Get the program part of the command line string (the first item). */
    Optional<String> getProgram();

    /** Set the program part of the command line string (the first item). */
    void setProgram(@Nonnull String program);

    /** Returns true if the command line has switches. */
    boolean hasSwitches();

    /** Returns true if the command line contains the given switch. */
    boolean hasSwitch(@Nonnull String name);

    /**
     * Returns the value associated with the given switch. If the switch has no value or isn't present this method
     * returns the empty string.
     */
    Optional<String> getSwitchValue(@Nonnull String name);

    /** Returns the map of switch names and values. If a switch has no value an empty string is returned. */
    void getSwitches(@Nonnull java.util.Map<String, String> switches);

    /** Add a switch to the end of the command line. */
    void appendSwitch(@Nonnull String name);

    /**
     * Add a switch with the specified value to the end of the command line. If the switch has no value pass an empty
     * value string.
     */
    void appendSwitchWithValue(@Nonnull String name, @Nonnull String value);

    /** True if there are remaining command line arguments. */
    boolean hasArguments();

    /** Get the remaining command line arguments. */
    void getArguments(@Nonnull java.util.List<String> arguments);

    /** Add an argument to the end of the command line. */
    void appendArgument(@Nonnull String argument);

    /** Insert a command before the current command. Common for debuggers, like "valgrind" or "gdb --args". */
    void prependWrapper(@Nonnull String wrapper);

    /**
     * Remove a switch from the command line. If no such switch is present, this has no effect.
     *
     * <p>Added in CEF API version 14100.
     */
    void removeSwitch(@Nonnull String name);

    static class NativePeer implements CefCommandLine {
        private volatile long nativePtr;

        @Override
        public boolean isValid() {
            return N_IsValid(nativePtr);
        }

        @Override
        public boolean isReadOnly() {
            return N_IsReadOnly(nativePtr);
        }

        @Override
        public long copy() {
            return N_Copy(nativePtr);
        }

        @Override
        public void initFromArgv(int argc, long argv) {
            N_InitFromArgv(nativePtr, argc, argv);
        }

        @Override
        public void initFromString(String commandLine) {
            N_InitFromString(nativePtr, commandLine);
        }

        @Override
        public void reset() {
            N_Reset(nativePtr);
        }

        @Override
        public void getArgv(java.util.List<String> argv) {
            N_GetArgv(nativePtr, argv);
        }

        @Override
        public Optional<String> getCommandLineString() {
            return Optional.ofNullable(N_GetCommandLineString(nativePtr));
        }

        @Override
        public Optional<String> getProgram() {
            return Optional.ofNullable(N_GetProgram(nativePtr));
        }

        @Override
        public void setProgram(String program) {
            N_SetProgram(nativePtr, program);
        }

        @Override
        public boolean hasSwitches() {
            return N_HasSwitches(nativePtr);
        }

        @Override
        public boolean hasSwitch(String name) {
            return N_HasSwitch(nativePtr, name);
        }

        @Override
        public Optional<String> getSwitchValue(String name) {
            return Optional.ofNullable(N_GetSwitchValue(nativePtr, name));
        }

        @Override
        public void getSwitches(java.util.Map<String, String> switches) {
            N_GetSwitches(nativePtr, switches);
        }

        @Override
        public void appendSwitch(String name) {
            N_AppendSwitch(nativePtr, name);
        }

        @Override
        public void appendSwitchWithValue(String name, String value) {
            N_AppendSwitchWithValue(nativePtr, name, value);
        }

        @Override
        public boolean hasArguments() {
            return N_HasArguments(nativePtr);
        }

        @Override
        public void getArguments(java.util.List<String> arguments) {
            N_GetArguments(nativePtr, arguments);
        }

        @Override
        public void appendArgument(String argument) {
            N_AppendArgument(nativePtr, argument);
        }

        @Override
        public void prependWrapper(String wrapper) {
            N_PrependWrapper(nativePtr, wrapper);
        }

        @Override
        public void removeSwitch(String name) {
            N_RemoveSwitch(nativePtr, name);
        }

        private native boolean N_IsValid(long self);

        private native boolean N_IsReadOnly(long self);

        private native long N_Copy(long self);

        private native void N_InitFromArgv(long self, int argc, long argv);

        private native void N_InitFromString(long self, String commandLine);

        private native void N_Reset(long self);

        private native void N_GetArgv(long self, java.util.List<String> argv);

        private native String N_GetCommandLineString(long self);

        private native String N_GetProgram(long self);

        private native void N_SetProgram(long self, String program);

        private native boolean N_HasSwitches(long self);

        private native boolean N_HasSwitch(long self, String name);

        private native String N_GetSwitchValue(long self, String name);

        private native void N_GetSwitches(long self, java.util.Map<String, String> switches);

        private native void N_AppendSwitch(long self, String name);

        private native void N_AppendSwitchWithValue(long self, String name, String value);

        private native boolean N_HasArguments(long self);

        private native void N_GetArguments(long self, java.util.List<String> arguments);

        private native void N_AppendArgument(long self, String argument);

        private native void N_PrependWrapper(long self, String wrapper);

        private native void N_RemoveSwitch(long self, String name);

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
