package net.kurobako.cef4j.codegen

import java.nio.charset.StandardCharsets
import java.nio.file.AtomicMoveNotSupportedException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption

object AtomicFiles {
  def writeString(target: Path, content: String): Unit =
    writeBytes(target, content.getBytes(StandardCharsets.UTF_8))

  def writeBytes(target: Path, content: Array[Byte]): Unit = {
    val parent = target.toAbsolutePath.normalize().getParent
    FileSystem.createDirectories(parent)
    val temporary = Files.createTempFile(parent, target.getFileName.toString, ".part")
    try {
      val _ = Files.write(temporary, content)
      try {
        val _ = Files.move(temporary, target, StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING)
      } catch {
        case _: AtomicMoveNotSupportedException =>
          val _ = Files.move(temporary, target, StandardCopyOption.REPLACE_EXISTING)
      }
    } finally FileSystem.deleteIfExists(temporary)
  }
}
