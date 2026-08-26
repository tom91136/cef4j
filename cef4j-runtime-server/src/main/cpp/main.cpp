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
#include "include/capi/cef_browser_process_handler_capi.h"
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
extern "C" void* cef4jInitializeMacApplication();
extern "C" void cef4jReleaseMacApplication(void* autoreleasePool);
extern "C" void cef4jRunMacMessageLoop();
extern "C" void cef4jQuitMacMessageLoop();
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

#include "gen/Dispatcher.h"
#include "gen/HandlerForwarders.h"
#include "gen/RendererDispatcher.h"

namespace gendisp     = net_kurobako_cef4j_ipc_protocol_gen_dispatcher;
namespace genrender   = net_kurobako_cef4j_ipc_protocol_gen_renderer_dispatcher;
namespace genhandlers = net_kurobako_cef4j_ipc_protocol_gen_handlers;

using cef4j::ipc::IpcServer;
using cef4j::ipc::Header;
using cef4j::ipc::Kind;
using cef4j::ipc::kHeaderSize;
using cef4j::ipc::kNoCorrId;

static constexpr std::int32_t kMsgSessionReady         = 0;
static constexpr std::int32_t kMsgReleaseHandle        = 6;
static constexpr std::int32_t kMsgCreateBrowser        = 7;
static constexpr std::int32_t kMsgTriggerIntercept     = 8;
static constexpr std::int32_t kMsgSetViewportSize      = 25;
static constexpr std::int32_t kMsgDevToolsAttach       = 27;
static constexpr std::int32_t kMsgDevToolsDetach       = 30;

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

static IpcServer* g_ipc       = nullptr;
static cef_client_t* g_client = nullptr;
static std::function<void()> g_publishEndpoint;
static bool g_contextInitialized = false;
static bool g_endpointPublished = false;
inline cef4j::ipc::InterceptRegistry& g_intercepts = cef4j::ipc::intercepts();
struct InterceptWorker {
    std::thread thread;
    std::unique_ptr<std::atomic<bool>> finished;
};
static std::mutex g_interceptWorkersMutex;
static std::vector<InterceptWorker> g_interceptWorkers;

static void startInterceptWorker(std::function<void()> work) {
    std::vector<std::thread> completed;
    {
        std::lock_guard<std::mutex> lock(g_interceptWorkersMutex);
        for (auto it = g_interceptWorkers.begin(); it != g_interceptWorkers.end();) {
            if (it->finished->load(std::memory_order_acquire)) {
                completed.push_back(std::move(it->thread));
                it = g_interceptWorkers.erase(it);
            } else {
                ++it;
            }
        }
        g_interceptWorkers.emplace_back();
        auto& worker = g_interceptWorkers.back();
        try {
            worker.finished = std::make_unique<std::atomic<bool>>(false);
            auto* finishedFlag = worker.finished.get();
            worker.thread = std::thread([work = std::move(work), finishedFlag]() mutable {
                try {
                    work();
                } catch (const std::exception& failure) {
                    std::fprintf(stderr, "[cef4j-runtime-server] intercept worker failed: %s\n", failure.what());
                } catch (...) {
                    std::fprintf(stderr, "[cef4j-runtime-server] intercept worker failed\n");
                }
                finishedFlag->store(true, std::memory_order_release);
            });
        } catch (...) {
            g_interceptWorkers.pop_back();
            throw;
        }
    }
    for (auto& worker : completed) worker.join();
}

static void joinInterceptWorkers() {
    std::vector<InterceptWorker> workers;
    {
        std::lock_guard<std::mutex> lock(g_interceptWorkersMutex);
        workers.swap(g_interceptWorkers);
    }
    for (auto& worker : workers) {
        if (worker.thread.joinable()) worker.thread.join();
    }
}

