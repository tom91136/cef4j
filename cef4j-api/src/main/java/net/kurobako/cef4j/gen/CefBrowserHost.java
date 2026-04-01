// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Class used to represent the browser process aspects of a browser. The methods of this class can only be called in the
 * browser process. They may be called on any thread in that process unless otherwise indicated in the comments.
 *
 * <p>Definition generated from cef_browser_capi.h
 *
 * <pre>typedef struct _cef_browser_host_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_browser_host_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:275</a>
 */
public interface CefBrowserHost extends CefLibraryObject {

    /**
     * Returns the browser for this context. This method will return an empty reference for WebWorker contexts.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>cef_browser_t* (CEF_CALLBACK* get_browser)(struct _cef_browser_host_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:163</a>
     */
    Optional<CefBrowser> getBrowser();

    /**
     * Request that the browser close. Closing a browser is a multi-stage process that may complete either synchronously
     * or asynchronously, and involves callbacks such as {@link CefLifeSpanHandler#doClose(CefBrowser)} (Alloy style
     * only), {@link CefLifeSpanHandler#onBeforeClose(CefBrowser)}, and a top-level window close handler such as
     * CefWindowDelegate.canClose() (or platform-specific equivalent). In some cases a close request may be delayed or
     * canceled by the user. Using TryCloseBrowser() instead of CloseBrowser() is recommended for most use cases. See
     * {@link CefLifeSpanHandler#doClose(CefBrowser)} documentation for detailed usage and examples.
     *
     * <p>If {@code force_close} is {@code false} then JavaScript unload handlers, if any, may be fired and the close
     * may be delayed or canceled by the user. If {@code force_close} is {@code true} then the user will not be prompted
     * and the close will proceed immediately (possibly asynchronously). If browser close is delayed and not canceled
     * the default behavior is to call the top-level window close handler once the browser is ready to be closed. This
     * default behavior can be changed for Alloy style browsers by implementing
     * {@link CefLifeSpanHandler#doClose(CefBrowser)}. IsReadyToBeClosed() can be used to detect mandatory browser close
     * events when customizing close behavior on the browser process UI thread.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>void (CEF_CALLBACK* close_browser)(struct _cef_browser_host_t* self, int force_close);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:339</a>
     */
    void closeBrowser(boolean forceClose);

    /**
     * Helper for closing a browser. This is similar in behavior to CLoseBrowser({@code false}) but returns a boolean to
     * reflect the immediate close status. Call this method from a top-level window close handler such as
     * CefWindowDelegate.canClose() (or platform-specific equivalent) to request that the browser close, and return the
     * result to indicate if the window close should proceed. Returns {@code false} if the close will be delayed
     * (JavaScript unload handlers triggered but still pending) or {@code true} if the close will proceed immediately
     * (possibly asynchronously). See CloseBrowser() documentation for additional usage information. This method must be
     * called on the browser process UI thread.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>int (CEF_CALLBACK* try_close_browser)(struct _cef_browser_host_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:364</a>
     */
    boolean tryCloseBrowser();

    /**
     * Returns {@code true} if the browser is ready to be closed, meaning that the close has already been initiated and
     * that JavaScript unload handlers have already executed or should be ignored. This can be used from a top-level
     * window close handler such as CefWindowDelegate.canClose() (or platform-specific equivalent) to distringuish
     * between potentially cancelable browser close events (like the user clicking the top-level window close button
     * before browser close has started) and mandatory browser close events (like JavaScript `window.close()` or after
     * browser close has started in response to [Try]CloseBrowser()). Not completing the browser close for mandatory
     * close events (when this method returns {@code true}) will leave the browser in a partially closed state that
     * interferes with proper functioning. See CloseBrowser() documentation for additional usage information. This
     * method must be called on the browser process UI thread.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_ready_to_be_closed)(struct _cef_browser_host_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:379</a>
     */
    boolean isReadyToBeClosed();

    /**
     * Set whether the browser is focused.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_focus)(struct _cef_browser_host_t* self, int focus);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:397</a>
     */
    void setFocus(boolean focus);

    /**
     * Retrieve the window handle (if any) for this browser. If this browser is wrapped in a CefBrowserView this method
     * should be called on the browser process UI thread and it will return the handle for the top-level native window.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>int64_t (CEF_CALLBACK* get_window_handle)(struct _cef_browser_host_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:403</a>
     */
    long getWindowHandle();

    /**
     * Retrieve the window handle (if any) of the browser that opened this browser. Will return {@code null} for
     * non-popup browsers or if this browser is wrapped in a CefBrowserView. This method can be used in combination with
     * custom handling of modal windows.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>int64_t (CEF_CALLBACK* get_opener_window_handle)(struct _cef_browser_host_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:412</a>
     */
    long getOpenerWindowHandle();

    /**
     * Retrieve the unique identifier of the browser that opened this browser. Will return 0 for non-popup browsers.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_opener_identifier)(struct _cef_browser_host_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:421</a>
     */
    int getOpenerIdentifier();

    /**
     * Returns {@code true} if this browser is wrapped in a CefBrowserView.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>int (CEF_CALLBACK* has_view)(struct _cef_browser_host_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:428</a>
     */
    boolean hasView();

    /**
     * Returns the client.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>cef_client_t* (CEF_CALLBACK* get_client)(struct _cef_browser_host_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__urlrequest_8h.html">cef_urlrequest.h:90</a>
     */
    Optional<CefClient> getClient();

    /**
     * Returns the request context for this browser.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>cef_request_context_t* (CEF_CALLBACK* get_request_context)(struct _cef_browser_host_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:440</a>
     */
    Optional<CefRequestContext> getRequestContext();

    /**
     * Returns {@code true} if this browser can execute the specified zoom command. This method can only be called on
     * the UI thread.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>int (CEF_CALLBACK* can_zoom)(struct _cef_browser_host_t* self, cef_zoom_command_t command);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:446</a>
     */
    boolean canZoom(@Nonnull CefZoomCommand command);

    /**
     * Execute a zoom command in this browser. If called on the UI thread the change will be applied immediately.
     * Otherwise, the change will be applied asynchronously on the UI thread.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>void (CEF_CALLBACK* zoom)(struct _cef_browser_host_t* self, cef_zoom_command_t command);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:453</a>
     */
    void zoom(@Nonnull CefZoomCommand command);

    /**
     * Get the default zoom level. This value will be 0.0 by default but can be configured. This method can only be
     * called on the UI thread.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>double (CEF_CALLBACK* get_default_zoom_level)(struct _cef_browser_host_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:461</a>
     */
    double getDefaultZoomLevel();

    /**
     * Get the current zoom level. This method can only be called on the UI thread.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>double (CEF_CALLBACK* get_zoom_level)(struct _cef_browser_host_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:468</a>
     */
    double getZoomLevel();

    /**
     * Change the zoom level to the specified value. Specify 0.0 to reset the zoom level to the default. If called on
     * the UI thread the change will be applied immediately. Otherwise, the change will be applied asynchronously on the
     * UI thread.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_zoom_level)(struct _cef_browser_host_t* self, double zoomLevel);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:475</a>
     */
    void setZoomLevel(double zoomlevel);

