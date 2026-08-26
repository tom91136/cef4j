package net.kurobako.cef4j.osr.jfx;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.gen.CefBrowser;
import net.kurobako.cef4j.gen.CefBrowserSettings;
import net.kurobako.cef4j.gen.CefClient;
import net.kurobako.cef4j.gen.CefContextMenuHandler;
import net.kurobako.cef4j.gen.CefContextMenuParams;
import net.kurobako.cef4j.gen.CefCursorInfo;
import net.kurobako.cef4j.gen.CefCursorType;
import net.kurobako.cef4j.gen.CefDictionaryValue;
import net.kurobako.cef4j.gen.CefDisplayHandler;
import net.kurobako.cef4j.gen.CefErrorCode;
import net.kurobako.cef4j.gen.CefEventFlags;
import net.kurobako.cef4j.gen.CefFocusHandler;
import net.kurobako.cef4j.gen.CefFrame;
import net.kurobako.cef4j.gen.CefJsDialogCallback;
import net.kurobako.cef4j.gen.CefJsDialogHandler;
import net.kurobako.cef4j.gen.CefJsDialogType;
import net.kurobako.cef4j.gen.CefLifeSpanHandler;
import net.kurobako.cef4j.gen.CefLoadHandler;
import net.kurobako.cef4j.gen.CefLogSeverity;
import net.kurobako.cef4j.gen.CefMenuItemType;
import net.kurobako.cef4j.gen.CefMenuModel;
import net.kurobako.cef4j.gen.CefNavigationEntry;
import net.kurobako.cef4j.gen.CefNavigationEntryVisitor;
import net.kurobako.cef4j.gen.CefPoint;
import net.kurobako.cef4j.gen.CefPopupFeatures;
import net.kurobako.cef4j.gen.CefProcessId;
import net.kurobako.cef4j.gen.CefProcessMessage;
import net.kurobako.cef4j.gen.CefQuickMenuEditStateFlags;
import net.kurobako.cef4j.gen.CefRect;
import net.kurobako.cef4j.gen.CefRenderHandler;
import net.kurobako.cef4j.gen.CefRunContextMenuCallback;
import net.kurobako.cef4j.gen.CefRunQuickMenuCallback;
import net.kurobako.cef4j.gen.CefSize;
import net.kurobako.cef4j.gen.CefTransitionType;
import net.kurobako.cef4j.gen.CefWindowInfo;
import net.kurobako.cef4j.gen.CefWindowOpenDisposition;

@SuppressWarnings("resource")
final class CefWebViewClient implements CefClient {
    private final CefWebView view;
    private final CefRenderHandler renderHandler;
    private final CefLoadHandler scrollbarLoadHandler;
    private String lastHistoryFingerprint = "";

    CefWebViewClient(CefWebView view) {
        this.view = view;
        this.renderHandler = view.createRenderHandler();
        this.scrollbarLoadHandler = view.createScrollbarLoadHandler();
    }

    @Override
    public Optional<CefRenderHandler> getRenderHandler() {
        return Optional.of(renderHandler);
    }

    @Override
    public Optional<CefFocusHandler> getFocusHandler() {
        return Optional.of(new CefFocusHandler() {
            @Override
            public void onGotFocus(@Nullable CefBrowser browser) {
                Platform.runLater(() -> {
                    if (!view.isFocused()) view.requestFocus();
                });
            }
        });
    }

