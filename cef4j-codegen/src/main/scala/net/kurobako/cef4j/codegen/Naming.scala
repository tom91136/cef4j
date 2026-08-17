package net.kurobako.cef4j.codegen

import scala.annotation.tailrec

object Naming {

  case class Context(
      cppClassNames: Map[String, String],
      compoundSegments: Map[String, List[String]],
      javaPackage: String,
      subPackages: Map[String, String] = Map.empty,
      platformCppMode: Boolean = false,
      platformInterfaceTypes: Set[String] = Set.empty
  )

  object Context {
    given empty: Context = Context(Map.empty, Map.empty, "net.kurobako.cef4j.gen")

    def fromCppClassNames(
        names: Map[String, String],
        compoundSegments: Map[String, List[String]],
        javaPackage: String,
        subPackages: Map[String, String] = Map.empty,
        platformCppMode: Boolean = false,
        platformInterfaceTypes: Set[String] = Set.empty
    ): Context =
      Context(
        cppClassNames = names,
        compoundSegments = compoundSegments.map { case (k, words) => k -> words.map(titleCase) },
        javaPackage = javaPackage,
        subPackages = subPackages,
        platformCppMode = platformCppMode,
        platformInterfaceTypes = platformInterfaceTypes
      )
  }

  def subPackageFromHeader(sourceHeader: String): Option[String] = {
    val normalized = sourceHeader.replace('\\', '/')
    val slashIdx   = normalized.lastIndexOf('/')
    if (slashIdx > 0) Some(normalized.substring(0, slashIdx).replace('/', '.')) else None
  }

  def buildSubPackages(structHeaderMap: Map[String, String]): Map[String, String] =
    structHeaderMap.flatMap { case (structName, header) =>
      subPackageFromHeader(header).map(structName -> _)
    }

  def toCamelCase(snake: String)(using context: Context): String = {
    val name = if (!snake.contains('_')) {
      if (snake.isEmpty) "" else s"${snake.head.toLower}${snake.tail}"
    } else {
      val segments = snake.split("_").toList
        .flatMap(s => compoundSegments.getOrElse(s.toLowerCase, List(capitalise(s))))
      segments match {
        case Nil     => ""
        case x :: xs => x.toLowerCase + xs.mkString
      }
    }
    if (ReservedMethods.contains(name)) s"cef${capitalise(name)}" else name
  }

  def splitPascalWords(s: String): List[String] = {
    val starts = s.indices.drop(1).filter { index =>
      val current     = s(index)
      val previous    = s(index - 1)
      val nextIsLower = index + 1 < s.length && s(index + 1).isLower
      current.isUpper && (previous.isLower || previous.isDigit || (previous.isUpper && nextIsLower))
    }.toList
    if (s.isEmpty) Nil
    else (0 :: starts).zip(starts :+ s.length).map { case (from, until) => s.substring(from, until) }
  }

  private def titleCase(s: String): String = if (s.isEmpty) s else s"${s.head.toUpper}${s.tail.toLowerCase}"

  private def normalizePascal(cpp: String): String = splitPascalWords(cpp).map(titleCase).mkString

  private def pascalToCamel(pascal: String): String = {
    val words = splitPascalWords(pascal)
    val name  = words match {
      case Nil     => ""
      case x :: xs => x.toLowerCase + xs.map(titleCase).mkString
    }
    if (ReservedMethods.contains(name)) s"cef${capitalise(name)}" else name
  }

  def javaMethodName(fn: FnPtr)(using Context): String = fn.cppName match {
    case Some(cpp) => pascalToCamel(cpp)
    case None      => toCamelCase(fn.name)
  }

  def cppPascalName(fn: FnPtr)(using Context): String = fn.cppName match {
    case Some(cpp) => normalizePascal(cpp)
    case None      => toPascalCase(fn.name)
  }

  def nativeMethodName(fn: FnPtr)(using Context): String = javaMethodName(fn) + "0"

  def nativeMethodName(javaName: String): String = javaName + "0"

  private def compoundSegments(using context: Context): Map[String, List[String]] = context.compoundSegments

