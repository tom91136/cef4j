package net.kurobako.cef4j.policy;

import com.google.errorprone.CompilationTestHelper;
import org.junit.jupiter.api.Test;

class TestSpinWaitForbiddenTest {
    @Test
    void rejectsSpinWaitInTests() {
        helper().addSourceLines(
                        "test/ExampleTest.java",
                        "package test;",
                        "class ExampleTest {",
                        "  void waitForWork() {",
                        "    // BUG: Diagnostic contains: use TestDeadline",
                        "    Thread.onSpinWait();",
                        "  }",
                        "}")
                .doTest();
    }

    @Test
    void permitsSpinWaitInProductionCode() {
        helper().addSourceLines(
                        "test/Worker.java",
                        "package test;",
                        "class Worker {",
                        "  void waitForWork() { Thread.onSpinWait(); }",
                        "}")
                .doTest();
    }

    private static CompilationTestHelper helper() {
        return CompilationTestHelper.newInstance(TestSpinWaitForbidden.class, TestSpinWaitForbiddenTest.class);
    }
}