    @Override
    public Optional<CefLifeSpanHandler> getLifeSpanHandler() {
        return Optional.of(new CefLifeSpanHandler() {
            @Override
            public void onAfterCreated(@Nullable CefBrowser browser) {
                view.onBrowserCreated(browser);
                refreshHistoryFromBrowser(browser);
            }

            @SuppressWarnings({"MissingOverride", "UnusedVariable", "UnusedMethod", "EffectivelyPrivate"})
            public boolean onBeforePopup(
                    @Nullable CefBrowser browser,
                    @Nullable CefFrame frame,
                    int popupId,
                    @Nullable String targetUrl,
                    @Nullable String targetFrameName,
                    @Nonnull CefWindowOpenDisposition targetDisposition,
                    boolean userGesture,
                    @Nullable CefPopupFeatures popupFeatures,
                    @Nonnull CefWindowInfo.Mutable windowInfo,
                    @Nullable AtomicReference<CefClient> clientRef,
                    @Nonnull CefBrowserSettings.Mutable settings,
                    @Nullable AtomicReference<CefDictionaryValue> extraInfo,
                    int[] noJavascriptAccess) {
                return view.handleBeforePopup(windowInfo, clientRef);
            }

            @SuppressWarnings({"MissingOverride", "UnusedVariable", "UnusedMethod", "EffectivelyPrivate"})
            public boolean onBeforePopup(
                    @Nullable CefBrowser browser,
                    @Nullable CefFrame frame,
                    @Nullable String targetUrl,
                    @Nullable String targetFrameName,
                    @Nonnull CefWindowOpenDisposition targetDisposition,
                    boolean userGesture,
                    @Nullable CefPopupFeatures popupFeatures,
                    @Nonnull CefWindowInfo.Mutable windowInfo,
                    @Nullable AtomicReference<CefClient> clientRef,
                    @Nonnull CefBrowserSettings.Mutable settings,
                    @Nullable AtomicReference<CefDictionaryValue> extraInfo,
                    int[] noJavascriptAccess) {
                return view.handleBeforePopup(windowInfo, clientRef);
            }

            @Override
            public void onBeforeClose(@Nullable CefBrowser browser) {
                view.onBeforeBrowserClose();
            }
        });
    }

    @Override
    public Optional<CefLoadHandler> getLoadHandler() {
        return Optional.of(new CefLoadHandler() {
            @Override
            public void onLoadStart(
                    @Nullable CefBrowser browser, @Nullable CefFrame frame, @Nonnull CefTransitionType transitionType) {
                if (isMainFrame(frame)) {
                    view.scriptEngine.cancelPending("page navigation replaced the renderer context");
                }
            }

            @Override
            public void onLoadingStateChange(
                    @Nullable CefBrowser browser, boolean isLoading, boolean canGoBack, boolean canGoForward) {
                Platform.runLater(() -> view.engine.updateLoadState(isLoading, canGoBack, canGoForward));
                refreshHistoryFromBrowser(browser);
            }

            @Override
            public void onLoadEnd(@Nullable CefBrowser browser, @Nullable CefFrame frame, int httpStatusCode) {
                if (!isMainFrame(frame)) return;
                scrollbarLoadHandler.onLoadEnd(browser, frame, httpStatusCode);
                String completedUrl = frame == null ? null : frame.getUrl().orElse(null);
                Platform.runLater(() -> {
                    view.engine.markLoadFinished(completedUrl);
                    view.requestViewRefresh(false);
                });
                refreshHistoryFromBrowser(browser);
            }

            @Override
            public void onLoadError(
                    @Nullable CefBrowser browser,
                    @Nullable CefFrame frame,
                    @Nonnull CefErrorCode errorCode,
                    @Nullable String errorText,
                    @Nullable String failedUrl) {
                if (!isMainFrame(frame)) return;
                Platform.runLater(() -> view.engine.markLoadFailed(failedUrl, new RuntimeException(errorText)));
            }
        });
    }

