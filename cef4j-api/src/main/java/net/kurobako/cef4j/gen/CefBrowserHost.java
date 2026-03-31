// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Class used to represent the browser process aspects of a browser. The methods of this class can only be called in the
 * browser process. They may be called on any thread in that process unless otherwise indicated in the comments.
 */
public interface CefBrowserHost {

    /** Returns the browser for this context. This method will return an empty reference for WebWorker contexts. */
    long getBrowser();

    /**
     * Request that the browser close. Closing a browser is a multi-stage process that may complete either synchronously
     * or asynchronously, and involves callbacks such as CefLifeSpanHandler::DoClose (Alloy style only),
     * CefLifeSpanHandler::OnBeforeClose, and a top-level window close handler such as CefWindowDelegate::CanClose (or
     * platform-specific equivalent). In some cases a close request may be delayed or canceled by the user. Using
     * TryCloseBrowser() instead of CloseBrowser() is recommended for most use cases. See CefLifeSpanHandler::DoClose()
     * documentation for detailed usage and examples. If |force_close| is false then JavaScript unload handlers, if any,
     * may be fired and the close may be delayed or canceled by the user. If |force_close| is true then the user will
     * not be prompted and the close will proceed immediately (possibly asynchronously). If browser close is delayed and
     * not canceled the default behavior is to call the top-level window close handler once the browser is ready to be
     * closed. This default behavior can be changed for Alloy style browsers by implementing
     * CefLifeSpanHandler::DoClose(). IsReadyToBeClosed() can be used to detect mandatory browser close events when
     * customizing close behavior on the browser process UI thread.
     */
    void closeBrowser(boolean forceClose);

    /**
     * Helper for closing a browser. This is similar in behavior to CLoseBrowser(false) but returns a boolean to reflect
     * the immediate close status. Call this method from a top-level window close handler such as
     * CefWindowDelegate::CanClose (or platform-specific equivalent) to request that the browser close, and return the
     * result to indicate if the window close should proceed. Returns false if the close will be delayed (JavaScript
     * unload handlers triggered but still pending) or true if the close will proceed immediately (possibly
     * asynchronously). See CloseBrowser() documentation for additional usage information. This method must be called on
     * the browser process UI thread.
     */
    boolean tryCloseBrowser();

    /**
     * Returns true if the browser is ready to be closed, meaning that the close has already been initiated and that
     * JavaScript unload handlers have already executed or should be ignored. This can be used from a top-level window
     * close handler such as CefWindowDelegate::CanClose (or platform-specific equivalent) to distringuish between
     * potentially cancelable browser close events (like the user clicking the top-level window close button before
     * browser close has started) and mandatory browser close events (like JavaScript `window.close()` or after browser
     * close has started in response to [Try]CloseBrowser()). Not completing the browser close for mandatory close
     * events (when this method returns true) will leave the browser in a partially closed state that interferes with
     * proper functioning. See CloseBrowser() documentation for additional usage information. This method must be called
     * on the browser process UI thread.
     */
    boolean isReadyToBeClosed();

    /** Set whether the browser is focused. */
    void setFocus(boolean focus);

    /**
     * Retrieve the window handle (if any) for this browser. If this browser is wrapped in a CefBrowserView this method
     * should be called on the browser process UI thread and it will return the handle for the top-level native window.
     */
    long getWindowHandle();

    /**
     * Retrieve the window handle (if any) of the browser that opened this browser. Will return NULL for non-popup
     * browsers or if this browser is wrapped in a CefBrowserView. This method can be used in combination with custom
     * handling of modal windows.
     */
    long getOpenerWindowHandle();

    /** Retrieve the unique identifier of the browser that opened this browser. Will return 0 for non-popup browsers. */
    int getOpenerIdentifier();

    /** Returns true if this browser is wrapped in a CefBrowserView. */
    boolean hasView();

    /** Returns the client. */
    long getClient();

    /** Returns the request context for this browser. */
    long getRequestContext();

    /**
     * Returns true if this browser can execute the specified zoom command. This method can only be called on the UI
     * thread.
     */
    boolean canZoom(@Nonnull CefZoomCommand command);

    /**
     * Execute a zoom command in this browser. If called on the UI thread the change will be applied immediately.
     * Otherwise, the change will be applied asynchronously on the UI thread.
     */
    void zoom(@Nonnull CefZoomCommand command);

    /**
     * Get the default zoom level. This value will be 0.0 by default but can be configured. This method can only be
     * called on the UI thread.
     */
    double getDefaultZoomLevel();

