package net.kurobako.cef4j.codegen

import java.nio.file.Files
import java.nio.file.Path

/** Centralised file headers for all generated Java and C++ sources. */
final class Banners(val cefVersion: String) {
  val regenerateCommand: String = s"mvn generate-sources -pl cef4j-native -Dcef.version=$cefVersion"
  val java: String              = s"// GENERATED - do not edit. Regenerate via: $regenerateCommand"
  val javaAnnotation: String    =
    s"""@Generated("$regenerateCommand")\n@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})"""
  val cpp: String = s"// GENERATED - do not edit. Regenerate via: $regenerateCommand"
}

object Banners {

  /** The fully-qualified class name of the {@code @Generated} annotation. */
  val javaAnnotationClass: String = "javax.annotation.processing.Generated"

  /** Import line for the {@code @Generated} annotation. */
  val javaAnnotationImport: String = s"import $javaAnnotationClass;"

  def fromInclude(cefInclude: Path): Banners = {
    val versionHeader = cefInclude.resolve("cef_version.h")
    val CefVersionRe  = """#define\s+CEF_VERSION\s+"([^"]+)"""".r
    val version       = Files.readString(versionHeader).linesIterator
      .collectFirst { case CefVersionRe(v) => v }
      .getOrElse(throw IllegalStateException(s"CEF_VERSION not found in $versionHeader"))
    new Banners(version)
  }

  // Forwarding defs so callers can write `Banners.java` without `summon`.
  def java(using b: Banners): String           = b.java
  def javaAnnotation(using b: Banners): String = b.javaAnnotation
  def cpp(using b: Banners): String            = b.cpp
}
