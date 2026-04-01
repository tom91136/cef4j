package net.kurobako.cef4j.codegen

object DocComments {

  /** Regex matching --cef(...)-- metacomments in C++ header docs. */
  private val CefMetaPattern = """--cef\(([^)]*)\)--""".r

  // --- CEF doc syntax → Javadoc conversion ---

  /** CEF docs base URL. Set once at startup from cef_version.h. */
  @volatile private var docsBaseUrl: String = ""

  def initDocsBaseUrl(cefMajor: Int, cefMinor: Int): Unit =
    docsBaseUrl = s"https://cef-builds.spotifycdn.com/docs/$cefMajor.$cefMinor"

  /** Convert a C++ header filename to its Doxygen URL. Doxygen replaces _ with __ and . with _8 */
  private def doxygenUrl(headerFile: String): String = {
    if (docsBaseUrl.isEmpty) return ""
    val doxyName = headerFile.replace("_", "__").replace(".", "_8") + ".html"
    s"$docsBaseUrl/$doxyName"
  }

  /** Enum constant lookup: C constant name → "JavaEnumClass#CONSTANT". Set once at startup. */
  @volatile private var enumConstantMap: Map[String, String] = Map.empty

  /** C++ class name → Java class name mapping for cross-references. */
  @volatile private var classNameMap: Map[String, String] = Map.empty

  def initEnumConstants(enums: List[CefDecl.Enum]): Unit =
    enumConstantMap = enums.flatMap { e =>
      val javaEnum = Naming.structToJavaName(e.name)
      val cNames   = e.values.map(_._1)
      val prefix   = Naming.computeEnumPrefix(cNames)
      e.values.flatMap { case (constName, _, _) =>
        val jName = constName.stripPrefix(prefix)
        // Map C name → Java qualified ref on inner Kind enum
        List(constName -> s"$javaEnum.Kind#$jName")
      }
    }.toMap

  /** Resolve a C enum constant name to its fully qualified Java form (e.g., "RV_CONTINUE" → "CefReturnValue.CONTINUE").
    * Falls back to the original name if not found.
    */
  def resolveEnumConstant(cConstName: String): String =
    enumConstantMap.get(cConstName) match {
      case Some(qualified) => qualified.replace('#', '.')
      case None            => cConstName
    }

  /** Method signature lookup: (javaClassName, javaMethodName) → list of Java param type strings. If a key maps to
    * multiple entries, the method is overloaded and we give up on linking.
    */
  @volatile private var methodSigMap: Map[(String, String), List[List[String]]] = Map.empty

  def initClassNames(structNames: Set[String]): Unit =
    classNameMap = structNames.map { cName =>
      val cppName = Naming.toPascalCase(cName.stripSuffix("_t"))
      cppName -> Naming.structToJavaName(cName)
    }.toMap

  def initMethodSignatures(decls: List[CefDecl]): Unit = {
    val entries = decls.flatMap {
      case d: CefDecl.ObjectStruct  => d.fns.map(fn => (Naming.structToJavaName(d.name), fn))
      case d: CefDecl.HandlerStruct => d.fns.map(fn => (Naming.structToJavaName(d.name), fn))
      case _                        => Nil
    }
    methodSigMap = entries
      .map { case (cls, fn) =>
        val methodName = Naming.javaMethodName(fn)
        val paramTypes = fn.params.filterNot(_.typ.isInstanceOf[CType.BufferSize]).map(p => Naming.javaType(p.typ))
        (cls, methodName) -> paramTypes
      }
      .groupBy(_._1)
      .map { case (key, entries) => key -> entries.map(_._2).distinct }
  }

  /** Resolve a method cross-reference to a fully qualified {@link} or fall back to plain text. */
  private def resolveMethodLink(javaClass: String, javaMethod: String): String =
    methodSigMap.get((javaClass, javaMethod)) match {
      case Some(sigs) if sigs.size == 1 =>
        // Exactly one signature — fully resolve
        val sig = sigs.head.mkString(", ")
        s"{@link $javaClass#$javaMethod($sig)}"
      case Some(_) =>
        // Overloaded — plain text
        s"$javaClass.$javaMethod()"
      case None =>
        // Not found — plain text
        s"$javaClass.$javaMethod()"
    }

