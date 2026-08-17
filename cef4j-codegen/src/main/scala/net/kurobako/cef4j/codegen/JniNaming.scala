package net.kurobako.cef4j.codegen

object JniNaming {
  def jniName(cefName: String)(using Naming.Context): String =
    Naming.javaInternalName(Naming.fullyQualifiedJavaNameForJniLookup(cefName))

  def jniMutableName(cefName: String)(using Naming.Context): String =
    Naming.javaInternalName(Naming.fullyQualifiedMutableNameForJniLookup(cefName))

  def isHandlerPtr(ct: CType, handlerNames: Set[String]): Boolean = ct match {
    case CType.ObjectPtr(name) => handlerNames.contains(name)
    case CType.Ptr(inner)      => handlerNames.contains(inner.stripPrefix("_"))
    case _                     => false
  }

  def addRefExpr(ptr: String): String =
    s"{ auto* _b = reinterpret_cast<cef_base_ref_counted_t*>($ptr); _b->add_ref(_b); }"

  def outPrimInfo(inner: CType): (String, String, String) = inner match {
    case CType.Long | CType.SizeT => (Naming.cType(inner), "jlong", "Long")
    case CType.Float              => ("float", "jfloat", "Float")
    case CType.Double             => ("double", "jdouble", "Double")
    case _                        => (Naming.cType(inner), "jint", "Int")
  }
}
