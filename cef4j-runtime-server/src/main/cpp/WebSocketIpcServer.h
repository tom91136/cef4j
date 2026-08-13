#pragma once

#include "IpcServer.h"
#include <atomic>
#include <deque>
#include <mutex>
#include <thread>
#ifdef _WIN32
#include <winsock2.h>
#endif

namespace cef4j {
namespace ipc {

class WebSocketIpcServer final : public IpcServer {
public:
#ifdef _WIN32
    using SocketHandle = SOCKET;
    static constexpr SocketHandle kInvalidSocket = INVALID_SOCKET;
#else
    using SocketHandle = int;
    static constexpr SocketHandle kInvalidSocket = -1;
#endif
    WebSocketIpcServer() = default;
    ~WebSocketIpcServer() override;
    bool bind(const std::string& addr) override;
    const std::string& endpoint() const override { return endpoint_; }
    bool setBearerToken(const std::string& token) override { bearerToken_ = token; return true; }
    void start(FrameHandler handler) override;
    void stop() override;
    bool send(Kind kind, std::uint8_t flags, std::int32_t corrId, std::int32_t messageId,
              const std::uint8_t* payload, std::size_t payloadLen) override;
    bool sendLatest(Kind kind, std::uint8_t flags, std::int32_t corrId, std::int32_t messageId,
                    const std::uint8_t* payload, std::size_t payloadLen, std::int64_t streamId) override;

private:
    void workerLoop();
    bool handshake(SocketHandle fd);
    bool drainIncoming();
    bool drainOutbound();
    bool sendWebSocketFrame(std::uint8_t opcode, const std::uint8_t* payload, std::size_t length);
    void closeClient();

    SocketHandle listenFd_ = kInvalidSocket;
    SocketHandle clientFd_ = kInvalidSocket;
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
        std::vector<std::uint8_t> bytes;
        bool replaceable = false;
        std::int64_t streamId = 0;
    };
    std::deque<OutboundFrame> outbound_;
    std::mutex clientMu_;
    bool fragmented_ = false;
    std::vector<std::uint8_t> inboundMessage_;
};

} // namespace ipc
} // namespace cef4j
