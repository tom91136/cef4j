package net.kurobako.cef4j.codegen

object DocComments {
  case class Context(
      docsBaseUrl: String,
      enumConstantMap: Map[String, String] = Map.empty,
      classNameMap: Map[String, String] = Map.empty,
      methodSigMap: Map[(String, String), List[List[String]]] = Map.empty
  )

  object Context {
    given empty: Context = Context("")
  }

  private val CefMetaPattern = """--cef\(([^)]*)\)--""".r

  /** Find a doc entry qualified by C++ class where capi_name matches the given alias. */
  private def findByCapiAlias(
      cppClassName: Option[String],
      docs: Map[String, String],
      alias: String
  ): Option[String] =
    cppClassName.flatMap { cls =>
      docs.collectFirst {
        case (key, text)
            if key.startsWith(s"$cls::") &&
              extractAttrsList(text).exists { case (k, v) => k == "capi_name" && v == alias } =>
          text
      }
    }
  // Matches any HTML-like tag: <tag>, </tag>, <tag/>, <tag attr=val>
  private val AnyTagLiteralRe = """<(/?[A-Za-z][A-Za-z0-9:_-]*(?:/|\s[^>]*)?)>""".r

  def baseContext(cefMajor: Int, cefMinor: Int): Context =
    Context(docsBaseUrl = s"https://cef-builds.spotifycdn.com/docs/$cefMajor.$cefMinor")

  private def escapeHtml(text: String): String =
    text
      .replace("&", "&amp;")
      .replace("<", "&lt;")
      .replace(">", "&gt;")

  // Convert a C++ header filename to its Doxygen URL. Doxygen uses only the filename
  // (no directory) and replaces _ with __ and . with _8.
  private def doxygenUrl(headerFile: String)(using context: Context): String = {
    if (context.docsBaseUrl.isEmpty) return ""
    val baseName = headerFile.replace("\\", "/").split("/").last
    val doxyName = baseName
      .replace("_", "__")
      .replace(".", "_8") + ".html"
    s"${context.docsBaseUrl}/$doxyName"
  }

  def withEnumConstants(context: Context, enums: List[CefDecl.Enum])(using Naming.Context): Context =
    context.copy(enumConstantMap = enums.flatMap { e =>
      val javaEnum = Naming.structToJavaName(e.name)
      val cNames   = e.values.map(_._1)
      val prefix   = Naming.computeEnumPrefix(cNames)
      e.values.flatMap { case (constName, _, _) =>
        val jName = constName.stripPrefix(prefix)
        List(constName -> s"$javaEnum.Kind#$jName")
      }
    }.toMap)

  // Resolve a C enum constant name to its fully qualified Java form.
  def resolveEnumConstant(cConstName: String)(using context: Context): String =
    context.enumConstantMap.get(cConstName) match {
      case Some(qualified) => qualified.replace('#', '.')
      case None            => cConstName
    }

  def withClassNames(context: Context, structNames: Set[String])(using Naming.Context): Context =
    context.copy(classNameMap = structNames.map { cName =>
      val cppName = Naming.toPascalCase(cName.stripSuffix("_t"))
      cppName -> Naming.structToJavaName(cName)
    }.toMap)

  def withMethodSignatures(context: Context, decls: List[CefDecl])(using Naming.Context): Context = {
    val entries = decls.flatMap {
      case d: CefDecl.ObjectStruct  => d.fns.map(fn => (Naming.structToJavaName(d.name), fn))
      case d: CefDecl.HandlerStruct => d.fns.map(fn => (Naming.structToJavaName(d.name), fn))
      case _                        => Nil
    }
    context.copy(methodSigMap =
      entries
        .map { case (cls, fn) =>
          val methodName = Naming.javaMethodName(fn)
          val paramTypes = fn.visibleParams.map(p => Naming.javaType(p.typ, javadoc = true))
          (cls, methodName) -> paramTypes
        }
        .groupBy(_._1)
        .map { case (key, entries) => key -> entries.map(_._2).distinct }
    )
  }

