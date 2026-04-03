package net.kurobako.cef4j.codegen

import java.nio.file.Files
import java.nio.file.Path
import scala.annotation.tailrec
import scala.jdk.StreamConverters._

case class HeaderMetadataIndex(
    handlerNames: Set[String],
    docs: Map[String, String],
    cppTypeInfo: Map[String, CppMethodTypeInfo],
    cppClassNames: Map[String, String],
    enumDocs: Map[String, (String, Map[String, String])],
    classDocs: Map[String, String],
    structFieldDocs: Map[String, Map[String, String]]
) {
  def deriveCompoundSegments(cefIncludeDir: Path): Map[String, List[String]] =
    HeaderMetadataIndex.deriveCompoundSegments(cppClassNames, cefIncludeDir)
}

object HeaderMetadataIndex {
  private val CppClassDeclRe      = """class\s+(\w+)\s*:""".r
  private val CppMethodRe         = """virtual\s+[\w:<>]+\s+(\w+)\s*\(""".r
  private val NonVirtualMethodRe  = """[\w:<>]+\s+(\w+)\s*\(""".r
  private val CamelCaseSplitRe    = """([a-z])([A-Z])""".r
  private val DigitUpperSplitRe   = """([0-9])([A-Z])""".r
  private val StructTypedefNameRe = """typedef\s+struct\s+_(\w+)\s*\{""".r

  def apply(
      cefIncludeDir: Path,
      extraCppDirs: List[String] = Nil,
      extraCapiDirs: List[String] = Nil
  ): HeaderMetadataIndex = {
    val cppClassNames = parseCppClassNames(cefIncludeDir, extraCppDirs)
    HeaderMetadataIndex(
      handlerNames = parseHandlerAnnotations(cefIncludeDir, extraCppDirs),
      docs = parseDocComments(cefIncludeDir, extraCppDirs),
      cppTypeInfo = parseCppTypeInfo(cefIncludeDir, extraCppDirs),
      cppClassNames = cppClassNames,
      enumDocs = parseEnumDocs(cefIncludeDir),
      classDocs = parseClassDocs(cefIncludeDir, extraCppDirs, extraCapiDirs),
      structFieldDocs = parseStructFieldDocs(cefIncludeDir)
    )
  }

  def deriveCompoundSegments(cppClassNames: Map[String, String], cefIncludeDir: Path): Map[String, List[String]] = {
    val fromClasses = cppClassNames.values.flatMap { cppName =>
      val capiWords = cppNameToCapiName(cppName).stripPrefix("_").stripSuffix("_t").split("_").toList
      val cppWords  = Naming.splitPascalWords(cppName)
      alignCompoundSegments(capiWords, cppWords)
    }

    val typedefAliasRe = """typedef\s+(cef_\w+_t)\s+(\w+)\s*;""".r
    val fromTypedefs   = (cppHeaders(cefIncludeDir, Nil) :+ cefIncludeDir.resolve("internal/cef_types_wrappers.h"))
      .filter(Files.exists(_))
      .flatMap { header =>
        val content = Files.readString(header)
        typedefAliasRe.findAllMatchIn(content).flatMap { m =>
          val cName  = m.group(1)
          val alias  = m.group(2)
          val cWords = cName.stripPrefix("cef_").stripSuffix("_t").split("_").toList
          val aWords = Naming.splitPascalWords(alias)
          alignCompoundSegments(cWords, aWords)
        }
      }

    (fromClasses ++ fromTypedefs).toMap
  }

  private def cppHeaders(dir: Path, extraCppDirs: List[String]): List[Path] =
    publicHeaderDirs(dir, extraCppDirs)
      .flatMap(headerDir =>
        Files.list(headerDir).toScala(List).filter(p => Files.isRegularFile(p) && p.toString.endsWith(".h"))
      )
      .sorted

  private def capiHeaders(dir: Path, extraCapiDirs: List[String]): List[Path] =
    publicCapiHeaderDirs(dir, extraCapiDirs)
      .flatMap(headerDir =>
        Files.list(headerDir).toScala(List).filter(p => Files.isRegularFile(p) && p.toString.endsWith(".h"))
      )
      .sorted

