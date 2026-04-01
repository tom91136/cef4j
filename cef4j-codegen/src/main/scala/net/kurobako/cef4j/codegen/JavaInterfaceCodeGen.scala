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
  ): Unit = {
    val javaName       = Naming.structToJavaName(decl.name)
    val ffNativeDecls  = collectFreeFuncNativeDecls(freeFunctions)
    val nativePeerBody = JavaNativeCodeGen.renderInnerClass(decl, ffNativeDecls)
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

  /** Emit CefGlobals utility class for orphan free functions. */
  def emitGlobals(
      freeFunctions: List[CefDecl.FreeFunction],
      outDir: Path,
      docs: Map[String, String] = Map.empty
  ): Unit = {
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
  ): Unit = {
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

  private def handlerPtrJavaType(ret: CType): String = ret match {
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
  ): String = {
    val hasNullable = fns.exists { fn =>
      collectOptionalParams(fn).exists(pName =>
        fn.params.exists(p => p.name == pName && JavaCodeGen.isReferenceType(p.typ))
      )
    }
    val hasNonnull = fns.exists { fn =>
      val optionals = collectOptionalParams(fn)
      fn.params.exists(p => !optionals.contains(p.name) && JavaCodeGen.isReferenceType(p.typ))
    }

    val methods = fns.map { fn =>
      val methodName = Naming.javaMethodName(fn)
      val optionals  = collectOptionalParams(fn)

      val retType = if (isObject && JavaCodeGen.isOptionalReturn(fn)) {
        s"Optional<${Naming.javaType(fn.ret)}>"
      } else if (!isObject && isHandlerPtrReturn(fn.ret, handlerNames)) {
        s"Optional<${handlerPtrJavaType(fn.ret)}>"
      } else {
        Naming.javaType(fn.ret)
      }

      val params = fn.params.filterNot(_.typ.isInstanceOf[CType.BufferSize]).map { p =>
        val jType = Naming.javaType(p.typ)
        val pName = Naming.toCamelCase(p.name)
        if (optionals.contains(p.name) && JavaCodeGen.isReferenceType(p.typ))
          s"@Nullable $jType $pName"
        else if (JavaCodeGen.isReferenceType(p.typ))
          s"@Nonnull $jType $pName"
        else
          s"$jType $pName"
      }.mkString(", ")

      val proto   = if (cefStructName.nonEmpty) DocComments.cPrototypeForMethod(cefStructName, fn) else ""
      val javadoc = DocComments.forMethod(fn, docs, sourceHeader, proto)

      // Object interfaces: abstract methods. Handler interfaces: default methods with stub bodies.
      if (isObject) {
        s"$javadoc    $retType $methodName($params);"
      } else {
        val defaultReturn = if (isHandlerPtrReturn(fn.ret, handlerNames)) {
          "\n        return Optional.empty();"
        } else {
          fn.metaAttrs.collectFirst { case ("default_retval", v) => v } match {
            case Some(retVal) => defaultReturnFromMeta(fn.ret, retVal)
            case None         => defaultReturnForType(fn.ret)
          }
        }
        s"$javadoc    default $retType $methodName($params) {$defaultReturn\n    }"
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
      val optionals = ff.metaAttrs.collect { case ("optional_param", p) => p }.toSet
      optionals.exists(pName => ff.params.exists(p => p.name == pName && JavaCodeGen.isReferenceType(p.typ)))
    }
    val ffHasNonnull = freeFunctions.exists { ff =>
      val optionals = ff.metaAttrs.collect { case ("optional_param", p) => p }.toSet
      ff.params.exists(p => !optionals.contains(p.name) && JavaCodeGen.isReferenceType(p.typ))
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

  /** Collect all parameter names marked as optional from metacomment attributes. */
  private def collectOptionalParams(fn: FnPtr): Set[String] =
    fn.metaAttrs.collect { case ("optional_param", p) => p }.toSet

  /** Default return when --cef(default_retval=X)-- is present. */
  private def defaultReturnFromMeta(ret: CType, retVal: String): String = ret match {
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

  private def defaultReturnForType(ret: CType): String = ret match {
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

  /** Collect native method declarations for free functions, to embed in NativePeer. */
  private def collectFreeFuncNativeDecls(freeFunctions: List[CefDecl.FreeFunction]): List[String] =
    freeFunctions.map { ff =>
      val nativeName   = s"N_${Naming.capitalise(ff.javaMethodName)}"
      val rawRetType   = Naming.jniNativeReturnType(ff.ret)
      val nativeParams = ff.params.filterNot(_.typ.isInstanceOf[CType.BufferSize]).map { p =>
        s"${Naming.javaType(p.typ)} ${Naming.toCamelCase(p.name)}"
      }.mkString(", ")
      s"        static native $rawRetType $nativeName($nativeParams);"
    }

  /** Render a simple javadoc from raw doc text, stripping metacomments. */
  private def renderSimpleJavadoc(text: String, capiSource: String = "", cPrototype: String = ""): String = {
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

  /** Render static methods (from free functions) for embedding in an interface or class. For interfaces, native methods
    * go in a nested _N class since interfaces can't have native methods. For classes (isClass=true), native methods are
    * private static on the class itself.
    */
  private def renderStaticMethods(
      javaName: String,
      freeFunctions: List[CefDecl.FreeFunction],
      docs: Map[String, String],
      isClass: Boolean = false
  ): (String, List[String]) = {
    val methods = freeFunctions.map { ff =>
      val methodName = ff.javaMethodName
      val nativeName = s"N_${Naming.capitalise(ff.javaMethodName)}"
      val optionals  = ff.metaAttrs.collect { case ("optional_param", p) => p }.toSet

      val useOptional = ff.ret match {
        case CType.JString | CType.ObjectPtr(_) => true
        case _                                  => false
      }
      val rawRetType = Naming.javaType(ff.ret)
      val retType    = if (useOptional) s"Optional<$rawRetType>" else rawRetType

      val visibleParams = ff.params.filterNot(_.typ.isInstanceOf[CType.BufferSize])
      val params        = visibleParams.map { p =>
        val jType = Naming.javaType(p.typ)
        val pName = Naming.toCamelCase(p.name)
        if (optionals.contains(p.name) && JavaCodeGen.isReferenceType(p.typ))
          s"@Nullable $jType $pName"
        else if (JavaCodeGen.isReferenceType(p.typ))
          s"@Nonnull $jType $pName"
        else
          s"$jType $pName"
      }.mkString(", ")

      val args            = visibleParams.map(p => Naming.toCamelCase(p.name)).mkString(", ")
      val useArraysAsList = ff.ret match {
        case CType.CountFuncArray(elem, _, _, _) => !Naming.isPrimitiveElement(elem)
        case _                                   => false
      }
      val callTarget = if (isClass) nativeName else s"NativePeer.$nativeName"
      val body       = if (retType == "void") {
        s"$callTarget($args);"
      } else if (useOptional) {
        s"return Optional.ofNullable($callTarget($args));"
      } else if (useArraysAsList) {
        s"return Arrays.asList($callTarget($args));"
      } else {
        s"return $callTarget($args);"
      }

      val nativeRetType = Naming.jniNativeReturnType(ff.ret)
      val nativeParams  = visibleParams.map { p =>
        s"${Naming.javaType(p.typ)} ${Naming.toCamelCase(p.name)}"
      }.mkString(", ")

      val javadoc = docs.get(Naming.toPascalCase(ff.javaMethodName))
        .orElse(docs.get(ff.javaMethodName))
        .map(t => renderSimpleJavadoc(t, ff.sourceHeader, DocComments.cPrototypeForFreeFunction(ff)))
        .getOrElse("")

      val visibility = if (isClass) "public static" else "static"
      (
        s"""$javadoc    $visibility $retType $methodName($params) {
        $body
    }""",
        s"        static native $nativeRetType $nativeName($nativeParams);"
      )
    }

    val publicMethods   = methods.map(_._1).mkString("\n\n")
    val nativeDeclLines = methods.map(_._2).toList

    if (isClass) {
      // For classes, emit native methods as private static native directly
      val nativeDecls = freeFunctions.map { ff =>
        val nativeName   = s"N_${Naming.capitalise(ff.javaMethodName)}"
        val nativeRetTy  = Naming.jniNativeReturnType(ff.ret)
        val nativeParams = ff.params.filterNot(_.typ.isInstanceOf[CType.BufferSize]).map { p =>
          s"${Naming.javaType(p.typ)} ${Naming.toCamelCase(p.name)}"
        }.mkString(", ")
        s"    private static native $nativeRetTy $nativeName($nativeParams);"
      }.mkString("\n\n")
      (s"$publicMethods\n\n$nativeDecls", List.empty[String])
    } else {
      // For interfaces, native decls go into NativePeer
      (publicMethods, nativeDeclLines)
    }
  }

  /** Render the CefGlobals utility class for orphan free functions. */
  private def renderGlobalsClass(
      freeFunctions: List[CefDecl.FreeFunction],
      docs: Map[String, String]
  ): String = {
    val (staticMethods, _) = renderStaticMethods("CefGlobals", freeFunctions, docs, isClass = true)

    val hasNullable = freeFunctions.exists { ff =>
      val optionals = ff.metaAttrs.collect { case ("optional_param", p) => p }.toSet
      optionals.exists(pName => ff.params.exists(p => p.name == pName && JavaCodeGen.isReferenceType(p.typ)))
    }
    val hasNonnull = freeFunctions.exists { ff =>
      val optionals = ff.metaAttrs.collect { case ("optional_param", p) => p }.toSet
      ff.params.exists(p => !optionals.contains(p.name) && JavaCodeGen.isReferenceType(p.typ))
    }
    val hasOptional = freeFunctions.exists { ff =>
      ff.ret match {
        case CType.JString | CType.ObjectPtr(_) => true
        case _                                  => false
      }
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
