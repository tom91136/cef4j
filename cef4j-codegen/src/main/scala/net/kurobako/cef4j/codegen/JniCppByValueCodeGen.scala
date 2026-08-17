package net.kurobako.cef4j.codegen

class JniCppByValueCodeGen(
    dataStructs: Map[String, CefDecl.DataStruct]
)(using Naming.Context) {

  import JniCppByValueCodeGen.*
  import JniNaming.jniName

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
            case CType.Int | CType.UInt | CType.Bool => BvFieldType.Int
            case CType.Enum(enumName)                => BvFieldType.Enum(enumName)
            case CType.Char                          => BvFieldType.Char
            case CType.Float                         => BvFieldType.Float
            case CType.Double                        => BvFieldType.Double
            case CType.Long | CType.SizeT            => BvFieldType.Long
            case CType.JString                       => BvFieldType.String
            case CType.ConstCStringArray             => BvFieldType.CStringArray(mutable = false)
            case CType.CStringArray                  => BvFieldType.CStringArray(mutable = true)
            case CType.DataStruct(nested)            =>
              BvFieldType.NestedStruct(nested)
            case CType.OpaquePtr =>
              BvFieldType.OpaquePtr
            case other =>
              throw IllegalArgumentException(
                s"Unsupported field type $other for by-value struct $cefName field ${f.name}"
              )
          }
          BvField(f.name, javaName, bvType)
        }
      case None =>
        throw IllegalArgumentException(
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
    case BvFieldType.CStringArray(_) => "Ljava/util/List;"
    case BvFieldType.Enum(n)         => s"L${jniName(n)};"
    case BvFieldType.NestedStruct(n) => s"L${jniName(n)};"
    case BvFieldType.OpaquePtr       => s"L${Naming.nativePointerInternalName};"
  }

  def bvCastToJni(t: BvFieldType, expr: String): String = t match {
    case BvFieldType.Int     => s"static_cast<jint>($expr)"
    case BvFieldType.Char    => s"static_cast<jchar>($expr)"
    case BvFieldType.Float   => s"static_cast<jfloat>($expr)"
    case BvFieldType.Double  => s"static_cast<jdouble>($expr)"
    case BvFieldType.Long    => s"to_jlong($expr)"
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
      s"""env->GetLongField($obj, env->GetFieldID($cls, "$javaName", "J"))"""
    case _ => throw IllegalArgumentException(s"bvGetAndCast called on $t; use bvCopyJavaToCStruct for strings/nested")
  }

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

  private def sanitiseIdent(raw: String): String =
    raw.map(ch => if (ch.isLetterOrDigit || ch == '_') ch else '_')

  private def buildCtorExpr(fieldType: BvFieldType, cExpr: String, varPrefix: String): (List[String], String) =
    fieldType match {
      case BvFieldType.Int | BvFieldType.Char | BvFieldType.Float | BvFieldType.Double | BvFieldType.Long =>
        (Nil, bvCastToJni(fieldType, cExpr))
      case BvFieldType.String =>
        (List(s"""auto $varPrefix = CefStringToJString(env, &$cExpr);"""), varPrefix)
      case BvFieldType.CStringArray(_) =>
        // Raw C string arrays expose no reliable native element count.
        (Nil, "nullptr")
      case BvFieldType.Enum(enumCefName) =>
        val enumFqn = jniName(enumCefName)
        (
          List(
            s"""auto ${varPrefix}_cls = FindClassCached(env, "$enumFqn");""",
            s"""auto ${varPrefix}_of = env->GetStaticMethodID(${varPrefix}_cls, "of", "(J)L$enumFqn;");""",
            s"""auto $varPrefix = env->CallStaticObjectMethod(${varPrefix}_cls, ${varPrefix}_of, static_cast<jlong>($cExpr));"""
          ),
          varPrefix
        )
      case BvFieldType.OpaquePtr =>
        (
          List(
            s"""auto ${varPrefix}_cls = FindClassCached(env, "${Naming.nativePointerInternalName}");""",
            s"""auto ${varPrefix}_ctor = env->GetMethodID(${varPrefix}_cls, "<init>", "(J)V");""",
            s"""auto $varPrefix = env->NewObject(${varPrefix}_cls, ${varPrefix}_ctor, to_jlong($cExpr));"""
          ),
          varPrefix
        )
      case BvFieldType.NestedStruct(nestedCef) =>
        val nestedFields = byValueFields(nestedCef)
        val nestedBuilds = nestedFields.map { f =>
          buildCtorExpr(f.typ, s"$cExpr.${f.cName}", s"${varPrefix}_${f.cName}")
        }
        val nestedPre  = nestedBuilds.flatMap(_._1)
        val nestedArgs = nestedBuilds.map(_._2)
        val nestedFqn  = jniName(nestedCef)
        val nestedSig  = s"(${nestedFields.map(f => bvJniSig(f.typ)).mkString})V"
        (
          nestedPre ++ List(
            s"""auto ${varPrefix}_cls = FindClassCached(env, "$nestedFqn");""",
            s"""auto ${varPrefix}_ctor = env->GetMethodID(${varPrefix}_cls, "<init>", "$nestedSig");""",
            s"""auto $varPrefix = ${fmtNewObject(s"${varPrefix}_cls", s"${varPrefix}_ctor", nestedArgs)};"""
          ),
          varPrefix
        )
    }

  def byValueCtorFromPtr(cefName: String, ptrName: String): (String, List[String], List[String]) = {
    val fields    = byValueFields(cefName)
    val baseToken = sanitiseIdent(ptrName)
    val builds    = fields.map { f =>
      buildCtorExpr(f.typ, s"($ptrName)->${f.cName}", s"_bv_${baseToken}_${f.cName}")
    }
    val sig      = s"(${fields.map(f => bvJniSig(f.typ)).mkString})V"
    val argsList = builds.map(_._2)
    val preLines = builds.flatMap(_._1)
    (sig, argsList, preLines)
  }

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
        ) ++ nestedFields.map { nf =>
          val fieldRef = s"$dest$accessor$cName.${nf.cName}"
          nf.typ match {
            case BvFieldType.Long =>
              s"""    $fieldRef = from_jlong<decltype($fieldRef)>(env->GetLongField($v, env->GetFieldID(${v}c, "${nf.javaName}", "J")));"""
            case _ =>
              s"""    $fieldRef = static_cast<decltype($fieldRef)>(${bvGetAndCast(
                  nf.typ,
                  v,
                  s"${v}c",
                  nf.javaName
                )});"""
          }
        } ++ List("}")
      case BvField(cName, javaName, BvFieldType.String) =>
        val v = s"_${prefix}_$cName"
        List(
          s"""jstring $v = (jstring)env->GetObjectField($javaObj, env->GetFieldID($clsVar, "$javaName", "Ljava/lang/String;"));""",
          s"""CefStringSetFromJString(env, $v, &$dest$accessor$cName);"""
        )
      case BvField(cName, javaName, BvFieldType.CStringArray(mutable)) =>
        val sig         = bvJniSig(BvFieldType.CStringArray(mutable))
        val jListVar    = s"_${prefix}_${cName}_list"
        val storageVar  = s"_${prefix}_${cName}_storage"
        val ptrsVar     = s"_${prefix}_${cName}_ptrs"
        val arrVar      = s"_${prefix}_${cName}_arr"
        val converterFn = if (mutable) "JavaListToCStringArray" else "JavaListToConstCStringArray"
        val fieldRef    = s"$dest$accessor$cName"
        val ptrVecT     = if (mutable) "char*" else "const char*"
        List(
          s"""auto $jListVar = env->GetObjectField($javaObj, env->GetFieldID($clsVar, "$javaName", "$sig"));""",
          s"""std::vector<std::string> $storageVar;""",
          s"""std::vector<$ptrVecT> $ptrsVar;""",
          s"""auto $arrVar = $converterFn(env, $jListVar, $storageVar, $ptrsVar);""",
          s"""$fieldRef = static_cast<decltype($fieldRef)>($arrVar);"""
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
      case BvField(cName, javaName, BvFieldType.OpaquePtr) =>
        val sig      = bvJniSig(BvFieldType.OpaquePtr)
        val v        = s"_${prefix}_$cName"
        val fieldRef = s"$dest$accessor$cName"
        List(
          s"""auto $v = env->GetObjectField($javaObj, env->GetFieldID($clsVar, "$javaName", "$sig"));""",
          s"""$fieldRef = $v ? from_jlong<decltype($fieldRef)>(env->GetLongField($v, env->GetFieldID(env->GetObjectClass($v), "address", "J"))) : nullptr;"""
        )
      case BvField(cName, javaName, BvFieldType.Long) =>
        val fieldRef = s"$dest$accessor$cName"
        val copy     =
          s"""$fieldRef = from_jlong<decltype($fieldRef)>(env->GetLongField($javaObj, env->GetFieldID($clsVar, "$javaName", "J")));"""
        if (cefName == "cef_main_args_t" && cName == "instance")
          List(
            copy,
            "#ifdef _WIN32",
            s"if (!$fieldRef) $fieldRef = ::GetModuleHandleW(nullptr);",
            "#endif"
          )
        else List(copy)
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

  private def bvCopyCStructToJava(
      cefName: String,
      srcPtr: String,
      javaObj: String,
      clsVar: String,
      prefix: String
  ): List[String] = {
    val fields = byValueFields(cefName)
    fields.flatMap {
      case BvField(cName, javaName, BvFieldType.NestedStruct(nestedCef)) =>
        val nestedPrefix                 = s"_${prefix}_$cName"
        val nestedSig                    = bvJniSig(BvFieldType.NestedStruct(nestedCef))
        val nestedPtr                    = s"&(($srcPtr)->$cName)"
        val (ctorSig, ctorArgs, ctorPre) = byValueCtorFromPtr(nestedCef, nestedPtr)
        val nClsVar                      = s"${nestedPrefix}_cls"
        val nCtorVar                     = s"${nestedPrefix}_ctor"
        val nObjVar                      = s"${nestedPrefix}_obj"
        ctorPre ++ List(
          s"""auto $nClsVar = FindClassCached(env, "${jniName(nestedCef)}");""",
          s"""auto $nCtorVar = env->GetMethodID($nClsVar, "<init>", "$ctorSig");""",
          s"""auto $nObjVar = ${fmtNewObject(nClsVar, nCtorVar, ctorArgs)};""",
          s"""env->SetObjectField($javaObj, env->GetFieldID($clsVar, "$javaName", "$nestedSig"), $nObjVar);""",
          s"""if ($nObjVar) env->DeleteLocalRef($nObjVar);"""
        )
      case BvField(cName, javaName, BvFieldType.String) =>
        val jv = s"_${prefix}_${cName}_jstr"
        List(
          s"""auto $jv = CefStringToJStringAuto(env, ($srcPtr)->$cName);""",
          s"""env->SetObjectField($javaObj, env->GetFieldID($clsVar, "$javaName", "Ljava/lang/String;"), $jv);""",
          s"""if ($jv) env->DeleteLocalRef($jv);"""
        )
      case BvField(cName, javaName, BvFieldType.CStringArray(_)) =>
        List(
          s"""env->SetObjectField($javaObj, env->GetFieldID($clsVar, "$javaName", "Ljava/util/List;"), nullptr);"""
        )
      case BvField(cName, javaName, BvFieldType.Enum(enumCefName)) =>
        val enumFqn = jniName(enumCefName)
        val eClsVar = s"_${prefix}_${cName}_cls"
        val eOfVar  = s"_${prefix}_${cName}_of"
        val eObjVar = s"_${prefix}_${cName}_obj"
        val sig     = bvJniSig(BvFieldType.Enum(enumCefName))
        List(
          s"""auto $eClsVar = FindClassCached(env, "$enumFqn");""",
          s"""auto $eOfVar = env->GetStaticMethodID($eClsVar, "of", "(J)L$enumFqn;");""",
          s"""auto $eObjVar = env->CallStaticObjectMethod($eClsVar, $eOfVar, static_cast<jlong>(($srcPtr)->$cName));""",
          s"""env->SetObjectField($javaObj, env->GetFieldID($clsVar, "$javaName", "$sig"), $eObjVar);""",
          s"""if ($eObjVar) env->DeleteLocalRef($eObjVar);"""
        )
      case BvField(cName, javaName, BvFieldType.OpaquePtr) =>
        val npClsVar  = s"_${prefix}_${cName}_np_cls"
        val npCtorVar = s"_${prefix}_${cName}_np_ctor"
        val npObjVar  = s"_${prefix}_${cName}_np_obj"
        val sig       = bvJniSig(BvFieldType.OpaquePtr)
        List(
          s"""auto $npClsVar = FindClassCached(env, "${Naming.nativePointerInternalName}");""",
          s"""auto $npCtorVar = env->GetMethodID($npClsVar, "<init>", "(J)V");""",
          s"""auto $npObjVar = (($srcPtr)->$cName) ? env->NewObject($npClsVar, $npCtorVar, to_jlong(($srcPtr)->$cName)) : nullptr;""",
          s"""env->SetObjectField($javaObj, env->GetFieldID($clsVar, "$javaName", "$sig"), $npObjVar);""",
          s"""if ($npObjVar) env->DeleteLocalRef($npObjVar);"""
        )
      case BvField(cName, javaName, BvFieldType.Long) =>
        List(
          s"""env->SetLongField($javaObj, env->GetFieldID($clsVar, "$javaName", "J"), to_jlong(($srcPtr)->$cName));"""
        )
      case BvField(cName, javaName, BvFieldType.Int) =>
        List(
          s"""env->SetIntField($javaObj, env->GetFieldID($clsVar, "$javaName", "I"), static_cast<jint>(($srcPtr)->$cName));"""
        )
      case BvField(cName, javaName, BvFieldType.Char) =>
        List(
          s"""env->SetCharField($javaObj, env->GetFieldID($clsVar, "$javaName", "C"), static_cast<jchar>(($srcPtr)->$cName));"""
        )
      case BvField(cName, javaName, BvFieldType.Float) =>
        List(
          s"""env->SetFloatField($javaObj, env->GetFieldID($clsVar, "$javaName", "F"), static_cast<jfloat>(($srcPtr)->$cName));"""
        )
      case BvField(cName, javaName, BvFieldType.Double) =>
        List(
          s"""env->SetDoubleField($javaObj, env->GetFieldID($clsVar, "$javaName", "D"), static_cast<jdouble>(($srcPtr)->$cName));"""
        )
    }
  }

  def bvWriteBackLines(cefName: String, ptrName: String, javaObj: String, clsVar: String): List[String] =
    bvCopyCStructToJava(cefName, ptrName, javaObj, clsVar, "wb")

  def bvWriteBackToNativeLines(cefName: String, ptrName: String, javaObj: String, clsVar: String): List[String] =
    bvCopyJavaToCStruct(cefName, s"($ptrName)", "->", javaObj, clsVar, "wbn")

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
        throw IllegalArgumentException(s"Nested struct in ByValueArray not supported: $cefName")
      case BvField(_, _, BvFieldType.String) =>
        throw IllegalArgumentException(s"String field in ByValueArray not supported: $cefName")
      case BvField(_, _, BvFieldType.CStringArray(_)) =>
        throw IllegalArgumentException(s"C string array field in ByValueArray not supported: $cefName")
      case BvField(_, _, BvFieldType.Enum(_)) =>
        throw IllegalArgumentException(s"Enum field in ByValueArray not supported: $cefName")
      case BvField(_, _, BvFieldType.OpaquePtr) =>
        throw IllegalArgumentException(s"Opaque pointer field in ByValueArray not supported: $cefName")
      case BvField(cName, _, typ) => bvCastToJni(typ, s"$ptrName[$idxVar].$cName")
    }.mkString(", ")
  }

  def dataStructCtorInfo(cefName: String): (String, List[String], List[String]) =
    dataStructs.get(cefName) match {
      case Some(_) => byValueCtorFromPtr(cefName, "(&result)")
      case None    => ("()V", Nil, Nil)
    }

  def byValueLocalRefs(cefName: String): Int =
    dataStructs.get(cefName) match {
      case Some(ds) =>
        ds.fields.filterNot(isSizeField).map { f =>
          f.typ match {
            case CType.DataStruct(nested) =>
              3 + byValueLocalRefs(nested)
            case CType.JString                                => 1
            case CType.CStringArray | CType.ConstCStringArray => 1
            case CType.Enum(_)                                => 3
            case CType.OpaquePtr                              => 3
            case _                                            => 0
          }
        }.sum
      case None => 0
    }

  def byValueWriteBackRefs(cefName: String): Int =
    dataStructs.get(cefName) match {
      case Some(ds) =>
        ds.fields.filterNot(isSizeField).map { f =>
          f.typ match {
            case CType.DataStruct(_)                          => 2
            case CType.JString                                => 1
            case CType.CStringArray | CType.ConstCStringArray => 1
            case CType.Enum(_)                                => 2
            case CType.OpaquePtr                              => 1
            case _                                            => 0
          }
        }.sum
      case None => 0
    }
}

object JniCppByValueCodeGen {
  enum BvFieldType {
    case Int, Float, Double, Long, Char, String
    case CStringArray(mutable: Boolean)
    case Enum(cefName: scala.Predef.String)
    case NestedStruct(cefName: scala.Predef.String)
    case OpaquePtr
  }

  case class BvField(cName: String, javaName: String, typ: BvFieldType)
}
