package net.kurobako.cef4j.codegen.ipc

import cats.syntax.all.*
import net.kurobako.cef4j.codegen.CType
import net.kurobako.cef4j.codegen.CefDecl
import net.kurobako.cef4j.codegen.FnPtr
import net.kurobako.cef4j.codegen.Naming
import net.kurobako.cef4j.codegen.Param

object SpecDeriver {

  /** Keeps derived IDs outside the hand-written protocol range. */
  val AstIdBase: Int = 10000

  // CEF confines V8 and DOM objects to the renderer process.
  def isRendererProcessStruct(cefStructName: String): Boolean =
    cefStructName.startsWith("cef_v8_") || cefStructName.startsWith("cef_dom")

  def affinityOf(cefStructName: String): ProcessAffinity =
    if (isRendererProcessStruct(cefStructName)) ProcessAffinity.Renderer else ProcessAffinity.Browser

  def deriveHandlers(
      decls: List[CefDecl],
      packageName: String,
      knownDataStructs: Set[String] = Set.empty,
      methodDocs: Map[(String, String), String] = Map.empty
  )(using Naming.Context): List[HandlerSpec] =
    decls.collect {
      case h: CefDecl.HandlerStruct =>
        val structPrefix = toCamelCase(stripCefPrefix(h.name))
        val methods      = h.fns.flatMap(fn =>
          deriveHandlerMethod(structPrefix, fn, knownDataStructs, methodDocs.getOrElse((h.name, fn.name), ""))
        )
        Option.when(methods.nonEmpty)(HandlerSpec(
          className = "Cef" + toCamelCase(stripCefPrefix(h.name)),
          packageName = packageName,
          cefStructName = h.name,
          methods = methods
        ))
    }.flatten.distinctBy(_.cefStructName)

  def deriveDataStructs(decls: List[CefDecl], packageName: String)(using Naming.Context): List[DataStructSpec] =
    // Version-gated headers can redeclare the same struct.
    decls.collect { case d: CefDecl.DataStruct => d }.flatMap { d =>
      d.fields.traverse(f => toDataFieldType(f.typ).map(t => FieldSpec(safeFieldName(snakeToCamel(f.name)), t)))
        .filter(_.nonEmpty)
        .map(fields =>
          DataStructSpec(
            className = safeFacadeClassName(toCamelCase(stripCefPrefix(d.name))),
            packageName = packageName,
            cefStructName = d.name,
            fields = fields
          )
        )
    }.distinctBy(_.cefStructName)

  private def toDataFieldType(t: CType): Option[FieldType] = t match {
    case CType.Int | CType.UInt | CType.Enum(_) => Some(FieldType.I32)
    case CType.Long | CType.SizeT               => Some(FieldType.I64)
    case CType.Bool                             => Some(FieldType.Bool)
    case CType.JString                          => Some(FieldType.Utf8String)
    // char16_t uses I32 to avoid a dedicated wire type.
    case CType.Char => Some(FieldType.I32)
    case _          => None
  }

