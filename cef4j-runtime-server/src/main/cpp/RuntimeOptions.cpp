#include "RuntimeOptions.h"

#include <cerrno>
#include <cstdlib>
#include <limits>
#include <memory>
#include <stdexcept>
#include <thread>

namespace cef4j::runtime {
namespace {

std::string option(int argc, char* argv[], const char* name, const std::string& fallback) {
    for (int i = 1; i + 1 < argc; ++i) {
        if (std::string(argv[i]) == name) return argv[i + 1];
    }
    return fallback;
}

unsigned int positiveInteger(const std::string& value, const char* description) {
    char* end = nullptr;
    errno = 0;
    unsigned long parsed = std::strtoul(value.c_str(), &end, 10);
    if (errno != 0 || end == value.c_str() || *end != '\0' || parsed == 0
        || parsed > std::numeric_limits<unsigned int>::max()) {
        throw std::invalid_argument(std::string(description) + " must be a positive integer");
    }
    return static_cast<unsigned int>(parsed);
}

std::string environmentOr(const char* name, std::string fallback) {
#ifdef _WIN32
    char* configured = nullptr;
    std::size_t length = 0;
    if (_dupenv_s(&configured, &length, name) != 0) return fallback;
    std::unique_ptr<char, decltype(&std::free)> owned(configured, &std::free);
    return owned ? std::string(owned.get()) : fallback;
#else
    if (const char* configured = std::getenv(name)) return configured;
    return fallback;
#endif
}

} // namespace

RuntimeOptions RuntimeOptions::parse(int argc, char* argv[], int processId) {
    unsigned int detected = std::thread::hardware_concurrency();
    std::string workers = environmentOr("CEF4J_INTERCEPT_WORKERS", std::to_string(detected == 0 ? 1 : detected));
    std::string capacity = environmentOr("CEF4J_INTERCEPT_QUEUE_CAPACITY", "4096");
    std::string transport = option(argc, argv, "--transport", "zmq");
    std::string frameTransport = option(argc, argv, "--frame-transport", "shared-file");
    if (frameTransport != "shared-file" && frameTransport != "mmap" && frameTransport != "inline") {
        throw std::invalid_argument("unknown frame transport: " + frameTransport);
    }
#ifdef _WIN32
    std::string defaultLocal = "pipe://cef4j-runtime-server-" + std::to_string(processId);
#else
    std::string defaultLocal = "tcp://127.0.0.1:0";
#endif
    std::string defaultBind = transport == "uds"
                                  ? "unix:///tmp/cef4j-runtime-server-" + std::to_string(processId) + ".sock"
                              : transport == "local" ? defaultLocal
                              : transport == "websocket" ? "ws://127.0.0.1:0/cef4j"
                                                         : "tcp://127.0.0.1:0";
    return RuntimeOptions{
        transport,
        frameTransport,
        option(argc, argv, "--bind", defaultBind),
        positiveInteger(option(argc, argv, "--intercept-workers", workers), "intercept worker count"),
        positiveInteger(option(argc, argv, "--intercept-queue-capacity", capacity), "intercept queue capacity")
    };
}

} // namespace cef4j::runtime
