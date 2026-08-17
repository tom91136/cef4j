package net.kurobako.cef4j.codegen.cdp

import java.nio.file.Files
import scala.jdk.StreamConverters.*

class CdpCodegenSpec extends munit.FunSuite {
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

      assert(page.contains("public CompletionStage<NavigateResult> navigate(NavigateParams params)"))
      assert(page.contains("@Deprecated\n        public CdpSubscription onLoadEventFired"))
      assert(page.contains("{@code Page}"))
      assert(runtime.contains("public Builder classValue(@Nullable String value)"))
      assert(domains.contains("public Page.Client page()"))
      assert(schema.contains("\"domain\": \"Page\""))
      assert(schema.contains("\"domain\": \"Runtime\""))
      assert(metadata.contains("chromium.version=150.0.0.0"))
      assert(metadata.contains("schema.sha256="))
    } finally Files.walk(root).toScala(List).sortBy(_.getNameCount).reverse.foreach(Files.delete)
  }

  private def resourcePath(name: String) =
    java.nio.file.Paths.get(getClass.getResource(s"/cdp/$name").toURI)
}
