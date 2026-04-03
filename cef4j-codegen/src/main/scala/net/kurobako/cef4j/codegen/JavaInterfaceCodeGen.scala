package net.kurobako.cef4j.codegen

import java.nio.file.Path

object JavaInterfaceCodeGen {

  def emitObject(
      decl: CefDecl.ObjectStruct,
      outDir: Path,
      docs: Map[String, String] = Map.empty,
      classDoc: String = "",
      handlerNames: Set[String] = Set.empty,
      freeFunctions: List[CefDecl.FreeFunction] = Nil
  )(using Naming.Context, DocComments.Context): Unit = {
    val javaName       = Naming.structToJavaName(decl.name)
    val ffNativeDecls  = collectFreeFuncNativeDecls(freeFunctions)
    val nativePeerBody = JavaNativeCodeGen.renderInnerClass(decl, ffNativeDecls, handlerNames)
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
        cefStructName = decl.name
      )
    JavaCodeGen.writeJavaFile(outDir, javaName, content)
  }

  def emitGlobals(
      freeFunctions: List[CefDecl.FreeFunction],
      outDir: Path,
      docs: Map[String, String] = Map.empty
  )(using Naming.Context, DocComments.Context): Unit = {
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
  )(using Naming.Context, DocComments.Context): Unit = {
    val javaName = Naming.structToJavaName(decl.name)
    val content  =
      renderInterface(
        javaName,
        decl.fns,
        docs,
        isObject = false,
        classDoc = classDoc,
        handlerNames = handlerNames,
        sourceHeader = decl.sourceHeader,
        cefStructName = decl.name
      )
    JavaCodeGen.writeJavaFile(outDir, javaName, content)
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
      cefStructName: String = ""
  )(using Naming.Context, DocComments.Context): String = {
    val hasNullable = fns.exists { fn =>
      JavaMethods.hasNullableParam(fn.params, fn.metaAttrs)
    }
    val hasNonnull = fns.exists { fn =>
      JavaMethods.hasNonnullParam(fn.params, fn.metaAttrs)
    }

    val methods = fns.map { fn =>
      val methodName = Naming.javaMethodName(fn)

      val retType = if (isObject && JavaCodeGen.isOptionalReturn(fn)) {
        s"Optional<${Naming.javaType(fn.ret)}>"
      } else if (!isObject && isHandlerPtrReturn(fn.ret, handlerNames)) {
        s"Optional<${handlerPtrJavaType(fn.ret)}>"
      } else {
        Naming.javaType(fn.ret)
      }
      val shape = JavaMethods.shape(fn.ret, fn.visibleParams, fn.metaAttrs, Some(retType))

      val proto   = if (cefStructName.nonEmpty) DocComments.cPrototypeForMethod(cefStructName, fn) else ""
      val javadoc = DocComments.forMethod(fn, docs, sourceHeader, proto, cefStructName)

      // Object interfaces: abstract methods. Handler interfaces: default methods with stub bodies.
      if (isObject) {
        s"$javadoc    ${shape.retType} $methodName(${shape.paramsDecl});"
      } else {
        val defaultReturn = if (isHandlerPtrReturn(fn.ret, handlerNames)) {
          "\n        return Optional.empty();"
        } else {
          fn.metaAttrs.collectFirst { case ("default_retval", v) => v } match {
            case Some(retVal) => defaultReturnFromMeta(fn.ret, retVal)
            case None         => defaultReturnForType(fn.ret)
          }
        }
        s"$javadoc    default ${shape.retType} $methodName(${shape.paramsDecl}) {$defaultReturn\n    }"
      }
    }.mkString("\n\n")

    val hasOptional = (isObject && fns.exists(JavaCodeGen.isOptionalReturn)) ||
      (!isObject && fns.exists(fn => isHandlerPtrReturn(fn.ret, handlerNames)))

    val (staticMethodsBlock, _) = if (freeFunctions.nonEmpty) {
      renderStaticMethods(javaName, freeFunctions, docs)
    } else ("", List.empty[String])

    val staticMethodsSection = if (staticMethodsBlock.nonEmpty) s"\n$staticMethodsBlock\n" else ""

    val nativePeerSection = if (nativePeerBody.nonEmpty) s"\n$nativePeerBody\n" else ""

    // Check if free functions need additional imports
    val ffHasNullable = freeFunctions.exists { ff =>
      JavaMethods.hasNullableParam(ff.params, ff.metaAttrs)
    }
    val ffHasNonnull = freeFunctions.exists { ff =>
      JavaMethods.hasNonnullParam(ff.params, ff.metaAttrs)
    }
    val ffHasOptional = freeFunctions.exists(ff => JavaCodeGen.isOptionalReturn(FnPtr("_", ff.ret, ff.params)))

    // Collect type imports from all return types and parameter types
    val fnTypes     = fns.flatMap(fn => fn.ret :: fn.params.map(_.typ))
    val ffTypes     = freeFunctions.flatMap(ff => ff.ret :: ff.params.map(_.typ))
    val typeImports = (fnTypes ++ ffTypes).flatMap(Naming.javaImports).distinct.sorted.map(i => s"import $i;")

    val allImports = (List(
      if (hasOptional || ffHasOptional) Some("import java.util.Optional;") else None,
      if (hasNullable || ffHasNullable) Some("import javax.annotation.Nullable;") else None,
      if (hasNonnull || ffHasNonnull) Some("import javax.annotation.Nonnull;") else None
    ).flatten ++ typeImports).distinct.sorted

    val extendsClause = if (isObject) " extends CefLibraryObject" else " extends CefClientHandler"
    val structProto = if (cefStructName.nonEmpty) DocComments.cPrototypeForStruct(cefStructName, hasBase = true) else ""
    JavaCodeGen.renderJavaFile(
      declaration = s"public interface $javaName$extendsClause",
      body = s"$methods$staticMethodsSection$nativePeerSection",
      imports = allImports,
      classDoc = classDoc,
      capiSource = sourceHeader,
      cPrototype = structProto
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
    freeFunctions.map { ff =>
      val nativeName = Naming.nativeMethodName(ff.javaMethodName)
      val shape      = JavaMethods.shape(ff.ret, ff.visibleParams, ff.metaAttrs)
      s"        static native ${shape.nativeRetType} $nativeName(${shape.nativeParamsDecl});"
    }

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
    // For interfaces, native methods go in a nested _N class since interfaces can't have native methods.
    // For classes (isClass=true), native methods are private static on the class itself.
    val methods = freeFunctions.map { ff =>
      val methodName = ff.javaMethodName
      val nativeName = Naming.nativeMethodName(ff.javaMethodName)
      val shape      = JavaMethods.shape(ff.ret, ff.visibleParams, ff.metaAttrs)
      val callTarget = if (isClass) nativeName else s"NativePeer.$nativeName"
      val body       = JavaMethods.renderCallBody(s"$callTarget(${shape.argsExpr})", shape)

      val pascal  = Naming.toPascalCase(ff.javaMethodName)
      val javadoc = docs.get(s"Cef$pascal") // C++ free functions use CefXxx naming
        .orElse(docs.get(pascal))
        .orElse(docs.get(ff.javaMethodName))
        .orElse(docs.get(ff.cName))
        .map(t => renderSimpleJavadoc(t, ff.sourceHeader, DocComments.cPrototypeForFreeFunction(ff)))
        .getOrElse("")

      val visibility = if (isClass) "public static" else "static"
      (
        s"""$javadoc    $visibility ${shape.retType} $methodName(${shape.paramsDecl}) {
        $body
    }""",
        s"        static native ${shape.nativeRetType} $nativeName(${shape.nativeParamsDecl});"
      )
    }

    val publicMethods   = methods.map(_._1).mkString("\n\n")
    val nativeDeclLines = methods.map(_._2).toList

    if (isClass) {
      // For classes, emit native methods as private static native directly
      val nativeDecls = freeFunctions.map { ff =>
        val nativeName = Naming.nativeMethodName(ff.javaMethodName)
        val shape      = JavaMethods.shape(ff.ret, ff.visibleParams, ff.metaAttrs)
        s"    private static native ${shape.nativeRetType} $nativeName(${shape.nativeParamsDecl});"
      }.mkString("\n\n")
      (s"$publicMethods\n\n$nativeDecls", List.empty[String])
    } else {
      // For interfaces, native decls go into NativePeer
      (publicMethods, nativeDeclLines)
    }
  }

  private def renderGlobalsClass(
      freeFunctions: List[CefDecl.FreeFunction],
      docs: Map[String, String]
  )(using Naming.Context, DocComments.Context): String = {
    val (staticMethods, _) = renderStaticMethods("CefGlobals", freeFunctions, docs, isClass = true)

    val hasNullable = freeFunctions.exists { ff =>
      JavaMethods.hasNullableParam(ff.params, ff.metaAttrs)
    }
    val hasNonnull = freeFunctions.exists { ff =>
      JavaMethods.hasNonnullParam(ff.params, ff.metaAttrs)
    }
    val hasOptional = freeFunctions.exists { ff =>
      JavaMethods.usesOptionalReturn(ff.ret)
    }

    val ffTypes     = freeFunctions.flatMap(ff => ff.ret :: ff.params.map(_.typ))
    val typeImports = ffTypes.flatMap(Naming.javaImports).distinct.sorted.map(i => s"import $i;")

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
}
