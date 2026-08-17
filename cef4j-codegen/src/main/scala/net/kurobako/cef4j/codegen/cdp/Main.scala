package net.kurobako.cef4j.codegen.cdp

import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.security.MessageDigest
import java.util.Properties
import scala.collection.mutable
import scala.jdk.CollectionConverters.*
import scala.jdk.StreamConverters.*

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import net.kurobako.cef4j.codegen.AtomicFiles

/** Generates cef4j's map-backed Java CDP API from pinned Chromium protocol JSON files. */
object Main {
  private val mapper       = ObjectMapper()
  private val javaKeywords = Set(
    "abstract",
    "assert",
    "boolean",
    "break",
    "byte",
    "case",
    "catch",
    "char",
    "class",
    "const",
    "continue",
    "default",
    "do",
    "double",
    "else",
    "enum",
    "extends",
    "final",
    "finally",
    "float",
    "for",
    "goto",
    "if",
    "implements",
    "import",
    "instanceof",
    "int",
    "interface",
    "long",
    "native",
    "new",
    "package",
    "private",
    "protected",
    "public",
    "return",
    "short",
    "static",
    "strictfp",
    "super",
    "switch",
    "synchronized",
    "this",
    "throw",
    "throws",
    "transient",
    "try",
    "void",
    "volatile",
    "while",
    "true",
    "false",
    "null",
    "var",
    "record",
    "sealed",
    "permits",
    "yield"
  )

  private case class Config(
      browserSchema: Path = Paths.get("browser_protocol.json"),
      javascriptSchema: Path = Paths.get("js_protocol.json"),
      schemaMetadata: Path = Paths.get("schema.properties"),
      outJava: Path = Paths.get("."),
      outResources: Path = Paths.get("."),
      javaPackage: String = "net.kurobako.cef4j.cdp.generated"
  )

  private case class Resolved(javaType: String, kind: String)

  def main(args: Array[String]): Unit = generate(parseArgs(args.toList))

  private def generate(cfg: Config): Unit = {
    val browser = mapper.readTree(cfg.browserSchema.toFile)
    val js      = mapper.readTree(cfg.javascriptSchema.toFile)
    val props   = Properties()
    val reader  = Files.newBufferedReader(cfg.schemaMetadata, StandardCharsets.UTF_8)
    try props.load(reader)
    finally reader.close()

    val chromiumVersion = requiredProperty(props, "chromium.version")
    val v8Revision      = requiredProperty(props, "v8.revision")
    val protocol        = mapper.createObjectNode()
    protocol.set[JsonNode]("version", browser.path("version").deepCopy())
    val mergedDomains = protocol.putArray("domains")
    elements(browser.path("domains")).foreach(mergedDomains.add)
    elements(js.path("domains")).foreach(mergedDomains.add)

    val schemaBytes = (prettyJson(protocol) + "\n").getBytes(StandardCharsets.UTF_8)
    val fingerprint = MessageDigest.getInstance("SHA-256").digest(schemaBytes).map("%02x".format(_)).mkString
    val output      = cfg.outJava.resolve(cfg.javaPackage.replace('.', '/'))
    val resources   = cfg.outResources.resolve("META-INF/cef4j/cdp")
    cleanDirectory(output)
    Files.createDirectories(output)
    Files.createDirectories(resources)
    Files.write(resources.resolve("protocol.json"), schemaBytes)
    AtomicFiles.writeString(
      resources.resolve("schema.properties"),
      s"""chromium.version=$chromiumVersion
         |v8.revision=$v8Revision
         |browser.source=https://chromium.googlesource.com/chromium/src/+/refs/tags/$chromiumVersion/third_party/blink/public/devtools_protocol/
         |javascript.source=https://chromium.googlesource.com/v8/v8/+/$v8Revision/include/js_protocol.pdl
         |source.license=BSD-3-Clause
         |schema.sha256=$fingerprint
         |""".stripMargin
    )

    val domains        = elements(protocol.path("domains"))
    val browserDomains = elements(browser.path("domains")).map(_.path("domain").asText()).toSet
    val types          = domains.flatMap(domain =>
      elements(domain.path("types")).map(item => (domain.path("domain").asText(), item.path("id").asText()) -> item)
    ).toMap
    val emitter = Emitter(cfg.javaPackage, chromiumVersion, v8Revision, browserDomains, types)
    domains.foreach(domain =>
      AtomicFiles.writeString(output.resolve(s"${domain.path("domain").asText()}.java"), emitter.emitDomain(domain))
    )
    AtomicFiles.writeString(output.resolve("CdpDomains.java"), emitter.emitDomains(domains))
    println(s"cef4j CDP codegen complete: ${domains.size} domains for Chromium $chromiumVersion")
  }

