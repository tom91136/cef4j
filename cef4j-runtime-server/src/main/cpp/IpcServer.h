// Transport-neutral server API used by generated IPC dispatch code.
#pragma once

#include <cstdint>
#include <functional>
#include <memory>
#include <string>
#include <vector>

#include "Envelope.h"

namespace cef4j { namespace ipc {

constexpr size_t MaxFrameSize = 64U * 1024U * 1024U;
constexpr size_t MaxQueuedBytes = 128U * 1024U * 1024U;

class IpcServer {
public:
  // Called on the worker thread for each frame received. The header has been
  // parsed; payload is a copy. Implementation must not block long; for
  // CEF-affecting work post a CefTask onto the UI thread.
  using FrameHandler = std::function<void(const Header &h, std::vector<uint8_t> &&payload)>;

  IpcServer() = default;
  virtual ~IpcServer() = default;

  IpcServer(const IpcServer &) = delete;
  IpcServer &operator=(const IpcServer &) = delete;

  // Bind and resolve endpoint. Returns true on success.
  virtual bool bind(const std::string &addr) = 0;
  virtual const std::string &endpoint() const = 0;

  /** Optional transport authentication. Implementations that do not expose HTTP handshakes reject non-empty use. */
  virtual bool setBearerToken(const std::string &token) { return token.empty(); }

  // Spawn the worker thread. Frames received before {@code start} arrives are
  // not buffered (caller is expected to bind→start as one step before the
  // peer connects).
  virtual void start(FrameHandler handler) = 0;

  // Signal the worker to exit and join.
  virtual void stop() = 0;

  // Thread-safe. Enqueues an envelope frame and wakes the worker.
  virtual bool send(Kind kind, uint8_t flags, int32_t corrId, int32_t messageId, const uint8_t *payload, size_t payloadLen) = 0;

  // Enqueues replaceable stream state. Every transport must coalesce this path so a slow peer cannot accumulate
  // stale full-frame paints.
  virtual bool sendLatest(Kind kind, uint8_t flags, int32_t corrId, int32_t messageId, const uint8_t *payload, size_t payloadLen,
                          int64_t streamId) = 0;
};

// Creates a server for a registered transport name ("local", "zmq", "uds", or "websocket").
std::unique_ptr<IpcServer> createIpcServer(const std::string &transport);

}} // namespace cef4j::ipc
