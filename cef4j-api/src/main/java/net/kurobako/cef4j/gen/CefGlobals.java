// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class CefGlobals {

    private CefGlobals() {}

    public static int isRtl() {
        return N_IsRtl();
    }

    public static int beginTracing(@Nonnull String categories, @Nonnull CefCompletionCallback callback) {
        return N_BeginTracing(categories, callback);
    }

    public static int endTracing(@Nonnull String tracingFile, @Nonnull CefEndTracingCallback callback) {
        return N_EndTracing(tracingFile, callback);
    }

    public static long nowFromSystemTraceTime() {
        return N_NowFromSystemTraceTime();
    }

    public static int resolveUrl(@Nonnull String baseUrl, @Nonnull String relativeUrl, @Nonnull String resolvedUrl) {
        return N_ResolveUrl(baseUrl, relativeUrl, resolvedUrl);
    }

    public static int parseUrl(@Nonnull String url, @Nonnull NativePointer parts) {
        return N_ParseUrl(url, parts);
    }

    public static int createUrl(@Nonnull NativePointer parts, @Nonnull String url) {
        return N_CreateUrl(parts, url);
    }

    public static Optional<String> formatUrlForSecurityDisplay(@Nonnull String originUrl) {
        return Optional.ofNullable(N_FormatUrlForSecurityDisplay(originUrl));
    }

    /**
     * Returns the mime type.
     *
     * <p>Definition generated from cef_parser_capi.h
     *
     * <pre>CEF_EXPORT cef_string_userfree_t cef_get_mime_type(const cef_string_t* extension);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__item_8h.html">cef_download_item.h:167</a>
     */
    public static Optional<String> getMimeType(@Nonnull String extension) {
        return Optional.ofNullable(N_GetMimeType(extension));
    }

    public static void getExtensionsForMimeType(@Nonnull String mimeType, @Nonnull List<String> extensions) {
        N_GetExtensionsForMimeType(mimeType, extensions);
    }

    public static Optional<String> base64Encode(@Nonnull ByteBuffer data) {
        return Optional.ofNullable(N_Base64Encode(data));
    }

    public static Optional<String> uriencode(@Nonnull String text, int usePlus) {
        return Optional.ofNullable(N_Uriencode(text, usePlus));
    }

    public static Optional<String> uridecode(
            @Nonnull String text, int convertToUtf8, @Nonnull CefUriUnescapeRule unescapeRule) {
        return Optional.ofNullable(N_Uridecode(text, convertToUtf8, unescapeRule));
    }

    public static Optional<String> writeJson(@Nonnull CefValue node, @Nonnull CefJsonWriterOptions options) {
        return Optional.ofNullable(N_WriteJson(node, options));
    }

    public static int crashReportingEnabled() {
        return N_CrashReportingEnabled();
    }

    public static void setCrashKeyValue(@Nonnull String key, @Nonnull String value) {
        N_SetCrashKeyValue(key, value);
    }

    public static int currentlyOn(@Nonnull CefThreadId threadid) {
        return N_CurrentlyOn(threadid);
    }

    /**
     * Post a task for execution on the thread associated with this task runner. Execution will occur asynchronously.
     *
     * <p>Definition generated from cef_task_capi.h
     *
     * <pre>CEF_EXPORT int cef_post_task(cef_thread_id_t threadId, cef_task_t* task);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__task_8h.html">cef_task.h:107</a>
     */
    public static boolean postTask(@Nonnull CefThreadId threadid, @Nonnull CefTask task) {
        return N_PostTask(threadid, task);
    }

    /**
     * Post a task for delayed execution on the thread associated with this task runner. Execution will occur
     * asynchronously. Delayed tasks are not supported on V8 WebWorker threads and will be executed without the
     * specified delay.
     *
     * <p>Definition generated from cef_task_capi.h
     *
     * <pre>CEF_EXPORT int cef_post_delayed_task(cef_thread_id_t threadId, cef_task_t* task, int64_t delay_ms);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__task_8h.html">cef_task.h:114</a>
     */
    public static boolean postDelayedTask(@Nonnull CefThreadId threadid, @Nonnull CefTask task, long delayMs) {
        return N_PostDelayedTask(threadid, task, delayMs);
    }

    public static int addCrossOriginWhitelistEntry(
            @Nonnull String sourceOrigin,
            @Nonnull String targetProtocol,
            @Nonnull String targetDomain,
            int allowTargetSubdomains) {
        return N_AddCrossOriginWhitelistEntry(sourceOrigin, targetProtocol, targetDomain, allowTargetSubdomains);
    }

    public static int removeCrossOriginWhitelistEntry(
            @Nonnull String sourceOrigin,
            @Nonnull String targetProtocol,
            @Nonnull String targetDomain,
            int allowTargetSubdomains) {
        return N_RemoveCrossOriginWhitelistEntry(sourceOrigin, targetProtocol, targetDomain, allowTargetSubdomains);
    }

    public static int clearCrossOriginWhitelist() {
        return N_ClearCrossOriginWhitelist();
    }

    public static int isCertStatusError(@Nonnull CefCertStatus status) {
        return N_IsCertStatusError(status);
    }

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
     * <p>Definition generated from cef_scheme_capi.h
     *
     * <pre>
     * CEF_EXPORT int cef_register_scheme_handler_factory(const cef_string_t* scheme_name, const cef_string_t* domain_name, cef_scheme_handler_factory_t* factory);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__context_8h.html">cef_request_context.h:177</a>
     */
    public static boolean registerSchemeHandlerFactory(
            @Nonnull String schemeName, @Nullable String domainName, @Nullable CefSchemeHandlerFactory factory) {
        return N_RegisterSchemeHandlerFactory(schemeName, domainName, factory);
    }

    /**
     * Clear all registered scheme handler factories. Returns {@code false} on error. This function may be called on any
     * thread in the browser process.
     *
     * <p>Definition generated from cef_scheme_capi.h
     *
     * <pre>CEF_EXPORT int cef_clear_scheme_handler_factories(void);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__context_8h.html">cef_request_context.h:196</a>
     */
    public static boolean clearSchemeHandlerFactories() {
        return N_ClearSchemeHandlerFactories();
    }

    public static int registerExtension(
            @Nonnull String extensionName, @Nonnull String javascriptCode, @Nonnull CefV8Handler handler) {
        return N_RegisterExtension(extensionName, javascriptCode, handler);
    }

    public static int launchProcess(@Nonnull CefCommandLine commandLine) {
        return N_LaunchProcess(commandLine);
    }

    public static int getPath(@Nonnull CefPathKey key, @Nonnull String path) {
        return N_GetPath(key, path);
    }

    public static int executeProcess(
            @Nonnull NativePointer args, @Nonnull CefApp application, @Nonnull NativePointer windowsSandboxInfo) {
        return N_ExecuteProcess(args, application, windowsSandboxInfo);
    }

    public static int initialize(
            @Nonnull NativePointer args,
            @Nonnull NativePointer settings,
            @Nonnull CefApp application,
            @Nonnull NativePointer windowsSandboxInfo) {
        return N_Initialize(args, settings, application, windowsSandboxInfo);
    }

    public static int getExitCode() {
        return N_GetExitCode();
    }

    /**
     * Stop the server and shut down the dedicated server thread. See
     * {@link CefServerHandler#onServerCreated(CefServer)} documentation for a description of server lifespan.
     *
     * <p>Definition generated from cef_app_capi.h
     *
     * <pre>CEF_EXPORT void cef_shutdown(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__server_8h.html">cef_server.h:88</a>
     */
    public static void shutdown() {
        N_Shutdown();
    }

    public static void doMessageLoopWork() {
        N_DoMessageLoopWork();
    }

    public static void runMessageLoop() {
        N_RunMessageLoop();
    }

    public static void quitMessageLoop() {
        N_QuitMessageLoop();
    }

    public static void setNestableTasksAllowed(int allowed) {
        N_SetNestableTasksAllowed(allowed);
    }

    public static int createDirectory(@Nonnull String fullPath) {
        return N_CreateDirectory(fullPath);
    }

    public static int getTempDirectory(@Nonnull String tempDir) {
        return N_GetTempDirectory(tempDir);
    }

    public static int createNewTempDirectory(@Nonnull String prefix, @Nonnull String newTempPath) {
        return N_CreateNewTempDirectory(prefix, newTempPath);
    }

    public static int createTempDirectoryInDirectory(
            @Nonnull String baseDir, @Nonnull String prefix, @Nonnull String newDir) {
        return N_CreateTempDirectoryInDirectory(baseDir, prefix, newDir);
    }

    public static int directoryExists(@Nonnull String path) {
        return N_DirectoryExists(path);
    }

    public static int deleteFile(@Nonnull String path, int recursive) {
        return N_DeleteFile(path, recursive);
    }

    public static int zipDirectory(@Nonnull String srcDir, @Nonnull String destFile, int includeHiddenFiles) {
        return N_ZipDirectory(srcDir, destFile, includeHiddenFiles);
    }

    public static void loadCrlsetsFile(@Nonnull String path) {
        N_LoadCrlsetsFile(path);
    }

    private static native int N_IsRtl();

    private static native int N_BeginTracing(String categories, CefCompletionCallback callback);

    private static native int N_EndTracing(String tracingFile, CefEndTracingCallback callback);

    private static native long N_NowFromSystemTraceTime();

    private static native int N_ResolveUrl(String baseUrl, String relativeUrl, String resolvedUrl);

    private static native int N_ParseUrl(String url, NativePointer parts);

    private static native int N_CreateUrl(NativePointer parts, String url);

    private static native String N_FormatUrlForSecurityDisplay(String originUrl);

    private static native String N_GetMimeType(String extension);

    private static native void N_GetExtensionsForMimeType(String mimeType, List<String> extensions);

    private static native String N_Base64Encode(ByteBuffer data);

    private static native String N_Uriencode(String text, int usePlus);

    private static native String N_Uridecode(String text, int convertToUtf8, CefUriUnescapeRule unescapeRule);

    private static native String N_WriteJson(CefValue node, CefJsonWriterOptions options);

    private static native int N_CrashReportingEnabled();

    private static native void N_SetCrashKeyValue(String key, String value);

    private static native int N_CurrentlyOn(CefThreadId threadid);

    private static native boolean N_PostTask(CefThreadId threadid, CefTask task);

    private static native boolean N_PostDelayedTask(CefThreadId threadid, CefTask task, long delayMs);

    private static native int N_AddCrossOriginWhitelistEntry(
            String sourceOrigin, String targetProtocol, String targetDomain, int allowTargetSubdomains);

    private static native int N_RemoveCrossOriginWhitelistEntry(
            String sourceOrigin, String targetProtocol, String targetDomain, int allowTargetSubdomains);

    private static native int N_ClearCrossOriginWhitelist();

    private static native int N_IsCertStatusError(CefCertStatus status);

    private static native boolean N_RegisterSchemeHandlerFactory(
            String schemeName, String domainName, CefSchemeHandlerFactory factory);

    private static native boolean N_ClearSchemeHandlerFactories();

    private static native int N_RegisterExtension(String extensionName, String javascriptCode, CefV8Handler handler);

    private static native int N_LaunchProcess(CefCommandLine commandLine);

    private static native int N_GetPath(CefPathKey key, String path);

    private static native int N_ExecuteProcess(
            NativePointer args, CefApp application, NativePointer windowsSandboxInfo);

    private static native int N_Initialize(
            NativePointer args, NativePointer settings, CefApp application, NativePointer windowsSandboxInfo);

    private static native int N_GetExitCode();

    private static native void N_Shutdown();

    private static native void N_DoMessageLoopWork();

    private static native void N_RunMessageLoop();

    private static native void N_QuitMessageLoop();

    private static native void N_SetNestableTasksAllowed(int allowed);

    private static native int N_CreateDirectory(String fullPath);

    private static native int N_GetTempDirectory(String tempDir);

    private static native int N_CreateNewTempDirectory(String prefix, String newTempPath);

    private static native int N_CreateTempDirectoryInDirectory(String baseDir, String prefix, String newDir);

    private static native int N_DirectoryExists(String path);

    private static native int N_DeleteFile(String path, int recursive);

    private static native int N_ZipDirectory(String srcDir, String destFile, int includeHiddenFiles);

    private static native void N_LoadCrlsetsFile(String path);
}
