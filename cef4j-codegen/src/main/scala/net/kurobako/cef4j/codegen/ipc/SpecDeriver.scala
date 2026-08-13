package net.kurobako.cef4j.codegen.ipc

import net.kurobako.cef4j.codegen.CType
import net.kurobako.cef4j.codegen.CefDecl
import net.kurobako.cef4j.codegen.FnPtr
import net.kurobako.cef4j.codegen.Param

/** Derives {@link MessageSpec}s by walking the CEF C API AST that {@code cef4j-codegen}'s parse pipeline already
  * produces. Mapping rules:
  *
  *   - {@link CefDecl.HandlerStruct} method → event (no `self` field; runtime server → JVM, routed by messageId).
  *   - {@link CefDecl.ObjectStruct} method → request (gets an explicit `self: RemoteHandle` first field so the
  *     runtime-server-side dispatcher can resolve the receiver).
  *   - Param types: scalar primitives ({@link CType.Int}/{@link CType.Long}/{@link CType.Bool}) → matching
  *     {@link FieldType}; {@link CType.JString} → Utf8String; pointers to cef_*_t structs ({@link CType.ObjectPtr},
  *     {@link CType.OutObjectPtr}, raw {@link CType.Ptr}, {@link CType.ConstDataStructPtr}) →
  *     {@link FieldType.RemoteHandle}. Anything else (floats, complex out-params, callbacks-as-arg) makes the whole
  *     method ineligible.
  *   - {@link CType.BufferSize} params (the hidden length companion to a buffer) are filtered out before eligibility is
  *     judged; CEF's C API exposes them but they're not meaningful at the JVM layer.
  *
  * IDs land in {@code [AstIdBase, Int.MaxValue)} via Murmur3 of the generated class name — deterministic across CEF
  * versions while staying clear of the hand-written `[0, AstIdBase)` range.
  *
  * Against CEF 146 this currently yields ~975 unique specs after dedupe.
  */
object SpecDeriver {

  /** Lower bound on AST-derived message ids. Hand-written specs claim ids below this. */
  val AstIdBase: Int = 10000

  /** Heuristic: any CEF struct whose methods only run inside the renderer subprocess. CEF's process model confines V8
    * (`cef_v8_*`) and DOM (`cef_dom*` — note CEF spells these `cef_domnode_t`/`cef_domdocument_t` without an underscore
    * after `dom`, alongside `cef_domvisitor_t`) APIs to the renderer; calling them from the browser process is a soft
    * no-op or crash. The generated browser dispatcher relays renderer-affinity Requests via process_message; the
    * renderer dispatcher decodes and invokes them inside the right V8 context.
    */
  def isRendererProcessStruct(cefStructName: String): Boolean =
    cefStructName.startsWith("cef_v8_") || cefStructName.startsWith("cef_dom")

  /** Process affinity for a given CEF struct, used by emitters to pick the right dispatch path. */
  def affinityOf(cefStructName: String): ProcessAffinity =
    if (isRendererProcessStruct(cefStructName)) ProcessAffinity.Renderer else ProcessAffinity.Browser

  /** Walk every `ObjectStruct` and produce one `FacadeSpec` per struct, listing only the methods whose Request+Response
    * pair successfully derived. Empty facades (every method skipped) are omitted.
    *
    * Facade class name mirrors the JNI binding's choice (e.g. `cef_browser_t` → `Browser`); the user-facing "Cef"
    * prefix can be added by the consuming module if desired.
    */
  /** Walks every `HandlerStruct` and produces one `HandlerSpec` listing every callback that successfully derived Event
    * message fields. Methods with unsupported param types are skipped (same rules as `deriveOne` for events, since
    * handler callbacks become Event messages on the wire).
    */
  /** Computed by callers (typically: `deriveDataStructs(decls, …).map(_.cefStructName).toSet`) and threaded through
    * every derivation step that might map a {@link CType.ByValueIn} param to {@link FieldType.DataStruct}. Methods
    * whose params reference a struct missing from this set are skipped entirely so the emitter never produces a
    * `#include "Cookie.h"` line for a Cookie overlay we never generated. Default empty so test-only callers that don't
    * care still compile.
    */
  def deriveHandlers(
      decls: List[CefDecl],
      packageName: String,
      knownDataStructs: Set[String] = Set.empty,
      methodDocs: Map[(String, String), String] = Map.empty
  ): List[HandlerSpec] = {
    val byStruct = scala.collection.mutable.LinkedHashMap.empty[String, List[HandlerMethod]]
    decls.foreach {
      case h: CefDecl.HandlerStruct =>
        val structPrefix = toCamelCase(stripCefPrefix(h.name))
        val methods      = h.fns.flatMap(fn =>
          deriveHandlerMethod(structPrefix, fn, knownDataStructs, methodDocs.getOrElse((h.name, fn.name), ""))
        )
        if (methods.nonEmpty) {
          val existing = byStruct.getOrElse(h.name, Nil)
          if (existing.isEmpty) byStruct.put(h.name, methods)
        }
      case _ => ()
    }
    byStruct.iterator.map { case (struct, methods) =>
      HandlerSpec(
        className = "Cef" + toCamelCase(stripCefPrefix(struct)),
        packageName = packageName,
        cefStructName = struct,
        methods = methods
      )
    }.toList
  }

