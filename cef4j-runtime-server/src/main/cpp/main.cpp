// cef4j-runtime-server: transport-selectable host for the generated Remote CEF API.
//
// Multi-process: the same binary is re-execed by CEF for renderer/GPU/utility
// subprocesses. cef_execute_process at entry routes them; non-zero return
// means we were a sub-process and should exit. Otherwise we are the browser
// process and run the CEF message loop.
//
// Hand-written code is now minimal — most of the protocol comes from codegen:
//   - gen/Dispatcher.h dispatches ~685 AST-derived requests onto CEF UI-thread
//     calls, manages per-struct HandleTable<T> globals, routes ReleaseHandle.
//   - gen/HandlerForwarders.h provides 33 cef_X_handler_t subclasses; wireClient
//     binds each to the appropriate cef_client_t::get_X_handler.
//
// The remaining hand-written code in this file is just:
//   - ScopedCefString helper (RAII for cef_string_t)
//   - RenderHandler stub (view-rect bounds + on_paint no-op; OSR contract that
//     can't ride the event-only forwarder pattern)
//   - CefClient + App lifecycle and the kMsgReleaseHandle dispatch case.
//
#include <atomic>
#include <chrono>
#include <cstdint>
#include <cstdio>
#include <cstring>
#include <functional>
#include <filesystem>
#include <iostream>
#include <memory>
#include <mutex>
#include <string>
#include <thread>
#include <unordered_map>
#include <vector>
#ifdef _WIN32
#include <process.h>
#include <windows.h>
#else
#include <unistd.h>
#endif

#include "include/capi/cef_app_capi.h"
#include "include/capi/cef_browser_capi.h"
#include "include/capi/cef_client_capi.h"
#include "include/capi/cef_devtools_message_observer_capi.h"
#include "include/capi/cef_life_span_handler_capi.h"
#include "include/capi/cef_load_handler_capi.h"
#include "include/capi/cef_process_message_capi.h"
#include "include/capi/cef_render_handler_capi.h"
#include "include/capi/cef_render_process_handler_capi.h"
#include "include/capi/cef_task_capi.h"
#include "include/capi/cef_v8_capi.h"
#include "include/capi/cef_values_capi.h"
#include "include/cef_api_hash.h"
#include "include/internal/cef_types.h"
#ifdef __APPLE__
#include "include/wrapper/cef_library_loader.h"
#endif

#include "Envelope.h"
#include "CefCompat.h"
#include "HandleTable.h"
#include "InterceptRegistry.h"
#include "IpcServer.h"
#include "OsrPaintBuffer.h"
#include "gen/InlinePaintEvent.h"
#include "gen/CreateBrowserRequest.h"
#include "gen/DevToolsAgentDetachedEvent.h"
#include "gen/DevToolsAttachRequest.h"
#include "gen/DevToolsAttachResponse.h"
#include "gen/DevToolsDetachRequest.h"
#include "gen/DevToolsDetachResponse.h"
#include "gen/DevToolsMessageEvent.h"
#include "gen/EvaluateJavascriptRequest.h"
#include "gen/EvaluateJavascriptResponse.h"
#include "gen/JsFunctionCallEvent.h"
#include "gen/RegisterJsFunctionRequest.h"
#include "gen/RegisterJsFunctionResponse.h"
#include "gen/V8ExecuteFunctionRequest.h"
#include "gen/V8ExecuteFunctionResponse.h"
#include "gen/V8GetArrayLengthRequest.h"
#include "gen/V8GetArrayLengthResponse.h"
#include "gen/V8GetKeysRequest.h"
#include "gen/V8GetKeysResponse.h"
#include "gen/V8GetPropertyRequest.h"
#include "gen/V8GetPropertyResponse.h"
#include "gen/V8GetStringValueRequest.h"
#include "gen/V8GetStringValueResponse.h"
#include "gen/V8GetValueByIndexRequest.h"
#include "gen/V8GetValueByIndexResponse.h"
#include "gen/V8HasPropertyRequest.h"
#include "gen/V8HasPropertyResponse.h"
#include "gen/V8ReleaseHandleRequest.h"
#include "gen/V8ReleaseHandleResponse.h"
#include "gen/V8SetPropertyRequest.h"
#include "gen/V8SetPropertyResponse.h"
#include "gen/OsrPaintEvent.h"
#include "gen/ReleaseHandleRequest.h"
#include "gen/RendererReleaseHandleRequest.h"
#include "gen/RendererReleaseHandleResponse.h"
#include "gen/SetViewportSizeRequest.h"
#include "gen/SetViewportSizeResponse.h"
#include "gen/TriggerInterceptRequest.h"
#include "gen/TriggerInterceptResponse.h"
#include "gen/V8ContextCreatedEvent.h"

// Generated AST-driven dispatcher; covers hundreds of ObjectStruct methods across all CEF facades. Hand-written
// cases below take precedence; gendisp::dispatch is consulted afterwards for ids the hand-written switch
// doesn't claim. The dispatcher namespace also holds the per-struct HandleTable<T> static globals.
#include "gen/Dispatcher.h"

// Generated AST-driven handler forwarders. One C++ class per CEF HandlerStruct; each callback encodes the
// matching AST event and sends it via gendisp::tables + IpcServer. The server hands them out from CefClient
// instead of writing per-handler boilerplate.
#include "gen/HandlerForwarders.h"

// Generated AST-driven renderer-side dispatcher. Mirrors gen/Dispatcher.h but scoped to renderer-affinity
// facades (cef_v8_*, cef_dom*); the renderer subprocess calls into it on receipt of "cef4j_renderer_req"
// process_messages relayed by the browser-side dispatcher.
#include "gen/RendererDispatcher.h"

namespace gendisp     = net_kurobako_cef4j_ipc_protocol_gen_dispatcher;
namespace genrender   = net_kurobako_cef4j_ipc_protocol_gen_renderer_dispatcher;
namespace genhandlers = net_kurobako_cef4j_ipc_protocol_gen_handlers;

using cef4j::ipc::IpcServer;
using cef4j::ipc::Header;
using cef4j::ipc::Kind;
using cef4j::ipc::kHeaderSize;
using cef4j::ipc::kNoCorrId;

// Mirrors hand-written specs in cef4j-codegen/src/main/scala/.../ipc/Specs.scala. IDs in [0, AstIdBase) are
// hand-allocated; codegen-derived AST ids start at AstIdBase=10000.
static constexpr std::int32_t kMsgSessionReady         = 0;
static constexpr std::int32_t kMsgReleaseHandle        = 6;
static constexpr std::int32_t kMsgCreateBrowser        = 7;
static constexpr std::int32_t kMsgTriggerIntercept     = 8;
static constexpr std::int32_t kMsgSetViewportSize      = 25;
static constexpr std::int32_t kMsgDevToolsAttach       = 27;
static constexpr std::int32_t kMsgDevToolsDetach       = 30;

// ---------------------------------------------------------------------------
// CEF ref-counted base helpers (same pattern as cef4j-native subprocess_main).
// ---------------------------------------------------------------------------

// CEF validates {@code base.size == sizeof(cef_*_t)} on every wrap. We must report the parent CEF struct size, not
// our subclass size. T is our wrapper class (carries refCount); CefStruct is the cef_*_t we're implementing.
template <typename T, typename CefStruct>
static void initRef(cef_base_ref_counted_t* base) {
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
    ScopedCefString() : s_{} {}
    explicit ScopedCefString(const std::string& utf8) : s_{} {
        cef_string_utf8_to_utf16(utf8.data(), utf8.size(), &s_);
    }
    static ScopedCefString take(cef_string_userfree_t uf) {
        ScopedCefString s;
        if (uf) {
            s.s_ = *uf;
            uf->str = nullptr;
            uf->length = 0;
            cef_string_userfree_free(uf);
        }
        return s;
    }
    ~ScopedCefString() { cef_string_clear(&s_); }
    ScopedCefString(const ScopedCefString&) = delete;
    ScopedCefString& operator=(const ScopedCefString&) = delete;
    ScopedCefString(ScopedCefString&& o) noexcept : s_(o.s_) { o.s_ = {}; }
    cef_string_t* get() { return &s_; }
    const cef_string_t* get() const { return &s_; }
    std::string toUtf8() const {
        if (s_.length == 0) return {};
        cef_string_utf8_t utf8{};
        cef_string_utf16_to_utf8(s_.str, s_.length, &utf8);
        std::string r(utf8.str, utf8.length);
        cef_string_utf8_clear(&utf8);
        return r;
    }

private:
    cef_string_t s_;
};

// ---------------------------------------------------------------------------
// Globals (browser-process only, accessed only on CEF UI thread unless noted).
// ---------------------------------------------------------------------------

static IpcServer* g_ipc       = nullptr;     // accessed from many threads after start
static cef_client_t* g_client = nullptr;     // shared by the initial browser + any JVM-triggered creates
// Server→client sync callback waiter; lives in InterceptRegistry.h's process-wide singleton so generated
// handler forwarders share the same waiter map without needing an extern.
inline cef4j::ipc::InterceptRegistry& g_intercepts = cef4j::ipc::intercepts();

// Browser lifetime tracking exists independently of the generated handle table. A client can disappear without
// explicitly releasing facade handles, but the owning RuntimeServerProcess must still be able to close every browser
// and let Chromium reap its subprocesses before cef_shutdown(). Mutations happen on CEF's UI thread.
static std::unordered_map<int, cef_browser_t*> g_liveBrowsers;
static bool g_runtimeShuttingDown = false;
static bool g_runtimeQuitPosted = false;
static decltype(genhandlers::g_lifeSpanHandlerForwarder.on_after_created) g_forwardOnAfterCreated = nullptr;
static decltype(genhandlers::g_lifeSpanHandlerForwarder.do_close) g_forwardDoClose = nullptr;
static decltype(genhandlers::g_lifeSpanHandlerForwarder.on_before_close) g_forwardOnBeforeClose = nullptr;

static void trackBrowser(cef_browser_t* browser) {
    if (!browser) return;
    int id = browser->get_identifier(browser);
    g_liveBrowsers.emplace(id, browser);
}

static bool untrackBrowser(cef_browser_t* browser) {
    if (!browser) return false;
    int id = browser->get_identifier(browser);
    auto it = g_liveBrowsers.find(id);
    if (it == g_liveBrowsers.end()) return false;
    g_liveBrowsers.erase(it);
    return true;
}

static void releaseAllDevToolsRegistrations();
static void finishRuntimeShutdown();

static void closeTrackedBrowsers() {
    std::vector<cef_browser_t*> snapshot;
    snapshot.reserve(g_liveBrowsers.size());
    for (const auto& entry : g_liveBrowsers) {
        snapshot.push_back(entry.second);
    }
    if (snapshot.empty()) {
        finishRuntimeShutdown();
        return;
    }
    for (auto* browser : snapshot) {
        auto* host = browser->get_host(browser);
        if (host) {
            host->close_browser(host, 1);
            auto* hostBase = reinterpret_cast<cef_base_ref_counted_t*>(host);
            hostBase->release(hostBase);
        }
    }
}

static void beginRuntimeShutdown() {
    if (g_runtimeShuttingDown) return;
    g_runtimeShuttingDown = true;
    std::fprintf(stderr, "[cef4j-runtime-server] shutdown: closing %zu browser(s)\n", g_liveBrowsers.size());
    closeTrackedBrowsers();
}

static void installLifeSpanHooks() {
    auto& handler = genhandlers::g_lifeSpanHandlerForwarder;
    g_forwardOnAfterCreated = handler.on_after_created;
    g_forwardDoClose = handler.do_close;
    g_forwardOnBeforeClose = handler.on_before_close;
    handler.on_after_created = [](cef_life_span_handler_t* self, cef_browser_t* browser) {
        trackBrowser(browser);
        if (g_runtimeShuttingDown) {
            auto* host = browser ? browser->get_host(browser) : nullptr;
            if (host) {
                host->close_browser(host, 1);
                auto* hostBase = reinterpret_cast<cef_base_ref_counted_t*>(host);
                hostBase->release(hostBase);
            }
            return;
        }
        g_forwardOnAfterCreated(self, browser);
    };
    handler.do_close = [](cef_life_span_handler_t* self, cef_browser_t* browser) -> int {
        if (g_runtimeShuttingDown) return 0;
        return g_forwardDoClose(self, browser);
    };
    handler.on_before_close = [](cef_life_span_handler_t* self, cef_browser_t* browser) {
        if (!g_runtimeShuttingDown) g_forwardOnBeforeClose(self, browser);
        bool removed = untrackBrowser(browser);
        if (g_runtimeShuttingDown && removed && g_liveBrowsers.empty() && !g_runtimeQuitPosted) {
            g_runtimeQuitPosted = true;
            std::fprintf(stderr, "[cef4j-runtime-server] shutdown: final browser closed\n");
            // CEF invokes OnBeforeClose on TID_UI and explicitly permits CefQuitMessageLoop from the final callback.
            // An additional posted task can be stranded when this callback is the last runnable item in the loop.
            finishRuntimeShutdown();
        }
    };
}

static void releaseTrackedBrowsers() {
    g_liveBrowsers.clear();
}

// Per-type handle registries live in the codegen'd dispatcher namespace (`gendisp::tables::browser`,
// `gendisp::tables::frame`, etc.). The server registers handles it mints itself (currently the initial
// browser); the dispatcher's generated cases handle every other insert/retain/release.

// ---------------------------------------------------------------------------
// CefRenderHandler stub — required for windowless mode but we don't paint.
// ---------------------------------------------------------------------------

// Hard ceiling on OSR bitmap size. 4K BGRA = 33 MB. Bigger paints get clamped — CEF won't normally hand us
// anything larger than the requested viewport, so this is a safety net rather than the working budget.
static constexpr std::size_t kMaxOsrBytes = 3840 * 2160 * 4;

// Shrink-to-fit hysteresis: if the current mapping is more than this multiple of the actual paint size, we
// reallocate down. Picked at 4x to absorb common resize oscillations (e.g. 800x600 ↔ 1024x768 = 1.6x area
// difference) without rotating the shared-file path on every layout pass. Combined with a minimum size below this
// covers the "resize from huge to tiny" case without creating a shrink loop.
static constexpr std::size_t kOsrShrinkRatio = 4;
// Floor on mapping size to avoid micro-allocations when the viewport is briefly 1x1 during layout. 256x256 BGRA
// = 256 KB; smaller paints just leave the tail unused like before.
static constexpr std::size_t kOsrMinBytes = 256 * 256 * 4;

