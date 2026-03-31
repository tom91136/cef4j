package net.kurobako.cef4j.codegen

import java.nio.file.Files
import java.nio.file.Path

object JniCppCodeGen {

  private val GeneratedBanner = "// GENERATED - do not edit."

  // Parsed data struct declarations — set by Main before codegen runs
  private var dataStructs: Map[String, CefDecl.DataStruct]      = Map.empty
  def setDataStructs(ds: Map[String, CefDecl.DataStruct]): Unit = dataStructs = ds

  // Platform handle types that may be pointers on some platforms
  private val PlatformHandleTypes = Set(
    "cef_window_handle_t",
    "cef_cursor_handle_t",
    "cef_event_handle_t",
    "cef_platform_thread_id_t",
    "cef_platform_thread_handle_t"
  )

  def emit(decl: CefDecl.ObjectStruct, outDir: Path): Unit =
    writeCppFile(outDir, s"${Naming.cefBaseName(decl.name)}_N.cpp", emitToString(decl))

  def emitHandler(decl: CefDecl.HandlerStruct, outDir: Path): Unit =
    writeCppFile(outDir, s"${Naming.cefBaseName(decl.name)}.cpp", emitHandlerToString(decl))

  private def writeCppFile(outDir: Path, fileName: String, content: String): Unit = {
    val file = outDir.resolve(fileName)
    Files.createDirectories(file.getParent)
    Files.writeString(file, content)
  }

  def emitToString(decl: CefDecl.ObjectStruct): String = {
    val includes  = renderIncludes(decl.name, decl.sourceHeader)
    val functions = decl.fns.map(fn => renderObjectFunction(decl.name, fn)).mkString("\n\n")
    renderGeneratedCpp(includes, functions)
  }

  def emitHandlerToString(decl: CefDecl.HandlerStruct): String = {
    val javaName = Naming.structToJavaName(decl.name)
    val includes = renderIncludes(decl.name, decl.sourceHeader)

    val structDef = s"""
// JNI wrapper struct for ${decl.name}
struct Jni${javaName} : public ${decl.name} {
    JavaVM* jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount{1};

    Jni${javaName}(JavaVM* vm, jobject handler) : ${decl.name}{}, jvm(vm) {
        javaHandler = handler;
        InitRefCount<Jni${javaName}, ${decl.name}>(&base);
${decl.fns.map(fn => s"        ${fn.name} = &_${fn.name};").mkString("\n")}
    }
"""

    val trampolines = decl.fns.map(fn => renderHandlerTrampoline(decl.name, fn, javaName)).mkString("\n\n")

    val factoryFn = renderHandlerFactory(decl.name, javaName)

    val hasByValueArray = decl.fns.exists(_.params.exists(_.typ.isInstanceOf[CType.ByValueArray]))
    val vectorInclude   = if (hasByValueArray) "\n#include <vector>" else ""

    renderGeneratedCpp(
      s"""$includes
#include <atomic>$vectorInclude
#include "ref_counted_base.h"
""",
      s"""$structDef
$trampolines
};

$factoryFn"""
    )
  }

  private def renderGeneratedCpp(includes: String, body: String): String =
    s"""$GeneratedBanner
$includes

$body
"""

  private def renderIncludes(cefStructName: String, sourceHeader: String): String = {
    val header = if (sourceHeader.nonEmpty) sourceHeader
    else {
      // Fallback: derive from struct name (may be wrong for grouped headers)
      val headerName = Naming.cefBaseName(cefStructName)
      s"cef_${headerName}_capi.h"
    }
    s"""#include <jni.h>
#include "include/capi/$header"
#include "jni_util.h"
"""
  }