    /** Get the current zoom level. This method can only be called on the UI thread. */
    double getZoomLevel();

    /**
     * Change the zoom level to the specified value. Specify 0.0 to reset the zoom level to the default. If called on
     * the UI thread the change will be applied immediately. Otherwise, the change will be applied asynchronously on the
     * UI thread.
     */
    void setZoomLevel(double zoomLevel);

    /**
     * Call to run a file chooser dialog. Only a single file chooser dialog may be pending at any given time. |mode|
     * represents the type of dialog to display. |title| to the title to be used for the dialog and may be empty to show
     * the default title ("Open" or "Save" depending on the mode). |default_file_path| is the path with optional
     * directory and/or file name component that will be initially selected in the dialog. |accept_filters| are used to
     * restrict the selectable file types and may any combination of (a) valid lower-cased MIME types (e.g. "text/*" or
     * "image/*"), (b) individual file extensions (e.g. ".txt" or ".png"), or (c) combined description and file
     * extension delimited using "|" and ";" (e.g. "Image Types|.png;.gif;.jpg"). |callback| will be executed after the
     * dialog is dismissed or immediately if another dialog is already pending. The dialog will be initiated
     * asynchronously on the UI thread.
     *
     * @param title may be null
     * @param defaultFilePath may be null
     * @param acceptFilters may be null
     */
    void runFileDialog(
            @Nonnull CefFileDialogMode mode,
            @Nullable String title,
            @Nullable String defaultFilePath,
            @Nullable java.util.List<String> acceptFilters,
            long callback);

    /** Download the file at |url| using CefDownloadHandler. */
    void startDownload(@Nonnull String url);

    /**
     * Download |image_url| and execute |callback| on completion with the images received from the renderer. If
     * |is_favicon| is true then cookies are not sent and not accepted during download. Images with density independent
     * pixel (DIP) sizes larger than |max_image_size| are filtered out from the image results. Versions of the image at
     * different scale factors may be downloaded up to the maximum scale factor supported by the system. If there are no
     * image results <= |max_image_size| then the smallest image is resized to |max_image_size| and is the only result.
     * A |max_image_size| of 0 means unlimited. If |bypass_cache| is true then |image_url| is requested from the server
     * even if it is present in the browser cache.
     */
    void downloadImage(
            @Nonnull String imageUrl, boolean isFavicon, int maxImageSize, boolean bypassCache, long callback);

    /** Print the current browser contents. */
    void print();

    void printToPdf(@Nonnull String path, long settings, long callback);

    /**
     * Search for |searchText|. |forward| indicates whether to search forward or backward within the page. |matchCase|
     * indicates whether the search should be case-sensitive. |findNext| indicates whether this is the first request or
     * a follow-up. The search will be restarted if |searchText| or |matchCase| change. The search will be stopped if
     * |searchText| is empty. The CefFindHandler instance, if any, returned via CefClient::GetFindHandler will be called
     * to report find results.
     */
    void find(@Nonnull String searchText, boolean forward, boolean matchCase, boolean findNext);

    /** Cancel all searches that are currently going on. */
    void stopFinding(boolean clearSelection);

    /**
     * Open developer tools (DevTools) in its own browser. The DevTools browser will remain associated with this
     * browser. If the DevTools browser is already open then it will be focused, in which case the |windowInfo|,
     * |client| and |settings| parameters will be ignored. If |inspect_element_at| is non-empty then the element at the
     * specified (x,y) location will be inspected. The |windowInfo| parameter will be ignored if this browser is wrapped
     * in a CefBrowserView.
     *
     * @param windowInfo may be null
     * @param client may be null
     * @param settings may be null
     * @param inspectElementAt may be null
     */
    void showDevTools(long windowInfo, long client, long settings, @Nullable CefPoint inspectElementAt);

    /** Explicitly close the associated DevTools browser, if any. */
    void closeDevTools();

    /**
     * Returns true if this browser currently has an associated DevTools browser. Must be called on the browser process
     * UI thread.
     */
    boolean hasDevTools();

