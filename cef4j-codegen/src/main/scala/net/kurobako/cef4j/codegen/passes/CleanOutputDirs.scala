package net.kurobako.cef4j.codegen.passes

import java.nio.file.Files
import java.nio.file.Path
import scala.jdk.StreamConverters._

object CleanOutputDirs {
  def apply(dirs: Path*): Unit =
    dirs.foreach(cleanDir)

  private def cleanDir(dir: Path): Unit = {
    if (Files.exists(dir)) {
      Files.walk(dir)
        .toScala(List)
        .sortBy(_.toString)(using Ordering[String].reverse)
        .foreach(Files.deleteIfExists)
    }
    Files.createDirectories(dir)
  }
}
