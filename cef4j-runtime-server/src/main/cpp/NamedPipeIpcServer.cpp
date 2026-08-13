#include "NamedPipeIpcServer.h"

#ifdef _WIN32
#include <algorithm>
#include <cstdio>
#include <cstring>

namespace cef4j {
namespace ipc {

namespace {
constexpr std::uint32_t kMaxFrameSize = 64U * 1024U * 1024U;

bool readExact(HANDLE pipe, void* output, std::size_t length) {
    auto* cursor = static_cast<std::uint8_t*>(output);
    while (length > 0) {
        DWORD read = 0;
        DWORD chunk = static_cast<DWORD>(length > MAXDWORD ? MAXDWORD : length);
        if (!::ReadFile(pipe, cursor, chunk, &read, nullptr) || read == 0) return false;
        cursor += read;
        length -= read;
    }
    return true;
}

bool writeExact(HANDLE pipe, const void* input, std::size_t length) {
    const auto* cursor = static_cast<const std::uint8_t*>(input);
    while (length > 0) {
        DWORD written = 0;
        DWORD chunk = static_cast<DWORD>(length > MAXDWORD ? MAXDWORD : length);
        if (!::WriteFile(pipe, cursor, chunk, &written, nullptr) || written == 0) return false;
        cursor += written;
        length -= written;
    }
    return true;
}
} // namespace

NamedPipeIpcServer::~NamedPipeIpcServer() {
    stop();
    if (pipe_ != INVALID_HANDLE_VALUE) ::CloseHandle(pipe_);
}

bool NamedPipeIpcServer::bind(const std::string& addr) {
    constexpr const char* prefix = "pipe://";
    std::string name = addr.rfind(prefix, 0) == 0 ? addr.substr(std::strlen(prefix)) : addr;
    if (name.empty() || name.find_first_not_of("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789._-")
            != std::string::npos) return false;
    endpoint_ = std::string(prefix) + name;
    pipePath_ = "\\\\.\\pipe\\" + name;
    pipe_ = ::CreateNamedPipeA(
            pipePath_.c_str(), PIPE_ACCESS_DUPLEX, PIPE_TYPE_BYTE | PIPE_READMODE_BYTE | PIPE_WAIT,
            1, 1024 * 1024, 1024 * 1024, 0, nullptr);
    if (pipe_ == INVALID_HANDLE_VALUE) {
        std::fprintf(stderr, "[cef4j-runtime-server] CreateNamedPipe(%s) failed: %lu\n",
                     pipePath_.c_str(), static_cast<unsigned long>(::GetLastError()));
        return false;
    }
    return true;
}

void NamedPipeIpcServer::start(FrameHandler handler) {
    handler_ = std::move(handler);
    stop_ = false;
    running_ = true;
    worker_ = std::thread([this] { workerLoop(); });
}

void NamedPipeIpcServer::stop() {
    if (!running_.exchange(false)) return;
    stop_ = true;
    if (pipe_ != INVALID_HANDLE_VALUE) {
        ::CancelIoEx(pipe_, nullptr);
        ::DisconnectNamedPipe(pipe_);
    }
    // ConnectNamedPipe may be blocked before a client has arrived. Opening the pipe releases it.
    HANDLE wake = ::CreateFileA(pipePath_.c_str(), GENERIC_READ | GENERIC_WRITE, 0, nullptr, OPEN_EXISTING, 0, nullptr);
    if (wake != INVALID_HANDLE_VALUE) ::CloseHandle(wake);
    if (worker_.joinable()) worker_.join();
}

bool NamedPipeIpcServer::send(Kind kind, std::uint8_t flags, std::int32_t corrId, std::int32_t messageId,
                              const std::uint8_t* payload, std::size_t payloadLen) {
    if (!running_ || payloadLen > kMaxFrameSize - kHeaderSize) return false;
    std::vector<std::uint8_t> frame(kHeaderSize + payloadLen);
    writeHeader(frame.data(), kind, flags, corrId, messageId, static_cast<std::int32_t>(payloadLen));
    if (payload && payloadLen > 0) std::memcpy(frame.data() + kHeaderSize, payload, payloadLen);
    std::lock_guard<std::mutex> lock(outboundMu_);
    outbound_.push_back(std::move(frame));
    return true;
}

bool NamedPipeIpcServer::sendLatest(Kind kind, std::uint8_t flags, std::int32_t corrId, std::int32_t messageId,
                                    const std::uint8_t* payload, std::size_t payloadLen, std::int64_t streamId) {
    if (!running_ || payloadLen > kMaxFrameSize - kHeaderSize) return false;
    std::vector<std::uint8_t> frame(kHeaderSize + payloadLen);
    writeHeader(frame.data(), kind, flags, corrId, messageId, static_cast<std::int32_t>(payloadLen));
    if (payload && payloadLen > 0) std::memcpy(frame.data() + kHeaderSize, payload, payloadLen);
    std::lock_guard<std::mutex> lock(outboundMu_);
    auto it = std::find_if(latest_.begin(), latest_.end(),
                           [streamId](const auto& item) { return item.first == streamId; });
    if (it == latest_.end()) latest_.emplace_back(streamId, std::move(frame));
    else it->second = std::move(frame);
    return true;
}

void NamedPipeIpcServer::workerLoop() {
    while (!stop_) {
        if (!connectClient()) continue;
        while (!stop_) {
            DWORD available = 0;
            if (!::PeekNamedPipe(pipe_, nullptr, 0, nullptr, &available, nullptr)) break;
            if (available >= sizeof(std::uint32_t) && !drainIncoming()) break;
            if (!drainOutbound()) break;
            ::Sleep(2);
        }
        disconnectClient();
    }
}

bool NamedPipeIpcServer::connectClient() {
    BOOL connected = ::ConnectNamedPipe(pipe_, nullptr);
    return connected || ::GetLastError() == ERROR_PIPE_CONNECTED;
}

bool NamedPipeIpcServer::drainIncoming() {
    std::uint8_t encodedLength[4];
    if (!readExact(pipe_, encodedLength, sizeof(encodedLength))) return false;
    std::uint32_t length = (static_cast<std::uint32_t>(encodedLength[0]) << 24U)
                         | (static_cast<std::uint32_t>(encodedLength[1]) << 16U)
                         | (static_cast<std::uint32_t>(encodedLength[2]) << 8U)
                         | encodedLength[3];
    if (length < kHeaderSize || length > kMaxFrameSize) return false;
    std::vector<std::uint8_t> frame(length);
    if (!readExact(pipe_, frame.data(), frame.size())) return false;
    Header header;
    if (!readHeader(frame.data(), frame.size(), header)) return true;
    std::vector<std::uint8_t> payload(frame.begin() + kHeaderSize, frame.end());
    if (handler_) handler_(header, std::move(payload));
    return true;
}

bool NamedPipeIpcServer::drainOutbound() {
    std::deque<std::vector<std::uint8_t>> batch;
    {
        std::lock_guard<std::mutex> lock(outboundMu_);
        batch.swap(outbound_);
        for (auto& item : latest_) batch.push_back(std::move(item.second));
        latest_.clear();
    }
    for (const auto& frame : batch) {
        std::uint32_t length = static_cast<std::uint32_t>(frame.size());
        std::uint8_t encodedLength[] = {
            static_cast<std::uint8_t>(length >> 24U), static_cast<std::uint8_t>(length >> 16U),
            static_cast<std::uint8_t>(length >> 8U), static_cast<std::uint8_t>(length)};
        if (!writeExact(pipe_, encodedLength, sizeof(encodedLength))
                || !writeExact(pipe_, frame.data(), frame.size())) return false;
    }
    return true;
}

void NamedPipeIpcServer::disconnectClient() {
    if (pipe_ != INVALID_HANDLE_VALUE) ::DisconnectNamedPipe(pipe_);
}

} // namespace ipc
} // namespace cef4j
#endif
