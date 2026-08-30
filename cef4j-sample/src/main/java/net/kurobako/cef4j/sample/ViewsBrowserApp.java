package net.kurobako.cef4j.sample;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import javax.annotation.Nullable;
import net.kurobako.cef4j.Cef;
import net.kurobako.cef4j.OS;
import net.kurobako.cef4j.gen.CefBoxLayoutSettings;
import net.kurobako.cef4j.gen.CefBrowser;
import net.kurobako.cef4j.gen.CefBrowserSettings;
import net.kurobako.cef4j.gen.CefClient;
import net.kurobako.cef4j.gen.CefDisplayHandler;
import net.kurobako.cef4j.gen.CefFrame;
import net.kurobako.cef4j.gen.CefKeyEvent;
import net.kurobako.cef4j.gen.CefKeyEventType;
import net.kurobako.cef4j.gen.CefLoadHandler;
import net.kurobako.cef4j.gen.CefRect;
import net.kurobako.cef4j.gen.CefSettings;
import net.kurobako.cef4j.gen.CefSize;
import net.kurobako.cef4j.gen.views.CefBoxLayout;
import net.kurobako.cef4j.gen.views.CefBrowserView;
import net.kurobako.cef4j.gen.views.CefBrowserViewDelegate;
import net.kurobako.cef4j.gen.views.CefButton;
import net.kurobako.cef4j.gen.views.CefButtonDelegate;
import net.kurobako.cef4j.gen.views.CefLabelButton;
import net.kurobako.cef4j.gen.views.CefPanel;
import net.kurobako.cef4j.gen.views.CefTextField;
import net.kurobako.cef4j.gen.views.CefTextFieldDelegate;
import net.kurobako.cef4j.gen.views.CefWindow;
import net.kurobako.cef4j.gen.views.CefWindowDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Full-featured browser using the CEF Views framework. No Swing or JavaFX required - CEF manages all native windows and
 * UI.
 *
 * <p>Features: tabbed browsing, navigation bar (back/forward/reload/URL), status feedback, keyboard shortcuts
 * (Ctrl+T/W/L, F5, F12, Alt+Left/Right).
 *
 * <p>Layout hierarchy:
 *
 * <pre>
 * CefWindow (FillLayout)
 *   CefPanel - mainPanel (vertical BoxLayout)
 *     CefPanel - tabBar (horizontal BoxLayout)
 *       CefLabelButton - tab[0]
 *       CefLabelButton - tab[1] ...
 *       CefLabelButton - "+" addTabButton
 *     CefPanel - navBar (horizontal BoxLayout)
 *       CefLabelButton - backBtn
 *       CefLabelButton - fwdBtn
 *       CefLabelButton - reloadBtn
 *       CefTextField - urlBar (flex=1)
 *       CefLabelButton - devtoolsBtn
 *     CefPanel - contentPanel (FillLayout, flex=1)
 *       CefBrowserView (active tab)
 * </pre>
 */
@SuppressWarnings("NullAway.Init")
public final class ViewsBrowserApp {

    private static final Logger log = LoggerFactory.getLogger(ViewsBrowserApp.class);
    private static final String DEFAULT_URL = "https://microsoft.github.io/monaco-editor/";

    private static final int CMD_NEW_TAB = 1001;
    private static final int CMD_CLOSE_TAB = 1002;
    private static final int CMD_RELOAD = 1003;
    private static final int CMD_DEVTOOLS = 1004;
    private static final int CMD_BACK = 1005;
    private static final int CMD_FORWARD = 1006;
    private static final int CMD_FOCUS_URL = 1007;

    private static final int VK_F5 = 0x74;
    private static final int VK_F12 = 0x7B;
    private static final int VK_LEFT = 0x25;
    private static final int VK_RIGHT = 0x27;
    private static final int VK_T = 0x54;
    private static final int VK_W = 0x57;
    private static final int VK_L = 0x4C;
    private static final int VK_RETURN = 0x0D;

    private static final CefBoxLayoutSettings HBOX = new CefBoxLayoutSettings(1, 0, 0, null, 4, null, null, 0, 0);

    private static final CefBoxLayoutSettings VBOX = new CefBoxLayoutSettings(0, 0, 0, null, 0, null, null, 0, 0);

    private static final CountDownLatch shutdownLatch = new CountDownLatch(1);

