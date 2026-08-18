// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused", "NullableForbidden"})
public final class CefGlobals {

    private CefGlobals() {}

    /**
     * This function should be called from the application entry point function to execute a secondary process. It can be used to run secondary processes from the browser client executable (default behavior) or from a separate executable specified by the cef_settings_t.browser_subprocess_path value. If called for the browser process (identified by no "type" command-line value) it will return immediately with a value of -1. If called for a recognized secondary process it will block until the process should exit and then return the process exit code. The {@code application} parameter may be empty. The {@code windows_sandbox_info} parameter is only used on Windows and may be {@code null} (see cef_sandbox_win.h for details).
     * <p>Definition generated from cef_app_capi.h
     * <pre>CEF_EXPORT int cef_execute_process(const cef_main_args_t* args, cef_app_t* application, void* windows_sandbox_info);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__app_8h.html">cef_app.h:50</a>
     */
    public static int executeProcess(@Nonnull CefMainArgs args, @Nullable CefApp application, @Nullable NativePointer windowsSandboxInfo) {
      return executeProcess0(args, application, windowsSandboxInfo);
  }

    /**
     * This function should be called on the main application thread to initialize the CEF browser process. The {@code application} parameter may be empty. Returns {@code true} if initialization succeeds. Returns {@code false} if initialization fails or if early exit is desired (for example, due to process singleton relaunch behavior). If this function returns {@code false} then the application should exit immediately without calling any other CEF functions except, optionally, CefGetExitCode. The {@code windows_sandbox_info} parameter is only used on Windows and may be {@code null} (see cef_sandbox_win.h for details).
     * <p>Definition generated from cef_app_capi.h
     * <pre>CEF_EXPORT int cef_initialize(const cef_main_args_t* args, const struct _cef_settings_t* settings, cef_app_t* application, void* windows_sandbox_info);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__app_8h.html">cef_app.h:68</a>
     */
    public static int initialize(@Nonnull CefMainArgs args, @Nonnull CefSettings settings, @Nullable CefApp application, @Nullable NativePointer windowsSandboxInfo) {
      return initialize0(args, settings, application, windowsSandboxInfo);
  }

    /**
     * This function can optionally be called on the main application thread after CefInitialize to retrieve the initialization exit code. When CefInitialize returns {@code true} the exit code will be 0 ({@link net.kurobako.cef4j.gen.CefResultcode.Kind#NORMAL_EXIT}). Otherwise, see cef_resultcode_t for possible exit code values including browser process initialization errors and normal early exit conditions (such as {@link net.kurobako.cef4j.gen.CefResultcode.Kind#NORMAL_EXIT_PROCESS_NOTIFIED} for process singleton relaunch behavior).
     * <p>Definition generated from cef_app_capi.h
     * <pre>CEF_EXPORT int cef_get_exit_code(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__app_8h.html">cef_app.h:85</a>
     */
    public static int getExitCode() {
      return getExitCode0();
  }

    /**
     * This function should be called on the main application thread to shut down the CEF browser process before the application exits. Do not call any other CEF functions after calling this function.
     * <p>Definition generated from cef_app_capi.h
     * <pre>CEF_EXPORT void cef_shutdown(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__app_8h.html">cef_app.h:97</a>
     */
    public static void shutdown() {
      shutdown0();
  }

    /**
     * Perform a single iteration of CEF message loop processing. This function is provided for cases where the CEF message loop must be integrated into an existing application message loop. Use of this function is not recommended for most users; use either the CefRunMessageLoop() function or cef_settings_t.multi_threaded_message_loop if possible. When using this function care must be taken to balance performance against excessive CPU usage. It is recommended to enable the cef_settings_t.external_message_pump option when using this function so that {@link net.kurobako.cef4j.gen.CefBrowserProcessHandler#onScheduleMessagePumpWork(long)} callbacks can facilitate the scheduling process. This function should only be called on the main application thread and only if CefInitialize() is called with a cef_settings_t.multi_threaded_message_loop value of {@code false}. This function will not block.
     * <p>Definition generated from cef_app_capi.h
     * <pre>CEF_EXPORT void cef_do_message_loop_work(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__app_8h.html">cef_app.h:105</a>
     */
    public static void doMessageLoopWork() {
      doMessageLoopWork0();
  }

    /**
     * Run the CEF message loop. Use this function instead of an application-provided message loop to get the best balance between performance and CPU usage. This function should only be called on the main application thread and only if CefInitialize() is called with a cef_settings_t.multi_threaded_message_loop value of {@code false}. This function will block until a quit message is received by the system.
     * <p>Definition generated from cef_app_capi.h
     * <pre>CEF_EXPORT void cef_run_message_loop(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__app_8h.html">cef_app.h:123</a>
     */
    public static void runMessageLoop() {
      runMessageLoop0();
  }

