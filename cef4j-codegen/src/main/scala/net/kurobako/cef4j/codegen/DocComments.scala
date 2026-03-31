package net.kurobako.cef4j.codegen

object DocComments {

  /** Regex matching --cef(...)-- metacomments in C++ header docs. */
  private val CefMetaPattern = """--cef\(([^)]*)\)--""".r

  /** Generate javadoc for a function pointer, looking up by both snake_case and CamelCase names. */
  def forMethod(fn: FnPtr, docs: Map[String, String]): String = {
    val camelName  = Naming.toCamelCase(fn.name)
    val pascalName = Naming.toPascalCase(fn.name)

    val comment = docs.get(camelName)
      .orElse(docs.get(pascalName))
      .orElse(docs.get(fn.name))

    comment match {
      case Some(text) =>
        // Extract and remove --cef(...)-- metacomments
        val metaAttrs = CefMetaPattern.findAllMatchIn(text).flatMap { m =>
          parseMetaAttrs(m.group(1))
        }.toList

        // Remove the metacomment line(s) from visible text
        val cleanText = CefMetaPattern.replaceAllIn(text, "").trim

        // Build human-readable sentences from meta attributes
        val metaSentences = metaAttrsToSentences(metaAttrs, fn)

        // Combine remaining doc text with meta-derived sentences
        val docLines   = cleanText.linesIterator.filter(_.nonEmpty).toList
        val allContent = docLines ++ metaSentences

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

        // If there's no content at all, skip the javadoc entirely
        if (allContent.isEmpty && paramDocs.isEmpty && returnDoc.isEmpty) {
          ""
        } else {
          val contentLines = allContent.map(l => s"     * $l")
          val tagSeparator = if (contentLines.nonEmpty && (paramDocs.nonEmpty || returnDoc.nonEmpty)) List("") else Nil
          val sections     = contentLines ++ tagSeparator ++ paramDocs ++
            (if (paramDocs.nonEmpty && returnDoc.nonEmpty) List("") else Nil) ++ returnDoc

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
      case ("count_func", spec) =>
        spec match {
          case s"$param:$func" if param.nonEmpty && func.nonEmpty =>
            Some(s"<p>The size of {@code $param} is determined by {@code $func()}.")
          case _ => None
        }
      case ("added", version) =>
        Some(s"<p>Added in CEF API version $version.")
      case _ => None
    }
}
