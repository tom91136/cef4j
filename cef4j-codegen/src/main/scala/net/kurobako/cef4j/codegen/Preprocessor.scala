package net.kurobako.cef4j.codegen

import java.io.ByteArrayOutputStream
import java.nio.file.Path

object Preprocessor {

  def preprocess(headerFile: Path, includeDirs: Seq[Path], compilerId: String): String = {
    val cmd = compilerId match {
      case "msvc"          => msvcCommand(headerFile, includeDirs)
      case "clang" | "gcc" => unixCommand(headerFile, includeDirs)
      case other           => sys.error(s"Unknown compiler id: $other")
    }

    val proc = new ProcessBuilder(cmd*)
      .redirectErrorStream(true)
      .start()
    val out  = readFully(proc.getInputStream)
    val exit = proc.waitFor()
    if (exit != 0) {
      sys.error(s"Preprocessor failed (exit $exit): ${new String(out, "UTF-8")}")
    }
    stripLineMarkers(new String(out, "UTF-8"))
  }

  private def readFully(is: java.io.InputStream): Array[Byte] = {
    val buf = new ByteArrayOutputStream()
    val tmp = new Array[Byte](8192)

    @annotation.tailrec
    def loop(): Unit = {
      val n = is.read(tmp)
      if (n != -1) {
        buf.write(tmp, 0, n)
        loop()
      }
    }

    loop()
    buf.toByteArray
  }

  private def unixCommand(file: Path, includes: Seq[Path]): List[String] =
    List("cc", "-E", "-x", "c", "-std=c11") ++
      includes.flatMap(d => List("-I", d.toString)) ++
      List(file.toString)

  private def msvcCommand(file: Path, includes: Seq[Path]): List[String] =
    List("cl.exe", "/nologo", "/E", "/EP") ++
      includes.flatMap(d => List(s"/I${d}")) ++
      List(file.toString)

  private def stripLineMarkers(src: String): String =
    src.linesIterator
      .filterNot(l => l.startsWith("# ") || l.startsWith("#line "))
      .mkString("\n")
}
