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
import net.kurobako.cef4j.gen.CefProcessId;
import net.kurobako.cef4j.gen.CefProcessMessage;
import net.kurobako.cef4j.gen.CefQuickMenuEditStateFlags;
import net.kurobako.cef4j.gen.CefRect;
import net.kurobako.cef4j.gen.CefRenderHandler;
import net.kurobako.cef4j.gen.CefRunContextMenuCallback;
import net.kurobako.cef4j.gen.CefRunQuickMenuCallback;
import net.kurobako.cef4j.gen.CefSize;
import net.kurobako.cef4j.gen.CefWindowInfo;
import net.kurobako.cef4j.gen.CefWindowOpenDisposition;
import net.kurobako.cef4j.gen.NativePointer;

@SuppressWarnings("resource")
final class CefWebViewClient implements CefClient {
    private final CefWebView view;
    private final CefRenderHandler renderHandler;
    private final CefLoadHandler scrollbarLoadHandler;

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
            public void onAfterCreated(CefBrowser browser) {
                view.onBrowserCreated(browser);
                refreshHistoryFromBrowser(browser);
            }

            @Override
            public boolean onBeforePopup(
                    CefBrowser browser,
                    CefFrame frame,
                    int popupId,
                    String targetUrl,
                    String targetFrameName,
                    @Nonnull CefWindowOpenDisposition targetDisposition,
                    boolean userGesture,
                    NativePointer popupFeatures,
                    @Nonnull CefWindowInfo.Mutable windowInfo,
                    AtomicReference<CefClient> clientRef,
                    @Nonnull CefBrowserSettings.Mutable settings,
                    AtomicReference<CefDictionaryValue> extraInfo,
                    int[] noJavascriptAccess) {
                return view.handleBeforePopup(windowInfo, clientRef);
            }

            @Override
            public void onBeforeClose(CefBrowser browser) {
                view.onBeforeBrowserClose();
            }
        });
    }

    @Override
    public Optional<CefLoadHandler> getLoadHandler() {
        return Optional.of(new CefLoadHandler() {
            @Override
            public void onLoadingStateChange(
                    CefBrowser browser, boolean isLoading, boolean canGoBack, boolean canGoForward) {
                Platform.runLater(() -> view.engine.updateLoadState(isLoading, canGoBack, canGoForward));
                refreshHistoryFromBrowser(browser);
            }

            @Override
            public void onLoadEnd(CefBrowser browser, CefFrame frame, int httpStatusCode) {
                if (!isMainFrame(frame)) return;
                scrollbarLoadHandler.onLoadEnd(browser, frame, httpStatusCode);
                Platform.runLater(() -> {
                    view.engine.markLoadFinished();
                    view.requestViewRefresh(false);
                });
                refreshHistoryFromBrowser(browser);
            }

            @Override
            public void onLoadError(
                    CefBrowser browser,
                    CefFrame frame,
                    @Nonnull CefErrorCode errorCode,
                    String errorText,
                    String failedUrl) {
                if (!isMainFrame(frame)) return;
                Platform.runLater(() -> view.engine.markLoadFailed(new RuntimeException(errorText)));
            }
        });
    }

    @Override
    public Optional<CefDisplayHandler> getDisplayHandler() {
        return Optional.of(new CefDisplayHandler() {
            @Override
            public void onTitleChange(CefBrowser browser, String title) {
                Platform.runLater(() -> view.engine.updateTitle(title));
            }

            @Override
            public void onAddressChange(CefBrowser browser, CefFrame frame, String url) {
                if (!isMainFrame(frame)) return;
                Platform.runLater(() -> view.engine.updateLocation(url));
            }

            @Override
            public void onLoadingProgressChange(CefBrowser browser, double progress) {
                Platform.runLater(() -> view.engine.updateLoadProgress(progress));
            }

            @Override
            public boolean onConsoleMessage(
                    CefBrowser browser, @Nonnull CefLogSeverity level, String message, String source, int line) {
                return false;
            }

            @Override
            public void onStatusMessage(CefBrowser browser, String value) {
                Platform.runLater(() -> view.engine.fireStatusChanged(value != null ? value : ""));
            }

            @Override
            public boolean onAutoResize(CefBrowser browser, @Nonnull CefSize newSize) {
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

            @Override
            public boolean onContentsBoundsChange(CefBrowser browser, @Nonnull CefRect newBounds) {
                view.updateDetachedBounds(newBounds, true);
                view.requestViewRefresh(true);
                return true;
            }

            @Override
            public boolean onCursorChange(
                    CefBrowser browser, long cursor, @Nonnull CefCursorType type, NativePointer customCursorInfo) {
                Cursor jfxCursor = view.mapCursor(type);
                Platform.runLater(() -> view.setCursor(jfxCursor));
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
                    @Nullable int[] suppressMessage) {
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
                    CefBrowser browser, String messageText, boolean isReload, CefJsDialogCallback callback) {
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
            String label = model.getLabel(commandId).orElse("").replace("&", "");
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

    private static boolean isMainFrame(@Nullable CefFrame frame) {
        return frame != null && frame.isMain();
    }

    private void refreshHistoryFromBrowser(CefBrowser browser) {
        if (view.engine.shouldSuppressNavigationHistory()) {
            Platform.runLater(() -> view.engine.refreshHistory(List.of(), 0));
            return;
        }
        var host = browser != null ? browser.getHost().orElse(null) : null;
        if (host == null) return;
        List<CefWebHistory.EntrySnapshot> snapshots = new ArrayList<>();
        final int[] currentIndex = {-1};
        host.getNavigationEntries(
                new CefNavigationEntryVisitor() {
                    @Override
                    public boolean visit(CefNavigationEntry entry, boolean current, int index, int total) {
                        snapshots.add(new CefWebHistory.EntrySnapshot(
                                entry != null ? entry.getUrl().orElse("") : "",
                                entry != null ? entry.getTitle().orElse("") : "",
                                new Date()));
                        if (current) currentIndex[0] = index;
                        if (index + 1 == total) {
                            Platform.runLater(() -> view.engine.refreshHistory(snapshots, currentIndex[0]));
                        }
                        return true;
                    }
                },
                false);
    }
}