    /**
     * Send a method call message over the DevTools protocol. |message| must be a UTF8-encoded JSON dictionary that
     * contains "id" (int), "method" (string) and "params" (dictionary, optional) values. See the DevTools protocol
     * documentation at https://chromedevtools.github.io/devtools-protocol/ for details of supported methods and the
     * expected "params" dictionary contents. |message| will be copied if necessary. This method will return true if
     * called on the UI thread and the message was successfully submitted for validation, otherwise false. Validation
     * will be applied asynchronously and any messages that fail due to formatting errors or missing parameters may be
     * discarded without notification. Prefer ExecuteDevToolsMethod if a more structured approach to message formatting
     * is desired. Every valid method call will result in an asynchronous method result or error message that references
     * the sent message "id". Event messages are received while notifications are enabled (for example, between method
     * calls for "Page.enable" and "Page.disable"). All received messages will be delivered to the observer(s)
     * registered with AddDevToolsMessageObserver. See CefDevToolsMessageObserver::OnDevToolsMessage documentation for
     * details of received message contents. Usage of the SendDevToolsMessage, ExecuteDevToolsMethod and
     * AddDevToolsMessageObserver methods does not require an active DevTools front-end or remote-debugging session.
     * Other active DevTools sessions will continue to function independently. However, any modification of global
     * browser state by one session may not be reflected in the UI of other sessions. Communication with the DevTools
     * front-end (when displayed) can be logged for development purposes by passing the
     * `--devtools-protocol-log-file=<path>` command-line flag.
     */
    boolean sendDevToolsMessage(long message, long messageSize);

    /**
     * Execute a method call over the DevTools protocol. This is a more structured version of SendDevToolsMessage.
     * |message_id| is an incremental number that uniquely identifies the message (pass 0 to have the next number
     * assigned automatically based on previous values). |method| is the method name. |params| are the method
     * parameters, which may be empty. See the DevTools protocol documentation (linked above) for details of supported
     * methods and the expected |params| dictionary contents. This method will return the assigned message ID if called
     * on the UI thread and the message was successfully submitted for validation, otherwise 0. See the
     * SendDevToolsMessage documentation for additional usage information.
     *
     * @param params may be null
     */
    int executeDevToolsMethod(int messageId, @Nonnull String method, long params);

    /**
     * Add an observer for DevTools protocol messages (method results and events). The observer will remain registered
     * until the returned Registration object is destroyed. See the SendDevToolsMessage documentation for additional
     * usage information.
     */
    long addDevToolsMessageObserver(long observer);

    /**
     * Retrieve a snapshot of current navigation entries as values sent to the specified visitor. If |current_only| is
     * true only the current navigation entry will be sent, otherwise all navigation entries will be sent.
     */
    void getNavigationEntries(long visitor, boolean currentOnly);

    /**
     * If a misspelled word is currently selected in an editable node calling this method will replace it with the
     * specified |word|.
     */
    void replaceMisspelling(@Nonnull String word);

    /** Add the specified |word| to the spelling dictionary. */
    void addWordToDictionary(@Nonnull String word);

    /** Returns true if window rendering is disabled. */
    boolean isWindowRenderingDisabled();

    /**
     * Notify the browser that the widget has been resized. The browser will first call CefRenderHandler::GetViewRect to
     * get the new size and then call CefRenderHandler::OnPaint asynchronously with the updated regions. This method is
     * only used when window rendering is disabled.
     */
    void wasResized();

    /**
     * Notify the browser that it has been hidden or shown. Layouting and CefRenderHandler::OnPaint notification will
     * stop when the browser is hidden. This method is only used when window rendering is disabled.
     */
    void wasHidden(boolean hidden);

    /**
     * Notify the browser that screen information has changed. Updated information will be sent to the renderer process
     * to configure screen size and position values used by CSS and JavaScript (window.deviceScaleFactor,
     * window.screenX/Y, window.outerWidth/Height, etc.). For background see
     * https://chromiumembedded.github.io/cef/general_usage#coordinate-systems This method is used with (a) windowless
     * rendering and (b) windowed rendering with external (client-provided) root window. With windowless rendering the
     * browser will call CefRenderHandler::GetScreenInfo, CefRenderHandler::GetRootScreenRect and
     * CefRenderHandler::GetViewRect. This simulates moving or resizing the root window in the current display, moving
     * the root window from one display to another, or changing the properties of the current display. With windowed
     * rendering the browser will call CefDisplayHandler::GetRootWindowScreenRect and use the associated display
     * properties.
     */
    void notifyScreenInfoChanged();

    /**
     * Invalidate the view. The browser will call CefRenderHandler::OnPaint asynchronously. This method is only used
     * when window rendering is disabled.
     */
    void invalidate(@Nonnull CefPaintElementType type);

    /**
     * Issue a BeginFrame request to Chromium. Only valid when CefWindowInfo::external_begin_frame_enabled is set to
     * true.
     */
    void sendExternalBeginFrame();

