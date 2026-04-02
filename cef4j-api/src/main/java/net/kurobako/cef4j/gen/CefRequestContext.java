// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * A request context provides request handling for a set of related browser or URL request objects. A request context
 * can be specified when creating a new browser via the CefBrowserHost static factory methods or when creating a new URL
 * request via the CefURLRequest static factory methods. Browser objects with different request contexts will never be
 * hosted in the same render process. Browser objects with the same request context may or may not be hosted in the same
 * render process depending on the process model. Browser objects created indirectly via the JavaScript window.open
 * function or targeted links will share the same render process and the same request context as the source browser.
 * When running in single-process mode there is only a single render process (the main process) and so all browsers
 * created in single-process mode will share the same request context. This will be the first request context passed
 * into a CefBrowserHost static factory method and all other request context objects will be ignored.
 *
 * <p>Definition generated from cef_request_context_capi.h
 *
 * <pre>typedef struct _cef_request_context_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_request_context_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__context_8h.html">cef_request_context.h:91</a>
 */
public interface CefRequestContext extends CefLibraryObject {

    /**
     * Returns {@code true} if this object is pointing to the same context as {@code that} object.
     *
     * <p>Definition generated from cef_request_context_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_same)(struct _cef_request_context_t* self, struct _cef_request_context_t* other);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__context_8h.html">cef_request_context.h:134</a>
     */
    boolean isSame(@Nullable CefRequestContext other);

    /**
     * Returns {@code true} if this object is sharing the same storage as {@code that} object.
     *
     * <p>Definition generated from cef_request_context_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* is_sharing_with)(struct _cef_request_context_t* self, struct _cef_request_context_t* other);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__context_8h.html">cef_request_context.h:141</a>
     */
    boolean isSharingWith(@Nullable CefRequestContext other);

    /**
     * Returns {@code true} if this object is the global context. The global context is used by default when creating a
     * browser or URL request with a {@code null} context argument.
     *
     * <p>Definition generated from cef_request_context_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_global)(struct _cef_request_context_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__context_8h.html">cef_request_context.h:147</a>
     */
    boolean isGlobal();

    /**
     * Returns the handler for this context if any.
     *
     * <p>Definition generated from cef_request_context_capi.h
     *
     * <pre>cef_request_context_handler_t* (CEF_CALLBACK* get_handler)(struct _cef_request_context_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__context_8h.html">cef_request_context.h:155</a>
     */
    Optional<CefRequestContextHandler> getHandler();

    /**
     * Returns the cache path for this object. If empty an "incognito mode" in-memory cache is being used.
     *
     * <p>Definition generated from cef_request_context_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_cache_path)(struct _cef_request_context_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__context_8h.html">cef_request_context.h:161</a>
     */
    Optional<String> getCachePath();

    /**
     * Returns the cookie manager for this object. If {@code callback} is non-{@code null} it will be executed
     * asnychronously on the UI thread after the manager's storage has been initialized.
     *
     * <p>Definition generated from cef_request_context_capi.h
     *
     * <pre>
     * cef_cookie_manager_t* (CEF_CALLBACK* get_cookie_manager)(struct _cef_request_context_t* self, struct _cef_completion_callback_t* callback);
     * </pre>
     *
     * @param callback may be null
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__context_8h.html">cef_request_context.h:168</a>
     */
    Optional<CefCookieManager> getCookieManager(@Nullable CefCompletionCallback callback);

    /**
     * Register a scheme handler factory for the specified {@code scheme_name} and optional {@code domain_name}. An
     * empty {@code domain_name} value for a standard scheme will cause the factory to match all domain names. The
     * {@code domain_name} value will be ignored for non-standard schemes. If {@code scheme_name} is a built-in scheme
     * and no handler is returned by {@code factory} then the built-in scheme handler factory will be called. If
     * {@code scheme_name} is a custom scheme then you must also implement the
     * {@link CefApp#onRegisterCustomSchemes(CefSchemeRegistrar)} method in all processes. This function may be called
     * multiple times to change or remove the factory that matches the specified {@code scheme_name} and optional
     * {@code domain_name}. Returns {@code false} if an error occurs. This function may be called on any thread in the
     * browser process.
     *
     * <p>Definition generated from cef_request_context_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* register_scheme_handler_factory)(struct _cef_request_context_t* self, const cef_string_t* scheme_name, const cef_string_t* domain_name, struct _cef_scheme_handler_factory_t* factory);
     * </pre>
     *
     * @param domainName may be null
     * @param factory may be null
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__context_8h.html">cef_request_context.h:177</a>
     */
    boolean registerSchemeHandlerFactory(
            @Nullable String schemeName, @Nullable String domainName, @Nullable CefSchemeHandlerFactory factory);

