package net.kurobako.cef4j.codegen

import java.io.IOException
import java.nio.channels.FileChannel
import java.nio.file.AtomicMoveNotSupportedException
import java.nio.file.Files
import java.nio.file.LinkOption
import java.nio.file.Path
import java.nio.file.StandardCopyOption
import java.nio.file.StandardOpenOption
import scala.util.Using

/** Atomically publishes an extracted CEF distribution into the shared archive cache. */
object CefCachePublisher {

  def main(args: Array[String]): Unit = args match {
    case Array(staged, target, marker) => publish(Path.of(staged), Path.of(target), marker)
    case _ => throw new IllegalArgumentException("Usage: CefCachePublisher <staged-dir> <target-dir> <marker-name>")
  }

  def publish(staged: Path, target: Path, markerName: String): Unit = {
    val normalizedStaged = staged.toAbsolutePath.normalize()
    val normalizedTarget = target.toAbsolutePath.normalize()
    val stagingRoot      = Option(normalizedStaged.getParent)
    val insideCache      = (Option(normalizedTarget.getParent), stagingRoot) match {
      case (Some(cacheRoot), Some(root)) =>
        Option(root.getParent).contains(cacheRoot) && root.getFileName.toString.startsWith(".cef-extract-")
      case _ => false
    }
    if (!insideCache) throw new IOException(s"CEF staging directory is outside the target cache: $normalizedStaged")
    if (!isComplete(normalizedStaged, markerName))
      throw new IOException(s"Incomplete staged CEF distribution: $normalizedStaged")

    val lockPath = normalizedTarget.resolveSibling(s"${normalizedTarget.getFileName}.cef4j.lock")
    Using.resource(FileChannel.open(lockPath, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) { channel =>
      Using.resource(channel.lock()) { _ =>
        if (isComplete(normalizedTarget, markerName)) FileSystem.deleteTree(normalizedStaged)
        else {
          if (Files.exists(normalizedTarget, LinkOption.NOFOLLOW_LINKS)) FileSystem.deleteTree(normalizedTarget)
          try {
            val _ = Files.move(normalizedStaged, normalizedTarget, StandardCopyOption.ATOMIC_MOVE)
          } catch {
            case e: AtomicMoveNotSupportedException =>
              throw new IOException("CEF cache filesystem does not support atomic publication", e)
          }
        }
      }
    }
  }

  private def isComplete(directory: Path, markerName: String): Boolean =
    Files.isRegularFile(directory.resolve("include/cef_version.h"), LinkOption.NOFOLLOW_LINKS) &&
      Files.isDirectory(directory.resolve("Release"), LinkOption.NOFOLLOW_LINKS) &&
      Files.isRegularFile(directory.resolve(markerName), LinkOption.NOFOLLOW_LINKS)
}
