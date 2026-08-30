#include "RuntimeOptions.h"

#include <cstdlib>
#include <stdexcept>
#include <string>

static void require(bool condition, const char* message) {
    if (!condition) throw std::runtime_error(message);
}

static void setEnvironment(const char* name, const char* value) {
#ifdef _WIN32
    require(_putenv_s(name, value) == 0, "could not set environment variable");
#else
    require(setenv(name, value, 1) == 0, "could not set environment variable");
#endif
}

static void clearEnvironment(const char* name) {
#ifdef _WIN32
    require(_putenv_s(name, "") == 0, "could not clear environment variable");
#else
    require(unsetenv(name) == 0, "could not clear environment variable");
#endif
}

int main() {
    char program[] = "runtime";
    char transportFlag[] = "--transport";
    char transport[] = "websocket";
    char frameFlag[] = "--frame-transport";
    char frame[] = "inline";
    char workersFlag[] = "--intercept-workers";
    char workers[] = "3";
    char capacityFlag[] = "--intercept-queue-capacity";
    char capacity[] = "17";
    char* valid[] = {program, transportFlag, transport, frameFlag, frame, workersFlag, workers, capacityFlag, capacity};

    cef4j::runtime::RuntimeOptions parsed = cef4j::runtime::RuntimeOptions::parse(9, valid, 42);
    require(parsed.transport == "websocket", "transport was not parsed");
    require(parsed.frameTransport == "inline", "frame transport was not parsed");
    require(parsed.bindAddress == "ws://127.0.0.1:0/cef4j", "default bind address was incorrect");
    require(parsed.interceptWorkers == 3, "worker count was not parsed");
    require(parsed.interceptQueueCapacity == 17, "queue capacity was not parsed");

    setEnvironment("CEF4J_INTERCEPT_WORKERS", "5");
    setEnvironment("CEF4J_INTERCEPT_QUEUE_CAPACITY", "23");
    char* defaults[] = {program};
    cef4j::runtime::RuntimeOptions configured = cef4j::runtime::RuntimeOptions::parse(1, defaults, 42);
    clearEnvironment("CEF4J_INTERCEPT_WORKERS");
    clearEnvironment("CEF4J_INTERCEPT_QUEUE_CAPACITY");
    require(configured.interceptWorkers == 5, "environment worker count was not parsed");
    require(configured.interceptQueueCapacity == 23, "environment queue capacity was not parsed");

    char invalidFrame[] = "invalid";
    char* invalid[] = {program, frameFlag, invalidFrame};
    bool rejected = false;
    try {
        static_cast<void>(cef4j::runtime::RuntimeOptions::parse(3, invalid, 42));
    } catch (const std::invalid_argument&) {
        rejected = true;
    }
    require(rejected, "invalid frame transport was accepted");
}
