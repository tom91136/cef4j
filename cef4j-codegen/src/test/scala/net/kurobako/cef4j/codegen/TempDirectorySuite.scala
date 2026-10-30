package net.kurobako.cef4j.codegen

import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.time.Duration
import java.util.concurrent.locks.ReentrantLock
import scala.annotation.tailrec
import scala.collection.mutable

object TempDirectorySuite {
  private val DeleteRetryDelays =
    List(50L, 100L, 200L, 400L, 800L).map(Duration.ofMillis(_)) ++ List.fill(10)(Duration.ofSeconds(1))

  private def delete(path: Path): Unit = deleteWithRetry(() => FileSystem.deleteTree(path), sleep(_))

  def deleteWithRetry(operation: () => Unit, pause: Duration => Unit): Unit = {
    @tailrec
    def attempt(delays: List[Duration]): Unit =
      try operation()
      catch {
        case failure: IOException =>
          delays match {
            case Nil                => throw failure
            case delay :: remaining =>
              pause(delay)
              attempt(remaining)
          }
      }

    attempt(DeleteRetryDelays)
  }

  private def sleep(delay: Duration): Unit =
    try Thread.sleep(delay.toMillis)
    catch {
      case error: InterruptedException =>
        Thread.currentThread().interrupt()
        throw error
    }
}

abstract class TempDirectorySuite extends munit.FunSuite {
  private val directories = mutable.ListBuffer.empty[Path]
  private val lock        = ReentrantLock()

  protected final def tempDirectory(prefix: String): Path = {
    lock.lock()
    try {
      val directory = Files.createTempDirectory(prefix)
      directories += directory
      directory
    } finally lock.unlock()
  }

  override def afterEach(context: munit.AfterEach): Unit =
    try {
      lock.lock()
      try {
        directories.reverseIterator.foreach(TempDirectorySuite.delete(_))
        directories.clear()
      } finally lock.unlock()
    } finally super.afterEach(context)
}