  private final class Emitter(
      javaPackage: String,
      chromiumVersion: String,
      v8Revision: String,
      browserDomains: Set[String],
      types: Map[(String, String), JsonNode]
  ) {
    private def cap(name: String): String = {
      val value = name.replaceAll("[^A-Za-z0-9_$$]", "_")
      if (value.isEmpty) value else value.head.toUpper +: value.tail
    }

    private def ident(name: String): String = {
      val raw   = name.replaceAll("[^A-Za-z0-9_$$]", "_")
      val value = if (raw.isEmpty || raw.head.isDigit) s"_$raw" else raw
      if (javaKeywords(value) || Set("notify", "notifyAll", "wait")(value)) s"${value}Value" else value
    }

    private def modelName(domain: String, name: String): String =
      if (cap(name) == domain) s"${cap(name)}Value" else cap(name)

    private def resolve(domain: String, spec: JsonNode, seen: Set[(String, String)] = Set.empty): Resolved =
      if (spec.has("$ref")) {
        val ref   = spec.path("$ref").asText()
        val parts = ref.split("\\.", 2)
        val key   = if (parts.length == 2) parts(0) -> parts(1) else domain -> parts(0)
        types.get(key) match {
          case None                      => Resolved("Object", "any")
          case Some(target) if seen(key) => Resolved(s"${key._1}.${modelName(key._1, key._2)}", "model")
          case Some(target) if target.path("type").asText() == "object" && target.path("properties").size() > 0 =>
            Resolved(s"${key._1}.${modelName(key._1, key._2)}", "model")
          case Some(target) => resolve(key._1, target, seen + key)
        }
      } else if (spec.path("type").asText("any") == "array") {
        val inner = resolve(domain, spec.path("items"), seen)
        Resolved(s"java.util.List<${inner.javaType}>", s"list:${inner.kind}:${inner.javaType}")
      } else
        spec.path("type").asText("any") match {
          case "string" | "binary" => Resolved("String", "string")
          case "integer"           => Resolved("Long", "integer")
          case "number"            => Resolved("Double", "number")
          case "boolean"           => Resolved("Boolean", "boolean")
          case "object"            => Resolved("java.util.Map<String, Object>", "object")
          case _                   => Resolved("Object", "any")
        }

    private def decode(
        domain: String,
        spec: JsonNode,
        source: String,
        depth: Int = 0,
        seen: Set[(String, String)] = Set.empty
    ): String =
      if (spec.has("$ref")) {
        val parts = spec.path("$ref").asText().split("\\.", 2)
        val key   = if (parts.length == 2) parts(0) -> parts(1) else domain -> parts(0)
        types.get(key) match {
          case None => source
          case Some(target) if target.path("type").asText() == "object" && target.path("properties").size() > 0 =>
            s"${resolve(domain, spec).javaType}.fromMap(objectMap($source))"
          case Some(_) if seen(key) => source
          case Some(target)         => decode(key._1, target, source, depth, seen + key)
        }
      } else {
        resolve(domain, spec).kind match {
          case "string"                         => s"(String) $source"
          case "boolean"                        => s"(Boolean) $source"
          case "integer"                        => s"numberAsLong($source)"
          case "number"                         => s"numberAsDouble($source)"
          case "object"                         => s"objectMap($source)"
          case kind if kind.startsWith("list:") =>
            val variable = s"element$depth"
            s"list($source, $variable -> ${decode(domain, spec.path("items"), variable, depth + 1, seen)})"
          case _ => source
        }
      }

    private def normalizeDoc(text: String): String = {
      val escaped = text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
        .replace("\"", "&quot;").replace("'", "&#x27;").replace("@", "&#64;").replace("\n", " ")
      escaped.replaceAll("`([^`]+)`", "{@code $1}").replaceAll("\\s+", " ").trim
    }

    private def javadoc(
        spec: JsonNode,
        indent: String,
        fallback: String = "",
        params: List[(String, String)] = Nil,
        returns: Option[String] = None
    ): List[String] = {
      val text =
        Option(spec.get("description")).map(_.asText()).filter(_.nonEmpty).getOrElse(fallback).replace("*/", "*&#47;")
      val paragraphs = text.split("\\n\\s*\\n").iterator.map(_.trim).filter(_.nonEmpty).map(normalizeDoc).toList
      val lines      = mutable.ListBuffer(indent + "/**")
      paragraphs.zipWithIndex.foreach { case (paragraph, index) =>
        lines += s"$indent * ${if (index == 0) "" else "<p>"}$paragraph"
      }
      if (spec.path("experimental").asBoolean(false))
        lines += s"$indent * <p><b>Experimental:</b> this part of CDP may change without notice."
      params.foreach { case (name, description) =>
        lines += s"$indent * @param $name ${normalizeDoc(if (description.nonEmpty) description else "protocol value")}"
      }
      returns.foreach(value => lines += s"$indent * @return ${normalizeDoc(value)}")
      if (spec.path("deprecated").asBoolean(false))
        lines += s"$indent * @deprecated Deprecated by the Chromium DevTools Protocol."
      lines += s"$indent */"
      lines.toList
    }

    private def deprecated(spec: JsonNode, indent: String): List[String] =
      if (spec.path("deprecated").asBoolean(false)) List(s"${indent}@Deprecated") else Nil

    private def enumConstants(name: String, values: JsonNode, indent: String = "    ", spec: JsonNode): List[String] = {
      val lines = mutable.ListBuffer.from(javadoc(spec, indent, s"Wire values for $name."))
      lines ++= deprecated(spec, indent)
      lines ++= List(s"${indent}public static final class $name {", s"$indent    private $name() {}")
      val used = mutable.Set.empty[String]
      elements(values).foreach { valueNode =>
        val value = valueNode.asText()
        val raw   = value.replaceAll("[^A-Za-z0-9]", "_").toUpperCase.stripPrefix("_").stripSuffix("_") match {
          case "" => "EMPTY"
          case v  => v
        }
        val base      = if (raw.head.isDigit) s"_$raw" else raw
        var candidate = base
        var suffix    = 2
        while (used(candidate)) { candidate = s"${base}_$suffix"; suffix += 1 }
        used += candidate
        lines += s"$indent    public static final String $candidate = ${mapper.writeValueAsString(value)};"
      }
      lines += s"$indent}"
      lines.toList
    }

    private def model(
        name: String,
        domain: String,
        properties: JsonNode,
        spec: JsonNode,
        fallback: String
    ): List[String] = {
      val props = elements(properties)
      val lines = mutable.ListBuffer.from(javadoc(spec, "    ", fallback))
      lines ++= deprecated(spec, "    ")
      lines ++= List(
        s"    public static final class $name extends CdpObject {",
        s"        private $name(Map<String, Object> values) { super(values); }",
        s"        @Nullable public static $name fromMap(@Nullable Map<String, Object> values) {",
        s"            return values == null ? null : new $name(values);",
        "        }",
        "        public static Builder builder() { return new Builder(); }"
      )
      props.foreach { prop =>
        val javaType = resolve(domain, prop).javaType
        val accessor = ident(prop.path("name").asText())
        lines ++= javadoc(
          prop,
          "        ",
          s"Returns the ${prop.path("name").asText()} field.",
          returns = Some("the protocol field value")
        )
        lines ++= deprecated(prop, "        ")
        lines ++= List(
          s"        @Nullable public $javaType $accessor() {",
          s"            return ${decode(domain, prop, s"value(${mapper.writeValueAsString(prop.path("name").asText())})")};",
          "        }"
        )
        if (prop.has("enum"))
          lines ++= enumConstants(s"${cap(prop.path("name").asText())}Values", prop.path("enum"), "        ", prop)
      }
      lines ++= List(
        "        public static final class Builder {",
        "            private final Map<String, Object> values = new LinkedHashMap<>();"
      )
      props.foreach { prop =>
        val fieldName = prop.path("name").asText()
        val javaType  = resolve(domain, prop).javaType
        lines ++= javadoc(
          prop,
          "            ",
          s"Sets the $fieldName field.",
          List("value" -> "field value; null removes an optional value"),
          Some("this builder")
        )
        lines ++= deprecated(prop, "            ")
        lines ++= List(
          s"            public Builder ${ident(fieldName)}(@Nullable $javaType value) {",
          s"                if (value == null) values.remove(${mapper.writeValueAsString(fieldName)});",
          s"                else values.put(${mapper.writeValueAsString(fieldName)}, jsonValue(value));",
          "                return this;",
          "            }"
        )
      }
      lines += s"            public $name build() {"
      props.filterNot(_.path("optional").asBoolean(false)).foreach { prop =>
        val fieldName = prop.path("name").asText()
        lines += s"                if (!values.containsKey(${mapper.writeValueAsString(fieldName)})) throw new IllegalStateException(\"Missing required CDP field: $fieldName\");"
      }
      lines ++= List(s"                return new $name(values);", "            }", "        }", "    }")
      lines.toList
    }

    private def protocolSource(domain: String): String =
      if (browserDomains(domain))
        s"https://chromium.googlesource.com/chromium/src/+/refs/tags/$chromiumVersion/third_party/blink/public/devtools_protocol/domains/$domain.pdl"
      else s"https://chromium.googlesource.com/v8/v8/+/$v8Revision/include/js_protocol.pdl"

    def emitDomain(domainNode: JsonNode): String = {
      val domain   = domainNode.path("domain").asText()
      val commands = elements(domainNode.path("commands"))
      val events   = elements(domainNode.path("events"))
      val lines    = mutable.ListBuffer(
        "// GENERATED - do not edit. Run scripts/update-cdp-schema.sh.",
        s"package $javaPackage;",
        "",
        "import java.util.LinkedHashMap;",
        "import java.util.Map;",
        "import java.util.concurrent.CompletionStage;",
        "import java.util.function.Consumer;",
        "import javax.annotation.Nullable;",
        "import net.kurobako.cef4j.cdp.CdpClient;",
        "import net.kurobako.cef4j.cdp.CdpObject;",
        "import net.kurobako.cef4j.cdp.CdpSubscription;",
        ""
      )
      val docs = mutable.ListBuffer.from(javadoc(domainNode, "", s"Chrome DevTools Protocol $domain domain."))
      docs.insert(docs.size - 1, s" * @see <a href=\"${protocolSource(domain)}\">Pinned protocol source</a>")
      lines ++= docs
      lines ++= deprecated(domainNode, "")
      lines ++= List(
        "@SuppressWarnings({\"EscapedEntity\", \"JavaLangClash\", \"MissingSummary\", \"UnusedMethod\"})",
        s"public final class $domain {",
        s"    private $domain() {}",
        "    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }",
        "    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }"
      )
      elements(domainNode.path("types")).foreach { item =>
        if (item.path("type").asText() == "object" && item.path("properties").size() > 0)
          lines ++= model(modelName(domain, item.path("id").asText()), domain, item.path("properties"), item, "")
        else if (item.has("enum"))
          lines ++= enumConstants(modelName(domain, item.path("id").asText()), item.path("enum"), spec = item)
      }
      commands.foreach { command =>
        val operation = s"$domain.${command.path("name").asText()}"
        val prefix    = cap(command.path("name").asText())
        lines ++= model(s"${prefix}Params", domain, command.path("parameters"), command, s"Parameters for $operation.")
        lines ++= model(s"${prefix}Result", domain, command.path("returns"), command, s"Result of $operation.")
      }
      events.foreach { event =>
        val name = event.path("name").asText()
        lines ++= model(
          s"${cap(name)}Event",
          domain,
          event.path("parameters"),
          event,
          s"Payload of the $domain.$name event."
        )
      }
      lines ++= List(
        "    public static final class Client {",
        "        private final CdpClient client;",
        "        public Client(CdpClient client) { this.client = client; }"
      )
      commands.foreach { command =>
        val name          = command.path("name").asText()
        val prefix        = cap(name)
        val hasParameters = command.path("parameters").size() > 0
        lines ++= javadoc(
          command,
          "        ",
          s"Invokes $domain.$name.",
          if (hasParameters) List("params" -> "command parameters") else Nil,
          Some("a stage completing with the command result")
        )
        lines ++= deprecated(command, "        ")
        if (hasParameters)
          lines ++= List(
            s"        public CompletionStage<${prefix}Result> ${ident(name)}(${prefix}Params params) {",
            s"            return client.call(\"$domain.$name\", params, ${prefix}Result::fromMap);",
            "        }"
          )
        else
          lines ++= List(
            s"        public CompletionStage<${prefix}Result> ${ident(name)}() {",
            s"            return client.call(\"$domain.$name\", null, ${prefix}Result::fromMap);",
            "        }"
          )
      }
      events.foreach { event =>
        val name      = event.path("name").asText()
        val eventType = s"${cap(name)}Event"
        lines ++= javadoc(
          event,
          "        ",
          s"Subscribes to $domain.$name.",
          List("handler" -> "event callback"),
          Some("a removable subscription")
        )
        lines ++= deprecated(event, "        ")
        lines ++= List(
          s"        public CdpSubscription on${cap(name)}(Consumer<$eventType> handler) {",
          s"            return client.on(\"$domain.$name\", $eventType::fromMap, handler);",
          "        }"
        )
      }
      lines ++= List("    }", "}", "")
      lines.mkString("\n")
    }

    def emitDomains(domains: List[JsonNode]): String = {
      val lines = mutable.ListBuffer(
        "// GENERATED - do not edit. Run scripts/update-cdp-schema.sh.",
        s"package $javaPackage;",
        "",
        "import net.kurobako.cef4j.cdp.CdpClient;",
        "",
        "@SuppressWarnings(\"deprecation\")",
        "public final class CdpDomains {"
      )
      domains.foreach { domain =>
        val name  = domain.path("domain").asText()
        val field = ident(name.head.toLower +: name.tail)
        lines += s"    private final $name.Client $field;"
      }
      lines += "    public CdpDomains(CdpClient client) {"
      domains.foreach { domain =>
        val name  = domain.path("domain").asText()
        val field = ident(name.head.toLower +: name.tail)
        lines += s"        $field = new $name.Client(client);"
      }
      lines += "    }"
      domains.foreach { domain =>
        val name  = domain.path("domain").asText()
        val field = ident(name.head.toLower +: name.tail)
        lines += s"    public $name.Client $field() { return $field; }"
      }
      lines ++= List("}", "")
      lines.mkString("\n")
    }
  }