  /** Walks every `CefDecl.DataStruct` and produces one `DataStructSpec` per by-value type. Fields with unsupported
    * shapes (nested structs, arrays, platform-specific handles, out-params, etc.) drop the whole struct — emitting a
    * partial overlay would silently misrepresent the native layout, which is worse than not having it.
    *
    * Class name follows the facade convention: `cef_browser_settings_t` → `BrowserSettings` (or `CefBrowserSettings` if
    * it would clash with a `java.lang` type — same shadow rules as facades).
    */
  def deriveDataStructs(decls: List[CefDecl], packageName: String): List[DataStructSpec] = {
    // CEF DataStructs duplicate across version-gated headers (same struct redeclared via #if blocks). Keep the
    // first occurrence by `cefStructName`; drift in field shape across versions is a real concern but that's a
    // separate problem from emitting the same type twice.
    val seen = scala.collection.mutable.LinkedHashMap.empty[String, DataStructSpec]
    decls.collect { case d: CefDecl.DataStruct => d }.foreach { d =>
      if (!seen.contains(d.name)) {
        val maybeFields: List[Option[FieldSpec]] = d.fields.map { f =>
          toDataFieldType(f.typ).map(t => FieldSpec(safeFieldName(snakeToCamel(f.name)), t))
        }
        if (maybeFields.nonEmpty && !maybeFields.exists(_.isEmpty)) {
          seen.put(
            d.name,
            DataStructSpec(
              className = safeFacadeClassName(toCamelCase(stripCefPrefix(d.name))),
              packageName = packageName,
              cefStructName = d.name,
              fields = maybeFields.flatten
            )
          )
        }
      }
    }
    seen.values.toList
  }

  /** First-pass field-type filter for data structs. CEF enums and signed/unsigned ints all wire as I32; `size_t` widens
    * to I64 to dodge 32-bit truncation on 64-bit hosts. Anything more shape-dependent (nested structs, arrays, platform
    * handles) returns None and the whole struct gets skipped.
    */
  private def toDataFieldType(t: CType): Option[FieldType] = t match {
    case CType.Int | CType.UInt | CType.Enum(_) => Some(FieldType.I32)
    case CType.Long | CType.SizeT               => Some(FieldType.I64)
    case CType.Bool                             => Some(FieldType.Bool)
    case CType.JString                          => Some(FieldType.Utf8String)
    // char16_t is a UTF-16 code unit (0-65535). Wire as I32 — one extra byte per occurrence is harmless and
    // avoids inventing a new FieldType for the few structs that use it. cef_key_event_t's `character` /
    // `unmodified_character` fields are the main consumers; keyboard input depends on this being supported.
    case CType.Char => Some(FieldType.I32)
    case _          => None
  }

