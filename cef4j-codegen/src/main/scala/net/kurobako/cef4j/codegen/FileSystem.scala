package net.kurobako.cef4j.codegen

import java.nio.file.Files
import java.nio.file.Path
import scala.jdk.CollectionConverters.*
import scala.util.Using

object FileSystem {
  def createDirectories(path: Path): Unit = {
    val _ = Files.createDirectories(path)
  }

  def delete(path: Path): Unit = Files.delete(path)

  def deleteIfExists(path: Path): Unit = {
    val _ = Files.deleteIfExists(path)
  }

  def children(path: Path): List[Path] =
    Using.resource(Files.list(path))(_.iterator().asScala.toList)

  def descendants(path: Path): List[Path] =
    Using.resource(Files.walk(path))(_.iterator().asScala.toList)

  def deleteTree(path: Path): Unit =
    if (Files.exists(path)) {
      descendants(path).sortBy(_.getNameCount).reverse.foreach(delete)
    }
}
