package net.kurobako.cef4j.codegen

import java.nio.file.Path

object JavaRecordCodeGen {

  /** True for the `size_t size` field that CEF uses for struct version validation. */
  private def isSizeField(f: Field): Boolean = f.name == "size" && f.typ == CType.SizeT

  def emit(decl: CefDecl.DataStruct, outDir: Path, classDoc: String = "", needsMutable: Boolean = false): Unit = {
    val javaName = Naming.structToJavaName(decl.name)
    val cProto   = DocComments.cPrototypeForDataStruct(decl)
    val content  = render(javaName, decl.fields, classDoc, needsMutable, decl.sourceHeader, cProto)
    JavaCodeGen.writeJavaFile(outDir, javaName, content)
    if (needsMutable) {
      val mutableName    = s"CefMutable${Naming.cefBaseName(decl.name).split("_").map(Naming.capitalise).mkString}"
      val mutableContent = renderMutable(mutableName, javaName, decl.fields, classDoc, decl.sourceHeader, cProto)
      JavaCodeGen.writeJavaFile(outDir, mutableName, mutableContent)
    }
  }

  private def render(
      javaName: String,
      fields: List[Field],
      classDoc: String = "",
      needsMutable: Boolean = false,
      sourceHeader: String = "",
      cPrototype: String = ""
  ): String = {
    val mutableName = s"CefMutable${javaName.stripPrefix("Cef")}"

    val userFields = fields.filterNot(isSizeField)
    val hasSize    = fields.exists(isSizeField)

    val sizeFieldDecl = if (hasSize) {
      s"""    // Native struct size — set by JNI, not user-modifiable.
         |    @SuppressWarnings("FieldMayBeFinal")
         |    private volatile long size = -1;
         |
         |""".stripMargin
    } else ""

    val fieldDecls = userFields.map { f =>
      s"    public final ${Naming.javaType(f.typ)} ${Naming.toCamelCase(f.name)};"
    }.mkString("\n")

    val ctorParams = userFields.map { f =>
      s"${Naming.javaType(f.typ)} ${Naming.toCamelCase(f.name)}"
    }.mkString(", ")

    val ctorAssigns = userFields.map { f =>
      val n = Naming.toCamelCase(f.name)
      s"        this.$n = $n;"
    }.mkString("\n")

    val toMutableMethod = if (needsMutable) {
      val args = userFields.map(f => s"this.${Naming.toCamelCase(f.name)}").mkString(", ")
      s"""
         |
         |    /** Create a mutable copy of this instance. */
         |    public $mutableName toMutable() {
         |        return new $mutableName($args);
         |    }""".stripMargin
    } else ""

    JavaCodeGen.renderJavaFile(
      declaration = s"public final class $javaName",
      body = s"""$sizeFieldDecl$fieldDecls
                |
                |    public $javaName($ctorParams) {
                |$ctorAssigns
                |    }$toMutableMethod
                |
                |${renderEquals(javaName, userFields)}
                |
                |${renderHashCode(userFields)}
                |
                |${renderToString(javaName, fields)}""".stripMargin,
      classDoc = classDoc,
      capiSource = sourceHeader,
      cPrototype = cPrototype,
      cppSource = sourceHeader
    )
  }

  private def renderMutable(
      mutableName: String,
      immutableName: String,
      fields: List[Field],
      classDoc: String,
      sourceHeader: String = "",
      cPrototype: String = ""
  ): String = {
    val userFields = fields.filterNot(isSizeField)
    val hasSize    = fields.exists(isSizeField)

    val sizeFieldDecl = if (hasSize) {
      s"""    // Native struct size — set by JNI, not user-modifiable.
         |    @SuppressWarnings("FieldMayBeFinal")
         |    private volatile long size = -1;
         |
         |""".stripMargin
    } else ""

    val fieldDecls = userFields.map { f =>
      s"    public ${Naming.javaType(f.typ)} ${Naming.toCamelCase(f.name)};"
    }.mkString("\n")

    val ctorParams = userFields.map { f =>
      s"${Naming.javaType(f.typ)} ${Naming.toCamelCase(f.name)}"
    }.mkString(", ")

    val ctorAssigns = userFields.map { f =>
      val n = Naming.toCamelCase(f.name)
      s"        this.$n = $n;"
    }.mkString("\n")

    val defaultCtor = s"""    public $mutableName() {}"""

    val toImmutableArgs = userFields.map(f => s"this.${Naming.toCamelCase(f.name)}").mkString(", ")

    val mutableDoc = if (classDoc.nonEmpty) s"Mutable variant of {@link $immutableName}. $classDoc" else ""

    JavaCodeGen.renderJavaFile(
      declaration = s"public final class $mutableName",
      body = s"""$sizeFieldDecl$fieldDecls
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
                |${renderEquals(mutableName, userFields)}
                |
                |${renderHashCode(userFields)}
                |
                |${renderToString(mutableName, fields)}""".stripMargin,
      classDoc = mutableDoc,
      capiSource = sourceHeader,
      cPrototype = cPrototype,
      cppSource = sourceHeader
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

    if (fields.isEmpty) {
      s"""    @Override
    public boolean equals(Object obj) {
        return this == obj || obj instanceof $className;
    }"""
    } else {
      s"""    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof $className)) return false;
        $className other = ($className) obj;
        return $comparisons;
    }"""
    }
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
      if (isSizeField(f)) s""""$n=" + ($n == -1 ? "pending" : Long.toString($n))"""
      else s""""$n=" + $n"""
    }.mkString(""" + ", " + """)
    s"""    @Override
    public String toString() {
        return "$className{" + $parts + "}";
    }"""
  }
}
