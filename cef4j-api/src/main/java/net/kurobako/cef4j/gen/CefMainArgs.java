// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Structure representing CefExecuteProcess arguments. */
public final class CefMainArgs {

    public final int argc;
    public final long argv;

    public CefMainArgs(int argc, long argv) {
        this.argc = argc;
        this.argv = argv;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefMainArgs)) return false;
        CefMainArgs other = (CefMainArgs) obj;
        return this.argc == other.argc && java.util.Objects.equals(this.argv, other.argv);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(argc, argv);
    }

    @Override
    public String toString() {
        return "CefMainArgs{" + "argc=" + argc + ", " + "argv=" + argv + "}";
    }
}
