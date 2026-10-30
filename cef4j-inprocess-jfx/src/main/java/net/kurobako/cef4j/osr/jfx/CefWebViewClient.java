package net.kurobako.cef4j.osr.jfx;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
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
import net.kurobako.cef4j.gen.CefListValue;
import net.kurobako.cef4j.gen.CefLoadHandler;
import net.kurobako.cef4j.gen.CefMenuItemType;
import net.kurobako.cef4j.gen.CefMenuModel;
import net.kurobako.cef4j.gen.CefNavigationEntry;
import net.kurobako.cef4j.gen.CefNavigationEntryVisitor;
import net.kurobako.cef4j.gen.CefPopupFeatures;
import net.kurobako.cef4j.gen.CefProcessId;
import net.kurobako.cef4j.gen.CefProcessMessage;
import net.kurobako.cef4j.gen.CefRect;
import net.kurobako.cef4j.gen.CefRenderHandler;
import net.kurobako.cef4j.gen.CefRunContextMenuCallback;
import net.kurobako.cef4j.gen.CefTransitionType;
import net.kurobako.cef4j.gen.CefWindowInfo;
import net.kurobako.cef4j.gen.CefWindowOpenDisposition;
import net.kurobako.cef4j.policy.NullableBoundary;
import org.jspecify.annotations.Nullable;

