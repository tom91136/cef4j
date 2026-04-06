// CEF subprocess helper with JavaScript eval relay.
//
// This executable is launched by CEF for renderer/GPU/utility processes.
// For renderer processes, it installs a CefRenderProcessHandler that listens
// for IPC messages from the browser process and evaluates JavaScript in V8,
// sending results back via CefProcessMessage.
//
// Protocol:
//   Browser -> Renderer:
//     "cef4j:eval"    [reqId(int), expression(string), mode(int)]
//     "cef4j:get"     [reqId(int), handleId(int), key(string), mode(int)]
//     "cef4j:set"     [reqId(int), handleId(int), key(string), valueJson(string)]
//     "cef4j:call"    [reqId(int), handleId(int), method(string), argsJson(string), mode(int)]
//     "cef4j:release" [handleId(int)]
//
//     "cef4j:invoke"  [reqId(int), handleId(int), argsJson(string), mode(int)]
//     "cef4j:mkcb"    [reqId(int), callbackId(int)]
//
//   Renderer -> Browser:
//     "cef4j:result"  [reqId(int), ok(bool), type(int), payload(string or int)]
//       type: 0=json, 1=handle, 2=void, 3=error
//     "cef4j:cb"      [callbackId(int), numArgs(int), handle1(int), handle2(int), ...]

#include "include/capi/cef_app_capi.h"
#include "include/capi/cef_render_process_handler_capi.h"
#include "include/capi/cef_scheme_capi.h"
#include "include/capi/cef_v8_capi.h"
#include "include/capi/cef_process_message_capi.h"
#include "include/capi/cef_frame_capi.h"
#include "include/cef_api_hash.h"

#include <atomic>
#include <string>
#include <unordered_map>
#include <unordered_set>
#include <vector>

// ---------------------------------------------------------------------------
// RAII helpers
// ---------------------------------------------------------------------------

// Minimal ref-count init for subprocess structs (no JVM, so release just deletes).
template<typename T, typename CefStruct>
void InitSubprocessRefCount(cef_base_ref_counted_t* base) {
    base->size = sizeof(CefStruct);
    base->add_ref = [](cef_base_ref_counted_t* self) {
        reinterpret_cast<T*>(self)->refCount.fetch_add(1, std::memory_order_relaxed);
    };
    base->release = [](cef_base_ref_counted_t* self) -> int {
        auto* t = reinterpret_cast<T*>(self);
        if (t->refCount.fetch_sub(1, std::memory_order_acq_rel) == 1) {
            delete t;
            return 1;
        }
        return 0;
    };
    base->has_one_ref = [](cef_base_ref_counted_t* self) -> int {
        return reinterpret_cast<T*>(self)->refCount.load(std::memory_order_acquire) == 1;
    };
    base->has_at_least_one_ref = [](cef_base_ref_counted_t* self) -> int {
        return reinterpret_cast<T*>(self)->refCount.load(std::memory_order_acquire) >= 1;
    };
}

// RAII wrapper for cef_string_t (stack-allocated, auto-cleared).
class ScopedCefString {
public:
    ScopedCefString() : str_{} {}

    explicit ScopedCefString(const char* utf8) : str_{} {
        cef_string_utf8_to_utf16(utf8, strlen(utf8), &str_);
    }

    explicit ScopedCefString(const std::string& utf8) : str_{} {
        cef_string_utf8_to_utf16(utf8.data(), utf8.size(), &str_);
    }

    // Take ownership of a userfree string.
    static ScopedCefString take(cef_string_userfree_t uf) {
        ScopedCefString s;
        if (uf) {
            s.str_ = *uf;
            // Zero out the source so cef_string_userfree_free won't double-free
            uf->str = nullptr;
            uf->length = 0;
            cef_string_userfree_free(uf);
        }
        return s;
    }

    ~ScopedCefString() { cef_string_clear(&str_); }

    ScopedCefString(const ScopedCefString&) = delete;
    ScopedCefString& operator=(const ScopedCefString&) = delete;
    ScopedCefString(ScopedCefString&& o) noexcept : str_(o.str_) { o.str_ = {}; }
    ScopedCefString& operator=(ScopedCefString&& o) noexcept {
        if (this != &o) { cef_string_clear(&str_); str_ = o.str_; o.str_ = {}; }
        return *this;
    }