    /**
     * Quit the CEF message loop that was started by calling CefRunMessageLoop(). This function should only be called on the main application thread and only if CefRunMessageLoop() was used.
     * <p>Definition generated from cef_app_capi.h
     * <pre>CEF_EXPORT void cef_quit_message_loop(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__app_8h.html">cef_app.h:134</a>
     */
    public static void quitMessageLoop() {
      quitMessageLoop0();
  }

    /**
     * Set to {@code true} before calling OS APIs on the CEF UI thread that will enter a native message loop (see usage restrictions below). Set to {@code false} after exiting the native message loop. On Windows, use the CefSetOSModalLoop function instead in cases like native top menus where resize of the browser content is not required, or in cases like printer APIs where reentrancy safety cannot be guaranteed.
     * <p>
     * Nested processing of Chromium tasks is disabled by default because common controls and/or printer functions may use nested native message loops that lead to unplanned reentrancy. This function re-enables nested processing in the scope of an upcoming native message loop. It must only be used in cases where the stack is reentrancy safe and processing nestable tasks is explicitly safe. Do not use in cases (like the printer example) where an OS API may experience unplanned reentrancy as a result of a new task executing immediately.
     * <p>
     * For instance,
     * <ul>
     * <li>The UI thread is running a message loop.</li>
     * <li>It receives a task #1 and executes it.</li>
     * <li>The task #1 implicitly starts a nested message loop. For example, via</li>
     * </ul>
     * Windows APIs such as MessageBox or GetSaveFileName, or default handling of a user-initiated drag/resize operation (e.g. DefWindowProc handling of WM_SYSCOMMAND for SC_MOVE/SC_SIZE).
     * <ul>
     * <li>The UI thread receives a task #2 before or while in this second message</li>
     * </ul>
     * loop.
     * <ul>
     * <li>With NestableTasksAllowed set to {@code true}, the task #2 will run right away.</li>
     * </ul>
     * Otherwise, it will be executed right after task #1 completes at "thread message loop level".
     * <p>Definition generated from cef_app_capi.h
     * <pre>CEF_EXPORT void cef_set_nestable_tasks_allowed(int allowed);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__app_8h.html">cef_app.h:143</a>
     */
    public static void setNestableTasksAllowed(int allowed) {
      setNestableTasksAllowed0(allowed);
  }

    /**
     * Crash reporting is configured using an INI-style config file named "crash_reporter.cfg". On Windows and Linux this file must be placed next to the main application executable. On macOS this file must be placed in the top-level app bundle Resources directory (e.g. "&lt;appname&gt;.app/Contents/Resources"). File contents are as follows:
     * <pre>
     * # Comments start with a hash character and must be on their own line.
     * [Config] ProductName=&lt;Value of the "prod" crash key; defaults to "cef"&gt; ProductVersion=&lt;Value of the "ver" crash key; defaults to the CEF version&gt; AppName=&lt;Windows only; App-specific folder name component for storing crash information; default to "CEF"&gt; ExternalHandler=&lt;Windows only; Name of the external handler exe to use instead of re-launching the main exe; default to empty&gt; BrowserCrashForwardingEnabled=&lt;macOS only; True if browser process crashes should be forwarded to the system crash reporter; default to false&gt; ServerURL=&lt;crash server URL; default to empty&gt; RateLimitEnabled=&lt;True if uploads should be rate limited; default to true&gt; MaxUploadsPerDay=&lt;Max uploads per 24 hours, used if rate limit is enabled; default to 5&gt; MaxDatabaseSizeInMb=&lt;Total crash report disk usage greater than this value will cause older reports to be deleted; default to 20&gt; MaxDatabaseAgeInDays=&lt;Crash reports older than this value will be deleted; default to 5&gt;
     * [CrashKeys] my_key1=&lt;small|medium|large&gt; my_key2=&lt;small|medium|large&gt;
     * </pre>
     * <p>
     * &lt;b&gt;Config section:&lt;/b&gt;
     * <p>
     * If "ProductName" and/or "ProductVersion" are set then the specified values will be included in the crash dump metadata. On macOS if these values are set to empty then they will be retrieved from the Info.plist file using the "CFBundleName" and "CFBundleShortVersionString" keys respectively.
     * <p>
     * If "AppName" is set on Windows then crash report information (metrics, database and dumps) will be stored locally on disk under the "C:\Users\[CurrentUser]\AppData\Local\[AppName]\User Data" folder. On other platforms the cef_settings_t.root_cache_path value will be used.
     * <p>
     * If "ExternalHandler" is set on Windows then the specified exe will be launched as the crashpad-handler instead of re-launching the main process exe. The value can be an absolute path or a path relative to the main exe directory. On Linux the cef_settings_t.browser_subprocess_path value will be used. On macOS the existing subprocess app bundle will be used.
     * <p>
     * If "BrowserCrashForwardingEnabled" is set to {@code true} on macOS then browser process crashes will be forwarded to the system crash reporter. This results in the crash UI dialog being displayed to the user and crash reports being logged under "~/Library/Logs/DiagnosticReports". Forwarding of crash reports from non-browser processes and Debug builds is always disabled.
     * <p>
     * If "ServerURL" is set then crashes will be uploaded as a multi-part POST request to the specified URL. Otherwise, reports will only be stored locally on disk.
     * <p>
     * If "RateLimitEnabled" is set to {@code true} then crash report uploads will be rate limited as follows: 1. If "MaxUploadsPerDay" is set to a positive value then at most the specified number of crashes will be uploaded in each 24 hour period. 2. If crash upload fails due to a network or server error then an incremental backoff delay up to a maximum of 24 hours will be applied for retries. 3. If a backoff delay is applied and "MaxUploadsPerDay" is > 1 then the "MaxUploadsPerDay" value will be reduced to 1 until the client is restarted. This helps to avoid an upload flood when the network or server error is resolved. Rate limiting is not supported on Linux.
     * <p>
     * If "MaxDatabaseSizeInMb" is set to a positive value then crash report storage on disk will be limited to that size in megabytes. For example, on Windows each dump is about 600KB so a "MaxDatabaseSizeInMb" value of 20 equates to about 34 crash reports stored on disk. Not supported on Linux.
     * <p>
     * If "MaxDatabaseAgeInDays" is set to a positive value then crash reports older than the specified age in days will be deleted. Not supported on Linux.
     * <p>
     * &lt;b&gt;CrashKeys section:&lt;/b&gt;
     * <p>
     * A maximum of 26 crash keys of each size can be specified for use by the application. Crash key values will be truncated based on the specified size (small = 64 bytes, medium = 256 bytes, large = 1024 bytes). The value of crash keys can be set from any thread or process using the CefSetCrashKeyValue function. These key/value pairs will be sent to the crash server along with the crash dump file.
     * <p>Definition generated from cef_crash_util_capi.h
     * <pre>CEF_EXPORT int cef_crash_reporting_enabled(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__crash__util_8h.html">cef_crash_util.h:41</a>
     */
    public static int crashReportingEnabled() {
      return crashReportingEnabled0();
  }

