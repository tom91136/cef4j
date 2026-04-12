package net.kurobako.cef4j.codegen.passes

import java.nio.file.Path

import net.kurobako.cef4j.codegen.Config
import net.kurobako.cef4j.codegen.HeaderInputs
import net.kurobako.cef4j.codegen.Preprocessor

object PreprocessHeaders {
  def apply(headerInputs: HeaderInputs, cfg: Config): List[(Path, String)] =
    headerInputs.allHeaders.map { header =>
      header -> Preprocessor.preprocess(
        header,
        List(headerInputs.cefRoot),
        cfg.compilerId,
        cfg.targetPlatform.preprocessorDefines
      )
    }
}
