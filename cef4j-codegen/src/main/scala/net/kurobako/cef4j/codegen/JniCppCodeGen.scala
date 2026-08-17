package net.kurobako.cef4j.codegen

import java.nio.file.Path
import scala.annotation.tailrec

import net.kurobako.cef4j.codegen.passes.RefineTree

class JniCppCodeGen(
    dataStructs: Map[String, CefDecl.DataStruct],
    handlerNames: Set[String] = Set.empty,
    scopedNames: Set[String] = Set.empty,
    structHeaderMap: Map[String, String] = Map.empty
)(using Naming.Context, Banners) {

  import JniNaming.addRefExpr
  import JniNaming.jniMutableName
  import JniNaming.jniName
  import JniNaming.outPrimInfo

  val bv         = new JniCppByValueCodeGen(dataStructs)
  val trampoline = new JniCppHandlerTrampolineGen(handlerNames, scopedNames, bv)

  private val GeneratedBanner = Banners.cpp

  private def joinIndentedLines(lines: List[String], indent: String): String =
    lines match {
      case Nil => ""
      case xs  => "\n" + xs.map(line => s"$indent$line").mkString("\n")
    }

  private def isHandlerPtr(ct: CType): Boolean = JniNaming.isHandlerPtr(ct, handlerNames)

  private def strCollInfo(ct: CType): (String, String, String, String) = ct match {
    case CType.StringList     => ("_csl", "JavaListToCefStringList", "cef_string_list_free", "CefStringListWriteBack")
    case CType.StringMap      => ("_csm", "JavaMapToCefStringMap", "cef_string_map_free", "CefStringMapWriteBack")
    case CType.StringMultimap =>
      ("_csmm", "JavaMapToCefStringMultimap", "cef_string_multimap_free", "CefStringMultimapWriteBack")
    case _ => throw IllegalArgumentException(s"Not a string collection type: $ct")
  }

  private def convertStringCollectionParam(
      paramName: String,
      jniParamName: String,
      ct: CType,
      isOutParam: Boolean
  ): (List[String], String, List[String]) = {
    val (suffix, javaToC, freeFunc, writeBack) = strCollInfo(ct)
    val tmp                                    = s"_$paramName$suffix"
    (
      List(s"    auto $tmp = $javaToC(env, $jniParamName);"),
      tmp,
      if (isOutParam) List(s"    $writeBack(env, $tmp, $jniParamName);")
      else List(s"    $freeFunc($tmp);")
    )
  }

  private def convertCStringArrayParam(
      paramName: String,
      jniParamName: String,
      mutable: Boolean
  ): (List[String], String, List[String]) = {
    val storageVar = s"_${paramName}_storage"
    val ptrsVar    = s"_${paramName}_ptrs"
    val argVar     = s"_${paramName}_arr"
    val helper     = if (mutable) "JavaListToCStringArray" else "JavaListToConstCStringArray"
    val ptrVecT    = if (mutable) "char*" else "const char*"
    val cArrT      = if (mutable) "char**" else "const char* const*"
    (
      List(
        s"    std::vector<std::string> $storageVar;",
        s"    std::vector<$ptrVecT> $ptrsVar;",
        s"    $cArrT $argVar = $helper(env, $jniParamName, $storageVar, $ptrsVar);"
      ),
      argVar,
      Nil
    )
  }

  private def collectHandlerParamFactories(
      fns: List[FnPtr],
      ffs: List[CefDecl.FreeFunction]
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

  def emit(decl: CefDecl.ObjectStruct, outDir: Path, freeFunctions: List[CefDecl.FreeFunction] = Nil): Unit =
    writeCppFile(outDir, s"${Naming.cefBaseName(decl.name)}_N.cpp", emitToString(decl, freeFunctions))

  def emitHandler(decl: CefDecl.HandlerStruct, outDir: Path): Unit =
    writeCppFile(outDir, s"${Naming.cefBaseName(decl.name)}.cpp", emitHandlerToString(decl))

  def emitGlobals(freeFunctions: List[CefDecl.FreeFunction], outDir: Path): Unit =
    if (freeFunctions.nonEmpty) {
      val refs    = freeFunctions.flatMap(ff => collectReferencedStructsFromParams(ff.ret, ff.params)).toSet
      val headers = freeFunctions.flatMap(ff => structHeaderMap.get(ff.ownerStruct)).distinct ++
        freeFunctions.map(_.sourceHeader).distinct
      val includes        = renderIncludesForFreeFunc(headers.distinct, refs)
      val handlerFwdDecls = renderHandlerFactoryForwardDecls(collectHandlerParamFactories(Nil, freeFunctions))
      val functions       =
        freeFunctions.map(ff => renderFreeFunction("CefGlobals", ff, isDirectClass = true)).mkString("\n\n")
      writeCppFile(outDir, "cef_globals_N.cpp", renderGeneratedCpp(includes, s"$handlerFwdDecls$functions"))
    }

  private def writeCppFile(outDir: Path, fileName: String, content: String): Unit = {
    val file = outDir.resolve(fileName)
    FileSystem.createDirectories(file.getParent)
    AtomicFiles.writeString(file, content.replace("\r\n", "\n").replace("\r", "\n"))
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
      "\n\n" + freeFunctions
        .map(ff => renderFreeFunction(javaName, ff, ownerStructName = decl.name))
        .mkString("\n\n")
    } else ""
    renderGeneratedCpp(includes, s"$handlerFwdDecls$releaseFn\n\n$functions$ffFunctions")
  }

  private def renderRelease(structName: String, scoped: Boolean): String = {
    val exportSig = Naming.jniExportPeerStatic(Naming.jniClassPrefix(structName), "release", "void")
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
    val includes = renderIncludes(decl.name, decl.sourceHeader, refs, Nil)

    val initAssignments = joinIndentedLines(decl.fns.map(fn => s"${fn.name} = &_${fn.name};"), "        ")

    val structDef = s"""
struct Jni$javaName : public ${decl.name} {
    JavaVM* jvm;
    jobject javaHandler;  // global ref
    std::atomic<int> refCount{1};

    Jni$javaName(JavaVM* vm, jobject handler) : ${decl.name}{}, jvm(vm) {
        javaHandler = handler;
        InitRefCount<Jni$javaName, ${decl.name}>(reinterpret_cast<cef_base_ref_counted_t*>(static_cast<${decl.name}*>(this)));$initAssignments
    }
"""

    val trampolines = decl.fns.map(fn => trampoline.renderHandlerTrampoline(decl.name, fn, javaName)).mkString("\n\n")

    val factoryFn = trampoline.renderHandlerFactory(decl.name, javaName)

    val hasByValueArray = decl.fns.exists(_.params.exists {
      case Param(_, CType.ByValueArray(_), _, _) => true
      case _                                     => false
    })
    val vectorInclude = if (hasByValueArray) "\n#include <vector>" else ""

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
      referencedStructs: Set[String],
      additionalHeaders: List[String]
  ): String = {
    val header = if (sourceHeader.nonEmpty) sourceHeader
    else {
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
    val retJni    = Naming.jniType(fn.ret)
    val exportSig = Naming.jniExportPeer(structName, fn, retJni)
    val jniParams =
      ("JNIEnv* env" :: "jobject obj" :: "jlong self" :: fn.visibleParams.map {
        p =>
          s"${Naming.jniType(p.typ)} ${p.name}"
      }).mkString(", ")

    val castSelf  = s"    auto* s = reinterpret_cast<$structName*>(self);"
    val nullGuard = s"    if (!s) return${defaultReturn(fn.ret)};"

    val optionalParams = collectOptionalParams(fn.metaAttrs)
    val npeChecks      = renderStrictNullChecks(fn.params, optionalParams, fn.ret)

    val argConversions = fn.params.map { p =>
      val isOpt = optionalParams.contains(p.name)
      p.typ match {
        case CType.ByValueArray(cefName) =>
          val countVar = fn.params.find(_.name.toLowerCase == s"${p.name.toLowerCase}count").map(_.name).getOrElse("0")
          val arrVar   = s"_${p.name}_arr"
          val sizeVar  = s"_${p.name}_sz"
          val pre      = List(
            s"    size_t $sizeVar = static_cast<size_t>($countVar);",
            s"    $cefName* $arrVar = $sizeVar > 0 ? new $cefName[$sizeVar]() : nullptr;",
            s"""    { auto _bvac = FindClassCached(env, "${jniName(cefName)}");""",
            s"    for (size_t _i = 0; _i < $sizeVar; _i++) {",
            s"        auto _elem = env->GetObjectArrayElement(${p.name}, _i);",
            s"        if (_elem) {"
          ) ++ bv.bvReadFromJavaLines(cefName, s"$arrVar[_i]", "_elem", "_bvac").map(l => s"            $l") ++ List(
            "        }",
            s"    } }"
          )
          (pre, arrVar, List(s"    delete[] $arrVar;"))
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
            s"""        auto _peerCls = FindClassCached(env, "${jniName(cefName)}$$NativePeer");""",
            s"""        auto _peerCtor = env->GetMethodID(_peerCls, "<init>", "(J)V");"""
          ) ++ addRefLine ++ List(
            s"        auto _newPeer = env->NewObject(_peerCls, _peerCtor, reinterpret_cast<jlong>($tmp));",
            s"""        auto _set = env->GetMethodID(env->GetObjectClass(${p.name}), "set", "(Ljava/lang/Object;)V");""",
            s"        env->CallVoidMethod(${p.name}, _set, _newPeer);",
            s"    }"
          )
          (pre, s"&$tmp", post)
        case CType.OutPrimitivePtr(inner) =>
          val (cPrim, jniPrim, jniInfix) = outPrimInfo(inner)
          val tmp                        = s"_${p.name}_val"
          val pre                        = List(
            s"    $cPrim $tmp = 0;",
            s"    if (${p.name}) { $jniPrim _jv; env->Get${jniInfix}ArrayRegion(${p.name}, 0, 1, &_jv); $tmp = static_cast<$cPrim>(_jv); }"
          )
          val post = List(
            s"    if (${p.name}) { $jniPrim _jv = static_cast<$jniPrim>($tmp); env->Set${jniInfix}ArrayRegion(${p.name}, 0, 1, &_jv); }"
          )
          (pre, s"&$tmp", post)
        case CType.ObjectPtrArray(cefName) =>
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
          val javaFqn = jniName(cefName)
          val pre     = List(
            s"    size_t $sizeVar = static_cast<size_t>($countVar);",
            s"    $cefName** $arrVar = $sizeVar > 0 ? new $cefName*[$sizeVar]() : nullptr;"
          )
          val post = List(
            s"""    auto _${p.name}_cls = FindClassCached(env, "$javaFqn$$NativePeer");""",
            s"""    auto _${p.name}_ctor = env->GetMethodID(_${p.name}_cls, "<init>", "(J)V");""",
            s"    for (size_t _i = 0; _i < $sizeVar; _i++) {",
            s"        auto _elem = $arrVar[_i] ? env->NewObject(_${p.name}_cls, _${p.name}_ctor, reinterpret_cast<jlong>($arrVar[_i])) : nullptr;",
            s"        env->SetObjectArrayElement(${p.name}, _i, _elem);",
            s"    }",
            s"    delete[] $arrVar;"
          )
          (pre, arrVar, post)
        case _ =>
          convertParamShared(p, isOpt, fn.name.startsWith("get_"), fn.ret)
      }
    }
    val blocks = renderCallBlocks(argConversions, npeChecks)

    val fnCall = if (blocks.callArgs.isEmpty) {
      s"s->${fn.name}(s)"
    } else {
      s"s->${fn.name}(s, ${blocks.callArgs})"
    }

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

    val preamble = s"$castSelf\n$nullGuard\n"
    val body     = fn.ret match {
      case CType.Void =>
        s"""${preamble}${blocks.npeBlock}$countFuncSetup
${blocks.preBlock}    $fnCall;${blocks.postBlock}"""
      case cfa @ CType.CountFuncArray(_, _, _, _) =>
        renderCountFuncArrayBody(
          fn,
          cfa,
          castSelf,
          nullGuard,
          blocks.npeBlock,
          blocks.preBlock
        )
      case _ =>
        preamble + renderReturnDispatch(fn.ret, retJni, fnCall, blocks.npeBlock, blocks.preBlock, blocks.postBlock)
    }

    renderGeneratedFunction(exportSig, jniParams, body)
  }

  private def renderReturnDispatch(
      ret: CType,
      retJni: String,
      fnCall: String,
      npeBlock: String,
      preBlock: String,
      postBlock: String
  ): String = ret match {
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
      val javaFqn = jniName(cefName)
      s"""$npeBlock$preBlock    auto _r = $fnCall;$postBlock
    if (!_r) return nullptr;
    auto _rCls = FindClassCached(env, "$javaFqn$$NativePeer");
    auto _rCtor = env->GetMethodID(_rCls, "<init>", "(J)V");
    return env->NewObject(_rCls, _rCtor, reinterpret_cast<jlong>(_r));"""
    case CType.Ptr(_) =>
      s"""$npeBlock$preBlock    auto _r = $fnCall;$postBlock
    return reinterpret_cast<jlong>(_r);"""
    case CType.OpaquePtr =>
      s"""$npeBlock$preBlock    auto _r = $fnCall;$postBlock
    auto _npCls = FindClassCached(env, "${Naming.nativePointerInternalName}");
    auto _npCtor = env->GetMethodID(_npCls, "<init>", "(J)V");
    return env->NewObject(_npCls, _npCtor, reinterpret_cast<jlong>(_r));"""
    case CType.DataStruct(cefName) =>
      val javaFqn                             = jniName(cefName)
      val (ctorSig, ctorArgsList, ctorPreOps) = bv.dataStructCtorInfo(cefName)
      val preOpsBlock = if (ctorPreOps.nonEmpty) "\n" + ctorPreOps.map(l => s"    $l").mkString("\n") else ""
      val sizeSetLine = if (bv.hasNativeSizeField(cefName))
        "\n    env->SetLongField(_dsResult, env->GetFieldID(cls, \"size\", \"J\"), static_cast<jlong>(result.size));"
      else ""
      val newObjExpr = bv.fmtNewObject("cls", "ctor", ctorArgsList)
      s"""$npeBlock$preBlock    $cefName result = $fnCall;$postBlock$preOpsBlock
    auto cls = FindClassCached(env, "$javaFqn");
    auto ctor = env->GetMethodID(cls, "<init>", "$ctorSig");
    auto _dsResult = $newObjExpr;$sizeSetLine
    return _dsResult;"""
    case CType.Enum(name) =>
      val javaFqn = jniName(name)
      s"""$npeBlock$preBlock    auto _r = $fnCall;$postBlock
    auto _eCls = FindClassCached(env, "$javaFqn");
    auto _eOf = env->GetStaticMethodID(_eCls, "of", "(J)L$javaFqn;");
    return env->CallStaticObjectMethod(_eCls, _eOf, static_cast<jlong>(_r));"""
    case CType.Long =>
      s"""$npeBlock$preBlock    return to_jlong($fnCall);"""
    case _ =>
      s"""$npeBlock$preBlock    return static_cast<$retJni>($fnCall);"""
  }

  private def renderCountFuncArrayBody(
      fn: FnPtr,
      cfa: CType.CountFuncArray,
      castSelf: String,
      nullGuard: String,
      npeBlock: String,
      preBlock: String
  ): String = {
    val CType.CountFuncArray(elemType, countFuncC, countParamName, arrayParamName) = cfa

    val countCall = s"    size_t _count = s->$countFuncC(s);"

    val (allocAndCall, convertAndReturn) = elemType match {
      case CType.ObjectPtr(cefName) =>
        val javaFqn = jniName(cefName)
        val alloc   = s"    $cefName** _arr = _count > 0 ? new $cefName*[_count]() : nullptr;"
        val call    = s"    s->${fn.name}(s, &_count, _arr);"
        val convert =
          s"""    auto _elemCls = FindClassCached(env, "$javaFqn$$NativePeer");
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
          case _                   => throw IllegalStateException("unreachable")
        }
        val javaFqn   = jniName(bvCefName)
        val alloc     = s"    $bvCefName* _arr = _count > 0 ? new $bvCefName[_count]() : nullptr;"
        val call      = s"    s->${fn.name}(s, &_count, _arr);"
        val fields    = bv.byValueFields(bvCefName)
        val ctorSig   = s"(${fields.map(f => bv.bvJniSig(f.typ)).mkString})V"
        val fieldExpr = bv.byValueArrayFieldExpr(bvCefName, "_arr", "_i")
        val convert   =
          s"""    auto _elemCls = FindClassCached(env, "$javaFqn");
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
        throw IllegalArgumentException(s"CountFuncArray: unsupported element type $other in ${fn.name}")
    }

    s"""$castSelf
$nullGuard
$npeBlock$preBlock$countCall
$allocAndCall
$convertAndReturn"""
  }

  def renderFreeFunction(
      javaClassName: String,
      ff: CefDecl.FreeFunction,
      isDirectClass: Boolean = false,
      ownerStructName: String = ""
  ): String = {
    val retJni    = Naming.jniType(ff.ret)
    val clsPrefix = if (ownerStructName.nonEmpty) Naming.jniClassPrefix(ownerStructName) else javaClassName
    val exportSig = if (isDirectClass) Naming.jniExportStatic(javaClassName, ff.javaMethodName, retJni)
    else Naming.jniExportPeerStatic(clsPrefix, ff.javaMethodName, retJni)
    val jniParams =
      ("JNIEnv* env" :: "jclass clz" :: ff.visibleParams.map { p =>
        s"${Naming.jniType(p.typ)} ${p.name}"
      }).mkString(", ")

    val optionalParams = collectOptionalParams(ff.metaAttrs)
    val npeChecks      = renderStrictNullChecks(ff.params, optionalParams, ff.ret)

    val argConversions = ff.params.map { p =>
      val isOpt = optionalParams.contains(p.name)
      val isOut = ff.cName.contains("_get_") || ff.cName.startsWith("cef_get_")
      convertParamShared(p, isOpt, isOut, ff.ret)
    }
    val blocks = renderCallBlocks(argConversions, npeChecks)

    // Preserve JVM fatal-signal handlers across cef_initialize on old CEF.
    val nativeFunction = if (ff.cName == "cef_initialize") "Cef4jInitialize" else ff.cName
    val fnCall         = s"$nativeFunction(${blocks.callArgs})"

    val body = ff.ret match {
      case CType.Void =>
        s"""${blocks.npeBlock}${blocks.preBlock}    $fnCall;${blocks.postBlock}"""
      case cfa @ CType.CountFuncArray(_, _, _, _) =>
        renderCountFuncArrayFreeFunctionBody(ff, cfa, blocks.npeBlock, blocks.preBlock, blocks.postBlock)
      case _ =>
        renderReturnDispatch(ff.ret, retJni, fnCall, blocks.npeBlock, blocks.preBlock, blocks.postBlock)
    }

    renderGeneratedFunction(exportSig, jniParams, body)
  }

  private case class CallBlocks(
      callArgs: String,
      npeBlock: String,
      preBlock: String,
      postBlock: String
  )

  private def collectOptionalParams(metaAttrs: List[(String, String)]): Set[String] =
    metaAttrs.collect { case ("optional_param", p) => p }.toSet

  private def renderStrictNullChecks(params: List[Param], optionalParams: Set[String], ret: CType)(using
      Naming.Context
  ): List[String] =
    params.collect {
      case p if !optionalParams.contains(p.name) && JavaCodeGen.isStrictNullCheck(p.typ) =>
        val javaParamName = Naming.toCamelCase(p.name)
        s"""    if (!${p.name}) { env->ThrowNew(FindClassCached(env, "java/lang/NullPointerException"), "$javaParamName must not be null"); return${defaultReturn(
            ret
          )}; }"""
    }

  private def renderCallBlocks(
      argConversions: List[(List[String], String, List[String])],
      npeChecks: List[String]
  ): CallBlocks = {
    val preCallLines  = argConversions.flatMap(_._1)
    val callArgs      = argConversions.map(_._2).mkString(", ")
    val postCallLines = argConversions.flatMap(_._3)
    val preBlock      = if (preCallLines.nonEmpty) preCallLines.mkString("\n") + "\n" else ""
    val postBlock     = if (postCallLines.nonEmpty) "\n" + postCallLines.mkString("\n") else ""
    val npeBlock      = if (npeChecks.nonEmpty) npeChecks.mkString("\n") + "\n" else ""
    CallBlocks(callArgs, npeBlock, preBlock, postBlock)
  }

  private def renderGeneratedFunction(exportSig: String, jniParams: String, body: String): String = {
    val cleanBody = body.linesIterator.filter(_.trim.nonEmpty).mkString("\n")
    s"""$exportSig($jniParams) {
$cleanBody
}"""
  }

  private def renderCountFuncArrayFreeFunctionBody(
      ff: CefDecl.FreeFunction,
      cfa: CType.CountFuncArray,
      npeBlock: String,
      preBlock: String,
      postBlock: String
  ): String = {
    val CType.CountFuncArray(elemType, countFuncC, _, _) = cfa
    val ownerPrefix                                      = ff.ownerStruct.stripSuffix("_t")
    val ownerStem                                        = ownerPrefix.stripPrefix("cef_")
    val freeCountFunc                                    =
      if (ownerPrefix.nonEmpty) {
        val normalized =
          if (countFuncC.startsWith(s"get_${ownerStem}_")) s"get_${countFuncC.stripPrefix(s"get_${ownerStem}_")}"
          else countFuncC
        s"${ownerPrefix}_$normalized"
      } else countFuncC
    val countCall = s"    size_t _count = $freeCountFunc();"

    val (allocAndCall, convertAndReturn) = elemType match {
      case CType.ObjectPtr(cefName) =>
        val javaFqn = jniName(cefName)
        val alloc   = s"    $cefName** _arr = _count > 0 ? new $cefName*[_count]() : nullptr;"
        val call    = s"    ${ff.cName}(&_count, _arr);"
        val convert =
          s"""    auto _result = env->NewObjectArray(static_cast<jsize>(_count), FindClassCached(env, "${javaFqn}$$NativePeer"), nullptr);
    auto _peerCls = FindClassCached(env, "${javaFqn}$$NativePeer");
    auto _peerCtor = env->GetMethodID(_peerCls, "<init>", "(J)V");
    for (size_t _i = 0; _i < _count; _i++) {
        auto _elem = _arr[_i] ? env->NewObject(_peerCls, _peerCtor, reinterpret_cast<jlong>(_arr[_i])) : nullptr;
        env->SetObjectArrayElement(_result, static_cast<jsize>(_i), _elem);
    }
    delete[] _arr;
    return _result;"""
        (s"$alloc\n$call", convert)

      case CType.Long | CType.SizeT =>
        val alloc   = s"    size_t* _arr = _count > 0 ? new size_t[_count]() : nullptr;"
        val call    = s"    ${ff.cName}(&_count, _arr);"
        val convert =
          s"""    jlongArray _result = env->NewLongArray(static_cast<jsize>(_count));
    if (_count > 0) {
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
        val call    = s"    ${ff.cName}(&_count, _arr);"
        val convert =
          s"""    jintArray _result = env->NewIntArray(static_cast<jsize>(_count));
    if (_count > 0) {
        env->SetIntArrayRegion(_result, 0, static_cast<jsize>(_count), reinterpret_cast<jint*>(_arr));
    }
    delete[] _arr;
    return _result;"""
        (s"$alloc\n$call", convert)

      case other =>
        throw IllegalArgumentException(s"CountFuncArray: unsupported element type $other in ${ff.cName}")
    }

    s"""$npeBlock$preBlock$countCall
$allocAndCall$postBlock
$convertAndReturn"""
  }

  private def convertParamShared(
      p: Param,
      isOpt: Boolean,
      isOutParam: Boolean,
      retType: CType
  ): (List[String], String, List[String]) =
    p.typ match {
      case CType.JString =>
        val tmp = s"_${p.name}_str"
        val pre = if (isOpt) List(s"    auto $tmp = ${p.name} ? JStringToCefString(env, ${p.name}) : nullptr;")
        else List(s"    auto $tmp = JStringToCefString(env, ${p.name});")
        val post = List(s"    if ($tmp) cef_string_userfree_free($tmp);")
        (pre, tmp, post)
      case CType.ConstCStringArray =>
        convertCStringArrayParam(p.name, p.name, mutable = false)
      case CType.CStringArray =>
        convertCStringArrayParam(p.name, p.name, mutable = true)
      case CType.ObjectPtr(cefName) if handlerNames.contains(cefName) =>
        val tmp = s"_${p.name}_ptr"
        val pre = List(
          s"    $cefName* $tmp = ${p.name} ? Create_Jni${Naming.structToJavaName(cefName)}(env, ${p.name}) : nullptr;"
        )
        (pre, tmp, Nil)
      case CType.ObjectPtr(cefName) =>
        // CppToC::Unwrap consumes a reference.
        val tmp     = s"_${p.name}_ptr"
        val extract =
          s"""reinterpret_cast<$cefName*>(env->GetLongField(${p.name}, env->GetFieldID(env->GetObjectClass(${p.name}), "nativePtr", "J")))"""
        val pre = List(s"    $cefName* $tmp = ${p.name} ? $extract : nullptr;", s"    if ($tmp) ${addRefExpr(tmp)}")
        (pre, tmp, Nil)
      case CType.ByValueIn(cefName) =>
        val tmp   = s"_${p.name}_val"
        val cVar  = s"_${p.name}_c"
        val reads = if (isOpt) {
          List(
            s"""    if (${p.name}) {""",
            s"""        auto $cVar = FindClassCached(env, "${jniName(cefName)}");"""
          ) ++
            bv.bvReadFromJavaLines(cefName, tmp, p.name, cVar).map(l => s"        $l") ++ List("    }")
        } else {
          List(s"""    auto $cVar = FindClassCached(env, "${jniName(cefName)}");""") ++
            bv.bvReadFromJavaLines(cefName, tmp, p.name, cVar).map(l => s"    $l")
        }
        val pre = List(
          s"    $cefName $tmp = {};"
        ) ++ reads
        (pre, s"&$tmp", Nil)
      case CType.ByValueOut(cefName) =>
        val tmp   = s"_${p.name}_val"
        val cVar  = s"_${p.name}_c"
        val reads = if (isOpt) {
          List(
            s"""    if (${p.name}) {""",
            s"""        auto $cVar = FindClassCached(env, "${jniMutableName(cefName)}");"""
          ) ++
            bv.bvReadFromJavaLines(cefName, tmp, p.name, cVar).map(l => s"        $l") ++ List("    }")
        } else {
          List(s"""    auto $cVar = FindClassCached(env, "${jniMutableName(cefName)}");""") ++
            bv.bvReadFromJavaLines(cefName, tmp, p.name, cVar).map(l => s"    $l")
        }
        val pre = List(
          s"    $cefName $tmp = {};"
        ) ++ reads
        val writes = if (isOpt) {
          List(
            s"""    if (${p.name}) {""",
            s"""        auto $cVar = FindClassCached(env, "${jniMutableName(cefName)}");"""
          ) ++
            bv.bvWriteBackLines(cefName, s"&$tmp", p.name, cVar).map(l => s"        $l") ++ List("    }")
        } else {
          bv.bvWriteBackLines(cefName, s"&$tmp", p.name, cVar).map(l => s"    $l")
        }
        val post = writes
        (pre, s"&$tmp", post)
      case CType.ByValueArray(cefName) =>
        val arrLen = s"_${p.name}_len"
        val vecVar = s"_${p.name}_vec"
        val arrVar = s"_${p.name}_arr"
        val pre    = List(
          s"""    jsize $arrLen = ${p.name} ? env->GetArrayLength(${p.name}) : 0;""",
          s"""    std::vector<$cefName> $vecVar;""",
          s"""    $cefName* $arrVar = nullptr;""",
          s"""    if (${p.name} && $arrLen > 0) {""",
          s"""        $vecVar.resize(static_cast<size_t>($arrLen));""",
          s"""        auto _c = FindClassCached(env, "${jniName(cefName)}");""",
          s"""        for (jsize _i = 0; _i < $arrLen; _i++) {""",
          s"""            auto _elem = env->GetObjectArrayElement(${p.name}, _i);""",
          s"""            if (_elem) {"""
        ) ++
          bv.bvReadFromJavaLines(cefName, s"$vecVar[static_cast<size_t>(_i)]", "_elem", "_c").map(l =>
            s"                $l"
          ) ++
          List(
            s"""            }""",
            s"""            env->DeleteLocalRef(_elem);""",
            s"""        }""",
            s"""        $arrVar = $vecVar.data();""",
            s"""    }"""
          )
        (pre, arrVar, Nil)
      case CType.OutInt =>
        val tmp = s"_${p.name}_val"
        (
          List(
            s"    int $tmp = 0;",
            s"    if (${p.name}) { jint _jv; env->GetIntArrayRegion(${p.name}, 0, 1, &_jv); $tmp = _jv; }"
          ),
          s"&$tmp",
          List(s"    if (${p.name}) { jint _jv = $tmp; env->SetIntArrayRegion(${p.name}, 0, 1, &_jv); }")
        )
      case CType.OutBool =>
        val tmp = s"_${p.name}_val"
        (
          List(
            s"    int $tmp = 0;",
            s"    if (${p.name}) { jboolean _jv; env->GetBooleanArrayRegion(${p.name}, 0, 1, &_jv); $tmp = static_cast<bool>(_jv); }"
          ),
          s"&$tmp",
          List(
            s"    if (${p.name}) { jboolean _jv = static_cast<jboolean>($tmp); env->SetBooleanArrayRegion(${p.name}, 0, 1, &_jv); }"
          )
        )
      case CType.Buffer(sizeParam) =>
        val addrVar  = s"_${p.name}_addr"
        val isConst  = p.rawCType.contains("const")
        val castType = if (isConst) "const void*" else "void*"
        val pre      = List(
          s"    $castType $addrVar = ${p.name} ? env->GetDirectBufferAddress(${p.name}) : nullptr;",
          s"""    if (${p.name} && !$addrVar) { env->ThrowNew(FindClassCached(env, "java/lang/IllegalArgumentException"), "${Naming.toCamelCase(
              p.name
            )} must be a direct ByteBuffer; use ByteBuffer.allocateDirect(...)"); return${defaultReturn(retType)}; }"""
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
      case CType.Bool         => (Nil, s"static_cast<bool>(${p.name})", Nil)
      case CType.OutOpaquePtr =>
        val valVar   = s"_${p.name}_val"
        val getVar   = s"_${p.name}_get"
        val setVar   = s"_${p.name}_set"
        val objVar   = s"_${p.name}_obj"
        val npClsVar = s"_${p.name}_np_cls"
        val npCtor   = s"_${p.name}_np_ctor"
        val newVar   = s"_${p.name}_new"
        val pre      = List(
          s"""    void* $valVar = nullptr;""",
          s"""    auto ${p.name}_ar_cls = FindClassCached(env, "java/util/concurrent/atomic/AtomicReference");""",
          s"""    auto $getVar = env->GetMethodID(${p.name}_ar_cls, "get", "()Ljava/lang/Object;");""",
          s"""    auto $setVar = env->GetMethodID(${p.name}_ar_cls, "set", "(Ljava/lang/Object;)V");""",
          s"""    if (${p.name}) {""",
          s"""        auto $objVar = env->CallObjectMethod(${p.name}, $getVar);""",
          s"""        if ($objVar) $valVar = reinterpret_cast<void*>(env->GetLongField($objVar, env->GetFieldID(env->GetObjectClass($objVar), "address", "J")));""",
          s"""    }"""
        )
        val post = List(
          s"""    if (${p.name}) {""",
          s"""        auto $npClsVar = FindClassCached(env, "${Naming.nativePointerInternalName}");""",
          s"""        auto $npCtor = env->GetMethodID($npClsVar, "<init>", "(J)V");""",
          s"""        auto $newVar = $valVar ? env->NewObject($npClsVar, $npCtor, to_jlong($valVar)) : nullptr;""",
          s"""        env->CallVoidMethod(${p.name}, $setVar, $newVar);""",
          s"""        if ($newVar) env->DeleteLocalRef($newVar);""",
          s"""    }"""
        )
        (pre, s"&$valVar", post)
      case CType.OpaquePtr =>
        val ct = if (p.rawCType.nonEmpty) p.rawCType else "void*"
        (
          Nil,
          s"""reinterpret_cast<$ct>(${p.name} ? env->GetLongField(${p.name}, env->GetFieldID(env->GetObjectClass(${p.name}), "address", "J")) : 0)""",
          Nil
        )
      case sc @ (CType.StringList | CType.StringMap | CType.StringMultimap) =>
        convertStringCollectionParam(p.name, p.name, sc, isOutParam)
      case CType.DataStruct(cefName) if CHeaderParser.isByValueStruct(cefName) =>
        val tmp   = s"_${p.name}_val"
        val cVar  = s"_${p.name}_c"
        val reads = if (isOpt) {
          List(
            s"""    if (${p.name}) {""",
            s"""        auto $cVar = FindClassCached(env, "${jniName(cefName)}");"""
          ) ++
            bv.bvReadFromJavaLines(cefName, tmp, p.name, cVar).map(l => s"        $l") ++ List("    }")
        } else {
          List(s"""    auto $cVar = FindClassCached(env, "${jniName(cefName)}");""") ++
            bv.bvReadFromJavaLines(cefName, tmp, p.name, cVar).map(l => s"    $l")
        }
        val pre = List(
          s"    $cefName $tmp = {};"
        ) ++ reads
        (pre, tmp, Nil)
      case _ => (Nil, p.name, Nil)
    }

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

  private def collectReferencedStructsFromParams(ret: CType, params: List[Param]): Set[String] =
    (structNameFromType(ret) ++ params.flatMap(p => structNameFromType(p.typ))).toSet

  private def collectReferencedStructs(fns: List[FnPtr]): Set[String] =
    fns.flatMap(fn => structNameFromType(fn.ret) ++ fn.params.flatMap(p => structNameFromType(p.typ))).toSet

  private def defaultReturn(ct: CType): String = ct match {
    case CType.Void                       => ""
    case CType.Bool                       => " JNI_FALSE"
    case CType.JString                    => " nullptr"
    case CType.ConstCStringArray          => " nullptr"
    case CType.CStringArray               => " nullptr"
    case CType.Ptr(_)                     => " 0"
    case CType.ObjectPtr(_)               => " nullptr"
    case CType.OutOpaquePtr               => " nullptr"
    case CType.OpaquePtr                  => " nullptr"
    case CType.Buffer(_)                  => " nullptr"
    case CType.DataStruct(_)              => " nullptr"
    case CType.CountFuncArray(_, _, _, _) => " nullptr"
    case _                                => " 0"
  }
}
