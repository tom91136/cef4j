package net.kurobako.cef4j.codegen.passes

import net.kurobako.cef4j.codegen.Config
import net.kurobako.cef4j.codegen.HeaderMetadataIndex

object IndexHeaderMetadata {
  def apply(cfg: Config): HeaderMetadataIndex =
    HeaderMetadataIndex(cfg.cefInclude)
}
