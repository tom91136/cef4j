package net.kurobako.cef4j.osr.jfx;

/** Failure-only diagnostics for the generated JavaFX/CEF compatibility tests. */
public final class CefWebViewTestDiagnostics {
    private CefWebViewTestDiagnostics() {}

    public static String describe(Object view) {
        if (!(view instanceof CefWebView)) return "stock JavaFX WebView";
        CefWebView cefView = (CefWebView) view;
        return "cefFramesPainted=" + cefView.framesPainted.sum() + ", browserPresent=" + (cefView.getBrowser() != null)
                + ", hostPresent=" + (cefView.getBrowserHost() != null);
    }
}
