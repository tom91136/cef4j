// Wire-format envelope, mirrors net.kurobako.cef4j.ipc.session.Envelope.
// All fields little-endian; payload is opaque to this layer.
#pragma once

#include <cstddef>
#include <cstdint>

namespace cef4j { namespace ipc {

constexpr size_t HeaderSize = 14;
constexpr int32_t NoCorrId = -1;

enum class Kind : uint8_t {
  Request = 1,
  Response = 2,
  Event = 3,
  Intercept = 4,
  InterceptResponse = 5,
  Error = 6,
};

// Structured error codes carried in Kind::Error payload (matches CefRemoteException on the JVM side).
namespace ErrorCode {
constexpr int32_t ReceiverGone = 1;
constexpr int32_t MalformedRequest = 2;
constexpr int32_t TaskRejected = 3;
constexpr int32_t UnsupportedFeature = 4;
} // namespace ErrorCode

struct Header {
  int32_t payloadLength;
  Kind kind;
  uint8_t flags;
  int32_t corrId;
  int32_t messageId;
};

// dst must point to at least HeaderSize writable bytes.
void writeHeader(uint8_t *dst, Kind kind, uint8_t flags, int32_t corrId, int32_t messageId, int32_t payloadLength);

// src must point to at least HeaderSize readable bytes.
// Returns false if the kind byte is unknown.
bool readHeader(const uint8_t *src, size_t frameSize, Header &out);

}} // namespace cef4j::ipc
