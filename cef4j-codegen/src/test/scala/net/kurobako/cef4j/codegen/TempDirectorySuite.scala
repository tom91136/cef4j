package net.kurobako.cef4j.codegen

import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.time.Duration
import scala.annotation.tailrec
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
        directories.reverseIterator.foreach(TempDirectorySuite.delete)
        directories.clear()
      }
    finally super.afterEach(context)
}

object TempDirectorySuite {
  private val DeleteRetryDelays =
    List(50L, 100L, 200L, 400L, 800L).map(Duration.ofMillis) ++ List.fill(10)(Duration.ofSeconds(1))

  private def delete(path: Path): Unit = deleteWithRetry(() => FileSystem.deleteTree(path), sleep)

  private[codegen] def deleteWithRetry(operation: () => Unit, pause: Duration => Unit): Unit = {
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