  private val PipeRefRe       = """\|([^|]+)\|""".r
  private val CapiCrossRefRe  = """(cef_\w+_t)::(\w+)\(\)""".r // cef_xxx_t::method()
  private val CapiMemberRefRe = """(cef_\w+_t)::(\w+)""".r     // cef_xxx_t::Member
  private val CppCrossRefRe   = """(Cef\w+)::(\w+)\(\)""".r
  private val CppClassRefRe   = """(Cef\w+)::([\w]+)""".r
  private val CefEnumConstRe  = """\b(CEF_[A-Z][A-Z0-9_]+)\b""".r
  private val ErrConstRe      = """\b(ERR_[A-Z][A-Z0-9_]+)\b""".r
  private val TidConstRe      = """\b(TID_[A-Z][A-Z0-9_]+)\b""".r
  private val UrlRe           = """(https?://\S+)""".r
  private val BoolNullRe      = """\b(true|false|NULL|nullptr)\b""".r

  private val CefSrcMarkerRe = """@_cefsrc:([^:]+):(\d+)""".r

  /** Convert CEF C++ doc comment syntax to Javadoc syntax.
    * @param capiSource
    *   optional CAPI header filename for the source reference
    */
  def convertCefDoc(
      text: String,
      capiSource: String = "",
      cPrototype: String = "",
      cppSourceOverride: String = ""
  ): String = {
    // Extract embedded @_cefsrc marker if present, or use override
    var cppSource  = cppSourceOverride
    val cleanLines = text.linesIterator.toList.flatMap { line =>
      CefSrcMarkerRe.findFirstMatchIn(line) match {
        case Some(m) =>
          if (cppSource.isEmpty) cppSource = s"${m.group(1)}:${m.group(2)}"
          None // strip marker line
        case None => Some(line)
      }
    }

    // Join hyphenated line wraps: "command-\nline" → "command-line"
    val joinedLines = cleanLines.foldRight(List.empty[String]) { (line, acc) =>
      acc match {
        case next :: rest if line.endsWith("-") && next.nonEmpty && next.head.isLetter =>
          (line + next) :: rest
        case _ => line :: acc
      }
    }

    // Process line-by-line for structural elements (lists, paragraphs, pre blocks)
    val (converted, _) = joinedLines.foldLeft((List.empty[String], false)) { case ((acc, inPre), line) =>
      if (line.trim == "<pre>" || line.trim.startsWith("<pre>")) {
        (acc :+ line, true)
      } else if (line.trim == "</pre>" || line.trim.endsWith("</pre>")) {
        (acc :+ line, false)
      } else if (inPre) {
        (acc :+ line, true) // Don't transform inside <pre> blocks
      } else {
        (acc :+ convertLine(line), false)
      }
    }

    // Build source references: CAPI as inline <p>, C++ as @_see with Doxygen link
    // If no explicit CAPI source, fall back to C++ source file (for internal headers)
    val effectiveCapiSource = if (capiSource.nonEmpty) capiSource
    else cppSource.split(":").headOption.filter(_.nonEmpty).getOrElse("")
    val capiRef = if (effectiveCapiSource.nonEmpty) {
      val preBlock = if (cPrototype.nonEmpty) s"\n<pre>$cPrototype</pre>" else ""
      s"\n<p>Definition generated from $effectiveCapiSource$preBlock"
    } else ""
    val cppSee = if (cppSource.nonEmpty) {
      // cppSource is "file.h:line"
      val parts = cppSource.split(":", 2)
      val file  = parts(0)
      val line  = if (parts.length > 1) parts(1) else ""
      val url   = doxygenUrl(file)
      val label = if (line.nonEmpty) s"$file:$line" else file
      if (url.nonEmpty) s"""\n@_see:<a href="$url">$label</a>"""
      else s"\n@_see:$label"
    } else ""
    val srcRef = capiRef + cppSee

    // Convert bullet lists, collapse consecutive <p> tags
    val bulletWrapped = wrapBulletLists(converted)
    val collapsed     = collapseEmptyParagraphs(bulletWrapped)
    collapsed.mkString("\n") + srcRef
  }

