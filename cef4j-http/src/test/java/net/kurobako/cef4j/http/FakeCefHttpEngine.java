package net.kurobako.cef4j.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;

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

    private final AtomicInteger sendCount = new AtomicInteger();

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
        return sendCount.get();
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
        this.sendCount.incrementAndGet();
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
