#pragma once

#include "IpcServer.h"
#include <memory>

namespace cef4j {
namespace ipc {

/** Selects the platform/local mechanism from the bind URI while preserving one public transport name. */
class LocalIpcServer final : public IpcServer {
public:
    bool bind(const std::string& addr) override;
    const std::string& endpoint() const override;
    bool setBearerToken(const std::string& token) override;
    void start(FrameHandler handler) override;
    void stop() override;
    bool send(Kind kind, std::uint8_t flags, std::int32_t corrId, std::int32_t messageId,
              const std::uint8_t* payload, std::size_t payloadLen) override;
    bool sendLatest(Kind kind, std::uint8_t flags, std::int32_t corrId, std::int32_t messageId,
                    const std::uint8_t* payload, std::size_t payloadLen, std::int64_t streamId) override;

private:
    std::unique_ptr<IpcServer> delegate_;
    std::string emptyEndpoint_;
};

} // namespace ipc
} // namespace cef4j