  def computeEnumPrefix(names: List[String]): String =
    if (names.size < 2) ""
    else {
      val raw = names.reduce { (a, b) =>
        a.zip(b).takeWhile { case (x, y) => x == y }.map(_._1).mkString
      }
      val lastUnderscore = raw.lastIndexOf('_')
      if (lastUnderscore <= 0) ""
      else {
        val prefix = raw.substring(0, lastUnderscore + 1)
        if (names.forall(name => name.stripPrefix(prefix).headOption.exists(_.isLetter))) prefix else ""
      }
    }

  def toPascalCase(snake: String)(using Context): String =
    snake.split("_").toList
      .flatMap(s => compoundSegments.getOrElse(s.toLowerCase, List(capitalise(s))))
      .mkString

  def cefBaseName(cefName: String): String = cefName.stripPrefix("cef_").stripSuffix("_t")

  private def mutableJavaName(cefName: String)(using Context): String =
    s"${structToJavaName(cefName)}.Mutable"

  def capitalise(s: String): String = if (s.isEmpty) s else s"${s.head.toUpper}${s.tail}"

  def structToJavaName(cefName: String)(using context: Context): String =
    context.cppClassNames.get(cefName).map(normalizePascal).getOrElse(toPascalCase(cefName.stripSuffix("_t")))

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

  def javaPackage(using context: Context): String = context.javaPackage

  def javaPackageFor(cefStructName: String)(using context: Context): String =
    context.subPackages.get(cefStructName) match {
      case Some(sub) => s"${context.javaPackage}.$sub"
      case None      => context.javaPackage
    }

  def javaInternalName(name: String): String = name.replace('.', '/')

  private def jniPackagePrefix(using context: Context): String = javaPackage.replace('.', '_')

  def fullyQualifiedJavaName(cefStructName: String)(using Context): String =
    s"${javaPackageFor(cefStructName)}.${structToJavaName(cefStructName)}"

  def fullyQualifiedSharedJavaName(cefStructName: String)(using Context): String =
    s"$javaPackage.${structToJavaName(cefStructName)}"

  def fullyQualifiedMutableName(cefStructName: String)(using Context): String =
    s"${javaPackageFor(cefStructName)}.${structToJavaName(cefStructName)}$$Mutable"

  private def isSyntheticPlatformSubPackage(sub: String): Boolean =
    sub == "linux" || sub == "mac" || sub == "win"

  // Only marker interfaces use platform-qualified JNI class names.
  def fullyQualifiedJavaNameForJniLookup(cefStructName: String)(using context: Context): String =
    context.subPackages.get(cefStructName) match {
      case Some(sub)
          if isSyntheticPlatformSubPackage(sub) && (!context.platformCppMode || !context.platformInterfaceTypes
            .contains(cefStructName)) =>
        fullyQualifiedSharedJavaName(cefStructName)
      case _ =>
        fullyQualifiedJavaName(cefStructName)
    }

  def fullyQualifiedMutableNameForJniLookup(cefStructName: String)(using context: Context): String =
    context.subPackages.get(cefStructName) match {
      case Some(sub)
          if isSyntheticPlatformSubPackage(sub) && (!context.platformCppMode || !context.platformInterfaceTypes
            .contains(cefStructName)) =>
        s"$javaPackage.${structToJavaName(cefStructName)}$$Mutable"
      case _ =>
        fullyQualifiedMutableName(cefStructName)
    }

  private def nativePointerFqcn(using Context): String = s"$javaPackage.NativePointer"

  def nativePointerInternalName(using Context): String = javaInternalName(nativePointerFqcn)

  def jniSymbol(cefStructName: String, fn: FnPtr)(using Context): String = {
    val outerClass = structToJavaName(cefStructName)
    s"Java_${jniPackagePrefix}_${outerClass}_00024NativePeer_${nativeMethodName(fn)}"
  }

  private def jniExport(retJni: String, clsExpr: String, methodName: String): String =
    s"CEF4J_JNI_EXPORT($retJni, $clsExpr, $methodName)"

  private def peerExpr(javaClass: String): String = s"CEF4J_PEER($javaClass)"

  def jniClassPrefix(cefStructName: String)(using context: Context): String = {
    val javaName = structToJavaName(cefStructName)
    context.subPackages.get(cefStructName) match {
      case Some(sub) => s"${sub.replace('.', '_')}_$javaName"
      case None      => javaName
    }
  }

