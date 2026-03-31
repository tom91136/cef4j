// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Initialization settings. Specify NULL or 0 to get the recommended default values. Many of these and other settings
 * can also configured using command- line switches.
 */
public final class CefSettings {

    public final long size;
    public final int noSandbox;
    public final int browserSubprocessPath;
    public final int frameworkDirPath;
    public final int mainBundlePath;
    public final int multiThreadedMessageLoop;
    public final int externalMessagePump;
    public final int windowlessRenderingEnabled;
    public final int commandLineArgsDisabled;
    public final int cachePath;
    public final int rootCachePath;
    public final int persistSessionCookies;
    public final int userAgent;
    public final int userAgentProduct;
    public final int locale;
    public final int logFile;
    public final CefLogSeverity logSeverity;
    public final CefLogItems logItems;
    public final int javascriptFlags;
    public final int resourcesDirPath;
    public final int localesDirPath;
    public final int remoteDebuggingPort;
    public final int uncaughtExceptionStackSize;
    public final int backgroundColor;
    public final int acceptLanguageList;
    public final int cookieableSchemesList;
    public final int cookieableSchemesExcludeDefaults;
    public final int chromePolicyId;
    public final int chromeAppIconId;
    public final int disableSignalHandlers;
    public final int useViewsDefaultPopup;

    public CefSettings(
            long size,
            int noSandbox,
            int browserSubprocessPath,
            int frameworkDirPath,
            int mainBundlePath,
            int multiThreadedMessageLoop,
            int externalMessagePump,
            int windowlessRenderingEnabled,
            int commandLineArgsDisabled,
            int cachePath,
            int rootCachePath,
            int persistSessionCookies,
            int userAgent,
            int userAgentProduct,
            int locale,
            int logFile,
            CefLogSeverity logSeverity,
            CefLogItems logItems,
            int javascriptFlags,
            int resourcesDirPath,
            int localesDirPath,
            int remoteDebuggingPort,
            int uncaughtExceptionStackSize,
            int backgroundColor,
            int acceptLanguageList,
            int cookieableSchemesList,
            int cookieableSchemesExcludeDefaults,
            int chromePolicyId,
            int chromeAppIconId,
            int disableSignalHandlers,
            int useViewsDefaultPopup) {
        this.size = size;
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
        return this.size == other.size
                && this.noSandbox == other.noSandbox
                && this.browserSubprocessPath == other.browserSubprocessPath
                && this.frameworkDirPath == other.frameworkDirPath
                && this.mainBundlePath == other.mainBundlePath
                && this.multiThreadedMessageLoop == other.multiThreadedMessageLoop
                && this.externalMessagePump == other.externalMessagePump
                && this.windowlessRenderingEnabled == other.windowlessRenderingEnabled
                && this.commandLineArgsDisabled == other.commandLineArgsDisabled
                && this.cachePath == other.cachePath
                && this.rootCachePath == other.rootCachePath
                && this.persistSessionCookies == other.persistSessionCookies
                && this.userAgent == other.userAgent
                && this.userAgentProduct == other.userAgentProduct
                && this.locale == other.locale
                && this.logFile == other.logFile
                && java.util.Objects.equals(this.logSeverity, other.logSeverity)
                && java.util.Objects.equals(this.logItems, other.logItems)
                && this.javascriptFlags == other.javascriptFlags
                && this.resourcesDirPath == other.resourcesDirPath
                && this.localesDirPath == other.localesDirPath
                && this.remoteDebuggingPort == other.remoteDebuggingPort
                && this.uncaughtExceptionStackSize == other.uncaughtExceptionStackSize
                && this.backgroundColor == other.backgroundColor
                && this.acceptLanguageList == other.acceptLanguageList
                && this.cookieableSchemesList == other.cookieableSchemesList
                && this.cookieableSchemesExcludeDefaults == other.cookieableSchemesExcludeDefaults
                && this.chromePolicyId == other.chromePolicyId
                && this.chromeAppIconId == other.chromeAppIconId
                && this.disableSignalHandlers == other.disableSignalHandlers
                && this.useViewsDefaultPopup == other.useViewsDefaultPopup;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(
                size,
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
        return "CefSettings{" + "size=" + size + ", " + "noSandbox=" + noSandbox + ", " + "browserSubprocessPath="
                + browserSubprocessPath + ", " + "frameworkDirPath=" + frameworkDirPath + ", " + "mainBundlePath="
                + mainBundlePath + ", " + "multiThreadedMessageLoop=" + multiThreadedMessageLoop + ", "
                + "externalMessagePump=" + externalMessagePump + ", " + "windowlessRenderingEnabled="
                + windowlessRenderingEnabled + ", " + "commandLineArgsDisabled=" + commandLineArgsDisabled + ", "
                + "cachePath=" + cachePath + ", " + "rootCachePath=" + rootCachePath + ", " + "persistSessionCookies="
                + persistSessionCookies + ", " + "userAgent=" + userAgent + ", " + "userAgentProduct="
                + userAgentProduct + ", " + "locale=" + locale + ", " + "logFile=" + logFile + ", " + "logSeverity="
                + logSeverity + ", " + "logItems=" + logItems + ", " + "javascriptFlags=" + javascriptFlags + ", "
                + "resourcesDirPath=" + resourcesDirPath + ", " + "localesDirPath=" + localesDirPath + ", "
                + "remoteDebuggingPort=" + remoteDebuggingPort + ", " + "uncaughtExceptionStackSize="
                + uncaughtExceptionStackSize + ", " + "backgroundColor=" + backgroundColor + ", "
                + "acceptLanguageList=" + acceptLanguageList + ", " + "cookieableSchemesList=" + cookieableSchemesList
                + ", " + "cookieableSchemesExcludeDefaults=" + cookieableSchemesExcludeDefaults + ", "
                + "chromePolicyId=" + chromePolicyId + ", " + "chromeAppIconId=" + chromeAppIconId + ", "
                + "disableSignalHandlers=" + disableSignalHandlers + ", " + "useViewsDefaultPopup="
                + useViewsDefaultPopup + "}";
    }
}
