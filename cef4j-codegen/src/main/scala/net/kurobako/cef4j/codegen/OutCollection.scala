package net.kurobako.cef4j.codegen

/** A CEF string collection that the callee fills. Generated APIs expose these as return values rather than mutable
  * out-parameters, which keeps direct calls safe from Kotlin and Scala (a read-only collection cannot be silently
  * passed to a method that writes into it).
  */
enum OutCollection {
  case StringList
  case StringMap
  case StringMultimap

  def javaType: String = this match {
    case StringList     => "List<String>"
    case StringMap      => "Map<String, String>"
    case StringMultimap => "Map<String, List<String>>"
  }

  def docNoun: String = this match {
    case StringList     => "list"
    case StringMap      => "map"
    case StringMultimap => "header map"
  }

  def cType: String = this match {
    case StringList     => "cef_string_list_t"
    case StringMap      => "cef_string_map_t"
    case StringMultimap => "cef_string_multimap_t"
  }

  def cAllocFn: String = this match {
    case StringList     => "cef_string_list_alloc"
    case StringMap      => "cef_string_map_alloc"
    case StringMultimap => "cef_string_multimap_alloc"
  }

  def cFreeFn: String = this match {
    case StringList     => "cef_string_list_free"
    case StringMap      => "cef_string_map_free"
    case StringMultimap => "cef_string_multimap_free"
  }

  def toJavaFn: String = this match {
    case StringList     => "CefStringListToJavaList"
    case StringMap      => "CefStringMapToJavaMap"
    case StringMultimap => "CefStringMultimapToJavaMap"
  }
}

object OutCollection {
  def of(ct: CType): Option[OutCollection] = ct match {
    case CType.StringList     => Some(OutCollection.StringList)
    case CType.StringMap      => Some(OutCollection.StringMap)
    case CType.StringMultimap => Some(OutCollection.StringMultimap)
    case _                    => None
  }

  /** The collection parameter that returns its values for a {@code get_*} method, if any. */
  def forMethod(fn: FnPtr): Option[(Param, OutCollection)] =
    if (retIsFillable(fn.ret) && fn.name.startsWith("get_")) single(fn.visibleParams) else None

  /** The collection parameter that returns its values for a {@code cef_*_get_*} free function, if any. */
  def forFreeFunction(ff: CefDecl.FreeFunction): Option[(Param, OutCollection)] =
    if (retIsFillable(ff.ret) && (ff.cName.contains("_get_") || ff.cName.startsWith("cef_get_")))
      single(ff.visibleParams)
    else None

  private def retIsFillable(ret: CType): Boolean = ret match {
    case CType.Void | CType.Bool => true
    case _                       => false
  }

  private def single(params: List[Param]): Option[(Param, OutCollection)] =
    params.flatMap(p => of(p.typ).map(p -> _)) match {
      case one :: Nil => Some(one)
      case _          => None
    }
}
