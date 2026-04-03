package net.kurobako.cef4j.codegen

import java.nio.file.Files
import java.nio.file.Path
import scala.annotation.tailrec

import net.kurobako.cef4j.codegen.passes.RefineTree

class JniCppCodeGen(
    dataStructs: Map[String, CefDecl.DataStruct],
    handlerNames: Set[String] = Set.empty,
    scopedNames: Set[String] = Set.empty,
    structHeaderMap: Map[String, String] = Map.empty
)(using Naming.Context) {

  private val GeneratedBanner = Banners.cpp

  private def joinIndentedLines(lines: List[String], indent: String): String =
    lines match {
      case Nil => ""
      case xs  => "\n" + xs.map(line => s"$indent$line").mkString("\n")
    }

  private def isHandlerPtr(ct: CType): Boolean = ct match {
    case CType.ObjectPtr(name) => handlerNames.contains(name)
    case CType.Ptr(inner)      => handlerNames.contains(inner.stripPrefix("_"))
    case _                     => false
  }

  private def addRefExpr(ptr: String): String =
    s"{ auto* _b = reinterpret_cast<cef_base_ref_counted_t*>($ptr); _b->add_ref(_b); }"

  // Collect handler-typed parameters so wrapper files can forward-declare their factory functions.
  private def collectHandlerParamFactories(
      fns: List[FnPtr],
      ffs: List[CefDecl.FreeFunction] = Nil
  ): List[(String, String)] = {
    val fromFns = fns.flatMap(_.params).collect {
      case Param(_, CType.ObjectPtr(cefName), _, _) if handlerNames.contains(cefName) =>
        (cefName, Naming.structToJavaName(cefName))
    }
    val fromFFs = ffs.flatMap(_.params).collect {
      case Param(_, CType.ObjectPtr(cefName), _, _) if handlerNames.contains(cefName) =>
        (cefName, Naming.structToJavaName(cefName))
    }
    (fromFns ++ fromFFs).distinctBy(_._1)
  }

  private def renderHandlerFactoryForwardDecls(factories: List[(String, String)]): String =
    if (factories.isEmpty) ""
    else "\n" + factories.map { case (cefName, javaName) =>
      s"""extern "C" $cefName* Create_Jni$javaName(JNIEnv* env, jobject handler);"""
    }.mkString("\n") + "\n"

  // Platform handle types that may be pointers on some platforms
  private val PlatformHandleTypes = Set(
    "cef_window_handle_t",
    "cef_cursor_handle_t",
    "cef_event_handle_t",
    "cef_platform_thread_id_t",
    "cef_platform_thread_handle_t"
  )

  def emit(decl: CefDecl.ObjectStruct, outDir: Path, freeFunctions: List[CefDecl.FreeFunction] = Nil): Unit =
    writeCppFile(outDir, s"${Naming.cefBaseName(decl.name)}_N.cpp", emitToString(decl, freeFunctions))

  def emitHandler(decl: CefDecl.HandlerStruct, outDir: Path): Unit =
    writeCppFile(outDir, s"${Naming.cefBaseName(decl.name)}.cpp", emitHandlerToString(decl))

  // Emit a standalone C++ file for orphan free functions bound to CefGlobals.
  def emitGlobals(freeFunctions: List[CefDecl.FreeFunction], outDir: Path): Unit = {
    if (freeFunctions.isEmpty) return
    val refs    = freeFunctions.flatMap(ff => collectReferencedStructsFromParams(ff.ret, ff.params)).toSet
    val headers = freeFunctions.flatMap(ff => structHeaderMap.get(ff.ownerStruct)).distinct ++
      freeFunctions.map(_.sourceHeader).distinct
    val includes        = renderIncludesForFreeFunc(headers.distinct, refs)
    val handlerFwdDecls = renderHandlerFactoryForwardDecls(collectHandlerParamFactories(Nil, freeFunctions))
    val functions = freeFunctions.map(ff => renderFreeFunction("CefGlobals", ff, isDirectClass = true)).mkString("\n\n")
    writeCppFile(outDir, "cef_globals_N.cpp", renderGeneratedCpp(includes, s"$handlerFwdDecls$functions"))
  }

  private def writeCppFile(outDir: Path, fileName: String, content: String): Unit = {
    val file = outDir.resolve(fileName)
    Files.createDirectories(file.getParent)
    Files.writeString(file, content)
  }

  def emitToString(decl: CefDecl.ObjectStruct, freeFunctions: List[CefDecl.FreeFunction] = Nil): String = {
    val ffRefs          = freeFunctions.flatMap(ff => collectReferencedStructsFromParams(ff.ret, ff.params)).toSet
    val refs            = (collectReferencedStructs(decl.fns) ++ ffRefs) - decl.name
    val extraHeaders    = freeFunctions.map(_.sourceHeader).filter(h => h.nonEmpty && h != decl.sourceHeader).distinct
    val includes        = renderIncludes(decl.name, decl.sourceHeader, refs, extraHeaders)
    val handlerFwdDecls = renderHandlerFactoryForwardDecls(collectHandlerParamFactories(decl.fns, freeFunctions))
    val functions       = decl.fns.map(fn => renderObjectFunction(decl.name, fn)).mkString("\n\n")
    val releaseFn       = renderRelease(decl.name, decl.scoped)
    val javaName        = Naming.structToJavaName(decl.name)
    val ffFunctions     = if (freeFunctions.nonEmpty) {
      "\n\n" + freeFunctions.map(ff => renderFreeFunction(javaName, ff)).mkString("\n\n")
    } else ""
    renderGeneratedCpp(includes, s"$handlerFwdDecls$releaseFn\n\n$functions$ffFunctions")
  }

  // Generate the N_Release JNI function used by NativeCleaner cleanup.
  private def renderRelease(structName: String, scoped: Boolean): String = {
    val javaName  = Naming.structToJavaName(structName)
    val exportSig = Naming.jniExportPeerStatic(javaName, "release", "void")
    val body      = if (scoped) "    // Scoped struct - no ref-counting, release is a no-op."
    else
      s"""    auto* b = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
    if (b) b->release(b);"""
    s"""$exportSig(JNIEnv* env, jclass clz, jlong ptr) {
$body
}"""
  }

  def emitHandlerToString(decl: CefDecl.HandlerStruct): String = {
    val javaName = Naming.structToJavaName(decl.name)
    val refs     = collectReferencedStructs(decl.fns) - decl.name
    val includes = renderIncludes(decl.name, decl.sourceHeader, refs)

    val initAssignments = joinIndentedLines(decl.fns.map(fn => s"${fn.name} = &_${fn.name};"), "        ")

    val structDef = s"""
struct Jni$javaName : public ${decl.name} {
    JavaVM* jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount{1};

    Jni$javaName(JavaVM* vm, jobject handler) : ${decl.name}{}, jvm(vm) {
        javaHandler = handler;
        InitRefCount<Jni$javaName, ${decl.name}>(&base);$initAssignments
    }
"""

    val trampolines = decl.fns.map(fn => renderHandlerTrampoline(decl.name, fn, javaName)).mkString("\n\n")

    val factoryFn = renderHandlerFactory(decl.name, javaName)

    val hasByValueArray = decl.fns.exists(_.params.exists {
      case Param(_, CType.ByValueArray(_), _, _) => true
      case _                                     => false
    })
    val vectorInclude = if (hasByValueArray) "\n#include <vector>" else ""

    // Forward-declare factory functions for handler types returned by or used as out-params in trampolines
    val handlerPtrReturns = decl.fns.flatMap { fn =>
      val fromRet = if (isHandlerPtr(fn.ret)) {
        val cefName = fn.ret match {
          case CType.ObjectPtr(name) => name
          case CType.Ptr(inner)      => inner.stripPrefix("_")
          case _                     => ""
        }
        List((cefName, Naming.structToJavaName(cefName)))
      } else Nil
      val fromOutParams = fn.params.collect {
        case Param(_, CType.OutObjectPtr(cefName), _, _) if handlerNames.contains(cefName) =>
          (cefName, Naming.structToJavaName(cefName))
      }
      fromRet ++ fromOutParams
    }.distinctBy(_._1)
    val forwardDecls = handlerPtrReturns.map { case (cefName, javaName) =>
      s"""extern "C" $cefName* Create_Jni$javaName(JNIEnv* env, jobject handler);"""
    }.mkString("\n")
    val forwardDeclBlock = if (forwardDecls.nonEmpty) s"\n$forwardDecls\n" else ""

    renderGeneratedCpp(
      s"""$includes
#include <atomic>$vectorInclude
#include "jni_util.h"
""",
      s"""$forwardDeclBlock$structDef
$trampolines
};

$factoryFn"""
    )
  }

  private def renderGeneratedCpp(includes: String, body: String): String =
    s"""$GeneratedBanner
${includes.stripSuffix("\n")}

${body.stripPrefix("\n").stripSuffix("\n")}
"""

  private def renderIncludes(
      cefStructName: String,
      sourceHeader: String,
      referencedStructs: Set[String] = Set.empty,
      additionalHeaders: List[String] = Nil
  ): String = {
    val header = if (sourceHeader.nonEmpty) sourceHeader
    else {
      // Fallback: derive from struct name (may be wrong for grouped headers)
      val headerName = Naming.cefBaseName(cefStructName)
      s"cef_${headerName}_capi.h"
    }
    val extraHeaders = (referencedStructs.flatMap(structHeaderMap.get).toList ++ additionalHeaders)
      .filter(h => h != header && h.nonEmpty).distinct.sorted
    val extraIncludes = extraHeaders.map(h => s"""#include "include/capi/$h"""").mkString("\n")
    val extra         = if (extraIncludes.nonEmpty) s"\n$extraIncludes" else ""
    s"""#include <jni.h>
#include "include/capi/$header"$extra
#include "jni_util.h"
"""
  }

  // Render includes for standalone free function files bound to CefGlobals.
  private def renderIncludesForFreeFunc(
      sourceHeaders: List[String],
      referencedStructs: Set[String]
  ): String = {
    val allHeaders     = (sourceHeaders ++ referencedStructs.flatMap(structHeaderMap.get).toList).distinct.sorted
    val headerIncludes = allHeaders.map(h => s"""#include "include/capi/$h"""").mkString("\n")
    s"""#include <jni.h>
$headerIncludes
#include "jni_util.h"
"""
  }

  private def renderObjectFunction(structName: String, fn: FnPtr): String = {
    val javaName  = Naming.structToJavaName(structName)
    val retJni    = Naming.jniType(fn.ret)
    val exportSig = Naming.jniExportPeer(structName, fn, retJni)
    val jniParams =
      ("JNIEnv* env" :: "jobject obj" :: "jlong self" :: fn.visibleParams.map {
        p =>
          s"${Naming.jniType(p.typ)} ${p.name}"
      }).mkString(", ")

    val castSelf  = s"    auto* s = reinterpret_cast<$structName*>(self);"
    val nullGuard = s"    if (!s) return${defaultReturn(fn.ret)};"

    // Collect optional params for null-safe conversion
    val optionalParams = fn.metaAttrs.collect {
      case ("optional_param", p) => p
    }.toSet

    // NPE checks for types where null is certainly a programming error (enums, by-value structs,
    // buffers, collections). Strings, object pointers, and opaques are excluded because CEF
    // frequently accepts NULL for those but upstream metacomments only annotate ~10% of them.
    val npeChecks = fn.params.collect {
      case p if !optionalParams.contains(p.name) && JavaCodeGen.isStrictNullCheck(p.typ) =>
        val javaParamName = Naming.toCamelCase(p.name)
        s"""    if (!${p.name}) { env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "$javaParamName must not be null"); return${defaultReturn(
            fn.ret
          )}; }"""
    }

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
            s"    if (${p.name}) { jboolean _jv; env->GetBooleanArrayRegion(${p.name}, 0, 1, &_jv); $tmp = static_cast<bool>(_jv); }"
          )
          val post = List(
            s"    if (${p.name}) { jboolean _jv = static_cast<jboolean>($tmp); env->SetBooleanArrayRegion(${p.name}, 0, 1, &_jv); }"
          )
          (pre, s"&$tmp", post)
        case CType.ByValueIn(cefName) =>
          // Read-only geometry struct: extract fields from Java object into a local C struct
          val tmp     = s"_${p.name}_val"
          val javaFqn = Naming.fullyQualifiedJavaName(cefName).replace('.', '/')
          val pre     = List(
            s"    $cefName $tmp = {};",
            s"""    if (${p.name}) {""",
            s"""        auto _c = env->FindClass("$javaFqn");"""
          ) ++
            bvReadFromJavaLines(cefName, tmp, p.name, "_c").map(l => s"        $l") ++
            List("    }")
          (pre, s"&$tmp", Nil)
        case CType.ByValueOut(cefName) =>
          // Mutable out-param (Cef*.Mutable object on Java side): extract fields from object into C struct
          val tmp        = s"_${p.name}_val"
          val mutableFqn = Naming.fullyQualifiedMutableName(cefName).replace('.', '/')
          val pre        = List(
            s"    $cefName $tmp = {};",
            s"""    if (${p.name}) {""",
            s"""        auto _c = env->FindClass("$mutableFqn");"""
          ) ++
            bvReadFromJavaLines(cefName, tmp, p.name, "_c").map(l => s"        $l") ++
            List("    }")
          (pre, s"&$tmp", Nil)
        case CType.ByValueArray(cefName) =>
          // Input array of by-value structs: extract from Java array into C array
          val countVar = fn.params.find(_.name.toLowerCase == s"${p.name.toLowerCase}count")
            .map(cp => cp.name)
            .getOrElse("0")
          val arrVar  = s"_${p.name}_arr"
          val sizeVar = s"_${p.name}_sz"
          val javaFqn = Naming.fullyQualifiedJavaName(cefName).replace('.', '/')
          val pre     = List(
            s"    size_t $sizeVar = static_cast<size_t>($countVar);",
            s"    $cefName* $arrVar = $sizeVar > 0 ? new $cefName[$sizeVar]() : nullptr;",
            s"""    { auto _bvac = env->FindClass("$javaFqn");""",
            s"    for (size_t _i = 0; _i < $sizeVar; _i++) {",
            s"        auto _elem = env->GetObjectArrayElement(${p.name}, _i);",
            s"        if (_elem) {"
          ) ++
            bvReadFromJavaLines(cefName, s"$arrVar[_i]", "_elem", "_bvac").map(l => s"            $l") ++
            List(
              "        }",
              s"    } }"
            )
          val post = List(s"    delete[] $arrVar;")
          (pre, arrVar, post)
        case CType.ObjectPtr(cefName) if handlerNames.contains(cefName) =>
          // Handler-typed param: wrap the Java handler via its factory function
          val tmp      = s"_${p.name}_ptr"
          val javaName = Naming.structToJavaName(cefName)
          val factory  = s"Create_Jni$javaName"
          val pre      = List(
            s"    $cefName* $tmp = ${p.name} ? $factory(env, ${p.name}) : nullptr;"
          )
          (pre, tmp, Nil)
        case CType.ObjectPtr(cefName) =>
          // CEF's CppToC::Unwrap() consumes a reference on the argument - we must
          // add_ref before passing it so the caller's own reference stays valid.
          // Always null-guard: CEF accepts NULL for many object params (e.g. browser/frame
          // in service worker contexts) even when not annotated as optional_param.
          val tmp     = s"_${p.name}_ptr"
          val extract =
            s"""reinterpret_cast<$cefName*>(env->GetLongField(${p.name}, env->GetFieldID(env->GetObjectClass(${p.name}), "nativePtr", "J")))"""
          val addRef = s"    if ($tmp) ${addRefExpr(tmp)}"
          val pre    = List(
            s"    $cefName* $tmp = ${p.name} ? $extract : nullptr;",
            addRef
          )
          (pre, tmp, Nil)
        case CType.OutObjectPtr(cefName) =>
          val tmp = s"_${p.name}_ptr"
          val pre = List(
            s"    $cefName* $tmp = nullptr;",
            s"    if (${p.name}) {",
            s"""        auto _get = env->GetMethodID(env->GetObjectClass(${p.name}), "get", "()Ljava/lang/Object;");""",
            s"        auto _cur = env->CallObjectMethod(${p.name}, _get);",
            s"""        if (_cur) $tmp = reinterpret_cast<$cefName*>(env->GetLongField(_cur, env->GetFieldID(env->GetObjectClass(_cur), "nativePtr", "J")));""",
            s"    }"
          )
          val addRefLine =
            if (scopedNames.contains(cefName)) Nil
            else List(s"        ${addRefExpr(tmp)}")
          val post = List(
            s"    if (${p.name} && $tmp) {",
            s"""        auto _peerCls = env->FindClass("${Naming.fullyQualifiedJavaName(cefName).replace(
                '.',
                '/'
              )}$$NativePeer");""",
            s"""        auto _peerCtor = env->GetMethodID(_peerCls, "<init>", "(J)V");"""
          ) ++ addRefLine ++ List(
            s"        auto _newPeer = env->NewObject(_peerCls, _peerCtor, reinterpret_cast<jlong>($tmp));",
            s"""        auto _set = env->GetMethodID(env->GetObjectClass(${p.name}), "set", "(Ljava/lang/Object;)V");""",
            s"        env->CallVoidMethod(${p.name}, _set, _newPeer);",
            s"    }"
          )
          (pre, s"&$tmp", post)
        case CType.OutPrimitivePtr(inner @ (CType.Long | CType.SizeT)) =>
          val cPrim = Naming.cType(inner)
          val tmp   = s"_${p.name}_val"
          val pre   = List(
            s"    $cPrim $tmp = 0;",
            s"    if (${p.name}) { jlong _jv; env->GetLongArrayRegion(${p.name}, 0, 1, &_jv); $tmp = static_cast<$cPrim>(_jv); }"
          )
          val post = List(
            s"    if (${p.name}) { jlong _jv = static_cast<jlong>($tmp); env->SetLongArrayRegion(${p.name}, 0, 1, &_jv); }"
          )
          (pre, s"&$tmp", post)
        case CType.OutPrimitivePtr(CType.Float) =>
          val tmp = s"_${p.name}_val"
          val pre = List(
            s"    float $tmp = 0;",
            s"    if (${p.name}) { jfloat _jv; env->GetFloatArrayRegion(${p.name}, 0, 1, &_jv); $tmp = _jv; }"
          )
          val post = List(
            s"    if (${p.name}) { jfloat _jv = $tmp; env->SetFloatArrayRegion(${p.name}, 0, 1, &_jv); }"
          )
          (pre, s"&$tmp", post)
        case CType.OutPrimitivePtr(CType.Double) =>
          val tmp = s"_${p.name}_val"
          val pre = List(
            s"    double $tmp = 0;",
            s"    if (${p.name}) { jdouble _jv; env->GetDoubleArrayRegion(${p.name}, 0, 1, &_jv); $tmp = _jv; }"
          )
          val post = List(
            s"    if (${p.name}) { jdouble _jv = $tmp; env->SetDoubleArrayRegion(${p.name}, 0, 1, &_jv); }"
          )
          (pre, s"&$tmp", post)
        case CType.OutPrimitivePtr(CType.UInt) =>
          val tmp = s"_${p.name}_val"
          val pre = List(
            s"    unsigned int $tmp = 0;",
            s"    if (${p.name}) { jint _jv; env->GetIntArrayRegion(${p.name}, 0, 1, &_jv); $tmp = static_cast<unsigned int>(_jv); }"
          )
          val post = List(
            s"    if (${p.name}) { jint _jv = static_cast<jint>($tmp); env->SetIntArrayRegion(${p.name}, 0, 1, &_jv); }"
          )
          (pre, s"&$tmp", post)
        case CType.OutPrimitivePtr(inner) =>
          val cPrim = Naming.cType(inner)
          val tmp   = s"_${p.name}_val"
          val pre   = List(
            s"    $cPrim $tmp = 0;",
            s"    if (${p.name}) { jint _jv; env->GetIntArrayRegion(${p.name}, 0, 1, &_jv); $tmp = static_cast<$cPrim>(_jv); }"
          )
          val post = List(
            s"    if (${p.name}) { jint _jv = static_cast<jint>($tmp); env->SetIntArrayRegion(${p.name}, 0, 1, &_jv); }"
          )
          (pre, s"&$tmp", post)
        case CType.ObjectPtrArray(cefName) =>
          // Out-param array: allocate native array from adjacent count, fill, convert to Java NativePeer array
          val countVar = fn.params.find(_.name.toLowerCase == s"${p.name.toLowerCase}count")
            .map { cp =>
              cp.typ match {
                case CType.OutPrimitivePtr(_) => s"_${cp.name}_val" // pre-extracted local
                case _                        => cp.name            // direct param
              }
            }
            .getOrElse("0")
          val arrVar  = s"_${p.name}_arr"
          val sizeVar = s"_${p.name}_sz"
          val javaFqn = Naming.fullyQualifiedJavaName(cefName).replace('.', '/')
          val pre     = List(
            s"    size_t $sizeVar = static_cast<size_t>($countVar);",
            s"    $cefName** $arrVar = $sizeVar > 0 ? new $cefName*[$sizeVar]() : nullptr;"
          )
          val post = List(
            s"""    auto _${p.name}_cls = env->FindClass("$javaFqn$$NativePeer");""",
            s"""    auto _${p.name}_ctor = env->GetMethodID(_${p.name}_cls, "<init>", "(J)V");""",
            s"    for (size_t _i = 0; _i < $sizeVar; _i++) {",
            s"        auto _elem = $arrVar[_i] ? env->NewObject(_${p.name}_cls, _${p.name}_ctor, reinterpret_cast<jlong>($arrVar[_i])) : nullptr;",
            s"        env->SetObjectArrayElement(${p.name}, _i, _elem);",
            s"    }",
            s"    delete[] $arrVar;"
          )
          (pre, arrVar, post)
        case CType.Buffer(sizeParam) =>
          // ByteBuffer param: extract direct buffer address, derive size from capacity
          val addrVar       = s"_${p.name}_addr"
          val isConst       = p.rawCType.contains("const")
          val castType      = if (isConst) "const void*" else "void*"
          val javaParamName = Naming.toCamelCase(p.name)
          val pre           = List(
            s"    $castType $addrVar = ${p.name} ? env->GetDirectBufferAddress(${p.name}) : nullptr;",
            s"""    if (${p.name} && !$addrVar) { env->ThrowNew(env->FindClass("java/lang/IllegalArgumentException"), "$javaParamName must be a direct ByteBuffer; use ByteBuffer.allocateDirect(...)"); return${defaultReturn(
                fn.ret
              )}; }"""
          )
          (pre, addrVar, Nil)
        case CType.BufferSize(bufferParam) =>
          // Hidden size param: derive from ByteBuffer capacity (not received as JNI arg)
          val rawC  = p.rawCType.replaceAll("\\s+", " ").trim
          val cCast = if (rawC.nonEmpty && rawC != "size_t") rawC else "size_t"
          (Nil, s"static_cast<$cCast>(env->GetDirectBufferCapacity($bufferParam))", Nil)
        case CType.StringList =>
          val tmp        = s"_${p.name}_csl"
          val isOutParam = fn.name.startsWith("get_")
          (
            List(s"    auto $tmp = JavaListToCefStringList(env, ${p.name});"),
            tmp,
            if (isOutParam) List(s"    CefStringListWriteBack(env, $tmp, ${p.name});")
            else List(s"    cef_string_list_free($tmp);")
          )
        case CType.StringMap =>
          val tmp        = s"_${p.name}_csm"
          val isOutParam = fn.name.startsWith("get_")
          (
            List(s"    auto $tmp = JavaMapToCefStringMap(env, ${p.name});"),
            tmp,
            if (isOutParam) List(s"    CefStringMapWriteBack(env, $tmp, ${p.name});")
            else List(s"    cef_string_map_free($tmp);")
          )
        case CType.StringMultimap =>
          val tmp        = s"_${p.name}_csmm"
          val isOutParam = fn.name.startsWith("get_")
          (
            List(s"    auto $tmp = JavaMapToCefStringMultimap(env, ${p.name});"),
            tmp,
            if (isOutParam) List(s"    CefStringMultimapWriteBack(env, $tmp, ${p.name});")
            else List(s"    cef_string_multimap_free($tmp);")
          )
        case CType.JString =>
          // Allocate a cef_string_userfree_t, pass to CEF call, then free it
          val tmp = s"_${p.name}_str"
          val pre = if (isOpt) List(
            s"    auto $tmp = ${p.name} ? JStringToCefString(env, ${p.name}) : nullptr;"
          )
          else List(s"    auto $tmp = JStringToCefString(env, ${p.name});")
          val post = List(s"    if ($tmp) cef_string_userfree_free($tmp);")
          (pre, tmp, post)
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

    // count_func: pre-call to size arrays, only when a CountFuncArray param consumes it
    val hasCountFuncArray = fn.params.exists(_.typ match {
      case CType.CountFuncArray(_, _, _, _) => true; case _ => false
    })
    val countFuncSetup = if (!hasCountFuncArray) ""
    else
      fn.metaAttrs.collectFirst { case ("count_func", spec) => spec }
        .flatMap(RefineTree.parseCountFunc)
        .map { case (param, func) =>
          val snakeFunc = func.replaceAll("([a-z])([A-Z])|([A-Z]+)([A-Z][a-z])", "$1$3_$2$4").toLowerCase
          s"    size_t ${param}_count = s->$snakeFunc(s);"
        }
        .getOrElse("")

    val preBlock  = if (preCallLines.nonEmpty) preCallLines.mkString("\n") + "\n" else ""
    val postBlock = if (postCallLines.nonEmpty) "\n" + postCallLines.mkString("\n") else ""
    val npeBlock  = if (npeChecks.nonEmpty) npeChecks.mkString("\n") + "\n" else ""

    val body = fn.ret match {
      case CType.Void =>
        s"""$castSelf
$nullGuard
$npeBlock$countFuncSetup
$preBlock    $fnCall;$postBlock"""
      case CType.JString =>
        s"""$castSelf
$nullGuard
$npeBlock$preBlock    auto result = $fnCall;$postBlock
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;"""
      case CType.Bool =>
        s"""$castSelf
$nullGuard
$npeBlock$preBlock    auto _r = $fnCall;$postBlock
    return static_cast<jboolean>(_r);"""
      case CType.ObjectPtr(cefName) =>
        val javaFqn = Naming.fullyQualifiedJavaName(cefName).replace('.', '/')
        // CEF returns an already-add_ref'd pointer (caller owns it). Wrap in NativePeer.
        s"""$castSelf
$nullGuard
$npeBlock$preBlock    auto _r = $fnCall;$postBlock
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("$javaFqn$$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));"""
      case CType.Ptr(_) =>
        s"""$castSelf
$nullGuard
$npeBlock$preBlock    auto _r = $fnCall;$postBlock
    return reinterpret_cast<jlong>(_r);"""
      case CType.OpaquePtr =>
        s"""$castSelf
$nullGuard
$npeBlock$preBlock    auto _r = $fnCall;$postBlock
    auto _npCls = env->FindClass("${Naming.nativePointerInternalName}");
    auto _npCtor = env->GetMethodID(_npCls, "<init>", "(J)V");
    return env->NewObject(_npCls, _npCtor, reinterpret_cast<jlong>(_r));"""
      case CType.DataStruct(cefName) =>
        // By-value struct return: construct Java object via JNI
        val javaFqn                             = Naming.fullyQualifiedJavaName(cefName).replace('.', '/')
        val (ctorSig, ctorArgsList, ctorPreOps) = dataStructCtorInfo(cefName)
        val preOpsBlock = if (ctorPreOps.nonEmpty) "\n" + ctorPreOps.map(l => s"    $l").mkString("\n") else ""
        val sizeSetLine = if (hasNativeSizeField(cefName))
          "\n    env->SetLongField(_dsResult, env->GetFieldID(cls, \"size\", \"J\"), static_cast<jlong>(result.size));"
        else ""
        val newObjExpr = fmtNewObject("cls", "ctor", ctorArgsList)
        s"""$castSelf
$nullGuard
$npeBlock$preBlock    $cefName result = $fnCall;$postBlock$preOpsBlock
    auto cls = env->FindClass("$javaFqn");
    auto ctor = env->GetMethodID(cls, "<init>", "$ctorSig");
    auto _dsResult = $newObjExpr;$sizeSetLine
    return _dsResult;"""
      case CType.Enum(name) =>
        val javaFqn = Naming.fullyQualifiedJavaName(name).replace('.', '/')
        s"""$castSelf
$nullGuard
$npeBlock$preBlock    auto _r = $fnCall;$postBlock
    auto _eCls = env->FindClass("$javaFqn");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)L$javaFqn;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));"""
      case cfa @ CType.CountFuncArray(elemType, countFuncC, countParamName, arrayParamName) =>
        renderCountFuncArrayBody(structName, fn, cfa, castSelf, nullGuard, npeBlock, preBlock, postBlock)
      case _ =>
        s"""$castSelf
$nullGuard
$npeBlock$preBlock    return static_cast<$retJni>($fnCall);"""
    }

    // Clean up empty lines from optional countFuncSetup
    val cleanBody = body.linesIterator.filter(_.trim.nonEmpty).mkString("\n")

    s"""$exportSig($jniParams) {
$cleanBody
}"""
  }

  // Generate the CountFuncArray two-pass JNI path: count, allocate, fill, marshal.
  private def renderCountFuncArrayBody(
      structName: String,
      fn: FnPtr,
      cfa: CType.CountFuncArray,
      castSelf: String,
      nullGuard: String,
      npeBlock: String,
      preBlock: String,
      postBlock: String
  ): String = {
    val CType.CountFuncArray(elemType, countFuncC, countParamName, arrayParamName) = cfa

    // 1. Call count function
    val countCall = s"    size_t _count = s->$countFuncC(s);"

    // 2-3. Allocate + call depending on element type
    val (allocAndCall, convertAndReturn) = elemType match {
      case CType.ObjectPtr(cefName) =>
        val javaFqn = Naming.fullyQualifiedJavaName(cefName).replace('.', '/')
        val alloc   = s"    $cefName** _arr = _count > 0 ? new $cefName*[_count]() : nullptr;"
        val call    = s"    s->${fn.name}(s, &_count, _arr);"
        val convert =
          s"""    auto _elemCls = env->FindClass("$javaFqn$$NativePeer");
    auto _elemCtor = env->GetMethodID(_elemCls, "<init>", "(J)V");
    auto _result = env->NewObjectArray(static_cast<jsize>(_count), _elemCls, nullptr);
    for (size_t _i = 0; _i < _count; _i++) {
        if (_arr[_i]) {
            auto _peer = env->NewObject(_elemCls, _elemCtor, reinterpret_cast<jlong>(_arr[_i]));
            env->SetObjectArrayElement(_result, static_cast<jsize>(_i), _peer);
        }
    }
    delete[] _arr;
    return _result;"""
        (s"$alloc\n$call", convert)

      case CType.ByValueIn(_) | CType.ByValueOut(_) =>
        val bvCefName = elemType match {
          case CType.ByValueIn(n)  => n
          case CType.ByValueOut(n) => n
          case _                   => throw new RuntimeException("unreachable")
        }
        val javaFqn   = Naming.fullyQualifiedJavaName(bvCefName).replace('.', '/')
        val alloc     = s"    $bvCefName* _arr = _count > 0 ? new $bvCefName[_count]() : nullptr;"
        val call      = s"    s->${fn.name}(s, &_count, _arr);"
        val fields    = byValueFields(bvCefName)
        val ctorSig   = s"(${fields.map(f => bvJniSig(f.typ)).mkString})V"
        val fieldExpr = byValueArrayFieldExpr(bvCefName, "_arr", "_i")
        val convert   =
          s"""    auto _elemCls = env->FindClass("$javaFqn");
    auto _elemCtor = env->GetMethodID(_elemCls, "<init>", "$ctorSig");
    auto _result = env->NewObjectArray(static_cast<jsize>(_count), _elemCls, nullptr);
    for (size_t _i = 0; _i < _count; _i++) {
        auto _elem = env->NewObject(_elemCls, _elemCtor, $fieldExpr);
        env->SetObjectArrayElement(_result, static_cast<jsize>(_i), _elem);
    }
    delete[] _arr;
    return _result;"""
        (s"$alloc\n$call", convert)

      case CType.Long | CType.SizeT =>
        val cPrim   = if (elemType == CType.SizeT) "size_t" else "int64_t"
        val alloc   = s"    $cPrim* _arr = _count > 0 ? new $cPrim[_count]() : nullptr;"
        val call    = s"    s->${fn.name}(s, &_count, _arr);"
        val convert =
          s"""    jlongArray _result = env->NewLongArray(static_cast<jsize>(_count));
    if (_count > 0) {
        // Copy with static_cast in case of size_t -> jlong
        auto* _tmp = new jlong[_count];
        for (size_t _i = 0; _i < _count; _i++) _tmp[_i] = static_cast<jlong>(_arr[_i]);
        env->SetLongArrayRegion(_result, 0, static_cast<jsize>(_count), _tmp);
        delete[] _tmp;
    }
    delete[] _arr;
    return _result;"""
        (s"$alloc\n$call", convert)

      case CType.Int | CType.UInt =>
        val alloc   = s"    int* _arr = _count > 0 ? new int[_count]() : nullptr;"
        val call    = s"    s->${fn.name}(s, &_count, _arr);"
        val convert =
          s"""    jintArray _result = env->NewIntArray(static_cast<jsize>(_count));
    if (_count > 0) {
        env->SetIntArrayRegion(_result, 0, static_cast<jsize>(_count), reinterpret_cast<jint*>(_arr));
    }
    delete[] _arr;
    return _result;"""
        (s"$alloc\n$call", convert)

      case other =>
        throw new RuntimeException(s"CountFuncArray: unsupported element type $other in ${fn.name}")
    }

    s"""$castSelf
$nullGuard
$npeBlock$preBlock$countCall
$allocAndCall
$convertAndReturn"""
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

    // Default return for error paths
    val defaultRetExpr = defaultNativeReturn(fn.ret, fn.metaAttrs)
    val earlyReturn    = if (fn.ret == CType.Void) "return;" else s"return $defaultRetExpr;"

    // Convert each param: (preCallLines, jniArgExpr, postCallLines)
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

    // JNI call expression
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
      // For ref-counted pointer returns from handler callbacks, the returned object
      // has already been add_ref'd by the NativePeer's Cleaner ownership model.
      // ObjectPtr returns extract nativePtr from NativePeer which already owns a ref.
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
  // Returns (preCallLines, jniArgExpr, postCallLines).
  // Takes full params list and index for context-aware conversions (ByValueArray, PixelBuffer).
  private def convertNativeToJni(p: Param, allParams: List[Param], idx: Int): (List[String], String, List[String]) = {
    val jName = s"j_${p.name}"
    p.typ match {
      case CType.ObjectPtr(cefName) =>
        val javaFqn  = Naming.fullyQualifiedJavaName(cefName).replace('.', '/')
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
        // Handler struct out-param: Java user can set a new handler implementation.
        // We pass AtomicReference(null) since the initial native handler can't be unwrapped to a Java object.
        // On write-back, if a new Java handler is set, wrap it via the Create_JniXxx factory.
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
        val javaFqn = Naming.fullyQualifiedJavaName(cefName).replace('.', '/')
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
      case CType.OutPrimitivePtr(CType.Long) =>
        val pre = List(
          s"jlongArray $jName = env->NewLongArray(1);",
          s"if (${p.name}) { jlong _v = static_cast<jlong>(*${p.name}); env->SetLongArrayRegion($jName, 0, 1, &_v); }"
        )
        val post = List(
          s"if (${p.name}) { jlong _v; env->GetLongArrayRegion($jName, 0, 1, &_v); *${p.name} = static_cast<int64_t>(_v); }"
        )
        (pre, jName, post)
      case CType.OutPrimitivePtr(CType.SizeT) =>
        val pre = List(
          s"jlongArray $jName = env->NewLongArray(1);",
          s"if (${p.name}) { jlong _v = static_cast<jlong>(*${p.name}); env->SetLongArrayRegion($jName, 0, 1, &_v); }"
        )
        val post = List(
          s"if (${p.name}) { jlong _v; env->GetLongArrayRegion($jName, 0, 1, &_v); *${p.name} = static_cast<size_t>(_v); }"
        )
        (pre, jName, post)
      case CType.OutPrimitivePtr(CType.Float) =>
        val pre = List(
          s"jfloatArray $jName = env->NewFloatArray(1);",
          s"if (${p.name}) { jfloat _v = static_cast<jfloat>(*${p.name}); env->SetFloatArrayRegion($jName, 0, 1, &_v); }"
        )
        val post = List(
          s"if (${p.name}) { jfloat _v; env->GetFloatArrayRegion($jName, 0, 1, &_v); *${p.name} = static_cast<float>(_v); }"
        )
        (pre, jName, post)
      case CType.OutPrimitivePtr(CType.Double) =>
        val pre = List(
          s"jdoubleArray $jName = env->NewDoubleArray(1);",
          s"if (${p.name}) { jdouble _v = static_cast<jdouble>(*${p.name}); env->SetDoubleArrayRegion($jName, 0, 1, &_v); }"
        )
        val post = List(
          s"if (${p.name}) { jdouble _v; env->GetDoubleArrayRegion($jName, 0, 1, &_v); *${p.name} = static_cast<double>(_v); }"
        )
        (pre, jName, post)
      case CType.OutPrimitivePtr(CType.UInt) =>
        val pre = List(
          s"jintArray $jName = env->NewIntArray(1);",
          s"if (${p.name}) { jint _v = static_cast<jint>(*${p.name}); env->SetIntArrayRegion($jName, 0, 1, &_v); }"
        )
        val post = List(
          s"if (${p.name}) { jint _v; env->GetIntArrayRegion($jName, 0, 1, &_v); *${p.name} = static_cast<unsigned int>(_v); }"
        )
        (pre, jName, post)
      case CType.OutPrimitivePtr(inner) =>
        val pre = List(
          s"jintArray $jName = env->NewIntArray(1);",
          s"if (${p.name}) { jint _v = static_cast<jint>(*${p.name}); env->SetIntArrayRegion($jName, 0, 1, &_v); }"
        )
        val post = List(
          s"if (${p.name}) { jint _v; env->GetIntArrayRegion($jName, 0, 1, &_v); *${p.name} = static_cast<${Naming.cType(inner)}>(_v); }"
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
        val javaFqn  = Naming.fullyQualifiedJavaName(cefName).replace('.', '/')
        val isScoped = scopedNames.contains(cefName)
        // Find adjacent count param (convention: xxxCount before xxx)
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
      case CType.Int =>
        (Nil, s"static_cast<jint>(${p.name})", Nil)
      case CType.UInt =>
        (Nil, s"static_cast<jint>(${p.name})", Nil)
      case CType.Char =>
        (Nil, s"static_cast<jchar>(${p.name})", Nil)
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
        // Const geometry struct pointer -> construct Java object
        val javaFqn                            = Naming.fullyQualifiedJavaName(cefName).replace('.', '/')
        val (ctorSig, ctorArgsList, nestedPre) = byValueCtorFromPtr(cefName, p.name)
        val sizeSet                            = bvSetNativeSize(cefName, jName, s"${jName}_cls", p.name)
        val pre                                = nestedPre ++ List(
          s"""auto ${jName}_cls = env->FindClass("$javaFqn");""",
          s"""auto ${jName}_ctor = env->GetMethodID(${jName}_cls, "<init>", "$ctorSig");""",
          s"""auto $jName = ${fmtNewObject(s"${jName}_cls", s"${jName}_ctor", ctorArgsList, p.name)};"""
        ) ++ (if (sizeSet.nonEmpty) List(sizeSet) else Nil)
        (pre, jName, Nil)
      case CType.ByValueOut(cefName) =>
        // Non-const by-value struct pointer (out-param) -> Cef*.Mutable object, with write-back
        val mutableFqn                         = Naming.fullyQualifiedMutableName(cefName).replace('.', '/')
        val (ctorSig, ctorArgsList, nestedPre) = byValueCtorFromPtr(cefName, p.name)
        val sizeSet                            = bvSetNativeSize(cefName, jName, s"${jName}_cls", p.name)
        val pre                                = nestedPre ++ List(
          s"""auto ${jName}_cls = env->FindClass("$mutableFqn");""",
          s"""auto ${jName}_ctor = env->GetMethodID(${jName}_cls, "<init>", "$ctorSig");""",
          s"""auto $jName = ${fmtNewObject(s"${jName}_cls", s"${jName}_ctor", ctorArgsList, p.name)};"""
        ) ++ (if (sizeSet.nonEmpty) List(sizeSet) else Nil)
        val post = List(s"if (${p.name} && $jName) {") ++
          bvWriteBackLines(cefName, p.name, jName, s"${jName}_cls").map(l => s"    $l") ++
          List("}")
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
        // Wrap void* buffer as a direct ByteBuffer using the adjacent size param.
        val sizeExpr = allParams.find(_.name == sizeParam).map(_.name).getOrElse("0")
        val isConst  = p.rawCType.contains("const")
        val castExpr =
          if (isConst) s"const_cast<void*>(static_cast<const void*>(${p.name}))" else s"static_cast<void*>(${p.name})"
        val pre = List(
          s"jobject $jName = ($sizeExpr > 0 && ${p.name}) ? env->NewDirectByteBuffer($castExpr, static_cast<jlong>($sizeExpr)) : nullptr;"
        )
        (pre, jName, Nil)
      case CType.BufferSize(_) =>
        // Hidden from Java - skip in JNI call args
        (Nil, "", Nil)
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
      case other =>
        throw new RuntimeException(s"convertNativeToJni: unhandled type $other for param ${p.name}")
    }
  }

  // Count JNI local references created by a handler trampoline, for PushLocalFrame capacity.
  private def localRefCount(fn: FnPtr): Int = {
    val fixed     = 1 // GetObjectClass(h->javaHandler) -> cls
    val paramRefs = fn.params.zipWithIndex.map { case (p, idx) =>
      p.typ match {
        case CType.ObjectPtr(_)    => 3 // FindClass, GetMethodID(->0), NewObject
        case CType.OutObjectPtr(_) =>
          6 + 1 // pre: 2xFindClass, 2xGetMethodID(->0), 2xNewObject + init; post: CallObjectMethod
        case CType.OpaquePtr => 3 // FindClass, GetMethodID(->0), NewObject
        case CType.Enum(_)   => 3 // FindClass, GetStaticMethodID(->0), CallStaticObjectMethod
        case CType.JString   => 1 // CefStringToJString
        case CType.StringList | CType.StringMap | CType.StringMultimap => 1
        case CType.Buffer(_)                                           => 1 // NewDirectByteBuffer
        case CType.PixelBuffer                                         => 1 // NewDirectByteBuffer
        case CType.OutPrimitivePtr(_)                                  => 1 // NewArray
        case CType.OutInt                                              => 1
        case CType.OutBool                                             => 1
        case CType.ObjectPtrArray(cefName)                             =>
          // FindClass + NewObjectArray + N elements; estimate conservatively
          val countParam = if (idx > 0) Some(fn.params(idx - 1)) else None
          3 + 16 // fixed overhead + headroom for elements
        case CType.ByValueIn(cefName)    => byValueLocalRefs(cefName) + 3 // nested + FindClass, ctor, NewObject
        case CType.ByValueOut(cefName)   => byValueLocalRefs(cefName) + 3 + byValueWriteBackRefs(cefName)
        case CType.ByValueArray(cefName) =>
          // FindClass + NewObjectArray + Nx(NewObject+DeleteLocalRef); elements are deleted, so fixed overhead
          byValueLocalRefs(cefName) + 3 + 8 // headroom for array elements
        case _ => 0 // primitives, Ptr, DataStruct, BufferSize
      }
    }.sum
    val returnRefs = fn.ret match {
      case _ if isHandlerPtr(fn.ret) => 4 // jResult + Optional class + isPresent result + handlerObj
      case CType.Enum(_)             => 1 // jResult (jobject)
      case CType.ObjectPtr(_)        => 1 // jResult (jobject)
      case CType.OpaquePtr           => 1 // jResult (jobject)
      case _                         => 0
    }
    fixed + paramRefs + returnRefs + 4 // +4 safety margin
  }

  // Count local refs created by nested by-value struct construction.
  private def byValueLocalRefs(cefName: String): Int =
    dataStructs.get(cefName) match {
      case Some(ds) =>
        ds.fields.filterNot(isSizeField).map { f =>
          f.typ match {
            case CType.DataStruct(nested) if CHeaderParser.isByValueStruct(nested) =>
              3 + byValueLocalRefs(nested) // FindClass, ctor, NewObject + nested
            case CType.JString => 1 // CefStringToJString
            case CType.Enum(_) => 3 // FindClass, of, CallStatic
            case _             => 0
          }
        }.sum
      case None => 0
    }

  // Count local refs created by the write-back phase of a ByValueOut param (bvCopyJavaToCStruct).
  private def byValueWriteBackRefs(cefName: String): Int =
    dataStructs.get(cefName) match {
      case Some(ds) =>
        ds.fields.filterNot(isSizeField).map { f =>
          f.typ match {
            case CType.DataStruct(nested) if CHeaderParser.isByValueStruct(nested) =>
              2 // GetObjectField + GetObjectClass; nested primitive reads create no refs
            case CType.JString => 1 // GetObjectField returns jstring local ref
            case CType.Enum(_) => 2 // GetObjectField + GetObjectClass
            case _             => 0 // primitive reads via GetIntField/GetLongField create no local refs
          }
        }.sum
      case None => 0
    }

  // Convert JNI return value back to native C type.
  private def convertJniReturnToNative(ct: CType, jniVar: String): String = ct match {
    case CType.Void       => "" // should not be called for void
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
      // Extract nativePtr from the NativePeer object
      s"""$jniVar ? reinterpret_cast<$name*>(env->GetLongField($jniVar, env->GetFieldID(env->GetObjectClass($jniVar), "nativePtr", "J"))) : nullptr"""
    case CType.Ptr(inner) => s"reinterpret_cast<$inner*>($jniVar)"
    case CType.OpaquePtr  =>
      s"""reinterpret_cast<void*>($jniVar ? env->GetLongField($jniVar, env->GetFieldID(env->GetObjectClass($jniVar), "address", "J")) : 0)"""
    case CType.JString          => jniVar
    case CType.DataStruct(name) =>
      val javaFqn   = Naming.javaInternalName(Naming.fullyQualifiedJavaName(name))
      val bodyLines = bvReadFromJavaLines(name, "_result", jniVar, "_c")
      val inner     = (List(
        s"$name _result = {};",
        s"""if ($jniVar) {""",
        s"""    auto _c = env->FindClass("$javaFqn");"""
      ) ++ bodyLines.map(l => s"    $l") ++ List("}", "return _result;")).mkString("\n        ")
      s"([&]() {\n        $inner\n    })()"
    case _ => jniVar
  }

  // Default native return value for error/exception paths in handler trampolines.
  private def defaultNativeReturn(ct: CType, metaAttrs: List[(String, String)]): String = {
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

  private def renderHandlerFactory(structName: String, javaName: String): String =
    // C-linkage factory for use from other native code (e.g., browser creation)
    s"""extern "C" $structName* Create_Jni$javaName(JNIEnv* env, jobject handler) {
    JavaVM* jvm;
    env->GetJavaVM(&jvm);
    auto globalRef = env->NewGlobalRef(handler);
    return reinterpret_cast<$structName*>(new Jni$javaName(jvm, globalRef));
}"""

  private def convertJniToNative(p: Param, isOptional: Boolean = false): String =
    p.typ match {
      case CType.JString =>
        s"JStringToCefString(env, ${p.name})"
      case CType.ObjectPtr(cefName) =>
        s"""(${p.name} ? reinterpret_cast<$cefName*>(env->GetLongField(${p.name}, env->GetFieldID(env->GetObjectClass(${p.name}), "nativePtr", "J"))) : nullptr)"""
      case CType.Ptr(_) if isOptional =>
        s"(${p.name} ? reinterpret_cast<${Naming.cType(p.typ)}>(${p.name}) : nullptr)"
      case CType.Ptr(_)    => s"reinterpret_cast<${Naming.cType(p.typ)}>(${p.name})"
      case CType.OpaquePtr =>
        val castType = if (p.rawCType.nonEmpty) p.rawCType else "void*"
        s"""reinterpret_cast<$castType>(${p.name} ? env->GetLongField(${p.name}, env->GetFieldID(env->GetObjectClass(${p.name}), "address", "J")) : 0)"""
      case CType.Bool    => s"static_cast<bool>(${p.name})"
      case CType.Enum(_) =>
        s"""static_cast<${Naming.cType(
            p.typ
          )}>(env->GetLongField(${p.name}, env->GetFieldID(env->GetObjectClass(${p.name}), "value", "J")))"""
      case CType.StringList     => s"JavaListToCefStringList(env, ${p.name})"
      case CType.StringMap      => s"JavaMapToCefStringMap(env, ${p.name})"
      case CType.StringMultimap => s"JavaMapToCefStringMultimap(env, ${p.name})"
      case _                    => p.name
    }

  sealed trait BvFieldType
  case object BvInt                          extends BvFieldType
  case object BvFloat                        extends BvFieldType
  case object BvDouble                       extends BvFieldType
  case object BvLong                         extends BvFieldType
  case object BvChar                         extends BvFieldType
  case object BvString                       extends BvFieldType
  case class BvEnum(cefName: String)         extends BvFieldType
  case class BvNestedStruct(cefName: String) extends BvFieldType

  case class BvField(cName: String, javaName: String, typ: BvFieldType)

  // By-value struct field definitions. The generated layout must match the C struct layout.
  // The `size` field is a special versioning field that CEF validates separately.
  private def isSizeField(f: Field): Boolean = f.name == "size" && f.typ == CType.SizeT

  // Whether the given struct has a `size_t size` version field.
  private def hasNativeSizeField(cefName: String): Boolean =
    dataStructs.get(cefName).exists(_.fields.exists(isSizeField))

  // Write the native sizeof value into the Java `size` field when that field exists.
  private def bvSetNativeSize(cefName: String, javaObj: String, clsVar: String, nativePtr: String): String =
    if (hasNativeSizeField(cefName))
      s"""if ($javaObj) env->SetLongField($javaObj, env->GetFieldID($clsVar, "size", "J"), static_cast<jlong>($nativePtr->size));"""
    else ""

  private def byValueFields(cefName: String): List[BvField] =
    dataStructs.get(cefName) match {
      case Some(ds) =>
        ds.fields.filterNot(isSizeField).map { f =>
          val javaName = Naming.toCamelCase(f.name)
          val bvType   = f.typ match {
            case CType.Int | CType.UInt | CType.Bool                               => BvInt
            case CType.Enum(enumName)                                              => BvEnum(enumName)
            case CType.Char                                                        => BvChar
            case CType.Float                                                       => BvFloat
            case CType.Double                                                      => BvDouble
            case CType.Long | CType.SizeT                                          => BvLong
            case CType.JString                                                     => BvString
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
          s"No DataStruct declaration found for by-value struct $cefName - is it parsed from headers?"
        )
    }

  private def bvJniSig(t: BvFieldType): String = t match {
    case BvInt             => "I"
    case BvChar            => "C"
    case BvFloat           => "F"
    case BvDouble          => "D"
    case BvLong            => "J"
    case BvString          => "Ljava/lang/String;"
    case BvEnum(n)         => s"L${Naming.fullyQualifiedJavaName(n).replace('.', '/')};"
    case BvNestedStruct(n) => s"L${Naming.fullyQualifiedJavaName(n).replace('.', '/')};"
  }

  private def bvCastToJni(t: BvFieldType, expr: String): String = t match {
    case BvInt     => s"static_cast<jint>($expr)"
    case BvChar    => s"static_cast<jchar>($expr)"
    case BvFloat   => s"static_cast<jfloat>($expr)"
    case BvDouble  => s"static_cast<jdouble>($expr)"
    case BvLong    => s"static_cast<jlong>($expr)"
    case BvString  => s"CefStringToJString(env, &($expr))"
    case BvEnum(_) => s"static_cast<jlong>($expr)" // enum value for CefEnum.of(long)
    case _         => expr
  }

  private def bvGetAndCast(t: BvFieldType, obj: String, cls: String, javaName: String): String = t match {
    case BvInt    => s"""env->GetIntField($obj, env->GetFieldID($cls, "$javaName", "I"))"""
    case BvChar   => s"""env->GetCharField($obj, env->GetFieldID($cls, "$javaName", "C"))"""
    case BvFloat  => s"""env->GetFloatField($obj, env->GetFieldID($cls, "$javaName", "F"))"""
    case BvDouble => s"""env->GetDoubleField($obj, env->GetFieldID($cls, "$javaName", "D"))"""
    case BvLong   => s"""static_cast<size_t>(env->GetLongField($obj, env->GetFieldID($cls, "$javaName", "J")))"""
    case _ => throw new RuntimeException(s"bvGetAndCast called on $t - use bvCopyJavaToCStruct for strings/nested")
  }

  // Format a NewObject call, breaking args across lines when there are many.
  private def fmtNewObject(clsVar: String, ctorVar: String, args: List[String], guard: String = ""): String =
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
  private def byValueCtorFromPtr(cefName: String, ptrName: String): (String, List[String], List[String]) = {
    val fields = byValueFields(cefName)
    // Pre-create nested struct objects and strings (both are jobject args)
    val objectCreation = fields.collect {
      case BvField(cName, _, BvNestedStruct(nestedCef)) =>
        val varName      = s"_bv_${ptrName}_$cName"
        val nestedFqn    = Naming.fullyQualifiedJavaName(nestedCef).replace('.', '/')
        val nestedFields = byValueFields(nestedCef)
        val nestedSig    = s"(${nestedFields.map(f => bvJniSig(f.typ)).mkString})V"
        val nestedArgs   = nestedFields.map(f => bvCastToJni(f.typ, s"$ptrName->$cName.${f.cName}")).mkString(", ")
        (
          cName,
          varName,
          List(
            s"""auto ${varName}_cls = env->FindClass("$nestedFqn");""",
            s"""auto ${varName}_ctor = env->GetMethodID(${varName}_cls, "<init>", "$nestedSig");""",
            s"""auto $varName = env->NewObject(${varName}_cls, ${varName}_ctor, $nestedArgs);"""
          )
        )
      case BvField(cName, _, BvString) =>
        val varName = s"_bv_${ptrName}_$cName"
        (cName, varName, List(s"""auto $varName = CefStringToJString(env, &$ptrName->$cName);"""))
      case BvField(cName, _, BvEnum(enumCefName)) =>
        val varName = s"_bv_${ptrName}_$cName"
        val enumFqn = Naming.fullyQualifiedJavaName(enumCefName).replace('.', '/')
        (
          cName,
          varName,
          List(
            s"""auto ${varName}_cls = env->FindClass("$enumFqn");""",
            s"""auto ${varName}_of = env->GetStaticMethodID(${varName}_cls, "of", "(J)L$enumFqn;");""",
            s"""auto $varName = env->CallStaticObjectMethod(${varName}_cls, ${varName}_of, static_cast<jlong>($ptrName->$cName));"""
          )
        )
    }
    val objectVars = objectCreation.map(t => (t._1, t._2)).toMap
    val objectPre  = objectCreation.flatMap(_._3)

    val sig      = s"(${fields.map(f => bvJniSig(f.typ)).mkString})V"
    val argsList = fields.map {
      case BvField(cName, _, BvNestedStruct(_) | BvString | BvEnum(_)) => objectVars(cName)
      case BvField(cName, _, typ)                                      => bvCastToJni(typ, s"$ptrName->$cName")
    }
    (sig, argsList, objectPre)
  }

  // Copy fields from a Java object into a C struct via pointer or by-value access.
  // Returns a list of C++ statements (one per line, no trailing newlines).
  private def bvCopyJavaToCStruct(
      cefName: String,
      dest: String,
      accessor: String,
      javaObj: String,
      clsVar: String,
      prefix: String
  ): List[String] = {
    val fields = byValueFields(cefName)
    fields.flatMap {
      case BvField(cName, javaName, BvNestedStruct(nestedCef)) =>
        val nestedFields = byValueFields(nestedCef)
        val sig          = bvJniSig(BvNestedStruct(nestedCef))
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
      case BvField(cName, javaName, BvString) =>
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
      case BvField(cName, javaName, BvEnum(enumCefName)) =>
        val sig = bvJniSig(BvEnum(enumCefName))
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

  private def bvWriteBackLines(cefName: String, ptrName: String, javaObj: String, clsVar: String): List[String] =
    bvCopyJavaToCStruct(cefName, ptrName, "->", javaObj, clsVar, "wb")

  private def bvReadFromJavaLines(cefName: String, structVar: String, javaObj: String, clsVar: String): List[String] = {
    val base     = bvCopyJavaToCStruct(cefName, structVar, ".", javaObj, clsVar, "rd")
    val hasSize  = dataStructs.get(cefName).exists(_.fields.exists(f => f.name == "size" && f.typ == CType.SizeT))
    val sizeInit = if (hasSize) List(s"$structVar.size = sizeof($cefName);") else Nil
    base ++ sizeInit
  }

  // Generate a field expression for array element access, for example ptr[i].field.
  private def byValueArrayFieldExpr(cefName: String, ptrName: String, idxVar: String): String = {
    val fields = byValueFields(cefName)
    fields.map {
      case BvField(_, _, BvNestedStruct(_)) =>
        throw new RuntimeException(s"Nested struct in ByValueArray not supported: $cefName")
      case BvField(_, _, BvString) =>
        throw new RuntimeException(s"String field in ByValueArray not supported: $cefName")
      case BvField(_, _, BvEnum(_)) =>
        throw new RuntimeException(s"Enum field in ByValueArray not supported: $cefName")
      case BvField(cName, _, typ) => bvCastToJni(typ, s"$ptrName[$idxVar].$cName")
    }.mkString(", ")
  }

  // Return ctor metadata used to materialise by-value data structs into Java objects.
  private def dataStructCtorInfo(cefName: String): (String, List[String], List[String]) =
    dataStructs.get(cefName) match {
      case Some(_) =>
        val (sig, args, pre) = byValueCtorFromPtr(cefName, "(&result)")
        (sig, args, pre)
      case None => ("()V", Nil, Nil)
    }

  // Render a C++ JNI wrapper for a free function, used by globals and object-associated static helpers.
  def renderFreeFunction(
      javaClassName: String,
      ff: CefDecl.FreeFunction,
      isDirectClass: Boolean = false
  ): String = {
    val retJni    = Naming.jniType(ff.ret)
    val exportSig = if (isDirectClass) Naming.jniExportStatic(javaClassName, ff.javaMethodName, retJni)
    else Naming.jniExportPeerStatic(javaClassName, ff.javaMethodName, retJni)
    val jniParams =
      ("JNIEnv* env" :: "jclass clz" :: ff.visibleParams.map { p =>
        s"${Naming.jniType(p.typ)} ${p.name}"
      }).mkString(", ")

    val optionalParams = ff.metaAttrs.collect { case ("optional_param", p) => p }.toSet

    val npeChecks = ff.params.collect {
      case p if !optionalParams.contains(p.name) && JavaCodeGen.isStrictNullCheck(p.typ) =>
        val javaParamName = Naming.toCamelCase(p.name)
        s"""    if (!${p.name}) { env->ThrowNew(env->FindClass("java/lang/NullPointerException"), "$javaParamName must not be null"); return${defaultReturn(
            ff.ret
          )}; }"""
    }

    // Reuse the same arg conversion logic as renderObjectFunction
    val argConversions = ff.params.map { p =>
      val isOpt = optionalParams.contains(p.name)
      convertFreeFunctionParam(p, ff.params, isOpt, ff.cName, ff.ret)
    }
    val preCallLines  = argConversions.flatMap(_._1)
    val callArgs      = argConversions.map(_._2).mkString(", ")
    val postCallLines = argConversions.flatMap(_._3)

    val fnCall = s"${ff.cName}($callArgs)"

    val preBlock  = if (preCallLines.nonEmpty) preCallLines.mkString("\n") + "\n" else ""
    val postBlock = if (postCallLines.nonEmpty) "\n" + postCallLines.mkString("\n") else ""
    val npeBlock  = if (npeChecks.nonEmpty) npeChecks.mkString("\n") + "\n" else ""

    val body = ff.ret match {
      case CType.Void =>
        s"""$npeBlock$preBlock    $fnCall;$postBlock"""
      case CType.JString =>
        s"""$npeBlock$preBlock    auto result = $fnCall;$postBlock
    if (!result) return nullptr;
    auto jstr = CefStringToJString(env, result);
    cef_string_userfree_free(result);
    return jstr;"""
      case CType.Bool =>
        s"""$npeBlock$preBlock    auto _r = $fnCall;$postBlock
    return static_cast<jboolean>(_r);"""
      case CType.ObjectPtr(cefName) =>
        val javaFqn = Naming.fullyQualifiedJavaName(cefName).replace('.', '/')
        s"""$npeBlock$preBlock    auto _r = $fnCall;$postBlock
    if (!_r) return nullptr;
    auto _rCls = env->FindClass("$javaFqn$$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));"""
      case CType.OpaquePtr =>
        s"""$npeBlock$preBlock    auto _r = $fnCall;$postBlock
    auto _npCls = env->FindClass("${Naming.nativePointerInternalName}");
    auto _npCtor = env->GetMethodID(_npCls, "<init>", "(J)V");
    return env->NewObject(_npCls, _npCtor, reinterpret_cast<jlong>(_r));"""
      case CType.Ptr(_) =>
        s"""$npeBlock$preBlock    auto _r = $fnCall;$postBlock
    return reinterpret_cast<jlong>(_r);"""
      case CType.Enum(name) =>
        val javaFqn = Naming.fullyQualifiedJavaName(name).replace('.', '/')
        s"""$npeBlock$preBlock    auto _r = $fnCall;$postBlock
    auto _eCls = env->FindClass("$javaFqn");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)L$javaFqn;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));"""
      case _ =>
        s"""$npeBlock$preBlock    return static_cast<$retJni>($fnCall);"""
    }

    val cleanBody = body.linesIterator.filter(_.trim.nonEmpty).mkString("\n")

    s"""$exportSig($jniParams) {
$cleanBody
}"""
  }

  // Convert a free function parameter into pre-call, argument, and post-call code fragments.
  private def convertFreeFunctionParam(
      p: Param,
      allParams: List[Param],
      isOptional: Boolean,
      cFuncName: String = "",
      retType: CType = CType.Void
  ): (List[String], String, List[String]) =
    p.typ match {
      case CType.JString =>
        val tmp = s"_${p.name}_str"
        val pre = if (isOptional) List(
          s"    auto $tmp = ${p.name} ? JStringToCefString(env, ${p.name}) : nullptr;"
        )
        else List(s"    auto $tmp = JStringToCefString(env, ${p.name});")
        val post = List(s"    if ($tmp) cef_string_userfree_free($tmp);")
        (pre, tmp, post)
      case CType.ObjectPtr(cefName) if handlerNames.contains(cefName) =>
        // Handler-typed param: wrap the Java handler via its factory function
        val tmp      = s"_${p.name}_ptr"
        val javaName = Naming.structToJavaName(cefName)
        val factory  = s"Create_Jni$javaName"
        val pre      = List(
          s"    $cefName* $tmp = ${p.name} ? $factory(env, ${p.name}) : nullptr;"
        )
        (pre, tmp, Nil)
      case CType.ObjectPtr(cefName) =>
        val tmp     = s"_${p.name}_ptr"
        val extract =
          s"""reinterpret_cast<$cefName*>(env->GetLongField(${p.name}, env->GetFieldID(env->GetObjectClass(${p.name}), "nativePtr", "J")))"""
        val addRef = s"    if ($tmp) ${addRefExpr(tmp)}"
        val pre    = List(
          s"    $cefName* $tmp = ${p.name} ? $extract : nullptr;",
          addRef
        )
        (pre, tmp, Nil)
      case CType.ByValueIn(cefName) =>
        val tmp     = s"_${p.name}_val"
        val javaFqn = Naming.fullyQualifiedJavaName(cefName).replace('.', '/')
        val pre     = List(
          s"    $cefName $tmp = {};",
          s"""    if (${p.name}) {""",
          s"""        auto _c = env->FindClass("$javaFqn");"""
        ) ++
          bvReadFromJavaLines(cefName, tmp, p.name, "_c").map(l => s"        $l") ++
          List("    }")
        (pre, s"&$tmp", Nil)
      case CType.ByValueOut(cefName) =>
        val tmp        = s"_${p.name}_val"
        val mutableFqn = Naming.fullyQualifiedMutableName(cefName).replace('.', '/')
        val pre        = List(
          s"    $cefName $tmp = {};",
          s"""    if (${p.name}) {""",
          s"""        auto _c = env->FindClass("$mutableFqn");"""
        ) ++
          bvReadFromJavaLines(cefName, tmp, p.name, "_c").map(l => s"        $l") ++
          List("    }")
        (pre, s"&$tmp", Nil)
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
          s"    if (${p.name}) { jboolean _jv; env->GetBooleanArrayRegion(${p.name}, 0, 1, &_jv); $tmp = static_cast<bool>(_jv); }"
        )
        val post = List(
          s"    if (${p.name}) { jboolean _jv = static_cast<jboolean>($tmp); env->SetBooleanArrayRegion(${p.name}, 0, 1, &_jv); }"
        )
        (pre, s"&$tmp", post)
      case CType.Buffer(sizeParam) =>
        val addrVar       = s"_${p.name}_addr"
        val isConst       = p.rawCType.contains("const")
        val castType      = if (isConst) "const void*" else "void*"
        val javaParamName = Naming.toCamelCase(p.name)
        val pre           = List(
          s"    $castType $addrVar = ${p.name} ? env->GetDirectBufferAddress(${p.name}) : nullptr;",
          s"""    if (${p.name} && !$addrVar) { env->ThrowNew(env->FindClass("java/lang/IllegalArgumentException"), "$javaParamName must be a direct ByteBuffer; use ByteBuffer.allocateDirect(...)"); return${defaultReturn(
              retType
            )}; }"""
        )
        (pre, addrVar, Nil)
      case CType.BufferSize(bufferParam) =>
        val rawC  = p.rawCType.replaceAll("\\s+", " ").trim
        val cCast = if (rawC.nonEmpty && rawC != "size_t") rawC else "size_t"
        (Nil, s"static_cast<$cCast>(env->GetDirectBufferCapacity($bufferParam))", Nil)
      case CType.Enum(_) =>
        (
          Nil,
          s"""static_cast<${Naming.cType(
              p.typ
            )}>(env->GetLongField(${p.name}, env->GetFieldID(env->GetObjectClass(${p.name}), "value", "J")))""",
          Nil
        )
      case CType.Bool =>
        (Nil, s"static_cast<bool>(${p.name})", Nil)
      case CType.OpaquePtr =>
        val castType = if (p.rawCType.nonEmpty) p.rawCType else "void*"
        (
          Nil,
          s"""reinterpret_cast<$castType>(${p.name} ? env->GetLongField(${p.name}, env->GetFieldID(env->GetObjectClass(${p.name}), "address", "J")) : 0)""",
          Nil
        )
      case CType.StringList =>
        val tmp        = s"_${p.name}_csl"
        val isOutParam = cFuncName.contains("_get_") || cFuncName.startsWith("cef_get_")
        (
          List(s"    auto $tmp = JavaListToCefStringList(env, ${p.name});"),
          tmp,
          if (isOutParam) List(s"    CefStringListWriteBack(env, $tmp, ${p.name});")
          else List(s"    cef_string_list_free($tmp);")
        )
      case CType.StringMap =>
        val tmp        = s"_${p.name}_csm"
        val isOutParam = cFuncName.contains("_get_") || cFuncName.startsWith("cef_get_")
        (
          List(s"    auto $tmp = JavaMapToCefStringMap(env, ${p.name});"),
          tmp,
          if (isOutParam) List(s"    CefStringMapWriteBack(env, $tmp, ${p.name});")
          else List(s"    cef_string_map_free($tmp);")
        )
      case CType.StringMultimap =>
        val tmp        = s"_${p.name}_csmm"
        val isOutParam = cFuncName.contains("_get_") || cFuncName.startsWith("cef_get_")
        (
          List(s"    auto $tmp = JavaMapToCefStringMultimap(env, ${p.name});"),
          tmp,
          if (isOutParam) List(s"    CefStringMultimapWriteBack(env, $tmp, ${p.name});")
          else List(s"    cef_string_multimap_free($tmp);")
        )
      case CType.DataStruct(cefName) if CHeaderParser.isByValueStruct(cefName) =>
        // By-value struct passed by value (not pointer): extract fields from Java object
        val tmp     = s"_${p.name}_val"
        val javaFqn = Naming.fullyQualifiedJavaName(cefName).replace('.', '/')
        val pre     = List(
          s"    $cefName $tmp = {};",
          s"""    if (${p.name}) {""",
          s"""        auto _c = env->FindClass("$javaFqn");"""
        ) ++
          bvReadFromJavaLines(cefName, tmp, p.name, "_c").map(l => s"        $l") ++
          List("    }")
        (pre, tmp, Nil)
      case _ => (Nil, p.name, Nil)
    }

  // Extract a referenced struct name from a CType when present.
  @tailrec
  private def structNameFromType(ct: CType): Option[String] = ct match {
    case CType.ObjectPtr(n)                  => Some(n)
    case CType.OutObjectPtr(n)               => Some(n)
    case CType.ObjectPtrArray(n)             => Some(n)
    case CType.ByValueIn(n)                  => Some(n)
    case CType.ByValueOut(n)                 => Some(n)
    case CType.ByValueArray(n)               => Some(n)
    case CType.DataStruct(n)                 => Some(n)
    case CType.CountFuncArray(elem, _, _, _) => structNameFromType(elem)
    case _                                   => None
  }

  // Collect the struct names referenced by a function's params and return type.
  private def collectReferencedStructsFromParams(ret: CType, params: List[Param]): Set[String] =
    (structNameFromType(ret) ++ params.flatMap(p => structNameFromType(p.typ))).toSet

  // Collect all struct names referenced by function params and return types.
  private def collectReferencedStructs(fns: List[FnPtr]): Set[String] =
    fns.flatMap(fn => structNameFromType(fn.ret) ++ fn.params.flatMap(p => structNameFromType(p.typ))).toSet

  private def defaultReturn(ct: CType): String = ct match {
    case CType.Void                       => ""
    case CType.Bool                       => " JNI_FALSE"
    case CType.JString                    => " nullptr"
    case CType.Ptr(_)                     => " 0"
    case CType.ObjectPtr(_)               => " nullptr"
    case CType.OpaquePtr                  => " nullptr"
    case CType.Buffer(_)                  => " nullptr"
    case CType.DataStruct(_)              => " nullptr"
    case CType.CountFuncArray(_, _, _, _) => " nullptr"
    case _                                => " 0"
  }
}