static std::unordered_map<int, cef_browser_t*> g_liveBrowsers;
static bool g_runtimeShuttingDown = false;
static bool g_runtimeQuitPosted = false;
static decltype(genhandlers::g_lifeSpanHandlerForwarder.on_after_created) g_forwardOnAfterCreated = nullptr;
static decltype(genhandlers::g_lifeSpanHandlerForwarder.do_close) g_forwardDoClose = nullptr;
static decltype(genhandlers::g_lifeSpanHandlerForwarder.on_before_close) g_forwardOnBeforeClose = nullptr;

static cef_browser_t* canonicalBrowser(cef_browser_t* browser) {
    if (!browser) return nullptr;
    auto existing = g_liveBrowsers.find(browser->get_identifier(browser));
    return existing == g_liveBrowsers.end() ? browser : existing->second;
}

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
static void releaseBrowserState(cef_browser_t* browser);
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
        releaseBrowserState(browser);
        bool removed = untrackBrowser(browser);
        if (g_runtimeShuttingDown && removed && g_liveBrowsers.empty() && !g_runtimeQuitPosted) {
            g_runtimeQuitPosted = true;
            std::fprintf(stderr, "[cef4j-runtime-server] shutdown: final browser closed\n");
            finishRuntimeShutdown();
        }
    };
}

static void releaseTrackedBrowsers() {
    g_liveBrowsers.clear();
}

static constexpr int kMaxViewportDimension = 8192;
static constexpr std::int64_t kMaxViewportPixels = 3840LL * 2160LL;
static constexpr std::size_t kMaxOsrBytes = static_cast<std::size_t>(kMaxViewportPixels) * 4;

static constexpr std::size_t kOsrShrinkRatio = 4;
static constexpr std::size_t kOsrMinBytes = 256 * 256 * 4;

static std::mutex g_osrBuffersMu;
static std::unordered_map<int, std::unique_ptr<cef4j::ipc::OsrPaintBuffer>> g_osrBuffers;
static std::atomic<std::int64_t> g_inlineFrameSequence{0};
static bool g_useInlineFrames = false;

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
    int browserIdentifier;
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

