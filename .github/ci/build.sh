#!/usr/bin/env bash
set -euo pipefail

repo_root=$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")/../.." && pwd)
cd "${repo_root}"

: "${CEF_VERSION:?CEF_VERSION is required}"
: "${CEF_PLATFORM:?CEF_PLATFORM is required}"
: "${CEF_EXTRA_ARGS:?CEF_EXTRA_ARGS is required}"
: "${JDK_VERSION:?JDK_VERSION is required}"
[ -n "${JAVAFX_VERSION+x}" ] || { echo "JAVAFX_VERSION must be set, including to an empty value" >&2; exit 1; }
[ -n "${SUREFIRE_PLATFORM_ARG+x}" ] \
    || { echo "SUREFIRE_PLATFORM_ARG must be set, including to an empty value" >&2; exit 1; }
: "${SPOTBUGS_SKIP:?SPOTBUGS_SKIP is required}"
case "${SPOTBUGS_SKIP}" in true|false) ;; *) echo "SPOTBUGS_SKIP must be true or false" >&2; exit 1 ;; esac
expected_jdk=${JDK_VERSION}
if [ -n "${JAVA_HOME:-}" ]; then
    case "$(uname -s)" in
        MINGW*|MSYS*|CYGWIN*) JAVA_HOME=$(cygpath --unix "${JAVA_HOME}") ;;
    esac
fi
if [ -z "${JAVA_HOME:-}" ] || [ ! -d "${JAVA_HOME}/bin" ]; then
    JAVA_HOME="$(dirname "$(dirname "$(readlink -f "$(command -v java)")")")"
fi
[ -d "${JAVA_HOME}/bin" ] || { echo "invalid JAVA_HOME: ${JAVA_HOME}" >&2; exit 1; }
export JAVA_HOME
export PATH="${JAVA_HOME}/bin:${PATH}"
actual_jdk=$(java -XshowSettings:properties -version 2>&1 \
    | sed -n 's/^[[:space:]]*java.specification.version = //p' | tr -d '\r')
[ "${actual_jdk}" = "${expected_jdk}" ] || {
    echo "JDK_VERSION=${expected_jdk}, but JAVA_HOME selects Java ${actual_jdk:-unknown}" >&2
    exit 1
}

case "${CEF_PLATFORM}" in
    linux64|linuxarm64) is_linux=1 ;;
    windows64|windowsarm64) ;;
    macosx64|macosarm64) ;;
    *) echo "unknown CEF_PLATFORM: ${CEF_PLATFORM}" >&2; exit 2 ;;
esac

if [ "${is_linux:-}" = 1 ]; then
    DBUS_SESSION_BUS_ADDRESS=${DBUS_SESSION_BUS_ADDRESS:-disabled:}
    export DBUS_SESSION_BUS_ADDRESS
    : "${CMAKE_SYSROOT:?CMAKE_SYSROOT must point at a prepared Linux sysroot}"
    [ -d "${CMAKE_SYSROOT}/usr/include" ] || { echo "invalid CMAKE_SYSROOT: ${CMAKE_SYSROOT}" >&2; exit 1; }
fi

if [ -z "${JAVAFX_TEST_VERSION+x}" ]; then
    JAVAFX_TEST_VERSION=${JAVAFX_VERSION}
fi
if [ -z "${JAVAFX_TESTS+x}" ]; then
    JAVAFX_TESTS=false
    [ -n "${JAVAFX_VERSION}" ] && JAVAFX_TESTS=true
fi
SUREFIRE_EXTRA_ARG='--enable-native-access=ALL-UNNAMED'
[ -z "${SUREFIRE_PLATFORM_ARG}" ] || SUREFIRE_EXTRA_ARG+=" ${SUREFIRE_PLATFORM_ARG}"
maven_runtime_args='--enable-native-access=ALL-UNNAMED'
if [ "${expected_jdk}" -ge 25 ]; then
    maven_runtime_args+=' --sun-misc-unsafe-memory-access=allow'
fi
MAVEN_OPTS="${MAVEN_OPTS:+${MAVEN_OPTS} }${maven_runtime_args}"
export MAVEN_OPTS

verify_thin_platform_jar() {
    local artifacts artifact entries bridge_count launcher_count unexpected=false
    shopt -s nullglob
    artifacts=(cef4j-platform/target/cef4j-platform-*.jar)
    shopt -u nullglob
    [ "${#artifacts[@]}" -eq 1 ] || {
        echo "expected exactly one classified cef4j-platform JAR, found ${#artifacts[@]}" >&2
        return 1
    }

    artifact=${artifacts[0]}
    entries=$(mktemp "${RUNNER_TEMP:-/tmp}/cef4j-platform-entries.XXXXXX")
    if ! jar tf "${artifact}" | tr -d '\r' > "${entries}"; then
        rm -f "${entries}"
        return 1
    fi
    bridge_count=$(grep -Ec '^native/[^/]+/(libcef4j\.(so|dylib)|cef4j\.dll)$' "${entries}" || true)
    launcher_count=$(grep -Ec '^native/[^/]+/cef4j_launcher(\.exe)?$' "${entries}" || true)
    if [ "${bridge_count}" -ne 1 ] || [ "${launcher_count}" -ne 1 ]; then
        echo "${artifact} must contain exactly one JNI bridge and one launcher" >&2
        cat "${entries}" >&2
        unexpected=true
    fi
    while IFS= read -r entry; do
        case "${entry}" in
            META-INF/|META-INF/MANIFEST.MF|native/|native/*/|native/*/libcef4j.so|native/*/libcef4j.dylib|native/*/cef4j.dll|native/*/cef4j_launcher|native/*/cef4j_launcher.exe) ;;
            *) echo "unexpected platform JAR entry: ${entry}" >&2; unexpected=true ;;
        esac
    done < "${entries}"
    rm -f "${entries}"
    [ "${unexpected}" = false ] || return 1
    echo "Verified thin platform bridge: ${artifact}"
}