  private def publicHeaderDirs(cefIncludeDir: Path, extraCppDirs: List[String]): List[Path] =
    (cefIncludeDir :: extraCppDirs.map(cefIncludeDir.resolve)).filter(Files.isDirectory(_))

  private def publicCapiHeaderDirs(cefIncludeDir: Path, extraCapiDirs: List[String]): List[Path] =
    (cefIncludeDir.resolve("capi") :: extraCapiDirs.map(subdir => cefIncludeDir.resolve("capi").resolve(subdir)))
      .filter(Files.isDirectory(_))

  private def relativeHeaderPath(baseDir: Path, header: Path): String =
    baseDir.relativize(header).toString.replace('\\', '/')

  private def parseHandlerAnnotations(cefIncludeDir: Path, extraCppDirs: List[String]): Set[String] =
    cppHeaders(cefIncludeDir, extraCppDirs).flatMap { header =>
      val lines = Files.readString(header).linesIterator.toVector
      lines.zipWithIndex.collect {
        case (line, j) if line.contains("source=client") =>
          lines.drop(j + 1)
            .collectFirst {
              case l if CppClassDeclRe.findFirstMatchIn(l).isDefined =>
                cppNameToCapiName(CppClassDeclRe.findFirstMatchIn(l).get.group(1))
            }
      }.flatten
    }.toSet

  private def parseDocComments(cefIncludeDir: Path, extraCppDirs: List[String]): Map[String, String] = {
    val entries = cppHeaders(cefIncludeDir, extraCppDirs).flatMap { header =>
      val fileName = relativeHeaderPath(cefIncludeDir, header)
      val lines    = Files.readString(header).linesIterator.toVector
      extractDocComments(lines, fileName)
    }

    val capiAliases = entries.flatMap { case (name, text) =>
      val capiNamePattern = """capi_name=(\w+)""".r
      capiNamePattern.findFirstMatchIn(text).map(m => (m.group(1), text))
    }

    (entries ++ capiAliases).toMap
  }

  private def parseClassDocs(
      cefIncludeDir: Path,
      extraCppDirs: List[String],
      extraCapiDirs: List[String]
  ): Map[String, String] = {
    val allHeaders = {
      val topLevel = publicHeaderDirs(cefIncludeDir, extraCppDirs).flatMap(dir =>
        Files.list(dir).toScala(List).filter(p => Files.isRegularFile(p) && p.toString.endsWith(".h"))
      )
      val capi        = capiHeaders(cefIncludeDir, extraCapiDirs)
      val internalDir = cefIncludeDir.resolve("internal")
      val internal    =
        if (Files.exists(internalDir)) Files.list(internalDir).toScala(List).filter(_.toString.endsWith(".h")) else Nil
      topLevel ++ capi ++ internal
    }

    val cppDocs = allHeaders
      .filter(p => !p.toString.contains("capi") && !p.toString.contains("internal"))
      .flatMap(h => extractClassDocs(Files.readString(h).linesIterator.toVector, relativeHeaderPath(cefIncludeDir, h)))

    val structDocs = allHeaders
      .filter(p => p.toString.contains("capi") || p.toString.contains("internal"))
      .flatMap(h => extractStructDocs(Files.readString(h).linesIterator.toVector, relativeHeaderPath(cefIncludeDir, h)))

    (structDocs ++ cppDocs).toMap
  }