    const cef_string_t* get() const { return &str_; }
    cef_string_t* get() { return &str_; }
    bool empty() const { return str_.length == 0; }

    bool equals(const cef_string_t* other) const {
        if (!other) return empty();
        return cef_string_utf16_cmp(&str_, other) == 0;
    }

    std::string toUtf8() const {
        if (empty()) return {};
        cef_string_utf8_t utf8{};
        cef_string_utf16_to_utf8(str_.str, str_.length, &utf8);
        std::string result(utf8.str, utf8.length);
        cef_string_utf8_clear(&utf8);
        return result;
    }

private:
    cef_string_t str_;
};

// RAII add_ref/release for CEF ref-counted pointers.
template<typename T>
class CefScopedPtr {
public:
    explicit CefScopedPtr(T* p = nullptr, bool addRef = false) : ptr_(p) {
        if (ptr_ && addRef) {
            auto* base = reinterpret_cast<cef_base_ref_counted_t*>(ptr_);
            base->add_ref(base);
        }
    }
    ~CefScopedPtr() {
        if (ptr_) {
            auto* base = reinterpret_cast<cef_base_ref_counted_t*>(ptr_);
            base->release(base);
        }
    }
    CefScopedPtr(const CefScopedPtr&) = delete;
    CefScopedPtr& operator=(const CefScopedPtr&) = delete;
    CefScopedPtr(CefScopedPtr&& o) noexcept : ptr_(o.ptr_) { o.ptr_ = nullptr; }

    T* get() const { return ptr_; }
    T* operator->() const { return ptr_; }
    explicit operator bool() const { return ptr_ != nullptr; }
    T* release() { auto* p = ptr_; ptr_ = nullptr; return p; }

private:
    T* ptr_;
};

// ---------------------------------------------------------------------------
// Per-frame context and handle registry.
//
// Each frame (identified by cef_frame_t::get_identifier) gets its own V8
// context and handle set. This allows multiple browsers to coexist in the
// same renderer process without interfering with each other.
//
// Raw cef_v8_value_t* pointers become dangling between IPC round-trips (CEF
// frees the C wrapper when its internal ref count hits 0, even though the JS
// value remains alive). To work around this, every handle is stored as a
// property on the global object (__cef4j_h_<id>). Lookups go through
// global.get_value_bykey(), which always returns a fresh, valid wrapper.
// ---------------------------------------------------------------------------

struct FrameState {
    cef_v8_context_t* context = nullptr;
    std::unordered_set<int> handles;

    ~FrameState() {
        if (context) {
            auto* base = reinterpret_cast<cef_base_ref_counted_t*>(context);
            base->release(base);
        }
    }
};

static std::unordered_map<std::string, FrameState> g_frames;
static std::atomic<int> g_nextHandle{1};
static cef_frame_t* g_currentFrame = nullptr;  // set during on_process_message_received

static std::string frameId(cef_frame_t* frame) {
    if (!frame) return {};
    auto id = ScopedCefString::take(frame->get_identifier(frame));
    return id.toUtf8();
}

static std::string handlePropName(int id) {
    return "__cef4j_h_" + std::to_string(id);
}

// Store a V8 value as a handle. Requires V8 context to be entered.
// Returns the handle ID. The value is stored on the global object.
static int storeHandle(cef_v8_context_t* ctx, cef_v8_value_t* value, const std::string& fid) {
    if (!value || !ctx) return -1;
    int id = g_nextHandle.fetch_add(1, std::memory_order_relaxed);
    auto* global = ctx->get_global(ctx);
    if (!global) return -1;
    ScopedCefString propStr(handlePropName(id));
    global->set_value_bykey(global, propStr.get(), value, static_cast<cef_v8_propertyattribute_t>(0));
    g_frames[fid].handles.insert(id);
    return id;
}

// Release a handle. Removes the global property and erases from the set.
// Requires V8 context to be entered.
static void releaseHandle(cef_v8_context_t* ctx, int id, const std::string& fid) {
    if (ctx) {
        auto* global = ctx->get_global(ctx);
        if (global) {
            std::string code = "delete " + handlePropName(id);
            ScopedCefString codeStr(code);
            cef_v8_value_t* unused = nullptr;
            cef_v8_exception_t* unused_exc = nullptr;
            ctx->eval(ctx, codeStr.get(), nullptr, 0, &unused, &unused_exc);
        }
    }
    auto it = g_frames.find(fid);
    if (it != g_frames.end()) {
        it->second.handles.erase(id);
    }
}

