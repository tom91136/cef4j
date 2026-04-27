package net.kurobako.cef4j.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;

/**
 * Test double for {@link CefHttpEngine}. Two modes of use:
 *
 * <ul>
 *   <li><b>Staged</b>: call {@link #stage(int, Map, byte[])} before the connection fires; {@link #send} dispatches all
 *       callbacks synchronously in-thread.
 *   <li><b>Manual</b>: leave unstaged, grab {@link #capturedSink()} after {@code send} returns, drive
 *       {@code onResponse}/{@code onData}/{@code onComplete}/{@code onError} from the test at whatever timing is needed
 *       (including from another thread).
 * </ul>
 */
final class FakeCefHttpEngine implements CefHttpEngine {

    static FakeCefHttpEngine empty() {
        return new FakeCefHttpEngine();
    }

    final AtomicBoolean cancelled = new AtomicBoolean(false);
    final List<RequestSpec> allSpecs = new ArrayList<>();

    @Nullable
    private RequestSpec lastSpec;

    @Nullable
    private ResponseSink lastSink;

    private int stagedStatus = -1;
    private String stagedStatusText = "";
    private Map<String, List<String>> stagedHeaders = Map.of();
    private byte[] stagedBody = new byte[0];

    @Nullable
    private IOException stagedError;

    private int sendCount = 0;

    FakeCefHttpEngine stage(int status, Map<String, List<String>> headers, byte[] body) {
        this.stagedStatus = status;
        this.stagedStatusText = "OK";
        this.stagedHeaders = headers;
        this.stagedBody = body;
        this.stagedError = null;
        return this;
    }

    FakeCefHttpEngine stageError(IOException error) {
        this.stagedError = error;
        this.stagedStatus = -1;
        return this;
    }

    int sendCount() {
        return sendCount;
    }

    RequestSpec capturedSpec() {
        return Objects.requireNonNull(lastSpec, "send() has not been called");
    }

    ResponseSink capturedSink() {
        return Objects.requireNonNull(lastSink, "send() has not been called");
    }

    @Override
    public Cancellation send(RequestSpec spec, ResponseSink sink) {
        this.lastSpec = spec;
        this.lastSink = sink;
        this.allSpecs.add(spec);
        this.sendCount++;
        IOException err = this.stagedError;
        if (err != null) {
            sink.onError(err);
        } else if (stagedStatus >= 0) {
            sink.onResponse(stagedStatus, stagedStatusText, stagedHeaders);
            if (stagedBody.length > 0) sink.onData(stagedBody);
            sink.onComplete();
        }
        return () -> cancelled.set(true);
    }
}
