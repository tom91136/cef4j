package net.kurobako.cef4j.codegen.cdp

import java.io.IOException
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import scala.annotation.tailrec
import scala.collection.mutable
import scala.jdk.CollectionConverters.*

import PDL.TypeBody
import PDL.TypeExpr

/** Parses Chromium's line-oriented Protocol Definition Language into the [[PDL.Protocol]] AST. */
object PdlParser {
  private case class Flags(experimental: Boolean, deprecated: Boolean, rest: String)

  private case class Raw(line: String, doc: Option[String])

  /** The form of the declaration an operation is parsed as, which the command form alone holds returns and redirects.
    */
  private enum OperationForm {
    case Command, Event
  }

  private class State(lines: IndexedSeq[String], path: Path, sources: Set[Path], root: Path) {
    private val remaining  = mutable.ArrayDeque.from(lines)
    private val pendingDoc = mutable.ListBuffer.empty[String]
    private val lookahead  = mutable.ListBuffer.empty[Raw]

    private def lineNumber: Int = lines.length - remaining.size

    private def fail(message: String): Nothing = throw IOException(s"$path:$lineNumber: $message")

    private def isLevel1(line: String): Boolean = line.startsWith("  ") && !line.startsWith("    ")
    private def isLevel2(line: String): Boolean = line.startsWith("    ") && !line.startsWith("      ")
    private def isLevel3(line: String): Boolean = line.startsWith("      ") && !line.startsWith("        ")

    private def fill(): Option[Raw] =
      if (lookahead.nonEmpty) lookahead.headOption
      else {
        while remaining.nonEmpty && lookahead.isEmpty do {
          val raw     = remaining.removeHead()
          val trimmed = raw.trim
          if (trimmed.startsWith("#")) pendingDoc += trimmed.stripPrefix("#").stripPrefix(" ")
          else if (trimmed.isEmpty) pendingDoc.clear()
          else {
            val doc = Option(pendingDoc.mkString("\n").trim).filter(_.nonEmpty)
            pendingDoc.clear()
            lookahead += Raw(raw, doc)
          }
        }
        lookahead.headOption
      }

    private def peek(): Option[Raw] = fill()

    private def take(): Option[Raw] = {
      val result = fill()
      lookahead.clear()
      result
    }

    def parse(): PDL.Protocol = {
      val domains = mutable.ListBuffer.empty[PDL.Domain]

      @tailrec
      def loop(version: PDL.Version): PDL.Protocol =
        take() match {
          case None      => PDL.Protocol(version, domains.toList)
          case Some(raw) =>
            if (raw.line.startsWith(" ")) fail(s"illegal token: ${raw.line}")
            else {
              val declaration = flags(raw.line)
              val nextVersion = declaration.rest match {
                case s"domain $name" if name.nonEmpty    => domains += parseDomain(name, raw.doc, declaration); version
                case s"include $value" if value.nonEmpty => domains ++= parseInclude(value); version
                case "version"                           => parseVersion()
                case _                                   => fail(s"illegal token: ${raw.line}")
              }
              loop(nextVersion)
            }
        }
      loop(PDL.Version())
    }

    private def parseDomain(name: String, description: Option[String], declaration: Flags): PDL.Domain = {
      val declarations = mutable.ListBuffer.empty[PDL.Decl]
      @annotation.tailrec
      def loop(): Unit =
        peek() match {
          case Some(raw) if !raw.line.startsWith(" ") => ()
          case Some(raw) if isLevel1(raw.line)        =>
            val doc = raw.doc
            val f   = flags(raw.line)
            val _   = take()
            f.rest match {
              case _ if raw.line.startsWith("  depends on ") =>
                loop()
              case s"type $text" =>
                declarations += parseType(text, doc, f)
                loop()
              case s"command $commandName" =>
                declarations += parseOperation(OperationForm.Command, commandName, doc, f)
                loop()
              case s"event $eventName" =>
                declarations += parseOperation(OperationForm.Event, eventName, doc, f)
                loop()
              case _ => fail(s"illegal token: ${raw.line}")
            }
          case Some(raw) => fail(s"illegal token: ${raw.line}")
          case None      => ()
        }
      loop()
      val all = declarations.toList
      PDL.Domain(
        name,
        description,
        PDL.Flags(declaration.experimental, declaration.deprecated),
        all.collect { case t: PDL.Decl.Type => t } ++
          all.collect { case c: PDL.Decl.Command => c } ++
          all.collect { case e: PDL.Decl.Event => e }
      )
    }