  private def parseCppTypeInfo(cefIncludeDir: Path, extraCppDirs: List[String]): Map[String, CppMethodTypeInfo] = {
    val cppMethodWithParamsRe = """virtual\s+([\w:<>]+)\s+(\w+)\s*\(([^)]*)\)""".r

    cppHeaders(cefIncludeDir, extraCppDirs).flatMap { header =>
      val content = Files.readString(header)
      val lines   = content.linesIterator.toVector

      // Track enclosing class by scanning brace depth
      case class ClassState(depth: Int, cls: String, clsDepth: Int)
      val classAtLine = lines
        .scanLeft(ClassState(0, "", 0)) { (s, raw) =>
          val line      = raw.trim
          val (cls, cd) = CppClassDeclRe.findFirstMatchIn(line) match {
            case Some(m) => (m.group(1), s.depth)
            case None    => (s.cls, s.clsDepth)
          }
          val newDepth = s.depth + line.count(_ == '{') - line.count(_ == '}')
          val finalCls = if (newDepth <= cd && cls.nonEmpty && !line.contains("{")) "" else cls
          ClassState(newDepth, finalCls, cd)
        }
        .tail
        .map(_.cls)

      cppMethodWithParamsRe.findAllMatchIn(content).flatMap { m =>
        val retType = m.group(1).trim
        val name    = m.group(2).trim
        val params  = m.group(3).trim

        // Find line index of this match to determine enclosing class
        val matchLine = content.substring(0, m.start).count(_ == '\n')
        val cls       = if (matchLine < classAtLine.length) classAtLine(matchLine) else ""

        val paramTypes =
          if (params.isEmpty || params == "void") Map.empty[String, String]
          else params.split(",").flatMap { p =>
            val trimmed = p.trim
            val parts   = trimmed.split("\\s+")
            if (parts.length >= 2) {
              val pName = parts.last.stripPrefix("&").stripPrefix("*")
              val pType = parts.init.mkString(" ").replaceAll("\\bconst\\b", "").replaceAll("&", "").trim
              Some(pName -> pType)
            } else None
          }.toMap

        val info = CppMethodTypeInfo(retType, paramTypes)
        if (cls.nonEmpty) List(s"$cls::$name" -> info, name -> info)
        else List(name                        -> info)
      }
    }.toMap
  }

  private def parseEnumDocs(cefIncludeDir: Path): Map[String, (String, Map[String, String])] = {
    val typesHeaders = List(
      cefIncludeDir.resolve("internal/cef_types.h"),
      cefIncludeDir.resolve("internal/cef_time.h")
    ).filter(Files.exists(_))

    typesHeaders.flatMap { header =>
      val lines = Files.readString(header).linesIterator.toVector
      extractEnumDocs(lines)
    }.toMap
  }

  private def parseStructFieldDocs(cefIncludeDir: Path): Map[String, Map[String, String]] = {
    val internalDir = cefIncludeDir.resolve("internal")
    val headers     = if (Files.exists(internalDir))
      Files.list(internalDir).toScala(List).filter(_.toString.endsWith(".h"))
    else Nil

    headers.flatMap { header =>
      val lines = Files.readString(header).linesIterator.toVector
      extractStructFieldDocs(lines)
    }.toMap
  }

  private def extractStructFieldDocs(lines: Vector[String]): List[(String, Map[String, String])] = {
    @tailrec
    def loop(
        idx: Int,
        acc: List[(String, Map[String, String])]
    ): List[(String, Map[String, String])] =
      if (idx >= lines.length) acc
      else {
        val line = lines(idx).trim
        StructTypedefNameRe.findFirstMatchIn(line) match {
          case Some(m) =>
            val structName               = m.group(1)
            val (fieldDocs, afterStruct) = parseFieldDocs(lines, idx + 1)
            val updated                  = if (fieldDocs.nonEmpty) (structName -> fieldDocs) :: acc else acc
            loop(afterStruct, updated)
          case None =>
            loop(idx + 1, acc)
        }
      }

    loop(0, Nil).reverse
  }

  private val BeginMarkerRe = """^BEGIN\s+(.+)\.\s*$""".r
  private val EndMarkerRe   = """^END\s+(.+)\.\s*$""".r

