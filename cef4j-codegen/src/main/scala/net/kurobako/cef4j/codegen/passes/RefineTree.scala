package net.kurobako.cef4j.codegen.passes

import net.kurobako.cef4j.codegen.CType
import net.kurobako.cef4j.codegen.CefDecl
import net.kurobako.cef4j.codegen.CppMethodTypeInfo
import net.kurobako.cef4j.codegen.DocComments
import net.kurobako.cef4j.codegen.FnPtr
import net.kurobako.cef4j.codegen.Naming
import net.kurobako.cef4j.codegen.Param
import net.kurobako.cef4j.codegen.ParseState
import net.kurobako.cef4j.codegen.ParsedTree
import net.kurobako.cef4j.codegen.RefinedTree
import net.kurobako.cef4j.codegen.mapFns
import net.kurobako.cef4j.codegen.namedStruct

object RefineTree {

  def parseCountFunc(spec: String): Option[(String, String)] = spec match {
    case s"$param:$func" if param.nonEmpty && func.nonEmpty => Some((param, func))
    case _                                                  => None
  }

  def apply(parsed: ParsedTree, parseState: ParseState): RefinedTree = {
    given Naming.Context = parseState.namingContext

    val refinedDecls = promoteCountFuncArrays(
      parsed.structDecls.map(enrichDecl(_, parseState.docs, parseState.cppTypeInfo, parseState.enumDocs))
    )
    val refinedFreeFunctions =
      promoteCountFuncFreeFunctions(enrichFreeFunctions(parsed.freeFunctions, parseState.docs, parseState.cppTypeInfo))
    val declsWithMutableData = markMutableDataStructs(refinedDecls, refinedFreeFunctions)

    RefinedTree(
      decls = declsWithMutableData,
      freeFunctions = refinedFreeFunctions
    )
  }

  private def enrichDecl(
      decl: CefDecl,
      docs: Map[String, String],
      cppTypeInfo: Map[String, CppMethodTypeInfo],
      enumDocs: Map[String, (String, Map[String, String])]
  )(using Naming.Context): CefDecl = {
    val refined = recoverCppTypes(enrichWithMetaAttrs(decl, docs, cppTypeInfo.keySet), cppTypeInfo)
    refined match {
      case e: CefDecl.Enum =>
        enumDocs.get(e.name) match {
          case Some((doc, valDocs)) => e.copy(doc = doc, valueDocs = valDocs)
          case None                 => e
        }
      case other => other
    }
  }

  private def recoverCppTypes(
      decl: CefDecl,
      cppTypeInfo: Map[String, CppMethodTypeInfo]
  )(using ctx: Naming.Context): CefDecl = {
    val cppClassName    = decl.namedStruct.flatMap(ctx.cppClassNames.get)
    lazy val lowerToKey = cppTypeInfo.keysIterator.map(k => k.toLowerCase -> k).toMap

    def recover(fn: FnPtr): FnPtr = {
      val pascal       = Naming.toPascalCase(fn.name)
      val capiName     = fn.metaAttrs.collectFirst { case ("capi_name", n) => n }
      val qualifiedKey = cppClassName.map(cls => s"$cls::$pascal")
      val matchedKey   = qualifiedKey.filter(cppTypeInfo.contains)
        .orElse(cppTypeInfo.get(pascal).map(_ => pascal))
        .orElse(cppTypeInfo.get(fn.name).map(_ => fn.name))
        .orElse(capiName.filter(cppTypeInfo.contains))
        .orElse(lowerToKey.get(pascal.toLowerCase))
      val info = matchedKey.flatMap(cppTypeInfo.get)

      info match {
        case Some(ti) =>
          val recoveredRet    = recoverType(fn.ret, ti.returnType)
          val recoveredParams = fn.params.map { p =>
            val cppParamType = ti.paramTypes.get(p.name)
              .orElse(ti.paramTypes.get(Naming.toCamelCase(p.name)))
            cppParamType match {
              case Some(cppType) => p.copy(typ = recoverType(p.typ, cppType))
              case None          => p
            }
          }
          val unqualifiedName = matchedKey.map(k =>
            k.lastIndexOf("::") match {
              case -1  => k
              case idx => k.substring(idx + 2)
            }
          )
          fn.copy(ret = recoveredRet, params = recoveredParams, cppName = unqualifiedName)
        case None => fn
      }
    }

    decl.mapFns(recover)
  }

  private def recoverType(capiType: CType, cppTypeStr: String): CType = (capiType, cppTypeStr) match {
    case (CType.Int, "bool") => CType.Bool
    case _                   => capiType
  }

  private def enrichWithMetaAttrs(
      decl: CefDecl,
      docs: Map[String, String],
      cppMethodNames: Set[String]
  )(using Naming.Context): CefDecl = {
    lazy val lowerToName         = cppMethodNames.iterator.map(n => n.toLowerCase -> n).toMap
    def enrich(fn: FnPtr): FnPtr = {
      val pascal  = Naming.toPascalCase(fn.name)
      val docText = DocComments.resolveMethodDoc(fn, docs, decl.namedStruct.getOrElse(""))
        .orElse(lowerToName.get(pascal.toLowerCase).flatMap(docs.get))
      docText match {
        case Some(text) => fn.copy(metaAttrs = DocComments.extractAttrsList(text))
        case None       => fn
      }
    }
    decl.mapFns(enrich)
  }

