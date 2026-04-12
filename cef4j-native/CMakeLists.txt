cmake_minimum_required(VERSION 3.15)
project(cef4j)

if(NOT DEFINED CEF_ROOT OR CEF_ROOT STREQUAL "")
    message(FATAL_ERROR "CEF_ROOT must be set to the CEF binary distribution directory")
endif()
if(NOT DEFINED JAVA_HOME OR JAVA_HOME STREQUAL "")
    message(FATAL_ERROR "JAVA_HOME must be set to the JDK installation directory")
endif()
if(NOT EXISTS "${CEF_ROOT}/include/cef_version.h")
    message(FATAL_ERROR "CEF_ROOT does not look like a CEF distribution: ${CEF_ROOT}")
endif()
if(NOT EXISTS "${JAVA_HOME}/include/jni.h")
    message(FATAL_ERROR "JAVA_HOME does not look like a JDK: ${JAVA_HOME}")
endif()

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_CXX_EXTENSIONS OFF)

if(WIN32)
    set(CEF4J_GEN_PLATFORM_DIR "win")
elseif(APPLE)
    set(CEF4J_GEN_PLATFORM_DIR "mac")
else()
    set(CEF4J_GEN_PLATFORM_DIR "linux")
endif()

file(GLOB GENERATED_COMMON_SOURCES "${CMAKE_SOURCE_DIR}/native/gen/*.cpp")
file(GLOB_RECURSE GENERATED_PLATFORM_SOURCES "${CMAKE_SOURCE_DIR}/native/gen/${CEF4J_GEN_PLATFORM_DIR}/*.cpp")
set(GENERATED_SOURCES ${GENERATED_COMMON_SOURCES} ${GENERATED_PLATFORM_SOURCES})
file(GLOB_RECURSE RUNTIME_SOURCES   "${CMAKE_SOURCE_DIR}/native/runtime/*.cpp")

list(FILTER RUNTIME_SOURCES EXCLUDE REGEX "subprocess_main\\.cpp$")

if(APPLE)
    # On macOS, CEF is loaded dynamically via cef_load_library() (from libcef_dll_dylib.cc)
    # instead of linking against the framework directly.
    set(CEF_DYLIB_LOADER "${CEF_ROOT}/libcef_dll/wrapper/libcef_dll_dylib.cc")
    set(BUNDLE_FIX_MM "${CMAKE_SOURCE_DIR}/native/runtime/bundle_fix_mac.mm")
    add_library(cef4j SHARED ${GENERATED_SOURCES} ${RUNTIME_SOURCES} ${CEF_DYLIB_LOADER} ${BUNDLE_FIX_MM})
    set_source_files_properties(${CEF_DYLIB_LOADER} PROPERTIES COMPILE_DEFINITIONS "WRAPPING_CEF_SHARED")
else()
    add_library(cef4j SHARED ${GENERATED_SOURCES} ${RUNTIME_SOURCES})
endif()

set_source_files_properties(${GENERATED_SOURCES} PROPERTIES
    COMPILE_OPTIONS "$<$<CXX_COMPILER_ID:GNU,Clang,AppleClang>:-Wno-unused-parameter>"
)

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
    ${CMAKE_SOURCE_DIR}/native/gen
    ${CMAKE_SOURCE_DIR}/native/gen/${CEF4J_GEN_PLATFORM_DIR}
)

if(APPLE)
    # cef4j loads the CEF framework dynamically via cef_load_library() / dlopen.
    # Link only the system frameworks needed at compile/link time.
    target_link_libraries(cef4j PRIVATE "-framework Foundation" "-framework AppKit" "-ldl")
else()
    target_link_libraries(cef4j PRIVATE ${CEF_ROOT}/Release/libcef${CMAKE_SHARED_LIBRARY_SUFFIX})
endif()

target_compile_options(cef4j PRIVATE
    $<$<CXX_COMPILER_ID:GNU,Clang,AppleClang>:-Wall -Wextra -pedantic>
    $<$<CXX_COMPILER_ID:MSVC>:/W4>
)

if(MSVC)
    set(CMAKE_MSVC_RUNTIME_LIBRARY "MultiThreaded")
    foreach(flag CMAKE_CXX_FLAGS CMAKE_CXX_FLAGS_RELEASE)
        string(REPLACE "/MD" "/MT" ${flag} "${${flag}}")
    endforeach()
elseif(APPLE)
    set(CMAKE_OSX_DEPLOYMENT_TARGET "10.15" CACHE STRING "")
elseif(UNIX)
    target_link_options(cef4j PRIVATE -static-libstdc++ -static-libgcc)
    target_link_options(cef4j PRIVATE -Wl,--version-script=${CMAKE_SOURCE_DIR}/cef4j.map)
    target_link_options(cef4j PRIVATE -Wl,--no-undefined)
endif()

if(APPLE)
    set(LAUNCHER_DYLIB_LOADER "${CEF_ROOT}/libcef_dll/wrapper/libcef_dll_dylib.cc")
    add_executable(cef4j_launcher
        "${CMAKE_SOURCE_DIR}/native/runtime/subprocess_main.cpp"
        ${LAUNCHER_DYLIB_LOADER}
        "${CMAKE_SOURCE_DIR}/native/runtime/bundle_fix_mac.mm"
    )
    set_source_files_properties(${LAUNCHER_DYLIB_LOADER} PROPERTIES COMPILE_DEFINITIONS "WRAPPING_CEF_SHARED")
    target_include_directories(cef4j_launcher PRIVATE ${CEF_ROOT})
    target_link_libraries(cef4j_launcher PRIVATE "-framework Foundation" "-framework AppKit" "-ldl")
else()
    add_executable(cef4j_launcher "${CMAKE_SOURCE_DIR}/native/runtime/subprocess_main.cpp")
    target_include_directories(cef4j_launcher PRIVATE ${CEF_ROOT})
    target_link_libraries(cef4j_launcher PRIVATE ${CEF_ROOT}/Release/libcef${CMAKE_SHARED_LIBRARY_SUFFIX})
endif()
if(UNIX AND NOT APPLE)
    target_link_options(cef4j_launcher PRIVATE -static-libstdc++ -static-libgcc)
endif()

if(UNIX)
    set_target_properties(cef4j PROPERTIES
        INSTALL_RPATH "$ORIGIN"
        BUILD_RPATH "$ORIGIN"
    )
    set_target_properties(cef4j_launcher PROPERTIES
        INSTALL_RPATH "$ORIGIN"
        BUILD_RPATH "$ORIGIN"
    )
endif()
