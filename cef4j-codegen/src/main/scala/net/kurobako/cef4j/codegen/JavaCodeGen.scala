package net.kurobako.cef4j.codegen

import java.nio.file.Path
import scala.annotation.tailrec
import scala.collection.mutable

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
      val DocComments.SourceTags(docText, sourceRefTags) = DocComments.extractSourceTags(converted)
      val contentLines = docText.linesIterator.filter(_.nonEmpty).map(line => s" * $line").toList
      val suffixLines  =
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
    val allImports  = (Banners.javaAnnotationImport :: Banners.javaBoundaryImport :: imports).distinct
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
    FileSystem.createDirectories(file.getParent)
    AtomicFiles.writeString(file, content.replace("\r\n", "\n").replace("\r", "\n"))
  }

  /** The code of a file without the imports the code of it does not use.
    *
    * The generators write the same list of imports into every file they write, and the code of a file uses only some of
    * them, so the imports that are not used are left out here.
    *
    * @param content
    *   the code of the file, which is read as the compiler reads it
    */
  def withoutUnusedImports(content: String): String = {
    val lines       = content.split("\n", -1).toList
    val importLines = lines.filter(line => line.startsWith("import ") && line.endsWith(";"))
    if (importLines.isEmpty) content
    else {
      val imports = importLines.toSet
      val code    = lines.filterNot(imports).mkString("\n")
      val used    = usedNames(code)
      val kept    = importLines.filter(line => used.contains(simpleName(line))).toSet
      lines.filterNot(line => imports(line) && !kept(line)).mkString("\n")
    }
  }

  /** The name of the type an import brings in, which is the name the code of the file uses it by. */
  private def simpleName(importLine: String): String = {
    val name = importLine.stripPrefix("import ").stripSuffix(";").trim
    name.substring(name.lastIndexOf('.') + 1)
  }

  /** The names the code of a file uses by themselves, which the imports of the file are chosen by.
    *
    * The code is read as the compiler reads it: the comments and the literals hold no name of the code, and the name of
    * a type that is written as the member of another one is the name of that member and needs no import.
    */
  private def usedNames(code: String): Set[String] = {
    val text = withoutCommentsAndLiterals(code)
    NamePattern.findAllMatchIn(text).map(_.matched).toSet
  }

  /** A name of the code that the code before it does not qualify. */
  private val NamePattern = "(?<!\\.)[\\p{L}_$][\\p{L}\\p{Nd}_$]*".r

  /** The code of a file without its comments and its literals, which hold no name of the code. */
  private def withoutCommentsAndLiterals(code: String): String = {
    val builder = mutable.StringBuilder(code.length)
    val size    = code.length

    @tailrec
    def scan(index: Int): Unit =
      if (index < size) {
        val c = code.charAt(index)
        if (c == '/' && index + 1 < size && code.charAt(index + 1) == '/')
          scan(skipLineComment(code, index, size))
        else if (c == '/' && index + 1 < size && code.charAt(index + 1) == '*')
          scan(skipBlockComment(code, index + 2, size))
        else if (c == '"' || c == '\'') {
          builder.append(c)
          scan(skipLiteral(code, index + 1, size, c))
        } else {
          builder.append(c)
          scan(index + 1)
        }
      }
    scan(0)
    builder.toString
  }

  @tailrec
  private def skipLineComment(code: String, index: Int, size: Int): Int =
    if (index < size && code.charAt(index) != '\n') skipLineComment(code, index + 1, size) else index

  @tailrec
  private def skipBlockComment(code: String, index: Int, size: Int): Int =
    if (index + 1 < size && !(code.charAt(index) == '*' && code.charAt(index + 1) == '/'))
      skipBlockComment(code, index + 1, size)
    else math.min(index + 2, size)

  @tailrec
  private def skipLiteral(code: String, index: Int, size: Int, quote: Char): Int =
    if (index >= size || code.charAt(index) == quote) math.min(index + 1, size)
    else skipLiteral(code, index + (if (code.charAt(index) == '\\') 2 else 1), size, quote)

  def isOptionalReturn(fn: FnPtr): Boolean = fn.ret match {
    case CType.JString | CType.ObjectPtr(_) => true
    case CType.Bool                         => OutCollection.forMethod(fn).isDefined
    case _                                  => false
  }

  def isNullableHandlerReturn(fn: FnPtr, handlerNames: Set[String]): Boolean =
    if (Naming.isHandlerPtrReturn(fn.ret, handlerNames)) false
    else
      fn.ret match {
        case CType.CountFuncArray(elementType = elem) if !Naming.isPrimitiveElement(elem) => false
        case _                                                                            => isReferenceType(fn.ret)
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

  // CEF often permits null reference types without optional metadata.
  def isStrictNullCheck(ct: CType): Boolean = ct match {
    case CType.Enum(_) | CType.ByValueIn(_) | CType.ByValueOut(_) | CType.ByValueArray(_) |
        CType.Buffer(_) | CType.PixelBuffer | CType.ConstCStringArray | CType.CStringArray |
        CType.OutOpaquePtr |
        CType.StringList | CType.StringMap | CType.StringMultimap => true
    case _ => false
  }
}
