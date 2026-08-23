package net.kurobako.cef4j.codegen

import java.nio.file.Files
import java.nio.file.Path
import scala.collection.mutable.ListBuffer

abstract class TempDirectorySuite extends munit.FunSuite {
  private val directories = ListBuffer.empty[Path]

  protected final def tempDirectory(prefix: String): Path = directories.synchronized {
    val directory = Files.createTempDirectory(prefix)
    directories += directory
    directory
  }

  override def afterEach(context: munit.AfterEach): Unit =
    try directories.synchronized {
        directories.reverseIterator.foreach(FileSystem.deleteTree)
        directories.clear()
      }
    finally super.afterEach(context)
}
