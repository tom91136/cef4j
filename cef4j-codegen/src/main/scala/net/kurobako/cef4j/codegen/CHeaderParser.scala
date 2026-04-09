package net.kurobako.cef4j.codegen

import java.nio.file.Files
import java.nio.file.Path
import scala.annotation.tailrec
import scala.jdk.StreamConverters._

object CHeaderParser {

  // Structs marshalled by value between Java and native.
  private val ByValueStructs: Set[String] =
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
      "cef_pdf_print_settings_t",
      "cef_settings_t",
      "cef_request_context_settings_t",
      "cef_box_layout_settings_t"
    )

  def isByValueStruct(name: String): Boolean = ByValueStructs.contains(name)

  private def isIdentifier(value: String): Boolean =
    value.nonEmpty && value.head.isLetter && value.forall(ch => ch.isLetterOrDigit || ch == '_')

  private val StructTypedefRe   = """typedef\s+struct\s+_cef_\w+_t\s*\{""".r
  private val EnumTypedefRe     = """typedef\s+enum\s*\{""".r
  private val StructClosingRe   = """}\s*cef_\w+_t\s*;""".r
  private val FnPtrLineRe       = """\w\s*\*?\s*\(\s*\*\s*\w+\s*\)""".r
  private val EnumClosingRe     = """}\s*\w+\s*;""".r
  private val BaseFieldRe       = """.*cef_\w+_t\s+base\s*;.*""".r
  private val ScopedBaseRe      = """.*cef_base_scoped_t\s+base\s*;.*""".r
  private val BaseTypeCaptureRe = """(cef_\w+_t)\s+base\s*;""".r
  private val RootBaseTypes     = Set("cef_base_ref_counted_t", "cef_base_scoped_t")
  private val StructOpenRe      = """typedef\s+struct\s+_cef_\w+_t\s*\{""".r
  private val StructCloseRe     = """}\s*(cef_\w+_t)\s*;""".r

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
    HeaderMetadataIndex(cefIncludeDir).handlerNames

  def parseDocComments(cefIncludeDir: Path): Map[String, String] =
    HeaderMetadataIndex(cefIncludeDir).docs

  def parseClassDocs(cefIncludeDir: Path): Map[String, String] =
    HeaderMetadataIndex(cefIncludeDir).classDocs

  def parseCppTypeInfo(cefIncludeDir: Path): Map[String, CppMethodTypeInfo] =
    HeaderMetadataIndex(cefIncludeDir).cppTypeInfo

  def parseEnumDocs(cefIncludeDir: Path): Map[String, (String, Map[String, String])] =
    HeaderMetadataIndex(cefIncludeDir).enumDocs

  def parseCppClassNames(cefIncludeDir: Path): Map[String, String] =
    HeaderMetadataIndex(cefIncludeDir).cppClassNames

  def deriveCompoundSegments(cefIncludeDir: Path, cppClassNames: Map[String, String]): Map[String, List[String]] =
    HeaderMetadataIndex.deriveCompoundSegments(cppClassNames, cefIncludeDir)

  private case class StructState(
      fnPtrs: List[FnPtr],
      fields: List[Field],
      hasBase: Boolean,
      isScoped: Boolean,
      parentType: Option[String],
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
          val scoped     = ScopedBaseRe.matches(line)
          val parentType = BaseTypeCaptureRe.findFirstMatchIn(line)
            .map(_.group(1))
            .filterNot(RootBaseTypes.contains)
          loop(state.copy(hasBase = true, isScoped = scoped, parentType = parentType, idx = state.idx + 1))
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

    val result = loop(StructState(Nil, Nil, hasBase = false, isScoped = false, parentType = None, startIdx + 1))

    val closingLine = if (result.idx < lines.length) lines(result.idx) else ""
    val structName  = """}\s*(cef_\w+_t)\s*;""".r
      .findFirstMatchIn(closingLine)
      .map(_.group(1))
      .getOrElse("unknown_t")

    val decl = if (!result.hasBase && result.fields.nonEmpty) {
      CefDecl.DataStruct(structName, result.fields.reverse)
    } else if (handlerNames.contains(structName)) {
      CefDecl.HandlerStruct(structName, result.fnPtrs.reverse.map(classifySpecial))
    } else {
      CefDecl.ObjectStruct(
        structName,
        result.fnPtrs.reverse.map(classifySpecial),
        scoped = result.isScoped,
        parentStruct = result.parentType
      )
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
    val withoutArray = trimmed.replaceAll("\\[\\d+]", "").trim
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
      .replaceAll("\\b_(?=cef_)", "")
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
      case t if t.endsWith("**")                        => CType.Ptr(t.stripSuffix("*").trim)
      case "char16_t*" | "const char16_t*"              => CType.OpaquePtr
      case "char*" | "const char*"                      => CType.OpaquePtr
      case "wchar_t*" | "const wchar_t*"                => CType.OpaquePtr
      case "cef_string_list_t"                          => CType.StringList
      case "cef_string_map_t"                           => CType.StringMap
      case "cef_string_multimap_t"                      => CType.StringMultimap
      case "cef_window_handle_t" | "cef_cursor_handle_t" | "cef_event_handle_t"
          | "cef_platform_thread_id_t" | "cef_platform_thread_handle_t" => CType.Long
      case "cef_color_t"                                                           => CType.UInt
      case t if ByValueStructs.exists(g => t == s"const $g*" || t == s"$g const*") =>
        val cefName = ByValueStructs.find(g => t.contains(g)).get
        CType.ByValueIn(cefName)
      case t if ByValueStructs.exists(g => t == s"$g*") =>
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

  // Promote ByValueIn to ByValueArray when it is immediately preceded by a matching count parameter.
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

  // Pre-scan lines to find data-struct names before full declaration parsing.
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
    val enumName    = """}\s*(\w+)\s*;""".r
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

  private val ExplicitOwnerMap: Map[String, String] = Map(
    "cef_browser_host_create_browser"               -> "cef_browser_host_t",
    "cef_browser_host_create_browser_sync"          -> "cef_browser_host_t",
    "cef_browser_host_get_browser_by_identifier"    -> "cef_browser_host_t",
    "cef_v8_context_get_current_context"            -> "cef_v8_context_t",
    "cef_v8_context_get_entered_context"            -> "cef_v8_context_t",
    "cef_v8_context_in_context"                     -> "cef_v8_context_t",
    "cef_request_context_cef_create_context_shared" -> "cef_request_context_t"
  )

  // Parse CEF_EXPORT free functions from raw CAPI headers.
  def parseFreeExports(
      capiDir: Path,
      knownStructNames: Set[String],
      dataStructNames: Set[String],
      extraCapiDirs: List[String] = Nil
  )(using Naming.Context): List[CefDecl.FreeFunction] = {
    val capiHeaders = (capiDir :: extraCapiDirs.map(capiDir.resolve)).filter(Files.isDirectory(_))
      .flatMap(dir =>
        Files.list(dir).toScala(List).filter(p => Files.isRegularFile(p) && p.toString.endsWith("_capi.h"))
      )
      .sorted

    capiHeaders.flatMap { header =>
      val headerName = capiDir.relativize(header).toString.replace('\\', '/')
      val lines      = Files.readString(header).linesIterator.toVector
      parseFreeExportsFromFile(lines, headerName, knownStructNames, dataStructNames)
    }
  }

  private def parseFreeExportsFromFile(
      lines: Vector[String],
      headerName: String,
      knownStructNames: Set[String],
      dataStructNames: Set[String]
  )(using Naming.Context): List[CefDecl.FreeFunction] = {
    @tailrec
    def loop(
        idx: Int,
        inStructBlock: Boolean,
        braceDepth: Int,
        acc: List[CefDecl.FreeFunction]
    ): List[CefDecl.FreeFunction] =
      if (idx >= lines.length) acc.reverse
      else {
        val line              = lines(idx).trim
        val startsStructBlock = line.startsWith("typedef struct _cef_")
        val nextInStructBlock = inStructBlock || startsStructBlock
        val nextBraceDepth    = if (nextInStructBlock) braceDepth + line.count(_ == '{') - line.count(_ == '}') else 0
        val exitsStructBlock  = nextInStructBlock && nextBraceDepth <= 0 && line.contains("}")
        val continuingStructBlock = nextInStructBlock && !exitsStructBlock

        if (nextInStructBlock) {
          loop(idx + 1, continuingStructBlock, if (continuingStructBlock) nextBraceDepth else 0, acc)
        } else if (line.startsWith("CEF_EXPORT")) {
          val (fullDecl, nextIdx) = collectExportDecl(lines, idx)
          val nextAcc             = parseFreeFunction(fullDecl, headerName, knownStructNames, dataStructNames) match {
            case Some(freeFunction) => freeFunction :: acc
            case None               => acc
          }
          loop(nextIdx, inStructBlock = false, braceDepth = 0, nextAcc)
        } else {
          loop(idx + 1, inStructBlock = false, braceDepth = 0, acc)
        }
      }

    loop(0, inStructBlock = false, braceDepth = 0, Nil)
  }

  @tailrec
  private def collectExportDecl(lines: Vector[String], idx: Int, acc: List[String] = Nil): (String, Int) =
    if (idx >= lines.length) (normalizeWhitespace(acc.reverse.mkString(" ")), idx)
    else {
      val line    = lines(idx).trim
      val nextAcc = line :: acc
      if (line.contains(";")) (normalizeWhitespace(nextAcc.reverse.mkString(" ")), idx + 1)
      else collectExportDecl(lines, idx + 1, nextAcc)
    }

  private def parseFreeFunction(
      decl: String,
      headerName: String,
      knownStructNames: Set[String],
      dataStructNames: Set[String]
  ): Option[CefDecl.FreeFunction] =
    decl match {
      case s"CEF_EXPORT $signature" =>
        parseFreeFunctionSignature(signature.stripSuffix(";").trim, dataStructNames).flatMap {
          case (retStr, cName, paramStr) =>
            if (cName.startsWith("cef_string_") || cName.startsWith("cef_time_")) None
            else {
              val ret            = parseType(retStr, dataStructNames)
              val params         = parseParams(paramStr, dataStructNames)
              val ownerStruct    = resolveOwnerStruct(cName, ret, knownStructNames)
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
        }
      case _ => None
    }

  private def parseFreeFunctionSignature(
      signature: String,
      dataStructNames: Set[String]
  ): Option[(String, String, String)] = {
    val openParen  = signature.indexOf('(')
    val closeParen = signature.lastIndexOf(')')
    if (openParen < 0 || closeParen < openParen) return None

    val beforeParams = signature.substring(0, openParen).trim
    val paramStr     = signature.substring(openParen + 1, closeParen).trim
    val lastSpace    = beforeParams.lastIndexOf(' ')
    if (lastSpace < 0) None
    else {
      val retStr = beforeParams.substring(0, lastSpace).trim
      val cName  = beforeParams.substring(lastSpace + 1).trim
      Option.when(retStr.nonEmpty && cName.nonEmpty)((retStr, cName, paramStr))
    }
  }

  private def resolveOwnerStruct(cName: String, ret: CType, knownStructNames: Set[String]): String = {
    ExplicitOwnerMap.get(cName) match {
      case Some(owner) => return owner
      case None        =>
    }

    ret match {
      case CType.Ptr(inner) =>
        val stripped = inner.stripPrefix("struct ").stripPrefix("_").trim
        if (knownStructNames.contains(stripped)) return stripped
      case CType.ObjectPtr(name) if knownStructNames.contains(name) =>
        return name
      case _ =>
    }

    val candidates = knownStructNames.filter { structName =>
      val prefix = structName.stripSuffix("_t")
      cName.startsWith(prefix + "_")
    }
    if (candidates.nonEmpty) {
      return candidates.maxBy(_.length)
    }

    ""
  }

  // Compute the Java method name for a free function by stripping the owner prefix.
  private def computeJavaMethodName(cName: String, ownerStruct: String)(using Naming.Context): String = {
    val prefix = if (ownerStruct.nonEmpty) ownerStruct.stripSuffix("_t") + "_"
    else "cef_"
    val stripped = if (cName.startsWith(prefix)) cName.stripPrefix(prefix) else cName.stripPrefix("cef_")
    Naming.toCamelCase(stripped)
  }

  private def normalizeWhitespace(s: String): String =
    s.split("\\s+").filter(_.nonEmpty).mkString(" ")

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

  // Post-process declarations to reclassify raw Ptr types into richer internal CType variants.
  def reclassifyPointers(decls: List[CefDecl], handlerNames: Set[String]): List[CefDecl] = {
    val objectStructNames = decls.collect {
      case d: CefDecl.ObjectStruct  => d.name
      case d: CefDecl.HandlerStruct => d.name
    }.toSet
    val dataStructNames = decls.collect { case d: CefDecl.DataStruct => d.name }.toSet

    def stripToBareName(s: String): String =
      s.replaceAll("\\bconst\\b", "").replaceAll("\\bstruct\\b", "").replaceAll("\\*", "")
        .stripPrefix("_").trim

    def reclassifyType(ct: CType): CType = ct match {
      case CType.Ptr(inner) =>
        val stripped = inner.stripPrefix("const ").stripPrefix("struct ").stripPrefix("_")
          .stripSuffix("const").trim
        if (stripped.endsWith("*")) {
          val bare = stripToBareName(stripped)
          if (objectStructNames.contains(bare))
            CType.OutObjectPtr(bare)
          else CType.OpaquePtr // Treat unknown double pointers as opaque
        } else if (objectStructNames.contains(stripped))
          CType.ObjectPtr(stripped)
        else if (stripped == "void" || stripped.isEmpty)
          CType.OpaquePtr
        else if (dataStructNames.contains(stripped))
          CType.OpaquePtr // Data struct pointers not in ByValueStructs -> opaque for now
        else
          PrimitiveOutPtrMap.get(stripped) match {
            case Some(prim) => CType.OutPrimitivePtr(prim)
            case None       =>
              val bare = stripToBareName(inner)
              if (objectStructNames.contains(bare)) CType.ObjectPtr(bare)
              else if (dataStructNames.contains(bare)) CType.OpaquePtr
              else CType.OpaquePtr // Fallback to opaque - will be logged
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

  // Promote adjacent count + out-pointer pairs into array-shaped parameter types.
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

  // Promote void* plus adjacent size parameters into ByteBuffer-style parameter types.
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
                (Set("bytes", "buffer", "data", "ptr").contains(lower) && Set(
                  "size",
                  "length"
                ).contains(p.name.toLowerCase))
            ) =>
          p.name
      }
    }

    def promote(fn: FnPtr): FnPtr = {
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
