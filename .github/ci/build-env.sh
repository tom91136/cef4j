#!/usr/bin/env bash

normalize_java_home() {
    case "$(uname -s)" in
        MINGW*|MSYS*|CYGWIN*) cygpath --unix "$1" ;;
        *) printf '%s\n' "$1" ;;
    esac
}

java_specification_version() {
    sed -n 's/^[[:space:]]*java.specification.version = //p' | tr -d '\r'
}

cef_extra_args() {
    case "$1" in
        macosx64|macosarm64)
            printf '%s\n' '--use-gl=angle,--use-angle=swiftshader,--enable-unsafe-swiftshader,--use-mock-keychain'
            ;;
        *) printf '%s\n' '--disable-gpu' ;;
    esac
}

cef_dbus_session_bus_address() {
    local platform=$1 current=${2:-}
    case "${platform}" in
        linux64|linuxarm64)
            # Chromium uses this value itself when no session bus exists. Set it in the shell before the JVM becomes
            # multithreaded so Chromium does not have to mutate glibc's process environment during CEF startup.
            printf '%s\n' "${current:-disabled:}"
            ;;
        *) printf '%s\n' "${current}" ;;
    esac
}

java_runtime_args() {
    printf '%s\n' '--enable-native-access=ALL-UNNAMED'
}

maven_runtime_args() {
    local jdk_version=$1 args
    args=$(java_runtime_args "${jdk_version}")
    if [ "${jdk_version}" -ge 25 ]; then
        args+=' --sun-misc-unsafe-memory-access=allow'
    fi
    printf '%s\n' "${args}"
}

surefire_extra_arg() {
    local platform=$1 jdk_version=$2
    local args
    args=$(java_runtime_args "${jdk_version}")
    case "${platform}:${jdk_version}" in
        windows64:25|windowsarm64:25) args+=' -Djdk.net.URLClassPath.disableClassPathURLCheck=true' ;;
    esac
    printf '%s\n' "${args}"
}

spotbugs_extra_arg() {
    local platform=$1 jdk_version=$2
    if [ "${jdk_version}" -ne 17 ] || [ "${platform}" = linuxarm64 ]; then
        printf '%s\n' '-Dspotbugs.skip=true'
    fi
}

test_reactor_exclusions() {
    printf '%s\n' '!cef4j-platform,!cef4j-runtime-server'
}

verify_test_reactor_exclusions() {
    local repo_root=$1 module
    for module in cef4j-platform cef4j-runtime-server; do
        if [ -n "$(find "${repo_root}/${module}/src/test" -type f -print -quit 2>/dev/null)" ]; then
            echo "${module} has tests and cannot be excluded from the test reactor" >&2
            return 1
        fi
    done
}

cef_java_preload_required() {
    local platform=$1 arch=$2 cef_api=$3
    [ "${platform}" = linuxarm64 ] && [ "${arch}" = aarch64 ] && [ "${cef_api}" -lt 139 ]
}

write_cef_java_wrapper() {
    local output=$1 dynamic_loader=$2 libcef=$3 java=$4
    # glibc's per-executable --preload option reserves legacy CEF's initial-exec TLS before Java starts without
    # exporting LD_PRELOAD to Chromium subprocesses. Bash %q keeps all resolved paths as single arguments.
    printf '#!/usr/bin/env bash\nexec %q --preload %q %q "$@"\n' \
        "${dynamic_loader}" "${libcef}" "${java}" > "${output}"
    chmod +x "${output}"
}

xvfb_server_args() {
    # DisplayLock serialises UI classes, leaving short client-free gaps between them.
    # Do not let Xvfb reset during those gaps while the parallel reactor is still running.
    printf '%s\n' '-screen 0 1920x1080x24 -noreset'
}
