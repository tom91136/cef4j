package net.kurobako.cef4j.codegen

import java.nio.file.Files
import java.nio.file.Path
import scala.annotation.tailrec
import scala.jdk.StreamConverters._

object CHeaderParser {

  /** Structs marshaled by value between Java and native. */
  val ByValueStructs: Set[String] =
    Set("cef_rect_t", "cef_point_t", "cef_size_t", "cef_insets_t", "cef_range_t", "cef_screen_info_t")

  def isByValueStruct(name: String): Boolean = ByValueStructs.contains(name)

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
        if (line.matches("""typedef\s+struct\s+_cef_\w+_t\s*\{""")) {
          val (decl, next) = parseStruct(lines, state.idx, handlerNames, dataStructNames)
          loop(TopState(decl :: state.decls, next))
        } else if (line.matches("""typedef\s+enum\s*\{""")) {
          val (decl, next) = parseEnum(lines, state.idx)
          loop(TopState(decl :: state.decls, next))
        } else {
          loop(state.copy(idx = state.idx + 1))
        }
      }

    loop(TopState(Nil, 0)).decls.reverse
  }

  def parseHandlerAnnotations(cefIncludeDir: Path): Set[String] = {
    val cppHeaders = Files.list(cefIncludeDir)
      .toScala(List)
      .filter(p => p.toString.endsWith(".h") && !p.toString.contains("capi"))

    cppHeaders.flatMap { header =>
      val lines = Files.readString(header).linesIterator.toVector
      lines.zipWithIndex.collect {
        case (line, j) if line.contains("source=client") =>
          lines.drop(j + 1)
            .collectFirst {
              case l if """class\s+(\w+)\s*:""".r.findFirstMatchIn(l).isDefined =>
                val m = """class\s+(\w+)\s*:""".r.findFirstMatchIn(l).get
                cppNameToCapiName(m.group(1))
            }
      }.flatten
    }.toSet
  }

  /** Extract Javadoc-style comments from C++ headers, keyed by function name. Also indexes by capi_name when present
    * (e.g., Continue -> cont).
    */
  def parseDocComments(cefIncludeDir: Path): Map[String, String] = {
    val cppHeaders = Files.list(cefIncludeDir)
      .toScala(List)
      .filter(p => p.toString.endsWith(".h") && !p.toString.contains("capi") && !p.toString.contains("internal"))

    val entries = cppHeaders.flatMap { header =>
      val lines = Files.readString(header).linesIterator.toVector
      extractDocComments(lines)
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
      .flatMap(h => extractClassDocs(Files.readString(h).linesIterator.toVector))

    val structDocs = allHeaders
      .filter(p => p.toString.contains("capi") || p.toString.contains("internal"))
      .flatMap(h => extractStructDocs(Files.readString(h).linesIterator.toVector))

    // C++ class docs win over capi/internal typedef docs
    (structDocs ++ cppDocs).toMap
  }

  /** Extract class docs from C++ headers: /// doc -> /*--cef()--*/ -> class Name : */
  private def extractClassDocs(lines: Vector[String]): List[(String, String)] = {
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
          val classMatch = """class\s+(\w+)\s*:""".r.findFirstMatchIn(nextLine)
          classMatch match {
            case Some(m) =>
              val capiName = cppNameToCapiName(m.group(1))
              loop(searchStart + 1, (capiName, comment) :: acc)
            case None =>
              loop(afterComment, acc)
          }
        } else {
          loop(idx + 1, acc)
        }
      }
    loop(0, Nil).reverse
  }

  /** Extract struct docs from C/internal headers: /// doc -> typedef struct _cef_xxx_t { */
  private def extractStructDocs(lines: Vector[String]): List[(String, String)] = {
    val StructTypedefRe = """typedef\s+struct\s+_(\w+)\s*\{""".r

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
          StructTypedefRe.findFirstMatchIn(nextLine) match {
            case Some(m) =>
              val structName = m.group(1) // e.g., "cef_cookie_t"
              loop(searchStart + 1, (structName, comment) :: acc)
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
    val cppHeaders = Files.list(cefIncludeDir)
      .toScala(List)
      .filter(p => p.toString.endsWith(".h") && !p.toString.contains("capi") && !p.toString.contains("internal"))

    val CppMethodRe = """virtual\s+([\w:<>]+)\s+(\w+)\s*\(([^)]*)\)""".r

    cppHeaders.flatMap { header =>
      val content = Files.readString(header)
      CppMethodRe.findAllMatchIn(content).flatMap { m =>
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

  private def extractDocComments(lines: Vector[String]): List[(String, String)] = {
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
            case Some(name) => loop(methodSearchStart, (name, mergedComment) :: acc)
            case None       => loop(methodSearchStart, acc)
          }
        } else {
          collectMetaComment(lines, idx) match {
            case Some((meta, afterMeta)) =>
              // Standalone metacomment without preceding /// doc
              val methodLine = lines.drop(afterMeta).find(_.trim.nonEmpty).map(_.trim).getOrElse("")
              val methodName = extractMethodName(methodLine)
              methodName match {
                case Some(name) => loop(afterMeta, (name, meta) :: acc)
                case None       => loop(afterMeta, acc)
              }
            case None if line.startsWith("/*") =>
              val (comment, afterComment) = collectComment(lines, idx)
              val methodLine              = lines.drop(afterComment).find(_.trim.nonEmpty).map(_.trim).getOrElse("")
              val methodName              = extractMethodName(methodLine)
              methodName match {
                case Some(name) => loop(afterComment, (name, comment) :: acc)
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
    // Match: virtual RetType<...> MethodName(...) - handles CefRefPtr<T>, bool, void, etc.
    """virtual\s+[\w:<>]+\s+(\w+)\s*\(""".r.findFirstMatchIn(line).map(_.group(1))
      .orElse {
        // Match: RetType<...> MethodName(...) - non-virtual member
        """[\w:<>]+\s+(\w+)\s*\(""".r.findFirstMatchIn(line).map(_.group(1))
      }

  private def cppNameToCapiName(cppName: String): String = {
    val snake = cppName.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase
    s"${snake}_t"
  }

  // -- Immutable state for struct parsing --
  private case class StructState(
      fnPtrs: List[FnPtr],
      fields: List[Field],
      hasBase: Boolean,
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
      if (state.idx >= lines.length || lines(state.idx).matches("""\}\s*cef_\w+_t\s*;""")) {
        state
      } else {
        val line = lines(state.idx)
        if (line.contains("cef_base_ref_counted_t") && line.contains("base")) {
          loop(state.copy(hasBase = true, idx = state.idx + 1))
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

    val result = loop(StructState(Nil, Nil, hasBase = false, startIdx + 1))

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
      CefDecl.ObjectStruct(structName, result.fnPtrs.reverse.map(classifySpecial))
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

  /** Detect function pointer lines - both pre-preprocessed (CEF_CALLBACK*) and post-preprocessed ((* name)) */
  private def isFnPtrLine(line: String): Boolean =
    line.contains("CEF_CALLBACK*") || line.contains("CEF_CALLBACK *") ||
      // Post-preprocessed: `type(* name)(` or `type (* name)(`
      """[a-z_*]\s*\(\s*\*\s*\w+\s*\)""".r.findFirstIn(line).isDefined

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

  private def splitParams(s: String): List[String] = {
    val (parts, last) = s.foldLeft((List.empty[String], "")) {
      case ((acc, cur), ',') => (acc :+ cur, "")
      case ((acc, cur), ch)  => (acc, cur + ch)
    }
    (parts :+ last).map(_.trim).filter(_.nonEmpty)
  }

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
      .trim

    s match {
      case "void"                                       => CType.Void
      case "int"                                        => CType.Int
      case "unsigned int" | "uint32_t"                  => CType.UInt
      case "int64_t" | "int64" | "long long"            => CType.Long
      case "uint64_t" | "uint64" | "unsigned long long" => CType.Long
      case "size_t"                                     => CType.SizeT
      case "float"                                      => CType.Float
      case "double"                                     => CType.Double
      case "cef_string_t*" | "const cef_string_t*"      => CType.JString
      case "cef_string_userfree_t"                      => CType.JString
      case "const void*"                                => CType.Ptr("const void")
      case "cef_string_list_t"                          => CType.StringList
      case "cef_string_map_t"                           => CType.StringMap
      case "cef_string_multimap_t"                      => CType.StringMultimap
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
        case p if p.name == "buffer" && p.typ == CType.Ptr("const void") =>
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
    val StructOpen  = """typedef\s+struct\s+_cef_\w+_t\s*\{""".r
    val StructClose = """\}\s*(cef_\w+_t)\s*;""".r

    @tailrec
    def scan(idx: Int, acc: Set[String]): Set[String] =
      if (idx >= lines.length) acc
      else if (StructOpen.findFirstIn(lines(idx)).isDefined) {
        // Scan forward to closing brace, checking for base_ref_counted
        val (hasBase, name, endIdx) = scanStruct(lines, idx + 1)
        if (!hasBase && name.nonEmpty) scan(endIdx, acc + name)
        else scan(endIdx, acc)
      } else scan(idx + 1, acc)

    def scanStruct(lines: Vector[String], startIdx: Int): (Boolean, String, Int) = {
      @tailrec
      def loop(i: Int, hasBase: Boolean): (Boolean, String, Int) =
        if (i >= lines.length) (hasBase, "", i)
        else {
          val line = lines(i)
          StructClose.findFirstMatchIn(line) match {
            case Some(m) => (hasBase, m.group(1), i + 1)
            case None    =>
              val newBase = hasBase || line.contains("cef_base_ref_counted_t")
              loop(i + 1, newBase)
          }
        }
      loop(startIdx, false)
    }

    scan(0, Set.empty)
  }

  // -- Immutable state for enum parsing --
  private case class EnumState(values: List[(String, Long)], lastValue: Long, idx: Int)

  private def parseEnum(lines: Vector[String], startIdx: Int): (CefDecl, Int) = {

    @tailrec
    def loop(state: EnumState): EnumState =
      if (state.idx >= lines.length || lines(state.idx).matches("""\}\s*\w+\s*;""")) {
        state
      } else {
        val line      = lines(state.idx).trim.stripSuffix(",").trim
        val nextState = if (line.nonEmpty && !line.startsWith("//") && !line.startsWith("/*") && line != "{") {
          line match {
            case s"$name = $expr" if isIdentifier(name.trim) =>
              val cleanName = name.trim
              val v         = parseEnumValue(expr.trim, state.values)
              state.copy(values = (cleanName, v) :: state.values, lastValue = v)
            case name if isIdentifier(name) && name.head.isUpper =>
              val v = state.lastValue + 1
              state.copy(values = (name, v) :: state.values, lastValue = v)
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

  private def parseEnumValue(expr: String, existing: List[(String, Long)]): Long = {
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
}