    /**
     * Call to run a file chooser dialog. Only a single file chooser dialog may be pending at any given time.
     * {@code mode} represents the type of dialog to display. {@code title} to the title to be used for the dialog and
     * may be empty to show the default title ("Open" or "Save" depending on the mode). {@code default_file_path} is the
     * path with optional directory and/or file name component that will be initially selected in the dialog.
     * {@code accept_filters} are used to restrict the selectable file types and may any combination of (a) valid
     * lower-cased MIME types (e.g. "text/*" or "image/*"), (b) individual file extensions (e.g. ".txt" or ".png"), or
     * (c) combined description and file extension delimited using "{@code " and ";" (e.g. "Image
     * Types}.png;.gif;.jpg"). {@code callback} will be executed after the dialog is dismissed or immediately if another
     * dialog is already pending. The dialog will be initiated asynchronously on the UI thread.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* run_file_dialog)(struct _cef_browser_host_t* self, cef_file_dialog_mode_t mode, const cef_string_t* title, const cef_string_t* default_file_path, cef_string_list_t accept_filters, struct _cef_run_file_dialog_callback_t* callback);
     * </pre>
     *
     * @param title may be null
     * @param defaultFilePath may be null
     * @param acceptFilters may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:484</a>
     */
    void runFileDialog(
            @Nonnull CefFileDialogMode mode,
            @Nullable String title,
            @Nullable String defaultFilePath,
            @Nullable List<String> acceptFilters,
            @Nonnull CefRunFileDialogCallback callback);

    /**
     * Download the file at {@code url} using CefDownloadHandler.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>void (CEF_CALLBACK* start_download)(struct _cef_browser_host_t* self, const cef_string_t* url);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:508</a>
     */
    void startDownload(@Nonnull String url);

    /**
     * Download {@code image_url} and execute {@code callback} on completion with the images received from the renderer.
     * If {@code is_favicon} is {@code true} then cookies are not sent and not accepted during download. Images with
     * density independent pixel (DIP) sizes larger than {@code max_image_size} are filtered out from the image results.
     * Versions of the image at different scale factors may be downloaded up to the maximum scale factor supported by
     * the system. If there are no image results <= {@code max_image_size} then the smallest image is resized to
     * {@code max_image_size} and is the only result. A {@code max_image_size} of 0 means unlimited. If
     * {@code bypass_cache} is {@code true} then {@code image_url} is requested from the server even if it is present in
     * the browser cache.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* download_image)(struct _cef_browser_host_t* self, const cef_string_t* image_url, int is_favicon, uint32_t max_image_size, int bypass_cache, struct _cef_download_image_callback_t* callback);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:514</a>
     */
    void downloadImage(
            @Nonnull String imageUrl,
            boolean isFavicon,
            int maxImageSize,
            boolean bypassCache,
            @Nonnull CefDownloadImageCallback callback);

    /**
     * Print the current browser contents.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>void (CEF_CALLBACK* print)(struct _cef_browser_host_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:533</a>
     */
    void print();

    /**
     * Print the current browser contents to the PDF file specified by {@code path} and execute {@code callback} on
     * completion. The caller is responsible for deleting {@code path} when done. For PDF printing to work on Linux you
     * must implement the {@link CefPrintHandler#getPdfPaperSize(CefBrowser, int)} method.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* print_to_pdf)(struct _cef_browser_host_t* self, const cef_string_t* path, const struct _cef_pdf_print_settings_t* settings, struct _cef_pdf_print_callback_t* callback);
     * </pre>
     *
     * @param callback may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:539</a>
     */
    void printToPdf(
            @Nonnull String path, @Nonnull CefPdfPrintSettings settings, @Nullable CefPdfPrintCallback callback);

    /**
     * Search for {@code searchText}. {@code forward} indicates whether to search forward or backward within the page.
     * {@code matchCase} indicates whether the search should be case-sensitive. {@code findNext} indicates whether this
     * is the first request or a follow-up. The search will be restarted if {@code searchText} or {@code matchCase}
     * change. The search will be stopped if {@code searchText} is empty. The CefFindHandler instance, if any, returned
     * via {@link CefClient#getFindHandler()} will be called to report find results.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* find)(struct _cef_browser_host_t* self, const cef_string_t* searchText, int forward, int matchCase, int findNext);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:550</a>
     */
    void find(@Nonnull String searchtext, boolean forward, boolean matchcase, boolean findnext);

    /**
     * Cancel all searches that are currently going on.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>void (CEF_CALLBACK* stop_finding)(struct _cef_browser_host_t* self, int clearSelection);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:565</a>
     */
    void stopFinding(boolean clearselection);

    /**
     * Open developer tools (DevTools) in its own browser. The DevTools browser will remain associated with this
     * browser. If the DevTools browser is already open then it will be focused, in which case the {@code windowInfo},
     * {@code client} and {@code settings} parameters will be ignored. If {@code inspect_element_at} is non-empty then
     * the element at the specified (x,y) location will be inspected. The {@code windowInfo} parameter will be ignored
     * if this browser is wrapped in a CefBrowserView.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* show_dev_tools)(struct _cef_browser_host_t* self, const struct _cef_window_info_t* windowInfo, struct _cef_client_t* client, const struct _cef_browser_settings_t* settings, const cef_point_t* inspect_element_at);
     * </pre>
     *
     * @param windowinfo may be null
     * @param client may be null
     * @param settings may be null
     * @param inspectElementAt may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:571</a>
     */
    void showDevTools(
            @Nullable CefWindowInfo windowinfo,
            @Nullable CefClient client,
            @Nullable CefBrowserSettings settings,
            @Nullable CefPoint inspectElementAt);

    /**
     * Explicitly close the associated DevTools browser, if any.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>void (CEF_CALLBACK* close_dev_tools)(struct _cef_browser_host_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:587</a>
     */
    void closeDevTools();

    /**
     * Returns {@code true} if this browser currently has an associated DevTools browser. Must be called on the browser
     * process UI thread.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>int (CEF_CALLBACK* has_dev_tools)(struct _cef_browser_host_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:593</a>
     */
    boolean hasDevTools();

    /**
     * Send a method call message over the DevTools protocol. {@code message} must be a UTF8-encoded JSON dictionary
     * that contains "id" (int), "method" (string) and "params" (dictionary, optional) values. See the DevTools protocol
     * documentation at <a
     * href="https://chromedevtools.github.io/devtools-protocol/">https://chromedevtools.github.io/devtools-protocol/</a>
     * for details of supported methods and the expected "params" dictionary contents. {@code message} will be copied if
     * necessary. This method will return {@code true} if called on the UI thread and the message was successfully
     * submitted for validation, otherwise {@code false}. Validation will be applied asynchronously and any messages
     * that fail due to formatting errors or missing parameters may be discarded without notification. Prefer
     * ExecuteDevToolsMethod if a more structured approach to message formatting is desired.
     *
     * <p>Every valid method call will result in an asynchronous method result or error message that references the sent
     * message "id". Event messages are received while notifications are enabled (for example, between method calls for
     * "Page.enable" and "Page.disable"). All received messages will be delivered to the observer(s) registered with
     * AddDevToolsMessageObserver. See {@link CefDevToolsMessageObserver#onDevToolsMessage(CefBrowser, ByteBuffer)}
     * documentation for details of received message contents.
     *
     * <p>Usage of the SendDevToolsMessage, ExecuteDevToolsMethod and AddDevToolsMessageObserver methods does not
     * require an active DevTools front-end or remote-debugging session. Other active DevTools sessions will continue to
     * function independently. However, any modification of global browser state by one session may not be reflected in
     * the UI of other sessions.
     *
     * <p>Communication with the DevTools front-end (when displayed) can be logged for development purposes by passing
     * the `--devtools-protocol-log-file=<path>` command-line flag.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* send_dev_tools_message)(struct _cef_browser_host_t* self, const void* message, size_t message_size);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:600</a>
     */
    boolean sendDevToolsMessage(@Nonnull ByteBuffer message);