@SuppressWarnings("resource")
final class CefWebViewClient implements CefClient {
    private static final String LEGACY_CONTENTS_BOUNDS_MESSAGE = "cef4j:contents-bounds";
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
                    CefWindowOpenDisposition targetDisposition,
                    boolean userGesture,
                    @Nullable CefPopupFeatures popupFeatures,
                    CefWindowInfo.Mutable windowInfo,
                    @Nullable AtomicReference<CefClient> clientRef,
                    CefBrowserSettings.Mutable settings,
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
                    CefWindowOpenDisposition targetDisposition,
                    boolean userGesture,
                    @Nullable CefPopupFeatures popupFeatures,
                    CefWindowInfo.Mutable windowInfo,
                    @Nullable AtomicReference<CefClient> clientRef,
                    CefBrowserSettings.Mutable settings,
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
                    @Nullable CefBrowser browser, @Nullable CefFrame frame, CefTransitionType transitionType) {
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
                final String completedUrl =
                        frame == null ? null : frame.getUrl().orElse(null);
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
                    CefErrorCode errorCode,
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
            public void onStatusMessage(@Nullable CefBrowser browser, @Nullable String value) {
                Platform.runLater(() -> view.engine.fireStatusChanged(Objects.requireNonNullElse(value, "")));
            }

            @SuppressWarnings({"MissingOverride", "UnusedVariable", "UnusedMethod", "EffectivelyPrivate"})
            public boolean onContentsBoundsChange(@Nullable CefBrowser browser, CefRect newBounds) {
                view.updateDetachedBounds(newBounds, true);
                view.requestViewRefresh(true);
                return true;
            }

            @SuppressWarnings({"MissingOverride", "UnusedVariable", "UnusedMethod", "EffectivelyPrivate"})
            public boolean onCursorChange(
                    @Nullable CefBrowser browser,
                    long cursor,
                    CefCursorType type,
                    @Nullable CefCursorInfo customCursorInfo) {
                return updateCursor(type);
            }

            @SuppressWarnings({"MissingOverride", "UnusedVariable", "UnusedMethod", "EffectivelyPrivate"})
            public boolean onCursorChange(
                    @Nullable CefBrowser browser,
                    int cursor,
                    CefCursorType type,
                    @Nullable CefCursorInfo customCursorInfo) {
                return updateCursor(type);
            }

            private boolean updateCursor(CefCursorType type) {
                final Cursor jfxCursor = view.mapCursor(type);
                Platform.runLater(() -> {
                    if (view.getCursor() != jfxCursor) view.setCursor(jfxCursor);
                });
                return true;
            }
        });
    }

    @Override
    public Optional<CefContextMenuHandler> getContextMenuHandler() {
        final CefContextMenuHandler handler = new CefContextMenuHandler() {
            @Override
            public boolean runContextMenu(
                    @Nullable CefBrowser browser,
                    @Nullable CefFrame frame,
                    @Nullable CefContextMenuParams params,
                    @Nullable CefMenuModel model,
                    @Nullable CefRunContextMenuCallback callback) {
                if (model == null || callback == null) return false;
                if (!view.contextMenuAllowed) {
                    callback.cancel();
                    return true;
                }
                final AtomicBoolean dispatched = new AtomicBoolean();
                final List<MenuItem> items = buildMenuItems(model, callback, dispatched);
                if (items.isEmpty()) {
                    callback.cancel();
                    return true;
                }
                final int menuX = params != null ? params.getXCoord() : 0;
                final int menuY = params != null ? params.getYCoord() : 0;
                Platform.runLater(() -> {
                    view.hideContextMenu();
                    final ContextMenu menu = new ContextMenu(items.toArray(new MenuItem[0]));
                    menu.setOnHidden(e -> {
                        if (dispatched.compareAndSet(false, true)) callback.cancel();
                        if (view.activeContextMenu == menu) view.activeContextMenu = null;
                    });
                    view.activeContextMenu = menu;
                    double screenX = 0;
                    double screenY = 0;
                    final Point2D pt = view.localToScreen(menuX, menuY);
                    if (pt != null) {
                        screenX = pt.getX();
                        screenY = pt.getY();
                    }
                    menu.show(view, screenX, screenY);
                });
                return true;
            }
        };
        return Optional.of(withQuickMenuCompatibility(handler));
    }

    @Override
    public Optional<CefJsDialogHandler> getJsDialogHandler() {
        return Optional.of(new CefJsDialogHandler() {
            @Override
            public boolean onJsDialog(
                    @Nullable CefBrowser browser,
                    @Nullable String originUrl,
                    CefJsDialogType dialogType,
                    @Nullable String messageText,
                    @Nullable String defaultPromptText,
                    @Nullable CefJsDialogCallback callback,
                    int[] suppressMessage) {
                final CefJsDialogType.Kind kind = dialogType.kind().orElse(CefJsDialogType.Kind.ALERT);
                switch (kind) {
                    case ALERT:
                        Platform.runLater(() -> view.engine.fireAlert(Objects.requireNonNullElse(messageText, "")));
                        if (callback != null) callback.cont(1, null);
                        return true;
                    case CONFIRM:
                        return handleConfirm(messageText, callback);
                    case PROMPT:
                        return Optional.ofNullable(view.engine.getPromptHandler())
                                .map(prompt -> {
                                    final AtomicReference<@Nullable String> promptResult = new AtomicReference<>();
                                    view.runOnFxAndWait(() -> promptResult.set(prompt.call(new CefPromptData(
                                            Objects.requireNonNullElse(messageText, ""),
                                            Objects.requireNonNullElse(defaultPromptText, "")))));
                                    if (callback != null) {
                                        callback.cont(promptResult.get() != null ? 1 : 0, promptResult.get());
                                    }
                                    return true;
                                })
                                .orElse(false);
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
                return handleConfirm(messageText, callback);
            }
        });
    }

    private boolean handleConfirm(@Nullable String messageText, @Nullable CefJsDialogCallback callback) {
        return Optional.ofNullable(view.engine.getConfirmHandler())
                .map(confirm -> {
                    final AtomicReference<Boolean> confirmResult = new AtomicReference<>(Boolean.FALSE);
                    view.runOnFxAndWait(
                            () -> confirmResult.set(confirm.call(Objects.requireNonNullElse(messageText, ""))));
                    if (callback != null) {
                        callback.cont(Boolean.TRUE.equals(confirmResult.get()) ? 1 : 0, null);
                    }
                    return true;
                })
                .orElse(false);
    }

    @NullableBoundary("CEF client callbacks are nullable across supported ABI versions")
    @SuppressWarnings({"MissingOverride", "UnusedMethod"})
    public boolean onProcessMessageReceived(
            @Nullable CefBrowser browser, CefProcessId sourceProcess, @Nullable CefProcessMessage message) {
        if (handleLegacyContentsBounds(message)) return true;
        return view.scriptEngine.handleMessage(browser, null, sourceProcess, message);
    }

    @NullableBoundary("CEF client callbacks are nullable across supported ABI versions")
    @SuppressWarnings({"MissingOverride", "UnusedMethod"})
    public boolean onProcessMessageReceived(
            @Nullable CefBrowser browser,
            @Nullable CefFrame frame,
            CefProcessId sourceProcess,
            @Nullable CefProcessMessage message) {
        if (handleLegacyContentsBounds(message)) return true;
        return view.scriptEngine.handleMessage(browser, frame, sourceProcess, message);
    }

    private boolean handleLegacyContentsBounds(@Nullable CefProcessMessage message) {
        if (!view.popupBrowser || message == null) return false;
        if (!LEGACY_CONTENTS_BOUNDS_MESSAGE.equals(message.getName().orElse(null))) return false;
        final CefListValue arguments = message.getArgumentList().orElse(null);
        if (arguments == null) return true;
        try (arguments) {
            view.updateDetachedBounds(
                    new CefRect(arguments.getInt(0), arguments.getInt(1), arguments.getInt(2), arguments.getInt(3)),
                    true);
            view.requestViewRefresh(true);
        }
        return true;
    }

    private List<MenuItem> buildMenuItems(
            CefMenuModel model, CefRunContextMenuCallback callback, AtomicBoolean dispatched) {
        final List<MenuItem> items = new ArrayList<>();
        final long count = model.getCount();
        for (long i = 0; i < count; i++) {
            final int index = Math.toIntExact(i);
            final int commandId = model.getCommandIdAt(index);
            final CefMenuItemType.Kind kind = model.getType(commandId).kind().orElse(CefMenuItemType.Kind.NONE);
            final String label = stripMnemonic(model.getLabel(commandId).orElse(""));
            final javafx.event.EventHandler<javafx.event.ActionEvent> fire = e -> {
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
                    model.getSubMenuAt(index).ifPresent(sub -> {
                        final Menu menu = new Menu(label);
                        menu.getItems().addAll(buildMenuItems(sub, callback, dispatched));
                        items.add(menu);
                    });
                    break;
                case CHECK:
                    final CheckMenuItem check = new CheckMenuItem(label);
                    check.setSelected(model.isChecked(commandId));
                    check.setDisable(!model.isEnabled(commandId));
                    check.setOnAction(fire);
                    items.add(check);
                    break;
                default:
                    if (label.isEmpty() && kind == CefMenuItemType.Kind.NONE) break;
                    final MenuItem item = new MenuItem(label);
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
        final StringBuilder sb = new StringBuilder(label.length());
        int i = 0;
        while (i < label.length()) {
            final char c = label.charAt(i);
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
        final var host = browser != null ? browser.getHost().orElse(null) : null;
        if (host == null) return;
        final List<CefWebHistory.EntrySnapshot> snapshots = new ArrayList<>();
        final int[] currentIndex = {-1};
        final StringBuilder fingerprint = new StringBuilder();
        host.getNavigationEntries(
                new CefNavigationEntryVisitor() {
                    @Override
                    public boolean visit(@Nullable CefNavigationEntry entry, boolean current, int index, int total) {
                        final String url = entry != null ? entry.getUrl().orElse("") : "";
                        final String title = entry != null ? entry.getTitle().orElse("") : "";
                        snapshots.add(new CefWebHistory.EntrySnapshot(
                                url, title, completionDate(entry != null ? entry.getCompletionTime() : null)));
                        fingerprint.append(url).append('\u0001').append(title).append('\u0002');
                        if (current) currentIndex[0] = index;
                        if (index + 1 == total) {
                            final String fp = currentIndex[0] + "|" + fingerprint;
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

    @SuppressWarnings("JavaUtilDate")
    private static Date completionDate(@Nullable Object cefTime) {
        if (cefTime == null) return new Date(0);
        try {
            try {
                return completionDate(cefTime.getClass().getField("val").getLong(cefTime));
            } catch (NoSuchFieldException ignored) {
                final int year = cefTime.getClass().getField("year").getInt(cefTime);
                if (year == 0) return new Date(0);
                final LocalDateTime value = LocalDateTime.of(
                        year,
                        cefTime.getClass().getField("month").getInt(cefTime),
                        cefTime.getClass().getField("dayOfMonth").getInt(cefTime),
                        cefTime.getClass().getField("hour").getInt(cefTime),
                        cefTime.getClass().getField("minute").getInt(cefTime),
                        cefTime.getClass().getField("second").getInt(cefTime),
                        cefTime.getClass().getField("millisecond").getInt(cefTime) * 1_000_000);
                return Date.from(value.toInstant(ZoneOffset.UTC));
            }
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException("Unsupported CEF time representation", e);
        }
    }

    private static CefContextMenuHandler withQuickMenuCompatibility(CefContextMenuHandler delegate) {
        return (CefContextMenuHandler) Proxy.newProxyInstance(
                CefContextMenuHandler.class.getClassLoader(),
                new Class<?>[] {CefContextMenuHandler.class},
                (proxy, method, args) -> {
                    if (method.getName().equals("runQuickMenu")) {
                        final Object callback = args == null || args.length == 0 ? null : args[args.length - 1];
                        if (callback != null)
                            callback.getClass().getMethod("cancel").invoke(callback);
                        return true;
                    }
                    try {
                        return method.invoke(delegate, args);
                    } catch (InvocationTargetException e) {
                        throw e.getCause();
                    }
                });
    }
}
