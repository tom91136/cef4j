package net.kurobako.cef4j.codegen

import java.nio.file.Paths

import net.kurobako.cef4j.codegen.CodegenPlatform
import net.kurobako.cef4j.codegen.passes.CleanOutputDirs
import net.kurobako.cef4j.codegen.passes.DiscoverHeaders
import net.kurobako.cef4j.codegen.passes.EmitMarkerInterfaces
import net.kurobako.cef4j.codegen.passes.EmitNativePointer
import net.kurobako.cef4j.codegen.passes.EmitRuntimeStubs
import net.kurobako.cef4j.codegen.passes.EmitTree
import net.kurobako.cef4j.codegen.passes.InitialiseDocComments
import net.kurobako.cef4j.codegen.passes.LoadParseState
import net.kurobako.cef4j.codegen.passes.ParseTree
import net.kurobako.cef4j.codegen.passes.PreprocessHeaders
import net.kurobako.cef4j.codegen.passes.RefineTree
import net.kurobako.cef4j.codegen.passes.ValidateTypedPointers

object Main {
  def main(args: Array[String]): Unit = {
    val startNanos = System.nanoTime()
    val cfg        = parseArgs(args.toList)

    given Banners = cfg.regenerateCommand
      .map(Banners.forCommand)
      .getOrElse(Banners.fromInclude(cfg.cefInclude))

    val headerInputs      = DiscoverHeaders(cfg)
    val preprocessed      = PreprocessHeaders(headerInputs, cfg)
    val parseState        = LoadParseState(cfg, headerInputs)
    val parsedTree        = ParseTree(preprocessed, headerInputs, cfg, parseState)
    val refinedTree       = RefineTree(parsedTree, parseState)
    val untypedPtrWarning = ValidateTypedPointers(refinedTree, parseState.handlerNames)

    given namingContext: Naming.Context          = parseState.namingContext
    given docCommentContext: DocComments.Context =
      InitialiseDocComments(parseState.docContext, parsedTree, refinedTree)

    CleanOutputDirs.cleanCppOutput(cfg.outCpp, cfg.outCppPlatformDir, cleanCommon = cfg.emitCommonCpp)
    if (cfg.emitJava) {
      if (cfg.emitJavaPlatformOnly) {
        CleanOutputDirs(cfg.outJavaPlatformPackageDir)
      } else {
        CleanOutputDirs(cfg.outJavaPackageDir)
        EmitNativePointer(cfg.outJavaPackageDir, cfg.javaPackage)
        EmitMarkerInterfaces(cfg.outJavaPackageDir, cfg.javaPackage)
      }
    }
    EmitTree(cfg, parseState, refinedTree)
    if (cfg.emitCommonCpp) {
      val javaSourceRoot = cfg.runtimeJavaSourceRoot.getOrElse(cfg.outJava)
      EmitRuntimeStubs(javaSourceRoot, generatedPackageDir(javaSourceRoot, cfg.javaPackage), cfg.outCpp)
    }

    val elapsedSeconds = (System.nanoTime() - startNanos) / 1_000_000_000.0
    println(
      f"""cef4j codegen complete:
         |  headers scanned: ${preprocessed.size}%d
         |  Java classes generated: ${refinedTree.javaClassesGenerated}%d
         |  C++ classes generated: ${refinedTree.cppClassesGenerated}%d
         |  declarations processed: ${parsedTree.decls.size}%d
         |  free functions parsed: ${refinedTree.freeFunctions.size}%d (${refinedTree.orphanFreeFunctions.size}%d orphans)
         |  Untyped ptr warnings: ${untypedPtrWarning.size}%d
         |  total time: $elapsedSeconds%.2f s""".stripMargin
    )
  }

