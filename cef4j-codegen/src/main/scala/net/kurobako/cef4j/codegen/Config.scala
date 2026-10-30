package net.kurobako.cef4j.codegen

import java.nio.file.Path

object Config {

  /** Extract and normalise a CEF API version.
    *
    * Accepts either a bare major version number ("150") or a full CEF version string
    * ("150.0.18+gdb11278+chromium-150.0.7871.213"). In both cases, the major version is extracted and multiplied by 100
    * (e.g. 150 → 15000) to match CEF's internal `CEF_API_VERSION_NNNNN` naming convention.
    */
  def normaliseCefApiVersion(raw: String): String = {
    val trimmed  = raw.trim
    val majorStr = if (trimmed.contains(".")) trimmed.takeWhile(_.isDigit) else trimmed
    require(majorStr.matches("^[0-9]+$"), s"CEF API version must be numeric or a version string: $raw")
    val value = BigInt(majorStr)
    if (value >= 133 && value < 1000) (value * 100).toString else majorStr
  }
}

case class Config(
    cefInclude: Path,
    outCpp: Path,
    outJava: Path,
    javaPackage: String,
    compilerId: String,
    cefApiVersionRaw: Option[String] = None,
    outResourceDir: Option[Path] = None,
    extraCppDirs: List[String] = Nil,
    extraCapiDirs: List[String] = Nil,
    targetPlatform: Platform = Platform.detectCurrent,
    emitJava: Boolean = true,
    emitCommonCpp: Boolean = true,
    emitJavaPlatformOnly: Boolean = false,
    javaPlatformSubPackage: String = "",
    runtimeJavaSourceRoot: Option[Path] = None,
    regenerateCommand: Option[String] = None
) {
  val outJavaPackageDir: Path         = outJava.resolve(javaPackage.replace('.', '/'))
  val outJavaPlatformPackageDir: Path =
    if (javaPlatformSubPackage.nonEmpty) outJavaPackageDir.resolve(javaPlatformSubPackage.replace('.', '/'))
    else outJavaPackageDir
  val outCppPlatformDir: Path           = outCpp.resolve(targetPlatform.id)
  val preprocessorDefines: List[String] =
    targetPlatform.preprocessorDefines ++ cefApiVersionRaw.toList.map { raw =>
      s"CEF_API_VERSION=${Config.normaliseCefApiVersion(raw)}"
    }
}
