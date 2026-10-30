package net.kurobako.cef4j.codegen

import java.nio.file.Files
import java.nio.file.Path

object Banners {

  val javaAnnotationClass: String = "javax.annotation.processing.Generated"

  private val CefVersionRe = """#define\s+CEF_VERSION\s+"([^"]+)"""".r

  val javaAnnotationImport: String = s"import $javaAnnotationClass;"

  val javaBoundaryImport: String = "import net.kurobako.cef4j.policy.NullableBoundary;"

  def forCefVersion(cefVersion: String): Banners =
    Banners(s"mvn generate-sources -pl cef4j-platform -Dcef.version=$cefVersion")

  def forCommand(regenerateCommand: String): Banners = Banners(regenerateCommand)

  def forIpc: Banners = forCommand("mvn generate-sources -pl cef4j-remote")

  given defaultBanners: Banners = forIpc

  def fromInclude(cefInclude: Path): Banners = {
    val versionHeader = cefInclude.resolve("cef_version.h")
    val version       = Files.readString(versionHeader).linesIterator
      .collectFirst { case CefVersionRe(v) => v }
      .getOrElse(throw IllegalStateException(s"CEF_VERSION not found in $versionHeader"))
    forCefVersion(version)
  }

  def java(using b: Banners): String                    = b.java
  def javaGeneratedAnnotation(using b: Banners): String = b.javaGeneratedAnnotation
  def javaAnnotation(using b: Banners): String          = b.javaAnnotation
  def cpp(using b: Banners): String                     = b.cpp
}

class Banners private (val regenerateCommand: String) {
  val java: String                    = s"// GENERATED - do not edit. Regenerate via: $regenerateCommand"
  val javaGeneratedAnnotation: String = s"@Generated(\"$regenerateCommand\")"
  val javaAnnotation: String          =
    s"$javaGeneratedAnnotation\n@NullableBoundary(\"generated external ABI\")"
  val cpp: String = s"// GENERATED - do not edit. Regenerate via: $regenerateCommand"

  def javaAnnotations(suppressions: String*): String = {
    val values = suppressions.map(value => s"\"$value\"").mkString(", ")
    if (values.isEmpty) javaGeneratedAnnotation
    else s"$javaGeneratedAnnotation\n@SuppressWarnings({$values})"
  }
}
