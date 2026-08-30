#pragma once

#include <condition_variable>
#include <cstddef>
#include <deque>
#include <functional>
#include <mutex>
#include <thread>
#include <vector>

namespace cef4j::runtime {

class InterceptExecutor {
public:
    InterceptExecutor(unsigned int parallelism, std::size_t maxPending);
    ~InterceptExecutor();

    InterceptExecutor(const InterceptExecutor&) = delete;
    InterceptExecutor& operator=(const InterceptExecutor&) = delete;

    bool execute(std::function<void()> work);
    void shutdown();

private:
    void run();

    const std::size_t maxPending_;
    std::mutex mutex_;
    std::condition_variable ready_;
    std::deque<std::function<void()>> pending_;
    std::vector<std::thread> workers_;
    bool stopping_ = false;
};

} // namespace cef4j::runtime
