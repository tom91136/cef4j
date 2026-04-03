package net.kurobako.cef4j.codegen.passes

import java.nio.file.Files
import scala.jdk.StreamConverters._

import net.kurobako.cef4j.codegen.Config
import net.kurobako.cef4j.codegen.HeaderInputs

object DiscoverHeaders {
  def apply(cfg: Config): HeaderInputs = {
    val capiDir     = cfg.cefInclude.resolve("capi")
    val capiHeaders = (capiDir :: cfg.extraCapiDirs.map(capiDir.resolve)).filter(Files.isDirectory(_))
      .flatMap(dir =>
        Files.list(dir).toScala(List).filter(p => Files.isRegularFile(p) && p.toString.endsWith("_capi.h"))
      )
      .sorted
    val typesHeader = cfg.cefInclude.resolve("internal/cef_types.h")
    HeaderInputs(capiDir, capiHeaders, typesHeader, cfg.cefInclude.getParent)
  }
}
