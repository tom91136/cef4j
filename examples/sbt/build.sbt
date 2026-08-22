import scala.sys.process.Process

ThisBuild / organization := "net.kurobako.cef4j.examples"
ThisBuild / version := "1.0.0-SNAPSHOT"
ThisBuild / scalaVersion := "3.8.4"

lazy val CefPackager = config("cefPackager").hide

lazy val cef4jVersion = sys.props.getOrElse("cef4j.version", "150.0.0")
lazy val packagerVersion = sys.props.getOrElse("cef4j.packager.version", "0.1.0")
lazy val cefVersion = sys.props.getOrElse("cef.version", "150.0.18+gdb11278+chromium-150.0.7871.213")

lazy val detectedPlatform = {
  val architecture = sys.props("os.arch").toLowerCase match {
    case "aarch64" | "arm64"         => "arm64"
    case "amd64" | "x86_64" | "x64" => "x86_64"
    case unsupported                     => sys.error(s"Unsupported CEF host architecture: $unsupported")
  }
  sys.props("os.name").toLowerCase match {
    case linux if linux.startsWith("linux") => (s"linux-$architecture", "cef4j-platform-linux", architecture)
    case mac if mac.startsWith("mac") || mac.startsWith("darwin") =>
      (s"macos-$architecture", "cef4j-platform-macos", architecture)
    case windows if windows.startsWith("windows") =>
      (s"windows-$architecture", "cef4j-platform-windows", architecture)
    case unsupported => sys.error(s"Unsupported CEF host operating system: $unsupported")
  }
}

lazy val runtimePlatform = detectedPlatform._1
lazy val platformArtifact = sys.props.getOrElse("cef4j.platform.artifact", detectedPlatform._2)
lazy val platformClassifier = sys.props.getOrElse("cef4j.platform.classifier", detectedPlatform._3)
lazy val embedRuntime = booleanProperty("cef4j.embed-runtime", default = false)
lazy val stripRuntime = booleanProperty("cef4j.strip-runtime", default = runtimePlatform.startsWith("linux-"))
lazy val externalRuntime = sys.props.get("cef4j.libcef.dir").orElse(sys.env.get("LIBCEF_DIR")).exists(_.trim.nonEmpty)

def booleanProperty(name: String, default: Boolean): Boolean = sys.props.get(name) match {
  case None          => default
  case Some("true")  => true
  case Some("false") => false
  case Some(value)   => sys.error(s"-D$name must be true or false, not: $value")
}

lazy val root = project
  .in(file("."))
  .dependsOn(cefRuntime)
  .settings(
    name := "cef4j-sbt-consumer",
    resolvers += Resolver.mavenLocal,
    libraryDependencies ++= Seq(
      "net.kurobako.cef4j" % "cef4j-inprocess-swing" % cef4jVersion,
      "net.kurobako.cef4j" % "cef4j-cdp" % cef4jVersion,
      ("net.kurobako.cef4j" % platformArtifact % cef4jVersion).classifier(platformClassifier)
    )
  )

lazy val cefRuntime = project
  .in(file("cef-runtime"))
  .configs(CefPackager)
  .settings(
    name := "cef4j-sbt-consumer-runtime",
    crossPaths := false,
    autoScalaLibrary := false,
    publish / skip := !embedRuntime || externalRuntime,
    resolvers += Resolver.mavenLocal,
    libraryDependencies ++= (if (embedRuntime && !externalRuntime)
      Seq("net.kurobako.cef4j" % "cef4j-runtime-packager" % packagerVersion % CefPackager)
    else Seq.empty),
    Compile / resourceGenerators += packageCefRuntime.taskValue,
    Compile / packageBin / artifact ~= (_.withClassifier(Some(runtimePlatform)))
  )

lazy val packageCefRuntime = Def.task {
  val output = (Compile / resourceManaged).value
  val classpath = (CefPackager / update).value.allFiles
  if (!embedRuntime || externalRuntime) {
    IO.delete(output / "cef-runtime")
    Seq.empty
  } else {
    val packagerOffline = offline.value || booleanProperty("cef4j.packager.offline", default = false)
    val command = Seq(
      "java",
      "-cp",
      classpath.mkString(java.io.File.pathSeparator),
      "net.kurobako.cef4j.packager.CefPackager",
      "package",
      s"--cef-version=$cefVersion",
      "--platform=host",
      "--locales=en-US",
      "--skip-if-current",
      s"--output=$output"
    ) ++ (if (packagerOffline) Seq("--offline") else Seq.empty) ++
      (if (stripRuntime) Seq("--strip") else Seq.empty)
    val status = Process(command, baseDirectory.value).!
    if (status != 0) sys.error(s"cef4j-runtime-packager failed with exit code $status")
    (output ** "*").get.filter(_.isFile)
  }
}
