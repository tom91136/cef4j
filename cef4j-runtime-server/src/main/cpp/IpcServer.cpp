#include "IpcServer.h"
#include "LocalIpcServer.h"
#ifndef _WIN32
#include "UdsIpcServer.h"
#endif
#include "WebSocketIpcServer.h"
#include "ZmqIpcServer.h"

namespace cef4j {
namespace ipc {

std::unique_ptr<IpcServer> createIpcServer(const std::string& transport) {
    if (transport == "zmq") return std::unique_ptr<IpcServer>(new ZmqIpcServer());
#ifndef _WIN32
    if (transport == "uds") return std::unique_ptr<IpcServer>(new UdsIpcServer());
#endif
    if (transport == "local") return std::unique_ptr<IpcServer>(new LocalIpcServer());
    if (transport == "websocket") return std::unique_ptr<IpcServer>(new WebSocketIpcServer());
    return nullptr;
}

} // namespace ipc
} // namespace cef4j
