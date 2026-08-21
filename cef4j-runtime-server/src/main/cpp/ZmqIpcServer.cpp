#include "ZmqIpcServer.h"

#include <zmq.h>

#include <algorithm>
#include <cstdio>
#include <cstring>

namespace cef4j {
namespace ipc {

namespace {
constexpr std::size_t kMaxFrameSize = 64U * 1024U * 1024U;
constexpr long kPollTimeoutMs = 10;
constexpr int kHeartbeatIntervalMs = 1000;
constexpr int kHeartbeatTimeoutMs = 360000;

void setLingerZero(void* sock) {
    int linger = 0;
    zmq_setsockopt(sock, ZMQ_LINGER, &linger, sizeof(linger));
}
} // namespace

ZmqIpcServer::ZmqIpcServer() = default;

ZmqIpcServer::~ZmqIpcServer() {
    stop();
}

bool ZmqIpcServer::bind(const std::string& addr) {
    worker_ = std::thread([this, addr] { workerLoop(addr); });
    std::unique_lock<std::mutex> lk(lifecycleMu_);
    lifecycleCv_.wait(lk, [this] { return bindComplete_; });
    if (!bindSucceeded_ && worker_.joinable()) {
        lk.unlock();
        worker_.join();
    }
    return bindSucceeded_;
}

void ZmqIpcServer::start(FrameHandler handler) {
    {
        std::lock_guard<std::mutex> lk(lifecycleMu_);
        handler_ = std::move(handler);
        running_ = true;
        startRequested_ = true;
    }
    lifecycleCv_.notify_all();
}

void ZmqIpcServer::stop() {
    running_ = false;
    stop_ = true;
    lifecycleCv_.notify_all();
    if (worker_.joinable()) worker_.join();
}

void ZmqIpcServer::workerLoop(std::string addr) {
    // libzmq sockets are thread-affine. The worker owns the full lifetime,
    // including context/socket creation, bind, polling, and destruction.
    ctx_ = zmq_ctx_new();
    if (!ctx_) {
        std::lock_guard<std::mutex> lk(lifecycleMu_);
        bindComplete_ = true;
        lifecycleCv_.notify_all();
        return;
    }
    // The JVM is a DEALER client. ROUTER is the canonical server peer and keeps
    // the routing identity explicit if the connection is re-established.
    mainSock_ = zmq_socket(ctx_, ZMQ_ROUTER);
    if (!mainSock_) {
        std::lock_guard<std::mutex> lk(lifecycleMu_);
        bindComplete_ = true;
        lifecycleCv_.notify_all();
        if (ctx_) zmq_ctx_term(ctx_);
        ctx_ = nullptr;
        return;
    }
    setLingerZero(mainSock_);
    int mandatory = 1;
    zmq_setsockopt(mainSock_, ZMQ_ROUTER_MANDATORY, &mandatory, sizeof(mandatory));
    // Match the JVM peer's tolerance for scheduler stalls. Locally spawned servers are supervised by Process.onExit,
    // so this network heartbeat primarily covers remote peers and must not declare a busy live process dead.
    int hbIvl = kHeartbeatIntervalMs;
    int hbTo = kHeartbeatTimeoutMs;
    zmq_setsockopt(mainSock_, ZMQ_HEARTBEAT_IVL, &hbIvl, sizeof(hbIvl));
    zmq_setsockopt(mainSock_, ZMQ_HEARTBEAT_TIMEOUT, &hbTo, sizeof(hbTo));

    if (zmq_bind(mainSock_, addr.c_str()) != 0) {
        std::fprintf(stderr, "[cef4j-runtime-server] zmq_bind(%s) failed: %s\n", addr.c_str(), zmq_strerror(zmq_errno()));
    } else {
        char resolved[256];
        std::size_t resolvedLen = sizeof(resolved);
        if (zmq_getsockopt(mainSock_, ZMQ_LAST_ENDPOINT, resolved, &resolvedLen) != 0) {
            std::fprintf(stderr, "[cef4j-runtime-server] failed to read ZMQ_LAST_ENDPOINT: %s\n", zmq_strerror(zmq_errno()));
        } else {
            // ZMQ_LAST_ENDPOINT includes a trailing NUL; resolvedLen counts it.
            endpoint_.assign(resolved, resolvedLen > 0 ? resolvedLen - 1 : 0);
            bindSucceeded_ = true;
        }
    }
    {
        std::unique_lock<std::mutex> lk(lifecycleMu_);
        bindComplete_ = true;
        lifecycleCv_.notify_all();
        if (bindSucceeded_) lifecycleCv_.wait(lk, [this] { return startRequested_ || stop_; });
    }
    if (bindSucceeded_ && !stop_) {
        zmq_pollitem_t items[1];
        items[0].socket = mainSock_;
        items[0].events = ZMQ_POLLIN;
        items[0].fd = 0;
        items[0].revents = 0;
        while (!stop_) {
            int n = zmq_poll(items, 1, kPollTimeoutMs);
            if (n < 0) {
                if (zmq_errno() == ETERM) break;
                continue;
            }
            if (items[0].revents & ZMQ_POLLIN) drainIncoming();
            // send() can run on any CEF thread, but ZeroMQ sockets are thread-affine.
            // Producers therefore only enqueue. This short bounded poll is the
            // worker's progress clock for outbound frames and shutdown.
            drainOutbound();
        }
    }
    if (mainSock_) zmq_close(mainSock_);
    mainSock_ = nullptr;
    if (ctx_) zmq_ctx_term(ctx_);
    ctx_ = nullptr;
}

bool ZmqIpcServer::send(Kind kind, std::uint8_t flags, std::int32_t corrId, std::int32_t messageId,
                     const std::uint8_t* payload, std::size_t payloadLen) {
    if (!running_) return false;
    std::vector<std::uint8_t> frame(kHeaderSize + payloadLen);
    writeHeader(frame.data(), kind, flags, corrId, messageId, static_cast<std::int32_t>(payloadLen));
    if (payload && payloadLen > 0) {
        std::memcpy(frame.data() + kHeaderSize, payload, payloadLen);
    }
    {
        std::lock_guard<std::mutex> lk(outboundMu_);
        outbound_.push_back(std::move(frame));
    }
    return true;
}

bool ZmqIpcServer::sendLatest(Kind kind, std::uint8_t flags, std::int32_t corrId, std::int32_t messageId,
                              const std::uint8_t* payload, std::size_t payloadLen, std::int64_t streamId) {
    if (!running_ || payloadLen > kMaxFrameSize - kHeaderSize) return false;
    std::vector<std::uint8_t> frame(kHeaderSize + payloadLen);
    writeHeader(frame.data(), kind, flags, corrId, messageId, static_cast<std::int32_t>(payloadLen));
    if (payload && payloadLen > 0) std::memcpy(frame.data() + kHeaderSize, payload, payloadLen);
    {
        std::lock_guard<std::mutex> lk(outboundMu_);
        auto it = std::find_if(latest_.begin(), latest_.end(),
                               [streamId](const auto& item) { return item.first == streamId; });
        if (it == latest_.end()) latest_.emplace_back(streamId, std::move(frame));
        else it->second = std::move(frame);
    }
    return true;
}

void ZmqIpcServer::drainIncoming() {
    while (true) {
        // ROUTER prepends the DEALER's opaque routing identity.
        zmq_msg_t identity;
        zmq_msg_init(&identity);
        int rc = zmq_msg_recv(&identity, mainSock_, ZMQ_DONTWAIT);
        if (rc < 0) {
            zmq_msg_close(&identity);
            break;
        }
        int more = zmq_msg_more(&identity);
        if (!more) {
            zmq_msg_close(&identity);
            continue;
        }
        std::vector<std::uint8_t> route(
                static_cast<const std::uint8_t*>(zmq_msg_data(&identity)),
                static_cast<const std::uint8_t*>(zmq_msg_data(&identity)) + zmq_msg_size(&identity));
        zmq_msg_close(&identity);

        zmq_msg_t msg;
        zmq_msg_init(&msg);
        rc = zmq_msg_recv(&msg, mainSock_, 0);
        if (rc < 0) {
            zmq_msg_close(&msg);
            break;
        }
        std::size_t sz = zmq_msg_size(&msg);
        if (sz < kHeaderSize) {
            zmq_msg_close(&msg);
            continue;
        }
        const auto* data = static_cast<const std::uint8_t*>(zmq_msg_data(&msg));
        Header h;
        if (!readHeader(data, sz, h)) {
            zmq_msg_close(&msg);
            continue;
        }
        peerIdentity_ = std::move(route);
        std::vector<std::uint8_t> payload;
        if (sz > kHeaderSize) {
            payload.assign(data + kHeaderSize, data + sz);
        }
        zmq_msg_close(&msg);
        if (handler_) handler_(h, std::move(payload));
    }
}

void ZmqIpcServer::drainOutbound() {
    std::deque<std::vector<std::uint8_t>> batch;
    {
        std::lock_guard<std::mutex> lk(outboundMu_);
        batch.swap(outbound_);
        for (auto& item : latest_) batch.push_back(std::move(item.second));
        latest_.clear();
    }
    if (peerIdentity_.empty()) {
        std::lock_guard<std::mutex> lk(outboundMu_);
        while (!batch.empty()) {
            outbound_.push_front(std::move(batch.back()));
            batch.pop_back();
        }
        return;
    }
    while (!batch.empty()) {
        auto& frame = batch.front();
        int routeRc = zmq_send(
                mainSock_, peerIdentity_.data(), peerIdentity_.size(), ZMQ_DONTWAIT | ZMQ_SNDMORE);
        int frameRc = routeRc >= 0 ? zmq_send(mainSock_, frame.data(), frame.size(), ZMQ_DONTWAIT) : -1;
        if (frameRc >= 0) {
            batch.pop_front();
            continue;
        }
        int error = zmq_errno();
        if ((error == EAGAIN || error == EHOSTUNREACH) && running_) {
            // A disconnected or temporarily back-pressured peer must never pin
            // the worker inside zmq_send: stop() owns a join on this thread.
            // Put the unsent suffix ahead of anything producers queued while we
            // were sending, preserving request/response order for the next poll.
            if (error == EHOSTUNREACH) peerIdentity_.clear();
            std::lock_guard<std::mutex> lk(outboundMu_);
            while (!batch.empty()) {
                outbound_.push_front(std::move(batch.back()));
                batch.pop_back();
            }
            return;
        }
        if (error != EAGAIN && error != ETERM) {
            std::fprintf(stderr, "[cef4j-runtime-server] zmq_send failed: %s\n", zmq_strerror(error));
        }
        return;
    }
}

} // namespace ipc
} // namespace cef4j
