package net.kurobako.cef4j.codegen.passes

import java.nio.file.Files
import java.nio.file.Path

import net.kurobako.cef4j.codegen.DocComments

object InitialiseDocContext {
  private val CefMajorRe                           = """CEF_VERSION_MAJOR\s+(\d+)""".r
  private val CefMinorRe                           = """CEF_VERSION_MINOR\s+(\d+)""".r
  def apply(cefInclude: Path): DocComments.Context = {
    val versionHeader  = cefInclude.resolve("cef_version.h")
    val versionContent = if (Files.exists(versionHeader)) Files.readString(versionHeader) else ""
    val cefMajor       = CefMajorRe.findFirstMatchIn(versionContent).map(_.group(1).toInt).getOrElse(0)
    val cefMinor       = CefMinorRe.findFirstMatchIn(versionContent).map(_.group(1).toInt).getOrElse(0)
    DocComments.baseContext(cefMajor, cefMinor)
  }
}
