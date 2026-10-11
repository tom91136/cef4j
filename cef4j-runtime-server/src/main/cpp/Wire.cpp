#include "Wire.h"

#include <stdexcept>

namespace cef4j::wire {

void fail(const char *type, const char *field, const char *problem, int64_t value, size_t offset, size_t length) {
  std::string message(type);
  if (field) message.append(".").append(field);
  message.append(": ").append(problem).append(" (value ").append(std::to_string(value));
  message.append(" at offset ").append(std::to_string(offset)).append(" of ").append(std::to_string(length)).append(")");
  throw std::invalid_argument(message);
}

} // namespace cef4j::wire
