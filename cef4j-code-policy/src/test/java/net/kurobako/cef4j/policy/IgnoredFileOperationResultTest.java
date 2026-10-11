package net.kurobako.cef4j.policy;

import com.google.errorprone.CompilationTestHelper;
import org.junit.jupiter.api.Test;

class IgnoredFileOperationResultTest {
    @Test
    void rejectsIgnoredResult() {
        helper().addSourceLines(
                        "test/Installer.java",
                        "package test;",
                        "import java.io.File;",
                        "class Installer {",
                        "  void install(File launcher) {",
                        "    // BUG: Diagnostic contains: report failure",
                        "    launcher.setExecutable(true);",
                        "    // BUG: Diagnostic contains: report failure",
                        "    launcher.getParentFile().mkdirs();",
                        "  }",
                        "}")
                .doTest();
    }

    @Test
    void permitsCheckedResult() {
        helper().addSourceLines(
                        "test/Installer.java",
                        "package test;",
                        "import java.io.File;",
                        "import java.io.IOException;",
                        "class Installer {",
                        "  void install(File launcher) throws IOException {",
                        "    if (!launcher.setExecutable(true)) throw new IOException(\"not executable\");",
                        "    boolean created = launcher.exists() || launcher.createNewFile();",
                        "  }",
                        "}")
                .doTest();
    }

    private static CompilationTestHelper helper() {
        return CompilationTestHelper.newInstance(
                IgnoredFileOperationResult.class, IgnoredFileOperationResultTest.class);
    }
}
