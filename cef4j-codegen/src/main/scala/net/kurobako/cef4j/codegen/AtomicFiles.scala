package net.kurobako.cef4j.codegen

import java.nio.charset.StandardCharsets
import java.nio.file.AtomicMoveNotSupportedException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption

object AtomicFiles {
  def writeString(target: Path, content: String): Unit = {
    Files.createDirectories(target.getParent)
    val temporary = Files.createTempFile(target.getParent, target.getFileName.toString, ".part")
    try {
      Files.writeString(temporary, content, StandardCharsets.UTF_8)
      try Files.move(temporary, target, StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING)
      catch {
        case _: AtomicMoveNotSupportedException =>
          Files.move(temporary, target, StandardCopyOption.REPLACE_EXISTING)
      }
    } finally Files.deleteIfExists(temporary)
  }
}
