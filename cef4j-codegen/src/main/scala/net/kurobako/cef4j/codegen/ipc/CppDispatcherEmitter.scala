package net.kurobako.cef4j.codegen.ipc

/** Emits a single self-contained C++ header — `Dispatcher.h` — that the helper includes to handle JVM → helper
  * `Request` messages without hand-written switch cases.
  *
  * Supported method shapes (see {@link isDispatchable}):
  *   - params: `I32`/`I64`/`Bool`/`Utf8String`, plus the implicit `self` RemoteHandle (always present)
  *   - return: `Void` or any of the same primitive/string types
  *
  * Supported method shapes:
  *   - params: I32/I64/Bool/Utf8String, plus RemoteHandle when the C struct is a facade
  *   - return: Void or any of the same primitive/string/RemoteHandle types
  *
  * Byte-arrays and handles to non-facade structs (e.g. by-value data structs the IR maps to RemoteHandle) stay
  * deferred. Each dispatched call posts a LambdaTask onto the CEF UI thread (mandatory for almost all CEF C-API calls),
  * which performs the call, releases retains, and sends the response. The dispatcher itself runs on the IpcServer
  * worker thread and only does decode + post.
  *
  * String params arrive as std::string and materialise into a ScopedCefString inside the lambda so the cef_string_t
  * buffer lives across the call. String returns are taken via ScopedCefString::take so the cef_string_userfree_t is
  * freed before the response is encoded. RemoteHandle params arrive as std::int32_t; the lambda looks them up in the
  * matching HandleTable<T>, retains, passes the pointer, and releases after. RemoteHandle returns get inserted via
  * insertOrRelease, which rebalances the +1 the C-API returned against the +1 the table holds.
  */
object CppDispatcherEmitter {

  case class DispatchInputs(
      facades: List[FacadeSpec],
      messageSpecs: List[MessageSpec],
      // Relative include paths from the CEF dist root, e.g. "include/capi/cef_browser_capi.h" or
      // "include/capi/views/cef_box_layout_capi.h" — emitted verbatim into `#include "..."` lines.
      capiHeaders: List[String],
      packageName: String,
      // Lookup for by-value data struct params: cefStructName → DataStructSpec. Used by the dispatcher to
      // emit per-field copy from the wire-decoded overlay into a stack-local cef_X_t before the C call.
      dataStructByCef: Map[String, DataStructSpec] = Map.empty,
      // cefStructName → JvmVisitorSpec for visitor-typed RemoteHandle params: the dispatcher emits a
      // `new JvmXxxVisitor(handle, ipc)` call instead of `tables::xxx.retain` so JVM-owned callbacks fire
      // through the visitor wire.
      jvmVisitorByCef: Map[String, JvmVisitorSpec] = Map.empty,
      // Hand-written renderer-relay messageIds — Requests that are not tied to a per-facade method but
      // still need to be shipped to the renderer subprocess via `cef4j_renderer_req`. Currently used for
      // RendererReleaseHandleRequest, which the renderer-side helper turns into a dispatchRelease call on
      // its own handle tables.
      manualRendererRelayIds: List[Int] = Nil
  )