    /**
     * Execute a method call over the DevTools protocol. This is a more structured version of SendDevToolsMessage.
     * {@code message_id} is an incremental number that uniquely identifies the message (pass 0 to have the next number
     * assigned automatically based on previous values). {@code method} is the method name. {@code params} are the
     * method parameters, which may be empty. See the DevTools protocol documentation (linked above) for details of
     * supported methods and the expected {@code params} dictionary contents. This method will return the assigned
     * message ID if called on the UI thread and the message was successfully submitted for validation, otherwise 0. See
     * the SendDevToolsMessage documentation for additional usage information.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* execute_dev_tools_method)(struct _cef_browser_host_t* self, int message_id, const cef_string_t* method, struct _cef_dictionary_value_t* params);
     * </pre>
     *
     * @param params may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:636</a>
     */
    int executeDevToolsMethod(int messageId, @Nonnull String method, @Nullable CefDictionaryValue params);

    /**
     * Add an observer for DevTools protocol messages (method results and events). The observer will remain registered
     * until the returned Registration object is destroyed. See the SendDevToolsMessage documentation for additional
     * usage information.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>
     * cef_registration_t* (CEF_CALLBACK* add_dev_tools_message_observer)(struct _cef_browser_host_t* self, struct _cef_dev_tools_message_observer_t* observer);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:653</a>
     */
    Optional<CefRegistration> addDevToolsMessageObserver(@Nonnull CefDevToolsMessageObserver observer);

    /**
     * Retrieve a snapshot of current navigation entries as values sent to the specified visitor. If
     * {@code current_only} is {@code true} only the current navigation entry will be sent, otherwise all navigation
     * entries will be sent.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* get_navigation_entries)(struct _cef_browser_host_t* self, struct _cef_navigation_entry_visitor_t* visitor, int current_only);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:663</a>
     */
    void getNavigationEntries(@Nonnull CefNavigationEntryVisitor visitor, boolean currentOnly);

    /**
     * If a misspelled word is currently selected in an editable node calling this method will replace it with the
     * specified {@code word}.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>void (CEF_CALLBACK* replace_misspelling)(struct _cef_browser_host_t* self, const cef_string_t* word);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:673</a>
     */
    void replaceMisspelling(@Nonnull String word);

    /**
     * Add the specified {@code word} to the spelling dictionary.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>void (CEF_CALLBACK* add_word_to_dictionary)(struct _cef_browser_host_t* self, const cef_string_t* word);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:680</a>
     */
    void addWordToDictionary(@Nonnull String word);

    /**
     * Returns {@code true} if window rendering is disabled.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_window_rendering_disabled)(struct _cef_browser_host_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:686</a>
     */
    boolean isWindowRenderingDisabled();

    /**
     * Notify the browser that the widget has been resized. The browser will first call
     * {@link CefRenderHandler#getViewRect(CefBrowser, CefMutableRect)} to get the new size and then call
     * {@link CefRenderHandler#onPaint(CefBrowser, CefPaintElementType, long, CefRect[], ByteBuffer, int, int)}
     * asynchronously with the updated regions. This method is only used when window rendering is disabled.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>void (CEF_CALLBACK* was_resized)(struct _cef_browser_host_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:692</a>
     */
    void wasResized();

    /**
     * Notify the browser that it has been hidden or shown. Layouting and {@link CefRenderHandler#onPaint(CefBrowser,
     * CefPaintElementType, long, CefRect[], ByteBuffer, int, int)} notification will stop when the browser is hidden.
     * This method is only used when window rendering is disabled.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>void (CEF_CALLBACK* was_hidden)(struct _cef_browser_host_t* self, int hidden);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:701</a>
     */
    void wasHidden(boolean hidden);

    /**
     * Notify the browser that screen information has changed. Updated information will be sent to the renderer process
     * to configure screen size and position values used by CSS and JavaScript (window.deviceScaleFactor,
     * window.screenX/Y, window.outerWidth/Height, etc.). For background see <a
     * href="https://chromiumembedded.github.io/cef/general_usage#coordinate-systems">https://chromiumembedded.github.io/cef/general_usage#coordinate-systems</a>
     *
     * <p>This method is used with (a) windowless rendering and (b) windowed rendering with external (client-provided)
     * root window.
     *
     * <p>With windowless rendering the browser will call {@link CefRenderHandler#getScreenInfo(CefBrowser,
     * CefMutableScreenInfo)}, {@link CefRenderHandler#getRootScreenRect(CefBrowser, CefMutableRect)} and
     * {@link CefRenderHandler#getViewRect(CefBrowser, CefMutableRect)}. This simulates moving or resizing the root
     * window in the current display, moving the root window from one display to another, or changing the properties of
     * the current display.
     *
     * <p>With windowed rendering the browser will call {@link CefDisplayHandler#getRootWindowScreenRect(CefBrowser,
     * CefMutableRect)} and use the associated display properties.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>void (CEF_CALLBACK* notify_screen_info_changed)(struct _cef_browser_host_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:709</a>
     */
    void notifyScreenInfoChanged();

    /**
     * Invalidate the view. The browser will call {@link CefRenderHandler#onPaint(CefBrowser, CefPaintElementType, long,
     * CefRect[], ByteBuffer, int, int)} asynchronously. This method is only used when window rendering is disabled.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>void (CEF_CALLBACK* invalidate)(struct _cef_browser_host_t* self, cef_paint_element_type_t type);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:732</a>
     */
    void invalidate(@Nonnull CefPaintElementType type);

    /**
     * Issue a BeginFrame request to Chromium. Only valid when CefWindowInfo.externalBeginFrameEnabled() is set to
     * {@code true}.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>void (CEF_CALLBACK* send_external_begin_frame)(struct _cef_browser_host_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:740</a>
     */
    void sendExternalBeginFrame();

    /**
     * Send a key event to the browser.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>void (CEF_CALLBACK* send_key_event)(struct _cef_browser_host_t* self, const cef_key_event_t* event);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:747</a>
     */
    void sendKeyEvent(@Nonnull CefKeyEvent event);

    /**
     * Send a mouse click event to the browser. The {@code x} and {@code y} coordinates are relative to the upper-left
     * corner of the view.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* send_mouse_click_event)(struct _cef_browser_host_t* self, const cef_mouse_event_t* event, cef_mouse_button_type_t type, int mouseUp, int clickCount);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:753</a>
     */
    void sendMouseClickEvent(
            @Nonnull CefMouseEvent event, @Nonnull CefMouseButtonType type, boolean mouseup, int clickcount);

    /**
     * Send a mouse move event to the browser. The {@code x} and {@code y} coordinates are relative to the upper-left
     * corner of the view.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* send_mouse_move_event)(struct _cef_browser_host_t* self, const cef_mouse_event_t* event, int mouseLeave);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:763</a>
     */
    void sendMouseMoveEvent(@Nonnull CefMouseEvent event, boolean mouseleave);