  // Resolve a method cross-reference to a Javadoc link when the target signature is known.
  private def resolveMethodLink(javaClass: String, javaMethod: String)(using context: Context): String =
    context.methodSigMap.get((javaClass, javaMethod)) match {
      case Some(sigs) if sigs.size == 1 =>
        val sig = sigs.head.mkString(", ")
        s"{@link $javaClass#$javaMethod($sig)}"
      case Some(_) => s"$javaClass.$javaMethod()"
      case None    => s"$javaClass.$javaMethod()"
    }

  private val PipeRefRe       = """\|([^|]+)\|""".r
  private val CapiCrossRefRe  = """(cef_\w+_t)::(\w+)\(\)""".r // cef_xxx_t::method()
  private val CapiMemberRefRe = """(cef_\w+_t)::(\w+)""".r     // cef_xxx_t::Member
  private val CppCrossRefRe   = """(Cef\w+)::(\w+)\(\)""".r
  private val CppClassRefRe   = """(Cef\w+)::(\w+)""".r
  private val CefEnumConstRe  = """\b(CEF_[A-Z][A-Z0-9_]+)\b""".r
  private val ErrConstRe      = """\b(ERR_[A-Z][A-Z0-9_]+)\b""".r
  private val TidConstRe      = """\b(TID_[A-Z][A-Z0-9_]+)\b""".r
  private val UrlRe           = """(https?://[^\s"'<>]+[^\s"'<>.,;:)])""".r
  private val BoolNullRe      = """\b(true|false|NULL|nullptr)\b""".r

  private val CefSrcMarkerRe = """@_cefsrc:([^:]+):(\d+)""".r

  def resolveMethodDoc(
      fn: FnPtr,
      docs: Map[String, String],
      cefStructName: String = ""
  )(using naming: Naming.Context): Option[String] = {
    val camelName    = Naming.javaMethodName(fn)
    val pascalName   = Naming.cppPascalName(fn)
    val cppClassName = naming.cppClassNames.get(cefStructName)

    def qualified(name: String): Option[String] =
      cppClassName.flatMap(cls => docs.get(s"$cls::$name"))

    qualified(fn.cppName.getOrElse(""))
      .orElse(qualified(pascalName))
      .orElse(findByCapiAlias(cppClassName, docs, fn.name))
      .orElse(fn.cppName.flatMap(docs.get))
      .orElse(docs.get(pascalName))
      .orElse(docs.keys.find(_.equalsIgnoreCase(pascalName)).flatMap(docs.get))
      .orElse(docs.get(camelName))
      .orElse(docs.get(fn.name))
  }

  def resolveFreeFunctionDoc(
      ff: CefDecl.FreeFunction,
      docs: Map[String, String]
  )(using naming: Naming.Context): Option[String] = {
    val pascalName  = Naming.toPascalCase(ff.javaMethodName)
    val ownerCpp    = naming.cppClassNames.get(ff.ownerStruct)
    val ownerSuffix = ownerCpp.map(_.stripPrefix("Cef")).filter(_.nonEmpty)
    val countParams = ff.params.collect {
      case Param(name, _, _, _) if ff.params.exists(_.name == s"${name}Count") => name
    }.toSet

    def qualified(name: String): Option[String] =
      ownerCpp.flatMap(cls => docs.get(s"$cls::$name"))

    def qualifiedByOwnerSuffixNormalization: Option[String] =
      ownerCpp.flatMap { cls =>
        docs.collectFirst {
          case (key, text)
              if key.startsWith(s"$cls::") &&
                ownerSuffix.exists { suffix =>
                  val methodName = key.stripPrefix(s"$cls::")
                  methodName != pascalName && methodName.endsWith(suffix) && methodName.stripSuffix(
                    suffix
                  ) == pascalName
                } =>
            text
        }
      }

    def qualifiedByCountFunc: Option[String] =
      ownerCpp.flatMap { cls =>
        docs.collectFirst {
          case (key, text)
              if key.startsWith(s"$cls::") &&
                countParams.exists(name => text.contains(s"count_func=$name:")) =>
            text
        }
      }

    qualified(pascalName)
      .orElse(qualifiedByOwnerSuffixNormalization)
      .orElse(findByCapiAlias(ownerCpp, docs, ff.javaMethodName))
      .orElse(findByCapiAlias(ownerCpp, docs, ff.cName))
      .orElse(docs.get(s"Cef$pascalName"))
      .orElse(docs.get(pascalName))
      .orElse(docs.get(ff.javaMethodName))
      .orElse(docs.get(ff.cName))
      .orElse(qualifiedByCountFunc)
  }

