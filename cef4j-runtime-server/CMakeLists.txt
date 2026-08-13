cmake_minimum_required(VERSION 3.20)
project(cef4j-runtime-server LANGUAGES CXX)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_CXX_EXTENSIONS OFF)
set(CMAKE_POSITION_INDEPENDENT_CODE ON)

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
    message(STATUS "cef4j-runtime-server: CEF_API_VERSION=${CEF4J_CEF_API_VERSION}")
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

# ----- CEF runtime server --------------------------------------------------------
set(CEF4J_RUNTIME_SERVER_SOURCES
    src/main/cpp/main.cpp
    src/main/cpp/Envelope.cpp
    src/main/cpp/IpcServer.cpp
    src/main/cpp/LocalIpcServer.cpp
    src/main/cpp/ZmqIpcServer.cpp
    src/main/cpp/WebSocketIpcServer.cpp
)

if(APPLE)
    list(APPEND CEF4J_RUNTIME_SERVER_SOURCES
        "${CEF_ROOT}/libcef_dll/wrapper/libcef_dll_dylib.cc"
        "${CMAKE_SOURCE_DIR}/../cef4j-native/native/runtime/bundle_fix_mac.mm")
    set_source_files_properties("${CEF_ROOT}/libcef_dll/wrapper/libcef_dll_dylib.cc"
        PROPERTIES COMPILE_DEFINITIONS "WRAPPING_CEF_SHARED")
endif()

add_executable(cef4j-runtime-server ${CEF4J_RUNTIME_SERVER_SOURCES})

if(WIN32)
    target_sources(cef4j-runtime-server PRIVATE src/main/cpp/NamedPipeIpcServer.cpp)
else()
    target_sources(cef4j-runtime-server PRIVATE src/main/cpp/UdsIpcServer.cpp)
endif()

if(DEFINED CEF4J_CEF_API_VERSION AND NOT CEF4J_CEF_API_VERSION STREQUAL "")
    target_compile_definitions(cef4j-runtime-server PRIVATE CEF_API_VERSION=${CEF4J_CEF_API_VERSION})
endif()

if(WIN32)
    target_compile_definitions(cef4j-runtime-server PRIVATE NOMINMAX _CRT_SECURE_NO_WARNINGS)
endif()

target_include_directories(cef4j-runtime-server PRIVATE
    ${CEF_ROOT}
    ${CEF_ROOT}/include
    src/main/cpp
)

if(APPLE)
    target_link_libraries(cef4j-runtime-server PRIVATE libzmq-static
        "-framework Foundation" "-framework AppKit" "-ldl")
elseif(WIN32)
    target_link_libraries(cef4j-runtime-server PRIVATE libzmq-static ${CEF_ROOT}/Release/libcef.lib ws2_32)
else()
    target_link_libraries(cef4j-runtime-server PRIVATE
        libzmq-static ${CEF_ROOT}/Release/libcef${CMAKE_SHARED_LIBRARY_SUFFIX} pthread dl)
endif()

if(UNIX)
    set_target_properties(cef4j-runtime-server PROPERTIES INSTALL_RPATH "$ORIGIN" BUILD_RPATH "$ORIGIN")
endif()
target_compile_options(cef4j-runtime-server PRIVATE
    $<$<CXX_COMPILER_ID:GNU,Clang,AppleClang>:-Wall -Wextra -Wno-unused-parameter>
    $<$<CXX_COMPILER_ID:MSVC>:/W4 /wd4100 /bigobj>)
if(UNIX AND NOT APPLE)
    target_link_options(cef4j-runtime-server PRIVATE -static-libstdc++ -static-libgcc)
elseif(APPLE)
    set(CMAKE_OSX_DEPLOYMENT_TARGET "10.15" CACHE STRING "")
elseif(MSVC)
    set(CMAKE_MSVC_RUNTIME_LIBRARY "MultiThreaded")
    set_target_properties(cef4j-runtime-server PROPERTIES
        RUNTIME_OUTPUT_DIRECTORY_RELEASE "${CMAKE_BINARY_DIR}"
        ARCHIVE_OUTPUT_DIRECTORY_RELEASE "${CMAKE_BINARY_DIR}")
endif()

# Assemble a self-contained runtime-server directory. The JVM launches only this executable; CEF and its native
# subprocess dependencies remain on the server side and never enter the client classpath or library path.
set(CEF4J_RUNTIME_DIST "${CMAKE_BINARY_DIR}/runtime-server")
add_custom_command(TARGET cef4j-runtime-server POST_BUILD
    COMMAND ${CMAKE_COMMAND} -E make_directory "${CEF4J_RUNTIME_DIST}"
    COMMAND ${CMAKE_COMMAND} -E copy_if_different "$<TARGET_FILE:cef4j-runtime-server>" "${CEF4J_RUNTIME_DIST}/"
    COMMAND ${CMAKE_COMMAND} -E copy_if_different "${CEF_ROOT}/LICENSE.txt" "${CEF4J_RUNTIME_DIST}/CEF-LICENSE.txt"
    COMMAND ${CMAKE_COMMAND} -E copy_if_different "${CEF_ROOT}/CREDITS.html" "${CEF4J_RUNTIME_DIST}/CEF-CREDITS.html")

if(APPLE)
    add_custom_command(TARGET cef4j-runtime-server POST_BUILD
        COMMAND ${CMAKE_COMMAND} -E make_directory "${CEF4J_RUNTIME_DIST}/Frameworks"
        COMMAND ${CMAKE_COMMAND} -E copy_directory
            "${CEF_ROOT}/Release/Chromium Embedded Framework.framework"
            "${CEF4J_RUNTIME_DIST}/Frameworks/Chromium Embedded Framework.framework")
    file(GLOB CEF4J_MAC_GRAPHICS_LIBS
        "${CEF_ROOT}/Release/Chromium Embedded Framework.framework/Libraries/*.dylib"
        "${CEF_ROOT}/Release/Chromium Embedded Framework.framework/Libraries/*.json")
    foreach(runtime_file IN LISTS CEF4J_MAC_GRAPHICS_LIBS)
        add_custom_command(TARGET cef4j-runtime-server POST_BUILD
            COMMAND ${CMAKE_COMMAND} -E copy_if_different "${runtime_file}" "${CEF4J_RUNTIME_DIST}/")
    endforeach()
else()
    if(WIN32)
        file(GLOB CEF4J_RUNTIME_FILES
            "${CEF_ROOT}/Release/*.dll" "${CEF_ROOT}/Release/*.bin"
            "${CEF_ROOT}/Release/*.json" "${CEF_ROOT}/Release/*.pak")
    else()
        file(GLOB CEF4J_RUNTIME_FILES
            "${CEF_ROOT}/Release/*.so" "${CEF_ROOT}/Release/*.so.*" "${CEF_ROOT}/Release/*.bin"
            "${CEF_ROOT}/Release/*.json" "${CEF_ROOT}/Release/*.pak")
    endif()
    foreach(runtime_file IN LISTS CEF4J_RUNTIME_FILES)
        add_custom_command(TARGET cef4j-runtime-server POST_BUILD
            COMMAND ${CMAKE_COMMAND} -E copy_if_different "${runtime_file}" "${CEF4J_RUNTIME_DIST}/")
    endforeach()
    add_custom_command(TARGET cef4j-runtime-server POST_BUILD
        COMMAND ${CMAKE_COMMAND} -E copy_directory "${CEF_ROOT}/Resources" "${CEF4J_RUNTIME_DIST}")
endif()
