#pragma once

#include <cstddef>
#include <cstdint>
#include <cstring>
#include <string>
#include <vector>

namespace cef4j::wire {

constexpr size_t MaxFieldBytes = 64U * 1024U * 1024U;
constexpr size_t MaxCollectionItems = 1000000U;

// Out of line so each decode site only carries its type and field names.
[[noreturn]] void fail(const char *type, const char *field, const char *problem, int64_t value, size_t offset, size_t length);

inline size_t encodedSize(const std::vector<std::string> &values) {
  size_t n = 4;
  for (const auto &value : values)
    n += 4 + value.size();
  return n;
}

/** Little-endian reader over a bounded payload; every failure names the message type, field, and offset. */
class Reader {
public:
  Reader(const uint8_t *src, size_t len, const char *type) : src_(src), len_(len), type_(type) {}

  int32_t i32(const char *field) {
    require(4, field);
    uint32_t value = 0;
    for (int i = 0; i < 4; ++i)
      value |= static_cast<uint32_t>(src_[pos_ + i]) << (i * 8);
    pos_ += 4;
    return static_cast<int32_t>(value);
  }

  int64_t i64(const char *field) {
    require(8, field);
    uint64_t value = 0;
    for (int i = 0; i < 8; ++i)
      value |= static_cast<uint64_t>(src_[pos_ + i]) << (i * 8);
    pos_ += 8;
    return static_cast<int64_t>(value);
  }

  bool boolean(const char *field) {
    require(1, field);
    return src_[pos_++] != 0;
  }

  std::string string(const char *field) {
    size_t n = length(field);
    std::string value(reinterpret_cast<const char *>(src_ + pos_), n);
    pos_ += n;
    return value;
  }

  std::vector<uint8_t> bytes(const char *field) {
    size_t n = length(field);
    std::vector<uint8_t> value(src_ + pos_, src_ + pos_ + n);
    pos_ += n;
    return value;
  }

  std::vector<std::string> strings(const char *field) {
    int32_t count = i32(field);
    if (count < 0) fail(type_, field, "negative count", count, pos_, len_);
    if (static_cast<size_t>(count) > MaxCollectionItems) fail(type_, field, "oversized count", count, pos_, len_);
    if (static_cast<size_t>(count) > (len_ - pos_) / 4) fail(type_, field, "count exceeds payload", count, pos_, len_);
    std::vector<std::string> values;
    values.reserve(static_cast<size_t>(count));
    for (int32_t i = 0; i < count; ++i)
      values.push_back(string(field));
    return values;
  }

  template <typename T> T nested(const char *field) {
    T value = T::decode(src_ + pos_, len_ - pos_);
    size_t n = value.encodedSize();
    require(n, field);
    pos_ += n;
    return value;
  }

  void finish() const {
    if (pos_ != len_) fail(type_, nullptr, "trailing bytes", static_cast<int64_t>(len_ - pos_), pos_, len_);
  }

private:
  void require(size_t count, const char *field) const {
    if (pos_ > len_ || count > len_ - pos_) fail(type_, field, "truncated", static_cast<int64_t>(count), pos_, len_);
  }

  size_t length(const char *field) {
    int32_t n = i32(field);
    if (n < 0) fail(type_, field, "negative length", n, pos_, len_);
    if (static_cast<size_t>(n) > MaxFieldBytes) fail(type_, field, "oversized length", n, pos_, len_);
    require(static_cast<size_t>(n), field);
    return static_cast<size_t>(n);
  }

  const uint8_t *src_;
  size_t len_;
  size_t pos_ = 0;
  const char *type_;
};

/** Little-endian writer into a buffer of at least the message's encodedSize() bytes. */
class Writer {
public:
  explicit Writer(uint8_t *dst) : dst_(dst) {}

  void i32(int32_t value) {
    auto bits = static_cast<uint32_t>(value);
    for (int i = 0; i < 4; ++i)
      dst_[pos_ + i] = static_cast<uint8_t>(bits >> (i * 8));
    pos_ += 4;
  }

  void i64(int64_t value) {
    auto bits = static_cast<uint64_t>(value);
    for (int i = 0; i < 8; ++i)
      dst_[pos_ + i] = static_cast<uint8_t>(bits >> (i * 8));
    pos_ += 8;
  }

  void boolean(bool value) { dst_[pos_++] = value ? 1 : 0; }

  void string(const std::string &value) { raw(value.data(), value.size()); }

  void bytes(const std::vector<uint8_t> &value) { raw(value.data(), value.size()); }

  void strings(const std::vector<std::string> &values) {
    i32(static_cast<int32_t>(values.size()));
    for (const auto &value : values)
      string(value);
  }

  template <typename T> void nested(const T &value) {
    value.encodeInto(dst_ + pos_);
    pos_ += value.encodedSize();
  }

private:
  void raw(const void *data, size_t n) {
    i32(static_cast<int32_t>(n));
    if (n != 0) std::memcpy(dst_ + pos_, data, n);
    pos_ += n;
  }

  uint8_t *dst_;
  size_t pos_ = 0;
};

} // namespace cef4j::wire