    /** Send a key event to the browser. */
    void sendKeyEvent(long event);

    /**
     * Send a mouse click event to the browser. The |x| and |y| coordinates are relative to the upper-left corner of the
     * view.
     */
    void sendMouseClickEvent(long event, @Nonnull CefMouseButtonType type, boolean mouseUp, int clickCount);

    /**
     * Send a mouse move event to the browser. The |x| and |y| coordinates are relative to the upper-left corner of the
     * view.
     */
    void sendMouseMoveEvent(long event, boolean mouseLeave);

    /**
     * Send a mouse wheel event to the browser. The |x| and |y| coordinates are relative to the upper-left corner of the
     * view. The |deltaX| and |deltaY| values represent the movement delta in the X and Y directions respectively. In
     * order to scroll inside select popups with window rendering disabled CefRenderHandler::GetScreenPoint should be
     * implemented properly.
     */
    void sendMouseWheelEvent(long event, int deltaX, int deltaY);

    /** Send a touch event to the browser for a windowless browser. */
    void sendTouchEvent(long event);

    /** Send a capture lost event to the browser. */
    void sendCaptureLostEvent();

    /**
     * Notify the browser that the window hosting it is about to be moved or resized. This method is only used on
     * Windows and Linux.
     */
    void notifyMoveOrResizeStarted();

    /**
     * Returns the maximum rate in frames per second (fps) that CefRenderHandler::OnPaint will be called for a
     * windowless browser. The actual fps may be lower if the browser cannot generate frames at the requested rate. The
     * minimum value is 1 and the default value is 30. This method can only be called on the UI thread.
     */
    int getWindowlessFrameRate();

    /**
     * Set the maximum rate in frames per second (fps) that CefRenderHandler:: OnPaint will be called for a windowless
     * browser. The actual fps may be lower if the browser cannot generate frames at the requested rate. The minimum
     * value is 1 and the default value is 30. Can also be set at browser creation via
     * CefBrowserSettings.windowless_frame_rate.
     */
    void setWindowlessFrameRate(int frameRate);

    /**
     * Begins a new composition or updates the existing composition. Blink has a special node (a composition node) that
     * allows the input method to change text without affecting other DOM nodes. |text| is the optional text that will
     * be inserted into the composition node. |underlines| is an optional set of ranges that will be underlined in the
     * resulting text. |replacement_range| is an optional range of the existing text that will be replaced.
     * |selection_range| is an optional range of the resulting text that will be selected after insertion or
     * replacement. The |replacement_range| value is only used on OS X. This method may be called multiple times as the
     * composition changes. When the client is done making changes the composition should either be canceled or
     * completed. To cancel the composition call ImeCancelComposition. To complete the composition call either
     * ImeCommitText or ImeFinishComposingText. Completion is usually signaled when: 1. The client receives a
     * WM_IME_COMPOSITION message with a GCS_RESULTSTR flag (on Windows), or; 2. The client receives a "commit" signal
     * of GtkIMContext (on Linux), or; 3. insertText of NSTextInput is called (on Mac). This method is only used when
     * window rendering is disabled.
     *
     * @param text may be null
     * @param underlines may be null
     */
    void imeSetComposition(
            @Nullable String text,
            long underlinesCount,
            long underlines,
            @Nonnull CefRange replacementRange,
            @Nonnull CefRange selectionRange);

    /**
     * Completes the existing composition by optionally inserting the specified |text| into the composition node.
     * |replacement_range| is an optional range of the existing text that will be replaced. |relative_cursor_pos| is
     * where the cursor will be positioned relative to the current cursor position. See comments on ImeSetComposition
     * for usage. The |replacement_range| and |relative_cursor_pos| values are only used on OS X. This method is only
     * used when window rendering is disabled.
     *
     * @param text may be null
     */
    void imeCommitText(@Nullable String text, @Nonnull CefRange replacementRange, int relativeCursorPos);

    /**
     * Completes the existing composition by applying the current composition node contents. If |keep_selection| is
     * false the current selection, if any, will be discarded. See comments on ImeSetComposition for usage. This method
     * is only used when window rendering is disabled.
     */
    void imeFinishComposingText(boolean keepSelection);

    /**
     * Cancels the existing composition and discards the composition node contents without applying them. See comments
     * on ImeSetComposition for usage. This method is only used when window rendering is disabled.
     */
    void imeCancelComposition();