// Per-browser OSR mapping. Keyed by cef_browser_t* identity so the server can find the right buffer in on_paint
// (CEF supplies us the browser pointer). Lazy-allocated on first paint.
static std::mutex g_osrBuffersMu;
static std::unordered_map<cef_browser_t*, std::unique_ptr<cef4j::ipc::OsrPaintBuffer>> g_osrBuffers;
static std::atomic<std::int64_t> g_inlineFrameSequence{0};
static bool g_useInlineFrames = false;

// Per-browser viewport size, set by SetViewportSizeRequest from the JVM. The render handler's get_view_rect
// looks the browser up here on each call; absence means "use the default 800x600". KEYED BY browser
// identifier (an int) rather than `cef_browser_t*` because CEF passes different `cef_browser_t*` shim
// instances to different callbacks for the same logical browser — the identifier is the only stable
// cross-callback identity. (g_osrBuffers above doesn't hit this because on_paint always lands on the same
// callback wrapper instance, but get_view_rect's wrapper is a different one set by CEF internally.)
struct Viewport {
    int width;
    int height;
};
static std::mutex g_viewportsMu;
static std::unordered_map<int, Viewport> g_viewports;

// DevTools observer registrations are deliberately owned by the server, not a transport implementation.
// Releasing a cef_registration_t unregisters the observer. All mutations happen on CEF's UI thread.
struct DevToolsRegistration {
    cef_registration_t* registration;
};
static std::unordered_map<std::int32_t, DevToolsRegistration> g_devToolsRegistrations;

struct DevToolsObserver : cef_dev_tools_message_observer_t {
    std::atomic<int> refCount{1};
    std::int32_t browserHandle;

    explicit DevToolsObserver(std::int32_t handle)
        : cef_dev_tools_message_observer_t{}, browserHandle(handle) {
        initRef<DevToolsObserver, cef_dev_tools_message_observer_t>(
                reinterpret_cast<cef_base_ref_counted_t*>(this));
        on_dev_tools_message = [](cef_dev_tools_message_observer_t* self, cef_browser_t*,
                                  const void* message, std::size_t messageSize) -> int {
            auto* observer = static_cast<DevToolsObserver*>(self);
            net_kurobako_cef4j_ipc_protocol_gen::DevToolsMessageEvent event;
            event.browser = observer->browserHandle;
            if (message && messageSize > 0) {
                auto* first = static_cast<const std::uint8_t*>(message);
                event.message.assign(first, first + messageSize);
            }
            std::vector<std::uint8_t> wire(event.encodedSize());
            event.encodeInto(wire.data());
            if (g_ipc) {
                g_ipc->send(Kind::Event, 0, kNoCorrId, event.kMessageId,
                            wire.data(), wire.size());
            }
            // Consume the raw message; CEF need not parse and dispatch it a second time.
            return 1;
        };
        // CEF's C-to-C++ wrapper invokes every callback slot without a null check. Raw messages above are consumed,
        // so result/event callbacks are normally unreachable, but valid no-ops are still required for ABI safety.
        on_dev_tools_method_result = [](cef_dev_tools_message_observer_t*, cef_browser_t*, int, int,
                                        const void*, std::size_t) {};
        on_dev_tools_event = [](cef_dev_tools_message_observer_t*, cef_browser_t*, const cef_string_t*,
                                const void*, std::size_t) {};
        on_dev_tools_agent_attached = [](cef_dev_tools_message_observer_t*, cef_browser_t*) {};
        on_dev_tools_agent_detached = [](cef_dev_tools_message_observer_t* self, cef_browser_t*) {
            auto* observer = static_cast<DevToolsObserver*>(self);
            net_kurobako_cef4j_ipc_protocol_gen::DevToolsAgentDetachedEvent event;
            event.browser = observer->browserHandle;
            std::vector<std::uint8_t> wire(event.encodedSize());
            event.encodeInto(wire.data());
            if (g_ipc) {
                g_ipc->send(Kind::Event, 0, kNoCorrId, event.kMessageId,
                            wire.data(), wire.size());
            }
        };
    }
};

static void releaseDevToolsRegistration(std::int32_t browserHandle) {
    auto it = g_devToolsRegistrations.find(browserHandle);
    if (it == g_devToolsRegistrations.end()) return;
    auto* registrationBase = reinterpret_cast<cef_base_ref_counted_t*>(it->second.registration);
    registrationBase->release(registrationBase);
    g_devToolsRegistrations.erase(it);
}

static void releaseAllDevToolsRegistrations() {
    for (auto& entry : g_devToolsRegistrations) {
        auto* registrationBase = reinterpret_cast<cef_base_ref_counted_t*>(entry.second.registration);
        registrationBase->release(registrationBase);
    }
    g_devToolsRegistrations.clear();
}

struct RenderHandler : cef_render_handler_t {
    std::atomic<int> refCount{1};

    RenderHandler() : cef_render_handler_t{} {
        initRef<RenderHandler, cef_render_handler_t>(reinterpret_cast<cef_base_ref_counted_t*>(this));
        get_view_rect = [](cef_render_handler_t* /*self*/, cef_browser_t* browser, cef_rect_t* rect) {
            rect->x = 0;
            rect->y = 0;
            rect->width = 800;
            rect->height = 600;
            if (!browser) return;
            int id = browser->get_identifier(browser);
            std::lock_guard<std::mutex> g(g_viewportsMu);
            auto it = g_viewports.find(id);
            if (it != g_viewports.end()) {
                rect->width = it->second.width;
                rect->height = it->second.height;
            }
        };
        on_paint = [](cef_render_handler_t* /*self*/, cef_browser_t* browser,
                      cef_paint_element_type_t type, size_t dirtyCount, cef_rect_t const* dirty,
                      const void* buffer, int width, int height) {
            if (!browser || !buffer || width <= 0 || height <= 0) return;
            std::size_t byteCount = static_cast<std::size_t>(width) * height * 4;
            // Lazy-allocate per-browser shared files. Server-side handle lookup: tables::browser stores +1 retains, so
            // we reverse-search by raw pointer (the table is small — at most a handful of browsers).
            // OSR's first paint can fire before on_after_created completes — CEF dispatches both on the UI
            // thread but the browser-internal sequencing isn't strict. `insert` dedupes by pointer, so calling
            // it here either returns the existing id (set by on_after_created) or mints one we can use until
            // the lifespan forwarder catches up.
            std::int32_t handleId = gendisp::tables::browser.insert(browser);
            if (handleId == 0) return;
            if (g_ipc && g_useInlineFrames) {
                if (byteCount > kMaxOsrBytes) return;
                net_kurobako_cef4j_ipc_protocol_gen::InlinePaintEvent ev;
                ev.browser = handleId;
                ev.frameSequence = g_inlineFrameSequence.fetch_add(1, std::memory_order_relaxed) + 1;
                ev.width = width;
                ev.height = height;
                ev.paintType = static_cast<std::int32_t>(type);
                if (dirtyCount > 0 && dirty != nullptr) {
                    ev.dirtyX = dirty[0].x;
                    ev.dirtyY = dirty[0].y;
                    ev.dirtyWidth = dirty[0].width;
                    ev.dirtyHeight = dirty[0].height;
                }
                const auto* pixels = static_cast<const std::uint8_t*>(buffer);
                ev.pixels.assign(pixels, pixels + byteCount);
                std::vector<std::uint8_t> wire(ev.encodedSize());
                ev.encodeInto(wire.data());
                g_ipc->sendLatest(Kind::Event, 0, kNoCorrId,
                                  net_kurobako_cef4j_ipc_protocol_gen::InlinePaintEvent::kMessageId,
                                  wire.data(), wire.size(), handleId);
                return;
            }
            // Pick the mapped-file size we want for this paint: enough to hold byteCount, never below kOsrMinBytes,
            // never above kMaxOsrBytes. Reusing the existing buffer is the steady state; we only allocate when
            // either (a) there is no buffer yet, (b) the existing one is too small, or (c) the existing one is
            // wastefully large per the kOsrShrinkRatio hysteresis. Each (re)allocation bumps the generation so
            // the JVM-side shared-file transport sees a new path and re-maps; without the bump the JVM would
            // keep its old mapping pointed at an unlinked file.
            std::size_t targetBytes = byteCount;
            if (targetBytes < kOsrMinBytes) targetBytes = kOsrMinBytes;
            if (targetBytes > kMaxOsrBytes) targetBytes = kMaxOsrBytes;
            cef4j::ipc::OsrPaintBuffer* buf = nullptr;
            {
                std::lock_guard<std::mutex> g(g_osrBuffersMu);
                auto it = g_osrBuffers.find(browser);
                bool needRealloc = false;
                std::uint32_t nextGen = 0;
                if (it == g_osrBuffers.end()) {
                    needRealloc = true;
                } else {
                    std::size_t cap = it->second->capacity();
                    if (byteCount > cap) {
                        needRealloc = true;
                    } else if (cap > kOsrMinBytes && cap >= byteCount * kOsrShrinkRatio) {
                        needRealloc = true;
                    }
                    nextGen = it->second->generation() + 1;
                }
                if (needRealloc) {
                    auto fresh = std::make_unique<cef4j::ipc::OsrPaintBuffer>(handleId, targetBytes, nextGen);
                    if (!fresh->ok()) return;
                    if (it == g_osrBuffers.end()) {
                        auto [inserted, _] = g_osrBuffers.emplace(browser, std::move(fresh));
                        buf = inserted->second.get();
                    } else {
                        it->second = std::move(fresh);
                        buf = it->second.get();
                    }
                } else {
                    buf = it->second.get();
                }
            }
            auto published = buf->writePixels(buffer, byteCount);
            if (published.byteCount == 0) return;
            net_kurobako_cef4j_ipc_protocol_gen::OsrPaintEvent ev;
            ev.browser = handleId;
            ev.shmName = published.shmName;
            ev.frameSequence = static_cast<std::int64_t>(published.sequence);
            ev.width = width;
            ev.height = height;
            ev.byteCount = static_cast<std::int32_t>(published.byteCount);
            ev.paintType = static_cast<std::int32_t>(type);
            if (dirtyCount > 0 && dirty != nullptr) {
                ev.dirtyX = dirty[0].x;
                ev.dirtyY = dirty[0].y;
                ev.dirtyWidth = dirty[0].width;
                ev.dirtyHeight = dirty[0].height;
            }
            std::vector<std::uint8_t> wire(ev.encodedSize());
            ev.encodeInto(wire.data());
            if (g_ipc) {
                g_ipc->send(Kind::Event, 0, kNoCorrId,
                            net_kurobako_cef4j_ipc_protocol_gen::OsrPaintEvent::kMessageId,
                            wire.data(), wire.size());
            }
        };
    }
};

// ---------------------------------------------------------------------------
// CefClient — generated wireClient binds every forwarder we have for cef_client_t::get_X_handler. The
// LifeSpanHandlerForwarder seeds tables::browser via on_after_created and emits the AST event, replacing the
// hand-written LifeSpanHandler that used to live here. RenderHandler stays hand-written because its
// view-rect/paint callbacks aren't event-shaped.
// ---------------------------------------------------------------------------

struct Client : cef_client_t {
    std::atomic<int> refCount{1};
    RenderHandler* renderHandler;