    private CefWindow window;
    private CefPanel mainPanel;
    private CefPanel tabBar;
    private CefLabelButton addTabButton;
    private CefPanel navBar;
    private CefLabelButton backButton;
    private CefLabelButton forwardButton;
    private CefLabelButton reloadButton;
    private CefTextField urlBar;
    private CefLabelButton devtoolsButton;
    private CefPanel contentPanel;

    private final List<TabEntry> tabs = new ArrayList<>();
    private int activeTabIndex = -1;

    private static final class TabEntry {
        final CefBrowserView browserView;
        final CefLabelButton tabButton;
        String currentUrl = "";
        String currentTitle = "New Tab";
        boolean isLoading = false;

        TabEntry(CefBrowserView browserView, CefLabelButton tabButton) {
            this.browserView = browserView;
            this.tabButton = tabButton;
        }
    }

    private int indexOfTab(TabEntry entry) {
        return tabs.indexOf(entry);
    }

    private void openNewTab(String url) {
        TabEntry[] entryHolder = {null};

        CefLabelButton tabButton = CefLabelButton.create(
                        new CefButtonDelegate() {
                            @Override
                            public void onButtonPressed(@Nullable CefButton button) {
                                int idx = indexOfTab(entryHolder[0]);
                                if (idx >= 0) switchToTab(idx);
                            }
                        },
                        "New Tab")
                .orElseThrow(() -> new RuntimeException("Failed to create tab button"));

        CefClient tabClient = buildTabClient(entryHolder);

        CefBrowserView browserView = CefBrowserView.create(
                        tabClient,
                        url,
                        new CefBrowserSettings.Mutable().toImmutable(),
                        null,
                        null,
                        new CefBrowserViewDelegate() {
                            @Override
                            public boolean onPopupBrowserViewCreated(
                                    @Nullable CefBrowserView bv, @Nullable CefBrowserView popup, boolean isDevtools) {
                                if (popup != null && !isDevtools) {
                                    popup.getBrowser()
                                            .flatMap(CefBrowser::getMainFrame)
                                            .flatMap(CefFrame::getUrl)
                                            .ifPresent(ViewsBrowserApp.this::openNewTab);
                                }
                                return false;
                            }
                        })
                .orElseThrow(() -> new RuntimeException("Failed to create CefBrowserView"));

        TabEntry entry = new TabEntry(browserView, tabButton);
        entryHolder[0] = entry;
        int insertIndex = tabs.size();
        tabs.add(entry);

        tabBar.addChildViewAt(tabButton, insertIndex);

        switchToTab(insertIndex);
    }

    private CefClient buildTabClient(TabEntry[] entryHolder) {
        return new CefClient() {
            @Override
            public Optional<CefDisplayHandler> getDisplayHandler() {
                return Optional.of(new CefDisplayHandler() {
                    @Override
                    public void onTitleChange(@Nullable CefBrowser browser, @Nullable String title) {
                        TabEntry entry = entryHolder[0];
                        if (entry == null) return;
                        String displayTitle = (title == null || title.isEmpty()) ? "New Tab" : title;
                        entry.currentTitle = displayTitle;
                        String tabLabel =
                                displayTitle.length() > 25 ? displayTitle.substring(0, 22) + "..." : displayTitle;
                        int idx = indexOfTab(entry);
                        if (idx == activeTabIndex) {
                            entry.tabButton.setText("[ " + trimTitle(displayTitle) + " ]");
                            if (window != null) window.setTitle(displayTitle + " - cef4j (Views)");
                        } else {
                            entry.tabButton.setText(tabLabel);
                        }
                    }

                    @Override
                    public void onAddressChange(
                            @Nullable CefBrowser browser, @Nullable CefFrame frame, @Nullable String url) {
                        TabEntry entry = entryHolder[0];
                        if (entry == null) return;
                        entry.currentUrl = url == null ? "" : url;
                        if (indexOfTab(entry) == activeTabIndex && urlBar != null) {
                            urlBar.setText(url);
                        }
                    }

                    @Override
                    public void onStatusMessage(@Nullable CefBrowser browser, @Nullable String value) {
                        log.trace("Status: {}", value);
                    }
                });
            }

            @Override
            public Optional<CefLoadHandler> getLoadHandler() {
                return Optional.of(new CefLoadHandler() {
                    @Override
                    public void onLoadingStateChange(
                            @Nullable CefBrowser browser, boolean isLoading, boolean canGoBack, boolean canGoForward) {
                        TabEntry entry = entryHolder[0];
                        if (entry == null) return;
                        entry.isLoading = isLoading;
                        if (indexOfTab(entry) == activeTabIndex) {
                            reloadButton.setText(isLoading ? "X" : "R");
                            backButton.setEnabled(canGoBack);
                            forwardButton.setEnabled(canGoForward);
                        }
                    }
                });
            }
        };
    }

