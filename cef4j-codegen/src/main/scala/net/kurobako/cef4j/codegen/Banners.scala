package net.kurobako.cef4j.codegen

import java.nio.file.Files
import java.nio.file.Path

final class Banners private (val regenerateCommand: String) {
  val java: String                    = s"// GENERATED - do not edit. Regenerate via: $regenerateCommand"
  val javaGeneratedAnnotation: String = s"@Generated(\"$regenerateCommand\")"
  val javaAnnotation: String          =
    javaAnnotations("SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused", "NullableForbidden")
  val cpp: String = s"// GENERATED - do not edit. Regenerate via: $regenerateCommand"

  def javaAnnotations(suppressions: String*): String = {
    val values = suppressions.map(value => s"\"$value\"").mkString(", ")
    if (values.isEmpty) javaGeneratedAnnotation
    else s"$javaGeneratedAnnotation\n@SuppressWarnings({$values})"
  }
}

object Banners {

  val javaAnnotationClass: String = "javax.annotation.processing.Generated"

  val javaAnnotationImport: String = s"import $javaAnnotationClass;"

  def forCefVersion(cefVersion: String): Banners =
    new Banners(s"mvn generate-sources -pl cef4j-platform -Dcef.version=$cefVersion")

  def forCommand(regenerateCommand: String): Banners = new Banners(regenerateCommand)

  def fromInclude(cefInclude: Path): Banners = {
    val versionHeader = cefInclude.resolve("cef_version.h")
    val CefVersionRe  = """#define\s+CEF_VERSION\s+"([^"]+)"""".r
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