    /**
     * Clear all registered scheme handler factories. Returns {@code false} on error. This function may be called on any
     * thread in the browser process.
     *
     * <p>Definition generated from cef_request_context_capi.h
     *
     * <pre>int (CEF_CALLBACK* clear_scheme_handler_factories)(struct _cef_request_context_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__context_8h.html">cef_request_context.h:196</a>
     */
    boolean clearSchemeHandlerFactories();

    /**
     * Clears all certificate exceptions that were added as part of handling
     * {@link CefRequestHandler#onCertificateError(CefBrowser, CefErrorCode, String, CefSslInfo, CefCallback)}. If you
     * call this it is recommended that you also call CloseAllConnections() or you risk not being prompted again for
     * server certificates if you reconnect quickly. If {@code callback} is non-{@code null} it will be executed on the
     * UI thread after completion.
     *
     * <p>Definition generated from cef_request_context_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* clear_certificate_exceptions)(struct _cef_request_context_t* self, struct _cef_completion_callback_t* callback);
     * </pre>
     *
     * @param callback may be null
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__context_8h.html">cef_request_context.h:203</a>
     */
    void clearCertificateExceptions(@Nullable CefCompletionCallback callback);

    /**
     * Clears all HTTP authentication credentials that were added as part of handling GetAuthCredentials. If
     * {@code callback} is non-{@code null} it will be executed on the UI thread after completion.
     *
     * <p>Definition generated from cef_request_context_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* clear_http_auth_credentials)(struct _cef_request_context_t* self, struct _cef_completion_callback_t* callback);
     * </pre>
     *
     * @param callback may be null
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__context_8h.html">cef_request_context.h:224</a>
     */
    void clearHttpAuthCredentials(@Nullable CefCompletionCallback callback);

    /**
     * Clears all active and idle connections that Chromium currently has. This is only recommended if you have released
     * all other CEF objects but don't yet want to call CefShutdown(). If {@code callback} is non-{@code null} it will
     * be executed on the UI thread after completion.
     *
     * <p>Definition generated from cef_request_context_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* close_all_connections)(struct _cef_request_context_t* self, struct _cef_completion_callback_t* callback);
     * </pre>
     *
     * @param callback may be null
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__context_8h.html">cef_request_context.h:233</a>
     */
    void closeAllConnections(@Nullable CefCompletionCallback callback);

    /**
     * Attempts to resolve {@code origin} to a list of associated IP addresses. {@code callback} will be executed on the
     * UI thread after completion.
     *
     * <p>Definition generated from cef_request_context_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* resolve_host)(struct _cef_request_context_t* self, const cef_string_t* origin, struct _cef_resolve_callback_t* callback);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__context_8h.html">cef_request_context.h:243</a>
     */
    void resolveHost(@Nullable String origin, @Nullable CefResolveCallback callback);

    /**
     * Returns the MediaRouter object associated with this context. If {@code callback} is non-{@code null} it will be
     * executed asnychronously on the UI thread after the manager's context has been initialized.
     *
     * <p>Definition generated from cef_request_context_capi.h
     *
     * <pre>
     * cef_media_router_t* (CEF_CALLBACK* get_media_router)(struct _cef_request_context_t* self, struct _cef_completion_callback_t* callback);
     * </pre>
     *
     * @param callback may be null
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__context_8h.html">cef_request_context.h:251</a>
     */
    Optional<CefMediaRouter> getMediaRouter(@Nullable CefCompletionCallback callback);

