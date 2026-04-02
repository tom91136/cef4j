package net.kurobako.cef4j.codegen

object JavaMethods {
  case class MethodShape(
      retType: String,
      paramsDecl: String,
      argsExpr: String,
      nativeRetType: String,
      nativeParamsDecl: String,
      usesOptional: Boolean,
      usesArraysAsList: Boolean
  )

  def optionalParams(metaAttrs: List[(String, String)]): Set[String] =
    metaAttrs.collect { case ("optional_param", paramName) => paramName }.toSet

  def usesOptionalReturn(ret: CType): Boolean =
    ret match {
      case CType.JString | CType.ObjectPtr(_) => true
      case _                                  => false
    }

  def usesArraysAsList(ret: CType)(using Naming.Context): Boolean =
    ret match {
      case CType.CountFuncArray(elem, _, _, _) => !Naming.isPrimitiveElement(elem)
      case _                                   => false
    }

  def hasNullableParam(params: List[Param], metaAttrs: List[(String, String)]): Boolean = {
    val optionals = optionalParams(metaAttrs)
    // @Nullable is used for: explicitly optional params, AND non-strict reference types
    // (JString, ObjectPtr, OpaquePtr, etc.) where CEF may accept NULL.
    params.exists { p =>
      val isRef = JavaCodeGen.isReferenceType(p.typ)
      (optionals.contains(p.name) && isRef) || (isRef && !JavaCodeGen.isStrictNullCheck(p.typ))
    }
  }

  def hasNonnullParam(params: List[Param], metaAttrs: List[(String, String)]): Boolean = {
    val optionals = optionalParams(metaAttrs)
    params.exists(p => !optionals.contains(p.name) && JavaCodeGen.isStrictNullCheck(p.typ))
  }

  def shape(
      ret: CType,
      visibleParams: List[Param],
      metaAttrs: List[(String, String)],
      retTypeOverride: Option[String] = None
  )(using Naming.Context): MethodShape = {
    val optionals     = optionalParams(metaAttrs)
    val rawRetType    = Naming.javaType(ret)
    val usesOptional  = usesOptionalReturn(ret)
    val usesList      = usesArraysAsList(ret)
    val effectiveRet  = retTypeOverride.getOrElse(if (usesOptional) s"Optional<$rawRetType>" else rawRetType)
    val paramsDecl    = renderJavaParams(visibleParams, optionals)
    val argsExpr      = visibleParams.map(p => Naming.toCamelCase(p.name)).mkString(", ")
    val nativeRetType = Naming.jniNativeReturnType(ret)
    val nativeParams  = renderNativeParams(visibleParams)

    MethodShape(
      retType = effectiveRet,
      paramsDecl = paramsDecl,
      argsExpr = argsExpr,
      nativeRetType = nativeRetType,
      nativeParamsDecl = nativeParams,
      usesOptional = usesOptional,
      usesArraysAsList = usesList
    )
  }

  def renderCallBody(callExpr: String, shape: MethodShape): String =
    if (shape.retType == "void") {
      s"$callExpr;"
    } else if (shape.usesOptional) {
      s"return Optional.ofNullable($callExpr);"
    } else if (shape.usesArraysAsList) {
      s"return Arrays.asList($callExpr);"
    } else {
      s"return $callExpr;"
    }

  private def renderJavaParams(visibleParams: List[Param], optionals: Set[String])(using Naming.Context): String =
    visibleParams.map { param =>
      val javaType  = Naming.javaType(param.typ)
      val paramName = Naming.toCamelCase(param.name)
      if (optionals.contains(param.name) && JavaCodeGen.isReferenceType(param.typ))
        s"@Nullable $javaType $paramName"
      else if (JavaCodeGen.isStrictNullCheck(param.typ))
        s"@Nonnull $javaType $paramName"
      else if (JavaCodeGen.isReferenceType(param.typ))
        s"@Nullable $javaType $paramName"
      else
        s"$javaType $paramName"
    }.mkString(", ")

  private def renderNativeParams(visibleParams: List[Param])(using Naming.Context): String =
    visibleParams.map { param =>
      s"${Naming.javaType(param.typ)} ${Naming.toCamelCase(param.name)}"
    }.mkString(", ")
}
