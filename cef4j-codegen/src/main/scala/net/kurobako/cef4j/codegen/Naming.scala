package net.kurobako.cef4j.codegen

object Naming {

  def toCamelCase(snake: String): String = {
    val name = snake.split("_").toList match {
      case Nil     => ""
      case x :: xs => s"$x${xs.map(capitalise).mkString}"
    }
    if (ReservedMethods.contains(name)) s"cef${capitalise(name)}" else name
  }

  def toPascalCase(snake: String): String = snake.split("_").map(capitalise).mkString

  def cefBaseName(cefName: String): String = cefName.stripPrefix("cef_").stripSuffix("_t")

  // cef_rect_t -> CefMutableRect
  def mutableJavaName(cefName: String): String = s"CefMutable${toPascalCase(cefBaseName(cefName))}"

  def capitalise(s: String): String = if (s.isEmpty) s else s.head.toUpper + s.tail

  // cef_browser_t -> CefBrowser
  def structToJavaName(cefName: String): String = toPascalCase(cefName.stripSuffix("_t"))

  private val ReservedMethods: Set[String] = Set(
    "clone",
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

  def jniSymbol(cefStructName: String, fnName: String): String = {
    val outerClass   = structToJavaName(cefStructName)
    val nativeMethod = "N_" + toPascalCase(fnName)
    s"Java_net_kurobako_cef4j_gen_${outerClass}_00024NativePeer_${jniMethodMangle(nativeMethod)}"
  }

  def javaType(ct: CType): String = ct match {
    case CType.Void               => "void"
    case CType.Bool               => "boolean"
    case CType.Int                => "int"
    case CType.UInt               => "int"
    case CType.Long               => "long"
    case CType.SizeT              => "long"
    case CType.Float              => "float"
    case CType.Double             => "double"
    case CType.JString            => "String"
    case CType.Ptr(_)             => "long"
    case CType.OutInt             => "int[]"
    case CType.OutBool            => "boolean[]"
    case CType.ByValueIn(name)    => structToJavaName(name)
    case CType.ByValueOut(name)   => mutableJavaName(name)
    case CType.ByValueArray(name) => s"${structToJavaName(name)}[]"
    case CType.PixelBuffer        => "java.nio.ByteBuffer"
    case CType.Enum(name)         => structToJavaName(name)
    case CType.DataStruct(n)      => structToJavaName(n)
    case CType.StringList         => "java.util.List<String>"
    case CType.StringMap          => "java.util.Map<String, String>"
    case CType.StringMultimap     => "java.util.Map<String, java.util.List<String>>"
  }

  def jniType(ct: CType): String = ct match {
    case CType.Void            => "void"
    case CType.Bool            => "jboolean"
    case CType.Int             => "jint"
    case CType.UInt            => "jint"
    case CType.Long            => "jlong"
    case CType.SizeT           => "jlong"
    case CType.Float           => "jfloat"
    case CType.Double          => "jdouble"
    case CType.JString         => "jstring"
    case CType.Ptr(_)          => "jlong"
    case CType.OutInt          => "jintArray"
    case CType.OutBool         => "jbooleanArray"
    case CType.ByValueIn(_)    => "jobject"
    case CType.ByValueOut(_)   => "jobject"
    case CType.ByValueArray(_) => "jobjectArray"
    case CType.PixelBuffer     => "jobject"
    case CType.Enum(_)         => "jint"
    case CType.DataStruct(_)   => "jobject"
    case CType.StringList      => "jobject"
    case CType.StringMap       => "jobject"
    case CType.StringMultimap  => "jobject"
  }

  def jniSig(ct: CType): String = ct match {
    case CType.Void               => "V"
    case CType.Bool               => "Z"
    case CType.Int                => "I"
    case CType.UInt               => "I"
    case CType.Long               => "J"
    case CType.SizeT              => "J"
    case CType.Float              => "F"
    case CType.Double             => "D"
    case CType.JString            => "Ljava/lang/String;"
    case CType.Ptr(_)             => "J"
    case CType.OutInt             => "[I"
    case CType.OutBool            => "[Z"
    case CType.ByValueIn(name)    => s"L${fullyQualifiedJavaName(name).replace('.', '/')};"
    case CType.ByValueOut(name)   => s"L${fullyQualifiedMutableName(name).replace('.', '/')};"
    case CType.ByValueArray(name) => s"[L${fullyQualifiedJavaName(name).replace('.', '/')};"
    case CType.PixelBuffer        => "Ljava/nio/ByteBuffer;"
    case CType.Enum(name)         => s"L${fullyQualifiedJavaName(name).replace('.', '/')};"
    case CType.DataStruct(_)      => "Ljava/lang/Object;"
    case CType.StringList         => "Ljava/util/List;"
    case CType.StringMap          => "Ljava/util/Map;"
    case CType.StringMultimap     => "Ljava/util/Map;"
  }

  def cType(ct: CType): String = ct match {
    case CType.Void               => "void"
    case CType.Bool               => "int"
    case CType.Int                => "int"
    case CType.UInt               => "unsigned int"
    case CType.Long               => "int64_t"
    case CType.SizeT              => "size_t"
    case CType.Float              => "float"
    case CType.Double             => "double"
    case CType.JString            => "cef_string_t*"
    case CType.Ptr(inner)         => s"$inner*"
    case CType.OutInt             => "int*"
    case CType.OutBool            => "int*"
    case CType.ByValueIn(name)    => s"const $name*"
    case CType.ByValueOut(name)   => s"$name*"
    case CType.ByValueArray(name) => s"const $name*"
    case CType.PixelBuffer        => "const void*"
    case CType.Enum(name)         => name
    case CType.DataStruct(n)      => s"$n*"
    case CType.StringList         => "cef_string_list_t"
    case CType.StringMap          => "cef_string_map_t"
    case CType.StringMultimap     => "cef_string_multimap_t"
  }
}