    @Override
    public Optional<CefDisplayHandler> getDisplayHandler() {
        return Optional.of(new CefDisplayHandler() {
            @Override
            public void onTitleChange(@Nullable CefBrowser browser, @Nullable String title) {
                Platform.runLater(() -> view.engine.updateTitle(title));
            }

            @Override
            public void onAddressChange(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nullable String url) {
                if (!isMainFrame(frame)) return;
                Platform.runLater(() -> view.engine.updateLocation(url));
            }

            @Override
            public void onLoadingProgressChange(@Nullable CefBrowser browser, double progress) {
                Platform.runLater(() -> view.engine.updateLoadProgress(progress));
            }

            @Override
            public boolean onConsoleMessage(
                    @Nullable CefBrowser browser,
                    @Nonnull CefLogSeverity level,
                    @Nullable String message,
                    @Nullable String source,
                    int line) {
                return false;
            }

            @Override
            public void onStatusMessage(@Nullable CefBrowser browser, @Nullable String value) {
                Platform.runLater(() -> view.engine.fireStatusChanged(Objects.requireNonNullElse(value, "")));
            }

            @Override
            public boolean onAutoResize(@Nullable CefBrowser browser, @Nonnull CefSize newSize) {
                Rectangle2D currentBounds = view.detachedBounds;
                view.updateDetachedBounds(
                        new CefRect(
                                (int) Math.round(currentBounds.getMinX()),
                                (int) Math.round(currentBounds.getMinY()),
                                Math.max(1, newSize.width),
                                Math.max(1, newSize.height)),
                        false);
                Platform.runLater(() -> view.engine.fireResized(new Rectangle2D(0, 0, newSize.width, newSize.height)));
                return false;
            }

            @SuppressWarnings({"MissingOverride", "UnusedVariable", "UnusedMethod", "EffectivelyPrivate"})
            public boolean onContentsBoundsChange(@Nullable CefBrowser browser, @Nonnull CefRect newBounds) {
                view.updateDetachedBounds(newBounds, true);
                view.requestViewRefresh(true);
                return true;
            }

            @SuppressWarnings({"MissingOverride", "UnusedVariable", "UnusedMethod", "EffectivelyPrivate"})
            public boolean onCursorChange(
                    @Nullable CefBrowser browser,
                    long cursor,
                    @Nonnull CefCursorType type,
                    @Nullable CefCursorInfo customCursorInfo) {
                return updateCursor(type);
            }

            @SuppressWarnings({"MissingOverride", "UnusedVariable", "UnusedMethod", "EffectivelyPrivate"})
            public boolean onCursorChange(
                    @Nullable CefBrowser browser,
                    int cursor,
                    @Nonnull CefCursorType type,
                    @Nullable CefCursorInfo customCursorInfo) {
                return updateCursor(type);
            }

            private boolean updateCursor(@Nonnull CefCursorType type) {
                Cursor jfxCursor = view.mapCursor(type);
                Platform.runLater(() -> {
                    if (view.getCursor() != jfxCursor) view.setCursor(jfxCursor);
                });
                return true;
            }
        });
    }

    @Override
    public Optional<CefContextMenuHandler> getContextMenuHandler() {
        return Optional.of(new CefContextMenuHandler() {
            @Override
            public boolean runContextMenu(
                    @Nullable CefBrowser browser,
                    @Nullable CefFrame frame,
                    @Nullable CefContextMenuParams params,
                    @Nullable CefMenuModel model,
                    @Nullable CefRunContextMenuCallback callback) {
                if (model == null || callback == null) return false;
                AtomicBoolean dispatched = new AtomicBoolean();
                List<MenuItem> items = buildMenuItems(model, callback, dispatched);
                int menuX = params != null ? params.getXCoord() : 0;
                int menuY = params != null ? params.getYCoord() : 0;
                if (items.isEmpty()) {
                    callback.cancel();
                    return true;
                }
                Platform.runLater(() -> {
                    view.hideContextMenu();
                    ContextMenu menu = new ContextMenu(items.toArray(new MenuItem[0]));
                    menu.setOnHidden(e -> {
                        if (dispatched.compareAndSet(false, true)) callback.cancel();
                        if (view.activeContextMenu == menu) view.activeContextMenu = null;
                    });
                    view.activeContextMenu = menu;
                    double screenX = 0;
                    double screenY = 0;
                    Point2D pt = view.localToScreen(menuX, menuY);
                    if (pt != null) {
                        screenX = pt.getX();
                        screenY = pt.getY();
                    }
                    menu.show(view, screenX, screenY);
                });
                return true;
            }

            @Override
            public boolean runQuickMenu(
                    @Nullable CefBrowser browser,
                    @Nullable CefFrame frame,
                    @Nonnull CefPoint location,
                    @Nonnull CefSize touchHandleSize,
                    @Nonnull CefQuickMenuEditStateFlags editStateFlags,
                    @Nullable CefRunQuickMenuCallback callback) {
                if (callback != null) callback.cancel();
                return true;
            }
        });
    }

