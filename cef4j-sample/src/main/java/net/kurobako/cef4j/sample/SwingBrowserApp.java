package net.kurobako.cef4j.sample;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.swing.*;
import net.kurobako.cef4j.Cef;
import net.kurobako.cef4j.OS;
import net.kurobako.cef4j.SystemBootstrap;
import net.kurobako.cef4j.gen.CefBrowser;
import net.kurobako.cef4j.gen.CefBrowserHost;
import net.kurobako.cef4j.gen.CefBrowserSettings;
import net.kurobako.cef4j.gen.CefClient;
import net.kurobako.cef4j.gen.CefCursorType;
import net.kurobako.cef4j.gen.CefDisplayHandler;
import net.kurobako.cef4j.gen.CefFrame;
import net.kurobako.cef4j.gen.CefLifeSpanHandler;
import net.kurobako.cef4j.gen.CefLoadHandler;
import net.kurobako.cef4j.gen.CefRect;
import net.kurobako.cef4j.gen.CefRenderHandler;
import net.kurobako.cef4j.gen.CefSettings;
import net.kurobako.cef4j.gen.CefWindowInfo;
import net.kurobako.cef4j.gen.NativePointer;
import net.kurobako.cef4j.osr.swing.CefBrowserView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Sample Swing browser using {@link CefBrowserView}. */
public final class SwingBrowserApp {

    private static final Logger log = LoggerFactory.getLogger(SwingBrowserApp.class);

    private static volatile CefBrowser browser;
    private static volatile boolean shutdownRequested;

    public static void main(String[] args) throws Exception {
        log.info("cef4j Swing Browser starting");
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SystemBootstrap.load();

        Path cacheDir = Files.createTempDirectory("cef4j-swing-");
        cacheDir.toFile().deleteOnExit();

        CefSettings.Mutable settings = new CefSettings.Mutable();
        settings.cachePath = cacheDir.toAbsolutePath().toString();
        settings.windowlessRenderingEnabled = 1;
        settings.externalMessagePump = 0;
        settings.multiThreadedMessageLoop = 1;
        List<String> extraArgs = new ArrayList<>();
        if (OS.isLinux()) {
            extraArgs.add("--ozone-platform=x11");
            //            extraArgs.add("--no-zygote");
        }
        Cef.INSTANCE.initialise(settings, extraArgs);

        SwingUtilities.invokeAndWait(() -> createUI());
        SigintHelper.install(SwingBrowserApp::shutdown);
    }

    private static void createUI() {
        JFrame window = new JFrame("cef4j Browser (Swing)");
        window.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        window.setSize(1280, 800);

        JTextField urlBar = new JTextField("https://www.example.com");
        urlBar.addActionListener(e -> {
            CefBrowser current = browser;
            if (current != null) {
                current.getMainFrame()
                        .ifPresent(frame -> frame.loadUrl(urlBar.getText().trim()));
            }
        });

        JButton backBtn = new JButton("\u25C0");
        JButton fwdBtn = new JButton("\u25B6");
        JButton reloadBtn = new JButton("\u21BB");
        backBtn.addActionListener(e -> {
            var b = browser;
            if (b != null) b.goBack();
        });
        fwdBtn.addActionListener(e -> {
            var b = browser;
            if (b != null) b.goForward();
        });
        reloadBtn.addActionListener(e -> {
            var b = browser;
            if (b != null) b.reload();
        });

        JPanel navBar = new JPanel(new BorderLayout(4, 0));
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 0));
        buttons.add(backBtn);
        buttons.add(fwdBtn);
        buttons.add(reloadBtn);
        navBar.add(buttons, BorderLayout.WEST);
        navBar.add(urlBar, BorderLayout.CENTER);
        navBar.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

        JLabel statusLabel = new JLabel(" ");
        statusLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, UIManager.getColor("Separator.foreground")),
                BorderFactory.createEmptyBorder(2, 6, 2, 6)));
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setPreferredSize(new Dimension(120, progressBar.getPreferredSize().height));
        progressBar.setVisible(false);
        JPanel statusBar = new JPanel(new BorderLayout());
        statusBar.add(statusLabel, BorderLayout.CENTER);
        statusBar.add(progressBar, BorderLayout.EAST);

        CefBrowserView surface = new CefBrowserView();

        window.setLayout(new BorderLayout());
        window.add(navBar, BorderLayout.NORTH);
        window.add(surface, BorderLayout.CENTER);
        window.add(statusBar, BorderLayout.SOUTH);

        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                shutdown();
            }
        });

        window.setVisible(true);

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
                            CefBrowserHost host = b.getHost().orElse(null);
                            if (host != null) {
                                host.setFocus(true);
                            }
                            surface.requestFocusInWindow();
                        });
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
                                progressBar.setVisible(false);
                                progressBar.setValue(0);
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
                        SwingUtilities.invokeLater(() -> window.setTitle(title + " - cef4j (Swing)"));
                    }

                    @Override
                    public void onAddressChange(CefBrowser b, CefFrame f, String url) {
                        SwingUtilities.invokeLater(() -> urlBar.setText(url));
                    }

                    @Override
                    public void onStatusMessage(CefBrowser b, String value) {
                        SwingUtilities.invokeLater(
                                () -> statusLabel.setText(value != null && !value.isEmpty() ? value : " "));
                    }

                    @Override
                    public void onLoadingProgressChange(CefBrowser b, double progress) {
                        SwingUtilities.invokeLater(() -> {
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
                            CefBrowser b, long cursor, CefCursorType type, NativePointer customCursorInfo) {
                        Cursor awtCursor = surface.mapCursor(type);
                        SwingUtilities.invokeLater(() -> surface.setCursor(awtCursor));
                        return true;
                    }
                });
            }
        };

        CefWindowInfo.Mutable windowInfo = new CefWindowInfo.Mutable();
        windowInfo.bounds = new CefRect(0, 0, Math.max(1, surface.getWidth()), Math.max(1, surface.getHeight()));
        windowInfo.windowlessRenderingEnabled = 1;
        CefBrowserSettings.Mutable browserSettings = new CefBrowserSettings.Mutable();
        browserSettings.windowlessFrameRate = 60;
        CefBrowserHost.createBrowser(
                windowInfo.toImmutable(), client, urlBar.getText().trim(), browserSettings.toImmutable(), null, null);
    }

    private static void shutdown() {
        if (shutdownRequested) return;
        shutdownRequested = true;
        log.info("Shutting down");
        CefBrowser current = browser;
        if (current != null) {
            current.getHost().ifPresent(host -> host.closeBrowser(true));
        }
        if (Cef.INSTANCE.getState() == Cef.State.INITIALISED) {
            Cef.INSTANCE.dispose();
        }
        log.info("Exiting");
        System.exit(0);
    }
}
