package net.kurobako.cef4j;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.gen.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * Integration tests for {@link CefScriptEngine} - the IPC-based JavaScript eval relay.
 *
 * <p>Tests exercise the full round-trip: Java -> CefProcessMessage IPC -> renderer subprocess (V8 eval) -> IPC -> Java
 * CompletableFuture completion.
 *
 * <p>Both JSON mode (serialized results) and handle mode (opaque V8 references) are covered.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CefScriptEngineTest extends CefTestBase {

    private static CefScriptEngine evaluator;
    private static CefBrowser browser;

    @BeforeAll
    static void initCef() throws Exception {
        CefTestBase.initCef();

        evaluator = new CefScriptEngine(
                () -> browser != null ? browser.getMainFrame().orElse(null) : null);

        CountDownLatch createdLatch = new CountDownLatch(1);
        CountDownLatch loadEndLatch = new CountDownLatch(1);

        CefClient client = new CefClient() {
            @Override
            public Optional<CefLifeSpanHandler> getLifeSpanHandler() {
                return Optional.of(new CefLifeSpanHandler() {
                    @Override
                    public void onAfterCreated(@Nonnull CefBrowser b) {
                        createdLatch.countDown();
                    }
                });
            }

            @Override
            public Optional<CefLoadHandler> getLoadHandler() {
                return Optional.of(new CefLoadHandler() {
                    @Override
                    public void onLoadEnd(@Nonnull CefBrowser b, @Nonnull CefFrame frame, int httpStatusCode) {
                        loadEndLatch.countDown();
                    }
                });
            }

            @Override
            public Optional<CefRenderHandler> getRenderHandler() {
                return Optional.of(new CefInteropTest.MinimalRenderHandler(800, 600));
            }

            @Override
            public boolean onProcessMessageReceived(
                    @Nullable CefBrowser b,
                    @Nullable CefFrame frame,
                    @Nonnull CefProcessId sourceProcess,
                    @Nullable CefProcessMessage message) {
                return evaluator.handleMessage(b, frame, sourceProcess, message);
            }
        };

        browser = createWindowlessBrowser(client, "about:blank");

        assertThat(pumpUntil(createdLatch, 10_000)).as("browser created").isTrue();
        assertThat(pumpUntil(loadEndLatch, 10_000)).as("page loaded").isTrue();
    }

    @AfterAll
    static void cleanup() {
        if (evaluator != null) evaluator.dispose();
        if (browser != null) {
            browser.getHost().ifPresent(host -> host.closeBrowser(true));
        }
    }

    // -----------------------------------------------------------------------
    // JSON mode: evaluate()
    // -----------------------------------------------------------------------

    @Test
    @Order(1)
    void eval_simpleArithmetic() throws Exception {
        CompletableFuture<String> future = evaluator.evaluate("1 + 2");
        String result = pumpAndGet(future, 5_000);
        assertThat(result).isEqualTo("3");
    }

    @Test
    @Order(2)
    void eval_stringResult() throws Exception {
        String result = pumpAndGet(evaluator.evaluate("'hello ' + 'world'"), 5_000);
        assertThat(result).isEqualTo("\"hello world\"");
    }

    @Test
    @Order(3)
    void eval_booleanResult() throws Exception {
        assertThat(pumpAndGet(evaluator.evaluate("true"), 5_000)).isEqualTo("true");
        assertThat(pumpAndGet(evaluator.evaluate("false"), 5_000)).isEqualTo("false");
    }

    @Test
    @Order(4)
    void eval_nullResult() throws Exception {
        String result = pumpAndGet(evaluator.evaluate("null"), 5_000);
        assertThat(result).isEqualTo("null");
    }

    @Test
    @Order(5)
    void eval_undefinedResult() throws Exception {
        String result = pumpAndGet(evaluator.evaluate("undefined"), 5_000);
        assertThat(result).isEqualTo("null");
    }

    @Test
    @Order(6)
    void eval_objectResult() throws Exception {
        String result = pumpAndGet(evaluator.evaluate("({a: 1, b: 'two'})"), 5_000);
        assertThat(result).isEqualTo("{\"a\":1,\"b\":\"two\"}");
    }

    @Test
    @Order(7)
    void eval_arrayResult() throws Exception {
        String result = pumpAndGet(evaluator.evaluate("[1, 2, 3]"), 5_000);
        assertThat(result).isEqualTo("[1,2,3]");
    }

    @Test
    @Order(8)
    void eval_nestedObjectResult() throws Exception {
        String result = pumpAndGet(evaluator.evaluate("({x: {y: {z: 42}}})"), 5_000);
        assertThat(result).isEqualTo("{\"x\":{\"y\":{\"z\":42}}}");
    }

    @Test
    @Order(9)
    void eval_floatResult() throws Exception {
        String result = pumpAndGet(evaluator.evaluate("3.14"), 5_000);
        assertThat(result).isEqualTo("3.14");
    }

    @Test
    @Order(10)
    void eval_largeNumber() throws Exception {
        String result = pumpAndGet(evaluator.evaluate("Number.MAX_SAFE_INTEGER"), 5_000);
        assertThat(result).isEqualTo("9007199254740991");
    }

    @Test
    @Order(11)
    void eval_emptyObject() throws Exception {
        assertThat(pumpAndGet(evaluator.evaluate("({})"), 5_000)).isEqualTo("{}");
    }

    @Test
    @Order(12)
    void eval_emptyArray() throws Exception {
        assertThat(pumpAndGet(evaluator.evaluate("[]"), 5_000)).isEqualTo("[]");
    }

    @Test
    @Order(13)
    void eval_specialCharactersInString() throws Exception {
        String result = pumpAndGet(evaluator.evaluate("'line1\\nline2\\ttab'"), 5_000);
        assertThat(result).isEqualTo("\"line1\\nline2\\ttab\"");
    }

    @Test
    @Order(14)
    void eval_unicodeString() throws Exception {
        String result = pumpAndGet(evaluator.evaluate("'\\u00e9\\u00e8\\u00ea'"), 5_000);
        // JSON should contain the actual unicode chars
        assertThat(result).contains("é");
    }

    // -----------------------------------------------------------------------
    // Error handling
    // -----------------------------------------------------------------------

    @Test
    @Order(20)
    void eval_syntaxError_completesExceptionally() throws Exception {
        CompletableFuture<String> future = evaluator.evaluate("function(");
        assertPumpedExceptionally(future, 5_000, "SyntaxError");
    }

    @Test
    @Order(21)
    void eval_thrownError_completesExceptionally() throws Exception {
        CompletableFuture<String> future = evaluator.evaluate("throw new Error('boom')");
        assertPumpedExceptionally(future, 5_000, "boom");
    }

    @Test
    @Order(22)
    void eval_thrownString_completesExceptionally() throws Exception {
        CompletableFuture<String> future = evaluator.evaluate("throw 'plain string error'");
        assertPumpedExceptionally(future, 5_000, "plain string error");
    }

    @Test
    @Order(23)
    void eval_referenceError_completesExceptionally() throws Exception {
        CompletableFuture<String> future = evaluator.evaluate("nonExistentVariable");
        assertPumpedExceptionally(future, 5_000, "not defined");
    }

    @Test
    @Order(24)
    void eval_typeError_completesExceptionally() throws Exception {
        CompletableFuture<String> future = evaluator.evaluate("null.property");
        assertPumpedExceptionally(future, 5_000, "Cannot read");
    }

    // -----------------------------------------------------------------------
    // Handle mode: evaluateHandle()
    // -----------------------------------------------------------------------

    @Test
    @Order(30)
    void handle_evalAndGetProperty() throws Exception {
        // Create an object, get a handle to it
        int handle = pumpAndGet(evaluator.evaluateHandle("({name: 'test', value: 42})"), 5_000);
        assertThat(handle).isGreaterThan(0);

        // Get properties via handle
        CefScriptEngine.Result nameResult = pumpAndGet(evaluator.getProperty(handle, "name", false), 5_000);
        assertThat(nameResult.isJson()).isTrue();
        assertThat(nameResult.json()).isEqualTo("\"test\"");

        CefScriptEngine.Result valueResult = pumpAndGet(evaluator.getProperty(handle, "value", false), 5_000);
        assertThat(valueResult.isJson()).isTrue();
        assertThat(valueResult.json()).isEqualTo("42");

        evaluator.release(handle);
    }

    @Test
    @Order(31)
    void handle_setProperty() throws Exception {
        int handle = pumpAndGet(evaluator.evaluateHandle("({x: 0})"), 5_000);

        pumpAndGet(evaluator.setProperty(handle, "x", "99"), 5_000);

        CefScriptEngine.Result result = pumpAndGet(evaluator.getProperty(handle, "x", false), 5_000);
        assertThat(result.json()).isEqualTo("99");

        evaluator.release(handle);
    }

    @Test
    @Order(32)
    void handle_setPropertyString() throws Exception {
        int handle = pumpAndGet(evaluator.evaluateHandle("({})"), 5_000);

        pumpAndGet(evaluator.setProperty(handle, "msg", "\"hello\""), 5_000);

        CefScriptEngine.Result result = pumpAndGet(evaluator.getProperty(handle, "msg", false), 5_000);
        assertThat(result.json()).isEqualTo("\"hello\"");

        evaluator.release(handle);
    }

    @Test
    @Order(33)
    void handle_setPropertyObject() throws Exception {
        int handle = pumpAndGet(evaluator.evaluateHandle("({})"), 5_000);

        pumpAndGet(evaluator.setProperty(handle, "nested", "{\"a\": 1}"), 5_000);

        CefScriptEngine.Result result = pumpAndGet(evaluator.getProperty(handle, "nested", false), 5_000);
        assertThat(result.json()).isEqualTo("{\"a\":1}");

        evaluator.release(handle);
    }

    @Test
    @Order(34)
    void handle_callMethod() throws Exception {
        int handle = pumpAndGet(evaluator.evaluateHandle("({add: function(a, b) { return a + b; }})"), 5_000);

        CefScriptEngine.Result result = pumpAndGet(evaluator.call(handle, "add", "[3, 4]", false), 5_000);
        assertThat(result.isJson()).isTrue();
        assertThat(result.json()).isEqualTo("7");

        evaluator.release(handle);
    }

    @Test
    @Order(35)
    void handle_callMethodReturningString() throws Exception {
        int handle =
                pumpAndGet(evaluator.evaluateHandle("({greet: function(name) { return 'hello ' + name; }})"), 5_000);

        CefScriptEngine.Result result = pumpAndGet(evaluator.call(handle, "greet", "[\"world\"]", false), 5_000);
        assertThat(result.json()).isEqualTo("\"hello world\"");

        evaluator.release(handle);
    }

    @Test
    @Order(36)
    void handle_callMethodReturningObject() throws Exception {
        int handle = pumpAndGet(evaluator.evaluateHandle("({make: function() { return {x: 1, y: 2}; }})"), 5_000);

        CefScriptEngine.Result result = pumpAndGet(evaluator.call(handle, "make", "[]", false), 5_000);
        assertThat(result.json()).isEqualTo("{\"x\":1,\"y\":2}");

        evaluator.release(handle);
    }

    @Test
    @Order(37)
    void handle_callMethodReturningHandle() throws Exception {
        int handle = pumpAndGet(evaluator.evaluateHandle("({make: function() { return {nested: true}; }})"), 5_000);

        CefScriptEngine.Result result = pumpAndGet(evaluator.call(handle, "make", "[]", true), 5_000);
        assertThat(result.isHandle()).isTrue();
        int nestedHandle = result.handle();

        CefScriptEngine.Result prop = pumpAndGet(evaluator.getProperty(nestedHandle, "nested", false), 5_000);
        assertThat(prop.json()).isEqualTo("true");

        evaluator.release(nestedHandle);
        evaluator.release(handle);
    }

    @Test
    @Order(38)
    void handle_getPropertyAsHandle() throws Exception {
        int handle = pumpAndGet(evaluator.evaluateHandle("({child: {a: 1, b: 2}})"), 5_000);

        CefScriptEngine.Result childResult = pumpAndGet(evaluator.getProperty(handle, "child", true), 5_000);
        assertThat(childResult.isHandle()).isTrue();
        int childHandle = childResult.handle();

        CefScriptEngine.Result a = pumpAndGet(evaluator.getProperty(childHandle, "a", false), 5_000);
        assertThat(a.json()).isEqualTo("1");

        CefScriptEngine.Result b = pumpAndGet(evaluator.getProperty(childHandle, "b", false), 5_000);
        assertThat(b.json()).isEqualTo("2");

        evaluator.release(childHandle);
        evaluator.release(handle);
    }

    @Test
    @Order(39)
    void handle_invalidHandle_returnsError() throws Exception {
        CefScriptEngine.Result result = pumpAndGet(evaluator.getProperty(999999, "anything", false), 5_000);
        assertThat(result.isError()).isTrue();
        assertThat(result.error()).contains("handle not found");
    }

    // -----------------------------------------------------------------------
    // Complex values and edge cases
    // -----------------------------------------------------------------------

    @Test
    @Order(40)
    void eval_arrayOfObjects() throws Exception {
        String result = pumpAndGet(evaluator.evaluate("[{a:1},{a:2},{a:3}]"), 5_000);
        assertThat(result).isEqualTo("[{\"a\":1},{\"a\":2},{\"a\":3}]");
    }

    @Test
    @Order(41)
    void eval_dateToJson() throws Exception {
        // Date.toJSON() produces an ISO string
        String result = pumpAndGet(evaluator.evaluate("new Date('2025-01-15T00:00:00.000Z')"), 5_000);
        assertThat(result).isEqualTo("\"2025-01-15T00:00:00.000Z\"");
    }

    @Test
    @Order(42)
    void eval_regexToJson() throws Exception {
        // RegExp has no toJSON, JSON.stringify returns {}
        String result = pumpAndGet(evaluator.evaluate("/abc/g"), 5_000);
        assertThat(result).isEqualTo("{}");
    }

    @Test
    @Order(43)
    void eval_mapDoesNotSerialize() throws Exception {
        // Map is not JSON-serializable by default
        String result = pumpAndGet(evaluator.evaluate("new Map([['a',1]])"), 5_000);
        assertThat(result).isEqualTo("{}");
    }

    @Test
    @Order(44)
    void eval_multilineExpression() throws Exception {
        String expr = "(function() {\n  var x = 10;\n  var y = 20;\n  return x + y;\n})()";
        String result = pumpAndGet(evaluator.evaluate(expr), 5_000);
        assertThat(result).isEqualTo("30");
    }

    @Test
    @Order(45)
    void eval_iife() throws Exception {
        String result = pumpAndGet(evaluator.evaluate("(function() { return 'iife'; })()"), 5_000);
        assertThat(result).isEqualTo("\"iife\"");
    }

    @Test
    @Order(46)
    void eval_arrowFunction() throws Exception {
        String result = pumpAndGet(evaluator.evaluate("(() => 42)()"), 5_000);
        assertThat(result).isEqualTo("42");
    }

    // -----------------------------------------------------------------------
    // Global state and mutation
    // -----------------------------------------------------------------------

    @Test
    @Order(50)
    void eval_setGlobalVariable_thenRead() throws Exception {
        pumpAndGet(evaluator.evaluate("window.__testVal = 123"), 5_000);
        String result = pumpAndGet(evaluator.evaluate("window.__testVal"), 5_000);
        assertThat(result).isEqualTo("123");
    }

    @Test
    @Order(51)
    void eval_mutateGlobalObject() throws Exception {
        pumpAndGet(evaluator.evaluate("window.__obj = {count: 0}"), 5_000);
        pumpAndGet(evaluator.evaluate("window.__obj.count++"), 5_000);
        pumpAndGet(evaluator.evaluate("window.__obj.count++"), 5_000);
        String result = pumpAndGet(evaluator.evaluate("window.__obj.count"), 5_000);
        assertThat(result).isEqualTo("2");
    }

    @Test
    @Order(52)
    void handle_mutateViaHandle() throws Exception {
        pumpAndGet(evaluator.evaluate("window.__counter = {n: 0}"), 5_000);
        int handle = pumpAndGet(evaluator.evaluateHandle("window.__counter"), 5_000);

        // Increment via setProperty
        pumpAndGet(evaluator.setProperty(handle, "n", "5"), 5_000);

        // Verify via both handle and eval
        CefScriptEngine.Result result = pumpAndGet(evaluator.getProperty(handle, "n", false), 5_000);
        assertThat(result.json()).isEqualTo("5");

        String evalResult = pumpAndGet(evaluator.evaluate("window.__counter.n"), 5_000);
        assertThat(evalResult).isEqualTo("5");

        evaluator.release(handle);
    }

    // -----------------------------------------------------------------------
    // Method calls with various argument types
    // -----------------------------------------------------------------------

    @Test
    @Order(60)
    void handle_callWithNoArgs() throws Exception {
        int handle = pumpAndGet(evaluator.evaluateHandle("({f: function() { return 'no args'; }})"), 5_000);
        CefScriptEngine.Result result = pumpAndGet(evaluator.call(handle, "f", "[]", false), 5_000);
        assertThat(result.json()).isEqualTo("\"no args\"");
        evaluator.release(handle);
    }

    @Test
    @Order(61)
    void handle_callWithMixedArgs() throws Exception {
        int handle = pumpAndGet(evaluator.evaluateHandle("({f: function(n, s, b) { return '' + n + s + b; }})"), 5_000);
        CefScriptEngine.Result result = pumpAndGet(evaluator.call(handle, "f", "[42, \"hello\", true]", false), 5_000);
        assertThat(result.json()).isEqualTo("\"42hellotrue\"");
        evaluator.release(handle);
    }

    @Test
    @Order(62)
    void handle_callWithObjectArg() throws Exception {
        int handle = pumpAndGet(evaluator.evaluateHandle("({f: function(obj) { return obj.x + obj.y; }})"), 5_000);
        CefScriptEngine.Result result =
                pumpAndGet(evaluator.call(handle, "f", "[{\"x\": 10, \"y\": 20}]", false), 5_000);
        assertThat(result.json()).isEqualTo("30");
        evaluator.release(handle);
    }

    @Test
    @Order(63)
    void handle_callWithArrayArg() throws Exception {
        int handle = pumpAndGet(
                evaluator.evaluateHandle("({sum: function(arr) { return arr.reduce((a,b) => a+b, 0); }})"), 5_000);
        CefScriptEngine.Result result = pumpAndGet(evaluator.call(handle, "sum", "[[1,2,3,4,5]]", false), 5_000);
        assertThat(result.json()).isEqualTo("15");
        evaluator.release(handle);
    }

    // -----------------------------------------------------------------------
    // Chained handle operations (simulate real-world usage)
    // -----------------------------------------------------------------------

    @Test
    @Order(70)
    void handle_chainedOperations() throws Exception {
        // Create a builder-like object
        pumpAndGet(
                evaluator.evaluate("window.__Builder = function() {"
                        + "  this.items = [];"
                        + "  this.add = function(v) { this.items.push(v); return this; };"
                        + "  this.result = function() { return this.items; };"
                        + "}"),
                5_000);

        int handle = pumpAndGet(evaluator.evaluateHandle("new window.__Builder()"), 5_000);

        // Chain calls - each returns 'this' so we get a handle back
        CefScriptEngine.Result r1 = pumpAndGet(evaluator.call(handle, "add", "[\"a\"]", true), 5_000);
        assertThat(r1.isHandle()).isTrue();

        // Use handle mode for chained calls (natural usage pattern)
        CefScriptEngine.Result r2 = pumpAndGet(evaluator.call(r1.handle(), "add", "[\"b\"]", true), 5_000);
        assertThat(r2.isHandle()).isTrue();

        CefScriptEngine.Result r3 = pumpAndGet(evaluator.call(handle, "add", "[\"c\"]", true), 5_000);
        assertThat(r3.isHandle()).isTrue();

        // Final result call returns a plain array - use JSON mode
        CefScriptEngine.Result result = pumpAndGet(evaluator.call(handle, "result", "[]", false), 5_000);
        assertThat(result.isJson())
                .as("result() should return JSON, got: " + result)
                .isTrue();
        assertThat(result.json()).isEqualTo("[\"a\",\"b\",\"c\"]");

        evaluator.release(r3.handle());
        evaluator.release(r2.handle());
        evaluator.release(r1.handle());
        evaluator.release(handle);
    }

    @Test
    @Order(71)
    void handle_accessDomDocument() throws Exception {
        // Get a handle to document
        int docHandle = pumpAndGet(evaluator.evaluateHandle("document"), 5_000);
        assertThat(docHandle).isGreaterThan(0);

        // Get document.title (about:blank has empty title)
        CefScriptEngine.Result titleResult = pumpAndGet(evaluator.getProperty(docHandle, "title", false), 5_000);
        assertThat(titleResult.isJson()).isTrue();
        assertThat(titleResult.json()).isEqualTo("\"\"");

        // Call document.createElement
        CefScriptEngine.Result elemResult =
                pumpAndGet(evaluator.call(docHandle, "createElement", "[\"div\"]", true), 5_000);
        assertThat(elemResult.isHandle()).isTrue();
        int divHandle = elemResult.handle();

        // Set innerHTML on the created element
        pumpAndGet(evaluator.setProperty(divHandle, "innerHTML", "\"<span>test</span>\""), 5_000);

        // Read back innerHTML
        CefScriptEngine.Result htmlResult = pumpAndGet(evaluator.getProperty(divHandle, "innerHTML", false), 5_000);
        assertThat(htmlResult.json()).isEqualTo("\"<span>test</span>\"");

        evaluator.release(divHandle);
        evaluator.release(docHandle);
    }

    // -----------------------------------------------------------------------
    // Multiple concurrent evaluations
    // -----------------------------------------------------------------------

    @Test
    @Order(80)
    void eval_multipleConcurrentRequests() throws Exception {
        CompletableFuture<String> f1 = evaluator.evaluate("1 + 1");
        CompletableFuture<String> f2 = evaluator.evaluate("2 + 2");
        CompletableFuture<String> f3 = evaluator.evaluate("3 + 3");

        // Pump until all complete
        pumpUntilAllDone(5_000, f1, f2, f3);

        assertThat(f1.get()).isEqualTo("2");
        assertThat(f2.get()).isEqualTo("4");
        assertThat(f3.get()).isEqualTo("6");
    }

    @Test
    @Order(81)
    void eval_interleavedJsonAndHandleRequests() throws Exception {
        CompletableFuture<String> jsonFuture = evaluator.evaluate("'json result'");
        CompletableFuture<Integer> handleFuture = evaluator.evaluateHandle("({type: 'handle'})");
        CompletableFuture<String> jsonFuture2 = evaluator.evaluate("100 * 2");

        pumpUntilAllDone(5_000, jsonFuture, handleFuture, jsonFuture2);

        assertThat(jsonFuture.get()).isEqualTo("\"json result\"");
        assertThat(handleFuture.get()).isGreaterThan(0);
        assertThat(jsonFuture2.get()).isEqualTo("200");

        evaluator.release(handleFuture.get());
    }

    // -----------------------------------------------------------------------
    // Callbacks: createCallback()
    // -----------------------------------------------------------------------

    @Test
    @Order(90)
    void callback_invokedWithHandleArgs() throws Exception {
        CompletableFuture<int[]> received = new CompletableFuture<>();
        int cbHandle = pumpAndGet(evaluator.createCallback(received::complete), 5_000);
        assertThat(cbHandle).isGreaterThan(0);

        // Invoke the callback with two args via the invoke API
        pumpAndGet(evaluator.invoke(cbHandle, "[42, \"hello\"]", false), 5_000);

        pumpUntilDone(received, 5_000);
        int[] argHandles = received.get();
        assertThat(argHandles).hasSize(2);
        assertThat(argHandles[0]).isGreaterThan(0);
        assertThat(argHandles[1]).isGreaterThan(0);

        for (int h : argHandles) evaluator.release(h);
        evaluator.release(cbHandle);
    }

    @Test
    @Order(91)
    void callback_invokedMultipleTimes() throws Exception {
        java.util.List<int[]> invocations = java.util.Collections.synchronizedList(new java.util.ArrayList<>());
        int cbHandle = pumpAndGet(evaluator.createCallback(invocations::add), 5_000);

        pumpAndGet(evaluator.invoke(cbHandle, "[\"a\"]", false), 5_000);
        pumpAndGet(evaluator.invoke(cbHandle, "[\"b\"]", false), 5_000);
        pumpAndGet(evaluator.invoke(cbHandle, "[\"c\"]", false), 5_000);

        long deadline = System.currentTimeMillis() + 5_000;
        while (invocations.size() < 3 && System.currentTimeMillis() < deadline) {
            Cef.INSTANCE.doMessageLoopWork();
            Thread.sleep(16);
        }
        assertThat(invocations).hasSize(3);
        assertThat(invocations.get(0)).hasSize(1);
        assertThat(invocations.get(1)).hasSize(1);
        assertThat(invocations.get(2)).hasSize(1);

        for (int[] handles : invocations) {
            for (int h : handles) evaluator.release(h);
        }
        evaluator.release(cbHandle);
    }

    @Test
    @Order(92)
    void callback_noArgs() throws Exception {
        CompletableFuture<int[]> received = new CompletableFuture<>();
        int cbHandle = pumpAndGet(evaluator.createCallback(received::complete), 5_000);

        pumpAndGet(evaluator.invoke(cbHandle, "[]", false), 5_000);

        pumpUntilDone(received, 5_000);
        assertThat(received.get()).isEmpty();

        evaluator.release(cbHandle);
    }

    @Test
    @Order(93)
    void callback_objectArgPreservesProperties() throws Exception {
        CompletableFuture<int[]> received = new CompletableFuture<>();
        int cbHandle = pumpAndGet(evaluator.createCallback(received::complete), 5_000);

        pumpAndGet(evaluator.invoke(cbHandle, "[{\"x\":1, \"y\":2}]", false), 5_000);

        pumpUntilDone(received, 5_000);
        int[] argHandles = received.get();
        assertThat(argHandles).hasSize(1);

        CefScriptEngine.Result xResult = pumpAndGet(evaluator.getProperty(argHandles[0], "x", false), 5_000);
        assertThat(xResult.isJson()).isTrue();
        assertThat(xResult.json()).isEqualTo("1");

        CefScriptEngine.Result yResult = pumpAndGet(evaluator.getProperty(argHandles[0], "y", false), 5_000);
        assertThat(yResult.json()).isEqualTo("2");

        evaluator.release(argHandles[0]);
        evaluator.release(cbHandle);
    }

    @Test
    @Order(94)
    void callback_nullArgIsMinusOne() throws Exception {
        CompletableFuture<int[]> received = new CompletableFuture<>();
        int cbHandle = pumpAndGet(evaluator.createCallback(received::complete), 5_000);

        pumpAndGet(evaluator.invoke(cbHandle, "[null, undefined]", false), 5_000);

        pumpUntilDone(received, 5_000);
        int[] argHandles = received.get();
        assertThat(argHandles).hasSize(2);
        assertThat(argHandles[0]).isEqualTo(-1);
        assertThat(argHandles[1]).isEqualTo(-1);

        evaluator.release(cbHandle);
    }

    // -----------------------------------------------------------------------
    // dispose() behavior
    // -----------------------------------------------------------------------

    @Test
    @Order(99)
    void terminate_cancelsPendingFutures() {
        CefScriptEngine disposable =
                new CefScriptEngine(() -> browser.getMainFrame().orElse(null));

        // We don't wire this engine to receive messages, so futures stay pending
        CompletableFuture<String> pending1 = disposable.evaluate("1");
        CompletableFuture<String> pending2 = disposable.evaluate("2");

        assertThat(pending1).isNotDone();
        assertThat(pending2).isNotDone();

        disposable.dispose();

        assertThat(pending1).isCompletedExceptionally();
        assertThat(pending2).isCompletedExceptionally();
    }

    // -----------------------------------------------------------------------
    // Helpers
    // -----------------------------------------------------------------------

    private static <T> T pumpAndGet(CompletableFuture<T> future, long timeoutMs) throws Exception {
        long deadline = System.currentTimeMillis() + timeoutMs;
        while (!future.isDone() && System.currentTimeMillis() < deadline) {
            Cef.INSTANCE.doMessageLoopWork();
            Thread.sleep(16);
        }
        assertThat(future)
                .as("future should complete within " + timeoutMs + "ms")
                .isDone();
        return future.get(0, TimeUnit.MILLISECONDS);
    }

    private static void assertPumpedExceptionally(CompletableFuture<?> future, long timeoutMs, String messageContains)
            throws Exception {
        long deadline = System.currentTimeMillis() + timeoutMs;
        while (!future.isDone() && System.currentTimeMillis() < deadline) {
            Cef.INSTANCE.doMessageLoopWork();
            Thread.sleep(16);
        }
        assertThat(future)
                .as("future should complete within " + timeoutMs + "ms")
                .isDone();
        assertThat(future).isCompletedExceptionally();
        try {
            future.get();
        } catch (java.util.concurrent.ExecutionException e) {
            assertThat(e.getCause()).isInstanceOf(CefScriptException.class);
            assertThat(e.getCause().getMessage()).contains(messageContains);
        }
    }

    private static void pumpUntilAllDone(long timeoutMs, CompletableFuture<?>... futures) throws InterruptedException {
        long deadline = System.currentTimeMillis() + timeoutMs;
        while (System.currentTimeMillis() < deadline) {
            Cef.INSTANCE.doMessageLoopWork();
            boolean allDone = true;
            for (CompletableFuture<?> f : futures) {
                if (!f.isDone()) {
                    allDone = false;
                    break;
                }
            }
            if (allDone) return;
            Thread.sleep(16);
        }
        for (CompletableFuture<?> f : futures) {
            assertThat(f).as("all futures should complete").isDone();
        }
    }

    private static void pumpUntilDone(CompletableFuture<?> future, long timeoutMs) throws InterruptedException {
        long deadline = System.currentTimeMillis() + timeoutMs;
        while (!future.isDone() && System.currentTimeMillis() < deadline) {
            Cef.INSTANCE.doMessageLoopWork();
            Thread.sleep(16);
        }
        assertThat(future)
                .as("future should complete within " + timeoutMs + "ms")
                .isDone();
    }
}