  /** Walks every facade and collects the cef struct names that appear as RemoteHandle params. These are the candidates
    * for "JVM-owned visitor" treatment — handler structs that the JVM provides to the runtime server as method args
    * (the runtime server synthesises a real cef_X_t and routes its callback back to JVM). Compare with
    * {@link deriveHandlers}: that's for HandlerStructs the runtime server owns and broadcasts events from.
    *
    * Cross-references with HandlerStructs to filter to single-void-method shapes that look like visitors.
    */
  def deriveJvmVisitors(
      decls: List[CefDecl],
      packageName: String,
      knownDataStructs: Set[String] = Set.empty,
      methodDocs: Map[(String, String), String] = Map.empty
  ): List[JvmVisitorSpec] = {
    // Step 1: find every cef struct name passed as an ObjectPtr param to any ObjectStruct method.
    val paramStructs: Set[String] = decls.collect {
      case o: CefDecl.ObjectStruct =>
        o.fns.flatMap(_.params.collect {
          case Param(_, CType.ObjectPtr(name), _, _)                           => name
          case Param(_, CType.Ptr(inner), _, _) if inner.matches("cef_\\w+_t") => inner
        })
    }.flatten.toSet

    // Step 2: keep HandlerStructs in that set, with a single-void-method shape and primitive-only params.
    // Dedupe by cefStructName upfront — CEF re-declares structs across version-gated headers and we don't
    // want two synthetic classes with the same name in JvmVisitors.h.
    val seen = scala.collection.mutable.HashSet.empty[String]
    decls.collect { case h: CefDecl.HandlerStruct if paramStructs.contains(h.name) && seen.add(h.name) => h }
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
            // Simple-typed params only: ints, strings. Reject visitors with handle/struct/buffer args; they'd
            // need handle resolution on the JVM side which is a separate concern.
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

  /** Walks `cef_client_t`'s methods (which all look like `get_X_handler` returning `cef_X_handler_t*`) and returns a
    * mapping from `cef_X_handler_t` → `get_X_handler` so the runtime-server-side `wireClient` can bind every known
    * forwarder. Note: `cef_client_t` is a HandlerStruct (not an ObjectStruct), and its methods have non-void return so
    * {@link deriveHandlers} filters them out — we have to read decls directly here.
    */
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

  /** Mirrors {@link deriveOne} for HandlerStructs: a callback's params become Event-message fields; methods with
    * unsupported types are skipped. Also records `handleStructByField` so a runtime-server-side forwarder can pick the
    * right `HandleTable<T>` to look up each pointer param.
    *
    * Void-returning callbacks ride Kind::Event (fire-and-forget). Non-void callbacks (`int on_before_popup(...)`,
    * `int do_close(...)`) ride Kind::Intercept — runtime server sends the request, blocks waiting for the JVM-supplied
    * return value, then hands it back to CEF. Currently {@link FieldType.Bool} is the only supported return type since
    * CEF's non-void handler callbacks are overwhelmingly `int`-as-bool flow-control signals.
    */
  private def deriveHandlerMethod(
      structPrefix: String,
      fn: FnPtr,
      knownDataStructs: Set[String],
      javadoc: String
  ): Option[HandlerMethod] = {
    val returnType: Option[FieldType] = fn.ret match {
      case CType.Void                             => None
      case CType.Int | CType.UInt | CType.Enum(_) => Some(FieldType.Bool) // narrow int returns to bool
      case _                                      => return None          // unsupported return shape, skip
    }
    val visible = fn.params.filterNot {
      case Param(_, CType.BufferSize(_), _, _) => true
      case _                                   => false
    }
    // Pair each param with its derived FieldSpec + handle struct + isConst flag (the last needed for the
    // forwarder to emit `const cef_string_t*` vs `cef_string_t*` matching the CEF callback signature).
    val triples: List[Option[(FieldSpec, Option[String], Boolean)]] = visible.map { p =>
      toFieldTypeWithStruct(p.typ, knownDataStructs).map { case (t, structOpt) =>
        (FieldSpec(safeFieldName(snakeToCamel(p.name)), t), structOpt, p.isConst)
      }
    }
    if (triples.exists(_.isEmpty)) return None
    val flat    = triples.flatten
    val params  = flat.map(_._1)
    val handles = flat.collect {
      case (spec, Some(struct), _) if spec.ty == FieldType.RemoteHandle => spec.name -> struct
    }.toMap
    val constStrings = flat.collect {
      case (spec, _, isConst) if spec.ty == FieldType.Utf8String => spec.name -> isConst
    }.toMap
    val opName = toCamelCase(fn.name)
    Some(HandlerMethod(
      methodName = snakeToCamel(fn.name),
      cefMethodName = fn.name,
      eventClassName = structPrefix + opName + "Event",
      params = params,
      handleStructByField = handles,
      constStringByField = constStrings,
      returnType = returnType,
      responseClassName = returnType.map(_ => structPrefix + opName + "Response"),
      javadoc = javadoc
    ))
  }

  def deriveFacades(
      decls: List[CefDecl],
      packageName: String,
      knownDataStructs: Set[String] = Set.empty,
      methodDocs: Map[(String, String), String] = Map.empty
  ): List[FacadeSpec] = {
    val byStruct = scala.collection.mutable.LinkedHashMap.empty[String, List[FacadeMethod]]
    decls.foreach {
      case o: CefDecl.ObjectStruct =>
        val structPrefix = toCamelCase(stripCefPrefix(o.name))
        val methods      = o.fns.flatMap(fn =>
          deriveFacadeMethod(structPrefix, fn, knownDataStructs, methodDocs.getOrElse((o.name, fn.name), ""))
        )
        if (methods.nonEmpty) byStruct.put(o.name, methods)
      case _ => ()
    }
    byStruct.iterator.map { case (struct, methods) =>
      FacadeSpec(
        className = safeFacadeClassName(toCamelCase(stripCefPrefix(struct))),
        packageName = packageName,
        cefStructName = struct,
        methods = methods,
        affinity = affinityOf(struct)
      )
    }.toList
  }

  /** Bare facade names that would either shadow a `java.lang` type (Error Prone's `JavaLangClash`) or violate Java
    * naming conventions in a way SpotBugs flags (e.g. `V8Exception` not extending `Throwable` trips
    * `NM_CLASS_NOT_EXCEPTION`). Prefixed with `Cef` to disambiguate; messages are unaffected because they always carry
    * a `Request`/`Response`/`Event` suffix. Add new entries as CEF surfaces them.
    */
  private val ClashingFacadeNames: Set[String] =
    Set("Thread", "Process", "Object", "String", "System", "V8Exception")

  private def safeFacadeClassName(name: String): String =
    if (ClashingFacadeNames.contains(name)) "Cef" + name else name

  /** Public form of the same name conversion used by `deriveFacades` / `deriveDataStructs`. Emitters that need to refer
    * to a generated class by its CEF struct name (e.g. a message field of `FieldType.DataStruct(...)` needs to know the
    * Java type for the `cef_browser_settings_t` overlay) call this.
    */
  def cefStructToClassName(cefStructName: String): String =
    safeFacadeClassName(toCamelCase(stripCefPrefix(cefStructName)))

  /** Inverse of {@link snakeToCamel}: `windowlessFrameRate` → `windowless_frame_rate`. Used by emitters that need to
    * recover the original CEF field name (e.g. dispatcher copying overlay fields into a native `cef_X_t` whose members
    * are snake_case). Doesn't account for `safeFieldName`'s trailing-underscore collision rename — data struct fields
    * don't currently hit those names.
    */
  def camelToSnake(s: String): String =
    s.foldLeft(new StringBuilder()) { (sb, c) =>
      if (c.isUpper && sb.nonEmpty) sb.append('_').append(c.toLower)
      else sb.append(c.toLower)
    }.toString

  /** Method names whose generated facade signature would either fail to compile (overriding an `Object` final method)
    * or silently override an `Object` method and trip Error Prone's `MissingOverride` check. We skip them outright —
    * the underlying Request/Response messages still exist, callers can dispatch directly through `session.request` if
    * they really need these.
    */
  private val ReservedFacadeMethodNames: Set[String] =
    Set("wait", "notify", "notifyAll", "getClass", "clone", "equals", "hashCode", "toString", "finalize")

  private def deriveFacadeMethod(
      structPrefix: String,
      fn: FnPtr,
      knownDataStructs: Set[String],
      javadoc: String
  ): Option[FacadeMethod] = {
    val methodName = snakeToCamel(fn.name)
    if (ReservedFacadeMethodNames.contains(methodName)) return None
    val visible = fn.params.filterNot {
      case Param(_, CType.BufferSize(_), _, _) => true
      case _                                   => false
    }
    // Skip methods with non-const Buffer params: CEF treats those as output buffers (writable, caller-
    // allocated), which doesn't fit the wire model (we'd need a way to ship the result back, but the buffer
    // is already on the JVM side from the request decode). Inputs (const void*) are fine — the runtime server just
    // hands the bytes to the method as a read-only view.
    val hasOutputBuffer = visible.exists {
      case Param(_, CType.Buffer(_), isConst, _) => !isConst
      case _                                     => false
    }
    if (hasOutputBuffer) return None
    val maybeParams: List[Option[(FieldSpec, Option[String])]] =
      visible.map(p => toFieldSpecWithStruct(p, knownDataStructs))
    if (maybeParams.exists(_.isEmpty)) return None
    val explicitParams = maybeParams.flatten.map(_._1)
    val paramHandles   = maybeParams.flatten.collect {
      case (spec, Some(struct)) if spec.ty == FieldType.RemoteHandle => spec.name -> struct
    }.toMap
    val resultEntry: Option[(FieldSpec, Option[String])] = fn.ret match {
      case CType.Void => None
      case other      => toFieldTypeWithStruct(other, knownDataStructs) match {
          case Some((t, structOpt)) => Some(FieldSpec("result", t) -> structOpt)
          case None                 => return None
        }
    }
    val resultField  = resultEntry.map(_._1)
    val resultHandle = resultEntry.flatMap { case (spec, structOpt) =>
      if (spec.ty == FieldType.RemoteHandle) structOpt.map("result" -> _) else None
    }
    val handleStructByField = paramHandles ++ resultHandle.toMap
    val opName              = toCamelCase(fn.name)
    // For methods with Buffer/BufferSize companion pairs, build the original C-arg ordering so the dispatcher
    // can emit the call with size and bytes at their actual positions. The wire only carries Bytes; the
    // BufferSize companion gets re-derived from the byte vector's length on the runtime server side.
    val cCallArgs: Option[List[CCallArg]] =
      if (fn.params.exists(p => p.typ.isInstanceOf[CType.Buffer] || p.typ.isInstanceOf[CType.BufferSize])) {
        // Map each param's snake_case original name to its safeFieldName-camelCase to match what's in
        // explicitParams. CType.Buffer/BufferSize cross-reference each other by original snake name.
        val byOrigName: Map[String, String] = visible.map(p => p.name -> safeFieldName(snakeToCamel(p.name))).toMap
        Some(fn.params.map { p =>
          p.typ match {
            case CType.BufferSize(buf) => CCallArg.BytesSize(byOrigName.getOrElse(buf, snakeToCamel(buf)))
            case _                     => CCallArg.Explicit(byOrigName.getOrElse(p.name, snakeToCamel(p.name)))
          }
        })
      } else None
    Some(FacadeMethod(
      methodName = methodName,
      cefMethodName = fn.name,
      requestClassName = structPrefix + opName + "Request",
      responseClassName = structPrefix + opName + "Response",
      explicitParams = explicitParams,
      resultField = resultField,
      handleStructByField = handleStructByField,
      cCallArgs = cCallArgs,
      javadoc = javadoc
    ))
  }

  /** Variant of {@link toFieldSpec} that also returns the original C struct type for RemoteHandle fields, so the
    * runtime-server-side dispatcher can pick the right `HandleTable<T>`.
    */
  private def toFieldSpecWithStruct(p: Param, knownDataStructs: Set[String]): Option[(FieldSpec, Option[String])] =
    toFieldTypeWithStruct(p.typ, knownDataStructs).map { case (t, structOpt) =>
      FieldSpec(safeFieldName(snakeToCamel(p.name)), t) -> structOpt
    }

  private def toFieldTypeWithStruct(t: CType, knownDataStructs: Set[String]): Option[(FieldType, Option[String])] =
    t match {
      case CType.ObjectPtr(name)    => Some(FieldType.RemoteHandle -> Some(name))
      case CType.OutObjectPtr(name) => Some(FieldType.RemoteHandle -> Some(name))
      case CType.Ptr(inner) if inner.startsWith("cef_") && inner.endsWith("_t") =>
        Some(FieldType.RemoteHandle -> Some(inner))
      // ConstDataStructPtr is a pointer to a by-value data struct (cef_browser_settings_t etc.). It's NOT a
      // refcounted handle — it's a value type the caller fills in. Map to the DataStruct variant so the
      // dispatcher emits overlay decode + native-struct copy. The struct name is also recorded in the second
      // tuple slot so the dispatcher's `isDispatchable` check can resolve it.
      // The `knownDataStructs.isEmpty || …contains` guard lets test-only callers that pass an empty set
      // through the legacy unfiltered path; production callers (Main.scala) pass the deriveDataStructs-derived
      // set so methods referencing structs the overlay deriver couldn't emit get skipped.
      case CType.ConstDataStructPtr(name) if knownDataStructs.isEmpty || knownDataStructs.contains(name) =>
        Some(FieldType.DataStruct(name) -> Some(name))
      case CType.DataStruct(name) if knownDataStructs.isEmpty || knownDataStructs.contains(name) =>
        Some(FieldType.DataStruct(name) -> Some(name))
      // ByValueIn = `const cef_mouse_event_t*` and similar. Same wire shape as ConstDataStructPtr.
      // Strict filter (no isEmpty fallback) — test callers don't exercise this CType anyway, and skipping
      // when unknown matches the production filter in toFieldType above.
      case CType.ByValueIn(name) if knownDataStructs.contains(name) =>
        Some(FieldType.DataStruct(name) -> Some(name))
      case other => toFieldType(other, knownDataStructs).map(_ -> None)
    }

  def derive(
      decls: List[CefDecl],
      packageName: String,
      knownDataStructs: Set[String] = Set.empty
  ): List[MessageSpec] = {
    val all = decls.flatMap {
      case h: CefDecl.HandlerStruct =>
        h.fns.flatMap(deriveOne(h.name, _, packageName, isMethod = false, knownDataStructs))
      case o: CefDecl.ObjectStruct =>
        o.fns.flatMap(deriveOne(o.name, _, packageName, isMethod = true, knownDataStructs))
      case _ => Nil
    }
    // CEF can have struct re-declarations across version-gated headers (e.g. cef_browser_capi.h plus a v144+
    // counterpart), which produces identical class names from {@link deriveOne}. Keep the first occurrence;
    // duplicates that disagree about field shape would be a real codegen ambiguity worth noticing later.
    val seen = scala.collection.mutable.LinkedHashMap.empty[String, MessageSpec]
    all.foreach(s => if (!seen.contains(s.className)) seen.put(s.className, s))
    seen.values.toList
  }

  /** Methods on an `ObjectStruct` (`isMethod=true`) emit a Request + Response pair (sharing `messageId` so they
    * correlate via `corrId`). Void returns produce an empty Response; non-void returns produce a Response with a single
    * `result` field. Methods with an unsupported return type are skipped wholesale.
    *
    * Methods on a `HandlerStruct` (`isMethod=false`) emit a single Event message with no `self` field.
    */
  private def deriveOne(
      structName: String,
      fn: FnPtr,
      packageName: String,
      isMethod: Boolean,
      knownDataStructs: Set[String]
  ): List[MessageSpec] = {
    val visible = fn.params.filterNot {
      case Param(_, CType.BufferSize(_), _, _) => true
      case _                                   => false
    }
    val maybeFields: List[Option[FieldSpec]] = visible.map(p => toFieldSpec(p, knownDataStructs))
    if (maybeFields.exists(_.isEmpty)) return Nil
    val explicitFields = maybeFields.flatten

    val baseName = toCamelCase(stripCefPrefix(structName)) + toCamelCase(fn.name)
    if (isMethod) {
      // Renderer-affinity Requests carry a `frame: RemoteHandle` ahead of `self` so the browser-side relay
      // can pick the correct cef_frame_t to send the process_message on, and so the renderer dispatcher can
      // enter the right V8 context before invoking the method. JVM-side facades hold the frame in a
      // constructor-injected field and inject it transparently, so the user-facing method signature is
      // unaffected.
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
          Nil // unsupported return type; skip the whole method
      }
    } else {
      // Handler callback. Void returns emit a single Event message (fire-and-forget). Non-void Bool returns
      // also emit a paired Response — these ride the Kind::Intercept wire: runtime server sends the Event, blocks for
      // a Response, returns the bool to CEF. Other non-void return shapes are still skipped here.
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

  /** Java method names that Request/Response classes already define (from `CefMessageView` / `CefMessageEncoder`) or
    * that `Object` reserves. A CEF param mapped to one of these would generate a duplicate getter — instead we suffix
    * the field with an underscore so accessors don't clash.
    */
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

  private def toFieldSpec(p: Param, knownDataStructs: Set[String]): Option[FieldSpec] =
    toFieldType(p.typ, knownDataStructs).map(t => FieldSpec(safeFieldName(snakeToCamel(p.name)), t))

  private def toFieldType(t: CType, knownDataStructs: Set[String]): Option[FieldType] = t match {
    case CType.Int | CType.UInt | CType.Enum(_)                               => Some(FieldType.I32)
    case CType.Long | CType.SizeT                                             => Some(FieldType.I64)
    case CType.Bool                                                           => Some(FieldType.Bool)
    case CType.JString                                                        => Some(FieldType.Utf8String)
    case CType.ObjectPtr(_)                                                   => Some(FieldType.RemoteHandle)
    case CType.OutObjectPtr(_)                                                => Some(FieldType.RemoteHandle)
    case CType.Ptr(inner) if inner.startsWith("cef_") && inner.endsWith("_t") =>
      // Raw parsed pointers to cef_*_t structs (before RefineTree promotes them to ObjectPtr) — same wire
      // semantics as RemoteHandle.
      Some(FieldType.RemoteHandle)
    case CType.ConstDataStructPtr(name) if knownDataStructs.isEmpty || knownDataStructs.contains(name) =>
      // By-value settings/info structs. Wire shape: the data struct's own encodedSize/encodeInto rides
      // inline; runtime-server-side dispatcher fills a stack-local cef_X_t before the C call.
      Some(FieldType.DataStruct(name))
    case CType.DataStruct(name) if knownDataStructs.isEmpty || knownDataStructs.contains(name) =>
      // Raw by-value return — `cef_rect_t (*get_bounds)(...)`. Wire shape is the same overlay; dispatcher
      // copies the native struct's fields into the response's overlay member after the call.
      Some(FieldType.DataStruct(name))
    case CType.ByValueIn(name) if knownDataStructs.contains(name) =>
      // `const cef_mouse_event_t*` and friends — the parser hard-codes a list of "by-value" structs in
      // ByValueStructs and tags them ByValueIn. Wire shape is identical to ConstDataStructPtr (inline
      // overlay), so we route them through the same emitter path. Only enable if the data-struct deriver
      // actually emitted a Java/C++ overlay for this struct (some have nested data structs / char16
      // fields the overlay deriver skips — referencing them would produce broken includes).
      Some(FieldType.DataStruct(name))
    case CType.Buffer(_) =>
      // Raw byte buffer — wire shape is length-prefixed bytes. The companion BufferSize was filtered earlier
      // and gets re-synthesized at the call site via FacadeMethod.cCallArgs.
      Some(FieldType.Bytes)
    case CType.StringList =>
      // CEF's cef_string_list_t — wire shape is int32 count + count UTF-8 strings. Runtime-server-side dispatcher
      // converts std::vector<std::string> into a transient cef_string_list_t before/after the C call.
      Some(FieldType.StringList)
    case _ => None
  }

  private def stripCefPrefix(s: String): String =
    s.stripPrefix("cef_").stripSuffix("_t")

  private def toCamelCase(s: String): String =
    s.split('_').iterator.filter(_.nonEmpty).map(p => p.head.toUpper +: p.tail).mkString

  private def snakeToCamel(s: String): String = {
    val parts = s.split('_').iterator.filter(_.nonEmpty).toList
    parts match {
      case Nil          => s
      case head :: rest => head + rest.map(p => p.head.toUpper +: p.tail).mkString
    }
  }

  /** Public so {@link Main} can mint matching ids for synthesised visitor-callback events. */
  def stableId(name: String): Int = {
    val raw    = scala.util.hashing.MurmurHash3.stringHash(name)
    val nonNeg = if (raw == Int.MinValue) 0 else math.abs(raw)
    AstIdBase + (nonNeg % (Int.MaxValue - AstIdBase))
  }
}