    /**
     * Sets or clears a specific key-value pair from the crash metadata.
     * <p>Definition generated from cef_crash_util_capi.h
     * <pre>CEF_EXPORT void cef_set_crash_key_value(const cef_string_t* key, const cef_string_t* value);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__crash__util_8h.html">cef_crash_util.h:139</a>
     */
    public static void setCrashKeyValue(@Nullable String key, @Nullable String value) {
      setCrashKeyValue0(key, value);
  }

    /**
     * Creates a directory and all parent directories if they don't already exist. Returns {@code true} on successful creation or if the directory already exists. The directory is only readable by the current user. Calling this function on the browser process UI or IO threads is not allowed.
     * <p>Definition generated from cef_file_util_capi.h
     * <pre>CEF_EXPORT int cef_create_directory(const cef_string_t* full_path);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__file__util_8h.html">cef_file_util.h:44</a>
     */
    public static int createDirectory(@Nullable String fullPath) {
      return createDirectory0(fullPath);
  }

    /**
     * Get the temporary directory provided by the system.
     * <p>
     * <b>WARNING:</b> In general, you should use the temp directory variants below instead of this function. Those variants will ensure that the proper permissions are set so that other users on the system can't edit them while they're open (which could lead to security issues).
     * <p>Definition generated from cef_file_util_capi.h
     * <pre>CEF_EXPORT int cef_get_temp_directory(cef_string_t* temp_dir);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__file__util_8h.html">cef_file_util.h:53</a>
     */
    public static int getTempDirectory(@Nullable String tempDir) {
      return getTempDirectory0(tempDir);
  }

    /**
     * Creates a new directory. On Windows if {@code prefix} is provided the new directory name is in the format of "prefixyyyy". Returns {@code true} on success and sets {@code new_temp_path} to the full path of the directory that was created. The directory is only readable by the current user. Calling this function on the browser process UI or IO threads is not allowed.
     * <p>Definition generated from cef_file_util_capi.h
     * <pre>CEF_EXPORT int cef_create_new_temp_directory(const cef_string_t* prefix, cef_string_t* new_temp_path);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__file__util_8h.html">cef_file_util.h:64</a>
     */
    public static int createNewTempDirectory(@Nullable String prefix, @Nullable String newTempPath) {
      return createNewTempDirectory0(prefix, newTempPath);
  }