  private def parseFieldDocs(lines: Vector[String], startIdx: Int): (Map[String, String], Int) = {
    @tailrec
    def loop(
        idx: Int,
        docs: Map[String, String],
        currentDoc: String,
        sectionNote: String
    ): (Map[String, String], Int) =
      if (idx >= lines.length) (docs, idx)
      else {
        val line = lines(idx).trim
        if (line.startsWith("}")) (docs, idx)
        else if (line.startsWith("///")) {
          val docLine = line.stripPrefix("///").trim
          docLine match {
            case BeginMarkerRe(suffix) =>
              // Remaining doc text after BEGIN line becomes the next field's doc
              loop(idx + 1, docs, currentDoc, suffix.trim)
            case EndMarkerRe(_) =>
              // Remaining doc text after END line becomes the next field's doc, clear section
              loop(idx + 1, docs, currentDoc, "")
            case _ =>
              val newDoc = if (currentDoc.isEmpty) docLine else s"$currentDoc $docLine"
              loop(idx + 1, docs, newDoc, sectionNote)
          }
        } else if (line.contains(";") && !line.startsWith("//") && !line.startsWith("/*")) {
          val beforeSemi  = line.stripSuffix(";").trim
          val fieldName   = beforeSemi.split("\\s+").last.stripPrefix("*")
          val docWithNote =
            if (sectionNote.nonEmpty && currentDoc.nonEmpty) s"$currentDoc\n@_section:$sectionNote"
            else if (sectionNote.nonEmpty) s"@_section:$sectionNote"
            else currentDoc
          val updatedDocs = if (docWithNote.nonEmpty && fieldName.nonEmpty) docs + (fieldName -> docWithNote) else docs
          loop(idx + 1, updatedDocs, "", sectionNote)
        } else {
          loop(idx + 1, docs, currentDoc, sectionNote)
        }
      }

    loop(startIdx, Map.empty, "", "")
  }

  private def parseCppClassNames(cefIncludeDir: Path, extraCppDirs: List[String]): Map[String, String] = {
    val fromClasses = cppHeaders(cefIncludeDir, extraCppDirs).flatMap { header =>
      val content = Files.readString(header)
      CppClassDeclRe.findAllMatchIn(content).map { m =>
        val cppName  = m.group(1)
        val capiName = cppNameToCapiName(cppName)
        capiName -> cppName
      }
    }

    val wrappersFile = cefIncludeDir.resolve("internal/cef_types_wrappers.h")
    val fromWrappers = if (Files.exists(wrappersFile)) {
      val content       = Files.readString(wrappersFile)
      val classInherits = """class\s+(Cef\w+)\s*:\s*public\s+(cef_\w+_t)""".r
        .findAllMatchIn(content).map(m => m.group(2) -> m.group(1)).toList
      val usingAliases = """using\s+(Cef\w+)\s*=\s*\w+<\w+>""".r
        .findAllMatchIn(content).map { m =>
          val cppName = m.group(1)
          cppNameToCapiName(cppName) -> cppName
        }.toList
      classInherits ++ usingAliases
    } else Nil

    (fromClasses ++ fromWrappers).toMap
  }

