package net.kurobako.cef4j.codegen

import java.nio.file.Path

object JavaInterfaceCodeGen {

  def emitObject(
      decl: CefDecl.ObjectStruct,
      outDir: Path,
      docs: Map[String, String] = Map.empty,
      classDoc: String = "",
      handlerNames: Set[String] = Set.empty,
      freeFunctions: List[CefDecl.FreeFunction] = Nil,
      ancestorDecls: List[CefDecl.ObjectStruct] = Nil
  )(using ctx: Naming.Context, dc: DocComments.Context)(using Banners): Unit = {
    val javaName       = Naming.structToJavaName(decl.name)
    val subPkg         = ctx.subPackages.getOrElse(decl.name, "")
    val ffNativeDecls  = collectFreeFuncNativeDecls(freeFunctions)
    val nativePeerBody = JavaNativeCodeGen.renderInnerClass(decl, ffNativeDecls, handlerNames, ancestorDecls)
    val parentCefName  = ancestorDecls.headOption.map(_.name)
    val content        =
      renderInterface(
        javaName,
        decl.fns,
        docs,
        isObject = true,
        classDoc = classDoc,
        nativePeerBody = nativePeerBody,
        freeFunctions = freeFunctions,
        sourceHeader = decl.sourceHeader,
        cefStructName = decl.name,
        subPackage = subPkg,
        parentCefName = parentCefName,
        ancestorDecls = ancestorDecls
      )
    JavaCodeGen.writeJavaFile(outDir, javaName, content, subPkg)
  }

  def emitGlobals(
      freeFunctions: List[CefDecl.FreeFunction],
      outDir: Path,
      docs: Map[String, String] = Map.empty
  )(using Naming.Context, DocComments.Context, Banners): Unit = {
    if (freeFunctions.isEmpty) return
    val content = renderGlobalsClass(freeFunctions, docs)
    JavaCodeGen.writeJavaFile(outDir, "CefGlobals", content)
  }

  def emitHandler(
      decl: CefDecl.HandlerStruct,
      outDir: Path,
      docs: Map[String, String] = Map.empty,
      classDoc: String = "",
      handlerNames: Set[String] = Set.empty
  )(using ctx: Naming.Context, dc: DocComments.Context)(using Banners): Unit = {
    val javaName = Naming.structToJavaName(decl.name)
    val subPkg   = ctx.subPackages.getOrElse(decl.name, "")
    val content  =
      renderInterface(
        javaName,
        decl.fns,
        docs,
        isObject = false,
        classDoc = classDoc,
        handlerNames = handlerNames,
        sourceHeader = decl.sourceHeader,
        cefStructName = decl.name,
        subPackage = subPkg
      )
    JavaCodeGen.writeJavaFile(outDir, javaName, content, subPkg)
  }

  private def isHandlerPtrReturn(ret: CType, handlerNames: Set[String]): Boolean = ret match {
    case CType.ObjectPtr(name) => handlerNames.contains(name)
    case CType.Ptr(inner)      => handlerNames.contains(inner.stripPrefix("_"))
    case _                     => false
  }

  private def handlerPtrJavaType(ret: CType)(using Naming.Context): String = ret match {
    case CType.ObjectPtr(name) => Naming.structToJavaName(name)
    case CType.Ptr(inner)      => Naming.structToJavaName(inner.stripPrefix("_"))
    case _                     => Naming.javaType(ret)
  }

