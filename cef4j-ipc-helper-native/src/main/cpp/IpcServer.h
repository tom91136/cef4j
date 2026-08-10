// Single-PAIR IPC server, mirrors the JVM-side ZmqTransport pattern: one
// worker thread owns the socket, external sends queue + wake the worker.
#pragma once

#include "Envelope.h"
#include <atomic>
#include <condition_variable>
#include <cstdint>
#include <deque>
#include <functional>
#include <mutex>
#include <string>
#include <thread>
#include <vector>

namespace cef4j {
namespace ipc {

class IpcServer {
public:
    // Called on the worker thread for each frame received. The header has been
    // parsed; payload is a copy. Implementation must not block long; for
    // CEF-affecting work post a CefTask onto the UI thread.
    using FrameHandler = std::function<void(const Header& h, std::vector<std::uint8_t>&& payload)>;

    IpcServer();
    ~IpcServer();

    IpcServer(const IpcServer&) = delete;
    IpcServer& operator=(const IpcServer&) = delete;

    // Bind and resolve endpoint. Returns true on success.
    bool bind(const std::string& addr);
    const std::string& endpoint() const { return endpoint_; }

    // Spawn the worker thread. Frames received before {@code start} arrives are
    // not buffered (caller is expected to bind→start as one step before the
    // peer connects).
    void start(FrameHandler handler);

    // Signal the worker to exit and join.
    void stop();

    // Thread-safe. Enqueues an envelope frame and wakes the worker.
    bool send(Kind kind, std::uint8_t flags, std::int32_t corrId, std::int32_t messageId,
              const std::uint8_t* payload, std::size_t payloadLen);

private:
    void workerLoop();
    void drainOutbound();
    void drainIncoming();

    void* ctx_ = nullptr;
    void* mainSock_ = nullptr;
    void* wakeWorkerSock_ = nullptr;   // worker side: PAIR bound to inproc address
    void* wakeSenderSock_ = nullptr;   // sender side: PAIR connected to same inproc

    std::string endpoint_;
    FrameHandler handler_;
    std::thread worker_;
    std::atomic<bool> running_{false};
    std::atomic<bool> stop_{false};

    std::mutex outboundMu_;
    std::deque<std::vector<std::uint8_t>> outbound_;
    std::mutex senderMu_; // serialises wakeSenderSock_ access from external threads
};

} // namespace ipc
} // namespace cef4j
