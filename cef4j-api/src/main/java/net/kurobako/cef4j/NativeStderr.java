package net.kurobako.cef4j;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Captures native stderr output (from CEF and its subprocesses) and routes it through SLF4J.
 *
 * <p>Call {@link #install()} after loading the native library but before {@code cef_initialize}. Subprocesses inherit
 * the redirected stderr fd, so their output is also captured.
 */
final class NativeStderr {

    private static final Logger log = LoggerFactory.getLogger("cef4j.native");
    private static volatile boolean installed = false;

    private NativeStderr() {}

    /**
     * Redirect native stderr to a pipe and start a daemon thread that reads lines and logs them via SLF4J.
     *
     * <p>Java's {@code System.err} is reassigned to the original stderr fd, so only native (CEF) output goes through
     * the SLF4J pipe.
     */
    static synchronized void install() {
        if (installed) return;
        if (Boolean.getBoolean("cef4j.disableStderrRedirect")) {
            log.debug("Stderr redirect disabled via -Dcef4j.disableStderrRedirect=true");
            return;
        }

        Object[] result = redirectStderr0();
        if (result == null) {
            log.warn("Failed to redirect native stderr - CEF logs will go to stderr");
            return;
        }

        FileInputStream fis = (FileInputStream) result[0];
        FileOutputStream originalStderr = (FileOutputStream) result[1];

        System.setErr(new PrintStream(originalStderr, true, StandardCharsets.UTF_8));

        Thread reader = new Thread(
                () -> {
                    try (BufferedReader br = new BufferedReader(new InputStreamReader(fis, StandardCharsets.UTF_8))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            if (isActionable(line)) {
                                log.warn("{}", line);
                            } else {
                                log.debug("{}", line);
                            }
                        }
                    } catch (IOException ignored) {
                        // Pipe closed during shutdown, ignore
                    }
                },
                "cef4j-stderr-reader");
        reader.setDaemon(true);
        reader.start();

        installed = true;
    }

    static boolean isActionable(String line) {
        if (line.contains("Default dialog implementation requires a parent window handle")) return false;
        if (line.contains("Add application/vnd.portal.filetransfer to kAtomsToCache")) return false;
        if (line.contains("Add application/vnd.portal.files to kAtomsToCache")) return false;
        return line.contains("ERROR:") || line.contains("WARNING:");
    }

    /**
     * Tell the native crash handler the exact path to chrome_debug.log so crash messages include it. Call after
     * {@code cef_initialize} once the cache path is known.
     */
    static void setCrashLogPath(String cachePath) {
        if (!installed || cachePath == null || cachePath.isEmpty()) return;
        String sep = cachePath.endsWith("/") || cachePath.endsWith("\\") ? "" : System.getProperty("file.separator");
        setCrashLogPath0(cachePath + sep + "chrome_debug.log");
    }

    static boolean internalDescriptorsCloseOnExec() {
        return OS.isWindows() || internalDescriptorsCloseOnExec0();
    }

    private static native Object[] redirectStderr0();

    private static native void setCrashLogPath0(String path);

    private static native boolean internalDescriptorsCloseOnExec0();
}
