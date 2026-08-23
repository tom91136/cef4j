#include "include/capi/cef_app_capi.h"
#include "include/capi/cef_render_process_handler_capi.h"
#include "include/capi/cef_scheme_capi.h"
#include "include/capi/cef_v8_capi.h"
#include "include/capi/cef_process_message_capi.h"
#include "include/capi/cef_frame_capi.h"
#include "cef_compat.h"

#ifdef __APPLE__
#include "include/wrapper/cef_library_loader.h"
extern "C" void cef4j_fix_main_bundle_id(void);
#endif

#include <atomic>
#include <cstdio>
#include <string>
#include <unordered_map>
#include <unordered_set>
#include <utility>
#include <vector>

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

class ScopedCefString {
public:
    ScopedCefString() : str_{} {}

    explicit ScopedCefString(const char* utf8) : str_{} {
        cef_string_utf8_to_utf16(utf8, strlen(utf8), &str_);
    }

    explicit ScopedCefString(const std::string& utf8) : str_{} {
        cef_string_utf8_to_utf16(utf8.data(), utf8.size(), &str_);
    }

    static ScopedCefString take(cef_string_userfree_t uf) {
        ScopedCefString s;
        if (uf) {
            s.str_ = *uf;
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

// XXX: CEF 109-150's generated CToCpp bridge adds a reference in Unwrap for CefRefPtr parameters and consumes it in
// Wrap on entry; mirror that boundary transfer until the relay uses CEF's C++ CefRefPtr API directly.
template<typename T>
T* addRefForTransfer(T* ptr) {
    if (ptr) {
        auto* base = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
        base->add_ref(base);
    }
    return ptr;
}

// XXX: CEF 109-150 invalidates raw cef_v8_value_t wrappers between IPC turns; keep values on each frame's global
// object and reacquire wrappers by key until the CEF 109-150 compatibility lanes are removed.

struct FrameState {
    cef4j_v8_context_t* context = nullptr;
    std::unordered_set<int> handles;
    std::unordered_map<int, cef4j_v8_handler_t*> callbacks;

    ~FrameState() {
        for (auto& callback : callbacks) {
            auto* base = reinterpret_cast<cef_base_ref_counted_t*>(callback.second);
            base->release(base);
        }
        if (context) {
            auto* base = reinterpret_cast<cef_base_ref_counted_t*>(context);
            base->release(base);
        }
    }
};

static std::unordered_map<std::string, FrameState> g_frames;
static std::atomic<int> g_nextHandle{1};

static std::string frameId(cef_frame_t* frame) {
    if (!frame) return {};
#if CEF_VERSION_MAJOR < 133
    return std::to_string(frame->get_identifier(frame));
#else
    auto id = ScopedCefString::take(frame->get_identifier(frame));
    return id.toUtf8();
#endif
}

static std::string handlePropName(int id) {
    return "__cef4j_h_" + std::to_string(id);
}

static cef4j_v8_value_t* frameGlobal(cef4j_v8_context_t* ctx, const std::string& fid) {
    return g_frames.find(fid) == g_frames.end() ? nullptr : ctx->get_global(ctx);
}

static int storeHandle(cef4j_v8_context_t* ctx, cef4j_v8_value_t* value, const std::string& fid) {
    if (!value || !ctx) return -1;
    int id = g_nextHandle.fetch_add(1, std::memory_order_relaxed);
    CefScopedPtr<cef4j_v8_value_t> global(frameGlobal(ctx, fid));
    if (!global) return -1;
    ScopedCefString propStr(handlePropName(id));
    global->set_value_bykey(
            global.get(), propStr.get(), addRefForTransfer(value), static_cast<cef_v8_propertyattribute_t>(0));
    g_frames[fid].handles.insert(id);
    return id;
}

static void releaseHandle(cef4j_v8_context_t* ctx, int id, const std::string& fid) {
    if (ctx) {
        CefScopedPtr<cef4j_v8_value_t> global(frameGlobal(ctx, fid));
        if (global) {
            ScopedCefString key(handlePropName(id));
            global->delete_value_bykey(global.get(), key.get());
        }
    }
    auto it = g_frames.find(fid);
    if (it != g_frames.end()) {
        it->second.handles.erase(id);
        auto callback = it->second.callbacks.find(id);
        if (callback != it->second.callbacks.end()) {
            auto* base = reinterpret_cast<cef_base_ref_counted_t*>(callback->second);
            base->release(base);
            it->second.callbacks.erase(callback);
        }
    }
}

static constexpr int TYPE_JSON   = 0;
static constexpr int TYPE_HANDLE = 1;
static constexpr int TYPE_VOID   = 2;
static constexpr int TYPE_ERROR  = 3;

static std::string jsonStringify(
        cef4j_v8_context_t* ctx, cef4j_v8_value_t* value, const std::string& fid) {
    CefScopedPtr<cef4j_v8_value_t> global(frameGlobal(ctx, fid));
    if (!global) return {};

    ScopedCefString propName("__cef4j_tmp");
    global->set_value_bykey(
            global.get(), propName.get(), addRefForTransfer(value), static_cast<cef_v8_propertyattribute_t>(0));

    ScopedCefString code("JSON.stringify(__cef4j_tmp)");
    cef4j_v8_value_t* retval = nullptr;
    cef4j_v8_exception_t* exc = nullptr;
    int ok = ctx->eval(ctx, code.get(), nullptr, 0, &retval, &exc);
    CefScopedPtr<cef4j_v8_value_t> retvalGuard(retval);
    CefScopedPtr<cef4j_v8_exception_t> exceptionGuard(exc);
    ScopedCefString deleteCode("delete __cef4j_tmp");
    cef4j_v8_value_t* unused = nullptr;
    cef4j_v8_exception_t* unusedException = nullptr;
    ctx->eval(ctx, deleteCode.get(), nullptr, 0, &unused, &unusedException);
    CefScopedPtr<cef4j_v8_value_t> unusedGuard(unused);
    CefScopedPtr<cef4j_v8_exception_t> unusedExceptionGuard(unusedException);

    if (!ok || !retval) return {};
    if (!retval->is_string(retval)) return {};
    auto jsonStr = ScopedCefString::take(retval->get_string_value(retval));
    return jsonStr.toUtf8();
}

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

    // XXX: CEF 109-150 invalidates the message reference after send_process_message; remove only if a future CEF API
    // explicitly changes that ownership contract.
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

    // XXX: CEF 109-150 invalidates the message reference after send_process_message; remove only if a future CEF API
    // explicitly changes that ownership contract.
    frame->send_process_message(frame, PID_BROWSER, msg);
}

static void sendError(cef_frame_t* frame, int reqId, const std::string& message) {
    sendResult(frame, reqId, false, TYPE_ERROR, message);
}

static std::string listGetString(cef_list_value_t* list, size_t index) {
    auto* val = list->get_value(list, index);
    if (!val) return {};
    CefScopedPtr<cef_value_t> valGuard(val);
    auto str = ScopedCefString::take(val->get_string(val));
    return str.toUtf8();
}

static void replyWithValue(cef_frame_t* frame, cef4j_v8_context_t* ctx, int reqId,
                           cef4j_v8_value_t* retval, int mode, const std::string& fid) {
    if (mode == 1) {
        int handleId = storeHandle(ctx, retval, fid);
        sendResultHandle(frame, reqId, handleId);
    } else {
        if (retval->is_undefined(retval) || retval->is_null(retval)) {
            sendResult(frame, reqId, true, TYPE_JSON, "null");
        } else {
            std::string json = jsonStringify(ctx, retval, fid);
            if (json.empty()) {
                sendResult(frame, reqId, true, TYPE_JSON, "null");
            } else {
                sendResult(frame, reqId, true, TYPE_JSON, json);
            }
        }
    }
}

static CefScopedPtr<cef4j_v8_value_t> evaluateValue(
        cef4j_v8_context_t* ctx, const std::string& expression, std::string& error) {
    ScopedCefString code(expression);
    cef4j_v8_value_t* retval = nullptr;
    cef4j_v8_exception_t* exc = nullptr;
    int ok = ctx->eval(ctx, code.get(), nullptr, 0, &retval, &exc);
    CefScopedPtr<cef4j_v8_exception_t> exceptionGuard(exc);
    if (!ok || !retval) {
        error = "evaluation failed";
        if (exc) {
            auto message = ScopedCefString::take(exc->get_message(exc));
            error = message.toUtf8();
        }
    }
    return CefScopedPtr<cef4j_v8_value_t>(retval);
}

static void evalAndReply(cef_frame_t* frame, cef4j_v8_context_t* ctx, int reqId,
                         const std::string& expression, int mode, const std::string& fid) {
    ScopedCefString code(expression);
    cef4j_v8_value_t* retval = nullptr;
    cef4j_v8_exception_t* exception = nullptr;
    int ok = ctx->eval(ctx, code.get(), nullptr, 0, &retval, &exception);
    CefScopedPtr<cef4j_v8_value_t> retvalGuard(retval);
    CefScopedPtr<cef4j_v8_exception_t> exceptionGuard(exception);
    if (!ok || !retval) {
        std::string error = "evaluation failed";
        if (exception) {
            auto message = ScopedCefString::take(exception->get_message(exception));
            error = message.toUtf8();
        }
        sendError(frame, reqId, error);
        return;
    }
    replyWithValue(frame, ctx, reqId, retval, mode, fid);
}

static cef4j_v8_context_t* acquireContext(const std::string& fid) {
    auto it = g_frames.find(fid);
    if (it == g_frames.end() || !it->second.context) return nullptr;
    auto* ctx = it->second.context;
    auto* base = reinterpret_cast<cef_base_ref_counted_t*>(ctx);
    base->add_ref(base);
    return ctx;
}

class EnteredV8Context {
public:
    explicit EnteredV8Context(cef4j_v8_context_t* context)
        : context_(context), entered_(context && context->enter(context)) {}

    ~EnteredV8Context() {
        if (entered_) context_->exit(context_);
    }

    explicit operator bool() const { return entered_; }

private:
    cef4j_v8_context_t* context_;
    bool entered_;
};

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
    CefScopedPtr<cef4j_v8_context_t> ctxGuard(ctx);
    EnteredV8Context entered(ctx);
    if (!entered) {
        sendError(frame, reqId, "failed to enter V8 context");
        return;
    }
    evalAndReply(frame, ctx, reqId, expression, mode, fid);
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
    CefScopedPtr<cef4j_v8_context_t> ctxGuard(ctx);
    EnteredV8Context entered(ctx);
    if (!entered) {
        sendError(frame, reqId, "failed to enter V8 context");
        return;
    }

    auto fit = g_frames.find(fid);
    if (fit == g_frames.end() || fit->second.handles.find(handleId) == fit->second.handles.end()) {
        sendError(frame, reqId, "handle not found");
        return;
    }

    CefScopedPtr<cef4j_v8_value_t> global(frameGlobal(ctx, fid));
    if (!global) {
        sendError(frame, reqId, "global object unavailable");
        return;
    }
    ScopedCefString handleKey(handlePropName(handleId));
    CefScopedPtr<cef4j_v8_value_t> receiver(global->get_value_bykey(global.get(), handleKey.get()));
    if (!receiver) {
        sendError(frame, reqId, "handle value unavailable");
        return;
    }
    ScopedCefString propertyKey(key);
    CefScopedPtr<cef4j_v8_value_t> value(receiver->get_value_bykey(receiver.get(), propertyKey.get()));
    if (value) {
        replyWithValue(frame, ctx, reqId, value.get(), mode, fid);
    } else {
        sendError(frame, reqId, "property read failed");
    }
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
    CefScopedPtr<cef4j_v8_context_t> ctxGuard(ctx);
    EnteredV8Context entered(ctx);
    if (!entered) {
        sendError(frame, reqId, "failed to enter V8 context");
        return;
    }

    auto fit = g_frames.find(fid);
    if (fit == g_frames.end() || fit->second.handles.find(handleId) == fit->second.handles.end()) {
        sendError(frame, reqId, "handle not found");
        return;
    }

    std::string error;
    auto value = evaluateValue(ctx, "(" + valueJson + ")", error);
    CefScopedPtr<cef4j_v8_value_t> global(frameGlobal(ctx, fid));
    if (!global) {
        sendError(frame, reqId, "global object unavailable");
        return;
    }
    ScopedCefString handleKey(handlePropName(handleId));
    CefScopedPtr<cef4j_v8_value_t> receiver(global->get_value_bykey(global.get(), handleKey.get()));
    ScopedCefString propertyKey(key);
    if (!value) {
        sendError(frame, reqId, error);
    } else if (!receiver) {
        sendError(frame, reqId, "handle value unavailable");
    } else if (!receiver->set_value_bykey(
                       receiver.get(),
                       propertyKey.get(),
                       addRefForTransfer(value.get()),
                       static_cast<cef_v8_propertyattribute_t>(0))) {
        sendError(frame, reqId, "property write failed");
    } else {
        sendResult(frame, reqId, true, TYPE_VOID, "");
    }
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
    CefScopedPtr<cef4j_v8_context_t> ctxGuard(ctx);
    EnteredV8Context entered(ctx);
    if (!entered) {
        sendError(frame, reqId, "failed to enter V8 context");
        return;
    }

    auto fit = g_frames.find(fid);
    if (fit == g_frames.end() || fit->second.handles.find(handleId) == fit->second.handles.end()) {
        sendError(frame, reqId, "handle not found");
        return;
    }

    CefScopedPtr<cef4j_v8_value_t> global(frameGlobal(ctx, fid));
    if (!global) {
        sendError(frame, reqId, "global object unavailable");
        return;
    }
    ScopedCefString handleKey(handlePropName(handleId));
    CefScopedPtr<cef4j_v8_value_t> receiver(global->get_value_bykey(global.get(), handleKey.get()));
    if (!receiver) {
        sendError(frame, reqId, "handle value unavailable");
        return;
    }
    ScopedCefString methodKey(method);
    CefScopedPtr<cef4j_v8_value_t> function(
            receiver->get_value_bykey(receiver.get(), methodKey.get()));
    std::string error;
    auto argumentArray = evaluateValue(ctx, "(" + argsJson + ")", error);
    if (!argumentArray) {
        sendError(frame, reqId, error);
        return;
    }
    int argumentCount = argumentArray->get_array_length(argumentArray.get());
    if (argumentCount < 0) {
        sendError(frame, reqId, "arguments must be a JSON array");
        return;
    }
    std::vector<CefScopedPtr<cef4j_v8_value_t>> argumentGuards;
    std::vector<cef4j_v8_value_t*> arguments;
    argumentGuards.reserve(static_cast<size_t>(argumentCount));
    arguments.reserve(static_cast<size_t>(argumentCount));
    for (int i = 0; i < argumentCount; ++i) {
        argumentGuards.emplace_back(argumentArray->get_value_byindex(argumentArray.get(), i));
        arguments.push_back(addRefForTransfer(argumentGuards.back().get()));
    }
    CefScopedPtr<cef4j_v8_value_t> retval(function
            ? function->execute_function(
                      function.get(),
                      addRefForTransfer(receiver.get()),
                      arguments.size(),
                      arguments.data())
            : nullptr);
    if (retval) {
        replyWithValue(frame, ctx, reqId, retval.get(), mode, fid);
    } else {
        sendError(frame, reqId, "method invocation failed");
    }
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
    CefScopedPtr<cef4j_v8_context_t> ctxGuard(ctx);
    EnteredV8Context entered(ctx);
    if (!entered) {
        sendError(frame, reqId, "failed to enter V8 context");
        return;
    }

    auto fit = g_frames.find(fid);
    if (fit == g_frames.end() || fit->second.handles.find(handleId) == fit->second.handles.end()) {
        sendError(frame, reqId, "handle not found");
        return;
    }

    std::string callExpr = handlePropName(handleId) + ".apply(null, " + argsJson + ")";
    evalAndReply(frame, ctx, reqId, callExpr, mode, fid);
}

static void handleRelease(cef_frame_t* frame, cef_list_value_t* args) {
    int handleId = args->get_int(args, 0);
    auto fid = frameId(frame);
    auto* ctx = acquireContext(fid);
    if (ctx) {
        CefScopedPtr<cef4j_v8_context_t> ctxGuard(ctx);
        EnteredV8Context entered(ctx);
        if (entered) releaseHandle(ctx, handleId, fid);
    } else {
        auto it = g_frames.find(fid);
        if (it != g_frames.end()) {
            it->second.handles.erase(handleId);
        }
    }
}

struct CallbackHandler : public cef4j_v8_handler_t {
    std::atomic<int> refCount{1};
    int callbackId;
    cef_frame_t* frame;
    std::string frameIdentifier;

