cmake_minimum_required(VERSION 3.21)
project(cef4j)

# C++17 - required by hand-written runtime (structured bindings, std::optional,
# if-constexpr, inline variables used in ref-counting and value marshaling).
set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_CXX_EXTENSIONS OFF)

# --- Static C++ runtime linkage ---
# Goal: libcef4j.so/dll/dylib depends only on libcef + system libs.

# Auto-discover generated + runtime sources
file(GLOB_RECURSE GENERATED_SOURCES "${CMAKE_SOURCE_DIR}/native/gen/*.cpp")
file(GLOB_RECURSE RUNTIME_SOURCES   "${CMAKE_SOURCE_DIR}/native/runtime/*.cpp")

# Exclude subprocess helper from shared library sources
list(FILTER RUNTIME_SOURCES EXCLUDE REGEX "subprocess_main\\.cpp$")

add_library(cef4j SHARED ${GENERATED_SOURCES} ${RUNTIME_SOURCES})

# Detect JNI platform include directory
if(WIN32)
    set(JNI_PLATFORM_DIR "win32")
elseif(APPLE)
    set(JNI_PLATFORM_DIR "darwin")
else()
    set(JNI_PLATFORM_DIR "linux")
endif()

target_include_directories(cef4j PRIVATE
    ${CEF_ROOT}
    ${CEF_ROOT}/include
    ${JAVA_HOME}/include
    ${JAVA_HOME}/include/${JNI_PLATFORM_DIR}
    ${CMAKE_SOURCE_DIR}/native/runtime
)

# Link against libcef (dynamic - always a shared library)
target_link_libraries(cef4j PRIVATE ${CEF_ROOT}/Release/libcef${CMAKE_SHARED_LIBRARY_SUFFIX})

if(MSVC)
    # /MT = static CRT (release). Eliminates MSVC redistributable dependency.
    set(CMAKE_MSVC_RUNTIME_LIBRARY "MultiThreaded")
    foreach(flag CMAKE_CXX_FLAGS CMAKE_CXX_FLAGS_RELEASE)
        string(REPLACE "/MD" "/MT" ${flag} "${${flag}}")
    endforeach()

elseif(APPLE)
    # macOS: libc++ is a system framework - always available, no static needed.
    set(CMAKE_OSX_DEPLOYMENT_TARGET "10.15" CACHE STRING "")

elseif(UNIX)
    # Linux: statically link libstdc++ and libgcc_s.
    target_link_options(cef4j PRIVATE
        -static-libstdc++
        -static-libgcc
    )
    # Hide all symbols except JNI exports
    target_link_options(cef4j PRIVATE -Wl,--version-script=${CMAKE_SOURCE_DIR}/cef4j.map)
endif()

# --- Subprocess helper executable ---
# CEF launches this for renderer/GPU/utility processes.
add_executable(cef4j_helper "${CMAKE_SOURCE_DIR}/native/runtime/subprocess_main.cpp")
target_include_directories(cef4j_helper PRIVATE ${CEF_ROOT})
target_link_libraries(cef4j_helper PRIVATE ${CEF_ROOT}/Release/libcef${CMAKE_SHARED_LIBRARY_SUFFIX})
if(UNIX AND NOT APPLE)
    target_link_options(cef4j_helper PRIVATE -static-libstdc++ -static-libgcc)
endif()

# --- RPATH: use $ORIGIN so binaries find libcef.so in the same directory ---
if(UNIX)
    set_target_properties(cef4j PROPERTIES
        INSTALL_RPATH "$ORIGIN"
        BUILD_RPATH "$ORIGIN"
    )
    set_target_properties(cef4j_helper PROPERTIES
        INSTALL_RPATH "$ORIGIN"
        BUILD_RPATH "$ORIGIN"
    )
endif()
