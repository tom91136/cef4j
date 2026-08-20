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