    private void switchToTab(int index) {
        if (index < 0 || index >= tabs.size()) return;

        if (activeTabIndex >= 0 && activeTabIndex < tabs.size()) {
            TabEntry prev = tabs.get(activeTabIndex);
            contentPanel.removeChildView(prev.browserView);
            prev.tabButton.setText(trimTitle(prev.currentTitle));
        }

        activeTabIndex = index;
        TabEntry entry = tabs.get(index);

        contentPanel.addChildView(entry.browserView);

        urlBar.setText(entry.currentUrl);
        reloadButton.setText(entry.isLoading ? "X" : "R");
        entry.browserView.getBrowser().ifPresent(b -> {
            backButton.setEnabled(b.canGoBack());
            forwardButton.setEnabled(b.canGoForward());
        });

        if (window != null && !entry.currentTitle.isEmpty()) {
            window.setTitle(entry.currentTitle + " - cef4j (Views)");
        }

        entry.tabButton.setText("[ " + trimTitle(entry.currentTitle) + " ]");
    }

    private static String trimTitle(String title) {
        if (title == null || title.isEmpty()) return "New Tab";
        return title.length() > 20 ? title.substring(0, 17) + "..." : title;
    }

    private void closeActiveTab() {
        if (tabs.isEmpty()) return;
        if (tabs.size() == 1) {
            if (window != null) window.cefClose();
            return;
        }

        TabEntry entry = tabs.remove(activeTabIndex);
        contentPanel.removeChildView(entry.browserView);
        tabBar.removeChildView(entry.tabButton);

        int nextIndex = Math.min(activeTabIndex, tabs.size() - 1);
        activeTabIndex = -1;
        switchToTab(nextIndex);
    }

    private void navigateActiveTab(String url) {
        if (activeTabIndex < 0 || activeTabIndex >= tabs.size()) return;
        String navigateUrl = url.contains("://") ? url : "https://" + url;
        tabs.get(activeTabIndex)
                .browserView
                .getBrowser()
                .flatMap(CefBrowser::getMainFrame)
                .ifPresent(f -> f.loadUrl(navigateUrl));
    }

    private void toggleDevTools() {
        if (activeTabIndex < 0 || activeTabIndex >= tabs.size()) return;
        tabs.get(activeTabIndex)
                .browserView
                .getBrowser()
                .flatMap(CefBrowser::getHost)
                .ifPresent(host -> {
                    if (host.hasDevTools()) {
                        host.closeDevTools();
                    } else {
                        host.showDevTools(null, null, null, null);
                    }
                });
    }

