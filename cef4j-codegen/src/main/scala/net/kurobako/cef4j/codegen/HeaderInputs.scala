package net.kurobako.cef4j.codegen

import java.nio.file.Path

case class HeaderInputs(
    capiDir: Path,
    capiHeaders: List[Path],
    typesHeader: Path,
    cefRoot: Path
) {
  val allHeaders: List[Path] = capiHeaders :+ typesHeader
}
