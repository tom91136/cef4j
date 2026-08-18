package net.kurobako.cef4j.ipc.devtools.gson;

import java.util.concurrent.CompletableFuture;
import net.kurobako.cef4j.cdp.CdpTransport;
import net.kurobako.cef4j.cdp.gson.GsonCdpCodec;
import net.kurobako.cef4j.ipc.devtools.DevToolsSession;
import net.kurobako.cef4j.ipc.devtools.RemoteDevToolsSessionFactory;
import net.kurobako.cef4j.ipc.protocol.gen.BrowserHost;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.RemoteHandle;

/** Gson provider for Remote CEF CDP attachment. */
public final class GsonRemoteDevToolsSessionFactory implements RemoteDevToolsSessionFactory {
    @Override
    public CompletableFuture<? extends CdpTransport> attach(
            CefSession session, RemoteHandle browser, BrowserHost host) {
        return DevToolsSession.attach(session, browser, host, new GsonCdpCodec());
    }
}
