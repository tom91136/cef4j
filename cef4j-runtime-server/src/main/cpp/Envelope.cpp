#include "Envelope.h"

namespace cef4j { namespace ipc {

static void writeI32LE(uint8_t *dst, int32_t v) {
  auto u = static_cast<uint32_t>(v);
  dst[0] = static_cast<uint8_t>(u & 0xFF);
  dst[1] = static_cast<uint8_t>((u >> 8) & 0xFF);
  dst[2] = static_cast<uint8_t>((u >> 16) & 0xFF);
  dst[3] = static_cast<uint8_t>((u >> 24) & 0xFF);
}

static int32_t readI32LE(const uint8_t *src) {
  uint32_t u = static_cast<uint32_t>(src[0]) | (static_cast<uint32_t>(src[1]) << 8) | (static_cast<uint32_t>(src[2]) << 16)
               | (static_cast<uint32_t>(src[3]) << 24);
  return static_cast<int32_t>(u);
}

void writeHeader(uint8_t *dst, Kind kind, uint8_t flags, int32_t corrId, int32_t messageId, int32_t payloadLength) {
  writeI32LE(dst + 0, payloadLength);
  dst[4] = static_cast<uint8_t>(kind);
  dst[5] = flags;
  writeI32LE(dst + 6, corrId);
  writeI32LE(dst + 10, messageId);
}

bool readHeader(const uint8_t *src, size_t frameSize, Header &out) {
  if (!src || frameSize < HeaderSize) return false;
  out.payloadLength = readI32LE(src + 0);
  auto k = src[4];
  if (k < 1 || k > 6) return false;
  out.kind = static_cast<Kind>(k);
  out.flags = src[5];
  out.corrId = readI32LE(src + 6);
  out.messageId = readI32LE(src + 10);
  return out.payloadLength >= 0 && static_cast<size_t>(out.payloadLength) == frameSize - HeaderSize;
}

}} // namespace cef4j::ipc