  private def extractDocComments(lines: Vector[String], fileName: String = ""): List[(String, String)] = {
    def withSrc(comment: String, lineIdx: Int): String =
      if (fileName.nonEmpty) s"$comment\n@_cefsrc:$fileName:${lineIdx + 1}" else comment

    // Pre-compute the enclosing C++ class name at each line by scanning brace depth and class declarations.
    // This avoids brace-counting drift when the main loop jumps over lines during comment collection.
    val classAtLine: Vector[String] = {
      case class State(depth: Int, cls: String, clsDepth: Int)
      lines
        .scanLeft(State(0, "", 0)) { (s, raw) =>
          val line      = raw.trim
          val (cls, cd) = CppClassDeclRe.findFirstMatchIn(line) match {
            case Some(m) => (m.group(1), s.depth)
            case None    => (s.cls, s.clsDepth)
          }
          val newDepth = s.depth + line.count(_ == '{') - line.count(_ == '}')
          val finalCls = if (newDepth <= cd && cls.nonEmpty && !line.contains("{")) "" else cls
          State(newDepth, finalCls, cd)
        }
        .tail
        .map(_.cls)
    }

    // Emit both a qualified key ("CefBrowser::IsValid") and an unqualified key ("IsValid").
    def emitKeys(
        name: String,
        value: String,
        cls: String,
        acc: List[(String, String)]
    ): List[(String, String)] =
      if (cls.nonEmpty) (s"$cls::$name", value) :: (name, value) :: acc
      else (name, value) :: acc

    @tailrec
    def loop(idx: Int, acc: List[(String, String)]): List[(String, String)] =
      if (idx >= lines.length) acc
      else {
        val line = lines(idx).trim
        if (line.startsWith("///")) {
          val (comment, afterComment)      = collectComment(lines, idx)
          val (metaStr, methodSearchStart) = collectMetaComment(lines, afterComment) match {
            case Some((meta, afterMeta)) => (meta, afterMeta)
            case None                    => ("", afterComment)
          }

          val mergedComment = if (metaStr.nonEmpty) s"$comment\n$metaStr" else comment
          val methodLineIdx = lines.indices.drop(methodSearchStart).find(i => lines(i).trim.nonEmpty)
          val methodLine    = methodLineIdx.map(i => lines(i).trim).getOrElse("")
          val cls           = methodLineIdx.map(classAtLine).getOrElse("")
          extractMethodName(methodLine) match {
            case Some(name) => loop(methodSearchStart, emitKeys(name, withSrc(mergedComment, idx), cls, acc))
            case None       => loop(methodSearchStart, acc)
          }
        } else {
          collectMetaComment(lines, idx) match {
            case Some((meta, afterMeta)) =>
              val methodLineIdx = lines.indices.drop(afterMeta).find(i => lines(i).trim.nonEmpty)
              val methodLine    = methodLineIdx.map(i => lines(i).trim).getOrElse("")
              val cls           = methodLineIdx.map(classAtLine).getOrElse("")
              extractMethodName(methodLine) match {
                case Some(name) => loop(afterMeta, emitKeys(name, withSrc(meta, idx), cls, acc))
                case None       => loop(afterMeta, acc)
              }
            case None if line.startsWith("/*") =>
              val (comment, afterComment) = collectComment(lines, idx)
              val methodLineIdx           = lines.indices.drop(afterComment).find(i => lines(i).trim.nonEmpty)
              val methodLine              = methodLineIdx.map(i => lines(i).trim).getOrElse("")
              val cls                     = methodLineIdx.map(classAtLine).getOrElse("")
              extractMethodName(methodLine) match {
                case Some(name) => loop(afterComment, emitKeys(name, withSrc(comment, idx), cls, acc))
                case None       => loop(afterComment, acc)
              }
            case None =>
              loop(idx + 1, acc)
          }
        }
      }

    loop(0, Nil).reverse
  }

  private def extractClassDocs(lines: Vector[String], fileName: String = ""): List[(String, String)] = {
    def withSrc(comment: String, lineIdx: Int): String =
      if (fileName.nonEmpty) s"$comment\n@_cefsrc:$fileName:${lineIdx + 1}" else comment

    @tailrec
    def loop(idx: Int, acc: List[(String, String)]): List[(String, String)] =
      if (idx >= lines.length) acc
      else {
        val line = lines(idx).trim
        if (line.startsWith("///")) {
          val commentLines = lines.drop(idx)
            .takeWhile(_.trim.startsWith("///"))
            .map(_.trim.stripPrefix("///").trim)
          val comment      = commentLines.filter(_.nonEmpty).mkString("\n")
          val afterComment = idx + commentLines.length
          val searchStart  = lines.drop(afterComment).headOption.map(_.trim) match {
            case Some(nextLine) if isSingleLineMetaComment(nextLine) => afterComment + 1
            case _                                                   => afterComment
          }
          val nextLine = lines.drop(searchStart).find(_.trim.nonEmpty).map(_.trim).getOrElse("")
          CppClassDeclRe.findFirstMatchIn(nextLine) match {
            case Some(m) => loop(searchStart + 1, (cppNameToCapiName(m.group(1)), withSrc(comment, idx)) :: acc)
            case None    => loop(afterComment, acc)
          }
        } else {
          loop(idx + 1, acc)
        }
      }

    loop(0, Nil).reverse
  }