  private def renderObjectFunction(structName: String, fn: FnPtr): String = {
    val javaName  = Naming.structToJavaName(structName)
    val jniSym    = Naming.jniSymbol(structName, fn.name)
    val retJni    = Naming.jniType(fn.ret)
    val jniParams = ("JNIEnv* env" :: "jobject obj" :: "jlong self" :: fn.params.map { p =>
      s"${Naming.jniType(p.typ)} ${p.name}"
    }).mkString(", ")

    val castSelf  = s"    auto* s = reinterpret_cast<$structName*>(self);"
    val nullGuard = s"    if (!s) return${defaultReturn(fn.ret)};"

    // Collect optional params for null-safe conversion
    val optionalParams = fn.metaAttrs.collect {
      case ("optional_param", p) => p
    }.toSet

    // Generate (preCallLines, argExpr, postCallLines) for each param
    val argConversions = fn.params.map { p =>
      val isOpt = optionalParams.contains(p.name)
      p.typ match {
        case CType.OutInt =>
          val tmp = s"_${p.name}_val"
          val pre = List(
            s"    int $tmp = 0;",
            s"    if (${p.name}) { jint _jv; env->GetIntArrayRegion(${p.name}, 0, 1, &_jv); $tmp = _jv; }"
          )
          val post = List(
            s"    if (${p.name}) { jint _jv = $tmp; env->SetIntArrayRegion(${p.name}, 0, 1, &_jv); }"
          )
          (pre, s"&$tmp", post)
        case CType.OutBool =>
          val tmp = s"_${p.name}_val"
          val pre = List(
            s"    int $tmp = 0;",
            s"    if (${p.name}) { jboolean _jv; env->GetBooleanArrayRegion(${p.name}, 0, 1, &_jv); $tmp = _jv == JNI_TRUE ? 1 : 0; }"
          )
          val post = List(
            s"    if (${p.name}) { jboolean _jv = $tmp ? JNI_TRUE : JNI_FALSE; env->SetBooleanArrayRegion(${p.name}, 0, 1, &_jv); }"
          )
          (pre, s"&$tmp", post)
        case CType.ByValueIn(cefName) =>
          // Read-only geometry struct: extract fields from Java object into a local C struct
          val tmp     = s"_${p.name}_val"
          val javaFqn = Naming.fullyQualifiedJavaName(cefName).replace('.', '/')
          val pre     = List(
            s"    $cefName $tmp = {};",
            s"""    if (${p.name}) { jclass _c = env->FindClass("$javaFqn"); ${bvReadFromJavaObject(
                cefName,
                tmp,
                p.name,
                "_c"
              )} }"""
          )
          (pre, s"&$tmp", Nil)
        case CType.ByValueOut(cefName) =>
          // Mutable out-param (CefMutable* object on Java side): extract fields from object into C struct
          val tmp        = s"_${p.name}_val"
          val mutableFqn = Naming.fullyQualifiedMutableName(cefName).replace('.', '/')
          val pre        = List(
            s"    $cefName $tmp = {};",
            s"""    if (${p.name}) { jclass _c = env->FindClass("$mutableFqn"); ${bvReadFromJavaObject(
                cefName,
                tmp,
                p.name,
                "_c"
              )} }"""
          )
          (pre, s"&$tmp", Nil)
        case CType.ByValueArray(_) =>
          // Array of geometry structs for object functions - not common, pass as-is for now
          (Nil, s"nullptr /* TODO: ByValueArray in object function */", Nil)
        case CType.DataStruct(_) =>
          // By-value data struct params: pass as pointer (from jlong)
          val cTypeName = Naming.cType(p.typ)
          (Nil, s"reinterpret_cast<$cTypeName>(${p.name})", Nil)
        case _ =>
          (Nil, convertJniToNative(p, isOpt), Nil)
      }
    }
    val preCallLines  = argConversions.flatMap(_._1)
    val callArgs      = argConversions.map(_._2).mkString(", ")
    val postCallLines = argConversions.flatMap(_._3)

    val fnCall = if (callArgs.isEmpty) {
      s"s->${fn.name}(s)"
    } else {
      s"s->${fn.name}(s, $callArgs)"
    }

    // count_func: pre-call to size arrays (e.g., count_func=identifiers:GetFrameCount)
    val countFuncSetup = fn.metaAttrs.collectFirst { case ("count_func", spec) => spec }
      .flatMap(parseCountFunc)
      .map { case (param, func) =>
        val snakeFunc = Naming.toCamelCase(func).replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase
        s"    size_t ${param}_count = s->$snakeFunc(s);"
      }
      .getOrElse("")

    val preBlock  = if (preCallLines.nonEmpty) preCallLines.mkString("\n") + "\n" else ""
    val postBlock = if (postCallLines.nonEmpty) "\n" + postCallLines.mkString("\n") else ""

    val body = fn.ret match {
      case CType.Void =>
        s"""$castSelf
$nullGuard
$countFuncSetup
$preBlock    $fnCall;$postBlock"""
      case CType.JString =>
        s"""$castSelf
$nullGuard
$preBlock    cef_string_userfree_t result = $fnCall;$postBlock
    if (!result) return nullptr;
    jstring jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;"""
      case CType.Bool =>
        s"""$castSelf
$nullGuard
$preBlock    auto _r = $fnCall;$postBlock
    return _r ? JNI_TRUE : JNI_FALSE;"""
      case CType.Ptr(_) =>
        s"""$castSelf
$nullGuard
$preBlock    auto _r = $fnCall;$postBlock
    return reinterpret_cast<jlong>(_r);"""
      case CType.DataStruct(cefName) =>
        // By-value struct return: construct Java object via JNI
        val javaFqn             = Naming.fullyQualifiedJavaName(cefName).replace('.', '/')
        val (ctorSig, ctorArgs) = dataStructCtorInfo(cefName)
        s"""$castSelf
$nullGuard
$preBlock    $cefName result = $fnCall;$postBlock
    jclass cls = env->FindClass("$javaFqn");
    jmethodID ctor = env->GetMethodID(cls, "<init>", "$ctorSig");
    return env->NewObject(cls, ctor, $ctorArgs);"""
      case _ =>
        s"""$castSelf
$nullGuard
$preBlock    return static_cast<$retJni>($fnCall);"""
    }

    // Clean up empty lines from optional countFuncSetup
    val cleanBody = body.linesIterator.filter(_.trim.nonEmpty).mkString("\n")

    s"""extern "C" JNIEXPORT $retJni JNICALL $jniSym($jniParams) {
$cleanBody
}"""
  }

