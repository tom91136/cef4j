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

# XXX: libzmq 4.3.5 declares a pre-3.5 policy floor rejected by CMake 4; remove when libzmq is upgraded past 4.3.5.
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
    URL      https://github.com/zeromq/libzmq/releases/download/v4.3.5/zeromq-4.3.5.tar.gz
    URL_HASH SHA256=6653ef5910f17954861fe72332e68b03ca6e4d9c7160eb3a8de5a5a913bfab43
    DOWNLOAD_EXTRACT_TIMESTAMP TRUE
)
FetchContent_MakeAvailable(libzmq)
if(TARGET objects)
    target_compile_options(objects PRIVATE
        $<$<CXX_COMPILER_ID:Clang,AppleClang>:-Wno-missing-braces -Wno-missing-field-initializers>)
endif()

set(CEF4J_RUNTIME_SERVER_SOURCES
    src/main/cpp/main.cpp
    src/main/cpp/Envelope.cpp
    src/main/cpp/InterceptExecutor.cpp
    src/main/cpp/RuntimeOptions.cpp
    src/main/cpp/ShutdownCommandMonitor.cpp
    src/main/cpp/IpcServer.cpp
    src/main/cpp/LocalIpcServer.cpp
    src/main/cpp/ZmqIpcServer.cpp
    src/main/cpp/WebSocketIpcServer.cpp
)

if(APPLE)
    enable_language(OBJCXX)
    list(APPEND CEF4J_RUNTIME_SERVER_SOURCES
        src/main/macos/MacApplication.mm
        "${CEF_ROOT}/libcef_dll/wrapper/libcef_dll_dylib.cc")
    set_source_files_properties("${CEF_ROOT}/libcef_dll/wrapper/libcef_dll_dylib.cc" PROPERTIES
        COMPILE_DEFINITIONS "WRAPPING_CEF_SHARED"
        COMPILE_OPTIONS "$<$<CXX_COMPILER_ID:Clang,AppleClang>:-Wno-missing-field-initializers>")
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
        "-framework AppKit" "-framework Foundation" "-ldl")
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

include(CTest)
option(CEF4J_ENABLE_TEST_SANITIZERS "Build native unit tests with AddressSanitizer and UndefinedBehaviorSanitizer" OFF)
if(BUILD_TESTING)
    find_package(Threads REQUIRED)
    add_executable(cef4j-intercept-executor-test
        src/test/cpp/InterceptExecutorTest.cpp
        src/main/cpp/InterceptExecutor.cpp)
    target_include_directories(cef4j-intercept-executor-test PRIVATE src/main/cpp)
    target_link_libraries(cef4j-intercept-executor-test PRIVATE Threads::Threads)
    target_compile_options(cef4j-intercept-executor-test PRIVATE
        $<$<CXX_COMPILER_ID:GNU,Clang,AppleClang>:-Wall -Wextra -Werror>
        $<$<CXX_COMPILER_ID:MSVC>:/W4 /WX>)
    add_test(NAME cef4j-intercept-executor COMMAND cef4j-intercept-executor-test)
    add_executable(cef4j-runtime-options-test
        src/test/cpp/RuntimeOptionsTest.cpp
        src/main/cpp/RuntimeOptions.cpp)
    target_include_directories(cef4j-runtime-options-test PRIVATE src/main/cpp)
    target_compile_options(cef4j-runtime-options-test PRIVATE
        $<$<CXX_COMPILER_ID:GNU,Clang,AppleClang>:-Wall -Wextra -Werror>
        $<$<CXX_COMPILER_ID:MSVC>:/W4 /WX>)
    add_test(NAME cef4j-runtime-options COMMAND cef4j-runtime-options-test)
    add_executable(cef4j-shutdown-command-monitor-test
        src/test/cpp/ShutdownCommandMonitorTest.cpp
        src/main/cpp/ShutdownCommandMonitor.cpp)
    target_include_directories(cef4j-shutdown-command-monitor-test PRIVATE src/main/cpp)
    target_link_libraries(cef4j-shutdown-command-monitor-test PRIVATE Threads::Threads)
    target_compile_options(cef4j-shutdown-command-monitor-test PRIVATE
        $<$<CXX_COMPILER_ID:GNU,Clang,AppleClang>:-Wall -Wextra -Werror>
        $<$<CXX_COMPILER_ID:MSVC>:/W4 /WX>)
    add_test(NAME cef4j-shutdown-command-monitor COMMAND cef4j-shutdown-command-monitor-test)
    if(CEF4J_ENABLE_TEST_SANITIZERS AND CMAKE_CXX_COMPILER_ID MATCHES "GNU|Clang")
        foreach(test_target cef4j-intercept-executor-test cef4j-runtime-options-test cef4j-shutdown-command-monitor-test)
            target_compile_options(${test_target} PRIVATE -fsanitize=address,undefined -fno-omit-frame-pointer)
            target_link_options(${test_target} PRIVATE -fsanitize=address,undefined)
        endforeach()
    endif()
endif()
if(UNIX AND NOT APPLE)
    target_link_options(cef4j-runtime-server PRIVATE -static-libstdc++ -static-libgcc)
elseif(APPLE)
    set(CMAKE_OSX_DEPLOYMENT_TARGET "10.15" CACHE STRING "")
    set_target_properties(cef4j-runtime-server PROPERTIES
        MACOSX_BUNDLE TRUE
        MACOSX_BUNDLE_BUNDLE_NAME "cef4j-runtime-server"
        MACOSX_BUNDLE_GUI_IDENTIFIER "net.kurobako.cef4j.runtime-server"
        MACOSX_BUNDLE_INFO_PLIST
            "${CMAKE_CURRENT_SOURCE_DIR}/src/main/macos/Main-Info.plist.in")
