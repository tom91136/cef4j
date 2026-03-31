package net.kurobako.cef4j.codegen

enum CefDecl {
  case ObjectStruct(name: String, fns: List[FnPtr], sourceHeader: String = "")
  case HandlerStruct(name: String, fns: List[FnPtr], sourceHeader: String = "")
  case DataStruct(name: String, fields: List[Field], sourceHeader: String = "")
  case Enum(name: String, values: List[(String, Long)], doc: String = "", valueDocs: Map[String, String] = Map.empty)
}

extension (decl: CefDecl) {
  def namedStruct: Option[String] =
    decl match {
      case CefDecl.ObjectStruct(name, _, _)  => Some(name)
      case CefDecl.HandlerStruct(name, _, _) => Some(name)
      case CefDecl.DataStruct(name, _, _)    => Some(name)
      case CefDecl.Enum(_, _, _, _)          => None
    }

  def withSourceHeader(sourceHeader: String): CefDecl =
    decl match {
      case d: CefDecl.ObjectStruct  => d.copy(sourceHeader = sourceHeader)
      case d: CefDecl.HandlerStruct => d.copy(sourceHeader = sourceHeader)
      case d: CefDecl.DataStruct    => d.copy(sourceHeader = sourceHeader)
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
      case _: CefDecl.ObjectStruct | _: CefDecl.HandlerStruct => true
      case _                                                  => false
    }
}

case class FnPtr(
    name: String,
    ret: CType,
    params: List[Param],
    isSpecial: Option[SpecialFn] = None,
    metaAttrs: List[(String, String)] = Nil
)

enum SpecialFn {
  case OnPaint
  case OnAcceleratedPaint
}

case class Param(name: String, typ: CType, isConst: Boolean = false, rawCType: String = "")
case class Field(name: String, typ: CType)

enum CType {
  case Void
  case Bool
  case Int
  case UInt
  case Long
  case SizeT
  case Float
  case Double
  case JString
  case Ptr(inner: String)
  case OutInt
  case OutBool
  case ByValueIn(cefName: String)
  case ByValueOut(cefName: String)
  case ByValueArray(cefName: String)
  case PixelBuffer
  case Enum(cefName: String)
  case DataStruct(cefName: String)
  case StringList
  case StringMap
  case StringMultimap
}
