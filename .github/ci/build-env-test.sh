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

actual=$(cef_extra_args macosx64)
[ "${actual}" = '--use-gl=angle,--use-angle=swiftshader,--enable-unsafe-swiftshader,--use-mock-keychain' ] \
    || fail "unexpected macOS CEF arguments: ${actual}"

actual=$(cef_extra_args linux64)
[ "${actual}" = --disable-gpu ] || fail "unexpected Linux CEF arguments: ${actual}"

actual=$(xvfb_server_args)
case " ${actual} " in
    *' -noreset '*) ;;
    *) fail "Xvfb must remain alive between display-locked UI test classes: ${actual}" ;;
esac

actual=$(static_tls_reserve linuxarm64 aarch64 116)
[ "${actual}" = 65536 ] || fail "unexpected ARM64 static TLS reserve: ${actual}"
[ -z "$(static_tls_reserve linuxarm64 aarch64 144)" ] || fail "CEF 144 should not need an enlarged static TLS reserve"
[ -z "$(static_tls_reserve linux64 x86_64 116)" ] || fail "x64 should not need an enlarged static TLS reserve"

actual=$(printf '%s\n' \
    'glibc.malloc.trim_threshold: 0x0 (min: 0x0, max: 0xffff)' \
    'glibc.rtld.optional_static_tls: 0x10000 (min: 0x0, max: 0xffff)' \
    | glibc_tunable_value glibc.rtld.optional_static_tls)
[ "${actual}" = 0x10000 ] || fail "unexpected parsed glibc tunable: ${actual}"

repo_root=$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")/../.." && pwd)
grep -q '<spotbugs.timeout>1800000</spotbugs.timeout>' "${repo_root}/pom.xml" \
    || fail "SpotBugs must allow slower ARM64 analysis to run for 30 minutes"
grep -q 'archive}.sha1" skipexisting="true"' "${repo_root}/cef4j-cef-dist/pom.xml" \
    || fail "Maven must reuse the CEF SHA-1 sidecar prepared by CI"
grep -q 'cef_binary_.*minimal.tar.bz2.sha1' "${repo_root}/.github/actions/cef-archives/action.yml" \
    || fail "the CEF archive action must prepare SHA-1 sidecars"
if grep -q -- '<argument>--without-swiftshader</argument>' "${repo_root}/cef4j-platform/pom.xml"; then
    fail "reactor test runtimes must retain SwiftShader"
fi
grep -q -- '-Doutput="${non_javafx_file}"' "${repo_root}/.github/ci/build.sh" \
    || fail "non-JavaFX module discovery must preserve visible Maven diagnostics"
grep -Eq 'HEARTBEAT_TIMEOUT_MS = 30_000;' \
    "${repo_root}/cef4j-remote-core/src/main/java/net/kurobako/cef4j/ipc/transport/ZmqTransport.java" \
    || fail "the JVM transport must tolerate a 30-second scheduler stall"
grep -Eq 'kHeartbeatTimeoutMs = 30000;' \
    "${repo_root}/cef4j-runtime-server/src/main/cpp/ZmqIpcServer.cpp" \
    || fail "the native transport must match the JVM heartbeat timeout"