  private def convertLine(line: String): String = {
    var result = line

    // Empty line → paragraph break
    if (result.trim.isEmpty) return "<p>"

    // WARNING: at start of line → bold
    result = result.replaceAll("""^(WARNING:)""", "<b>$1</b>")

    // |param| → {@code param}
    result = PipeRefRe.replaceAllIn(result, m => s"{@code ${m.group(1)}}")

    // CefClass::Method() → {@link JavaClass#method(ParamTypes)} or plain text if unresolvable
    result = CppCrossRefRe.replaceAllIn(
      result,
      m => {
        val cppClass   = m.group(1)
        val cppMethod  = m.group(2)
        val javaClass  = classNameMap.getOrElse(cppClass, cppClass)
        val javaMethod = Naming.toCamelCase(
          cppMethod.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase
        )
        java.util.regex.Matcher.quoteReplacement(resolveMethodLink(javaClass, javaMethod))
      }
    )

    // CefClass::Member (no parens) → try method resolution first, fall back to plain ref
    result = CppClassRefRe.replaceAllIn(
      result,
      m => {
        val cppClass   = m.group(1)
        val member     = m.group(2)
        val javaClass  = classNameMap.getOrElse(cppClass, cppClass)
        val javaMethod = Naming.toCamelCase(
          member.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase
        )
        java.util.regex.Matcher.quoteReplacement(resolveMethodLink(javaClass, javaMethod))
      }
    )

    // cef_xxx_t::method() → resolve via CAPI struct name
    result = CapiCrossRefRe.replaceAllIn(
      result,
      m => {
        val capiStruct = m.group(1)
        val capiMethod = m.group(2)
        val javaClass  = Naming.structToJavaName(capiStruct)
        val javaMethod = Naming.toCamelCase(capiMethod)
        java.util.regex.Matcher.quoteReplacement(resolveMethodLink(javaClass, javaMethod))
      }
    )

    // cef_xxx_t::Member (no parens) → resolve via CAPI struct name
    result = CapiMemberRefRe.replaceAllIn(
      result,
      m => {
        val capiStruct = m.group(1)
        val capiMethod = m.group(2)
        val javaClass  = Naming.structToJavaName(capiStruct)
        val javaMethod = Naming.toCamelCase(capiMethod)
        java.util.regex.Matcher.quoteReplacement(resolveMethodLink(javaClass, javaMethod))
      }
    )

    // CEF_ENUM_CONSTANT → try to resolve to {@link EnumClass#CONSTANT}, fallback to {@code ...}
    result = CefEnumConstRe.replaceAllIn(
      result,
      m => {
        val constant = m.group(1)
        enumConstantMap.get(constant) match {
          case Some(qualified) => s"{@link $qualified}"
          case None            => s"{@code $constant}"
        }
      }
    )

    // ERR_XXX → {@code ERR_XXX}
    result = ErrConstRe.replaceAllIn(result, m => s"{@code ${m.group(1)}}")

    // TID_XXX → {@code TID_XXX}
    result = TidConstRe.replaceAllIn(result, m => s"{@code ${m.group(1)}}")

    // true/false/NULL/nullptr → {@code ...}
    result = BoolNullRe.replaceAllIn(
      result,
      m => {
        val word = m.group(1) match {
          case "NULL" | "nullptr" => "null"
          case other              => other
        }
        s"{@code $word}"
      }
    )

    // URLs → <a href="url">url</a>
    result = UrlRe.replaceAllIn(
      result,
      m => {
        val url = m.group(1).stripSuffix(".").stripSuffix(",")
        s"""<a href="$url">$url</a>"""
      }
    )

    result
  }

  /** Collapse consecutive empty paragraph tags and trailing empty paragraphs. */
  private def collapseEmptyParagraphs(lines: List[String]): List[String] = {
    // Remove consecutive <p> lines (keep only the first)
    val collapsed = lines.foldLeft(List.empty[String]) { (acc, line) =>
      if (line.trim == "<p>" && acc.lastOption.exists(_.trim == "<p>")) acc
      else acc :+ line
    }
    // Remove trailing <p> at end of content
    collapsed.reverse.dropWhile(_.trim == "<p>").reverse
  }

  /** Extract @_see markers from converted text and return (cleanText, list of see tag contents). */
  private val SeeMarkerRe = """@_see:(.+)""".r

  def extractSourceTags(text: String): (String, List[String]) = {
    val lines   = text.linesIterator.toList
    val seeTags = List.newBuilder[String]
    val clean   = lines.flatMap { line =>
      SeeMarkerRe.findFirstMatchIn(line) match {
        case Some(m) => seeTags += m.group(1); None
        case None    => Some(line)
      }
    }
    (clean.mkString("\n"), seeTags.result())
  }

  // --- C prototype reconstruction ---

