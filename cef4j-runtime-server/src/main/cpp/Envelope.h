// Wire-format envelope, mirrors net.kurobako.cef4j.ipc.session.Envelope.
// All fields little-endian; payload is opaque to this layer.
#pragma once

#include <cstddef>
#include <cstdint>

namespace cef4j {
namespace ipc {

constexpr std::size_t kHeaderSize = 14;
constexpr std::int32_t kNoCorrId = -1;

enum class Kind : std::uint8_t {
    Request = 1,
    Response = 2,
    Event = 3,
    Intercept = 4,
    InterceptResponse = 5,
    Error = 6,
};

// Structured error codes carried in Kind::Error payload (matches CefRemoteException on the JVM side).
namespace ErrorCode {
    constexpr std::int32_t ReceiverGone = 1;
    constexpr std::int32_t MalformedRequest = 2;
    constexpr std::int32_t TaskRejected = 3;
}

struct Header {
    std::int32_t payloadLength;
    Kind kind;
    std::uint8_t flags;
    std::int32_t corrId;
    std::int32_t messageId;
};

// dst must point to at least kHeaderSize writable bytes.
void writeHeader(
        std::uint8_t* dst,
        Kind kind,
        std::uint8_t flags,
        std::int32_t corrId,
        std::int32_t messageId,
        std::int32_t payloadLength);

// src must point to at least kHeaderSize readable bytes.
// Returns false if the kind byte is unknown.
bool readHeader(const std::uint8_t* src, std::size_t frameSize, Header& out);

} // namespace ipc
} // namespace cef4j
