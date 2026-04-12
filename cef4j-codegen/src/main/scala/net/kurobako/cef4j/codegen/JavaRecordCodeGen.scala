package net.kurobako.cef4j.codegen

import java.nio.file.Path

object JavaRecordCodeGen {

  private def isSizeField(f: Field): Boolean = f.name == "size" && f.typ == CType.SizeT

  private val NativeSizeDecl =
    s"""    // Native struct size, populated by the JNI layer with sizeof(struct) as required by CEF. Not user-modifiable.
                                         |    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
                                         |    private volatile long size = -1;
                                         |
                                         |""".stripMargin

  def emit(
      decl: CefDecl.DataStruct,
      outDir: Path,
      classDoc: String = "",
      needsMutable: Boolean = false,
      fieldDocs: Map[String, String] = Map.empty,
      emitAsPlatformInterface: Boolean = false,
      platformImplSubPackages: List[String] = Nil,
      implementSharedType: Boolean = false
  )(using
      Naming.Context,
      DocComments.Context,
      Banners
  ): Unit = {
    val javaName = Naming.structToJavaName(decl.name)
    val subPkg   = summon[Naming.Context].subPackages.getOrElse(decl.name, "")
    val cProto   = DocComments.cPrototypeForDataStruct(decl)
    val content  = render(
      javaName,
      decl.fields,
      classDoc,
      needsMutable,
      decl.sourceHeader,
      cProto,
      fieldDocs,
      subPkg,
      emitAsPlatformInterface,
      platformImplSubPackages,
      implementSharedType
    )
    JavaCodeGen.writeJavaFile(outDir, javaName, content, subPkg)
  }

  private val SectionMarkerRe = """@_section:(.+)""".r

  private def renderFieldDoc(fieldName: String, fieldDocs: Map[String, String])(using
      Naming.Context,
      DocComments.Context
  ): String =
    fieldDocs.get(fieldName) match {
      case Some(doc) =>
        val (rawLines, sectionLines) = doc.linesIterator.toList.partition(!_.startsWith("@_section:"))
        val sectionNote              = sectionLines.collectFirst { case SectionMarkerRe(note) => note }
        val rawDoc                   = rawLines.mkString("\n")
        val converted                = DocComments.convertCefDoc(rawDoc)
        val (cleanDoc, seeTags)      = DocComments.extractSourceTags(converted)
        val lines                    = cleanDoc.linesIterator.filter(_.nonEmpty).toList
        val noteLine                 = sectionNote.map(n => s"<p><i>$n</i>").toList
        val allContent               = lines ++ noteLine
        if (allContent.isEmpty) ""
        else {
          val docLines = allContent.map(l => s"     * $l")
          val seeLines = seeTags.map(tag => s"     * @see $tag")
          val allLines = if (seeLines.nonEmpty) docLines ++ List("     *") ++ seeLines else docLines
          s"""    /**
${allLines.mkString("\n")}
     */
"""
        }
      case None => ""
    }

