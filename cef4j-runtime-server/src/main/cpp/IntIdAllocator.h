#pragma once

#include <cstdint>
#include <limits>
#include <stdexcept>

namespace cef4j { namespace ipc {

class IntIdAllocator {
public:
  template <typename IsOccupied> int32_t allocate(IsOccupied &&occupied) {
    constexpr uint32_t max = static_cast<uint32_t>(std::numeric_limits<int32_t>::max());
    for (uint32_t attempts = 0; attempts < max; ++attempts) {
      uint32_t candidate = next_;
      next_ = candidate == max ? 1U : candidate + 1U;
      auto id = static_cast<int32_t>(candidate);
      if (!occupied(id)) return id;
    }
    throw std::overflow_error("positive int32 id space exhausted");
  }

private:
  uint32_t next_ = 1;
};

}} // namespace cef4j::ipc
