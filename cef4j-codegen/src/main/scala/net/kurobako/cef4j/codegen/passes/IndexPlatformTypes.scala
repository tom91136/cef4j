package net.kurobako.cef4j.codegen.passes

import java.nio.file.Files
import java.nio.file.Path

import net.kurobako.cef4j.codegen.Config
import net.kurobako.cef4j.codegen.Platform

object IndexPlatformTypes {
  private val StructOrEnumCloseRe = """}\s*(cef_\w+_t)\s*;""".r
  private val SimpleTypedefRe     = """typedef\s+[^;{}]+\s+(cef_\w+_t)\s*;""".r

  def apply(cfg: Config): Set[String] = apply(cfg.cefInclude, cfg.targetPlatform)

  def apply(cefInclude: Path, platform: Platform): Set[String] = {
    val header = cefInclude.resolve("internal").resolve(platform.cefTypesHeader)
    if (!Files.exists(header)) {
      System.err.println(s"WARN: platform types header not found: $header")
      Set.empty
    } else {
      val content     = Files.readString(header)
      val fromStructs = StructOrEnumCloseRe.findAllMatchIn(content).map(_.group(1))
      val fromAliases = SimpleTypedefRe.findAllMatchIn(content).map(_.group(1))
      (fromStructs ++ fromAliases).toSet
    }
  }
}
