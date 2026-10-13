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

  def usesArraysAsList(ret: CType): Boolean =
    ret match {
      case CType.CountFuncArray(elem, _, _, _) => !Naming.isPrimitiveElement(elem)
      case _                                   => false
    }

  def hasNullableParam(params: List[Param], metaAttrs: List[(String, String)]): Boolean = {
    val optionals = optionalParams(metaAttrs)
    // CEF accepts null for non-strict reference types even without optional metadata.
    params.exists { p =>
      val isRef = JavaCodeGen.isReferenceType(p.typ)
      (optionals.contains(p.name) && isRef) || (isRef && !JavaCodeGen.isStrictNullCheck(p.typ))
    }
  }

  def shape(
      ret: CType,
      visibleParams: List[Param],
      metaAttrs: List[(String, String)],
      retTypeOverride: Option[String] = None,
      outCollection: Option[OutCollection] = None
  )(using Naming.Context): MethodShape = {
    val optionals     = optionalParams(metaAttrs)
    val outParam      = outCollection.flatMap(_ => visibleParams.find(p => OutCollection.of(p.typ).isDefined))
    val params        = outParam.fold(visibleParams)(p => visibleParams.filterNot(_ eq p))
    val rawRetType    = outCollection.fold(Naming.javaType(ret))(_.javaType)
    val usesOptional  = outCollection.fold(usesOptionalReturn(ret))(_ => ret == CType.Bool)
    val usesList      = usesArraysAsList(ret)
    val effectiveRet  = retTypeOverride.getOrElse(if (usesOptional) s"Optional<$rawRetType>" else rawRetType)
    val paramsDecl    = params.map(p => renderParam(p, optionals)).mkString(", ")
    val argsExpr      = params.map(p => Naming.toCamelCase(p.name)).mkString(", ")
    val nativeRetType = if (outCollection.isDefined) rawRetType else Naming.jniNativeReturnType(ret)

    // Native stubs preserve facade nullness for NullAway.
    MethodShape(
      retType = effectiveRet,
      paramsDecl = paramsDecl,
      argsExpr = argsExpr,
      nativeRetType = nativeRetType,
      nativeParamsDecl = paramsDecl,
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

  private def isNullableParam(param: Param, optionals: Set[String]): Boolean =
    JavaCodeGen.isReferenceType(param.typ)
      && (optionals.contains(param.name) || !JavaCodeGen.isStrictNullCheck(param.typ))

  private def renderParam(param: Param, optionals: Set[String])(using Naming.Context): String = {
    val javaType  = Naming.javaType(param.typ)
    val paramName = Naming.toCamelCase(param.name)
    val declared  = if (isNullableParam(param, optionals)) Naming.nullableType(javaType) else javaType
    s"$declared $paramName"
  }
}
