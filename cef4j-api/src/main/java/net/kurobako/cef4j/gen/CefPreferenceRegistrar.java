// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Class that manages custom preference registrations. */
public final class CefPreferenceRegistrar {

    public final CefBaseScoped base;

    public CefPreferenceRegistrar(CefBaseScoped base) {
        this.base = base;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefPreferenceRegistrar)) return false;
        CefPreferenceRegistrar other = (CefPreferenceRegistrar) obj;
        return java.util.Objects.equals(this.base, other.base);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(base);
    }

    @Override
    public String toString() {
        return "CefPreferenceRegistrar{" + "base=" + base + "}";
    }
}