    /**
     * Send a mouse wheel event to the browser. The {@code x} and {@code y} coordinates are relative to the upper-left
     * corner of the view. The {@code deltaX} and {@code deltaY} values represent the movement delta in the X and Y
     * directions respectively. In order to scroll inside select popups with window rendering disabled
     * {@link CefRenderHandler#getScreenPoint(CefBrowser, int, int, int[], int[])} should be implemented properly.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* send_mouse_wheel_event)(struct _cef_browser_host_t* self, const cef_mouse_event_t* event, int deltaX, int deltaY);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:771</a>
     */
    void sendMouseWheelEvent(@Nonnull CefMouseEvent event, int deltax, int deltay);

    /**
     * Send a touch event to the browser for a windowless browser.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>void (CEF_CALLBACK* send_touch_event)(struct _cef_browser_host_t* self, const cef_touch_event_t* event);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:784</a>
     */
    void sendTouchEvent(@Nonnull CefTouchEvent event);

    /**
     * Send a capture lost event to the browser.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>void (CEF_CALLBACK* send_capture_lost_event)(struct _cef_browser_host_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:790</a>
     */
    void sendCaptureLostEvent();

    /**
     * Notify the browser that the window hosting it is about to be moved or resized. This method is only used on
     * Windows and Linux.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>void (CEF_CALLBACK* notify_move_or_resize_started)(struct _cef_browser_host_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:796</a>
     */
    void notifyMoveOrResizeStarted();

    /**
     * Returns the maximum rate in frames per second (fps) that {@link CefRenderHandler#onPaint(CefBrowser,
     * CefPaintElementType, long, CefRect[], ByteBuffer, int, int)} will be called for a windowless browser. The actual
     * fps may be lower if the browser cannot generate frames at the requested rate. The minimum value is 1 and the
     * default value is 30. This method can only be called on the UI thread.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_windowless_frame_rate)(struct _cef_browser_host_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:803</a>
     */
    int getWindowlessFrameRate();

    /**
     * Set the maximum rate in frames per second (fps) that CefRenderHandler:: OnPaint will be called for a windowless
     * browser. The actual fps may be lower if the browser cannot generate frames at the requested rate. The minimum
     * value is 1 and the default value is 30. Can also be set at browser creation via
     * CefBrowserSettings.windowless_frame_rate.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_windowless_frame_rate)(struct _cef_browser_host_t* self, int frame_rate);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:813</a>
     */
    void setWindowlessFrameRate(int frameRate);

    /**
     * Begins a new composition or updates the existing composition. Blink has a special node (a composition node) that
     * allows the input method to change text without affecting other DOM nodes. {@code text} is the optional text that
     * will be inserted into the composition node. {@code underlines} is an optional set of ranges that will be
     * underlined in the resulting text. {@code replacement_range} is an optional range of the existing text that will
     * be replaced. {@code selection_range} is an optional range of the resulting text that will be selected after
     * insertion or replacement. The {@code replacement_range} value is only used on OS X.
     *
     * <p>This method may be called multiple times as the composition changes. When the client is done making changes
     * the composition should either be canceled or completed. To cancel the composition call ImeCancelComposition. To
     * complete the composition call either ImeCommitText or ImeFinishComposingText. Completion is usually signaled
     * when:
     *
     * <p>1. The client receives a WM_IME_COMPOSITION message with a GCS_RESULTSTR flag (on Windows), or; 2. The client
     * receives a "commit" signal of GtkIMContext (on Linux), or; 3. insertText of NSTextInput is called (on Mac).
     *
     * <p>This method is only used when window rendering is disabled.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* ime_set_composition)(struct _cef_browser_host_t* self, const cef_string_t* text, size_t underlinesCount, cef_composition_underline_t const* underlines, const cef_range_t* replacement_range, const cef_range_t* selection_range);
     * </pre>
     *
     * @param text may be null
     * @param underlines may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:823</a>
     */
    void imeSetComposition(
            @Nullable String text,
            long underlinescount,
            @Nullable CefCompositionUnderline[] underlines,
            @Nonnull CefRange replacementRange,
            @Nonnull CefRange selectionRange);

    /**
     * Completes the existing composition by optionally inserting the specified {@code text} into the composition node.
     * {@code replacement_range} is an optional range of the existing text that will be replaced.
     * {@code relative_cursor_pos} is where the cursor will be positioned relative to the current cursor position. See
     * comments on ImeSetComposition for usage. The {@code replacement_range} and {@code relative_cursor_pos} values are
     * only used on OS X. This method is only used when window rendering is disabled.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* ime_commit_text)(struct _cef_browser_host_t* self, const cef_string_t* text, const cef_range_t* replacement_range, int relative_cursor_pos);
     * </pre>
     *
     * @param text may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:855</a>
     */
    void imeCommitText(@Nullable String text, @Nonnull CefRange replacementRange, int relativeCursorPos);

    /**
     * Completes the existing composition by applying the current composition node contents. If {@code keep_selection}
     * is {@code false} the current selection, if any, will be discarded. See comments on ImeSetComposition for usage.
     * This method is only used when window rendering is disabled.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>void (CEF_CALLBACK* ime_finish_composing_text)(struct _cef_browser_host_t* self, int keep_selection);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:869</a>
     */
    void imeFinishComposingText(boolean keepSelection);

    /**
     * Cancels the existing composition and discards the composition node contents without applying them. See comments
     * on ImeSetComposition for usage. This method is only used when window rendering is disabled.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>void (CEF_CALLBACK* ime_cancel_composition)(struct _cef_browser_host_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:878</a>
     */
    void imeCancelComposition();

    /**
     * Call this method when the user drags the mouse into the web view (before calling
     * DragTargetDragOver/DragTargetLeave/DragTargetDrop). {@code drag_data} should not contain file contents as this
     * type of data is not allowed to be dragged into the web view. File contents can be removed using
     * {@link CefDragData#resetFileContents()} (for example, if {@code drag_data} comes from
     * {@link CefRenderHandler#startDragging(CefBrowser, CefDragData, CefDragOperationsMask, int, int)}). This method is
     * only used when window rendering is disabled.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* drag_target_drag_enter)(struct _cef_browser_host_t* self, struct _cef_drag_data_t* drag_data, const cef_mouse_event_t* event, cef_drag_operations_mask_t allowed_ops);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:887</a>
     */
    void dragTargetDragEnter(
            @Nonnull CefDragData dragData, @Nonnull CefMouseEvent event, @Nonnull CefDragOperationsMask allowedOps);

    /**
     * Call this method each time the mouse is moved across the web view during a drag operation (after calling
     * DragTargetDragEnter and before calling DragTargetDragLeave/DragTargetDrop). This method is only used when window
     * rendering is disabled.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* drag_target_drag_over)(struct _cef_browser_host_t* self, const cef_mouse_event_t* event, cef_drag_operations_mask_t allowed_ops);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:901</a>
     */
    void dragTargetDragOver(@Nonnull CefMouseEvent event, @Nonnull CefDragOperationsMask allowedOps);

    /**
     * Call this method when the user drags the mouse out of the web view (after calling DragTargetDragEnter). This
     * method is only used when window rendering is disabled.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>void (CEF_CALLBACK* drag_target_drag_leave)(struct _cef_browser_host_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:911</a>
     */
    void dragTargetDragLeave();

