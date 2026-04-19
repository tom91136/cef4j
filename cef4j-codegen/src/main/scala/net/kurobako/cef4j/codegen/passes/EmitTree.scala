package net.kurobako.cef4j.codegen.passes

import net.kurobako.cef4j.codegen.Banners
import net.kurobako.cef4j.codegen.CType
import net.kurobako.cef4j.codegen.CefDecl
import net.kurobako.cef4j.codegen.Config
import net.kurobako.cef4j.codegen.DocComments
import net.kurobako.cef4j.codegen.JavaEnumCodeGen
import net.kurobako.cef4j.codegen.JavaInterfaceCodeGen
import net.kurobako.cef4j.codegen.JavaRecordCodeGen
import net.kurobako.cef4j.codegen.JniCppCodeGen
import net.kurobako.cef4j.codegen.Naming
import net.kurobako.cef4j.codegen.Param
import net.kurobako.cef4j.codegen.ParseState
import net.kurobako.cef4j.codegen.PlatformSpecificity
import net.kurobako.cef4j.codegen.RefinedTree

object EmitTree {
  def apply(cfg: Config, parseState: ParseState, refined: RefinedTree)(using DocComments.Context, Banners): Unit = {
    val objectDeclMap                  = refined.decls.collect { case d: CefDecl.ObjectStruct => d.name -> d }.toMap
    val sharedPlatformInterfaceStructs = {
      // Data structs referenced as ConstDataStructPtr in handler callbacks, plus platform-specific
      // data structs referenced by-value in object/handler struct methods
      // (e.g., CefWindowInfo is platform-specific but used by CefBrowserHost.createBrowserSync).
      val byValueName: PartialFunction[CType, String] = {
        case CType.ByValueIn(name)  => name
        case CType.ByValueOut(name) => name
      }
      def byValueMatch(p: Param): Option[String] = p match {
        case Param(_, t, _, _)
            if byValueName.isDefinedAt(t) && parseState.platformSpecificTypes.contains(byValueName(t)) =>
          Some(byValueName(t))
        case _ => None
      }
      refined.decls.foldLeft(Set.empty[String]) {
        case (acc, d: CefDecl.HandlerStruct) =>
          d.fns.foldLeft(acc)((a, fn) =>
            fn.params.foldLeft(a) {
              case (s, Param(_, CType.ConstDataStructPtr(name), _, _)) => s + name
              case (s, p)                                              => byValueMatch(p).fold(s)(s + _)
            }
          )
        case (acc, d: CefDecl.ObjectStruct) =>
          d.fns.foldLeft(acc)((a, fn) =>
            fn.params.foldLeft(a)((s, p) => byValueMatch(p).fold(s)(s + _))
          )
        case (acc, _) => acc
      }
    }
    given Naming.Context = parseState.namingContext.copy(platformInterfaceTypes = sharedPlatformInterfaceStructs)

    val jniCodeGen = new JniCppCodeGen(
      refined.dataStructMap,
      parseState.handlerNames,
      refined.scopedNames,
      parseState.structHeaderMap
    )

    refined.decls.foreach(emitDecl(
      _,
      cfg,
      parseState,
      refined,
      jniCodeGen,
      objectDeclMap,
      sharedPlatformInterfaceStructs
    ))

    if (refined.orphanFreeFunctions.nonEmpty) {
      val (platformOrphans, commonOrphans) = refined.orphanFreeFunctions.partition(ff =>
        PlatformSpecificity.isPlatformSpecificFreeFunction(ff, parseState.platformSpecificTypes)
      )
      if (cfg.emitJava && !cfg.emitJavaPlatformOnly) {
        JavaInterfaceCodeGen.emitGlobals(refined.orphanFreeFunctions, cfg.outJavaPackageDir, parseState.docs)
      }
      if (cfg.emitCommonCpp && commonOrphans.nonEmpty) {
        jniCodeGen.emitGlobals(commonOrphans, cfg.outCpp)
      }
      if (platformOrphans.nonEmpty) {
        jniCodeGen.emitGlobals(platformOrphans, cfg.outCppPlatformDir)
      }
    }
  }

