package net.kurobako.cef4j.policy;

import com.google.errorprone.CompilationTestHelper;
import org.junit.jupiter.api.Test;

class NullableForbiddenTest {

    @Test
    void documentedInteropBoundaryAllowsNullableApi() {
        helper().addSourceLines(
                        "test/Boundary.java",
                        "package test;",
                        "import org.jspecify.annotations.Nullable;",
                        "import net.kurobako.cef4j.policy.NullableBoundary;",
                        "@NullableBoundary(\"external callback contract\")",
                        "public interface Boundary {",
                        "  @Nullable Object result(@Nullable Object input);",
                        "}")
                .doTest();
    }

    @Test
    void unrelatedOverloadsDoNotInheritNullableExemption() {
        helper().addSourceLines(
                        "test/Overload.java",
                        "package test;",
                        "import org.jspecify.annotations.Nullable;",
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
                        "import org.jspecify.annotations.Nullable;",
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
                        "import org.jspecify.annotations.Nullable;",
                        "public interface Base<T> {",
                        "  @Nullable T value();",
                        "  void accept(@Nullable T input);",
                        "}")
                .addSourceLines(
                        "test/Diamond.java",
                        "package test;",
                        "import org.jspecify.annotations.Nullable;",
                        "interface Left extends allowed.Base<String> {}",
                        "interface Right extends allowed.Base<String> {}",
                        "class Diamond implements Left, Right {",
                        "  @Override @Nullable public String value() { return null; }",
                        "  @Override public void accept(@Nullable String input) {}",
                        "}")
                .doTest();
    }

    @Test
    void nullAwaySuppressionOnPublicApiNeedsBoundary() {
        helper().addSourceLines(
                        "test/Lookup.java",
                        "package test;",
                        "import java.util.HashMap;",
                        "import java.util.Map;",
                        "import net.kurobako.cef4j.policy.NullableBoundary;",
                        "public class Lookup {",
                        "  private final Map<String, String> values = new HashMap<>();",
                        "  // BUG: Diagnostic contains: hides a nullable contract",
                        "  @SuppressWarnings(\"NullAway\")",
                        "  public String get(String key) { return values.get(key); }",
                        "  // BUG: Diagnostic contains: hides a nullable contract",
                        "  @SuppressWarnings({\"unchecked\", \"NullAway\"})",
                        "  public String remove(String key) { return values.remove(key); }",
                        "  @SuppressWarnings(\"NullAway\")",
                        "  private String peek(String key) { return values.get(key); }",
                        "  @NullableBoundary(\"map lookup\")",
                        "  @SuppressWarnings(\"NullAway\")",
                        "  public String find(String key) { return values.get(key); }",
                        "  @SuppressWarnings(\"unchecked\")",
                        "  public String size() { return String.valueOf(values.size()); }",
                        "}")
                .doTest();
    }

    @Test
    void equalsParameterMayBeNullable() {
        helper().addSourceLines(
                        "test/Value.java",
                        "package test;",
                        "import org.jspecify.annotations.Nullable;",
                        "public final class Value {",
                        "  @Override public boolean equals(@Nullable Object other) { return other == this; }",
                        "  @Override public int hashCode() { return 0; }",
                        "  // BUG: Diagnostic contains: @Nullable is banned",
                        "  public boolean equals(@Nullable Value other) { return other == this; }",
                        "}")
                .doTest();
    }

    private static CompilationTestHelper helper() {
        return CompilationTestHelper.newInstance(NullableForbidden.class, NullableForbiddenTest.class);
    }
}
