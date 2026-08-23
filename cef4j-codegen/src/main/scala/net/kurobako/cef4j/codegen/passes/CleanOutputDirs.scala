package net.kurobako.cef4j.codegen.passes

import java.nio.file.Files
import java.nio.file.Path

import net.kurobako.cef4j.codegen.FileSystem

object CleanOutputDirs {
  private val GeneratedPrefix = "// GENERATED - do not edit. Regenerate via:"

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
        .filter(isGenerated)
        .foreach(FileSystem.deleteIfExists)
    }

    if (Files.exists(targetPlatformDir)) {
      cleanGeneratedTree(targetPlatformDir)
    }
    FileSystem.createDirectories(targetPlatformDir)
  }

  private def cleanDir(dir: Path): Unit = {
    if (Files.exists(dir)) {
      cleanGeneratedTree(dir)
    }
    FileSystem.createDirectories(dir)
  }

  private def cleanGeneratedTree(dir: Path): Unit = {
    val descendants = FileSystem.descendants(dir).toList
    descendants.filter(Files.isRegularFile(_)).filter(isGenerated).foreach(FileSystem.deleteIfExists)
    descendants
      .filter(Files.isDirectory(_))
      .sortBy(_.toString)(using Ordering[String].reverse)
      .filter(path => FileSystem.children(path).isEmpty)
      .foreach(FileSystem.deleteIfExists)
  }

  private def isGenerated(path: Path): Boolean = {
    val reader = Files.newBufferedReader(path)
    try Option(reader.readLine()).exists(_.startsWith(GeneratedPrefix))
    finally reader.close()
  }
}
