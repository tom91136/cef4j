package net.kurobako.cef4j.codegen

object Naming {

  /** C struct name -> C++ class name, e.g. "cef_domvisitor_t" -> "CefDOMVisitor". Set once at startup. */
  private var cppClassNames: Map[String, String] = Map.empty

  /** Compound segments derived from C++ class names, e.g. "jsdialog" -> List("Js", "Dialog"). */
  private var derivedCompoundSegments: Map[String, List[String]] = Map.empty

  /** Initialise the C++ class name and compound segment lookups. Must be called before structToJavaName. */
  def initCppClassNames(names: Map[String, String], compoundSegments: Map[String, List[String]]): Unit = {
    cppClassNames = names
    derivedCompoundSegments = compoundSegments.map { case (k, words) => k -> words.map(titleCase) }
  }

  def toCamelCase(snake: String): String = {
    val segments = snake.split("_").toList
      .flatMap(s => compoundSegments.getOrElse(s.toLowerCase, List(capitalise(s))))
    val name = segments match {
      case Nil     => ""
      case x :: xs => x.toLowerCase + xs.mkString
    }
    if (ReservedMethods.contains(name)) s"cef${capitalise(name)}" else name
  }

  /** Split a PascalCase string (potentially with acronyms) into words. Uses uppercase runs and case transitions as
    * boundaries. e.g., "CefDOMDocument" -> List("Cef", "DOM", "Document"), "URIEncode" -> List("URI", "Encode").
    */
  private def splitPascalWords(s: String): List[String] = {
    val buf    = new StringBuilder
    val result = List.newBuilder[String]
    for (i <- s.indices) {
      val c = s(i)
      if (c.isUpper && buf.nonEmpty) {
        val prev          = s(i - 1)
        val nextIsLower   = i + 1 < s.length && s(i + 1).isLower
        val startsNewWord = prev.isLower || (prev.isUpper && nextIsLower)
        if (startsNewWord) {
          result += buf.toString
          buf.clear()
        }
      }
      buf += c
    }
    if (buf.nonEmpty) result += buf.toString
    result.result()
  }

  private def titleCase(s: String): String = if (s.isEmpty) s else s.head.toUpper + s.tail.toLowerCase

  /** Normalise a C++ PascalCase name (with acronyms) to regular PascalCase. Each word gets title-cased.
    * "CefDOMDocument" -> "CefDomDocument", "GetDEREncoded" -> "GetDerEncoded", "URIEncode" -> "UriEncode".
    */
  def normalizePascal(cpp: String): String = splitPascalWords(cpp).map(titleCase).mkString

  /** Derive camelCase Java method name from C++ PascalCase. Normalises acronyms to title case. "GetFoo" -> "getFoo",
    * "URIEncode" -> "uriEncode", "GetDOMDocument" -> "getDomDocument".
    */
  def pascalToCamel(pascal: String): String = {
    val words = splitPascalWords(pascal)
    val name  = words match {
      case Nil     => ""
      case x :: xs => x.toLowerCase + xs.map(titleCase).mkString
    }
    if (ReservedMethods.contains(name)) s"cef${capitalise(name)}" else name
  }

  /** Derive the Java method name for a FnPtr, preferring the C++ name when available. */
  def javaMethodName(fn: FnPtr): String = fn.cppName match {
    case Some(cpp) => pascalToCamel(cpp)
    case None      => toCamelCase(fn.name)
  }

  /** Derive the PascalCase native method suffix for a FnPtr (used for N_ prefix and JNI symbols). */
  def javaPascalName(fn: FnPtr): String = fn.cppName match {
    case Some(cpp) => normalizePascal(cpp)
    case None      => toPascalCase(fn.name)
  }

  /** Compound segments derived from C++ class names. Populated at init time. */
  private def compoundSegments: Map[String, List[String]] = derivedCompoundSegments