    /**
     * Call this method when the user completes the drag operation by dropping the object onto the web view (after
     * calling DragTargetDragEnter). The object being dropped is {@code drag_data}, given as an argument to the previous
     * DragTargetDragEnter call. This method is only used when window rendering is disabled.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>void (CEF_CALLBACK* drag_target_drop)(struct _cef_browser_host_t* self, const cef_mouse_event_t* event);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:919</a>
     */
    void dragTargetDrop(@Nonnull CefMouseEvent event);

    /**
     * Call this method when the drag operation started by a {@link CefRenderHandler#startDragging(CefBrowser,
     * CefDragData, CefDragOperationsMask, int, int)} call has ended either in a drop or by being cancelled. {@code x}
     * and {@code y} are mouse coordinates relative to the upper-left corner of the view. If the web view is both the
     * drag source and the drag target then all DragTarget* methods should be called before DragSource* mthods. This
     * method is only used when window rendering is disabled.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* drag_source_ended_at)(struct _cef_browser_host_t* self, int x, int y, cef_drag_operations_mask_t op);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:929</a>
     */
    void dragSourceEndedAt(int x, int y, @Nonnull CefDragOperationsMask op);

    /**
     * Call this method when the drag operation started by a {@link CefRenderHandler#startDragging(CefBrowser,
     * CefDragData, CefDragOperationsMask, int, int)} call has completed. This method may be called immediately without
     * first calling DragSourceEndedAt to cancel a drag operation. If the web view is both the drag source and the drag
     * target then all DragTarget* methods should be called before DragSource* mthods. This method is only used when
     * window rendering is disabled.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>void (CEF_CALLBACK* drag_source_system_drag_ended)(struct _cef_browser_host_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:941</a>
     */
    void dragSourceSystemDragEnded();

    /**
     * Returns the current visible navigation entry for this browser. This method can only be called on the UI thread.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>cef_navigation_entry_t* (CEF_CALLBACK* get_visible_navigation_entry)(struct _cef_browser_host_t* self);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:953</a>
     */
    Optional<CefNavigationEntry> getVisibleNavigationEntry();

    /**
     * Set accessibility state for all frames. {@code accessibility_state} may be default, enabled or disabled. If
     * {@code accessibility_state} is STATE_DEFAULT then accessibility will be disabled by default and the state may be
     * further controlled with the "force-renderer-accessibility" and "disable-renderer-accessibility" command-line
     * switches. If {@code accessibility_state} is STATE_ENABLED then accessibility will be enabled. If
     * {@code accessibility_state} is STATE_DISABLED then accessibility will be completely disabled.
     *
     * <p>For windowed browsers accessibility will be enabled in Complete mode (which corresponds to
     * kAccessibilityModeComplete in Chromium). In this mode all platform accessibility objects will be created and
     * managed by Chromium's internal implementation. The client needs only to detect the screen reader and call this
     * method appropriately. For example, on macOS the client can handle the @"AXEnhancedUserInterface" accessibility
     * attribute to detect VoiceOver state changes and on Windows the client can handle WM_GETOBJECT with OBJID_CLIENT
     * to detect accessibility readers.
     *
     * <p>For windowless browsers accessibility will be enabled in TreeOnly mode (which corresponds to
     * kAccessibilityModeWebContentsOnly in Chromium). In this mode renderer accessibility is enabled, the full tree is
     * computed, and events are passed to CefAccessibiltyHandler, but platform accessibility objects are not created.
     * The client may implement platform accessibility objects using CefAccessibiltyHandler callbacks if desired.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* set_accessibility_state)(struct _cef_browser_host_t* self, cef_state_t accessibility_state);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:960</a>
     */
    void setAccessibilityState(@Nonnull CefState accessibilityState);

    /**
     * Enable notifications of auto resize via {@link CefDisplayHandler#onAutoResize(CefBrowser, CefSize)}.
     * Notifications are disabled by default. {@code min_size} and {@code max_size} define the range of allowed sizes.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* set_auto_resize_enabled)(struct _cef_browser_host_t* self, int enabled, const cef_size_t* min_size, const cef_size_t* max_size);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:989</a>
     */
    void setAutoResizeEnabled(boolean enabled, @Nonnull CefSize minSize, @Nonnull CefSize maxSize);

    /**
     * Set whether the browser's audio is muted.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_audio_muted)(struct _cef_browser_host_t* self, int mute);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:999</a>
     */
    void setAudioMuted(boolean mute);

    /**
     * Returns {@code true} if the browser's audio is muted. This method can only be called on the UI thread.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_audio_muted)(struct _cef_browser_host_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:1005</a>
     */
    boolean isAudioMuted();

    /**
     * Returns {@code true} if the renderer is currently in browser fullscreen. This differs from window fullscreen in
     * that browser fullscreen is entered using the JavaScript Fullscreen API and modifies CSS attributes such as the
     * ::backdrop pseudo-element and :fullscreen pseudo-class. This method can only be called on the UI thread.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_fullscreen)(struct _cef_browser_host_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:1012</a>
     */
    boolean isFullscreen();

    /**
     * Requests the renderer to exit browser fullscreen. In most cases exiting window fullscreen should also exit
     * browser fullscreen. With Alloy style this method should be called in response to a user action such as clicking
     * the green traffic light button on MacOS (CefWindowDelegate.onWindowFullscreenTransition() callback) or pressing
     * the "ESC" key ({@link CefKeyboardHandler#onPreKeyEvent(CefBrowser, CefKeyEvent, long, int[])} callback). With
     * Chrome style these standard exit actions are handled internally but new/additional user actions can use this
     * method. Set {@code will_cause_resize} to {@code true} if exiting browser fullscreen will cause a view resize.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>void (CEF_CALLBACK* exit_fullscreen)(struct _cef_browser_host_t* self, int will_cause_resize);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:1022</a>
     */
    void exitFullscreen(boolean willCauseResize);

    /**
     * Returns {@code true} if a Chrome command is supported and enabled. Use the cef_id_for_command_id_name() function
     * for version-safe mapping of command IDC names from cef_command_ids.h to version-specific numerical
     * {@code command_id} values. This method can only be called on the UI thread. Only used with Chrome style.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>int (CEF_CALLBACK* can_execute_chrome_command)(struct _cef_browser_host_t* self, int command_id);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:1036</a>
     */
    boolean canExecuteChromeCommand(int commandId);

    /**
     * Execute a Chrome command. Use the cef_id_for_command_id_name() function for version-safe mapping of command IDC
     * names from cef_command_ids.h to version-specific numerical {@code command_id} values. {@code disposition}
     * provides information about the intended command target. Only used with Chrome style.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* execute_chrome_command)(struct _cef_browser_host_t* self, int command_id, cef_window_open_disposition_t disposition);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:1046</a>
     */
    void executeChromeCommand(int commandId, @Nonnull CefWindowOpenDisposition disposition);

    /**
     * Returns {@code true} if the render process associated with this browser is currently unresponsive as indicated by
     * a lack of input event processing for at least 15 seconds. To receive associated state change notifications and
     * optionally handle an unresponsive render process implement
     * {@link CefRequestHandler#onRenderProcessUnresponsive(CefBrowser, CefUnresponsiveProcessCallback)}. This method
     * can only be called on the UI thread.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_render_process_unresponsive)(struct _cef_browser_host_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:1058</a>
     */
    boolean isRenderProcessUnresponsive();

