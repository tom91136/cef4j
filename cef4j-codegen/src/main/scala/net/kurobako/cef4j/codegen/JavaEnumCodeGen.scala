package net.kurobako.cef4j.codegen

import java.nio.file.Path

object JavaEnumCodeGen {

  def emit(decl: CefDecl.Enum, outDir: Path): Unit = {
    val javaName = Naming.structToJavaName(decl.name)
    val content  = render(javaName, decl.values, decl.doc, decl.valueDocs)
    JavaCodeGen.writeJavaFile(outDir, javaName, content)
  }

  def emitToString(decl: CefDecl.Enum): String = {
    val javaName = Naming.structToJavaName(decl.name)
    render(javaName, decl.values, decl.doc, decl.valueDocs)
  }

  private def render(
      javaName: String,
      values: List[(String, Long)],
      doc: String = "",
      valueDocs: Map[String, String] = Map.empty
  ): String = {
    // Deduplicate enum constants - keep first occurrence of each name
    val deduped = values.foldLeft((Set.empty[String], List.empty[(String, Long)])) {
      case ((seen, acc), entry @ (name, _)) if seen.contains(name) =>
        (seen, acc)
      case ((seen, acc), entry @ (name, _)) =>
        (seen + name, entry :: acc)
    }._2.reverse
    val entries = (deduped :+ ("UNKNOWN" -> -1L))
      .map { case (name, v) =>
        val javadoc = valueDocs.get(name) match {
          case Some(d) => s"    /** $d */\n"
          case None    => ""
        }
        s"$javadoc    $name(${v}L)"
      }
      .mkString(",\n")

    JavaCodeGen.renderJavaFile(
      declaration = s"public enum $javaName",
      body = s"""$entries;
                |
                |    public final long value;
                |
                |    $javaName(long v) {
                |        this.value = v;
                |    }
                |
                |    public static $javaName fromLong(long v) {
                |        for ($javaName e : values()) {
                |            if (e.value == v) return e;
                |        }
                |        return UNKNOWN;
                |    }""".stripMargin,
      classDoc = doc
    )
  }
}
