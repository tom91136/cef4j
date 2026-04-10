package net.kurobako.cef4j.codegen

// By-value struct marshalling utilities for JNI C++ code generation.
// Handles reading/writing C struct fields from/to Java objects.
class JniCppByValueCodeGen(
    dataStructs: Map[String, CefDecl.DataStruct]
)(using Naming.Context) {

  import JniCppByValueCodeGen.*

  private def jniName(cefName: String): String =
    Naming.javaInternalName(Naming.fullyQualifiedJavaName(cefName))

  private def jniMutableName(cefName: String): String =
    Naming.javaInternalName(Naming.fullyQualifiedMutableName(cefName))

  private def isSizeField(f: Field): Boolean = f.name == "size" && f.typ == CType.SizeT

  def hasNativeSizeField(cefName: String): Boolean =
    dataStructs.get(cefName).exists(_.fields.exists(isSizeField))

  def bvSetNativeSize(cefName: String, javaObj: String, clsVar: String, nativePtr: String): String =
    if (hasNativeSizeField(cefName))
      s"""if ($javaObj) env->SetLongField($javaObj, env->GetFieldID($clsVar, "size", "J"), static_cast<jlong>($nativePtr->size));"""
    else ""

  def byValueFields(cefName: String): List[BvField] =
    dataStructs.get(cefName) match {
      case Some(ds) =>
        ds.fields.filterNot(isSizeField).map { f =>
          val javaName = Naming.toCamelCase(f.name)
          val bvType   = f.typ match {
            case CType.Int | CType.UInt | CType.Bool                               => BvFieldType.Int
            case CType.Enum(enumName)                                              => BvFieldType.Enum(enumName)
            case CType.Char                                                        => BvFieldType.Char
            case CType.Float                                                       => BvFieldType.Float
            case CType.Double                                                      => BvFieldType.Double
            case CType.Long | CType.SizeT                                          => BvFieldType.Long
            case CType.JString                                                     => BvFieldType.String
            case CType.DataStruct(nested) if CHeaderParser.isByValueStruct(nested) =>
              BvFieldType.NestedStruct(nested)
            case other =>
              throw new RuntimeException(
                s"Unsupported field type $other for by-value struct $cefName field ${f.name}"
              )
          }
          BvField(f.name, javaName, bvType)
        }
      case None =>
        throw new RuntimeException(
          s"No DataStruct declaration found for by-value struct $cefName - is it parsed from headers?"
        )
    }

  def bvJniSig(t: BvFieldType): String = t match {
    case BvFieldType.Int             => "I"
    case BvFieldType.Char            => "C"
    case BvFieldType.Float           => "F"
    case BvFieldType.Double          => "D"
    case BvFieldType.Long            => "J"
    case BvFieldType.String          => "Ljava/lang/String;"
    case BvFieldType.Enum(n)         => s"L${jniName(n)};"
    case BvFieldType.NestedStruct(n) => s"L${jniName(n)};"
  }

  def bvCastToJni(t: BvFieldType, expr: String): String = t match {
    case BvFieldType.Int     => s"static_cast<jint>($expr)"
    case BvFieldType.Char    => s"static_cast<jchar>($expr)"
    case BvFieldType.Float   => s"static_cast<jfloat>($expr)"
    case BvFieldType.Double  => s"static_cast<jdouble>($expr)"
    case BvFieldType.Long    => s"static_cast<jlong>($expr)"
    case BvFieldType.String  => s"CefStringToJString(env, &($expr))"
    case BvFieldType.Enum(_) => s"static_cast<jlong>($expr)"
    case _                   => expr
  }

  def bvGetAndCast(t: BvFieldType, obj: String, cls: String, javaName: String): String = t match {
    case BvFieldType.Int    => s"""env->GetIntField($obj, env->GetFieldID($cls, "$javaName", "I"))"""
    case BvFieldType.Char   => s"""env->GetCharField($obj, env->GetFieldID($cls, "$javaName", "C"))"""
    case BvFieldType.Float  => s"""env->GetFloatField($obj, env->GetFieldID($cls, "$javaName", "F"))"""
    case BvFieldType.Double => s"""env->GetDoubleField($obj, env->GetFieldID($cls, "$javaName", "D"))"""
    case BvFieldType.Long   =>
      s"""static_cast<size_t>(env->GetLongField($obj, env->GetFieldID($cls, "$javaName", "J")))"""
    case _ => throw new RuntimeException(s"bvGetAndCast called on $t - use bvCopyJavaToCStruct for strings/nested")
  }

  // Format a NewObject call, breaking args across lines when there are many.
  def fmtNewObject(clsVar: String, ctorVar: String, args: List[String], guard: String = ""): String =
    if (args.length <= 4) {
      val argsStr = args.mkString(", ")
      if (guard.nonEmpty) s"$guard ? env->NewObject($clsVar, $ctorVar, $argsStr) : nullptr"
      else s"env->NewObject($clsVar, $ctorVar, $argsStr)"
    } else {
      val indent    = "        "
      val argsBlock = args.map(a => s"$indent$a").mkString(",\n")
      if (guard.nonEmpty)
        s"$guard\n    ? env->NewObject($clsVar, $ctorVar,\n$argsBlock)\n    : nullptr"
      else s"env->NewObject($clsVar, $ctorVar,\n$argsBlock)"
    }

  // Return the ctor signature, ctor args list, and any pre-creation lines for nested JNI objects.
  def byValueCtorFromPtr(cefName: String, ptrName: String): (String, List[String], List[String]) = {
    val fields         = byValueFields(cefName)
    val objectCreation = fields.collect {
      case BvField(cName, _, BvFieldType.NestedStruct(nestedCef)) =>
        val varName      = s"_bv_${ptrName}_$cName"
        val nestedFqn    = jniName(nestedCef)
        val nestedFields = byValueFields(nestedCef)
        val nestedSig    = s"(${nestedFields.map(f => bvJniSig(f.typ)).mkString})V"
        val nestedArgs   = nestedFields.map(f => bvCastToJni(f.typ, s"$ptrName->$cName.${f.cName}")).mkString(", ")
        (
          cName,
          varName,
          List(
            s"""auto ${varName}_cls = FindClassCached(env, "$nestedFqn");""",
            s"""auto ${varName}_ctor = env->GetMethodID(${varName}_cls, "<init>", "$nestedSig");""",
            s"""auto $varName = env->NewObject(${varName}_cls, ${varName}_ctor, $nestedArgs);"""
          )
        )
      case BvField(cName, _, BvFieldType.String) =>
        val varName = s"_bv_${ptrName}_$cName"
        (cName, varName, List(s"""auto $varName = CefStringToJString(env, &$ptrName->$cName);"""))
      case BvField(cName, _, BvFieldType.Enum(enumCefName)) =>
        val varName = s"_bv_${ptrName}_$cName"
        val enumFqn = jniName(enumCefName)
        (
          cName,
          varName,
          List(
            s"""auto ${varName}_cls = FindClassCached(env, "$enumFqn");""",
            s"""auto ${varName}_of = env->GetStaticMethodID(${varName}_cls, "of", "(J)L$enumFqn;");""",
            s"""auto $varName = env->CallStaticObjectMethod(${varName}_cls, ${varName}_of, static_cast<jlong>($ptrName->$cName));"""
          )
        )
    }
    val objectVars = objectCreation.map(t => (t._1, t._2)).toMap
    val objectPre  = objectCreation.flatMap(_._3)

    val sig      = s"(${fields.map(f => bvJniSig(f.typ)).mkString})V"
    val argsList = fields.map {
      case BvField(cName, _, BvFieldType.NestedStruct(_) | BvFieldType.String | BvFieldType.Enum(_)) =>
        objectVars(cName)
      case BvField(cName, _, typ) => bvCastToJni(typ, s"$ptrName->$cName")
    }
    (sig, argsList, objectPre)
  }

  // Copy fields from a Java object into a C struct via pointer or by-value access.
  def bvCopyJavaToCStruct(
      cefName: String,
      dest: String,
      accessor: String,
      javaObj: String,
      clsVar: String,
      prefix: String
  ): List[String] = {
    val fields = byValueFields(cefName)
    fields.flatMap {
      case BvField(cName, javaName, BvFieldType.NestedStruct(nestedCef)) =>
        val nestedFields = byValueFields(nestedCef)
        val sig          = bvJniSig(BvFieldType.NestedStruct(nestedCef))
        val v            = s"_${prefix}_$cName"
        List(
          s"""auto $v = env->GetObjectField($javaObj, env->GetFieldID($clsVar, "$javaName", "$sig"));""",
          s"if ($v) {",
          s"    auto ${v}c = env->GetObjectClass($v);"
        ) ++ nestedFields.map(nf =>
          s"""    $dest$accessor$cName.${nf.cName} = static_cast<decltype($dest$accessor$cName.${nf.cName})>(${bvGetAndCast(
              nf.typ,
              v,
              s"${v}c",
              nf.javaName
            )});"""
        ) ++ List("}")
      case BvField(cName, javaName, BvFieldType.String) =>
        val v = s"_${prefix}_$cName"
        List(
          s"""jstring $v = (jstring)env->GetObjectField($javaObj, env->GetFieldID($clsVar, "$javaName", "Ljava/lang/String;"));""",
          s"if ($v) {",
          s"""    const jchar* ${v}_chars = env->GetStringChars($v, nullptr);""",
          s"""    jsize ${v}_len = env->GetStringLength($v);""",
          s"""    cef_string_set(reinterpret_cast<const char16_t*>(${v}_chars), ${v}_len, &$dest$accessor$cName, 1);""",
          s"""    env->ReleaseStringChars($v, ${v}_chars);""",
          "}"
        )
      case BvField(cName, javaName, BvFieldType.Enum(enumCefName)) =>
        val sig = bvJniSig(BvFieldType.Enum(enumCefName))
        val v   = s"_${prefix}_$cName"
        List(
          s"""auto $v = env->GetObjectField($javaObj, env->GetFieldID($clsVar, "$javaName", "$sig"));""",
          s"if ($v) {",
          s"""    $dest$accessor$cName = static_cast<decltype($dest$accessor$cName)>(env->GetLongField($v, env->GetFieldID(env->GetObjectClass($v), "value", "J")));""",
          "}"
        )
      case BvField(cName, javaName, typ) =>
        List(
          s"""$dest$accessor$cName = static_cast<decltype($dest$accessor$cName)>(${bvGetAndCast(
              typ,
              javaObj,
              clsVar,
              javaName
            )});"""
        )
    }
  }

  def bvWriteBackLines(cefName: String, ptrName: String, javaObj: String, clsVar: String): List[String] =
    bvCopyJavaToCStruct(cefName, ptrName, "->", javaObj, clsVar, "wb")

  def bvReadFromJavaLines(cefName: String, structVar: String, javaObj: String, clsVar: String): List[String] = {
    val base     = bvCopyJavaToCStruct(cefName, structVar, ".", javaObj, clsVar, "rd")
    val hasSize  = dataStructs.get(cefName).exists(_.fields.exists(f => f.name == "size" && f.typ == CType.SizeT))
    val sizeInit = if (hasSize) List(s"$structVar.size = sizeof($cefName);") else Nil
    base ++ sizeInit
  }

  def byValueArrayFieldExpr(cefName: String, ptrName: String, idxVar: String): String = {
    val fields = byValueFields(cefName)
    fields.map {
      case BvField(_, _, BvFieldType.NestedStruct(_)) =>
        throw new RuntimeException(s"Nested struct in ByValueArray not supported: $cefName")
      case BvField(_, _, BvFieldType.String) =>
        throw new RuntimeException(s"String field in ByValueArray not supported: $cefName")
      case BvField(_, _, BvFieldType.Enum(_)) =>
        throw new RuntimeException(s"Enum field in ByValueArray not supported: $cefName")
      case BvField(cName, _, typ) => bvCastToJni(typ, s"$ptrName[$idxVar].$cName")
    }.mkString(", ")
  }

  def dataStructCtorInfo(cefName: String): (String, List[String], List[String]) =
    dataStructs.get(cefName) match {
      case Some(_) => byValueCtorFromPtr(cefName, "(&result)")
      case None    => ("()V", Nil, Nil)
    }

  // Count local refs created by nested by-value struct construction.
  def byValueLocalRefs(cefName: String): Int =
    dataStructs.get(cefName) match {
      case Some(ds) =>
        ds.fields.filterNot(isSizeField).map { f =>
          f.typ match {
            case CType.DataStruct(nested) if CHeaderParser.isByValueStruct(nested) =>
              3 + byValueLocalRefs(nested)
            case CType.JString => 1
            case CType.Enum(_) => 3
            case _             => 0
          }
        }.sum
      case None => 0
    }

  // Count local refs created by write-back phase of a ByValueOut param.
  def byValueWriteBackRefs(cefName: String): Int =
    dataStructs.get(cefName) match {
      case Some(ds) =>
        ds.fields.filterNot(isSizeField).map { f =>
          f.typ match {
            case CType.DataStruct(nested) if CHeaderParser.isByValueStruct(nested) => 2
            case CType.JString                                                     => 1
            case CType.Enum(_)                                                     => 2
            case _                                                                 => 0
          }
        }.sum
      case None => 0
    }
}

object JniCppByValueCodeGen {
  enum BvFieldType {
    case Int, Float, Double, Long, Char, String
    case Enum(cefName: scala.Predef.String)
    case NestedStruct(cefName: scala.Predef.String)
  }

  case class BvField(cName: String, javaName: String, typ: BvFieldType)
}