static void releaseAllHandlesForFrame(const std::string& fid) {
    auto it = g_frames.find(fid);
    if (it != g_frames.end()) {
        it->second.handles.clear();
    }
}

// ---------------------------------------------------------------------------
// V8 helpers
// ---------------------------------------------------------------------------

// Result type constants (must match Java CefScriptEvaluator.TYPE_*)
static constexpr int TYPE_JSON   = 0;
static constexpr int TYPE_HANDLE = 1;
static constexpr int TYPE_VOID   = 2;
static constexpr int TYPE_ERROR  = 3;

// JSON.stringify a V8 value by evaluating it in the current context.
// Returns the JSON string, or empty string on failure.
static std::string jsonStringify(cef_v8_context_t* ctx, cef_v8_value_t* value) {
    // Store value as a property on the global, stringify it, then remove it.
    auto* global = ctx->get_global(ctx);
    if (!global) return {};

    ScopedCefString propName("__cef4j_tmp");
    global->set_value_bykey(global, propName.get(), value, static_cast<cef_v8_propertyattribute_t>(0));

    ScopedCefString code("JSON.stringify(__cef4j_tmp)");
    cef_v8_value_t* retval = nullptr;
    cef_v8_exception_t* exc = nullptr;
    int ok = ctx->eval(ctx, code.get(), nullptr, 0, &retval, &exc);

    // Clean up temp property
    ScopedCefString undefinedCode("delete __cef4j_tmp");
    cef_v8_value_t* unused = nullptr;
    cef_v8_exception_t* unused_exc = nullptr;
    ctx->eval(ctx, undefinedCode.get(), nullptr, 0, &unused, &unused_exc);

    if (!ok || !retval) return {};
    if (!retval->is_string(retval)) return {};
    auto jsonStr = ScopedCefString::take(retval->get_string_value(retval));
    return jsonStr.toUtf8();
}

// Send a result message back to the browser process.
static void sendResult(cef_frame_t* frame, int reqId, bool ok, int type, const std::string& payload) {
    ScopedCefString msgName("cef4j:result");
    auto* msg = cef_process_message_create(msgName.get());
    if (!msg) return;

    auto* args = msg->get_argument_list(msg);
    if (!args) {
        auto* base = reinterpret_cast<cef_base_ref_counted_t*>(msg);
        base->release(base);
        return;
    }

    args->set_int(args, 0, reqId);
    args->set_bool(args, 1, ok ? 1 : 0);
    args->set_int(args, 2, type);
    ScopedCefString payloadStr(payload);
    args->set_string(args, 3, payloadStr.get());

    // send_process_message takes ownership - do NOT release msg after this
    frame->send_process_message(frame, PID_BROWSER, msg);
}

static void sendResultHandle(cef_frame_t* frame, int reqId, int handleId) {
    ScopedCefString msgName("cef4j:result");
    auto* msg = cef_process_message_create(msgName.get());
    if (!msg) return;

    auto* args = msg->get_argument_list(msg);
    if (!args) {
        auto* base = reinterpret_cast<cef_base_ref_counted_t*>(msg);
        base->release(base);
        return;
    }

    args->set_int(args, 0, reqId);
    args->set_bool(args, 1, 1);
    args->set_int(args, 2, TYPE_HANDLE);
    args->set_int(args, 3, handleId);

    // send_process_message takes ownership - do NOT release msg after this
    frame->send_process_message(frame, PID_BROWSER, msg);
}

static void sendError(cef_frame_t* frame, int reqId, const std::string& message) {
    sendResult(frame, reqId, false, TYPE_ERROR, message);
}

// Extract string from CefListValue at index (via get_value -> get_string_value).
static std::string listGetString(cef_list_value_t* list, size_t index) {
    auto* val = list->get_value(list, index);
    if (!val) return {};
    CefScopedPtr<cef_value_t> valGuard(val);
    auto str = ScopedCefString::take(val->get_string(val));
    return str.toUtf8();
}

