package net.kurobako.cef4j.codegen

import JniCppByValueCodeGen.*

// Generates C++ trampoline functions for handler structs (Java -> native callbacks).
class JniCppHandlerTrampolineGen(
    handlerNames: Set[String],
    scopedNames: Set[String],
    bv: JniCppByValueCodeGen
)(using Naming.Context) {

  private def jniName(cefName: String): String =
    Naming.javaInternalName(Naming.fullyQualifiedJavaName(cefName))

  private def jniMutableName(cefName: String): String =
    Naming.javaInternalName(Naming.fullyQualifiedMutableName(cefName))

  private def isHandlerPtr(ct: CType): Boolean = ct match {
    case CType.ObjectPtr(name) => handlerNames.contains(name)
    case CType.Ptr(inner)      => handlerNames.contains(inner.stripPrefix("_"))
    case _                     => false
  }

  private def addRefExpr(ptr: String): String =
    s"{ auto* _b = reinterpret_cast<cef_base_ref_counted_t*>($ptr); _b->add_ref(_b); }"

  private val PlatformHandleTypes = Set(
    "cef_window_handle_t",
    "cef_cursor_handle_t",
    "cef_event_handle_t",
    "cef_platform_thread_id_t",
    "cef_platform_thread_handle_t",
    "HWND",
    "NSView*",
    "unsigned long"
  )

  private def outPrimInfo(inner: CType): (String, String, String) = inner match {
    case CType.Long | CType.SizeT => (Naming.cType(inner), "jlong", "Long")
    case CType.Float              => ("float", "jfloat", "Float")
    case CType.Double             => ("double", "jdouble", "Double")
    case _                        => (Naming.cType(inner), "jint", "Int")
  }

  def renderHandlerTrampoline(structName: String, fn: FnPtr, wrapperName: String): String = {
    val cParams = (s"$structName* self" :: fn.params.map { p =>
      val cType = if (p.rawCType.nonEmpty) p.rawCType
      else {
        val baseType = Naming.cType(p.typ)
        if (p.isConst && !baseType.startsWith("const ")) s"const $baseType" else baseType
      }
      s"$cType ${p.name}"
    }).mkString(", ")
    val retC = fn.ret match {
      case CType.DataStruct(name) => name
      case other                  => Naming.cType(other)
    }

    val javaMethodName    = Naming.javaMethodName(fn)
    val paramSigs         = fn.visibleParams.map(p => Naming.jniSig(p.typ)).mkString
    val handlerPtrCefName = fn.ret match {
      case CType.ObjectPtr(name) if isHandlerPtr(fn.ret) => Some(name)
      case CType.Ptr(inner) if isHandlerPtr(fn.ret)      => Some(inner.stripPrefix("_"))
      case _                                             => None
    }
    val retSig = handlerPtrCefName match {
      case Some(_) => "Ljava/util/Optional;"
      case None    => Naming.jniSig(fn.ret)
    }
    val jniMethodSig = s"($paramSigs)$retSig"

    val defaultRetExpr = defaultNativeReturn(fn.ret, fn.metaAttrs)
    val earlyReturn    = if (fn.ret == CType.Void) "return;" else s"return $defaultRetExpr;"

    val conversions  = fn.params.zipWithIndex.map { case (p, i) => convertNativeToJni(p, fn.params, i) }
    val preCallLines = conversions.flatMap(_._1)
    val jniArgExprs  =
      fn.params.zip(conversions).collect {
        case (p, (_, expr, _)) if p.typ match {
              case CType.BufferSize(_) => false
              case _                   => true
            } => expr
      }
    val postCallLines = conversions.flatMap(_._3)

    val preCall  = if (preCallLines.nonEmpty) preCallLines.map(l => s"        $l").mkString("\n") + "\n" else ""
    val postCall = if (postCallLines.nonEmpty) "\n" + postCallLines.map(l => s"        $l").mkString("\n") else ""

    val argsStr = if (jniArgExprs.nonEmpty) ", " + jniArgExprs.mkString(", ") else ""

    val (callType, jniRetType) = fn.ret match {
      case CType.Void                              => ("Void", "")
      case CType.Bool                              => ("Boolean", "jboolean")
      case CType.Int | CType.UInt | CType.Char     => ("Int", "jint")
      case CType.Enum(_)                           => ("Object", "jobject")
      case _ if handlerPtrCefName.isDefined        => ("Object", "jobject")
      case CType.ObjectPtr(_)                      => ("Object", "jobject")
      case CType.Long | CType.SizeT | CType.Ptr(_) => ("Long", "jlong")
      case CType.OpaquePtr                         => ("Object", "jobject")
      case CType.Float                             => ("Float", "jfloat")
      case CType.Double                            => ("Double", "jdouble")
      case _                                       => ("Object", "jobject")
    }

    val callAndReturn = if (fn.ret == CType.Void) {
      s"""        env->CallVoidMethod(h->javaHandler, mid$argsStr);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }"""
    } else if (handlerPtrCefName.isDefined) {
      val cefName     = handlerPtrCefName.get
      val javaName    = Naming.structToJavaName(cefName)
      val factoryName = s"Create_Jni$javaName"
      val callExpr    = s"(jobject)env->CallObjectMethod(h->javaHandler, mid$argsStr)"
      s"""        auto jResult = $callExpr;
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return nullptr; }
        $retC nativeResult = nullptr;
        if (jResult) {
            auto _optCls = env->FindClass("java/util/Optional");
            auto _isPresentMid = env->GetMethodID(_optCls, "isPresent", "()Z");
            if (env->CallBooleanMethod(jResult, _isPresentMid) == JNI_TRUE) {
                auto _getMid = env->GetMethodID(_optCls, "get", "()Ljava/lang/Object;");
                auto _handlerObj = env->CallObjectMethod(jResult, _getMid);
                nativeResult = $factoryName(env, _handlerObj);
            }
        }
        env->PopLocalFrame(nullptr);
        return nativeResult;"""
    } else if (fn.ret == CType.Bool) {
      val callExpr = s"env->CallBooleanMethod(h->javaHandler, mid$argsStr)"
      s"""        auto jResult = $callExpr;
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); $earlyReturn }$postCall
        env->PopLocalFrame(nullptr);
        return jResult;"""
    } else {
      val callExpr = if (callType == "Object")
        s"($jniRetType)env->CallObjectMethod(h->javaHandler, mid$argsStr)"
      else
        s"env->Call${callType}Method(h->javaHandler, mid$argsStr)"
      val nativeConv = convertJniReturnToNative(fn.ret, "jResult")
      val addRefLine = ""
      s"""        auto jResult = $callExpr;
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); $earlyReturn }$postCall
        $retC nativeResult = $nativeConv;$addRefLine
        env->PopLocalFrame(nullptr);
        return nativeResult;"""
    }

    val popAndReturn = if (fn.ret == CType.Void) {
      s"""$postCall
        env->PopLocalFrame(nullptr);"""
    } else ""

    val frameCapacity = localRefCount(fn)

    s"""    static $retC CEF_CALLBACK _${fn.name}($cParams) {
        auto* h = reinterpret_cast<Jni$wrapperName*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame($frameCapacity) < 0) { $earlyReturn }
$preCall        auto cls = env->GetObjectClass(h->javaHandler);
        auto mid = env->GetMethodID(cls, "$javaMethodName", "$jniMethodSig");
        if (!mid) { env->PopLocalFrame(nullptr); $earlyReturn }
$callAndReturn$popAndReturn
    }"""
  }

  // Convert a native C param to its JNI equivalent for handler trampolines.
  private def convertNativeToJni(
      p: Param,
      allParams: List[Param],
      idx: Int
  ): (List[String], String, List[String]) = {
    val jName = s"j_${p.name}"
    p.typ match {
      case CType.ObjectPtr(cefName) =>
        val javaFqn  = jniName(cefName)
        val isScoped = scopedNames.contains(cefName)
        val addRef   =
          if (isScoped) Nil
          else List(s"""if (_p_${p.name}) ${addRefExpr(s"_p_${p.name}")}""")
        val pre = List(s"$cefName* _p_${p.name} = ${p.name};") ++ addRef ++ List(
          s"""auto ${jName}_cls = env->FindClass("$javaFqn$$NativePeer");""",
          s"""auto ${jName}_ctor = env->GetMethodID(${jName}_cls, "<init>", "(J)V");""",
          s"""auto $jName = _p_${p.name} ? env->NewObject(${jName}_cls, ${jName}_ctor, reinterpret_cast<jlong>(_p_${p.name})) : nullptr;"""
        )
        (pre, jName, Nil)
      case CType.OutObjectPtr(cefName) if handlerNames.contains(cefName) =>
        val javaName    = Naming.structToJavaName(cefName)
        val factoryName = s"Create_Jni$javaName"
        val pre         = List(
          s"""auto ${jName}_ar_cls = env->FindClass("java/util/concurrent/atomic/AtomicReference");""",
          s"""auto ${jName}_ar_ctor = env->GetMethodID(${jName}_ar_cls, "<init>", "(Ljava/lang/Object;)V");""",
          s"jobject ${jName}_init = nullptr;",
          s"""auto $jName = env->NewObject(${jName}_ar_cls, ${jName}_ar_ctor, ${jName}_init);"""
        )
        val post = List(
          s"if (${p.name}) {",
          s"""    auto ${jName}_get = env->GetMethodID(${jName}_ar_cls, "get", "()Ljava/lang/Object;");""",
          s"    auto ${jName}_new = env->CallObjectMethod($jName, ${jName}_get);",
          s"    if (${jName}_new) {",
          s"        *${p.name} = $factoryName(env, ${jName}_new);",
          s"    } else if (!${jName}_new && ${jName}_new != ${jName}_init) {",
          s"        *${p.name} = nullptr;",
          s"    }",
          s"}"
        )
        (pre, jName, post)
      case CType.OutObjectPtr(cefName) =>
        val javaFqn = jniName(cefName)
        val pre     = List(
          s"""auto ${jName}_ar_cls = env->FindClass("java/util/concurrent/atomic/AtomicReference");""",
          s"""auto ${jName}_ar_ctor = env->GetMethodID(${jName}_ar_cls, "<init>", "(Ljava/lang/Object;)V");""",
          s"""auto ${jName}_peer_cls = env->FindClass("$javaFqn$$NativePeer");""",
          s"""auto ${jName}_peer_ctor = env->GetMethodID(${jName}_peer_cls, "<init>", "(J)V");""",
          s"jobject ${jName}_init = nullptr;",
          s"if (${p.name} && *${p.name}) {"
        ) ++ (if (scopedNames.contains(cefName)) Nil
              else List(s"    ${addRefExpr(s"*${p.name}")}")) ++ List(
          s"""    ${jName}_init = env->NewObject(${jName}_peer_cls, ${jName}_peer_ctor, reinterpret_cast<jlong>(*${p.name}));""",
          s"}",
          s"""auto $jName = env->NewObject(${jName}_ar_cls, ${jName}_ar_ctor, ${jName}_init);"""
        )
        val post = List(
          s"if (${p.name}) {",
          s"""    auto ${jName}_get = env->GetMethodID(${jName}_ar_cls, "get", "()Ljava/lang/Object;");""",
          s"    auto ${jName}_new = env->CallObjectMethod($jName, ${jName}_get);",
          s"    if (${jName}_new && ${jName}_new != ${jName}_init) {",
          s"""        auto ${jName}_fid = env->GetFieldID(${jName}_peer_cls, "nativePtr", "J");""",
          s"        jlong ${jName}_ptr = env->GetLongField(${jName}_new, ${jName}_fid);",
          s"        *${p.name} = reinterpret_cast<$cefName*>(${jName}_ptr);",
          s"    } else if (!${jName}_new) {",
          s"        *${p.name} = nullptr;",
          s"    }",
          s"}"
        )
        (pre, jName, post)
      case CType.OutPrimitivePtr(inner) =>
        val (cPrim, jniPrim, jniInfix) = outPrimInfo(inner)
        val pre                        = List(
          s"${jniPrim}Array $jName = env->New${jniInfix}Array(1);",
          s"if (${p.name}) { $jniPrim _v = static_cast<$jniPrim>(*${p.name}); env->Set${jniInfix}ArrayRegion($jName, 0, 1, &_v); }"
        )
        val post = List(
          s"if (${p.name}) { $jniPrim _v; env->Get${jniInfix}ArrayRegion($jName, 0, 1, &_v); *${p.name} = static_cast<$cPrim>(_v); }"
        )
        (pre, jName, post)
      case CType.OpaquePtr =>
        val pre = List(
          s"""auto ${jName}_cls = env->FindClass("${Naming.nativePointerInternalName}");""",
          s"""auto ${jName}_ctor = env->GetMethodID(${jName}_cls, "<init>", "(J)V");""",
          s"""auto $jName = env->NewObject(${jName}_cls, ${jName}_ctor, reinterpret_cast<jlong>(${p.name}));"""
        )
        (pre, jName, Nil)
      case CType.Ptr(_) =>
        (Nil, s"reinterpret_cast<jlong>(${p.name})", Nil)
      case CType.ObjectPtrArray(cefName) =>
        val javaFqn   = jniName(cefName)
        val isScoped  = scopedNames.contains(cefName)
        val countExpr = if (idx > 0) allParams(idx - 1).name else "0"
        val pre       = List(
          s"""auto ${jName}_cls = env->FindClass("$javaFqn$$NativePeer");""",
          s"""auto ${jName}_ctor = env->GetMethodID(${jName}_cls, "<init>", "(J)V");""",
          s"jsize ${jName}_len = static_cast<jsize>($countExpr);",
          s"""auto $jName = env->NewObjectArray(${jName}_len, ${jName}_cls, nullptr);""",
          s"for (jsize _i = 0; _i < ${jName}_len; _i++) {",
          s"    $cefName* _elem = ${p.name}[_i];"
        ) ++ (if (isScoped) Nil else List(s"    if (_elem) ${addRefExpr("_elem")}")) ++ List(
          s"    auto _jelem = _elem ? env->NewObject(${jName}_cls, ${jName}_ctor, reinterpret_cast<jlong>(_elem)) : nullptr;",
          s"    env->SetObjectArrayElement($jName, _i, _jelem);",
          s"}"
        )
        (pre, jName, Nil)
      case CType.JString =>
        (List(s"auto $jName = CefStringToJString(env, ${p.name});"), jName, Nil)
      case CType.Bool =>
        (Nil, s"static_cast<jboolean>(${p.name})", Nil)
      case CType.Int | CType.UInt =>
        (Nil, s"static_cast<jint>(${p.name})", Nil)
      case CType.Char =>
        (Nil, s"static_cast<jchar>(${p.name})", Nil)
      case CType.Long if PlatformHandleTypes.exists(p.rawCType.contains) =>
        (Nil, s"(jlong)(${p.name})", Nil)
      case CType.Long | CType.SizeT =>
        (Nil, s"static_cast<jlong>(${p.name})", Nil)
      case CType.Float =>
        (Nil, s"static_cast<jfloat>(${p.name})", Nil)
      case CType.Double =>
        (Nil, s"static_cast<jdouble>(${p.name})", Nil)
      case CType.Enum(name) =>
        val javaFqn = jniName(name)
        val pre     = List(
          s"""auto ${jName}_cls = env->FindClass("$javaFqn");""",
          s"""auto ${jName}_from = env->GetStaticMethodID(${jName}_cls, "of", "(J)L$javaFqn;");""",
          s"""auto $jName = env->CallStaticObjectMethod(${jName}_cls, ${jName}_from, static_cast<jlong>(${p.name}));"""
        )
        (pre, jName, Nil)
      case CType.OutInt =>
        val pre = List(
          s"jintArray $jName = env->NewIntArray(1);",
          s"if (${p.name}) { jint _v = *${p.name}; env->SetIntArrayRegion($jName, 0, 1, &_v); }"
        )
        val post = List(
          s"if (${p.name}) { jint _v; env->GetIntArrayRegion($jName, 0, 1, &_v); *${p.name} = _v; }"
        )
        (pre, jName, post)
      case CType.OutBool =>
        val pre = List(
          s"jbooleanArray $jName = env->NewBooleanArray(1);",
          s"if (${p.name}) { jboolean _v = static_cast<jboolean>(*${p.name}); env->SetBooleanArrayRegion($jName, 0, 1, &_v); }"
        )
        val post = List(
          s"if (${p.name}) { jboolean _v; env->GetBooleanArrayRegion($jName, 0, 1, &_v); *${p.name} = static_cast<bool>(_v); }"
        )
        (pre, jName, post)
      case CType.StringList =>
        (List(s"auto $jName = CefStringListToJavaList(env, ${p.name});"), jName, Nil)
      case CType.StringMap =>
        (List(s"auto $jName = CefStringMapToJavaMap(env, ${p.name});"), jName, Nil)
      case CType.StringMultimap =>
        (List(s"auto $jName = CefStringMultimapToJavaMap(env, ${p.name});"), jName, Nil)
      case CType.DataStruct(_) =>
        (Nil, s"reinterpret_cast<jlong>(${p.name})", Nil)
      case CType.ByValueIn(cefName) =>
        val javaFqn                            = jniName(cefName)
        val (ctorSig, ctorArgsList, nestedPre) = bv.byValueCtorFromPtr(cefName, p.name)
        val sizeSet                            = bv.bvSetNativeSize(cefName, jName, s"${jName}_cls", p.name)
        val pre                                = nestedPre ++ List(
          s"""auto ${jName}_cls = env->FindClass("$javaFqn");""",
          s"""auto ${jName}_ctor = env->GetMethodID(${jName}_cls, "<init>", "$ctorSig");""",
          s"""auto $jName = ${bv.fmtNewObject(s"${jName}_cls", s"${jName}_ctor", ctorArgsList, p.name)};"""
        ) ++ (if (sizeSet.nonEmpty) List(sizeSet) else Nil)
        (pre, jName, Nil)
      case CType.ByValueOut(cefName) =>
        val mutableFqn                         = jniMutableName(cefName)
        val (ctorSig, ctorArgsList, nestedPre) = bv.byValueCtorFromPtr(cefName, p.name)
        val sizeSet                            = bv.bvSetNativeSize(cefName, jName, s"${jName}_cls", p.name)
        val pre                                = nestedPre ++ List(
          s"""auto ${jName}_cls = env->FindClass("$mutableFqn");""",
          s"""auto ${jName}_ctor = env->GetMethodID(${jName}_cls, "<init>", "$ctorSig");""",
          s"""auto $jName = ${bv.fmtNewObject(s"${jName}_cls", s"${jName}_ctor", ctorArgsList, p.name)};"""
        ) ++ (if (sizeSet.nonEmpty) List(sizeSet) else Nil)
        val post = List(s"if (${p.name} && $jName) {") ++
          bv.bvWriteBackLines(cefName, p.name, jName, s"${jName}_cls").map(l => s"    $l") ++
          List("}")
        (pre, jName, post)
      case CType.ByValueArray(cefName) =>
        val javaFqn         = jniName(cefName)
        val (ctorSig, _, _) = bv.byValueCtorFromPtr(cefName, "_dummy")
        val countParam      = if (idx > 0) {
          val prev = allParams(idx - 1)
          if (
            (prev.typ == CType.SizeT || prev.typ == CType.Int) &&
            prev.name.toLowerCase.contains("count")
          ) Some(prev.name)
          else None
        } else None
        val countExpr  = countParam.getOrElse("0")
        val fieldExprs = bv.byValueArrayFieldExpr(cefName, p.name, "_i")
        val pre        = List(
          s"""auto ${jName}_cls = env->FindClass("$javaFqn");""",
          s"""auto ${jName}_ctor = env->GetMethodID(${jName}_cls, "<init>", "$ctorSig");""",
          s"""jint ${jName}_len = static_cast<jint>($countExpr);""",
          s"""auto $jName = env->NewObjectArray(${jName}_len, ${jName}_cls, nullptr);""",
          s"for (jint _i = 0; _i < ${jName}_len; _i++) {",
          s"    auto _elem = env->NewObject(${jName}_cls, ${jName}_ctor, $fieldExprs);",
          s"    env->SetObjectArrayElement($jName, _i, _elem);",
          s"    env->DeleteLocalRef(_elem);",
          s"}"
        )
        (pre, jName, Nil)
      case CType.Buffer(sizeParam) =>
        val sizeExpr = allParams.find(_.name == sizeParam).map(_.name).getOrElse("0")
        val isConst  = p.rawCType.contains("const")
        val castExpr =
          if (isConst) s"const_cast<void*>(static_cast<const void*>(${p.name}))" else s"static_cast<void*>(${p.name})"
        val pre = List(
          s"jobject $jName = ($sizeExpr > 0 && ${p.name}) ? env->NewDirectByteBuffer($castExpr, static_cast<jlong>($sizeExpr)) : nullptr;"
        )
        (pre, jName, Nil)
      case CType.BufferSize(_) =>
        (Nil, "", Nil)
      case CType.PixelBuffer =>
        val widthParam  = allParams.find(_.name == "width").map(_.name)
        val heightParam = allParams.find(_.name == "height").map(_.name)
        (widthParam, heightParam) match {
          case (Some(w), Some(h)) =>
            val pre = List(
              s"jlong ${jName}_len = static_cast<jlong>($w) * static_cast<jlong>($h) * 4;",
              s"jobject $jName = (${p.name} && ${jName}_len > 0) ? env->NewDirectByteBuffer(const_cast<void*>(${p.name}), ${jName}_len) : nullptr;"
            )
            (pre, jName, Nil)
          case _ =>
            (List(s"jobject $jName = nullptr;"), jName, Nil)
        }
      case CType.Void =>
        (Nil, "0", Nil)
      case other =>
        throw new RuntimeException(s"convertNativeToJni: unhandled type $other for param ${p.name}")
    }
  }

  def localRefCount(fn: FnPtr): Int = {
    val fixed     = 1
    val paramRefs = fn.params.zipWithIndex.map { case (p, idx) =>
      p.typ match {
        case CType.ObjectPtr(_)                                        => 3
        case CType.OutObjectPtr(_)                                     => 7
        case CType.OpaquePtr                                           => 3
        case CType.Enum(_)                                             => 3
        case CType.JString                                             => 1
        case CType.StringList | CType.StringMap | CType.StringMultimap => 1
        case CType.Buffer(_)                                           => 1
        case CType.PixelBuffer                                         => 1
        case CType.OutPrimitivePtr(_)                                  => 1
        case CType.OutInt                                              => 1
        case CType.OutBool                                             => 1
        case CType.ObjectPtrArray(_)                                   => 3 + 16
        case CType.ByValueIn(cefName)                                  => bv.byValueLocalRefs(cefName) + 3
        case CType.ByValueOut(cefName)   => bv.byValueLocalRefs(cefName) + 3 + bv.byValueWriteBackRefs(cefName)
        case CType.ByValueArray(cefName) => bv.byValueLocalRefs(cefName) + 3 + 8
        case _                           => 0
      }
    }.sum
    val returnRefs = fn.ret match {
      case _ if isHandlerPtr(fn.ret) => 4
      case CType.Enum(_)             => 1
      case CType.ObjectPtr(_)        => 1
      case CType.OpaquePtr           => 1
      case _                         => 0
    }
    fixed + paramRefs + returnRefs + 4
  }

  def convertJniReturnToNative(ct: CType, jniVar: String): String = ct match {
    case CType.Void       => ""
    case CType.Bool       => s"static_cast<bool>($jniVar)"
    case CType.Int        => s"static_cast<int>($jniVar)"
    case CType.UInt       => s"static_cast<unsigned int>($jniVar)"
    case CType.Char       => s"static_cast<char16_t>($jniVar)"
    case CType.Long       => s"static_cast<int64_t>($jniVar)"
    case CType.SizeT      => s"static_cast<size_t>($jniVar)"
    case CType.Float      => s"static_cast<float>($jniVar)"
    case CType.Double     => s"static_cast<double>($jniVar)"
    case CType.Enum(name) =>
      s"static_cast<$name>($jniVar ? env->GetLongField($jniVar, env->GetFieldID(env->GetObjectClass($jniVar), \"value\", \"J\")) : 0)"
    case CType.ObjectPtr(name) =>
      s"""$jniVar ? reinterpret_cast<$name*>(env->GetLongField($jniVar, env->GetFieldID(env->GetObjectClass($jniVar), "nativePtr", "J"))) : nullptr"""
    case CType.Ptr(inner) => s"reinterpret_cast<$inner*>($jniVar)"
    case CType.OpaquePtr  =>
      s"""reinterpret_cast<void*>($jniVar ? env->GetLongField($jniVar, env->GetFieldID(env->GetObjectClass($jniVar), "address", "J")) : 0)"""
    case CType.JString          => jniVar
    case CType.DataStruct(name) =>
      val javaFqn   = jniName(name)
      val bodyLines = bv.bvReadFromJavaLines(name, "_result", jniVar, "_c")
      val inner     = (List(
        s"$name _result = {};",
        s"""if ($jniVar) {""",
        s"""    auto _c = env->FindClass("$javaFqn");"""
      ) ++ bodyLines.map(l => s"    $l") ++ List("}", "return _result;")).mkString("\n        ")
      s"([&]() {\n        $inner\n    })()"
    case _ => jniVar
  }

  def defaultNativeReturn(ct: CType, metaAttrs: List[(String, String)]): String = {
    val metaDefault = metaAttrs.collectFirst { case ("default_retval", v) => v }
    metaDefault match {
      case Some(v) => ct match {
          case CType.Bool       => if (v == "true" || v == "1") "true" else "false"
          case CType.Enum(name) => s"$name::$v"
          case _                => v
        }
      case None => ct match {
          case CType.Void             => ""
          case CType.Bool             => "false"
          case CType.Int              => "0"
          case CType.UInt             => "0"
          case CType.Char             => "0"
          case CType.Long             => "0"
          case CType.SizeT            => "0"
          case CType.Float            => "0.0f"
          case CType.Double           => "0.0"
          case CType.Ptr(_)           => "nullptr"
          case CType.ObjectPtr(_)     => "nullptr"
          case CType.OpaquePtr        => "nullptr"
          case CType.JString          => "nullptr"
          case CType.Enum(_)          => "static_cast<" + Naming.cType(ct) + ">(0)"
          case CType.DataStruct(name) => s"$name{}"
          case _                      => "0"
        }
    }
  }

  def renderHandlerFactory(structName: String, javaName: String): String =
    s"""extern "C" $structName* Create_Jni$javaName(JNIEnv* env, jobject handler) {
    JavaVM* jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<$structName*>(new Jni$javaName(jvm, globalRef));
}"""
}