  private def renderInterface(
      javaName: String,
      fns: List[FnPtr],
      docs: Map[String, String],
      isObject: Boolean,
      classDoc: String = "",
      nativePeerBody: String = "",
      handlerNames: Set[String] = Set.empty,
      freeFunctions: List[CefDecl.FreeFunction] = Nil,
      sourceHeader: String = "",
      cefStructName: String = "",
      subPackage: String = "",
      parentCefName: Option[String] = None,
      ancestorDecls: List[CefDecl.ObjectStruct] = Nil
  )(using Naming.Context, DocComments.Context, Banners): String = {
    val ancestorFns = ancestorDecls.flatMap(_.fns)
    val hasNullable = hasNullableFnParams(fns ++ ancestorFns)
    val hasNonnull  = hasNonnullFnParams(fns ++ ancestorFns)

    val methods = fns.map(fn =>
      renderInterfaceMethod(fn, docs, isObject, handlerNames, sourceHeader, cefStructName)
    ).mkString("\n\n")

    val hasOptional = (isObject && fns.exists(JavaCodeGen.isOptionalReturn)) ||
      (!isObject && fns.exists(fn => isHandlerPtrReturn(fn.ret, handlerNames))) ||
      (isObject && ancestorFns.exists(JavaCodeGen.isOptionalReturn))

    val (staticMethodsBlock, _) = if (freeFunctions.nonEmpty) {
      renderStaticMethods(javaName, freeFunctions, docs)
    } else ("", List.empty[String])

    val staticMethodsSection = if (staticMethodsBlock.nonEmpty) s"\n$staticMethodsBlock\n" else ""

    val nativePeerSection = if (nativePeerBody.nonEmpty) s"\n$nativePeerBody\n" else ""

    // Check if free functions need additional imports
    val ffHasNullable = hasNullableFreeFunctionParams(freeFunctions)
    val ffHasNonnull  = hasNonnullFreeFunctionParams(freeFunctions)
    val ffHasOptional = freeFunctions.exists(ff => JavaCodeGen.isOptionalReturn(FnPtr("_", ff.ret, ff.params)))

    // Collect type imports from all return types and parameter types (including ancestor overrides)
    val fnTypes       = fns.flatMap(fn => fn.ret :: fn.params.map(_.typ))
    val ffTypes       = freeFunctions.flatMap(ff => ff.ret :: ff.params.map(_.typ))
    val ancestorTypes = ancestorDecls.flatMap(a => a.fns.flatMap(fn => fn.ret :: fn.params.map(_.typ)))
    val allTypes      = fnTypes ++ ffTypes ++ ancestorTypes
    val typeImports   = renderTypeImports(allTypes)

    // Cross-package imports: if this class is in a sub-package, import referenced types from other packages
    val crossPkgImports = if (subPackage.nonEmpty) {
      val cefNames  = allTypes.flatMap(Naming.referencedCefNames).distinct
      val basePkg   = Naming.javaPackage
      val thisPkg   = s"$basePkg.$subPackage"
      val fromTypes = cefNames.map(n => Naming.fullyQualifiedJavaName(n)).filter(!_.startsWith(s"$thisPkg."))
      // Import marker interface or parent interface from base/other package if needed
      val markerImports = if (isObject) {
        parentCefName match {
          case Some(pName) =>
            val parentPkg    = Naming.javaPackageFor(pName)
            val parentImport = if (parentPkg != thisPkg) List(s"$parentPkg.${Naming.structToJavaName(pName)}") else Nil
            // CefLibraryObject.requireOpen is referenced in ancestor override arg-checks
            val cloImport = if (ancestorDecls.nonEmpty) List(s"$basePkg.CefLibraryObject") else Nil
            parentImport ++ cloImport
          case None => List(s"$basePkg.CefLibraryObject")
        }
      } else List(s"$basePkg.CefClientHandler")
      val hasOpaquePtr  = allTypes.exists(_ == CType.OpaquePtr)
      val helperImports = if (hasOpaquePtr) List(s"$basePkg.NativePointer") else Nil
      (fromTypes ++ markerImports ++ helperImports).distinct.sorted.map(fqn => s"import $fqn;")
    } else Nil

    val allImports = (List(
      if (hasOptional || ffHasOptional) Some("import java.util.Optional;") else None,
      if (hasNullable || ffHasNullable) Some("import javax.annotation.Nullable;") else None,
      if (hasNonnull || ffHasNonnull) Some("import javax.annotation.Nonnull;") else None
    ).flatten ++ typeImports ++ crossPkgImports).distinct.sorted

    val extendsClause = if (isObject) {
      parentCefName.fold(" extends CefLibraryObject")(n => s" extends ${Naming.structToJavaName(n)}")
    } else " extends CefClientHandler"
    val structProto = if (cefStructName.nonEmpty) DocComments.cPrototypeForStruct(cefStructName, hasBase = true) else ""
    JavaCodeGen.renderJavaFile(
      declaration = s"public interface $javaName$extendsClause",
      body = s"$methods$staticMethodsSection$nativePeerSection",
      imports = allImports,
      classDoc = classDoc,
      capiSource = sourceHeader,
      cPrototype = structProto,
      subPackage = subPackage
    )
  }