static void releaseBrowserState(cef_browser_t* browser) {
    if (!browser) return;
    int identifier = browser->get_identifier(browser);
    {
        std::lock_guard<std::mutex> lock(g_osrBuffersMu);
        g_osrBuffers.erase(identifier);
    }
    {
        std::lock_guard<std::mutex> lock(g_viewportsMu);
        g_viewports.erase(identifier);
    }
    for (auto it = g_devToolsRegistrations.begin(); it != g_devToolsRegistrations.end();) {
        if (it->second.browserIdentifier != identifier) {
            ++it;
            continue;
        }
        auto* registrationBase = reinterpret_cast<cef_base_ref_counted_t*>(it->second.registration);
        registrationBase->release(registrationBase);
        it = g_devToolsRegistrations.erase(it);
    }
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
            int browserIdentifier = browser->get_identifier(browser);
            std::size_t byteCount = static_cast<std::size_t>(width) * height * 4;
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
            std::size_t targetBytes = byteCount;
            if (targetBytes < kOsrMinBytes) targetBytes = kOsrMinBytes;
            if (targetBytes > kMaxOsrBytes) targetBytes = kMaxOsrBytes;
            cef4j::ipc::OsrPaintBuffer* buf = nullptr;
            {
                std::lock_guard<std::mutex> g(g_osrBuffersMu);
                auto it = g_osrBuffers.find(browserIdentifier);
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
                        auto [inserted, _] = g_osrBuffers.emplace(browserIdentifier, std::move(fresh));
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
                g_ipc->sendLatest(Kind::Event, 0, kNoCorrId,
                                  net_kurobako_cef4j_ipc_protocol_gen::OsrPaintEvent::kMessageId,
                                  wire.data(), wire.size(), handleId);
            }
        };
    }
};


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
                std::int32_t handleId = gendisp::tables::browser.insert(canonicalBrowser(browser));
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
                } else if (name == "v8_execute_function_resp") {
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
                } else { // v8_get_value_by_index_resp
                    net_kurobako_cef4j_ipc_protocol_gen::V8GetValueByIndexResponse resp;
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
                                net_kurobako_cef4j_ipc_protocol_gen::V8GetValueByIndexResponse::kMessageId,
                                wire.data(), wire.size());
                    }
                }
                return 1;
            }
            if (name == "v8_set_property_resp") {
                auto* args = msg->get_argument_list(msg);
                if (!args || args->get_size(args) < 2) {
                    if (args) {
                        auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(args);
                        ab->release(ab);
                    }
                    return 1;
                }
                std::int32_t corrId = args->get_int(args, 0);
                bool ok = args->get_bool(args, 1) != 0;
                std::string errorMessage;
                if (args->get_size(args) >= 3) {
                    cef_string_userfree_t s = args->get_string(args, 2);
                    if (s) {
                        cef_string_utf8_t u{};
                        cef_string_utf16_to_utf8(s->str, s->length, &u);
                        errorMessage.assign(u.str, u.length);
                        cef_string_utf8_clear(&u);
                        cef_string_userfree_free(s);
                    }
                }
                auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(args);
                ab->release(ab);
                net_kurobako_cef4j_ipc_protocol_gen::V8SetPropertyResponse resp;
                resp.ok = ok;
                resp.errorMessage = std::move(errorMessage);
                std::vector<std::uint8_t> wire(resp.encodedSize());
                resp.encodeInto(wire.data());
                if (g_ipc) {
                    g_ipc->send(Kind::Response, 0, corrId,
                                net_kurobako_cef4j_ipc_protocol_gen::V8SetPropertyResponse::kMessageId,
                                wire.data(), wire.size());
                }
                return 1;
            }
            if (name == "v8_has_property_resp") {
                auto* args = msg->get_argument_list(msg);
                if (!args || args->get_size(args) < 2) {
                    if (args) {
                        auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(args);
                        ab->release(ab);
                    }
                    return 1;
                }
                std::int32_t corrId = args->get_int(args, 0);
                bool has = args->get_bool(args, 1) != 0;
                auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(args);
                ab->release(ab);
                net_kurobako_cef4j_ipc_protocol_gen::V8HasPropertyResponse resp;
                resp.has = has;
                std::vector<std::uint8_t> wire(resp.encodedSize());
                resp.encodeInto(wire.data());
                if (g_ipc) {
                    g_ipc->send(Kind::Response, 0, corrId,
                                net_kurobako_cef4j_ipc_protocol_gen::V8HasPropertyResponse::kMessageId,
                                wire.data(), wire.size());
                }
                return 1;
            }
            if (name == "v8_get_keys_resp") {
                auto* args = msg->get_argument_list(msg);
                if (!args || args->get_size(args) < 3) {
                    if (args) {
                        auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(args);
                        ab->release(ab);
                    }
                    return 1;
                }
                std::int32_t corrId = args->get_int(args, 0);
                bool ok = args->get_bool(args, 1) != 0;
                std::int32_t count = args->get_int(args, 2);
                net_kurobako_cef4j_ipc_protocol_gen::V8GetKeysResponse resp;
                resp.ok = ok;
                for (std::int32_t i = 0; i < count; ++i) {
                    std::string key;
                    cef_string_userfree_t s = args->get_string(args, 3 + i);
                    if (s) {
                        cef_string_utf8_t u{};
                        cef_string_utf16_to_utf8(s->str, s->length, &u);
                        key.assign(u.str, u.length);
                        cef_string_utf8_clear(&u);
                        cef_string_userfree_free(s);
                    }
                    resp.keys.push_back(std::move(key));
                }
                auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(args);
                ab->release(ab);
                std::vector<std::uint8_t> wire(resp.encodedSize());
                resp.encodeInto(wire.data());
                if (g_ipc) {
                    g_ipc->send(Kind::Response, 0, corrId,
                                net_kurobako_cef4j_ipc_protocol_gen::V8GetKeysResponse::kMessageId,
                                wire.data(), wire.size());
                }
                return 1;
            }
            if (name == "v8_get_array_length_resp") {
                auto* args = msg->get_argument_list(msg);
                if (!args || args->get_size(args) < 3) {
                    if (args) {
                        auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(args);
                        ab->release(ab);
                    }
                    return 1;
                }
                std::int32_t corrId = args->get_int(args, 0);
                bool ok = args->get_bool(args, 1) != 0;
                std::int32_t length = args->get_int(args, 2);
                auto* ab = reinterpret_cast<cef_base_ref_counted_t*>(args);
                ab->release(ab);
                net_kurobako_cef4j_ipc_protocol_gen::V8GetArrayLengthResponse resp;
                resp.ok = ok;
                resp.length = length;
                std::vector<std::uint8_t> wire(resp.encodedSize());
                resp.encodeInto(wire.data());
                if (g_ipc) {
                    g_ipc->send(Kind::Response, 0, corrId,
                                net_kurobako_cef4j_ipc_protocol_gen::V8GetArrayLengthResponse::kMessageId,
                                wire.data(), wire.size());
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
            if (name == "cef4j_renderer_resp" || name == "cef4j_renderer_err") {
                Kind kind = name == "cef4j_renderer_err" ? Kind::Error : Kind::Response;
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
#ifdef __APPLE__
    cef4jQuitMacMessageLoop();
#else
    cef_quit_message_loop();
#endif
}

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
    if (retainHandle) {
        r.valueKind = 4; // still kind=4 for a complex value; caller distinguishes via valueHandle != 0
        r.valueHandle = gendisp::tables::v8Value.insert(retval);
        return r;
    }
    r.valueKind = 4;
    r.stringValue = "[unsupported v8 value]";
    return r;
}


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
            if (valueHandle != 0) return gendisp::tables::v8Value.find(valueHandle);
            cef_string_t cs{};
            if (!stringValue.empty()) cef_string_utf8_to_utf16(stringValue.data(), stringValue.size(), &cs);
            cef_v8_value_t* v = cef_v8_value_create_string(&cs);
            cef_string_clear(&cs);
            return v;
        }
        default:
            if (valueHandle != 0) return gendisp::tables::v8Value.find(valueHandle);
            return nullptr;
    }
}