  /** Reconstruct the C fn-ptr prototype for a struct method. */
  def cPrototypeForMethod(structName: String, fn: FnPtr): String = {
    val ret       = cReturnType(fn.ret)
    val self      = s"struct _${structName}* self"
    val params    = fn.params.map(cParamDecl).mkString(", ")
    val allParams = if (params.isEmpty) self else s"$self, $params"
    s"$ret (CEF_CALLBACK* ${fn.name})($allParams);"
  }

  /** Reconstruct the C prototype for a free function. */
  def cPrototypeForFreeFunction(ff: CefDecl.FreeFunction): String = {
    val ret    = cReturnType(ff.ret)
    val params = if (ff.params.isEmpty) "void" else ff.params.map(cParamDecl).mkString(", ")
    s"CEF_EXPORT $ret ${ff.cName}($params);"
  }

  /** Reconstruct the C struct typedef stub for a class-level doc. */
  def cPrototypeForStruct(structName: String, hasBase: Boolean): String = {
    val base = if (hasBase) "\n  cef_base_ref_counted_t base;\n  ...\n" else "\n  ...\n"
    s"typedef struct _$structName {${base}} $structName;"
  }

  private def cReturnType(ct: CType): String = ct match {
    case CType.JString => "cef_string_userfree_t"
    case CType.Bool    => "int"
    case other         => Naming.cType(other)
  }

  private def cParamDecl(p: Param): String = {
    val cType = if (p.rawCType.nonEmpty) p.rawCType else Naming.cType(p.typ)
    s"$cType ${p.name}"
  }

  /** Reconstruct the C enum typedef stub. */
  def cPrototypeForEnum(cefName: String, values: List[(String, Long, String)]): String = {
    val members  = values.take(5).map { case (name, _, expr) => s"  $name = $expr" }.mkString(",\n")
    val ellipsis = if (values.size > 5) ",\n  ..." else ""
    s"typedef enum {\n$members$ellipsis\n} $cefName;"
  }

  /** Reconstruct the C data struct typedef. */
  def cPrototypeForDataStruct(d: CefDecl.DataStruct): String = {
    val fields = d.fields.map { f =>
      val cType = Naming.cType(f.typ)
      s"  $cType ${f.name};"
    }.mkString("\n")
    s"typedef struct _${d.name} {\n$fields\n} ${d.name};"
  }

  /** Wrap consecutive lines starting with "- " in <ul><li>...</li></ul> blocks. */
  private def wrapBulletLists(lines: List[String]): List[String] = {
    val result = List.newBuilder[String]
    var inList = false
    for (line <- lines) {
      val trimmed = line.trim
      if (trimmed.startsWith("- ")) {
        if (!inList) { result += "<ul>"; inList = true }
        result += s"<li>${trimmed.stripPrefix("- ")}</li>"
      } else if (trimmed.startsWith("  ") && inList) {
        // Continuation of previous list item (indented)
        // Append to the concept but just emit as-is within the list
        result += trimmed
      } else {
        if (inList) { result += "</ul>"; inList = false }
        result += line
      }
    }
    if (inList) result += "</ul>"
    result.result()
  }

