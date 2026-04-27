package net.kurobako.cef4j.http;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
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
    @Nonnull
    public Cancellation send(@Nonnull RequestSpec spec, @Nonnull ResponseSink sink) {
        if (CefGlobals.currentlyOn(UI) != 0) return sendOnUi(spec, sink);
        AtomicReference<Cancellation> handle = new AtomicReference<>();
        AtomicBoolean cancelled = new AtomicBoolean(false);
        // CefTask has a default execute() (not a SAM); use an anonymous class.
        boolean posted = CefGlobals.postTask(UI, new CefTask() {
            @Override
            public void execute() {
                if (cancelled.get()) return;
                Cancellation c = sendOnUi(spec, sink);
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
            Cancellation c = handle.get();
            if (c != null) c.cancel();
        };
    }

    @Nonnull
    private Cancellation sendOnUi(@Nonnull RequestSpec spec, @Nonnull ResponseSink sink) {
        CefRequest req = CefRequest.create().orElse(null);
        if (req == null) {
            sink.onError(new IOException("CefRequest.create() returned empty - is CEF initialised?"));
            return () -> {};
        }
        req.set(spec.url, spec.method, buildPostData(spec.body), spec.headers);

        AtomicBoolean responseFired = new AtomicBoolean(false);
        CefUrlRequestClient client = new CefUrlRequestClient() {
            @Override
            public void onDownloadProgress(@Nullable CefUrlRequest request, long current, long total) {
                fireResponseIfReady(request);
            }

            @Override
            public void onDownloadData(@Nullable CefUrlRequest request, @Nonnull ByteBuffer data) {
                fireResponseIfReady(request);
                int n = data.remaining();
                if (n == 0) return;
                // ByteBuffer is scoped to the callback; copy before returning.
                byte[] copy = new byte[n];
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
                CefUrlRequestStatus status = request.getRequestStatus();
                Optional<CefUrlRequestStatus.Kind> kind = status.kind();
                if (kind.isPresent() && kind.get() == CefUrlRequestStatus.Kind.SUCCESS) {
                    sink.onComplete();
                } else {
                    CefErrorCode err = request.getRequestError();
                    sink.onError(new IOException("CEF URL request failed: status=" + status + " error=" + err));
                }
            }

            private void fireResponseIfReady(@Nullable CefUrlRequest request) {
                if (request == null) return;
                if (!responseFired.compareAndSet(false, true)) return;
                Optional<CefResponse> resp = request.getResponse();
                if (!resp.isPresent()) {
                    // Headers not yet materialised; rewind and try later.
                    responseFired.set(false);
                    return;
                }
                CefResponse r = resp.get();
                Map<String, List<String>> headers = new HashMap<>();
                r.getHeaderMap(headers);
                sink.onResponse(r.getStatus(), r.getStatusText().orElse(""), headers);
            }
        };

        CefUrlRequest handle = CefUrlRequest.create(req, client, null).orElse(null);
        if (handle == null) {
            sink.onError(new IOException("CefUrlRequest.create() returned empty"));
            return () -> {};
        }
        return handle::cancel;
    }

    @Nullable
    private static CefPostData buildPostData(@Nonnull byte[] body) {
        if (body.length == 0) return null;
        CefPostDataElement el = CefPostDataElement.create().orElse(null);
        CefPostData pd = CefPostData.create().orElse(null);
        if (el == null || pd == null) return null;
        ByteBuffer buf = ByteBuffer.allocateDirect(body.length);
        buf.put(body);
        buf.flip();
        el.setToBytes(buf);
        pd.addElement(el);
        return pd;
    }
}
