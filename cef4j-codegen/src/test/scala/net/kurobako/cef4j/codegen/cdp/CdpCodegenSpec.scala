package net.kurobako.cef4j.codegen.cdp

import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.time.Duration
import java.util.concurrent.atomic.AtomicInteger
import scala.collection.mutable

import net.kurobako.cef4j.codegen.FileSystem
import net.kurobako.cef4j.codegen.TempDirectorySuite

class CdpCodegenSpec extends TempDirectorySuite {
  private val requestConstructor = """\s*public \w+Request\(.+\) \{""".r

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
                List(PDL.SubItem("includeDebug", PDL.TypeExpr.Boolean, optional = true)),
                Nil,
                None,
                PDL.Flags()
              ),
              PDL.Decl.Command(
                "navigate",
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

  test("selects the legacy monolithic browser schema before Chromium 80") {
    assert(SchemaFetcher.usesLegacyBrowserSchema("73.0.3683.75"))
    assert(SchemaFetcher.usesLegacyBrowserSchema("78.0.3904.108"))
    assert(!SchemaFetcher.usesLegacyBrowserSchema("80.0.3987.163"))
  }

  test("selects the legacy V8 schema location before Chromium 78") {
    assert(SchemaFetcher.usesLegacyV8Schema("73.0.3683.75"))
    assert(SchemaFetcher.usesLegacyV8Schema("75.0.3770.100"))
    assert(!SchemaFetcher.usesLegacyV8Schema("78.0.3904.108"))
  }

  test("retries five consecutive transient download failures") {
    val attempts = AtomicInteger()
    val delays   = mutable.ListBuffer.empty[Duration]
    val result   = SchemaFetcher.retry(
      () => {
        attempts.incrementAndGet()
        if (attempts.get() <= 5) Left(IOException("transient")) else Right("schema")
      },
      delay => delays += delay
    )

    assertEquals(result, "schema")
    assertEquals(attempts.get(), 6)
    assertEquals(delays.toList, List(1L, 2L, 4L, 8L, 16L).map(Duration.ofSeconds(_)))
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
        "--java-package=example.cdp",
        "--regenerate-command=./mvnw generate-sources -pl cef4j-cdp"
      ))

      val page     = Files.readString(root.resolve("java/example/cdp/Page.java"))
      val runtime  = Files.readString(root.resolve("java/example/cdp/Runtime.java"))
      val domains  = Files.readString(root.resolve("java/example/cdp/CdpDomains.java"))
      val metadata = Files.readString(root.resolve("resources/META-INF/cef4j/cdp/schema.properties"))

      assert(page.startsWith("// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp"))
      assert(page.contains("import javax.annotation.processing.Generated;"))
      assert(page.contains("@Generated(\"./mvnw generate-sources -pl cef4j-cdp\")"))
      assert(page.contains("public CompletionStage<Page.FrameId> navigate(String url)"))
      assert(page.contains("public CompletionStage<Void> enable(boolean includeDebug)"))
      assert(page.contains("public CompletionStage<Void> enable()"))
      assert(page.contains("@Deprecated\n        public CdpSubscription onLoadEventFired"))
      assert(page.contains("{@code Page}"))
      assert(runtime.contains("public Optional<String> classValue()"))
      assert(domains.contains("@Generated(\"./mvnw generate-sources -pl cef4j-cdp\")"))
      assert(domains.contains("public Page.Client page()"))
      assert(domains.contains("public DOM.Client dom()"))
      assert(domains.contains("@Deprecated @SuppressWarnings(\"InlineMeSuggester\") public DOM.Client dOM()"))
      assert(metadata.contains("chromium.version=150.0.0.0"))
      assert(!metadata.contains("schema.sha256="))
    } finally FileSystem.deleteTree(root)
  }

  private val shapeProtocol =
    """version
      |  major 1
      |  minor 3
      |
      |domain Shape
      |  type NodeId extends integer
      |  type Label extends string
      |  type Mode extends string
      |    enum
      |      fast
      |      slow
      |  type Node extends object
      |    properties
      |      NodeId id
      |      optional NodeId parentId
      |      string name
      |      optional string title
      |      integer count
      |      optional integer depth
      |      number ratio
      |      optional number scale
      |      boolean flag
      |      optional boolean hidden
      |      object bag
      |      optional object extra
      |      any payload
      |      optional any hint
      |      array of string tags
      |      optional array of Node children
      |      Mode mode
      |      optional Label label
      |      enum level
      |        low
      |        high
      |
      |  command enable
      |
      |  command ping
      |    returns
      |      Label label
      |
      |  command probe
      |    parameters
      |      optional integer depth
      |      optional Label label
      |    returns
      |      optional Node node
      |
      |  command resolve
      |    parameters
      |      NodeId id
      |      optional boolean deep
      |    returns
      |      array of Node nodes
      |
      |  command describe
      |    returns
      |      string name
      |      integer count
      |
      |  event changed
      |    parameters
      |      NodeId id
      |""".stripMargin

  private def generateShape(): String = {
    val root = tempDirectory("cef4j-cdp-shape-")
    try {
      val schema = root.resolve("browser_protocol.pdl")
      val _      = Files.writeString(schema, shapeProtocol)
      Main.main(Array(
        s"--browser-schema=$schema",
        s"--javascript-schema=${resourcePath("js_protocol.pdl")}",
        s"--schema-metadata=${resourcePath("schema.properties")}",
        s"--out-java=${root.resolve("java")}",
        s"--out-resources=${root.resolve("resources")}",
        "--java-package=example.shape",
        "--regenerate-command=./mvnw generate-sources -pl cef4j-cdp"
      ))
      Files.readString(root.resolve("java/example/shape/Shape.java"))
    } finally FileSystem.deleteTree(root)
  }

  test("accessors of untyped values call a named helper") {
    val shape = generateShape()
    List(
      "return requireString(\"name\");",
      "return requireLong(\"count\");",
      "return requireDouble(\"ratio\");",
      "return requireBoolean(\"flag\");",
      "return requireObject(\"bag\");",
      "return require(\"payload\");",
      "return optionalString(\"title\");",
      "return optionalLong(\"depth\");",
      "return optionalDouble(\"scale\");",
      "return optionalBoolean(\"hidden\");",
      "return optionalObject(\"extra\");",
      "return raw(\"hint\");"
    ).foreach(line => assert(shape.contains(line), line))
  }

  test("accessors of converted values read a field constant") {
    val shape = generateShape()
    List(
      "    private static final CdpField<Shape.NodeId> ID = CdpField.of(\"id\", value -> new Shape.NodeId(((Number) value).longValue()));",
      "    private static final CdpField<Shape.Mode> MODE = CdpField.of(\"mode\", value -> Shape.Mode.of((String) value));",
      "    private static final CdpField<Node.LevelValues> LEVEL = CdpField.of(\"level\", value -> Node.LevelValues.of((String) value));",
      "return require(ID);",
      "return optional(PARENT_ID);",
      "return optional(LABEL);",
      "return require(TAGS);",
      "return optional(CHILDREN);",
      "return require(MODE);",
      "return require(LEVEL);"
    ).foreach(line => assert(shape.contains(line), line))
  }

  test("equal fields share one constant per domain") {
    val shape = generateShape()
    assertEquals(shape.linesIterator.count(_.contains("CdpField.of(\"label\"")), 1)
    assertEquals(shape.linesIterator.count(_.contains("CdpField.of(\"id\"")), 1)
    assert(!shape.linesIterator.exists(line => line.startsWith("        private static final CdpField")), shape)
  }

  test("accessors keep their public signatures") {
    val shape = generateShape()
    List(
      "public Shape.NodeId id()",
      "public Optional<Shape.NodeId> parentId()",
      "public OptionalLong depth()",
      "public long count()",
      "public Optional<Boolean> hidden()",
      "public java.util.List<String> tags()",
      "public Optional<java.util.List<Shape.Node>> children()"
    ).foreach(line => assert(shape.contains(line), line))
  }

  test("commands are single calls to the shared helper") {
    val shape = generateShape()
    List(
      "public static final class Client extends CdpDomainClient {",
      "public Client(CdpClient client) { super(client); }",
      "return call(\"Shape.enable\");",
      "return call(\"Shape.ping\", LABEL::require);",
      "return call(\"Shape.probe\", params(\"depth\", depth, \"label\", label), NODE::optional);",
      "return call(\"Shape.probe\", NODE::optional);",
      "return call(\"Shape.probe\", request, NODE::optional);",
      "return call(\"Shape.resolve\", params(\"id\", id, \"deep\", deep), NODES::require);",
      "return call(\"Shape.resolve\", params(\"id\", id), NODES::require);",
      "return call(\"Shape.describe\", DescribeResult::fromMap);",
      "return on(\"Shape.changed\", ChangedEvent::fromMap, handler);",
      "    private static final CdpField<Shape.Label> LABEL = CdpField.of(\"label\", value -> new Shape.Label((String) value));"
    ).foreach(line => assert(shape.contains(line), line))
  }

  test("commands build no parameter maps of their own") {
    val shape = generateShape()
    assert(!shape.contains("params.put("), shape)
    assert(!shape.contains("new LinkedHashMap"), shape)
    assert(!shape.contains("client.call("), shape)
    assert(!shape.contains("result_ ->"), shape)
    assert(!shape.contains("(String) require("), shape)
  }

  test("requests have only the no-argument constructor") {
    val shape = generateShape()
    assert(shape.contains("        public ResolveRequest() {}"), shape)
    assert(!shape.linesIterator.exists(line => requestConstructor.matches(line)), shape)
  }

  private def resourcePath(name: String): Path =
    Path.of(Option(getClass.getResource(s"/cdp/$name")).getOrElse(fail(s"missing CDP resource: $name")).toURI)
}