  private def extractStructDocs(lines: Vector[String], fileName: String = ""): List[(String, String)] = {
    def withSrc(comment: String, lineIdx: Int): String =
      if (fileName.nonEmpty) s"$comment\n@_cefsrc:$fileName:${lineIdx + 1}" else comment

    @tailrec
    def skipAdditionalDocBlocks(idx: Int): Int =
      if (idx >= lines.length || !lines(idx).trim.startsWith("///")) idx
      else skipAdditionalDocBlocks(idx + lines.drop(idx).takeWhile(_.trim.startsWith("///")).length)

    @tailrec
    def loop(idx: Int, acc: List[(String, String)]): List[(String, String)] =
      if (idx >= lines.length) acc
      else {
        val line = lines(idx).trim
        if (line.startsWith("///")) {
          val commentLines = lines.drop(idx)
            .takeWhile(_.trim.startsWith("///"))
            .map(_.trim.stripPrefix("///").trim)
          val comment      = commentLines.filter(_.nonEmpty).mkString("\n")
          val afterComment = idx + commentLines.length
          val searchStart  = skipAdditionalDocBlocks(afterComment)
          val nextLine     = lines.drop(searchStart).find(_.trim.nonEmpty).map(_.trim).getOrElse("")
          StructTypedefNameRe.findFirstMatchIn(nextLine) match {
            case Some(m) => loop(searchStart + 1, (m.group(1), withSrc(comment, idx)) :: acc)
            case None    => loop(afterComment, acc)
          }
        } else {
          loop(idx + 1, acc)
        }
      }

    loop(0, Nil).reverse
  }

  private def extractEnumDocs(lines: Vector[String]): List[(String, (String, Map[String, String]))] = {
    @tailrec
    def loop(
        idx: Int,
        acc: List[(String, (String, Map[String, String]))]
    ): List[(String, (String, Map[String, String]))] =
      if (idx >= lines.length) acc
      else {
        val line = lines(idx).trim
        if (line.startsWith("///")) {
          val commentLines = lines.drop(idx)
            .takeWhile(_.trim.startsWith("///"))
            .map(_.trim.stripPrefix("///").trim)
          val comment      = commentLines.mkString(" ").trim
          val afterComment = idx + commentLines.length
          val nextLine     = lines.drop(afterComment).find(_.trim.nonEmpty).map(_.trim).getOrElse("")
          if (nextLine.startsWith("typedef enum")) {
            val (constantDocs, enumEnd) = parseEnumConstantDocs(lines, afterComment)
            val closingLine             = lines.drop(enumEnd).find(_.trim.startsWith("}")).map(_.trim).getOrElse("")
            val enumName                = closingLine.stripPrefix("}").stripSuffix(";").trim
            if (enumName.nonEmpty) loop(enumEnd + 1, (enumName -> (comment, constantDocs)) :: acc)
            else loop(enumEnd + 1, acc)
          } else {
            loop(afterComment, acc)
          }
        } else {
          loop(idx + 1, acc)
        }
      }

    loop(0, Nil).reverse
  }

  private def parseEnumConstantDocs(lines: Vector[String], startIdx: Int): (Map[String, String], Int) = {
    @tailrec
    def loop(idx: Int, docs: Map[String, String], currentDoc: String): (Map[String, String], Int) =
      if (idx >= lines.length) (docs, idx)
      else {
        val line = lines(idx).trim
        if (line.startsWith("}")) (docs, idx)
        else if (line.startsWith("///")) {
          val docLine = line.stripPrefix("///").trim
          val newDoc  = if (currentDoc.isEmpty) docLine else s"$currentDoc $docLine"
          loop(idx + 1, docs, newDoc)
        } else if (
          !line.startsWith("//") &&
          !line.startsWith("/*") &&
          line != "{" &&
          extractLeadingIdentifier(line).exists(_.head.isUpper)
        ) {
          val name        = extractLeadingIdentifier(line).getOrElse("")
          val updatedDocs = if (currentDoc.nonEmpty && name.nonEmpty) docs + (name -> currentDoc) else docs
          loop(idx + 1, updatedDocs, "")
        } else {
          loop(idx + 1, docs, currentDoc)
        }
      }

    loop(startIdx, Map.empty, "")
  }