    @Override
    public Optional<CefJsDialogHandler> getJsDialogHandler() {
        return Optional.of(new CefJsDialogHandler() {
            @Override
            public boolean onJsDialog(
                    @Nullable CefBrowser browser,
                    @Nullable String originUrl,
                    @Nonnull CefJsDialogType dialogType,
                    @Nullable String messageText,
                    @Nullable String defaultPromptText,
                    @Nullable CefJsDialogCallback callback,
                    int[] suppressMessage) {
                CefJsDialogType.Kind kind = dialogType.kind().orElse(CefJsDialogType.Kind.ALERT);
                switch (kind) {
                    case ALERT:
                        Platform.runLater(() -> view.engine.fireAlert(Objects.requireNonNullElse(messageText, "")));
                        if (callback != null) callback.cont(1, null);
                        return true;
                    case CONFIRM:
                        javafx.util.Callback<String, Boolean> confirm = view.engine.getConfirmHandler();
                        if (confirm == null) return false;
                        AtomicReference<Boolean> confirmResult = new AtomicReference<>(Boolean.FALSE);
                        view.runOnFxAndWait(
                                () -> confirmResult.set(confirm.call(Objects.requireNonNullElse(messageText, ""))));
                        if (callback != null) callback.cont(Boolean.TRUE.equals(confirmResult.get()) ? 1 : 0, null);
                        return true;
                    case PROMPT:
                        javafx.util.Callback<CefPromptData, String> prompt = view.engine.getPromptHandler();
                        if (prompt == null) return false;
                        AtomicReference<String> promptResult = new AtomicReference<>();
                        view.runOnFxAndWait(() -> promptResult.set(prompt.call(new CefPromptData(
                                Objects.requireNonNullElse(messageText, ""),
                                Objects.requireNonNullElse(defaultPromptText, "")))));
                        if (callback != null) callback.cont(promptResult.get() != null ? 1 : 0, promptResult.get());
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public boolean onBeforeUnloadDialog(
                    @Nullable CefBrowser browser,
                    @Nullable String messageText,
                    boolean isReload,
                    @Nullable CefJsDialogCallback callback) {
                javafx.util.Callback<String, Boolean> confirm = view.engine.getConfirmHandler();
                if (confirm == null) return false;
                AtomicReference<Boolean> confirmResult = new AtomicReference<>(Boolean.FALSE);
                view.runOnFxAndWait(() -> confirmResult.set(confirm.call(Objects.requireNonNullElse(messageText, ""))));
                if (callback != null) callback.cont(Boolean.TRUE.equals(confirmResult.get()) ? 1 : 0, null);
                return true;
            }
        });
    }

    @Override
    public boolean onProcessMessageReceived(
            @Nullable CefBrowser browser,
            @Nullable CefFrame frame,
            @Nonnull CefProcessId sourceProcess,
            @Nullable CefProcessMessage message) {
        return view.scriptEngine.handleMessage(browser, frame, sourceProcess, message);
    }

    private List<MenuItem> buildMenuItems(
            CefMenuModel model, CefRunContextMenuCallback callback, AtomicBoolean dispatched) {
        List<MenuItem> items = new ArrayList<>();
        long count = model.getCount();
        for (long i = 0; i < count; i++) {
            int commandId = model.getCommandIdAt(i);
            CefMenuItemType.Kind kind = model.getType(commandId).kind().orElse(CefMenuItemType.Kind.NONE);
            String label = stripMnemonic(model.getLabel(commandId).orElse(""));
            javafx.event.EventHandler<javafx.event.ActionEvent> fire = e -> {
                if (dispatched.compareAndSet(false, true)) {
                    view.hideContextMenu();
                    callback.cont(commandId, CefEventFlags.of(CefEventFlags.Kind.NONE));
                    view.restoreBrowserFocus();
                }
            };
            switch (kind) {
                case SEPARATOR:
                    items.add(new SeparatorMenuItem());
                    break;
                case SUBMENU:
                    model.getSubMenuAt(i).ifPresent(sub -> {
                        Menu menu = new Menu(label);
                        menu.getItems().addAll(buildMenuItems(sub, callback, dispatched));
                        items.add(menu);
                    });
                    break;
                case CHECK:
                    CheckMenuItem check = new CheckMenuItem(label);
                    check.setSelected(model.isChecked(commandId));
                    check.setDisable(!model.isEnabled(commandId));
                    check.setOnAction(fire);
                    items.add(check);
                    break;
                default:
                    if (label.isEmpty() && kind == CefMenuItemType.Kind.NONE) break;
                    MenuItem item = new MenuItem(label);
                    item.setDisable(!model.isEnabled(commandId));
                    item.setOnAction(fire);
                    items.add(item);
                    break;
            }
        }
        return items;
    }

    private static String stripMnemonic(String label) {
        if (label.indexOf('&') < 0) return label;
        StringBuilder sb = new StringBuilder(label.length());
        int i = 0;
        while (i < label.length()) {
            char c = label.charAt(i);
            if (c == '&' && i + 1 < label.length() && label.charAt(i + 1) == '&') {
                sb.append('&');
                i += 2;
            } else if (c == '&') {
                i++;
            } else {
                sb.append(c);
                i++;
            }
        }
        return sb.toString();
    }

    private static boolean isMainFrame(@Nullable CefFrame frame) {
        return frame != null && frame.isMain();
    }

    @SuppressWarnings("JavaUtilDate")
    private void refreshHistoryFromBrowser(@Nullable CefBrowser browser) {
        if (view.engine.shouldSuppressNavigationHistory()) {
            if (!"suppressed".equals(lastHistoryFingerprint)) {
                lastHistoryFingerprint = "suppressed";
                Platform.runLater(() -> view.engine.refreshHistory(List.of(), 0));
            }
            return;
        }
        var host = browser != null ? browser.getHost().orElse(null) : null;
        if (host == null) return;
        List<CefWebHistory.EntrySnapshot> snapshots = new ArrayList<>();
        final int[] currentIndex = {-1};
        StringBuilder fingerprint = new StringBuilder();
        host.getNavigationEntries(
                new CefNavigationEntryVisitor() {
                    @Override
                    public boolean visit(@Nullable CefNavigationEntry entry, boolean current, int index, int total) {
                        String url = entry != null ? entry.getUrl().orElse("") : "";
                        String title = entry != null ? entry.getTitle().orElse("") : "";
                        snapshots.add(new CefWebHistory.EntrySnapshot(
                                url, title, completionDate(entry != null ? entry.getCompletionTime().val : 0)));
                        fingerprint.append(url).append('\u0001').append(title).append('\u0002');
                        if (current) currentIndex[0] = index;
                        if (index + 1 == total) {
                            String fp = currentIndex[0] + "|" + fingerprint;
                            if (!fp.equals(lastHistoryFingerprint)) {
                                lastHistoryFingerprint = fp;
                                Platform.runLater(() -> view.engine.refreshHistory(snapshots, currentIndex[0]));
                            }
                        }
                        return true;
                    }
                },
                false);
    }

    @SuppressWarnings("JavaUtilDate")
    static Date completionDate(long cefMicroseconds) {
        if (cefMicroseconds == 0) return new Date(0);
        return new Date(Math.floorDiv(cefMicroseconds - 11_644_473_600_000_000L, 1_000L));
    }
}
