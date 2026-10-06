package net.kurobako.cef4j.ipc.session;

/** Marker implemented by decoded protocol messages. */
public interface CefMessageView {

    /** The wire-protocol message ID this view describes. */
    int messageId();
}