  private def collectMetaComment(lines: Vector[String], startIdx: Int): Option[(String, Int)] = {
    val firstLine = lines(startIdx).trim
    if (isSingleLineMetaComment(firstLine)) {
      Some((firstLine.stripPrefix("/*").stripSuffix("*/").trim, startIdx + 1))
    } else if (isMetaCommentStart(firstLine)) {
      val endIdx = lines.indexWhere(line => isMetaCommentEnd(line.trim), startIdx)
      if (endIdx >= 0) {
        val metaLines = lines.slice(startIdx, endIdx + 1).map(_.trim)
        val joined    = metaLines.mkString(" ").stripPrefix("/*").stripSuffix("*/").trim
        Some((joined, endIdx + 1))
      } else None
    } else None
  }

  private def collectComment(lines: Vector[String], startIdx: Int): (String, Int) = {
    val firstLine = lines(startIdx).trim
    if (firstLine.startsWith("///")) {
      val commentLines = lines.drop(startIdx)
        .takeWhile(_.trim.startsWith("///"))
        .map(_.trim.stripPrefix("///").trim)
      (commentLines.mkString("\n"), startIdx + commentLines.length)
    } else {
      val endIdx       = lines.indexWhere(_.contains("*/"), startIdx)
      val actualEnd    = if (endIdx < 0) startIdx + 1 else endIdx + 1
      val commentLines = lines.slice(startIdx, actualEnd)
        .map(_.trim.stripPrefix("/*").stripPrefix("*").stripSuffix("*/").trim)
        .filter(_.nonEmpty)
      (commentLines.mkString("\n"), actualEnd)
    }
  }

  private def extractMethodName(line: String): Option[String] =
    CppMethodRe.findFirstMatchIn(line).map(_.group(1))
      .orElse(NonVirtualMethodRe.findFirstMatchIn(line).map(_.group(1)))

  private def extractLeadingIdentifier(line: String): Option[String] = {
    val name = line.takeWhile(ch => ch.isLetterOrDigit || ch == '_')
    Option.when(name.nonEmpty && name.head.isLetter && name.forall(ch => ch.isLetterOrDigit || ch == '_'))(name)
  }

  private def cppNameToCapiName(cppName: String): String =
    s"${DigitUpperSplitRe.replaceAllIn(CamelCaseSplitRe.replaceAllIn(cppName, "$1_$2"), "$1_$2").toLowerCase}_t"

  private def alignCompoundSegments(
      capiWords: List[String],
      cppWords: List[String]
  ): List[(String, List[String])] = {
    @tailrec
    def go(capi: List[String], cpp: List[String], acc: List[(String, List[String])]): List[(String, List[String])] =
      (capi, cpp) match {
        case (Nil, _) | (_, Nil) => acc
        case (c :: cs, _)        =>
          val found = (1 to cpp.length).find(n => cpp.take(n).mkString.toLowerCase == c.toLowerCase)
          found match {
            case Some(n) if n > 1 => go(cs, cpp.drop(n), (c.toLowerCase, cpp.take(n)) :: acc)
            case Some(n)          => go(cs, cpp.drop(n), acc)
            case None             => go(cs, cpp, acc)
          }
      }

    go(capiWords, cppWords, Nil)
  }

  private def isSingleLineMetaComment(line: String): Boolean =
    line.startsWith("/*--cef(") && line.endsWith("--*/")

  private def isMetaCommentStart(line: String): Boolean =
    line.startsWith("/*--cef(")

  private def isMetaCommentEnd(line: String): Boolean =
    line.contains("--*/")
}