    Client() : cef_client_t{}, renderHandler(new RenderHandler()) {
        initRef<Client, cef_client_t>(reinterpret_cast<cef_base_ref_counted_t*>(this));
        genhandlers::wireClient(this);
        get_render_handler = [](cef_client_t* self) -> cef_render_handler_t* {
            auto* c = reinterpret_cast<Client*>(self);
            auto* base = reinterpret_cast<cef_base_ref_counted_t*>(c->renderHandler);
            base->add_ref(base);
            return c->renderHandler;
        };
        // Receives messages the renderer subprocess sends via cef_frame_t::send_process_message. We translate
        // the well-known names ("v8ctx_created" so far) into IPC events for the JVM. Unknown names return 0
        // so CEF treats them as unhandled and any future client can take a turn.
        on_process_message_received = [](cef_client_t* /*self*/, cef_browser_t* browser,
                                         cef_frame_t* /*frame*/, cef_process_id_t /*src*/,
                                         cef_process_message_t* msg) -> int {
            if (!msg || !browser) return 0;
            cef_string_userfree_t nameUF = msg->get_name(msg);
            std::string name;
            if (nameUF) {
                cef_string_utf8_t u{};
                cef_string_utf16_to_utf8(nameUF->str, nameUF->length, &u);
                name.assign(u.str, u.length);
                cef_string_utf8_clear(&u);
                cef_string_userfree_free(nameUF);
            }
            if (name == "v8ctx_created") {
                // Resolve the browser handle id by reverse-lookup; renderer→browser→JVM hop preserves identity.
                std::int32_t handleId = gendisp::tables::browser.insert(browser);
                if (handleId == 0) return 1;
                std::string url;
                auto* args = msg->get_argument_list(msg);
                if (args && args->get_size(args) > 0) {
                    cef_string_userfree_t s = args->get_string(args, 0);
                    if (s) {
                        cef_string_utf8_t u{};
                        cef_string_utf16_to_utf8(s->str, s->length, &u);
                        url.assign(u.str, u.length);
                        cef_string_utf8_clear(&u);
                        cef_string_userfree_free(s);
                    }
                    auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(args);
                    ab->release(ab);
                }
                net_kurobako_cef4j_ipc_protocol_gen::V8ContextCreatedEvent ev;
                ev.browser = handleId;
                ev.frameUrl = std::move(url);
                std::vector<std::uint8_t> wire(ev.encodedSize());
                ev.encodeInto(wire.data());
                if (g_ipc) {
                    g_ipc->send(Kind::Event, 0, kNoCorrId,
                                net_kurobako_cef4j_ipc_protocol_gen::V8ContextCreatedEvent::kMessageId,
                                wire.data(), wire.size());
                }
                return 1;
            }
            // V8 response process_messages from the renderer share the JsResult-shaped wire layout (see
            // writeJsResultArgs in renderer code): [corrId, valueKind, boolValue, intValue, dblLow,
            // dblHigh, stringValue, errorMessage, valueHandle]. The browser-side Kind::Response repackages
            // them with the matching codegen'd Response class, addressed by the original JVM corrId.
            if (name == "v8_eval_resp" || name == "v8_get_property_resp"
                || name == "v8_execute_function_resp" || name == "v8_get_value_by_index_resp") {
                auto* args = msg->get_argument_list(msg);
                if (!args || args->get_size(args) < 9) {
                    if (args) {
                        auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(args);
                        ab->release(ab);
                    }
                    return 1;
                }
                std::int32_t corrId = args->get_int(args, 0);
                std::int32_t valueKind = args->get_int(args, 1);
                int boolValue = args->get_bool(args, 2);
                std::int32_t intValue = args->get_int(args, 3);
                std::int64_t dblLow = static_cast<std::uint32_t>(args->get_int(args, 4));
                std::int64_t dblHigh = static_cast<std::uint32_t>(args->get_int(args, 5));
                std::int64_t doubleBits = (dblHigh << 32) | (dblLow & 0xFFFFFFFFLL);
                std::string stringValue;
                std::string errorMessage;
                {
                    cef_string_userfree_t s = args->get_string(args, 6);
                    if (s) {
                        cef_string_utf8_t u{};
                        cef_string_utf16_to_utf8(s->str, s->length, &u);
                        stringValue.assign(u.str, u.length);
                        cef_string_utf8_clear(&u);
                        cef_string_userfree_free(s);
                    }
                }
                {
                    cef_string_userfree_t s = args->get_string(args, 7);
                    if (s) {
                        cef_string_utf8_t u{};
                        cef_string_utf16_to_utf8(s->str, s->length, &u);
                        errorMessage.assign(u.str, u.length);
                        cef_string_utf8_clear(&u);
                        cef_string_userfree_free(s);
                    }
                }
                std::int32_t valueHandle = args->get_int(args, 8);
                auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(args);
                ab->release(ab);

                if (name == "v8_eval_resp") {
                    net_kurobako_cef4j_ipc_protocol_gen::EvaluateJavascriptResponse resp;
                    resp.valueKind = valueKind;
                    resp.boolValue = boolValue != 0;
                    resp.intValue = intValue;
                    resp.doubleValue = doubleBits;
                    resp.stringValue = std::move(stringValue);
                    resp.errorMessage = std::move(errorMessage);
                    resp.valueHandle = valueHandle;
                    std::vector<std::uint8_t> wire(resp.encodedSize());
                    resp.encodeInto(wire.data());
                    if (g_ipc) {
                        g_ipc->send(
                                Kind::Response, 0, corrId,
                                net_kurobako_cef4j_ipc_protocol_gen::EvaluateJavascriptResponse::kMessageId,
                                wire.data(), wire.size());
                    }
                } else if (name == "v8_get_property_resp") {
                    net_kurobako_cef4j_ipc_protocol_gen::V8GetPropertyResponse resp;
                    resp.valueKind = valueKind;
                    resp.boolValue = boolValue != 0;
                    resp.intValue = intValue;
                    resp.doubleValue = doubleBits;
                    resp.stringValue = std::move(stringValue);
                    resp.errorMessage = std::move(errorMessage);
                    resp.valueHandle = valueHandle;
                    std::vector<std::uint8_t> wire(resp.encodedSize());
                    resp.encodeInto(wire.data());
                    if (g_ipc) {
                        g_ipc->send(Kind::Response, 0, corrId,
                                    net_kurobako_cef4j_ipc_protocol_gen::V8GetPropertyResponse::kMessageId,
                                    wire.data(), wire.size());
                    }
                } else { // v8_execute_function_resp
                    net_kurobako_cef4j_ipc_protocol_gen::V8ExecuteFunctionResponse resp;
                    resp.valueKind = valueKind;
                    resp.boolValue = boolValue != 0;
                    resp.intValue = intValue;
                    resp.doubleValue = doubleBits;
                    resp.stringValue = std::move(stringValue);
                    resp.errorMessage = std::move(errorMessage);
                    resp.valueHandle = valueHandle;
                    std::vector<std::uint8_t> wire(resp.encodedSize());
                    resp.encodeInto(wire.data());
                    if (g_ipc) {
                        g_ipc->send(
                                Kind::Response, 0, corrId,
                                net_kurobako_cef4j_ipc_protocol_gen::V8ExecuteFunctionResponse::kMessageId,
                                wire.data(), wire.size());
                    }
                }
                return 1;
            }
            if (name == "v8_get_string_resp") {
                auto* args = msg->get_argument_list(msg);
                if (!args || args->get_size(args) < 3) {
                    if (args) {
                        auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(args);
                        ab->release(ab);
                    }
                    return 1;
                }
                std::int32_t corrId = args->get_int(args, 0);
                int ok = args->get_bool(args, 1);
                std::string strVal;
                {
                    cef_string_userfree_t s = args->get_string(args, 2);
                    if (s) {
                        cef_string_utf8_t u{};
                        cef_string_utf16_to_utf8(s->str, s->length, &u);
                        strVal.assign(u.str, u.length);
                        cef_string_utf8_clear(&u);
                        cef_string_userfree_free(s);
                    }
                }
                auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(args);
                ab->release(ab);
                net_kurobako_cef4j_ipc_protocol_gen::V8GetStringValueResponse resp;
                resp.ok = ok != 0;
                resp.stringValue = std::move(strVal);
                std::vector<std::uint8_t> wire(resp.encodedSize());
                resp.encodeInto(wire.data());
                if (g_ipc) {
                    g_ipc->send(Kind::Response, 0, corrId,
                                net_kurobako_cef4j_ipc_protocol_gen::V8GetStringValueResponse::kMessageId,
                                wire.data(), wire.size());
                }
                return 1;
            }
            if (name == "v8_release_handle_resp") {
                auto* args = msg->get_argument_list(msg);
                if (!args || args->get_size(args) < 1) {
                    if (args) {
                        auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(args);
                        ab->release(ab);
                    }
                    return 1;
                }
                std::int32_t corrId = args->get_int(args, 0);
                auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(args);
                ab->release(ab);
                if (g_ipc) {
                    g_ipc->send(Kind::Response, 0, corrId,
                                net_kurobako_cef4j_ipc_protocol_gen::V8ReleaseHandleResponse::kMessageId,
                                nullptr, 0);
                }
                return 1;
            }
            // Codegen-driven renderer dispatch reply. The renderer subprocess invoked a V8/DOM method via
            // genrender::dispatch and is now shipping the encoded Response payload back. Translate to the
            // JVM-facing Kind::Response on the original corrId. The "_err" variant signals the renderer
            // detected a missing receiver / V8 context — translate to Kind::Error so the JVM future fails
            // with CefRemoteException.
            if (name == "cef4j_renderer_resp" || name == "cef4j_renderer_err") {
                Kind kind = name == "cef4j_renderer_err" ? Kind::Error : Kind::Response;
                // Empty-payload responses (e.g. RendererReleaseHandleResponse) skip the binary slot since
                // cef_binary_value_create(_, 0) returns null. Accept lists of size 2 (corrId, messageId)
                // and treat the missing arg[2] as a zero-byte payload.
                auto* args = msg->get_argument_list(msg);
                if (!args || args->get_size(args) < 2) {
                    if (args) {
                        auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(args);
                        ab->release(ab);
                    }
                    return 1;
                }
                std::int32_t corrId    = args->get_int(args, 0);
                std::int32_t messageId = args->get_int(args, 1);
                std::vector<std::uint8_t> payload;
                if (args->get_size(args) >= 3) {
                    cef_binary_value_t* binary = args->get_binary(args, 2);
                    if (binary) {
                        std::size_t size = binary->get_size(binary);
                        payload.resize(size);
                        if (size > 0) binary->get_data(binary, payload.data(), size, 0);
                        auto* bb = reinterpret_cast<cef_base_ref_counted_t*>(binary);
                        bb->release(bb);
                    }
                }
                auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(args);
                ab->release(ab);
                if (g_ipc) {
                    g_ipc->send(kind, 0, corrId, messageId, payload.data(), payload.size());
                }
                return 1;
            }
            if (name == "js_register_func_resp") {
                // Renderer's ack of a global JS-function install. Relay as Kind::Response to JVM.
                auto* args = msg->get_argument_list(msg);
                if (!args || args->get_size(args) < 1) {
                    if (args) {
                        auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(args);
                        ab->release(ab);
                    }
                    return 1;
                }
                std::int32_t corrId = args->get_int(args, 0);
                auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(args);
                ab->release(ab);
                if (g_ipc) {
                    g_ipc->send(Kind::Response, 0, corrId,
                                net_kurobako_cef4j_ipc_protocol_gen::RegisterJsFunctionResponse::kMessageId,
                                nullptr, 0);
                }
                return 1;
            }
            if (name == "js_function_call") {
                // JS in the page called a JVM-registered global function. Relay as Kind::Event so the
                // JVM's JsFunctionCallEvent subscriber routes by callbackId. Fire-and-forget for v1.
                auto* args = msg->get_argument_list(msg);
                if (!args || args->get_size(args) < 2) {
                    if (args) {
                        auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(args);
                        ab->release(ab);
                    }
                    return 1;
                }
                std::int32_t callbackId = args->get_int(args, 0);
                std::string argsJson;
                {
                    cef_string_userfree_t s = args->get_string(args, 1);
                    if (s) {
                        cef_string_utf8_t u{};
                        cef_string_utf16_to_utf8(s->str, s->length, &u);
                        argsJson.assign(u.str, u.length);
                        cef_string_utf8_clear(&u);
                        cef_string_userfree_free(s);
                    }
                }
                auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(args);
                ab->release(ab);
                net_kurobako_cef4j_ipc_protocol_gen::JsFunctionCallEvent ev;
                ev.callbackId = callbackId;
                ev.argsJson = std::move(argsJson);
                std::vector<std::uint8_t> wire(ev.encodedSize());
                ev.encodeInto(wire.data());
                if (g_ipc) {
                    g_ipc->send(Kind::Event, 0, kNoCorrId,
                                net_kurobako_cef4j_ipc_protocol_gen::JsFunctionCallEvent::kMessageId,
                                wire.data(), wire.size());
                }
                return 1;
            }
            return 0;
        };
    }

};

static void finishRuntimeShutdown() {
    std::fprintf(stderr, "[cef4j-runtime-server] shutdown: quitting CEF message loop\n");
    cef_quit_message_loop();
}

// ---------------------------------------------------------------------------
// Renderer-process surface. The same binary is re-execed for renderer subprocesses; cef_execute_process
// routes them. Inside the renderer we expose a render_process_handler whose on_context_created callback
// fires every time CEF mints a V8 context for a frame. We send a process message back to the browser
// process; the browser-side on_process_message_received translates it into a Kind::Event for the JVM.
//
// Process messages are CEF's built-in cross-process mechanism (built on Mojo internally). This is the
// foundation for full V8 RMI: future codegen will encode V8 method calls as process messages with a richer
// name+arg shape and route the JVM handler the same way.
// ---------------------------------------------------------------------------

// Renderer-side V8 value table reuses the dispatcher's `tables::v8Value` HandleTable<cef_v8_value_t>. The
// runtime server binary is also used for browser and renderer subprocesses; address spaces are independent
// so each process holds its own table state. Hand-written V8 helpers (eval, getProperty, executeFunction)
// and the codegen-generated RendererDispatcher both look up handles in this single table — when the JVM
// gets a V8 handle from EvaluateJavascript, codegen V8Value methods can retrieve it transparently.

// Pack a V8 retval into the wire response slots. If retainHandle is set and the value is complex (object/
// array/function), inserts into gendisp::tables::v8Value and writes the handle id; otherwise primitives go into their
// matching slot. Returns the kind so caller can also fill the kind field.
struct V8WireResult {
    std::int32_t valueKind = 0;
    int boolValue = 0;
    std::int32_t intValue = 0;
    std::int64_t doubleBits = 0;
    std::string stringValue;
    std::int32_t valueHandle = 0;
};

static V8WireResult packV8Retval(cef_v8_value_t* retval, bool retainHandle) {
    V8WireResult r;
    if (!retval || retval->is_null(retval) || retval->is_undefined(retval)) {
        r.valueKind = 0;
        return r;
    }
    if (retval->is_bool(retval)) {
        r.valueKind = 1;
        r.boolValue = retval->get_bool_value(retval) ? 1 : 0;
        return r;
    }
    if (retval->is_int(retval)) {
        r.valueKind = 2;
        r.intValue = retval->get_int_value(retval);
        return r;
    }
    if (retval->is_double(retval)) {
        r.valueKind = 3;
        double dv = retval->get_double_value(retval);
        std::memcpy(&r.doubleBits, &dv, sizeof(double));
        return r;
    }
    if (retval->is_string(retval)) {
        r.valueKind = 4;
        cef_string_userfree_t s = retval->get_string_value(retval);
        if (s) {
            cef_string_utf8_t u{};
            cef_string_utf16_to_utf8(s->str, s->length, &u);
            r.stringValue.assign(u.str, u.length);
            cef_string_utf8_clear(&u);
            cef_string_userfree_free(s);
        }
        return r;
    }
    // Complex: object, array, function. Either retain via handle table or JSON.stringify into the string
    // slot (the pre-handle behaviour).
    if (retainHandle) {
        r.valueKind = 4; // still kind=4 for a complex value; caller distinguishes via valueHandle != 0
        r.valueHandle = gendisp::tables::v8Value.insert(retval);
        return r;
    }
    r.valueKind = 4;
    r.stringValue = "[unsupported v8 value]";
    return r;
}

// ---- V8 process-message helpers (renderer side) ------------------------------------------------

static std::string readMessageName(cef_process_message_t* msg) {
    cef_string_userfree_t nameUF = msg->get_name(msg);
    std::string out;
    if (nameUF) {
        cef_string_utf8_t u{};
        cef_string_utf16_to_utf8(nameUF->str, nameUF->length, &u);
        out.assign(u.str, u.length);
        cef_string_utf8_clear(&u);
        cef_string_userfree_free(nameUF);
    }
    return out;
}

static std::string readListString(cef_list_value_t* list, std::size_t idx) {
    std::string out;
    cef_string_userfree_t s = list->get_string(list, idx);
    if (s) {
        cef_string_utf8_t u{};
        cef_string_utf16_to_utf8(s->str, s->length, &u);
        out.assign(u.str, u.length);
        cef_string_utf8_clear(&u);
        cef_string_userfree_free(s);
    }
    return out;
}

static void writeListString(cef_list_value_t* list, std::size_t idx, const std::string& s) {
    cef_string_t cs{};
    if (!s.empty()) cef_string_utf8_to_utf16(s.data(), s.size(), &cs);
    list->set_string(list, idx, &cs);
    cef_string_clear(&cs);
}

