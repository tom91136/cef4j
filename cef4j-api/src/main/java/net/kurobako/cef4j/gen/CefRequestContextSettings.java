// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Request context initialization settings. Specify NULL or 0 to get the recommended default values. */
public final class CefRequestContextSettings {

    public final long size;
    public final int cachePath;
    public final int persistSessionCookies;
    public final int acceptLanguageList;
    public final int cookieableSchemesList;
    public final int cookieableSchemesExcludeDefaults;

    public CefRequestContextSettings(
            long size,
            int cachePath,
            int persistSessionCookies,
            int acceptLanguageList,
            int cookieableSchemesList,
            int cookieableSchemesExcludeDefaults) {
        this.size = size;
        this.cachePath = cachePath;
        this.persistSessionCookies = persistSessionCookies;
        this.acceptLanguageList = acceptLanguageList;
        this.cookieableSchemesList = cookieableSchemesList;
        this.cookieableSchemesExcludeDefaults = cookieableSchemesExcludeDefaults;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefRequestContextSettings)) return false;
        CefRequestContextSettings other = (CefRequestContextSettings) obj;
        return this.size == other.size
                && this.cachePath == other.cachePath
                && this.persistSessionCookies == other.persistSessionCookies
                && this.acceptLanguageList == other.acceptLanguageList
                && this.cookieableSchemesList == other.cookieableSchemesList
                && this.cookieableSchemesExcludeDefaults == other.cookieableSchemesExcludeDefaults;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(
                size,
                cachePath,
                persistSessionCookies,
                acceptLanguageList,
                cookieableSchemesList,
                cookieableSchemesExcludeDefaults);
    }

    @Override
    public String toString() {
        return "CefRequestContextSettings{" + "size=" + size + ", " + "cachePath=" + cachePath + ", "
                + "persistSessionCookies=" + persistSessionCookies + ", " + "acceptLanguageList=" + acceptLanguageList
                + ", " + "cookieableSchemesList=" + cookieableSchemesList + ", " + "cookieableSchemesExcludeDefaults="
                + cookieableSchemesExcludeDefaults + "}";
    }
}