elseif(MSVC)
    set(CMAKE_MSVC_RUNTIME_LIBRARY "MultiThreaded")
    set_target_properties(cef4j-runtime-server PROPERTIES
        RUNTIME_OUTPUT_DIRECTORY_RELEASE "${CMAKE_BINARY_DIR}"
        ARCHIVE_OUTPUT_DIRECTORY_RELEASE "${CMAKE_BINARY_DIR}")
endif()

set(CEF4J_RUNTIME_DIST "${CMAKE_BINARY_DIR}/runtime-server")
add_custom_command(TARGET cef4j-runtime-server POST_BUILD
    COMMAND ${CMAKE_COMMAND} -E make_directory "${CEF4J_RUNTIME_DIST}"
    COMMAND ${CMAKE_COMMAND} -E copy_if_different "$<TARGET_FILE:cef4j-runtime-server>" "${CEF4J_RUNTIME_DIST}/"
    COMMAND ${CMAKE_COMMAND} -E copy_if_different "${CEF_ROOT}/LICENSE.txt" "${CEF4J_RUNTIME_DIST}/CEF-LICENSE.txt")

# XXX: CEF 109 minimal distributions omit CREDITS.html; remove the conditional when every supported CEF ships it.
if(EXISTS "${CEF_ROOT}/CREDITS.html")
    add_custom_command(TARGET cef4j-runtime-server POST_BUILD
        COMMAND ${CMAKE_COMMAND} -E copy_if_different
            "${CEF_ROOT}/CREDITS.html" "${CEF4J_RUNTIME_DIST}/CEF-CREDITS.html")
endif()

if(APPLE)
    set(CEF4J_RUNTIME_HELPER_ROLES default alerts gpu plugin renderer)
    foreach(helper_role IN LISTS CEF4J_RUNTIME_HELPER_ROLES)
        if(helper_role STREQUAL "default")
            set(CEF4J_RUNTIME_HELPER_SUFFIX "")
            set(CEF4J_RUNTIME_HELPER_ID_SUFFIX "")
        elseif(helper_role STREQUAL "alerts")
            set(CEF4J_RUNTIME_HELPER_SUFFIX " (Alerts)")
            set(CEF4J_RUNTIME_HELPER_ID_SUFFIX ".alerts")
        elseif(helper_role STREQUAL "gpu")
            set(CEF4J_RUNTIME_HELPER_SUFFIX " (GPU)")
            set(CEF4J_RUNTIME_HELPER_ID_SUFFIX ".gpu")
        elseif(helper_role STREQUAL "plugin")
            set(CEF4J_RUNTIME_HELPER_SUFFIX " (Plugin)")
            set(CEF4J_RUNTIME_HELPER_ID_SUFFIX ".plugin")
        elseif(helper_role STREQUAL "renderer")
            set(CEF4J_RUNTIME_HELPER_SUFFIX " (Renderer)")
            set(CEF4J_RUNTIME_HELPER_ID_SUFFIX ".renderer")
        endif()
        set(CEF4J_RUNTIME_HELPER_NAME "cef4j-runtime-server Helper${CEF4J_RUNTIME_HELPER_SUFFIX}")
        set(CEF4J_RUNTIME_HELPER_BUNDLE
            "$<TARGET_BUNDLE_DIR:cef4j-runtime-server>/Contents/Frameworks/${CEF4J_RUNTIME_HELPER_NAME}.app")
        set(CEF4J_RUNTIME_HELPER_PLIST "${CMAKE_CURRENT_BINARY_DIR}/Helper-${helper_role}-Info.plist")
        configure_file(
            "${CMAKE_CURRENT_SOURCE_DIR}/src/main/macos/Helper-Info.plist.in"
            "${CEF4J_RUNTIME_HELPER_PLIST}"
            @ONLY)
        add_custom_command(TARGET cef4j-runtime-server POST_BUILD
            COMMAND ${CMAKE_COMMAND} -E make_directory
                "${CEF4J_RUNTIME_HELPER_BUNDLE}/Contents/MacOS"
            COMMAND ${CMAKE_COMMAND} -E make_directory
                "${CEF4J_RUNTIME_HELPER_BUNDLE}/Contents/Resources"
            COMMAND ${CMAKE_COMMAND} -E copy_if_different
                "$<TARGET_FILE:cef4j-runtime-server>"
                "${CEF4J_RUNTIME_HELPER_BUNDLE}/Contents/MacOS/${CEF4J_RUNTIME_HELPER_NAME}"
            COMMAND ${CMAKE_COMMAND} -E copy_if_different
                "${CEF4J_RUNTIME_HELPER_PLIST}"
                "${CEF4J_RUNTIME_HELPER_BUNDLE}/Contents/Info.plist"
            VERBATIM)
    endforeach()
    add_custom_command(TARGET cef4j-runtime-server POST_BUILD
        COMMAND /usr/bin/codesign --force --deep --sign - "$<TARGET_BUNDLE_DIR:cef4j-runtime-server>"
        COMMAND /usr/bin/codesign --verify --deep --strict "$<TARGET_BUNDLE_DIR:cef4j-runtime-server>"
        COMMAND ${CMAKE_COMMAND} -E copy_directory
            "$<TARGET_BUNDLE_DIR:cef4j-runtime-server>"
            "${CEF4J_RUNTIME_DIST}/cef4j-runtime-server.app"
        COMMAND ${CMAKE_COMMAND} -E rm -f "${CEF4J_RUNTIME_DIST}/cef4j-runtime-server"
        VERBATIM)
endif()
