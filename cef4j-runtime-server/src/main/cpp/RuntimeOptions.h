#ifndef CEF4J_RUNTIME_OPTIONS_H_
#define CEF4J_RUNTIME_OPTIONS_H_

#include <string>

namespace cef4j::runtime {

struct RuntimeOptions {
    std::string transport;
    std::string frameTransport;
    std::string bindAddress;
    unsigned int interceptWorkers;
    unsigned int interceptQueueCapacity;

    static RuntimeOptions parse(int argc, char* argv[], int processId);
};

} // namespace cef4j::runtime

#endif
