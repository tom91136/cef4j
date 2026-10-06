package net.kurobako.cef4j.codegen

import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.util.concurrent.TimeUnit

class CefCachePublisherSpec extends TempDirectorySuite {
  private val Marker = ".cef4j-complete-test"

  private def staged(root: Path, id: String, payload: String): Path = {
    val dir = root.resolve(s".cef-extract-$id").resolve("cef_binary_test_linux64_minimal")
    FileSystem.createDirectories(dir.resolve("include"))
    FileSystem.createDirectories(dir.resolve("Release"))
    val _ = Files.writeString(dir.resolve("include/cef_version.h"), "test")
    val _ = Files.writeString(dir.resolve(Marker), "")
    val _ = Files.writeString(dir.resolve("payload"), payload)
    dir
  }

  test("publishes a complete staging directory") {
    val root   = tempDirectory("cef-cache")
    val target = root.resolve("cef_binary_test_linux64_minimal")
    val first  = staged(root, "first", "first")
    CefCachePublisher.publish(first, target, Marker)
    assertEquals(Files.readString(target.resolve("payload")), "first")
    assert(!Files.exists(first))
  }

  test("keeps an existing complete target and removes redundant staging") {
    val root   = tempDirectory("cef-cache")
    val target = root.resolve("cef_binary_test_linux64_minimal")
    CefCachePublisher.publish(staged(root, "first", "first"), target, Marker)
    val redundant = staged(root, "redundant", "redundant")
    CefCachePublisher.publish(redundant, target, Marker)
    assert(!Files.exists(redundant))
    assertEquals(Files.readString(target.resolve("payload")), "first")
  }

  test("replaces an incomplete target") {
    val root   = tempDirectory("cef-cache")
    val target = root.resolve("cef_binary_test_linux64_minimal")
    CefCachePublisher.publish(staged(root, "first", "first"), target, Marker)
    Files.delete(target.resolve(Marker))
    CefCachePublisher.publish(staged(root, "replacement", "replacement"), target, Marker)
    assertEquals(Files.readString(target.resolve("payload")), "replacement")
  }

  test("rejects incomplete or out-of-cache staging") {
    val root       = tempDirectory("cef-cache")
    val target     = root.resolve("cef_binary_test_linux64_minimal")
    val incomplete = root.resolve(".cef-extract-incomplete/cef_binary_test_linux64_minimal")
    FileSystem.createDirectories(incomplete)
    val _ = intercept[IOException](CefCachePublisher.publish(incomplete, target, Marker))

    val outside = root.resolve("outside")
    FileSystem.createDirectories(outside.resolve("include"))
    FileSystem.createDirectories(outside.resolve("Release"))
    val _ = Files.writeString(outside.resolve("include/cef_version.h"), "test")
    val _ = Files.writeString(outside.resolve(Marker), "")
    val _ = intercept[IOException](CefCachePublisher.publish(outside, target, Marker))
  }

  test("concurrent publishers leave one complete distribution") {
    val root       = tempDirectory("cef-cache")
    val target     = root.resolve("cef_binary_concurrent_linux64_minimal")
    val publishers = List("concurrent-first", "concurrent-second").map { id =>
      new ProcessBuilder(
        Path.of(System.getProperty("java.home"), "bin", "java").toString,
        "-cp",
        System.getProperty("java.class.path"),
        "net.kurobako.cef4j.codegen.CefCachePublisher",
        staged(root, id, id).toString,
        target.toString,
        Marker
      ).inheritIO().start()
    }
    publishers.foreach { process =>
      if (!process.waitFor(20, TimeUnit.SECONDS)) {
        val _ = process.destroyForcibly()
        fail("publisher process did not exit")
      }
      assertEquals(process.exitValue(), 0)
    }
    assert(Set("concurrent-first", "concurrent-second").contains(Files.readString(target.resolve("payload"))))
    assert(Files.isRegularFile(target.resolve(Marker)))
  }
}
