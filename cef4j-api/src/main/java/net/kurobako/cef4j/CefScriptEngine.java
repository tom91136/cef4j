package net.kurobako.cef4j;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import net.kurobako.cef4j.gen.CefBrowser;
import net.kurobako.cef4j.gen.CefFrame;
import net.kurobako.cef4j.gen.CefListValue;
import net.kurobako.cef4j.gen.CefProcessId;
import net.kurobako.cef4j.gen.CefProcessMessage;
import net.kurobako.cef4j.gen.CefValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Evaluates JavaScript in the renderer process and returns results via {@code CefProcessMessage} IPC.
 *
 * <p>Supports two modes:
 *
 * <ul>
 *   <li><b>JSON mode</b> - eval returns a JSON string, caller parses.
 *   <li><b>Handle mode</b> - eval returns an opaque integer handle to a V8 value kept alive in the renderer. Subsequent
 *       operations ({@link #getProperty}, {@link #call}, {@link #release}) reference the handle.
 * </ul>
 *
 * <p>The renderer subprocess must include the matching native eval relay ({@code subprocess_main.cpp}).
 *
 * <p>Thread safety: all public methods are safe to call from any thread. IPC ordering is per-frame as guaranteed by
 * CEF.
 */
public final class CefScriptEngine {

    private static final Logger log = LoggerFactory.getLogger(CefScriptEngine.class);

    // IPC message names - must match subprocess_main.cpp
    private static final String MSG_EVAL = "cef4j:eval";
    private static final String MSG_GET = "cef4j:get";
    private static final String MSG_SET = "cef4j:set";
    private static final String MSG_CALL = "cef4j:call";
    private static final String MSG_INVOKE = "cef4j:invoke";
    private static final String MSG_RELEASE = "cef4j:release";
    private static final String MSG_CREATE_CALLBACK = "cef4j:mkcb";
    private static final String MSG_RESULT = "cef4j:result";
    private static final String MSG_CALLBACK = "cef4j:cb";

    // Result type constants - must match subprocess_main.cpp
    private static final int TYPE_JSON = 0;
    private static final int TYPE_HANDLE = 1;
    private static final int TYPE_VOID = 2;
    private static final int TYPE_ERROR = 3;

    // Eval mode constants
    private static final int MODE_JSON = 0;
    private static final int MODE_HANDLE = 1;

    /**
     * Receives callback invocations from JS. Each argument is a V8 handle ID that can be used with
     * {@link #getProperty}, {@link #call}, etc. A handle ID of {@code -1} indicates null/undefined. The caller is
     * responsible for releasing argument handles when done.
     */
    @FunctionalInterface
    public interface CallbackHandler {
        void onCallback(int[] argHandles);
    }

    /** Result from a script evaluation or handle operation. */
    public static final class Result {

        private final int type;
        private final String stringPayload;
        private final int intPayload;

        private Result(int type, String stringPayload, int intPayload) {
            this.type = type;
            this.stringPayload = stringPayload;
            this.intPayload = intPayload;
        }

        static Result json(String value) {
            return new Result(TYPE_JSON, value, 0);
        }

        static Result handle(int id) {
            return new Result(TYPE_HANDLE, null, id);
        }

        static Result voidResult() {
            return new Result(TYPE_VOID, null, 0);
        }

        static Result error(String message) {
            return new Result(TYPE_ERROR, message, 0);
        }

        /** True if this result carries a JSON string payload. */
        public boolean isJson() {
            return type == TYPE_JSON;
        }

        /** True if this result carries a V8 handle ID. */
        public boolean isHandle() {
            return type == TYPE_HANDLE;
        }

        /** True if this result indicates void (no value). */
        public boolean isVoid() {
            return type == TYPE_VOID;
        }

        /** True if this result indicates an error. */
        public boolean isError() {
            return type == TYPE_ERROR;
        }

        /** Returns the JSON string payload. Only valid when {@link #isJson()} is true. */
        public String json() {
            if (type != TYPE_JSON) throw new IllegalStateException("Not a JSON result");
            return stringPayload;
        }

        /** Returns the V8 handle ID. Only valid when {@link #isHandle()} is true. */
        public int handle() {
            if (type != TYPE_HANDLE) throw new IllegalStateException("Not a handle result");
            return intPayload;
        }

        /** Returns the error message. Only valid when {@link #isError()} is true. */
        public String error() {
            if (type != TYPE_ERROR) throw new IllegalStateException("Not an error result");
            return stringPayload;
        }

        @Override
        public String toString() {
            switch (type) {
                case TYPE_JSON:
                    return "Result.Json(" + stringPayload + ")";
                case TYPE_HANDLE:
                    return "Result.Handle(" + intPayload + ")";
                case TYPE_VOID:
                    return "Result.Void";
                case TYPE_ERROR:
                    return "Result.Error(" + stringPayload + ")";
                default:
                    return "Result.Unknown(" + type + ")";
            }
        }
    }

    private final Supplier<CefFrame> frameSupplier;
    private final AtomicInteger nextId = new AtomicInteger(1);
    private final AtomicInteger nextCallbackId = new AtomicInteger(1);
    private final ConcurrentHashMap<Integer, CompletableFuture<Result>> pending = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Integer, CallbackHandler> callbacks = new ConcurrentHashMap<>();

    /**
     * Creates a new engine that obtains the target frame from the given supplier on each operation.
     *
     * @param frameSupplier returns the current {@link CefFrame}, or {@code null} if not yet available
     */
    public CefScriptEngine(@Nonnull Supplier<CefFrame> frameSupplier) {
        this.frameSupplier = Objects.requireNonNull(frameSupplier, "frameSupplier");
    }

    private CefFrame frame() {
        CefFrame f = frameSupplier.get();
        if (f == null) throw new IllegalStateException("No frame available");
        return f;
    }

    /**
     * Evaluate a JavaScript expression and return the result as a JSON string.
     *
     * @param expression the JS expression to evaluate
     * @return future completing with the JSON-serialized result
     */
    @Nonnull
    public CompletableFuture<String> evaluate(@Nonnull String expression) {
        Objects.requireNonNull(expression, "expression");
        return sendEval(frame(), expression, MODE_JSON).thenApply(result -> {
            if (result.isError()) throw new CefScriptException(result.error());
            if (result.isJson()) return result.json();
            if (result.isVoid()) return null;
            throw new IllegalStateException("Unexpected result type for JSON eval: " + result);
        });
    }

    /**
     * Evaluate a JavaScript expression and return an opaque handle to the V8 value. The handle keeps the value alive in
     * the renderer until {@link #release} is called.
     *
     * @param expression the JS expression to evaluate
     * @return future completing with the handle ID
     */
    @Nonnull
    public CompletableFuture<Integer> evaluateHandle(@Nonnull String expression) {
        Objects.requireNonNull(expression, "expression");
        return sendEval(frame(), expression, MODE_HANDLE).thenApply(result -> {
            if (result.isError()) throw new CefScriptException(result.error());
            if (result.isHandle()) return result.handle();
            throw new IllegalStateException("Unexpected result type for handle eval: " + result);
        });
    }

    /**
     * Get a property from a V8 handle.
     *
     * @param handleId the handle ID
     * @param key the property name
     * @param asHandle if true, return the property as a new handle; if false, JSON-serialize it
     * @return future completing with the result
     */
    @Nonnull
    public CompletableFuture<Result> getProperty(int handleId, @Nonnull String key, boolean asHandle) {
        Objects.requireNonNull(key, "key");
        int reqId = nextId.getAndIncrement();
        CompletableFuture<Result> future = new CompletableFuture<>();
        pending.put(reqId, future);

        CefProcessMessage msg = CefProcessMessage.create(MSG_GET).orElse(null);
        if (msg == null) {
            pending.remove(reqId);
            future.completeExceptionally(new IllegalStateException("Failed to create CefProcessMessage"));
            return future;
        }
        CefListValue args = msg.getArgumentList().orElse(null);
        if (args == null) {
            pending.remove(reqId);
            future.completeExceptionally(new IllegalStateException("Failed to get argument list"));
            return future;
        }
        args.setSize(4);
        args.setInt(0, reqId);
        args.setInt(1, handleId);
        args.setString(2, key);
        args.setInt(3, asHandle ? MODE_HANDLE : MODE_JSON);
        frame().sendProcessMessage(CefProcessId.of(CefProcessId.Kind.RENDERER), msg);
        return future;
    }

    /**
     * Set a property on a V8 handle. The value is provided as a JSON string.
     *
     * @param handleId the handle ID
     * @param key the property name
     * @param valueJson the value as JSON (e.g. "42", "\"hello\"", "{}")
     * @return future completing when the set is acknowledged
     */
    @Nonnull
    public CompletableFuture<Void> setProperty(int handleId, @Nonnull String key, @Nonnull String valueJson) {
        Objects.requireNonNull(key, "key");
        Objects.requireNonNull(valueJson, "valueJson");
        int reqId = nextId.getAndIncrement();
        CompletableFuture<Result> future = new CompletableFuture<>();
        pending.put(reqId, future);

        CefProcessMessage msg = CefProcessMessage.create(MSG_SET).orElse(null);
        if (msg == null) {
            pending.remove(reqId);
            return failedFuture(new IllegalStateException("Failed to create CefProcessMessage"));
        }
        CefListValue args = msg.getArgumentList().orElse(null);
        if (args == null) {
            pending.remove(reqId);
            return failedFuture(new IllegalStateException("Failed to get argument list"));
        }
        args.setSize(4);
        args.setInt(0, reqId);
        args.setInt(1, handleId);
        args.setString(2, key);
        args.setString(3, valueJson);
        frame().sendProcessMessage(CefProcessId.of(CefProcessId.Kind.RENDERER), msg);
        return future.thenApply(result -> {
            if (result.isError()) throw new CefScriptException(result.error());
            return null;
        });
    }

    /**
     * Call a method on a V8 handle.
     *
     * @param handleId the handle ID (the receiver)
     * @param method the method name
     * @param argsJson JSON array of arguments (e.g. "[1, \"hello\"]")
     * @param asHandle if true, return the call result as a new handle; if false, JSON-serialize it
     * @return future completing with the result
     */
    @Nonnull
    public CompletableFuture<Result> call(
            int handleId, @Nonnull String method, @Nonnull String argsJson, boolean asHandle) {
        Objects.requireNonNull(method, "method");
        Objects.requireNonNull(argsJson, "argsJson");
        int reqId = nextId.getAndIncrement();
        CompletableFuture<Result> future = new CompletableFuture<>();
        pending.put(reqId, future);

        CefProcessMessage msg = CefProcessMessage.create(MSG_CALL).orElse(null);
        if (msg == null) {
            pending.remove(reqId);
            future.completeExceptionally(new IllegalStateException("Failed to create CefProcessMessage"));
            return future;
        }
        CefListValue args = msg.getArgumentList().orElse(null);
        if (args == null) {
            pending.remove(reqId);
            future.completeExceptionally(new IllegalStateException("Failed to get argument list"));
            return future;
        }
        args.setSize(5);
        args.setInt(0, reqId);
        args.setInt(1, handleId);
        args.setString(2, method);
        args.setString(3, argsJson);
        args.setInt(4, asHandle ? MODE_HANDLE : MODE_JSON);
        frame().sendProcessMessage(CefProcessId.of(CefProcessId.Kind.RENDERER), msg);
        return future;
    }

    /**
     * Invoke a V8 handle as a function (not as a method on an object).
     *
     * @param handleId the handle ID of the function
     * @param argsJson JSON array of arguments (e.g. "[1, \"hello\"]")
     * @param asHandle if true, return the result as a new handle; if false, JSON-serialize it
     * @return future completing with the result
     */
    @Nonnull
    public CompletableFuture<Result> invoke(int handleId, @Nonnull String argsJson, boolean asHandle) {
        Objects.requireNonNull(argsJson, "argsJson");
        int reqId = nextId.getAndIncrement();
        CompletableFuture<Result> future = new CompletableFuture<>();
        pending.put(reqId, future);

        CefProcessMessage msg = CefProcessMessage.create(MSG_INVOKE).orElse(null);
        if (msg == null) {
            pending.remove(reqId);
            future.completeExceptionally(new IllegalStateException("Failed to create CefProcessMessage"));
            return future;
        }
        CefListValue args = msg.getArgumentList().orElse(null);
        if (args == null) {
            pending.remove(reqId);
            future.completeExceptionally(new IllegalStateException("Failed to get argument list"));
            return future;
        }
        args.setSize(4);
        args.setInt(0, reqId);
        args.setInt(1, handleId);
        args.setString(2, argsJson);
        args.setInt(3, asHandle ? MODE_HANDLE : MODE_JSON);

        frame().sendProcessMessage(CefProcessId.of(CefProcessId.Kind.RENDERER), msg);
        return future;
    }

    /**
     * Release a V8 handle in the renderer. Fire-and-forget - no reply expected.
     *
     * @param handleId the handle ID to release
     */
    public void release(int handleId) {
        CefFrame f = frameSupplier.get();
        if (f == null) return; // browser closing, handles will be cleaned up
        CefProcessMessage msg = CefProcessMessage.create(MSG_RELEASE).orElse(null);
        if (msg == null) {
            log.warn("Failed to create CefProcessMessage for release");
            return;
        }
        CefListValue args = msg.getArgumentList().orElse(null);
        if (args == null) {
            log.warn("Failed to get argument list for release message");
            return;
        }
        args.setSize(1);
        args.setInt(0, handleId);
        f.sendProcessMessage(CefProcessId.of(CefProcessId.Kind.RENDERER), msg);
    }

    /**
     * Create a JavaScript function in the renderer that, when called from JS, sends the arguments back to Java via IPC.
     *
     * <p>The returned handle is a V8 function that can be passed to JS APIs expecting callbacks (e.g. event handlers).
     * When JS invokes the function, the {@code handler} is called with a JSON array of the arguments.
     *
     * <p>The callback returns {@code undefined} to JS - it cannot synchronously return a value. This covers event
     * handlers and notification-style callbacks.
     *
     * @param handler receives an array of V8 handle IDs for the arguments each time JS calls the function
     * @return future completing with the V8 handle ID of the JS function
     */
    @Nonnull
    public CompletableFuture<Integer> createCallback(@Nonnull CallbackHandler handler) {
        Objects.requireNonNull(handler, "handler");
        int callbackId = nextCallbackId.getAndIncrement();
        callbacks.put(callbackId, handler);

        int reqId = nextId.getAndIncrement();
        CompletableFuture<Result> future = new CompletableFuture<>();
        pending.put(reqId, future);

        CefProcessMessage msg = CefProcessMessage.create(MSG_CREATE_CALLBACK).orElse(null);
        if (msg == null) {
            pending.remove(reqId);
            callbacks.remove(callbackId);
            return failedFuture(new IllegalStateException("Failed to create CefProcessMessage"));
        }
        CefListValue args = msg.getArgumentList().orElse(null);
        if (args == null) {
            pending.remove(reqId);
            callbacks.remove(callbackId);
            return failedFuture(new IllegalStateException("Failed to get argument list"));
        }
        args.setSize(2);
        args.setInt(0, reqId);
        args.setInt(1, callbackId);

        frame().sendProcessMessage(CefProcessId.of(CefProcessId.Kind.RENDERER), msg);
        return future.thenApply(result -> {
            if (result.isError()) {
                callbacks.remove(callbackId);
                throw new CefScriptException(result.error());
            }
            if (result.isHandle()) return result.handle();
            callbacks.remove(callbackId);
            throw new IllegalStateException("Unexpected result type for createCallback: " + result);
        });
    }

    /**
     * Handle an incoming process message. Call this from {@code CefClient.onProcessMessageReceived}.
     *
     * @return true if the message was handled
     */
    public boolean handleMessage(
            CefBrowser browser, CefFrame frame, CefProcessId sourceProcess, CefProcessMessage message) {
        if (message == null) return false;
        String name = message.getName().orElse(null);

        if (MSG_CALLBACK.equals(name)) {
            CefListValue args = message.getArgumentList().orElse(null);
            if (args == null) {
                log.warn("cef4j:cb message with no argument list");
                return true;
            }
            int callbackId = args.getInt(0);
            int numArgs = args.getInt(1);
            int[] argHandles = new int[numArgs];
            for (int i = 0; i < numArgs; i++) {
                argHandles[i] = args.getInt(2 + i);
            }
            CallbackHandler handler = callbacks.get(callbackId);
            if (handler != null) {
                handler.onCallback(argHandles);
            } else {
                log.warn("Received cef4j:cb for unknown callbackId {}", callbackId);
            }
            return true;
        }

        if (!MSG_RESULT.equals(name)) return false;

        CefListValue args = message.getArgumentList().orElse(null);
        if (args == null) {
            log.warn("cef4j:result message with no argument list");
            return true;
        }

        int reqId = args.getInt(0);
        boolean ok = args.getBool(1);
        int type = args.getInt(2);

        // Payload is at index 3 - could be string or int depending on type
        String stringPayload = null;
        int intPayload = 0;
        if (type == TYPE_JSON || type == TYPE_ERROR) {
            CefValue val = args.getValue(3).orElse(null);
            stringPayload = val != null ? val.getString().orElse(null) : null;
        } else if (type == TYPE_HANDLE) {
            intPayload = args.getInt(3);
        }

        Result result;
        if (!ok) {
            result = Result.error(stringPayload != null ? stringPayload : "Unknown error");
        } else {
            switch (type) {
                case TYPE_JSON:
                    result = Result.json(stringPayload);
                    break;
                case TYPE_HANDLE:
                    result = Result.handle(intPayload);
                    break;
                case TYPE_VOID:
                    result = Result.voidResult();
                    break;
                case TYPE_ERROR:
                    result = Result.error(stringPayload != null ? stringPayload : "Unknown error");
                    break;
                default:
                    result = Result.error("Unknown result type: " + type);
                    break;
            }
        }

        CompletableFuture<Result> future = pending.remove(reqId);
        if (future != null) {
            future.complete(result);
        } else {
            log.debug("No pending future for reqId={} (stale or timed out)", reqId);
        }
        return true;
    }

    /** Cancel all pending futures and clear all callbacks. Call this when the browser is closing. */
    public void dispose() {
        CefScriptException ex = new CefScriptException("CefScriptEngine disposed");
        pending.forEach((id, future) -> future.completeExceptionally(ex));
        pending.clear();
        callbacks.clear();
    }

    private CompletableFuture<Result> sendEval(CefFrame frame, String expression, int mode) {
        int reqId = nextId.getAndIncrement();
        CompletableFuture<Result> future = new CompletableFuture<>();
        pending.put(reqId, future);

        CefProcessMessage msg = CefProcessMessage.create(MSG_EVAL).orElse(null);
        if (msg == null) {
            pending.remove(reqId);
            future.completeExceptionally(new IllegalStateException("Failed to create CefProcessMessage"));
            return future;
        }
        CefListValue args = msg.getArgumentList().orElse(null);
        if (args == null) {
            pending.remove(reqId);
            future.completeExceptionally(new IllegalStateException("Failed to get argument list"));
            return future;
        }
        args.setSize(3);
        args.setInt(0, reqId);
        args.setString(1, expression);
        args.setInt(2, mode);
        frame.sendProcessMessage(CefProcessId.of(CefProcessId.Kind.RENDERER), msg);
        return future;
    }

    @SuppressWarnings("unchecked")
    private static <T> CompletableFuture<T> failedFuture(Throwable ex) {
        CompletableFuture<T> f = new CompletableFuture<>();
        f.completeExceptionally(ex);
        return f;
    }
}