    CallbackHandler(int cbId, cef_frame_t* creatingFrame, std::string fid)
        : cef4j_v8_handler_t{}, callbackId(cbId), frame(creatingFrame), frameIdentifier(std::move(fid)) {
        InitSubprocessRefCount<CallbackHandler, cef4j_v8_handler_t>(
            reinterpret_cast<cef_base_ref_counted_t*>(this));
        if (frame) {
            auto* base = reinterpret_cast<cef_base_ref_counted_t*>(frame);
            base->add_ref(base);
        }
        execute = _execute;
    }

    ~CallbackHandler() {
        if (frame) {
            auto* base = reinterpret_cast<cef_base_ref_counted_t*>(frame);
            base->release(base);
        }
    }

    static int CEF_CALLBACK _execute(
            cef4j_v8_handler_t* self,
            const cef_string_t* /*name*/,
            cef4j_v8_value_t* /*object*/,
            size_t argumentsCount,
            cef4j_v8_value_t* const* arguments,
            cef4j_v8_value_t** retval,
            cef_string_t* /*exception*/) {
        auto* handler = reinterpret_cast<CallbackHandler*>(self);

        auto* frame = handler->frame;
        if (!frame) return 0;

        auto fit = g_frames.find(handler->frameIdentifier);
        if (fit == g_frames.end()) return 0;
        cef4j_v8_context_t* ctx = fit->second.context;

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
                        handleId = storeHandle(ctx, arguments[i], handler->frameIdentifier);
                    }
                    msgArgs->set_int(msgArgs, 2 + i, handleId);
                }
                frame->send_process_message(frame, PID_BROWSER, msg);
            } else {
                auto* base = reinterpret_cast<cef_base_ref_counted_t*>(msg);
                base->release(base);
            }
        }

        *retval = cef4j_v8_create_undefined();
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
    CefScopedPtr<cef4j_v8_context_t> ctxGuard(ctx);
    EnteredV8Context entered(ctx);
    if (!entered) {
        sendError(frame, reqId, "failed to enter V8 context");
        return;
    }

    auto* handler = new CallbackHandler(callbackId, frame, fid);
    ScopedCefString fnName("__cef4j_cb_" + std::to_string(callbackId));
    CefScopedPtr<cef4j_v8_value_t> fn(
            cef4j_v8_create_function(fnName.get(), addRefForTransfer(handler)));

    if (!fn) {
        auto* base = reinterpret_cast<cef_base_ref_counted_t*>(handler);
        base->release(base);
        sendError(frame, reqId, "failed to create V8 function");
        return;
    }

    int handleId = storeHandle(ctx, fn.get(), fid);
    if (handleId < 0) {
        auto* base = reinterpret_cast<cef_base_ref_counted_t*>(handler);
        base->release(base);
        sendError(frame, reqId, "failed to store V8 function");
    } else {
        g_frames[fid].callbacks.emplace(handleId, handler);
        sendResultHandle(frame, reqId, handleId);
    }
}


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
            cef4j_v8_context_t* context) {
        auto fid = frameId(frame);
        auto& state = g_frames[fid];
        for (auto& callback : state.callbacks) {
            auto* base = reinterpret_cast<cef_base_ref_counted_t*>(callback.second);
            base->release(base);
        }
        state.callbacks.clear();
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
            cef4j_v8_context_t* /*context*/) {
        auto fid = frameId(frame);
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
        registrar->add_custom_scheme(
                registrar,
                &scheme,
                CEF_SCHEME_OPTION_SECURE | CEF_SCHEME_OPTION_CORS_ENABLED | CEF_SCHEME_OPTION_FETCH_ENABLED);
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

static bool verifyApiHash() {
    if (cef4j_verify_api_hash()) return true;
    fprintf(stderr, "cef4j_launcher: CEF API hash mismatch (expected %s, actual %s)\n",
            CEF_API_HASH_PLATFORM,
            cef4j_runtime_api_hash() ? cef4j_runtime_api_hash() : "<null>");
    return false;
}


#if defined(_WIN32)
#include <windows.h>
int WINAPI WinMain(HINSTANCE hInstance, HINSTANCE, LPSTR, int) {
    if (!verifyApiHash()) return 1;
    cef_main_args_t args{};
    args.instance = hInstance;
    auto* app = new SubprocessApp();
    return cef_execute_process(&args, app, nullptr);
}
#else
int main(int argc, char* argv[]) {
#ifdef __APPLE__
    // XXX: CEF 109-150 derives macOS rendezvous names before library load; keep browser/helper bundle IDs aligned
    // until the CEF 109-150 compatibility lanes are removed.
    cef4j_fix_main_bundle_id();

    {
        const std::string prefix = "--framework-dir-path=";
        for (int i = 1; i < argc; i++) {
            std::string arg = argv[i];
            if (arg.rfind(prefix, 0) == 0) {
                std::string frameworkBinary = arg.substr(prefix.size()) + "/Chromium Embedded Framework";
                if (!cef_load_library(frameworkBinary.c_str())) {
                    fprintf(stderr, "[cef4j_launcher] Failed to load CEF framework: %s\n", frameworkBinary.c_str());
                    return 1;
                }
                break;
            }
        }
    }
#endif
    if (!verifyApiHash()) return 1;
    cef_main_args_t args{};
    args.argc = argc;
    args.argv = argv;
    auto* app = new SubprocessApp();
    int exitCode = cef_execute_process(&args, app, nullptr);
#ifdef __APPLE__
    cef_unload_library();
#endif
    return exitCode;
}
#endif