verify_linux_abi() {
    local library bad max
    while IFS= read -r library; do
        readelf -d "${library}" | grep -E 'NEEDED|SONAME'
        bad=$(readelf -d "${library}" | grep -E 'NEEDED' \
            | grep -vE '(libcef\.so|libc\.so\.6|libm\.so\.6|libdl\.so\.2|libpthread\.so\.0|librt\.so\.1|ld-linux[^]]*\.so)' || true)
        [ -z "${bad}" ] || { echo "unexpected dynamic dependency in ${library}: ${bad}" >&2; return 1; }
        max=$(nm -D --with-symbol-versions "${library}" 2>/dev/null \
            | grep -oE '@GLIBC_[0-9.]+' | sort -uV | tail -1 || true)
        if [ -n "${max}" ] && [ "$(printf '%s\n2.28\n' "${max#@GLIBC_}" | sort -V | tail -1)" != 2.28 ]; then
            echo "${library} requires ${max}, above the GLIBC 2.28 baseline" >&2
            return 1
        fi
    done < <(find cef4j-platform/target cef4j-runtime-server/target \
        -type f \( -name libcef4j.so -o -name cef4j_launcher -o -name cef4j-runtime-server \))
}

java -version
[ "${is_linux:-}" = 1 ] && clang++ --version

cef_api=${CEF_VERSION%%.*}
properties=(
    "-Dcef.version=${CEF_VERSION}"
    "-Dcef.api.version=${cef_api}"
)
if [ -n "${JAVAFX_VERSION}" ]; then
    properties+=("-Djavafx.version=${JAVAFX_VERSION}")
fi
if [ -n "${JAVAFX_TEST_VERSION}" ]; then
    properties+=("-Djavafx.test.version=${JAVAFX_TEST_VERSION}")
fi
properties+=(
    "-Dcef4j.test.extraArgs=${CEF_EXTRA_ARGS}"
    "-Dcef4j.runtime.server.extraArgs=${CEF_EXTRA_ARGS}"
)
[ -z "${SUREFIRE_EXTRA_ARG}" ] || properties+=("-Dsurefire.argLine=${SUREFIRE_EXTRA_ARG}")
[ "${SPOTBUGS_SKIP}" != true ] || properties+=("-Dspotbugs.skip=true")
[ "${JAVAFX_TESTS}" = true ] || properties+=("-DskipJavafx=true")
[ "${JAVA11_SMOKE:-false}" != true ] || properties+=("-Djava11.runtime.smoke=true")

if [ "${CEF_PLATFORM}" = linuxarm64 ] && [ "${cef_api}" -lt 139 ]; then
    libcef="${repo_root}/.cef-dist/cef_binary_${CEF_VERSION}_${CEF_PLATFORM}_minimal/Release/libcef.so"
    dynamic_loader=$(LC_ALL=C readelf -Wl "${JAVA_HOME}/bin/java" \
        | sed -n 's/.*interpreter: \([^]]*\)].*/\1/p')
    [ -x "${dynamic_loader}" ] || { echo "unable to locate Java dynamic loader" >&2; exit 1; }
    cef_java_wrapper="${RUNNER_TEMP:-${TMPDIR:-/tmp}}/cef4j-java-preload-${CEF_PLATFORM}-${cef_api}/bin/java"
    mkdir -p "$(dirname -- "${cef_java_wrapper}")"
    printf '#!/usr/bin/env bash\nexec %q --preload %q %q "$@"\n' \
        "${dynamic_loader}" "${libcef}" "${JAVA_HOME}/bin/java" > "${cef_java_wrapper}"
    chmod +x "${cef_java_wrapper}"
    properties+=("-Djvm=${cef_java_wrapper}")
    echo "Legacy ARM64 CEF will be loaded before the Surefire JVM: ${libcef}"
fi

if [ "${is_linux:-}" = 1 ]; then
    xvfb-run -a --server-args='-screen 0 1920x1080x24 -noreset' \
        ./mvnw -B -T1 clean install "${properties[@]}"
else
    ./mvnw -B -T1 clean install "${properties[@]}"
fi
verify_thin_platform_jar
[ "${is_linux:-}" = 1 ] && verify_linux_abi

if [ "${RELEASE_BUILD:-false}" = true ]; then
    ./mvnw -B verify -DskipTests -Dgpg.skip=true -Drelease.bundle=true -Prelease "${properties[@]}"
fi
