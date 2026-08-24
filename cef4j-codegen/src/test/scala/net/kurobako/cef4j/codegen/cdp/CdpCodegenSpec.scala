package net.kurobako.cef4j.codegen.cdp

import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.time.Duration
import scala.collection.mutable.ListBuffer

import net.kurobako.cef4j.codegen.FileSystem
import net.kurobako.cef4j.codegen.TempDirectorySuite

class CdpCodegenSpec extends TempDirectorySuite {
  test("parses the PDL fixture into the expected AST") {
    val pdl = PdlParser.parse(resourcePath("browser_protocol.pdl"))
    assertEquals(
      pdl,
      PDL.Protocol(
        PDL.Version("1", "3"),
        List(
          PDL.Domain(
            "Page",
            Some("A small `Page` fixture."),
            PDL.Flags(),
            Nil,
            List(
              PDL.Decl.Type("FrameId", PDL.TypeExpr.Str, None, None, PDL.Flags()),
              PDL.Decl.Type(
                "Frame",
                PDL.TypeExpr.Obj,
                Some(PDL.TypeBody.Object(List(PDL.SubItem("id", PDL.TypeExpr.Ref(None, "FrameId"))))),
                None,
                PDL.Flags()
              ),
              PDL.Decl.Command(
                "enable",
                None,
                List(PDL.SubItem("includeDebug", PDL.TypeExpr.Boolean, optional = true)),
                Nil,
                None,
                PDL.Flags()
              ),
              PDL.Decl.Command(
                "navigate",
                None,
                List(PDL.SubItem("url", PDL.TypeExpr.Str)),
                List(PDL.SubItem("frameId", PDL.TypeExpr.Ref(None, "FrameId"))),
                None,
                PDL.Flags()
              ),
              PDL.Decl.Event(
                "loadEventFired",
                List(PDL.SubItem("time", PDL.TypeExpr.Number)),
                None,
                PDL.Flags(deprecated = true)
              )
            )
          )
        )
      )
    )
  }

  test("parses relative includes") {
    val root = tempDirectory("cef4j-pdl-include-")
    try {
      val _ = Files.writeString(root.resolve("included.pdl"), "domain Included\n")
      val _ = Files.writeString(root.resolve("root.pdl"), "include included.pdl\n")
      assertEquals(PdlParser.parse(root.resolve("root.pdl")).domains.map(_.name), List("Included"))
    } finally FileSystem.deleteTree(root)
  }

  test("rejects recursive includes") {
    val root = tempDirectory("cef4j-pdl-recursive-")
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

  test("retries five consecutive transient download failures") {
    var attempts = 0
    val delays   = ListBuffer.empty[Duration]
    val result   = SchemaFetcher.retry(
      () => {
        attempts += 1
        if (attempts <= 5) Left(IOException("transient")) else Right("schema")
      },
      delay => delays += delay
    )

    assertEquals(result, "schema")
    assertEquals(attempts, 6)
    assertEquals(delays.toList, List(1L, 2L, 4L, 8L, 16L).map(Duration.ofSeconds))
  }

  test("uses a complete cached schema without network access") {
    val root      = tempDirectory("cef4j-cdp-cache-")
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

  test("generates deterministic Java and schema resources from local schemas") {
    val root = tempDirectory("cef4j-cdp-codegen-")
    try {
      Main.main(Array(
        s"--browser-schema=${resourcePath("browser_protocol.pdl")}",
        s"--javascript-schema=${resourcePath("js_protocol.pdl")}",
        s"--schema-metadata=${resourcePath("schema.properties")}",
        s"--out-java=${root.resolve("java")}",
        s"--out-resources=${root.resolve("resources")}",
        "--java-package=example.cdp"
      ))

      val page     = Files.readString(root.resolve("java/example/cdp/Page.java"))
      val runtime  = Files.readString(root.resolve("java/example/cdp/Runtime.java"))
      val domains  = Files.readString(root.resolve("java/example/cdp/CdpDomains.java"))
      val metadata = Files.readString(root.resolve("resources/META-INF/cef4j/cdp/schema.properties"))

      assert(page.startsWith("// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform"))
      assert(page.contains("import javax.annotation.processing.Generated;"))
      assert(page.contains("@Generated(\"mvn generate-sources -pl cef4j-platform\")"))
      assert(page.contains("public CompletionStage<Page.FrameId> navigate(String url)"))
      assert(page.contains("public CompletionStage<Void> enable(Optional<Boolean> includeDebug)"))
      assert(page.contains("return enable(Optional.empty());"))
      assert(page.contains("@Deprecated\n        public CdpSubscription onLoadEventFired"))
      assert(page.contains("{@code Page}"))
      assert(runtime.contains("public Optional<String> classValue()"))
      assert(domains.contains("@Generated(\"mvn generate-sources -pl cef4j-platform\")"))
      assert(domains.contains("public Page.Client page()"))
      assert(domains.contains("public DOM.Client dom()"))
      assert(domains.contains("@Deprecated @SuppressWarnings(\"InlineMeSuggester\") public DOM.Client dOM()"))
      assert(metadata.contains("chromium.version=150.0.0.0"))
      assert(!metadata.contains("schema.sha256="))
    } finally FileSystem.deleteTree(root)
  }

  private def resourcePath(name: String): Path =
    Path.of(Option(getClass.getResource(s"/cdp/$name")).getOrElse(fail(s"missing CDP resource: $name")).toURI)
}
