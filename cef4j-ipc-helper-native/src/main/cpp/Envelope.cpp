#include "Envelope.h"

namespace cef4j {
namespace ipc {

static void writeI32LE(std::uint8_t* dst, std::int32_t v) {
    auto u = static_cast<std::uint32_t>(v);
    dst[0] = static_cast<std::uint8_t>(u & 0xFF);
    dst[1] = static_cast<std::uint8_t>((u >> 8) & 0xFF);
    dst[2] = static_cast<std::uint8_t>((u >> 16) & 0xFF);
    dst[3] = static_cast<std::uint8_t>((u >> 24) & 0xFF);
}

static std::int32_t readI32LE(const std::uint8_t* src) {
    std::uint32_t u = static_cast<std::uint32_t>(src[0])
            | (static_cast<std::uint32_t>(src[1]) << 8)
            | (static_cast<std::uint32_t>(src[2]) << 16)
            | (static_cast<std::uint32_t>(src[3]) << 24);
    return static_cast<std::int32_t>(u);
}

void writeHeader(
        std::uint8_t* dst,
        Kind kind,
        std::uint8_t flags,
        std::int32_t corrId,
        std::int32_t messageId,
        std::int32_t payloadLength) {
    writeI32LE(dst + 0, payloadLength);
    dst[4] = static_cast<std::uint8_t>(kind);
    dst[5] = flags;
    writeI32LE(dst + 6, corrId);
    writeI32LE(dst + 10, messageId);
}

bool readHeader(const std::uint8_t* src, Header& out) {
    out.payloadLength = readI32LE(src + 0);
    auto k = src[4];
    if (k < 1 || k > 5) return false;
    out.kind = static_cast<Kind>(k);
    out.flags = src[5];
    out.corrId = readI32LE(src + 6);
    out.messageId = readI32LE(src + 10);
    return true;
}

} // namespace ipc
} // namespace cef4j
