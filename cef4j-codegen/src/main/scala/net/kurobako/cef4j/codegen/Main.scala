package net.kurobako.cef4j.codegen

import java.nio.file.Paths

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
    Banners.init(cfg.cefInclude)

    val headerInputs      = DiscoverHeaders(cfg)
    val preprocessed      = PreprocessHeaders(headerInputs, cfg)
    val parseState        = LoadParseState(cfg, headerInputs)
    val parsedTree        = ParseTree(preprocessed, headerInputs, parseState)
    val refinedTree       = RefineTree(parsedTree, parseState)
    val untypedPtrWarning = ValidateTypedPointers(refinedTree, parseState.handlerNames)

    given namingContext: Naming.Context          = parseState.namingContext
    given docCommentContext: DocComments.Context =
      InitialiseDocComments(parseState.docContext, parsedTree, refinedTree)

    CleanOutputDirs(cfg.outCpp, cfg.outJavaPackageDir)
    EmitNativePointer(cfg.outJavaPackageDir, cfg.javaPackage)
    EmitMarkerInterfaces(cfg.outJavaPackageDir, cfg.javaPackage)
    EmitTree(cfg, parseState, refinedTree)
    EmitRuntimeStubs(cfg.outJava, cfg.outJavaPackageDir, cfg.outCpp)

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

  private def parseArgs(args: List[String]): Config =
    args.foldLeft(Config(
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
        case other =>
          System.err.println(s"Unknown argument: $other")
          cfg
      }
    }
}
