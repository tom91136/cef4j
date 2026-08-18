package net.kurobako.cef4j.codegen.cdp

import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import scala.annotation.tailrec
import scala.jdk.CollectionConverters.*

import PDL.TypeExpr
import net.kurobako.cef4j.codegen.AtomicFiles
import net.kurobako.cef4j.codegen.Banners
import net.kurobako.cef4j.codegen.FileSystem

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
      browserSchema: Path = Paths.get("browser_protocol.pdl"),
      javascriptSchema: Path = Paths.get("js_protocol.pdl"),
      schemaMetadata: Path = Paths.get("schema.properties"),
      cefVersion: Option[String] = None,
      schemaCache: Option[Path] = None,
      outJava: Path = Paths.get("."),
      outResources: Path = Paths.get("."),
      javaPackage: String = "net.kurobako.cef4j.cdp.generated"
  )

  private case class Resolved(javaType: String, kind: String)

  private final case class Doc(description: Option[String], experimental: Boolean, deprecated: Boolean)

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
    val protocol        = PDL.Protocol(browser.version, browser.domains ++ js.domains)

    val output    = cfg.outJava.resolve(cfg.javaPackage.replace('.', '/'))
    val resources = cfg.outResources.resolve("META-INF/cef4j/cdp")
    cleanDirectory(output)
    FileSystem.createDirectories(output)
    FileSystem.createDirectories(resources)
    AtomicFiles.writeString(
      resources.resolve("schema.properties"),
      s"""chromium.version=$chromiumVersion
         |v8.revision=$v8Revision
         |browser.source=https://chromium.googlesource.com/chromium/src/+/refs/tags/$chromiumVersion/third_party/blink/public/devtools_protocol/
         |javascript.source=https://chromium.googlesource.com/v8/v8/+/$v8Revision/include/js_protocol.pdl
         |source.license=BSD-3-Clause
         |""".stripMargin
    )

    val domains        = protocol.domains
    val browserDomains = browser.domains.map(_.name).toSet
    val types          = domains.flatMap(domain =>
      domain.types.map(decl => (domain.name, decl.name) -> decl)
    ).toMap
    val banners = cfg.cefVersion
      .map(Banners.forCefVersion)
      .getOrElse(Banners.forCommand("mvn generate-sources -pl cef4j-platform"))
    val emitter = Emitter(cfg.javaPackage, chromiumVersion, v8Revision, browserDomains, types, banners)
    domains.foreach(domain =>
      AtomicFiles.writeString(output.resolve(s"${domain.name}.java"), emitter.emitDomain(domain))
    )
    AtomicFiles.writeString(output.resolve("CdpDomains.java"), emitter.emitDomains(domains))
    println(s"cef4j CDP codegen complete: ${domains.size} domains for Chromium $chromiumVersion")
  }

  private final class Emitter(
      javaPackage: String,
      chromiumVersion: String,
      v8Revision: String,
      browserDomains: Set[String],
      types: Map[(String, String), PDL.Decl.Type],
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

    private def isModel(decl: PDL.Decl.Type): Boolean = decl.body match {
      case Some(PDL.TypeBody.Object(properties)) => properties.nonEmpty
      case _                                     => false
    }

    private def isEnum(decl: PDL.Decl.Type): Boolean = decl.body match {
      case Some(PDL.TypeBody.Enum(_)) => true
      case _                          => false
    }

    private def taggedAlias(decl: PDL.Decl.Type): Option[TypeExpr] = decl.body match {
      case None =>
        decl.alias match {
          case TypeExpr.Str | TypeExpr.Integer | TypeExpr.Number => Some(decl.alias)
          case _                                                 => None
        }
      case _ => None
    }

    private def taggedKind(alias: TypeExpr): String = alias match {
      case TypeExpr.Str     => "string"
      case TypeExpr.Integer => "integer"
      case TypeExpr.Number  => "number"
      case _                => "any"
    }

    private def primitiveOf(kind: String): Option[String] = kind match {
      case "integer" => Some("long")
      case "number"  => Some("double")
      case "boolean" => Some("boolean")
      case _         => None
    }

    private def enumValues(expr: TypeExpr): List[String] = expr match {
      case TypeExpr.InlineEnum(values) => values
      case _                           => Nil
    }

    private def typeKey(domain: String, ref: TypeExpr.Ref): (String, String) =
      ref.domain.fold(domain -> ref.name)(_ -> ref.name)

    private def kindOf(domain: String, expr: TypeExpr): String = expr match {
      case TypeExpr.InlineEnum(_) => "enum"
      case other                  => resolve(domain, other).kind
    }

    private def resolve(domain: String, expr: TypeExpr, seen: Set[(String, String)] = Set.empty): Resolved =
      expr match {
        case ref: TypeExpr.Ref =>
          val key = typeKey(domain, ref)
          types.get(key) match {
            case None                            => Resolved("Object", "any")
            case Some(_) if seen(key)            => Resolved(s"${key._1}.${modelName(key._1, key._2)}", "model")
            case Some(target) if isModel(target) => Resolved(s"${key._1}.${modelName(key._1, key._2)}", "model")
            case Some(target) if isEnum(target)  => Resolved(s"${key._1}.${modelName(key._1, key._2)}", "enum")
            case Some(target)                    =>
              taggedAlias(target) match {
                case Some(alias) =>
                  Resolved(s"${key._1}.${modelName(key._1, key._2)}", s"tagged:${taggedKind(alias)}")
                case None => resolve(key._1, target.alias, seen + key)
              }
          }
        case TypeExpr.ArrayOf(element) =>
          val inner = resolve(domain, element, seen)
          Resolved(s"java.util.List<${inner.javaType}>", s"list:${inner.kind}")
        case TypeExpr.Str | TypeExpr.Binary => Resolved("String", "string")
        case TypeExpr.Integer               => Resolved("Long", "integer")
        case TypeExpr.Number                => Resolved("Double", "number")
        case TypeExpr.Boolean               => Resolved("Boolean", "boolean")
        case TypeExpr.Obj                   => Resolved("java.util.Map<String, Object>", "object")
        case TypeExpr.AnyType               => Resolved("Object", "any")
        case TypeExpr.InlineEnum(_)         => Resolved("String", "string")
      }

    private def decode(
        domain: String,
        expr: TypeExpr,
        source: String,
        ctx: String,
        depth: Int = 0,
        seen: Set[(String, String)] = Set.empty
    ): String =
      expr match {
        case ref: TypeExpr.Ref =>
          val key = typeKey(domain, ref)
          types.get(key) match {
            case None                            => source
            case Some(target) if isModel(target) =>
              s"$source == null ? null : ${resolve(domain, expr).javaType}.fromMap(java.util.Objects.requireNonNull(${ctx}objectMap($source)))"
            case Some(_) if seen(key)           => source
            case Some(target) if isEnum(target) =>
              s"$source == null ? null : ${resolve(domain, expr).javaType}.of((String) $source)"
            case Some(target) =>
              taggedAlias(target) match {
                case Some(TypeExpr.Str) =>
                  s"$source == null ? null : new ${resolve(domain, expr).javaType}((String) $source)"
                case Some(TypeExpr.Integer) =>
                  s"$source == null ? null : new ${resolve(domain, expr).javaType}(((Number) $source).longValue())"
                case Some(TypeExpr.Number) =>
                  s"$source == null ? null : new ${resolve(domain, expr).javaType}(((Number) $source).doubleValue())"
                case _ => decode(key._1, target.alias, source, ctx, depth, seen + key)
              }
          }
        case TypeExpr.ArrayOf(element) =>
          val variable = s"element$depth"
          s"${ctx}list($source, $variable -> ${strictExpr(domain, element, variable, depth + 1, seen)})"
        case TypeExpr.Str | TypeExpr.Binary => s"(String) $source"
        case TypeExpr.Boolean               => s"(Boolean) $source"
        case TypeExpr.Integer               => s"CdpObject.numberAsLong($source)"
        case TypeExpr.Number                => s"CdpObject.numberAsDouble($source)"
        case TypeExpr.Obj                   => s"${ctx}objectMap($source)"
        case TypeExpr.InlineEnum(_)         => source
        case TypeExpr.AnyType               => source
      }

    private def strictExpr(
        domain: String,
        expr: TypeExpr,
        source: String,
        depth: Int = 0,
        seen: Set[(String, String)] = Set.empty
    ): String =
      expr match {
        case ref: TypeExpr.Ref =>
          val key = typeKey(domain, ref)
          types.get(key) match {
            case None                            => source
            case Some(_) if seen(key)            => source
            case Some(target) if isModel(target) =>
              s"java.util.Objects.requireNonNull(${resolve(domain, expr).javaType}.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap($source))))"
            case Some(target) if isEnum(target) =>
              s"${resolve(domain, expr).javaType}.of((String) $source)"
            case Some(target) =>
              taggedAlias(target) match {
                case Some(TypeExpr.Str) =>
                  s"new ${resolve(domain, expr).javaType}((String) $source)"
                case Some(TypeExpr.Integer) =>
                  s"new ${resolve(domain, expr).javaType}(((Number) $source).longValue())"
                case Some(TypeExpr.Number) =>
                  s"new ${resolve(domain, expr).javaType}(((Number) $source).doubleValue())"
                case _ => strictExpr(key._1, target.alias, source, depth, seen + key)
              }
          }
        case TypeExpr.ArrayOf(element) =>
          val variable = s"element$depth"
          s"CdpObject.requireList($source, $variable -> ${strictExpr(domain, element, variable, depth + 1, seen)})"
        case TypeExpr.Str | TypeExpr.Binary => s"(String) $source"
        case TypeExpr.Boolean               => s"(Boolean) $source"
        case TypeExpr.Integer               => s"((Number) $source).longValue()"
        case TypeExpr.Number                => s"((Number) $source).doubleValue()"
        case TypeExpr.Obj                   => s"java.util.Objects.requireNonNull(CdpObject.objectMap($source))"
        case TypeExpr.AnyType               => source
        case TypeExpr.InlineEnum(_)         => source
      }

    private def strictFieldDecode(domain: String, owner: String, prop: PDL.SubItem, source: String): String =
      prop.typeExpr match {
        case TypeExpr.InlineEnum(_) =>
          s"$owner.${cap(prop.name)}Values.of((String) $source)"
        case other => strictExpr(domain, other, source)
      }

    private def normalizeDoc(text: String): String = {
      val escaped = text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
        .replace("\"", "&quot;").replace("'", "&#x27;").replace("@", "&#64;").replace("\n", " ")
      escaped.replaceAll("`([^`]+)`", "{@code $1}").replaceAll("\\s+", " ").trim
    }

    private def javadoc(
        doc: Doc,
        indent: String,
        fallback: String,
        params: List[(String, String)],
        returns: Option[String]
    ): List[String] = {
      val text       = doc.description.filter(_.nonEmpty).getOrElse(fallback).replace("*/", "*&#47;")
      val paragraphs = text.split("\\n\\s*\\n").iterator.map(_.trim).filter(_.nonEmpty).map(normalizeDoc).toList
      List(indent + "/**") ++
        paragraphs.zipWithIndex.map { case (paragraph, index) =>
          s"$indent * ${if (index == 0) "" else "<p>"}$paragraph"
        } ++
        Option.when(doc.experimental)(
          s"$indent * <p><b>Experimental:</b> this part of CDP may change without notice."
        ) ++
        params.map { case (name, description) =>
          s"$indent * @param $name ${normalizeDoc(if (description.nonEmpty) description else "protocol value")}"
        } ++
        returns.map(value => s"$indent * @return ${normalizeDoc(value)}") ++
        Option.when(doc.deprecated)(s"$indent * @deprecated Deprecated by the Chromium DevTools Protocol.") ++
        List(s"$indent */")
    }

    private def deprecated(doc: Doc, indent: String): List[String] =
      if (doc.deprecated) List(s"${indent}@Deprecated") else Nil

    private def enumConstant(value: String, used: Set[String]): String = {
      @tailrec
      def available(base: String, suffix: Int): String = {
        val candidate = if (suffix == 1) base else s"${base}_$suffix"
        if (used(candidate)) available(base, suffix + 1) else candidate
      }

      val raw = value.replaceAll("[^A-Za-z0-9]", "_").toUpperCase.stripPrefix("_").stripSuffix("_") match {
        case "" => "EMPTY"
        case v  => v
      }
      val base = if (raw.head.isDigit) s"_$raw" else raw
      available(base, 1)
    }

    private def enumType(name: String, values: List[String], doc: Doc, indent: String): List[String] = {
      val (_, constants) = values.foldLeft(Set.empty[String] -> List.empty[String]) { case ((used, lines), value) =>
        val candidate = enumConstant(value, used)
        (used + candidate) -> (lines :+ s"$indent    $candidate(${jsonString(value)})")
      }
      val constantLines =
        if (constants.isEmpty) Nil
        else constants.init.map(_ + ",") ++ List(constants.last + ";")
      javadoc(doc, indent, s"Wire values for $name.", Nil, None) ++
        deprecated(doc, indent) ++
        List(s"${indent}public enum $name implements CdpValue<String> {") ++
        constantLines ++
        List(
          s"$indent    public final String value;",
          s"$indent    $name(String value) { this.value = value; }",
          s"$indent    @Nonnull public String value() { return value; }",
          s"$indent    public static $name of(@Nonnull String value) {",
          s"$indent        for ($name constant : values()) {",
          s"$indent            if (constant.value.equals(value)) return constant;",
          s"$indent        }",
          s"$indent        throw new IllegalArgumentException(\"Unknown $name value: \" + value);",
          s"$indent    }",
          s"$indent}"
        )
    }

    private def taggedClass(name: String, alias: TypeExpr, doc: Doc, indent: String): List[String] = {
      val (typeName, valueType, generic, compare, hash, show) = alias match {
        case TypeExpr.Str =>
          ("String", "String", "CdpValue<String>", s"value.equals((($name) other).value)", "value.hashCode()", "value")
        case TypeExpr.Integer =>
          ("long", "Long", "CdpValue<Long>", s"value == (($name) other).value", "Long.hashCode(value)", "value")
        case TypeExpr.Number =>
          (
            "double",
            "Double",
            "CdpValue<Double>",
            s"Double.compare(value, (($name) other).value) == 0",
            "Double.hashCode(value)",
            "value"
          )
        case _ =>
          ("String", "String", "CdpValue<String>", s"value.equals((($name) other).value)", "value.hashCode()", "value")
      }
      val constructor = alias match {
        case TypeExpr.Str => "java.util.Objects.requireNonNull(value)"
        case _            => "value"
      }
      val paramAnno = if (typeName == "String") "@Nonnull " else ""
      javadoc(doc, indent, s"Tagged $typeName wire value for $name.", Nil, None) ++
        deprecated(doc, indent) ++
        List(
          s"${indent}public static final class $name implements $generic {",
          s"$indent    public final $typeName value;",
          s"$indent    public $name(${paramAnno}$typeName value) { this.value = $constructor; }",
          s"$indent    @Nonnull public $valueType value() { return value; }",
          s"$indent    @Override public boolean equals(Object other) {",
          s"$indent        if (this == other) return true;",
          s"$indent        if (!(other instanceof $name)) return false;",
          s"$indent        return $compare;",
          s"$indent    }",
          s"$indent    @Override public int hashCode() { return $hash; }",
          s"$indent    @Override public String toString() { return \"$name(\" + $show + \")\"; }",
          s"$indent}"
        )
    }

    private def rawJavaType(domain: String, owner: String, prop: PDL.SubItem): String =
      prop.typeExpr match {
        case TypeExpr.InlineEnum(_) => s"$owner.${cap(prop.name)}Values"
        case other                  => resolve(domain, other).javaType
      }

    private def decodeField(domain: String, owner: String, prop: PDL.SubItem, source: String, ctx: String): String =
      prop.typeExpr match {
        case TypeExpr.InlineEnum(_) =>
          s"$source == null ? null : $owner.${cap(prop.name)}Values.of((String) $source)"
        case other => decode(domain, other, source, ctx)
      }

    private def accessor(domain: String, owner: String, prop: PDL.SubItem): List[String] = {
      val fieldName                = prop.name
      val accessor                 = ident(fieldName)
      val optional                 = prop.optional
      val kind                     = kindOf(domain, prop.typeExpr)
      val rawJava                  = rawJavaType(domain, owner, prop)
      val source                   = s"raw(${jsonString(fieldName)})"
      val decoded                  = decodeField(domain, owner, prop, source, "")
      val (typeName, body, prefix) =
        if (!optional) {
          val t        = primitiveOf(kind).getOrElse(rawJava)
          val required = s"require(${jsonString(fieldName)})"
          (t, List(s"return ${strictFieldDecode(domain, owner, prop, required)};"), "")
        } else primitiveOf(kind) match {
          case Some("long") =>
            (
              "OptionalLong",
              List(
                s"Long value = CdpObject.numberAsLong($source);",
                "return value == null ? OptionalLong.empty() : OptionalLong.of(value);"
              ),
              ""
            )
          case Some("double") =>
            (
              "OptionalDouble",
              List(
                s"Double value = CdpObject.numberAsDouble($source);",
                "return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);"
              ),
              ""
            )
          case _ =>
            (s"Optional<$rawJava>", List(s"return Optional.ofNullable($decoded);"), "")
        }
      javadoc(
        docOf(prop),
        "        ",
        s"Returns the $fieldName field.",
        Nil,
        Some(if (optional) "the protocol field value, empty when absent" else "the protocol field value")
      ) ++
        deprecated(docOf(prop), "        ") ++
        List(s"        ${prefix}public $typeName $accessor() {") ++
        body.map(line => s"            $line") ++
        List("        }")
    }

    private def setter(domain: String, owner: String, prop: PDL.SubItem): List[String] = {
      val fieldName    = prop.name
      val javaName     = ident(fieldName)
      val optional     = prop.optional
      val kind         = kindOf(domain, prop.typeExpr)
      val rawJava      = rawJavaType(domain, owner, prop)
      val requiredType = primitiveOf(kind).getOrElse(rawJava)
      val optionalType = primitiveOf(kind) match {
        case Some("long")   => "OptionalLong"
        case Some("double") => "OptionalDouble"
        case _              => s"Optional<$rawJava>"
      }
      val baseParam = if (optional) optionalType else requiredType
      val baseSet   =
        if (!optional) s"set(${jsonString(fieldName)}, $javaName);"
        else primitiveOf(kind) match {
          case Some("long") =>
            s"set(${jsonString(fieldName)}, $javaName.isPresent() ? $javaName.getAsLong() : null);"
          case Some("double") =>
            s"set(${jsonString(fieldName)}, $javaName.isPresent() ? $javaName.getAsDouble() : null);"
          case _ => s"set(${jsonString(fieldName)}, $javaName.orElse(null));"
        }
      val base = javadoc(
        docOf(prop),
        "        ",
        s"Sets the $fieldName field.",
        List(javaName -> (if (optional) "field value; empty omits the value" else "field value")),
        Some("this model")
      ) ++
        deprecated(docOf(prop), "        ") ++
        List(
          s"        public $owner $javaName($baseParam $javaName) {",
          s"            $baseSet",
          "            return this;",
          "        }"
        )
      val convenience = Option.when(optional)(
        javadoc(
          docOf(prop),
          "        ",
          s"Sets the $fieldName field.",
          List(javaName -> "field value; null removes the value"),
          Some("this model")
        ) ++
          deprecated(docOf(prop), "        ") ++
          List(
            s"        public $owner $javaName($rawJava $javaName) {",
            s"            set(${jsonString(fieldName)}, $javaName);",
            "            return this;",
            "        }"
          )
      ).toList.flatten
      base ++ convenience
    }

    private def model(
        name: String,
        domain: String,
        properties: List[PDL.SubItem],
        doc: Doc,
        fallback: String
    ): List[String] = {
      val enums = properties.flatMap { prop =>
        Option.when(enumValues(prop.typeExpr).nonEmpty)(
          enumType(s"${cap(prop.name)}Values", enumValues(prop.typeExpr), docOf(prop), "        ")
        ).toList.flatten
      }
      val accessors = properties.flatMap(prop => accessor(domain, name, prop))
      val setters   = properties.flatMap(prop => setter(domain, name, prop))
      javadoc(doc, "    ", fallback, Nil, None) ++
        deprecated(doc, "    ") ++
        List(
          s"    public static final class $name extends CdpObject {",
          s"        public $name() {}",
          s"        private $name(Map<String, Object> values) { super(values); }",
          s"        public static $name fromMap(Map<String, Object> values) {",
          s"            return new $name(values);",
          "        }"
        ) ++
        enums ++ accessors ++ setters ++
        List("    }")
    }

    private def docOf(sub: PDL.SubItem): Doc =
      Doc(sub.description, sub.flags.experimental, sub.flags.deprecated)

    private def docOf(decl: PDL.Decl.Type): Doc =
      Doc(decl.description, decl.flags.experimental, decl.flags.deprecated)

    private def docOf(command: PDL.Decl.Command): Doc =
      Doc(command.description, command.flags.experimental, command.flags.deprecated)

    private def docOf(event: PDL.Decl.Event): Doc =
      Doc(event.description, event.flags.experimental, event.flags.deprecated)

    private def protocolSource(domain: String): String =
      if (browserDomains(domain))
        s"https://chromium.googlesource.com/chromium/src/+/refs/tags/$chromiumVersion/third_party/blink/public/devtools_protocol/domains/$domain.pdl"
      else s"https://chromium.googlesource.com/v8/v8/+/$v8Revision/include/js_protocol.pdl"

    private def paramRawJava(domain: String, commandName: String, param: PDL.SubItem): String =
      param.typeExpr match {
        case TypeExpr.InlineEnum(_) => s"${cap(commandName)}${cap(param.name)}Values"
        case other                  => resolve(domain, other).javaType
      }

    private def paramKind(domain: String, param: PDL.SubItem): String =
      kindOf(domain, param.typeExpr)

    private def paramRequiredType(domain: String, commandName: String, param: PDL.SubItem): String =
      primitiveOf(paramKind(domain, param)).getOrElse(paramRawJava(domain, commandName, param))

    private def paramType(domain: String, commandName: String, param: PDL.SubItem): String =
      if (param.optional) primitiveOf(paramKind(domain, param)) match {
        case Some("long")   => "OptionalLong"
        case Some("double") => "OptionalDouble"
        case _              => s"Optional<${paramRawJava(domain, commandName, param)}>"
      }
      else paramRequiredType(domain, commandName, param)

    private def emptyArg(domain: String, param: PDL.SubItem): String =
      primitiveOf(paramKind(domain, param)) match {
        case Some("long")   => "OptionalLong.empty()"
        case Some("double") => "OptionalDouble.empty()"
        case _              => "Optional.empty()"
      }

    private def returnRawJava(domain: String, ret: PDL.SubItem): String = ret.typeExpr match {
      case TypeExpr.InlineEnum(_) => s"${cap(ret.name)}Values"
      case other                  => resolve(domain, other).javaType
    }

    private def returnKind(domain: String, ret: PDL.SubItem): String = kindOf(domain, ret.typeExpr)

    private def returnDecode(domain: String, ret: PDL.SubItem): String = ret.typeExpr match {
      case TypeExpr.InlineEnum(_) => throw IllegalStateException("unexpected inline enum return")
      case other                  => decode(domain, other, s"result_.get(${jsonString(ret.name)})", "CdpObject.")
    }

    private def strictReturnDecode(domain: String, ret: PDL.SubItem): String = ret.typeExpr match {
      case TypeExpr.InlineEnum(_) => throw IllegalStateException("unexpected inline enum return")
      case other                  =>
        strictExpr(domain, other, s"java.util.Objects.requireNonNull(result_.get(${jsonString(ret.name)}))")
    }

    private def commandMethod(domain: String, command: PDL.Decl.Command): List[String] = {
      val name        = command.name
      val method      = ident(name)
      val params      = command.params
      val prefix      = cap(name)
      val optionalAll = params.nonEmpty && params.forall(_.optional)
      val required    = params.filterNot(_.optional)
      val hasRequired = required.nonEmpty
      val hasOptional = params.exists(_.optional)
      val returnsDoc  =
        if (command.returns.isEmpty) "a stage completing when the command completes"
        else "a stage completing with the command result"
      val signature = params.map(param => s"${paramType(domain, name, param)} ${ident(param.name)}")
      val args      = if (params.isEmpty) "null" else "params"
      val build     = if (params.isEmpty) Nil
      else
        List("            Map<String, Object> params = new LinkedHashMap<>();") ++
          params.map { param =>
            val javaName = ident(param.name)
            val key      = jsonString(param.name)
            if (!param.optional) s"            params.put($key, CdpObject.json($javaName));"
            else primitiveOf(paramKind(domain, param)) match {
              case Some(_) => s"            $javaName.ifPresent(value_ -> params.put($key, value_));"
              case None    => s"            $javaName.ifPresent(value_ -> params.put($key, CdpObject.json(value_)));"
            }
          }
      val decoder = command.returns match {
        case Nil                       => "result_ -> null"
        case List(ret) if ret.optional =>
          primitiveOf(returnKind(domain, ret)) match {
            case Some("long") =>
              val source = s"result_.get(${jsonString(ret.name)})"
              s"""result_ -> {
            Long value = CdpObject.numberAsLong($source);
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }"""
            case Some("double") =>
              val source = s"result_.get(${jsonString(ret.name)})"
              s"""result_ -> {
            Double value = CdpObject.numberAsDouble($source);
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }"""
            case _ => s"result_ -> Optional.ofNullable(${returnDecode(domain, ret)})"
          }
        case List(ret) => s"result_ -> ${strictReturnDecode(domain, ret)}"
        case _         => s"result_ -> new ${prefix}Result(result_)"
      }
      val retType = command.returns match {
        case Nil       => "CompletionStage<Void>"
        case List(ret) =>
          val raw = returnRawJava(domain, ret)
          if (ret.optional) s"CompletionStage<Optional<$raw>>"
          else s"CompletionStage<$raw>"
        case _ => s"CompletionStage<${prefix}Result>"
      }
      val main = javadoc(
        docOf(command),
        "        ",
        s"Invokes $domain.$name.",
        params.map(param => ident(param.name) -> "protocol value"),
        Some(returnsDoc)
      ) ++
        deprecated(docOf(command), "        ") ++
        List(s"        public $retType $method(${signature.mkString(", ")}) {") ++
        build ++
        List(s"            return client.call(\"$domain.$name\", $args, $decoder);", "        }")
      val default = Option.when(optionalAll)(
        javadoc(docOf(command), "        ", s"Invokes $domain.$name with default parameters.", Nil, Some(returnsDoc)) ++
          deprecated(docOf(command), "        ") ++
          List(
            s"        public $retType $method() {",
            s"            return $method(${params.map(emptyArg(domain, _)).mkString(", ")});",
            "        }"
          )
      ).toList.flatten
      val requiredOnly = Option.when(hasRequired && hasOptional)(
        javadoc(
          docOf(command),
          "        ",
          s"Invokes $domain.$name with the required parameters.",
          required.map(param => ident(param.name) -> "protocol value"),
          Some(returnsDoc)
        ) ++
          deprecated(docOf(command), "        ") ++
          List(
            s"        public $retType $method(${required.map(param =>
                s"${paramType(domain, name, param)} ${ident(param.name)}"
              ).mkString(", ")}) {",
            s"            return $method(${params.map(param =>
                if (param.optional) emptyArg(domain, param) else ident(param.name)
              ).mkString(", ")});",
            "        }"
          )
      ).toList.flatten
      main ++ default ++ requiredOnly
    }

    def emitDomain(domainNode: PDL.Domain): String = {
      val domain   = domainNode.name
      val commands = domainNode.commands
      val events   = domainNode.events
      val header   = List(
        banners.java,
        s"package $javaPackage;",
        "",
        "import java.util.LinkedHashMap;",
        "import java.util.Map;",
        "import java.util.Optional;",
        "import java.util.OptionalDouble;",
        "import java.util.OptionalLong;",
        "import java.util.concurrent.CompletionStage;",
        "import java.util.function.Consumer;",
        "import javax.annotation.Nonnull;",
        Banners.javaAnnotationImport,
        "import net.kurobako.cef4j.cdp.CdpClient;",
        "import net.kurobako.cef4j.cdp.CdpObject;",
        "import net.kurobako.cef4j.cdp.CdpSubscription;",
        "import net.kurobako.cef4j.cdp.CdpValue;",
        ""
      )
      val domainDoc = Doc(domainNode.description, domainNode.flags.experimental, domainNode.flags.deprecated)
      val rawDocs   = javadoc(domainDoc, "", s"Chrome DevTools Protocol $domain domain.", Nil, None)
      val docs      = rawDocs.init ++
        List(s" * @see <a href=\"${protocolSource(domain)}\">Pinned protocol source</a>") ++
        rawDocs.takeRight(1)
      val classHeader = List(
        banners.javaAnnotations(
          "EscapedEntity",
          "InvalidParam",
          "JavaLangClash",
          "MissingSummary",
          "Unchecked",
          "UnusedMethod"
        ),
        s"public final class $domain {",
        s"    private $domain() {}"
      )
      val types = domainNode.types.flatMap { decl =>
        val id = decl.name
        if (isModel(decl))
          model(
            modelName(domain, id),
            domain,
            decl.body match {
              case Some(PDL.TypeBody.Object(properties)) => properties
              case _                                     => Nil
            },
            docOf(decl),
            ""
          )
        else if (isEnum(decl))
          enumType(
            modelName(domain, id),
            decl.body match {
              case Some(PDL.TypeBody.Enum(values)) => values
              case _                               => Nil
            },
            docOf(decl),
            "    "
          )
        else taggedAlias(decl) match {
          case Some(alias) => taggedClass(modelName(domain, id), alias, docOf(decl), "    ")
          case None        => Nil
        }
      }
      val resultModels = commands.flatMap { command =>
        val operation = s"$domain.${command.name}"
        val prefix    = cap(command.name)
        if (command.returns.size >= 2)
          model(s"${prefix}Result", domain, command.returns, docOf(command), s"Result of $operation.")
        else Nil
      }
      val eventModels = events.flatMap { event =>
        model(
          s"${cap(event.name)}Event",
          domain,
          event.params,
          docOf(event),
          s"Payload of the $domain.${event.name} event."
        )
      }
      val paramEnums = commands.flatMap { command =>
        command.params.flatMap { param =>
          Option.when(enumValues(param.typeExpr).nonEmpty)(
            enumType(
              s"${cap(command.name)}${cap(param.name)}Values",
              enumValues(param.typeExpr),
              docOf(param),
              "    "
            )
          ).toList.flatten
        }
      }
      val clientHeader = List(
        "    public static final class Client {",
        "        private final CdpClient client;",
        "        public Client(CdpClient client) { this.client = client; }"
      )
      val commandMethods = commands.flatMap(command => commandMethod(domain, command))
      val eventMethods   = events.flatMap { event =>
        val name      = event.name
        val eventType = s"${cap(name)}Event"
        javadoc(
          docOf(event),
          "        ",
          s"Subscribes to $domain.$name.",
          List("handler" -> "event callback"),
          Some("a removable subscription")
        ) ++
          deprecated(docOf(event), "        ") ++
          List(
            s"        public CdpSubscription on${cap(name)}(Consumer<$eventType> handler) {",
            s"            return client.on(\"$domain.$name\", $eventType::fromMap, handler);",
            "        }"
          )
      }
      (
        header ++ docs ++ deprecated(domainDoc, "") ++ classHeader ++ types ++ resultModels ++ eventModels ++
          paramEnums ++ clientHeader ++ commandMethods ++ eventMethods ++ List("    }", "}", "")
      ).mkString("\n")
    }

    def emitDomains(domains: List[PDL.Domain]): String = {
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
        val name  = domain.name
        val field = ident(name.head.toLower +: name.tail)
        s"    private final $name.Client $field;"
      }
      val initializers = domains.map { domain =>
        val name  = domain.name
        val field = ident(name.head.toLower +: name.tail)
        s"        $field = new $name.Client(client);"
      }
      val accessors = domains.map { domain =>
        val name  = domain.name
        val field = ident(name.head.toLower +: name.tail)
        s"    public $name.Client $field() { return $field; }"
      }
      (
        header ++ fields ++ List("    public CdpDomains(CdpClient client) {") ++ initializers ++ List("    }") ++
          accessors ++ List("}", "")
      ).mkString("\n")
    }
  }

  private def readProtocol(path: Path): PDL.Protocol = PdlParser.parse(path)

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
