package net.kurobako.cef4j;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

        Object[] result = redirectStderr0();
        if (result == null) {
            log.warn("Failed to redirect native stderr - CEF logs will go to stderr");
            return;
        }

        FileInputStream fis = (FileInputStream) result[0];
        FileOutputStream originalStderr = (FileOutputStream) result[1];

        // Reassign System.err to the original stderr so Java output is unaffected
        System.setErr(new PrintStream(originalStderr, true));

        Thread reader = new Thread(
                () -> {
                    try (BufferedReader br = new BufferedReader(new InputStreamReader(fis, StandardCharsets.UTF_8))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            if (line.contains("ERROR:") || line.contains("WARNING:")) {
                                log.warn("{}", line);
                            } else {
                                log.debug("{}", line);
                            }
                        }
                    } catch (Exception ignored) {
                        // Pipe closed during shutdown, ignore
                    }
                },
                "cef4j-stderr-reader");
        reader.setDaemon(true);
        reader.start();

        installed = true;
    }

    private static native Object[] redirectStderr0();
}
