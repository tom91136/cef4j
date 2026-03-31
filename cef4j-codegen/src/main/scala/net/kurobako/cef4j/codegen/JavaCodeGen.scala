package net.kurobako.cef4j.codegen

import java.nio.file.Files
import java.nio.file.Path

object JavaCodeGen {

  private val GeneratedBanner =
    "// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native"

  def renderClassDoc(classDoc: String): String =
    if (classDoc.nonEmpty) {
      val lines = classDoc.linesIterator.map(line => s" * $line").mkString("\n")
      s"""/**
$lines
 */
"""
    } else ""

  def renderJavaFile(
      declaration: String,
      body: String,
      imports: List[String] = Nil,
      classDoc: String = ""
  ): String = {
    val importBlock = if (imports.nonEmpty) s"\n${imports.mkString("\n")}\n" else ""
    s"""$GeneratedBanner
package net.kurobako.cef4j.gen;
$importBlock
${renderClassDoc(classDoc)}$declaration {

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
    case CType.JString => true
    case _             => false
  }

  def isReferenceType(ct: CType): Boolean = ct match {
    case CType.JString | CType.Enum(_) | CType.DataStruct(_) |
        CType.ByValueIn(_) | CType.ByValueOut(_) | CType.ByValueArray(_) |
        CType.StringList | CType.StringMap | CType.StringMultimap => true
    case _ => false
  }
}