  private def render(
      javaName: String,
      fields: List[Field],
      classDoc: String = "",
      needsMutable: Boolean = false,
      sourceHeader: String = "",
      cPrototype: String = "",
      fieldDocs: Map[String, String] = Map.empty,
      subPackage: String = "",
      emitAsPlatformInterface: Boolean = false,
      platformImplSubPackages: List[String] = Nil,
      implementSharedType: Boolean = false
  )(using Naming.Context, DocComments.Context, Banners): String = {
    if (emitAsPlatformInterface) {
      val rootPkg = Naming.javaPackage
      val impls   = platformImplSubPackages.distinct.map(p => s"{@link $rootPkg.$p.$javaName}")
      val suffix  = if (impls.nonEmpty) s"Platform-specific implementations: ${impls.mkString(", ")}." else ""
      return JavaCodeGen.renderJavaFile(
        declaration = s"public interface $javaName",
        body = "",
        classDoc = classDoc,
        capiSource = sourceHeader,
        cPrototype = cPrototype,
        cppSource = sourceHeader,
        classDocSuffix = suffix,
        subPackage = subPackage
      )
    }

    val userFields = fields.filterNot(isSizeField)
    val hasSize    = fields.exists(isSizeField)
    val allTypes   = fields.map(_.typ)

    val fieldDecls  = renderFieldDecls(userFields, fieldDocs, "    ", "    public final ")
    val ctorParams  = renderCtorParams(userFields)
    val ctorAssigns = renderCtorAssigns(userFields, "        ")

    val typeImports     = allTypes.flatMap(Naming.javaImports).distinct.sorted.map(i => s"import $i;")
    val crossPkgImports = if (subPackage.nonEmpty) {
      val cefNames      = allTypes.flatMap(Naming.referencedCefNames).distinct
      val basePkg       = Naming.javaPackage
      val thisPkg       = s"$basePkg.$subPackage"
      val fromTypes     = cefNames.map(n => Naming.fullyQualifiedJavaName(n)).filter(!_.startsWith(s"$thisPkg."))
      val hasOpaquePtr  = allTypes.exists(_ == CType.OpaquePtr)
      val helperImports = if (hasOpaquePtr) List(s"$basePkg.NativePointer") else Nil
      (fromTypes ++ helperImports).distinct.sorted.map(fqn => s"import $fqn;")
    } else Nil
    val allImports = (typeImports ++ crossPkgImports).distinct.sorted

    val toMutableMethod = if (needsMutable) {
      val args = renderThisArgs(userFields)
      s"""
         |
         |    /** Create a mutable copy of this instance. */
         |    public Mutable toMutable() {
         |        return new Mutable($args);
         |    }""".stripMargin
    } else ""

    val mutableInnerClass = if (needsMutable) {
      s"""
         |
         |${renderMutableInner(javaName, fields, classDoc, fieldDocs)}""".stripMargin
    } else ""

    val declaration = if (implementSharedType) {
      s"public final class $javaName implements ${Naming.javaPackage}.$javaName"
    } else s"public final class $javaName"

    JavaCodeGen.renderJavaFile(
      declaration = declaration,
      body =
        s"""${if (hasSize) NativeSizeDecl else ""}$fieldDecls
			 |
			 |    public $javaName($ctorParams) {
			 |$ctorAssigns
			 |    }$toMutableMethod
			 |
			 |${renderEquals(javaName, userFields)}
			 |
			 |${renderHashCode(userFields)}
			 |
			 |${renderToString(javaName, fields)}$mutableInnerClass""".stripMargin,
      classDoc = classDoc,
      capiSource = sourceHeader,
      cPrototype = cPrototype,
      cppSource = sourceHeader,
      imports = allImports,
      subPackage = subPackage
    )
  }

  private def renderMutableInner(
      immutableName: String,
      fields: List[Field],
      classDoc: String,
      fieldDocs: Map[String, String] = Map.empty
  )(using Naming.Context, DocComments.Context): String = {
    val userFields = fields.filterNot(isSizeField)
    val hasSize    = fields.exists(isSizeField)

    val fieldDecls      = renderFieldDecls(userFields, fieldDocs, "        ", "        public ")
    val ctorParams      = renderCtorParams(userFields)
    val ctorAssigns     = renderCtorAssigns(userFields, "            ")
    val toImmutableArgs = renderThisArgs(userFields)

    val mutableDoc = {
      val docText = if (classDoc.nonEmpty) s"Mutable variant of {@link $immutableName}. $classDoc"
      else s"Mutable variant of {@link $immutableName}."
      val converted                 = DocComments.convertCefDoc(docText)
      val (cleanDoc, sourceRefTags) = DocComments.extractSourceTags(converted)
      val lines                     = cleanDoc.linesIterator.filter(_.nonEmpty).map(l => s"     * $l").toList
      val seeTags                   = sourceRefTags.map(tag => s"     * @see $tag")
      val separator                 = if (lines.nonEmpty && seeTags.nonEmpty) List("     *") else Nil
      val allLines                  = lines ++ separator ++ seeTags
      s"""    /**
${allLines.mkString("\n")}
     */"""
    }

    val sizeDecl = if (hasSize) NativeSizeDecl else ""

    s"""$mutableDoc
    public static final class Mutable {

$sizeDecl$fieldDecls

        public Mutable() {}

        public Mutable($ctorParams) {
$ctorAssigns
        }

        /** Create an immutable snapshot of this instance. */
        public $immutableName toImmutable() {
            return new $immutableName($toImmutableArgs);
        }

${renderEquals("Mutable", userFields, indent = 8)}

${renderHashCode(userFields, indent = 8)}

${renderToString(s"$immutableName.Mutable", fields, indent = 8)}
    }"""
  }

