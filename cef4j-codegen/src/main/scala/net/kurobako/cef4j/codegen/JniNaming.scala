package net.kurobako.cef4j.codegen

object JniNaming {
  def jniName(cefName: String)(using Naming.Context): String =
    Naming.javaInternalName(Naming.fullyQualifiedJavaNameForJniLookup(cefName))

  def jniMutableName(cefName: String)(using Naming.Context): String =
    Naming.javaInternalName(Naming.fullyQualifiedMutableNameForJniLookup(cefName))

  def isHandlerPtr(ct: CType, handlerNames: Set[String]): Boolean =
    Naming.isHandlerPtrReturn(ct, handlerNames)

  def addRefExpr(ptr: String): String =
    s"{ auto* _b = reinterpret_cast<cef_base_ref_counted_t*>($ptr); _b->add_ref(_b); }"

  case class OutPrimInfo(cType: String, jniType: String, javaInfix: String)

  def outPrimInfo(inner: CType): OutPrimInfo = inner match {
    case CType.Long | CType.SizeT => OutPrimInfo(Naming.cType(inner), "jlong", "Long")
    case CType.Float              => OutPrimInfo("float", "jfloat", "Float")
    case CType.Double             => OutPrimInfo("double", "jdouble", "Double")
    case _                        => OutPrimInfo(Naming.cType(inner), "jint", "Int")
  }
}