  def deriveJvmVisitors(
      decls: List[CefDecl],
      packageName: String,
      methodDocs: Map[(String, String), String] = Map.empty
  )(using Naming.Context): List[JvmVisitorSpec] = {
    val paramStructs: Set[String] = decls.collect {
      case o: CefDecl.ObjectStruct =>
        o.fns.flatMap(_.params.collect {
          case Param(_, CType.ObjectPtr(name), _, _)                           => name
          case Param(_, CType.Ptr(inner), _, _) if inner.matches("cef_\\w+_t") => inner
        })
    }.flatten.toSet

    decls.collect { case h: CefDecl.HandlerStruct if paramStructs.contains(h.name) => h }
      .distinctBy(_.name)
      .flatMap { h =>
        if (h.fns.size != 1) None
        else {
          val fn = h.fns.head
          if (fn.ret != CType.Void) None
          else {
            val visible = fn.params.filterNot {
              case Param(_, CType.BufferSize(_), _, _) => true
              case _                                   => false
            }
            val maybeFields = visible.map { p =>
              p.typ match {
                case CType.Int | CType.UInt | CType.Enum(_) =>
                  Some((FieldSpec(safeFieldName(snakeToCamel(p.name)), FieldType.I32), p.isConst))
                case CType.Long | CType.SizeT =>
                  Some((FieldSpec(safeFieldName(snakeToCamel(p.name)), FieldType.I64), p.isConst))
                case CType.Bool =>
                  Some((FieldSpec(safeFieldName(snakeToCamel(p.name)), FieldType.Bool), p.isConst))
                case CType.JString =>
                  Some((FieldSpec(safeFieldName(snakeToCamel(p.name)), FieldType.Utf8String), p.isConst))
                case _ => None
              }
            }
            if (maybeFields.exists(_.isEmpty)) None
            else {
              val flat         = maybeFields.flatten
              val params       = flat.map(_._1)
              val constStrings = flat.collect {
                case (spec, isConst) if spec.ty == FieldType.Utf8String => spec.name -> isConst
              }.toMap
              val structPrefix = toCamelCase(stripCefPrefix(h.name))
              val opName       = toCamelCase(fn.name)
              Some(JvmVisitorSpec(
                cefStructName = h.name,
                className = "Cef" + structPrefix,
                eventClassName = structPrefix + opName + "CallbackEvent",
                packageName = packageName,
                methodName = snakeToCamel(fn.name),
                cefMethodName = fn.name,
                params = params,
                constStringByField = constStrings,
                javadoc = methodDocs.getOrElse((h.name, fn.name), "")
              ))
            }
          }
        }
      }
  }

  def deriveClientGetters(decls: List[CefDecl]): Map[String, String] =
    decls.collectFirst {
      case h: CefDecl.HandlerStruct if h.name == "cef_client_t" =>
        h.fns.flatMap { fn =>
          fn.ret match {
            case CType.ObjectPtr(name)                                                => Some(name -> fn.name)
            case CType.Ptr(inner) if inner.startsWith("cef_") && inner.endsWith("_t") =>
              Some(inner -> fn.name)
            case _ => None
          }
        }.toMap
    }.getOrElse(Map.empty)

  private def deriveHandlerMethod(
      structPrefix: String,
      fn: FnPtr,
      knownDataStructs: Set[String],
      javadoc: String
  )(using Naming.Context): Option[HandlerMethod] = {
    val returnType: Option[Option[FieldType]] = fn.ret match {
      case CType.Void                             => Some(None)
      case CType.Int | CType.UInt | CType.Enum(_) => Some(Some(FieldType.Bool))
      case _                                      => None
    }
    val visible = fn.params.filterNot {
      case Param(_, CType.BufferSize(_), _, _) => true
      case _                                   => false
    }
    val triples = visible.traverse { p =>
      toFieldTypeWithStruct(p.typ, knownDataStructs).map { case (t, structOpt) =>
        (FieldSpec(safeFieldName(snakeToCamel(p.name)), t), structOpt, p.isConst)
      }
    }
    for {
      resultType <- returnType
      fields     <- triples
    } yield {
      val params  = fields.map(_._1)
      val handles = fields.collect {
        case (spec, Some(struct), _) if spec.ty == FieldType.RemoteHandle => spec.name -> struct
      }.toMap
      val constStrings = fields.collect {
        case (spec, _, isConst) if spec.ty == FieldType.Utf8String => spec.name -> isConst
      }.toMap
      val opName = toCamelCase(fn.name)
      HandlerMethod(
        methodName = snakeToCamel(fn.name),
        cefMethodName = fn.name,
        eventClassName = structPrefix + opName + "Event",
        params = params,
        handleStructByField = handles,
        constStringByField = constStrings,
        returnType = resultType,
        responseClassName = resultType.map(_ => structPrefix + opName + "Response"),
        javadoc = javadoc
      )
    }
  }