    private def parseType(text: String, doc: Option[String], declaration: Flags): PDL.Decl.Type = text match {
      case s"$id extends array of $typeName" if id.nonEmpty && typeName.nonEmpty =>
        val expr = TypeExpr.ArrayOf(typeExpr(typeName))
        PDL.Decl.Type(
          id,
          expr,
          parseTypeBody(),
          binaryNote(doc, expr),
          PDL.Flags(declaration.experimental, declaration.deprecated)
        )
      case s"$id extends $typeName" if id.nonEmpty && typeName.nonEmpty =>
        val expr = typeExpr(typeName)
        PDL.Decl.Type(
          id,
          expr,
          parseTypeBody(),
          binaryNote(doc, expr),
          PDL.Flags(declaration.experimental, declaration.deprecated)
        )
      case _ => fail(s"invalid type declaration: $text")
    }

    private def parseTypeBody(): Option[TypeBody] =
      peek() match {
        case Some(raw) if isLevel2(raw.line) && flags(raw.line).rest == "properties" =>
          val _          = take()
          val properties = parseSubitems()
          Option.when(properties.nonEmpty)(TypeBody.Object(properties))
        case Some(raw) if isLevel2(raw.line) && flags(raw.line).rest == "enum" =>
          val _ = take()
          Some(TypeBody.Enum(parseEnumValues()))
        case _ => None
      }

    private def parseSubitems(): List[PDL.SubItem] = {
      val items = mutable.ListBuffer.empty[PDL.SubItem]
      @annotation.tailrec
      def loop(): Unit =
        peek() match {
          case Some(raw) if isLevel3(raw.line) =>
            val _ = take()
            items += parseSubitem(raw)
            loop()
          case _ => ()
        }
      loop()
      items.toList
    }

    private def parseSubitem(raw: Raw): PDL.SubItem = {
      val declaration            = flags(raw.line)
      val (name, expr, optional) = declaration.rest match {
        case s"optional array of $typeName $name" if typeName.nonEmpty && name.nonEmpty =>
          (name, TypeExpr.ArrayOf(inlineEnumOr(typeName)), true)
        case s"array of $typeName $name" if typeName.nonEmpty && name.nonEmpty =>
          (name, TypeExpr.ArrayOf(inlineEnumOr(typeName)), false)
        case s"optional $typeName $name" if typeName.nonEmpty && name.nonEmpty =>
          (name, inlineEnumOr(typeName), true)
        case s"$typeName $name" if typeName.nonEmpty && name.nonEmpty =>
          (name, inlineEnumOr(typeName), false)
        case _ => fail(s"illegal token: ${raw.line}")
      }
      PDL.SubItem(
        name,
        expr,
        optional,
        binaryNote(raw.doc, expr),
        PDL.Flags(declaration.experimental, declaration.deprecated)
      )
    }

    private def inlineEnumOr(typeName: String): TypeExpr =
      if (typeName == "enum") TypeExpr.InlineEnum(parseEnumValues()) else typeExpr(typeName)

    private def parseEnumValues(): List[String] = {
      val values = mutable.ListBuffer.empty[String]
      @annotation.tailrec
      def loop(): Unit =
        peek() match {
          case Some(raw)
              if !raw.line.trim.contains(' ') && (isLevel3(raw.line) || raw.line.startsWith("        ")) =>
            val _ = take()
            values += raw.line.trim
            loop()
          case _ => ()
        }
      loop()
      values.toList
    }