    private void buildUi(CefWindow win) {
        this.window = win;

        mainPanel = CefPanel.create(null).orElseThrow(() -> new RuntimeException("Failed to create mainPanel"));
        CefBoxLayout mainLayout = mainPanel
                .setToBoxLayout(VBOX)
                .orElseThrow(() -> new RuntimeException("Failed to set mainPanel box layout"));

        tabBar = CefPanel.create(null).orElseThrow(() -> new RuntimeException("Failed to create tabBar"));
        tabBar.setToBoxLayout(HBOX);

        addTabButton = CefLabelButton.create(
                        new CefButtonDelegate() {
                            @Override
                            public void onButtonPressed(@Nullable CefButton button) {
                                openNewTab(DEFAULT_URL);
                            }
                        },
                        "+")
                .orElseThrow(() -> new RuntimeException("Failed to create addTabButton"));
        tabBar.addChildView(addTabButton);

        navBar = CefPanel.create(null).orElseThrow(() -> new RuntimeException("Failed to create navBar"));
        CefBoxLayout navLayout =
                navBar.setToBoxLayout(HBOX).orElseThrow(() -> new RuntimeException("Failed to set navBar box layout"));

        backButton = CefLabelButton.create(
                        new CefButtonDelegate() {
                            @Override
                            public void onButtonPressed(@Nullable CefButton button) {
                                goBack();
                            }
                        },
                        "<")
                .orElseThrow(() -> new RuntimeException("Failed to create backButton"));
        backButton.setEnabled(false);

        forwardButton = CefLabelButton.create(
                        new CefButtonDelegate() {
                            @Override
                            public void onButtonPressed(@Nullable CefButton button) {
                                goForward();
                            }
                        },
                        ">")
                .orElseThrow(() -> new RuntimeException("Failed to create forwardButton"));
        forwardButton.setEnabled(false);

        reloadButton = CefLabelButton.create(
                        new CefButtonDelegate() {
                            @Override
                            public void onButtonPressed(@Nullable CefButton button) {
                                reloadOrStop();
                            }
                        },
                        "R")
                .orElseThrow(() -> new RuntimeException("Failed to create reloadButton"));

        urlBar = CefTextField.create(new CefTextFieldDelegate() {
                    @Override
                    public boolean onKeyEvent(@Nullable CefTextField textField, CefKeyEvent event) {
                        if (textField != null
                                && event.windowsKeyCode == VK_RETURN
                                && Objects.equals(event.type, CefKeyEventType.of(CefKeyEventType.Kind.RAWKEYDOWN))) {
                            textField.getText().ifPresent(ViewsBrowserApp.this::navigateActiveTab);
                            return true;
                        }
                        return false;
                    }
                })
                .orElseThrow(() -> new RuntimeException("Failed to create urlBar"));

        devtoolsButton = CefLabelButton.create(
                        new CefButtonDelegate() {
                            @Override
                            public void onButtonPressed(@Nullable CefButton button) {
                                toggleDevTools();
                            }
                        },
                        "F12")
                .orElseThrow(() -> new RuntimeException("Failed to create devtoolsButton"));

        navBar.addChildView(backButton);
        navBar.addChildView(forwardButton);
        navBar.addChildView(reloadButton);
        navBar.addChildView(urlBar);
        navBar.addChildView(devtoolsButton);

        navLayout.setFlexForView(urlBar, 1);

        contentPanel = CefPanel.create(null).orElseThrow(() -> new RuntimeException("Failed to create contentPanel"));
        contentPanel.setToFillLayout();

        mainPanel.addChildView(tabBar);
        mainPanel.addChildView(navBar);
        mainPanel.addChildView(contentPanel);

        mainLayout.setFlexForView(contentPanel, 1);

        setAcceleratorCompat(win, CMD_NEW_TAB, VK_T, false, true, false, true);
        setAcceleratorCompat(win, CMD_CLOSE_TAB, VK_W, false, true, false, true);
        setAcceleratorCompat(win, CMD_RELOAD, VK_F5, false, false, false, true);
        setAcceleratorCompat(win, CMD_DEVTOOLS, VK_F12, false, false, false, true);
        setAcceleratorCompat(win, CMD_BACK, VK_LEFT, false, false, true, true);
        setAcceleratorCompat(win, CMD_FORWARD, VK_RIGHT, false, false, true, true);
        setAcceleratorCompat(win, CMD_FOCUS_URL, VK_L, false, true, false, true);

        win.setToFillLayout();
        win.addChildView(mainPanel);
        win.setTitle("cef4j Browser (Views)");
        win.centerWindow(new CefSize(1280, 800));

        openNewTab(DEFAULT_URL);

        win.show();
    }

    private static void setAcceleratorCompat(
            CefWindow win,
            int commandId,
            int keyCode,
            boolean shiftPressed,
            boolean ctrlPressed,
            boolean altPressed,
            boolean highPriority) {
        java.lang.reflect.Method accelerator = null;
        try {
            accelerator = win.getClass()
                    .getMethod(
                            "setAccelerator",
                            int.class,
                            int.class,
                            boolean.class,
                            boolean.class,
                            boolean.class,
                            boolean.class);
        } catch (NoSuchMethodException ignored) {
            accelerator = null;
        }
        if (accelerator != null) {
            try {
                accelerator.invoke(win, commandId, keyCode, shiftPressed, ctrlPressed, altPressed, highPriority);
                return;
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException("Failed to invoke CefWindow#setAccelerator", e);
            }
        }

        try {
            win.getClass()
                    .getMethod("setAccelerator", int.class, int.class, boolean.class, boolean.class, boolean.class)
                    .invoke(win, commandId, keyCode, shiftPressed, ctrlPressed, altPressed);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Failed to invoke legacy CefWindow#setAccelerator", e);
        }
    }

