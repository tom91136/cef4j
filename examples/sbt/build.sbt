import scala.sys.process.Process

ThisBuild / organization := "net.kurobako.cef4j.examples"
ThisBuild / version := "1.0.0-SNAPSHOT"
ThisBuild / scalaVersion := "3.8.4"

lazy val CefPackager = config("cefPackager").hide

lazy val root = project
  .in(file("."))
  .configs(CefPackager)
  .settings(
    name := "cef4j-sbt-consumer",
    resolvers += Resolver.mavenLocal,
    libraryDependencies ++= Seq(
      "net.kurobako.cef4j" % "cef4j-inprocess-swing" % cef4jVersion,
      "net.kurobako.cef4j" % "cef4j-cdp" % cef4jVersion,
      ("net.kurobako.cef4j" % platformArtifact % cef4jVersion).classifier(platformClassifier),
      "net.kurobako.cef4j" % "cef4j-runtime-packager" % packagerVersion % CefPackager
    ),
    Compile / resourceGenerators += packageCefRuntime.taskValue
  )

lazy val cef4jVersion = sys.props.getOrElse("cef4j.version", "150.0.0")
lazy val packagerVersion = sys.props.getOrElse("cef4j.packager.version", "0.1.0")
lazy val platformArtifact = sys.props.getOrElse("cef4j.platform.artifact", "cef4j-platform-linux")
lazy val platformClassifier = sys.props.getOrElse("cef4j.platform.classifier", "x86_64")
lazy val cefVersion = sys.props.getOrElse("cef.version", "150.0.18+gdb11278+chromium-150.0.7871.213")

lazy val packageCefRuntime = Def.task {
  val output = (Compile / resourceManaged).value
  val classpath = (CefPackager / update).value.allFiles
  val command = Seq(
    "java",
    "-cp",
    classpath.mkString(java.io.File.pathSeparator),
    "net.kurobako.cef4j.packager.CefPackager",
    "package",
    s"--cef-version=$cefVersion",
    "--platform=linux-x86_64",
    "--locales=en-US",
    s"--output=$output"
  )
  val status = Process(command, baseDirectory.value).!
  if (status != 0) sys.error(s"cef4j-runtime-packager failed with exit code $status")
  (output ** "*").get.filter(_.isFile)
}