    /**
     * Returns the runtime style for this browser (ALLOY or CHROME). See cef_runtime_style_t documentation for details.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>cef_runtime_style_t (CEF_CALLBACK* get_runtime_style)(struct _cef_browser_host_t* self);</pre>
     *
     * @return the result, or {@code CEF_RUNTIME_STYLE_DEFAULT} for default handling
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:1069</a>
     */
    CefRuntimeStyle getRuntimeStyle();

    /**
     * Enable or disable CDP accessibility tree viewport collapse for this browser. When enabled, off-screen landmarks
     * and headings are serialized as summaries and other off-screen nodes are pruned. Overrides the
     * CefBrowserSettings.ax_viewport_collapse value. If called on the UI thread the change will be applied immediately.
     * Otherwise, the change will be applied asynchronously on the UI thread. <b>WARNING:</b> This collapses the CDP
     * accessibility tree and disables CDP dynamic tree updates (nodesUpdated events). The DevTools Accessibility panel
     * will show an incomplete tree. Platform screen readers (NVDA, JAWS, VoiceOver) are unaffected — they use a
     * separate code path.
     *
     * <p>Added in CEF API version experimental.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_ax_viewport_collapse)(struct _cef_browser_host_t* self, int enabled);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:1077</a>
     */
    void setAxViewportCollapse(boolean enabled);
    /**
     * Create a new browser using the window parameters specified by {@code windowInfo}. All values will be copied
     * internally and the actual window (if any) will be created on the UI thread. If {@code request_context} is empty
     * the global request context will be used. This method can be called on any browser process thread and will not
     * block. The optional {@code extra_info} parameter provides an opportunity to specify extra information specific to
     * the created browser that will be passed to {@link CefRenderProcessHandler#onBrowserCreated(CefBrowser,
     * CefDictionaryValue)} in the render process.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>
     * CEF_EXPORT int cef_browser_host_create_browser(const cef_window_info_t* windowInfo, struct _cef_client_t* client, const cef_string_t* url, const struct _cef_browser_settings_t* settings, struct _cef_dictionary_value_t* extra_info, struct _cef_request_context_t* request_context);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:289</a>
     */
    static int createBrowser(
            @Nonnull CefWindowInfo windowinfo,
            @Nullable CefClient client,
            @Nullable String url,
            @Nonnull CefBrowserSettings settings,
            @Nullable CefDictionaryValue extraInfo,
            @Nullable CefRequestContext requestContext) {
        return NativePeer.N_CreateBrowser(windowinfo, client, url, settings, extraInfo, requestContext);
    }

    /**
     * Create a new browser using the window parameters specified by {@code windowInfo}. If {@code request_context} is
     * empty the global request context will be used. This method can only be called on the browser process UI thread.
     * The optional {@code extra_info} parameter provides an opportunity to specify extra information specific to the
     * created browser that will be passed to {@link CefRenderProcessHandler#onBrowserCreated(CefBrowser,
     * CefDictionaryValue)} in the render process.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>
     * CEF_EXPORT cef_browser_t* cef_browser_host_create_browser_sync(const cef_window_info_t* windowInfo, struct _cef_client_t* client, const cef_string_t* url, const struct _cef_browser_settings_t* settings, struct _cef_dictionary_value_t* extra_info, struct _cef_request_context_t* request_context);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:308</a>
     */
    static Optional<CefBrowser> createBrowserSync(
            @Nonnull CefWindowInfo windowinfo,
            @Nullable CefClient client,
            @Nullable String url,
            @Nonnull CefBrowserSettings settings,
            @Nullable CefDictionaryValue extraInfo,
            @Nullable CefRequestContext requestContext) {
        return Optional.ofNullable(
                NativePeer.N_CreateBrowserSync(windowinfo, client, url, settings, extraInfo, requestContext));
    }

    /**
     * Returns the browser (if any) with the specified identifier.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>CEF_EXPORT cef_browser_t* cef_browser_host_get_browser_by_identifier(int browser_id);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:327</a>
     */
    static Optional<CefBrowser> getBrowserByIdentifier(int browserId) {
        return Optional.ofNullable(NativePeer.N_GetBrowserByIdentifier(browserId));
    }

    final class NativePeer implements CefBrowserHost, AutoCloseable {
        private final long nativePtr;
        private final java.lang.ref.Cleaner.Cleanable cleanable;

        NativePeer(long ptr) {
            this.nativePtr = ptr;
            this.cleanable = net.kurobako.cef4j.NativeCleaner.INSTANCE.register(this, new Release(ptr));
        }

