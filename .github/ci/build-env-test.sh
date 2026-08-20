#!/usr/bin/env bash
set -euo pipefail

source "$(dirname -- "${BASH_SOURCE[0]}")/build-env.sh"

fail() {
    echo "$1" >&2
    exit 1
}

actual=$(printf '    java.specification.version = 17\r\n' | java_specification_version)
[ "${actual}" = 17 ] || fail "expected CRLF Java version output to parse as 17, got: ${actual}"

uname() {
    printf '%s\n' MINGW64_NT-10.0
}
cygpath() {
    [ "$1" = --unix ] || fail "expected cygpath --unix"
    [ "$2" = 'C:\hostedtoolcache\Java\17\x64' ] || fail "unexpected Windows Java home: $2"
    printf '%s\n' /c/hostedtoolcache/Java/17/x64
}

actual=$(normalize_java_home 'C:\hostedtoolcache\Java\17\x64')
[ "${actual}" = /c/hostedtoolcache/Java/17/x64 ] || fail "unexpected normalized Java home: ${actual}"
