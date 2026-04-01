// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Initialization settings. Specify {@code null} or 0 to get the recommended default values. Many of these and other
 * settings can also configured using command-line switches.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef struct _cef_settings_t {
 *   size_t size;
 *   int no_sandbox;
 *   cef_string_t* browser_subprocess_path;
 *   cef_string_t* framework_dir_path;
 *   cef_string_t* main_bundle_path;
 *   int multi_threaded_message_loop;
 *   int external_message_pump;
 *   int windowless_rendering_enabled;
 *   int command_line_args_disabled;
 *   cef_string_t* cache_path;
 *   cef_string_t* root_cache_path;
 *   int persist_session_cookies;
 *   cef_string_t* user_agent;
 *   cef_string_t* user_agent_product;
 *   cef_string_t* locale;
 *   cef_string_t* log_file;
 *   cef_log_severity_t log_severity;
 *   cef_log_items_t log_items;
 *   cef_string_t* javascript_flags;
 *   cef_string_t* resources_dir_path;
 *   cef_string_t* locales_dir_path;
 *   int remote_debugging_port;
 *   int uncaught_exception_stack_size;
 *   unsigned int background_color;
 *   cef_string_t* accept_language_list;
 *   cef_string_t* cookieable_schemes_list;
 *   int cookieable_schemes_exclude_defaults;
 *   cef_string_t* chrome_policy_id;
 *   int chrome_app_icon_id;
 *   int disable_signal_handlers;
 *   int use_views_default_popup;
 * } cef_settings_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h:199</a>
 */
public final class CefSettings {

    // Native struct size — set by JNI, not user-modifiable.
    @SuppressWarnings("FieldMayBeFinal")
    private volatile long size = -1;

    public final int noSandbox;
    public final String browserSubprocessPath;
    public final String frameworkDirPath;
    public final String mainBundlePath;
    public final int multiThreadedMessageLoop;
    public final int externalMessagePump;
    public final int windowlessRenderingEnabled;
    public final int commandLineArgsDisabled;
    public final String cachePath;
    public final String rootCachePath;
    public final int persistSessionCookies;
    public final String userAgent;
    public final String userAgentProduct;
    public final String locale;
    public final String logFile;
    public final CefLogSeverity logSeverity;
    public final CefLogItems logItems;
    public final String javascriptFlags;
    public final String resourcesDirPath;
    public final String localesDirPath;
    public final int remoteDebuggingPort;
    public final int uncaughtExceptionStackSize;
    public final int backgroundColor;
    public final String acceptLanguageList;
    public final String cookieableSchemesList;
    public final int cookieableSchemesExcludeDefaults;
    public final String chromePolicyId;
    public final int chromeAppIconId;
    public final int disableSignalHandlers;
    public final int useViewsDefaultPopup;

