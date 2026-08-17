package net.kurobako.cef4j.codegen.cdp

import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.security.MessageDigest
import scala.annotation.tailrec
import scala.jdk.CollectionConverters.*

import net.kurobako.cef4j.codegen.AtomicFiles
import net.kurobako.cef4j.codegen.Banners
import net.kurobako.cef4j.codegen.FileSystem
import upickle.default.read
import upickle.default.write

/** Generates cef4j's map-backed Java CDP API from pinned Chromium protocol schemas. */
object Main {
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
      cefVersion: Option[String] = None,
      schemaCache: Option[Path] = None,
      outJava: Path = Paths.get("."),
      outResources: Path = Paths.get("."),
      javaPackage: String = "net.kurobako.cef4j.cdp.generated"
  )

  private case class Resolved(javaType: String, kind: String)

  def main(args: Array[String]): Unit = {
    val parsed = parseArgs(args.toList)
    val config = (parsed.cefVersion, parsed.schemaCache) match {
      case (Some(cefVersion), Some(cache)) =>
        val schema = SchemaFetcher.fetch(cefVersion, cache)
        parsed.copy(
          browserSchema = schema.browser,
          javascriptSchema = schema.javascript,
          schemaMetadata = schema.metadata
        )
      case (None, None) => parsed
      case _            => throw IllegalArgumentException("--cef-version and --schema-cache must be specified together")
    }
    generate(config)
  }

  private def generate(cfg: Config): Unit = {
    val browser = readProtocol(cfg.browserSchema)
    val js      = readProtocol(cfg.javascriptSchema)
    val props   = Files.readAllLines(cfg.schemaMetadata, StandardCharsets.UTF_8).asScala.toList
      .map(_.trim)
      .filter(line => line.nonEmpty && !line.startsWith("#") && !line.startsWith("!"))
      .map { line =>
        val separator = line.indexWhere(char => char == '=' || char == ':')
        if (separator < 1) throw IllegalArgumentException(s"Invalid schema property: $line")
        line.substring(0, separator).trim -> line.substring(separator + 1).trim
      }.toMap

    val chromiumVersion = requiredProperty(props, "chromium.version")
    val v8Revision      = requiredProperty(props, "v8.revision")
    val protocol        = Protocol(browser.version, browser.domains ++ js.domains)

    val schemaBytes = (write(protocol, indent = 2) + "\n").getBytes(StandardCharsets.UTF_8)
    val fingerprint = MessageDigest.getInstance("SHA-256").digest(schemaBytes).map("%02x".format(_)).mkString
    val output      = cfg.outJava.resolve(cfg.javaPackage.replace('.', '/'))
    val resources   = cfg.outResources.resolve("META-INF/cef4j/cdp")
    cleanDirectory(output)
    FileSystem.createDirectories(output)
    FileSystem.createDirectories(resources)
    AtomicFiles.writeBytes(resources.resolve("protocol.json"), schemaBytes)
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

    val domains        = protocol.domains
    val browserDomains = browser.domains.map(_.domain).toSet
    val types          = domains.flatMap(domain =>
      domain.types.map(item =>
        (domain.domain, item.id.getOrElse(throw IllegalArgumentException("CDP type without id"))) -> item
      )
    ).toMap
    val banners = cfg.cefVersion
      .map(Banners.forCefVersion)
      .getOrElse(Banners.forCommand("mvn generate-sources -pl cef4j-platform"))
    val emitter = Emitter(cfg.javaPackage, chromiumVersion, v8Revision, browserDomains, types, banners)
    domains.foreach(domain =>
      AtomicFiles.writeString(output.resolve(s"${domain.domain}.java"), emitter.emitDomain(domain))
    )
    AtomicFiles.writeString(output.resolve("CdpDomains.java"), emitter.emitDomains(domains))
    println(s"cef4j CDP codegen complete: ${domains.size} domains for Chromium $chromiumVersion")
  }

  private final class Emitter(
      javaPackage: String,
      chromiumVersion: String,
      v8Revision: String,
      browserDomains: Set[String],
      types: Map[(String, String), Item],
      banners: Banners
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

    private def resolve(domain: String, spec: Item, seen: Set[(String, String)] = Set.empty): Resolved =
      spec.reference match {
        case Some(ref) =>
          val parts = ref.split("\\.", 2)
          val key   = if (parts.length == 2) parts(0) -> parts(1) else domain -> parts(0)
          types.get(key) match {
            case None                 => Resolved("Object", "any")
            case Some(_) if seen(key) => Resolved(s"${key._1}.${modelName(key._1, key._2)}", "model")
            case Some(target) if target.kind.contains("object") && target.properties.nonEmpty =>
              Resolved(s"${key._1}.${modelName(key._1, key._2)}", "model")
            case Some(target) => resolve(key._1, target, seen + key)
          }
        case None if spec.kind.contains("array") =>
          val inner = resolve(domain, spec.items.getOrElse(Item()), seen)
          Resolved(s"java.util.List<${inner.javaType}>", s"list:${inner.kind}:${inner.javaType}")
        case None =>
          spec.kind.getOrElse("any") match {
            case "string" | "binary" => Resolved("String", "string")
            case "integer"           => Resolved("Long", "integer")
            case "number"            => Resolved("Double", "number")
            case "boolean"           => Resolved("Boolean", "boolean")
            case "object"            => Resolved("java.util.Map<String, Object>", "object")
            case _                   => Resolved("Object", "any")
          }
      }

    private def decode(
        domain: String,
        spec: Item,
        source: String,
        depth: Int = 0,
        seen: Set[(String, String)] = Set.empty
    ): String =
      spec.reference match {
        case Some(reference) =>
          val parts = reference.split("\\.", 2)
          val key   = if (parts.length == 2) parts(0) -> parts(1) else domain -> parts(0)
          types.get(key) match {
            case None                                                                         => source
            case Some(target) if target.kind.contains("object") && target.properties.nonEmpty =>
              s"${resolve(domain, spec).javaType}.fromMap(objectMap($source))"
            case Some(_) if seen(key) => source
            case Some(target)         => decode(key._1, target, source, depth, seen + key)
          }
        case None =>
          resolve(domain, spec).kind match {
            case "string"                         => s"(String) $source"
            case "boolean"                        => s"(Boolean) $source"
            case "integer"                        => s"numberAsLong($source)"
            case "number"                         => s"numberAsDouble($source)"
            case "object"                         => s"objectMap($source)"
            case kind if kind.startsWith("list:") =>
              val variable = s"element$depth"
              s"list($source, $variable -> ${decode(domain, spec.items.getOrElse(Item()), variable, depth + 1, seen)})"
            case _ => source
          }
      }

    private def normalizeDoc(text: String): String = {
      val escaped = text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
        .replace("\"", "&quot;").replace("'", "&#x27;").replace("@", "&#64;").replace("\n", " ")
      escaped.replaceAll("`([^`]+)`", "{@code $1}").replaceAll("\\s+", " ").trim
    }

    private def javadoc(
        spec: Item,
        indent: String,
        fallback: String,
        params: List[(String, String)],
        returns: Option[String]
    ): List[String] = {
      val text       = spec.description.filter(_.nonEmpty).getOrElse(fallback).replace("*/", "*&#47;")
      val paragraphs = text.split("\\n\\s*\\n").iterator.map(_.trim).filter(_.nonEmpty).map(normalizeDoc).toList
      List(indent + "/**") ++
        paragraphs.zipWithIndex.map { case (paragraph, index) =>
          s"$indent * ${if (index == 0) "" else "<p>"}$paragraph"
        } ++
        Option.when(spec.experimental)(
          s"$indent * <p><b>Experimental:</b> this part of CDP may change without notice."
        ) ++
        params.map { case (name, description) =>
          s"$indent * @param $name ${normalizeDoc(if (description.nonEmpty) description else "protocol value")}"
        } ++
        returns.map(value => s"$indent * @return ${normalizeDoc(value)}") ++
        Option.when(spec.deprecated)(s"$indent * @deprecated Deprecated by the Chromium DevTools Protocol.") ++
        List(s"$indent */")
    }

    private def deprecated(spec: Item, indent: String): List[String] =
      if (spec.deprecated) List(s"${indent}@Deprecated") else Nil

    private def enumConstants(name: String, values: List[String], indent: String = "    ", spec: Item): List[String] = {
      @tailrec
      def available(base: String, used: Set[String], suffix: Int = 1): String = {
        val candidate = if (suffix == 1) base else s"${base}_$suffix"
        if (used(candidate)) available(base, used, suffix + 1) else candidate
      }

      val (_, constants) = values.foldLeft(Set.empty[String] -> List.empty[String]) { case ((used, lines), value) =>
        val raw = value.replaceAll("[^A-Za-z0-9]", "_").toUpperCase.stripPrefix("_").stripSuffix("_") match {
          case "" => "EMPTY"
          case v  => v
        }
        val base      = if (raw.head.isDigit) s"_$raw" else raw
        val candidate = available(base, used)
        (used + candidate) -> (lines :+ s"$indent    public static final String $candidate = ${jsonString(value)};")
      }
      javadoc(spec, indent, s"Wire values for $name.", Nil, None) ++
        deprecated(spec, indent) ++
        List(s"${indent}public static final class $name {", s"$indent    private $name() {}") ++
        constants ++
        List(s"$indent}")
    }

    private def model(
        name: String,
        domain: String,
        properties: List[Item],
        spec: Item,
        fallback: String
    ): List[String] = {
      val props     = properties
      val accessors = props.flatMap { prop =>
        val javaType  = resolve(domain, prop).javaType
        val fieldName = prop.name.getOrElse(throw IllegalArgumentException("CDP property without name"))
        val accessor  = ident(fieldName)
        javadoc(
          prop,
          "        ",
          s"Returns the $fieldName field.",
          Nil,
          Some("the protocol field value")
        ) ++
          deprecated(prop, "        ") ++
          List(
            s"        @Nullable public $javaType $accessor() {",
            s"            return ${decode(domain, prop, s"value(${jsonString(fieldName)})")};",
            "        }"
          ) ++
          Option.when(prop.enumValues.nonEmpty)(
            enumConstants(s"${cap(fieldName)}Values", prop.enumValues, "        ", prop)
          ).toList.flatten
      }
      val setters = props.flatMap { prop =>
        val fieldName = prop.name.getOrElse(throw IllegalArgumentException("CDP property without name"))
        val javaType  = resolve(domain, prop).javaType
        javadoc(
          prop,
          "            ",
          s"Sets the $fieldName field.",
          List("value" -> "field value; null removes an optional value"),
          Some("this builder")
        ) ++
          deprecated(prop, "            ") ++
          List(
            s"            public Builder ${ident(fieldName)}(@Nullable $javaType value) {",
            s"                if (value == null) values.remove(${jsonString(fieldName)});",
            s"                else values.put(${jsonString(fieldName)}, jsonValue(value));",
            "                return this;",
            "            }"
          )
      }
      val required = props.filterNot(_.optional).map { prop =>
        val fieldName = prop.name.getOrElse(throw IllegalArgumentException("CDP property without name"))
        s"                if (!values.containsKey(${jsonString(fieldName)})) throw new IllegalStateException(\"Missing required CDP field: $fieldName\");"
      }
      javadoc(spec, "    ", fallback, Nil, None) ++
        deprecated(spec, "    ") ++
        List(
          s"    public static final class $name extends CdpObject {",
          s"        private $name(Map<String, Object> values) { super(values); }",
          s"        @Nullable public static $name fromMap(@Nullable Map<String, Object> values) {",
          s"            return values == null ? null : new $name(values);",
          "        }",
          "        public static Builder builder() { return new Builder(); }"
        ) ++
        accessors ++
        List(
          "        public static final class Builder {",
          "            private final Map<String, Object> values = new LinkedHashMap<>();"
        ) ++
        setters ++
        List(s"            public $name build() {") ++
        required ++
        List(s"                return new $name(values);", "            }", "        }", "    }")
    }

    private def protocolSource(domain: String): String =
      if (browserDomains(domain))
        s"https://chromium.googlesource.com/chromium/src/+/refs/tags/$chromiumVersion/third_party/blink/public/devtools_protocol/domains/$domain.pdl"
      else s"https://chromium.googlesource.com/v8/v8/+/$v8Revision/include/js_protocol.pdl"

    def emitDomain(domainNode: Domain): String = {
      val domain   = domainNode.domain
      val commands = domainNode.commands
      val events   = domainNode.events
      val header   = List(
        banners.java,
        s"package $javaPackage;",
        "",
        "import java.util.LinkedHashMap;",
        "import java.util.Map;",
        "import java.util.concurrent.CompletionStage;",
        "import java.util.function.Consumer;",
        "import javax.annotation.Nullable;",
        Banners.javaAnnotationImport,
        "import net.kurobako.cef4j.cdp.CdpClient;",
        "import net.kurobako.cef4j.cdp.CdpObject;",
        "import net.kurobako.cef4j.cdp.CdpSubscription;",
        ""
      )
      val domainSpec = Item(
        description = domainNode.description,
        experimental = domainNode.experimental,
        deprecated = domainNode.deprecated
      )
      val rawDocs = javadoc(domainSpec, "", s"Chrome DevTools Protocol $domain domain.", Nil, None)
      val docs    = rawDocs.init ++
        List(s" * @see <a href=\"${protocolSource(domain)}\">Pinned protocol source</a>") ++
        rawDocs.takeRight(1)
      val classHeader = List(
        banners.javaAnnotations("EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"),
        s"public final class $domain {",
        s"    private $domain() {}",
        "    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }",
        "    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }"
      )
      val types = domainNode.types.flatMap { item =>
        val id = item.id.getOrElse(throw IllegalArgumentException("CDP type without id"))
        if (item.kind.contains("object") && item.properties.nonEmpty)
          model(modelName(domain, id), domain, item.properties, item, "")
        else if (item.enumValues.nonEmpty)
          enumConstants(modelName(domain, id), item.enumValues, spec = item)
        else Nil
      }
      val commandModels = commands.flatMap { command =>
        val commandName = command.name.getOrElse(throw IllegalArgumentException("CDP command without name"))
        val operation   = s"$domain.$commandName"
        val prefix      = cap(commandName)
        model(s"${prefix}Params", domain, command.parameters, command, s"Parameters for $operation.") ++
          model(s"${prefix}Result", domain, command.returns, command, s"Result of $operation.")
      }
      val eventModels = events.flatMap { event =>
        val name = event.name.getOrElse(throw IllegalArgumentException("CDP event without name"))
        model(
          s"${cap(name)}Event",
          domain,
          event.parameters,
          event,
          s"Payload of the $domain.$name event."
        )
      }
      val clientHeader = List(
        "    public static final class Client {",
        "        private final CdpClient client;",
        "        public Client(CdpClient client) { this.client = client; }"
      )
      val commandMethods = commands.flatMap { command =>
        val name          = command.name.getOrElse(throw IllegalArgumentException("CDP command without name"))
        val prefix        = cap(name)
        val hasParameters = command.parameters.nonEmpty
        val allOptional   = hasParameters && command.parameters.forall(_.optional)
        val docs          = javadoc(
          command,
          "        ",
          s"Invokes $domain.$name.",
          if (hasParameters) List("params" -> "command parameters") else Nil,
          Some("a stage completing with the command result")
        )
        val invocation =
          if (hasParameters)
            List(
              s"        public CompletionStage<${prefix}Result> ${ident(name)}(${prefix}Params params) {",
              s"            return client.call(\"$domain.$name\", params, ${prefix}Result::fromMap);",
              "        }"
            )
          else
            List(
              s"        public CompletionStage<${prefix}Result> ${ident(name)}() {",
              s"            return client.call(\"$domain.$name\", null, ${prefix}Result::fromMap);",
              "        }"
            )
        val defaultInvocation = Option.when(allOptional)(
          javadoc(
            command,
            "        ",
            s"Invokes $domain.$name with default parameters.",
            Nil,
            Some("a stage completing with the command result")
          ) ++
            deprecated(command, "        ") ++
            List(
              s"        public CompletionStage<${prefix}Result> ${ident(name)}() {",
              s"            return ${ident(name)}(${prefix}Params.builder().build());",
              "        }"
            )
        ).toList.flatten
        docs ++ deprecated(command, "        ") ++ invocation ++ defaultInvocation
      }
      val eventMethods = events.flatMap { event =>
        val name      = event.name.getOrElse(throw IllegalArgumentException("CDP event without name"))
        val eventType = s"${cap(name)}Event"
        javadoc(
          event,
          "        ",
          s"Subscribes to $domain.$name.",
          List("handler" -> "event callback"),
          Some("a removable subscription")
        ) ++
          deprecated(event, "        ") ++
          List(
            s"        public CdpSubscription on${cap(name)}(Consumer<$eventType> handler) {",
            s"            return client.on(\"$domain.$name\", $eventType::fromMap, handler);",
            "        }"
          )
      }
      (
        header ++ docs ++ deprecated(domainSpec, "") ++ classHeader ++ types ++ commandModels ++ eventModels ++
          clientHeader ++ commandMethods ++ eventMethods ++ List("    }", "}", "")
      ).mkString("\n")
    }

    def emitDomains(domains: List[Domain]): String = {
      val header = List(
        banners.java,
        s"package $javaPackage;",
        "",
        Banners.javaAnnotationImport,
        "import net.kurobako.cef4j.cdp.CdpClient;",
        "",
        banners.javaAnnotations("deprecation"),
        "public final class CdpDomains {"
      )
      val fields = domains.map { domain =>
        val name  = domain.domain
        val field = ident(name.head.toLower +: name.tail)
        s"    private final $name.Client $field;"
      }
      val initializers = domains.map { domain =>
        val name  = domain.domain
        val field = ident(name.head.toLower +: name.tail)
        s"        $field = new $name.Client(client);"
      }
      val accessors = domains.map { domain =>
        val name  = domain.domain
        val field = ident(name.head.toLower +: name.tail)
        s"    public $name.Client $field() { return $field; }"
      }
      (
        header ++ fields ++ List("    public CdpDomains(CdpClient client) {") ++ initializers ++ List("    }") ++
          accessors ++ List("}", "")
      ).mkString("\n")
    }
  }

  private def readProtocol(path: Path): Protocol =
    if (path.getFileName.toString.endsWith(".pdl")) PdlParser.parse(path)
    else read[Protocol](Files.readString(path, StandardCharsets.UTF_8))

  private def jsonString(value: String): String = ujson.Str(value).render()

  private def cleanDirectory(path: Path): Unit =
    FileSystem.deleteTree(path)

  private def requiredProperty(properties: Map[String, String], name: String): String =
    properties.get(name).filter(_.nonEmpty).getOrElse(throw IllegalArgumentException(s"Missing property: $name"))

  private def parseArgs(args: List[String]): Config = args.foldLeft(Config()) { (cfg, arg) =>
    arg match {
      case s"--browser-schema=$value"    => cfg.copy(browserSchema = Paths.get(value))
      case s"--javascript-schema=$value" => cfg.copy(javascriptSchema = Paths.get(value))
      case s"--schema-metadata=$value"   => cfg.copy(schemaMetadata = Paths.get(value))
      case s"--cef-version=$value"       => cfg.copy(cefVersion = Some(value))
      case s"--schema-cache=$value"      => cfg.copy(schemaCache = Some(Paths.get(value)))
      case s"--out-java=$value"          => cfg.copy(outJava = Paths.get(value))
      case s"--out-resources=$value"     => cfg.copy(outResources = Paths.get(value))
      case s"--java-package=$value"      => cfg.copy(javaPackage = value)
      case other                         => throw IllegalArgumentException(s"Unknown argument: $other")
    }
  }
}
