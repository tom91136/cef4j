package net.kurobako.cef4j.sample;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import net.kurobako.cef4j.CefApp;
import net.kurobako.cef4j.CefBrowserOsr;
import net.kurobako.cef4j.SystemBootstrap;
import net.kurobako.cef4j.gen.CefBrowser;
import net.kurobako.cef4j.gen.CefClient;
import net.kurobako.cef4j.gen.CefCursorType;
import net.kurobako.cef4j.gen.CefDisplayHandler;
import net.kurobako.cef4j.gen.CefFrame;
import net.kurobako.cef4j.gen.CefLifeSpanHandler;
import net.kurobako.cef4j.gen.CefLoadHandler;
import net.kurobako.cef4j.gen.CefRenderHandler;
import net.kurobako.cef4j.gen.NativePointer;
import net.kurobako.cef4j.osr.swing.CefPanel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Sample Swing browser using {@link CefPanel}. */
public final class SwingBrowserApp {

    private static final Logger log = LoggerFactory.getLogger(SwingBrowserApp.class);

    private static CefBrowserOsr browser;
    private static volatile boolean shutdownRequested;

    public static void main(String[] args) throws Exception {
        log.info("cef4j Swing Browser starting");
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SystemBootstrap.load();

        Path cacheDir = Files.createTempDirectory("cef4j-swing-");
        cacheDir.toFile().deleteOnExit();

        CefApp.INSTANCE.cachePath(cacheDir.toAbsolutePath().toString()).initialize();

        SwingUtilities.invokeAndWait(() -> createUI());

        browser.createImmediately();
        var host = browser.getHost();
        if (host != null) host.setFocus(true);

        Thread mainThread = Thread.currentThread();
        SigintHelper.install(() -> {
            shutdownRequested = true;
            mainThread.interrupt();
        });

        // Auto-exit for headless testing: -Dcef4j.exit.after=<millis>
        String exitAfter = System.getProperty("cef4j.exit.after");
        if (exitAfter != null) {
            long delay = Long.parseLong(exitAfter);
            log.info("Auto-exit scheduled in {}ms", delay);
            Executors.newSingleThreadScheduledExecutor(r -> {
                        Thread t = new Thread(r, "cef4j-exit-timer");
                        t.setDaemon(true);
                        return t;
                    })
                    .schedule(() -> shutdownRequested = true, delay, TimeUnit.MILLISECONDS);
        }

        try {
            while (!shutdownRequested && CefApp.INSTANCE.getState() == CefApp.State.INITIALIZED) {
                CefApp.INSTANCE.doMessageLoopWork();
                Thread.sleep(8);
            }
        } catch (InterruptedException ignored) {
        }

        log.info("Shutting down");
        if (browser != null) browser.close(true);
        CefApp.INSTANCE.dispose();
        log.info("Exiting");
        System.exit(0);
    }

    private static int getMonitorRefreshRate() {
        DisplayMode dm = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDisplayMode();
        int rate = dm.getRefreshRate();
        return rate > 0 && rate != DisplayMode.REFRESH_RATE_UNKNOWN ? rate : 60;
    }

    private static void createUI() {
        JFrame window = new JFrame("cef4j Browser (Swing)");
        window.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        window.setSize(1280, 800);

        JTextField urlBar = new JTextField("https://www.example.com");
        urlBar.addActionListener(e -> {
            if (browser != null) browser.loadURL(urlBar.getText().trim());
        });

        JButton backBtn = new JButton("\u25C0");
        JButton fwdBtn = new JButton("\u25B6");
        JButton reloadBtn = new JButton("\u21BB");
        backBtn.addActionListener(e -> {
            var b = browser != null ? browser.getBrowser() : null;
            if (b != null) b.goBack();
        });
        fwdBtn.addActionListener(e -> {
            var b = browser != null ? browser.getBrowser() : null;
            if (b != null) b.goForward();
        });
        reloadBtn.addActionListener(e -> {
            var b = browser != null ? browser.getBrowser() : null;
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

        CefPanel surface = new CefPanel();

        window.setLayout(new BorderLayout());
        window.add(navBar, BorderLayout.NORTH);
        window.add(surface, BorderLayout.CENTER);
        window.add(statusBar, BorderLayout.SOUTH);

        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                shutdownRequested = true;
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
                        SwingUtilities.invokeLater(() -> surface.requestFocusInWindow());
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

        browser = CefApp.INSTANCE.createBrowser(client, urlBar.getText().trim(), getMonitorRefreshRate());
        surface.setBrowser(browser);
    }
}