// Pack (corrId, V8WireResult) into the args of a "v8_*_resp" process_message. Args layout used by
// EvaluateJavascriptResponse / V8GetPropertyResponse — both have the same wire shape so they share this
// packer. The browser-process Client::on_process_message_received expects this layout.
static void writeJsResultArgs(cef_list_value_t* args, std::int32_t corrId, const V8WireResult& r,
                              const std::string& errorMessage) {
    args->set_int(args, 0, corrId);
    args->set_int(args, 1, r.valueKind);
    args->set_bool(args, 2, r.boolValue);
    args->set_int(args, 3, r.intValue);
    args->set_int(args, 4, static_cast<int>(r.doubleBits & 0xFFFFFFFF));
    args->set_int(args, 5, static_cast<int>((r.doubleBits >> 32) & 0xFFFFFFFF));
    writeListString(args, 6, r.stringValue);
    writeListString(args, 7, errorMessage);
    args->set_int(args, 8, r.valueHandle);
}

static void sendV8Response(cef_frame_t* frame, const char* name, std::size_t nameLen,
                           std::function<void(cef_list_value_t*)> fillArgs) {
    cef_string_t cefName{};
    cef_string_utf8_to_utf16(name, nameLen, &cefName);
    auto* respMsg = cef_process_message_create(&cefName);
    cef_string_clear(&cefName);
    if (!respMsg) return;
    auto* respArgs = respMsg->get_argument_list(respMsg);
    if (respArgs) {
        fillArgs(respArgs);
        auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(respArgs);
        ab->release(ab);
    }
    frame->send_process_message(frame, PID_BROWSER, respMsg);
}

static void handleV8EvalReq(cef_frame_t* frame, cef_process_message_t* msg) {
    auto* args = msg->get_argument_list(msg);
    if (!args || args->get_size(args) < 3) {
        if (args) {
            auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(args);
            ab->release(ab);
        }
        return;
    }
    std::int32_t corrId = args->get_int(args, 0);
    std::string code = readListString(args, 1);
    bool retainHandle = args->get_bool(args, 2) != 0;
    auto* argsBase = reinterpret_cast<cef_base_ref_counted_t*>(args);
    argsBase->release(argsBase);

    V8WireResult r;
    std::string errorMessage;
    auto* ctx = frame->get_v8_context(frame);
    if (!ctx) {
        r.valueKind = 5;
        errorMessage = "no v8 context for frame";
    } else if (!ctx->enter(ctx)) {
        r.valueKind = 5;
        errorMessage = "enter v8 context failed";
        auto* cb = reinterpret_cast<cef_base_ref_counted_t*>(ctx);
        cb->release(cb);
        ctx = nullptr;
    } else {
        // For retainHandle=true, leave complex values as the live V8 object so the table can hold a ref.
        // For retainHandle=false, JSON.stringify complex values into the string slot (legacy ergonomics).
        std::string wrappedCode;
        if (retainHandle) {
            wrappedCode = "(function(){return (" + code + ");})()";
        } else {
            wrappedCode = "(function(){var __r=(" + code + ");"
                          "if(__r===null||__r===undefined)return __r;"
                          "var __t=typeof __r;"
                          "if(__t==='object'||__t==='function')return JSON.stringify(__r);"
                          "return __r;})()";
        }
        cef_string_t cefCode{};
        if (!wrappedCode.empty()) cef_string_utf8_to_utf16(wrappedCode.data(), wrappedCode.size(), &cefCode);
        cef_string_t scriptUrl{};
        cef_v8_value_t* retval = nullptr;
        cef_v8_exception_t* exc = nullptr;
        int ok = ctx->eval(ctx, &cefCode, &scriptUrl, 1, &retval, &exc);
        cef_string_clear(&cefCode);
        cef_string_clear(&scriptUrl);
        if (!ok) {
            r.valueKind = 5;
            if (exc) {
                cef_string_userfree_t msgUf = exc->get_message(exc);
                if (msgUf) {
                    cef_string_utf8_t u{};
                    cef_string_utf16_to_utf8(msgUf->str, msgUf->length, &u);
                    errorMessage.assign(u.str, u.length);
                    cef_string_utf8_clear(&u);
                    cef_string_userfree_free(msgUf);
                }
                auto* eb = reinterpret_cast<cef_base_ref_counted_t*>(exc);
                eb->release(eb);
            } else {
                errorMessage = "eval failed";
            }
        } else {
            r = packV8Retval(retval, retainHandle);
            if (retval) {
                auto* rb = reinterpret_cast<cef_base_ref_counted_t*>(retval);
                rb->release(rb);
            }
        }
        ctx->exit(ctx);
        auto* cb = reinterpret_cast<cef_base_ref_counted_t*>(ctx);
        cb->release(cb);
    }

    sendV8Response(frame, "v8_eval_resp", 12, [&](cef_list_value_t* a) {
        writeJsResultArgs(a, corrId, r, errorMessage);
    });
}

static void handleV8GetStringReq(cef_frame_t* frame, cef_process_message_t* msg) {
    auto* args = msg->get_argument_list(msg);
    if (!args || args->get_size(args) < 2) {
        if (args) {
            auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(args);
            ab->release(ab);
        }
        return;
    }
    std::int32_t corrId = args->get_int(args, 0);
    std::int32_t v8Handle = args->get_int(args, 1);
    auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(args);
    ab->release(ab);

    bool ok = false;
    std::string strVal;
    cef_v8_value_t* v = gendisp::tables::v8Value.find(v8Handle);
    if (v) {
        // Operate inside the frame's V8 context (retained values are live cross-context but accessor
        // calls expect to be in a context).
        auto* ctx = frame->get_v8_context(frame);
        if (ctx && ctx->enter(ctx)) {
            if (v->is_string(v)) {
                ok = true;
                cef_string_userfree_t s = v->get_string_value(v);
                if (s) {
                    cef_string_utf8_t u{};
                    cef_string_utf16_to_utf8(s->str, s->length, &u);
                    strVal.assign(u.str, u.length);
                    cef_string_utf8_clear(&u);
                    cef_string_userfree_free(s);
                }
            }
            ctx->exit(ctx);
        }
        if (ctx) {
            auto* cb = reinterpret_cast<cef_base_ref_counted_t*>(ctx);
            cb->release(cb);
        }
    }

    sendV8Response(frame, "v8_get_string_resp", 18, [&](cef_list_value_t* a) {
        a->set_int(a, 0, corrId);
        a->set_bool(a, 1, ok);
        writeListString(a, 2, strVal);
    });
}

static void handleV8GetPropertyReq(cef_frame_t* frame, cef_process_message_t* msg) {
    auto* args = msg->get_argument_list(msg);
    if (!args || args->get_size(args) < 3) {
        if (args) {
            auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(args);
            ab->release(ab);
        }
        return;
    }
    std::int32_t corrId = args->get_int(args, 0);
    std::int32_t v8Handle = args->get_int(args, 1);
    std::string propertyName = readListString(args, 2);
    auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(args);
    ab->release(ab);

    V8WireResult r;
    std::string errorMessage;
    cef_v8_value_t* v = gendisp::tables::v8Value.find(v8Handle);
    if (!v) {
        r.valueKind = 5;
        errorMessage = "v8 handle gone";
    } else {
        auto* ctx = frame->get_v8_context(frame);
        if (!ctx || !ctx->enter(ctx)) {
            r.valueKind = 5;
            errorMessage = "no v8 context";
            if (ctx) {
                auto* cb = reinterpret_cast<cef_base_ref_counted_t*>(ctx);
                cb->release(cb);
            }
        } else {
            cef_string_t propKey{};
            if (!propertyName.empty())
                cef_string_utf8_to_utf16(propertyName.data(), propertyName.size(), &propKey);
            cef_v8_value_t* prop = v->get_value_bykey(v, &propKey);
            cef_string_clear(&propKey);
            if (!prop) {
                r.valueKind = 5;
                errorMessage = "no such property";
            } else {
                r = packV8Retval(prop, /*retainHandle=*/true);
                auto* pb = reinterpret_cast<cef_base_ref_counted_t*>(prop);
                pb->release(pb);
            }
            ctx->exit(ctx);
            auto* cb = reinterpret_cast<cef_base_ref_counted_t*>(ctx);
            cb->release(cb);
        }
    }

    sendV8Response(frame, "v8_get_property_resp", 20, [&](cef_list_value_t* a) {
        writeJsResultArgs(a, corrId, r, errorMessage);
    });
}

// Execute a V8 function handle with args supplied as a JSON array string. We JSON.parse the args inside
// the V8 context so each arg becomes a real V8 value (number, string, bool, object). Then call
// execute_function. Return value goes through packV8Retval — same JsResult layout as eval/getProperty.
static void handleV8ExecuteFunctionReq(cef_frame_t* frame, cef_process_message_t* msg) {
    auto* args = msg->get_argument_list(msg);
    if (!args || args->get_size(args) < 3) {
        if (args) {
            auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(args);
            ab->release(ab);
        }
        return;
    }
    std::int32_t corrId = args->get_int(args, 0);
    std::int32_t v8Handle = args->get_int(args, 1);
    std::string argsJson = readListString(args, 2);
    auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(args);
    ab->release(ab);

    V8WireResult r;
    std::string errorMessage;
    cef_v8_value_t* fn = gendisp::tables::v8Value.find(v8Handle);
    if (!fn) {
        r.valueKind = 5;
        errorMessage = "v8 handle gone";
    } else {
        auto* ctx = frame->get_v8_context(frame);
        if (!ctx || !ctx->enter(ctx)) {
            r.valueKind = 5;
            errorMessage = "no v8 context";
        } else if (!fn->is_function(fn)) {
            r.valueKind = 5;
            errorMessage = "v8 handle is not a function";
            ctx->exit(ctx);
        } else {
            // Parse argsJson into a V8 array via the context's JSON.parse. Empty or "[]" gives an empty
            // call; missing parens behave the same as no args.
            std::vector<cef_v8_value_t*> v8Args;
            if (!argsJson.empty() && argsJson != "[]") {
                auto* global = ctx->get_global(ctx);
                if (global) {
                    cef_string_t jsonKey{};
                    cef_string_utf8_to_utf16("JSON", 4, &jsonKey);
                    cef_v8_value_t* jsonObj = global->get_value_bykey(global, &jsonKey);
                    cef_string_clear(&jsonKey);
                    if (jsonObj) {
                        cef_string_t parseKey{};
                        cef_string_utf8_to_utf16("parse", 5, &parseKey);
                        cef_v8_value_t* parse = jsonObj->get_value_bykey(jsonObj, &parseKey);
                        cef_string_clear(&parseKey);
                        if (parse && parse->is_function(parse)) {
                            cef_string_t cs{};
                            cef_string_utf8_to_utf16(argsJson.data(), argsJson.size(), &cs);
                            cef_v8_value_t* jsonArg = cef_v8_value_create_string(&cs);
                            cef_string_clear(&cs);
                            cef_v8_value_t* parseArgs[1] = {jsonArg};
                            cef_v8_value_t* parsed = parse->execute_function(parse, jsonObj, 1, parseArgs);
                            if (parsed && parsed->is_array(parsed)) {
                                int n = parsed->get_array_length(parsed);
                                for (int i = 0; i < n; ++i) {
                                    cef_v8_value_t* item = parsed->get_value_byindex(parsed, i);
                                    if (item) v8Args.push_back(item);
                                }
                            }
                        }
                    }
                }
            }
            cef_v8_value_t* retval = fn->execute_function(
                    fn, /*object=*/nullptr,
                    static_cast<size_t>(v8Args.size()),
                    v8Args.empty() ? nullptr : v8Args.data());
            if (!retval) {
                r.valueKind = 5;
                errorMessage = "execute_function returned null (V8 exception?)";
            } else {
                r = packV8Retval(retval, /*retainHandle=*/true);
            }
            ctx->exit(ctx);
        }
    }

    sendV8Response(frame, "v8_execute_function_resp", 24, [&](cef_list_value_t* a) {
        writeJsResultArgs(a, corrId, r, errorMessage);
    });
}

// Materialise a JsValue payload (kind + matching slot) into a real cef_v8_value_t. Caller must be inside
// the V8 context. Used by setProperty's valueKind handling. Returns nullptr if kind/handle unrecognised.
static cef_v8_value_t* materialiseV8Value(std::int32_t valueKind, int boolValue, std::int32_t intValue,
                                          std::int64_t doubleBits, const std::string& stringValue,
                                          std::int32_t valueHandle) {
    switch (valueKind) {
        case 0: return cef_v8_value_create_null();
        case 1: return cef_v8_value_create_bool(boolValue ? 1 : 0);
        case 2: return cef_v8_value_create_int(intValue);
        case 3: {
            double d;
            std::memcpy(&d, &doubleBits, sizeof(double));
            return cef_v8_value_create_double(d);
        }
        case 4: {
            cef_string_t cs{};
            if (!stringValue.empty()) cef_string_utf8_to_utf16(stringValue.data(), stringValue.size(), &cs);
            cef_v8_value_t* v = cef_v8_value_create_string(&cs);
            cef_string_clear(&cs);
            return v;
        }
        default:
            // Existing V8 handle from the renderer-side table.
            if (valueHandle != 0) return gendisp::tables::v8Value.find(valueHandle);
            return nullptr;
    }
}

static void handleV8SetPropertyReq(cef_frame_t* frame, cef_process_message_t* msg) {
    auto* args = msg->get_argument_list(msg);
    // [corrId, v8Handle, name, valueKind, bool, int, dblLow, dblHigh, stringValue, valueHandle]
    if (!args || args->get_size(args) < 10) {
        if (args) {
            auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(args);
            ab->release(ab);
        }
        return;
    }
    std::int32_t corrId = args->get_int(args, 0);
    std::int32_t v8Handle = args->get_int(args, 1);
    std::string propertyName = readListString(args, 2);
    std::int32_t valueKind = args->get_int(args, 3);
    int boolValue = args->get_bool(args, 4);
    std::int32_t intValue = args->get_int(args, 5);
    std::int64_t dblLow = static_cast<std::uint32_t>(args->get_int(args, 6));
    std::int64_t dblHigh = static_cast<std::uint32_t>(args->get_int(args, 7));
    std::int64_t doubleBits = (dblHigh << 32) | (dblLow & 0xFFFFFFFFLL);
    std::string stringValue = readListString(args, 8);
    std::int32_t srcHandle = args->get_int(args, 9);
    auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(args);
    ab->release(ab);

    bool ok = false;
    std::string errorMessage;
    cef_v8_value_t* target = gendisp::tables::v8Value.find(v8Handle);
    if (!target) {
        errorMessage = "v8 handle gone";
    } else {
        auto* ctx = frame->get_v8_context(frame);
        if (!ctx || !ctx->enter(ctx)) {
            errorMessage = "no v8 context";
        } else {
            cef_v8_value_t* val = materialiseV8Value(
                    valueKind, boolValue, intValue, doubleBits, stringValue, srcHandle);
            if (!val) {
                errorMessage = "could not materialise value (unknown kind or stale handle)";
            } else {
                cef_string_t propKey{};
                if (!propertyName.empty())
                    cef_string_utf8_to_utf16(propertyName.data(), propertyName.size(), &propKey);
                int setOk = target->set_value_bykey(target, &propKey, val,
                                                   static_cast<cef_v8_propertyattribute_t>(0));
                cef_string_clear(&propKey);
                ok = setOk != 0;
                if (!ok) errorMessage = "set_value_bykey returned false";
            }
            ctx->exit(ctx);
        }
    }
    sendV8Response(frame, "v8_set_property_resp", 20, [&](cef_list_value_t* a) {
        a->set_int(a, 0, corrId);
        a->set_bool(a, 1, ok);
        writeListString(a, 2, errorMessage);
    });
}

