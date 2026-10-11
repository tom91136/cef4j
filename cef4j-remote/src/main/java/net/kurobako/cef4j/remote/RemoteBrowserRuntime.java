package net.kurobako.cef4j.remote;

import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.RemoteHandle;

/** One owned Remote CEF browser connection, without exposing concrete control or frame transports. */
public interface RemoteBrowserRuntime extends AutoCloseable {
    CefSession session();

    RemoteHandle browser();

    @Override
    void close();
}
