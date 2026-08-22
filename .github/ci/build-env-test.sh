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

actual=$(cef_dbus_session_bus_address linux64 '')
[ "${actual}" = 'disabled:' ] \
    || fail "headless Linux CEF must enter Java with D-Bus autolaunch disabled: ${actual}"
actual=$(cef_dbus_session_bus_address linuxarm64 'unix:path=/run/user/1000/bus')
[ "${actual}" = 'unix:path=/run/user/1000/bus' ] \
    || fail "Linux CEF must preserve a runner-provided D-Bus address: ${actual}"
[ -z "$(cef_dbus_session_bus_address macosarm64 '')" ] \
    || fail "non-Linux CEF jobs must not synthesize a D-Bus address"

actual=$(surefire_extra_arg windows64 25)
[ "${actual}" = '-Djdk.net.URLClassPath.disableClassPathURLCheck=true' ] \
    || fail "JDK 25 Windows tests must allow Surefire classpaths spanning drive roots: ${actual}"
[ -z "$(surefire_extra_arg windowsarm64 21)" ] || fail "the Surefire compatibility switch is JDK 25-specific"
[ -z "$(surefire_extra_arg linux64 25)" ] || fail "the Surefire compatibility switch is Windows-specific"

actual=$(spotbugs_extra_arg linuxarm64)
[ "${actual}" = '-Dspotbugs.skip=true' ] \
    || fail "Linux ARM64 must reuse platform-independent SpotBugs coverage from native x64 jobs: ${actual}"
[ -z "$(spotbugs_extra_arg linux64)" ] || fail "native x64 jobs must retain SpotBugs coverage"

cef_java_preload_required linuxarm64 aarch64 138 \
    || fail "old-CEF Linux ARM64 test JVMs must preload CEF before Java starts"
if cef_java_preload_required linuxarm64 aarch64 144; then
    fail "newer Linux ARM64 CEF builds must use the normal Java launcher"
fi
if cef_java_preload_required linux64 x86_64 138; then
    fail "Linux x64 builds must use the normal Java launcher"
fi

actual=$(xvfb_server_args)
case " ${actual} " in
    *' -noreset '*) ;;
    *) fail "Xvfb must remain alive between display-locked UI test classes: ${actual}" ;;
esac

wrapper_dir=$(mktemp -d)
trap 'rm -rf -- "${wrapper_dir}"' EXIT
wrapper="${wrapper_dir}/bin/java"
mkdir -p "$(dirname -- "${wrapper}")"
write_cef_java_wrapper "${wrapper}" '/lib/loader with spaces' '/cef/libcef with spaces.so' '/jdk/bin/java with spaces'
[ -x "${wrapper}" ] || fail "the generated CEF Java wrapper must be executable"
grep -Fq 'exec /lib/loader\ with\ spaces --preload /cef/libcef\ with\ spaces.so /jdk/bin/java\ with\ spaces "$@"' "${wrapper}" \
    || fail "the CEF Java wrapper must preserve launcher paths and arguments"

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
grep -Eq 'HEARTBEAT_TIMEOUT_MS = 360_000;' \
    "${repo_root}/cef4j-remote-core/src/main/java/net/kurobako/cef4j/ipc/transport/ZmqTransport.java" \
    || fail "the JVM transport must tolerate a six-minute scheduler stall"
grep -Eq 'HANDSHAKE_TIMEOUT_MS = 30_000;' \
    "${repo_root}/cef4j-remote-core/src/main/java/net/kurobako/cef4j/ipc/transport/ZmqTransport.java" \
    || fail "the JVM transport must recover an initial handshake before session readiness expires"
grep -Eq 'RUNTIME_SESSION_READY_TIMEOUT = Duration.ofMinutes\(5\);' \
    "${repo_root}/cef4j-remote-core/src/main/java/net/kurobako/cef4j/ipc/session/CefSessionImpl.java" \
    || fail "runtime readiness must have its own 180-second startup budget"
grep -Eq 'kHeartbeatTimeoutMs = 360000;' \
    "${repo_root}/cef4j-runtime-server/src/main/cpp/ZmqIpcServer.cpp" \
    || fail "the native transport must match the JVM heartbeat timeout"
grep -q '<junit.jupiter.execution.timeout.default>60 s</junit.jupiter.execution.timeout.default>' \
    "${repo_root}/pom.xml" \
    || fail "ordinary tests must tolerate a minute of CI runner starvation"
grep -q '<forkedProcessTimeoutInSeconds>1200</forkedProcessTimeoutInSeconds>' \
    "${repo_root}/pom.xml" \
    || fail "Surefire forks must outlive the longest class-level CI timeout"
grep -q 'run_reactor run_with_display ./mvnw -B -T1 test' \
    "${repo_root}/.github/ci/build.sh" \
    || fail "native CEF test modules must run serially inside each matrix job"
grep -q 'cef_java_preload_required "${CEF_PLATFORM}" "${ARCH}" "${CEF_API}"' \
    "${repo_root}/.github/ci/build.sh" \
    || fail "the build must identify legacy ARM64 test JVMs that need early CEF loading"
grep -q 'test_properties+=("-Djvm=${cef_java_wrapper}")' \
    "${repo_root}/.github/ci/build.sh" \
    || fail "Surefire must launch affected test JVMs through the scoped CEF wrapper"
grep -q 'cef_java_wrapper="${repo_root}/target/ci-cef-preloaded/bin/java"' \
    "${repo_root}/.github/ci/build.sh" \
    || fail "Surefire requires the configured JVM executable to be named java"
if grep -Eq '(^|[[:space:]])(export[[:space:]]+)?(LD_PRELOAD|GLIBC_TUNABLES)=|processReaperUseDefaultStackSize' \
    "${repo_root}/.github/ci/build.sh" "${repo_root}/.github/ci/build-env.sh"; then
    fail "legacy ARM64 CEF must not alter inherited preload, glibc TLS, or JVM stack settings"
fi
if grep -q 'if:.*!cancelled()' "${repo_root}/.github/workflows/main.yaml"; then
    fail "later JDK builds must not run in a workspace contaminated by an earlier failed JDK"
fi
grep -q 'DBUS_SESSION_BUS_ADDRESS=$(cef_dbus_session_bus_address "${CEF_PLATFORM}" "${DBUS_SESSION_BUS_ADDRESS:-}")' \
    "${repo_root}/.github/ci/build.sh" \
    || fail "the build must seed Linux D-Bus state before launching Maven"
grep -q 'on_context_initialized' \
    "${repo_root}/cef4j-runtime-server/src/main/cpp/main.cpp" \
    || fail "the runtime server must wait for CEF browser-context initialization"
grep -q 'CEF context initialized; publishing endpoint' \
    "${repo_root}/cef4j-runtime-server/src/main/cpp/main.cpp" \
    || fail "the runtime server must publish its endpoint from the CEF context-ready callback"
