#include "InterceptExecutor.h"

#include <cstdio>
#include <stdexcept>
#include <utility>

namespace cef4j::runtime {

InterceptExecutor::InterceptExecutor(unsigned int parallelism, std::size_t maxPending)
    : maxPending_(maxPending) {
    if (parallelism == 0) throw std::invalid_argument("intercept parallelism must be positive");
    if (maxPending == 0) throw std::invalid_argument("intercept queue capacity must be positive");
    workers_.reserve(parallelism);
    try {
        for (unsigned int i = 0; i < parallelism; ++i) workers_.emplace_back([this] { run(); });
    } catch (...) {
        shutdown();
        throw;
    }
}

InterceptExecutor::~InterceptExecutor() { shutdown(); }

bool InterceptExecutor::execute(std::function<void()> work) {
    {
        std::lock_guard<std::mutex> lock(mutex_);
        if (stopping_) throw std::logic_error("intercept executor is stopped");
        if (pending_.size() >= maxPending_) return false;
        pending_.push_back(std::move(work));
    }
    ready_.notify_one();
    return true;
}

void InterceptExecutor::shutdown() {
    {
        std::lock_guard<std::mutex> lock(mutex_);
        if (stopping_) return;
        stopping_ = true;
        pending_.clear();
    }
    ready_.notify_all();
    for (auto& worker : workers_) {
        if (worker.joinable()) worker.join();
    }
    workers_.clear();
}

void InterceptExecutor::run() {
    while (true) {
        std::function<void()> work;
        {
            std::unique_lock<std::mutex> lock(mutex_);
            ready_.wait(lock, [this] { return stopping_ || !pending_.empty(); });
            if (stopping_ && pending_.empty()) return;
            work = std::move(pending_.front());
            pending_.pop_front();
        }
        try {
            work();
        } catch (const std::exception& failure) {
            std::fprintf(stderr, "[cef4j-runtime-server] intercept worker failed: %s\n", failure.what());
        } catch (...) {
            std::fprintf(stderr, "[cef4j-runtime-server] intercept worker failed\n");
        }
    }
}

} // namespace cef4j::runtime
