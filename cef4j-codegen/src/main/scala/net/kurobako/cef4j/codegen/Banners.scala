package net.kurobako.cef4j.codegen

import java.nio.file.Files
import java.nio.file.Path

/** Centralised file headers for all generated Java and C++ sources. */
object Banners {

  private var _cefVersion: String = "UNKNOWN"

  /** Initialise with the CEF version string parsed from {@code cef_version.h}. Must be called before any banner values
    * are used.
    */
  def init(cefInclude: Path): Unit = {
    val versionHeader = cefInclude.resolve("cef_version.h")
    val CefVersionRe  = """#define\s+CEF_VERSION\s+"([^"]+)"""".r
    _cefVersion = Files.readString(versionHeader).linesIterator
      .collectFirst { case CefVersionRe(v) => v }
      .getOrElse(throw IllegalStateException(s"CEF_VERSION not found in $versionHeader"))
  }

  /** The CEF version string, e.g. {@code "146.0.9+g3ca6a87+chromium-146.0.7680.165"}. */
  def cefVersion: String = _cefVersion

  /** Command to regenerate all generated sources. */
  lazy val regenerateCommand: String = s"mvn generate-sources -pl cef4j-native -Dcef.version=$_cefVersion"

  /** Top-of-file comment for generated Java sources. */
  lazy val java: String = s"// GENERATED - do not edit. Regenerate via: $regenerateCommand"

  /** {@code @Generated} annotation for generated Java classes. Requires {@code javax.annotation.processing.Generated}.
    */
  lazy val javaAnnotation: String = s"""@Generated("$regenerateCommand")"""

  /** Fully-qualified class name of the {@code @Generated} annotation. */
  val javaAnnotationClass: String = "javax.annotation.processing.Generated"

  /** Import for the {@code @Generated} annotation. */
  val javaAnnotationImport: String = s"import $javaAnnotationClass;"

  /** Top-of-file comment for generated C++ sources. */
  lazy val cpp: String = s"// GENERATED - do not edit. Regenerate via: $regenerateCommand"
}
