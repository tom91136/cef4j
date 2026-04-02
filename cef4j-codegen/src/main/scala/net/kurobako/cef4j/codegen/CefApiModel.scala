package net.kurobako.cef4j.codegen

enum CefDecl {
  case ObjectStruct(name: String, fns: List[FnPtr], sourceHeader: String = "", scoped: Boolean = false)
  case HandlerStruct(name: String, fns: List[FnPtr], sourceHeader: String = "")
  case DataStruct(name: String, fields: List[Field], sourceHeader: String = "", needsMutable: Boolean = false)
  case Enum(
      name: String,
      values: List[(String, Long, String)],
      doc: String = "",
      valueDocs: Map[String, String] = Map.empty
  )
  case FreeFunction(
      cName: String,
      ret: CType,
      params: List[Param],
      ownerStruct: String,
      javaMethodName: String,
      sourceHeader: String = "",
      metaAttrs: List[(String, String)] = Nil
  )
}

extension (decl: CefDecl) {
  def namedStruct: Option[String] =
    decl match {
      case CefDecl.ObjectStruct(name, _, _, _)       => Some(name)
      case CefDecl.HandlerStruct(name, _, _)         => Some(name)
      case CefDecl.DataStruct(name, _, _, _)         => Some(name)
      case CefDecl.Enum(_, _, _, _)                  => None
      case CefDecl.FreeFunction(_, _, _, _, _, _, _) => None
    }

  def withSourceHeader(sourceHeader: String): CefDecl =
    decl match {
      case d: CefDecl.ObjectStruct  => d.copy(sourceHeader = sourceHeader)
      case d: CefDecl.HandlerStruct => d.copy(sourceHeader = sourceHeader)
      case d: CefDecl.DataStruct    => d.copy(sourceHeader = sourceHeader)
      case d: CefDecl.FreeFunction  => d.copy(sourceHeader = sourceHeader)
      case other                    => other
    }

  def mapFns(f: FnPtr => FnPtr): CefDecl =
    decl match {
      case d: CefDecl.ObjectStruct  => d.copy(fns = d.fns.map(f))
      case d: CefDecl.HandlerStruct => d.copy(fns = d.fns.map(f))
      case other                    => other
    }

  def isCppGenerated: Boolean =
    decl match {
      case _: CefDecl.ObjectStruct | _: CefDecl.HandlerStruct | _: CefDecl.FreeFunction => true
      case _                                                                            => false
    }
}

private def isVisibleParam(param: Param): Boolean =
  param.typ match {
    case CType.BufferSize(_) => false
    case _                   => true
  }

extension (params: List[Param]) {
  def visible: List[Param] = params.filter(isVisibleParam)
}

extension (fn: FnPtr) {
  def visibleParams: List[Param] = fn.params.visible
}

extension (ff: CefDecl.FreeFunction) {
  def visibleParams: List[Param] = ff.params.visible
}

case class FnPtr(
    name: String,
    ret: CType,
    params: List[Param],
    isSpecial: Option[SpecialFn] = None,
    metaAttrs: List[(String, String)] = Nil,
    cppName: Option[String] = None
)

enum SpecialFn {
  case OnPaint
  case OnAcceleratedPaint
}

case class Param(name: String, typ: CType, isConst: Boolean = false, rawCType: String = "")
case class Field(name: String, typ: CType, doc: String = "")

enum CType {
  case Void
  case Bool
  case Int
  case UInt
  case Long
  case SizeT
  case Char
  case Float
  case Double
  case JString
  case Ptr(inner: String)
  case ObjectPtr(cefName: String)
  case OutObjectPtr(cefName: String)
  case OutPrimitivePtr(primitiveType: CType)
  case OpaquePtr
  case ObjectPtrArray(cefName: String)
  case OutInt
  case OutBool
  case ByValueIn(cefName: String)
  case ByValueOut(cefName: String)
  case ByValueArray(cefName: String)
  case PixelBuffer
  case Buffer(sizeParam: String)
  case BufferSize(bufferParam: String)
  case Enum(cefName: String)
  case DataStruct(cefName: String)
  case StringList
  case StringMap
  case StringMultimap
  case CountFuncArray(elementType: CType, countFuncCName: String, countParamName: String, arrayParamName: String)
}