  def deriveFacades(
      decls: List[CefDecl],
      packageName: String,
      knownDataStructs: Set[String] = Set.empty,
      methodDocs: Map[(String, String), String] = Map.empty
  )(using Naming.Context): List[FacadeSpec] =
    decls.collect {
      case o: CefDecl.ObjectStruct =>
        val structPrefix = toCamelCase(stripCefPrefix(o.name))
        val methods      = o.fns.flatMap(fn =>
          deriveFacadeMethod(structPrefix, fn, knownDataStructs, methodDocs.getOrElse((o.name, fn.name), ""))
        )
        Option.when(methods.nonEmpty)(FacadeSpec(
          className = safeFacadeClassName(toCamelCase(stripCefPrefix(o.name))),
          packageName = packageName,
          cefStructName = o.name,
          methods = methods,
          affinity = affinityOf(o.name)
        ))
    }.flatten.distinctBy(_.cefStructName)

  // These names collide with Java types or tooling conventions.
  private val ClashingFacadeNames: Set[String] =
    Set("Thread", "Process", "Object", "String", "System", "V8Exception")

  private def safeFacadeClassName(name: String): String =
    if (ClashingFacadeNames.contains(name)) "Cef" + name else name

  def cefStructToClassName(cefStructName: String)(using Naming.Context): String =
    safeFacadeClassName(toCamelCase(stripCefPrefix(cefStructName)))

  def camelToSnake(s: String): String =
    s.zipWithIndex.flatMap { case (c, index) =>
      if (c.isUpper && index > 0) s"_${c.toLower}" else c.toLower.toString
    }.mkString

  // Generated facades cannot safely expose Object methods.
  private val ReservedFacadeMethodNames: Set[String] =
    Set("wait", "notify", "notifyAll", "getClass", "clone", "equals", "hashCode", "toString", "finalize")

  private def deriveFacadeMethod(
      structPrefix: String,
      fn: FnPtr,
      knownDataStructs: Set[String],
      javadoc: String
  )(using Naming.Context): Option[FacadeMethod] = {
    val derivedName = snakeToCamel(fn.name)
    val methodName  = if (derivedName == "close") "cefClose" else derivedName
    val visible     = fn.params.filterNot {
      case Param(_, CType.BufferSize(_), _, _) => true
      case _                                   => false
    }
    val hasOutputBuffer = visible.exists {
      case Param(_, CType.Buffer(_), isConst, _) => !isConst
      case _                                     => false
    }
    if (ReservedFacadeMethodNames.contains(methodName) || hasOutputBuffer) None
    else {
      val params = visible.traverse(p => toFieldSpecWithStruct(p, knownDataStructs))
      val result = fn.ret match {
        case CType.Void => Some(None)
        case other      => toFieldTypeWithStruct(other, knownDataStructs).map { case (fieldType, struct) =>
            Some(FieldSpec("result", fieldType) -> struct)
          }
      }
      for {
        explicitEntries <- params
        resultEntry     <- result
      } yield {
        val explicitParams = explicitEntries.map(_._1)
        val paramHandles   = explicitEntries.collect {
          case (spec, Some(struct)) if spec.ty == FieldType.RemoteHandle => spec.name -> struct
        }.toMap
        val resultField  = resultEntry.map(_._1)
        val resultHandle = resultEntry.flatMap { case (spec, structOpt) =>
          if (spec.ty == FieldType.RemoteHandle) structOpt.map("result" -> _) else None
        }
        val handleStructByField = paramHandles ++ resultHandle.toMap
        val opName              = toCamelCase(fn.name)
        val hasBuffer           = fn.params.exists {
          case Param(_, CType.Buffer(_) | CType.BufferSize(_), _, _) => true
          case _                                                     => false
        }
        val cCallArgs = Option.when(hasBuffer) {
          val byOriginalName = visible.map(p => p.name -> safeFieldName(snakeToCamel(p.name))).toMap
          fn.params.map { p =>
            p.typ match {
              case CType.BufferSize(buffer) =>
                CCallArg.BytesSize(byOriginalName.getOrElse(buffer, snakeToCamel(buffer)))
              case _ => CCallArg.Explicit(byOriginalName.getOrElse(p.name, snakeToCamel(p.name)))
            }
          }
        }
        FacadeMethod(
          methodName = methodName,
          cefMethodName = fn.name,
          requestClassName = structPrefix + opName + "Request",
          responseClassName = structPrefix + opName + "Response",
          explicitParams = explicitParams,
          resultField = resultField,
          handleStructByField = handleStructByField,
          cCallArgs = cCallArgs,
          javadoc = javadoc
        )
      }
    }
  }