  /** Find the longest common prefix of all names, trimmed to the last underscore boundary. Returns empty string if
    * stripping would produce empty or digit-leading names.
    */
  def computeEnumPrefix(names: List[String]): String = {
    if (names.size < 2) return ""
    val raw = names.reduce { (a, b) =>
      a.zip(b).takeWhile { case (x, y) => x == y }.map(_._1).mkString
    }
    val lastUs = raw.lastIndexOf('_')
    if (lastUs <= 0) return ""
    val prefix   = raw.substring(0, lastUs + 1)
    val allValid = names.forall { n =>
      val stripped = n.stripPrefix(prefix)
      stripped.nonEmpty && stripped.head.isLetter
    }
    if (allValid) prefix else ""
  }

  def toPascalCase(snake: String): String =
    snake.split("_").toList
      .flatMap(s => compoundSegments.getOrElse(s.toLowerCase, List(capitalise(s))))
      .mkString

  def cefBaseName(cefName: String): String = cefName.stripPrefix("cef_").stripSuffix("_t")

  // cef_rect_t -> CefMutableRect
  def mutableJavaName(cefName: String): String = s"CefMutable${toPascalCase(cefBaseName(cefName))}"

  def capitalise(s: String): String = if (s.isEmpty) s else s.head.toUpper + s.tail

  // cef_browser_t -> CefBrowser, cef_domvisitor_t -> CefDomVisitor (from C++ class name, normalised)
  def structToJavaName(cefName: String): String =
    cppClassNames.get(cefName).map(normalizePascal).getOrElse(toPascalCase(cefName.stripSuffix("_t")))

  private val ReservedMethods: Set[String] = Set(
    "clone",
    "close",
    "equals",
    "finalize",
    "getClass",
    "hashCode",
    "notify",
    "notifyAll",
    "toString",
    "wait"
  )

  /** cef_browser_t -> net.kurobako.cef4j.gen.CefBrowser */
  def fullyQualifiedJavaName(cefStructName: String): String =
    s"net.kurobako.cef4j.gen.${structToJavaName(cefStructName)}"

  /** cef_rect_t -> net.kurobako.cef4j.gen.CefMutableRect */
  def fullyQualifiedMutableName(cefStructName: String): String =
    s"net.kurobako.cef4j.gen.${mutableJavaName(cefStructName)}"

  /** N_GoBack -> N_1GoBack */
  def jniMethodMangle(methodName: String): String = methodName.replace("_", "_1")

  def jniSymbol(cefStructName: String, fn: FnPtr): String = {
    val outerClass   = structToJavaName(cefStructName)
    val nativeMethod = "N_" + javaPascalName(fn)
    s"Java_net_kurobako_cef4j_gen_${outerClass}_00024NativePeer_${jniMethodMangle(nativeMethod)}"
  }

  /** JNI symbol for a static native method directly on a Java class (e.g., CefGlobals). */
  def jniSymbolStatic(javaClass: String, javaMethodName: String): String = {
    val nativeMethod = "N_" + capitalise(javaMethodName)
    s"Java_net_kurobako_cef4j_gen_${javaClass}_${jniMethodMangle(nativeMethod)}"
  }

  /** JNI symbol for a static native method in the NativePeer inner class of a Java interface. */
  def jniSymbolStaticInner(javaClass: String, javaMethodName: String): String = {
    val nativeMethod = "N_" + capitalise(javaMethodName)
    s"Java_net_kurobako_cef4j_gen_${javaClass}_00024NativePeer_${jniMethodMangle(nativeMethod)}"
  }