        @Override
        public void close() {
            cleanable.clean();
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefBrowserHost.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefBrowserHost 0x{}", Long.toHexString(ptr));
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

        @Override
        public Optional<CefBrowser> getBrowser() {
            return Optional.ofNullable(N_GetBrowser(nativePtr));
        }

        @Override
        public void closeBrowser(boolean forceClose) {
            N_CloseBrowser(nativePtr, forceClose);
        }

        @Override
        public boolean tryCloseBrowser() {
            return N_TryCloseBrowser(nativePtr);
        }

        @Override
        public boolean isReadyToBeClosed() {
            return N_IsReadyToBeClosed(nativePtr);
        }

        @Override
        public void setFocus(boolean focus) {
            N_SetFocus(nativePtr, focus);
        }

        @Override
        public long getWindowHandle() {
            return N_GetWindowHandle(nativePtr);
        }

        @Override
        public long getOpenerWindowHandle() {
            return N_GetOpenerWindowHandle(nativePtr);
        }

        @Override
        public int getOpenerIdentifier() {
            return N_GetOpenerIdentifier(nativePtr);
        }

        @Override
        public boolean hasView() {
            return N_HasView(nativePtr);
        }

        @Override
        public Optional<CefClient> getClient() {
            return Optional.ofNullable(N_GetClient(nativePtr));
        }

        @Override
        public Optional<CefRequestContext> getRequestContext() {
            return Optional.ofNullable(N_GetRequestContext(nativePtr));
        }

        @Override
        public boolean canZoom(@Nonnull CefZoomCommand command) {
            return N_CanZoom(nativePtr, command);
        }

        @Override
        public void zoom(@Nonnull CefZoomCommand command) {
            N_Zoom(nativePtr, command);
        }

        @Override
        public double getDefaultZoomLevel() {
            return N_GetDefaultZoomLevel(nativePtr);
        }

        @Override
        public double getZoomLevel() {
            return N_GetZoomLevel(nativePtr);
        }

        @Override
        public void setZoomLevel(double zoomlevel) {
            N_SetZoomLevel(nativePtr, zoomlevel);
        }

        @Override
        public void runFileDialog(
                @Nonnull CefFileDialogMode mode,
                @Nullable String title,
                @Nullable String defaultFilePath,
                @Nullable List<String> acceptFilters,
                @Nonnull CefRunFileDialogCallback callback) {
            N_RunFileDialog(nativePtr, mode, title, defaultFilePath, acceptFilters, callback);
        }

        @Override
        public void startDownload(@Nonnull String url) {
            N_StartDownload(nativePtr, url);
        }

        @Override
        public void downloadImage(
                @Nonnull String imageUrl,
                boolean isFavicon,
                int maxImageSize,
                boolean bypassCache,
                @Nonnull CefDownloadImageCallback callback) {
            N_DownloadImage(nativePtr, imageUrl, isFavicon, maxImageSize, bypassCache, callback);
        }

        @Override
        public void print() {
            N_Print(nativePtr);
        }

        @Override
        public void printToPdf(
                @Nonnull String path, @Nonnull CefPdfPrintSettings settings, @Nullable CefPdfPrintCallback callback) {
            N_PrintToPdf(nativePtr, path, settings, callback);
        }

        @Override
        public void find(@Nonnull String searchtext, boolean forward, boolean matchcase, boolean findnext) {
            N_Find(nativePtr, searchtext, forward, matchcase, findnext);
        }

        @Override
        public void stopFinding(boolean clearselection) {
            N_StopFinding(nativePtr, clearselection);
        }

        @Override
        public void showDevTools(
                @Nullable CefWindowInfo windowinfo,
                @Nullable CefClient client,
                @Nullable CefBrowserSettings settings,
                @Nullable CefPoint inspectElementAt) {
            N_ShowDevTools(nativePtr, windowinfo, client, settings, inspectElementAt);
        }

        @Override
        public void closeDevTools() {
            N_CloseDevTools(nativePtr);
        }

        @Override
        public boolean hasDevTools() {
            return N_HasDevTools(nativePtr);
        }

        @Override
        public boolean sendDevToolsMessage(@Nonnull ByteBuffer message) {
            return N_SendDevToolsMessage(nativePtr, message);
        }

        @Override
        public int executeDevToolsMethod(int messageId, @Nonnull String method, @Nullable CefDictionaryValue params) {
            return N_ExecuteDevToolsMethod(nativePtr, messageId, method, params);
        }

        @Override
        public Optional<CefRegistration> addDevToolsMessageObserver(@Nonnull CefDevToolsMessageObserver observer) {
            return Optional.ofNullable(N_AddDevToolsMessageObserver(nativePtr, observer));
        }

        @Override
        public void getNavigationEntries(@Nonnull CefNavigationEntryVisitor visitor, boolean currentOnly) {
            N_GetNavigationEntries(nativePtr, visitor, currentOnly);
        }

        @Override
        public void replaceMisspelling(@Nonnull String word) {
            N_ReplaceMisspelling(nativePtr, word);
        }

        @Override
        public void addWordToDictionary(@Nonnull String word) {
            N_AddWordToDictionary(nativePtr, word);
        }

        @Override
        public boolean isWindowRenderingDisabled() {
            return N_IsWindowRenderingDisabled(nativePtr);
        }

        @Override
        public void wasResized() {
            N_WasResized(nativePtr);
        }

        @Override
        public void wasHidden(boolean hidden) {
            N_WasHidden(nativePtr, hidden);
        }

        @Override
        public void notifyScreenInfoChanged() {
            N_NotifyScreenInfoChanged(nativePtr);
        }

        @Override
        public void invalidate(@Nonnull CefPaintElementType type) {
            N_Invalidate(nativePtr, type);
        }

        @Override
        public void sendExternalBeginFrame() {
            N_SendExternalBeginFrame(nativePtr);
        }

        @Override
        public void sendKeyEvent(@Nonnull CefKeyEvent event) {
            N_SendKeyEvent(nativePtr, event);
        }

        @Override
        public void sendMouseClickEvent(
                @Nonnull CefMouseEvent event, @Nonnull CefMouseButtonType type, boolean mouseup, int clickcount) {
            N_SendMouseClickEvent(nativePtr, event, type, mouseup, clickcount);
        }

        @Override
        public void sendMouseMoveEvent(@Nonnull CefMouseEvent event, boolean mouseleave) {
            N_SendMouseMoveEvent(nativePtr, event, mouseleave);
        }

        @Override
        public void sendMouseWheelEvent(@Nonnull CefMouseEvent event, int deltax, int deltay) {
            N_SendMouseWheelEvent(nativePtr, event, deltax, deltay);
        }

        @Override
        public void sendTouchEvent(@Nonnull CefTouchEvent event) {
            N_SendTouchEvent(nativePtr, event);
        }

        @Override
        public void sendCaptureLostEvent() {
            N_SendCaptureLostEvent(nativePtr);
        }

        @Override
        public void notifyMoveOrResizeStarted() {
            N_NotifyMoveOrResizeStarted(nativePtr);
        }

        @Override
        public int getWindowlessFrameRate() {
            return N_GetWindowlessFrameRate(nativePtr);
        }

        @Override
        public void setWindowlessFrameRate(int frameRate) {
            N_SetWindowlessFrameRate(nativePtr, frameRate);
        }

        @Override
        public void imeSetComposition(
                @Nullable String text,
                long underlinescount,
                @Nullable CefCompositionUnderline[] underlines,
                @Nonnull CefRange replacementRange,
                @Nonnull CefRange selectionRange) {
            N_ImeSetComposition(nativePtr, text, underlinescount, underlines, replacementRange, selectionRange);
        }

        @Override
        public void imeCommitText(@Nullable String text, @Nonnull CefRange replacementRange, int relativeCursorPos) {
            N_ImeCommitText(nativePtr, text, replacementRange, relativeCursorPos);
        }

        @Override
        public void imeFinishComposingText(boolean keepSelection) {
            N_ImeFinishComposingText(nativePtr, keepSelection);
        }

        @Override
        public void imeCancelComposition() {
            N_ImeCancelComposition(nativePtr);
        }

        @Override
        public void dragTargetDragEnter(
                @Nonnull CefDragData dragData,
                @Nonnull CefMouseEvent event,
                @Nonnull CefDragOperationsMask allowedOps) {
            N_DragTargetDragEnter(nativePtr, dragData, event, allowedOps);
        }

        @Override
        public void dragTargetDragOver(@Nonnull CefMouseEvent event, @Nonnull CefDragOperationsMask allowedOps) {
            N_DragTargetDragOver(nativePtr, event, allowedOps);
        }

        @Override
        public void dragTargetDragLeave() {
            N_DragTargetDragLeave(nativePtr);
        }

        @Override
        public void dragTargetDrop(@Nonnull CefMouseEvent event) {
            N_DragTargetDrop(nativePtr, event);
        }

        @Override
        public void dragSourceEndedAt(int x, int y, @Nonnull CefDragOperationsMask op) {
            N_DragSourceEndedAt(nativePtr, x, y, op);
        }

        @Override
        public void dragSourceSystemDragEnded() {
            N_DragSourceSystemDragEnded(nativePtr);
        }

        @Override
        public Optional<CefNavigationEntry> getVisibleNavigationEntry() {
            return Optional.ofNullable(N_GetVisibleNavigationEntry(nativePtr));
        }

        @Override
        public void setAccessibilityState(@Nonnull CefState accessibilityState) {
            N_SetAccessibilityState(nativePtr, accessibilityState);
        }

        @Override
        public void setAutoResizeEnabled(boolean enabled, @Nonnull CefSize minSize, @Nonnull CefSize maxSize) {
            N_SetAutoResizeEnabled(nativePtr, enabled, minSize, maxSize);
        }

        @Override
        public void setAudioMuted(boolean mute) {
            N_SetAudioMuted(nativePtr, mute);
        }

        @Override
        public boolean isAudioMuted() {
            return N_IsAudioMuted(nativePtr);
        }

        @Override
        public boolean isFullscreen() {
            return N_IsFullscreen(nativePtr);
        }

        @Override
        public void exitFullscreen(boolean willCauseResize) {
            N_ExitFullscreen(nativePtr, willCauseResize);
        }

        @Override
        public boolean canExecuteChromeCommand(int commandId) {
            return N_CanExecuteChromeCommand(nativePtr, commandId);
        }

        @Override
        public void executeChromeCommand(int commandId, @Nonnull CefWindowOpenDisposition disposition) {
            N_ExecuteChromeCommand(nativePtr, commandId, disposition);
        }

        @Override
        public boolean isRenderProcessUnresponsive() {
            return N_IsRenderProcessUnresponsive(nativePtr);
        }

        @Override
        public CefRuntimeStyle getRuntimeStyle() {
            return N_GetRuntimeStyle(nativePtr);
        }

        @Override
        public void setAxViewportCollapse(boolean enabled) {
            N_SetAxViewportCollapse(nativePtr, enabled);
        }

        private static native CefBrowser N_GetBrowser(long self);

        private static native void N_CloseBrowser(long self, boolean forceClose);

        private static native boolean N_TryCloseBrowser(long self);

        private static native boolean N_IsReadyToBeClosed(long self);

        private static native void N_SetFocus(long self, boolean focus);

        private static native long N_GetWindowHandle(long self);

        private static native long N_GetOpenerWindowHandle(long self);

        private static native int N_GetOpenerIdentifier(long self);

        private static native boolean N_HasView(long self);

        private static native CefClient N_GetClient(long self);

        private static native CefRequestContext N_GetRequestContext(long self);

        private static native boolean N_CanZoom(long self, CefZoomCommand command);

        private static native void N_Zoom(long self, CefZoomCommand command);

        private static native double N_GetDefaultZoomLevel(long self);

        private static native double N_GetZoomLevel(long self);

        private static native void N_SetZoomLevel(long self, double zoomlevel);

        private static native void N_RunFileDialog(
                long self,
                CefFileDialogMode mode,
                String title,
                String defaultFilePath,
                List<String> acceptFilters,
                CefRunFileDialogCallback callback);

        private static native void N_StartDownload(long self, String url);

        private static native void N_DownloadImage(
                long self,
                String imageUrl,
                boolean isFavicon,
                int maxImageSize,
                boolean bypassCache,
                CefDownloadImageCallback callback);

        private static native void N_Print(long self);

        private static native void N_PrintToPdf(
                long self, String path, CefPdfPrintSettings settings, CefPdfPrintCallback callback);

        private static native void N_Find(
                long self, String searchtext, boolean forward, boolean matchcase, boolean findnext);

        private static native void N_StopFinding(long self, boolean clearselection);

        private static native void N_ShowDevTools(
                long self,
                CefWindowInfo windowinfo,
                CefClient client,
                CefBrowserSettings settings,
                CefPoint inspectElementAt);

        private static native void N_CloseDevTools(long self);

        private static native boolean N_HasDevTools(long self);

        private static native boolean N_SendDevToolsMessage(long self, ByteBuffer message);

        private static native int N_ExecuteDevToolsMethod(
                long self, int messageId, String method, CefDictionaryValue params);

        private static native CefRegistration N_AddDevToolsMessageObserver(
                long self, CefDevToolsMessageObserver observer);

        private static native void N_GetNavigationEntries(
                long self, CefNavigationEntryVisitor visitor, boolean currentOnly);

        private static native void N_ReplaceMisspelling(long self, String word);

        private static native void N_AddWordToDictionary(long self, String word);

        private static native boolean N_IsWindowRenderingDisabled(long self);

        private static native void N_WasResized(long self);

        private static native void N_WasHidden(long self, boolean hidden);

        private static native void N_NotifyScreenInfoChanged(long self);

        private static native void N_Invalidate(long self, CefPaintElementType type);

        private static native void N_SendExternalBeginFrame(long self);

        private static native void N_SendKeyEvent(long self, CefKeyEvent event);

        private static native void N_SendMouseClickEvent(
                long self, CefMouseEvent event, CefMouseButtonType type, boolean mouseup, int clickcount);

        private static native void N_SendMouseMoveEvent(long self, CefMouseEvent event, boolean mouseleave);

        private static native void N_SendMouseWheelEvent(long self, CefMouseEvent event, int deltax, int deltay);

        private static native void N_SendTouchEvent(long self, CefTouchEvent event);

        private static native void N_SendCaptureLostEvent(long self);

        private static native void N_NotifyMoveOrResizeStarted(long self);

        private static native int N_GetWindowlessFrameRate(long self);

        private static native void N_SetWindowlessFrameRate(long self, int frameRate);

        private static native void N_ImeSetComposition(
                long self,
                String text,
                long underlinescount,
                CefCompositionUnderline[] underlines,
                CefRange replacementRange,
                CefRange selectionRange);

        private static native void N_ImeCommitText(
                long self, String text, CefRange replacementRange, int relativeCursorPos);

        private static native void N_ImeFinishComposingText(long self, boolean keepSelection);

        private static native void N_ImeCancelComposition(long self);

        private static native void N_DragTargetDragEnter(
                long self, CefDragData dragData, CefMouseEvent event, CefDragOperationsMask allowedOps);

        private static native void N_DragTargetDragOver(
                long self, CefMouseEvent event, CefDragOperationsMask allowedOps);

        private static native void N_DragTargetDragLeave(long self);

        private static native void N_DragTargetDrop(long self, CefMouseEvent event);

        private static native void N_DragSourceEndedAt(long self, int x, int y, CefDragOperationsMask op);

        private static native void N_DragSourceSystemDragEnded(long self);

        private static native CefNavigationEntry N_GetVisibleNavigationEntry(long self);

        private static native void N_SetAccessibilityState(long self, CefState accessibilityState);

        private static native void N_SetAutoResizeEnabled(long self, boolean enabled, CefSize minSize, CefSize maxSize);

        private static native void N_SetAudioMuted(long self, boolean mute);

        private static native boolean N_IsAudioMuted(long self);

        private static native boolean N_IsFullscreen(long self);

        private static native void N_ExitFullscreen(long self, boolean willCauseResize);

        private static native boolean N_CanExecuteChromeCommand(long self, int commandId);

        private static native void N_ExecuteChromeCommand(
                long self, int commandId, CefWindowOpenDisposition disposition);

        private static native boolean N_IsRenderProcessUnresponsive(long self);

        private static native CefRuntimeStyle N_GetRuntimeStyle(long self);

        private static native void N_SetAxViewportCollapse(long self, boolean enabled);

        static native int N_CreateBrowser(
                CefWindowInfo windowinfo,
                CefClient client,
                String url,
                CefBrowserSettings settings,
                CefDictionaryValue extraInfo,
                CefRequestContext requestContext);

        static native CefBrowser N_CreateBrowserSync(
                CefWindowInfo windowinfo,
                CefClient client,
                String url,
                CefBrowserSettings settings,
                CefDictionaryValue extraInfo,
                CefRequestContext requestContext);

        static native CefBrowser N_GetBrowserByIdentifier(int browserId);

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
            return "CefBrowserHost{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