    /**
     * Creates a directory within another directory. Extra characters will be appended to {@code prefix} to ensure that the new directory does not have the same name as an existing directory. Returns {@code true} on success and sets {@code new_dir} to the full path of the directory that was created. The directory is only readable by the current user. Calling this function on the browser process UI or IO threads is not allowed.
     * <p>Definition generated from cef_file_util_capi.h
     * <pre>CEF_EXPORT int cef_create_temp_directory_in_directory(const cef_string_t* base_dir, const cef_string_t* prefix, cef_string_t* new_dir);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__file__util_8h.html">cef_file_util.h:75</a>
     */
    public static int createTempDirectoryInDirectory(@Nullable String baseDir, @Nullable String prefix, @Nullable String newDir) {
      return createTempDirectoryInDirectory0(baseDir, prefix, newDir);
  }

    /**
     * Returns {@code true} if the given path exists and is a directory. Calling this function on the browser process UI or IO threads is not allowed.
     * <p>Definition generated from cef_file_util_capi.h
     * <pre>CEF_EXPORT int cef_directory_exists(const cef_string_t* path);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__file__util_8h.html">cef_file_util.h:88</a>
     */
    public static int directoryExists(@Nullable String path) {
      return directoryExists0(path);
  }

    /**
     * Deletes the given path whether it's a file or a directory. If {@code path} is a directory all contents will be deleted.  If {@code recursive} is {@code true} any sub-directories and their contents will also be deleted (equivalent to executing "rm -rf", so use with caution). On POSIX environments if {@code path} is a symbolic link then only the symlink will be deleted. Returns {@code true} on successful deletion or if {@code path} does not exist. Calling this function on the browser process UI or IO threads is not allowed.
     * <p>Definition generated from cef_file_util_capi.h
     * <pre>CEF_EXPORT int cef_delete_file(const cef_string_t* path, int recursive);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__file__util_8h.html">cef_file_util.h:95</a>
     */
    public static int deleteFile(@Nullable String path, int recursive) {
      return deleteFile0(path, recursive);
  }

    /**
     * Writes the contents of {@code src_dir} into a zip archive at {@code dest_file}. If {@code include_hidden_files} is {@code true} files starting with "." will be included. Returns {@code true} on success.  Calling this function on the browser process UI or IO threads is not allowed.
     * <p>Definition generated from cef_file_util_capi.h
     * <pre>CEF_EXPORT int cef_zip_directory(const cef_string_t* src_dir, const cef_string_t* dest_file, int include_hidden_files);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__file__util_8h.html">cef_file_util.h:107</a>
     */
    public static int zipDirectory(@Nullable String srcDir, @Nullable String destFile, int includeHiddenFiles) {
      return zipDirectory0(srcDir, destFile, includeHiddenFiles);
  }

    public static void loadCrlsetsFile(@Nullable String path) {
      loadCrlsetsFile0(path);
  }

    public static int isRtl() {
      return isRtl0();
  }

    /**
     * Add an entry to the cross-origin access whitelist.
     * <p>
     * The same-origin policy restricts how scripts hosted from different origins (scheme + domain + port) can communicate. By default, scripts can only access resources with the same origin. Scripts hosted on the HTTP and HTTPS schemes (but no other schemes) can use the "Access-Control-Allow-Origin" header to allow cross-origin requests. For example, <a href="https://source.example.com">https://source.example.com</a> can make XMLHttpRequest requests on <a href="http://target.example.com">http://target.example.com</a> if the <a href="http://target.example.com">http://target.example.com</a> request returns an "Access-Control-Allow-Origin: <a href="https://source.example.com">https://source.example.com</a>" response header.
     * <p>
     * Scripts in separate frames or iframes and hosted from the same protocol and domain suffix can execute cross-origin JavaScript if both pages set the document.domain value to the same domain suffix. For example, scheme://foo.example.com and scheme://bar.example.com can communicate using JavaScript if both domains set document.domain="example.com".
     * <p>
     * This method is used to allow access to origins that would otherwise violate the same-origin policy. Scripts hosted underneath the fully qualified {@code source_origin} URL (like <a href="http://www.example.com">http://www.example.com</a>) will be allowed access to all resources hosted on the specified {@code target_protocol} and {@code target_domain}. If {@code target_domain} is non-empty and {@code allow_target_subdomains} is {@code false} only exact domain matches will be allowed. If {@code target_domain} contains a top-level domain component (like "example.com") and {@code allow_target_subdomains} is {@code true} sub-domain matches will be allowed. If {@code target_domain} is empty and {@code allow_target_subdomains} if {@code true} all domains and IP addresses will be allowed.
     * <p>
     * This method cannot be used to bypass the restrictions on local or display isolated schemes. See the comments on CefRegisterCustomScheme for more information.
     * <p>
     * This function may be called on any thread. Returns {@code false} if {@code source_origin} is invalid or the whitelist cannot be accessed.
     * <p>Definition generated from cef_origin_whitelist_capi.h
     * <pre>CEF_EXPORT int cef_add_cross_origin_whitelist_entry(const cef_string_t* source_origin, const cef_string_t* target_protocol, const cef_string_t* target_domain, int allow_target_subdomains);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__origin__whitelist_8h.html">cef_origin_whitelist.h:43</a>
     */
    public static int addCrossOriginWhitelistEntry(@Nullable String sourceOrigin, @Nullable String targetProtocol, @Nullable String targetDomain, int allowTargetSubdomains) {
      return addCrossOriginWhitelistEntry0(sourceOrigin, targetProtocol, targetDomain, allowTargetSubdomains);
  }

