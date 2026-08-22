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

surefire_extra_arg() {
    local platform=$1 jdk_version=$2
    case "${platform}:${jdk_version}" in
        windows64:25|windowsarm64:25)
            # JDK 25 rejects Surefire's manifest-only classpath when the checkout and dependency cache use different
            # drive roots. This switch affects test JVM bootstrap only; Maven and published runtimes are unchanged.
            printf '%s\n' '-Djdk.net.URLClassPath.disableClassPathURLCheck=true'
            ;;
    esac
}

spotbugs_extra_arg() {
    case "$1" in
        linuxarm64)
            # SpotBugs is platform-independent and already runs throughout the native x64 matrix. Its analysis JVM
            # can make no progress on the emulated ARM64 runners and otherwise consumes the full 30-minute timeout.
            printf '%s\n' '-Dspotbugs.skip=true'
            ;;
    esac
}

process_reaper_jvm_arg() {
    local platform=$1 arch=$2 cef_api=$3
    if [ "${platform}" = linuxarm64 ] && [ "${arch}" = aarch64 ] && [ "${cef_api}" -lt 139 ]; then
        # The old-CEF static-TLS reserve exhausts the small stack Java assigns to process-reaper threads. This must
        # reach Maven, Exec Plugin code generation, and Surefire forks on every JDK used by the affected jobs.
        printf '%s\n' '-Djdk.lang.processReaperUseDefaultStackSize=true'
    fi
}

xvfb_server_args() {
    # DisplayLock serialises UI classes, leaving short client-free gaps between them.
    # Do not let Xvfb reset during those gaps while the parallel reactor is still running.
    printf '%s\n' '-screen 0 1920x1080x24 -noreset'
}

static_tls_reserve() {
    local platform=$1 arch=$2 cef_api=$3
    if [ "${platform}" = linuxarm64 ] && [ "${arch}" = aarch64 ] && [ "${cef_api}" -lt 139 ]; then
        printf '%s\n' 65536
    fi
}

glibc_tunable_value() {
    local name=$1
    awk -v name="${name}:" '$1 == name { print $2; exit }'
}