  def jniExportPeer(cefStructName: String, fn: FnPtr, retJni: String)(using Context): String =
    jniExport(retJni, peerExpr(jniClassPrefix(cefStructName)), nativeMethodName(fn))

  def jniExportPeerStatic(jniClassPrefix: String, javaMethodName: String, retJni: String): String =
    jniExport(retJni, peerExpr(jniClassPrefix), nativeMethodName(javaMethodName))

  def jniExportStatic(javaClass: String, javaMethodName: String, retJni: String): String =
    jniExport(retJni, javaClass, nativeMethodName(javaMethodName))

  def javaType(ct: CType, javadoc: Boolean = false)(using Context): String = ct match {
    case CType.Void               => "void"
    case CType.Bool               => "boolean"
    case CType.Int                => "int"
    case CType.UInt               => "int"
    case CType.Char               => "char"
    case CType.Long               => "long"
    case CType.SizeT              => "long"
    case CType.Float              => "float"
    case CType.Double             => "double"
    case CType.JString            => "String"
    case CType.ConstCStringArray  => if (javadoc) "java.util.List" else "List<String>"
    case CType.CStringArray       => if (javadoc) "java.util.List" else "List<String>"
    case CType.Ptr(_)             => "long"
    case CType.ObjectPtr(name)    => structToJavaName(name)
    case CType.OutObjectPtr(name) =>
      if (javadoc) "java.util.concurrent.atomic.AtomicReference" else s"AtomicReference<${structToJavaName(name)}>"
    case CType.OutOpaquePtr =>
      if (javadoc) "java.util.concurrent.atomic.AtomicReference" else "AtomicReference<NativePointer>"
    case CType.OutPrimitivePtr(inner)        => s"${javaType(inner, javadoc)}[]"
    case CType.OpaquePtr                     => "NativePointer"
    case CType.ConstDataStructPtr(name)      => structToJavaName(name)
    case CType.ObjectPtrArray(name)          => s"${structToJavaName(name)}[]"
    case CType.OutInt                        => "int[]"
    case CType.OutBool                       => "boolean[]"
    case CType.Enum(name)                    => structToJavaName(name)
    case CType.DataStruct(n)                 => structToJavaName(n)
    case CType.ByValueIn(name)               => structToJavaName(name)
    case CType.ByValueOut(name)              => mutableJavaName(name)
    case CType.ByValueArray(name)            => s"${structToJavaName(name)}[]"
    case CType.Buffer(_) | CType.PixelBuffer => if (javadoc) "java.nio.ByteBuffer" else "ByteBuffer"
    case CType.BufferSize(_)                 => "long" // hidden from Java; only used in C++ codegen
    case CType.StringList                    => if (javadoc) "java.util.List" else "List<String>"
    case CType.StringMap                     => if (javadoc) "java.util.Map" else "Map<String, String>"
    case CType.StringMultimap                => if (javadoc) "java.util.Map" else "Map<String, List<String>>"
    case CType.CountFuncArray(elem, _, _, _) =>
      if (isPrimitiveElement(elem)) s"${javaType(elem, javadoc)}[]"
      else if (javadoc) "List"
      else s"List<${javaType(elem)}>"
  }

  def javaImports(ct: CType): Set[String] = ct match {
    case CType.OutObjectPtr(_)                        => Set("java.util.concurrent.atomic.AtomicReference")
    case CType.OutOpaquePtr                           => Set("java.util.concurrent.atomic.AtomicReference")
    case CType.PixelBuffer | CType.Buffer(_)          => Set("java.nio.ByteBuffer")
    case CType.ConstCStringArray | CType.CStringArray =>
      Set("java.util.List")
    case CType.StringList                                                 => Set("java.util.List")
    case CType.StringMap                                                  => Set("java.util.Map")
    case CType.StringMultimap                                             => Set("java.util.Map", "java.util.List")
    case CType.CountFuncArray(elem, _, _, _) if !isPrimitiveElement(elem) =>
      Set("java.util.List", "java.util.Arrays", "java.util.Collections")
    case _ => Set.empty
  }