// Evaluate expression or return V8 value depending on mode.
// mode: 0 = return JSON, 1 = return handle
static void evalAndReply(cef_frame_t* frame, cef_v8_context_t* ctx, int reqId,
                         const std::string& expression, int mode, const std::string& fid) {
    ScopedCefString code(expression);
    cef_v8_value_t* retval = nullptr;
    cef_v8_exception_t* exc = nullptr;
    int ok = ctx->eval(ctx, code.get(), nullptr, 0, &retval, &exc);

    if (!ok || !retval) {
        std::string errMsg = "evaluation failed";
        if (exc) {
            auto msg = ScopedCefString::take(exc->get_message(exc));
            errMsg = msg.toUtf8();
        }
        sendError(frame, reqId, errMsg);
        return;
    }

    if (mode == 1) {
        // Handle mode - store value, return handle ID
        int handleId = storeHandle(ctx, retval, fid);
        sendResultHandle(frame, reqId, handleId);
    } else {
        // JSON mode
        if (retval->is_undefined(retval) || retval->is_null(retval)) {
            sendResult(frame, reqId, true, TYPE_JSON, "null");
        } else {
            std::string json = jsonStringify(ctx, retval);
            if (json.empty()) {
                sendResult(frame, reqId, true, TYPE_JSON, "null");
            } else {
                sendResult(frame, reqId, true, TYPE_JSON, json);
            }
        }
    }
}

// ---------------------------------------------------------------------------
// Message handlers
// ---------------------------------------------------------------------------

// Acquire the V8 context for a given frame. Returns nullptr if not available.
// Caller must release when done (via CefScopedPtr or manual release).
static cef_v8_context_t* acquireContext(const std::string& fid) {
    auto it = g_frames.find(fid);
    if (it == g_frames.end() || !it->second.context) return nullptr;
    auto* ctx = it->second.context;
    auto* base = reinterpret_cast<cef_base_ref_counted_t*>(ctx);
    base->add_ref(base);
    return ctx;
}

static void handleEval(cef_frame_t* frame, cef_list_value_t* args) {
    int reqId = args->get_int(args, 0);
    std::string expression = listGetString(args, 1);
    int mode = args->get_int(args, 2);
    auto fid = frameId(frame);

    auto* ctx = acquireContext(fid);
    if (!ctx) {
        sendError(frame, reqId, "no V8 context available");
        return;
    }
    CefScopedPtr<cef_v8_context_t> ctxGuard(ctx);
    ctx->enter(ctx);
    evalAndReply(frame, ctx, reqId, expression, mode, fid);
    ctx->exit(ctx);
}

static void handleGet(cef_frame_t* frame, cef_list_value_t* args) {
    int reqId = args->get_int(args, 0);
    int handleId = args->get_int(args, 1);
    std::string key = listGetString(args, 2);
    int mode = args->get_int(args, 3);
    auto fid = frameId(frame);

    auto* ctx = acquireContext(fid);
    if (!ctx) {
        sendError(frame, reqId, "no V8 context available");
        return;
    }
    CefScopedPtr<cef_v8_context_t> ctxGuard(ctx);
    ctx->enter(ctx);

    auto fit = g_frames.find(fid);
    if (fit == g_frames.end() || fit->second.handles.find(handleId) == fit->second.handles.end()) {
        sendError(frame, reqId, "handle not found");
        ctx->exit(ctx);
        return;
    }

    // Use eval to get the property via the handle's global name
    std::string expr = handlePropName(handleId) + "['" + key + "']";
    evalAndReply(frame, ctx, reqId, expr, mode, fid);
    ctx->exit(ctx);
}

static void handleSet(cef_frame_t* frame, cef_list_value_t* args) {
    int reqId = args->get_int(args, 0);
    int handleId = args->get_int(args, 1);
    std::string key = listGetString(args, 2);
    std::string valueJson = listGetString(args, 3);
    auto fid = frameId(frame);

    auto* ctx = acquireContext(fid);
    if (!ctx) {
        sendError(frame, reqId, "no V8 context available");
        return;
    }
    CefScopedPtr<cef_v8_context_t> ctxGuard(ctx);
    ctx->enter(ctx);

    auto fit = g_frames.find(fid);
    if (fit == g_frames.end() || fit->second.handles.find(handleId) == fit->second.handles.end()) {
        sendError(frame, reqId, "handle not found");
        ctx->exit(ctx);
        return;
    }

    // Set via eval using the handle's global property name
    std::string expr = handlePropName(handleId) + "['" + key + "'] = (" + valueJson + ")";
    ScopedCefString code(expr);
    cef_v8_value_t* retval = nullptr;
    cef_v8_exception_t* exc = nullptr;
    int ok = ctx->eval(ctx, code.get(), nullptr, 0, &retval, &exc);

    if (!ok) {
        std::string errMsg = "set failed";
        if (exc) {
            auto msg = ScopedCefString::take(exc->get_message(exc));
            errMsg = msg.toUtf8();
        }
        sendError(frame, reqId, errMsg);
    } else {
        sendResult(frame, reqId, true, TYPE_VOID, "");
    }

    ctx->exit(ctx);
}

