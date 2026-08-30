#include "ShutdownCommandMonitor.h"

#include <atomic>
#include <chrono>
#include <condition_variable>
#include <deque>
#include <mutex>
#include <stdexcept>

using namespace std::chrono_literals;

static void require(bool condition, const char* message) {
    if (!condition) throw std::runtime_error(message);
}

int main() {
    std::mutex mutex;
    std::condition_variable changed;
    std::deque<cef4j::runtime::ShutdownCommandMonitor::ReadResult> results{
        {cef4j::runtime::ShutdownCommandMonitor::Status::Data, "ignored\r\nCEF4J_"},
        {cef4j::runtime::ShutdownCommandMonitor::Status::Data, "SHUTDOWN\r\n"}};
    bool invoked = false;
    {
        cef4j::runtime::ShutdownCommandMonitor monitor(
            [&] {
                std::lock_guard<std::mutex> lock(mutex);
                invoked = true;
                changed.notify_all();
            },
            [&](std::chrono::milliseconds timeout) {
                std::unique_lock<std::mutex> lock(mutex);
                if (results.empty()) {
                    changed.wait_for(lock, timeout);
                    return cef4j::runtime::ShutdownCommandMonitor::ReadResult{
                        cef4j::runtime::ShutdownCommandMonitor::Status::Timeout, {}};
                }
                auto result = std::move(results.front());
                results.pop_front();
                return result;
            });
        std::unique_lock<std::mutex> lock(mutex);
        require(changed.wait_for(lock, 2s, [&] { return invoked; }), "split shutdown command was not detected");
    }

    std::atomic<int> callbacks{0};
    {
        cef4j::runtime::ShutdownCommandMonitor monitor(
            [&] {
                callbacks.fetch_add(1);
                changed.notify_all();
            },
            [](std::chrono::milliseconds) {
                return cef4j::runtime::ShutdownCommandMonitor::ReadResult{
                    cef4j::runtime::ShutdownCommandMonitor::Status::End, "CEF4J_SHUTDOWN"};
            });
        std::unique_lock<std::mutex> lock(mutex);
        require(changed.wait_for(lock, 2s, [&] { return callbacks.load() == 1; }), "EOF command was not detected");
    }
    require(callbacks.load() == 1, "shutdown callback count was incorrect");
}
