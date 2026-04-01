package net.kurobako.cef4j.codegen

import java.nio.file.Path

object JavaEnumCodeGen {

  /** Regex for expressions that are safe to use as Java constant expressions (no named references). */
  private val JavaSafeExprRe = """^[\d\s+\-*/|&^~<>()xXaAbBcCdDeEfFlL]+$""".r

  def emit(decl: CefDecl.Enum, outDir: Path, sourceHeader: String = ""): Unit = {
    val javaName = Naming.structToJavaName(decl.name)
    val content  = render(javaName, decl.name, decl.values, decl.doc, decl.valueDocs, sourceHeader)
    JavaCodeGen.writeJavaFile(outDir, javaName, content)
  }

  private def javaExpr(rawExpr: String, evaluated: Long): String = {
    val trimmed = rawExpr.trim
    if (JavaSafeExprRe.matches(trimmed)) trimmed
    else s"${evaluated}L"
  }

  private def render(
      javaName: String,
      cefName: String,
      values: List[(String, Long, String)],
      doc: String = "",
      valueDocs: Map[String, String] = Map.empty,
      sourceHeader: String = ""
  ): String = {
    // Deduplicate enum constants - keep first occurrence of each name
    val deduped = values.foldLeft((Set.empty[String], List.empty[(String, Long, String)])) {
      case ((seen, acc), entry @ (name, _, _)) if seen.contains(name) => (seen, acc)
      case ((seen, acc), entry @ (name, _, _))                        => (seen + name, entry :: acc)
    }._2.reverse

    // Strip common prefix from constant names
    val cNames  = deduped.map(_._1)
    val prefix  = Naming.computeEnumPrefix(cNames)
    val jNames  = cNames.map(_.stripPrefix(prefix))
    val renamed = deduped.zip(jNames).map { case ((cName, v, rawExpr), jName) => (cName, jName, v, rawExpr) }

    val kindConstants = renamed.map { case (cName, jName, v, rawExpr) =>
      val javadoc = valueDocs.get(cName) match {
        case Some(d) =>
          val converted = DocComments.convertCefDoc(d)
          s"        /** $converted */\n"
        case None => ""
      }
      val expr    = javaExpr(rawExpr, v)
      val exprLit = rawExpr.replace("\\", "\\\\").replace("\"", "\\\"")
      s"""${javadoc}        $jName($expr, "$exprLit", "$cName")"""
    }.mkString(",\n")

    val cProto = DocComments.cPrototypeForEnum(cefName, deduped)

    // Build "Possible values" list — appended raw, not through convertCefDoc
    val possibleValues = jNames.map(n => s"{@link Kind#$n}").mkString(", ")
    val valuesDocLine  = s"<p>Possible values: $possibleValues"

    JavaCodeGen.renderJavaFile(
      declaration = s"public final class $javaName implements CefEnum<$javaName>",
      body =
        s"""    /** Known constants for {@link $javaName}. */
           |    public enum Kind {
           |$kindConstants;
           |
           |        private static final Kind[] VALUES = Kind.values();
           |
           |        /** The underlying C enum numeric value. */
           |        public final long value;
           |
           |        /** The original C expression (e.g., {@code "1 << 3"}), or the numeric string. */
           |        public final String expr;
           |
           |        /** The C constant name (e.g., {@code "$cefName"}). */
           |        public final String name;
           |
           |        Kind(long value, String expr, String name) {
           |            this.value = value;
           |            this.expr = expr;
           |            this.name = name;
           |        }
           |
           |        @Override
           |        public String toString() {
           |            return name + "(expr=" + expr + ", value=" + value + ")";
           |        }
           |    }
           |
           |    /** The underlying C enum numeric value. May not correspond to any known {@link Kind}. */
           |    public final long value;
           |
           |    private $javaName(long value) {
           |        this.value = value;
           |    }
           |
           |    @Override
           |    public long value() {
           |        return value;
           |    }
           |
           |    @Override
           |    public String expr() {
           |        return kind().map(k -> k.expr).orElse(String.valueOf(value));
           |    }
           |
           |    @Override
           |    public String name() {
           |        return kind().map(k -> k.name).orElse("UNKNOWN(" + value + ")");
           |    }
           |
           |    /**
           |     * Returns the {@link Kind} matching this value, or empty for unknown/composite values.
           |     * Use this for exhaustive switch over known constants.
           |     */
           |    public java.util.Optional<Kind> kind() {
           |        for (Kind k : Kind.VALUES) {
           |            if (k.value == value) return java.util.Optional.of(k);
           |        }
           |        return java.util.Optional.empty();
           |    }
           |
           |    /**
           |     * Returns an instance for the given raw value.
           |     * No data is lost — unknown or composite values are preserved.
           |     */
           |    public static $javaName of(long v) {
           |        return new $javaName(v);
           |    }
           |
           |    /** Returns an instance for the given known constant. */
           |    public static $javaName of(Kind k) {
           |        return new $javaName(k.value);
           |    }
           |
           |    @Override
           |    public boolean equals(Object obj) {
           |        if (this == obj) return true;
           |        if (!(obj instanceof $javaName)) return false;
           |        return this.value == (($javaName) obj).value;
           |    }
           |
           |    @Override
           |    public int hashCode() {
           |        return Long.hashCode(value);
           |    }
           |
           |    @Override
           |    public String toString() {
           |        return kind().map(Kind::toString).orElse("UNKNOWN(value=" + value + ")");
           |    }""".stripMargin,
      classDoc = doc,
      classDocSuffix = valuesDocLine,
      capiSource = sourceHeader,
      cPrototype = cProto,
      cppSource = sourceHeader
    )
  }
}
