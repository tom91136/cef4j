package net.kurobako.cef4j.sample;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import net.kurobako.cef4j.CefApp;
import net.kurobako.cef4j.CefBrowserOsr;
import net.kurobako.cef4j.CefClient;
import net.kurobako.cef4j.SystemBootstrap;
import net.kurobako.cef4j.gen.CefCursorType;
import net.kurobako.cef4j.gen.CefDisplayHandler;
import net.kurobako.cef4j.gen.CefLifeSpanHandler;
import net.kurobako.cef4j.gen.CefLoadHandler;
import net.kurobako.cef4j.osr.swing.CefPanel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Sample Swing browser using {@link CefPanel}. */
public final class SwingBrowserApp {

    private static final Logger log = LoggerFactory.getLogger(SwingBrowserApp.class);

    private static CefApp cefApp;
    private static CefBrowserOsr browser;
    private static volatile boolean shutdownRequested;

    public static void main(String[] args) throws Exception {
        log.info("cef4j Swing Browser starting");
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SystemBootstrap.load();

        Path cacheDir = Files.createTempDirectory("cef4j-swing-");
        cacheDir.toFile().deleteOnExit();

        cefApp = CefApp.getInstance(cacheDir.toAbsolutePath().toString(), null, true, null, new String[] {});
        cefApp.initialize();

        SwingUtilities.invokeAndWait(() -> createUI());

        browser.createImmediately();
        browser.setFocus(true);

        installSigintHandler(Thread.currentThread());

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
            while (!shutdownRequested && cefApp.getState() == CefApp.State.INITIALIZED) {
                cefApp.doMessageLoopWork();
                Thread.sleep(8);
            }
        } catch (InterruptedException ignored) {
        }

        log.info("Shutting down");
        if (browser != null) browser.close(true);
        cefApp.dispose();
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
            if (browser != null) browser.goBack();
        });
        fwdBtn.addActionListener(e -> {
            if (browser != null) browser.goForward();
        });
        reloadBtn.addActionListener(e -> {
            if (browser != null) browser.reload();
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

        CefClient client = cefApp.createClient();
        client.addLifeSpanHandler(new CefLifeSpanHandler() {
                    @Override
                    public void onAfterCreated(long b) {
                        SwingUtilities.invokeLater(() -> surface.requestFocusInWindow());
                    }
                })
                .addLoadHandler(new CefLoadHandler() {
                    @Override
                    public void onLoadingStateChange(
                            long b, boolean isLoading, boolean canGoBack, boolean canGoForward) {
                        SwingUtilities.invokeLater(() -> {
                            backBtn.setEnabled(canGoBack);
                            fwdBtn.setEnabled(canGoForward);
                            if (!isLoading) {
                                progressBar.setVisible(false);
                                progressBar.setValue(0);
                            }
                        });
                    }
                })
                .addDisplayHandler(new CefDisplayHandler() {
                    @Override
                    public void onTitleChange(long b, String title) {
                        SwingUtilities.invokeLater(() -> window.setTitle(title + " - cef4j (Swing)"));
                    }

                    @Override
                    public void onAddressChange(long b, long f, String url) {
                        SwingUtilities.invokeLater(() -> urlBar.setText(url));
                    }

                    @Override
                    public void onStatusMessage(long b, String value) {
                        SwingUtilities.invokeLater(
                                () -> statusLabel.setText(value != null && !value.isEmpty() ? value : " "));
                    }

                    @Override
                    public void onLoadingProgressChange(long b, double progress) {
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
                    public boolean onCursorChange(long b, long cursor, CefCursorType type, long customCursorInfo) {
                        Cursor awtCursor = surface.mapCursor(type);
                        SwingUtilities.invokeLater(() -> surface.setCursor(awtCursor));
                        return true;
                    }
                });

        browser = surface.createBrowser(client, urlBar.getText().trim(), getMonitorRefreshRate());
    }

    private static void installSigintHandler(Thread threadToInterrupt) {
        try {
            Class<?> signalClass = Class.forName("sun.misc.Signal");
            Class<?> handlerClass = Class.forName("sun.misc.SignalHandler");
            Object sigInt = signalClass.getConstructor(String.class).newInstance("INT");
            Object handler = java.lang.reflect.Proxy.newProxyInstance(
                    handlerClass.getClassLoader(), new Class<?>[] {handlerClass}, (proxy, method, margs) -> {
                        if ("handle".equals(method.getName())) {
                            shutdownRequested = true;
                            threadToInterrupt.interrupt();
                        }
                        return null;
                    });
            signalClass.getMethod("handle", signalClass, handlerClass).invoke(null, sigInt, handler);
        } catch (Exception e) {
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                shutdownRequested = true;
                threadToInterrupt.interrupt();
            }));
        }
    }
}