    /**
     * Returns the current value for {@code content_type} that applies for the specified URLs. If both URLs are empty
     * the default value will be returned. Returns {@code null} if no value is configured. Must be called on the browser
     * process UI thread.
     *
     * <p>Definition generated from cef_request_context_capi.h
     *
     * <pre>
     * cef_value_t* (CEF_CALLBACK* get_website_setting)(struct _cef_request_context_t* self, const cef_string_t* requesting_url, const cef_string_t* top_level_url, cef_content_setting_types_t content_type);
     * </pre>
     *
     * @param requestingUrl may be null
     * @param topLevelUrl may be null
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__context_8h.html">cef_request_context.h:260</a>
     */
    Optional<CefValue> getWebsiteSetting(
            @Nullable String requestingUrl, @Nullable String topLevelUrl, @Nonnull CefContentSettingTypes contentType);

    /**
     * Sets the current value for {@code content_type} for the specified URLs in the default scope. If both URLs are
     * empty, and the context is not incognito, the default value will be set. Pass {@code null} for {@code value} to
     * remove the default value for this content type.
     *
     * <p><b>WARNING:</b> Incorrect usage of this method may cause instability or security issues in Chromium. Make sure
     * that you first understand the potential impact of any changes to {@code content_type} by reviewing the related
     * source code in Chromium. For example, if you plan to modify {@link CefContentSettingTypes.Kind#POPUPS}, first
     * review and understand the usage of ContentSettingsType::POPUPS in Chromium: <a
     * href="https://source.chromium.org/search?q=ContentSettingsType::POPUPS">https://source.chromium.org/search?q=ContentSettingsType::POPUPS</a>
     *
     * <p>Definition generated from cef_request_context_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* set_website_setting)(struct _cef_request_context_t* self, const cef_string_t* requesting_url, const cef_string_t* top_level_url, cef_content_setting_types_t content_type, struct _cef_value_t* value);
     * </pre>
     *
     * @param requestingUrl may be null
     * @param topLevelUrl may be null
     * @param value may be null
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__context_8h.html">cef_request_context.h:272</a>
     */
    void setWebsiteSetting(
            @Nullable String requestingUrl,
            @Nullable String topLevelUrl,
            @Nonnull CefContentSettingTypes contentType,
            @Nullable CefValue value);

    /**
     * Returns the current value for {@code content_type} that applies for the specified URLs. If both URLs are empty
     * the default value will be returned. Returns {@link CefContentSettingValues.Kind#DEFAULT} if no value is
     * configured. Must be called on the browser process UI thread.
     *
     * <p>Definition generated from cef_request_context_capi.h
     *
     * <pre>
     * cef_content_setting_values_t (CEF_CALLBACK* get_content_setting)(struct _cef_request_context_t* self, const cef_string_t* requesting_url, const cef_string_t* top_level_url, cef_content_setting_types_t content_type);
     * </pre>
     *
     * @param requestingUrl may be null
     * @param topLevelUrl may be null
     * @return the result, or {@code CEF_CONTENT_SETTING_VALUE_DEFAULT} for default handling
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__context_8h.html">cef_request_context.h:293</a>
     */
    CefContentSettingValues getContentSetting(
            @Nullable String requestingUrl, @Nullable String topLevelUrl, @Nonnull CefContentSettingTypes contentType);

    /**
     * Sets the current value for {@code content_type} for the specified URLs in the default scope. If both URLs are
     * empty, and the context is not incognito, the default value will be set. Pass
     * {@link CefContentSettingValues.Kind#DEFAULT} for {@code value} to use the default value for this content type.
     *
     * <p><b>WARNING:</b> Incorrect usage of this method may cause instability or security issues in Chromium. Make sure
     * that you first understand the potential impact of any changes to {@code content_type} by reviewing the related
     * source code in Chromium. For example, if you plan to modify {@link CefContentSettingTypes.Kind#POPUPS}, first
     * review and understand the usage of ContentSettingsType::POPUPS in Chromium: <a
     * href="https://source.chromium.org/search?q=ContentSettingsType::POPUPS">https://source.chromium.org/search?q=ContentSettingsType::POPUPS</a>
     *
     * <p>Definition generated from cef_request_context_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* set_content_setting)(struct _cef_request_context_t* self, const cef_string_t* requesting_url, const cef_string_t* top_level_url, cef_content_setting_types_t content_type, cef_content_setting_values_t value);
     * </pre>
     *
     * @param requestingUrl may be null
     * @param topLevelUrl may be null
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__context_8h.html">cef_request_context.h:306</a>
     */
    void setContentSetting(
            @Nullable String requestingUrl,
            @Nullable String topLevelUrl,
            @Nonnull CefContentSettingTypes contentType,
            @Nonnull CefContentSettingValues value);