    /**
     * Call this method when the user drags the mouse into the web view (before calling
     * DragTargetDragOver/DragTargetLeave/DragTargetDrop). |drag_data| should not contain file contents as this type of
     * data is not allowed to be dragged into the web view. File contents can be removed using
     * CefDragData::ResetFileContents (for example, if |drag_data| comes from CefRenderHandler::StartDragging). This
     * method is only used when window rendering is disabled.
     */
    void dragTargetDragEnter(long dragData, long event, @Nonnull CefDragOperationsMask allowedOps);

    /**
     * Call this method each time the mouse is moved across the web view during a drag operation (after calling
     * DragTargetDragEnter and before calling DragTargetDragLeave/DragTargetDrop). This method is only used when window
     * rendering is disabled.
     */
    void dragTargetDragOver(long event, @Nonnull CefDragOperationsMask allowedOps);

    /**
     * Call this method when the user drags the mouse out of the web view (after calling DragTargetDragEnter). This
     * method is only used when window rendering is disabled.
     */
    void dragTargetDragLeave();

    /**
     * Call this method when the user completes the drag operation by dropping the object onto the web view (after
     * calling DragTargetDragEnter). The object being dropped is |drag_data|, given as an argument to the previous
     * DragTargetDragEnter call. This method is only used when window rendering is disabled.
     */
    void dragTargetDrop(long event);

    /**
     * Call this method when the drag operation started by a CefRenderHandler::StartDragging call has ended either in a
     * drop or by being cancelled. |x| and |y| are mouse coordinates relative to the upper-left corner of the view. If
     * the web view is both the drag source and the drag target then all DragTarget* methods should be called before
     * DragSource* mthods. This method is only used when window rendering is disabled.
     */
    void dragSourceEndedAt(int x, int y, @Nonnull CefDragOperationsMask op);

    /**
     * Call this method when the drag operation started by a CefRenderHandler::StartDragging call has completed. This
     * method may be called immediately without first calling DragSourceEndedAt to cancel a drag operation. If the web
     * view is both the drag source and the drag target then all DragTarget* methods should be called before DragSource*
     * mthods. This method is only used when window rendering is disabled.
     */
    void dragSourceSystemDragEnded();

    /**
     * Returns the current visible navigation entry for this browser. This method can only be called on the UI thread.
     */
    long getVisibleNavigationEntry();

    /**
     * Set accessibility state for all frames. |accessibility_state| may be default, enabled or disabled. If
     * |accessibility_state| is STATE_DEFAULT then accessibility will be disabled by default and the state may be
     * further controlled with the "force-renderer-accessibility" and "disable-renderer-accessibility" command-line
     * switches. If |accessibility_state| is STATE_ENABLED then accessibility will be enabled. If |accessibility_state|
     * is STATE_DISABLED then accessibility will be completely disabled. For windowed browsers accessibility will be
     * enabled in Complete mode (which corresponds to kAccessibilityModeComplete in Chromium). In this mode all platform
     * accessibility objects will be created and managed by Chromium's internal implementation. The client needs only to
     * detect the screen reader and call this method appropriately. For example, on macOS the client can handle
     * the @"AXEnhancedUserInterface" accessibility attribute to detect VoiceOver state changes and on Windows the
     * client can handle WM_GETOBJECT with OBJID_CLIENT to detect accessibility readers. For windowless browsers
     * accessibility will be enabled in TreeOnly mode (which corresponds to kAccessibilityModeWebContentsOnly in
     * Chromium). In this mode renderer accessibility is enabled, the full tree is computed, and events are passed to
     * CefAccessibiltyHandler, but platform accessibility objects are not created. The client may implement platform
     * accessibility objects using CefAccessibiltyHandler callbacks if desired.
     */
    void setAccessibilityState(@Nonnull CefState accessibilityState);

    /**
     * Enable notifications of auto resize via CefDisplayHandler::OnAutoResize. Notifications are disabled by default.
     * |min_size| and |max_size| define the range of allowed sizes.
     */
    void setAutoResizeEnabled(boolean enabled, @Nonnull CefSize minSize, @Nonnull CefSize maxSize);

    /** Set whether the browser's audio is muted. */
    void setAudioMuted(boolean mute);

    /** Returns true if the browser's audio is muted. This method can only be called on the UI thread. */
    boolean isAudioMuted();

    /**
     * Returns true if the renderer is currently in browser fullscreen. This differs from window fullscreen in that
     * browser fullscreen is entered using the JavaScript Fullscreen API and modifies CSS attributes such as the
     * ::backdrop pseudo-element and :fullscreen pseudo-class. This method can only be called on the UI thread.
     */
    boolean isFullscreen();

