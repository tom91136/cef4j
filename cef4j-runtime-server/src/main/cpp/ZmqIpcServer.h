#pragma once

#include "IpcServer.h"
#include <atomic>
#include <deque>
#include <mutex>
#include <thread>
#include <utility>

namespace cef4j {
namespace ipc {

class ZmqIpcServer final : public IpcServer {
public:
    ZmqIpcServer();
    ~ZmqIpcServer() override;
    bool bind(const std::string& addr) override;
    const std::string& endpoint() const override { return endpoint_; }
    void start(FrameHandler handler) override;
    void stop() override;
    bool send(Kind kind, std::uint8_t flags, std::int32_t corrId, std::int32_t messageId,
              const std::uint8_t* payload, std::size_t payloadLen) override;
    bool sendLatest(Kind kind, std::uint8_t flags, std::int32_t corrId, std::int32_t messageId,
                    const std::uint8_t* payload, std::size_t payloadLen, std::int64_t streamId) override;

private:
    void workerLoop();
    void drainOutbound();
    void drainIncoming();
    void* ctx_ = nullptr;
    void* mainSock_ = nullptr;
    void* wakeWorkerSock_ = nullptr;
    void* wakeSenderSock_ = nullptr;
    std::string endpoint_;
    FrameHandler handler_;
    std::thread worker_;
    std::atomic<bool> running_{false};
    std::atomic<bool> stop_{false};
    std::mutex outboundMu_;
    std::deque<std::vector<std::uint8_t>> outbound_;
    std::deque<std::pair<std::int64_t, std::vector<std::uint8_t>>> latest_;
    std::mutex senderMu_;
};

} // namespace ipc
} // namespace cef4j
