package net.kurobako.cef4j.codegen

import java.nio.file.Files
import java.nio.file.Path

object JavaCodeGen {

  def renderClassDoc(
      classDoc: String,
      capiSource: String = "",
      cPrototype: String = "",
      cppSource: String = "",
      classDocSuffix: String = ""
  )(using Naming.Context, DocComments.Context): String =
    if (classDoc.nonEmpty || classDocSuffix.nonEmpty) {
      val converted =
        if (classDoc.nonEmpty) DocComments.convertCefDoc(classDoc, capiSource, cPrototype, cppSource) else ""
      val (docText, sourceRefTags) = DocComments.extractSourceTags(converted)
      val contentLines             = docText.linesIterator.filter(_.nonEmpty).map(line => s" * $line").toList
      // Insert the suffix as-is between converted content and source references.
      val suffixLines =
        if (classDocSuffix.nonEmpty) classDocSuffix.linesIterator.filter(_.nonEmpty).map(l => s" * $l").toList else Nil
      val seeTags    = sourceRefTags.map(tag => s" * @see $tag")
      val allContent = contentLines ++ suffixLines
      val separator  = if (allContent.nonEmpty && seeTags.nonEmpty) List(" *") else Nil
      val allLines   = allContent ++ separator ++ seeTags
      s"""/**
${allLines.mkString("\n")}
 */
"""
    } else ""

  def renderJavaFile(
      declaration: String,
      body: String,
      imports: List[String] = Nil,
      classDoc: String = "",
      capiSource: String = "",
      cPrototype: String = "",
      cppSource: String = "",
      classDocSuffix: String = "",
      subPackage: String = ""
  )(using Naming.Context, DocComments.Context, Banners): String = {
    val allImports  = (Banners.javaAnnotationImport :: imports).distinct
    val importBlock = s"\n${allImports.mkString("\n")}\n"
    val pkg         = if (subPackage.nonEmpty) s"${Naming.javaPackage}.$subPackage" else Naming.javaPackage
    s"""${Banners.java}
package $pkg;
$importBlock
${renderClassDoc(classDoc, capiSource, cPrototype, cppSource, classDocSuffix)}${Banners.javaAnnotation}
$declaration {

$body
}
"""
  }

  def writeJavaFile(outDir: Path, className: String, content: String, subPackage: String = ""): Unit = {
    val dir  = if (subPackage.nonEmpty) outDir.resolve(subPackage.replace('.', '/')) else outDir
    val file = dir.resolve(s"$className.java")
    Files.createDirectories(file.getParent)
    Files.writeString(file, content)
  }

  def isOptionalReturn(fn: FnPtr): Boolean = fn.ret match {
    case CType.JString      => true
    case CType.ObjectPtr(_) => true
    case _                  => false
  }

  def isReferenceType(ct: CType): Boolean = ct match {
    case CType.JString | CType.Enum(_) | CType.DataStruct(_) |
        CType.ByValueIn(_) | CType.ByValueOut(_) | CType.ByValueArray(_) |
        CType.ObjectPtr(_) | CType.OutObjectPtr(_) | CType.ObjectPtrArray(_) |
        CType.OpaquePtr | CType.OutOpaquePtr | CType.ConstDataStructPtr(_) | CType.ConstCStringArray |
        CType.CStringArray |
        CType.PixelBuffer | CType.Buffer(_) |
        CType.StringList | CType.StringMap | CType.StringMultimap |
        CType.CountFuncArray(_, _, _, _) => true
    case _ => false
  }

  // Types where null is almost certainly a programming error on the Java side.
  // JString, ObjectPtr, OpaquePtr, and Handler types are excluded because CEF
  // frequently accepts NULL for these (e.g. NULL browser/frame in service worker
  // contexts, NULL strings for defaults) but upstream metacomments only annotate
  // ~10% of nullable parameters.
  def isStrictNullCheck(ct: CType): Boolean = ct match {
    case CType.Enum(_) | CType.ByValueIn(_) | CType.ByValueOut(_) | CType.ByValueArray(_) |
        CType.Buffer(_) | CType.PixelBuffer | CType.ConstCStringArray | CType.CStringArray |
        CType.OutOpaquePtr |
        CType.StringList | CType.StringMap | CType.StringMultimap => true
    case _ => false
  }
}
