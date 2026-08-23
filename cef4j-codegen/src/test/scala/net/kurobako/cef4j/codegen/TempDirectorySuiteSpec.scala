package net.kurobako.cef4j.codegen

import java.io.IOException
import java.time.Duration
import scala.collection.mutable.ListBuffer

class TempDirectorySuiteSpec extends munit.FunSuite {
  test("retries transient cleanup failures") {
    var attempts = 0
    val delays   = ListBuffer.empty[Duration]

    TempDirectorySuite.deleteWithRetry(
      () => {
        attempts += 1
        if (attempts < 3) throw IOException("locked")
      },
      delay => delays += delay
    )

    assertEquals(attempts, 3)
    assertEquals(delays.toList, List(Duration.ofMillis(50), Duration.ofMillis(100)))
  }
}
