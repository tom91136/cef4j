// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Request context initialization settings. Specify {@code null} or 0 to get the recommended default values.
 * <p>Definition generated from internal/cef_types.h
 * <pre>typedef struct _cef_request_context_settings_t {
 *   size_t size;
 *   cef_string_t* cache_path;
 *   int persist_session_cookies;
 *   cef_string_t* accept_language_list;
 *   cef_string_t* cookieable_schemes_list;
 *   int cookieable_schemes_exclude_defaults;
 * } cef_request_context_settings_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">internal/cef_types.h:514</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public final class CefRequestContextSettings {

    // Native struct size, populated by the JNI layer with sizeof(struct) as required by CEF. Not user-modifiable.
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    private volatile long size = -1;

        /**
         * The directory where cache data for this request context will be stored on disk. If this value is non-empty then it must be an absolute path that is either equal to or a child directory of CefSettings.root_cache_path. If this value is empty then browsers will be created in "incognito mode" where in-memory caches are used for storage and no profile-specific data is persisted to disk (installation-specific data will still be persisted in root_cache_path). HTML5 databases such as localStorage will only persist across sessions if a cache path is specified. To share the global browser cache and related configuration set this value to match the CefSettings.cache_path value. 
         */    public final String cachePath;
        /**
         * To persist session cookies (cookies without an expiry date or validity interval) by default when using the global cookie manager set this value to {@code true} (1). Session cookies are generally intended to be transient and most Web browsers do not persist them. Can be set globally using the CefSettings.persist_session_cookies value. This value will be ignored if {@code cache_path} is empty or if it matches the CefSettings.cache_path value. 
         */    public final int persistSessionCookies;
        /**
         * Comma delimited ordered list of language codes without any whitespace that will be used in the "Accept-Language" HTTP request header and "navigator.language" JS attribute. Can be set globally using the CefSettings.accept_language_list value. If all values are empty then "en-US,en" will be used. This value will be ignored if {@code cache_path} matches the CefSettings.cache_path value. 
         */    public final String acceptLanguageList;
        /**
         * Comma delimited list of schemes supported by the associated CefCookieManager. If {@code cookieable_schemes_exclude_defaults} is {@code false} (0) the default schemes ("http", "https", "ws" and "wss") will also be supported. Not specifying a {@code cookieable_schemes_list} value and setting {@code cookieable_schemes_exclude_defaults} to {@code true} (1) will disable all loading and saving of cookies. These values will be ignored if {@code cache_path} matches the CefSettings.cache_path value. 
         */    public final String cookieableSchemesList;
    public final int cookieableSchemesExcludeDefaults;

    public CefRequestContextSettings(String cachePath, int persistSessionCookies, String acceptLanguageList, String cookieableSchemesList, int cookieableSchemesExcludeDefaults) {
        this.cachePath = cachePath;
        this.persistSessionCookies = persistSessionCookies;
        this.acceptLanguageList = acceptLanguageList;
        this.cookieableSchemesList = cookieableSchemesList;
        this.cookieableSchemesExcludeDefaults = cookieableSchemesExcludeDefaults;
    }

    /** Create a mutable copy of this instance. */
    public Mutable toMutable() {
        return new Mutable(this.cachePath, this.persistSessionCookies, this.acceptLanguageList, this.cookieableSchemesList, this.cookieableSchemesExcludeDefaults);
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
        return java.util.Objects.hash(cachePath, persistSessionCookies, acceptLanguageList, cookieableSchemesList, cookieableSchemesExcludeDefaults);
    }

    @Override
    public String toString() {
        return "CefRequestContextSettings{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", " + "cachePath=" + cachePath + ", " + "persistSessionCookies=" + persistSessionCookies + ", " + "acceptLanguageList=" + acceptLanguageList + ", " + "cookieableSchemesList=" + cookieableSchemesList + ", " + "cookieableSchemesExcludeDefaults=" + cookieableSchemesExcludeDefaults + "}";
    }

    /**
     * Mutable variant of {@link CefRequestContextSettings}. Request context initialization settings. Specify {@code null} or 0 to get the recommended default values.
     * <p>Definition generated from internal/cef_types.h
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">internal/cef_types.h:514</a>
     */
    public static final class Mutable {

    // Native struct size, populated by the JNI layer with sizeof(struct) as required by CEF. Not user-modifiable.
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    private volatile long size = -1;

            /**
             * The directory where cache data for this request context will be stored on disk. If this value is non-empty then it must be an absolute path that is either equal to or a child directory of CefSettings.root_cache_path. If this value is empty then browsers will be created in "incognito mode" where in-memory caches are used for storage and no profile-specific data is persisted to disk (installation-specific data will still be persisted in root_cache_path). HTML5 databases such as localStorage will only persist across sessions if a cache path is specified. To share the global browser cache and related configuration set this value to match the CefSettings.cache_path value. 
             */        public String cachePath;
            /**
             * To persist session cookies (cookies without an expiry date or validity interval) by default when using the global cookie manager set this value to {@code true} (1). Session cookies are generally intended to be transient and most Web browsers do not persist them. Can be set globally using the CefSettings.persist_session_cookies value. This value will be ignored if {@code cache_path} is empty or if it matches the CefSettings.cache_path value. 
             */        public int persistSessionCookies;
            /**
             * Comma delimited ordered list of language codes without any whitespace that will be used in the "Accept-Language" HTTP request header and "navigator.language" JS attribute. Can be set globally using the CefSettings.accept_language_list value. If all values are empty then "en-US,en" will be used. This value will be ignored if {@code cache_path} matches the CefSettings.cache_path value. 
             */        public String acceptLanguageList;
            /**
             * Comma delimited list of schemes supported by the associated CefCookieManager. If {@code cookieable_schemes_exclude_defaults} is {@code false} (0) the default schemes ("http", "https", "ws" and "wss") will also be supported. Not specifying a {@code cookieable_schemes_list} value and setting {@code cookieable_schemes_exclude_defaults} to {@code true} (1) will disable all loading and saving of cookies. These values will be ignored if {@code cache_path} matches the CefSettings.cache_path value. 
             */        public String cookieableSchemesList;
        public int cookieableSchemesExcludeDefaults;

        public Mutable() {}

        public Mutable(String cachePath, int persistSessionCookies, String acceptLanguageList, String cookieableSchemesList, int cookieableSchemesExcludeDefaults) {
            this.cachePath = cachePath;
            this.persistSessionCookies = persistSessionCookies;
            this.acceptLanguageList = acceptLanguageList;
            this.cookieableSchemesList = cookieableSchemesList;
            this.cookieableSchemesExcludeDefaults = cookieableSchemesExcludeDefaults;
        }

        /** Create an immutable snapshot of this instance. */
        public CefRequestContextSettings toImmutable() {
            return new CefRequestContextSettings(this.cachePath, this.persistSessionCookies, this.acceptLanguageList, this.cookieableSchemesList, this.cookieableSchemesExcludeDefaults);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Mutable)) return false;
            Mutable other = (Mutable) obj;
            return java.util.Objects.equals(this.cachePath, other.cachePath)
                        && this.persistSessionCookies == other.persistSessionCookies
                        && java.util.Objects.equals(this.acceptLanguageList, other.acceptLanguageList)
                        && java.util.Objects.equals(this.cookieableSchemesList, other.cookieableSchemesList)
                        && this.cookieableSchemesExcludeDefaults == other.cookieableSchemesExcludeDefaults;
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(cachePath, persistSessionCookies, acceptLanguageList, cookieableSchemesList, cookieableSchemesExcludeDefaults);
        }

        @Override
        public String toString() {
            return "CefRequestContextSettings.Mutable{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", " + "cachePath=" + cachePath + ", " + "persistSessionCookies=" + persistSessionCookies + ", " + "acceptLanguageList=" + acceptLanguageList + ", " + "cookieableSchemesList=" + cookieableSchemesList + ", " + "cookieableSchemesExcludeDefaults=" + cookieableSchemesExcludeDefaults + "}";
        }
    }
}
