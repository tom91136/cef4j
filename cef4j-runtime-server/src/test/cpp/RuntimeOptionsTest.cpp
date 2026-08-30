#include "RuntimeOptions.h"

#include <stdexcept>
#include <string>

static void require(bool condition, const char* message) {
    if (!condition) throw std::runtime_error(message);
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
