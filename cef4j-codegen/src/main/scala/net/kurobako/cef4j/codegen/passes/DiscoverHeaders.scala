package net.kurobako.cef4j.codegen.passes

import java.nio.file.Files
import scala.jdk.StreamConverters._

import net.kurobako.cef4j.codegen.Config
import net.kurobako.cef4j.codegen.HeaderInputs

object DiscoverHeaders {
  def apply(cfg: Config): HeaderInputs = {
    val capiDir     = cfg.cefInclude.resolve("capi")
    val capiHeaders = Files.list(capiDir)
      .toScala(List)
      .filter(_.toString.endsWith("_capi.h"))
    val typesHeader = cfg.cefInclude.resolve("internal/cef_types.h")
    HeaderInputs(capiDir, capiHeaders, typesHeader, cfg.cefInclude.getParent)
  }
}
