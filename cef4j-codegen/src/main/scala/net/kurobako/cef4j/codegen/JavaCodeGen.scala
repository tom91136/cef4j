package net.kurobako.cef4j.codegen

import java.nio.file.Files
import java.nio.file.Path

object JavaCodeGen {

  private val GeneratedBanner =
    "// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native"

  def renderClassDoc(
      classDoc: String,
      capiSource: String = "",
      cPrototype: String = "",
      cppSource: String = "",
      classDocSuffix: String = ""
  ): String =
    if (classDoc.nonEmpty || classDocSuffix.nonEmpty) {
      val converted =
        if (classDoc.nonEmpty) DocComments.convertCefDoc(classDoc, capiSource, cPrototype, cppSource) else ""
      val (docText, sourceRefTags) = DocComments.extractSourceTags(converted)
      val contentLines             = docText.linesIterator.filter(_.nonEmpty).map(line => s" * $line").toList
      // Suffix is inserted raw (not through convertCefDoc) — between content and source refs
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
      classDocSuffix: String = ""
  ): String = {
    val importBlock = if (imports.nonEmpty) s"\n${imports.mkString("\n")}\n" else ""
    s"""$GeneratedBanner
package net.kurobako.cef4j.gen;
$importBlock
${renderClassDoc(classDoc, capiSource, cPrototype, cppSource, classDocSuffix)}$declaration {

$body
}
"""
  }

  def writeJavaFile(outDir: Path, className: String, content: String): Unit = {
    val file = outDir.resolve(s"$className.java")
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
        CType.OpaquePtr | CType.PixelBuffer | CType.Buffer(_) |
        CType.StringList | CType.StringMap | CType.StringMultimap |
        CType.CountFuncArray(_, _, _, _) => true
    case _ => false
  }
}
