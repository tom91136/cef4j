#include "InterceptExecutor.h"

#include <atomic>
#include <chrono>
#include <condition_variable>
#include <mutex>
#include <stdexcept>

static void require(bool condition, const char* message) {
    if (!condition) throw std::runtime_error(message);
}

int main() {
    using namespace std::chrono_literals;
    bool invalidParallelism = false;
    try {
        cef4j::runtime::InterceptExecutor invalid(0, 1);
    } catch (const std::invalid_argument&) {
        invalidParallelism = true;
    }
    require(invalidParallelism, "zero parallelism was accepted");

    bool invalidCapacity = false;
    try {
        cef4j::runtime::InterceptExecutor invalid(1, 0);
    } catch (const std::invalid_argument&) {
        invalidCapacity = true;
    }
    require(invalidCapacity, "zero capacity was accepted");

    std::mutex mutex;
    std::condition_variable stateChanged;
    bool workerEntered = false;
    bool releaseWorker = false;
    std::atomic<int> completed{0};
    cef4j::runtime::InterceptExecutor executor(1, 1);
    require(executor.execute([&] {
        std::unique_lock<std::mutex> lock(mutex);
        workerEntered = true;
        stateChanged.notify_all();
        stateChanged.wait(lock, [&] { return releaseWorker; });
        completed.fetch_add(1);
        stateChanged.notify_all();
    }), "first task was rejected");
    {
        std::unique_lock<std::mutex> lock(mutex);
        require(stateChanged.wait_for(lock, 2s, [&] { return workerEntered; }), "worker did not start");
    }
    require(executor.execute([&] {
        completed.fetch_add(1);
        stateChanged.notify_all();
    }), "queued task was rejected");
    require(!executor.execute([&] { completed.fetch_add(100); }), "overflow task was accepted");
    {
        std::lock_guard<std::mutex> lock(mutex);
        releaseWorker = true;
    }
    stateChanged.notify_all();
    {
        std::unique_lock<std::mutex> lock(mutex);
        require(stateChanged.wait_for(lock, 2s, [&] { return completed.load() == 2; }), "tasks did not complete");
    }
    executor.shutdown();
    require(completed.load() == 2, "unexpected task completion count");
    return 0;
}