    /**
     * Remove an entry from the cross-origin access whitelist. Returns {@code false} if {@code source_origin} is invalid or the whitelist cannot be accessed.
     * <p>Definition generated from cef_origin_whitelist_capi.h
     * <pre>CEF_EXPORT int cef_remove_cross_origin_whitelist_entry(const cef_string_t* source_origin, const cef_string_t* target_protocol, const cef_string_t* target_domain, int allow_target_subdomains);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__origin__whitelist_8h.html">cef_origin_whitelist.h:86</a>
     */
    public static int removeCrossOriginWhitelistEntry(@Nullable String sourceOrigin, @Nullable String targetProtocol, @Nullable String targetDomain, int allowTargetSubdomains) {
      return removeCrossOriginWhitelistEntry0(sourceOrigin, targetProtocol, targetDomain, allowTargetSubdomains);
  }

    /**
     * Remove all entries from the cross-origin access whitelist. Returns {@code false} if the whitelist cannot be accessed.
     * <p>Definition generated from cef_origin_whitelist_capi.h
     * <pre>CEF_EXPORT int cef_clear_cross_origin_whitelist(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__origin__whitelist_8h.html">cef_origin_whitelist.h:96</a>
     */
    public static int clearCrossOriginWhitelist() {
      return clearCrossOriginWhitelist0();
  }

    public static int resolveUrl(@Nullable String baseUrl, @Nullable String relativeUrl, @Nullable String resolvedUrl) {
      return resolveUrl0(baseUrl, relativeUrl, resolvedUrl);
  }

    public static int parseUrl(@Nullable String url, @Nonnull CefUrlParts.Mutable parts) {
      return parseUrl0(url, parts);
  }

    public static int createUrl(@Nonnull CefUrlParts parts, @Nullable String url) {
      return createUrl0(parts, url);
  }

    /**
     * This is a convenience function for formatting a URL in a concise and human-friendly way to help users make security-related decisions (or in other circumstances when people need to distinguish sites, origins, or otherwise-simplified URLs from each other). Internationalized domain names (IDN) may be presented in Unicode if the conversion is considered safe. The returned value will (a) omit the path for standard schemes, excepting file and filesystem, and (b) omit the port if it is the default for the scheme. Do not use this for URLs which will be parsed or sent to other applications.
     * <p>Definition generated from cef_parser_capi.h
     * <pre>CEF_EXPORT cef_string_userfree_t cef_format_url_for_security_display(const cef_string_t* origin_url);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__parser_8h.html">cef_parser.h:70</a>
     */
    public static Optional<String> formatUrlForSecurityDisplay(@Nullable String originUrl) {
      return Optional.ofNullable(formatUrlForSecurityDisplay0(originUrl));
  }

    /**
     * Returns the mime type for the specified file extension or an empty string if unknown.
     * <p>Definition generated from cef_parser_capi.h
     * <pre>CEF_EXPORT cef_string_userfree_t cef_get_mime_type(const cef_string_t* extension);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__parser_8h.html">cef_parser.h:83</a>
     */
    public static Optional<String> getMimeType(@Nullable String extension) {
      return Optional.ofNullable(getMimeType0(extension));
  }

    /**
     * Get the extensions associated with the given mime type. This should be passed in lower case. There could be multiple extensions for a given mime type, like "html,htm" for "text/html", or "txt,text,html,..." for "text/*". Any existing elements in the provided vector will not be erased.
     * <p>Definition generated from cef_parser_capi.h
     * <pre>CEF_EXPORT void cef_get_extensions_for_mime_type(const cef_string_t* mime_type, cef_string_list_t extensions);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__parser_8h.html">cef_parser.h:90</a>
     */
    public static void getExtensionsForMimeType(@Nullable String mimeType, @Nonnull List<String> extensions) {
      getExtensionsForMimeType0(mimeType, extensions);
  }

    /**
     * Encodes {@code data} as a base64 string.
     * <p>Definition generated from cef_parser_capi.h
     * <pre>CEF_EXPORT cef_string_userfree_t cef_base64_encode(const void* data, size_t data_size);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__parser_8h.html">cef_parser.h:100</a>
     */
    public static Optional<String> base64Encode(@Nonnull ByteBuffer data) {
      return Optional.ofNullable(base64Encode0(data));
  }