  /** Returns the full text of the generated `Dispatcher.h`. */
  def emit(in: DispatchInputs): String = {
    val ns = in.packageName.replace('.', '_').toLowerCase
    // Browser-process dispatch only handles browser-affinity facades. Renderer-affinity facades (cef_v8_*,
    // cef_dom_*) are handled by the renderer dispatcher emitted separately; the browser side relays their
    // Requests to the renderer via cef_process_message rather than dispatching them here.
    val browserFacades     = in.facades.filter(_.affinity == ProcessAffinity.Browser)
    val rendererMessageIds = in.facades
      .filter(_.affinity == ProcessAffinity.Renderer)
      .flatMap(_.methods.map(_.requestClassName))
      .distinct
    val facadeStructs     = browserFacades.map(_.cefStructName).toSet
    val dataStructNames   = in.dataStructByCef.keySet
    val visitorStructs    = in.jvmVisitorByCef.keySet
    val dispatchableByFac =
      browserFacades
        .map(f => f -> f.methods.filter(m => isDispatchable(m, facadeStructs, dataStructNames, visitorStructs)))
        .filter(_._2.nonEmpty)
    // A facade gets a static table when either it has its own dispatched methods (so `self` lookups work) or it's
    // referenced as a non-self handle by some dispatched method elsewhere. Without this, `getMainFrame` would
    // compile but the frame return wouldn't have a table to live in.
    // Renderer-affinity Requests are relayed by frame; force `tables::frame` to exist whenever any renderer
    // method exists so the relay can find a cef_frame_t* by id even if no browser-affinity method happens to
    // reference cef_frame_t (defensive — Browser::getMainFrame currently keeps it referenced anyway).
    val frameAlwaysNeeded = rendererMessageIds.nonEmpty
    // Tables include renderer-affinity facades too — they don't get dispatched cases here, but the helper's
    // hand-written code (and the auto-generated HandlerForwarders) still need a `tables::v8Value` etc. so
    // V8 handles passed through render-process handler callbacks can be inserted/looked up uniformly. The
    // renderer dispatcher uses the same tables when the helper runs as the renderer subprocess.
    val tableStructs = in.facades.filter { f =>
      val hasOwnDispatchable = dispatchableByFac.exists(_._1.cefStructName == f.cefStructName)
      val referencedByOther  = dispatchableByFac.exists { case (_, ms) =>
        ms.exists(_.handleStructByField.values.toSet.contains(f.cefStructName))
      }
      val isFrameForRelay = frameAlwaysNeeded && f.cefStructName == "cef_frame_t"
      val isRenderer      = f.affinity == ProcessAffinity.Renderer
      hasOwnDispatchable || referencedByOther || isFrameForRelay || isRenderer
    }
    val tableFields  = tableStructs.map(tableField)
    val capiIncludes = in.capiHeaders.sorted.map(h => s"""#include "$h"""").mkString("\n")
    val genIncludes  = dispatchableByFac.flatMap { case (_, ms) =>
      ms.flatMap(m => List(m.requestClassName, m.responseClassName))
    }.distinct.sorted.map(c => s"""#include "$c.h"""").mkString("\n")
    val cases = dispatchableByFac.flatMap { case (f, ms) =>
      ms.map(m => renderCase(f, m, ns, in.messageSpecs, in.dataStructByCef, in.jvmVisitorByCef))
    }.mkString("\n")
    val dispatchableCount = dispatchableByFac.map(_._2.size).sum
    val skippedCount      = browserFacades.flatMap(_.methods).size - dispatchableCount
    val rendererCount     = in.facades.filter(_.affinity == ProcessAffinity.Renderer).flatMap(_.methods).size

    // Case-label list of renderer-affinity Request message ids, all falling through to relayToRenderer.
    // Generated from the messageSpecs index so we don't depend on each Request header being included in the
    // dispatcher (the relay only needs the id and the payload bytes — it never instantiates the Request).
    val perFacadeRendererLabels: List[String] = in.facades
      .filter(_.affinity == ProcessAffinity.Renderer)
      .flatMap { f =>
        f.methods.flatMap { m =>
          in.messageSpecs.find(_.className == m.requestClassName).map { spec =>
            s"        case ${spec.messageId}: // ${m.requestClassName} (${f.cefStructName}::${m.cefMethodName})"
          }
        }
      }
    val manualRendererLabels: List[String] = in.manualRendererRelayIds.map(id =>
      s"        case $id: // hand-written renderer-relay message"
    )
    val rendererCaseLabels: List[String] = (perFacadeRendererLabels ++ manualRendererLabels).distinct
    val rendererSwitch                   =
      if (rendererCaseLabels.isEmpty) ""
      else
        s"""${rendererCaseLabels.mkString("\n")}
           |            return relayToRenderer(ctx, h, payload);""".stripMargin

    s"""// Generated by net.kurobako.cef4j.codegen.ipc — do not edit by hand.
       |//
       |// Dispatchable AST methods covered: $dispatchableCount
       |// Methods skipped (RemoteHandle params/return, byte arrays, etc.): $skippedCount
       |// Renderer-affinity methods relayed to renderer subprocess: $rendererCount
       |#ifndef CEF4J_IPC_GEN_DISPATCHER_H_
       |#define CEF4J_IPC_GEN_DISPATCHER_H_
       |
       |#include <atomic>
       |#include <cstdint>
       |#include <cstring>
       |#include <functional>
       |#include <string>
       |#include <tuple>
       |#include <utility>
       |#include <vector>
       |
       |#include "include/capi/cef_base_capi.h"
       |#include "include/capi/cef_frame_capi.h"
       |#include "include/capi/cef_process_message_capi.h"
       |#include "include/capi/cef_task_capi.h"
       |#include "include/internal/cef_string.h"
       |#include "include/internal/cef_string_list.h"
       |$capiIncludes
       |
       |#include "Envelope.h"
       |#include "HandleTable.h"
       |#include "IpcServer.h"
       |${if (in.jvmVisitorByCef.nonEmpty) "\n#include \"JvmVisitors.h\"" else ""}
       |
       |$genIncludes
       |
       |namespace ${ns}_dispatcher {
       |
       |namespace genvisitors = ${ns}_visitors;
       |
       |/** RAII shim around `cef_string_t` for params and `cef_string_userfree_t` for returns; mirrors the helper's
       |  * own ScopedCefString. Lives in the dispatcher namespace to avoid clashing with the helper's copy. */
       |class ScopedCefString {
       |public:
       |    ScopedCefString() : s_{} {}
       |    explicit ScopedCefString(const std::string& utf8) : s_{} {
       |        if (!utf8.empty()) cef_string_utf8_to_utf16(utf8.data(), utf8.size(), &s_);
       |    }
       |    static ScopedCefString take(cef_string_userfree_t uf) {
       |        ScopedCefString s;
       |        if (uf) {
       |            s.s_ = *uf;
       |            uf->str = nullptr;
       |            uf->length = 0;
       |            cef_string_userfree_free(uf);
       |        }
       |        return s;
       |    }
       |    ~ScopedCefString() { cef_string_clear(&s_); }
       |    ScopedCefString(const ScopedCefString&) = delete;
       |    ScopedCefString& operator=(const ScopedCefString&) = delete;
       |    ScopedCefString(ScopedCefString&& o) noexcept : s_(o.s_) { o.s_ = {}; }
       |    cef_string_t* get() { return &s_; }
       |    const cef_string_t* get() const { return &s_; }
       |    std::string toUtf8() const {
       |        if (s_.length == 0) return {};
       |        cef_string_utf8_t utf8{};
       |        cef_string_utf16_to_utf8(s_.str, s_.length, &utf8);
       |        std::string r(utf8.str, utf8.length);
       |        cef_string_utf8_clear(&utf8);
       |        return r;
       |    }
       |private:
       |    cef_string_t s_;
       |};
       |
       |/** Extracts CEF C-API function-pointer parameter types so we can `static_cast` int32 wire values to the
       |  * native enum types CEF expects (cef_state_t, cef_color_model_t, etc.). The dispatcher's call sites use
       |  * `cefArg<decltype(receiver->method), N>(value)` to convert arg N to the right CEF enum/int. */
       |template <typename F> struct fn_args;
       |template <typename R, typename... Args> struct fn_args<R (*)(Args...)> {
       |    using result = R;
       |    template <std::size_t N> using arg = std::tuple_element_t<N, std::tuple<Args...>>;
       |};
       |template <typename F, std::size_t N, typename V>
       |constexpr typename fn_args<F>::template arg<N> cefArg(V v) {
       |    return static_cast<typename fn_args<F>::template arg<N>>(v);
       |}
       |template <typename F, typename V>
       |constexpr typename fn_args<F>::result cefRet(V v) {
       |    return static_cast<typename fn_args<F>::result>(v);
       |}
       |
       |/** Reads a `cef_string_t` member out of a CEF return-by-value struct into UTF-8. The native cef_string_t
       |  * still owns its UTF-16 buffer until the caller clears it; this helper is read-only. */
       |inline std::string cef4j_dispatcher_utf16ToStdString(const cef_string_t& s) {
       |    if (s.length == 0) return {};
       |    cef_string_utf8_t utf8{};
       |    cef_string_utf16_to_utf8(s.str, s.length, &utf8);
       |    std::string r(utf8.str, utf8.length);
       |    cef_string_utf8_clear(&utf8);
       |    return r;
       |}
       |
       |/** Inserts a freshly-returned, +1-retained CEF pointer into a HandleTable and rebalances the refcount.
       |  * `HandleTable::insert` retains again (+2 total), so we release once to drop back to +1 owned by the table.
       |  * Null pointers become id 0; a null table releases the input and returns 0. */
       |template <typename T>
       |std::int32_t insertOrRelease(cef4j::ipc::HandleTable<T>* table, T* ptr) {
       |    if (!ptr) return 0;
       |    if (!table) {
       |        auto* base = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
       |        base->release(base);
       |        return 0;
       |    }
       |    std::int32_t id = table->insert(ptr);
       |    auto* base = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
       |    base->release(base);
       |    return id;
       |}
       |
       |/** Per-facade-struct HandleTable<T> instances. Lifetime: process. The helper accesses these directly to
       |  * register browsers etc. (e.g. `tables::browser.insert(b)` after `OnAfterCreated`); the dispatcher's
       |  * generated cases use them for retain/release/insert against handle params and returns. */
       |namespace tables {
       |${tableFields.mkString("\n")}
       |} // namespace tables
       |
       |/** Releases the table entry matching the given CEF struct name. Used by the helper to dispatch
       |  * `ReleaseHandleRequest{handle, kind}` to the right `HandleTable<T>::release`. Returns true if `kind`
       |  * was recognised (release attempted). Unknown kinds are silently ignored. */
       |inline bool dispatchRelease(const std::string& kind, std::int32_t id) {
       |${tableStructs.map(f => releaseCase(f)).mkString("\n")}
       |    return false;
       |}
       |
       |/** Held by the helper main and passed into {@link dispatch}. Owned by the helper; the dispatcher does not
       |  * free anything. Tables are static and live in `${ns}_dispatcher::tables`. */
       |struct DispatcherContext {
       |    cef4j::ipc::IpcServer* ipc = nullptr;
       |};
       |
       |/** Runs a {@code std::function<void()>} as a CEF task. Self-deletes after one execute via the standard
       |  * ref-counted release path; refCount starts at 1 and the task pointer is handed off to {@code cef_post_task}. */
       |struct LambdaTask : cef_task_t {
       |    std::atomic<int> refCount{1};
       |    std::function<void()> fn;
       |
       |    explicit LambdaTask(std::function<void()> f) : cef_task_t{}, fn(std::move(f)) {
       |        base.size = sizeof(cef_task_t);
       |        base.add_ref = [](cef_base_ref_counted_t* self) {
       |            reinterpret_cast<LambdaTask*>(self)->refCount.fetch_add(1, std::memory_order_relaxed);
       |        };
       |        base.release = [](cef_base_ref_counted_t* self) -> int {
       |            auto* t = reinterpret_cast<LambdaTask*>(self);
       |            if (t->refCount.fetch_sub(1, std::memory_order_acq_rel) == 1) {
       |                delete t;
       |                return 1;
       |            }
       |            return 0;
       |        };
       |        base.has_one_ref = [](cef_base_ref_counted_t* self) -> int {
       |            return reinterpret_cast<LambdaTask*>(self)->refCount.load(std::memory_order_acquire) == 1;
       |        };
       |        base.has_at_least_one_ref = [](cef_base_ref_counted_t* self) -> int {
       |            return reinterpret_cast<LambdaTask*>(self)->refCount.load(std::memory_order_acquire) >= 1;
       |        };
       |        execute = [](cef_task_t* self) { reinterpret_cast<LambdaTask*>(self)->fn(); };
       |    }
       |};
       |
       |/** Relays a renderer-affinity Request to the renderer subprocess via cef_process_message. Decodes the
       |  * leading int32 frame handle from the wire payload (renderer-affinity Requests have `frame: RemoteHandle`
       |  * as their first field, see SpecDeriver.deriveOne), looks the frame up in `tables::frame`, and ships the
       |  * raw payload over a "cef4j_renderer_req" process_message. The renderer-side dispatcher decodes the
       |  * Request from the same payload bytes and invokes the method inside the right V8 context.
       |  *
       |  * Reply path: renderer fires "cef4j_renderer_resp" carrying corrId/messageId/payload back; the helper's
       |  * Client::on_process_message_received turns that into a Kind::Response on the IPC wire.
       |  *
       |  * Posts the actual `frame->send_process_message` call onto the CEF UI thread (CEF API contract for
       |  * cef_frame_t methods); the dispatcher itself runs on the IPC worker thread.
       |  */
       |inline bool relayToRenderer(const DispatcherContext& ctx, const cef4j::ipc::Header& h,
       |                            const std::vector<std::uint8_t>& payload) {
       |    if (payload.size() < 4) return false;
       |    std::int32_t frameId = 0;
       |    std::memcpy(&frameId, payload.data(), sizeof(frameId));
       |    cef_frame_t* frame = tables::frame.retain(frameId);
       |    if (!frame) {
       |        static const std::uint8_t kReceiverGonePayload[8] = {0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
       |        if (ctx.ipc) ctx.ipc->send(cef4j::ipc::Kind::Error, 0, h.corrId, h.messageId,
       |                                   kReceiverGonePayload, sizeof(kReceiverGonePayload));
       |        return true;
       |    }
       |    std::int32_t corrId    = h.corrId;
       |    std::int32_t messageId = h.messageId;
       |    std::vector<std::uint8_t> payloadCopy = payload;
       |    cef_post_task(TID_UI, new LambdaTask([frame, corrId, messageId, payloadCopy]() {
       |        cef_string_t name{};
       |        cef_string_utf8_to_utf16("cef4j_renderer_req", 18, &name);
       |        cef_process_message_t* msg = cef_process_message_create(&name);
       |        cef_string_clear(&name);
       |        if (msg) {
       |            cef_list_value_t* args = msg->get_argument_list(msg);
       |            if (args) {
       |                args->set_int(args, 0, corrId);
       |                args->set_int(args, 1, messageId);
       |                cef_binary_value_t* binary = cef_binary_value_create(payloadCopy.data(), payloadCopy.size());
       |                if (binary) {
       |                    // `set_binary` ADOPTS the +1 from `cef_binary_value_create` — do NOT release `binary`
       |                    // again here, that's a double-decrement that crashes the helper.
       |                    args->set_binary(args, 2, binary);
       |                }
       |                auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(args);
       |                ab->release(ab);
       |            }
       |            // CEF adopts the message and invalidates our reference — do NOT release it ourselves.
       |            frame->send_process_message(frame, PID_RENDERER, msg);
       |        }
       |        auto* base = reinterpret_cast<cef_base_ref_counted_t*>(frame);
       |        base->release(base);
       |    }));
       |    return true;
       |}
       |
       |/** Tries to handle one frame. Returns {@code true} iff the message id was recognised by codegen — the helper's
       |  * own switch should fall through to this for ids it doesn't claim. */
       |inline bool dispatch(const DispatcherContext& ctx, const cef4j::ipc::Header& h,
       |                     std::vector<std::uint8_t> payload) {
       |    if (h.kind != cef4j::ipc::Kind::Request) return false;
       |    namespace gen = $ns;
       |    switch (h.messageId) {
       |$cases
       |$rendererSwitch
       |        default:
       |            return false;
       |    }
       |}
       |
       |} // namespace ${ns}_dispatcher
       |
       |#endif // CEF4J_IPC_GEN_DISPATCHER_H_
       |""".stripMargin
  }

  /** Methods that fit our supported subset. Byte-arrays remain deferred. RemoteHandle params and returns are supported
    * when their CEF struct type is itself a facade (so the dispatcher's `DispatcherContext` carries the matching
    * `HandleTable<T>*`); methods referencing non-facade struct types (e.g. by-value data structs that the IR maps to
    * RemoteHandle) get skipped.
    */
  private def isDispatchable(
      m: FacadeMethod,
      facadeStructs: Set[String],
      dataStructNames: Set[String],
      visitorStructs: Set[String] = Set.empty
  ): Boolean = {
    def fieldOk(name: String, ty: FieldType): Boolean = ty match {
      case FieldType.I32 | FieldType.I64 | FieldType.Bool | FieldType.Utf8String => true
      case FieldType.Bytes        => true // raw buffer; BufferSize companion rebuilt at the call via cCallArgs
      case FieldType.StringList   => true // cef_string_list_t — built transiently inside the lambda
      case FieldType.RemoteHandle =>
        m.handleStructByField.get(name).exists(s => facadeStructs.contains(s) || visitorStructs.contains(s))
      case FieldType.DataStruct(cefName) => dataStructNames.contains(cefName)
      case _                             => false
    }
    val paramsOk = m.explicitParams.forall(p => fieldOk(p.name, p.ty))
    val resultOk = m.resultField match {
      case None    => true
      case Some(f) => fieldOk("result", f.ty)
    }
    paramsOk && resultOk
  }

  /** Inside `namespace tables`, field name is just the struct's short camelCase form: `cef_browser_t` → `browser`,
    * `cef_browser_host_t` → `browserHost`. The `tables::` namespace makes the suffix redundant.
    */
  private def tableFieldName(cefStruct: String): String = {
    val core = stripCefPrefix(cefStruct)
    core.split('_').iterator.zipWithIndex.map { case (p, i) =>
      if (i == 0) p else p.headOption.fold("")(_.toUpper.toString) + p.drop(1)
    }.mkString
  }

  /** `inline` lets the table be defined in this header without ODR violations across multiple translation units. */
  private def tableField(f: FacadeSpec): String =
    s"    inline cef4j::ipc::HandleTable<${f.cefStructName}> ${tableFieldName(f.cefStructName)};"

  /** One `if (kind == "cef_X_t") { tables::x.release(id); return true; }` clause per facade. */
  private def releaseCase(f: FacadeSpec): String =
    s"""    if (kind == "${f.cefStructName}") { tables::${tableFieldName(
        f.cefStructName
      )}.release(id); return true; }"""

  private def stripCefPrefix(name: String): String =
    name.stripPrefix("cef_").stripSuffix("_t")

  private def renderCase(
      f: FacadeSpec,
      m: FacadeMethod,
      ns: String,
      messageSpecs: List[MessageSpec],
      dataStructByCef: Map[String, DataStructSpec],
      jvmVisitorByCef: Map[String, JvmVisitorSpec] = Map.empty
  ): String = {
    val req       = m.requestClassName
    val resp      = m.responseClassName
    val table     = tableFieldName(f.cefStructName)
    val cFn       = m.cefMethodName
    val msgIdExpr = s"gen::$req::kMessageId"
    // Per-param decode + capture. RemoteHandle params snapshot the int32 id; the lambda then looks up + retains via
    // the right HandleTable inside its body. Bool narrows to int at the call site to match CEF's C-API. String
    // params arrive as std::string and materialise into a ScopedCefString inside the lambda so the cef_string_t
    // buffer outlives the call.
    val captureExtras = m.explicitParams.map { p =>
      val cppTy = p.ty match {
        case FieldType.I32          => "std::int32_t"
        case FieldType.I64          => "std::int64_t"
        case FieldType.Bool         => "bool"
        case FieldType.Utf8String   => "std::string"
        case FieldType.Bytes        => "std::vector<std::uint8_t>"
        case FieldType.StringList   => "std::vector<std::string>"
        case FieldType.RemoteHandle => "std::int32_t"
        // Qualify with the generated namespace (`gen` is aliased near the top of dispatch()) so the
        // dispatcher case body resolves the overlay class regardless of the current namespace context.
        case FieldType.DataStruct(cefName) => "gen::" + SpecDeriver.cefStructToClassName(cefName)
        case _                             => "std::int32_t" // unreachable per isDispatchable
      }
      // Move-construct overlays/byte vectors (which can be large) so we don't deep-copy out of the request.
      val rhs = p.ty match {
        case FieldType.DataStruct(_) => s"std::move(req.${p.name})"
        case FieldType.Bytes         => s"std::move(req.${p.name})"
        case FieldType.StringList    => s"std::move(req.${p.name})"
        case _                       => s"req.${p.name}"
      }
      s"            $cppTy ${p.name} = $rhs;"
    }.mkString("\n")
    // Inside the lambda: materialise strings, look up RemoteHandle params, build native cef_X_t structs from
    // by-value data struct overlays before the call.
    val lambdaPrelude = m.explicitParams.flatMap { p =>
      p.ty match {
        case FieldType.Utf8String =>
          List(s"                ScopedCefString ${p.name}_cef(${p.name});")
        case FieldType.RemoteHandle =>
          val struct = m.handleStructByField(p.name)
          jvmVisitorByCef.get(struct) match {
            case Some(vSpec) =>
              // JVM-owned visitor: synthesise a fresh cef_X_t bound to the JVM callback id. CEF holds the
              // resulting +1 retain; when CEF eventually releases, the synthetic deletes itself.
              val syntheticCls = CppJvmVisitorEmitter.syntheticClassName(vSpec)
              List(
                s"                $struct* ${p.name}_ptr = ${p.name} != 0 ?",
                s"                        reinterpret_cast<$struct*>(new genvisitors::$syntheticCls(${p.name}, ipc)) :",
                s"                        nullptr;"
              )
            case None =>
              val tbl = tableFieldName(struct)
              List(
                s"                $struct* ${p.name}_ptr = ${p.name} != 0 ? tables::$tbl.retain(${p.name}) : nullptr;"
              )
          }
        case FieldType.DataStruct(cefName) =>
          val spec      = dataStructByCef(cefName)
          val nativeVar = s"${p.name}_native"
          val initLines = s"                $cefName $nativeVar{};" :: nativeStructFieldCopies(spec, p.name, nativeVar)
          initLines
        case FieldType.StringList =>
          // Build a transient cef_string_list_t from the std::vector<std::string>. cef_string_list_append
          // takes a cef_string_t* — convert each std::string via cef_string_utf8_to_utf16 into a temp.
          List(
            s"                cef_string_list_t ${p.name}_list = cef_string_list_alloc();",
            s"                for (const auto& __s : ${p.name}) {",
            s"                    cef_string_t __cs{};",
            s"                    if (!__s.empty()) cef_string_utf8_to_utf16(__s.data(), __s.size(), &__cs);",
            s"                    cef_string_list_append(${p.name}_list, &__cs);",
            s"                    cef_string_clear(&__cs);",
            s"                }"
          )
        case _ => Nil
      }
    }.mkString("\n")
    // After the call: release retained RemoteHandle param pointers and clear cef_string_t fields in any
    // native structs we built (so their UTF-16 buffers don't leak).
    val lambdaPostlude = m.explicitParams.flatMap { p =>
      p.ty match {
        case FieldType.RemoteHandle =>
          val struct = m.handleStructByField(p.name)
          if (jvmVisitorByCef.contains(struct)) {
            // Visitor synthetics are handed to CEF with refcount=1; CEF owns the lifecycle from here. No
            // release on our side — that would double-decrement and crash on the next callback.
            Nil
          } else {
            List(
              s"""                if (${p.name}_ptr) {
                 |                    auto* ${p.name}_base = reinterpret_cast<cef_base_ref_counted_t*>(${p.name}_ptr);
                 |                    ${p.name}_base->release(${p.name}_base);
                 |                }""".stripMargin
            )
          }
        case FieldType.DataStruct(cefName) =>
          val spec = dataStructByCef(cefName)
          nativeStructStringClears(spec, s"${p.name}_native")
        case FieldType.StringList =>
          List(s"                cef_string_list_free(${p.name}_list);")
        case _ => Nil
      }
    }.mkString("\n")
    // Tables are static globals in `tables::`, so the lambda doesn't need to capture pointers to them.
    val captureNames   = m.explicitParams.map(_.name)
    val lambdaCaptures = ("receiver" :: "ipc" :: "corrId" :: "msgId" :: captureNames).mkString(", ")
    // Each explicit param goes at C-arg index N+1 (self is at 0). CEF enum-typed args need a cast from
    // int32 → cef_X_t; the cefArg<F, N> template handles all cases (int → int is no-op). When `cCallArgs`
    // is supplied (Buffer/BufferSize-pair methods), walk the original CEF signature instead so the size
    // companion lands at its real C-arg index.
    val cFnRef  = s"receiver->$cFn"
    val argList = m.cCallArgs match {
      case Some(orig) =>
        val byName = m.explicitParams.map(p => p.name -> p).toMap
        val pieces = orig.zipWithIndex.map { case (entry, idx) =>
          val cArgIdx = idx + 1 // self is at 0
          entry match {
            case CCallArg.Explicit(name) =>
              val p = byName(name)
              paramArgExpr(p, cFnRef, cArgIdx)
            case CCallArg.BytesSize(bytesField) =>
              s"static_cast<size_t>(${bytesField}.size())"
          }
        }
        ("receiver" :: pieces).mkString(", ")
      case None =>
        ("receiver" :: m.explicitParams.zipWithIndex.map { case (p, idx) =>
          paramArgExpr(p, cFnRef, idx + 1)
        }).mkString(", ")
    }
    val callAndRespond = m.resultField match {
      case None =>
        val preludeBlock  = if (lambdaPrelude.isEmpty) "" else s"$lambdaPrelude\n"
        val postludeBlock = if (lambdaPostlude.isEmpty) "" else s"\n$lambdaPostlude"
        s"""$preludeBlock                receiver->$cFn($argList);
           |                auto* base = reinterpret_cast<cef_base_ref_counted_t*>(receiver);
           |                base->release(base);$postludeBlock
           |                if (ipc) ipc->send(cef4j::ipc::Kind::Response, 0, corrId, msgId, nullptr, 0);""".stripMargin
      case Some(field) =>
        val preludeBlock  = if (lambdaPrelude.isEmpty) "" else s"$lambdaPrelude\n"
        val postludeBlock = if (lambdaPostlude.isEmpty) "" else s"\n$lambdaPostlude"
        val resultStruct  = m.handleStructByField.get("result")
        val fieldName     =
          messageSpecs.find(_.className == resp).flatMap(_.fields.headOption.map(_.name)).getOrElse(field.name)
        field.ty match {
          case FieldType.DataStruct(cefName) =>
            // Returned-by-value cef_X_t: copy each field into the response's overlay member, then encode.
            // No `auto* base; base->release(base)` for the result — by-value structs aren't refcounted.
            val spec   = dataStructByCef(cefName)
            val copies = nativeStructToOverlayCopies(spec, "rawResult", s"resp.$fieldName").mkString("\n")
            s"""$preludeBlock                $cefName rawResult = receiver->$cFn($argList);
               |                auto* base = reinterpret_cast<cef_base_ref_counted_t*>(receiver);
               |                base->release(base);$postludeBlock
               |                if (ipc) {
               |                    gen::$resp resp;
               |$copies
               |                    std::vector<std::uint8_t> respPayload(resp.encodedSize());
               |                    resp.encodeInto(respPayload.data());
               |                    ipc->send(cef4j::ipc::Kind::Response, 0, corrId, msgId,
               |                              respPayload.data(), respPayload.size());
               |                }""".stripMargin
          case _ =>
            val (cppRetType, assign) = field.ty match {
              case FieldType.I32        => ("int", "static_cast<std::int32_t>(rawResult)")
              case FieldType.I64        => ("int64_t", "static_cast<std::int64_t>(rawResult)")
              case FieldType.Bool       => ("int", "rawResult != 0")
              case FieldType.Utf8String =>
                ("cef_string_userfree_t", "ScopedCefString::take(rawResult).toUtf8()")
              case FieldType.RemoteHandle =>
                val tbl = resultStruct.map(tableFieldName).getOrElse("nullptr_table")
                (s"${resultStruct.get}*", s"insertOrRelease(&tables::$tbl, rawResult)")
              case _ => ("int", "0") // unreachable per isDispatchable
            }
            s"""$preludeBlock                $cppRetType rawResult = receiver->$cFn($argList);
               |                auto* base = reinterpret_cast<cef_base_ref_counted_t*>(receiver);
               |                base->release(base);$postludeBlock
               |                if (ipc) {
               |                    gen::$resp resp;
               |                    resp.$fieldName = $assign;
               |                    std::vector<std::uint8_t> respPayload(resp.encodedSize());
               |                    resp.encodeInto(respPayload.data());
               |                    ipc->send(cef4j::ipc::Kind::Response, 0, corrId, msgId,
               |                              respPayload.data(), respPayload.size());
               |                }""".stripMargin
        }
    }
    val captureBlock = if (captureExtras.isEmpty) "" else s"\n$captureExtras"
    // When the receiver is missing, raise a structured Kind::Error(ReceiverGone) so the JVM-side future
    // fails with CefRemoteException instead of decoding a zero-default response. Payload: int32 code,
    // int32 utf8MessageLength, utf8 bytes — fixed for now (no message text, len = 0).
    val emptyAck =
      """static const std::uint8_t kReceiverGonePayload[8] = {0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
        |                if (ipc) ipc->send(cef4j::ipc::Kind::Error, 0, corrId, msgId,
        |                                   kReceiverGonePayload, sizeof(kReceiverGonePayload));""".stripMargin
    s"""        case $msgIdExpr: {
       |            auto* ipc = ctx.ipc;
       |            const std::int32_t corrId = h.corrId;
       |            const std::int32_t msgId = h.messageId;
       |            auto req = gen::$req::decode(payload.data(), payload.size());
       |            auto* receiver = tables::$table.retain(req.self);
       |            if (!receiver) {
       |                $emptyAck
       |                return true;
       |            }$captureBlock
       |            cef_post_task(TID_UI, new LambdaTask([$lambdaCaptures]() {
       |$callAndRespond
       |            }));
       |            return true;
       |        }""".stripMargin
  }

  /** Per-param expression at the call site. Primitives go through `cefArg<F, N>` so int32 wire values land as the right
    * CEF enum/int type (CEF's enums are scoped C-style typedefs that don't accept implicit int conversion under
    * -Wno-permissive). Strings, handles, and data structs already build to native shapes via their preludes — they pass
    * through directly.
    */
  private def paramArgExpr(p: FieldSpec, cFnRef: String, argIdx: Int): String = p.ty match {
    case FieldType.Bool                => s"cefArg<decltype($cFnRef), $argIdx>(${p.name} ? 1 : 0)"
    case FieldType.I32 | FieldType.I64 =>
      s"cefArg<decltype($cFnRef), $argIdx>(${p.name})"
    case FieldType.Utf8String    => s"${p.name}_cef.get()"
    case FieldType.Bytes         => s"${p.name}.data()"
    case FieldType.StringList    => s"${p.name}_list"
    case FieldType.RemoteHandle  => s"${p.name}_ptr"
    case FieldType.DataStruct(_) => s"&${p.name}_native"
    case _                       => p.name
  }

  /** Per-field copy from a wire-decoded overlay into a native `cef_X_t` value. Uses `decltype`-cast for
    * primitives/enums (handles `cef_state_t`, `cef_color_t`, etc. uniformly) and `cef_string_utf8_to_utf16` for
    * strings. The native struct's `size` field gets `sizeof(...)` so CEF's struct-version checks pass.
    */
  private def nativeStructFieldCopies(spec: DataStructSpec, overlayVar: String, nativeVar: String): List[String] = {
    // Some CEF data structs (cef_browser_settings_t, cef_window_info_t…) start with `size_t size` for ABI
    // versioning; others (cef_mouse_event_t, cef_rect_t) don't. Only emit the size-init line when the
    // struct actually has the field — otherwise we emit `event_native.size = sizeof(...)` for a struct
    // that has no `size` member and the helper doesn't compile.
    val hasSizeField = spec.fields.exists(f => SpecDeriver.camelToSnake(f.name) == "size")
    val sizeAssign   = if (hasSizeField) List(s"                $nativeVar.size = sizeof($nativeVar);") else Nil
    val copies       = spec.fields.flatMap { f =>
      val nativeField = SpecDeriver.camelToSnake(f.name)
      // Skip `size` — we set it explicitly above when present. CEF treats it as the struct-version
      // sentinel and the overlay's value (zero by default) would clobber that.
      if (nativeField == "size") Nil
      else f.ty match {
        case FieldType.I32 | FieldType.I64 | FieldType.Bool =>
          List(
            s"                $nativeVar.$nativeField = static_cast<decltype($nativeVar.$nativeField)>($overlayVar.${f.name});"
          )
        case FieldType.Utf8String =>
          List(
            s"                if (!$overlayVar.${f.name}.empty()) cef_string_utf8_to_utf16($overlayVar.${f.name}.data(), $overlayVar.${f.name}.size(), &$nativeVar.$nativeField);"
          )
        case _ => Nil // RemoteHandle / DataStruct fields can't appear in the overlay (deriveDataStructs filters)
      }
    }
    sizeAssign ++ copies
  }

  /** Per-string-field `cef_string_clear` for any cef_string_t members of a native struct we built. CEF copies settings
    * strings internally during `cef_browser_host_create_browser` etc., so clearing them right after the call is safe
    * and avoids leaking the UTF-16 buffer.
    */
  private def nativeStructStringClears(spec: DataStructSpec, nativeVar: String): List[String] =
    spec.fields.collect {
      case f if f.ty == FieldType.Utf8String =>
        val nativeField = SpecDeriver.camelToSnake(f.name)
        s"                cef_string_clear(&$nativeVar.$nativeField);"
    }

  /** Reverse of {@link nativeStructFieldCopies}: copies a native `cef_X_t` value (returned from a CEF method) into an
    * overlay so it can be encoded back to the JVM. Primitives go through `decltype` casts to the overlay field's
    * `std::int32_t`/`std::int64_t`/`bool` type; cef_string_t members are utf16-decoded into `std::string` and the
    * native string buffer is cleared right after (CEF returns ownership of by-value-struct strings to the caller).
    */
  private def nativeStructToOverlayCopies(
      spec: DataStructSpec,
      nativeVar: String,
      overlayVar: String
  ): List[String] =
    spec.fields.flatMap { f =>
      val nativeField = SpecDeriver.camelToSnake(f.name)
      // Skip the struct-version `size` field — it's an implementation detail of CEF's struct ABI, not user data.
      if (nativeField == "size") Nil
      else f.ty match {
        case FieldType.I32 | FieldType.I64 | FieldType.Bool =>
          List(
            s"                $overlayVar.${f.name} = static_cast<decltype($overlayVar.${f.name})>($nativeVar.$nativeField);"
          )
        case FieldType.Utf8String =>
          List(
            s"                $overlayVar.${f.name} = cef4j_dispatcher_utf16ToStdString($nativeVar.$nativeField);",
            s"                cef_string_clear(&$nativeVar.$nativeField);"
          )
        case _ => Nil // RemoteHandle / nested DataStruct fields are filtered out by deriveDataStructs
      }
    }
}
