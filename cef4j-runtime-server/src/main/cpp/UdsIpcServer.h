#pragma once

#include <atomic>
#include <deque>
#include <mutex>
#include <thread>
#include <utility>

#include "IpcServer.h"

namespace cef4j { namespace ipc {

class UdsIpcServer final : public IpcServer {
public:
  UdsIpcServer() = default;
  ~UdsIpcServer() override;
  bool bind(const std::string &addr) override;
  const std::string &endpoint() const override { return endpoint_; }
  void start(FrameHandler handler) override;
  void stop() override;
  bool send(Kind kind, uint8_t flags, int32_t corrId, int32_t messageId, const uint8_t *payload, size_t payloadLen) override;
  bool sendLatest(Kind kind, uint8_t flags, int32_t corrId, int32_t messageId, const uint8_t *payload, size_t payloadLen,
                  int64_t streamId) override;

private:
  void workerLoop();
  bool drainIncoming();
  bool drainOutbound();
  int listenFd_ = -1;
  int clientFd_ = -1;
  int wakeReadFd_ = -1;
  int wakeWriteFd_ = -1;
  std::string path_;
  std::string endpoint_;
  FrameHandler handler_;
  std::thread worker_;
  std::atomic<bool> running_{false};
  std::atomic<bool> stop_{false};
  std::mutex outboundMu_;
  std::deque<std::vector<uint8_t>> outbound_;
  std::deque<std::pair<int64_t, std::vector<uint8_t>>> latest_;
  size_t queuedBytes_ = 0;
  std::mutex clientMu_;
};

}} // namespace cef4j::ipc