  /** Generate javadoc for a function pointer, looking up by both snake_case and CamelCase names. */
  def forMethod(
      fn: FnPtr,
      docs: Map[String, String],
      capiSource: String = "",
      cPrototype: String = ""
  ): String = {
    val camelName  = Naming.javaMethodName(fn)
    val pascalName = Naming.javaPascalName(fn)

    val comment = fn.cppName.flatMap(docs.get)
      .orElse(docs.get(camelName))
      .orElse(docs.get(pascalName))
      .orElse(docs.get(fn.name))
      .orElse(docs.keys.find(_.equalsIgnoreCase(pascalName)).flatMap(docs.get))

    comment match {
      case Some(text) =>
        // Extract and remove --cef(...)-- metacomments
        val metaAttrs = CefMetaPattern.findAllMatchIn(text).flatMap { m =>
          parseMetaAttrs(m.group(1))
        }.toList

        // Remove the metacomment line(s) from visible text, then convert CEF doc syntax
        val cleanText                = CefMetaPattern.replaceAllIn(text, "").trim
        val convertedText            = convertCefDoc(cleanText, capiSource, cPrototype)
        val (docText, sourceRefTags) = extractSourceTags(convertedText)

        // Build human-readable sentences from meta attributes
        val metaSentences = metaAttrsToSentences(metaAttrs, fn)

        // Combine remaining doc text with meta-derived sentences
        val docLines      = docText.linesIterator.filter(_.nonEmpty).toList
        val countFuncNote = fn.ret match {
          case CType.CountFuncArray(elem, countFuncCName, _, _) =>
            val countMethodJava = Naming.toCamelCase(countFuncCName)
            val kind            = if (Naming.isPrimitiveElement(elem)) "array" else "array/collection"
            List(
              s"<p>The C API exposes this as a two-pass pattern: first call {@link #$countMethodJava()} to obtain",
              s"the count, then allocate and populate the $kind. This method performs both steps and returns the",
              "result directly."
            )
          case _ => Nil
        }
        // Insert count_func note before the "Definition generated" block
        val (beforeDef, defAndAfter) = docLines.span(!_.startsWith("<p>Definition generated"))
        val allContent               = beforeDef ++ metaSentences ++ countFuncNote ++ defAndAfter

        // Only emit @param for params that have something meaningful to say
        val optionalParams = metaAttrs.collect { case ("optional_param", p) => p }.toSet
        val indexParams    = metaAttrs.collect { case ("index_param", p) => p }.toSet
        val paramDocs      = fn.params.flatMap { p =>
          val pName = Naming.toCamelCase(p.name)
          val notes = List(
            if (optionalParams.contains(p.name)) Some("may be null") else None,
            if (indexParams.contains(p.name)) Some("zero-based index") else None
          ).flatten
          if (notes.nonEmpty) Some(s"     * @param $pName ${notes.mkString(", ")}")
          else None
        }

        // Only emit @return if default_retval provides useful info
        val returnDoc = fn.metaAttrs.collectFirst { case ("default_retval", v) => v } match {
          case Some(retVal) => List(s"     * @return the result, or {@code $retVal} for default handling")
          case None         => Nil
        }

        // @see tags for source location
        val seeDoc = sourceRefTags.map(tag => s"     * @see $tag")

        // If there's no content at all, skip the javadoc entirely
        if (allContent.isEmpty && paramDocs.isEmpty && returnDoc.isEmpty && seeDoc.isEmpty) {
          ""
        } else {
          val contentLines = allContent.map(l => s"     * $l")
          val hasTags      = paramDocs.nonEmpty || returnDoc.nonEmpty || seeDoc.nonEmpty
          val tagSeparator = if (contentLines.nonEmpty && hasTags) List("     *") else Nil
          val sections     = contentLines ++ tagSeparator ++ paramDocs ++
            (if (paramDocs.nonEmpty && returnDoc.nonEmpty) List("     *") else Nil) ++ returnDoc ++
            (if ((paramDocs.nonEmpty || returnDoc.nonEmpty) && seeDoc.nonEmpty) List("     *") else Nil) ++ seeDoc

          s"""    /**
${sections.mkString("\n")}
     */
"""
        }

      case None => ""
    }
  }

  /** Extract meta attributes from a raw doc text string (which may contain --cef(...)-- metacomments). Returns a list
    * since keys like optional_param can repeat.
    */
  def extractAttrsList(docText: String): List[(String, String)] =
    CefMetaPattern.findAllMatchIn(docText)
      .flatMap(m => parseMetaAttrs(m.group(1)))
      .toList

  /** Parse key=value pairs from the inside of --cef(...)--. */
  private def parseMetaAttrs(inner: String): List[(String, String)] =
    if (inner.isBlank) Nil
    else {
      inner.split(",").toList.flatMap { part =>
        val trimmed = part.trim
        trimmed match {
          case ""                             => None
          case s"$key=$value" if key.nonEmpty =>
            Some((key.trim, value.trim))
          case key =>
            Some((key, ""))
        }
      }
    }

  /** Convert parsed meta attributes into human-readable sentences. Only emits sentences for attributes not already
    * covered by annotations or @param/@return tags.
    */
  private def metaAttrsToSentences(attrs: List[(String, String)], fn: FnPtr): List[String] =
    attrs.flatMap {
      // optional_param -> covered by @Nullable annotation + @param tag
      // index_param -> covered by @param tag
      // default_retval -> covered by @return tag
      case ("count_func", _) =>
        // Suppressed: the count_func pattern is flattened into a CountFuncArray return type
        None
      case ("added", version) =>
        Some(s"<p>Added in CEF API version $version.")
      case _ => None
    }
}
