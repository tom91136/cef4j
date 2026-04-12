package net.kurobako.cef4j.codegen

import scala.annotation.tailrec

object PlatformSpecificity {

  def isPlatformSpecificDecl(decl: CefDecl, platformTypes: Set[String]): Boolean =
    decl match {
      case d: CefDecl.ObjectStruct =>
        d.fns.exists(fnUsesPlatformType(_, platformTypes))
      case d: CefDecl.HandlerStruct =>
        d.fns.exists(fnUsesPlatformType(_, platformTypes))
      case d: CefDecl.DataStruct =>
        platformTypes.contains(d.name) || d.fields.exists(f => typeUsesPlatformType(f.typ, platformTypes))
      case d: CefDecl.Enum =>
        platformTypes.contains(d.name)
      case ff: CefDecl.FreeFunction =>
        isPlatformSpecificFreeFunction(ff, platformTypes)
    }

  def isPlatformSpecificFreeFunction(ff: CefDecl.FreeFunction, platformTypes: Set[String]): Boolean =
    typeUsesPlatformType(ff.ret, platformTypes) || ff.params.exists(p => typeUsesPlatformType(p.typ, platformTypes))

  private def fnUsesPlatformType(fn: FnPtr, platformTypes: Set[String]): Boolean =
    typeUsesPlatformType(fn.ret, platformTypes) || fn.params.exists(p => typeUsesPlatformType(p.typ, platformTypes))

  private def normalizeName(value: String): String =
    value.stripPrefix("_")

  @tailrec
  def typeUsesPlatformType(ct: CType, platformTypes: Set[String]): Boolean =
    ct match {
      case CType.Ptr(inner)                    => platformTypes.contains(normalizeName(inner))
      case CType.ObjectPtr(cefName)            => platformTypes.contains(cefName)
      case CType.OutObjectPtr(cefName)         => platformTypes.contains(cefName)
      case CType.ObjectPtrArray(cefName)       => platformTypes.contains(cefName)
      case CType.ConstDataStructPtr(cefName)   => platformTypes.contains(cefName)
      case CType.ByValueIn(cefName)            => platformTypes.contains(cefName)
      case CType.ByValueOut(cefName)           => platformTypes.contains(cefName)
      case CType.ByValueArray(cefName)         => platformTypes.contains(cefName)
      case CType.Enum(cefName)                 => platformTypes.contains(cefName)
      case CType.DataStruct(cefName)           => platformTypes.contains(cefName)
      case CType.OutPrimitivePtr(inner)        => typeUsesPlatformType(inner, platformTypes)
      case CType.CountFuncArray(elem, _, _, _) =>
        typeUsesPlatformType(elem, platformTypes)
      case _ => false
    }
}