    /**
     * Sets the Chrome color scheme for all browsers that share this request context. {@code variant} values of SYSTEM,
     * LIGHT and DARK change the underlying color mode (e.g. light vs dark). Other {@code variant} values determine how
     * {@code user_color} will be applied in the current color mode. If {@code user_color} is transparent (0) the
     * default color will be used.
     *
     * <p>Definition generated from cef_request_context_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* set_chrome_color_scheme)(struct _cef_request_context_t* self, cef_color_variant_t variant, cef_color_t user_color);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__context_8h.html">cef_request_context.h:337</a>
     */
    void setChromeColorScheme(@Nonnull CefColorVariant variant, int userColor);

    /**
     * Returns the current Chrome color scheme mode (SYSTEM, LIGHT or DARK). Must be called on the browser process UI
     * thread.
     *
     * <p>Definition generated from cef_request_context_capi.h
     *
     * <pre>cef_color_variant_t (CEF_CALLBACK* get_chrome_color_scheme_mode)(struct _cef_request_context_t* self);</pre>
     *
     * @return the result, or {@code CEF_COLOR_VARIANT_SYSTEM} for default handling
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__context_8h.html">cef_request_context.h:348</a>
     */
    CefColorVariant getChromeColorSchemeMode();

    /**
     * Returns the current Chrome color scheme color, or transparent (0) for the default color. Must be called on the
     * browser process UI thread.
     *
     * <p>Definition generated from cef_request_context_capi.h
     *
     * <pre>unsigned int (CEF_CALLBACK* get_chrome_color_scheme_color)(struct _cef_request_context_t* self);</pre>
     *
     * @return the result, or {@code 0} for default handling
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__context_8h.html">cef_request_context.h:355</a>
     */
    int getChromeColorSchemeColor();

    /**
     * Returns the current Chrome color scheme variant. Must be called on the browser process UI thread.
     *
     * <p>Definition generated from cef_request_context_capi.h
     *
     * <pre>cef_color_variant_t (CEF_CALLBACK* get_chrome_color_scheme_variant)(struct _cef_request_context_t* self);
     * </pre>
     *
     * @return the result, or {@code CEF_COLOR_VARIANT_SYSTEM} for default handling
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__context_8h.html">cef_request_context.h:362</a>
     */
    CefColorVariant getChromeColorSchemeVariant();

    /**
     * Add an observer for content and website setting changes. The observer will remain registered until the returned
     * Registration object is destroyed. This method must be called on the browser process UI thread.
     *
     * <p>Added in CEF API version 13401.
     *
     * <p>Definition generated from cef_request_context_capi.h
     *
     * <pre>
     * cef_registration_t* (CEF_CALLBACK* add_setting_observer)(struct _cef_request_context_t* self, struct _cef_setting_observer_t* observer);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__context_8h.html">cef_request_context.h:327</a>
     */
    Optional<CefRegistration> addSettingObserver(@Nullable CefSettingObserver observer);

    /**
     * Clears the HTTP cache. If {@code callback} is non-{@code null} it will be executed on the UI thread after
     * completion.
     *
     * <p>Added in CEF API version 14400.
     *
     * <p>Definition generated from cef_request_context_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* clear_http_cache)(struct _cef_request_context_t* self, struct _cef_completion_callback_t* callback);
     * </pre>
     *
     * @param callback may be null
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__context_8h.html">cef_request_context.h:216</a>
     */
    void clearHttpCache(@Nullable CefCompletionCallback callback);
    /**
     * Returns the global context object.
     *
     * <p>Definition generated from cef_request_context_capi.h
     *
     * <pre>CEF_EXPORT cef_request_context_t* cef_request_context_get_global_context(void);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__context_8h.html">cef_request_context.h:110</a>
     */
    static Optional<CefRequestContext> getGlobalContext() {
        return Optional.ofNullable(NativePeer.N_GetGlobalContext());
    }

