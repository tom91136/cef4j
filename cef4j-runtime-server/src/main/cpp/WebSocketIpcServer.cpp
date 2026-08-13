#include "WebSocketIpcServer.h"

#include <array>
#include <cctype>
#include <cerrno>
#include <cstdio>
#include <cstring>
#include <map>
#include <sstream>
#ifdef _WIN32
#include <ws2tcpip.h>
#else
#include <arpa/inet.h>
#include <fcntl.h>
#include <poll.h>
#include <sys/socket.h>
#include <unistd.h>
#endif

namespace cef4j {
namespace ipc {

namespace {
constexpr std::uint64_t kMaxFrameSize = 64ULL * 1024ULL * 1024ULL;
constexpr std::size_t kMaxHandshakeSize = 16U * 1024U;
constexpr const char* kWebSocketGuid = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
#ifdef _WIN32
constexpr short kReadable = POLLRDNORM;
#else
constexpr short kReadable = POLLIN;
#endif

struct Address {
    std::string host;
    std::uint16_t port = 0;
    std::string path;
};

bool parseAddress(const std::string& value, Address& output) {
    constexpr const char* prefix = "ws://";
    if (value.compare(0, std::strlen(prefix), prefix) != 0) return false;
    std::size_t authorityStart = std::strlen(prefix);
    std::size_t pathStart = value.find('/', authorityStart);
    std::string authority = value.substr(authorityStart, pathStart - authorityStart);
    std::size_t colon = authority.rfind(':');
    if (colon == std::string::npos) return false;
    output.host = authority.substr(0, colon);
    output.path = pathStart == std::string::npos ? "/" : value.substr(pathStart);
    try {
        unsigned long port = std::stoul(authority.substr(colon + 1));
        if (port > 65535) return false;
        output.port = static_cast<std::uint16_t>(port);
    } catch (...) {
        return false;
    }
    return (output.host == "127.0.0.1" || output.host == "localhost") && !output.path.empty();
}

using SocketHandle = WebSocketIpcServer::SocketHandle;

int socketError() {
#ifdef _WIN32
    return ::WSAGetLastError();
#else
    return errno;
#endif
}

bool interrupted(int error) {
#ifdef _WIN32
    return error == WSAEINTR;
#else
    return error == EINTR;
#endif
}

void closeSocket(SocketHandle socket) {
#ifdef _WIN32
    if (socket != INVALID_SOCKET) ::closesocket(socket);
#else
    if (socket >= 0) ::close(socket);
#endif
}

bool readAll(SocketHandle fd, void* output, std::size_t length) {
    auto* cursor = static_cast<std::uint8_t*>(output);
    while (length > 0) {
        int n = ::recv(fd, reinterpret_cast<char*>(cursor), static_cast<int>(length), 0);
        if (n == 0) return false;
        if (n < 0) {
            if (interrupted(socketError())) continue;
            return false;
        }
        cursor += n;
        length -= static_cast<std::size_t>(n);
    }
    return true;
}

bool writeAll(SocketHandle fd, const void* input, std::size_t length) {
    const auto* cursor = static_cast<const std::uint8_t*>(input);
    while (length > 0) {
        int flags = 0;
#if !defined(_WIN32) && !defined(__APPLE__)
        flags = MSG_NOSIGNAL;
#endif
        int n = ::send(fd, reinterpret_cast<const char*>(cursor), static_cast<int>(length), flags);
        if (n < 0) {
            if (interrupted(socketError())) continue;
            return false;
        }
        cursor += n;
        length -= static_cast<std::size_t>(n);
    }
    return true;
}

std::string trim(std::string value) {
    while (!value.empty() && std::isspace(static_cast<unsigned char>(value.front()))) value.erase(value.begin());
    while (!value.empty() && std::isspace(static_cast<unsigned char>(value.back()))) value.pop_back();
    return value;
}

std::string lower(std::string value) {
    for (char& c : value) c = static_cast<char>(std::tolower(static_cast<unsigned char>(c)));
    return value;
}

std::uint32_t rotateLeft(std::uint32_t value, unsigned bits) {
    return (value << bits) | (value >> (32U - bits));
}

std::array<std::uint8_t, 20> sha1(const std::string& input) {
    std::vector<std::uint8_t> message(input.begin(), input.end());
    std::uint64_t bitLength = static_cast<std::uint64_t>(message.size()) * 8U;
    message.push_back(0x80);
    while ((message.size() % 64U) != 56U) message.push_back(0);
    for (int shift = 56; shift >= 0; shift -= 8) message.push_back(static_cast<std::uint8_t>(bitLength >> shift));

    std::uint32_t h0 = 0x67452301U;
    std::uint32_t h1 = 0xEFCDAB89U;
    std::uint32_t h2 = 0x98BADCFEU;
    std::uint32_t h3 = 0x10325476U;
    std::uint32_t h4 = 0xC3D2E1F0U;
    for (std::size_t offset = 0; offset < message.size(); offset += 64) {
        std::uint32_t words[80]{};
        for (int i = 0; i < 16; ++i) {
            std::size_t p = offset + static_cast<std::size_t>(i) * 4U;
            words[i] = (static_cast<std::uint32_t>(message[p]) << 24U) |
                       (static_cast<std::uint32_t>(message[p + 1]) << 16U) |
                       (static_cast<std::uint32_t>(message[p + 2]) << 8U) | message[p + 3];
        }
        for (int i = 16; i < 80; ++i) words[i] = rotateLeft(words[i - 3] ^ words[i - 8] ^ words[i - 14] ^ words[i - 16], 1);
        std::uint32_t a = h0, b = h1, c = h2, d = h3, e = h4;
        for (int i = 0; i < 80; ++i) {
            std::uint32_t f;
            std::uint32_t k;
            if (i < 20) {
                f = (b & c) | ((~b) & d);
                k = 0x5A827999U;
            } else if (i < 40) {
                f = b ^ c ^ d;
                k = 0x6ED9EBA1U;
            } else if (i < 60) {
                f = (b & c) | (b & d) | (c & d);
                k = 0x8F1BBCDCU;
            } else {
                f = b ^ c ^ d;
                k = 0xCA62C1D6U;
            }
            std::uint32_t next = rotateLeft(a, 5) + f + e + k + words[i];
            e = d;
            d = c;
            c = rotateLeft(b, 30);
            b = a;
            a = next;
        }
        h0 += a;
        h1 += b;
        h2 += c;
        h3 += d;
        h4 += e;
    }
    std::array<std::uint8_t, 20> digest{};
    const std::uint32_t hashes[] = {h0, h1, h2, h3, h4};
    for (std::size_t i = 0; i < 5; ++i) {
        digest[i * 4] = static_cast<std::uint8_t>(hashes[i] >> 24U);
        digest[i * 4 + 1] = static_cast<std::uint8_t>(hashes[i] >> 16U);
        digest[i * 4 + 2] = static_cast<std::uint8_t>(hashes[i] >> 8U);
        digest[i * 4 + 3] = static_cast<std::uint8_t>(hashes[i]);
    }
    return digest;
}

std::string base64(const std::array<std::uint8_t, 20>& input) {
    static constexpr char alphabet[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    std::string output;
    for (std::size_t i = 0; i < input.size(); i += 3) {
        std::uint32_t value = static_cast<std::uint32_t>(input[i]) << 16U;
        if (i + 1 < input.size()) value |= static_cast<std::uint32_t>(input[i + 1]) << 8U;
        if (i + 2 < input.size()) value |= input[i + 2];
        output.push_back(alphabet[(value >> 18U) & 63U]);
        output.push_back(alphabet[(value >> 12U) & 63U]);
        output.push_back(i + 1 < input.size() ? alphabet[(value >> 6U) & 63U] : '=');
        output.push_back(i + 2 < input.size() ? alphabet[value & 63U] : '=');
    }
    return output;
}

std::vector<std::uint8_t> envelope(Kind kind, std::uint8_t flags, std::int32_t corrId,
                                   std::int32_t messageId, const std::uint8_t* payload,
                                   std::size_t payloadLen) {
    std::vector<std::uint8_t> frame(kHeaderSize + payloadLen);
    writeHeader(frame.data(), kind, flags, corrId, messageId, static_cast<std::int32_t>(payloadLen));
    if (payload && payloadLen > 0) std::memcpy(frame.data() + kHeaderSize, payload, payloadLen);
    return frame;
}
} // namespace

WebSocketIpcServer::~WebSocketIpcServer() {
    stop();
    closeSocket(clientFd_);
    closeSocket(listenFd_);
#ifndef _WIN32
    if (wakeReadFd_ >= 0) ::close(wakeReadFd_);
    if (wakeWriteFd_ >= 0) ::close(wakeWriteFd_);
#else
    ::WSACleanup();
#endif
}

bool WebSocketIpcServer::bind(const std::string& addr) {
#ifdef _WIN32
    WSADATA winsock{};
    if (::WSAStartup(MAKEWORD(2, 2), &winsock) != 0) return false;
#endif
    Address parsed;
    if (!parseAddress(addr, parsed)) {
        std::fprintf(stderr, "[cef4j-runtime-server] invalid WebSocket endpoint: %s\n", addr.c_str());
        return false;
    }
    host_ = parsed.host == "localhost" ? "127.0.0.1" : parsed.host;
    path_ = parsed.path;
    listenFd_ = ::socket(AF_INET, SOCK_STREAM, 0);
    if (listenFd_ == kInvalidSocket) return false;
    int reuse = 1;
    (void)::setsockopt(listenFd_, SOL_SOCKET, SO_REUSEADDR,
                       reinterpret_cast<const char*>(&reuse), sizeof(reuse));
#ifdef __APPLE__
    int noSigPipe = 1;
    (void)::setsockopt(listenFd_, SOL_SOCKET, SO_NOSIGPIPE, &noSigPipe, sizeof(noSigPipe));
#endif
    sockaddr_in address{};
    address.sin_family = AF_INET;
    address.sin_port = htons(parsed.port);
    if (::inet_pton(AF_INET, host_.c_str(), &address.sin_addr) != 1 ||
        ::bind(listenFd_, reinterpret_cast<sockaddr*>(&address), sizeof(address)) != 0 || ::listen(listenFd_, 1) != 0) {
        std::fprintf(stderr, "[cef4j-runtime-server] WebSocket bind(%s) failed: %d\n", addr.c_str(), socketError());
        return false;
    }
#ifdef _WIN32
    int addressLength = sizeof(address);
#else
    socklen_t addressLength = sizeof(address);
#endif
    if (::getsockname(listenFd_, reinterpret_cast<sockaddr*>(&address), &addressLength) != 0) return false;
#ifndef _WIN32
    int wake[2];
    if (::pipe(wake) != 0) return false;
    wakeReadFd_ = wake[0];
    wakeWriteFd_ = wake[1];
    int wakeFlags = ::fcntl(wakeWriteFd_, F_GETFL, 0);
    if (wakeFlags < 0 || ::fcntl(wakeWriteFd_, F_SETFL, wakeFlags | O_NONBLOCK) != 0) return false;
#endif
    endpoint_ = "ws://" + host_ + ":" + std::to_string(ntohs(address.sin_port)) + path_;
    return true;
}

void WebSocketIpcServer::start(FrameHandler handler) {
    handler_ = std::move(handler);
    stop_ = false;
    running_ = true;
    worker_ = std::thread([this] { workerLoop(); });
}

void WebSocketIpcServer::stop() {
    if (!running_.exchange(false)) return;
    stop_ = true;
    {
        std::lock_guard<std::mutex> lock(clientMu_);
        if (clientFd_ != kInvalidSocket) {
#ifdef _WIN32
            ::shutdown(clientFd_, SD_BOTH);
#else
            ::shutdown(clientFd_, SHUT_RDWR);
#endif
        }
    }
#ifndef _WIN32
    const std::uint8_t wake = 1;
    if (wakeWriteFd_ >= 0) (void)::write(wakeWriteFd_, &wake, 1);
#endif
    if (worker_.joinable()) worker_.join();
}

bool WebSocketIpcServer::send(Kind kind, std::uint8_t flags, std::int32_t corrId, std::int32_t messageId,
                              const std::uint8_t* payload, std::size_t payloadLen) {
    if (!running_ || payloadLen > kMaxFrameSize - kHeaderSize) return false;
    {
        std::lock_guard<std::mutex> lock(outboundMu_);
        outbound_.push_back({envelope(kind, flags, corrId, messageId, payload, payloadLen), false, 0});
    }
#ifdef _WIN32
    return true;
#else
    const std::uint8_t wake = 1;
    if (wakeWriteFd_ < 0) return false;
    ssize_t result = ::write(wakeWriteFd_, &wake, 1);
    return result == 1 || (result < 0 && errno == EAGAIN);
#endif
}

bool WebSocketIpcServer::sendLatest(Kind kind, std::uint8_t flags, std::int32_t corrId,
                                    std::int32_t messageId, const std::uint8_t* payload,
                                    std::size_t payloadLen, std::int64_t streamId) {
    if (!running_ || payloadLen > kMaxFrameSize - kHeaderSize) return false;
    OutboundFrame latest{envelope(kind, flags, corrId, messageId, payload, payloadLen), true, streamId};
    {
        std::lock_guard<std::mutex> lock(outboundMu_);
        bool replaced = false;
        for (auto it = outbound_.rbegin(); it != outbound_.rend(); ++it) {
            if (it->replaceable && it->streamId == streamId) {
                *it = std::move(latest);
                replaced = true;
                break;
            }
        }
        if (!replaced) outbound_.push_back(std::move(latest));
    }
#ifdef _WIN32
    return true;
#else
    const std::uint8_t wake = 1;
    if (wakeWriteFd_ < 0) return false;
    ssize_t result = ::write(wakeWriteFd_, &wake, 1);
    return result == 1 || (result < 0 && errno == EAGAIN);
#endif
}

void WebSocketIpcServer::workerLoop() {
    while (!stop_) {
#ifdef _WIN32
        WSAPOLLFD descriptors[2] = {
            {listenFd_, POLLRDNORM, 0},
            {clientFd_, POLLRDNORM, 0}};
        int n = ::WSAPoll(descriptors, 2, 20);
#else
        pollfd descriptors[3] = {{listenFd_, POLLIN, 0}, {clientFd_, POLLIN, 0}, {wakeReadFd_, POLLIN, 0}};
        int n = ::poll(descriptors, 3, 200);
#endif
        if (n < 0) {
            if (interrupted(socketError())) continue;
            break;
        }
        if (descriptors[0].revents & kReadable) {
            SocketHandle accepted = ::accept(listenFd_, nullptr, nullptr);
            if (accepted != kInvalidSocket) {
#ifdef __APPLE__
                int noSigPipe = 1;
                (void)::setsockopt(accepted, SOL_SOCKET, SO_NOSIGPIPE, &noSigPipe, sizeof(noSigPipe));
#endif
                closeClient();
                {
                    std::lock_guard<std::mutex> lock(clientMu_);
                    clientFd_ = accepted;
                }
                if (handshake(accepted)) (void)drainOutbound();
                else closeClient();
            }
        }
        if (clientFd_ != kInvalidSocket
                && (descriptors[1].revents & (kReadable | POLLHUP | POLLERR))) {
            if (!drainIncoming()) closeClient();
        }
#ifndef _WIN32
        if (descriptors[2].revents & POLLIN) {
            std::uint8_t buffer[64];
            (void)::read(wakeReadFd_, buffer, sizeof(buffer));
            if (!drainOutbound()) closeClient();
        }
#else
        if (!drainOutbound()) closeClient();
#endif
    }
}

bool WebSocketIpcServer::handshake(SocketHandle fd) {
    std::string request;
    std::array<char, 1024> buffer{};
    while (request.find("\r\n\r\n") == std::string::npos && request.size() < kMaxHandshakeSize) {
        int n = ::recv(fd, buffer.data(), static_cast<int>(buffer.size()), 0);
        if (n < 0 && interrupted(socketError())) continue;
        if (n <= 0) return false;
        request.append(buffer.data(), static_cast<std::size_t>(n));
    }
    if (request.size() >= kMaxHandshakeSize) return false;
    std::istringstream lines(request);
    std::string line;
    if (!std::getline(lines, line)) return false;
    if (!line.empty() && line.back() == '\r') line.pop_back();
    if (line != "GET " + path_ + " HTTP/1.1") return false;
    std::map<std::string, std::string> headers;
    while (std::getline(lines, line)) {
        if (!line.empty() && line.back() == '\r') line.pop_back();
        if (line.empty()) break;
        std::size_t colon = line.find(':');
        if (colon != std::string::npos) headers[lower(trim(line.substr(0, colon)))] = trim(line.substr(colon + 1));
    }
    auto key = headers.find("sec-websocket-key");
    auto upgrade = headers.find("upgrade");
    auto connection = headers.find("connection");
    if (key == headers.end() || upgrade == headers.end() || lower(upgrade->second) != "websocket" ||
        connection == headers.end() || lower(connection->second).find("upgrade") == std::string::npos) return false;
    if (!bearerToken_.empty()) {
        auto authorization = headers.find("authorization");
        if (authorization == headers.end() || authorization->second != "Bearer " + bearerToken_) {
            static constexpr char unauthorized[] =
                    "HTTP/1.1 401 Unauthorized\r\nWWW-Authenticate: Bearer\r\nContent-Length: 0\r\n\r\n";
            (void)writeAll(fd, unauthorized, sizeof(unauthorized) - 1);
            return false;
        }
    }
    std::string accept = base64(sha1(key->second + kWebSocketGuid));
    std::string response = "HTTP/1.1 101 Switching Protocols\r\nUpgrade: websocket\r\nConnection: Upgrade\r\nSec-WebSocket-Accept: " + accept + "\r\n\r\n";
    return writeAll(fd, response.data(), response.size());
}

bool WebSocketIpcServer::drainIncoming() {
    std::uint8_t header[2];
    if (!readAll(clientFd_, header, sizeof(header))) return false;
    bool fin = (header[0] & 0x80U) != 0;
    std::uint8_t opcode = header[0] & 0x0FU;
    bool masked = (header[1] & 0x80U) != 0;
    std::uint64_t length = header[1] & 0x7FU;
    if (!masked || (header[0] & 0x70U) != 0) return false;
    if (length == 126) {
        std::uint8_t encoded[2];
        if (!readAll(clientFd_, encoded, sizeof(encoded))) return false;
        length = (static_cast<std::uint64_t>(encoded[0]) << 8U) | encoded[1];
    } else if (length == 127) {
        std::uint8_t encoded[8];
        if (!readAll(clientFd_, encoded, sizeof(encoded)) || (encoded[0] & 0x80U) != 0) return false;
        length = 0;
        for (std::uint8_t byte : encoded) length = (length << 8U) | byte;
    }
    bool control = opcode >= 0x8U;
    if (length > kMaxFrameSize || (control && (!fin || length > 125))) return false;
    std::uint8_t mask[4];
    if (!readAll(clientFd_, mask, sizeof(mask))) return false;
    std::vector<std::uint8_t> payload(static_cast<std::size_t>(length));
    if (!payload.empty() && !readAll(clientFd_, payload.data(), payload.size())) return false;
    for (std::size_t i = 0; i < payload.size(); ++i) payload[i] ^= mask[i % 4U];

    if (opcode == 0x8U) {
        (void)sendWebSocketFrame(0x8U, payload.data(), payload.size());
        return false;
    }
    if (opcode == 0x9U) return sendWebSocketFrame(0xAU, payload.data(), payload.size());
    if (opcode == 0xAU) return true;
    if (opcode == 0x2U) {
        if (fragmented_) return false;
        inboundMessage_ = std::move(payload);
        fragmented_ = !fin;
    } else if (opcode == 0x0U) {
        if (!fragmented_ || inboundMessage_.size() + payload.size() > kMaxFrameSize) return false;
        inboundMessage_.insert(inboundMessage_.end(), payload.begin(), payload.end());
        fragmented_ = !fin;
    } else {
        return false;
    }
    if (!fin) return true;
    if (inboundMessage_.size() < kHeaderSize) return false;
    Header envelope;
    if (!readHeader(inboundMessage_.data(), inboundMessage_.size(), envelope)) {
        inboundMessage_.clear();
        return true;
    }
    std::vector<std::uint8_t> body(inboundMessage_.begin() + kHeaderSize, inboundMessage_.end());
    inboundMessage_.clear();
    if (handler_) handler_(envelope, std::move(body));
    return true;
}

bool WebSocketIpcServer::drainOutbound() {
    if (clientFd_ == kInvalidSocket) return true;
    std::deque<OutboundFrame> batch;
    {
        std::lock_guard<std::mutex> lock(outboundMu_);
        batch.swap(outbound_);
    }
    for (const auto& frame : batch) {
        if (!sendWebSocketFrame(0x2U, frame.bytes.data(), frame.bytes.size())) return false;
    }
    return true;
}

bool WebSocketIpcServer::sendWebSocketFrame(std::uint8_t opcode, const std::uint8_t* payload, std::size_t length) {
    if (clientFd_ == kInvalidSocket) return false;
    std::vector<std::uint8_t> header;
    header.push_back(static_cast<std::uint8_t>(0x80U | opcode));
    if (length < 126) {
        header.push_back(static_cast<std::uint8_t>(length));
    } else if (length <= 0xFFFFU) {
        header.push_back(126);
        header.push_back(static_cast<std::uint8_t>(length >> 8U));
        header.push_back(static_cast<std::uint8_t>(length));
    } else {
        header.push_back(127);
        for (int shift = 56; shift >= 0; shift -= 8) header.push_back(static_cast<std::uint8_t>(static_cast<std::uint64_t>(length) >> shift));
    }
    return writeAll(clientFd_, header.data(), header.size()) && (length == 0 || writeAll(clientFd_, payload, length));
}

void WebSocketIpcServer::closeClient() {
    std::lock_guard<std::mutex> lock(clientMu_);
    if (clientFd_ != kInvalidSocket) {
        closeSocket(clientFd_);
        clientFd_ = kInvalidSocket;
    }
    fragmented_ = false;
    inboundMessage_.clear();
}

} // namespace ipc
} // namespace cef4j