static void handleV8HasPropertyReq(cef_frame_t* frame, cef_process_message_t* msg) {
    auto* args = msg->get_argument_list(msg);
    if (!args || args->get_size(args) < 3) {
        if (args) {
            auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(args);
            ab->release(ab);
        }
        return;
    }
    std::int32_t corrId = args->get_int(args, 0);
    std::int32_t v8Handle = args->get_int(args, 1);
    std::string propertyName = readListString(args, 2);
    auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(args);
    ab->release(ab);

    bool has = false;
    cef_v8_value_t* v = gendisp::tables::v8Value.find(v8Handle);
    if (v) {
        auto* ctx = frame->get_v8_context(frame);
        if (ctx && ctx->enter(ctx)) {
            cef_string_t propKey{};
            if (!propertyName.empty())
                cef_string_utf8_to_utf16(propertyName.data(), propertyName.size(), &propKey);
            has = v->has_value_bykey(v, &propKey) != 0;
            cef_string_clear(&propKey);
            ctx->exit(ctx);
        }
    }
    sendV8Response(frame, "v8_has_property_resp", 20, [&](cef_list_value_t* a) {
        a->set_int(a, 0, corrId);
        a->set_bool(a, 1, has);
    });
}

static void handleV8GetKeysReq(cef_frame_t* frame, cef_process_message_t* msg) {
    auto* args = msg->get_argument_list(msg);
    if (!args || args->get_size(args) < 2) {
        if (args) {
            auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(args);
            ab->release(ab);
        }
        return;
    }
    std::int32_t corrId = args->get_int(args, 0);
    std::int32_t v8Handle = args->get_int(args, 1);
    auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(args);
    ab->release(ab);

    bool ok = false;
    std::vector<std::string> keys;
    cef_v8_value_t* v = gendisp::tables::v8Value.find(v8Handle);
    if (v) {
        auto* ctx = frame->get_v8_context(frame);
        if (ctx && ctx->enter(ctx)) {
            cef_string_list_t keyList = cef_string_list_alloc();
            if (v->get_keys(v, keyList)) {
                ok = true;
                std::size_t n = cef_string_list_size(keyList);
                keys.reserve(n);
                for (std::size_t i = 0; i < n; ++i) {
                    cef_string_t k{};
                    if (cef_string_list_value(keyList, i, &k)) {
                        cef_string_utf8_t u{};
                        cef_string_utf16_to_utf8(k.str, k.length, &u);
                        keys.emplace_back(u.str, u.length);
                        cef_string_utf8_clear(&u);
                    }
                    cef_string_clear(&k);
                }
            }
            cef_string_list_free(keyList);
            ctx->exit(ctx);
        }
    }
    sendV8Response(frame, "v8_get_keys_resp", 16, [&](cef_list_value_t* a) {
        a->set_int(a, 0, corrId);
        a->set_bool(a, 1, ok);
        a->set_int(a, 2, static_cast<int>(keys.size()));
        for (std::size_t i = 0; i < keys.size(); ++i) {
            writeListString(a, 3 + i, keys[i]);
        }
    });
}

static void handleV8GetArrayLengthReq(cef_frame_t* frame, cef_process_message_t* msg) {
    auto* args = msg->get_argument_list(msg);
    if (!args || args->get_size(args) < 2) {
        if (args) {
            auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(args);
            ab->release(ab);
        }
        return;
    }
    std::int32_t corrId = args->get_int(args, 0);
    std::int32_t v8Handle = args->get_int(args, 1);
    auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(args);
    ab->release(ab);

    bool ok = false;
    std::int32_t length = 0;
    cef_v8_value_t* v = gendisp::tables::v8Value.find(v8Handle);
    if (v) {
        auto* ctx = frame->get_v8_context(frame);
        if (ctx && ctx->enter(ctx) && v->is_array(v)) {
            ok = true;
            length = v->get_array_length(v);
            ctx->exit(ctx);
        } else if (ctx) {
            ctx->exit(ctx);
        }
    }
    sendV8Response(frame, "v8_get_array_length_resp", 24, [&](cef_list_value_t* a) {
        a->set_int(a, 0, corrId);
        a->set_bool(a, 1, ok);
        a->set_int(a, 2, length);
    });
}

static void handleV8GetValueByIndexReq(cef_frame_t* frame, cef_process_message_t* msg) {
    auto* args = msg->get_argument_list(msg);
    if (!args || args->get_size(args) < 3) {
        if (args) {
            auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(args);
            ab->release(ab);
        }
        return;
    }
    std::int32_t corrId = args->get_int(args, 0);
    std::int32_t v8Handle = args->get_int(args, 1);
    std::int32_t index = args->get_int(args, 2);
    auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(args);
    ab->release(ab);

    V8WireResult r;
    std::string errorMessage;
    cef_v8_value_t* v = gendisp::tables::v8Value.find(v8Handle);
    if (!v) {
        r.valueKind = 5;
        errorMessage = "v8 handle gone";
    } else {
        auto* ctx = frame->get_v8_context(frame);
        if (!ctx || !ctx->enter(ctx)) {
            r.valueKind = 5;
            errorMessage = "no v8 context";
        } else {
            cef_v8_value_t* item = v->get_value_byindex(v, index);
            if (!item) {
                r.valueKind = 5;
                errorMessage = "no value at index";
            } else {
                r = packV8Retval(item, /*retainHandle=*/true);
            }
            ctx->exit(ctx);
        }
    }
    sendV8Response(frame, "v8_get_value_by_index_resp", 26, [&](cef_list_value_t* a) {
        writeJsResultArgs(a, corrId, r, errorMessage);
    });
}

static void handleV8ReleaseHandleReq(cef_frame_t* frame, cef_process_message_t* msg) {
    auto* args = msg->get_argument_list(msg);
    if (!args || args->get_size(args) < 2) {
        if (args) {
            auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(args);
            ab->release(ab);
        }
        return;
    }
    std::int32_t corrId = args->get_int(args, 0);
    std::int32_t v8Handle = args->get_int(args, 1);
    auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(args);
    ab->release(ab);
    gendisp::tables::v8Value.release(v8Handle);
    sendV8Response(frame, "v8_release_handle_resp", 22,
                   [&](cef_list_value_t* a) { a->set_int(a, 0, corrId); });
}

// ---- JVM-implemented JS function (renderer side) -----------------------------------------------
//
// JvmJsHandler: a cef_v8_handler_t synthetic that, when JS calls the registered global function, encodes
// the args as JSON via the V8 context's JSON.stringify and ships them to the browser process as a
// "js_function_call" process_message. Browser-side relays as Kind::Event(JsFunctionCallEvent) to JVM.
//
// Fire-and-forget for v1 — JS sees `undefined` returned. Sync return would block the renderer's V8 thread
// waiting for an InterceptResponse; deferred until a real use case demands it.
struct JvmJsHandler : cef_v8_handler_t {
    std::atomic<int> refCount{1};
    std::int32_t callbackId;

    JvmJsHandler(std::int32_t cbId) : cef_v8_handler_t{}, callbackId(cbId) {
        auto* self                  = reinterpret_cast<cef_base_ref_counted_t*>(this);
        self->size                  = sizeof(cef_v8_handler_t);
        self->add_ref               = [](cef_base_ref_counted_t* s) {
            reinterpret_cast<JvmJsHandler*>(s)->refCount.fetch_add(1, std::memory_order_relaxed);
        };
        self->release               = [](cef_base_ref_counted_t* s) -> int {
            auto* h = reinterpret_cast<JvmJsHandler*>(s);
            if (h->refCount.fetch_sub(1, std::memory_order_acq_rel) == 1) {
                delete h;
                return 1;
            }
            return 0;
        };
        self->has_one_ref           = [](cef_base_ref_counted_t* s) -> int {
            return reinterpret_cast<JvmJsHandler*>(s)->refCount.load(std::memory_order_acquire) == 1;
        };
        self->has_at_least_one_ref  = [](cef_base_ref_counted_t* s) -> int {
            return reinterpret_cast<JvmJsHandler*>(s)->refCount.load(std::memory_order_acquire) >= 1;
        };
        execute = [](cef_v8_handler_t* selfPtr, const cef_string_t* /*funcName*/,
                     cef_v8_value_t* /*object*/, size_t argumentsCount,
                     cef_v8_value_t* const* arguments, cef_v8_value_t** retval,
                     cef_string_t* /*exception*/) -> int {
            auto* self = reinterpret_cast<JvmJsHandler*>(selfPtr);
            // Build a JS array of the args, then JSON.stringify it via the live context's JSON global.
            // V8 GC handles the lifetime of the temporaries we touch — calling C-API release on them in
            // this dispatch context can deadlock the renderer (same root cause as the register-time
            // hang on fnVal->release). So we don't release any v8 value handles here.
            std::string argsJson;
            auto* ctx = cef_v8_context_get_current_context();
            if (ctx) {
                auto* global = ctx->get_global(ctx);
                if (global) {
                    cef_string_t jsonKey{};
                    cef_string_utf8_to_utf16("JSON", 4, &jsonKey);
                    cef_v8_value_t* jsonObj = global->get_value_bykey(global, &jsonKey);
                    cef_string_clear(&jsonKey);
                    if (jsonObj) {
                        cef_string_t strKey{};
                        cef_string_utf8_to_utf16("stringify", 9, &strKey);
                        cef_v8_value_t* stringify = jsonObj->get_value_bykey(jsonObj, &strKey);
                        cef_string_clear(&strKey);
                        if (stringify && stringify->is_function(stringify)) {
                            cef_v8_value_t* arr =
                                    cef_v8_value_create_array(static_cast<int>(argumentsCount));
                            if (arr) {
                                for (size_t i = 0; i < argumentsCount; ++i) {
                                    arr->set_value_byindex(arr, static_cast<int>(i), arguments[i]);
                                }
                                cef_v8_value_t* args1[1] = {arr};
                                cef_v8_value_t* result =
                                        stringify->execute_function(stringify, jsonObj, 1, args1);
                                if (result && result->is_string(result)) {
                                    cef_string_userfree_t s = result->get_string_value(result);
                                    if (s) {
                                        cef_string_utf8_t u{};
                                        cef_string_utf16_to_utf8(s->str, s->length, &u);
                                        argsJson.assign(u.str, u.length);
                                        cef_string_utf8_clear(&u);
                                        cef_string_userfree_free(s);
                                    }
                                }
                            }
                        }
                    }
                }
                // Send process_message to PID_BROWSER. We need a frame to send via — use the context's
                // frame.
                cef_frame_t* frame = ctx->get_frame(ctx);
                if (frame) {
                    cef_string_t mname{};
                    cef_string_utf8_to_utf16("js_function_call", 16, &mname);
                    auto* m = cef_process_message_create(&mname);
                    cef_string_clear(&mname);
                    if (m) {
                        auto* a = m->get_argument_list(m);
                        if (a) {
                            a->set_int(a, 0, self->callbackId);
                            cef_string_t jsonStr{};
                            if (!argsJson.empty())
                                cef_string_utf8_to_utf16(argsJson.data(), argsJson.size(), &jsonStr);
                            a->set_string(a, 1, &jsonStr);
                            cef_string_clear(&jsonStr);
                            auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(a);
                            ab->release(ab);
                        }
                        frame->send_process_message(frame, PID_BROWSER, m);
                    }
                    // Skip frame->release here too; V8/IPC manage the frame's lifetime.
                }
                // Skip ctx release — same V8 GC reasoning.
            }
            // Fire-and-forget: return undefined to JS.
            *retval = cef_v8_value_create_undefined();
            return 1;
        };
    }
};

static void handleJsRegisterFuncReq(cef_frame_t* frame, cef_process_message_t* msg) {
    auto* args = msg->get_argument_list(msg);
    if (!args || args->get_size(args) < 3) {
        if (args) {
            auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(args);
            ab->release(ab);
        }
        return;
    }
    std::int32_t corrId = args->get_int(args, 0);
    std::string fnName = readListString(args, 1);
    std::int32_t callbackId = args->get_int(args, 2);
    auto* argsBase = reinterpret_cast<cef_base_ref_counted_t*>(args);
    argsBase->release(argsBase);

    auto* ctx = frame->get_v8_context(frame);
    if (ctx && ctx->enter(ctx)) {
        auto* global = ctx->get_global(ctx);
        if (global) {
            cef_string_t cefFnName{};
            cef_string_utf8_to_utf16(fnName.data(), fnName.size(), &cefFnName);
            auto* handler = new JvmJsHandler(callbackId);
            auto* fnVal = cef_v8_value_create_function(
                    &cefFnName, reinterpret_cast<cef_v8_handler_t*>(handler));
            global->set_value_bykey(global, &cefFnName, fnVal,
                                    static_cast<cef_v8_propertyattribute_t>(0));
            cef_string_clear(&cefFnName);
            // NOTE: NOT calling release on fnVal/global/ctx/etc. CEF's CToCpp wrappers handle V8 value
            // lifetime via V8's own GC; the C-API release functions can deadlock the renderer in this
            // dispatch context (observed: register hung at fnVal->release; execute hung at arr->release).
            // Leaking the C-API +1 ref is fine because V8's GC reclaims when the context unwinds.
        }
        ctx->exit(ctx);
    }
    sendV8Response(frame, "js_register_func_resp", 21,
                   [&](cef_list_value_t* a) { a->set_int(a, 0, corrId); });
}

