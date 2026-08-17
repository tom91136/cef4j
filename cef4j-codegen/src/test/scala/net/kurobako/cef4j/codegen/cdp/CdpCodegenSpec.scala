package net.kurobako.cef4j.codegen.cdp

import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path

import net.kurobako.cef4j.codegen.FileSystem
import upickle.default.read

class CdpCodegenSpec extends munit.FunSuite {
  test("parses PDL into the JSON protocol model") {
    val pdl  = PdlParser.parse(resourcePath("browser_protocol.pdl"))
    val json = read[Protocol](Files.readString(resourcePath("browser_protocol.json")))
    assertEquals(pdl, json)
  }

  test("parses relative includes") {
    val root = Files.createTempDirectory("cef4j-pdl-include-")
    try {
      val _ = Files.writeString(root.resolve("included.pdl"), "domain Included\n")
      val _ = Files.writeString(root.resolve("root.pdl"), "include included.pdl\n")
      assertEquals(PdlParser.parse(root.resolve("root.pdl")).domains.map(_.domain), List("Included"))
    } finally FileSystem.deleteTree(root)
  }

  test("rejects recursive includes") {
    val root = Files.createTempDirectory("cef4j-pdl-recursive-")
    try {
      val schema = root.resolve("schema.pdl")
      val _      = Files.writeString(schema, "include schema.pdl\n")
      intercept[IOException](PdlParser.parse(schema))
    } finally FileSystem.deleteTree(root)
  }

  test("derives the Chromium version from CEF coordinates") {
    assertEquals(
      SchemaFetcher.chromiumVersionOf("150.0.18+gdb11278+chromium-150.0.7871.213"),
      "150.0.7871.213"
    )
    intercept[IllegalArgumentException](SchemaFetcher.chromiumVersionOf("150.0.18+gdb11278"))
  }

  test("uses a complete cached schema without network access") {
    val root      = Files.createTempDirectory("cef4j-cdp-cache-")
    val schemaDir = root.resolve("cache/cdp-pdl-150.0.0.0")
    try {
      Files.createDirectories(schemaDir)
      Files.copy(resourcePath("browser_protocol.pdl"), schemaDir.resolve("browser_protocol.pdl"))
      Files.copy(resourcePath("js_protocol.pdl"), schemaDir.resolve("js_protocol.pdl"))
      Files.copy(resourcePath("schema.properties"), schemaDir.resolve("schema.properties"))

      Main.main(Array(
        "--cef-version=150.0.0+fixture+chromium-150.0.0.0",
        s"--schema-cache=${root.resolve("cache")}",
        s"--out-java=${root.resolve("java")}",
        s"--out-resources=${root.resolve("resources")}",
        "--java-package=example.cached"
      ))

      assert(Files.isRegularFile(root.resolve("java/example/cached/Page.java")))
    } finally FileSystem.deleteTree(root)
  }

  test("generates deterministic Java and schema resources from local JSON") {
    val root = Files.createTempDirectory("cef4j-cdp-codegen-")
    try {
      Main.main(Array(
        s"--browser-schema=${resourcePath("browser_protocol.json")}",
        s"--javascript-schema=${resourcePath("js_protocol.json")}",
        s"--schema-metadata=${resourcePath("schema.properties")}",
        s"--out-java=${root.resolve("java")}",
        s"--out-resources=${root.resolve("resources")}",
        "--java-package=example.cdp"
      ))

      val page     = Files.readString(root.resolve("java/example/cdp/Page.java"))
      val runtime  = Files.readString(root.resolve("java/example/cdp/Runtime.java"))
      val domains  = Files.readString(root.resolve("java/example/cdp/CdpDomains.java"))
      val schema   = Files.readString(root.resolve("resources/META-INF/cef4j/cdp/protocol.json"))
      val metadata = Files.readString(root.resolve("resources/META-INF/cef4j/cdp/schema.properties"))

      assert(page.startsWith("// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform"))
      assert(page.contains("import javax.annotation.processing.Generated;"))
      assert(page.contains("@Generated(\"mvn generate-sources -pl cef4j-platform\")"))
      assert(page.contains("public CompletionStage<NavigateResult> navigate(NavigateParams params)"))
      assert(page.contains("public CompletionStage<EnableResult> enable()"))
      assert(page.contains("return enable(EnableParams.builder().build());"))
      assert(page.contains("@Deprecated\n        public CdpSubscription onLoadEventFired"))
      assert(page.contains("{@code Page}"))
      assert(runtime.contains("public Builder classValue(@Nullable String value)"))
      assert(domains.contains("@Generated(\"mvn generate-sources -pl cef4j-platform\")"))
      assert(domains.contains("public Page.Client page()"))
      assert(schema.contains("\"domain\": \"Page\""))
      assert(schema.contains("\"domain\": \"Runtime\""))
      assert(metadata.contains("chromium.version=150.0.0.0"))
      assert(metadata.contains("schema.sha256="))
    } finally FileSystem.deleteTree(root)
  }

  private def resourcePath(name: String): Path =
    Path.of(Option(getClass.getResource(s"/cdp/$name")).getOrElse(fail(s"missing CDP resource: $name")).toURI)
}