    public static Optional<String> uriencode(@Nullable String text, int usePlus) {
      return Optional.ofNullable(uriencode0(text, usePlus));
  }

    public static Optional<String> uridecode(@Nullable String text, int convertToUtf8, @Nonnull CefUriUnescapeRule unescapeRule) {
      return Optional.ofNullable(uridecode0(text, convertToUtf8, unescapeRule));
  }

    public static Optional<String> writeJson(@Nullable CefValue node, @Nonnull CefJsonWriterOptions options) {
      return Optional.ofNullable(writeJson0(node, options));
  }

    /**
     * Retrieve the path associated with the specified {@code key}. Returns {@code true} on success. Can be called on any thread in the browser process.
     * <p>Definition generated from cef_path_util_capi.h
     * <pre>CEF_EXPORT int cef_get_path(cef_path_key_t key, cef_string_t* path);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__path__util_8h.html">cef_path_util.h:45</a>
     */
    public static int getPath(@Nonnull CefPathKey key, @Nullable String path) {
      return getPath0(key, path);
  }

    /**
     * Launches the process specified via {@code command_line}. Returns {@code true} upon success. Must be called on the browser process {@code TID_PROCESS_LAUNCHER} thread.
     * <p>
     * Unix-specific notes:
     * <ul>
     * <li>All file descriptors open in the parent process will be closed in the</li>
     * </ul>
     * child process except for stdin, stdout, and stderr.
     * <ul>
     * <li>If the first argument on the command line does not contain a slash,</li>
     * </ul>
     * PATH will be searched. (See man execvp.)
     * <p>Definition generated from cef_process_util_capi.h
     * <pre>CEF_EXPORT int cef_launch_process(struct _cef_command_line_t* command_line);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__process__util_8h.html">cef_process_util.h:44</a>
     */
    public static int launchProcess(@Nullable CefCommandLine commandLine) {
      return launchProcess0(commandLine);
  }

    /**
     * Register a scheme handler factory with the global request context. An empty {@code domain_name} value for a standard scheme will cause the factory to match all domain names. The {@code domain_name} value will be ignored for non-standard schemes. If {@code scheme_name} is a built-in scheme and no handler is returned by {@code factory} then the built-in scheme handler factory will be called. If {@code scheme_name} is a custom scheme then you must also implement the {@link net.kurobako.cef4j.gen.CefApp#onRegisterCustomSchemes(CefSchemeRegistrar)} method in all processes. This function may be called multiple times to change or remove the factory that matches the specified {@code scheme_name} and optional {@code domain_name}. Returns {@code false} if an error occurs. This function may be called on any thread in the browser process. Using this function is equivalent to calling net.kurobako.cef4j.gen.CefRequestContext.getGlobalContext()->RegisterSchemeHandlerFactory().
     * <p>Definition generated from cef_scheme_capi.h
     * <pre>CEF_EXPORT int cef_register_scheme_handler_factory(const cef_string_t* scheme_name, const cef_string_t* domain_name, cef_scheme_handler_factory_t* factory);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__scheme_8h.html">cef_scheme.h:50</a>
     */
    public static boolean registerSchemeHandlerFactory(@Nullable String schemeName, @Nullable String domainName, @Nullable CefSchemeHandlerFactory factory) {
      return registerSchemeHandlerFactory0(schemeName, domainName, factory);
  }

    /**
     * Clear all scheme handler factories registered with the global request context. Returns {@code false} on error. This function may be called on any thread in the browser process. Using this function is equivalent to calling net.kurobako.cef4j.gen.CefRequestContext.getGlobalContext()->ClearSchemeHandlerFactories().
     * <p>Definition generated from cef_scheme_capi.h
     * <pre>CEF_EXPORT int cef_clear_scheme_handler_factories(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__scheme_8h.html">cef_scheme.h:70</a>
     */
    public static boolean clearSchemeHandlerFactories() {
      return clearSchemeHandlerFactories0();
  }

    /**
     * Returns {@code true} if the certificate status represents an error.
     * <p>Definition generated from cef_ssl_info_capi.h
     * <pre>CEF_EXPORT int cef_is_cert_status_error(cef_cert_status_t status);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__ssl__info_8h.html">cef_ssl_info.h:65</a>
     */
    public static int isCertStatusError(@Nonnull CefCertStatus status) {
      return isCertStatusError0(status);
  }

    /**
     * Returns {@code true} if called on the specified thread. Equivalent to using net.kurobako.cef4j.gen.CefTaskRunner.getForThread()(threadId)->BelongsToCurrentThread().
     * <p>Definition generated from cef_task_capi.h
     * <pre>CEF_EXPORT int cef_currently_on(cef_thread_id_t threadId);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__task_8h.html">cef_task.h:124</a>
     */
    public static int currentlyOn(@Nonnull CefThreadId threadId) {
      return currentlyOn0(threadId);
  }

