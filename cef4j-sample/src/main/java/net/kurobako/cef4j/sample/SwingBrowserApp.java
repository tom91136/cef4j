package net.kurobako.cef4j.sample;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import javax.swing.*;
import net.kurobako.cef4j.Cef;
import net.kurobako.cef4j.gen.CefBrowser;
import net.kurobako.cef4j.gen.CefBrowserHost;
import net.kurobako.cef4j.gen.CefBrowserSettings;
import net.kurobako.cef4j.gen.CefClient;
import net.kurobako.cef4j.gen.CefCursorInfo;
import net.kurobako.cef4j.gen.CefCursorType;
import net.kurobako.cef4j.gen.CefDisplayHandler;
import net.kurobako.cef4j.gen.CefFrame;
import net.kurobako.cef4j.gen.CefLifeSpanHandler;
import net.kurobako.cef4j.gen.CefLoadHandler;
import net.kurobako.cef4j.gen.CefPoint;
import net.kurobako.cef4j.gen.CefPopupFeatures;
import net.kurobako.cef4j.gen.CefRect;
import net.kurobako.cef4j.gen.CefRenderHandler;
import net.kurobako.cef4j.gen.CefSettings;
import net.kurobako.cef4j.gen.CefWindowInfo;
import net.kurobako.cef4j.osr.swing.CefBrowserPanel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Sample Swing browser using {@link CefBrowserPanel} with tabbed browsing, zoom, and keyboard shortcuts. */
public final class SwingBrowserApp {

    private static final Logger log = LoggerFactory.getLogger(SwingBrowserApp.class);
    private static final String DEFAULT_URL = "https://microsoft.github.io/monaco-editor/";

    private static volatile boolean shutdownRequested;
    private static final CountDownLatch shutdownLatch = new CountDownLatch(1);
    private static JFrame window;
    private static JTabbedPane tabbedPane;
    private static JPanel newTabPlaceholder;
    private static JLabel statusLabel;
    private static JProgressBar progressBar;
    private static boolean creatingTab;

    public static void main(String[] args) throws Exception {
        log.info("cef4j Swing Browser starting");

        Path cacheDir = Files.createTempDirectory("cef4j-swing-");
        cacheDir.toFile().deleteOnExit();

        CefSettings.Mutable settings = new CefSettings.Mutable();
        settings.cachePath = cacheDir.toAbsolutePath().toString();
        CefBrowserPanel.initialise(settings, List.of(), null);

        SwingUtilities.invokeAndWait(SwingBrowserApp::createUI);
        SigintHelper.install(SwingBrowserApp::requestShutdown);
        shutdownLatch.await();
        CefBrowserPanel.terminate();
        log.info("Exiting");
        // halt() instead of exit(): on macOS, System.exit() triggers JVM teardown
        // which drains the CFRunLoop and fires CEF's registered observers after
        // the message loop has stopped, causing a CHECK failure.  halt() does an
        // immediate _exit() that avoids this.
        Runtime.getRuntime().halt(0);
    }

