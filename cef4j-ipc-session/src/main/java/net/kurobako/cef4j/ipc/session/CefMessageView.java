package net.kurobako.cef4j.ipc.session;

/**
 * Marker for a decoded view over a payload {@link java.nio.ByteBuffer}. Codegen produces concrete subtypes per CEF API
 * method in Slice D; for now hand-written messages and tests implement this directly.
 *
 * <p>Views are typically flyweight: they hold a reference to the payload buffer and offer typed accessors. Since the
 * underlying buffer is owned by the transport and only valid for the duration of the receive callback, callers that
 * outlive the callback must materialise the data via {@code .toString()} / explicit accessor calls before retaining the
 * view.
 */
public interface CefMessageView {

    /** The wire-protocol message ID this view describes. */
    int messageId();
}