  def javaType(ct: CType): String = ct match {
    case CType.Void                          => "void"
    case CType.Bool                          => "boolean"
    case CType.Int                           => "int"
    case CType.UInt                          => "int"
    case CType.Char                          => "char"
    case CType.Long                          => "long"
    case CType.SizeT                         => "long"
    case CType.Float                         => "float"
    case CType.Double                        => "double"
    case CType.JString                       => "String"
    case CType.Ptr(_)                        => "long"
    case CType.ObjectPtr(name)               => structToJavaName(name)
    case CType.OutObjectPtr(name)            => s"AtomicReference<${structToJavaName(name)}>"
    case CType.OutPrimitivePtr(inner)        => s"${javaType(inner)}[]"
    case CType.OpaquePtr                     => "NativePointer"
    case CType.ObjectPtrArray(name)          => s"${structToJavaName(name)}[]"
    case CType.OutInt                        => "int[]"
    case CType.OutBool                       => "boolean[]"
    case CType.ByValueIn(name)               => structToJavaName(name)
    case CType.ByValueOut(name)              => mutableJavaName(name)
    case CType.ByValueArray(name)            => s"${structToJavaName(name)}[]"
    case CType.PixelBuffer                   => "ByteBuffer"
    case CType.Buffer(_)                     => "ByteBuffer"
    case CType.BufferSize(_)                 => "long" // hidden from Java; only used in C++ codegen
    case CType.Enum(name)                    => structToJavaName(name)
    case CType.DataStruct(n)                 => structToJavaName(n)
    case CType.StringList                    => "List<String>"
    case CType.StringMap                     => "Map<String, String>"
    case CType.StringMultimap                => "Map<String, List<String>>"
    case CType.CountFuncArray(elem, _, _, _) =>
      if (isPrimitiveElement(elem)) s"${javaType(elem)}[]"
      else s"List<${javaType(elem)}>"
  }

  /** Collect java imports required by a CType (only java.util.*, java.nio.*, java.util.concurrent.atomic.*). */
  def javaImports(ct: CType): Set[String] = ct match {
    case CType.OutObjectPtr(_)               => Set("java.util.concurrent.atomic.AtomicReference")
    case CType.PixelBuffer | CType.Buffer(_) => Set("java.nio.ByteBuffer")
    case CType.StringList                    => Set("java.util.List")
    case CType.StringMap                     => Set("java.util.Map")
    case CType.StringMultimap                => Set("java.util.Map", "java.util.List")
    case CType.CountFuncArray(elem, _, _, _) if !isPrimitiveElement(elem) =>
      Set("java.util.List", "java.util.Arrays", "java.util.Collections")
    case _ => Set.empty
  }

  /** Whether a CountFuncArray element type is a JNI primitive (returns a primitive array, not List). */
  def isPrimitiveElement(ct: CType): Boolean = ct match {
    case CType.Bool | CType.Int | CType.UInt | CType.Char |
        CType.Long | CType.SizeT | CType.Float | CType.Double => true
    case _ => false
  }

  /** The JNI native method return type for CountFuncArray (always an array, even when Java API returns List). */
  def jniNativeReturnType(ct: CType): String = ct match {
    case CType.CountFuncArray(elem, _, _, _) => s"${javaType(elem)}[]"
    case other                               => javaType(other)
  }

  def jniType(ct: CType): String = ct match {
    case CType.Void                          => "void"
    case CType.Bool                          => "jboolean"
    case CType.Int                           => "jint"
    case CType.UInt                          => "jint"
    case CType.Char                          => "jchar"
    case CType.Long                          => "jlong"
    case CType.SizeT                         => "jlong"
    case CType.Float                         => "jfloat"
    case CType.Double                        => "jdouble"
    case CType.JString                       => "jstring"
    case CType.Ptr(_)                        => "jlong"
    case CType.ObjectPtr(_)                  => "jobject"
    case CType.OutObjectPtr(_)               => "jobject"
    case CType.OutPrimitivePtr(inner)        => s"${jniType(inner)}Array"
    case CType.OpaquePtr                     => "jobject"
    case CType.ObjectPtrArray(_)             => "jobjectArray"
    case CType.OutInt                        => "jintArray"
    case CType.OutBool                       => "jbooleanArray"
    case CType.ByValueIn(_)                  => "jobject"
    case CType.ByValueOut(_)                 => "jobject"
    case CType.ByValueArray(_)               => "jobjectArray"
    case CType.PixelBuffer                   => "jobject"
    case CType.Buffer(_)                     => "jobject"
    case CType.BufferSize(_)                 => "jlong" // hidden from JNI params
    case CType.Enum(_)                       => "jobject"
    case CType.DataStruct(_)                 => "jobject"
    case CType.StringList                    => "jobject"
    case CType.StringMap                     => "jobject"
    case CType.StringMultimap                => "jobject"
    case CType.CountFuncArray(elem, _, _, _) => s"${jniType(elem)}Array"
  }