    private static void createUI() {
        com.formdev.flatlaf.FlatLightLaf.setup();

        float userScale = com.formdev.flatlaf.util.UIScale.getUserScaleFactor();
        double systemScale =
                com.formdev.flatlaf.util.UIScale.getSystemScaleFactor(GraphicsEnvironment.getLocalGraphicsEnvironment()
                        .getDefaultScreenDevice()
                        .getDefaultConfiguration());
        log.info("Display scaling - system: {}, user: {}", systemScale, userScale);

        window = new JFrame("cef4j Browser (Swing)");
        window.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        window.setSize(1280, 800);
        window.setLocationRelativeTo(null);

        statusLabel = new JLabel(" ");
        statusLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, UIManager.getColor("Separator.foreground")),
                BorderFactory.createEmptyBorder(2, 6, 2, 6)));
        progressBar = new JProgressBar(0, 100);
        progressBar.setPreferredSize(new Dimension(120, progressBar.getPreferredSize().height));
        progressBar.setVisible(false);
        JPanel statusBar = new JPanel(new BorderLayout());
        statusBar.add(statusLabel, BorderLayout.CENTER);
        statusBar.add(progressBar, BorderLayout.EAST);

        tabbedPane = new JTabbedPane();
        newTabPlaceholder = new JPanel();
        tabbedPane.addTab("+", newTabPlaceholder);
        JLabel plusLabel = new JLabel("+");
        plusLabel.setToolTipText("New Tab");
        tabbedPane.setTabComponentAt(0, plusLabel);

        tabbedPane.addChangeListener(e -> {
            if (tabbedPane.getSelectedComponent() == newTabPlaceholder) {
                createTab(DEFAULT_URL);
                return;
            }
            BrowserTab tab = selectedTab();
            if (tab != null) {
                tab.updateWindowTitle();
                tab.updateStatusBar();
            } else {
                window.setTitle("cef4j Browser (Swing)");
            }
        });

        // Double-click on empty tab bar area to create new tab
        tabbedPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && tabbedPane.indexAtLocation(e.getX(), e.getY()) == -1) {
                    createTab(DEFAULT_URL);
                }
            }
        });

        window.setLayout(new BorderLayout());
        window.add(tabbedPane, BorderLayout.CENTER);
        window.add(statusBar, BorderLayout.SOUTH);

        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                requestShutdown();
            }
        });

        // Global keyboard shortcuts
        int shortcutMask = Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx();
        InputMap inputMap = window.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = window.getRootPane().getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_T, shortcutMask), "newTab");
        actionMap.put("newTab", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createTab(DEFAULT_URL);
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, shortcutMask), "closeTab");
        actionMap.put("closeTab", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeSelectedTab();
            }
        });

        createTab(DEFAULT_URL);
        window.setVisible(true);
        window.toFront();
        window.requestFocus();
    }

    private static BrowserTab selectedTab() {
        Component sel = tabbedPane.getSelectedComponent();
        return sel instanceof BrowserTab ? (BrowserTab) sel : null;
    }

    private static void createTab(String url) {
        if (creatingTab) return;
        creatingTab = true;
        try {
            BrowserTab tab = new BrowserTab(url);
            int insertAt = tabbedPane.indexOfComponent(newTabPlaceholder);
            if (insertAt < 0) insertAt = tabbedPane.getTabCount();
            tabbedPane.insertTab("New Tab", null, tab, null, insertAt);
            tabbedPane.setTabComponentAt(insertAt, tab.createTabHeader());
            tabbedPane.setSelectedComponent(tab);
        } finally {
            creatingTab = false;
        }
    }

    private static void closeSelectedTab() {
        BrowserTab tab = selectedTab();
        if (tab != null) {
            closeTab(tab);
        }
    }

    private static void closeTab(BrowserTab tab) {
        int index = tabbedPane.indexOfComponent(tab);
        if (index >= 0) {
            tabbedPane.removeTabAt(index);
            tab.release();
        }
        // Only the "+" placeholder tab remains - no real tabs left
        boolean noRealTabs = true;
        for (int i = 0; i < tabbedPane.getTabCount(); i++) {
            if (tabbedPane.getComponentAt(i) instanceof BrowserTab) {
                noRealTabs = false;
                break;
            }
        }
        if (noRealTabs) {
            requestShutdown();
        }
    }

    private static void requestShutdown() {
        if (shutdownRequested) return;
        shutdownRequested = true;
        log.info("Shutting down");
        Runnable disposeBrowsers = () -> {
            for (int i = tabbedPane.getTabCount() - 1; i >= 0; i--) {
                Component c = tabbedPane.getComponentAt(i);
                if (c instanceof BrowserTab) {
                    ((BrowserTab) c).release();
                }
            }
            window.dispose();
            // Signal the main thread to proceed with Cef.INSTANCE.terminate()
            shutdownLatch.countDown();
        };
        if (SwingUtilities.isEventDispatchThread()) {
            disposeBrowsers.run();
        } else {
            SwingUtilities.invokeLater(disposeBrowsers);
        }
    }

    @SuppressWarnings("this-escape")
    private static final class BrowserTab extends JPanel {
        private static final long serialVersionUID = 1L;

        private final transient CefBrowserPanel surface;
        private final JTextField urlBar;
        private final JButton backBtn;
        private final JButton fwdBtn;
        private final JButton reloadBtn;
        private final JButton zoomOutBtn;
        private final JButton zoomResetBtn;
        private final JButton zoomInBtn;
        private final JButton devToolsBtn;
        private transient volatile CefBrowser browser;
        private double zoomLevel = 1.0;
        private boolean disposed;

        BrowserTab(String initialUrl) {
            super(new BorderLayout());
            surface = new CefBrowserPanel();
            urlBar = new JTextField(initialUrl);
            backBtn = new JButton("\u25C0");
            fwdBtn = new JButton("\u25B6");
            reloadBtn = new JButton("\u21BB");
            zoomOutBtn = new JButton("-");
            zoomResetBtn = new JButton("100%");
            zoomInBtn = new JButton("+");
            devToolsBtn = new JButton("\u2699");

            backBtn.setEnabled(false);
            fwdBtn.setEnabled(false);

            JPanel leftButtons = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 0));
            leftButtons.add(backBtn);
            leftButtons.add(fwdBtn);
            leftButtons.add(reloadBtn);

            JPanel rightButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 2, 0));
            rightButtons.add(zoomOutBtn);
            rightButtons.add(zoomResetBtn);
            rightButtons.add(zoomInBtn);
            rightButtons.add(devToolsBtn);

            JPanel navBar = new JPanel(new BorderLayout(4, 0));
            navBar.add(leftButtons, BorderLayout.WEST);
            navBar.add(urlBar, BorderLayout.CENTER);
            navBar.add(rightButtons, BorderLayout.EAST);
            navBar.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

            add(navBar, BorderLayout.NORTH);
            add(surface, BorderLayout.CENTER);

            // Button actions
            backBtn.addActionListener(e -> {
                CefBrowser b = browser;
                if (b != null) b.goBack();
            });
            fwdBtn.addActionListener(e -> {
                CefBrowser b = browser;
                if (b != null) b.goForward();
            });
            reloadBtn.addActionListener(e -> {
                CefBrowser b = browser;
                if (b != null) b.reload();
            });
            zoomOutBtn.addActionListener(e -> setZoom(zoomLevel / 1.2));
            zoomResetBtn.addActionListener(e -> setZoom(1.0));
            zoomInBtn.addActionListener(e -> setZoom(zoomLevel * 1.2));
            devToolsBtn.addActionListener(e -> toggleDevTools());
            urlBar.addActionListener(e -> {
                CefBrowser b = browser;
                if (b != null) {
                    b.getMainFrame()
                            .ifPresent(frame -> frame.loadUrl(urlBar.getText().trim()));
                }
            });

            // Keyboard shortcuts on the surface
            int shortcutMask = Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx();
            InputMap im = surface.getInputMap(JComponent.WHEN_FOCUSED);
            ActionMap am = surface.getActionMap();

            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), "reload");
            am.put("reload", action(() -> {
                CefBrowser b = browser;
                if (b != null) b.reload();
            }));

            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "stop");
            am.put("stop", action(() -> {
                CefBrowser b = browser;
                if (b != null) b.stopLoad();
            }));

            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0), "devtools");
            am.put("devtools", action(this::toggleDevTools));

            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_R, shortcutMask), "ctrlReload");
            am.put("ctrlReload", action(() -> {
                CefBrowser b = browser;
                if (b != null) b.reload();
            }));

            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_L, shortcutMask), "focusUrl");
            am.put("focusUrl", action(() -> {
                urlBar.requestFocusInWindow();
                urlBar.selectAll();
            }));

            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, shortcutMask), "focusUrl2");
            am.put("focusUrl2", action(() -> {
                urlBar.requestFocusInWindow();
                urlBar.selectAll();
            }));

            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, InputEvent.ALT_DOWN_MASK), "historyBack");
            am.put("historyBack", action(() -> {
                CefBrowser b = browser;
                if (b != null) b.goBack();
            }));

            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, InputEvent.ALT_DOWN_MASK), "historyForward");
            am.put("historyForward", action(() -> {
                CefBrowser b = browser;
                if (b != null) b.goForward();
            }));

            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, shortcutMask), "zoomIn");
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ADD, shortcutMask), "zoomIn");
            am.put("zoomIn", action(() -> setZoom(zoomLevel * 1.2)));

            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, shortcutMask), "zoomOut");
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SUBTRACT, shortcutMask), "zoomOut");
            am.put("zoomOut", action(() -> setZoom(zoomLevel / 1.2)));

            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_0, shortcutMask), "zoomReset");
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD0, shortcutMask), "zoomReset");
            am.put("zoomReset", action(() -> setZoom(1.0)));

            // Create the browser
            createBrowser(initialUrl);
        }

        private void createBrowser(String initialUrl) {
            CefRenderHandler renderHandler = surface.createRenderHandler();

            CefClient client = new CefClient() {
                @Override
                public Optional<CefRenderHandler> getRenderHandler() {
                    return Optional.of(renderHandler);
                }

                @Override
                public Optional<CefLifeSpanHandler> getLifeSpanHandler() {
                    return Optional.of(new CefLifeSpanHandler() {
                        @Override
                        public void onAfterCreated(CefBrowser b) {
                            browser = b;
                            SwingUtilities.invokeLater(() -> {
                                surface.setBrowser(b);
                                b.getHost().ifPresent(host -> host.setFocus(true));
                                surface.requestFocusInWindow();
                            });
                        }

                        @Override
                        public boolean onBeforePopup(
                                CefBrowser b,
                                CefFrame frame,
                                int popupId,
                                String targetUrl,
                                String targetFrameName,
                                net.kurobako.cef4j.gen.CefWindowOpenDisposition targetDisposition,
                                boolean userGesture,
                                CefPopupFeatures popupFeatures,
                                CefWindowInfo.Mutable windowInfo,
                                java.util.concurrent.atomic.AtomicReference<CefClient> client,
                                CefBrowserSettings.Mutable settings,
                                java.util.concurrent.atomic.AtomicReference<net.kurobako.cef4j.gen.CefDictionaryValue>
                                        extraInfo,
                                int[] noJavascriptAccess) {
                            SwingUtilities.invokeLater(() -> createTab(targetUrl != null ? targetUrl : "about:blank"));
                            return true;
                        }

                        @Override
                        public void onBeforeClose(CefBrowser b) {
                            if (browser != null && browser.isSame(b)) {
                                browser = null;
                            }
                        }
                    });
                }

                @Override
                public Optional<CefLoadHandler> getLoadHandler() {
                    return Optional.of(new CefLoadHandler() {
                        @Override
                        public void onLoadingStateChange(
                                CefBrowser b, boolean isLoading, boolean canGoBack, boolean canGoForward) {
                            SwingUtilities.invokeLater(() -> {
                                backBtn.setEnabled(canGoBack);
                                fwdBtn.setEnabled(canGoForward);
                                if (!isLoading) {
                                    updateStatusBar();
                                }
                            });
                        }
                    });
                }

                @Override
                public Optional<CefDisplayHandler> getDisplayHandler() {
                    return Optional.of(new CefDisplayHandler() {
                        @Override
                        public void onTitleChange(CefBrowser b, String title) {
                            SwingUtilities.invokeLater(() -> {
                                updateTabTitle(title);
                                if (isSelected()) {
                                    updateWindowTitle();
                                }
                            });
                        }

                        @Override
                        public void onAddressChange(CefBrowser b, CefFrame f, String url) {
                            SwingUtilities.invokeLater(() -> {
                                urlBar.setText(url);
                                if (getTabTitle().equals("New Tab") && url != null && !url.isEmpty()) {
                                    updateTabTitle(url);
                                }
                                if (isSelected()) {
                                    updateWindowTitle();
                                }
                            });
                        }

                        @Override
                        public void onStatusMessage(CefBrowser b, String value) {
                            SwingUtilities.invokeLater(() -> {
                                if (isSelected()) {
                                    statusLabel.setText(value != null && !value.isEmpty() ? value : " ");
                                }
                            });
                        }

                        @Override
                        public void onLoadingProgressChange(CefBrowser b, double progress) {
                            SwingUtilities.invokeLater(() -> {
                                if (!isSelected()) return;
                                if (progress >= 0 && progress < 1.0) {
                                    progressBar.setVisible(true);
                                    progressBar.setValue((int) (progress * 100));
                                } else {
                                    progressBar.setVisible(false);
                                }
                            });
                        }

                        @Override
                        public boolean onCursorChange(
                                CefBrowser b, long cursor, CefCursorType type, CefCursorInfo customCursorInfo) {
                            Cursor awtCursor = surface.mapCursor(type);
                            SwingUtilities.invokeLater(() -> surface.setCursor(awtCursor));
                            return true;
                        }
                    });
                }

                @Override
                public Optional<net.kurobako.cef4j.gen.CefContextMenuHandler> getContextMenuHandler() {
                    return Optional.of(new net.kurobako.cef4j.gen.CefContextMenuHandler() {
                        @Override
                        public boolean runContextMenu(
                                CefBrowser b,
                                net.kurobako.cef4j.gen.CefFrame f,
                                net.kurobako.cef4j.gen.CefContextMenuParams params,
                                net.kurobako.cef4j.gen.CefMenuModel model,
                                net.kurobako.cef4j.gen.CefRunContextMenuCallback callback) {
                            if (model == null || callback == null) return false;
                            // Extract menu data on CEF thread - model/params invalid after return
                            List<MenuEntry> entries = extractMenuEntries(model);
                            int menuX = params != null ? params.getXCoord() : 0;
                            int menuY = params != null ? params.getYCoord() : 0;
                            if (entries.isEmpty()) {
                                callback.cancel();
                                return true;
                            }
                            java.util.concurrent.atomic.AtomicBoolean dispatched =
                                    new java.util.concurrent.atomic.AtomicBoolean();
                            SwingUtilities.invokeLater(() -> {
                                JPopupMenu menu = buildPopupMenu(entries, callback, dispatched);
                                menu.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
                                    @Override
                                    public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent e) {}

                                    @Override
                                    public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent e) {
                                        if (dispatched.compareAndSet(false, true)) callback.cancel();
                                    }

                                    @Override
                                    public void popupMenuCanceled(javax.swing.event.PopupMenuEvent e) {
                                        if (dispatched.compareAndSet(false, true)) callback.cancel();
                                    }
                                });
                                menu.show(surface, menuX, menuY);
                            });
                            return true;
                        }

                        @Override
                        public boolean runQuickMenu(
                                CefBrowser b,
                                net.kurobako.cef4j.gen.CefFrame f,
                                net.kurobako.cef4j.gen.CefPoint location,
                                net.kurobako.cef4j.gen.CefSize size,
                                net.kurobako.cef4j.gen.CefQuickMenuEditStateFlags editStateFlags,
                                net.kurobako.cef4j.gen.CefRunQuickMenuCallback callback) {
                            if (callback != null) callback.cancel();
                            return true;
                        }
                    });
                }
            };

            CefWindowInfo windowInfo = Cef.createWindowlessInfo(
                    new CefRect(0, 0, Math.max(1, surface.getWidth()), Math.max(1, surface.getHeight())));
            CefBrowserSettings.Mutable browserSettings = new CefBrowserSettings.Mutable();
            browserSettings.windowlessFrameRate = 60;
            CefBrowserHost.createBrowser(windowInfo, client, initialUrl, browserSettings.toImmutable(), null, null);
        }

        JComponent createTabHeader() {
            JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
            header.setOpaque(false);
            JLabel label = new JLabel("New Tab");
            JButton closeBtn = new JButton("\u00D7");
            closeBtn.setMargin(new Insets(0, 2, 0, 2));
            closeBtn.setBorderPainted(false);
            closeBtn.setContentAreaFilled(false);
            closeBtn.setFocusable(false);
            closeBtn.addActionListener(e -> closeTab(this));
            header.add(label);
            header.add(closeBtn);
            return header;
        }

        boolean isSelected() {
            return tabbedPane.getSelectedComponent() == this;
        }

        String getTabTitle() {
            int index = tabbedPane.indexOfComponent(this);
            if (index < 0) return "";
            Component tabComponent = tabbedPane.getTabComponentAt(index);
            if (tabComponent instanceof JPanel) {
                for (Component c : ((JPanel) tabComponent).getComponents()) {
                    if (c instanceof JLabel) {
                        return ((JLabel) c).getText();
                    }
                }
            }
            return tabbedPane.getTitleAt(index);
        }

        void updateTabTitle(String value) {
            if (value == null || value.isEmpty()) return;
            String display = value.length() > 24 ? value.substring(0, 24) + "\u2026" : value;
            int index = tabbedPane.indexOfComponent(this);
            if (index >= 0) {
                tabbedPane.setTitleAt(index, display);
                Component tabComponent = tabbedPane.getTabComponentAt(index);
                if (tabComponent instanceof JPanel) {
                    for (Component c : ((JPanel) tabComponent).getComponents()) {
                        if (c instanceof JLabel) {
                            ((JLabel) c).setText(display);
                            break;
                        }
                    }
                }
            }
        }

        void updateWindowTitle() {
            CefBrowser b = browser;
            String title = getTabTitle();
            String url = urlBar.getText();
            String display = (title != null && !title.isEmpty() && !"New Tab".equals(title)) ? title : url;
            window.setTitle((display != null ? display : "") + " - cef4j (Swing)");
        }

        void updateStatusBar() {
            if (isSelected()) {
                progressBar.setVisible(false);
                progressBar.setValue(0);
                statusLabel.setText(" ");
            }
        }

        void setZoom(double factor) {
            zoomLevel = Math.max(0.25, Math.min(5.0, factor));
            zoomResetBtn.setText(Math.round(zoomLevel * 100) + "%");
            CefBrowser b = browser;
            if (b != null) {
                b.getHost().ifPresent(host -> host.setZoomLevel(Math.log(zoomLevel) / Math.log(1.2)));
            }
        }

        void toggleDevTools() {
            CefBrowser b = browser;
            if (b == null) return;
            b.getHost().ifPresent(host -> {
                if (host.hasDevTools()) {
                    host.closeDevTools();
                } else {
                    host.showDevTools(null, null, null, new CefPoint(0, 0));
                }
            });
        }

        void release() {
            if (disposed) return;
            disposed = true;
            surface.release();
        }

        // Plain data extracted from CefMenuModel on CEF thread (no Swing dependency)
        private static final class MenuEntry {
            enum Type {
                SEPARATOR,
                ITEM,
                CHECK,
                SUBMENU
            }

            final Type type;
            final int commandId;
            final String label;
            final boolean enabled;
            final boolean checked;
            final List<MenuEntry> children; // for SUBMENU

            MenuEntry(
                    Type type,
                    int commandId,
                    String label,
                    boolean enabled,
                    boolean checked,
                    List<MenuEntry> children) {
                this.type = type;
                this.commandId = commandId;
                this.label = label;
                this.enabled = enabled;
                this.checked = checked;
                this.children = children;
            }
        }

        /** Extract menu data from CefMenuModel on CEF thread (safe, no Swing). */
        private static List<MenuEntry> extractMenuEntries(net.kurobako.cef4j.gen.CefMenuModel model) {
            List<MenuEntry> entries = new ArrayList<>();
            long count = model.getCount();
            for (long i = 0; i < count; i++) {
                int commandId = model.getCommandIdAt(i);
                net.kurobako.cef4j.gen.CefMenuItemType.Kind kind =
                        model.getType(commandId).kind().orElse(net.kurobako.cef4j.gen.CefMenuItemType.Kind.NONE);
                switch (kind) {
                    case SEPARATOR:
                        entries.add(new MenuEntry(MenuEntry.Type.SEPARATOR, 0, "", false, false, List.of()));
                        break;
                    case SUBMENU: {
                        String label = model.getLabel(commandId).orElse("").replace("&", "");
                        List<MenuEntry> children = model.getSubMenuAt(i)
                                .map(sub -> extractMenuEntries(sub))
                                .orElse(List.of());
                        entries.add(new MenuEntry(MenuEntry.Type.SUBMENU, commandId, label, true, false, children));
                        break;
                    }
                    case CHECK: {
                        String label = model.getLabel(commandId).orElse("").replace("&", "");
                        entries.add(new MenuEntry(
                                MenuEntry.Type.CHECK,
                                commandId,
                                label,
                                model.isEnabled(commandId),
                                model.isChecked(commandId),
                                List.of()));
                        break;
                    }
                    default: {
                        String label = model.getLabel(commandId).orElse("").replace("&", "");
                        if (label.isEmpty() && kind == net.kurobako.cef4j.gen.CefMenuItemType.Kind.NONE) break;
                        entries.add(new MenuEntry(
                                MenuEntry.Type.ITEM, commandId, label, model.isEnabled(commandId), false, List.of()));
                        break;
                    }
                }
            }
            return entries;
        }

        /** Build JPopupMenu from extracted data - must be called on EDT. */
        private static JPopupMenu buildPopupMenu(
                List<MenuEntry> entries,
                net.kurobako.cef4j.gen.CefRunContextMenuCallback callback,
                java.util.concurrent.atomic.AtomicBoolean dispatched) {
            JPopupMenu popup = new JPopupMenu();
            for (MenuEntry entry : entries) {
                switch (entry.type) {
                    case SEPARATOR:
                        popup.addSeparator();
                        break;
                    case SUBMENU: {
                        JMenu sub = new JMenu(entry.label);
                        JPopupMenu subPopup = buildPopupMenu(entry.children, callback, dispatched);
                        for (int j = 0; j < subPopup.getComponentCount(); j++) {
                            sub.add(subPopup.getComponent(j));
                        }
                        popup.add(sub);
                        break;
                    }
                    case CHECK: {
                        JCheckBoxMenuItem ci = new JCheckBoxMenuItem(entry.label);
                        ci.setSelected(entry.checked);
                        ci.setEnabled(entry.enabled);
                        int cmdId = entry.commandId;
                        ci.addActionListener(e -> {
                            if (dispatched.compareAndSet(false, true)) {
                                callback.cont(
                                        cmdId,
                                        net.kurobako.cef4j.gen.CefEventFlags.of(
                                                net.kurobako.cef4j.gen.CefEventFlags.Kind.NONE));
                            }
                        });
                        popup.add(ci);
                        break;
                    }
                    case ITEM: {
                        JMenuItem mi = new JMenuItem(entry.label);
                        mi.setEnabled(entry.enabled);
                        int cmdId = entry.commandId;
                        mi.addActionListener(e -> {
                            if (dispatched.compareAndSet(false, true)) {
                                callback.cont(
                                        cmdId,
                                        net.kurobako.cef4j.gen.CefEventFlags.of(
                                                net.kurobako.cef4j.gen.CefEventFlags.Kind.NONE));
                            }
                        });
                        popup.add(mi);
                        break;
                    }
                }
            }
            return popup;
        }

        private static AbstractAction action(Runnable r) {
            return new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    r.run();
                }
            };
        }
    }
}
