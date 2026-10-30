package net.kurobako.cef4j.codegen

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
  case ConstCStringArray
  case CStringArray
  case Ptr(inner: String)
  case ObjectPtr(cefName: String)
  case OutObjectPtr(cefName: String)
  case OutOpaquePtr
  case OutPrimitivePtr(primitiveType: CType)
  case OpaquePtr
  case ConstDataStructPtr(cefName: String)
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
