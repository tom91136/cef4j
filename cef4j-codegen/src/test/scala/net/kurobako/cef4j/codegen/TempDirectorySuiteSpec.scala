package net.kurobako.cef4j.codegen

import java.io.IOException
import java.time.Duration
import java.util.concurrent.atomic.AtomicInteger
import scala.collection.mutable

class TempDirectorySuiteSpec extends munit.FunSuite {
  test("retries transient cleanup failures") {
    val attempts = AtomicInteger()
    val delays   = mutable.ListBuffer.empty[Duration]

    TempDirectorySuite.deleteWithRetry(
      () => {
        attempts.incrementAndGet()
        if (attempts.get() < 3) throw IOException("locked")
      },
      delay => delays += delay
    )

    assertEquals(attempts.get(), 3)
    assertEquals(delays.toList, List(Duration.ofMillis(50), Duration.ofMillis(100)))
  }
}
