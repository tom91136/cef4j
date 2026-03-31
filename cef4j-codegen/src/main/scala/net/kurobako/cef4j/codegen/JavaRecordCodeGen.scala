package net.kurobako.cef4j.codegen

import java.nio.file.Path

object JavaRecordCodeGen {

  def emit(decl: CefDecl.DataStruct, outDir: Path, classDoc: String = "", needsMutable: Boolean = false): Unit = {
    val javaName = Naming.structToJavaName(decl.name)
    val content  = render(javaName, decl.fields, classDoc, needsMutable)
    JavaCodeGen.writeJavaFile(outDir, javaName, content)
    if (needsMutable) {
      val mutableName    = s"CefMutable${Naming.cefBaseName(decl.name).split("_").map(Naming.capitalise).mkString}"
      val mutableContent = renderMutable(mutableName, javaName, decl.fields, classDoc)
      JavaCodeGen.writeJavaFile(outDir, mutableName, mutableContent)
    }
  }

  private def render(
      javaName: String,
      fields: List[Field],
      classDoc: String = "",
      needsMutable: Boolean = false
  ): String = {
    val mutableName = s"CefMutable${javaName.stripPrefix("Cef")}"

    val fieldDecls = fields.map { f =>
      s"    public final ${Naming.javaType(f.typ)} ${Naming.toCamelCase(f.name)};"
    }.mkString("\n")

    val ctorParams = fields.map { f =>
      s"${Naming.javaType(f.typ)} ${Naming.toCamelCase(f.name)}"
    }.mkString(", ")

    val ctorAssigns = fields.map { f =>
      val n = Naming.toCamelCase(f.name)
      s"        this.$n = $n;"
    }.mkString("\n")

    val toMutableMethod = if (needsMutable) {
      val args = fields.map(f => s"this.${Naming.toCamelCase(f.name)}").mkString(", ")
      s"""
         |
         |    /** Create a mutable copy of this instance. */
         |    public $mutableName toMutable() {
         |        return new $mutableName($args);
         |    }""".stripMargin
    } else ""

    JavaCodeGen.renderJavaFile(
      declaration = s"public final class $javaName",
      body = s"""$fieldDecls
                |
                |    public $javaName($ctorParams) {
                |$ctorAssigns
                |    }$toMutableMethod
                |
                |${renderEquals(javaName, fields)}
                |
                |${renderHashCode(fields)}
                |
                |${renderToString(javaName, fields)}""".stripMargin,
      classDoc = classDoc
    )
  }

  private def renderMutable(
      mutableName: String,
      immutableName: String,
      fields: List[Field],
      classDoc: String
  ): String = {
    val fieldDecls = fields.map { f =>
      s"    public ${Naming.javaType(f.typ)} ${Naming.toCamelCase(f.name)};"
    }.mkString("\n")

    val ctorParams = fields.map { f =>
      s"${Naming.javaType(f.typ)} ${Naming.toCamelCase(f.name)}"
    }.mkString(", ")

    val ctorAssigns = fields.map { f =>
      val n = Naming.toCamelCase(f.name)
      s"        this.$n = $n;"
    }.mkString("\n")

    val defaultCtor = s"""    public $mutableName() {}"""

    val toImmutableArgs = fields.map(f => s"this.${Naming.toCamelCase(f.name)}").mkString(", ")

    val mutableDoc = if (classDoc.nonEmpty) s"Mutable variant of {@link $immutableName}. $classDoc" else ""

    JavaCodeGen.renderJavaFile(
      declaration = s"public final class $mutableName",
      body = s"""$fieldDecls
                |
                |$defaultCtor
                |
                |    public $mutableName($ctorParams) {
                |$ctorAssigns
                |    }
                |
                |    /** Create an immutable snapshot of this instance. */
                |    public $immutableName toImmutable() {
                |        return new $immutableName($toImmutableArgs);
                |    }
                |
                |${renderEquals(mutableName, fields)}
                |
                |${renderHashCode(fields)}
                |
                |${renderToString(mutableName, fields)}""".stripMargin,
      classDoc = mutableDoc
    )
  }

  private def isPrimitive(typ: CType): Boolean = typ match {
    case CType.Bool | CType.Int | CType.UInt | CType.Long | CType.SizeT | CType.Float | CType.Double => true
    case _                                                                                           => false
  }

  private def renderEquals(className: String, fields: List[Field]): String = {
    val comparisons = fields.map { f =>
      val n = Naming.toCamelCase(f.name)
      if (isPrimitive(f.typ)) s"this.$n == other.$n"
      else s"java.util.Objects.equals(this.$n, other.$n)"
    }.mkString("\n                && ")

    s"""    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof $className)) return false;
        $className other = ($className) obj;
        return $comparisons;
    }"""
  }

  private def renderHashCode(fields: List[Field]): String = {
    val args = fields.map(f => Naming.toCamelCase(f.name)).mkString(", ")
    s"""    @Override
    public int hashCode() {
        return java.util.Objects.hash($args);
    }"""
  }

  private def renderToString(className: String, fields: List[Field]): String = {
    val parts = fields.map { f =>
      val n = Naming.toCamelCase(f.name)
      s""""$n=" + $n"""
    }.mkString(""" + ", " + """)
    s"""    @Override
    public String toString() {
        return "$className{" + $parts + "}";
    }"""
  }
}
