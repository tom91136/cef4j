package net.kurobako.cef4j.codegen

import java.nio.file.Path

object JavaEnumCodeGen {

  private val JavaSafeExprRe = """^[\d\s+\-*/|&^~<>()xXaAbBcCdDeEfFlL]+$""".r
  private val ExprTokenRe    = """0[xX][0-9A-Fa-f]+[UuLl]*|\d+[UuLl]*|<<|>>|[()+\-*/|&^~]|[A-Za-z_]\w*""".r

  def emit(decl: CefDecl.Enum, outDir: Path, sourceHeader: String = "")(using
      Naming.Context,
      DocComments.Context,
      Banners
  ): Unit = {
    val javaName = Naming.structToJavaName(decl.name)
    val subPkg   = summon[Naming.Context].subPackages.getOrElse(decl.name, "")
    val content  = render(javaName, decl.name, decl.values, decl.doc, decl.valueDocs, sourceHeader, subPkg)
    JavaCodeGen.writeJavaFile(outDir, javaName, content, subPkg)
  }

  private val SimpleLiteralRe = """0[xX][0-9A-Fa-f]+[UuLl]*|[0-9]+[UuLl]*""".r

  private def canonicalJavaLongLiteral(value: Long): String =
    if (java.lang.Long.compareUnsigned(value, 0x7fffffffL) > 0) s"0x${java.lang.Long.toUnsignedString(value, 16)}L"
    else s"${value}L"

  private def javaExpr(rawExpr: String, evaluated: Long): String = {
    val trimmed = rawExpr.trim
    if (JavaSafeExprRe.matches(trimmed)) {
      // Wide Java integer literals require an L suffix.
      val needsLong = evaluated > Int.MaxValue || evaluated < Int.MinValue
      if (!needsLong) trimmed
      else if (SimpleLiteralRe.matches(trimmed)) s"${stripCSuffix(trimmed)}L"
      else canonicalJavaLongLiteral(evaluated)
    } else canonicalJavaLongLiteral(evaluated)
  }

  private def stripCSuffix(token: String): String =
    token.replaceAll("[UuLl]+$", "")

  private def normaliseExprToken(token: String, inCompositeExpr: Boolean): String = {
    val trimmed = token.trim
    if (trimmed.matches("""0[xX][0-9A-Fa-f]+[UuLl]*""")) {
      val clean = stripCSuffix(trimmed)
      val value = java.lang.Long.parseUnsignedLong(clean.substring(2), 16)
      s"0x${java.lang.Long.toUnsignedString(value, 16)}"
    } else if (trimmed.matches("""\d+[UuLl]*""")) {
      val clean = stripCSuffix(trimmed)
      val value = java.lang.Long.parseUnsignedLong(clean, 10)
      if (inCompositeExpr && value >= 4096) s"0x${java.lang.Long.toUnsignedString(value, 16)}" else clean
    } else {
      trimmed
    }
  }

  private def canonicalLargeLiteral(value: Long): String =
    if (java.lang.Long.compareUnsigned(value, 0x7fffffffL) > 0) s"0x${java.lang.Long.toUnsignedString(value, 16)}"
    else java.lang.Long.toString(value)

  private def canonicalExprText(rawExpr: String, evaluated: Long): String = {
    val trimmed = rawExpr.trim
    if (trimmed.isEmpty) trimmed
    else {
      val noWs            = trimmed.replaceAll("\\s+", "")
      val tokens          = ExprTokenRe.findAllIn(trimmed).toList
      val inCompositeExpr =
        tokens.exists(t =>
          t == "<<" || t == ">>" || t == "+" || t == "-" || t == "*" || t == "/" || t == "|" || t == "&" || t == "^"
        )
      val hasArithmetic = tokens.exists(t => t == "+" || t == "-" || t == "*" || t == "/")
      val hasIdentifier = tokens.exists(_.matches("""[A-Za-z_]\w*"""))
      val isUnaryMinus  =
        tokens.length == 2 &&
          tokens.head == "-" &&
          (tokens(1).matches("""\d+[UuLl]*""") || tokens(1).matches("""0[xX][0-9A-Fa-f]+[UuLl]*"""))
      if (tokens.isEmpty || tokens.mkString != noWs) trimmed
      else if (hasArithmetic && !hasIdentifier && !isUnaryMinus) canonicalLargeLiteral(evaluated)
      else
        tokens
          .map(t => normaliseExprToken(t, inCompositeExpr))
          .mkString(" ")
          .replaceAll("\\(\\s+", "(")
          .replaceAll("\\s+\\)", ")")
    }
  }

  private def render(
      javaName: String,
      cefName: String,
      values: List[(String, Long, String)],
      doc: String,
      valueDocs: Map[String, String],
      sourceHeader: String,
      subPackage: String
  )(using Naming.Context, DocComments.Context, Banners): String = {
    val deduped = values.foldLeft((Set.empty[String], List.empty[(String, Long, String)])) {
      case ((seen, acc), (name, _, _)) if seen.contains(name) => (seen, acc)
      case ((seen, acc), entry @ (name, _, _))                => (seen + name, entry :: acc)
    }._2.reverse

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
      val exprLit = canonicalExprText(rawExpr, v).replace("\\", "\\\\").replace("\"", "\\\"")
      s"""$javadoc        $jName($expr, "$exprLit", "$cName")"""
    }.mkString(",\n")

    val cProto = DocComments.cPrototypeForEnum(cefName, deduped)

    val possibleValues = jNames.map(n => s"{@link Kind#$n}").mkString(", ")
    val valuesDocLine  = s"<p>Possible values: $possibleValues"

    val basePkg    = Naming.javaPackage
    val enumImport =
      if (subPackage.nonEmpty) List(s"import $basePkg.CefEnum;")
      else Nil

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
           |        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
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
           |    /** The underlying C enum numeric value. This may not correspond to any known {@link Kind}. */
           |    public final long value;
           |
           |    private $javaName(long value) {
           |        this.value = value;
           |    }
           |
		   |    /** {@inheritDoc} */
           |    @Override
           |    public long value() {
           |        return value;
           |    }
           |
		   |    /** {@inheritDoc} */
           |    @Override
           |    public String expr() {
           |        return kind().map(k -> k.expr).orElse(String.valueOf(value));
           |    }
           |
		   |    /** {@inheritDoc} */
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
		   |    /** Returns an instance for the given raw value, use {@link #kind} to resolve to a concrete enum. */
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
      cppSource = sourceHeader,
      imports = enumImport,
      subPackage = subPackage
    )
  }
}