  private def elements(node: JsonNode): List[JsonNode] =
    if (node != null && node.isArray) node.elements().asScala.toList else Nil

  /** Match Python's json.dumps(indent=2, ensure_ascii=False), which was used by the original generator. */
  private def prettyJson(node: JsonNode, depth: Int = 0): String = {
    val indent = "  " * depth
    if (node.isObject) {
      val fields = node.fields().asScala.toList
      if (fields.isEmpty) "{}"
      else {
        val body = fields.map { entry =>
          s"${"  " * (depth + 1)}${mapper.writeValueAsString(entry.getKey)}: ${prettyJson(entry.getValue, depth + 1)}"
        }.mkString(",\n")
        s"{\n$body\n$indent}"
      }
    } else if (node.isArray) {
      val values = elements(node)
      if (values.isEmpty) "[]"
      else {
        val body = values.map(value => s"${"  " * (depth + 1)}${prettyJson(value, depth + 1)}").mkString(",\n")
        s"[\n$body\n$indent]"
      }
    } else mapper.writeValueAsString(node)
  }

  private def cleanDirectory(path: Path): Unit =
    if (Files.exists(path)) Files.walk(path).toScala(List).sortBy(_.getNameCount).reverse.foreach(Files.delete)

  private def requiredProperty(properties: Properties, name: String): String =
    Option(
      properties.getProperty(name)
    ).filter(_.nonEmpty).getOrElse(throw IllegalArgumentException(s"Missing property: $name"))

  private def parseArgs(args: List[String]): Config = args.foldLeft(Config()) { (cfg, arg) =>
    arg match {
      case s"--browser-schema=$value"    => cfg.copy(browserSchema = Paths.get(value))
      case s"--javascript-schema=$value" => cfg.copy(javascriptSchema = Paths.get(value))
      case s"--schema-metadata=$value"   => cfg.copy(schemaMetadata = Paths.get(value))
      case s"--out-java=$value"          => cfg.copy(outJava = Paths.get(value))
      case s"--out-resources=$value"     => cfg.copy(outResources = Paths.get(value))
      case s"--java-package=$value"      => cfg.copy(javaPackage = value)
      case other                         => throw IllegalArgumentException(s"Unknown argument: $other")
    }
  }
}
