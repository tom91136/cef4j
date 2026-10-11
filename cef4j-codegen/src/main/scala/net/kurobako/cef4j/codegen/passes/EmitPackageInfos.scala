package net.kurobako.cef4j.codegen.passes

import java.nio.file.Files
import java.nio.file.Path
import scala.jdk.CollectionConverters.*

import net.kurobako.cef4j.codegen.AtomicFiles
import net.kurobako.cef4j.codegen.FileSystem

object EmitPackageInfos {
  def apply(packageDir: Path, javaPackage: String, banner: String): Unit =
    FileSystem.descendants(packageDir)
      .filter(Files.isDirectory(_))
      .filter(dir => FileSystem.children(dir).exists(isJavaType))
      .foreach { dir =>
        val segments = packageDir.relativize(dir).iterator().asScala.map(_.toString).filter(_.nonEmpty)
        val pkg      = (javaPackage +: segments.toSeq).mkString(".")
        AtomicFiles.writeString(
          dir.resolve("package-info.java"),
          s"""$banner
             |@NullMarked
             |package $pkg;
             |
             |import org.jspecify.annotations.NullMarked;
             |""".stripMargin
        )
      }

  private def isJavaType(path: Path): Boolean = {
    val name = path.getFileName.toString
    name.endsWith(".java") && name != "package-info.java"
  }
}
