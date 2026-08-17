package net.kurobako.cef4j.codegen.passes

import java.nio.file.Files
import java.nio.file.Path

import net.kurobako.cef4j.codegen.FileSystem

object CleanOutputDirs {
  def apply(dirs: Path*): Unit =
    dirs.foreach(cleanDir)

  def cleanCppOutput(outCpp: Path, targetPlatformDir: Path, cleanCommon: Boolean = true): Unit = {
    FileSystem.createDirectories(outCpp)

    if (cleanCommon) {
      FileSystem.children(outCpp)
        .filter(Files.isRegularFile(_))
        .filter { p =>
          val n = p.getFileName.toString
          n.endsWith(".cpp") || n.endsWith(".h")
        }
        .foreach(FileSystem.deleteIfExists)
    }

    if (Files.exists(targetPlatformDir)) {
      FileSystem.descendants(targetPlatformDir)
        .sortBy(_.toString)(using Ordering[String].reverse)
        .foreach(FileSystem.deleteIfExists)
    }
    FileSystem.createDirectories(targetPlatformDir)
  }

  private def cleanDir(dir: Path): Unit = {
    if (Files.exists(dir)) {
      FileSystem.descendants(dir)
        .sortBy(_.toString)(using Ordering[String].reverse)
        .foreach(FileSystem.deleteIfExists)
    }
    FileSystem.createDirectories(dir)
  }
}