  private def isPrimitive(typ: CType): Boolean = typ match {
    case CType.Bool | CType.Int | CType.UInt | CType.Long | CType.SizeT | CType.Float | CType.Double => true
    case _                                                                                           => false
  }

  private def renderFieldDecls(
      fields: List[Field],
      fieldDocs: Map[String, String],
      docIndent: String,
      fieldPrefix: String
  )(using Naming.Context, DocComments.Context): String =
    fields.map { f =>
      val doc = indentDoc(renderFieldDoc(f.name, fieldDocs), docIndent)
      s"$doc$fieldPrefix${Naming.javaType(f.typ)} ${Naming.toCamelCase(f.name)};"
    }.mkString("\n")

  private def renderCtorParams(fields: List[Field])(using Naming.Context): String =
    fields.map(f => s"${Naming.javaType(f.typ)} ${Naming.toCamelCase(f.name)}").mkString(", ")

  private def renderCtorAssigns(fields: List[Field], indent: String)(using Naming.Context): String =
    fields.map { f =>
      val n = Naming.toCamelCase(f.name)
      s"${indent}this.$n = $n;"
    }.mkString("\n")

  private def renderThisArgs(fields: List[Field])(using Naming.Context): String =
    fields.map(f => s"this.${Naming.toCamelCase(f.name)}").mkString(", ")

  private def indentDoc(doc: String, indent: String): String =
    if (doc.isEmpty) ""
    else doc.linesIterator.map(l => if (l.nonEmpty) s"$indent$l" else l).mkString("\n")

  private def renderEquals(className: String, fields: List[Field], indent: Int = 4)(using Naming.Context): String = {
    val pad         = " " * indent
    val comparisons = fields.map { f =>
      val n = Naming.toCamelCase(f.name)
      if (isPrimitive(f.typ)) s"this.$n == other.$n"
      else s"java.util.Objects.equals(this.$n, other.$n)"
    }.mkString(s"\n$pad                && ")

    if (fields.isEmpty) {
      s"""$pad@Override
${pad}public boolean equals(Object obj) {
$pad    return this == obj || obj instanceof $className;
$pad}"""
    } else {
      s"""$pad@Override
${pad}public boolean equals(Object obj) {
$pad    if (this == obj) return true;
$pad    if (!(obj instanceof $className)) return false;
$pad    $className other = ($className) obj;
$pad    return $comparisons;
$pad}"""
    }
  }

  private def renderHashCode(fields: List[Field], indent: Int = 4)(using Naming.Context): String = {
    val pad  = " " * indent
    val args = fields.map(f => Naming.toCamelCase(f.name)).mkString(", ")
    s"""$pad@Override
${pad}public int hashCode() {
$pad    return java.util.Objects.hash($args);
$pad}"""
  }

  private def renderToString(className: String, fields: List[Field], indent: Int = 4)(using Naming.Context): String = {
    val pad   = " " * indent
    val parts = fields.map { f =>
      val n = Naming.toCamelCase(f.name)
      if (isSizeField(f)) s""""$n=" + ($n == -1 ? "pending" : Long.toString($n))"""
      else s""""$n=" + $n"""
    }.mkString(""" + ", " + """)
    s"""$pad@Override
${pad}public String toString() {
$pad    return "$className{" + $parts + "}";
$pad}"""
  }
}
