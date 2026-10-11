package net.kurobako.cef4j.policy;

import com.google.errorprone.CompilationTestHelper;
import org.junit.jupiter.api.Test;

class NullableReferenceInitialiserTest {
    @Test
    void rejectsEmptyHolderWithNonNullValueType() {
        helper().addSourceLines(
                        "test/Holder.java",
                        "package test;",
                        "import java.util.concurrent.atomic.AtomicReference;",
                        "import java.util.concurrent.atomic.AtomicReferenceArray;",
                        "class Holder {",
                        "  // BUG: Diagnostic contains: starts as null",
                        "  private final AtomicReference<String> field = new AtomicReference<>();",
                        "  // BUG: Diagnostic contains: starts as null",
                        "  private final AtomicReferenceArray<String> slots = new AtomicReferenceArray<>(4);",
                        "  // BUG: Diagnostic contains: starts as null",
                        "  private final ThreadLocal<String> local = new ThreadLocal<>();",
                        "  void method() {",
                        "    // BUG: Diagnostic contains: starts as null",
                        "    AtomicReference<Thread> worker = new AtomicReference<>();",
                        "  }",
                        "}")
                .doTest();
    }

    @Test
    void permitsNullableValueTypeOrInitialValue() {
        helper().addSourceLines(
                        "test/Holder.java",
                        "package test;",
                        "import java.util.concurrent.atomic.AtomicReference;",
                        "import org.jspecify.annotations.Nullable;",
                        "class Holder<T> {",
                        "  private final AtomicReference<@Nullable String> field = new AtomicReference<>();",
                        "  private final AtomicReference<@Nullable T> generic = new AtomicReference<>();",
                        "  private final AtomicReference<String> initial = new AtomicReference<>(\"\");",
                        "  private final AtomicReference<java.util.Map.@Nullable Entry<String, String>> nested =",
                        "      new AtomicReference<>();",
                        "  private final ThreadLocal<String> withInitial = ThreadLocal.withInitial(() -> \"\");",
                        "  void method() {",
                        "    AtomicReference<@Nullable Thread> worker = new AtomicReference<>();",
                        "  }",
                        "}")
                .doTest();
    }

    private static CompilationTestHelper helper() {
        return CompilationTestHelper.newInstance(
                NullableReferenceInitialiser.class, NullableReferenceInitialiserTest.class);
    }
}
