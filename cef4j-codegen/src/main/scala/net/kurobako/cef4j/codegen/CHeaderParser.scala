package net.kurobako.cef4j.codegen

import java.nio.file.Files
import java.nio.file.Path
import scala.annotation.tailrec
import scala.jdk.StreamConverters._

object CHeaderParser {

  /** Structs marshaled by value between Java and native. */
  val ByValueStructs: Set[String] =
    Set(
      "cef_rect_t",
      "cef_point_t",
      "cef_size_t",
      "cef_insets_t",
      "cef_range_t",
      "cef_screen_info_t",
      "cef_mouse_event_t",
      "cef_key_event_t",
      "cef_audio_parameters_t",
      "cef_touch_handle_state_t",
      "cef_basetime_t",
      "cef_touch_event_t",
      "cef_composition_underline_t",
      "cef_cookie_t",
      "cef_window_info_t",
      "cef_browser_settings_t",
      "cef_pdf_print_settings_t"
    )

  val OpaqueStructs: Set[String] = Set.empty

  def isByValueStruct(name: String): Boolean = ByValueStructs.contains(name)

  private def cppHeaders(dir: Path): List[Path] =
    Files.list(dir).toScala(List)
      .filter(p => p.toString.endsWith(".h") && !p.toString.contains("capi") && !p.toString.contains("internal"))

  private def isIdentifier(value: String): Boolean =
    value.nonEmpty && value.head.isLetter && value.forall(ch => ch.isLetterOrDigit || ch == '_')

  private def extractLeadingIdentifier(line: String): Option[String] = {
    val name = line.takeWhile(ch => ch.isLetterOrDigit || ch == '_')
    Option.when(isIdentifier(name))(name)
  }

  private def isSingleLineMetaComment(line: String): Boolean =
    line.startsWith("/*--cef(") && line.endsWith("--*/")

  private def isMetaCommentStart(line: String): Boolean =
    line.startsWith("/*--cef(")

  private def isMetaCommentEnd(line: String): Boolean =
    line.contains("--*/")

  private val StructTypedefRe    = """typedef\s+struct\s+_cef_\w+_t\s*\{""".r
  private val EnumTypedefRe      = """typedef\s+enum\s*\{""".r
  private val StructClosingRe    = """\}\s*cef_\w+_t\s*;""".r
  private val CppClassDeclRe     = """class\s+(\w+)\s*:""".r
  private val CppMethodRe        = """virtual\s+[\w:<>]+\s+(\w+)\s*\(""".r
  private val NonVirtualMethodRe = """[\w:<>]+\s+(\w+)\s*\(""".r
  private val FnPtrLineRe        = """\w\s*\*?\s*\(\s*\*\s*\w+\s*\)""".r
  private val CamelCaseSplitRe   = """([a-z])([A-Z])""".r
  private val EnumClosingRe      = """\}\s*\w+\s*;""".r
  private val BaseFieldRe        = """.*cef_\w+_t\s+base\s*;.*""".r
  private val ScopedBaseRe       = """.*cef_base_scoped_t\s+base\s*;.*""".r
  private val StructOpenRe       = """typedef\s+struct\s+_cef_\w+_t\s*\{""".r
  private val StructCloseRe      = """\}\s*(cef_\w+_t)\s*;""".r

  // -- Immutable parse state for the top-level scan --
  private case class TopState(decls: List[CefDecl], idx: Int)

  def parse(preprocessed: String, handlerNames: Set[String] = Set.empty): List[CefDecl] = {
    val lines           = preprocessed.linesIterator.map(_.trim).toVector
    val dataStructNames = prescanDataStructs(lines)

    @tailrec
    def loop(state: TopState): TopState =
      if (state.idx >= lines.length) state
      else {
        val line = lines(state.idx)
        if (StructTypedefRe.matches(line)) {
          val (decl, next) = parseStruct(lines, state.idx, handlerNames, dataStructNames)
          loop(TopState(decl :: state.decls, next))
        } else if (EnumTypedefRe.matches(line)) {
          val (decl, next) = parseEnum(lines, state.idx)
          loop(TopState(decl :: state.decls, next))
        } else {
          loop(state.copy(idx = state.idx + 1))
        }
      }

    loop(TopState(Nil, 0)).decls.reverse
  }

