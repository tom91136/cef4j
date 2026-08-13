#include "LocalIpcServer.h"
#include "ZmqIpcServer.h"
#ifdef _WIN32
#include "NamedPipeIpcServer.h"
#else
#include "UdsIpcServer.h"
#endif

namespace cef4j {
namespace ipc {

bool LocalIpcServer::bind(const std::string& addr) {
    if (addr.rfind("tcp://", 0) == 0) {
        delegate_ = std::make_unique<ZmqIpcServer>();
#ifdef _WIN32
    } else if (addr.rfind("pipe://", 0) == 0) {
        delegate_ = std::make_unique<NamedPipeIpcServer>();
#else
    } else if (addr.rfind("unix://", 0) == 0) {
        delegate_ = std::make_unique<UdsIpcServer>();
#endif
    } else {
        return false;
    }
    return delegate_->bind(addr);
}

const std::string& LocalIpcServer::endpoint() const {
    return delegate_ ? delegate_->endpoint() : emptyEndpoint_;
}

bool LocalIpcServer::setBearerToken(const std::string& token) {
    return delegate_ && delegate_->setBearerToken(token);
}

void LocalIpcServer::start(FrameHandler handler) { delegate_->start(std::move(handler)); }
void LocalIpcServer::stop() { if (delegate_) delegate_->stop(); }

bool LocalIpcServer::send(Kind kind, std::uint8_t flags, std::int32_t corrId, std::int32_t messageId,
                          const std::uint8_t* payload, std::size_t payloadLen) {
    return delegate_ && delegate_->send(kind, flags, corrId, messageId, payload, payloadLen);
}

bool LocalIpcServer::sendLatest(Kind kind, std::uint8_t flags, std::int32_t corrId, std::int32_t messageId,
                                const std::uint8_t* payload, std::size_t payloadLen, std::int64_t streamId) {
    return delegate_ && delegate_->sendLatest(kind, flags, corrId, messageId, payload, payloadLen, streamId);
}

} // namespace ipc
} // namespace cef4j