static void handleV8SetPropertyReq(cef_frame_t* frame, cef_process_message_t* msg) {
    auto* args = msg->get_argument_list(msg);
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
                }
            }
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
            if (!frame || !frame->is_valid(frame) || !frame->is_main(frame)) return;
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
                if (messageId == net_kurobako_cef4j_ipc_protocol_gen::RendererReleaseHandleRequest::kMessageId) {
                    auto req = net_kurobako_cef4j_ipc_protocol_gen::RendererReleaseHandleRequest::decode(
                            payload.data(), payload.size());
                    (void)gendisp::dispatchRelease(req.kind, req.handle);
                    std::vector<std::uint8_t> empty;
                    genrender::sendResponseEnvelope(
                            frame, "cef4j_renderer_resp", corrId, messageId, empty.data(), empty.size());
                } else if (!genrender::dispatch(frame, corrId, messageId, payload)) {
                    genrender::sendReceiverGone(frame, corrId, messageId);
                }
                return 1;
            }
            return 0;
        };
    }
};


struct BrowserProcessHandler : cef_browser_process_handler_t {
    std::atomic<int> refCount{1};

    BrowserProcessHandler() : cef_browser_process_handler_t{} {
        initRef<BrowserProcessHandler, cef_browser_process_handler_t>(
                reinterpret_cast<cef_base_ref_counted_t*>(this));
        on_context_initialized = [](cef_browser_process_handler_t*) {
            g_contextInitialized = true;
            if (g_publishEndpoint && !g_endpointPublished) {
                g_endpointPublished = true;
                g_publishEndpoint();
            }
        };
    }
};