    public CefSettings(
            int noSandbox,
            String browserSubprocessPath,
            String frameworkDirPath,
            String mainBundlePath,
            int multiThreadedMessageLoop,
            int externalMessagePump,
            int windowlessRenderingEnabled,
            int commandLineArgsDisabled,
            String cachePath,
            String rootCachePath,
            int persistSessionCookies,
            String userAgent,
            String userAgentProduct,
            String locale,
            String logFile,
            CefLogSeverity logSeverity,
            CefLogItems logItems,
            String javascriptFlags,
            String resourcesDirPath,
            String localesDirPath,
            int remoteDebuggingPort,
            int uncaughtExceptionStackSize,
            int backgroundColor,
            String acceptLanguageList,
            String cookieableSchemesList,
            int cookieableSchemesExcludeDefaults,
            String chromePolicyId,
            int chromeAppIconId,
            int disableSignalHandlers,
            int useViewsDefaultPopup) {
        this.noSandbox = noSandbox;
        this.browserSubprocessPath = browserSubprocessPath;
        this.frameworkDirPath = frameworkDirPath;
        this.mainBundlePath = mainBundlePath;
        this.multiThreadedMessageLoop = multiThreadedMessageLoop;
        this.externalMessagePump = externalMessagePump;
        this.windowlessRenderingEnabled = windowlessRenderingEnabled;
        this.commandLineArgsDisabled = commandLineArgsDisabled;
        this.cachePath = cachePath;
        this.rootCachePath = rootCachePath;
        this.persistSessionCookies = persistSessionCookies;
        this.userAgent = userAgent;
        this.userAgentProduct = userAgentProduct;
        this.locale = locale;
        this.logFile = logFile;
        this.logSeverity = logSeverity;
        this.logItems = logItems;
        this.javascriptFlags = javascriptFlags;
        this.resourcesDirPath = resourcesDirPath;
        this.localesDirPath = localesDirPath;
        this.remoteDebuggingPort = remoteDebuggingPort;
        this.uncaughtExceptionStackSize = uncaughtExceptionStackSize;
        this.backgroundColor = backgroundColor;
        this.acceptLanguageList = acceptLanguageList;
        this.cookieableSchemesList = cookieableSchemesList;
        this.cookieableSchemesExcludeDefaults = cookieableSchemesExcludeDefaults;
        this.chromePolicyId = chromePolicyId;
        this.chromeAppIconId = chromeAppIconId;
        this.disableSignalHandlers = disableSignalHandlers;
        this.useViewsDefaultPopup = useViewsDefaultPopup;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefSettings)) return false;
        CefSettings other = (CefSettings) obj;
        return this.noSandbox == other.noSandbox
                && java.util.Objects.equals(this.browserSubprocessPath, other.browserSubprocessPath)
                && java.util.Objects.equals(this.frameworkDirPath, other.frameworkDirPath)
                && java.util.Objects.equals(this.mainBundlePath, other.mainBundlePath)
                && this.multiThreadedMessageLoop == other.multiThreadedMessageLoop
                && this.externalMessagePump == other.externalMessagePump
                && this.windowlessRenderingEnabled == other.windowlessRenderingEnabled
                && this.commandLineArgsDisabled == other.commandLineArgsDisabled
                && java.util.Objects.equals(this.cachePath, other.cachePath)
                && java.util.Objects.equals(this.rootCachePath, other.rootCachePath)
                && this.persistSessionCookies == other.persistSessionCookies
                && java.util.Objects.equals(this.userAgent, other.userAgent)
                && java.util.Objects.equals(this.userAgentProduct, other.userAgentProduct)
                && java.util.Objects.equals(this.locale, other.locale)
                && java.util.Objects.equals(this.logFile, other.logFile)
                && java.util.Objects.equals(this.logSeverity, other.logSeverity)
                && java.util.Objects.equals(this.logItems, other.logItems)
                && java.util.Objects.equals(this.javascriptFlags, other.javascriptFlags)
                && java.util.Objects.equals(this.resourcesDirPath, other.resourcesDirPath)
                && java.util.Objects.equals(this.localesDirPath, other.localesDirPath)
                && this.remoteDebuggingPort == other.remoteDebuggingPort
                && this.uncaughtExceptionStackSize == other.uncaughtExceptionStackSize
                && this.backgroundColor == other.backgroundColor
                && java.util.Objects.equals(this.acceptLanguageList, other.acceptLanguageList)
                && java.util.Objects.equals(this.cookieableSchemesList, other.cookieableSchemesList)
                && this.cookieableSchemesExcludeDefaults == other.cookieableSchemesExcludeDefaults
                && java.util.Objects.equals(this.chromePolicyId, other.chromePolicyId)
                && this.chromeAppIconId == other.chromeAppIconId
                && this.disableSignalHandlers == other.disableSignalHandlers
                && this.useViewsDefaultPopup == other.useViewsDefaultPopup;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(
                noSandbox,
                browserSubprocessPath,
                frameworkDirPath,
                mainBundlePath,
                multiThreadedMessageLoop,
                externalMessagePump,
                windowlessRenderingEnabled,
                commandLineArgsDisabled,
                cachePath,
                rootCachePath,
                persistSessionCookies,
                userAgent,
                userAgentProduct,
                locale,
                logFile,
                logSeverity,
                logItems,
                javascriptFlags,
                resourcesDirPath,
                localesDirPath,
                remoteDebuggingPort,
                uncaughtExceptionStackSize,
                backgroundColor,
                acceptLanguageList,
                cookieableSchemesList,
                cookieableSchemesExcludeDefaults,
                chromePolicyId,
                chromeAppIconId,
                disableSignalHandlers,
                useViewsDefaultPopup);
    }

    @Override
    public String toString() {
        return "CefSettings{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", " + "noSandbox="
                + noSandbox + ", " + "browserSubprocessPath=" + browserSubprocessPath + ", " + "frameworkDirPath="
                + frameworkDirPath + ", " + "mainBundlePath=" + mainBundlePath + ", " + "multiThreadedMessageLoop="
                + multiThreadedMessageLoop + ", " + "externalMessagePump=" + externalMessagePump + ", "
                + "windowlessRenderingEnabled=" + windowlessRenderingEnabled + ", " + "commandLineArgsDisabled="
                + commandLineArgsDisabled + ", " + "cachePath=" + cachePath + ", " + "rootCachePath=" + rootCachePath
                + ", " + "persistSessionCookies=" + persistSessionCookies + ", " + "userAgent=" + userAgent + ", "
                + "userAgentProduct=" + userAgentProduct + ", " + "locale=" + locale + ", " + "logFile=" + logFile
                + ", " + "logSeverity=" + logSeverity + ", " + "logItems=" + logItems + ", " + "javascriptFlags="
                + javascriptFlags + ", " + "resourcesDirPath=" + resourcesDirPath + ", " + "localesDirPath="
                + localesDirPath + ", " + "remoteDebuggingPort=" + remoteDebuggingPort + ", "
                + "uncaughtExceptionStackSize=" + uncaughtExceptionStackSize + ", " + "backgroundColor="
                + backgroundColor + ", " + "acceptLanguageList=" + acceptLanguageList + ", " + "cookieableSchemesList="
                + cookieableSchemesList + ", " + "cookieableSchemesExcludeDefaults=" + cookieableSchemesExcludeDefaults
                + ", " + "chromePolicyId=" + chromePolicyId + ", " + "chromeAppIconId=" + chromeAppIconId + ", "
                + "disableSignalHandlers=" + disableSignalHandlers + ", " + "useViewsDefaultPopup="
                + useViewsDefaultPopup + "}";
    }
}
