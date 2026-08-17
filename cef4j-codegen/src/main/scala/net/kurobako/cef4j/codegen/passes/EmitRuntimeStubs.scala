package net.kurobako.cef4j.codegen.passes

import java.nio.file.Files
import java.nio.file.Path

import net.kurobako.cef4j.codegen.AtomicFiles
import net.kurobako.cef4j.codegen.Banners
import net.kurobako.cef4j.codegen.FileSystem

object EmitRuntimeStubs {

  private case class NativeMethod(
      packageName: String,
      className: String,
      isStatic: Boolean,
      returnType: String,
      name: String,
      params: List[(String, String)] // (type, name)
  )

  private val NativeMethodRe =
    """(?:(?:private|public|protected|static|final|synchronized)\s+)*native\s+(\S+(?:\[])?)\s+(\w+)\s*\(([^)]*)\)\s*;""".r

  private val PackageRe = """package\s+([\w.]+)\s*;""".r
  private val ClassRe   = """(?:public\s+)?(?:final\s+)?(?:class|enum)\s+(\w+)""".r
  private val StaticRe  = """\bstatic\b""".r

  def apply(javaSourceRoot: Path, generatedPackageDir: Path, outCpp: Path)(using Banners): Unit = {
    val javaFiles = FileSystem.descendants(javaSourceRoot)
      .filter(p => p.toString.endsWith(".java") && !p.startsWith(generatedPackageDir))

    val methods = javaFiles
      .flatMap(scanFile)
      .sortBy(m =>
        (
          m.packageName,
          m.className,
          m.name,
          m.params.map { case (t, n) => s"$t:$n" }.mkString(","),
          m.returnType,
          if (m.isStatic) "1" else "0"
        )
      )
    if (methods.nonEmpty) {
      val declarations = methods.map { m =>
        val jniRet    = jniType(m.returnType)
        val jniParams = jniParamList(m)
        s"CEF4J_JNI_EXPORT_RT($jniRet, ${m.className}, ${m.name})($jniParams);\n\n"
      }.mkString
      val header =
        s"${Banners.cpp}\n" +
          "// JNI stubs for hand-written native methods.\n" +
          "// Include this header and jni_util.h, then implement each function.\n\n" +
          "#pragma once\n" +
          "#include <jni.h>\n\n" +
          declarations

      // Force link-time validation of every native symbol.
      val symbols      = methods.map(m => s"    reinterpret_cast<FnPtr_>(&${jniSymbol(m)}),\n").mkString
      val verification =
        s"${Banners.cpp}\n" +
          "#include \"jni_util.h\"\n" +
          "#include \"runtime_stubs.gen.h\"\n\n" +
          "using FnPtr_ = void (*)();\n" +
          "#ifdef _MSC_VER\n" +
          "#pragma warning(disable: 4100)\n" +
          "static FnPtr_ runtime_stubs_verify_[] = {\n" +
          "#else\n" +
          "__attribute__((used)) static FnPtr_ runtime_stubs_verify_[] = {\n" +
          "#endif\n" +
          symbols +
          "};\n"

      val outFile = outCpp.resolve("runtime_stubs.gen.h")
      FileSystem.createDirectories(outFile.getParent)

      def normalize(s: String) = s.replace("\r\n", "\n").replace("\r", "\n")
      AtomicFiles.writeString(outCpp.resolve("runtime_stubs_verify.gen.cpp"), normalize(verification))
      AtomicFiles.writeString(outFile, normalize(header))
      println(s"  runtime stubs: ${methods.size} native methods -> ${outFile.getFileName}")
    }
  }

  private def scanFile(path: Path): List[NativeMethod] = {
    val source = Files.readString(path)
    val pkg    = PackageRe.findFirstMatchIn(source).map(_.group(1)).getOrElse("")

    ClassRe.findFirstMatchIn(source).map(_.group(1)) match {
      case None      => Nil
      case Some(cls) =>
        NativeMethodRe.findAllMatchIn(source).map { m =>
          val line       = source.substring(0, m.start).count(_ == '\n')
          val lineStart  = source.lastIndexOf('\n', m.start) + 1
          val linePrefix = source.substring(lineStart, m.start)
          val isStatic   = StaticRe.findFirstIn(linePrefix + m.matched).isDefined

          val rawParams = m.group(3).trim
          val params    = if (rawParams.isEmpty) Nil
          else
            rawParams.split(",").toList.map { p =>
              val parts = p.trim.split("\\s+")
              if (parts.length >= 2) (parts.init.mkString(" "), parts.last)
              else (parts.head, s"arg$line")
            }

          NativeMethod(pkg, cls, isStatic, m.group(1), m.group(2), params)
        }.toList
    }
  }

  private def jniSymbol(m: NativeMethod): String = {
    val pkgPart = m.packageName.replace('.', '_')
    s"Java_${pkgPart}_${m.className}_${m.name}"
  }

  private def jniType(javaType: String): String = javaType match {
    case "void"                => "void"
    case "long"                => "jlong"
    case "int"                 => "jint"
    case "boolean"             => "jboolean"
    case "byte"                => "jbyte"
    case "short"               => "jshort"
    case "char"                => "jchar"
    case "float"               => "jfloat"
    case "double"              => "jdouble"
    case "String"              => "jstring"
    case "String[]"            => "jobjectArray"
    case "Object[]"            => "jobjectArray"
    case s if s.endsWith("[]") => "jobjectArray"
    case _                     => "jobject"
  }

  private def jniParamList(m: NativeMethod): String = {
    val receiver = if (m.isStatic) "jclass clz" else "jobject thiz"
    val base     = s"JNIEnv* env, $receiver"
    if (m.params.isEmpty) base
    else {
      val extras = m.params.map { case (t, n) => s"${jniType(t)} $n" }.mkString(", ")
      s"$base, $extras"
    }
  }
}
