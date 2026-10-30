package net.kurobako.cef4j.http;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;
import net.kurobako.cef4j.gen.CefErrorCode;
import net.kurobako.cef4j.gen.CefGlobals;
import net.kurobako.cef4j.gen.CefPostData;
import net.kurobako.cef4j.gen.CefPostDataElement;
import net.kurobako.cef4j.gen.CefRequest;
import net.kurobako.cef4j.gen.CefResponse;
import net.kurobako.cef4j.gen.CefTask;
import net.kurobako.cef4j.gen.CefThreadId;
import net.kurobako.cef4j.gen.CefUrlRequest;
import net.kurobako.cef4j.gen.CefUrlRequestClient;
import net.kurobako.cef4j.gen.CefUrlRequestStatus;
import org.jspecify.annotations.Nullable;

/**
 * Default {@link CefHttpEngine} that wraps {@link CefUrlRequest}.
 *
 * <p>CEF requires URL requests to be created on a valid CEF thread. {@link #send} detects whether the caller is already
 * on the UI thread and, if not, posts the create-and-fire onto it via {@link CefGlobals#postTask}. The returned
 * {@link Cancellation} is async-safe: cancelling before the task runs prevents the request, cancelling after cancels
 * the in-flight {@link CefUrlRequest}.
 */
final class CefUrlRequestHttpEngine implements CefHttpEngine {

    static final CefUrlRequestHttpEngine INSTANCE = new CefUrlRequestHttpEngine();

    private static final CefThreadId UI = CefThreadId.of(CefThreadId.Kind.UI);

    private CefUrlRequestHttpEngine() {}

    @Override
    public Cancellation send(RequestSpec spec, ResponseSink sink) {
        if (CefGlobals.currentlyOn(UI) != 0) return sendOnUi(spec, sink);
        final AtomicReference<@Nullable Cancellation> handle = new AtomicReference<>();
        final AtomicBoolean cancelled = new AtomicBoolean(false);
        final boolean posted = CefGlobals.postTask(UI, new CefTask() {
            @Override
            public void execute() {
                if (cancelled.get()) return;
                final Cancellation c = sendOnUi(spec, sink);
                handle.set(c);
                if (cancelled.get()) c.cancel();
            }
        });
        if (!posted) {
            sink.onError(new IOException("Failed to post CefUrlRequest creation onto CEF UI thread"));
            return () -> {};
        }
        return () -> {
            cancelled.set(true);
            final Cancellation c = handle.get();
            if (c != null) c.cancel();
        };
    }

    private Cancellation sendOnUi(RequestSpec spec, ResponseSink sink) {
        final CefRequest req = CefRequest.create().orElse(null);
        if (req == null) {
            sink.onError(new IOException("CefRequest.create() returned empty - is CEF initialised?"));
            return () -> {};
        }
        CefPostData postData;
        try {
            postData = buildPostData(spec.body).orElse(null);
        } catch (IOException failure) {
            sink.onError(failure);
            return () -> {};
        }
        req.set(spec.url, spec.method, postData, spec.headers);

        final AtomicBoolean responseFired = new AtomicBoolean(false);
        final CefUrlRequestClient client = new CefUrlRequestClient() {
            @Override
            public void onDownloadProgress(@Nullable CefUrlRequest request, long current, long total) {
                fireResponseIfReady(request);
            }

            @Override
            public void onDownloadData(@Nullable CefUrlRequest request, ByteBuffer data) {
                fireResponseIfReady(request);
                final int n = data.remaining();
                if (n == 0) return;
                // XXX: CEF owns this callback buffer only until onDownloadData returns; remove the copy only if the CEF
                // API contract changes to transfer buffer ownership to the client.
                final byte[] copy = new byte[n];
                data.get(copy);
                sink.onData(copy);
            }

            @Override
            public void onRequestComplete(@Nullable CefUrlRequest request) {
                fireResponseIfReady(request);
                if (request == null) {
                    sink.onError(new IOException("CefUrlRequest completed without handle"));
                    return;
                }
                final CefUrlRequestStatus status = request.getRequestStatus();
                final Optional<CefUrlRequestStatus.Kind> kind = status.kind();
                if (kind.isPresent() && kind.get() == CefUrlRequestStatus.Kind.SUCCESS) {
                    sink.onComplete();
                } else {
                    final CefErrorCode err = request.getRequestError();
                    sink.onError(new IOException("CEF URL request failed: status=" + status + " error=" + err));
                }
            }

            private void fireResponseIfReady(@Nullable CefUrlRequest request) {
                if (request == null) return;
                if (!responseFired.compareAndSet(false, true)) return;
                final Optional<CefResponse> resp = request.getResponse();
                if (resp.isEmpty()) {
                    responseFired.set(false);
                    return;
                }
                final CefResponse r = resp.get();
                final Map<String, List<String>> headers = r.getHeaderMap();
                sink.onResponse(r.getStatus(), r.getStatusText().orElse(""), headers);
            }
        };

        final CefUrlRequest handle = CefUrlRequest.create(req, client, null).orElse(null);
        if (handle == null) {
            sink.onError(new IOException("CefUrlRequest.create() returned empty"));
            return () -> {};
        }
        return () -> cancelOnUi(handle);
    }

    private static void cancelOnUi(CefUrlRequest handle) {
        if (CefGlobals.currentlyOn(UI) != 0) {
            handle.cancel();
        } else {
            // XXX: CEF requires CefUrlRequest.cancel on TID_UI; remove dispatch only when the API permits cancellation
            // from arbitrary threads.
            CefGlobals.postTask(UI, new CefTask() {
                @Override
                public void execute() {
                    handle.cancel();
                }
            });
        }
    }

    private static Optional<CefPostData> buildPostData(byte[] body) throws IOException {
        return buildPostData(body, CefPostDataElement::create, CefPostData::create);
    }

    static Optional<CefPostData> buildPostData(
            byte[] body,
            Supplier<Optional<CefPostDataElement>> elementFactory,
            Supplier<Optional<CefPostData>> postDataFactory)
            throws IOException {
        if (body.length == 0) return Optional.empty();
        final CefPostDataElement el = elementFactory
                .get()
                .orElseThrow(() -> new IOException("CefPostDataElement.create() returned empty - is CEF initialised?"));
        final CefPostData pd = postDataFactory
                .get()
                .orElseThrow(() -> new IOException("CefPostData.create() returned empty - is CEF initialised?"));
        final ByteBuffer buf = ByteBuffer.allocateDirect(body.length);
        buf.put(body);
        buf.flip();
        el.setToBytes(buf);
        if (!pd.addElement(el)) {
            throw new IOException("Failed to add post data element to CefPostData");
        }
        return Optional.of(pd);
    }
}