    private def parseOperation(
        form: OperationForm,
        name: String,
        doc: Option[String],
        declaration: Flags
    ): PDL.Decl = {
      val command = form == OperationForm.Command
      val params  = mutable.ListBuffer.empty[PDL.SubItem]
      val returns = mutable.ListBuffer.empty[PDL.SubItem]
      @annotation.tailrec
      def loop(): Unit =
        peek() match {
          case Some(raw) if isLevel2(raw.line) =>
            val rest = flags(raw.line).rest
            rest match {
              case "parameters" =>
                val _ = take()
                params ++= parseSubitems()
                loop()
              case "returns" if command =>
                val _ = take()
                returns ++= parseSubitems()
                loop()
              case s"redirect $_" if raw.line.startsWith("    redirect ") =>
                val _ = take()
                loop()
              case _ => fail(s"illegal token: ${raw.line}")
            }
          case Some(raw) if isLevel3(raw.line)              => fail(s"illegal token: ${raw.line}")
          case Some(raw) if raw.line.startsWith("        ") => fail(s"illegal token: ${raw.line}")
          case _                                            => ()
        }
      loop()
      val f = PDL.Flags(declaration.experimental, declaration.deprecated)
      if (command) PDL.Decl.Command(name, params.toList, returns.toList, doc, f)
      else PDL.Decl.Event(name, params.toList, doc, f)
    }

    private def parseVersion(): PDL.Version = {
      @tailrec
      def loop(version: PDL.Version): PDL.Version =
        peek() match {
          case Some(raw) if isLevel1(raw.line) =>
            flags(raw.line).rest match {
              case s"major $value" if raw.line.startsWith("  major ") =>
                val _ = take()
                loop(version.copy(major = value))
              case s"minor $value" if raw.line.startsWith("  minor ") =>
                val _ = take()
                loop(version.copy(minor = value))
              case _ => version
            }
          case _ => version
        }
      loop(PDL.Version())
    }

    private def parseInclude(value: String): List[PDL.Domain] = {
      val relative = Path.of(value)
      if (relative.isAbsolute) fail(s"absolute include: $relative")
      val included = path.getParent.resolve(relative).normalize()
      if (!included.startsWith(root)) fail(s"include escapes the schema root: $included")
      parseFile(included, sources, root).domains
    }
  }

  def parse(path: Path): PDL.Protocol = {
    val source = path.toAbsolutePath.normalize()
    parseFile(source, Set.empty, source.getParent)
  }

  private def parseFile(path: Path, sources: Set[Path], root: Path): PDL.Protocol = {
    if (sources(path)) throw IOException(s"Recursive PDL include: $path")
    val active = sources + path
    val state  =
      State(Files.readAllLines(path, StandardCharsets.UTF_8).asScala.toIndexedSeq, path, active, root)
    state.parse()
  }

  private def normalize(value: String): String = value.trim.split("\\s+").filter(_.nonEmpty).mkString(" ")

  private def typeExpr(value: String): TypeExpr =
    if (value == "enum") TypeExpr.Str
    else if (value == "binary") TypeExpr.Binary
    else if (value == "array") TypeExpr.ArrayOf(TypeExpr.AnyType)
    else if (value == "integer") TypeExpr.Integer
    else if (value == "number") TypeExpr.Number
    else if (value == "boolean") TypeExpr.Boolean
    else if (value == "string") TypeExpr.Str
    else if (value == "object") TypeExpr.Obj
    else if (value == "any") TypeExpr.AnyType
    else {
      val parts = value.split("\\.", 2)
      TypeExpr.Ref(Option.when(parts.length == 2)(parts(0)), if (parts.length == 2) parts(1) else parts(0))
    }

  private def binaryNote(doc: Option[String], expr: TypeExpr): Option[String] = expr match {
    case TypeExpr.Binary => doc.map(_ + " (Encoded as a base64 string when passed over JSON)")
    case _               => doc
  }

  private def flags(value: String): Flags = {
    @tailrec
    def loop(rest: String, experimental: Boolean, deprecated: Boolean): Flags = normalize(rest) match {
      case s"experimental $tail" => loop(tail, experimental = true, deprecated)
      case s"deprecated $tail"   => loop(tail, experimental, deprecated = true)
      case normalized            => Flags(experimental, deprecated, normalized)
    }

    loop(value, experimental = false, deprecated = false)
  }
}