struct App : cef_app_t {
    std::atomic<int> refCount{1};
    BrowserProcessHandler* browserProcessHandler;
    RenderProcessHandler* renderProcessHandler;
    App()
            : cef_app_t{}, browserProcessHandler(new BrowserProcessHandler()),
              renderProcessHandler(new RenderProcessHandler()) {
        initRef<App, cef_app_t>(reinterpret_cast<cef_base_ref_counted_t*>(this));
        get_browser_process_handler = [](cef_app_t* self) -> cef_browser_process_handler_t* {
            auto* a = reinterpret_cast<App*>(self);
            auto* base = reinterpret_cast<cef_base_ref_counted_t*>(a->browserProcessHandler);
            base->add_ref(base);
            return a->browserProcessHandler;
        };
        get_render_process_handler = [](cef_app_t* self) -> cef_render_process_handler_t* {
            auto* a = reinterpret_cast<App*>(self);
            auto* base = reinterpret_cast<cef_base_ref_counted_t*>(a->renderProcessHandler);
            base->add_ref(base);
            return a->renderProcessHandler;
        };
#if defined(__APPLE__) && CEF_VERSION_MAJOR <= 109
        on_before_command_line_processing = [](cef_app_t*, const cef_string_t*, cef_command_line_t* commandLine) {
            ScopedCefString disableGpu("disable-gpu");
            commandLine->append_switch(commandLine, disableGpu.get());
        };
#endif
    }
};


struct CreateBrowserTask : cef_task_t {
    std::atomic<int> refCount{1};
    std::string url;
    net_kurobako_cef4j_ipc_protocol_gen::BrowserSettings settings;
    std::int32_t corrId;
    std::int32_t messageId;

    CreateBrowserTask(std::string u, net_kurobako_cef4j_ipc_protocol_gen::BrowserSettings s,
                      std::int32_t correlation = kNoCorrId, std::int32_t message = 0)
            : cef_task_t{}, url(std::move(u)), settings(std::move(s)), corrId(correlation), messageId(message) {
        initRef<CreateBrowserTask, cef_task_t>(reinterpret_cast<cef_base_ref_counted_t*>(this));
        execute = [](cef_task_t* self) {
            auto* t = reinterpret_cast<CreateBrowserTask*>(self);
            bool accepted = t->createBrowser();
            if (t->corrId == kNoCorrId) return;
            if (accepted) {
                if (g_ipc) g_ipc->send(Kind::Response, 0, t->corrId, t->messageId, nullptr, 0);
            } else {
                gendisp::sendTaskRejected(g_ipc, t->corrId, t->messageId);
            }
        };
    }

    bool createBrowser() {
        if (!g_client || g_runtimeShuttingDown) return false;

        cef_window_info_t windowInfo{};
#if CEF_VERSION_MAJOR >= 133
        windowInfo.size = sizeof(windowInfo);
#endif
        windowInfo.windowless_rendering_enabled = 1;
        windowInfo.bounds.x = 0;
        windowInfo.bounds.y = 0;
        windowInfo.bounds.width = 800;
        windowInfo.bounds.height = 600;

        cef_browser_settings_t native{};
        native.size = sizeof(native);
        native.windowless_frame_rate = static_cast<int>(settings.windowlessFrameRate);
        native.background_color = static_cast<cef_color_t>(settings.backgroundColor);
        native.remote_fonts = static_cast<cef_state_t>(settings.remoteFonts);
        native.javascript = static_cast<cef_state_t>(settings.javascript);
        native.image_loading = static_cast<cef_state_t>(settings.imageLoading);
        native.local_storage = static_cast<cef_state_t>(settings.localStorage);
        native.webgl = static_cast<cef_state_t>(settings.webgl);
        native.default_font_size = static_cast<int>(settings.defaultFontSize);
        native.minimum_font_size = static_cast<int>(settings.minimumFontSize);
        if (!settings.standardFontFamily.empty()) {
            cef_string_utf8_to_utf16(settings.standardFontFamily.data(),
                        settings.standardFontFamily.size(), &native.standard_font_family);
        }
        if (!settings.defaultEncoding.empty()) {
            cef_string_utf8_to_utf16(settings.defaultEncoding.data(),
                        settings.defaultEncoding.size(), &native.default_encoding);
        }

        ScopedCefString cefUrl(url);
        return cef_browser_host_create_browser(&windowInfo, g_client, cefUrl.get(), &native, nullptr, nullptr);
    }
};