static void handleCall(cef_frame_t* frame, cef_list_value_t* args) {
    int reqId = args->get_int(args, 0);
    int handleId = args->get_int(args, 1);
    std::string method = listGetString(args, 2);
    std::string argsJson = listGetString(args, 3);
    int mode = args->get_int(args, 4);
    auto fid = frameId(frame);

    auto* ctx = acquireContext(fid);
    if (!ctx) {
        sendError(frame, reqId, "no V8 context available");
        return;
    }
    CefScopedPtr<cef_v8_context_t> ctxGuard(ctx);
    ctx->enter(ctx);

    auto fit = g_frames.find(fid);
    if (fit == g_frames.end() || fit->second.handles.find(handleId) == fit->second.handles.end()) {
        sendError(frame, reqId, "handle not found");
        ctx->exit(ctx);
        return;
    }

    // Use the handle's global property name directly in the eval expression.
    std::string prop = handlePropName(handleId);
    std::string callExpr = prop + "['" + method + "'].apply(" + prop + ", " + argsJson + ")";
    evalAndReply(frame, ctx, reqId, callExpr, mode, fid);

    ctx->exit(ctx);
}

static void handleInvoke(cef_frame_t* frame, cef_list_value_t* args) {
    int reqId = args->get_int(args, 0);
    int handleId = args->get_int(args, 1);
    std::string argsJson = listGetString(args, 2);
    int mode = args->get_int(args, 3);
    auto fid = frameId(frame);

    auto* ctx = acquireContext(fid);
    if (!ctx) {
        sendError(frame, reqId, "no V8 context available");
        return;
    }
    CefScopedPtr<cef_v8_context_t> ctxGuard(ctx);
    ctx->enter(ctx);

    auto fit = g_frames.find(fid);
    if (fit == g_frames.end() || fit->second.handles.find(handleId) == fit->second.handles.end()) {
        sendError(frame, reqId, "handle not found");
        ctx->exit(ctx);
        return;
    }

    // Invoke via eval using the handle's global property name.
    // The function is stored as __cef4j_h_N on the global object.
    std::string callExpr = handlePropName(handleId) + ".apply(null, " + argsJson + ")";
    evalAndReply(frame, ctx, reqId, callExpr, mode, fid);

    ctx->exit(ctx);
}

static void handleRelease(cef_frame_t* frame, cef_list_value_t* args) {
    int handleId = args->get_int(args, 0);
    auto fid = frameId(frame);
    auto* ctx = acquireContext(fid);
    if (ctx) {
        CefScopedPtr<cef_v8_context_t> ctxGuard(ctx);
        ctx->enter(ctx);
        releaseHandle(ctx, handleId, fid);
        ctx->exit(ctx);
    } else {
        // No context - just remove from set
        auto it = g_frames.find(fid);
        if (it != g_frames.end()) {
            it->second.handles.erase(handleId);
        }
    }
}

// ---------------------------------------------------------------------------
// Callback support - V8 functions that send IPC to the browser
// ---------------------------------------------------------------------------

// V8 function handler that fires a "cef4j:cb" message when JS calls the function.
struct CallbackHandler : public cef_v8_handler_t {
    std::atomic<int> refCount{1};
    int callbackId;

    explicit CallbackHandler(int cbId) : cef_v8_handler_t{}, callbackId(cbId) {
        InitSubprocessRefCount<CallbackHandler, cef_v8_handler_t>(
            reinterpret_cast<cef_base_ref_counted_t*>(this));
        execute = _execute;
    }

