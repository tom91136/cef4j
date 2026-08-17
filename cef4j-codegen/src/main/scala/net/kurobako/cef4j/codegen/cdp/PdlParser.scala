package net.kurobako.cef4j.codegen.cdp

import java.io.IOException
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import scala.annotation.tailrec
import scala.jdk.CollectionConverters.*

/** Parses Chromium's line-oriented Protocol Definition Language. */
private[cdp] object PdlParser {
  private val primitives = Set("integer", "number", "boolean", "string", "object", "any", "array", "binary")

  private enum ItemSection {
    case Types, Commands, Events
  }

  private enum SubitemSection {
    case Properties, Parameters, Returns
  }

  private enum EnumTarget {
    case Item
    case Subitem(section: SubitemSection)
  }

  private final case class Flags(experimental: Boolean, deprecated: Boolean, rest: String)

  private final case class State(
      path: Path,
      sources: Set[Path],
      root: Path,
      lineNumber: Int = 0,
      description: List[String] = Nil,
      version: ProtocolVersion = ProtocolVersion(),
      domains: List[Domain] = Nil,
      itemSection: Option[ItemSection] = None,
      subitemSection: Option[SubitemSection] = None,
      enumTarget: Option[EnumTarget] = None
  ) {
    private def fail(message: String): Nothing = throw IOException(s"$path:$lineNumber: $message")

    private def pendingDoc: Option[String] = Option(description.mkString("\n").trim).filter(_.nonEmpty)

    private def modifyLast[A](values: List[A])(f: A => A): List[A] =
      values.lastOption.fold(fail("missing declaration"))(_ => values.init :+ f(values.last))

    private def modifyDomain(f: Domain => Domain): State =
      copy(domains = modifyLast(domains)(f))

    private def modifyItem(f: Item => Item): State = itemSection match {
      case Some(ItemSection.Types)    => modifyDomain(d => d.copy(types = modifyLast(d.types)(f)))
      case Some(ItemSection.Commands) => modifyDomain(d => d.copy(commands = modifyLast(d.commands)(f)))
      case Some(ItemSection.Events)   => modifyDomain(d => d.copy(events = modifyLast(d.events)(f)))
      case None                       => fail("declaration outside a type, command, or event")
    }

    private def appendItem(section: ItemSection, value: Item): State = {
      val appended = section match {
        case ItemSection.Types    => modifyDomain(d => d.copy(types = d.types :+ value))
        case ItemSection.Commands => modifyDomain(d => d.copy(commands = d.commands :+ value))
        case ItemSection.Events   => modifyDomain(d => d.copy(events = d.events :+ value))
      }
      appended.copy(itemSection = Some(section), subitemSection = None, enumTarget = None)
    }

    private def modifySubitems(section: SubitemSection)(f: List[Item] => List[Item]): State =
      modifyItem(item =>
        section match {
          case SubitemSection.Properties => item.copy(properties = f(item.properties))
          case SubitemSection.Parameters => item.copy(parameters = f(item.parameters))
          case SubitemSection.Returns    => item.copy(returns = f(item.returns))
        }
      )

    private def appendSubitem(section: SubitemSection, value: Item, isEnum: Boolean): State =
      modifySubitems(section)(_ :+ value).copy(
        subitemSection = Some(section),
        enumTarget = Option.when(isEnum)(EnumTarget.Subitem(section))
      )

    private def addEnumValue(value: String): Option[State] = enumTarget.map {
      case EnumTarget.Item             => modifyItem(item => item.copy(enumValues = item.enumValues :+ value))
      case EnumTarget.Subitem(section) =>
        modifySubitems(section)(items => modifyLast(items)(item => item.copy(enumValues = item.enumValues :+ value)))
    }

    private def include(value: String): List[Domain] = {
      val relative = Path.of(value)
      if (relative.isAbsolute) fail(s"absolute include: $relative")
      val included = path.getParent.resolve(relative).normalize()
      if (!included.startsWith(root)) fail(s"include escapes the schema root: $included")
      parseProtocol(included, sources, root).domains
    }

    private def beginType(text: String, doc: Option[String], declaration: Flags): State = text match {
      case s"$id extends array of $typeName" if id.nonEmpty && typeName.nonEmpty =>
        appendItem(
          ItemSection.Types,
          typed(item(id = Some(id), description = doc, flags = declaration), typeName, array = true)
        )
      case s"$id extends $typeName" if id.nonEmpty && typeName.nonEmpty =>
        appendItem(ItemSection.Types, typed(item(id = Some(id), description = doc, flags = declaration), typeName))
      case _ => fail(s"invalid type declaration: $text")
    }

    private def beginOperation(
        section: ItemSection,
        name: String,
        doc: Option[String],
        declaration: Flags
    ): State = appendItem(section, item(name = Some(name), description = doc, flags = declaration))

    private def beginSubitems(section: SubitemSection): State =
      copy(subitemSection = Some(section), enumTarget = None)

    private def acceptSubitem(rest: String, doc: Option[String], declaration: Flags): Option[State] = {
      val normalized                      = normalize(rest)
      val parsed: Option[(Item, Boolean)] = normalized match {
        case s"optional array of $typeName $name" if typeName.nonEmpty && name.nonEmpty =>
          Some(
            typed(
              item(name = Some(name), description = doc, flags = declaration, optional = true),
              typeName,
              array = true
            ) ->
              (typeName == "enum")
          )
        case s"array of $typeName $name" if typeName.nonEmpty && name.nonEmpty =>
          Some(
            typed(item(name = Some(name), description = doc, flags = declaration), typeName, array = true) ->
              (typeName == "enum")
          )
        case s"optional $typeName $name" if typeName.nonEmpty && name.nonEmpty =>
          Some(
            typed(item(name = Some(name), description = doc, flags = declaration, optional = true), typeName) ->
              (typeName == "enum")
          )
        case s"$typeName $name" if typeName.nonEmpty && name.nonEmpty =>
          Some(
            typed(item(name = Some(name), description = doc, flags = declaration), typeName) -> (typeName == "enum")
          )
        case _ => None
      }
      parsed.map { case (value, isEnum) =>
        appendSubitem(subitemSection.getOrElse(fail("property outside a section")), value, isEnum)
      }
        .orElse(Option.when(!normalized.contains(' '))(addEnumValue(normalized)).flatten)
    }

    private def acceptDeclaration(line: String, doc: Option[String], declaration: Flags): State = {
      val rest     = normalize(declaration.rest)
      val isTop    = !line.startsWith(" ")
      val isLevel1 = line.startsWith("  ") && !line.startsWith("    ")
      val isLevel2 = line.startsWith("    ") && !line.startsWith("      ")
      val isLevel3 = line.startsWith("      ") && !line.startsWith("        ")

      (isTop, isLevel1, isLevel2, isLevel3, rest) match {
        case (true, _, _, _, s"domain $name") if name.nonEmpty =>
          copy(
            domains = domains :+ Domain(name, doc, declaration.experimental, declaration.deprecated),
            itemSection = None,
            subitemSection = None,
            enumTarget = None
          )
        case (true, _, _, _, s"include $value") if value.nonEmpty         => copy(domains = domains ++ include(value))
        case (_, _, _, _, "version")                                      => this
        case (_, _, _, _, s"major $value") if line.startsWith("  major ") =>
          copy(version = version.copy(major = value))
        case (_, _, _, _, s"minor $value") if line.startsWith("  minor ") =>
          copy(version = version.copy(minor = value))
        case (_, _, _, _, s"depends on $name") if line.startsWith("  depends on ") =>
          modifyDomain(domain => domain.copy(dependencies = domain.dependencies :+ name))
        case (_, true, _, _, s"type $text")    => beginType(text, doc, declaration)
        case (_, true, _, _, s"command $name") => beginOperation(ItemSection.Commands, name, doc, declaration)
        case (_, true, _, _, s"event $name")   => beginOperation(ItemSection.Events, name, doc, declaration)
        case (_, _, true, _, "parameters")     => beginSubitems(SubitemSection.Parameters)
        case (_, _, true, _, "returns")        => beginSubitems(SubitemSection.Returns)
        case (_, _, true, _, "properties")     => beginSubitems(SubitemSection.Properties)
        case (_, _, true, _, "enum")           => copy(enumTarget = Some(EnumTarget.Item))
        case (_, _, _, _, s"redirect $target") if line.startsWith("    redirect ") =>
          modifyItem(item => item.copy(redirect = Some(target)))
        case (_, _, _, true, _) => acceptSubitem(rest, doc, declaration).getOrElse(fail(s"illegal token: $line"))
        case (_, _, _, _, value) if line.startsWith("        ") && !value.contains(' ') =>
          addEnumValue(value).getOrElse(fail(s"illegal token: $line"))
        case _ => fail(s"illegal token: $line")
      }
    }

    def accept(line: String): State = {
      val next    = copy(lineNumber = lineNumber + 1)
      val trimmed = line.trim
      if (trimmed.startsWith("#"))
        next.copy(description = description :+ trimmed.stripPrefix("#").stripPrefix(" "))
      else if (trimmed.isEmpty) next.copy(description = Nil)
      else next.copy(description = Nil).acceptDeclaration(line, next.pendingDoc, flags(trimmed))
    }

    def result: Protocol = Protocol(version, domains)
  }

  def parse(path: Path): Protocol = {
    val source = path.toAbsolutePath.normalize()
    parseProtocol(source, Set.empty, source.getParent)
  }

  private def parseProtocol(path: Path, sources: Set[Path], root: Path): Protocol = {
    if (sources(path)) throw IOException(s"Recursive PDL include: $path")
    val active = sources + path
    Files.readAllLines(path, StandardCharsets.UTF_8).asScala
      .foldLeft(State(path, active, root))(_.accept(_))
      .result
  }

  private def normalize(value: String): String = value.trim.split("\\s+").filter(_.nonEmpty).mkString(" ")

  private def item(
      id: Option[String] = None,
      name: Option[String] = None,
      description: Option[String],
      flags: Flags,
      optional: Boolean = false
  ): Item = Item(
    id = id,
    name = name,
    description = description,
    experimental = flags.experimental,
    deprecated = flags.deprecated,
    optional = optional
  )

  private def flags(value: String): Flags = {
    @tailrec
    def loop(rest: String, experimental: Boolean, deprecated: Boolean): Flags = normalize(rest) match {
      case s"experimental $tail" => loop(tail, experimental = true, deprecated)
      case s"deprecated $tail"   => loop(tail, experimental, deprecated = true)
      case normalized            => Flags(experimental, deprecated, normalized)
    }

    loop(value, experimental = false, deprecated = false)
  }

  private def typed(item: Item, value: String, array: Boolean = false): Item = {
    val mapped = if (value == "enum") "string" else value
    val scalar =
      if (mapped == "binary")
        item.copy(
          description = item.description.map(_ + " (Encoded as a base64 string when passed over JSON)"),
          kind = Some("string")
        )
      else if (primitives(mapped)) item.copy(kind = Some(mapped))
      else item.copy(reference = Some(mapped))
    if (array)
      item.copy(kind = Some("array"), reference = None, items = Some(typed(Item(), value)))
    else scalar
  }
}