  def jniSig(ct: CType): String = ct match {
    case CType.Void                          => "V"
    case CType.Bool                          => "Z"
    case CType.Int                           => "I"
    case CType.UInt                          => "I"
    case CType.Char                          => "C"
    case CType.Long                          => "J"
    case CType.SizeT                         => "J"
    case CType.Float                         => "F"
    case CType.Double                        => "D"
    case CType.JString                       => "Ljava/lang/String;"
    case CType.Ptr(_)                        => "J"
    case CType.ObjectPtr(name)               => s"L${fullyQualifiedJavaName(name).replace('.', '/')};"
    case CType.OutObjectPtr(_)               => "Ljava/util/concurrent/atomic/AtomicReference;"
    case CType.OutPrimitivePtr(inner)        => s"[${jniSig(inner)}"
    case CType.OpaquePtr                     => "Lnet/kurobako/cef4j/gen/NativePointer;"
    case CType.ObjectPtrArray(name)          => s"[L${fullyQualifiedJavaName(name).replace('.', '/')};"
    case CType.OutInt                        => "[I"
    case CType.OutBool                       => "[Z"
    case CType.ByValueIn(name)               => s"L${fullyQualifiedJavaName(name).replace('.', '/')};"
    case CType.ByValueOut(name)              => s"L${fullyQualifiedMutableName(name).replace('.', '/')};"
    case CType.ByValueArray(name)            => s"[L${fullyQualifiedJavaName(name).replace('.', '/')};"
    case CType.PixelBuffer                   => "Ljava/nio/ByteBuffer;"
    case CType.Buffer(_)                     => "Ljava/nio/ByteBuffer;"
    case CType.BufferSize(_)                 => "J" // hidden; not emitted in JNI sigs
    case CType.Enum(name)                    => s"L${fullyQualifiedJavaName(name).replace('.', '/')};"
    case CType.DataStruct(_)                 => "Ljava/lang/Object;"
    case CType.StringList                    => "Ljava/util/List;"
    case CType.StringMap                     => "Ljava/util/Map;"
    case CType.StringMultimap                => "Ljava/util/Map;"
    case CType.CountFuncArray(elem, _, _, _) => s"[${jniSig(elem)}"
  }

  def cType(ct: CType): String = ct match {
    case CType.Void                          => "void"
    case CType.Bool                          => "int"
    case CType.Int                           => "int"
    case CType.UInt                          => "unsigned int"
    case CType.Char                          => "char16_t"
    case CType.Long                          => "int64_t"
    case CType.SizeT                         => "size_t"
    case CType.Float                         => "float"
    case CType.Double                        => "double"
    case CType.JString                       => "cef_string_t*"
    case CType.Ptr(inner)                    => s"$inner*"
    case CType.ObjectPtr(name)               => s"$name*"
    case CType.OutObjectPtr(name)            => s"$name**"
    case CType.OutPrimitivePtr(inner)        => s"${cType(inner)}*"
    case CType.OpaquePtr                     => "void*"
    case CType.ObjectPtrArray(name)          => s"$name**"
    case CType.OutInt                        => "int*"
    case CType.OutBool                       => "int*"
    case CType.ByValueIn(name)               => s"const $name*"
    case CType.ByValueOut(name)              => s"$name*"
    case CType.ByValueArray(name)            => s"const $name*"
    case CType.PixelBuffer                   => "const void*"
    case CType.Buffer(_)                     => "void*"
    case CType.BufferSize(_)                 => "size_t"
    case CType.Enum(name)                    => name
    case CType.DataStruct(n)                 => s"$n*"
    case CType.StringList                    => "cef_string_list_t"
    case CType.StringMap                     => "cef_string_map_t"
    case CType.StringMultimap                => "cef_string_multimap_t"
    case CType.CountFuncArray(elem, _, _, _) => cType(elem) + "*" // not directly used; C side handles it
  }
}
