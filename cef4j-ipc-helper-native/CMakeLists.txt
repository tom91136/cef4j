cmake_minimum_required(VERSION 3.20)
project(cef4j-ipc-helper LANGUAGES CXX)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_CXX_EXTENSIONS OFF)
set(CMAKE_POSITION_INDEPENDENT_CODE ON)

if(NOT UNIX OR APPLE)
    message(FATAL_ERROR "cef4j-ipc-helper-native: Slice C is Linux-only. Mac/Windows pending.")
endif()

if(NOT DEFINED CEF_ROOT OR CEF_ROOT STREQUAL "")
    message(FATAL_ERROR "CEF_ROOT must be set to the CEF binary distribution directory")
endif()
if(NOT EXISTS "${CEF_ROOT}/include/cef_version.h")
    message(FATAL_ERROR "CEF_ROOT does not look like a CEF distribution: ${CEF_ROOT}")
endif()

if(DEFINED CEF4J_API_VERSION_RAW AND NOT CEF4J_API_VERSION_RAW STREQUAL "")
    if(CEF4J_API_VERSION_RAW MATCHES "^([0-9]+)\\.")
        set(CEF4J_API_VERSION_MAJOR "${CMAKE_MATCH_1}")
    elseif(CEF4J_API_VERSION_RAW MATCHES "^[0-9]+$")
        set(CEF4J_API_VERSION_MAJOR "${CEF4J_API_VERSION_RAW}")
    else()
        message(FATAL_ERROR "CEF4J_API_VERSION_RAW must be numeric or a version string: ${CEF4J_API_VERSION_RAW}")
    endif()
    set(CEF4J_CEF_API_VERSION "${CEF4J_API_VERSION_MAJOR}")
    if(CEF4J_CEF_API_VERSION GREATER_EQUAL 133 AND CEF4J_CEF_API_VERSION LESS 1000)
        math(EXPR CEF4J_CEF_API_VERSION "${CEF4J_CEF_API_VERSION} * 100")
    endif()
    message(STATUS "cef4j-helper: CEF_API_VERSION=${CEF4J_CEF_API_VERSION}")
endif()

# ----- libzmq via FetchContent ---------------------------------------------------
# libzmq 4.3.5 still declares compatibility with pre-3.5 CMake. CMake 4 removed
# that compatibility mode, so give dependencies the policy floor CMake itself
# recommends while keeping this project's own 3.20 minimum unchanged.
set(CMAKE_POLICY_VERSION_MINIMUM 3.5)
include(FetchContent)
set(BUILD_SHARED OFF CACHE BOOL "" FORCE)
set(BUILD_STATIC ON CACHE BOOL "" FORCE)
set(BUILD_TESTS OFF CACHE BOOL "" FORCE)
set(WITH_PERF_TOOL OFF CACHE BOOL "" FORCE)
set(ENABLE_CPACK OFF CACHE BOOL "" FORCE)
set(ENABLE_DRAFTS OFF CACHE BOOL "" FORCE)
set(WITH_DOCS OFF CACHE BOOL "" FORCE)
FetchContent_Declare(
    libzmq
    GIT_REPOSITORY https://github.com/zeromq/libzmq.git
    GIT_TAG        v4.3.5
    GIT_SHALLOW    TRUE
)
FetchContent_MakeAvailable(libzmq)

# ----- helper executable ---------------------------------------------------------
add_executable(cef4j-helper
    src/main/cpp/main.cpp
    src/main/cpp/Envelope.cpp
    src/main/cpp/IpcServer.cpp
)

if(DEFINED CEF4J_CEF_API_VERSION AND NOT CEF4J_CEF_API_VERSION STREQUAL "")
    target_compile_definitions(cef4j-helper PRIVATE CEF_API_VERSION=${CEF4J_CEF_API_VERSION})
endif()

target_include_directories(cef4j-helper PRIVATE
    ${CEF_ROOT}
    ${CEF_ROOT}/include
    src/main/cpp
    src/main/cpp/gen
)

target_link_libraries(cef4j-helper PRIVATE
    libzmq-static
    ${CEF_ROOT}/Release/libcef${CMAKE_SHARED_LIBRARY_SUFFIX}
    pthread
    dl
)

set_target_properties(cef4j-helper PROPERTIES
    INSTALL_RPATH "$ORIGIN"
    BUILD_RPATH "$ORIGIN"
)
target_compile_options(cef4j-helper PRIVATE -Wall -Wextra -Wno-unused-parameter)
target_link_options(cef4j-helper PRIVATE -static-libstdc++ -static-libgcc)