    static int CEF_CALLBACK _execute(
            cef_v8_handler_t* self,
            const cef_string_t* /*name*/,
            cef_v8_value_t* /*object*/,
            size_t argumentsCount,
            cef_v8_value_t* const* arguments,
            cef_v8_value_t** retval,
            cef_string_t* /*exception*/) {
        auto* handler = reinterpret_cast<CallbackHandler*>(self);

        // Use the frame from the current IPC handler (set in on_process_message_received).
        auto* frame = g_currentFrame;
        if (!frame) return 0;

        auto fid = frameId(frame);
        auto fit = g_frames.find(fid);
        cef_v8_context_t* ctx = (fit != g_frames.end()) ? fit->second.context : nullptr;

        // Store each argument as a V8 handle so Java can interact with them
        // via getProperty/call/release. This preserves live object references
        // rather than cloning via JSON serialization.
        // Message format: cef4j:cb [callbackId, numArgs, handle1, handle2, ...]
        ScopedCefString msgName("cef4j:cb");
        auto* msg = cef_process_message_create(msgName.get());
        if (msg) {
            auto* msgArgs = msg->get_argument_list(msg);
            if (msgArgs) {
                msgArgs->set_size(msgArgs, 2 + argumentsCount);
                msgArgs->set_int(msgArgs, 0, handler->callbackId);
                msgArgs->set_int(msgArgs, 1, static_cast<int>(argumentsCount));
                for (size_t i = 0; i < argumentsCount; i++) {
                    int handleId = -1;
                    if (ctx && arguments[i] && !arguments[i]->is_undefined(arguments[i])
                            && !arguments[i]->is_null(arguments[i])) {
                        handleId = storeHandle(ctx, arguments[i], fid);
                    }
                    msgArgs->set_int(msgArgs, 2 + i, handleId);
                }
                frame->send_process_message(frame, PID_BROWSER, msg);
            } else {
                auto* base = reinterpret_cast<cef_base_ref_counted_t*>(msg);
                base->release(base);
            }
        }

        // Return undefined to JS
        *retval = cef_v8_value_create_undefined();
        return 1;
    }
};

static void handleCreateCallback(cef_frame_t* frame, cef_list_value_t* args) {
    int reqId = args->get_int(args, 0);
    int callbackId = args->get_int(args, 1);
    auto fid = frameId(frame);

    auto* ctx = acquireContext(fid);
    if (!ctx) {
        sendError(frame, reqId, "no V8 context available");
        return;
    }
    CefScopedPtr<cef_v8_context_t> ctxGuard(ctx);
    ctx->enter(ctx);

    auto* handler = new CallbackHandler(callbackId);
    ScopedCefString fnName("__cef4j_cb_" + std::to_string(callbackId));
    auto* fn = cef_v8_value_create_function(fnName.get(), handler);
    // Don't release handler - cef_v8_value_create_function may not add_ref internally

    if (!fn) {
        auto* handlerBase = reinterpret_cast<cef_base_ref_counted_t*>(handler);
        handlerBase->release(handlerBase);
        sendError(frame, reqId, "failed to create V8 function");
        ctx->exit(ctx);
        return;
    }

    // storeHandle stores the value as a global property (__cef4j_h_N), keeping it alive
    int handleId = storeHandle(ctx, fn, fid);
    sendResultHandle(frame, reqId, handleId);
    ctx->exit(ctx);
}

// ---------------------------------------------------------------------------
// CefRenderProcessHandler
// ---------------------------------------------------------------------------

struct EvalHandler : public cef_render_process_handler_t {
    std::atomic<int> refCount{1};

    EvalHandler() : cef_render_process_handler_t{} {
        InitSubprocessRefCount<EvalHandler, cef_render_process_handler_t>(
            reinterpret_cast<cef_base_ref_counted_t*>(this));
        on_context_created = _on_context_created;
        on_context_released = _on_context_released;
        on_process_message_received = _on_process_message_received;
    }

    static void CEF_CALLBACK _on_context_created(
            cef_render_process_handler_t* /*self*/,
            cef_browser_t* /*browser*/,
            cef_frame_t* frame,
            cef_v8_context_t* context) {
        auto fid = frameId(frame);
        auto& state = g_frames[fid];
        // Release old context if present (e.g. navigation within the same frame)
        if (state.context) {
            auto* base = reinterpret_cast<cef_base_ref_counted_t*>(state.context);
            base->release(base);
        }
        state.context = context;
        if (context) {
            auto* base = reinterpret_cast<cef_base_ref_counted_t*>(context);
            base->add_ref(base);
        }
        state.handles.clear();
    }