  private def enrichFreeFunctions(
      freeFunctions: List[CefDecl.FreeFunction],
      docs: Map[String, String],
      cppTypeInfo: Map[String, CppMethodTypeInfo]
  )(using Naming.Context): List[CefDecl.FreeFunction] = freeFunctions.map { ff =>
    val pascal  = Naming.toPascalCase(ff.javaMethodName)
    val docText = DocComments.resolveFreeFunctionDoc(ff, docs)
    val attrs   = docText.map(DocComments.extractAttrsList).getOrElse(Nil)
    val info    = cppTypeInfo.get(pascal).orElse(cppTypeInfo.get(ff.javaMethodName))

    val (recoveredRet, recoveredParams) = info match {
      case Some(ti) =>
        val ret    = recoverType(ff.ret, ti.returnType)
        val params = ff.params.map { p =>
          val cppType = ti.paramTypes.get(p.name)
            .orElse(ti.paramTypes.get(Naming.toCamelCase(p.name)))
          cppType match {
            case Some(ct) => p.copy(typ = recoverType(p.typ, ct))
            case None     => p
          }
        }
        (ret, params)
      case None => (ff.ret, ff.params)
    }

    ff.copy(ret = recoveredRet, params = recoveredParams, metaAttrs = attrs)
  }

  private def markMutableDataStructs(
      decls: List[CefDecl],
      freeFunctions: List[CefDecl.FreeFunction]
  ): List[CefDecl] = {
    val allParams = decls.flatMap {
      case d: CefDecl.ObjectStruct  => d.fns.flatMap(_.params)
      case d: CefDecl.HandlerStruct => d.fns.flatMap(_.params)
      case _                        => Nil
    } ++ freeFunctions.flatMap(_.params)

    val byValueOuts = allParams.collect { case Param(_, CType.ByValueOut(cefName), _, _) => cefName }
    val byValueIns  = allParams.collect { case Param(_, CType.ByValueIn(cefName), _, _) => cefName }

    val needsMutable = (byValueOuts ++ byValueIns).toSet

    decls.map {
      case d: CefDecl.DataStruct if needsMutable.contains(d.name) => d.copy(needsMutable = true)
      case other                                                  => other
    }
  }

  private def toSnakeCase(pascal: String): String =
    pascal.replaceAll("([a-z0-9])([A-Z])", "$1_$2").toLowerCase

  private def countFuncElementType(ct: CType): Option[CType] = ct match {
    case CType.ObjectPtrArray(name)   => Some(CType.ObjectPtr(name))
    case CType.OutObjectPtr(name)     => Some(CType.ObjectPtr(name))
    case CType.ObjectPtr(name)        => Some(CType.ObjectPtr(name))
    case CType.ByValueOut(name)       => Some(CType.ByValueIn(name))
    case CType.ByValueArray(name)     => Some(CType.ByValueIn(name))
    case CType.OutPrimitivePtr(inner) => Some(inner)
    case CType.OpaquePtr              => None
    case _                            => None
  }

  private def promoteCountFunc(
      metaAttrs: List[(String, String)],
      params: List[Param],
      debugName: String
  ): Option[(CType, List[Param])] =
    metaAttrs.collectFirst { case ("count_func", spec) => spec }
      .flatMap(parseCountFunc)
      .flatMap { case (arrayParamName, countMethodCpp) =>
        val countParamName = s"${arrayParamName}Count"
        val countParam     = params.find(_.name == countParamName)
        val arrayParam     = params.find(_.name == arrayParamName)
        (countParam, arrayParam) match {
          case (Some(_), Some(ap)) =>
            countFuncElementType(ap.typ) match {
              case Some(elemTy) =>
                val countFuncC = toSnakeCase(countMethodCpp)
                val cfaType    = CType.CountFuncArray(elemTy, countFuncC, countParamName, arrayParamName)
                val newParams  = params.filterNot(p => p.name == countParamName || p.name == arrayParamName)
                Some((cfaType, newParams))
              case None =>
                System.err.println(
                  s"  WARN: count_func on $debugName: cannot determine element type for ${ap.name} (${ap.typ})"
                )
                None
            }
          // cef_string_list_t already carries the flattened vector count.
          case (None, Some(Param(_, CType.StringList, _, _))) => None
          case _                                              =>
            System.err.println(
              s"  WARN: count_func on $debugName: expected params $countParamName + $arrayParamName but not found"
            )
            None
        }
      }

  private def promoteCountFuncArrays(decls: List[CefDecl]): List[CefDecl] =
    decls.map(_.mapFns { fn =>
      promoteCountFunc(fn.metaAttrs, fn.params, fn.name) match {
        case Some((cfaType, newParams)) => fn.copy(ret = cfaType, params = newParams)
        case None                       => fn
      }
    })

  private def promoteCountFuncFreeFunctions(
      freeFunctions: List[CefDecl.FreeFunction]
  ): List[CefDecl.FreeFunction] =
    freeFunctions.map { ff =>
      promoteCountFunc(ff.metaAttrs, ff.params, ff.javaMethodName) match {
        case Some((cfaType, newParams)) => ff.copy(ret = cfaType, params = newParams)
        case None                       => ff
      }
    }
}