  private def isStructuralDocLine(line: String): Boolean = {
    val trimmed = line.trim
    trimmed.isEmpty ||
    trimmed.startsWith("<pre") ||
    trimmed.startsWith("</pre") ||
    trimmed.startsWith("- ") ||
    trimmed.startsWith("<ul>") ||
    trimmed.startsWith("</ul>") ||
    trimmed.startsWith("<li>")
  }

  private def joinSoftWrappedLines(lines: List[String]): List[String] = {
    val (joined, current) = lines.foldLeft((List.empty[String], Option.empty[String])) { case ((acc, current), line) =>
      current match {
        case Some(buffer) if !isStructuralDocLine(buffer) && !isStructuralDocLine(line) =>
          (acc, Some(s"$buffer $line"))
        case Some(buffer) =>
          (acc :+ buffer, Some(line))
        case None =>
          (acc, Some(line))
      }
    }
    current.fold(joined)(joined :+ _)
  }

  // Convert CEF C++ doc comment syntax to Javadoc syntax.
  def convertCefDoc(
      text: String,
      capiSource: String = "",
      cPrototype: String = "",
      cppSourceOverride: String = ""
  )(using Naming.Context, Context): String = {
    val (cppSource, cleanLines) = text.linesIterator.toList.foldLeft((cppSourceOverride, List.empty[String])) {
      case ((source, acc), line) =>
        CefSrcMarkerRe.findFirstMatchIn(line) match {
          case Some(m) =>
            val nextSource = if (source.isEmpty) s"${m.group(1)}:${m.group(2)}" else source
            (nextSource, acc)
          case None =>
            (source, acc :+ line)
        }
    }

    val joinedLines = cleanLines.foldRight(List.empty[String]) { (line, acc) =>
      acc match {
        case next :: rest if line.endsWith("-") && next.nonEmpty && next.head.isLetter =>
          (line + next) :: rest
        case _ => line :: acc
      }
    }

    val paragraphLines = joinSoftWrappedLines(joinedLines)

    val (converted, _) = paragraphLines.foldLeft((List.empty[String], false)) { case ((acc, inPre), line) =>
      if (line.trim == "<pre>" || line.trim.startsWith("<pre>")) {
        (acc :+ line, true)
      } else if (line.trim == "</pre>" || line.trim.endsWith("</pre>")) {
        (acc :+ line, false)
      } else if (inPre) {
        (acc :+ escapeHtml(line), true)
      } else {
        (acc :+ convertLine(line), false)
      }
    }

    val effectiveCapiSource = if (capiSource.nonEmpty) capiSource
    else cppSource.split(":").headOption.filter(_.nonEmpty).getOrElse("")
    val capiRef = if (effectiveCapiSource.nonEmpty) {
      val preBlock = if (cPrototype.nonEmpty) s"\n<pre>${escapeHtml(cPrototype)}</pre>" else ""
      s"\n<p>Definition generated from $effectiveCapiSource$preBlock"
    } else ""
    val cppSee = if (cppSource.nonEmpty) {
      val parts = cppSource.split(":", 2)
      val file  = parts(0)
      val line  = if (parts.length > 1) parts(1) else ""
      val url   = doxygenUrl(file)
      val label = if (line.nonEmpty) s"$file:$line" else file
      if (url.nonEmpty) s"""\n@_see:<a href="$url">$label</a>"""
      else s"\n@_see:$label"
    } else ""
    val srcRef = capiRef + cppSee

    val bulletWrapped = wrapBulletLists(converted)
    val collapsed     = collapseEmptyParagraphs(bulletWrapped)
    collapsed.mkString("\n") + srcRef
  }

  private def escapeTagLiterals(text: String): String =
    AnyTagLiteralRe.replaceAllIn(text, m => s"&lt;${m.group(1)}&gt;")

