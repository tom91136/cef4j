# Available for future native components; CEF itself has no RISC-V binary today.
set(CEF4J_SYSTEM_PROCESSOR riscv64)
set(CEF4J_HOST_PROCESSOR_REGEX "^riscv64$")
set(CEF4J_TARGET_TRIPLE riscv64-linux-gnu)
set(CEF4J_EXTRA_FLAGS "")
include("${CMAKE_CURRENT_LIST_DIR}/linux-common.cmake")