  private[codegen] def parseArgs(args: List[String]): Config = {
    val required = List("--cef-include=", "--out-cpp=", "--out-java=")
    required.foreach { prefix =>
      require(args.exists(_.startsWith(prefix)), s"Missing required argument: ${prefix.dropRight(1)}")
    }
    val cfg = args.foldLeft(Config(
      cefInclude = Paths.get("."),
      outCpp = Paths.get("."),
      outJava = Paths.get("."),
      javaPackage = "net.kurobako.cef4j.gen",
      compilerId = "gcc"
    )) { (cfg, arg) =>
      arg match {
        case s"--cef-include=$path" =>
          cfg.copy(cefInclude = Paths.get(path))
        case s"--out-cpp=$path" =>
          cfg.copy(outCpp = Paths.get(path))
        case s"--out-java=$path" =>
          cfg.copy(outJava = Paths.get(path))
        case s"--java-package=$value" =>
          cfg.copy(javaPackage = value)
        case s"--compiler=$id" =>
          cfg.copy(compilerId = id)
        case s"--cef-api-version=$value" =>
          cfg.copy(cefApiVersionRaw = Some(value))
        case s"--target-platform=$platform" =>
          CodegenPlatform.parse(platform) match {
            case Some(p) => cfg.copy(targetPlatform = p)
            case None    =>
              throw new IllegalArgumentException(
                s"Unknown target platform '$platform' (expected linux/mac/windows, linux64/linuxarm64, macosx64/macosarm64, windows64/windowsarm64, or auto)"
              )
          }
        case s"--emit-java=$enabled" =>
          cfg.copy(emitJava = parseBoolean(enabled))
        case s"--emit-common-cpp=$enabled" =>
          cfg.copy(emitCommonCpp = parseBoolean(enabled))
        case s"--emit-java-platform-only=$enabled" =>
          cfg.copy(emitJavaPlatformOnly = parseBoolean(enabled))
        case s"--java-platform-subpackage=$value" =>
          cfg.copy(javaPlatformSubPackage = value.trim)
        case s"--runtime-java-source-root=$path" =>
          cfg.copy(runtimeJavaSourceRoot = Some(Paths.get(path)))
        case s"--regenerate-command=$command" =>
          cfg.copy(regenerateCommand = Some(command))
        case s"--extra-cpp-dirs=$dirs" =>
          cfg.copy(extraCppDirs = parseDirList(dirs))
        case s"--extra-capi-dirs=$dirs" =>
          cfg.copy(extraCapiDirs = parseDirList(dirs))
        case other =>
          throw new IllegalArgumentException(s"Unknown argument: $other")
      }
    }
    validateOutputPaths(cfg)
    cfg
  }

  private def validateOutputPaths(cfg: Config): Unit = {
    val workingDir = Paths.get("").toAbsolutePath.normalize
    val input      = cfg.cefInclude.toAbsolutePath.normalize
    val outputs    = List(cfg.outCpp.toAbsolutePath.normalize, cfg.outJava.toAbsolutePath.normalize)
    outputs.foreach { output =>
      require(output != workingDir, s"Refusing repository/current-directory output: $output")
      require(!input.startsWith(output), s"Refusing output that contains the CEF input tree: $output")
    }
    require(
      !outputs(0).startsWith(outputs(1)) && !outputs(1).startsWith(outputs(0)),
      "C++ and Java output directories must not overlap"
    )
  }

  private def parseDirList(value: String): List[String] =
    value.split(",").iterator.map(_.trim).filter(_.nonEmpty).toList

  private def parseBoolean(value: String): Boolean =
    value.trim.toLowerCase match {
      case "1" | "true" | "yes" | "on"  => true
      case "0" | "false" | "no" | "off" => false
      case other                        => throw new IllegalArgumentException(s"Invalid boolean value: $other")
    }

  private[codegen] def generatedPackageDir(
      javaSourceRoot: java.nio.file.Path,
      javaPackage: String
  ): java.nio.file.Path =
    javaPackage.split('.').foldLeft(javaSourceRoot)((path, segment) => path.resolve(segment))
}
