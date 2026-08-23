package net.kurobako.cef4j.sample;

import net.kurobako.cef4j.OS;

final class SampleShutdown {
    private SampleShutdown() {}

    static void afterCefTermination() {
        if (!OS.isMacOS()) return;
        // XXX: CEF 150 can enter AppKit/CFRunLoop callbacks after cef_shutdown during macOS JVM teardown; remove this
        // hard exit when the minimum CEF major exceeds 150 and all three macOS sample smoke tests return normally.
        Runtime.getRuntime().halt(0);
    }
}
