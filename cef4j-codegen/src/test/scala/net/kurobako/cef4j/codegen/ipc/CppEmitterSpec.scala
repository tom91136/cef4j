package net.kurobako.cef4j.codegen.ipc

import java.nio.file.Files
import java.nio.file.Paths
import scala.concurrent.duration.*

import net.kurobako.cef4j.codegen.TempDirectorySuite

class CppEmitterSpec extends TempDirectorySuite {

  // XXX: Reduce this timeout when hosted Windows compiler startup is consistently below two minutes.
  override val munitTimeout = 5.minutes

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
    assert(src.contains("MessageId = 1"))
    assert(src.contains("std::string url"))
    assert(src.contains("size_t encodedSize() const noexcept"))
    assert(src.contains("void encodeInto(uint8_t* dst) const noexcept"))
    assert(src.contains("static LoadUrlRequest decode(const uint8_t* src, size_t len)"))
    assert(!src.contains("static LoadUrlRequest decode(const uint8_t* src, size_t len) noexcept"))
    assert(src.contains("reader.finish();"))
  }

  test("message and nested-data decoders have distinct exhaustion contracts") {
    val data = MessageSpec(
      className = "Label",
      packageName = sampleSpec.packageName,
      messageId = 0,
      fields = List(FieldSpec("text", FieldType.Utf8String))
    )
    val exact  = CppEmitter.emit(data)
    val nested = CppEmitter.emit(data, exactPayload = false)
    assert(exact.contains("reader.finish();"))
    assert(!nested.contains("reader.finish();"))
    assert(exact.contains("""reader.string("text")"""))
  }

  test("encodedSize for fixed+variable mix sums fixed bytes plus variable .size() expressions") {
    val src = CppEmitter.emit(mixedSpec)
    assert(
      src.contains("return 21 + url.size() + digest.size();"),
      s"unexpected encodedSize body in:\n$src"
    )
  }

  test("emitted header uses pragma once and a package namespace") {
    val src = CppEmitter.emit(sampleSpec)
    assert(src.contains("#pragma once"))
    assert(src.contains("namespace net_kurobako_cef4j_ipc_protocol_gen"))
  }

  test("dispatcher reports CEF UI task submission failures") {
    val src = CppDispatcherEmitter.emit(
      CppDispatcherEmitter.DispatchInputs(Nil, Nil, Nil, sampleSpec.packageName)
    )

    assert(src.contains("inline bool postUiTask"))
    assert(src.contains("cef4j::ipc::ErrorCode::TaskRejected"))
    assert(!src.contains("cef_post_task(TID_UI, new LambdaTask"))
    assert(!src.contains("auto* task = new LambdaTask"))
  }

  test("Specs.all produces both Java and C++ output without overlap") {
    val specs = Specs.all("net.kurobako.cef4j.ipc.protocol.gen")
    specs.foreach { s =>
      val cpp  = CppEmitter.emit(s)
      val java = JavaEmitter.emit(s)
      assert(cpp.nonEmpty)
      assert(java.nonEmpty)
      assert(cpp.contains(s"MessageId = ${s.messageId}"), s"C++ output missing messageId for ${s.className}")
      assert(java.contains(s"MESSAGE_ID = ${s.messageId}"), s"Java output missing MESSAGE_ID for ${s.className}")
    }
  }

  test("emitted header compiles via a system C++17 compiler") {
    val cxx = locateCxx().getOrElse(fail("no g++/clang++ on PATH"))

    val tmp    = tempDirectory("cef4j-cppgen-")
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
         |    std::vector<uint8_t> buf(req.encodedSize());
         |    req.encodeInto(buf.data());
         |    LoadUrlRequest decoded = LoadUrlRequest::decode(buf.data(), buf.size());
         |    return decoded.url == req.url ? 0 : 1;
         |}
         |""".stripMargin
    )

    val wireDir = Paths.get(sys.props.getOrElse("cef4j.wire.dir", fail("cef4j.wire.dir is not set")))
    val pb      = new ProcessBuilder(
      cxx,
      "-std=c++17",
      "-Wall",
      "-Werror",
      s"-I$wireDir",
      "-o",
      outBin.toString,
      source.toString,
      wireDir.resolve("Wire.cpp").toString
    )
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