    static void CEF_CALLBACK _on_context_released(
            cef_render_process_handler_t* /*self*/,
            cef_browser_t* /*browser*/,
            cef_frame_t* frame,
            cef_v8_context_t* /*context*/) {
        auto fid = frameId(frame);
        // Erase the entire frame state - handles become invalid with the context.
        g_frames.erase(fid);
    }

    static int CEF_CALLBACK _on_process_message_received(
            cef_render_process_handler_t* /*self*/,
            cef_browser_t* /*browser*/,
            cef_frame_t* frame,
            cef_process_id_t /*source_process*/,
            cef_process_message_t* message) {
        auto name = ScopedCefString::take(message->get_name(message));
        auto* args = message->get_argument_list(message);
        if (!args) return 0;

        // Make frame available to callback handlers during this message dispatch
        g_currentFrame = frame;

        ScopedCefString evalName("cef4j:eval");
        ScopedCefString getName("cef4j:get");
        ScopedCefString setName("cef4j:set");
        ScopedCefString callName("cef4j:call");
        ScopedCefString invokeName("cef4j:invoke");
        ScopedCefString releaseName("cef4j:release");
        ScopedCefString mkcbName("cef4j:mkcb");

        if (name.equals(evalName.get())) {
            handleEval(frame, args);
            return 1;
        }
        if (name.equals(getName.get())) {
            handleGet(frame, args);
            return 1;
        }
        if (name.equals(setName.get())) {
            handleSet(frame, args);
            return 1;
        }
        if (name.equals(callName.get())) {
            handleCall(frame, args);
            return 1;
        }
        if (name.equals(invokeName.get())) {
            handleInvoke(frame, args);
            return 1;
        }
        if (name.equals(releaseName.get())) {
            handleRelease(frame, args);
            return 1;
        }
        if (name.equals(mkcbName.get())) {
            handleCreateCallback(frame, args);
            return 1;
        }

        return 0;
    }
};

// ---------------------------------------------------------------------------
// CefApp (subprocess only - just returns the render process handler)
// ---------------------------------------------------------------------------

struct SubprocessApp : public cef_app_t {
    std::atomic<int> refCount{1};
    EvalHandler* handler;

    SubprocessApp() : cef_app_t{}, handler(new EvalHandler()) {
        InitSubprocessRefCount<SubprocessApp, cef_app_t>(
            reinterpret_cast<cef_base_ref_counted_t*>(this));
        get_render_process_handler = _get_render_process_handler;
        on_register_custom_schemes = _on_register_custom_schemes;
    }

    ~SubprocessApp() {
        if (handler) {
            auto* base = reinterpret_cast<cef_base_ref_counted_t*>(handler);
            base->release(base);
        }
    }

    static void CEF_CALLBACK _on_register_custom_schemes(
            cef_app_t* /*self*/, cef_scheme_registrar_t* registrar) {
        cef_string_t scheme = {};
        cef_string_utf8_to_utf16("classpath", 9, &scheme);
        // SECURE(8) | CORS_ENABLED(16) | FETCH_ENABLED(64) = 88
        registrar->add_custom_scheme(registrar, &scheme, 88);
        cef_string_clear(&scheme);
    }

    static cef_render_process_handler_t* CEF_CALLBACK _get_render_process_handler(
            cef_app_t* self) {
        auto* app = reinterpret_cast<SubprocessApp*>(self);
        if (app->handler) {
            auto* base = reinterpret_cast<cef_base_ref_counted_t*>(app->handler);
            base->add_ref(base);
        }
        return app->handler;
    }
};

// ---------------------------------------------------------------------------
// Entry point
// ---------------------------------------------------------------------------

#if defined(_WIN32)
#include <windows.h>
int WINAPI WinMain(HINSTANCE hInstance, HINSTANCE, LPSTR, int) {
    cef_api_hash(CEF_API_VERSION, 0);
    cef_main_args_t args{};
    args.instance = hInstance;
    SubprocessApp app;
    return cef_execute_process(&args, &app, nullptr);
}
#else
int main(int argc, char* argv[]) {
    cef_api_hash(CEF_API_VERSION, 0);
    cef_main_args_t args{};
    args.argc = argc;
    args.argv = argv;
    SubprocessApp app;
    return cef_execute_process(&args, &app, nullptr);
}
#endif
