package net.kurobako.cef4j.policy;

import com.google.errorprone.CompilationTestHelper;
import org.junit.jupiter.api.Test;

class NullableForbiddenTest {

    @Test
    void unrelatedOverloadsDoNotInheritNullableExemption() {
        helper().addSourceLines(
                        "test/Overload.java",
                        "package test;",
                        "import javax.annotation.Nullable;",
                        "class Parent {",
                        "  @Nullable Object value(String input) { return null; }",
                        "  void accept(@Nullable String input) {}",
                        "}",
                        "class Child extends Parent {",
                        "  // BUG: Diagnostic contains: @Nullable is banned",
                        "  @Nullable public Object value(Integer input) { return null; }",
                        "  // BUG: Diagnostic contains: @Nullable is banned",
                        "  public void accept(@Nullable Integer input) {}",
                        "}")
                .doTest();
    }

    @Test
    void genericOverridesInheritReturnAndParameterContracts() {
        helper().addSourceLines(
                        "test/GenericOverride.java",
                        "package test;",
                        "import javax.annotation.Nullable;",
                        "class Parent<T> {",
                        "  @Nullable T value() { return null; }",
                        "  void accept(@Nullable T input) {}",
                        "}",
                        "class Child extends Parent<String> {",
                        "  @Override @Nullable public String value() { return null; }",
                        "  @Override public void accept(@Nullable String input) {}",
                        "}")
                .doTest();
    }

    @Test
    void interfaceDiamondUsesActualOverriddenDeclarations() {
        helper().setArgs("-XepOpt:NullableForbidden:AllowedPackages=allowed")
                .addSourceLines(
                        "allowed/Base.java",
                        "package allowed;",
                        "import javax.annotation.Nullable;",
                        "public interface Base<T> {",
                        "  @Nullable T value();",
                        "  void accept(@Nullable T input);",
                        "}")
                .addSourceLines(
                        "test/Diamond.java",
                        "package test;",
                        "import javax.annotation.Nullable;",
                        "interface Left extends allowed.Base<String> {}",
                        "interface Right extends allowed.Base<String> {}",
                        "class Diamond implements Left, Right {",
                        "  @Override @Nullable public String value() { return null; }",
                        "  @Override public void accept(@Nullable String input) {}",
                        "}")
                .doTest();
    }

    private static CompilationTestHelper helper() {
        return CompilationTestHelper.newInstance(NullableForbidden.class, NullableForbiddenTest.class);
    }
}