  private def resolveCppRef(cppName: String, member: String)(using Naming.Context, Context): String = {
    val javaClass  = summon[Context].classNameMap.getOrElse(cppName, cppName)
    val javaMethod = Naming.toCamelCase(member.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase)
    java.util.regex.Matcher.quoteReplacement(resolveMethodLink(javaClass, javaMethod))
  }

  private def resolveCapiRef(capiStruct: String, capiMethod: String)(using Naming.Context, Context): String = {
    val javaClass  = Naming.structToJavaName(capiStruct)
    val javaMethod = Naming.toCamelCase(capiMethod)
    java.util.regex.Matcher.quoteReplacement(resolveMethodLink(javaClass, javaMethod))
  }

  private def convertLine(line: String)(using Naming.Context, Context): String =
    if (line.trim.isEmpty) "<p>"
    else {
      val escaped = escapeTagLiterals(line.replace("&", "&amp;"))
        .replace("<<", "&lt;&lt;")
        .replace("<=", "&lt;=")
        .replaceAll("""<(?=[\s\d])""", "&lt;")
      val warningBolded = escaped.replaceAll("""^(WARNING:)""", "<b>$1</b>")
      val codeRefs      = PipeRefRe.replaceAllIn(warningBolded, m => s"{@code ${m.group(1)}}")
      val cppRefs       = CppClassRefRe.replaceAllIn(
        CppCrossRefRe.replaceAllIn(codeRefs, m => resolveCppRef(m.group(1), m.group(2))),
        m => resolveCppRef(m.group(1), m.group(2))
      )
      val capiRefs = CapiMemberRefRe.replaceAllIn(
        CapiCrossRefRe.replaceAllIn(cppRefs, m => resolveCapiRef(m.group(1), m.group(2))),
        m => resolveCapiRef(m.group(1), m.group(2))
      )
      val enumRefs = CefEnumConstRe.replaceAllIn(
        capiRefs,
        m =>
          summon[Context].enumConstantMap.get(m.group(1)) match {
            case Some(qualified) => s"{@link $qualified}"
            case None            => s"{@code ${m.group(1)}}"
          }
      )
      val constRefs = TidConstRe.replaceAllIn(
        ErrConstRe.replaceAllIn(enumRefs, m => s"{@code ${m.group(1)}}"),
        m => s"{@code ${m.group(1)}}"
      )
      val nullLiterals = BoolNullRe.replaceAllIn(
        constRefs,
        m => {
          val word = if (m.group(1) == "NULL" || m.group(1) == "nullptr") "null" else m.group(1)
          s"{@code $word}"
        }
      )
      UrlRe.replaceAllIn(
        nullLiterals,
        m => {
          val url = m.group(1).replaceAll("""[.,;:"')]+$""", "")
          s"""<a href="$url">$url</a>"""
        }
      )
    }

  private def collapseEmptyParagraphs(lines: List[String]): List[String] = {
    val collapsed = lines.foldLeft(List.empty[String]) { (acc, line) =>
      if (line.trim == "<p>" && acc.lastOption.exists(_.trim == "<p>")) acc
      else if (line.trim.startsWith("<pre") && acc.lastOption.exists(_.trim == "<p>")) acc.init :+ line
      else acc :+ line
    }
    collapsed.reverse.dropWhile(_.trim == "<p>").reverse
  }

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

  def cPrototypeForMethod(structName: String, fn: FnPtr)(using Naming.Context): String = {
    val ret       = cReturnType(fn.ret)
    val self      = s"struct _$structName* self"
    val params    = fn.params.map(cParamDecl).mkString(", ")
    val allParams = if (params.isEmpty) self else s"$self, $params"
    s"$ret (CEF_CALLBACK* ${fn.name})($allParams);"
  }

  def cPrototypeForFreeFunction(ff: CefDecl.FreeFunction)(using Naming.Context): String = {
    val ret    = cReturnType(ff.ret)
    val params = if (ff.params.isEmpty) "void" else ff.params.map(cParamDecl).mkString(", ")
    s"CEF_EXPORT $ret ${ff.cName}($params);"
  }