  private def getAncestors(
      decl: CefDecl.ObjectStruct,
      allDecls: Map[String, CefDecl.ObjectStruct]
  ): List[CefDecl.ObjectStruct] = {
    @scala.annotation.tailrec
    def loop(current: CefDecl.ObjectStruct, acc: List[CefDecl.ObjectStruct]): List[CefDecl.ObjectStruct] =
      current.parentStruct.flatMap(allDecls.get) match {
        case None         => acc.reverse
        case Some(parent) => loop(parent, parent :: acc)
      }
    loop(decl, Nil)
  }

  private def emitDecl(
      decl: CefDecl,
      cfg: Config,
      parseState: ParseState,
      refined: RefinedTree,
      jniCodeGen: JniCppCodeGen,
      objectDeclMap: Map[String, CefDecl.ObjectStruct],
      sharedPlatformInterfaceStructs: Set[String]
  )(using Naming.Context, DocComments.Context, Banners): Unit =
    decl match {
      case d: CefDecl.ObjectStruct =>
        val associatedFreeFunctions    = refined.freeFunctionsByOwner.getOrElse(d.name, Nil)
        val ancestors                  = getAncestors(d, objectDeclMap)
        val hasPlatformSpecificFreeFns = associatedFreeFunctions.exists(ff =>
          PlatformSpecificity.isPlatformSpecificFreeFunction(ff, parseState.platformSpecificTypes)
        )
        val cppOutDir =
          if (
            PlatformSpecificity.isPlatformSpecificDecl(
              d,
              parseState.platformSpecificTypes
            ) || hasPlatformSpecificFreeFns
          )
            cfg.outCppPlatformDir
          else cfg.outCpp
        if (cppOutDir == cfg.outCppPlatformDir || cfg.emitCommonCpp) {
          jniCodeGen.emit(d, cppOutDir, associatedFreeFunctions)
        }
        if (cfg.emitJava && !cfg.emitJavaPlatformOnly) {
          JavaInterfaceCodeGen.emitObject(
            d,
            cfg.outJavaPackageDir,
            parseState.docs,
            parseState.classDocs.getOrElse(d.name, ""),
            handlerNames = parseState.handlerNames,
            freeFunctions = associatedFreeFunctions,
            ancestorDecls = ancestors
          )
        }
      case d: CefDecl.HandlerStruct =>
        val cppOutDir = if (PlatformSpecificity.isPlatformSpecificDecl(d, parseState.platformSpecificTypes))
          cfg.outCppPlatformDir
        else cfg.outCpp
        if (cppOutDir == cfg.outCppPlatformDir || cfg.emitCommonCpp) {
          jniCodeGen.emitHandler(d, cppOutDir)
        }
        if (cfg.emitJava && !cfg.emitJavaPlatformOnly) {
          JavaInterfaceCodeGen.emitHandler(
            d,
            cfg.outJavaPackageDir,
            parseState.docs,
            parseState.classDocs.getOrElse(d.name, ""),
            handlerNames = parseState.handlerNames
          )
        }
      case d: CefDecl.DataStruct =>
        val isPlatformSpecific = PlatformSpecificity.isPlatformSpecificDecl(d, parseState.platformSpecificTypes)
        if (cfg.emitJava && (!cfg.emitJavaPlatformOnly || isPlatformSpecific)) {
          val emitAsPlatformInterface =
            !cfg.emitJavaPlatformOnly && isPlatformSpecific && sharedPlatformInterfaceStructs.contains(d.name)
          JavaRecordCodeGen.emit(
            d,
            cfg.outJavaPackageDir,
            parseState.classDocs.getOrElse(d.name, ""),
            needsMutable = d.needsMutable,
            fieldDocs = parseState.structFieldDocs.getOrElse(d.name, Map.empty),
            emitAsPlatformInterface = emitAsPlatformInterface,
            platformImplSubPackages = if (emitAsPlatformInterface) List("linux", "mac", "win") else Nil,
            implementSharedType =
              cfg.emitJavaPlatformOnly && isPlatformSpecific && sharedPlatformInterfaceStructs.contains(d.name)
          )
        }
      case d: CefDecl.Enum =>
        val isPlatformSpecific = PlatformSpecificity.isPlatformSpecificDecl(d, parseState.platformSpecificTypes)
        if (cfg.emitJava && (!cfg.emitJavaPlatformOnly || isPlatformSpecific)) {
          JavaEnumCodeGen.emit(d, cfg.outJavaPackageDir, sourceHeader = "cef_types.h")
        }
      case _: CefDecl.FreeFunction =>
    }
}
