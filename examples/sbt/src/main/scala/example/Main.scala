package example

import java.nio.charset.StandardCharsets
import scala.io.Source
import scala.util.Using
import net.kurobako.cef4j.OS
import net.kurobako.cef4j.cdp.CdpSchema

@main def cef4jExample(): Unit =
  val platform = OS.platform()
  val manifest = s"cef-runtime/$platform/file-list.txt"
  val stream = Option(Thread.currentThread().getContextClassLoader.getResourceAsStream(manifest))
    .getOrElse(throw IllegalStateException(s"missing packaged CEF manifest $manifest"))
  val files =
    Using.resource(Source.fromInputStream(stream, StandardCharsets.UTF_8.name())):
      _.getLines().count(_.nonEmpty)
  require(files > 0, s"empty packaged CEF manifest $manifest")
  println(s"cef4j packaged $files CEF files for $platform; generated CDP targets Chromium ${CdpSchema.chromiumVersion()}")