static void onIpcFrameUnchecked(const Header& h, std::vector<std::uint8_t>&& payload) {
    namespace gen = net_kurobako_cef4j_ipc_protocol_gen;

    if (h.kind == Kind::InterceptResponse) {
        g_intercepts.deliverResponse(h.corrId, std::move(payload));
        return;
    }
    if (h.kind != Kind::Request) return;

    switch (h.messageId) {
        case kMsgSessionReady: {
            const auto corrId = h.corrId;
            const auto messageId = h.messageId;
            auto* task = new gendisp::LambdaTask([corrId, messageId]() {
                static std::atomic<bool> bootstrapStarted{false};
                if (!bootstrapStarted.exchange(true)) {
                    net_kurobako_cef4j_ipc_protocol_gen::BrowserSettings settings{};
                    settings.windowlessFrameRate = 30;
                    auto* create = new CreateBrowserTask("about:blank", std::move(settings));
                    bool accepted = create->createBrowser();
                    auto* base = reinterpret_cast<cef_base_ref_counted_t*>(create);
                    base->release(base);
                    if (!accepted) {
                        bootstrapStarted.store(false);
                        gendisp::sendTaskRejected(g_ipc, corrId, messageId);
                        return;
                    }
                }
                if (g_ipc) g_ipc->send(Kind::Response, 0, corrId, messageId, nullptr, 0);
            });
            if (!gendisp::postUiTask(task)) gendisp::sendTaskRejected(g_ipc, corrId, messageId);
            return;
        }
        case kMsgReleaseHandle: {
            auto req = gen::ReleaseHandleRequest::decode(payload.data(), payload.size());
            (void)gendisp::dispatchRelease(req.kind, req.handle);
            if (g_ipc) g_ipc->send(Kind::Response, 0, h.corrId, h.messageId, nullptr, 0);
            return;
        }
        case kMsgCreateBrowser: {
            auto req = gen::CreateBrowserRequest::decode(payload.data(), payload.size());
            auto* task = new CreateBrowserTask(
                    std::move(req.url), std::move(req.settings), h.corrId, h.messageId);
            if (!gendisp::postUiTask(task)) gendisp::sendTaskRejected(g_ipc, h.corrId, h.messageId);
            return;
        }
        case kMsgTriggerIntercept: {
            auto req = gen::TriggerInterceptRequest::decode(payload.data(), payload.size());
            std::int32_t origCorrId = h.corrId;
            std::int32_t origMsgId = h.messageId;
            std::int32_t echoMsgId = req.echoMessageId;
            std::vector<std::uint8_t> echoBytes = std::move(req.echoPayload);
            startInterceptWorker([origCorrId, origMsgId, echoMsgId, echoBytes = std::move(echoBytes)]() mutable {
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
            });
            return;
        }
        case kMsgSetViewportSize: {
            auto req = gen::SetViewportSizeRequest::decode(payload.data(), payload.size());
            std::int32_t corrId = h.corrId;
            std::int32_t msgId = h.messageId;
            int width = static_cast<int>(req.width);
            int height = static_cast<int>(req.height);
            std::int64_t pixels = static_cast<std::int64_t>(width) * height;
            if (width <= 0 || height <= 0 || width > kMaxViewportDimension || height > kMaxViewportDimension
                    || pixels > kMaxViewportPixels) {
                throw std::invalid_argument("viewport dimensions exceed runtime limits");
            }
            cef_browser_t* browser = gendisp::tables::browser.retain(req.browser);
            if (!browser) {
                static const std::uint8_t kReceiverGonePayload[8] = {
                        0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
                if (g_ipc) g_ipc->send(Kind::Error, 0, corrId, msgId,
                                       kReceiverGonePayload, sizeof(kReceiverGonePayload));
                return;
            }
            auto* task = new gendisp::LambdaTask([browser, corrId, msgId, width, height]() {
                int id = browser->get_identifier(browser);
                {
                    std::lock_guard<std::mutex> g(g_viewportsMu);
                    g_viewports[id] = Viewport{width, height};
                }
                auto* host = browser->get_host(browser);
                if (host) {
                    host->notify_screen_info_changed(host);
                    host->was_resized(host);
                    host->invalidate(host, PET_VIEW);
                    auto* hb = reinterpret_cast<cef_base_ref_counted_t*>(host);
                    hb->release(hb);
                }
                auto* base = reinterpret_cast<cef_base_ref_counted_t*>(browser);
                base->release(base);
                if (g_ipc) g_ipc->send(Kind::Response, 0, corrId, msgId, nullptr, 0);
            });
            if (!gendisp::postUiTask(task)) {
                auto* base = reinterpret_cast<cef_base_ref_counted_t*>(browser);
                base->release(base);
                gendisp::sendTaskRejected(g_ipc, corrId, msgId);
            }
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
            auto* task = new gendisp::LambdaTask([browser, browserHandle, corrId, msgId]() {
                int browserIdentifier = browser->get_identifier(browser);
                auto* host = browser->get_host(browser);
                cef_registration_t* registration = nullptr;
                if (host) {
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
                g_devToolsRegistrations.emplace(
                        browserHandle, DevToolsRegistration{registration, browserIdentifier});
                if (g_ipc) g_ipc->send(Kind::Response, 0, corrId, msgId, nullptr, 0);
            });
            if (!gendisp::postUiTask(task)) {
                auto* base = reinterpret_cast<cef_base_ref_counted_t*>(browser);
                base->release(base);
                gendisp::sendTaskRejected(g_ipc, corrId, msgId);
            }
            return;
        }
        case kMsgDevToolsDetach: {
            auto req = gen::DevToolsDetachRequest::decode(payload.data(), payload.size());
            std::int32_t browserHandle = req.browser;
            std::int32_t corrId = h.corrId;
            std::int32_t msgId = h.messageId;
            auto* task = new gendisp::LambdaTask([browserHandle, corrId, msgId]() {
                releaseDevToolsRegistration(browserHandle);
                auto* acknowledge = new gendisp::LambdaTask([corrId, msgId]() {
                    if (g_ipc) g_ipc->send(Kind::Response, 0, corrId, msgId, nullptr, 0);
                });
                if (!gendisp::postUiTask(acknowledge)) gendisp::sendTaskRejected(g_ipc, corrId, msgId);
            });
            if (!gendisp::postUiTask(task)) gendisp::sendTaskRejected(g_ipc, corrId, msgId);
            return;
        }
        default: {
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
                auto* task = new gendisp::LambdaTask([receiver, corrId, code, retainHandle]() {
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
                });
                if (!gendisp::postUiTask(task)) {
                    auto* base = reinterpret_cast<cef_base_ref_counted_t*>(receiver);
                    base->release(base);
                    gendisp::sendTaskRejected(g_ipc, corrId, msgId);
                }
                return;
            }
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
                auto* task = new gendisp::LambdaTask(
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
                                              });
                if (!gendisp::postUiTask(task)) {
                    auto* base = reinterpret_cast<cef_base_ref_counted_t*>(receiver);
                    base->release(base);
                    gendisp::sendTaskRejected(g_ipc, corrId, msgId);
                }
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
    struct MacApplicationScope {
        ~MacApplicationScope() {
            if (autoreleasePool) cef4jReleaseMacApplication(autoreleasePool);
        }

        void* autoreleasePool = nullptr;
    } macApplication;
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
        const auto frameworkName = std::filesystem::path("Chromium Embedded Framework.framework");
        const auto browserFramework = executableDirectory.parent_path() / "Frameworks" / frameworkName;
        if (std::filesystem::exists(browserFramework)) {
            frameworkDirectory = browserFramework.string();
        } else {
            frameworkDirectory = (executableDirectory.parent_path().parent_path().parent_path() / frameworkName)
                                     .string();
        }
    }
    frameworkBinary = (std::filesystem::path(frameworkDirectory) / "Chromium Embedded Framework").string();
    if (frameworkBinary.empty() || !cef_load_library(frameworkBinary.c_str())) {
        std::fprintf(stderr, "[cef4j-runtime-server] failed to load CEF framework: %s\n", frameworkBinary.c_str());
        return 1;
    }
    bool isSubprocess = false;
    for (int i = 1; i < argc; ++i) {
        if (std::strncmp(argv[i], "--type=", 7) == 0) {
            isSubprocess = true;
            break;
        }
    }
    if (!isSubprocess) {
        std::fprintf(stderr, "[cef4j-runtime-server] macOS application bootstrap: begin\n");
        macApplication.autoreleasePool = cef4jInitializeMacApplication();
        if (!macApplication.autoreleasePool) return 1;
        std::fprintf(stderr, "[cef4j-runtime-server] macOS application bootstrap: complete\n");
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
    // XXX: Java 11 has no standard UDS API; keep loopback ZMQ as the portable local default until Java 11 support ends.
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
#ifdef __APPLE__
    std::fprintf(stderr, "[cef4j-runtime-server] cef_initialize: begin\n");
#endif
    if (!cef_initialize(&args, &settings, browserApp, nullptr)) {
        std::fprintf(stderr, "[cef4j-runtime-server] cef_initialize failed\n");
        return 1;
    }
#ifdef __APPLE__
    std::fprintf(stderr, "[cef4j-runtime-server] cef_initialize: complete\n");
#endif

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
    genhandlers::g_ipc = ipc.get();

    installLifeSpanHooks();
    auto* client = new Client();
    g_client     = client;
    ipc->start(onIpcFrame);

    const std::string advertisedEndpoint = ipc->endpoint();
    g_publishEndpoint = [transportName, frameTransportName, advertisedEndpoint]() {
        std::fprintf(stderr, "[cef4j-runtime-server] CEF context initialized; publishing endpoint\n");
        std::printf(
            "CEF4J_RUNTIME_SERVER protocol=1 api=remote-cef cef-api=%d transport=%s frame=%s endpoint=%s "
            "capabilities=remote-cef-api,devtools,osr,input,graceful-shutdown\n",
            CEF_API_VERSION,
            transportName.c_str(),
            frameTransportName.c_str(),
            advertisedEndpoint.c_str());
        std::fflush(stdout);
    };
    if (g_contextInitialized) {
        auto* publishEndpoint = new gendisp::LambdaTask([]() {
            if (g_publishEndpoint && !g_endpointPublished) {
                g_endpointPublished = true;
                g_publishEndpoint();
            }
        });
        if (!gendisp::postUiTask(publishEndpoint)) {
            std::fprintf(stderr, "[cef4j-runtime-server] failed to schedule context-ready endpoint publication\n");
            ipc->stop();
            g_ipc = nullptr;
            genhandlers::g_ipc = nullptr;
            cef_shutdown();
            return 1;
        }
    }

    std::thread([] {
        std::string command;
        if (std::getline(std::cin, command) && command == "CEF4J_SHUTDOWN") {
            std::fprintf(stderr, "[cef4j-runtime-server] shutdown: parent command received\n");
            (void)gendisp::postUiTask(new gendisp::LambdaTask(beginRuntimeShutdown));
        }
    }).detach();

#ifdef __APPLE__
    cef4jRunMacMessageLoop();
#else
    cef_run_message_loop();
#endif
    std::fprintf(stderr, "[cef4j-runtime-server] shutdown: CEF message loop returned\n");
    releaseAllDevToolsRegistrations();
    releaseTrackedBrowsers();
    joinInterceptWorkers();
    std::fprintf(stderr, "[cef4j-runtime-server] shutdown: stopping IPC transport\n");
    g_publishEndpoint = {};
    g_contextInitialized = false;
    g_endpointPublished = false;
    ipc->stop();
    std::fprintf(stderr, "[cef4j-runtime-server] shutdown: IPC transport stopped\n");
    g_ipc = nullptr;
    genhandlers::g_ipc = nullptr;
    cef_shutdown();
    std::fprintf(stderr, "[cef4j-runtime-server] shutdown: cef_shutdown complete\n");
    return 0;
}