  private def defaultReturnFromMeta(ret: CType, retVal: String)(using Naming.Context, DocComments.Context): String =
    ret match {
      case CType.Enum(cefName) =>
        val javaEnum  = Naming.structToJavaName(cefName)
        val javaConst = DocComments.resolveEnumConstant(retVal)
        s"\n        return $javaEnum.of($javaConst);"
      case CType.Bool =>
        if (retVal == "true" || retVal == "1") "\n        return true;"
        else "\n        return false;"
      case CType.Int | CType.UInt =>
        s"\n        return $retVal;"
      case CType.Long | CType.SizeT =>
        s"\n        return ${retVal}L;"
      case _ => defaultReturnForType(ret)
    }

  private def defaultReturnForType(ret: CType)(using Naming.Context): String = ret match {
    case CType.Void                          => ""
    case CType.Bool                          => "\n        return false;"
    case CType.Int | CType.UInt | CType.Char => "\n        return 0;"
    case CType.Long | CType.SizeT            => "\n        return 0L;"
    case CType.Float                         => "\n        return 0.0f;"
    case CType.Double                        => "\n        return 0.0;"
    case CType.Ptr(_)                        => "\n        return 0L;"
    case CType.OpaquePtr                     => "\n        return null;"
    case CType.CountFuncArray(elem, _, _, _) =>
      if (Naming.isPrimitiveElement(elem)) "\n        return null;"
      else "\n        return Collections.emptyList();"
    case _ => "\n        return null;"
  }

  private def collectFreeFuncNativeDecls(freeFunctions: List[CefDecl.FreeFunction])(using
      Naming.Context
  ): List[String] =
    freeFunctions.map(ff => renderNativeDecl(ff, "        static native "))

  private def renderSimpleJavadoc(text: String, capiSource: String = "", cPrototype: String = "")(using
      Naming.Context,
      DocComments.Context
  ): String = {
    val cleaned                  = text.replaceAll("""--cef\([^)]*\)--""", "").trim
    val converted                = DocComments.convertCefDoc(cleaned, capiSource, cPrototype)
    val (docText, sourceRefTags) = DocComments.extractSourceTags(converted)
    val contentLines             = docText.linesIterator.filter(_.nonEmpty).map(l => s"     * $l").toList
    val seeTags                  = sourceRefTags.map(tag => s"     * @see $tag")
    val separator                = if (contentLines.nonEmpty && seeTags.nonEmpty) List("     *") else Nil
    val allLines                 = contentLines ++ separator ++ seeTags
    if (allLines.isEmpty) ""
    else {
      s"""    /**
${allLines.mkString("\n")}
     */
"""
    }
  }

  private def renderStaticMethods(
      javaName: String,
      freeFunctions: List[CefDecl.FreeFunction],
      docs: Map[String, String],
      isClass: Boolean = false
  )(using Naming.Context, DocComments.Context): (String, List[String]) = {
    val methods = freeFunctions.map(ff => renderStaticMethod(ff, docs, isClass))

    val publicMethods   = methods.map(_._1).mkString("\n\n")
    val nativeDeclLines = methods.map(_._2).toList

    if (isClass) {
      val nativeDecls = freeFunctions.map(ff => renderNativeDecl(ff, "    private static native ")).mkString("\n\n")
      (s"$publicMethods\n\n$nativeDecls", List.empty[String])
    } else {
      (publicMethods, nativeDeclLines)
    }
  }

  private def renderGlobalsClass(
      freeFunctions: List[CefDecl.FreeFunction],
      docs: Map[String, String]
  )(using Naming.Context, DocComments.Context, Banners): String = {
    val (staticMethods, _) = renderStaticMethods("CefGlobals", freeFunctions, docs, isClass = true)

    val hasNullable = hasNullableFreeFunctionParams(freeFunctions)
    val hasNonnull  = hasNonnullFreeFunctionParams(freeFunctions)
    val hasOptional = freeFunctions.exists(ff => JavaMethods.usesOptionalReturn(ff.ret))

    val ffTypes     = freeFunctions.flatMap(ff => ff.ret :: ff.params.map(_.typ))
    val typeImports = renderTypeImports(ffTypes)

    val imports = (List(
      if (hasOptional) Some("import java.util.Optional;") else None,
      if (hasNullable) Some("import javax.annotation.Nullable;") else None,
      if (hasNonnull) Some("import javax.annotation.Nonnull;") else None
    ).flatten ++ typeImports).distinct.sorted

    JavaCodeGen.renderJavaFile(
      declaration = "public final class CefGlobals",
      body = s"    private CefGlobals() {}\n\n$staticMethods",
      imports = imports
    )
  }

