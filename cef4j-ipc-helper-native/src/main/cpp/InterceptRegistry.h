// Helper→JVM synchronous callback infrastructure. The helper sends a Kind::Intercept
// frame and blocks until the matching Kind::InterceptResponse arrives (or timeout).
// Used for handler callbacks whose return value the JVM must supply: OnBeforePopup,
// DoClose, OnJsDialog, OnBeforeBrowse, etc.
//
// Lifecycle of one intercept:
//   1. CEF UI thread calls a handler forwarder lambda.
//   2. Lambda calls allocateCorrId(), encodes the request, IpcServer::send(Kind::Intercept).
//   3. Lambda calls awaitResponse(corrId, timeout, out). The CEF UI thread blocks here.
//   4. JVM receives Intercept, runs handler, sends Kind::InterceptResponse with same corrId.
//   5. IpcServer worker delivers it to deliverResponse(corrId, payload).
//   6. awaitResponse wakes, fills out, returns true. Lambda decodes, returns to CEF.
//
// On timeout: awaitResponse returns false; the forwarder uses a sane default (return 0
// for "no opinion" bool callbacks) so CEF gets a deterministic answer regardless of
// JVM responsiveness. Blocking the CEF UI thread is safe ONLY because we have a hard
// timeout — without one, an unresponsive JVM would deadlock the helper.
#pragma once

#include <atomic>
#include <chrono>
#include <condition_variable>
#include <cstdint>
#include <mutex>
#include <unordered_map>
#include <utility>
#include <vector>

namespace cef4j {
namespace ipc {

class InterceptRegistry {
public:
    InterceptRegistry() = default;
    InterceptRegistry(const InterceptRegistry&) = delete;
    InterceptRegistry& operator=(const InterceptRegistry&) = delete;

    // Reserves a fresh corrId for a pending intercept. Must be paired with awaitResponse on
    // the same thread, or the helper leaks an entry until shutdown.
    std::int32_t allocateCorrId() {
        std::int32_t id = nextCorrId_.fetch_add(1, std::memory_order_relaxed);
        // corrId is signed int32 on the wire; avoid wrapping into negative space (we use -1 as
        // the kNoCorrId sentinel elsewhere). Wrap back to 1 well before INT32_MAX.
        if (id <= 0) {
            nextCorrId_.store(2, std::memory_order_relaxed);
            id = 1;
        }
        // try_emplace default-constructs Slot in place; std::condition_variable inside is non-movable so
        // we can't pass a `Slot{}` rvalue here.
        std::lock_guard<std::mutex> g(mu_);
        waiters_.try_emplace(id);
        return id;
    }

    // Blocks for up to `timeout`. Returns true if a response arrived, false on timeout.
    // Either way the slot is removed; calling awaitResponse twice on the same corrId is a bug.
    bool awaitResponse(std::int32_t corrId,
                       std::chrono::milliseconds timeout,
                       std::vector<std::uint8_t>& out) {
        std::unique_lock<std::mutex> g(mu_);
        auto it = waiters_.find(corrId);
        if (it == waiters_.end()) return false;
        Slot& slot = it->second;
        bool got = slot.cv.wait_for(g, timeout, [&] { return slot.done; });
        if (got) out = std::move(slot.payload);
        waiters_.erase(it);
        return got;
    }

    // Called by the IpcServer worker when a Kind::InterceptResponse arrives. If the corrId
    // doesn't match any waiter (already timed out, or unknown), the payload is dropped.
    void deliverResponse(std::int32_t corrId, std::vector<std::uint8_t> payload) {
        std::lock_guard<std::mutex> g(mu_);
        auto it = waiters_.find(corrId);
        if (it == waiters_.end()) return;
        it->second.payload = std::move(payload);
        it->second.done = true;
        it->second.cv.notify_one();
    }

private:
    struct Slot {
        std::condition_variable cv;
        std::vector<std::uint8_t> payload;
        bool done = false;
    };

    std::mutex mu_;
    std::unordered_map<std::int32_t, Slot> waiters_;
    // corrId 0 is reserved as "no correlation"; start at 1.
    std::atomic<std::int32_t> nextCorrId_{1};
};

/** Process-wide singleton accessor — main.cpp's hand-written code, generated handler forwarders, and any
  * future intercept use sites all share one waiter map so corrIds don't collide. */
inline InterceptRegistry& intercepts() {
    static InterceptRegistry instance;
    return instance;
}

} // namespace ipc
} // namespace cef4j