  def parseHandlerAnnotations(cefIncludeDir: Path): Set[String] =
    cppHeaders(cefIncludeDir).flatMap { header =>
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

  /** Extract Javadoc-style comments from C++ headers, keyed by function name. Also indexes by capi_name when present
    * (e.g., Continue -> cont).
    */
  def parseDocComments(cefIncludeDir: Path): Map[String, String] = {
    val entries = cppHeaders(cefIncludeDir).flatMap { header =>
      val fileName = header.getFileName.toString
      val lines    = Files.readString(header).linesIterator.toVector
      extractDocComments(lines, fileName)
    }

    // Also index by capi_name when a --cef(capi_name=X)-- attribute is present
    val capiAliases = entries.flatMap { case (name, text) =>
      val capiNamePattern = """capi_name=(\w+)""".r
      capiNamePattern.findFirstMatchIn(text).map(m => (m.group(1), text))
    }

    (entries ++ capiAliases).toMap
  }

  /** Parse class-level documentation from all headers, keyed by capi struct name (e.g., "cef_browser_t"). Scans C++
    * headers (class declarations), capi headers (typedef struct), and internal headers (typedef struct).
    */
  def parseClassDocs(cefIncludeDir: Path): Map[String, String] = {
    val allHeaders = {
      val topLevel = Files.list(cefIncludeDir).toScala(List).filter(_.toString.endsWith(".h"))
      val capiDir  = cefIncludeDir.resolve("capi")
      val capi = if (Files.exists(capiDir)) Files.list(capiDir).toScala(List).filter(_.toString.endsWith(".h")) else Nil
      val internalDir = cefIncludeDir.resolve("internal")
      val internal    =
        if (Files.exists(internalDir)) Files.list(internalDir).toScala(List).filter(_.toString.endsWith(".h")) else Nil
      topLevel ++ capi ++ internal
    }

    // C++ class docs take priority over capi/internal typedef struct docs
    val cppDocs = allHeaders
      .filter(p => !p.toString.contains("capi") && !p.toString.contains("internal"))
      .flatMap(h => extractClassDocs(Files.readString(h).linesIterator.toVector, h.getFileName.toString))

    val structDocs = allHeaders
      .filter(p => p.toString.contains("capi") || p.toString.contains("internal"))
      .flatMap(h => extractStructDocs(Files.readString(h).linesIterator.toVector, h.getFileName.toString))

    // C++ class docs win over capi/internal typedef docs
    (structDocs ++ cppDocs).toMap
  }

  /** Extract class docs from C++ headers: /// doc -> /*--cef()--*/ -> class Name : */
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

          val searchStart = lines.drop(afterComment).headOption.map(_.trim) match {
            case Some(nextLine) if isSingleLineMetaComment(nextLine) => afterComment + 1
            case _                                                   => afterComment
          }

          val nextLine   = lines.drop(searchStart).find(_.trim.nonEmpty).map(_.trim).getOrElse("")
          val classMatch = CppClassDeclRe.findFirstMatchIn(nextLine)
          classMatch match {
            case Some(m) =>
              val capiName = cppNameToCapiName(m.group(1))
              loop(searchStart + 1, (capiName, withSrc(comment, idx)) :: acc)
            case None =>
              loop(afterComment, acc)
          }
        } else {
          loop(idx + 1, acc)
        }
      }
    loop(0, Nil).reverse
  }

  private val StructTypedefNameRe = """typedef\s+struct\s+_(\w+)\s*\{""".r

  /** Extract struct docs from C/internal headers: /// doc -> typedef struct _cef_xxx_t { */
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

          // Skip any additional /// blocks (e.g., "NOTE: This struct is allocated DLL-side.")
          val searchStart = skipAdditionalDocBlocks(afterComment)

          val nextLine = lines.drop(searchStart).find(_.trim.nonEmpty).map(_.trim).getOrElse("")
          StructTypedefNameRe.findFirstMatchIn(nextLine) match {
            case Some(m) =>
              val structName = m.group(1) // e.g., "cef_cookie_t"
              loop(searchStart + 1, (structName, withSrc(comment, idx)) :: acc)
            case None =>
              loop(afterComment, acc)
          }
        } else {
          loop(idx + 1, acc)
        }
      }
    loop(0, Nil).reverse
  }

  /** Per-method type info recovered from C++ headers (bool, etc. that are coerced to int in C API). */
  case class CppMethodTypeInfo(returnType: String, paramTypes: Map[String, String])

  /** Parse C++ headers and extract method type info, keyed by method name. Recovers types lost in C API translation
    * (bool->int, etc.).
    */
  def parseCppTypeInfo(cefIncludeDir: Path): Map[String, CppMethodTypeInfo] = {
    val CppMethodWithParamsRe = """virtual\s+([\w:<>]+)\s+(\w+)\s*\(([^)]*)\)""".r

    cppHeaders(cefIncludeDir).flatMap { header =>
      val content = Files.readString(header)
      CppMethodWithParamsRe.findAllMatchIn(content).flatMap { m =>
        val retType = m.group(1).trim
        val name    = m.group(2).trim
        val params  = m.group(3).trim

        // Parse param types: "bool foo, const CefString& bar" -> Map("foo" -> "bool", "bar" -> "CefString")
        val paramTypes = if (params.isEmpty || params == "void") Map.empty[String, String]
        else params.split(",").flatMap { p =>
          val trimmed = p.trim
          // Last token is the param name, everything before is the type
          val parts = trimmed.split("\\s+")
          if (parts.length >= 2) {
            val pName = parts.last.stripPrefix("&").stripPrefix("*")
            val pType = parts.init.mkString(" ").replaceAll("\\bconst\\b", "").replaceAll("&", "").trim
            Some(pName -> pType)
          } else None
        }.toMap

        Some(name -> CppMethodTypeInfo(retType, paramTypes))
      }
    }.toMap
  }

  /** Parse enum documentation from raw (non-preprocessed) C headers. Returns (enumName -> enumDoc, enumName ->
    * Map(constantName -> constantDoc)).
    */
  def parseEnumDocs(cefIncludeDir: Path): Map[String, (String, Map[String, String])] = {
    val typesHeaders = List(
      cefIncludeDir.resolve("internal/cef_types.h"),
      cefIncludeDir.resolve("internal/cef_time.h")
    ).filter(Files.exists(_))

    typesHeaders.flatMap { header =>
      val lines = Files.readString(header).linesIterator.toVector
      extractEnumDocs(lines)
    }.toMap
  }

  private def extractEnumDocs(lines: Vector[String]): List[(String, (String, Map[String, String]))] = {
    import scala.annotation.tailrec

    @tailrec
    def loop(
        idx: Int,
        acc: List[(String, (String, Map[String, String]))]
    ): List[(String, (String, Map[String, String]))] =
      if (idx >= lines.length) acc
      else {
        val line = lines(idx).trim
        if (line.startsWith("///")) {
          // Collect comment block
          val commentLines = lines.drop(idx)
            .takeWhile(_.trim.startsWith("///"))
            .map(_.trim.stripPrefix("///").trim)
          val comment      = commentLines.mkString(" ").trim
          val afterComment = idx + commentLines.length

          // Check if next non-empty line starts an enum
          val nextLine = lines.drop(afterComment).find(_.trim.nonEmpty).map(_.trim).getOrElse("")
          if (nextLine.startsWith("typedef enum")) {
            // Parse enum body for constant docs
            val (constantDocs, enumEnd) = parseEnumConstantDocs(lines, afterComment)
            // Find enum name from closing line
            val closingLine = lines.drop(enumEnd).find(_.trim.matches("""\}\s*\w+\s*;""")).map(_.trim).getOrElse("")
            val enumName    = """\}\s*(\w+)\s*;""".r.findFirstMatchIn(closingLine).map(_.group(1)).getOrElse("")
            if (enumName.nonEmpty)
              loop(enumEnd + 1, (enumName -> (comment, constantDocs)) :: acc)
            else
              loop(enumEnd + 1, acc)
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
    import scala.annotation.tailrec
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
          // Enum constant line
          val name        = extractLeadingIdentifier(line).getOrElse("")
          val updatedDocs = if (currentDoc.nonEmpty && name.nonEmpty) docs + (name -> currentDoc) else docs
          loop(idx + 1, updatedDocs, "")
        } else {
          loop(idx + 1, docs, currentDoc)
        }
      }
    loop(startIdx, Map.empty, "")
  }

  /** Try to collect a --cef(...)-- metacomment block starting at `startIdx`. Returns (metaText, lineAfterMeta) or None
    * if not a metacomment.
    */
  private def collectMetaComment(lines: Vector[String], startIdx: Int): Option[(String, Int)] = {
    val firstLine = lines(startIdx).trim
    if (isSingleLineMetaComment(firstLine)) {
      // Single-line metacomment
      val inner = firstLine.stripPrefix("/*").stripSuffix("*/").trim
      Some((inner, startIdx + 1))
    } else if (isMetaCommentStart(firstLine)) {
      // Multi-line metacomment: collect until --*/
      val endIdx = lines.indexWhere(line => isMetaCommentEnd(line.trim), startIdx)
      if (endIdx >= 0) {
        val metaLines = lines.slice(startIdx, endIdx + 1).map(_.trim)
        val joined    = metaLines.mkString(" ")
          .stripPrefix("/*").stripSuffix("*/").trim
        Some((joined, endIdx + 1))
      } else None
    } else None
  }

  private def extractDocComments(lines: Vector[String], fileName: String = ""): List[(String, String)] = {
    def withSrc(comment: String, lineIdx: Int): String =
      if (fileName.nonEmpty) s"$comment\n@_cefsrc:$fileName:${lineIdx + 1}" else comment

    // Scan for ///... or /* ... */ blocks followed by virtual method declarations.
    // CEF headers follow the pattern: /// doc /// \n /*--cef()--*/ \n virtual ...
    // The metacomment may span multiple lines.
    @tailrec
    def loop(idx: Int, acc: List[(String, String)]): List[(String, String)] =
      if (idx >= lines.length) acc
      else {
        val line = lines(idx).trim
        if (line.startsWith("///")) {
          val (comment, afterComment) = collectComment(lines, idx)
          // Collect any --cef(...)-- meta attributes from the following block
          val (metaStr, methodSearchStart) = collectMetaComment(lines, afterComment) match {
            case Some((meta, afterMeta)) => (meta, afterMeta)
            case None                    => ("", afterComment)
          }

          val mergedComment = if (metaStr.nonEmpty) s"$comment\n$metaStr" else comment

          val methodLine = lines.drop(methodSearchStart).find(_.trim.nonEmpty).map(_.trim).getOrElse("")
          val methodName = extractMethodName(methodLine)
          methodName match {
            case Some(name) => loop(methodSearchStart, (name, withSrc(mergedComment, idx)) :: acc)
            case None       => loop(methodSearchStart, acc)
          }
        } else {
          collectMetaComment(lines, idx) match {
            case Some((meta, afterMeta)) =>
              // Standalone metacomment without preceding /// doc
              val methodLine = lines.drop(afterMeta).find(_.trim.nonEmpty).map(_.trim).getOrElse("")
              val methodName = extractMethodName(methodLine)
              methodName match {
                case Some(name) => loop(afterMeta, (name, withSrc(meta, idx)) :: acc)
                case None       => loop(afterMeta, acc)
              }
            case None if line.startsWith("/*") =>
              val (comment, afterComment) = collectComment(lines, idx)
              val methodLine              = lines.drop(afterComment).find(_.trim.nonEmpty).map(_.trim).getOrElse("")
              val methodName              = extractMethodName(methodLine)
              methodName match {
                case Some(name) => loop(afterComment, (name, withSrc(comment, idx)) :: acc)
                case None       => loop(afterComment, acc)
              }
            case None =>
              loop(idx + 1, acc)
          }
        }
      }
    loop(0, Nil).reverse
  }

  private def collectComment(lines: Vector[String], startIdx: Int): (String, Int) = {
    val firstLine = lines(startIdx).trim
    if (firstLine.startsWith("///")) {
      // Collect consecutive /// lines
      val commentLines = lines.drop(startIdx)
        .takeWhile(_.trim.startsWith("///"))
        .map(_.trim.stripPrefix("///").trim)
      (commentLines.mkString("\n"), startIdx + commentLines.length)
    } else {
      // Block comment: collect until */
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

  private def cppNameToCapiName(cppName: String): String =
    s"${CamelCaseSplitRe.replaceAllIn(cppName, "$1_$2").toLowerCase}_t"

  /** Build a map from CAPI struct name to C++ class name. e.g., "cef_domvisitor_t" -> "CefDOMVisitor", "cef_browser_t"
    * -> "CefBrowser". Also picks up data struct wrappers from cef_types_wrappers.h.
    */
  def parseCppClassNames(cefIncludeDir: Path): Map[String, String] = {
    val fromClasses = cppHeaders(cefIncludeDir).flatMap { header =>
      val content = Files.readString(header)
      CppClassDeclRe.findAllMatchIn(content).map { m =>
        val cppName  = m.group(1)
        val capiName = cppNameToCapiName(cppName)
        capiName -> cppName
      }
    }

    // Also pick up data struct wrappers: "class CefXxx : public cef_xxx_t" and "using CefXxx = CefStructBase<...>"
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

  /** Derive compound segment mappings from C++ class names and typedef aliases. Compares each C API segment (between
    * underscores) with the C++ PascalCase words to find merged segments. e.g., "CefJSDialogHandler" has capi name
    * "cef_jsdialog_handler_t" so "jsdialog" -> List("JS", "Dialog").
    */
  def deriveCompoundSegments(cefIncludeDir: Path, cppClassNames: Map[String, String]): Map[String, List[String]] = {
    // From class names: align C struct segments with PascalCase words
    val fromClasses = cppClassNames.values.flatMap { cppName =>
      val capiWords = cppNameToCapiName(cppName).stripPrefix("_").stripSuffix("_t").split("_").toList
      val cppWords  = splitPascalWords(cppName)
      alignCompoundSegments(capiWords, cppWords)
    }

    // From typedef aliases in C++ headers: "typedef cef_xxx_t AliasName;"
    val TypedefAliasRe = """typedef\s+(cef_\w+_t)\s+(\w+)\s*;""".r
    val fromTypedefs   = (cppHeaders(cefIncludeDir) :+ cefIncludeDir.resolve("internal/cef_types_wrappers.h"))
      .filter(Files.exists(_))
      .flatMap { header =>
        val content = Files.readString(header)
        TypedefAliasRe.findAllMatchIn(content).flatMap { m =>
          val cName  = m.group(1) // e.g. "cef_v8_propertyattribute_t"
          val alias  = m.group(2) // e.g. "PropertyAttribute"
          val cWords = cName.stripPrefix("cef_").stripSuffix("_t").split("_").toList
          val aWords = splitPascalWords(alias)
          alignCompoundSegments(cWords, aWords)
        }
      }

    (fromClasses ++ fromTypedefs).toMap
  }

  /** Split PascalCase (with acronyms) into words: "JSDialog" -> ["JS", "Dialog"]. */
  private def splitPascalWords(s: String): List[String] = {
    val buf    = new StringBuilder
    val result = List.newBuilder[String]
    for (i <- s.indices) {
      val c = s(i)
      if (c.isUpper && buf.nonEmpty) {
        val prev          = s(i - 1)
        val nextIsLower   = i + 1 < s.length && s(i + 1).isLower
        val startsNewWord = prev.isLower || (prev.isUpper && nextIsLower)
        if (startsNewWord) { result += buf.toString; buf.clear() }
      }
      buf += c
    }
    if (buf.nonEmpty) result += buf.toString
    result.result()
  }

  /** Align snake_case segments against PascalCase words to find compound segments (where one snake_case segment
    * corresponds to multiple PascalCase words). Returns compound entries only.
    */
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
            case None             => go(cs, cpp, acc) // skip unmatched capi word (e.g. prefix not in alias)
          }
      }
    go(capiWords, cppWords, Nil)
  }

  // -- Immutable state for struct parsing --
  private case class StructState(
      fnPtrs: List[FnPtr],
      fields: List[Field],
      hasBase: Boolean,
      isScoped: Boolean,
      idx: Int
  )

  private def parseStruct(
      lines: Vector[String],
      startIdx: Int,
      handlerNames: Set[String],
      dataStructNames: Set[String]
  ): (CefDecl, Int) = {

    @tailrec
    def loop(state: StructState): StructState =
      if (state.idx >= lines.length || StructClosingRe.matches(lines(state.idx))) {
        state
      } else {
        val line = lines(state.idx)
        if (BaseFieldRe.matches(line)) {
          val scoped = ScopedBaseRe.matches(line)
          loop(state.copy(hasBase = true, isScoped = scoped, idx = state.idx + 1))
        } else if (isFnPtrLine(line)) {
          val fnText = collectFnPtr(lines, state.idx)
          val newFns = parseFnPtr(fnText, dataStructNames).fold(state.fnPtrs)(_ :: state.fnPtrs)
          loop(state.copy(fnPtrs = newFns, idx = skipPastSemicolon(lines, state.idx)))
        } else if (line.nonEmpty && !line.startsWith("//") && !line.startsWith("/*") && line != "{") {
          val newFields = parseField(line, dataStructNames).fold(state.fields)(_ :: state.fields)
          loop(state.copy(fields = newFields, idx = state.idx + 1))
        } else {
          loop(state.copy(idx = state.idx + 1))
        }
      }

    val result = loop(StructState(Nil, Nil, hasBase = false, isScoped = false, startIdx + 1))

    val closingLine = if (result.idx < lines.length) lines(result.idx) else ""
    val structName  = """\}\s*(cef_\w+_t)\s*;""".r
      .findFirstMatchIn(closingLine)
      .map(_.group(1))
      .getOrElse("unknown_t")

    val decl = if (!result.hasBase && result.fields.nonEmpty) {
      CefDecl.DataStruct(structName, result.fields.reverse)
    } else if (handlerNames.contains(structName)) {
      CefDecl.HandlerStruct(structName, result.fnPtrs.reverse.map(classifySpecial))
    } else {
      CefDecl.ObjectStruct(structName, result.fnPtrs.reverse.map(classifySpecial), scoped = result.isScoped)
    }

    (decl, result.idx + 1)
  }

  private def collectFnPtr(lines: Vector[String], startIdx: Int): String = {
    @tailrec
    def loop(i: Int, acc: String, depth: Int, foundOpen: Boolean): String =
      if (i >= lines.length) acc.trim
      else {
        val line                 = lines(i)
        val newAcc               = acc + line + " "
        val (newDepth, newFound) = line.foldLeft((depth, foundOpen)) {
          case ((d, fo), '(') => (d + 1, true)
          case ((d, fo), ')') => (d - 1, fo)
          case (s, _)         => s
        }
        if (newFound && newDepth <= 0 && line.contains(";")) newAcc.trim
        else loop(i + 1, newAcc, newDepth, newFound)
      }
    loop(startIdx, "", 0, false)
  }

  @tailrec
  private def skipPastSemicolon(lines: Vector[String], idx: Int): Int =
    if (idx >= lines.length || lines(idx).contains(";")) idx + 1
    else skipPastSemicolon(lines, idx + 1)

  private def isFnPtrLine(line: String): Boolean =
    line.contains("CEF_CALLBACK*") || line.contains("CEF_CALLBACK *") ||
      FnPtrLineRe.findFirstIn(line).isDefined

  // Matches both `ret (CEF_CALLBACK* name)(params);` and `ret(* name)(params);`
  private val FnPtrPattern =
    """(.+?)\s*\(\s*(?:CEF_CALLBACK\s*)?\*\s*(\w+)\s*\)\s*\((.+)\)\s*;""".r

  private def parseFnPtr(text: String, dataStructNames: Set[String]): Option[FnPtr] = {
    val normalized = text.replaceAll("\\s+", " ").trim
    FnPtrPattern.findFirstMatchIn(normalized).map { m =>
      val retStr    = m.group(1).trim
      val name      = m.group(2).trim
      val paramsStr = m.group(3).trim

      val ret           = parseType(retStr, dataStructNames)
      val params        = parseParams(paramsStr, dataStructNames)
      val nonSelfParams = if (params.nonEmpty) params.tail else params

      FnPtr(name, ret, nonSelfParams)
    }
  }

  private def parseParams(paramsStr: String, dataStructNames: Set[String]): List[Param] =
    if (paramsStr.isBlank || paramsStr == "void") Nil
    else {
      splitParams(paramsStr).flatMap { part =>
        val trimmed = part.trim
        if (trimmed.isEmpty || trimmed == "void") None
        else {
          val (typ, name) = splitTypeName(trimmed)
          val trimTyp     = typ.trim
          val isConst     = trimTyp.startsWith("const ") || trimTyp.matches(""".*\bconst\s*\*""")
          Some(Param(name, parseType(typ, dataStructNames), isConst = isConst, rawCType = typ.trim))
        }
      }
    }

  private def splitParams(s: String): List[String] =
    s.split(",").iterator.map(_.trim).filter(_.nonEmpty).toList

  private def splitTypeName(s: String): (String, String) = {
    val trimmed      = s.trim
    val withoutArray = trimmed.replaceAll("\\[\\d+\\]", "").trim
    val lastSpace    = withoutArray.lastIndexOf(' ')
    if (lastSpace < 0) {
      (withoutArray, "unnamed")
    } else {
      val rawType = withoutArray.substring(0, lastSpace + 1).trim
      val rawName = withoutArray.substring(lastSpace + 1).trim
      if (rawName.startsWith("*")) {
        (rawType + rawName.takeWhile(_ == '*'), rawName.dropWhile(_ == '*'))
      } else {
        (rawType, rawName)
      }
    }
  }

  private def parseType(typeStr: String, dataStructNames: Set[String]): CType = {
    val s = typeStr.trim
      .replaceAll("\\bstruct\\b", "")
      .replaceAll("\\s+", " ")
      .replaceAll("\\b_(?=cef_)", "") // strip leading _ prefix from _cef_xxx_t
      .trim

    s match {
      case "void"                                       => CType.Void
      case "int"                                        => CType.Int
      case "unsigned int" | "uint32_t"                  => CType.UInt
      case "int64_t" | "int64" | "long long"            => CType.Long
      case "uint64_t" | "uint64" | "unsigned long long" => CType.Long
      case "size_t"                                     => CType.SizeT
      case "char16_t" | "char16"                        => CType.Char
      case "float"                                      => CType.Float
      case "double"                                     => CType.Double
      case "cef_string_t"                               => CType.JString
      case "cef_string_t*" | "const cef_string_t*"      => CType.JString
      case "cef_string_userfree_t"                      => CType.JString
      case "const void*" | "void*"                      => CType.OpaquePtr
      case t if t.endsWith("**") => CType.Ptr(t.stripSuffix("*").trim) // double pointers — reclassify resolves later
      case "char16_t*" | "const char16_t*" => CType.OpaquePtr // raw char pointers
      case "char*" | "const char*"         => CType.OpaquePtr
      case "wchar_t*" | "const wchar_t*"   => CType.OpaquePtr
      case "cef_string_list_t"             => CType.StringList
      case "cef_string_map_t"              => CType.StringMap
      case "cef_string_multimap_t"         => CType.StringMultimap
      // Platform-specific opaque handles - always map to long (pointer-sized)
      case "cef_window_handle_t" | "cef_cursor_handle_t" | "cef_event_handle_t"
          | "cef_platform_thread_id_t" | "cef_platform_thread_handle_t" => CType.Long
      case "cef_color_t"                                                             => CType.UInt
      case t if ByValueStructs.exists(g => t == s"const ${g}*" || t == s"$g const*") =>
        val cefName = ByValueStructs.find(g => t.contains(g)).get
        CType.ByValueIn(cefName)
      case t if ByValueStructs.exists(g => t == s"${g}*") =>
        val cefName = ByValueStructs.find(g => t.contains(g)).get
        CType.ByValueOut(cefName)
      case "int*" | "const int*" => CType.OutInt
      case t if t.endsWith("*")  =>
        CType.Ptr(t.stripSuffix("*").trim)
      case t if t.matches("cef_\\w+_t") && !t.contains("string") && !dataStructNames.contains(t) =>
        CType.Enum(t)
      case t if dataStructNames.contains(t) =>
        CType.DataStruct(t)
      case _ =>
        CType.Int
    }
  }

  private def parseField(line: String, dataStructNames: Set[String]): Option[Field] = {
    val trimmed = line.stripSuffix(";").trim
    if (trimmed.isEmpty) None
    else {
      val (typ, name) = splitTypeName(trimmed)
      if (name.isEmpty || name == "unnamed") None
      else Some(Field(name, parseType(typ, dataStructNames)))
    }
  }

  /** Promote ByValueIn to ByValueArray when preceded by a count parameter.
    *
    * CEF naming convention: the count param is named `{arrayParam}Count` (e.g., `dirtyRectsCount` for `dirtyRects`). We
    * require this naming match to avoid false positives (e.g., `count` before `selectionRect` in on_find_result).
    */
  private def promoteByValueArrays(fn: FnPtr): FnPtr = {
    val promoted = fn.params.zipWithIndex.map { case (p, i) =>
      p.typ match {
        case CType.ByValueIn(cefName) if i > 0 =>
          val prev           = fn.params(i - 1)
          val isCountForThis = (prev.typ == CType.SizeT || prev.typ == CType.Int) &&
            prev.name.toLowerCase == s"${p.name.toLowerCase}count"
          if (isCountForThis) p.copy(typ = CType.ByValueArray(cefName))
          else p
        case _ => p
      }
    }
    fn.copy(params = promoted)
  }

  private def classifySpecial(fn: FnPtr): FnPtr = {
    val promoted = promoteByValueArrays(fn)
    if (promoted.name == "on_paint" && promoted.params.exists(p => p.name == "buffer")) {
      // Upgrade the `buffer` param (const void*) to PixelBuffer for OnPaint
      val upgradedParams = promoted.params.map {
        case p if p.name == "buffer" && p.typ == CType.OpaquePtr =>
          p.copy(typ = CType.PixelBuffer)
        case p => p
      }
      promoted.copy(isSpecial = Some(SpecialFn.OnPaint), params = upgradedParams)
    } else if (promoted.name == "on_accelerated_paint") {
      promoted.copy(isSpecial = Some(SpecialFn.OnAcceleratedPaint))
    } else {
      promoted
    }
  }

  /** Pre-scan lines to find data struct names (structs without cef_base_ref_counted_t base). This lets parseType
    * distinguish data structs from enums for non-pointer cef_xxx_t types.
    */
  private def prescanDataStructs(lines: Vector[String]): Set[String] = {
    @tailrec
    def scan(idx: Int, acc: Set[String]): Set[String] =
      if (idx >= lines.length) acc
      else if (StructOpenRe.findFirstIn(lines(idx)).isDefined) {
        val (hasBase, name, endIdx) = scanStruct(lines, idx + 1)
        if (!hasBase && name.nonEmpty) scan(endIdx, acc + name)
        else scan(endIdx, acc)
      } else scan(idx + 1, acc)

    def scanStruct(lines: Vector[String], startIdx: Int): (Boolean, String, Int) = {
      @tailrec
      def loop(i: Int, hasBase: Boolean): (Boolean, String, Int) =
        if (i >= lines.length) (hasBase, "", i)
        else StructCloseRe.findFirstMatchIn(lines(i)) match {
          case Some(m) => (hasBase, m.group(1), i + 1)
          case None    => loop(i + 1, hasBase || BaseFieldRe.matches(lines(i)))
        }
      loop(startIdx, false)
    }

    scan(0, Set.empty)
  }

  // -- Immutable state for enum parsing --
  private case class EnumState(values: List[(String, Long, String)], lastValue: Long, idx: Int)

  private def parseEnum(lines: Vector[String], startIdx: Int): (CefDecl, Int) = {

    @tailrec
    def loop(state: EnumState): EnumState =
      if (state.idx >= lines.length || EnumClosingRe.matches(lines(state.idx))) {
        state
      } else {
        val line      = lines(state.idx).trim.stripSuffix(",").trim
        val nextState = if (line.nonEmpty && !line.startsWith("//") && !line.startsWith("/*") && line != "{") {
          line match {
            case s"$name = $expr" if isIdentifier(name.trim) =>
              val cleanName = name.trim
              val rawExpr   = expr.trim
              val v         = parseEnumValue(rawExpr, state.values)
              state.copy(values = (cleanName, v, rawExpr) :: state.values, lastValue = v)
            case name if isIdentifier(name) && name.head.isUpper =>
              val v = state.lastValue + 1
              state.copy(values = (name, v, s"$v") :: state.values, lastValue = v)
            case _ => state
          }
        } else {
          state
        }
        loop(nextState.copy(idx = nextState.idx + 1))
      }

    val result = loop(EnumState(Nil, -1L, startIdx + 1))

    val closingLine = if (result.idx < lines.length) lines(result.idx) else ""
    val enumName    = """\}\s*(\w+)\s*;""".r
      .findFirstMatchIn(closingLine)
      .map(_.group(1))
      .getOrElse("unknown_t")

    (CefDecl.Enum(enumName, result.values.reverse), result.idx + 1)
  }

  private def parseEnumValue(expr: String, existing: List[(String, Long, String)]): Long = {
    val trimmed = expr.trim
    if (trimmed.startsWith("0x") || trimmed.startsWith("0X")) {
      java.lang.Long.parseLong(trimmed.substring(2), 16)
    } else if (trimmed.startsWith("-")) {
      -parseEnumValue(trimmed.substring(1), existing)
    } else {
      trimmed.toLongOption.getOrElse {
        val shiftPattern = """(\w+)\s*<<\s*(\d+)""".r
        trimmed match {
          case shiftPattern(base, shift) =>
            val baseVal = existing.find(_._1 == base).map(_._2).getOrElse(
              parseEnumValue(base, existing)
            )
            baseVal << shift.toInt
          case _ if trimmed.contains("|") =>
            trimmed.split("\\|").map(p => parseEnumValue(p.trim, existing)).reduce(_ | _)
          case _ =>
            existing.find(_._1 == trimmed).map(_._2).getOrElse(0L)
        }
      }
    }
  }

  // -- Free function parsing --

  /** Explicit mapping for free functions that don't follow return-type or name-prefix conventions. */
  private val ExplicitOwnerMap: Map[String, String] = Map(
    "cef_browser_host_create_browser"               -> "cef_browser_host_t",
    "cef_browser_host_create_browser_sync"          -> "cef_browser_host_t",
    "cef_browser_host_get_browser_by_identifier"    -> "cef_browser_host_t",
    "cef_v8_context_get_current_context"            -> "cef_v8_context_t",
    "cef_v8_context_get_entered_context"            -> "cef_v8_context_t",
    "cef_v8_context_in_context"                     -> "cef_v8_context_t",
    "cef_request_context_cef_create_context_shared" -> "cef_request_context_t"
  )

  /** Parse CEF_EXPORT free functions from raw (non-preprocessed) CAPI headers. Returns FreeFunction decls with initial
    * CType.Ptr types (reclassification happens later).
    */
  def parseFreeExports(
      capiDir: Path,
      knownStructNames: Set[String],
      dataStructNames: Set[String]
  ): List[CefDecl.FreeFunction] = {
    val capiHeaders = Files.list(capiDir).toScala(List)
      .filter(p => p.toString.endsWith("_capi.h") && !p.toString.contains("test/"))

    capiHeaders.flatMap { header =>
      val headerName = header.getFileName.toString
      val lines      = Files.readString(header).linesIterator.toVector
      parseFreeExportsFromFile(lines, headerName, knownStructNames, dataStructNames)
    }
  }

  private val CefExportRe = """CEF_EXPORT\s+(.+)""".r

  private def parseFreeExportsFromFile(
      lines: Vector[String],
      headerName: String,
      knownStructNames: Set[String],
      dataStructNames: Set[String]
  ): List[CefDecl.FreeFunction] = {
    // Track struct block depth to skip CEF_EXPORT-like lines inside structs
    var inStructBlock = false
    var braceDepth    = 0
    val result        = List.newBuilder[CefDecl.FreeFunction]

    var i = 0
    while (i < lines.length) {
      val line = lines(i).trim

      // Track typedef struct blocks
      if (StructTypedefRe.findFirstIn(line).isDefined) {
        inStructBlock = true
        braceDepth = 0
      }
      if (inStructBlock) {
        braceDepth += line.count(_ == '{') - line.count(_ == '}')
        if (braceDepth <= 0 && line.contains("}")) {
          inStructBlock = false
        }
        i += 1
      } else if (line.startsWith("CEF_EXPORT")) {
        // Collect the full declaration (may span multiple lines)
        val sb = new StringBuilder(line)
        var j  = i + 1
        while (!sb.toString.contains(";") && j < lines.length) {
          sb.append(" ").append(lines(j).trim)
          j += 1
        }
        val fullDecl = sb.toString.replaceAll("\\s+", " ").trim

        parseFreeFunction(fullDecl, headerName, knownStructNames, dataStructNames).foreach(result += _)
        i = j
      } else {
        i += 1
      }
    }
    result.result()
  }

  /** Parse a single CEF_EXPORT declaration into a FreeFunction. */
  private val FreeFuncPattern =
    """CEF_EXPORT\s+(.+?)\s+(\w+)\s*\(([^)]*)\)\s*;""".r

  private def parseFreeFunction(
      decl: String,
      headerName: String,
      knownStructNames: Set[String],
      dataStructNames: Set[String]
  ): Option[CefDecl.FreeFunction] =
    FreeFuncPattern.findFirstMatchIn(decl).flatMap { m =>
      val retStr   = m.group(1).trim
      val cName    = m.group(2).trim
      val paramStr = m.group(3).trim

      // Skip string utility functions
      if (cName.startsWith("cef_string_") || cName.startsWith("cef_time_")) return None

      val ret    = parseType(retStr, dataStructNames)
      val params = parseParams(paramStr, dataStructNames)

      // Determine owner struct
      val ownerStruct = resolveOwnerStruct(cName, ret, knownStructNames)

      // Compute Java method name by stripping the owner prefix
      val javaMethodName = computeJavaMethodName(cName, ownerStruct)

      Some(CefDecl.FreeFunction(
        cName = cName,
        ret = ret,
        params = params,
        ownerStruct = ownerStruct,
        javaMethodName = javaMethodName,
        sourceHeader = headerName
      ))
    }

  /** Resolve which struct a free function belongs to. */
  private def resolveOwnerStruct(cName: String, ret: CType, knownStructNames: Set[String]): String = {
    // 1. Explicit mapping
    ExplicitOwnerMap.get(cName) match {
      case Some(owner) => return owner
      case None        =>
    }

    // 2. Return type match: if returns cef_xxx_t*, it belongs on that struct
    ret match {
      case CType.Ptr(inner) =>
        val stripped = inner.stripPrefix("struct ").stripPrefix("_").trim
        if (knownStructNames.contains(stripped)) return stripped
      case CType.ObjectPtr(name) if knownStructNames.contains(name) =>
        return name
      case _ =>
    }

    // 3. Name prefix match: try longest matching struct prefix
    //    cef_browser_host_create_browser -> check cef_browser_host_t, cef_browser_t, etc.
    val candidates = knownStructNames.filter { structName =>
      val prefix = structName.stripSuffix("_t")
      cName.startsWith(prefix + "_")
    }
    if (candidates.nonEmpty) {
      // Pick the longest prefix match (most specific)
      return candidates.maxBy(_.length)
    }

    // 4. Orphan — goes to CefGlobals
    ""
  }

  /** Compute the Java method name by stripping the owner prefix. */
  private def computeJavaMethodName(cName: String, ownerStruct: String): String = {
    val prefix = if (ownerStruct.nonEmpty) ownerStruct.stripSuffix("_t") + "_"
    else "cef_"
    val stripped = if (cName.startsWith(prefix)) cName.stripPrefix(prefix) else cName.stripPrefix("cef_")
    Naming.toCamelCase(stripped)
  }

  /** Primitive types that can appear as out-param pointers (e.g. `int64_t*`, `float*`, `cef_color_t*`). */
  private val PrimitiveOutPtrMap: Map[String, CType] = Map(
    "int64_t"     -> CType.Long,
    "int64"       -> CType.Long,
    "uint64_t"    -> CType.Long,
    "long long"   -> CType.Long,
    "float"       -> CType.Float,
    "double"      -> CType.Double,
    "cef_color_t" -> CType.UInt,
    "size_t"      -> CType.SizeT
  )

  /** Post-process all declarations to reclassify CType.Ptr into specific typed variants.
    *
    * Called after all files are parsed so that the full set of object/handler struct names is known.
    */
  def reclassifyPointers(decls: List[CefDecl], handlerNames: Set[String]): List[CefDecl] = {
    val objectStructNames = decls.collect {
      case d: CefDecl.ObjectStruct  => d.name
      case d: CefDecl.HandlerStruct => d.name
    }.toSet
    val dataStructNames = decls.collect { case d: CefDecl.DataStruct => d.name }.toSet

    /** Strip all qualifiers and prefixes to get the bare cef type name. */
    def stripToBareName(s: String): String =
      s.replaceAll("\\bconst\\b", "").replaceAll("\\bstruct\\b", "").replaceAll("\\*", "")
        .stripPrefix("_").trim

    def reclassifyType(ct: CType): CType = ct match {
      case CType.Ptr(inner) =>
        val stripped = inner.stripPrefix("const ").stripPrefix("struct ").stripPrefix("_")
          .stripSuffix("const").trim
        if (stripped.endsWith("*")) {
          // Double pointer or const-qualified pointer array
          val bare = stripToBareName(stripped)
          if (objectStructNames.contains(bare))
            CType.OutObjectPtr(bare)
          else CType.OpaquePtr // Treat unknown double pointers as opaque
        } else if (objectStructNames.contains(stripped))
          CType.ObjectPtr(stripped)
        else if (stripped == "void" || stripped.isEmpty)
          CType.OpaquePtr
        else if (OpaqueStructs.contains(stripped))
          CType.OpaquePtr
        else if (dataStructNames.contains(stripped))
          CType.OpaquePtr // Data struct pointers not in ByValueStructs → opaque for now
        else
          PrimitiveOutPtrMap.get(stripped) match {
            case Some(prim) => CType.OutPrimitivePtr(prim)
            case None       =>
              // Try stripping more aggressively for const-qualified types
              val bare = stripToBareName(inner)
              if (objectStructNames.contains(bare)) CType.ObjectPtr(bare)
              else if (dataStructNames.contains(bare)) CType.OpaquePtr
              else CType.OpaquePtr // Fallback to opaque — will be logged
          }
      case other => other
    }

    def reclassifyFn(fn: FnPtr): FnPtr =
      fn.copy(ret = reclassifyType(fn.ret), params = fn.params.map(p => p.copy(typ = reclassifyType(p.typ))))

    decls.map {
      case d: CefDecl.ObjectStruct  => d.copy(fns = d.fns.map(reclassifyFn))
      case d: CefDecl.HandlerStruct => d.copy(fns = d.fns.map(reclassifyFn))
      case d: CefDecl.FreeFunction  =>
        d.copy(ret = reclassifyType(d.ret), params = d.params.map(p => p.copy(typ = reclassifyType(p.typ))))
      case other => other
    }
  }

  /** Promote OutObjectPtr params preceded by a count param to ObjectPtrArray, and promote OutPrimitivePtr arrays
    * similarly. Also handles the count_func metacomment pattern.
    */
  def promoteArrayParams(decls: List[CefDecl]): List[CefDecl] = {
    def promote(fn: FnPtr): FnPtr = {
      val promoted = fn.params.zipWithIndex.map { case (p, i) =>
        p.typ match {
          case CType.OutObjectPtr(cefName) if i > 0 =>
            val prev           = fn.params(i - 1)
            val isCountForThis =
              (prev.typ == CType.SizeT || prev.typ == CType.OutPrimitivePtr(CType.SizeT)) &&
                prev.name.toLowerCase == s"${p.name.toLowerCase}count"
            if (isCountForThis) p.copy(typ = CType.ObjectPtrArray(cefName))
            else p
          case _ => p
        }
      }
      fn.copy(params = promoted)
    }

    decls.map {
      case d: CefDecl.FreeFunction =>
        val promoted = promote(FnPtr("_ff", d.ret, d.params))
        d.copy(params = promoted.params)
      case other => other.mapFns(promote)
    }
  }

  /** Promote OpaquePtr params whose rawCType is void-ptr or const-void-ptr and that have an adjacent size param to
    * Buffer/BufferSize pairs. The size param is hidden from the Java API — its value is derived from
    * ByteBuffer.capacity() on the JNI side.
    */
  def promoteBufferParams(decls: List[CefDecl]): List[CefDecl] = {
    def isSizeType(ct: CType): Boolean = ct match {
      case CType.SizeT | CType.Long | CType.Int => true
      case _                                    => false
    }

    def isVoidPtr(p: Param): Boolean =
      p.typ == CType.OpaquePtr && p.rawCType.replaceAll("\\s+", " ").trim.matches("(const )?void\\s*\\*")

    def findSizeParam(bufName: String, params: List[Param]): Option[String] = {
      val lower = bufName.toLowerCase
      params.collectFirst {
        case p
            if isSizeType(p.typ) && (
              p.name.toLowerCase == s"${lower}size" ||
                p.name.toLowerCase == s"${lower}_size" ||
                p.name.toLowerCase == s"${lower}length" ||
                p.name.toLowerCase == s"${lower}_length" ||
                // Generic "size" for params named "bytes", "buffer", "data"
                (Set("bytes", "buffer", "data").contains(lower) && p.name.toLowerCase == "size")
            ) =>
          p.name
      }
    }

    def promote(fn: FnPtr): FnPtr = {
      // First pass: identify buffer+size pairs
      val bufferSizePairs = fn.params.collect {
        case p if isVoidPtr(p) => findSizeParam(p.name, fn.params).map(sp => (p.name, sp))
      }.flatten.toMap

      if (bufferSizePairs.isEmpty) return fn

      val sizeToBuffer = bufferSizePairs.map(_.swap)
      val promoted     = fn.params.map { p =>
        if (bufferSizePairs.contains(p.name))
          p.copy(typ = CType.Buffer(bufferSizePairs(p.name)))
        else if (sizeToBuffer.contains(p.name))
          p.copy(typ = CType.BufferSize(sizeToBuffer(p.name)))
        else p
      }
      fn.copy(params = promoted)
    }

    decls.map {
      case d: CefDecl.FreeFunction =>
        val promoted = promote(FnPtr("_ff", d.ret, d.params))
        d.copy(params = promoted.params)
      case other => other.mapFns(promote)
    }
  }
}