    private void goBack() {
        if (activeTabIndex < 0 || activeTabIndex >= tabs.size()) return;
        tabs.get(activeTabIndex).browserView.getBrowser().ifPresent(CefBrowser::goBack);
    }

    private void goForward() {
        if (activeTabIndex < 0 || activeTabIndex >= tabs.size()) return;
        tabs.get(activeTabIndex).browserView.getBrowser().ifPresent(CefBrowser::goForward);
    }

    private void reloadOrStop() {
        if (activeTabIndex < 0 || activeTabIndex >= tabs.size()) return;
        tabs.get(activeTabIndex).browserView.getBrowser().ifPresent(b -> {
            if (tabs.get(activeTabIndex).isLoading) {
                b.stopLoad();
            } else {
                b.reload();
            }
        });
    }

    public static void main(String[] args) throws Exception {
        log.info("cef4j Views browser starting");

        Path cacheDir = Files.createTempDirectory("cef4j-views-");
        cacheDir.toFile().deleteOnExit();

        CefSettings.Mutable settings = new CefSettings.Mutable();
        settings.noSandbox = 1;
        settings.cachePath = cacheDir.toAbsolutePath().toString();
        settings.windowlessRenderingEnabled = 0;
        settings.multiThreadedMessageLoop = 0;
        settings.externalMessagePump = 0;

        List<String> extraArgs = new ArrayList<>();
        if (OS.isLinux()) {
            extraArgs.add("--ozone-platform=x11");
            extraArgs.add("--no-zygote");
        }

        Cef.INSTANCE.initialise(settings, extraArgs);

        ViewsBrowserApp app = new ViewsBrowserApp();

        CefWindowDelegate windowDelegate = new CefWindowDelegate() {
            @Override
            public CefRect getInitialBounds(@Nullable CefWindow window) {
                return new CefRect(0, 0, 1280, 800);
            }

            @Override
            public boolean canResize(@Nullable CefWindow window) {
                return true;
            }

            @Override
            public boolean canMaximize(@Nullable CefWindow window) {
                return true;
            }

            @Override
            public boolean canMinimize(@Nullable CefWindow window) {
                return true;
            }

            @Override
            public boolean canClose(@Nullable CefWindow window) {
                return true;
            }

            @Override
            public void onWindowCreated(@Nullable CefWindow window) {
                if (window == null) return;
                log.info("Window created - building UI");
                app.buildUi(window);
            }

            @Override
            public void onWindowClosing(@Nullable CefWindow window) {
                log.info("Window closing");
            }

            @Override
            public void onWindowDestroyed(@Nullable CefWindow window) {
                log.info("Window destroyed");
                shutdownLatch.countDown();
            }

            @Override
            public boolean onAccelerator(@Nullable CefWindow window, int commandId) {
                if (commandId == CMD_NEW_TAB) {
                    app.openNewTab(DEFAULT_URL);
                } else if (commandId == CMD_CLOSE_TAB) {
                    app.closeActiveTab();
                } else if (commandId == CMD_RELOAD) {
                    app.reloadOrStop();
                } else if (commandId == CMD_DEVTOOLS) {
                    app.toggleDevTools();
                } else if (commandId == CMD_BACK) {
                    app.goBack();
                } else if (commandId == CMD_FORWARD) {
                    app.goForward();
                } else if (commandId == CMD_FOCUS_URL) {
                    if (app.urlBar != null) {
                        app.urlBar.requestFocus();
                        app.urlBar.selectAll(false);
                    }
                } else {
                    return false;
                }
                return true;
            }
        };

        CefWindow topLevel = CefWindow.createTopLevel(windowDelegate)
                .orElseThrow(() -> new RuntimeException("Failed to create top-level CefWindow"));
        log.debug("Top-level window created: {}", topLevel);

        SigintHelper.install(shutdownLatch::countDown);

        log.info("Waiting for window close");
        shutdownLatch.await();

        log.info("Shutting down CEF");
        Cef.INSTANCE.terminate();
        log.info("Exiting");
        SampleShutdown.afterCefTermination();
    }
}