struct RenderProcessHandler : cef_render_process_handler_t {
    std::atomic<int> refCount{1};
    RenderProcessHandler() : cef_render_process_handler_t{} {
        initRef<RenderProcessHandler, cef_render_process_handler_t>(
                reinterpret_cast<cef_base_ref_counted_t*>(this));
        on_context_created = [](cef_render_process_handler_t* /*self*/, cef_browser_t* /*browser*/,
                                cef_frame_t* frame, cef_v8_context_t* /*context*/) {
            if (!frame || !frame->is_valid(frame)) return;
            cef_string_t name{};
            cef_string_utf8_to_utf16("v8ctx_created", 13, &name);
            auto* msg = cef_process_message_create(&name);
            cef_string_clear(&name);
            if (!msg) return;
            auto* args = msg->get_argument_list(msg);
            if (args) {
                cef_string_userfree_t urlUF = frame->get_url(frame);
                if (urlUF) {
                    args->set_string(args, 0, urlUF);
                    cef_string_userfree_free(urlUF);
                } else {
                    cef_string_t empty{};
                    args->set_string(args, 0, &empty);
                }
                auto* argsBase = reinterpret_cast<cef_base_ref_counted_t*>(args);
                argsBase->release(argsBase);
            }
            // CEF doc on send_process_message: "Ownership of the message contents will be transferred and
            // the |message| reference will be invalidated." So send_process_message ADOPTS our +1 from
            // cef_process_message_create — we must NOT release it afterward, that would double-decrement
            // and corrupt the IPC bus.
            frame->send_process_message(frame, PID_BROWSER, msg);
        };
        // Renderer-side handler dispatches by message name. All V8 ops follow the same shape:
        // [corrId at args[0], request-specific args, ...] and respond with a per-name response message
        // whose first arg is the corrId for the browser-process to relay back to the JVM.
        on_process_message_received = [](cef_render_process_handler_t* /*self*/, cef_browser_t* /*browser*/,
                                         cef_frame_t* frame, cef_process_id_t /*src*/,
                                         cef_process_message_t* msg) -> int {
            if (!msg || !frame) return 0;
            std::string name = readMessageName(msg);
            if (name == "v8_eval_req") {
                handleV8EvalReq(frame, msg);
                return 1;
            }
            if (name == "v8_get_string_req") {
                handleV8GetStringReq(frame, msg);
                return 1;
            }
            if (name == "v8_get_property_req") {
                handleV8GetPropertyReq(frame, msg);
                return 1;
            }
            if (name == "v8_release_handle_req") {
                handleV8ReleaseHandleReq(frame, msg);
                return 1;
            }
            if (name == "v8_execute_function_req") {
                handleV8ExecuteFunctionReq(frame, msg);
                return 1;
            }
            if (name == "v8_set_property_req") {
                handleV8SetPropertyReq(frame, msg);
                return 1;
            }
            if (name == "v8_has_property_req") {
                handleV8HasPropertyReq(frame, msg);
                return 1;
            }
            if (name == "v8_get_keys_req") {
                handleV8GetKeysReq(frame, msg);
                return 1;
            }
            if (name == "v8_get_array_length_req") {
                handleV8GetArrayLengthReq(frame, msg);
                return 1;
            }
            if (name == "v8_get_value_by_index_req") {
                handleV8GetValueByIndexReq(frame, msg);
                return 1;
            }
            if (name == "js_register_func_req") {
                handleJsRegisterFuncReq(frame, msg);
                return 1;
            }
            // Codegen-driven renderer dispatch. The browser-side relayToRenderer packages the original
            // wire payload in args[2] (binary). Decode the envelope, hand the bytes to genrender::dispatch
            // which invokes the V8/DOM method inside the right context and ships back a "cef4j_renderer_resp"
            // (or "cef4j_renderer_err" on lookup failure).
            if (name == "cef4j_renderer_req") {
                auto* args = msg->get_argument_list(msg);
                if (!args || args->get_size(args) < 3) {
                    if (args) {
                        auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(args);
                        ab->release(ab);
                    }
                    return 1;
                }
                std::int32_t corrId    = args->get_int(args, 0);
                std::int32_t messageId = args->get_int(args, 1);
                std::vector<std::uint8_t> payload;
                cef_binary_value_t* binary = args->get_binary(args, 2);
                if (binary) {
                    std::size_t size = binary->get_size(binary);
                    payload.resize(size);
                    if (size > 0) binary->get_data(binary, payload.data(), size, 0);
                    auto* bb = reinterpret_cast<cef_base_ref_counted_t*>(binary);
                    bb->release(bb);
                }
                auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(args);
                ab->release(ab);
                // Hand-written renderer-relay messages get dispatched here before the codegen renderer
                // dispatcher sees them. Currently just RendererReleaseHandleRequest, which routes to
                // gendisp::dispatchRelease against the renderer subprocess's own table state.
                if (messageId == net_kurobako_cef4j_ipc_protocol_gen::RendererReleaseHandleRequest::kMessageId) {
                    auto req = net_kurobako_cef4j_ipc_protocol_gen::RendererReleaseHandleRequest::decode(
                            payload.data(), payload.size());
                    (void)gendisp::dispatchRelease(req.kind, req.handle);
                    std::vector<std::uint8_t> empty;
                    genrender::sendResponseEnvelope(
                            frame, "cef4j_renderer_resp", corrId, messageId, empty.data(), empty.size());
                } else if (!genrender::dispatch(frame, corrId, messageId, payload)) {
                    // Unknown messageId — codegen renderer didn't claim it. Send an error response so the
                    // JVM-side future fails fast instead of hanging on a never-arriving response.
                    genrender::sendReceiverGone(frame, corrId, messageId);
                }
                return 1;
            }
            return 0;
        };
    }
};

// ---------------------------------------------------------------------------
// CefApp. Browser-process and renderer-subprocess share the struct but only the renderer's variant exposes
// get_render_process_handler — `cef_execute_process` queries it once for renderer processes.
// ---------------------------------------------------------------------------

struct App : cef_app_t {
    std::atomic<int> refCount{1};
    RenderProcessHandler* renderProcessHandler;
    App() : cef_app_t{}, renderProcessHandler(new RenderProcessHandler()) {
        initRef<App, cef_app_t>(reinterpret_cast<cef_base_ref_counted_t*>(this));
        get_render_process_handler = [](cef_app_t* self) -> cef_render_process_handler_t* {
            auto* a = reinterpret_cast<App*>(self);
            auto* base = reinterpret_cast<cef_base_ref_counted_t*>(a->renderProcessHandler);
            base->add_ref(base);
            return a->renderProcessHandler;
        };
    }
};

// ---------------------------------------------------------------------------
// CreateBrowserTask — posts cef_browser_host_create_browser onto the UI thread with default settings.
// The new browser arrives via on_after_created (handled by the generated LifeSpanHandlerForwarder), which
// inserts it into tables::browser and emits LifeSpanHandlerOnAfterCreatedEvent. The JVM caller learns the
// new browser's handle by subscribing to that event.
// ---------------------------------------------------------------------------

struct CreateBrowserTask : cef_task_t {
    std::atomic<int> refCount{1};
    std::string url;
    net_kurobako_cef4j_ipc_protocol_gen::BrowserSettings settings;

    CreateBrowserTask(std::string u, net_kurobako_cef4j_ipc_protocol_gen::BrowserSettings s)
            : cef_task_t{}, url(std::move(u)), settings(std::move(s)) {
        initRef<CreateBrowserTask, cef_task_t>(reinterpret_cast<cef_base_ref_counted_t*>(this));
        execute = [](cef_task_t* self) {
            auto* t = reinterpret_cast<CreateBrowserTask*>(self);
            // A request can be queued just before the parent shutdown command reaches TID_UI. Never begin a new
            // browser after shutdown has started: the existing final browser may already have posted the message-loop
            // quit, leaving CEF browser creation racing teardown.
            if (!g_client || g_runtimeShuttingDown) return;

            cef_window_info_t windowInfo{};
#if CEF_VERSION_MAJOR >= 133
            windowInfo.size                       = sizeof(windowInfo);
#endif
            windowInfo.windowless_rendering_enabled = 1;
            windowInfo.bounds.x                   = 0;
            windowInfo.bounds.y                   = 0;
            windowInfo.bounds.width               = 800;
            windowInfo.bounds.height              = 600;

            // Copy decoded BrowserSettings overlay into the native cef_browser_settings_t. We zero-init
            // first so any field the overlay doesn't carry stays at CEF's default. `decltype`-cast handles
            // both same-type ints and enum-typed fields (cef_state_t, cef_color_t etc.) uniformly.
            cef_browser_settings_t native{};
            native.size                  = sizeof(native);
            native.windowless_frame_rate = static_cast<int>(t->settings.windowlessFrameRate);
            native.background_color      = static_cast<cef_color_t>(t->settings.backgroundColor);
            native.remote_fonts          = static_cast<cef_state_t>(t->settings.remoteFonts);
            native.javascript            = static_cast<cef_state_t>(t->settings.javascript);
            native.image_loading         = static_cast<cef_state_t>(t->settings.imageLoading);
            native.local_storage         = static_cast<cef_state_t>(t->settings.localStorage);
            native.webgl                 = static_cast<cef_state_t>(t->settings.webgl);
            native.default_font_size     = static_cast<int>(t->settings.defaultFontSize);
            native.minimum_font_size     = static_cast<int>(t->settings.minimumFontSize);
            // Strings (when non-empty): convert UTF-8 → UTF-16 into the cef_string_t fields.
            if (!t->settings.standardFontFamily.empty()) {
                cef_string_utf8_to_utf16(t->settings.standardFontFamily.data(),
                        t->settings.standardFontFamily.size(), &native.standard_font_family);
            }
            if (!t->settings.defaultEncoding.empty()) {
                cef_string_utf8_to_utf16(t->settings.defaultEncoding.data(),
                        t->settings.defaultEncoding.size(), &native.default_encoding);
            }

            ScopedCefString cefUrl(t->url);
            (void)cef_browser_host_create_browser(
                    &windowInfo, g_client, cefUrl.get(), &native, /*extra_info*/ nullptr,
                    /*request_context*/ nullptr);
        };
    }
};

// ---------------------------------------------------------------------------
// IPC -> CEF dispatch.
// ---------------------------------------------------------------------------

