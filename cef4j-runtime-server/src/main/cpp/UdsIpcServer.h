#pragma once

#include "IpcServer.h"
#include <atomic>
#include <deque>
#include <mutex>
#include <thread>
#include <utility>

namespace cef4j {
namespace ipc {

class UdsIpcServer final : public IpcServer {
public:
    UdsIpcServer() = default;
    ~UdsIpcServer() override;
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
    std::deque<std::vector<std::uint8_t>> outbound_;
    std::deque<std::pair<std::int64_t, std::vector<std::uint8_t>>> latest_;
    std::size_t queuedBytes_ = 0;
    std::mutex clientMu_;
};

} // namespace ipc
} // namespace cef4j
