package net.kurobako.cef4j.codegen

import java.nio.file.Files
import java.nio.file.Path

import net.kurobako.cef4j.codegen.passes.CleanOutputDirs

class GeneratorSafetySpec extends munit.FunSuite {
  test("generation requires explicit input and outputs") {
    interceptMessage[IllegalArgumentException]("requirement failed: Missing required argument: --cef-include") {
      Main.parseArgs(Nil)
    }
  }

  test("generation refuses the current directory as output") {
    val current = Path.of("").toAbsolutePath.normalize
    val error   = intercept[IllegalArgumentException] {
      Main.parseArgs(List(
        "--cef-include=/tmp/cef/include",
        s"--out-cpp=$current",
        "--out-java=/tmp/cef4j-java"
      ))
    }
    assert(error.getMessage.contains("repository/current-directory output"))
  }

  test("cleanup preserves handwritten files") {
    val output    = Files.createTempDirectory("cef4j-generator-safety")
    val generated = output.resolve("generated.cpp")
    val manual    = output.resolve("manual.cpp")
    Files.writeString(generated, "// GENERATED - do not edit. Regenerate via: test\n")
    Files.writeString(manual, "int manual();\n")

    CleanOutputDirs.cleanCppOutput(output, output.resolve("linux"))

    assert(!Files.exists(generated))
    assert(Files.exists(manual))
  }
}