    /**
     * Creates a new context object that shares storage with {@code other} and uses an optional {@code handler}.
     *
     * <p>Definition generated from cef_request_context_capi.h
     *
     * <pre>
     * CEF_EXPORT cef_request_context_t* cef_request_context_create_context(const struct _cef_request_context_settings_t* settings, struct _cef_request_context_handler_t* handler);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__context_8h.html">cef_request_context.h:125</a>
     */
    static Optional<CefRequestContext> createContext(
            @Nonnull CefRequestContextSettings settings, @Nullable CefRequestContextHandler handler) {
        return Optional.ofNullable(NativePeer.N_CreateContext(settings, handler));
    }

    static Optional<CefRequestContext> cefCreateContextShared(
            @Nullable CefRequestContext other, @Nullable CefRequestContextHandler handler) {
        return Optional.ofNullable(NativePeer.N_CefCreateContextShared(other, handler));
    }

    final class NativePeer implements CefRequestContext, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefRequestContext has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefRequestContext.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefRequestContext 0x{}", Long.toHexString(ptr));
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

        @Override
        public boolean isSame(@Nullable CefRequestContext other) {
            checkNotClosed();
            CefLibraryObject.requireOpen(other, "CefRequestContext");
            return N_IsSame(nativePtr, other);
        }

        @Override
        public boolean isSharingWith(@Nullable CefRequestContext other) {
            checkNotClosed();
            CefLibraryObject.requireOpen(other, "CefRequestContext");
            return N_IsSharingWith(nativePtr, other);
        }

        @Override
        public boolean isGlobal() {
            checkNotClosed();
            return N_IsGlobal(nativePtr);
        }

        @Override
        public Optional<CefRequestContextHandler> getHandler() {
            checkNotClosed();
            return Optional.ofNullable(N_GetHandler(nativePtr));
        }

        @Override
        public Optional<String> getCachePath() {
            checkNotClosed();
            return Optional.ofNullable(N_GetCachePath(nativePtr));
        }

        @Override
        public Optional<CefCookieManager> getCookieManager(@Nullable CefCompletionCallback callback) {
            checkNotClosed();
            return Optional.ofNullable(N_GetCookieManager(nativePtr, callback));
        }

        @Override
        public boolean registerSchemeHandlerFactory(
                @Nullable String schemeName, @Nullable String domainName, @Nullable CefSchemeHandlerFactory factory) {
            checkNotClosed();
            return N_RegisterSchemeHandlerFactory(nativePtr, schemeName, domainName, factory);
        }

        @Override
        public boolean clearSchemeHandlerFactories() {
            checkNotClosed();
            return N_ClearSchemeHandlerFactories(nativePtr);
        }

        @Override
        public void clearCertificateExceptions(@Nullable CefCompletionCallback callback) {
            checkNotClosed();
            N_ClearCertificateExceptions(nativePtr, callback);
        }

        @Override
        public void clearHttpAuthCredentials(@Nullable CefCompletionCallback callback) {
            checkNotClosed();
            N_ClearHttpAuthCredentials(nativePtr, callback);
        }

        @Override
        public void closeAllConnections(@Nullable CefCompletionCallback callback) {
            checkNotClosed();
            N_CloseAllConnections(nativePtr, callback);
        }

        @Override
        public void resolveHost(@Nullable String origin, @Nullable CefResolveCallback callback) {
            checkNotClosed();
            N_ResolveHost(nativePtr, origin, callback);
        }

        @Override
        public Optional<CefMediaRouter> getMediaRouter(@Nullable CefCompletionCallback callback) {
            checkNotClosed();
            return Optional.ofNullable(N_GetMediaRouter(nativePtr, callback));
        }

        @Override
        public Optional<CefValue> getWebsiteSetting(
                @Nullable String requestingUrl,
                @Nullable String topLevelUrl,
                @Nonnull CefContentSettingTypes contentType) {
            checkNotClosed();
            return Optional.ofNullable(N_GetWebsiteSetting(nativePtr, requestingUrl, topLevelUrl, contentType));
        }

        @Override
        public void setWebsiteSetting(
                @Nullable String requestingUrl,
                @Nullable String topLevelUrl,
                @Nonnull CefContentSettingTypes contentType,
                @Nullable CefValue value) {
            checkNotClosed();
            CefLibraryObject.requireOpen(value, "CefValue");
            N_SetWebsiteSetting(nativePtr, requestingUrl, topLevelUrl, contentType, value);
        }