static void onIpcFrameUnchecked(const Header& h, std::vector<std::uint8_t>&& payload) {
    namespace gen = net_kurobako_cef4j_ipc_protocol_gen;

    // Client intercept responses wake up via Kind::InterceptResponse on the IpcServer worker thread; we hand
    // the payload straight to the registry, which signals whichever waiter (CEF UI thread, typically) is
    // blocked on this corrId. Drop everything else with a non-Request kind.
    if (h.kind == Kind::InterceptResponse) {
        g_intercepts.deliverResponse(h.corrId, std::move(payload));
        return;
    }
    if (h.kind != Kind::Request) return;

    switch (h.messageId) {
        case kMsgSessionReady: {
            // Browser creation is deliberately behind a client-originated barrier. Without this, fast CEF startup
            // can emit on_after_created after the server handshake but before the client's session receive handler
            // exists, making startup depend on scheduler timing (especially on loaded CI runners).
            //
            // Send the acknowledgement from the UI task itself. Publishing the process endpoint does not prove that
            // CEF's message loop is accepting cross-thread tasks yet (most visibly on macOS), while acknowledging on
            // the IPC worker can let the client issue browser work that an older CEF strands before its loop starts.
            // The JVM retransmits SessionReady until it receives this idempotent acknowledgement, so post every
            // attempt: if an early task is stranded, a later retry made after the loop starts can still establish the
            // session. Browser bootstrap remains one-shot among the tasks that actually execute.
            const auto corrId = h.corrId;
            const auto messageId = h.messageId;
            cef_post_task(TID_UI, new gendisp::LambdaTask([corrId, messageId]() {
                static std::atomic<bool> bootstrapStarted{false};
                if (!bootstrapStarted.exchange(true)) {
                    net_kurobako_cef4j_ipc_protocol_gen::BrowserSettings settings{};
                    settings.windowlessFrameRate = 30;
                    cef_post_task(TID_UI, new CreateBrowserTask("about:blank", std::move(settings)));
                }
                if (g_ipc) g_ipc->send(Kind::Response, 0, corrId, messageId, nullptr, 0);
            }));
            return;
        }
        case kMsgReleaseHandle: {
            // Drops the server-side retain for `kind=cef_X_t`'s `handle`. The dispatcher's generated switch
            // knows every facade's table; unknown kinds are silently ignored. Ack regardless so the JVM
            // future always completes.
            auto req = gen::ReleaseHandleRequest::decode(payload.data(), payload.size());
            (void)gendisp::dispatchRelease(req.kind, req.handle);
            if (g_ipc) g_ipc->send(Kind::Response, 0, h.corrId, h.messageId, nullptr, 0);
            return;
        }
        case kMsgCreateBrowser: {
            // The decoded request now carries a BrowserSettings overlay; CreateBrowserTask copies its fields
            // into a native cef_browser_settings_t before invoking CEF. The new browser arrives via the
            // LifeSpanHandlerForwarder path so the JVM learns its handle from
            // LifeSpanHandlerOnAfterCreatedEvent. Ack immediately so the request future completes.
            auto req = gen::CreateBrowserRequest::decode(payload.data(), payload.size());
            cef_post_task(TID_UI, new CreateBrowserTask(std::move(req.url), std::move(req.settings)));
            if (g_ipc) g_ipc->send(Kind::Response, 0, h.corrId, h.messageId, nullptr, 0);
            return;
        }
        case kMsgTriggerIntercept: {
            // Test-only fixture for the Kind::Intercept wire. Decode the request, hand it to a detached
            // worker thread so we don't block the IpcServer's single worker (which also delivers the
            // InterceptResponse — blocking here would deadlock). The worker fires the Intercept, blocks on
            // the registry, then sends back the response. Real handler-return callbacks fire on the CEF UI
            // thread, where this worker-thread workaround isn't needed.
            auto req = gen::TriggerInterceptRequest::decode(payload.data(), payload.size());
            std::int32_t origCorrId = h.corrId;
            std::int32_t origMsgId = h.messageId;
            std::int32_t echoMsgId = req.echoMessageId;
            std::vector<std::uint8_t> echoBytes = std::move(req.echoPayload);
            std::thread([origCorrId, origMsgId, echoMsgId, echoBytes = std::move(echoBytes)]() mutable {
                std::int32_t corrId = g_intercepts.allocateCorrId();
                if (g_ipc) {
                    g_ipc->send(Kind::Intercept, 0, corrId, echoMsgId, echoBytes.data(), echoBytes.size());
                }
                std::vector<std::uint8_t> respPayload;
                bool got = g_intercepts.awaitResponse(
                        corrId, std::chrono::milliseconds(5000), respPayload);
                gen::TriggerInterceptResponse out;
                if (got) out.returnedPayload = std::move(respPayload);
                std::vector<std::uint8_t> wire(out.encodedSize());
                out.encodeInto(wire.data());
                if (g_ipc) {
                    g_ipc->send(Kind::Response, 0, origCorrId, origMsgId, wire.data(), wire.size());
                }
            }).detach();
            return;
        }
        case kMsgSetViewportSize: {
            // Update per-browser viewport size and call cef_browser_host_t::was_resized so CEF re-queries
            // get_view_rect (which now reads from g_viewports) and emits a fresh paint at the new size.
            // The was_resized call must run on the CEF UI thread; post a task. Ack the JVM immediately so
            // the layout caller doesn't have to wait for the repaint round-trip.
            auto req = gen::SetViewportSizeRequest::decode(payload.data(), payload.size());
            std::int32_t corrId = h.corrId;
            std::int32_t msgId = h.messageId;
            int width = std::max(1, static_cast<int>(req.width));
            int height = std::max(1, static_cast<int>(req.height));
            cef_browser_t* browser = gendisp::tables::browser.retain(req.browser);
            if (!browser) {
                static const std::uint8_t kReceiverGonePayload[8] = {
                        0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
                if (g_ipc) g_ipc->send(Kind::Error, 0, corrId, msgId,
                                       kReceiverGonePayload, sizeof(kReceiverGonePayload));
                return;
            }
            {
                int id = browser->get_identifier(browser);
                std::lock_guard<std::mutex> g(g_viewportsMu);
                g_viewports[id] = Viewport{width, height};
            }
            cef_post_task(TID_UI, new gendisp::LambdaTask([browser]() {
                auto* host = browser->get_host(browser);
                if (host) {
                    // Mirrors cef4j-inprocess-jfx's requestViewRefresh: notify_screen_info_changed forces CEF to
                    // re-query get_screen_info, was_resized re-queries get_view_rect, invalidate forces a
                    // fresh on_paint even if the page didn't change. About:blank wouldn't otherwise repaint
                    // just because the viewport grew.
                    host->notify_screen_info_changed(host);
                    host->was_resized(host);
                    host->invalidate(host, PET_VIEW);
                    auto* hb = reinterpret_cast<cef_base_ref_counted_t*>(host);
                    hb->release(hb);
                }
                auto* base = reinterpret_cast<cef_base_ref_counted_t*>(browser);
                base->release(base);
            }));
            if (g_ipc) g_ipc->send(Kind::Response, 0, corrId, msgId, nullptr, 0);
            return;
        }
        case kMsgDevToolsAttach: {
            auto req = gen::DevToolsAttachRequest::decode(payload.data(), payload.size());
            std::int32_t browserHandle = req.browser;
            std::int32_t corrId = h.corrId;
            std::int32_t msgId = h.messageId;
            cef_browser_t* browser = gendisp::tables::browser.retain(browserHandle);
            if (!browser) {
                static const std::uint8_t kReceiverGonePayload[8] = {
                        0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
                if (g_ipc) g_ipc->send(Kind::Error, 0, corrId, msgId,
                                       kReceiverGonePayload, sizeof(kReceiverGonePayload));
                return;
            }
            cef_post_task(TID_UI, new gendisp::LambdaTask([browser, browserHandle, corrId, msgId]() {
                auto* host = browser->get_host(browser);
                cef_registration_t* registration = nullptr;
                if (host) {
                    // The CEF C API transfers the incoming observer reference into its C++ CefRefPtr wrapper.
                    // The returned registration owns that observer for the rest of its lifetime; retaining the raw
                    // pointer here and releasing it after the registration is destroyed is a use-after-free.
                    auto* observer = new DevToolsObserver(browserHandle);
                    registration = host->add_dev_tools_message_observer(host, observer);
                    auto* hostBase = reinterpret_cast<cef_base_ref_counted_t*>(host);
                    hostBase->release(hostBase);
                }
                auto* browserBase = reinterpret_cast<cef_base_ref_counted_t*>(browser);
                browserBase->release(browserBase);

                if (!registration) {
                    static const std::uint8_t kReceiverGonePayload[8] = {
                            0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
                    if (g_ipc) g_ipc->send(Kind::Error, 0, corrId, msgId,
                                           kReceiverGonePayload, sizeof(kReceiverGonePayload));
                    return;
                }
                releaseDevToolsRegistration(browserHandle);
                g_devToolsRegistrations.emplace(browserHandle, DevToolsRegistration{registration});
                if (g_ipc) g_ipc->send(Kind::Response, 0, corrId, msgId, nullptr, 0);
            }));
            return;
        }
        case kMsgDevToolsDetach: {
            auto req = gen::DevToolsDetachRequest::decode(payload.data(), payload.size());
            std::int32_t browserHandle = req.browser;
            std::int32_t corrId = h.corrId;
            std::int32_t msgId = h.messageId;
            cef_post_task(TID_UI, new gendisp::LambdaTask([browserHandle, corrId, msgId]() {
                releaseDevToolsRegistration(browserHandle);
                // Releasing the registration queues Chromium-side agent cleanup. Acknowledge from the next UI
                // task so a client that immediately shuts down after the response cannot race browser destruction
                // against that cleanup.
                cef_post_task(TID_UI, new gendisp::LambdaTask([corrId, msgId]() {
                    if (g_ipc) g_ipc->send(Kind::Response, 0, corrId, msgId, nullptr, 0);
                }));
            }));
            return;
        }
        default: {
            // EvaluateJavascript is hand-routed because it relays through the renderer process via a CEF
            // process_message. The JVM corrId is preserved end-to-end: dispatcher → process_message arg →
            // renderer eval → process_message response → Client::on_process_message_received → Kind::Response.
            if (h.messageId == gen::EvaluateJavascriptRequest::kMessageId) {
                auto req = gen::EvaluateJavascriptRequest::decode(payload.data(), payload.size());
                std::int32_t corrId = h.corrId;
                std::int32_t msgId = h.messageId;
                auto* receiver = gendisp::tables::frame.retain(req.frame);
                if (!receiver) {
                    static const std::uint8_t kReceiverGonePayload[8] = {
                            0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
                    if (g_ipc) g_ipc->send(Kind::Error, 0, corrId, msgId,
                                           kReceiverGonePayload, sizeof(kReceiverGonePayload));
                    return;
                }
                std::string code = std::move(req.code);
                bool retainHandle = req.retainHandle;
                cef_post_task(TID_UI, new gendisp::LambdaTask([receiver, corrId, code, retainHandle]() {
                    cef_string_t name{};
                    cef_string_utf8_to_utf16("v8_eval_req", 11, &name);
                    auto* m = cef_process_message_create(&name);
                    cef_string_clear(&name);
                    if (m) {
                        auto* args = m->get_argument_list(m);
                        if (args) {
                            args->set_int(args, 0, corrId);
                            cef_string_t cs{};
                            if (!code.empty()) cef_string_utf8_to_utf16(code.data(), code.size(), &cs);
                            args->set_string(args, 1, &cs);
                            cef_string_clear(&cs);
                            args->set_bool(args, 2, retainHandle);
                            auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(args);
                            ab->release(ab);
                        }
                        receiver->send_process_message(receiver, PID_RENDERER, m);
                    }
                    auto* base = reinterpret_cast<cef_base_ref_counted_t*>(receiver);
                    base->release(base);
                }));
                return;
            }
            // V8 method dispatch — relay to renderer via process_message. Same shape as Eval but the
            // request type tells us which renderer-side handler will run.
            auto relayV8Method = [&](const char* msgName, std::size_t msgNameLen,
                                     std::int32_t frameHandle,
                                     std::function<void(cef_list_value_t*)> packArgs) {
                std::int32_t corrId = h.corrId;
                std::int32_t msgId = h.messageId;
                auto* receiver = gendisp::tables::frame.retain(frameHandle);
                if (!receiver) {
                    static const std::uint8_t kReceiverGonePayload[8] = {
                            0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
                    if (g_ipc) g_ipc->send(Kind::Error, 0, corrId, msgId,
                                           kReceiverGonePayload, sizeof(kReceiverGonePayload));
                    return;
                }
                std::string nameStr(msgName, msgNameLen);
                cef_post_task(TID_UI, new gendisp::LambdaTask(
                                              [receiver, corrId, nameStr, packArgs]() {
                                                  cef_string_t cefName{};
                                                  cef_string_utf8_to_utf16(nameStr.data(), nameStr.size(),
                                                                           &cefName);
                                                  auto* m = cef_process_message_create(&cefName);
                                                  cef_string_clear(&cefName);
                                                  if (m) {
                                                      auto* args = m->get_argument_list(m);
                                                      if (args) {
                                                          args->set_int(args, 0, corrId);
                                                          packArgs(args);
                                                          auto* ab = reinterpret_cast<
                                                                  cef_base_ref_counted_t*>(args);
                                                          ab->release(ab);
                                                      }
                                                      receiver->send_process_message(receiver,
                                                                                     PID_RENDERER, m);
                                                  }
                                                  auto* base =
                                                          reinterpret_cast<cef_base_ref_counted_t*>(receiver);
                                                  base->release(base);
                                              }));
            };
            if (h.messageId == gen::V8GetStringValueRequest::kMessageId) {
                auto req = gen::V8GetStringValueRequest::decode(payload.data(), payload.size());
                std::int32_t v8Handle = req.v8Handle;
                relayV8Method("v8_get_string_req", 17, req.frame,
                              [v8Handle](cef_list_value_t* a) { a->set_int(a, 1, v8Handle); });
                return;
            }
            if (h.messageId == gen::V8GetPropertyRequest::kMessageId) {
                auto req = gen::V8GetPropertyRequest::decode(payload.data(), payload.size());
                std::int32_t v8Handle = req.v8Handle;
                std::string propName = std::move(req.propertyName);
                relayV8Method("v8_get_property_req", 19, req.frame,
                              [v8Handle, propName](cef_list_value_t* a) {
                                  a->set_int(a, 1, v8Handle);
                                  cef_string_t cs{};
                                  if (!propName.empty())
                                      cef_string_utf8_to_utf16(propName.data(), propName.size(), &cs);
                                  a->set_string(a, 2, &cs);
                                  cef_string_clear(&cs);
                              });
                return;
            }
            if (h.messageId == gen::V8ReleaseHandleRequest::kMessageId) {
                auto req = gen::V8ReleaseHandleRequest::decode(payload.data(), payload.size());
                std::int32_t v8Handle = req.v8Handle;
                relayV8Method("v8_release_handle_req", 21, req.frame,
                              [v8Handle](cef_list_value_t* a) { a->set_int(a, 1, v8Handle); });
                return;
            }
            if (h.messageId == gen::V8ExecuteFunctionRequest::kMessageId) {
                auto req = gen::V8ExecuteFunctionRequest::decode(payload.data(), payload.size());
                std::int32_t v8Handle = req.v8Handle;
                std::string argsJson = std::move(req.argsJson);
                relayV8Method("v8_execute_function_req", 23, req.frame,
                              [v8Handle, argsJson](cef_list_value_t* a) {
                                  a->set_int(a, 1, v8Handle);
                                  cef_string_t cs{};
                                  if (!argsJson.empty())
                                      cef_string_utf8_to_utf16(argsJson.data(), argsJson.size(), &cs);
                                  a->set_string(a, 2, &cs);
                                  cef_string_clear(&cs);
                              });
                return;
            }
            if (h.messageId == gen::V8SetPropertyRequest::kMessageId) {
                auto req = gen::V8SetPropertyRequest::decode(payload.data(), payload.size());
                std::int32_t v8Handle = req.v8Handle;
                std::string propName = std::move(req.propertyName);
                std::int32_t valueKind = req.valueKind;
                bool boolValue = req.boolValue;
                std::int32_t intValue = req.intValue;
                std::int64_t doubleBits = req.doubleValue;
                std::string stringValue = std::move(req.stringValue);
                std::int32_t srcHandle = req.valueHandle;
                relayV8Method(
                        "v8_set_property_req", 19, req.frame,
                        [v8Handle, propName, valueKind, boolValue, intValue, doubleBits, stringValue,
                         srcHandle](cef_list_value_t* a) {
                            a->set_int(a, 1, v8Handle);
                            cef_string_t cs{};
                            if (!propName.empty())
                                cef_string_utf8_to_utf16(propName.data(), propName.size(), &cs);
                            a->set_string(a, 2, &cs);
                            cef_string_clear(&cs);
                            a->set_int(a, 3, valueKind);
                            a->set_bool(a, 4, boolValue);
                            a->set_int(a, 5, intValue);
                            a->set_int(a, 6, static_cast<int>(doubleBits & 0xFFFFFFFF));
                            a->set_int(a, 7, static_cast<int>((doubleBits >> 32) & 0xFFFFFFFF));
                            cef_string_t sv{};
                            if (!stringValue.empty())
                                cef_string_utf8_to_utf16(stringValue.data(), stringValue.size(), &sv);
                            a->set_string(a, 8, &sv);
                            cef_string_clear(&sv);
                            a->set_int(a, 9, srcHandle);
                        });
                return;
            }
            if (h.messageId == gen::V8HasPropertyRequest::kMessageId) {
                auto req = gen::V8HasPropertyRequest::decode(payload.data(), payload.size());
                std::int32_t v8Handle = req.v8Handle;
                std::string propName = std::move(req.propertyName);
                relayV8Method("v8_has_property_req", 19, req.frame,
                              [v8Handle, propName](cef_list_value_t* a) {
                                  a->set_int(a, 1, v8Handle);
                                  cef_string_t cs{};
                                  if (!propName.empty())
                                      cef_string_utf8_to_utf16(propName.data(), propName.size(), &cs);
                                  a->set_string(a, 2, &cs);
                                  cef_string_clear(&cs);
                              });
                return;
            }
            if (h.messageId == gen::V8GetKeysRequest::kMessageId) {
                auto req = gen::V8GetKeysRequest::decode(payload.data(), payload.size());
                std::int32_t v8Handle = req.v8Handle;
                relayV8Method("v8_get_keys_req", 15, req.frame,
                              [v8Handle](cef_list_value_t* a) { a->set_int(a, 1, v8Handle); });
                return;
            }
            if (h.messageId == gen::V8GetArrayLengthRequest::kMessageId) {
                auto req = gen::V8GetArrayLengthRequest::decode(payload.data(), payload.size());
                std::int32_t v8Handle = req.v8Handle;
                relayV8Method("v8_get_array_length_req", 23, req.frame,
                              [v8Handle](cef_list_value_t* a) { a->set_int(a, 1, v8Handle); });
                return;
            }
            if (h.messageId == gen::V8GetValueByIndexRequest::kMessageId) {
                auto req = gen::V8GetValueByIndexRequest::decode(payload.data(), payload.size());
                std::int32_t v8Handle = req.v8Handle;
                std::int32_t index = req.index;
                relayV8Method("v8_get_value_by_index_req", 25, req.frame,
                              [v8Handle, index](cef_list_value_t* a) {
                                  a->set_int(a, 1, v8Handle);
                                  a->set_int(a, 2, index);
                              });
                return;
            }
            if (h.messageId == gen::RegisterJsFunctionRequest::kMessageId) {
                auto req = gen::RegisterJsFunctionRequest::decode(payload.data(), payload.size());
                std::string fnName = std::move(req.name);
                std::int32_t callbackId = req.callbackId;
                relayV8Method("js_register_func_req", 20, req.frame,
                              [fnName, callbackId](cef_list_value_t* a) {
                                  cef_string_t cs{};
                                  if (!fnName.empty())
                                      cef_string_utf8_to_utf16(fnName.data(), fnName.size(), &cs);
                                  a->set_string(a, 1, &cs);
                                  cef_string_clear(&cs);
                                  a->set_int(a, 2, callbackId);
                              });
                return;
            }
            // Hand off to the generated dispatcher (covers ~685 AST-derived methods). Tables live as static
            // globals in `gendisp::tables`; the server only seeds `tables::browser` after the initial browser
            // is created.
            gendisp::DispatcherContext ctx;
            ctx.ipc = g_ipc;
            (void)gendisp::dispatch(ctx, h, std::move(payload));
            return;
        }
    }
}

static void onIpcFrame(const Header& h, std::vector<std::uint8_t>&& payload) {
    try {
        onIpcFrameUnchecked(h, std::move(payload));
    } catch (const std::exception& failure) {
        std::string message = failure.what();
        std::vector<std::uint8_t> error(8 + message.size());
        auto writeI32 = [](std::uint8_t* out, std::int32_t value) {
            auto bits = static_cast<std::uint32_t>(value);
            for (int i = 0; i < 4; ++i) out[i] = static_cast<std::uint8_t>(bits >> (i * 8));
        };
        writeI32(error.data(), cef4j::ipc::ErrorCode::MalformedRequest);
        writeI32(error.data() + 4, static_cast<std::int32_t>(message.size()));
        if (!message.empty()) std::memcpy(error.data() + 8, message.data(), message.size());
        if (g_ipc && h.kind == Kind::Request) {
            g_ipc->send(Kind::Error, 0, h.corrId, h.messageId, error.data(), error.size());
        }
    } catch (...) {
        static const std::uint8_t error[8] = {0x02, 0, 0, 0, 0, 0, 0, 0};
        if (g_ipc && h.kind == Kind::Request) {
            g_ipc->send(Kind::Error, 0, h.corrId, h.messageId, error, sizeof(error));
        }
    }
}

// ---------------------------------------------------------------------------
// Entry point.
// ---------------------------------------------------------------------------

static std::string parseOption(int argc, char* argv[], const char* option, const std::string& fallback) {
    for (int i = 1; i + 1 < argc; ++i) {
        if (std::strcmp(argv[i], option) == 0) return argv[i + 1];
    }
    return fallback;
}

static int processId() {
#ifdef _WIN32
    return ::_getpid();
#else
    return static_cast<int>(::getpid());
#endif
}

int main(int argc, char* argv[]) {
#ifdef __APPLE__
    std::string frameworkDirectory;
    std::string frameworkBinary;
    std::vector<std::string> cefArgumentStorage;
    std::vector<char*> cefArguments;
    const std::string frameworkPrefix = "--framework-dir-path=";
    for (int i = 1; i < argc; ++i) {
        std::string arg = argv[i];
        if (arg.rfind(frameworkPrefix, 0) == 0) {
            frameworkDirectory = arg.substr(frameworkPrefix.size());
            break;
        }
    }
    if (frameworkDirectory.empty()) {
        if (const char* configured = std::getenv("CEF_FRAMEWORK_DIR")) {
            frameworkDirectory = configured;
        }
    }
    if (frameworkDirectory.empty()) {
        const std::filesystem::path executableDirectory = std::filesystem::absolute(argv[0]).parent_path();
        frameworkDirectory = (executableDirectory.parent_path() / "Frameworks"
                              / "Chromium Embedded Framework.framework")
                                     .string();
    }
    frameworkBinary = (std::filesystem::path(frameworkDirectory) / "Chromium Embedded Framework").string();
    if (frameworkBinary.empty() || !cef_load_library(frameworkBinary.c_str())) {
        std::fprintf(stderr, "[cef4j-runtime-server] failed to load CEF framework: %s\n", frameworkBinary.c_str());
        return 1;
    }
    cefArgumentStorage.assign(argv, argv + argc);
    const auto hasCefOption = [&cefArgumentStorage](const std::string& prefix) {
        for (const auto& argument : cefArgumentStorage) {
            if (argument.rfind(prefix, 0) == 0) return true;
        }
        return false;
    };
    if (!hasCefOption(frameworkPrefix)) {
        cefArgumentStorage.push_back(frameworkPrefix + frameworkDirectory);
    }
    const std::string resourcesPrefix = "--resources-dir-path=";
    if (!hasCefOption(resourcesPrefix)) {
        cefArgumentStorage.push_back(
                resourcesPrefix + (std::filesystem::path(frameworkDirectory) / "Resources").string());
    }
    cefArguments.reserve(cefArgumentStorage.size());
    for (auto& argument : cefArgumentStorage) cefArguments.push_back(argument.data());
#endif
    // Register the API version we were compiled against. CEF's CToCpp wrappers reject calls otherwise.
    cef4j_verify_api_hash();

    cef_main_args_t args{};
#ifdef _WIN32
    args.instance = ::GetModuleHandleW(nullptr);
#else
#ifdef __APPLE__
    args.argc = static_cast<int>(cefArguments.size());
    args.argv = cefArguments.data();
#else
    args.argc = argc;
    args.argv = argv;
#endif
#endif

    // Subprocess gate: if cef_execute_process returns >= 0, we were a renderer/gpu/utility
    // subprocess and should exit. The same binary is re-execed for these.
    auto* subprocessApp = new App();
    int rc = cef_execute_process(&args, subprocessApp, nullptr);
    if (rc >= 0) return rc;

    std::string transportName = parseOption(argc, argv, "--transport", "zmq");
    std::string frameTransportName = parseOption(argc, argv, "--frame-transport", "shared-file");
    if (frameTransportName != "shared-file" && frameTransportName != "mmap" && frameTransportName != "inline") {
        std::fprintf(stderr, "[cef4j-runtime-server] unknown frame transport: %s\n", frameTransportName.c_str());
        return 1;
    }
    g_useInlineFrames = frameTransportName == "inline";
#ifdef _WIN32
    std::string defaultLocal = "pipe://cef4j-runtime-server-" + std::to_string(processId());
#else
    // Java 11 has no standard UDS API. Loopback ZMQ keeps the portable local client pure Java; callers that accept
    // junixsocket's native shim can explicitly bind local/uds to unix:// instead.
    std::string defaultLocal = "tcp://127.0.0.1:0";
#endif
    std::string defaultBind = transportName == "uds"
                                  ? "unix:///tmp/cef4j-runtime-server-" + std::to_string(processId()) + ".sock"
                              : transportName == "local" ? defaultLocal
                              : transportName == "websocket" ? "ws://127.0.0.1:0/cef4j"
                                                             : "tcp://127.0.0.1:0";
    std::string bindAddr = parseOption(argc, argv, "--bind", defaultBind);

    cef_settings_t settings{};
    settings.size = sizeof(settings);
    settings.no_sandbox = 1;
    settings.windowless_rendering_enabled = 1;
    settings.multi_threaded_message_loop = 0;
    settings.external_message_pump = 0;
    settings.log_severity = LOGSEVERITY_WARNING;

    // Force a unique per-process cache directory. CEF defaults to a shared user-config dir
    // (~/.config/cef_user_data on Linux); without overriding, two server instances racing for
    // browser creation can deadlock on the singleton lock. Each test spawns a fresh server, so
    // a per-pid temp dir gives every instance its own state. Caller can override via
    // CEF4J_RUNTIME_SERVER_CACHE_DIR if they want persistent caches.
    {
        std::string cacheDir;
        if (const char* override = std::getenv("CEF4J_RUNTIME_SERVER_CACHE_DIR")) {
            cacheDir = override;
        } else {
            cacheDir = (std::filesystem::temp_directory_path()
                        / ("cef4j-runtime-server-" + std::to_string(processId()))).string();
        }
        ScopedCefString cachePath(cacheDir);
        cef_string_set(cachePath.get()->str, cachePath.get()->length, &settings.root_cache_path, 1);
    }

    // CEF needs to find its pak files / locales. The integration test points us at the
    // cef-dist Release directory via env; otherwise CEF defaults to argv[0]'s directory.
    std::string resourceDirectory;
    if (const char* configured = std::getenv("CEF_RESOURCES_DIR")) {
        resourceDirectory = configured;
#ifdef __APPLE__
    } else {
        resourceDirectory = (std::filesystem::path(frameworkDirectory) / "Resources").string();
#endif
    }
    if (!resourceDirectory.empty()) {
        ScopedCefString resPath(resourceDirectory);
        cef_string_set(resPath.get()->str, resPath.get()->length, &settings.resources_dir_path, 1);
        std::string localesPath = (std::filesystem::path(resourceDirectory) / "locales").string();
        ScopedCefString locPath(localesPath);
        cef_string_set(locPath.get()->str, locPath.get()->length, &settings.locales_dir_path, 1);
    }
    // Subprocesses re-exec this program. On macOS they must not use the browser-process
    // executable inside the top-level .app: recent Chromium releases can deadlock in
    // cef_initialize while trying to establish process identity that way. The packaged
    // distribution includes a byte-identical executable in a proper helper application bundle.
    // It enters cef_execute_process above before any browser-only server setup.
    {
        std::filesystem::path subprocessPath = std::filesystem::absolute(argv[0]);
#ifdef __APPLE__
        const auto packagedHelper = subprocessPath.parent_path().parent_path() / "Frameworks"
                                  / "cef4j-runtime-server Helper.app" / "Contents" / "MacOS"
                                  / "cef4j-runtime-server Helper";
        if (std::filesystem::exists(packagedHelper)) subprocessPath = packagedHelper;
#endif
        ScopedCefString sp(subprocessPath.string());
        cef_string_set(sp.get()->str, sp.get()->length, &settings.browser_subprocess_path, 1);
    }

    auto* browserApp = new App();
    if (!cef_initialize(&args, &settings, browserApp, nullptr)) {
        std::fprintf(stderr, "[cef4j-runtime-server] cef_initialize failed\n");
        return 1;
    }

    auto ipc = cef4j::ipc::createIpcServer(transportName);
    if (!ipc) {
        std::fprintf(stderr, "[cef4j-runtime-server] unknown transport: %s\n", transportName.c_str());
        cef_shutdown();
        return 1;
    }
    if (const char* bearerToken = std::getenv("CEF4J_WEBSOCKET_BEARER_TOKEN")) {
        if (!ipc->setBearerToken(bearerToken)) {
            std::fprintf(stderr, "[cef4j-runtime-server] selected transport does not support bearer authentication\n");
            cef_shutdown();
            return 1;
        }
    }
    if (!ipc->bind(bindAddr)) {
        std::fprintf(
            stderr, "[cef4j-runtime-server] failed to bind %s using %s\n", bindAddr.c_str(), transportName.c_str());
        cef_shutdown();
        return 1;
    }
    g_ipc                 = ipc.get();
    genhandlers::g_ipc    = ipc.get(); // Generated forwarders fire events through this pointer.

    // Publish the CEF client before the IPC worker can receive SessionReady. Otherwise a fast client can consume the
    // one-shot bootstrap barrier, post CreateBrowserTask, and have that task observe a null g_client before main gets
    // here. The task then returns without creating a browser and subsequent SessionReady messages cannot retry it.
    installLifeSpanHooks();
    auto* client = new Client();
    g_client     = client;
    ipc->start(onIpcFrame);

    // Publish the bound endpoint as soon as the IPC worker is available. SessionReady is the stronger barrier: its
    // acknowledgement is emitted by a task that has executed on CEF's UI loop, and the JVM retransmits it until that
    // happens. Keeping transport discovery separate from UI readiness gives ZMTP time to establish under load while
    // still preventing callers from using a CEF loop that has not started processing work.
    std::printf(
        "CEF4J_RUNTIME_SERVER protocol=1 api=remote-cef cef-api=%d transport=%s frame=%s endpoint=%s "
        "capabilities=remote-cef-api,devtools,osr,input,graceful-shutdown\n",
        CEF_API_VERSION,
        transportName.c_str(),
        frameTransportName.c_str(),
        ipc->endpoint().c_str());
    std::fflush(stdout);

    // RuntimeServerProcess owns this private control pipe. It deliberately sits below every IPC backend so a wedged
    // or already-disconnected ZMQ/UDS/WebSocket session cannot prevent orderly native teardown. The command reader is
    // detached because process exit is the final lifetime boundary; an exact command posts all CEF work to TID_UI.
    std::thread([] {
        std::string command;
        if (std::getline(std::cin, command) && command == "CEF4J_SHUTDOWN") {
            std::fprintf(stderr, "[cef4j-runtime-server] shutdown: parent command received\n");
            cef_post_task(TID_UI, new gendisp::LambdaTask(beginRuntimeShutdown));
        }
    }).detach();

    cef_run_message_loop();
    std::fprintf(stderr, "[cef4j-runtime-server] shutdown: CEF message loop returned\n");
    releaseAllDevToolsRegistrations();
    releaseTrackedBrowsers();
    cef_shutdown();
    std::fprintf(stderr, "[cef4j-runtime-server] shutdown: cef_shutdown complete\n");
    g_ipc = nullptr;
    genhandlers::g_ipc = nullptr;
    std::fprintf(stderr, "[cef4j-runtime-server] shutdown: stopping IPC transport\n");
    ipc->stop();
    std::fprintf(stderr, "[cef4j-runtime-server] shutdown: IPC transport stopped\n");
    return 0;
}
