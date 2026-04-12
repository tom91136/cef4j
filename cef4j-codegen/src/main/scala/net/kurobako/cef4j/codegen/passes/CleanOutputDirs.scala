package net.kurobako.cef4j.codegen.passes

import java.nio.file.Files
import java.nio.file.Path
import scala.jdk.StreamConverters._

object CleanOutputDirs {
  def apply(dirs: Path*): Unit =
    dirs.foreach(cleanDir)

  /** Clean generated C++ output while preserving non-target platform subdirectories. */
  def cleanCppOutput(outCpp: Path, targetPlatformDir: Path, cleanCommon: Boolean = true): Unit = {
    Files.createDirectories(outCpp)

    if (cleanCommon) {
      Files.list(outCpp)
        .toScala(List)
        .filter(Files.isRegularFile(_))
        .filter { p =>
          val n = p.getFileName.toString
          n.endsWith(".cpp") || n.endsWith(".h")
        }
        .foreach(Files.deleteIfExists)
    }

    if (Files.exists(targetPlatformDir)) {
      Files.walk(targetPlatformDir)
        .toScala(List)
        .sortBy(_.toString)(using Ordering[String].reverse)
        .foreach(Files.deleteIfExists)
    }
    Files.createDirectories(targetPlatformDir)
  }

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