        @Override
        public CefContentSettingValues getContentSetting(
                @Nullable String requestingUrl,
                @Nullable String topLevelUrl,
                @Nonnull CefContentSettingTypes contentType) {
            checkNotClosed();
            return N_GetContentSetting(nativePtr, requestingUrl, topLevelUrl, contentType);
        }

        @Override
        public void setContentSetting(
                @Nullable String requestingUrl,
                @Nullable String topLevelUrl,
                @Nonnull CefContentSettingTypes contentType,
                @Nonnull CefContentSettingValues value) {
            checkNotClosed();
            N_SetContentSetting(nativePtr, requestingUrl, topLevelUrl, contentType, value);
        }

        @Override
        public void setChromeColorScheme(@Nonnull CefColorVariant variant, int userColor) {
            checkNotClosed();
            N_SetChromeColorScheme(nativePtr, variant, userColor);
        }

        @Override
        public CefColorVariant getChromeColorSchemeMode() {
            checkNotClosed();
            return N_GetChromeColorSchemeMode(nativePtr);
        }

        @Override
        public int getChromeColorSchemeColor() {
            checkNotClosed();
            return N_GetChromeColorSchemeColor(nativePtr);
        }

        @Override
        public CefColorVariant getChromeColorSchemeVariant() {
            checkNotClosed();
            return N_GetChromeColorSchemeVariant(nativePtr);
        }

        @Override
        public Optional<CefRegistration> addSettingObserver(@Nullable CefSettingObserver observer) {
            checkNotClosed();
            return Optional.ofNullable(N_AddSettingObserver(nativePtr, observer));
        }

        @Override
        public void clearHttpCache(@Nullable CefCompletionCallback callback) {
            checkNotClosed();
            N_ClearHttpCache(nativePtr, callback);
        }

        private static native boolean N_IsSame(long self, CefRequestContext other);

        private static native boolean N_IsSharingWith(long self, CefRequestContext other);

        private static native boolean N_IsGlobal(long self);

        private static native CefRequestContextHandler N_GetHandler(long self);

        private static native String N_GetCachePath(long self);

        private static native CefCookieManager N_GetCookieManager(long self, CefCompletionCallback callback);

        private static native boolean N_RegisterSchemeHandlerFactory(
                long self, String schemeName, String domainName, CefSchemeHandlerFactory factory);

        private static native boolean N_ClearSchemeHandlerFactories(long self);

        private static native void N_ClearCertificateExceptions(long self, CefCompletionCallback callback);

        private static native void N_ClearHttpAuthCredentials(long self, CefCompletionCallback callback);

        private static native void N_CloseAllConnections(long self, CefCompletionCallback callback);

        private static native void N_ResolveHost(long self, String origin, CefResolveCallback callback);

        private static native CefMediaRouter N_GetMediaRouter(long self, CefCompletionCallback callback);

        private static native CefValue N_GetWebsiteSetting(
                long self, String requestingUrl, String topLevelUrl, CefContentSettingTypes contentType);

        private static native void N_SetWebsiteSetting(
                long self,
                String requestingUrl,
                String topLevelUrl,
                CefContentSettingTypes contentType,
                CefValue value);

        private static native CefContentSettingValues N_GetContentSetting(
                long self, String requestingUrl, String topLevelUrl, CefContentSettingTypes contentType);

        private static native void N_SetContentSetting(
                long self,
                String requestingUrl,
                String topLevelUrl,
                CefContentSettingTypes contentType,
                CefContentSettingValues value);

        private static native void N_SetChromeColorScheme(long self, CefColorVariant variant, int userColor);

        private static native CefColorVariant N_GetChromeColorSchemeMode(long self);

        private static native int N_GetChromeColorSchemeColor(long self);

        private static native CefColorVariant N_GetChromeColorSchemeVariant(long self);

        private static native CefRegistration N_AddSettingObserver(long self, CefSettingObserver observer);

        private static native void N_ClearHttpCache(long self, CefCompletionCallback callback);

        static native CefRequestContext N_GetGlobalContext();

        static native CefRequestContext N_CreateContext(
                CefRequestContextSettings settings, CefRequestContextHandler handler);

        static native CefRequestContext N_CefCreateContextShared(
                CefRequestContext other, CefRequestContextHandler handler);

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
            return "CefRequestContext{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