  def cPrototypeForStruct(structName: String, hasBase: Boolean)(using Naming.Context): String = {
    val base = if (hasBase) "\n  cef_base_ref_counted_t base;\n  ...\n" else "\n  ...\n"
    s"typedef struct _$structName {$base} $structName;"
  }

  private def cReturnType(ct: CType)(using Naming.Context): String = ct match {
    case CType.JString => "cef_string_userfree_t"
    case CType.Bool    => "int"
    case other         => Naming.cType(other)
  }

  private def cParamDecl(p: Param)(using Naming.Context): String = {
    val cType = if (p.rawCType.nonEmpty) p.rawCType else Naming.cType(p.typ)
    s"$cType ${p.name}"
  }

  def cPrototypeForEnum(cefName: String, values: List[(String, Long, String)]): String = {
    val members  = values.take(5).map { case (name, _, expr) => s"  $name = $expr" }.mkString(",\n")
    val ellipsis = if (values.size > 5) ",\n  ..." else ""
    s"typedef enum {\n$members$ellipsis\n} $cefName;"
  }

  def cPrototypeForDataStruct(d: CefDecl.DataStruct)(using Naming.Context): String = {
    val fields = d.fields.map { f =>
      val cType = Naming.cType(f.typ)
      s"  $cType ${f.name};"
    }.mkString("\n")
    s"typedef struct _${d.name} {\n$fields\n} ${d.name};"
  }

  private def wrapBulletLists(lines: List[String]): List[String] = {
    val (wrapped, inList) = lines.foldLeft((List.empty[String], false)) { case ((acc, inList), line) =>
      val trimmed = line.trim
      if (trimmed.startsWith("- ")) {
        val openList = if (inList) acc else acc :+ "<ul>"
        (openList :+ s"<li>${trimmed.stripPrefix("- ")}</li>", true)
      } else if (trimmed.startsWith("  ") && inList) {
        (acc :+ trimmed, true)
      } else {
        val closedList = if (inList) acc :+ "</ul>" else acc
        (closedList :+ line, false)
      }
    }
    if (inList) wrapped :+ "</ul>" else wrapped
  }

  private def isBufferParam(p: Param): Boolean = p.typ match {
    case CType.Buffer(_) | CType.PixelBuffer => true
    case _                                   => false
  }

  private def isBufferSizeParam(p: Param): Boolean = p.typ match {
    case CType.BufferSize(_) => true
    case _                   => false
  }

  private def bufferConversionNote(params: List[Param])(using Naming.Context): List[String] = {
    val bufferParams = params.filter(isBufferParam)
    if (bufferParams.isEmpty) Nil
    else {
      val hiddenSizes = params.filter(isBufferSizeParam)
      val sizeNames   = hiddenSizes.map(p => s"{@code ${Naming.toCamelCase(p.name)}}").mkString(", ")
      // Detect array-count params: a SizeT named <array>Count immediately before an ObjectPtrArray/ByValueArray.
      val arrayCountNames = params.zip(params.drop(1)).collect {
        case (count, arr)
            if (count.typ == CType.SizeT || count.typ == CType.OutPrimitivePtr(CType.SizeT)) &&
              (arr.typ match {
                case _: CType.ObjectPtrArray | _: CType.ByValueArray => true
                case _                                               => false
              }) &&
              count.name.toLowerCase == s"${arr.name.toLowerCase}count" =>
          count.name
      }.toSet

      val remainingSizeParams = params.filter(p =>
        !bufferParams.contains(p) && !hiddenSizes.contains(p) && !arrayCountNames.contains(p.name) && (p.typ match {
          case CType.SizeT | CType.Long => true
          case _                        => false
        })
      )
      // Only emit fread/fwrite note when remaining size params look like the actual fread convention
      // (param named "n" - the element count in fread(ptr, size, n, stream)).
      val freadParams = remainingSizeParams.filter(p => p.name.toLowerCase == "n")
      val freadNote   = if (freadParams.nonEmpty) {
        val names = freadParams.map(p => s"{@code ${Naming.toCamelCase(p.name)}}").mkString(", ")
        List(
          s"<p><b>This follows the {@code fread}/{@code fwrite} convention where $names is the element count" +
            " and the buffer capacity is the element size.</b>"
        )
      } else Nil
      List(
        s"<p><b>The C API {@code void*} buffer parameter has been converted to {@link java.nio.ByteBuffer}; the" +
          s" hidden $sizeNames parameter is derived from the buffer's capacity.</b>"
      ) ++ freadNote
    }
  }

