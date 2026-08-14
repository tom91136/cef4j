#include "ZmqIpcServer.h"

#include <zmq.h>

#include <algorithm>
#include <chrono>
#include <cstdio>
#include <cstring>
#include <random>

namespace cef4j {
namespace ipc {

namespace {
constexpr std::size_t kMaxFrameSize = 64U * 1024U * 1024U;

std::string makeInprocAddr() {
    static std::atomic<std::uint64_t> counter{0};
    auto n = counter.fetch_add(1, std::memory_order_relaxed);
    auto epoch = std::chrono::steady_clock::now().time_since_epoch().count();
    char buf[64];
    std::snprintf(buf, sizeof(buf), "inproc://cef4j-runtime-server-wake-%lld-%llu",
                  static_cast<long long>(epoch), static_cast<unsigned long long>(n));
    return buf;
}

void setLingerZero(void* sock) {
    int linger = 0;
    zmq_setsockopt(sock, ZMQ_LINGER, &linger, sizeof(linger));
}
} // namespace

ZmqIpcServer::ZmqIpcServer() {
    ctx_ = zmq_ctx_new();
}

ZmqIpcServer::~ZmqIpcServer() {
    stop();
    if (wakeSenderSock_) zmq_close(wakeSenderSock_);
    if (wakeWorkerSock_) zmq_close(wakeWorkerSock_);
    if (mainSock_) zmq_close(mainSock_);
    if (ctx_) zmq_ctx_term(ctx_);
}

bool ZmqIpcServer::bind(const std::string& addr) {
    // DEALER queues frames across connection establishment and exposes backpressure
    // consistently to the Java DEALER peer. The inproc wake sockets remain PAIR.
    mainSock_ = zmq_socket(ctx_, ZMQ_DEALER);
    if (!mainSock_) return false;
    setLingerZero(mainSock_);
    int hbIvl = 500;
    int hbTo = 2000;
    zmq_setsockopt(mainSock_, ZMQ_HEARTBEAT_IVL, &hbIvl, sizeof(hbIvl));
    zmq_setsockopt(mainSock_, ZMQ_HEARTBEAT_TIMEOUT, &hbTo, sizeof(hbTo));

    if (zmq_bind(mainSock_, addr.c_str()) != 0) {
        std::fprintf(stderr, "[cef4j-runtime-server] zmq_bind(%s) failed: %s\n", addr.c_str(), zmq_strerror(zmq_errno()));
        return false;
    }

    char resolved[256];
    std::size_t resolvedLen = sizeof(resolved);
    if (zmq_getsockopt(mainSock_, ZMQ_LAST_ENDPOINT, resolved, &resolvedLen) != 0) {
        std::fprintf(stderr, "[cef4j-runtime-server] failed to read ZMQ_LAST_ENDPOINT: %s\n", zmq_strerror(zmq_errno()));
        return false;
    }
    // ZMQ_LAST_ENDPOINT includes a trailing NUL; resolvedLen counts it.
    endpoint_.assign(resolved, resolvedLen > 0 ? resolvedLen - 1 : 0);

    auto wakeAddr = makeInprocAddr();
    wakeWorkerSock_ = zmq_socket(ctx_, ZMQ_PAIR);
    wakeSenderSock_ = zmq_socket(ctx_, ZMQ_PAIR);
    if (!wakeWorkerSock_ || !wakeSenderSock_) return false;
    setLingerZero(wakeWorkerSock_);
    setLingerZero(wakeSenderSock_);
    if (zmq_bind(wakeWorkerSock_, wakeAddr.c_str()) != 0) return false;
    if (zmq_connect(wakeSenderSock_, wakeAddr.c_str()) != 0) return false;
    return true;
}

void ZmqIpcServer::start(FrameHandler handler) {
    handler_ = std::move(handler);
    running_ = true;
    worker_ = std::thread([this] { workerLoop(); });
}

void ZmqIpcServer::stop() {
    if (!running_.exchange(false)) return;
    stop_ = true;
    {
        std::lock_guard<std::mutex> lk(senderMu_);
        if (wakeSenderSock_) {
            zmq_send(wakeSenderSock_, "", 0, ZMQ_DONTWAIT);
        }
    }
    if (worker_.joinable()) worker_.join();
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
    {
        std::lock_guard<std::mutex> lk(senderMu_);
        if (wakeSenderSock_) {
            zmq_send(wakeSenderSock_, "", 0, ZMQ_DONTWAIT);
        }
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
    std::lock_guard<std::mutex> lk(senderMu_);
    if (wakeSenderSock_) zmq_send(wakeSenderSock_, "", 0, ZMQ_DONTWAIT);
    return true;
}

void ZmqIpcServer::workerLoop() {
    zmq_pollitem_t items[2];
    items[0].socket = mainSock_;
    items[0].events = ZMQ_POLLIN;
    items[0].fd = 0;
    items[0].revents = 0;
    items[1].socket = wakeWorkerSock_;
    items[1].events = ZMQ_POLLIN;
    items[1].fd = 0;
    items[1].revents = 0;

    while (!stop_) {
        long timeoutMs = 200;
        int n = zmq_poll(items, 2, timeoutMs);
        if (n < 0) {
            if (zmq_errno() == ETERM) break;
            continue;
        }
        if (items[0].revents & ZMQ_POLLIN) drainIncoming();
        if (items[1].revents & ZMQ_POLLIN) {
            // drain wake signals
            char buf[16];
            while (zmq_recv(wakeWorkerSock_, buf, sizeof(buf), ZMQ_DONTWAIT) >= 0) {}
        }
        // A producer can enqueue before the inproc wake PAIR has completed its
        // connection.  The best-effort DONTWAIT wake is then legitimately
        // dropped, so polling only after a wake can strand the first outbound
        // frame forever.  The bounded main poll is also a progress clock: check
        // the queue on every pass so bootstrap events and responses cannot be
        // lost solely because they were the first send in a fresh process.
        drainOutbound();
    }
}

void ZmqIpcServer::drainIncoming() {
    while (true) {
        zmq_msg_t msg;
        zmq_msg_init(&msg);
        int rc = zmq_msg_recv(&msg, mainSock_, ZMQ_DONTWAIT);
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
    while (!batch.empty()) {
        auto& frame = batch.front();
        if (zmq_send(mainSock_, frame.data(), frame.size(), ZMQ_DONTWAIT) >= 0) {
            batch.pop_front();
            continue;
        }
        int error = zmq_errno();
        if (error == EAGAIN && running_) {
            // A disconnected or temporarily back-pressured peer must never pin
            // the worker inside zmq_send: stop() owns a join on this thread.
            // Put the unsent suffix ahead of anything producers queued while we
            // were sending, preserving request/response order for the next poll.
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
