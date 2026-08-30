#include "ShutdownCommandMonitor.h"

#include <array>
#include <utility>

#ifdef _WIN32
#include <windows.h>
#else
#include <cerrno>
#include <poll.h>
#include <unistd.h>
#endif

namespace cef4j::runtime {

namespace {
constexpr auto kReadInterval = std::chrono::milliseconds(100);
constexpr const char* kShutdownCommand = "CEF4J_SHUTDOWN";
}

ShutdownCommandMonitor::ShutdownCommandMonitor(Callback callback)
    : ShutdownCommandMonitor(std::move(callback), readStdin) {}

ShutdownCommandMonitor::ShutdownCommandMonitor(Callback callback, Reader reader)
    : callback_(std::move(callback)), reader_(std::move(reader)), thread_(&ShutdownCommandMonitor::run, this) {}

ShutdownCommandMonitor::~ShutdownCommandMonitor() {
    stopping_.store(true, std::memory_order_release);
    if (thread_.joinable()) thread_.join();
}

void ShutdownCommandMonitor::run() {
    std::string buffered;
    while (!stopping_.load(std::memory_order_acquire)) {
        ReadResult result = reader_(kReadInterval);
        if (result.status == Status::Data) {
            buffered.append(result.data);
            if (consumeLines(buffered, false)) return;
        } else if (result.status == Status::End) {
            buffered.append(result.data);
            consumeLines(buffered, true);
            return;
        } else if (result.status == Status::Error) {
            return;
        }
    }
}

bool ShutdownCommandMonitor::consumeLines(std::string& buffered, bool endOfInput) {
    std::size_t start = 0;
    while (true) {
        std::size_t newline = buffered.find('\n', start);
        if (newline == std::string::npos) break;
        std::size_t end = newline;
        if (end > start && buffered[end - 1] == '\r') --end;
        if (buffered.compare(start, end - start, kShutdownCommand) == 0 && end - start == std::char_traits<char>::length(kShutdownCommand)) {
            callback_();
            return true;
        }
        start = newline + 1;
    }
    buffered.erase(0, start);
    if (endOfInput) {
        if (!buffered.empty() && buffered.back() == '\r') buffered.pop_back();
        if (buffered == kShutdownCommand) {
            callback_();
            return true;
        }
    }
    return false;
}

ShutdownCommandMonitor::ReadResult ShutdownCommandMonitor::readStdin(std::chrono::milliseconds timeout) {
    std::array<char, 256> buffer{};
#ifdef _WIN32
    HANDLE input = GetStdHandle(STD_INPUT_HANDLE);
    if (input == nullptr || input == INVALID_HANDLE_VALUE) return {Status::Error, {}};
    DWORD wait = WaitForSingleObject(input, static_cast<DWORD>(timeout.count()));
    if (wait == WAIT_TIMEOUT) return {Status::Timeout, {}};
    if (wait != WAIT_OBJECT_0) return {Status::Error, {}};
    DWORD count = 0;
    if (!ReadFile(input, buffer.data(), static_cast<DWORD>(buffer.size()), &count, nullptr)) {
        DWORD error = GetLastError();
        if (error == ERROR_BROKEN_PIPE || error == ERROR_HANDLE_EOF) return {Status::End, {}};
        return {Status::Error, {}};
    }
    if (count == 0) return {Status::End, {}};
    return {Status::Data, std::string(buffer.data(), count)};
#else
    pollfd input{STDIN_FILENO, POLLIN, 0};
    int ready;
    do {
        ready = poll(&input, 1, static_cast<int>(timeout.count()));
    } while (ready < 0 && errno == EINTR);
    if (ready == 0) return {Status::Timeout, {}};
    if (ready < 0 || (input.revents & POLLNVAL) != 0) return {Status::Error, {}};
    ssize_t count;
    do {
        count = read(STDIN_FILENO, buffer.data(), buffer.size());
    } while (count < 0 && errno == EINTR);
    if (count == 0) return {Status::End, {}};
    if (count < 0) return {Status::Error, {}};
    return {Status::Data, std::string(buffer.data(), static_cast<std::size_t>(count))};
#endif
}

}
