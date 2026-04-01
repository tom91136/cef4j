// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Request context initialization settings. Specify {@code null} or 0 to get the recommended default values.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef struct _cef_request_context_settings_t {
 *   size_t size;
 *   cef_string_t* cache_path;
 *   int persist_session_cookies;
 *   cef_string_t* accept_language_list;
 *   cef_string_t* cookieable_schemes_list;
 *   int cookieable_schemes_exclude_defaults;
 * } cef_request_context_settings_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h:514</a>
 */
public final class CefRequestContextSettings {

    // Native struct size — set by JNI, not user-modifiable.
    @SuppressWarnings("FieldMayBeFinal")
    private volatile long size = -1;

    public final String cachePath;
    public final int persistSessionCookies;
    public final String acceptLanguageList;
    public final String cookieableSchemesList;
    public final int cookieableSchemesExcludeDefaults;

    public CefRequestContextSettings(
            String cachePath,
            int persistSessionCookies,
            String acceptLanguageList,
            String cookieableSchemesList,
            int cookieableSchemesExcludeDefaults) {
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
        return java.util.Objects.equals(this.cachePath, other.cachePath)
                && this.persistSessionCookies == other.persistSessionCookies
                && java.util.Objects.equals(this.acceptLanguageList, other.acceptLanguageList)
                && java.util.Objects.equals(this.cookieableSchemesList, other.cookieableSchemesList)
                && this.cookieableSchemesExcludeDefaults == other.cookieableSchemesExcludeDefaults;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(
                cachePath,
                persistSessionCookies,
                acceptLanguageList,
                cookieableSchemesList,
                cookieableSchemesExcludeDefaults);
    }

    @Override
    public String toString() {
        return "CefRequestContextSettings{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", "
                + "cachePath=" + cachePath + ", " + "persistSessionCookies=" + persistSessionCookies + ", "
                + "acceptLanguageList=" + acceptLanguageList + ", " + "cookieableSchemesList=" + cookieableSchemesList
                + ", " + "cookieableSchemesExcludeDefaults=" + cookieableSchemesExcludeDefaults + "}";
    }
}
