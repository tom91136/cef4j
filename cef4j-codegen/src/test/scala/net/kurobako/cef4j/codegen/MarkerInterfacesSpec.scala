package net.kurobako.cef4j.codegen

import java.nio.file.Files

import net.kurobako.cef4j.codegen.passes.EmitMarkerInterfaces

class MarkerInterfacesSpec extends TempDirectorySuite {
  test("marker interfaces and the enum support types are emitted together") {
    val dir = tempDirectory("cef4j-marker")
    EmitMarkerInterfaces(dir, "p.gen")
    for (name <- List("CefLibraryObject", "CefClientHandler", "CefEnum", "EnumConstant"))
      assert(Files.exists(dir.resolve(s"$name.java")), s"Missing $name.java")
  }
}
