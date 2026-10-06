package net.kurobako.cef4j.ipc.devtools;

import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.concurrent.CompletableFuture;
import javax.annotation.Nonnull;
import net.kurobako.cef4j.cdp.CdpTransport;
import net.kurobako.cef4j.ipc.protocol.gen.BrowserHost;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.RemoteHandle;

/** JSON-adapter boundary for attaching a raw CDP channel to a Remote CEF browser. */
public interface RemoteDevToolsSessionFactory {
    @Nonnull
    CompletableFuture<? extends CdpTransport> attach(
            @Nonnull CefSession session, @Nonnull RemoteHandle browser, @Nonnull BrowserHost host);

    /** Returns the installed Gson or Jackson provider. */
    @Nonnull
    static RemoteDevToolsSessionFactory installed() {
        Iterator<RemoteDevToolsSessionFactory> providers =
                ServiceLoader.load(RemoteDevToolsSessionFactory.class).iterator();
        if (!providers.hasNext()) {
            throw new IllegalStateException(
                    "No Remote CDP adapter installed; add cef4j-codecs-gson or cef4j-codecs-jackson");
        }
        RemoteDevToolsSessionFactory provider = providers.next();
        if (providers.hasNext()) {
            throw new IllegalStateException("Multiple Remote CDP adapters installed ("
                    + provider.getClass().getName() + ", "
                    + providers.next().getClass().getName()
                    + "); supply one explicitly");
        }
        return provider;
    }
}
