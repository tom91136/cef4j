package net.kurobako.cef4j.codegen.ipc

import java.nio.file.Files

class CppEmitterSpec extends munit.FunSuite {

  private val sampleSpec = MessageSpec(
    className = "LoadUrlRequest",
    packageName = "net.kurobako.cef4j.ipc.protocol.gen",
    messageId = 1,
    fields = List(FieldSpec("url", FieldType.Utf8String))
  )

  private val mixedSpec = MessageSpec(
    className = "NavigateRequest",
    packageName = "net.kurobako.cef4j.ipc.protocol.gen",
    messageId = 100,
    fields = List(
      FieldSpec("url", FieldType.Utf8String),
      FieldSpec("transitionType", FieldType.I32),
      FieldSpec("preserveSession", FieldType.Bool),
      FieldSpec("nonce", FieldType.I64),
      FieldSpec("digest", FieldType.Bytes)
    )
  )

  test("emitted header has the required structural elements") {
    val src = CppEmitter.emit(sampleSpec)
    assert(src.contains("struct LoadUrlRequest"), s"missing struct LoadUrlRequest in:\n$src")
    assert(src.contains("kMessageId = 1"))
    assert(src.contains("std::string url"))
    assert(src.contains("std::size_t encodedSize() const noexcept"))
    assert(src.contains("void encodeInto(std::uint8_t* dst) const noexcept"))
    assert(src.contains("static LoadUrlRequest decode(const std::uint8_t* src, std::size_t len) noexcept"))
  }

  test("encodedSize for fixed+variable mix sums fixed bytes plus variable .size() expressions") {
    val src = CppEmitter.emit(mixedSpec)
    // url string: 4 (len) + bytes; transitionType: 4; bool: 1; nonce: 8; digest: 4 (len) + bytes.
    // Fixed sum = 4 + 4 + 1 + 8 + 4 = 21.
    assert(
      src.contains("return 21 + url.size() + digest.size();"),
      s"unexpected encodedSize body in:\n$src"
    )
  }

  test("emitted header includes a unique guard macro and namespace") {
    val src = CppEmitter.emit(sampleSpec)
    assert(src.contains("#ifndef NET_KUROBAKO_CEF4J_IPC_PROTOCOL_GEN_LOAD_URL_REQUEST_H_"))
    assert(src.contains("namespace net_kurobako_cef4j_ipc_protocol_gen"))
  }

  test("Specs.all produces both Java and C++ output without overlap") {
    // Pure Scala check that emitting both sides for the same spec list doesn't error.
    val specs = Specs.all("net.kurobako.cef4j.ipc.protocol.gen")
    specs.foreach { s =>
      val cpp  = CppEmitter.emit(s)
      val java = JavaEmitter.emit(s)
      assert(cpp.nonEmpty)
      assert(java.nonEmpty)
      assert(cpp.contains(s"kMessageId = ${s.messageId}"), s"C++ output missing messageId for ${s.className}")
      assert(java.contains(s"MESSAGE_ID = ${s.messageId}"), s"Java output missing MESSAGE_ID for ${s.className}")
    }
  }

  /** If a system C++17 compiler is available, attempt to compile the emitted header to catch outright syntax errors.
    * Skipped silently when no compiler is on PATH (e.g. CI containers without build-essential).
    */
  test("emitted header compiles via system C++17 compiler when one is available") {
    val cxx = locateCxx()
    assume(cxx.isDefined, "no g++/clang++ on PATH")

    val tmp    = Files.createTempDirectory("cef4j-cppgen-")
    val header = tmp.resolve("LoadUrlRequest.h")
    val source = tmp.resolve("smoketest.cpp")
    val outBin = tmp.resolve("smoketest")

    Files.writeString(header, CppEmitter.emit(sampleSpec))
    Files.writeString(
      source,
      """|#include "LoadUrlRequest.h"
         |#include <cstdio>
         |#include <vector>
         |int main() {
         |    using namespace net_kurobako_cef4j_ipc_protocol_gen;
         |    LoadUrlRequest req;
         |    req.url = "https://example.com";
         |    std::vector<std::uint8_t> buf(req.encodedSize());
         |    req.encodeInto(buf.data());
         |    LoadUrlRequest decoded = LoadUrlRequest::decode(buf.data(), buf.size());
         |    return decoded.url == req.url ? 0 : 1;
         |}
         |""".stripMargin
    )

    val pb = new ProcessBuilder(cxx.get, "-std=c++17", "-Wall", "-Werror", "-o", outBin.toString, source.toString)
    pb.directory(tmp.toFile)
    pb.redirectErrorStream(true)
    val proc      = pb.start()
    val output    = new String(proc.getInputStream.readAllBytes())
    val compileOk = proc.waitFor()
    assertEquals(compileOk, 0, s"C++ compile failed:\n$output")

    val runProc = new ProcessBuilder(outBin.toString).redirectErrorStream(true).start()
    val runOk   = runProc.waitFor()
    assertEquals(runOk, 0, "C++ smoke binary exited non-zero (encode/decode mismatch)")
  }

  private def locateCxx(): Option[String] = {
    val candidates = List("g++", "clang++")
    candidates.find { name =>
      val p = new ProcessBuilder(name, "--version").redirectErrorStream(true).start()
      p.getInputStream.readAllBytes()
      p.waitFor() == 0
    }
  }
}