  @tailrec
  def referencedCefNames(ct: CType): List[String] = ct match {
    case CType.ObjectPtr(name)               => List(name)
    case CType.OutObjectPtr(name)            => List(name)
    case CType.ObjectPtrArray(name)          => List(name)
    case CType.ConstDataStructPtr(name)      => List(name)
    case CType.ByValueIn(name)               => List(name)
    case CType.ByValueOut(name)              => List(name)
    case CType.ByValueArray(name)            => List(name)
    case CType.Enum(name)                    => List(name)
    case CType.DataStruct(name)              => List(name)
    case CType.CountFuncArray(elem, _, _, _) => referencedCefNames(elem)
    case _                                   => Nil
  }

  def isPrimitiveElement(ct: CType): Boolean = ct match {
    case CType.Bool | CType.Int | CType.UInt | CType.Char |
        CType.Long | CType.SizeT | CType.Float | CType.Double => true
    case _ => false
  }

  def jniNativeReturnType(ct: CType)(using Context): String = ct match {
    case CType.CountFuncArray(elem, _, _, _) => s"${javaType(elem)}[]"
    case other                               => javaType(other)
  }

  def jniType(ct: CType)(using Context): String = ct match {
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
    case CType.ConstCStringArray             => "jobject"
    case CType.CStringArray                  => "jobject"
    case CType.Ptr(_)                        => "jlong"
    case CType.ObjectPtr(_)                  => "jobject"
    case CType.OutObjectPtr(_)               => "jobject"
    case CType.OutOpaquePtr                  => "jobject"
    case CType.OutPrimitivePtr(inner)        => s"${jniType(inner)}Array"
    case CType.OpaquePtr                     => "jobject"
    case CType.ConstDataStructPtr(_)         => "jobject"
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

  def jniSig(ct: CType)(using Context): String = ct match {
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
    case CType.ConstCStringArray             => "Ljava/util/List;"
    case CType.CStringArray                  => "Ljava/util/List;"
    case CType.Ptr(_)                        => "J"
    case CType.ObjectPtr(name)               => s"L${javaInternalName(fullyQualifiedJavaNameForJniLookup(name))};"
    case CType.OutObjectPtr(_)               => "Ljava/util/concurrent/atomic/AtomicReference;"
    case CType.OutOpaquePtr                  => "Ljava/util/concurrent/atomic/AtomicReference;"
    case CType.OutPrimitivePtr(inner)        => s"[${jniSig(inner)}"
    case CType.OpaquePtr                     => s"L$nativePointerInternalName;"
    case CType.ConstDataStructPtr(name)      => s"L${javaInternalName(fullyQualifiedSharedJavaName(name))};"
    case CType.ObjectPtrArray(name)          => s"[L${javaInternalName(fullyQualifiedJavaNameForJniLookup(name))};"
    case CType.OutInt                        => "[I"
    case CType.OutBool                       => "[Z"
    case CType.ByValueIn(name)               => s"L${javaInternalName(fullyQualifiedSharedJavaName(name))};"
    case CType.ByValueOut(name)              => s"L${javaInternalName(fullyQualifiedSharedJavaName(name))}$$Mutable;"
    case CType.ByValueArray(name)            => s"[L${javaInternalName(fullyQualifiedJavaNameForJniLookup(name))};"
    case CType.PixelBuffer                   => "Ljava/nio/ByteBuffer;"
    case CType.Buffer(_)                     => "Ljava/nio/ByteBuffer;"
    case CType.BufferSize(_)                 => "J" // hidden; not emitted in JNI sigs
    case CType.Enum(name)                    => s"L${javaInternalName(fullyQualifiedJavaNameForJniLookup(name))};"
    case CType.DataStruct(name)              => s"L${javaInternalName(fullyQualifiedJavaNameForJniLookup(name))};"
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
    case CType.Char                          => "cef4j_char_t"
    case CType.Long                          => "int64_t"
    case CType.SizeT                         => "size_t"
    case CType.Float                         => "float"
    case CType.Double                        => "double"
    case CType.JString                       => "cef_string_t*"
    case CType.ConstCStringArray             => "const char* const*"
    case CType.CStringArray                  => "char**"
    case CType.Ptr(inner)                    => s"$inner*"
    case CType.ObjectPtr(name)               => s"$name*"
    case CType.OutObjectPtr(name)            => s"$name**"
    case CType.OutOpaquePtr                  => "void**"
    case CType.OutPrimitivePtr(inner)        => s"${cType(inner)}*"
    case CType.OpaquePtr                     => "void*"
    case CType.ConstDataStructPtr(name)      => s"const $name*"
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
