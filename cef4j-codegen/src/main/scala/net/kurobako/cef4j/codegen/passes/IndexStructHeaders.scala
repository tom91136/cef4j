package net.kurobako.cef4j.codegen.passes

import java.nio.file.Files
import java.nio.file.Path
import scala.jdk.StreamConverters._

object IndexStructHeaders {
  private val StructDefPattern = """typedef\s+struct\s+_?(cef_\w+_t)\s*\{""".r

  def apply(capiDir: Path): Map[String, String] = {
    val headers = Files.list(capiDir).toScala(List).filter(_.toString.endsWith(".h"))
    headers.flatMap { header =>
      val headerName = header.getFileName.toString
      val content    = Files.readString(header)
      StructDefPattern.findAllMatchIn(content).map(m => m.group(1) -> headerName)
    }.toMap
  }
}