    /**
     * Post a task for execution on the specified thread. Equivalent to using net.kurobako.cef4j.gen.CefTaskRunner.getForThread()(threadId)->PostTask(task).
     * <p>Definition generated from cef_task_capi.h
     * <pre>CEF_EXPORT int cef_post_task(cef_thread_id_t threadId, cef_task_t* task);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__task_8h.html">cef_task.h:131</a>
     */
    public static boolean postTask(@Nonnull CefThreadId threadId, @Nullable CefTask task) {
      return postTask0(threadId, task);
  }

    /**
     * Post a task for delayed execution on the specified thread. Equivalent to using net.kurobako.cef4j.gen.CefTaskRunner.getForThread()(threadId)->PostDelayedTask(task, delay_ms).
     * <p>Definition generated from cef_task_capi.h
     * <pre>CEF_EXPORT int cef_post_delayed_task(cef_thread_id_t threadId, cef_task_t* task, int64_t delay_ms);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__task_8h.html">cef_task.h:138</a>
     */
    public static boolean postDelayedTask(@Nonnull CefThreadId threadId, @Nullable CefTask task, long delayMs) {
      return postDelayedTask0(threadId, task, delayMs);
  }

    /**
     * Start tracing events on all processes. Tracing is initialized asynchronously and {@code callback} will be executed on the UI thread after initialization is complete.
     * <p>
     * If CefBeginTracing was called previously, or if a CefEndTracingAsync call is pending, CefBeginTracing will fail and return {@code false}.
     * <p>
     * {@code categories} is a comma-delimited list of category wildcards. A category can have an optional '-' prefix to make it an excluded category. Having both included and excluded categories in the same list is not supported.
     * <p>
     * Examples:
     * <ul>
     * <li>"test_MyTest*"</li>
     * <li>"test_MyTest*,test_OtherStuff"</li>
     * <li>"-excluded_category1,-excluded_category2"</li>
     * </ul>
     * <p>
     * This function must be called on the browser process UI thread.
     * <p>Definition generated from cef_trace_capi.h
     * <pre>CEF_EXPORT int cef_begin_tracing(const cef_string_t* categories, struct _cef_completion_callback_t* callback);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__trace_8h.html">cef_trace.h:63</a>
     */
    public static int beginTracing(@Nullable String categories, @Nullable CefCompletionCallback callback) {
      return beginTracing0(categories, callback);
  }

    /**
     * Stop tracing events on all processes.
     * <p>
     * This function will fail and return {@code false} if a previous call to CefEndTracingAsync is already pending or if CefBeginTracing was not called.
     * <p>
     * {@code tracing_file} is the path at which tracing data will be written and {@code callback} is the callback that will be executed once all processes have sent their trace data. If {@code tracing_file} is empty a new temporary file path will be used. If {@code callback} is empty no trace data will be written.
     * <p>
     * This function must be called on the browser process UI thread.
     * <p>Definition generated from cef_trace_capi.h
     * <pre>CEF_EXPORT int cef_end_tracing(const cef_string_t* tracing_file, cef_end_tracing_callback_t* callback);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__trace_8h.html">cef_trace.h:86</a>
     */
    public static int endTracing(@Nullable String tracingFile, @Nullable CefEndTracingCallback callback) {
      return endTracing0(tracingFile, callback);
  }

    /**
     * Returns the current system trace time or, if none is defined, the current high-res time. Can be used by clients to synchronize with the time information in trace events.
     * <p>Definition generated from cef_trace_capi.h
     * <pre>CEF_EXPORT int64_t cef_now_from_system_trace_time(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__trace_8h.html">cef_trace.h:103</a>
     */
    public static long nowFromSystemTraceTime() {
      return nowFromSystemTraceTime0();
  }

    /**
     * Register a new V8 extension with the specified JavaScript extension code and handler. Functions implemented by the handler are prototyped using the keyword 'native'. The calling of a native function is restricted to the scope in which the prototype of the native function is defined. This function may only be called on the render process main thread.
     * <p>
     * Example JavaScript extension code:
     * <pre>
     * // create the 'example' global object if it doesn't already exist. if (!example) example = {}; // create the 'example.test' global object if it doesn't already exist. if (!example.test) example.test = {}; (function() { // Define the function 'example.test.myfunction'. example.test.myfunction = function() { // Call CefV8Handler::Execute() with the function name 'MyFunction' // and no arguments. native function MyFunction(); return MyFunction(); }; // Define the getter function for parameter 'example.test.myparam'. example.test.__defineGetter__('myparam', function() { // Call CefV8Handler::Execute() with the function name 'GetMyParam' // and no arguments. native function GetMyParam(); return GetMyParam(); }); // Define the setter function for parameter 'example.test.myparam'. example.test.__defineSetter__('myparam', function(b) { // Call CefV8Handler::Execute() with the function name 'SetMyParam' // and a single argument. native function SetMyParam(); if(b) SetMyParam(b); });
     * // Extension definitions can also contain normal JavaScript variables // and functions. var myint = 0; example.test.increment = function() { myint += 1; return myint; }; })();
     * </pre>
     * <p>
     * Example usage in the page:
     * <pre>
     * // Call the function. example.test.myfunction(); // Set the parameter. example.test.myparam = value; // Get the parameter. value = example.test.myparam; // Call another function. example.test.increment();
     * </pre>
     * <p>Definition generated from cef_v8_capi.h
     * <pre>CEF_EXPORT int cef_register_extension(const cef_string_t* extension_name, const cef_string_t* javascript_code, cef_v8_handler_t* handler);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:54</a>
     */
    public static int registerExtension(@Nullable String extensionName, @Nullable String javascriptCode, @Nullable CefV8Handler handler) {
      return registerExtension0(extensionName, javascriptCode, handler);
  }