  private def renderInterfaceMethod(
      fn: FnPtr,
      docs: Map[String, String],
      isObject: Boolean,
      handlerNames: Set[String],
      sourceHeader: String,
      cefStructName: String
  )(using Naming.Context, DocComments.Context): String = {
    val methodName = Naming.javaMethodName(fn)
    val retType    = methodReturnType(fn, isObject, handlerNames)
    val shape      = JavaMethods.shape(fn.ret, fn.visibleParams, fn.metaAttrs, Some(retType))
    val proto      = if (cefStructName.nonEmpty) DocComments.cPrototypeForMethod(cefStructName, fn) else ""
    val javadoc    = DocComments.forMethod(fn, docs, sourceHeader, proto, cefStructName)

    if (isObject) {
      s"$javadoc    ${shape.retType} $methodName(${shape.paramsDecl});"
    } else {
      val defaultReturn = defaultMethodReturn(fn, handlerNames)
      s"$javadoc    default ${shape.retType} $methodName(${shape.paramsDecl}) {$defaultReturn\n    }"
    }
  }

  private def methodReturnType(fn: FnPtr, isObject: Boolean, handlerNames: Set[String])(using Naming.Context): String =
    if (isObject && JavaCodeGen.isOptionalReturn(fn)) {
      s"Optional<${Naming.javaType(fn.ret)}>"
    } else if (!isObject && isHandlerPtrReturn(fn.ret, handlerNames)) {
      s"Optional<${handlerPtrJavaType(fn.ret)}>"
    } else {
      Naming.javaType(fn.ret)
    }

  private def defaultMethodReturn(fn: FnPtr, handlerNames: Set[String])(using
      Naming.Context,
      DocComments.Context
  ): String =
    if (isHandlerPtrReturn(fn.ret, handlerNames)) {
      "\n        return Optional.empty();"
    } else {
      fn.metaAttrs.collectFirst { case ("default_retval", v) => v } match {
        case Some(retVal) => defaultReturnFromMeta(fn.ret, retVal)
        case None         => defaultReturnForType(fn.ret)
      }
    }

  private def renderStaticMethod(
      ff: CefDecl.FreeFunction,
      docs: Map[String, String],
      isClass: Boolean
  )(using Naming.Context, DocComments.Context): (String, String) = {
    val methodName = ff.javaMethodName
    val nativeName = Naming.nativeMethodName(ff.javaMethodName)
    val shape      = JavaMethods.shape(ff.ret, ff.visibleParams, ff.metaAttrs)
    val callTarget = if (isClass) nativeName else s"NativePeer.$nativeName"
    val body       = JavaMethods.renderCallBody(s"$callTarget(${shape.argsExpr})", shape)

    val javadoc = DocComments.resolveFreeFunctionDoc(ff, docs)
      .map(t => renderSimpleJavadoc(t, ff.sourceHeader, DocComments.cPrototypeForFreeFunction(ff)))
      .getOrElse("")

    val visibility = if (isClass) "public static" else "static"
    (
      s"""$javadoc    $visibility ${shape.retType} $methodName(${shape.paramsDecl}) {
      $body
  }""",
      renderNativeDecl(ff, "        static native ")
    )
  }

  private def renderNativeDecl(ff: CefDecl.FreeFunction, prefix: String)(using Naming.Context): String = {
    val nativeName = Naming.nativeMethodName(ff.javaMethodName)
    val shape      = JavaMethods.shape(ff.ret, ff.visibleParams, ff.metaAttrs)
    s"$prefix${shape.nativeRetType} $nativeName(${shape.nativeParamsDecl});"
  }

  private def hasNullableFnParams(functions: IterableOnce[FnPtr]): Boolean =
    functions.iterator.exists(fn => JavaMethods.hasNullableParam(fn.params, fn.metaAttrs))

  private def hasNullableFreeFunctionParams(functions: IterableOnce[CefDecl.FreeFunction]): Boolean =
    functions.iterator.exists(fn => JavaMethods.hasNullableParam(fn.params, fn.metaAttrs))

  private def hasNonnullFnParams(functions: IterableOnce[FnPtr]): Boolean =
    functions.iterator.exists(fn => JavaMethods.hasNonnullParam(fn.params, fn.metaAttrs))

  private def hasNonnullFreeFunctionParams(functions: IterableOnce[CefDecl.FreeFunction]): Boolean =
    functions.iterator.exists(fn => JavaMethods.hasNonnullParam(fn.params, fn.metaAttrs))

  private def renderTypeImports(types: List[CType])(using Naming.Context): List[String] =
    types.flatMap(Naming.javaImports).distinct.sorted.map(i => s"import $i;")
}