  private def renderHandlerTrampoline(structName: String, fn: FnPtr, wrapperName: String): String = {
    val cParams = (s"$structName* self" :: fn.params.map { p =>
      // Use raw C type when available for exact ABI match; fall back to reconstructed type
      val cType = if (p.rawCType.nonEmpty) p.rawCType
      else {
        val baseType = Naming.cType(p.typ)
        if (p.isConst && !baseType.startsWith("const ")) s"const $baseType" else baseType
      }
      s"$cType ${p.name}"
    }).mkString(", ")
    val retC = fn.ret match {
      case CType.DataStruct(name) => name // by-value, not pointer
      case other                  => Naming.cType(other)
    }

    // JNI method signature
    val javaMethodName = Naming.toCamelCase(fn.name)
    val paramSigs      = fn.params.map(p => Naming.jniSig(p.typ)).mkString
    val retSig         = Naming.jniSig(fn.ret)
    val jniMethodSig   = s"($paramSigs)$retSig"

    // Default return for error paths
    val defaultRetExpr = defaultNativeReturn(fn.ret, fn.metaAttrs)
    val earlyReturn    = if (fn.ret == CType.Void) "return;" else s"return $defaultRetExpr;"

    // Convert each param: (preCallLines, jniArgExpr, postCallLines)
    val conversions   = fn.params.zipWithIndex.map { case (p, i) => convertNativeToJni(p, fn.params, i) }
    val preCallLines  = conversions.flatMap(_._1)
    val jniArgExprs   = conversions.map(_._2)
    val postCallLines = conversions.flatMap(_._3)

    val preCall  = if (preCallLines.nonEmpty) preCallLines.map(l => s"        $l").mkString("\n") + "\n" else ""
    val postCall = if (postCallLines.nonEmpty) "\n" + postCallLines.map(l => s"        $l").mkString("\n") else ""

    val argsStr = if (jniArgExprs.nonEmpty) ", " + jniArgExprs.mkString(", ") else ""

    // JNI call expression
    val (callType, jniRetType) = fn.ret match {
      case CType.Void                              => ("Void", "")
      case CType.Bool                              => ("Boolean", "jboolean")
      case CType.Int | CType.UInt                  => ("Int", "jint")
      case CType.Enum(_)                           => ("Object", "jobject")
      case CType.Long | CType.SizeT | CType.Ptr(_) => ("Long", "jlong")
      case CType.Float                             => ("Float", "jfloat")
      case CType.Double                            => ("Double", "jdouble")
      case _                                       => ("Object", "jobject")
    }

    val callAndReturn = if (fn.ret == CType.Void) {
      s"""        env->CallVoidMethod(h->javaHandler, mid$argsStr);
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); return; }"""
    } else {
      val callExpr = if (callType == "Object")
        s"($jniRetType)env->CallObjectMethod(h->javaHandler, mid$argsStr)"
      else
        s"env->Call${callType}Method(h->javaHandler, mid$argsStr)"
      val nativeConv = convertJniReturnToNative(fn.ret, "jResult")
      // For ref-counted pointer returns (handler getters), CEF expects the
      // caller to add_ref before returning. The caller will release when done.
      val addRefLine = fn.ret match {
        case CType.Ptr(_) =>
          "\n        if (nativeResult) nativeResult->base.add_ref(&nativeResult->base);"
        case _ => ""
      }
      s"""        $jniRetType jResult = $callExpr;
        if (CheckJNIException(env)) { env->PopLocalFrame(nullptr); $earlyReturn }$postCall
        $retC nativeResult = $nativeConv;$addRefLine
        env->PopLocalFrame(nullptr);
        return nativeResult;"""
    }

    val popAndReturn = if (fn.ret == CType.Void) {
      s"""$postCall
        env->PopLocalFrame(nullptr);"""
    } else ""

    s"""    static $retC CEF_CALLBACK _${fn.name}($cParams) {
        auto* h = reinterpret_cast<Jni$wrapperName*>(self);
        ScopedJNIEnv env(h->jvm);
        if (env->PushLocalFrame(16) < 0) { $earlyReturn }
$preCall        jclass cls = env->GetObjectClass(h->javaHandler);
        jmethodID mid = env->GetMethodID(cls, "$javaMethodName", "$jniMethodSig");
        if (!mid) { env->PopLocalFrame(nullptr); $earlyReturn }
$callAndReturn$popAndReturn
    }"""
  }

