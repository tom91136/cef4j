#include "UdsIpcServer.h"

#include <algorithm>
#include <arpa/inet.h>
#include <cerrno>
#include <cstdio>
#include <cstring>
#include <fcntl.h>
#include <poll.h>
#include <sys/socket.h>
#include <sys/un.h>
#include <unistd.h>

namespace cef4j {
namespace ipc {

namespace {
std::string socketPath(const std::string& addr) {
    constexpr const char* prefix = "unix://";
    return addr.compare(0, std::strlen(prefix), prefix) == 0 ? addr.substr(std::strlen(prefix)) : addr;
}

bool readAll(int fd, void* output, std::size_t length) {
    auto* cursor = static_cast<std::uint8_t*>(output);
    while (length > 0) {
        ssize_t n = ::recv(fd, cursor, length, 0);
        if (n == 0) return false;
        if (n < 0) {
            if (errno == EINTR) continue;
            return false;
        }
        cursor += n;
        length -= static_cast<std::size_t>(n);
    }
    return true;
}

bool writeAll(int fd, const void* input, std::size_t length) {
    const auto* cursor = static_cast<const std::uint8_t*>(input);
    while (length > 0) {
        int flags = 0;
#ifndef __APPLE__
        flags = MSG_NOSIGNAL;
#endif
        ssize_t n = ::send(fd, cursor, length, flags);
        if (n < 0) {
            if (errno == EINTR) continue;
            return false;
        }
        cursor += n;
        length -= static_cast<std::size_t>(n);
    }
    return true;
}
} // namespace

UdsIpcServer::~UdsIpcServer() {
    stop();
    if (clientFd_ >= 0) ::close(clientFd_);
    if (listenFd_ >= 0) ::close(listenFd_);
    if (wakeReadFd_ >= 0) ::close(wakeReadFd_);
    if (wakeWriteFd_ >= 0) ::close(wakeWriteFd_);
    if (!path_.empty()) ::unlink(path_.c_str());
}

bool UdsIpcServer::bind(const std::string& addr) {
    path_ = socketPath(addr);
    sockaddr_un address{};
    if (path_.empty() || path_.size() >= sizeof(address.sun_path)) {
        std::fprintf(stderr, "[cef4j-runtime-server] invalid UDS path: %s\n", path_.c_str());
        return false;
    }
    listenFd_ = ::socket(AF_UNIX, SOCK_STREAM, 0);
    if (listenFd_ < 0) return false;
#ifdef __APPLE__
    int noSigPipe = 1;
    (void)::setsockopt(listenFd_, SOL_SOCKET, SO_NOSIGPIPE, &noSigPipe, sizeof(noSigPipe));
#endif
    address.sun_family = AF_UNIX;
    std::memcpy(address.sun_path, path_.c_str(), path_.size() + 1);
    ::unlink(path_.c_str());
    if (::bind(listenFd_, reinterpret_cast<sockaddr*>(&address), sizeof(address)) != 0 ||
        ::listen(listenFd_, 1) != 0) {
        std::fprintf(stderr, "[cef4j-runtime-server] UDS bind(%s) failed: %s\n", path_.c_str(), std::strerror(errno));
        return false;
    }
    int wake[2];
    if (::pipe(wake) != 0) return false;
    wakeReadFd_ = wake[0];
    wakeWriteFd_ = wake[1];
    int wakeFlags = ::fcntl(wakeWriteFd_, F_GETFL, 0);
    if (wakeFlags < 0 || ::fcntl(wakeWriteFd_, F_SETFL, wakeFlags | O_NONBLOCK) != 0) return false;
    endpoint_ = "unix://" + path_;
    return true;
}

void UdsIpcServer::start(FrameHandler handler) {
    handler_ = std::move(handler);
    stop_ = false;
    running_ = true;
    worker_ = std::thread([this] { workerLoop(); });
}

void UdsIpcServer::stop() {
    if (!running_.exchange(false)) return;
    stop_ = true;
    {
        std::lock_guard<std::mutex> lock(clientMu_);
        if (clientFd_ >= 0) ::shutdown(clientFd_, SHUT_RDWR);
    }
    if (wakeWriteFd_ >= 0) {
        const std::uint8_t wake = 1;
        (void)::write(wakeWriteFd_, &wake, 1);
    }
    if (worker_.joinable()) worker_.join();
}

bool UdsIpcServer::send(Kind kind, std::uint8_t flags, std::int32_t corrId, std::int32_t messageId,
                        const std::uint8_t* payload, std::size_t payloadLen) {
    if (!running_ || payloadLen > kMaxFrameSize - kHeaderSize) return false;
    std::vector<std::uint8_t> frame(kHeaderSize + payloadLen);
    writeHeader(frame.data(), kind, flags, corrId, messageId, static_cast<std::int32_t>(payloadLen));
    if (payload && payloadLen > 0) std::memcpy(frame.data() + kHeaderSize, payload, payloadLen);
    {
        std::lock_guard<std::mutex> lock(outboundMu_);
        if (queuedBytes_ > kMaxQueuedBytes || frame.size() > kMaxQueuedBytes - queuedBytes_) {
            stop_ = true;
            return false;
        }
        queuedBytes_ += frame.size();
        outbound_.push_back(std::move(frame));
    }
    const std::uint8_t wake = 1;
    if (wakeWriteFd_ < 0) return false;
    ssize_t wakeResult = ::write(wakeWriteFd_, &wake, 1);
    // EAGAIN means an earlier wake is already queued; the worker will still drain this frame.
    return wakeResult == 1 || (wakeResult < 0 && errno == EAGAIN);
}

bool UdsIpcServer::sendLatest(Kind kind, std::uint8_t flags, std::int32_t corrId, std::int32_t messageId,
                              const std::uint8_t* payload, std::size_t payloadLen, std::int64_t streamId) {
    if (!running_ || payloadLen > kMaxFrameSize - kHeaderSize) return false;
    std::vector<std::uint8_t> frame(kHeaderSize + payloadLen);
    writeHeader(frame.data(), kind, flags, corrId, messageId, static_cast<std::int32_t>(payloadLen));
    if (payload && payloadLen > 0) std::memcpy(frame.data() + kHeaderSize, payload, payloadLen);
    {
        std::lock_guard<std::mutex> lock(outboundMu_);
        auto it = std::find_if(latest_.begin(), latest_.end(),
                               [streamId](const auto& item) { return item.first == streamId; });
        std::size_t replacedBytes = it == latest_.end() ? 0 : it->second.size();
        std::size_t retainedBytes = queuedBytes_ - replacedBytes;
        if (retainedBytes > kMaxQueuedBytes || frame.size() > kMaxQueuedBytes - retainedBytes) {
            stop_ = true;
            return false;
        }
        queuedBytes_ = retainedBytes + frame.size();
        if (it == latest_.end()) latest_.emplace_back(streamId, std::move(frame));
        else it->second = std::move(frame);
    }
    const std::uint8_t wake = 1;
    ssize_t result = wakeWriteFd_ < 0 ? -1 : ::write(wakeWriteFd_, &wake, 1);
    return result == 1 || (result < 0 && errno == EAGAIN);
}

void UdsIpcServer::workerLoop() {
    while (!stop_) {
        pollfd descriptors[3] = {{listenFd_, POLLIN, 0}, {clientFd_, POLLIN, 0}, {wakeReadFd_, POLLIN, 0}};
        int n = ::poll(descriptors, 3, 200);
        if (n < 0) {
            if (errno == EINTR) continue;
            break;
        }
        if (descriptors[0].revents & POLLIN) {
            int accepted = ::accept(listenFd_, nullptr, nullptr);
            if (accepted >= 0) {
#ifdef __APPLE__
                int noSigPipe = 1;
                (void)::setsockopt(accepted, SOL_SOCKET, SO_NOSIGPIPE, &noSigPipe, sizeof(noSigPipe));
#endif
                {
                    std::lock_guard<std::mutex> lock(clientMu_);
                    if (clientFd_ >= 0) ::close(clientFd_);
                    clientFd_ = accepted;
                }
                (void)drainOutbound();
            }
        }
        if (clientFd_ >= 0 && (descriptors[1].revents & (POLLIN | POLLHUP | POLLERR))) {
            if (!drainIncoming()) {
                std::lock_guard<std::mutex> lock(clientMu_);
                ::close(clientFd_);
                clientFd_ = -1;
            }
        }
        if (descriptors[2].revents & POLLIN) {
            std::uint8_t buffer[64];
            (void)::read(wakeReadFd_, buffer, sizeof(buffer));
            (void)drainOutbound();
        }
    }
}

bool UdsIpcServer::drainIncoming() {
    std::uint32_t encodedLength;
    if (!readAll(clientFd_, &encodedLength, sizeof(encodedLength))) return false;
    std::uint32_t length = ntohl(encodedLength);
    if (length < kHeaderSize || length > kMaxFrameSize) return false;
    std::vector<std::uint8_t> frame(length);
    if (!readAll(clientFd_, frame.data(), frame.size())) return false;
    Header header;
    if (!readHeader(frame.data(), frame.size(), header)) return true;
    std::vector<std::uint8_t> payload(frame.begin() + kHeaderSize, frame.end());
    if (handler_) handler_(header, std::move(payload));
    return true;
}

bool UdsIpcServer::drainOutbound() {
    if (clientFd_ < 0) return true;
    std::deque<std::vector<std::uint8_t>> batch;
    {
        std::lock_guard<std::mutex> lock(outboundMu_);
        batch.swap(outbound_);
        for (auto& item : latest_) batch.push_back(std::move(item.second));
        latest_.clear();
        queuedBytes_ = 0;
    }
    for (const auto& frame : batch) {
        std::uint32_t length = htonl(static_cast<std::uint32_t>(frame.size()));
        if (!writeAll(clientFd_, &length, sizeof(length)) || !writeAll(clientFd_, frame.data(), frame.size())) {
            return false;
        }
    }
    return true;
}

} // namespace ipc
} // namespace cef4j
