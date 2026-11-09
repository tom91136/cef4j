package net.kurobako.cef4j.codegen

class PlatformSpecificitySpec extends munit.FunSuite {

  private val platformTypes = Set("cef_native_plane_t", "cef_window_info_t")
  private val shared        = Set("cef_window_info_t")

  private def struct(name: String): CefDecl.DataStruct = CefDecl.DataStruct(name, List(Field("x", CType.Int)))

  test("common pass records platform-only structs in the platform packages alone") {
    assert(!PlatformSpecificity.emitsRecord(struct("cef_native_plane_t"), platformTypes, shared, platformOnly = false))
    assert(PlatformSpecificity.emitsRecord(struct("cef_native_plane_t"), platformTypes, shared, platformOnly = true))
  }

  test("common pass keeps the shared interface of a platform struct") {
    assert(PlatformSpecificity.emitsRecord(struct("cef_window_info_t"), platformTypes, shared, platformOnly = false))
    assert(PlatformSpecificity.emitsRecord(struct("cef_window_info_t"), platformTypes, shared, platformOnly = true))
  }

  test("platform pass skips structs every platform shares") {
    assert(PlatformSpecificity.emitsRecord(struct("cef_point_t"), platformTypes, shared, platformOnly = false))
    assert(!PlatformSpecificity.emitsRecord(struct("cef_point_t"), platformTypes, shared, platformOnly = true))
  }
}
