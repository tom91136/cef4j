package net.kurobako.cef4j.policy;

import com.google.errorprone.CompilationTestHelper;
import org.junit.jupiter.api.Test;

class VolatileNullCheckRereadTest {
    @Test
    void rejectsRereadAfterNullCheck() {
        helper().addSourceLines(
                        "test/Settings.java",
                        "package test;",
                        "import org.jspecify.annotations.Nullable;",
                        "class Settings {",
                        "  private volatile @Nullable String path;",
                        "  int length() {",
                        "    // BUG: Diagnostic contains: read again",
                        "    if (path != null) return path.length();",
                        "    return 0;",
                        "  }",
                        "  boolean empty() {",
                        "    // BUG: Diagnostic contains: read again",
                        "    return this.path == null || this.path.isEmpty();",
                        "  }",
                        "  Runnable later() {",
                        "    // BUG: Diagnostic contains: read again",
                        "    return () -> { if (path != null) System.out.println(path); };",
                        "  }",
                        "}")
                .doTest();
    }

    @Test
    void permitsLocalCopyAndPlainFields() {
        helper().addSourceLines(
                        "test/Settings.java",
                        "package test;",
                        "import org.jspecify.annotations.Nullable;",
                        "class Settings {",
                        "  private volatile @Nullable String path;",
                        "  private @Nullable String plain;",
                        "  int length() {",
                        "    String current = path;",
                        "    return current != null ? current.length() : 0;",
                        "  }",
                        "  boolean present() { return path != null; }",
                        "  void reset() { if (path != null) path = null; }",
                        "  int plainLength() { return plain != null ? plain.length() : 0; }",
                        "}")
                .doTest();
    }

    private static CompilationTestHelper helper() {
        return CompilationTestHelper.newInstance(VolatileNullCheckReread.class, VolatileNullCheckRereadTest.class);
    }
}
