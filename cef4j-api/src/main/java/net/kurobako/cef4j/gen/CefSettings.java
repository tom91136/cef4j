// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Initialization settings. Specify {@code null} or 0 to get the recommended default values. Many of these and other settings can also configured using command-line switches.
 * <p>Definition generated from internal/cef_types.h
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
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">internal/cef_types.h:199</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public final class CefSettings {

    // Native struct size, populated by the JNI layer with sizeof(struct) as required by CEF. Not user-modifiable.
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    private volatile long size = -1;

        /**
         * Set to {@code true} (1) to disable the sandbox for sub-processes. See cef_sandbox_win.h for requirements to enable the sandbox on Windows. Also configurable using the "no-sandbox" command-line switch. 
         */    public final int noSandbox;
        /**
         * The path to a separate executable that will be launched for sub-processes. If this value is empty on Windows or Linux then the main process executable will be used. If this value is empty on macOS then a helper executable must exist at "Contents/Frameworks/&lt;app&gt; Helper.app/Contents/MacOS/&lt;app&gt; Helper" in the top-level app bundle. See the comments on CefExecuteProcess() for details. If this value is non-empty then it must be an absolute path. Also configurable using the "browser-subprocess-path" command-line switch. 
         */    public final String browserSubprocessPath;
        /**
         * The path to the CEF framework directory on macOS. If this value is empty then the framework must exist at "Contents/Frameworks/Chromium Embedded Framework.framework" in the top-level app bundle. If this value is non-empty then it must be an absolute path. Also configurable using the "framework-dir-path" command-line switch. 
         */    public final String frameworkDirPath;
        /**
         * The path to the main bundle on macOS. If this value is empty then it defaults to the top-level app bundle. If this value is non-empty then it must be an absolute path. Also configurable using the "main-bundle-path" command-line switch. 
         */    public final String mainBundlePath;
        /**
         * Set to {@code true} (1) to have the browser process message loop run in a separate thread. If {@code false} (0) then the CefDoMessageLoopWork() function must be called from your application message loop. This option is only supported on Windows and Linux. 
         */    public final int multiThreadedMessageLoop;
        /**
         * Set to {@code true} (1) to control browser process main (UI) thread message pump scheduling via the {@link net.kurobako.cef4j.gen.CefBrowserProcessHandler#onScheduleMessagePumpWork(long)} callback. This option is recommended for use in combination with the CefDoMessageLoopWork() function in cases where the CEF message loop must be integrated into an existing application message loop (see additional comments and warnings on CefDoMessageLoopWork). Enabling this option is not recommended for most users; leave this option disabled and use either the CefRunMessageLoop() function or multi_threaded_message_loop if possible. 
         */    public final int externalMessagePump;
        /**
         * Set to {@code true} (1) to enable windowless (off-screen) rendering support. Do not enable this value if the application does not use windowless rendering as it may reduce rendering performance on some systems. 
         */    public final int windowlessRenderingEnabled;
        /**
         * Set to {@code true} (1) to disable configuration of browser process features using standard CEF and Chromium command-line arguments. Configuration can still be specified using CEF data structures or via the {@link net.kurobako.cef4j.gen.CefApp#onBeforeCommandLineProcessing(String, CefCommandLine)} method. 
         */    public final int commandLineArgsDisabled;
        /**
         * The directory where data for the global browser cache will be stored on disk. If this value is non-empty then it must be an absolute path that is either equal to or a child directory of CefSettings.root_cache_path. If this value is empty then browsers will be created in "incognito mode" where in-memory caches are used for storage and no profile-specific data is persisted to disk (installation-specific data will still be persisted in root_cache_path). HTML5 databases such as localStorage will only persist across sessions if a cache path is specified. Can be overridden for individual CefRequestContext instances via the CefRequestContextSettings.cache_path value. Any child directory value will be ignored and the "default" profile (also a child directory) will be used instead. 
         */    public final String cachePath;
        /**
         * The root directory for installation-specific data and the parent directory for profile-specific data. All CefSettings.cache_path and CefRequestContextSettings.cache_path values must have this parent directory in common. If this value is empty and CefSettings.cache_path is non-empty then it will default to the CefSettings.cache_path value. Any non-empty value must be an absolute path. If both values are empty then the default platform-specific directory will be used ("~/.config/cef_user_data" directory on Linux, "~/Library/Application Support/CEF/User Data" directory on MacOS, "AppData\Local\CEF\User Data" directory under the user profile directory on Windows). Use of the default directory is not recommended in production applications (see below).  Multiple application instances writing to the same root_cache_path directory could result in data corruption. A process singleton lock based on the root_cache_path value is therefore used to protect against this. This singleton behavior applies to all CEF-based applications using version 120 or newer. You should customize root_cache_path for your application and implement CefBrowserProcessHandler:: OnAlreadyRunningAppRelaunch, which will then be called on any app relaunch with the same root_cache_path value.  Failure to set the root_cache_path value correctly may result in startup crashes or other unexpected behaviors (for example, the sandbox blocking read/write access to certain files). 
         */    public final String rootCachePath;
        /**
         * To persist session cookies (cookies without an expiry date or validity interval) by default when using the global cookie manager set this value to {@code true} (1). Session cookies are generally intended to be transient and most Web browsers do not persist them. A {@code cache_path} value must also be specified to enable this feature. Also configurable using the "persist-session-cookies" command-line switch. Can be overridden for individual CefRequestContext instances via the CefRequestContextSettings.persist_session_cookies value. 
         */    public final int persistSessionCookies;
        /**
         * Value that will be returned as the User-Agent HTTP header. If empty the default User-Agent string will be used. Also configurable using the "user-agent" command-line switch. 
         */    public final String userAgent;
        /**
         * Value that will be inserted as the product portion of the default User-Agent string. If empty the Chromium product version will be used. If {@code userAgent} is specified this value will be ignored. Also configurable using the "user-agent-product" command-line switch. 
         */    public final String userAgentProduct;
        /**
         * The locale string that will be passed to WebKit. If empty the default locale of "en-US" will be used. This value is ignored on Linux where locale is determined using environment variable parsing with the precedence order: LANGUAGE, LC_ALL, LC_MESSAGES and LANG. Also configurable using the "lang" command-line switch. 
         */    public final String locale;
        /**
         * The directory and file name to use for the debug log. If empty a default log file name and location will be used. On Windows and Linux a "debug.log" file will be written in the main executable directory. On MacOS a "~/Library/Logs/[app name]_debug.log" file will be written where [app name] is the name of the main app executable. Also configurable using the "log-file" command-line switch. 
         */    public final String logFile;
        /**
         * The log severity. Only messages of this severity level or higher will be logged. When set to DISABLE no messages will be written to the log file, but FATAL messages will still be output to stderr. Also configurable using the "log-severity" command-line switch with a value of "verbose", "info", "warning", "error", "fatal" or "disable". 
         */    public final CefLogSeverity logSeverity;
        /**
         * The log items prepended to each log line. If not set the default log items will be used. Also configurable using the "log-items" command-line switch with a value of "none" for no log items, or a comma-delimited list of values "pid", "tid", "timestamp" or "tickcount" for custom log items. 
         */    public final CefLogItems logItems;
        /**
         * Custom flags that will be used when initializing the V8 JavaScript engine. The consequences of using custom flags may not be well tested. Also configurable using the "js-flags" command-line switch. 
         */    public final String javascriptFlags;
        /**
         * The fully qualified path for the resources directory. If this value is empty the *.pak files must be located in the module directory on Windows/Linux or the app bundle Resources directory on MacOS. If this value is non-empty then it must be an absolute path. Also configurable using the "resources-dir-path" command-line switch. 
         */    public final String resourcesDirPath;
        /**
         * The fully qualified path for the locales directory. If this value is empty the locales directory must be located in the module directory. If this value is non-empty then it must be an absolute path. This value is ignored on MacOS where pack files are always loaded from the app bundle Resources directory. Also configurable using the "locales-dir-path" command-line switch. 
         */    public final String localesDirPath;
        /**
         * Set to a value between 1024 and 65535 to enable remote debugging on the specified port. Also configurable using the "remote-debugging-port" command-line switch. Specifying 0 via the command-line switch will result in the selection of an ephemeral port and the port number will be printed as part of the WebSocket endpoint URL to stderr. If a cache directory path is provided the port will also be written to the &lt;cache-dir&gt;/DevToolsActivePort file. Remote debugging can be accessed by loading the chrome://inspect page in Google Chrome. Port numbers 9222 and 9229 are discoverable by default. Other port numbers may need to be configured via "Discover network targets" on the Devices tab. 
         */    public final int remoteDebuggingPort;
        /**
         * The number of stack trace frames to capture for uncaught exceptions. Specify a positive value to enable the {@link net.kurobako.cef4j.gen.CefRenderProcessHandler#onUncaughtException(CefBrowser, CefFrame, CefV8Context, CefV8Exception, CefV8StackTrace)} callback. Specify 0 (default value) and OnUncaughtException() will not be called. Also configurable using the "uncaught-exception-stack-size" command-line switch. 
         */    public final int uncaughtExceptionStackSize;
        /**
         * Background color used for the browser before a document is loaded and when no document color is specified. The alpha component must be either fully opaque (0xFF) or fully transparent (0x00). If the alpha component is fully opaque then the RGB components will be used as the background color. If the alpha component is fully transparent for a windowed browser then the default value of opaque white be used. If the alpha component is fully transparent for a windowless (off-screen) browser then transparent painting will be enabled. 
         */    public final int backgroundColor;
        /**
         * Comma delimited ordered list of language codes without any whitespace that will be used in the "Accept-Language" HTTP request header and "navigator.language" JS attribute. Can be overridden for individual CefRequestContext instances via the CefRequestContextSettings.accept_language_list value. 
         */    public final String acceptLanguageList;
        /**
         * Comma delimited list of schemes supported by the associated CefCookieManager. If {@code cookieable_schemes_exclude_defaults} is {@code false} (0) the default schemes ("http", "https", "ws" and "wss") will also be supported. Not specifying a {@code cookieable_schemes_list} value and setting {@code cookieable_schemes_exclude_defaults} to {@code true} (1) will disable all loading and saving of cookies. These settings will only impact the global CefRequestContext. Individual CefRequestContext instances can be configured via the CefRequestContextSettings.cookieable_schemes_list and CefRequestContextSettings.cookieable_schemes_exclude_defaults values. 
         */    public final String cookieableSchemesList;
    public final int cookieableSchemesExcludeDefaults;
        /**
         * Specify an ID to enable Chrome policy management via Platform and OS-user policies. On Windows, this is a registry key like "SOFTWARE\\Policies\\Google\\Chrome". On MacOS, this is a bundle ID like "com.google.Chrome". On Linux, this is an absolute directory path like "/etc/opt/chrome/policies". Only supported with Chrome style. See <a href="https://support.google.com/chrome/a/answer/9037717">https://support.google.com/chrome/a/answer/9037717</a> for details.  Chrome Browser Cloud Management integration, when enabled via the "enable-chrome-browser-cloud-management" command-line flag, will also use the specified ID. See <a href="https://support.google.com/chrome/a/answer/9116814">https://support.google.com/chrome/a/answer/9116814</a> for details. 
         */    public final String chromePolicyId;
        /**
         * Specify an ID for an ICON resource that can be loaded from the main executable and used when creating default Chrome windows such as DevTools and Task Manager. If unspecified the default Chromium ICON (IDR_MAINFRAME [101]) will be loaded from libcef.dll. Only supported with Chrome style on Windows. 
         */    public final int chromeAppIconId;
        /**
         * Specify whether signal handlers must be disabled on POSIX systems. 
         */    public final int disableSignalHandlers;
        /**
         * If {@code true} use a Views (bare-bones) window instead of a Chrome UI window when creating default popups for Chrome style native-hosted (non-Views) browsers. This applies when {@link net.kurobako.cef4j.gen.CefLifeSpanHandler#onBeforePopup(CefBrowser, CefFrame, int, String, String, CefWindowOpenDisposition, boolean, NativePointer, CefWindowInfo.Mutable, java.util.concurrent.atomic.AtomicReference, CefBrowserSettings.Mutable, java.util.concurrent.atomic.AtomicReference, int[])} has not been implemented to provide parent window information for the new popup. 
         */    public final int useViewsDefaultPopup;

    public CefSettings(int noSandbox, String browserSubprocessPath, String frameworkDirPath, String mainBundlePath, int multiThreadedMessageLoop, int externalMessagePump, int windowlessRenderingEnabled, int commandLineArgsDisabled, String cachePath, String rootCachePath, int persistSessionCookies, String userAgent, String userAgentProduct, String locale, String logFile, CefLogSeverity logSeverity, CefLogItems logItems, String javascriptFlags, String resourcesDirPath, String localesDirPath, int remoteDebuggingPort, int uncaughtExceptionStackSize, int backgroundColor, String acceptLanguageList, String cookieableSchemesList, int cookieableSchemesExcludeDefaults, String chromePolicyId, int chromeAppIconId, int disableSignalHandlers, int useViewsDefaultPopup) {
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

    /** Create a mutable copy of this instance. */
    public Mutable toMutable() {
        return new Mutable(this.noSandbox, this.browserSubprocessPath, this.frameworkDirPath, this.mainBundlePath, this.multiThreadedMessageLoop, this.externalMessagePump, this.windowlessRenderingEnabled, this.commandLineArgsDisabled, this.cachePath, this.rootCachePath, this.persistSessionCookies, this.userAgent, this.userAgentProduct, this.locale, this.logFile, this.logSeverity, this.logItems, this.javascriptFlags, this.resourcesDirPath, this.localesDirPath, this.remoteDebuggingPort, this.uncaughtExceptionStackSize, this.backgroundColor, this.acceptLanguageList, this.cookieableSchemesList, this.cookieableSchemesExcludeDefaults, this.chromePolicyId, this.chromeAppIconId, this.disableSignalHandlers, this.useViewsDefaultPopup);
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
        return java.util.Objects.hash(noSandbox, browserSubprocessPath, frameworkDirPath, mainBundlePath, multiThreadedMessageLoop, externalMessagePump, windowlessRenderingEnabled, commandLineArgsDisabled, cachePath, rootCachePath, persistSessionCookies, userAgent, userAgentProduct, locale, logFile, logSeverity, logItems, javascriptFlags, resourcesDirPath, localesDirPath, remoteDebuggingPort, uncaughtExceptionStackSize, backgroundColor, acceptLanguageList, cookieableSchemesList, cookieableSchemesExcludeDefaults, chromePolicyId, chromeAppIconId, disableSignalHandlers, useViewsDefaultPopup);
    }

    @Override
    public String toString() {
        return "CefSettings{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", " + "noSandbox=" + noSandbox + ", " + "browserSubprocessPath=" + browserSubprocessPath + ", " + "frameworkDirPath=" + frameworkDirPath + ", " + "mainBundlePath=" + mainBundlePath + ", " + "multiThreadedMessageLoop=" + multiThreadedMessageLoop + ", " + "externalMessagePump=" + externalMessagePump + ", " + "windowlessRenderingEnabled=" + windowlessRenderingEnabled + ", " + "commandLineArgsDisabled=" + commandLineArgsDisabled + ", " + "cachePath=" + cachePath + ", " + "rootCachePath=" + rootCachePath + ", " + "persistSessionCookies=" + persistSessionCookies + ", " + "userAgent=" + userAgent + ", " + "userAgentProduct=" + userAgentProduct + ", " + "locale=" + locale + ", " + "logFile=" + logFile + ", " + "logSeverity=" + logSeverity + ", " + "logItems=" + logItems + ", " + "javascriptFlags=" + javascriptFlags + ", " + "resourcesDirPath=" + resourcesDirPath + ", " + "localesDirPath=" + localesDirPath + ", " + "remoteDebuggingPort=" + remoteDebuggingPort + ", " + "uncaughtExceptionStackSize=" + uncaughtExceptionStackSize + ", " + "backgroundColor=" + backgroundColor + ", " + "acceptLanguageList=" + acceptLanguageList + ", " + "cookieableSchemesList=" + cookieableSchemesList + ", " + "cookieableSchemesExcludeDefaults=" + cookieableSchemesExcludeDefaults + ", " + "chromePolicyId=" + chromePolicyId + ", " + "chromeAppIconId=" + chromeAppIconId + ", " + "disableSignalHandlers=" + disableSignalHandlers + ", " + "useViewsDefaultPopup=" + useViewsDefaultPopup + "}";
    }

    /**
     * Mutable variant of {@link CefSettings}. Initialization settings. Specify {@code null} or 0 to get the recommended default values. Many of these and other settings can also configured using command-line switches.
     * <p>Definition generated from internal/cef_types.h
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">internal/cef_types.h:199</a>
     */
    public static final class Mutable {

    // Native struct size, populated by the JNI layer with sizeof(struct) as required by CEF. Not user-modifiable.
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    private volatile long size = -1;

            /**
             * Set to {@code true} (1) to disable the sandbox for sub-processes. See cef_sandbox_win.h for requirements to enable the sandbox on Windows. Also configurable using the "no-sandbox" command-line switch. 
             */        public int noSandbox;
            /**
             * The path to a separate executable that will be launched for sub-processes. If this value is empty on Windows or Linux then the main process executable will be used. If this value is empty on macOS then a helper executable must exist at "Contents/Frameworks/&lt;app&gt; Helper.app/Contents/MacOS/&lt;app&gt; Helper" in the top-level app bundle. See the comments on CefExecuteProcess() for details. If this value is non-empty then it must be an absolute path. Also configurable using the "browser-subprocess-path" command-line switch. 
             */        public String browserSubprocessPath;
            /**
             * The path to the CEF framework directory on macOS. If this value is empty then the framework must exist at "Contents/Frameworks/Chromium Embedded Framework.framework" in the top-level app bundle. If this value is non-empty then it must be an absolute path. Also configurable using the "framework-dir-path" command-line switch. 
             */        public String frameworkDirPath;
            /**
             * The path to the main bundle on macOS. If this value is empty then it defaults to the top-level app bundle. If this value is non-empty then it must be an absolute path. Also configurable using the "main-bundle-path" command-line switch. 
             */        public String mainBundlePath;
            /**
             * Set to {@code true} (1) to have the browser process message loop run in a separate thread. If {@code false} (0) then the CefDoMessageLoopWork() function must be called from your application message loop. This option is only supported on Windows and Linux. 
             */        public int multiThreadedMessageLoop;
            /**
             * Set to {@code true} (1) to control browser process main (UI) thread message pump scheduling via the {@link net.kurobako.cef4j.gen.CefBrowserProcessHandler#onScheduleMessagePumpWork(long)} callback. This option is recommended for use in combination with the CefDoMessageLoopWork() function in cases where the CEF message loop must be integrated into an existing application message loop (see additional comments and warnings on CefDoMessageLoopWork). Enabling this option is not recommended for most users; leave this option disabled and use either the CefRunMessageLoop() function or multi_threaded_message_loop if possible. 
             */        public int externalMessagePump;
            /**
             * Set to {@code true} (1) to enable windowless (off-screen) rendering support. Do not enable this value if the application does not use windowless rendering as it may reduce rendering performance on some systems. 
             */        public int windowlessRenderingEnabled;
            /**
             * Set to {@code true} (1) to disable configuration of browser process features using standard CEF and Chromium command-line arguments. Configuration can still be specified using CEF data structures or via the {@link net.kurobako.cef4j.gen.CefApp#onBeforeCommandLineProcessing(String, CefCommandLine)} method. 
             */        public int commandLineArgsDisabled;
            /**
             * The directory where data for the global browser cache will be stored on disk. If this value is non-empty then it must be an absolute path that is either equal to or a child directory of CefSettings.root_cache_path. If this value is empty then browsers will be created in "incognito mode" where in-memory caches are used for storage and no profile-specific data is persisted to disk (installation-specific data will still be persisted in root_cache_path). HTML5 databases such as localStorage will only persist across sessions if a cache path is specified. Can be overridden for individual CefRequestContext instances via the CefRequestContextSettings.cache_path value. Any child directory value will be ignored and the "default" profile (also a child directory) will be used instead. 
             */        public String cachePath;
            /**
             * The root directory for installation-specific data and the parent directory for profile-specific data. All CefSettings.cache_path and CefRequestContextSettings.cache_path values must have this parent directory in common. If this value is empty and CefSettings.cache_path is non-empty then it will default to the CefSettings.cache_path value. Any non-empty value must be an absolute path. If both values are empty then the default platform-specific directory will be used ("~/.config/cef_user_data" directory on Linux, "~/Library/Application Support/CEF/User Data" directory on MacOS, "AppData\Local\CEF\User Data" directory under the user profile directory on Windows). Use of the default directory is not recommended in production applications (see below).  Multiple application instances writing to the same root_cache_path directory could result in data corruption. A process singleton lock based on the root_cache_path value is therefore used to protect against this. This singleton behavior applies to all CEF-based applications using version 120 or newer. You should customize root_cache_path for your application and implement CefBrowserProcessHandler:: OnAlreadyRunningAppRelaunch, which will then be called on any app relaunch with the same root_cache_path value.  Failure to set the root_cache_path value correctly may result in startup crashes or other unexpected behaviors (for example, the sandbox blocking read/write access to certain files). 
             */        public String rootCachePath;
            /**
             * To persist session cookies (cookies without an expiry date or validity interval) by default when using the global cookie manager set this value to {@code true} (1). Session cookies are generally intended to be transient and most Web browsers do not persist them. A {@code cache_path} value must also be specified to enable this feature. Also configurable using the "persist-session-cookies" command-line switch. Can be overridden for individual CefRequestContext instances via the CefRequestContextSettings.persist_session_cookies value. 
             */        public int persistSessionCookies;
            /**
             * Value that will be returned as the User-Agent HTTP header. If empty the default User-Agent string will be used. Also configurable using the "user-agent" command-line switch. 
             */        public String userAgent;
            /**
             * Value that will be inserted as the product portion of the default User-Agent string. If empty the Chromium product version will be used. If {@code userAgent} is specified this value will be ignored. Also configurable using the "user-agent-product" command-line switch. 
             */        public String userAgentProduct;
            /**
             * The locale string that will be passed to WebKit. If empty the default locale of "en-US" will be used. This value is ignored on Linux where locale is determined using environment variable parsing with the precedence order: LANGUAGE, LC_ALL, LC_MESSAGES and LANG. Also configurable using the "lang" command-line switch. 
             */        public String locale;
            /**
             * The directory and file name to use for the debug log. If empty a default log file name and location will be used. On Windows and Linux a "debug.log" file will be written in the main executable directory. On MacOS a "~/Library/Logs/[app name]_debug.log" file will be written where [app name] is the name of the main app executable. Also configurable using the "log-file" command-line switch. 
             */        public String logFile;
            /**
             * The log severity. Only messages of this severity level or higher will be logged. When set to DISABLE no messages will be written to the log file, but FATAL messages will still be output to stderr. Also configurable using the "log-severity" command-line switch with a value of "verbose", "info", "warning", "error", "fatal" or "disable". 
             */        public CefLogSeverity logSeverity;
            /**
             * The log items prepended to each log line. If not set the default log items will be used. Also configurable using the "log-items" command-line switch with a value of "none" for no log items, or a comma-delimited list of values "pid", "tid", "timestamp" or "tickcount" for custom log items. 
             */        public CefLogItems logItems;
            /**
             * Custom flags that will be used when initializing the V8 JavaScript engine. The consequences of using custom flags may not be well tested. Also configurable using the "js-flags" command-line switch. 
             */        public String javascriptFlags;
            /**
             * The fully qualified path for the resources directory. If this value is empty the *.pak files must be located in the module directory on Windows/Linux or the app bundle Resources directory on MacOS. If this value is non-empty then it must be an absolute path. Also configurable using the "resources-dir-path" command-line switch. 
             */        public String resourcesDirPath;
            /**
             * The fully qualified path for the locales directory. If this value is empty the locales directory must be located in the module directory. If this value is non-empty then it must be an absolute path. This value is ignored on MacOS where pack files are always loaded from the app bundle Resources directory. Also configurable using the "locales-dir-path" command-line switch. 
             */        public String localesDirPath;
            /**
             * Set to a value between 1024 and 65535 to enable remote debugging on the specified port. Also configurable using the "remote-debugging-port" command-line switch. Specifying 0 via the command-line switch will result in the selection of an ephemeral port and the port number will be printed as part of the WebSocket endpoint URL to stderr. If a cache directory path is provided the port will also be written to the &lt;cache-dir&gt;/DevToolsActivePort file. Remote debugging can be accessed by loading the chrome://inspect page in Google Chrome. Port numbers 9222 and 9229 are discoverable by default. Other port numbers may need to be configured via "Discover network targets" on the Devices tab. 
             */        public int remoteDebuggingPort;
            /**
             * The number of stack trace frames to capture for uncaught exceptions. Specify a positive value to enable the {@link net.kurobako.cef4j.gen.CefRenderProcessHandler#onUncaughtException(CefBrowser, CefFrame, CefV8Context, CefV8Exception, CefV8StackTrace)} callback. Specify 0 (default value) and OnUncaughtException() will not be called. Also configurable using the "uncaught-exception-stack-size" command-line switch. 
             */        public int uncaughtExceptionStackSize;
            /**
             * Background color used for the browser before a document is loaded and when no document color is specified. The alpha component must be either fully opaque (0xFF) or fully transparent (0x00). If the alpha component is fully opaque then the RGB components will be used as the background color. If the alpha component is fully transparent for a windowed browser then the default value of opaque white be used. If the alpha component is fully transparent for a windowless (off-screen) browser then transparent painting will be enabled. 
             */        public int backgroundColor;
            /**
             * Comma delimited ordered list of language codes without any whitespace that will be used in the "Accept-Language" HTTP request header and "navigator.language" JS attribute. Can be overridden for individual CefRequestContext instances via the CefRequestContextSettings.accept_language_list value. 
             */        public String acceptLanguageList;
            /**
             * Comma delimited list of schemes supported by the associated CefCookieManager. If {@code cookieable_schemes_exclude_defaults} is {@code false} (0) the default schemes ("http", "https", "ws" and "wss") will also be supported. Not specifying a {@code cookieable_schemes_list} value and setting {@code cookieable_schemes_exclude_defaults} to {@code true} (1) will disable all loading and saving of cookies. These settings will only impact the global CefRequestContext. Individual CefRequestContext instances can be configured via the CefRequestContextSettings.cookieable_schemes_list and CefRequestContextSettings.cookieable_schemes_exclude_defaults values. 
             */        public String cookieableSchemesList;
        public int cookieableSchemesExcludeDefaults;
            /**
             * Specify an ID to enable Chrome policy management via Platform and OS-user policies. On Windows, this is a registry key like "SOFTWARE\\Policies\\Google\\Chrome". On MacOS, this is a bundle ID like "com.google.Chrome". On Linux, this is an absolute directory path like "/etc/opt/chrome/policies". Only supported with Chrome style. See <a href="https://support.google.com/chrome/a/answer/9037717">https://support.google.com/chrome/a/answer/9037717</a> for details.  Chrome Browser Cloud Management integration, when enabled via the "enable-chrome-browser-cloud-management" command-line flag, will also use the specified ID. See <a href="https://support.google.com/chrome/a/answer/9116814">https://support.google.com/chrome/a/answer/9116814</a> for details. 
             */        public String chromePolicyId;
            /**
             * Specify an ID for an ICON resource that can be loaded from the main executable and used when creating default Chrome windows such as DevTools and Task Manager. If unspecified the default Chromium ICON (IDR_MAINFRAME [101]) will be loaded from libcef.dll. Only supported with Chrome style on Windows. 
             */        public int chromeAppIconId;
            /**
             * Specify whether signal handlers must be disabled on POSIX systems. 
             */        public int disableSignalHandlers;
            /**
             * If {@code true} use a Views (bare-bones) window instead of a Chrome UI window when creating default popups for Chrome style native-hosted (non-Views) browsers. This applies when {@link net.kurobako.cef4j.gen.CefLifeSpanHandler#onBeforePopup(CefBrowser, CefFrame, int, String, String, CefWindowOpenDisposition, boolean, NativePointer, CefWindowInfo.Mutable, java.util.concurrent.atomic.AtomicReference, CefBrowserSettings.Mutable, java.util.concurrent.atomic.AtomicReference, int[])} has not been implemented to provide parent window information for the new popup. 
             */        public int useViewsDefaultPopup;

        public Mutable() {}

        public Mutable(int noSandbox, String browserSubprocessPath, String frameworkDirPath, String mainBundlePath, int multiThreadedMessageLoop, int externalMessagePump, int windowlessRenderingEnabled, int commandLineArgsDisabled, String cachePath, String rootCachePath, int persistSessionCookies, String userAgent, String userAgentProduct, String locale, String logFile, CefLogSeverity logSeverity, CefLogItems logItems, String javascriptFlags, String resourcesDirPath, String localesDirPath, int remoteDebuggingPort, int uncaughtExceptionStackSize, int backgroundColor, String acceptLanguageList, String cookieableSchemesList, int cookieableSchemesExcludeDefaults, String chromePolicyId, int chromeAppIconId, int disableSignalHandlers, int useViewsDefaultPopup) {
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

        /** Create an immutable snapshot of this instance. */
        public CefSettings toImmutable() {
            return new CefSettings(this.noSandbox, this.browserSubprocessPath, this.frameworkDirPath, this.mainBundlePath, this.multiThreadedMessageLoop, this.externalMessagePump, this.windowlessRenderingEnabled, this.commandLineArgsDisabled, this.cachePath, this.rootCachePath, this.persistSessionCookies, this.userAgent, this.userAgentProduct, this.locale, this.logFile, this.logSeverity, this.logItems, this.javascriptFlags, this.resourcesDirPath, this.localesDirPath, this.remoteDebuggingPort, this.uncaughtExceptionStackSize, this.backgroundColor, this.acceptLanguageList, this.cookieableSchemesList, this.cookieableSchemesExcludeDefaults, this.chromePolicyId, this.chromeAppIconId, this.disableSignalHandlers, this.useViewsDefaultPopup);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Mutable)) return false;
            Mutable other = (Mutable) obj;
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
            return java.util.Objects.hash(noSandbox, browserSubprocessPath, frameworkDirPath, mainBundlePath, multiThreadedMessageLoop, externalMessagePump, windowlessRenderingEnabled, commandLineArgsDisabled, cachePath, rootCachePath, persistSessionCookies, userAgent, userAgentProduct, locale, logFile, logSeverity, logItems, javascriptFlags, resourcesDirPath, localesDirPath, remoteDebuggingPort, uncaughtExceptionStackSize, backgroundColor, acceptLanguageList, cookieableSchemesList, cookieableSchemesExcludeDefaults, chromePolicyId, chromeAppIconId, disableSignalHandlers, useViewsDefaultPopup);
        }

        @Override
        public String toString() {
            return "CefSettings.Mutable{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", " + "noSandbox=" + noSandbox + ", " + "browserSubprocessPath=" + browserSubprocessPath + ", " + "frameworkDirPath=" + frameworkDirPath + ", " + "mainBundlePath=" + mainBundlePath + ", " + "multiThreadedMessageLoop=" + multiThreadedMessageLoop + ", " + "externalMessagePump=" + externalMessagePump + ", " + "windowlessRenderingEnabled=" + windowlessRenderingEnabled + ", " + "commandLineArgsDisabled=" + commandLineArgsDisabled + ", " + "cachePath=" + cachePath + ", " + "rootCachePath=" + rootCachePath + ", " + "persistSessionCookies=" + persistSessionCookies + ", " + "userAgent=" + userAgent + ", " + "userAgentProduct=" + userAgentProduct + ", " + "locale=" + locale + ", " + "logFile=" + logFile + ", " + "logSeverity=" + logSeverity + ", " + "logItems=" + logItems + ", " + "javascriptFlags=" + javascriptFlags + ", " + "resourcesDirPath=" + resourcesDirPath + ", " + "localesDirPath=" + localesDirPath + ", " + "remoteDebuggingPort=" + remoteDebuggingPort + ", " + "uncaughtExceptionStackSize=" + uncaughtExceptionStackSize + ", " + "backgroundColor=" + backgroundColor + ", " + "acceptLanguageList=" + acceptLanguageList + ", " + "cookieableSchemesList=" + cookieableSchemesList + ", " + "cookieableSchemesExcludeDefaults=" + cookieableSchemesExcludeDefaults + ", " + "chromePolicyId=" + chromePolicyId + ", " + "chromeAppIconId=" + chromeAppIconId + ", " + "disableSignalHandlers=" + disableSignalHandlers + ", " + "useViewsDefaultPopup=" + useViewsDefaultPopup + "}";
        }
    }
}
