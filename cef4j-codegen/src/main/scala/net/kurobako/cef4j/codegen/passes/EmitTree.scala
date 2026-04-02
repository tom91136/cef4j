package net.kurobako.cef4j.codegen.passes

import net.kurobako.cef4j.codegen.CefDecl
import net.kurobako.cef4j.codegen.Config
import net.kurobako.cef4j.codegen.DocComments
import net.kurobako.cef4j.codegen.JavaEnumCodeGen
import net.kurobako.cef4j.codegen.JavaInterfaceCodeGen
import net.kurobako.cef4j.codegen.JavaRecordCodeGen
import net.kurobako.cef4j.codegen.JniCppCodeGen
import net.kurobako.cef4j.codegen.Naming
import net.kurobako.cef4j.codegen.ParseState
import net.kurobako.cef4j.codegen.RefinedTree

object EmitTree {
  def apply(cfg: Config, parseState: ParseState, refined: RefinedTree)(using DocComments.Context): Unit = {
    given Naming.Context = parseState.namingContext

    val jniCodeGen = new JniCppCodeGen(
      refined.dataStructMap,
      parseState.handlerNames,
      refined.scopedNames,
      parseState.structHeaderMap
    )

    refined.decls.foreach(emitDecl(_, cfg, parseState, refined, jniCodeGen))

    if (refined.orphanFreeFunctions.nonEmpty) {
      JavaInterfaceCodeGen.emitGlobals(refined.orphanFreeFunctions, cfg.outJavaPackageDir, parseState.docs)
      jniCodeGen.emitGlobals(refined.orphanFreeFunctions, cfg.outCpp)
    }
  }

  private def emitDecl(
      decl: CefDecl,
      cfg: Config,
      parseState: ParseState,
      refined: RefinedTree,
      jniCodeGen: JniCppCodeGen
  )(using Naming.Context, DocComments.Context): Unit =
    decl match {
      case d: CefDecl.ObjectStruct =>
        val associatedFreeFunctions = refined.freeFunctionsByOwner.getOrElse(d.name, Nil)
        jniCodeGen.emit(d, cfg.outCpp, associatedFreeFunctions)
        JavaInterfaceCodeGen.emitObject(
          d,
          cfg.outJavaPackageDir,
          parseState.docs,
          parseState.classDocs.getOrElse(d.name, ""),
          handlerNames = parseState.handlerNames,
          freeFunctions = associatedFreeFunctions
        )
      case d: CefDecl.HandlerStruct =>
        jniCodeGen.emitHandler(d, cfg.outCpp)
        JavaInterfaceCodeGen.emitHandler(
          d,
          cfg.outJavaPackageDir,
          parseState.docs,
          parseState.classDocs.getOrElse(d.name, ""),
          handlerNames = parseState.handlerNames
        )
      case d: CefDecl.DataStruct =>
        JavaRecordCodeGen.emit(
          d,
          cfg.outJavaPackageDir,
          parseState.classDocs.getOrElse(d.name, ""),
          needsMutable = d.needsMutable,
          fieldDocs = parseState.structFieldDocs.getOrElse(d.name, Map.empty)
        )
      case d: CefDecl.Enum =>
        JavaEnumCodeGen.emit(d, cfg.outJavaPackageDir, sourceHeader = "cef_types.h")
      case _: CefDecl.FreeFunction =>
    }
}