  private def bufferParamNote(p: Param): Option[String] =
    if (isBufferParam(p))
      Some(
        "<b>a direct {@link java.nio.ByteBuffer} whose capacity is the buffer size. This buffer is not reference-counted; its lifetime is not predictable beyond the scope of this callback. Storing a reference to it is unsafe unless explicitly permitted by the CEF documentation and may lead to native crashes.</b>"
      )
    else None

  def forMethod(
      fn: FnPtr,
      docs: Map[String, String],
      capiSource: String = "",
      cPrototype: String = "",
      cefStructName: String = ""
  )(using naming: Naming.Context, dc: Context): String = {
    val comment = resolveMethodDoc(fn, docs, cefStructName)

    comment match {
      case Some(text) =>
        val metaAttrs = CefMetaPattern.findAllMatchIn(text).flatMap { m =>
          parseMetaAttrs(m.group(1))
        }.toList

        val cleanText                = CefMetaPattern.replaceAllIn(text, "").trim
        val convertedText            = convertCefDoc(cleanText, capiSource, cPrototype)
        val (docText, sourceRefTags) = extractSourceTags(convertedText)

        val metaSentences = metaAttrsToSentences(metaAttrs, fn)

        val docLines      = docText.linesIterator.filter(_.nonEmpty).toList
        val countFuncNote = fn.ret match {
          case CType.CountFuncArray(elem, countFuncCName, _, _) =>
            val countMethodJava = Naming.toCamelCase(countFuncCName)
            val kind            = if (Naming.isPrimitiveElement(elem)) "array" else "array/collection"
            List(
              s"<p><b>The C API exposes this as a two-pass pattern: first call {@link #$countMethodJava()} to obtain",
              s"the count, then allocate and populate the $kind. This method performs both steps and returns the",
              "result directly.</b>"
            )
          case _ => Nil
        }
        val bufferNote               = bufferConversionNote(fn.params)
        val (beforeDef, defAndAfter) = docLines.span(!_.startsWith("<p>Definition generated"))
        val allContent               = beforeDef ++ metaSentences ++ countFuncNote ++ bufferNote ++ defAndAfter

        val optionalParams = metaAttrs.collect { case ("optional_param", p) => p }.toSet
        val indexParams    = metaAttrs.collect { case ("index_param", p) => p }.toSet
        val paramDocs      = fn.params.flatMap { p =>
          val pName = Naming.toCamelCase(p.name)
          val notes = List(
            if (optionalParams.contains(p.name)) Some("may be null") else None,
            if (indexParams.contains(p.name)) Some("zero-based index") else None,
            bufferParamNote(p)
          ).flatten
          if (notes.nonEmpty) Some(s"     * @param $pName ${notes.mkString(", ")}")
          else None
        }

        val returnDoc = fn.metaAttrs.collectFirst { case ("default_retval", v) => v } match {
          case Some(retVal) => List(s"     * @return the result, or {@code $retVal} for default handling")
          case None         => Nil
        }

        val seeDoc = sourceRefTags.map(tag => s"     * @see $tag")

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

  // Extract --cef(...)-- meta attributes as key/value pairs, preserving repeated keys.
  def extractAttrsList(docText: String): List[(String, String)] =
    CefMetaPattern.findAllMatchIn(docText)
      .flatMap(m => parseMetaAttrs(m.group(1)))
      .toList

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

  private def metaAttrsToSentences(attrs: List[(String, String)], fn: FnPtr): List[String] =
    attrs.flatMap {
      case ("count_func", _) =>
        None
      case ("added", version) =>
        Some(s"<p>Added in CEF API version $version.")
      case _ => None
    }
}