  private def toFieldSpecWithStruct(
      p: Param,
      knownDataStructs: Set[String]
  )(using Naming.Context): Option[(FieldSpec, Option[String])] =
    toFieldTypeWithStruct(p.typ, knownDataStructs).map { case (t, structOpt) =>
      FieldSpec(safeFieldName(snakeToCamel(p.name)), t) -> structOpt
    }

  private def toFieldTypeWithStruct(t: CType, knownDataStructs: Set[String]): Option[(FieldType, Option[String])] =
    t match {
      case CType.ObjectPtr(name)    => Some(FieldType.RemoteHandle -> Some(name))
      case CType.OutObjectPtr(name) => Some(FieldType.RemoteHandle -> Some(name))
      case CType.Ptr(inner) if inner.startsWith("cef_") && inner.endsWith("_t") =>
        Some(FieldType.RemoteHandle -> Some(inner))
      // Const pointers to by-value structs are not remote handles.
      case CType.ConstDataStructPtr(name) if knownDataStructs.isEmpty || knownDataStructs.contains(name) =>
        Some(FieldType.DataStruct(name) -> Some(name))
      case CType.DataStruct(name) if knownDataStructs.isEmpty || knownDataStructs.contains(name) =>
        Some(FieldType.DataStruct(name) -> Some(name))
      case CType.ByValueIn(name) if knownDataStructs.contains(name) =>
        Some(FieldType.DataStruct(name) -> Some(name))
      case other => toFieldType(other, knownDataStructs).map(_ -> None)
    }

  def derive(
      decls: List[CefDecl],
      packageName: String,
      knownDataStructs: Set[String] = Set.empty
  )(using Naming.Context): List[MessageSpec] = {
    val all = decls.flatMap {
      case h: CefDecl.HandlerStruct =>
        h.fns.flatMap(deriveOne(h.name, _, packageName, isMethod = false, knownDataStructs))
      case o: CefDecl.ObjectStruct =>
        o.fns.flatMap(deriveOne(o.name, _, packageName, isMethod = true, knownDataStructs))
      case _ => Nil
    }
    // Version-gated headers can produce duplicate generated class names.
    all.distinctBy(_.className)
  }