  // Convert a native C param to its JNI equivalent for handler trampolines.
  // Returns (preCallLines, jniArgExpr, postCallLines).
  // Takes full params list and index for context-aware conversions (ByValueArray, PixelBuffer).
  private def convertNativeToJni(p: Param, allParams: List[Param], idx: Int): (List[String], String, List[String]) = {
    val jName = s"j_${p.name}"
    p.typ match {
      case CType.Ptr(_) =>
        (Nil, s"reinterpret_cast<jlong>(${p.name})", Nil)
      case CType.JString =>
        (List(s"jstring $jName = CefStringToJString(env, ${p.name});"), jName, Nil)
      case CType.Bool =>
        (Nil, s"(jboolean)(${p.name} ? JNI_TRUE : JNI_FALSE)", Nil)
      case CType.Int =>
        (Nil, s"static_cast<jint>(${p.name})", Nil)
      case CType.UInt =>
        (Nil, s"static_cast<jint>(${p.name})", Nil)
      case CType.Long if PlatformHandleTypes.exists(p.rawCType.contains) =>
        // Platform handle types vary across platforms (pointer on some, integer on others) - use C-style cast
        (Nil, s"(jlong)(${p.name})", Nil)
      case CType.Long =>
        (Nil, s"static_cast<jlong>(${p.name})", Nil)
      case CType.SizeT =>
        (Nil, s"static_cast<jlong>(${p.name})", Nil)
      case CType.Float =>
        (Nil, s"static_cast<jfloat>(${p.name})", Nil)
      case CType.Double =>
        (Nil, s"static_cast<jdouble>(${p.name})", Nil)
      case CType.Enum(name) =>
        val javaFqn      = Naming.fullyQualifiedJavaName(name).replace('.', '/')
        val enumJavaName = Naming.structToJavaName(name)
        val pre          = List(
          s"""jclass ${jName}_cls = env->FindClass("$javaFqn");""",
          s"""jmethodID ${jName}_from = env->GetStaticMethodID(${jName}_cls, "fromLong", "(J)L$javaFqn;");""",
          s"""jobject $jName = env->CallStaticObjectMethod(${jName}_cls, ${jName}_from, static_cast<jlong>(${p.name}));"""
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
          s"if (${p.name}) { jboolean _v = *${p.name} ? JNI_TRUE : JNI_FALSE; env->SetBooleanArrayRegion($jName, 0, 1, &_v); }"
        )
        val post = List(
          s"if (${p.name}) { jboolean _v; env->GetBooleanArrayRegion($jName, 0, 1, &_v); *${p.name} = _v == JNI_TRUE ? 1 : 0; }"
        )
        (pre, jName, post)
      case CType.StringList =>
        (List(s"jobject $jName = CefStringListToJavaList(env, ${p.name});"), jName, Nil)
      case CType.StringMap =>
        (List(s"jobject $jName = CefStringMapToJavaMap(env, ${p.name});"), jName, Nil)
      case CType.StringMultimap =>
        (List(s"jobject $jName = CefStringMultimapToJavaMap(env, ${p.name});"), jName, Nil)
      case CType.DataStruct(_) =>
        (Nil, s"reinterpret_cast<jlong>(${p.name})", Nil)
      case CType.ByValueIn(cefName) =>
        // Const geometry struct pointer → construct Java object
        val javaFqn                        = Naming.fullyQualifiedJavaName(cefName).replace('.', '/')
        val (ctorSig, ctorArgs, nestedPre) = byValueCtorFromPtr(cefName, p.name)
        val pre                            = nestedPre ++ List(
          s"""jclass ${jName}_cls = env->FindClass("$javaFqn");""",
          s"""jmethodID ${jName}_ctor = env->GetMethodID(${jName}_cls, "<init>", "$ctorSig");""",
          s"""jobject $jName = ${p.name} ? env->NewObject(${jName}_cls, ${jName}_ctor, $ctorArgs) : nullptr;"""
        )
        (pre, jName, Nil)
      case CType.ByValueOut(cefName) =>
        // Non-const by-value struct pointer (out-param) → CefMutable* object, with write-back
        val mutableFqn                     = Naming.fullyQualifiedMutableName(cefName).replace('.', '/')
        val (ctorSig, ctorArgs, nestedPre) = byValueCtorFromPtr(cefName, p.name)
        val pre                            = nestedPre ++ List(
          s"""jclass ${jName}_cls = env->FindClass("$mutableFqn");""",
          s"""jmethodID ${jName}_ctor = env->GetMethodID(${jName}_cls, "<init>", "$ctorSig");""",
          s"""jobject $jName = ${p.name} ? env->NewObject(${jName}_cls, ${jName}_ctor, $ctorArgs) : nullptr;"""
        )
        val post = List(
          s"if (${p.name} && $jName) { ${bvWriteBack(cefName, p.name, jName, s"${jName}_cls")} }"
        )
        (pre, jName, post)
      case CType.ByValueArray(cefName) =>
        // Array of const geometry structs preceded by a count param
        val javaFqn         = Naming.fullyQualifiedJavaName(cefName).replace('.', '/')
        val (ctorSig, _, _) = byValueCtorFromPtr(cefName, "_dummy")
        val countParam      = if (idx > 0) {
          val prev = allParams(idx - 1)
          if (
            (prev.typ == CType.SizeT || prev.typ == CType.Int) &&
            prev.name.toLowerCase.contains("count")
          ) Some(prev.name)
          else None
        } else None
        val countExpr  = countParam.getOrElse("0")
        val fieldExprs = byValueArrayFieldExpr(cefName, p.name, "_i")
        val pre        = List(
          s"""jclass ${jName}_cls = env->FindClass("$javaFqn");""",
          s"""jmethodID ${jName}_ctor = env->GetMethodID(${jName}_cls, "<init>", "$ctorSig");""",
          s"""jint ${jName}_len = static_cast<jint>($countExpr);""",
          s"""jobjectArray $jName = env->NewObjectArray(${jName}_len, ${jName}_cls, nullptr);""",
          s"for (jint _i = 0; _i < ${jName}_len; _i++) {",
          s"    jobject _elem = env->NewObject(${jName}_cls, ${jName}_ctor, $fieldExprs);",
          s"    env->SetObjectArrayElement($jName, _i, _elem);",
          s"    env->DeleteLocalRef(_elem);",
          s"}"
        )
        (pre, jName, Nil)
      case CType.PixelBuffer =>
        // Wrap the native buffer as a direct ByteBuffer (zero-copy).
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
            val pre = List(s"jobject $jName = nullptr;")
            (pre, jName, Nil)
        }
      case CType.Void =>
        (Nil, "0", Nil)
    }
  }

  // Convert JNI return value back to native C type.
  private def convertJniReturnToNative(ct: CType, jniVar: String): String = ct match {
    case CType.Void       => "" // should not be called for void
    case CType.Bool       => s"$jniVar == JNI_TRUE ? 1 : 0"
    case CType.Int        => s"static_cast<int>($jniVar)"
    case CType.UInt       => s"static_cast<unsigned int>($jniVar)"
    case CType.Long       => s"static_cast<int64_t>($jniVar)"
    case CType.SizeT      => s"static_cast<size_t>($jniVar)"
    case CType.Float      => s"static_cast<float>($jniVar)"
    case CType.Double     => s"static_cast<double>($jniVar)"
    case CType.Enum(name) =>
      s"static_cast<$name>($jniVar ? env->GetLongField($jniVar, env->GetFieldID(env->GetObjectClass($jniVar), \"value\", \"J\")) : 0)"
    case CType.Ptr(inner)       => s"reinterpret_cast<$inner*>($jniVar)"
    case CType.JString          => jniVar
    case CType.DataStruct(name) => s"/* TODO: DataStruct by-value return ($name) */ $name{}"
    case _                      => jniVar
  }

  // Default native return value for error/exception paths in handler trampolines.
  private def defaultNativeReturn(ct: CType, metaAttrs: List[(String, String)]): String = {
    val metaDefault = metaAttrs.collectFirst { case ("default_retval", v) => v }
    metaDefault match {
      case Some(v) => ct match {
          case CType.Bool       => if (v == "true" || v == "1") "1" else "0"
          case CType.Enum(name) => s"$name::$v"
          case _                => v
        }
      case None => ct match {
          case CType.Void             => ""
          case CType.Bool             => "0"
          case CType.Int              => "0"
          case CType.UInt             => "0"
          case CType.Long             => "0"
          case CType.SizeT            => "0"
          case CType.Float            => "0.0f"
          case CType.Double           => "0.0"
          case CType.Ptr(_)           => "nullptr"
          case CType.JString          => "nullptr"
          case CType.Enum(_)          => "static_cast<" + Naming.cType(ct) + ">(0)"
          case CType.DataStruct(name) => s"$name{}"
          case _                      => "0"
        }
    }
  }

  private def renderHandlerFactory(structName: String, javaName: String): String = {
    val factorySym = s"Java_net_kurobako_cef4j_gen_${javaName}_1N_N_1Create"
    // C-linkage factory for use from other native code (e.g., browser creation)
    s"""extern "C" $structName* Create_Jni$javaName(JNIEnv* env, jobject handler) {
    JavaVM* jvm;
    env->GetJavaVM(&jvm);
    jobject globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<$structName*>(new Jni$javaName(jvm, globalRef));
}

extern "C" JNIEXPORT jlong JNICALL $factorySym(JNIEnv* env, jobject obj) {
    return reinterpret_cast<jlong>(Create_Jni$javaName(env, obj));
}"""
  }

  private def convertJniToNative(p: Param, isOptional: Boolean = false): String =
    p.typ match {
      case CType.JString if isOptional =>
        s"(${p.name} ? JStringToCefString(env, ${p.name}) : nullptr)"
      case CType.JString              => s"JStringToCefString(env, ${p.name})"
      case CType.Ptr(_) if isOptional =>
        s"(${p.name} ? reinterpret_cast<${Naming.cType(p.typ)}>(${p.name}) : nullptr)"
      case CType.Ptr(_)         => s"reinterpret_cast<${Naming.cType(p.typ)}>(${p.name})"
      case CType.Bool           => s"(${p.name} == JNI_TRUE ? 1 : 0)"
      case CType.Enum(_)        => s"static_cast<${Naming.cType(p.typ)}>(${p.name})"
      case CType.StringList     => s"JavaListToCefStringList(env, ${p.name})"
      case CType.StringMap      => s"JavaMapToCefStringMap(env, ${p.name})"
      case CType.StringMultimap => s"JavaMapToCefStringMultimap(env, ${p.name})"
      case _                    => p.name
    }

  private def parseCountFunc(spec: String): Option[(String, String)] =
    spec match {
      case s"$param:$func" if param.nonEmpty && func.nonEmpty => Some((param, func))
      case _                                                  => None
    }

  // --- By-value struct field type system ---

  sealed trait BvFieldType
  case object BvInt                          extends BvFieldType
  case object BvFloat                        extends BvFieldType
  case object BvLong                         extends BvFieldType
  case class BvNestedStruct(cefName: String) extends BvFieldType

  case class BvField(cName: String, javaName: String, typ: BvFieldType)

  /** By-value struct field definitions. Must match the C struct layout (excluding `size` is OK if the struct has one
    * managed by CEF).
    */
  private def byValueFields(cefName: String): List[BvField] =
    dataStructs.get(cefName) match {
      case Some(ds) =>
        ds.fields.map { f =>
          val javaName = Naming.toCamelCase(f.name)
          val bvType   = f.typ match {
            case CType.Int | CType.UInt | CType.Bool                               => BvInt
            case CType.Float                                                       => BvFloat
            case CType.Double                                                      => BvFloat
            case CType.Long | CType.SizeT                                          => BvLong
            case CType.DataStruct(nested) if CHeaderParser.isByValueStruct(nested) =>
              BvNestedStruct(nested)
            case other =>
              throw new RuntimeException(
                s"Unsupported field type $other for by-value struct $cefName field ${f.name}"
              )
          }
          BvField(f.name, javaName, bvType)
        }
      case None =>
        throw new RuntimeException(
          s"No DataStruct declaration found for by-value struct $cefName — is it parsed from headers?"
        )
    }

  private def bvJniSig(t: BvFieldType): String = t match {
    case BvInt             => "I"
    case BvFloat           => "F"
    case BvLong            => "J"
    case BvNestedStruct(n) => s"L${Naming.fullyQualifiedJavaName(n).replace('.', '/')};"
  }

  private def bvCastToJni(t: BvFieldType, expr: String): String = t match {
    case BvInt   => s"static_cast<jint>($expr)"
    case BvFloat => s"static_cast<jfloat>($expr)"
    case BvLong  => s"static_cast<jlong>($expr)"
    case _       => expr
  }

  /** Get a field from a Java object and cast to C type. */
  private def bvGetAndCast(t: BvFieldType, obj: String, cls: String, javaName: String): String = t match {
    case BvInt   => s"""env->GetIntField($obj, env->GetFieldID($cls, "$javaName", "I"))"""
    case BvFloat => s"""env->GetFloatField($obj, env->GetFieldID($cls, "$javaName", "F"))"""
    case BvLong  => s"""static_cast<size_t>(env->GetLongField($obj, env->GetFieldID($cls, "$javaName", "J")))"""
    case _       => throw new RuntimeException("bvGetAndCast called on nested struct")
  }

  /** Returns (JNI ctor signature, ctor arg expression, pre-creation lines for nested objects). */
  private def byValueCtorFromPtr(cefName: String, ptrName: String): (String, String, List[String]) = {
    val fields = byValueFields(cefName)
    // Pre-create nested struct objects
    val nestedCreation = fields.collect { case BvField(cName, _, BvNestedStruct(nestedCef)) =>
      val varName      = s"_bv_${ptrName}_$cName"
      val nestedFqn    = Naming.fullyQualifiedJavaName(nestedCef).replace('.', '/')
      val nestedFields = byValueFields(nestedCef)
      val nestedSig    = s"(${nestedFields.map(f => bvJniSig(f.typ)).mkString})V"
      val nestedArgs   = nestedFields.map(f => bvCastToJni(f.typ, s"$ptrName->$cName.${f.cName}")).mkString(", ")
      (
        cName,
        varName,
        List(
          s"""jclass ${varName}_cls = env->FindClass("$nestedFqn");""",
          s"""jmethodID ${varName}_ctor = env->GetMethodID(${varName}_cls, "<init>", "$nestedSig");""",
          s"""jobject $varName = env->NewObject(${varName}_cls, ${varName}_ctor, $nestedArgs);"""
        )
      )
    }
    val nestedVars = nestedCreation.map(t => (t._1, t._2)).toMap
    val nestedPre  = nestedCreation.flatMap(_._3)

    val sig  = s"(${fields.map(f => bvJniSig(f.typ)).mkString})V"
    val expr = fields.map {
      case BvField(cName, _, BvNestedStruct(_)) => nestedVars(cName)
      case BvField(cName, _, typ)               => bvCastToJni(typ, s"$ptrName->$cName")
    }.mkString(", ")
    (sig, expr, nestedPre)
  }

  /** Generate write-back lines from a Java object to a C struct pointer. */
  private def bvWriteBack(cefName: String, ptrName: String, javaObj: String, clsVar: String): String = {
    val fields = byValueFields(cefName)
    val lines  = fields.flatMap {
      case BvField(cName, javaName, BvNestedStruct(nestedCef)) =>
        val nestedFields = byValueFields(nestedCef)
        val sig          = bvJniSig(BvNestedStruct(nestedCef))
        List(
          s"""jobject _wb_$cName = env->GetObjectField($javaObj, env->GetFieldID($clsVar, "$javaName", "$sig")); """ +
            s"if (_wb_$cName) { jclass _wbc = env->GetObjectClass(_wb_$cName); " +
            nestedFields.map(nf =>
              s"""$ptrName->$cName.${nf.cName} = ${bvGetAndCast(nf.typ, s"_wb_$cName", "_wbc", nf.javaName)};"""
            ).mkString(" ") +
            " }"
        )
      case BvField(cName, javaName, typ) =>
        List(s"""$ptrName->$cName = ${bvGetAndCast(typ, javaObj, clsVar, javaName)};""")
    }
    lines.mkString(" ")
  }

  /** Generate code to read Java object fields into a C struct variable. */
  private def bvReadFromJavaObject(cefName: String, structVar: String, javaObj: String, clsVar: String): String = {
    val fields = byValueFields(cefName)
    val lines  = fields.flatMap {
      case BvField(cName, javaName, BvNestedStruct(nestedCef)) =>
        val nestedFields = byValueFields(nestedCef)
        val sig          = bvJniSig(BvNestedStruct(nestedCef))
        List(
          s"""jobject _rd_$cName = env->GetObjectField($javaObj, env->GetFieldID($clsVar, "$javaName", "$sig")); """ +
            s"if (_rd_$cName) { jclass _rdc = env->GetObjectClass(_rd_$cName); " +
            nestedFields.map(nf =>
              s"""$structVar.$cName.${nf.cName} = ${bvGetAndCast(nf.typ, s"_rd_$cName", "_rdc", nf.javaName)};"""
            ).mkString(" ") +
            " }"
        )
      case BvField(cName, javaName, typ) =>
        List(s"""$structVar.$cName = ${bvGetAndCast(typ, javaObj, clsVar, javaName)};""")
    }
    lines.mkString(" ")
  }

  /** Generate field expr for array element access: ptr[i].field */
  private def byValueArrayFieldExpr(cefName: String, ptrName: String, idxVar: String): String = {
    val fields = byValueFields(cefName)
    fields.map {
      case BvField(_, _, BvNestedStruct(_)) =>
        throw new RuntimeException(s"Nested struct in ByValueArray not supported: $cefName")
      case BvField(cName, _, typ) => bvCastToJni(typ, s"$ptrName[$idxVar].$cName")
    }.mkString(", ")
  }

  /** Returns (JNI constructor signature, C field access expressions) for known by-value data structs. */
  private def dataStructCtorInfo(cefName: String): (String, String) = cefName match {
    case "cef_rect_t"     => ("(IIII)V", "result.x, result.y, result.width, result.height")
    case "cef_point_t"    => ("(II)V", "result.x, result.y")
    case "cef_size_t"     => ("(II)V", "result.width, result.height")
    case "cef_insets_t"   => ("(IIII)V", "result.top, result.left, result.bottom, result.right")
    case "cef_range_t"    => ("(II)V", "static_cast<jint>(result.from), static_cast<jint>(result.to)")
    case "cef_basetime_t" => ("(J)V", "static_cast<jlong>(result.val)")
    case _                => ("()V", "") // fallback: default constructor
  }

  private def defaultReturn(ct: CType): String = ct match {
    case CType.Void          => ""
    case CType.Bool          => " JNI_FALSE"
    case CType.JString       => " nullptr"
    case CType.Ptr(_)        => " 0"
    case CType.DataStruct(_) => " nullptr"
    case _                   => " 0"
  }
}
