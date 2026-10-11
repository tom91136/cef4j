#pragma once

#include <memory>

#include "IpcServer.h"

namespace cef4j { namespace ipc {

/** Selects the platform/local mechanism from the bind URI while preserving one public transport name. */
class LocalIpcServer final : public IpcServer {
public:
  bool bind(const std::string &addr) override;
  const std::string &endpoint() const override;
  bool setBearerToken(const std::string &token) override;
  void start(FrameHandler handler) override;
  void stop() override;
  bool send(Kind kind, uint8_t flags, int32_t corrId, int32_t messageId, const uint8_t *payload, size_t payloadLen) override;
  bool sendLatest(Kind kind, uint8_t flags, int32_t corrId, int32_t messageId, const uint8_t *payload, size_t payloadLen,
                  int64_t streamId) override;

private:
  std::unique_ptr<IpcServer> delegate_;
  std::string emptyEndpoint_;
};

}} // namespace cef4j::ipc