    /**
     * Requests the renderer to exit browser fullscreen. In most cases exiting window fullscreen should also exit
     * browser fullscreen. With Alloy style this method should be called in response to a user action such as clicking
     * the green traffic light button on MacOS (CefWindowDelegate::OnWindowFullscreenTransition callback) or pressing
     * the "ESC" key (CefKeyboardHandler::OnPreKeyEvent callback). With Chrome style these standard exit actions are
     * handled internally but new/additional user actions can use this method. Set |will_cause_resize| to true if
     * exiting browser fullscreen will cause a view resize.
     */
    void exitFullscreen(boolean willCauseResize);

    /**
     * Returns true if a Chrome command is supported and enabled. Use the cef_id_for_command_id_name() function for
     * version-safe mapping of command IDC names from cef_command_ids.h to version-specific numerical |command_id|
     * values. This method can only be called on the UI thread. Only used with Chrome style.
     */
    boolean canExecuteChromeCommand(int commandId);

    /**
     * Execute a Chrome command. Use the cef_id_for_command_id_name() function for version-safe mapping of command IDC
     * names from cef_command_ids.h to version-specific numerical |command_id| values. |disposition| provides
     * information about the intended command target. Only used with Chrome style.
     */
    void executeChromeCommand(int commandId, @Nonnull CefWindowOpenDisposition disposition);

    /**
     * Returns true if the render process associated with this browser is currently unresponsive as indicated by a lack
     * of input event processing for at least 15 seconds. To receive associated state change notifications and
     * optionally handle an unresponsive render process implement CefRequestHandler::OnRenderProcessUnresponsive. This
     * method can only be called on the UI thread.
     */
    boolean isRenderProcessUnresponsive();

    /**
     * Returns the runtime style for this browser (ALLOY or CHROME). See cef_runtime_style_t documentation for details.
     *
     * @return the result, or {@code CEF_RUNTIME_STYLE_DEFAULT} for default handling
     */
    CefRuntimeStyle getRuntimeStyle();

    /**
     * Enable or disable CDP accessibility tree viewport collapse for this browser. When enabled, off-screen landmarks
     * and headings are serialized as summaries and other off-screen nodes are pruned. Overrides the
     * CefBrowserSettings.ax_viewport_collapse value. If called on the UI thread the change will be applied immediately.
     * Otherwise, the change will be applied asynchronously on the UI thread. WARNING: This collapses the CDP
     * accessibility tree and disables CDP dynamic tree updates (nodesUpdated events). The DevTools Accessibility panel
     * will show an incomplete tree. Platform screen readers (NVDA, JAWS, VoiceOver) are unaffected — they use a
     * separate code path.
     *
     * <p>Added in CEF API version experimental.
     */
    void setAxViewportCollapse(boolean enabled);

    static class NativePeer implements CefBrowserHost {
        private volatile long nativePtr;

