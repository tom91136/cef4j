#pragma once

#include <atomic>
#include <chrono>
#include <functional>
#include <string>
#include <thread>

namespace cef4j::runtime {

class ShutdownCommandMonitor {
public:
    enum class Status { Data, Timeout, End, Error };

    struct ReadResult {
        Status status;
        std::string data;
    };

    using Callback = std::function<void()>;
    using Reader = std::function<ReadResult(std::chrono::milliseconds)>;

    explicit ShutdownCommandMonitor(Callback callback);
    ShutdownCommandMonitor(Callback callback, Reader reader);
    ~ShutdownCommandMonitor();

    ShutdownCommandMonitor(const ShutdownCommandMonitor&) = delete;
    ShutdownCommandMonitor& operator=(const ShutdownCommandMonitor&) = delete;

private:
    static ReadResult readStdin(std::chrono::milliseconds timeout);
    void run();
    bool consumeLines(std::string& buffered, bool endOfInput);

    Callback callback_;
    Reader reader_;
    std::atomic<bool> stopping_{false};
    std::thread thread_;
};

}
