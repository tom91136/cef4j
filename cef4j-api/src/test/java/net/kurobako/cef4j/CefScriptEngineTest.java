package net.kurobako.cef4j;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Proxy;
import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import net.kurobako.cef4j.gen.*;
import net.kurobako.cef4j.test.TestDeadline;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CefScriptEngineTest extends CefTestBase {

    private static CefScriptEngine evaluator;
    private static CefBrowser browser;
    private static final CountDownLatch browserClosed = new CountDownLatch(1);

    @BeforeAll
    static void initCef() throws Exception {
        CefTestBase.initCef(List.of());

        evaluator = new CefScriptEngine(
                () -> browser != null ? browser.getMainFrame().orElse(null) : null);

        CountDownLatch createdLatch = new CountDownLatch(1);
        CountDownLatch loadEndLatch = new CountDownLatch(1);

        CefClient client = new CefClient() {
            @Override
            public Optional<CefLifeSpanHandler> getLifeSpanHandler() {
                return Optional.of(new CefLifeSpanHandler() {
                    @Override
                    public void onAfterCreated(@Nullable CefBrowser b) {
                        createdLatch.countDown();
                    }

                    @Override
                    public void onBeforeClose(@Nullable CefBrowser b) {
                        browserClosed.countDown();
                    }
                });
            }

            @Override
            public Optional<CefLoadHandler> getLoadHandler() {
                return Optional.of(new CefLoadHandler() {
                    @Override
                    public void onLoadEnd(@Nullable CefBrowser b, @Nullable CefFrame frame, int httpStatusCode) {
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
                    @Nullable CefProcessId sourceProcess,
                    @Nullable CefProcessMessage message) {
                if (b == null || frame == null || sourceProcess == null || message == null) return false;
                return evaluator.handleMessage(b, frame, sourceProcess, message);
            }
        };

        browser = createWindowlessBrowser(client, "about:blank");

        assertThat(pumpUntil(createdLatch, 10_000)).as("browser created").isTrue();
        assertThat(pumpUntil(loadEndLatch, 10_000)).as("page loaded").isTrue();
    }

    @AfterAll
    static void cleanup() throws Exception {
        if (evaluator != null) evaluator.dispose();
        if (browser != null) {
            browser.getHost().ifPresent(host -> host.closeBrowser(true));
        }
        assertThat(pumpUntil(browserClosed, 10_000)).as("browser closed").isTrue();
        if (!OS.isMacOS() && Cef.INSTANCE.state() == Cef.State.INITIALISED) Cef.INSTANCE.terminate();
    }

    @Test
    @Order(1)
    void evalSimpleArithmetic() throws Exception {
        CompletableFuture<String> future = evaluator.evaluate("1 + 2");
        String result = pumpAndGet(future, 15_000);
        assertThat(result).isEqualTo("3");
    }

    @Test
    @Order(2)
    void evalStringResult() throws Exception {
        String result = pumpAndGet(evaluator.evaluate("'hello ' + 'world'"), 5_000);
        assertThat(result).isEqualTo("\"hello world\"");
    }

    @Test
    @Order(3)
    void evalBooleanResult() throws Exception {
        assertThat(pumpAndGet(evaluator.evaluate("true"), 5_000)).isEqualTo("true");
        assertThat(pumpAndGet(evaluator.evaluate("false"), 5_000)).isEqualTo("false");
    }

    @Test
    @Order(4)
    void evalNullResult() throws Exception {
        String result = pumpAndGet(evaluator.evaluate("null"), 5_000);
        assertThat(result).isEqualTo("null");
    }

    @Test
    @Order(5)
    void evalUndefinedResult() throws Exception {
        String result = pumpAndGet(evaluator.evaluate("undefined"), 5_000);
        assertThat(result).isEqualTo("null");
    }

    @Test
    @Order(6)
    void evalObjectResult() throws Exception {
        String result = pumpAndGet(evaluator.evaluate("({a: 1, b: 'two'})"), 5_000);
        assertThat(result).isEqualTo("{\"a\":1,\"b\":\"two\"}");
    }

    @Test
    @Order(7)
    void evalArrayResult() throws Exception {
        String result = pumpAndGet(evaluator.evaluate("[1, 2, 3]"), 5_000);
        assertThat(result).isEqualTo("[1,2,3]");
    }

    @Test
    @Order(8)
    void evalNestedObjectResult() throws Exception {
        String result = pumpAndGet(evaluator.evaluate("({x: {y: {z: 42}}})"), 5_000);
        assertThat(result).isEqualTo("{\"x\":{\"y\":{\"z\":42}}}");
    }

    @Test
    @Order(9)
    void evalFloatResult() throws Exception {
        String result = pumpAndGet(evaluator.evaluate("3.14"), 5_000);
        assertThat(result).isEqualTo("3.14");
    }

    @Test
    @Order(10)
    void evalLargeNumber() throws Exception {
        String result = pumpAndGet(evaluator.evaluate("Number.MAX_SAFE_INTEGER"), 5_000);
        assertThat(result).isEqualTo("9007199254740991");
    }

    @Test
    @Order(11)
    void evalEmptyObject() throws Exception {
        assertThat(pumpAndGet(evaluator.evaluate("({})"), 5_000)).isEqualTo("{}");
    }

    @Test
    @Order(12)
    void evalEmptyArray() throws Exception {
        assertThat(pumpAndGet(evaluator.evaluate("[]"), 5_000)).isEqualTo("[]");
    }

    @Test
    @Order(13)
    void evalSpecialCharactersInString() throws Exception {
        String result = pumpAndGet(evaluator.evaluate("'line1\\nline2\\ttab'"), 5_000);
        assertThat(result).isEqualTo("\"line1\\nline2\\ttab\"");
    }

    @Test
    @Order(14)
    void evalUnicodeString() throws Exception {
        String result = pumpAndGet(evaluator.evaluate("'\\u00e9\\u00e8\\u00ea'"), 5_000);
        assertThat(result).contains("é");
    }

    @Test
    @Order(20)
    void evalSyntaxErrorCompletesExceptionally() throws Exception {
        CompletableFuture<String> future = evaluator.evaluate("function(");
        assertPumpedExceptionally(future, 5_000, "SyntaxError");
    }

    @Test
    @Order(21)
    void evalThrownErrorCompletesExceptionally() throws Exception {
        CompletableFuture<String> future = evaluator.evaluate("throw new Error('boom')");
        assertPumpedExceptionally(future, 5_000, "boom");
    }

    @Test
    @Order(22)
    void evalThrownStringCompletesExceptionally() throws Exception {
        CompletableFuture<String> future = evaluator.evaluate("throw 'plain string error'");
        assertPumpedExceptionally(future, 5_000, "plain string error");
    }

    @Test
    @Order(23)
    void evalReferenceErrorCompletesExceptionally() throws Exception {
        CompletableFuture<String> future = evaluator.evaluate("nonExistentVariable");
        assertPumpedExceptionally(future, 5_000, "not defined");
    }

    @Test
    @Order(24)
    void evalTypeErrorCompletesExceptionally() throws Exception {
        CompletableFuture<String> future = evaluator.evaluate("null.property");
        assertPumpedExceptionally(future, 5_000, "Cannot read");
    }

    @Test
    @Order(30)
    void handleEvalAndGetProperty() throws Exception {
        int handle = pumpAndGet(evaluator.evaluateHandle("({name: 'test', value: 42})"), 5_000);
        assertThat(handle).isGreaterThan(0);

        CefScriptEngine.Result nameResult = pumpAndGet(evaluator.getProperty(handle, "name", false), 5_000);
        assertThat(nameResult.isJson()).isTrue();
        assertThat(nameResult.json()).hasValue("\"test\"");

        CefScriptEngine.Result valueResult = pumpAndGet(evaluator.getProperty(handle, "value", false), 5_000);
        assertThat(valueResult.isJson()).isTrue();
        assertThat(valueResult.json()).hasValue("42");

        evaluator.release(handle);
    }

    @Test
    @Order(31)
    void handleSetProperty() throws Exception {
        int handle = pumpAndGet(evaluator.evaluateHandle("({x: 0})"), 5_000);

        pumpAndGet(evaluator.setProperty(handle, "x", "99"), 5_000);

        CefScriptEngine.Result result = pumpAndGet(evaluator.getProperty(handle, "x", false), 5_000);
        assertThat(result.json()).hasValue("99");

        evaluator.release(handle);
    }

    @Test
    @Order(32)
    void handleSetPropertyString() throws Exception {
        int handle = pumpAndGet(evaluator.evaluateHandle("({})"), 5_000);

        pumpAndGet(evaluator.setProperty(handle, "msg", "\"hello\""), 5_000);

        CefScriptEngine.Result result = pumpAndGet(evaluator.getProperty(handle, "msg", false), 5_000);
        assertThat(result.json()).hasValue("\"hello\"");

        evaluator.release(handle);
    }

    @Test
    @Order(33)
    void handleSetPropertyObject() throws Exception {
        int handle = pumpAndGet(evaluator.evaluateHandle("({})"), 5_000);

        pumpAndGet(evaluator.setProperty(handle, "nested", "{\"a\": 1}"), 5_000);

        CefScriptEngine.Result result = pumpAndGet(evaluator.getProperty(handle, "nested", false), 5_000);
        assertThat(result.json()).hasValue("{\"a\":1}");

        evaluator.release(handle);
    }

    @Test
    @Order(34)
    void handleCallMethod() throws Exception {
        int handle = pumpAndGet(evaluator.evaluateHandle("({add: function(a, b) { return a + b; }})"), 5_000);

        CefScriptEngine.Result result = pumpAndGet(evaluator.call(handle, "add", "[3, 4]", false), 5_000);
        assertThat(result.isJson()).isTrue();
        assertThat(result.json()).hasValue("7");

        evaluator.release(handle);
    }

    @Test
    @Order(35)
    void handleCallMethodReturningString() throws Exception {
        int handle =
                pumpAndGet(evaluator.evaluateHandle("({greet: function(name) { return 'hello ' + name; }})"), 5_000);

        CefScriptEngine.Result result = pumpAndGet(evaluator.call(handle, "greet", "[\"world\"]", false), 5_000);
        assertThat(result.json()).hasValue("\"hello world\"");

        evaluator.release(handle);
    }

    @Test
    @Order(36)
    void handleCallMethodReturningObject() throws Exception {
        int handle = pumpAndGet(evaluator.evaluateHandle("({make: function() { return {x: 1, y: 2}; }})"), 5_000);

        CefScriptEngine.Result result = pumpAndGet(evaluator.call(handle, "make", "[]", false), 5_000);
        assertThat(result.json()).hasValue("{\"x\":1,\"y\":2}");

        evaluator.release(handle);
    }

    @Test
    @Order(37)
    void handleCallMethodReturningHandle() throws Exception {
        int handle = pumpAndGet(evaluator.evaluateHandle("({make: function() { return {nested: true}; }})"), 5_000);

        CefScriptEngine.Result result = pumpAndGet(evaluator.call(handle, "make", "[]", true), 5_000);
        assertThat(result.isHandle()).isTrue();
        int nestedHandle = result.handle();

        CefScriptEngine.Result prop = pumpAndGet(evaluator.getProperty(nestedHandle, "nested", false), 5_000);
        assertThat(prop.json()).hasValue("true");

        evaluator.release(nestedHandle);
        evaluator.release(handle);
    }

    @Test
    @Order(38)
    void handleGetPropertyAsHandle() throws Exception {
        int handle = pumpAndGet(evaluator.evaluateHandle("({child: {a: 1, b: 2}})"), 5_000);

        CefScriptEngine.Result childResult = pumpAndGet(evaluator.getProperty(handle, "child", true), 5_000);
        assertThat(childResult.isHandle()).isTrue();
        int childHandle = childResult.handle();

        CefScriptEngine.Result a = pumpAndGet(evaluator.getProperty(childHandle, "a", false), 5_000);
        assertThat(a.json()).hasValue("1");

        CefScriptEngine.Result b = pumpAndGet(evaluator.getProperty(childHandle, "b", false), 5_000);
        assertThat(b.json()).hasValue("2");

        evaluator.release(childHandle);
        evaluator.release(handle);
    }

    @Test
    @Order(39)
    void handleInvalidHandleReturnsError() throws Exception {
        CefScriptEngine.Result result = pumpAndGet(evaluator.getProperty(999999, "anything", false), 5_000);
        assertThat(result.isError()).isTrue();
        assertThat(result.error()).contains("handle not found");
    }

    @Test
    @Order(40)
    void evalArrayOfObjects() throws Exception {
        String result = pumpAndGet(evaluator.evaluate("[{a:1},{a:2},{a:3}]"), 5_000);
        assertThat(result).isEqualTo("[{\"a\":1},{\"a\":2},{\"a\":3}]");
    }

    @Test
    @Order(41)
    void evalDateToJson() throws Exception {
        String result = pumpAndGet(evaluator.evaluate("new Date('2025-01-15T00:00:00.000Z')"), 5_000);
        assertThat(result).isEqualTo("\"2025-01-15T00:00:00.000Z\"");
    }

    @Test
    @Order(42)
    void evalRegexToJson() throws Exception {
        String result = pumpAndGet(evaluator.evaluate("/abc/g"), 5_000);
        assertThat(result).isEqualTo("{}");
    }

    @Test
    @Order(43)
    void evalMapDoesNotSerialize() throws Exception {
        String result = pumpAndGet(evaluator.evaluate("new Map([['a',1]])"), 5_000);
        assertThat(result).isEqualTo("{}");
    }

    @Test
    @Order(44)
    void evalMultilineExpression() throws Exception {
        String expr = "(function() {\n  var x = 10;\n  var y = 20;\n  return x + y;\n})()";
        String result = pumpAndGet(evaluator.evaluate(expr), 5_000);
        assertThat(result).isEqualTo("30");
    }

    @Test
    @Order(45)
    void evalIife() throws Exception {
        String result = pumpAndGet(evaluator.evaluate("(function() { return 'iife'; })()"), 5_000);
        assertThat(result).isEqualTo("\"iife\"");
    }

    @Test
    @Order(46)
    void evalArrowFunction() throws Exception {
        String result = pumpAndGet(evaluator.evaluate("(() => 42)()"), 5_000);
        assertThat(result).isEqualTo("42");
    }

    @Test
    @Order(50)
    void evalSetGlobalVariableThenRead() throws Exception {
        pumpAndGet(evaluator.evaluate("window.__testVal = 123"), 5_000);
        String result = pumpAndGet(evaluator.evaluate("window.__testVal"), 5_000);
        assertThat(result).isEqualTo("123");
    }

    @Test
    @Order(51)
    void evalMutateGlobalObject() throws Exception {
        pumpAndGet(evaluator.evaluate("window.__obj = {count: 0}"), 5_000);
        pumpAndGet(evaluator.evaluate("window.__obj.count++"), 5_000);
        pumpAndGet(evaluator.evaluate("window.__obj.count++"), 5_000);
        String result = pumpAndGet(evaluator.evaluate("window.__obj.count"), 5_000);
        assertThat(result).isEqualTo("2");
    }

    @Test
    @Order(52)
    void handleMutateViaHandle() throws Exception {
        pumpAndGet(evaluator.evaluate("window.__counter = {n: 0}"), 5_000);
        int handle = pumpAndGet(evaluator.evaluateHandle("window.__counter"), 5_000);

        pumpAndGet(evaluator.setProperty(handle, "n", "5"), 5_000);

        CefScriptEngine.Result result = pumpAndGet(evaluator.getProperty(handle, "n", false), 5_000);
        assertThat(result.json()).hasValue("5");

        String evalResult = pumpAndGet(evaluator.evaluate("window.__counter.n"), 5_000);
        assertThat(evalResult).isEqualTo("5");

        evaluator.release(handle);
    }

    @Test
    @Order(60)
    void handleCallWithNoArgs() throws Exception {
        int handle = pumpAndGet(evaluator.evaluateHandle("({f: function() { return 'no args'; }})"), 5_000);
        CefScriptEngine.Result result = pumpAndGet(evaluator.call(handle, "f", "[]", false), 5_000);
        assertThat(result.json()).hasValue("\"no args\"");
        evaluator.release(handle);
    }

    @Test
    @Order(61)
    void handleCallWithMixedArgs() throws Exception {
        int handle = pumpAndGet(evaluator.evaluateHandle("({f: function(n, s, b) { return '' + n + s + b; }})"), 5_000);
        CefScriptEngine.Result result = pumpAndGet(evaluator.call(handle, "f", "[42, \"hello\", true]", false), 5_000);
        assertThat(result.json()).hasValue("\"42hellotrue\"");
        evaluator.release(handle);
    }

    @Test
    @Order(62)
    void handleCallWithObjectArg() throws Exception {
        int handle = pumpAndGet(evaluator.evaluateHandle("({f: function(obj) { return obj.x + obj.y; }})"), 5_000);
        CefScriptEngine.Result result =
                pumpAndGet(evaluator.call(handle, "f", "[{\"x\": 10, \"y\": 20}]", false), 5_000);
        assertThat(result.json()).hasValue("30");
        evaluator.release(handle);
    }

    @Test
    @Order(63)
    void handleCallWithArrayArg() throws Exception {
        int handle = pumpAndGet(
                evaluator.evaluateHandle("({sum: function(arr) { return arr.reduce((a,b) => a+b, 0); }})"), 5_000);
        CefScriptEngine.Result result = pumpAndGet(evaluator.call(handle, "sum", "[[1,2,3,4,5]]", false), 5_000);
        assertThat(result.json()).hasValue("15");
        evaluator.release(handle);
    }

    @Test
    @Order(70)
    void handleChainedOperations() throws Exception {
        pumpAndGet(
                evaluator.evaluate("window.__Builder = function() {"
                        + "  this.items = [];"
                        + "  this.add = function(v) { this.items.push(v); return this; };"
                        + "  this.result = function() { return this.items; };"
                        + "}"),
                5_000);

        int handle = pumpAndGet(evaluator.evaluateHandle("new window.__Builder()"), 5_000);

        CefScriptEngine.Result r1 = pumpAndGet(evaluator.call(handle, "add", "[\"a\"]", true), 5_000);
        assertThat(r1.isHandle()).isTrue();

        CefScriptEngine.Result r2 = pumpAndGet(evaluator.call(r1.handle(), "add", "[\"b\"]", true), 5_000);
        assertThat(r2.isHandle()).isTrue();

        CefScriptEngine.Result r3 = pumpAndGet(evaluator.call(handle, "add", "[\"c\"]", true), 5_000);
        assertThat(r3.isHandle()).isTrue();

        CefScriptEngine.Result result = pumpAndGet(evaluator.call(handle, "result", "[]", false), 5_000);
        assertThat(result.isJson())
                .as("result() should return JSON, got: " + result)
                .isTrue();
        assertThat(result.json()).hasValue("[\"a\",\"b\",\"c\"]");

        evaluator.release(r3.handle());
        evaluator.release(r2.handle());
        evaluator.release(r1.handle());
        evaluator.release(handle);
    }

    @Test
    @Order(71)
    void handleAccessDomDocument() throws Exception {
        int docHandle = pumpAndGet(evaluator.evaluateHandle("document"), 5_000);
        assertThat(docHandle).isGreaterThan(0);

        CefScriptEngine.Result titleResult = pumpAndGet(evaluator.getProperty(docHandle, "title", false), 5_000);
        assertThat(titleResult.isJson()).isTrue();
        assertThat(titleResult.json()).hasValue("\"\"");

        CefScriptEngine.Result elemResult =
                pumpAndGet(evaluator.call(docHandle, "createElement", "[\"div\"]", true), 5_000);
        assertThat(elemResult.isHandle()).isTrue();
        int divHandle = elemResult.handle();

        pumpAndGet(evaluator.setProperty(divHandle, "innerHTML", "\"<span>test</span>\""), 5_000);

        CefScriptEngine.Result htmlResult = pumpAndGet(evaluator.getProperty(divHandle, "innerHTML", false), 5_000);
        assertThat(htmlResult.json()).hasValue("\"<span>test</span>\"");

        evaluator.release(divHandle);
        evaluator.release(docHandle);
    }

    @Test
    @Order(80)
    void evalMultipleConcurrentRequests() throws Exception {
        CompletableFuture<String> f1 = evaluator.evaluate("1 + 1");
        CompletableFuture<String> f2 = evaluator.evaluate("2 + 2");
        CompletableFuture<String> f3 = evaluator.evaluate("3 + 3");

        pumpUntilAllDone(5_000, f1, f2, f3);

        assertThat(f1.get()).isEqualTo("2");
        assertThat(f2.get()).isEqualTo("4");
        assertThat(f3.get()).isEqualTo("6");
    }

    @Test
    @Order(81)
    void evalInterleavedJsonAndHandleRequests() throws Exception {
        CompletableFuture<String> jsonFuture = evaluator.evaluate("'json result'");
        CompletableFuture<Integer> handleFuture = evaluator.evaluateHandle("({type: 'handle'})");
        CompletableFuture<String> jsonFuture2 = evaluator.evaluate("100 * 2");

        pumpUntilAllDone(5_000, jsonFuture, handleFuture, jsonFuture2);

        assertThat(jsonFuture.get()).isEqualTo("\"json result\"");
        assertThat(handleFuture.get()).isGreaterThan(0);
        assertThat(jsonFuture2.get()).isEqualTo("200");

        evaluator.release(handleFuture.get());
    }

    @Test
    @Order(90)
    void callbackInvokedWithHandleArgs() throws Exception {
        CompletableFuture<int[]> received = new CompletableFuture<>();
        int cbHandle = pumpAndGet(evaluator.createCallback(received::complete), 5_000);
        assertThat(cbHandle).isGreaterThan(0);

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
    void callbackInvokedMultipleTimes() throws Exception {
        java.util.List<int[]> invocations = java.util.Collections.synchronizedList(new java.util.ArrayList<>());
        int cbHandle = pumpAndGet(evaluator.createCallback(invocations::add), 5_000);

        pumpAndGet(evaluator.invoke(cbHandle, "[\"a\"]", false), 5_000);
        pumpAndGet(evaluator.invoke(cbHandle, "[\"b\"]", false), 5_000);
        pumpAndGet(evaluator.invoke(cbHandle, "[\"c\"]", false), 5_000);

        TestDeadline.after(Duration.ofSeconds(5))
                .until(
                        () -> invocations.size() == 3,
                        Cef.INSTANCE::doMessageLoopWork,
                        Duration.ofMillis(16),
                        "callback delivery");
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
    void callbackNoArgs() throws Exception {
        CompletableFuture<int[]> received = new CompletableFuture<>();
        int cbHandle = pumpAndGet(evaluator.createCallback(received::complete), 5_000);

        pumpAndGet(evaluator.invoke(cbHandle, "[]", false), 5_000);

        pumpUntilDone(received, 5_000);
        assertThat(received.get()).isEmpty();

        evaluator.release(cbHandle);
    }

    @Test
    @Order(93)
    void callbackObjectArgPreservesProperties() throws Exception {
        CompletableFuture<int[]> received = new CompletableFuture<>();
        int cbHandle = pumpAndGet(evaluator.createCallback(received::complete), 5_000);

        pumpAndGet(evaluator.invoke(cbHandle, "[{\"x\":1, \"y\":2}]", false), 5_000);

        pumpUntilDone(received, 5_000);
        int[] argHandles = received.get();
        assertThat(argHandles).hasSize(1);

        CefScriptEngine.Result xResult = pumpAndGet(evaluator.getProperty(argHandles[0], "x", false), 5_000);
        assertThat(xResult.isJson()).isTrue();
        assertThat(xResult.json()).hasValue("1");

        CefScriptEngine.Result yResult = pumpAndGet(evaluator.getProperty(argHandles[0], "y", false), 5_000);
        assertThat(yResult.json()).hasValue("2");

        evaluator.release(argHandles[0]);
        evaluator.release(cbHandle);
    }

    @Test
    @Order(94)
    void callbackNullArgIsMinusOne() throws Exception {
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

    @Test
    @Order(99)
    void disposeCancelsPendingFutures() {
        CefFrame frame = nonRespondingFrame();
        CefScriptEngine disposable = new CefScriptEngine(() -> frame);

        CompletableFuture<String> pending1 = disposable.evaluate("1");
        CompletableFuture<String> pending2 = disposable.evaluate("2");

        assertThat(pending1).isNotDone();
        assertThat(pending2).isNotDone();

        disposable.close();

        assertThat(pending1).isCompletedExceptionally();
        assertThat(pending2).isCompletedExceptionally();
    }

    @Test
    @Order(97)
    void cancelPendingClearsOldContextWorkWithoutDisposingEngine() {
        CefFrame frame = nonRespondingFrame();
        try (CefScriptEngine reusable = new CefScriptEngine(() -> frame)) {
            CompletableFuture<String> pending = reusable.evaluate("1 + 1");
            CompletableFuture<Integer> callback = reusable.createCallback(arguments -> {});

            assertThat(pending).isNotDone();
            assertThat(callback).isNotDone();
            assertThat(reusable.pendingRequestCount()).isEqualTo(2);
            assertThat(reusable.callbackCount()).isOne();

            reusable.cancelPending("test navigation");

            assertThat(pending).isCompletedExceptionally();
            assertThat(callback).isCompletedExceptionally();
            assertThat(reusable.pendingRequestCount()).isZero();
            assertThat(reusable.callbackCount()).isZero();

            CompletableFuture<String> acceptedAfterCancellation = reusable.evaluate("6 * 7");
            assertThat(acceptedAfterCancellation).isNotDone();
            assertThat(reusable.pendingRequestCount()).isOne();

            reusable.cancelPending("test cleanup");
            assertThat(acceptedAfterCancellation).isCompletedExceptionally();
            assertThat(reusable.pendingRequestCount()).isZero();
        }
    }

    @Test
    @Order(98)
    void timeoutRemovesPendingRequestAndCallback() throws Exception {
        CefFrame frame = nonRespondingFrame();
        CefScriptEngine disposable = new CefScriptEngine(() -> frame);

        CompletableFuture<String> evaluation = disposable.evaluate("1 + 1").orTimeout(1, TimeUnit.MILLISECONDS);
        CompletableFuture<Integer> callback =
                disposable.createCallback(arguments -> {}).orTimeout(1, TimeUnit.MILLISECONDS);
        TestDeadline.after(java.time.Duration.ofSeconds(2))
                .until(
                        () -> evaluation.isCompletedExceptionally()
                                && callback.isCompletedExceptionally()
                                && disposable.pendingRequestCount() == 0
                                && disposable.callbackCount() == 0,
                        java.time.Duration.ofMillis(1),
                        "script timeout cleanup");

        assertThat(evaluation).isCompletedExceptionally();
        assertThat(callback).isCompletedExceptionally();
        assertThat(disposable.pendingRequestCount()).isZero();
        assertThat(disposable.callbackCount()).isZero();
        disposable.dispose();
    }

    @Test
    @Order(95)
    void handleOperationsAcceptArbitraryPropertyNames() throws Exception {
        int handle = pumpAndGet(evaluator.evaluateHandle("({})"), 5_000);
        String key = "quote'\\slash\nline\u2028separator";

        pumpAndGet(evaluator.setProperty(handle, key, "41"), 5_000);
        CefScriptEngine.Result result = pumpAndGet(evaluator.getProperty(handle, key, false), 5_000);

        assertThat(result.json()).contains("41");
        evaluator.release(handle);
    }

    @Test
    @Order(96)
    void callAcceptsArbitraryMethodNames() throws Exception {
        String method = "quote'\\slash\nline\u2028separator";
        int handle = pumpAndGet(evaluator.evaluateHandle("({[" + jsString(method) + "]: value => value + 1})"), 5_000);

        CefScriptEngine.Result result = pumpAndGet(evaluator.call(handle, method, "[41]", false), 5_000);

        assertThat(result.json()).contains("42");
        evaluator.release(handle);
    }

    private static String jsString(String value) {
        return "'"
                + value.replace("\\", "\\\\")
                        .replace("'", "\\'")
                        .replace("\n", "\\n")
                        .replace("\u2028", "\\u2028") + "'";
    }

    private static CefFrame nonRespondingFrame() {
        return (CefFrame) Proxy.newProxyInstance(
                CefFrame.class.getClassLoader(), new Class<?>[] {CefFrame.class}, (proxy, method, arguments) -> {
                    if (method.getName().equals("sendProcessMessage")) {
                        return null;
                    }
                    throw new UnsupportedOperationException(method.getName());
                });
    }

    private static <T> T pumpAndGet(CompletableFuture<T> future, long timeoutMs) throws Exception {
        TestDeadline deadline = TestDeadline.after(Duration.ofMillis(timeoutMs));
        deadline.until(
                future::isDone, () -> Cef.INSTANCE.doMessageLoopWork(), Duration.ofMillis(16), "script completion");
        return deadline.await(future, "script result");
    }

    private static void assertPumpedExceptionally(CompletableFuture<?> future, long timeoutMs, String messageContains)
            throws Exception {
        TestDeadline.after(Duration.ofMillis(timeoutMs))
                .until(
                        future::isDone,
                        () -> Cef.INSTANCE.doMessageLoopWork(),
                        Duration.ofMillis(16),
                        "exceptional script completion");
        assertThat(future).isCompletedExceptionally();
        try {
            future.get();
        } catch (java.util.concurrent.ExecutionException e) {
            Throwable cause = Objects.requireNonNull(e.getCause(), "execution exception cause");
            assertThat(cause).isInstanceOf(CefScriptException.class);
            assertThat(cause.getMessage()).contains(messageContains);
        }
    }

    private static void pumpUntilAllDone(long timeoutMs, CompletableFuture<?>... futures) throws Exception {
        CompletableFuture<Void> all = CompletableFuture.allOf(futures);
        TestDeadline.after(Duration.ofMillis(timeoutMs))
                .until(
                        all::isDone,
                        () -> Cef.INSTANCE.doMessageLoopWork(),
                        Duration.ofMillis(16),
                        "script completions");
    }

    private static void pumpUntilDone(CompletableFuture<?> future, long timeoutMs) throws Exception {
        TestDeadline.after(Duration.ofMillis(timeoutMs))
                .until(
                        future::isDone,
                        () -> Cef.INSTANCE.doMessageLoopWork(),
                        Duration.ofMillis(16),
                        "script completion");
    }
}