        @Override
        public long getBrowser() {
            return N_GetBrowser(nativePtr);
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
        public long getClient() {
            return N_GetClient(nativePtr);
        }

        @Override
        public long getRequestContext() {
            return N_GetRequestContext(nativePtr);
        }

        @Override
        public boolean canZoom(CefZoomCommand command) {
            return N_CanZoom(nativePtr, command);
        }

        @Override
        public void zoom(CefZoomCommand command) {
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
        public void setZoomLevel(double zoomLevel) {
            N_SetZoomLevel(nativePtr, zoomLevel);
        }

        @Override
        public void runFileDialog(
                CefFileDialogMode mode,
                String title,
                String defaultFilePath,
                java.util.List<String> acceptFilters,
                long callback) {
            N_RunFileDialog(nativePtr, mode, title, defaultFilePath, acceptFilters, callback);
        }

        @Override
        public void startDownload(String url) {
            N_StartDownload(nativePtr, url);
        }

        @Override
        public void downloadImage(
                String imageUrl, boolean isFavicon, int maxImageSize, boolean bypassCache, long callback) {
            N_DownloadImage(nativePtr, imageUrl, isFavicon, maxImageSize, bypassCache, callback);
        }

        @Override
        public void print() {
            N_Print(nativePtr);
        }

        @Override
        public void printToPdf(String path, long settings, long callback) {
            N_PrintToPdf(nativePtr, path, settings, callback);
        }

        @Override
        public void find(String searchText, boolean forward, boolean matchCase, boolean findNext) {
            N_Find(nativePtr, searchText, forward, matchCase, findNext);
        }

        @Override
        public void stopFinding(boolean clearSelection) {
            N_StopFinding(nativePtr, clearSelection);
        }

        @Override
        public void showDevTools(long windowInfo, long client, long settings, CefPoint inspectElementAt) {
            N_ShowDevTools(nativePtr, windowInfo, client, settings, inspectElementAt);
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
        public boolean sendDevToolsMessage(long message, long messageSize) {
            return N_SendDevToolsMessage(nativePtr, message, messageSize);
        }

        @Override
        public int executeDevToolsMethod(int messageId, String method, long params) {
            return N_ExecuteDevToolsMethod(nativePtr, messageId, method, params);
        }

        @Override
        public long addDevToolsMessageObserver(long observer) {
            return N_AddDevToolsMessageObserver(nativePtr, observer);
        }

        @Override
        public void getNavigationEntries(long visitor, boolean currentOnly) {
            N_GetNavigationEntries(nativePtr, visitor, currentOnly);
        }

        @Override
        public void replaceMisspelling(String word) {
            N_ReplaceMisspelling(nativePtr, word);
        }

        @Override
        public void addWordToDictionary(String word) {
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
        public void invalidate(CefPaintElementType type) {
            N_Invalidate(nativePtr, type);
        }

        @Override
        public void sendExternalBeginFrame() {
            N_SendExternalBeginFrame(nativePtr);
        }

        @Override
        public void sendKeyEvent(long event) {
            N_SendKeyEvent(nativePtr, event);
        }

        @Override
        public void sendMouseClickEvent(long event, CefMouseButtonType type, boolean mouseUp, int clickCount) {
            N_SendMouseClickEvent(nativePtr, event, type, mouseUp, clickCount);
        }

        @Override
        public void sendMouseMoveEvent(long event, boolean mouseLeave) {
            N_SendMouseMoveEvent(nativePtr, event, mouseLeave);
        }

        @Override
        public void sendMouseWheelEvent(long event, int deltaX, int deltaY) {
            N_SendMouseWheelEvent(nativePtr, event, deltaX, deltaY);
        }

        @Override
        public void sendTouchEvent(long event) {
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
                String text,
                long underlinesCount,
                long underlines,
                CefRange replacementRange,
                CefRange selectionRange) {
            N_ImeSetComposition(nativePtr, text, underlinesCount, underlines, replacementRange, selectionRange);
        }

        @Override
        public void imeCommitText(String text, CefRange replacementRange, int relativeCursorPos) {
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
        public void dragTargetDragEnter(long dragData, long event, CefDragOperationsMask allowedOps) {
            N_DragTargetDragEnter(nativePtr, dragData, event, allowedOps);
        }

        @Override
        public void dragTargetDragOver(long event, CefDragOperationsMask allowedOps) {
            N_DragTargetDragOver(nativePtr, event, allowedOps);
        }

        @Override
        public void dragTargetDragLeave() {
            N_DragTargetDragLeave(nativePtr);
        }

        @Override
        public void dragTargetDrop(long event) {
            N_DragTargetDrop(nativePtr, event);
        }

        @Override
        public void dragSourceEndedAt(int x, int y, CefDragOperationsMask op) {
            N_DragSourceEndedAt(nativePtr, x, y, op);
        }

        @Override
        public void dragSourceSystemDragEnded() {
            N_DragSourceSystemDragEnded(nativePtr);
        }

        @Override
        public long getVisibleNavigationEntry() {
            return N_GetVisibleNavigationEntry(nativePtr);
        }

        @Override
        public void setAccessibilityState(CefState accessibilityState) {
            N_SetAccessibilityState(nativePtr, accessibilityState);
        }

        @Override
        public void setAutoResizeEnabled(boolean enabled, CefSize minSize, CefSize maxSize) {
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
        public void executeChromeCommand(int commandId, CefWindowOpenDisposition disposition) {
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

        private native long N_GetBrowser(long self);

        private native void N_CloseBrowser(long self, boolean forceClose);

        private native boolean N_TryCloseBrowser(long self);

        private native boolean N_IsReadyToBeClosed(long self);

        private native void N_SetFocus(long self, boolean focus);

        private native long N_GetWindowHandle(long self);

        private native long N_GetOpenerWindowHandle(long self);

        private native int N_GetOpenerIdentifier(long self);

        private native boolean N_HasView(long self);

        private native long N_GetClient(long self);

        private native long N_GetRequestContext(long self);

        private native boolean N_CanZoom(long self, CefZoomCommand command);

        private native void N_Zoom(long self, CefZoomCommand command);

        private native double N_GetDefaultZoomLevel(long self);

        private native double N_GetZoomLevel(long self);

        private native void N_SetZoomLevel(long self, double zoomLevel);

        private native void N_RunFileDialog(
                long self,
                CefFileDialogMode mode,
                String title,
                String defaultFilePath,
                java.util.List<String> acceptFilters,
                long callback);

        private native void N_StartDownload(long self, String url);

        private native void N_DownloadImage(
                long self, String imageUrl, boolean isFavicon, int maxImageSize, boolean bypassCache, long callback);

        private native void N_Print(long self);

        private native void N_PrintToPdf(long self, String path, long settings, long callback);

        private native void N_Find(long self, String searchText, boolean forward, boolean matchCase, boolean findNext);

        private native void N_StopFinding(long self, boolean clearSelection);

        private native void N_ShowDevTools(
                long self, long windowInfo, long client, long settings, CefPoint inspectElementAt);

        private native void N_CloseDevTools(long self);

        private native boolean N_HasDevTools(long self);

        private native boolean N_SendDevToolsMessage(long self, long message, long messageSize);

        private native int N_ExecuteDevToolsMethod(long self, int messageId, String method, long params);

        private native long N_AddDevToolsMessageObserver(long self, long observer);

        private native void N_GetNavigationEntries(long self, long visitor, boolean currentOnly);

        private native void N_ReplaceMisspelling(long self, String word);

        private native void N_AddWordToDictionary(long self, String word);

        private native boolean N_IsWindowRenderingDisabled(long self);

        private native void N_WasResized(long self);

        private native void N_WasHidden(long self, boolean hidden);

        private native void N_NotifyScreenInfoChanged(long self);

        private native void N_Invalidate(long self, CefPaintElementType type);

        private native void N_SendExternalBeginFrame(long self);

        private native void N_SendKeyEvent(long self, long event);

        private native void N_SendMouseClickEvent(
                long self, long event, CefMouseButtonType type, boolean mouseUp, int clickCount);

        private native void N_SendMouseMoveEvent(long self, long event, boolean mouseLeave);

        private native void N_SendMouseWheelEvent(long self, long event, int deltaX, int deltaY);

        private native void N_SendTouchEvent(long self, long event);

        private native void N_SendCaptureLostEvent(long self);

        private native void N_NotifyMoveOrResizeStarted(long self);

        private native int N_GetWindowlessFrameRate(long self);

        private native void N_SetWindowlessFrameRate(long self, int frameRate);

        private native void N_ImeSetComposition(
                long self,
                String text,
                long underlinesCount,
                long underlines,
                CefRange replacementRange,
                CefRange selectionRange);

        private native void N_ImeCommitText(long self, String text, CefRange replacementRange, int relativeCursorPos);

        private native void N_ImeFinishComposingText(long self, boolean keepSelection);

        private native void N_ImeCancelComposition(long self);

        private native void N_DragTargetDragEnter(
                long self, long dragData, long event, CefDragOperationsMask allowedOps);

        private native void N_DragTargetDragOver(long self, long event, CefDragOperationsMask allowedOps);

        private native void N_DragTargetDragLeave(long self);

        private native void N_DragTargetDrop(long self, long event);

        private native void N_DragSourceEndedAt(long self, int x, int y, CefDragOperationsMask op);

        private native void N_DragSourceSystemDragEnded(long self);

        private native long N_GetVisibleNavigationEntry(long self);

        private native void N_SetAccessibilityState(long self, CefState accessibilityState);

        private native void N_SetAutoResizeEnabled(long self, boolean enabled, CefSize minSize, CefSize maxSize);

        private native void N_SetAudioMuted(long self, boolean mute);

        private native boolean N_IsAudioMuted(long self);

        private native boolean N_IsFullscreen(long self);

        private native void N_ExitFullscreen(long self, boolean willCauseResize);

        private native boolean N_CanExecuteChromeCommand(long self, int commandId);

        private native void N_ExecuteChromeCommand(long self, int commandId, CefWindowOpenDisposition disposition);

        private native boolean N_IsRenderProcessUnresponsive(long self);

        private native CefRuntimeStyle N_GetRuntimeStyle(long self);

        private native void N_SetAxViewportCollapse(long self, boolean enabled);

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
