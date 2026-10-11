#pragma once

#include <atomic>
#include <deque>
#include <mutex>
#include <thread>

#include "IpcServer.h"
#ifdef _WIN32
  #include <winsock2.h>
#endif

namespace cef4j { namespace ipc {

class WebSocketIpcServer final : public IpcServer {
public:
#ifdef _WIN32
  using SocketHandle = SOCKET;
  static constexpr SocketHandle InvalidSocket = INVALID_SOCKET;
#else
  using SocketHandle = int;
  static constexpr SocketHandle InvalidSocket = -1;
#endif
  WebSocketIpcServer() = default;
  ~WebSocketIpcServer() override;
  bool bind(const std::string &addr) override;
  const std::string &endpoint() const override { return endpoint_; }
  bool setBearerToken(const std::string &token) override {
    bearerToken_ = token;
    return true;
  }
  void start(FrameHandler handler) override;
  void stop() override;
  bool send(Kind kind, uint8_t flags, int32_t corrId, int32_t messageId, const uint8_t *payload, size_t payloadLen) override;
  bool sendLatest(Kind kind, uint8_t flags, int32_t corrId, int32_t messageId, const uint8_t *payload, size_t payloadLen,
                  int64_t streamId) override;

private:
  void workerLoop();
  bool handshake(SocketHandle fd);
  bool drainIncoming();
  bool drainOutbound();
  bool sendWebSocketFrame(uint8_t opcode, const uint8_t *payload, size_t length);
  void closeClient();

  SocketHandle listenFd_ = InvalidSocket;
  SocketHandle clientFd_ = InvalidSocket;
  int wakeReadFd_ = -1;
  int wakeWriteFd_ = -1;
  std::string host_;
  std::string path_;
  std::string endpoint_;
  std::string bearerToken_;
  FrameHandler handler_;
  std::thread worker_;
  std::atomic<bool> running_{false};
  std::atomic<bool> stop_{false};
  std::mutex outboundMu_;
  struct OutboundFrame {
    std::vector<uint8_t> bytes;
    bool replaceable = false;
    int64_t streamId = 0;
  };
  std::deque<OutboundFrame> outbound_;
  size_t queuedBytes_ = 0;
  std::mutex clientMu_;
  bool fragmented_ = false;
  std::vector<uint8_t> inboundMessage_;
};

}} // namespace cef4j::ipc