  private def deriveOne(
      structName: String,
      fn: FnPtr,
      packageName: String,
      isMethod: Boolean,
      knownDataStructs: Set[String]
  )(using Naming.Context): List[MessageSpec] = {
    val visible = fn.params.filterNot {
      case Param(_, CType.BufferSize(_), _, _) => true
      case _                                   => false
    }
    visible.traverse(p => toFieldSpec(p, knownDataStructs)).fold(Nil) { explicitFields =>
      val baseName = toCamelCase(stripCefPrefix(structName)) + toCamelCase(fn.name)
      if (isMethod) {
        // Renderer dispatch needs the frame that owns the object.
        val rendererPrefix =
          if (isRendererProcessStruct(structName)) List(FieldSpec("frame", FieldType.RemoteHandle)) else Nil
        val reqFields = rendererPrefix ::: (FieldSpec("self", FieldType.RemoteHandle) :: explicitFields)
        val reqName   = baseName + "Request"
        val msgId     = stableId(reqName)
        val req       = MessageSpec(reqName, packageName, msgId, reqFields)

        val respFields: Option[List[FieldSpec]] = fn.ret match {
          case CType.Void => Some(Nil)
          case other      => toFieldType(other, knownDataStructs).map(t => List(FieldSpec("result", t)))
        }
        respFields match {
          case Some(fields) =>
            List(req, MessageSpec(baseName + "Response", packageName, msgId, fields))
          case None =>
            Nil
        }
      } else {
        val name      = baseName + "Event"
        val eventSpec = MessageSpec(name, packageName, stableId(name), explicitFields)
        fn.ret match {
          case CType.Void                             => List(eventSpec)
          case CType.Int | CType.UInt | CType.Enum(_) =>
            val respName = baseName + "Response"
            val respSpec =
              MessageSpec(respName, packageName, stableId(respName), List(FieldSpec("result", FieldType.Bool)))
            List(eventSpec, respSpec)
          case _ => Nil
        }
      }
    }
  }

  // Avoid generated accessor collisions.
  private val ReservedFieldGetterNames: Set[String] =
    Set(
      "messageId",
      "encodedSize",
      "encodeInto",
      "wait",
      "notify",
      "notifyAll",
      "getClass",
      "hashCode",
      "toString",
      "clone",
      "equals",
      "finalize"
    )

  private def safeFieldName(name: String): String =
    if (ReservedFieldGetterNames.contains(name)) name + "_" else name

  private def toFieldSpec(p: Param, knownDataStructs: Set[String])(using Naming.Context): Option[FieldSpec] =
    toFieldType(p.typ, knownDataStructs).map(t => FieldSpec(safeFieldName(snakeToCamel(p.name)), t))

  private def toFieldType(t: CType, knownDataStructs: Set[String]): Option[FieldType] = t match {
    case CType.Int | CType.UInt | CType.Enum(_)                               => Some(FieldType.I32)
    case CType.Long | CType.SizeT                                             => Some(FieldType.I64)
    case CType.Bool                                                           => Some(FieldType.Bool)
    case CType.JString                                                        => Some(FieldType.Utf8String)
    case CType.ObjectPtr(_)                                                   => Some(FieldType.RemoteHandle)
    case CType.OutObjectPtr(_)                                                => Some(FieldType.RemoteHandle)
    case CType.Ptr(inner) if inner.startsWith("cef_") && inner.endsWith("_t") =>
      Some(FieldType.RemoteHandle)
    case CType.ConstDataStructPtr(name) if knownDataStructs.isEmpty || knownDataStructs.contains(name) =>
      Some(FieldType.DataStruct(name))
    case CType.DataStruct(name) if knownDataStructs.isEmpty || knownDataStructs.contains(name) =>
      Some(FieldType.DataStruct(name))
    case CType.ByValueIn(name) if knownDataStructs.contains(name) =>
      Some(FieldType.DataStruct(name))
    case CType.Buffer(_) =>
      Some(FieldType.Bytes)
    case CType.StringList =>
      Some(FieldType.StringList)
    case _ => None
  }

  private def stripCefPrefix(s: String): String =
    s.stripPrefix("cef_").stripSuffix("_t")

  private def toCamelCase(s: String)(using Naming.Context): String = Naming.toPascalCase(s)

  private def snakeToCamel(s: String)(using Naming.Context): String = Naming.toCamelCase(s)

  def stableId(name: String): Int = {
    val raw    = scala.util.hashing.MurmurHash3.stringHash(name)
    val nonNeg = if (raw == Int.MinValue) 0 else math.abs(raw)
    AstIdBase + (nonNeg % (Int.MaxValue - AstIdBase))
  }
}
