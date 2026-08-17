package net.kurobako.cef4j.codegen.passes

import java.nio.file.Files
import java.nio.file.Path

import net.kurobako.cef4j.codegen.FileSystem

object IndexStructHeaders {
  private val StructDefPattern = """typedef\s+struct\s+_?(cef_\w+_t)\s*\{""".r

  def apply(capiDir: Path, extraCapiDirs: List[String] = Nil): Map[String, String] = {
    val headers = (capiDir :: extraCapiDirs.map(capiDir.resolve)).filter(Files.isDirectory(_))
      .flatMap(dir =>
        FileSystem.children(dir).filter(p => Files.isRegularFile(p) && p.toString.endsWith(".h"))
      )
      .sorted
    headers.flatMap { header =>
      val headerName = capiDir.relativize(header).toString.replace('\\', '/')
      val content    = Files.readString(header)
      StructDefPattern.findAllMatchIn(content).map(m => m.group(1) -> headerName)
    }.toMap
  }
}