    private static native int executeProcess0(@Nonnull CefMainArgs args, @Nullable CefApp application, @Nullable NativePointer windowsSandboxInfo);

    private static native int initialize0(@Nonnull CefMainArgs args, @Nonnull CefSettings settings, @Nullable CefApp application, @Nullable NativePointer windowsSandboxInfo);

    private static native int getExitCode0();

    private static native void shutdown0();

    private static native void doMessageLoopWork0();

    private static native void runMessageLoop0();

    private static native void quitMessageLoop0();

    private static native void setNestableTasksAllowed0(int allowed);

    private static native int crashReportingEnabled0();

    private static native void setCrashKeyValue0(@Nullable String key, @Nullable String value);

    private static native int createDirectory0(@Nullable String fullPath);

    private static native int getTempDirectory0(@Nullable String tempDir);

    private static native int createNewTempDirectory0(@Nullable String prefix, @Nullable String newTempPath);

    private static native int createTempDirectoryInDirectory0(@Nullable String baseDir, @Nullable String prefix, @Nullable String newDir);

    private static native int directoryExists0(@Nullable String path);

    private static native int deleteFile0(@Nullable String path, int recursive);

    private static native int zipDirectory0(@Nullable String srcDir, @Nullable String destFile, int includeHiddenFiles);

    private static native void loadCrlsetsFile0(@Nullable String path);

    private static native int isRtl0();

    private static native int addCrossOriginWhitelistEntry0(@Nullable String sourceOrigin, @Nullable String targetProtocol, @Nullable String targetDomain, int allowTargetSubdomains);

    private static native int removeCrossOriginWhitelistEntry0(@Nullable String sourceOrigin, @Nullable String targetProtocol, @Nullable String targetDomain, int allowTargetSubdomains);

    private static native int clearCrossOriginWhitelist0();

    private static native int resolveUrl0(@Nullable String baseUrl, @Nullable String relativeUrl, @Nullable String resolvedUrl);

    private static native int parseUrl0(@Nullable String url, @Nonnull CefUrlParts.Mutable parts);

    private static native int createUrl0(@Nonnull CefUrlParts parts, @Nullable String url);

    private static native String formatUrlForSecurityDisplay0(@Nullable String originUrl);

    private static native String getMimeType0(@Nullable String extension);

    private static native void getExtensionsForMimeType0(@Nullable String mimeType, @Nonnull List<String> extensions);

    private static native String base64Encode0(@Nonnull ByteBuffer data);

    private static native String uriencode0(@Nullable String text, int usePlus);

    private static native String uridecode0(@Nullable String text, int convertToUtf8, @Nonnull CefUriUnescapeRule unescapeRule);

    private static native String writeJson0(@Nullable CefValue node, @Nonnull CefJsonWriterOptions options);

    private static native int getPath0(@Nonnull CefPathKey key, @Nullable String path);

    private static native int launchProcess0(@Nullable CefCommandLine commandLine);

    private static native boolean registerSchemeHandlerFactory0(@Nullable String schemeName, @Nullable String domainName, @Nullable CefSchemeHandlerFactory factory);

    private static native boolean clearSchemeHandlerFactories0();

    private static native int isCertStatusError0(@Nonnull CefCertStatus status);

    private static native int currentlyOn0(@Nonnull CefThreadId threadId);

    private static native boolean postTask0(@Nonnull CefThreadId threadId, @Nullable CefTask task);

    private static native boolean postDelayedTask0(@Nonnull CefThreadId threadId, @Nullable CefTask task, long delayMs);

    private static native int beginTracing0(@Nullable String categories, @Nullable CefCompletionCallback callback);

    private static native int endTracing0(@Nullable String tracingFile, @Nullable CefEndTracingCallback callback);

    private static native long nowFromSystemTraceTime0();

    private static native int registerExtension0(@Nullable String extensionName, @Nullable String javascriptCode, @Nullable CefV8Handler handler);
}
