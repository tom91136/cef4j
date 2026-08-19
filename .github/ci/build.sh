#!/usr/bin/env bash
set -euo pipefail

repo_root=$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")/../.." && pwd)
cd "${repo_root}"

# XXX Required env: CEF_VERSION CEF_API CEF_PLATFORM ARCH JDK_VERSION.
for name in CEF_VERSION CEF_API CEF_PLATFORM ARCH JDK_VERSION; do
    [ -n "${!name:-}" ] || { echo "${name} is required" >&2; exit 1; }
done
# setup-java does not always emit the java-home output; fall back to the java on PATH.
if [ -z "${JAVA_HOME:-}" ] || [ ! -d "${JAVA_HOME}/bin" ]; then
    JAVA_HOME="$(dirname "$(dirname "$(readlink -f "$(command -v java)")")")"
fi
[ -d "${JAVA_HOME}/bin" ] || { echo "invalid JAVA_HOME: ${JAVA_HOME}" >&2; exit 1; }
export JAVA_HOME
export PATH="${JAVA_HOME}/bin:${PATH}"

case "${CEF_PLATFORM}" in
    linux64|linuxarm64) is_linux=1 ;;
    windows64|windowsarm64) ;;
    macosx64|macosarm64) is_macos=1 ;;
    *) echo "unknown CEF_PLATFORM: ${CEF_PLATFORM}" >&2; exit 2 ;;
esac

if [ "${is_linux:-}" = 1 ]; then
    : "${CMAKE_SYSROOT:?CMAKE_SYSROOT must point at a prepared Linux sysroot}"
    [ -d "${CMAKE_SYSROOT}/usr/include" ] || { echo "invalid CMAKE_SYSROOT: ${CMAKE_SYSROOT}" >&2; exit 1; }
fi

# XXX JavaFX version/gating is keyed on CEF_PLATFORM + JDK_VERSION.
javafx_version() {
    case "${CEF_PLATFORM}" in
        linux64)
            case "${JDK_VERSION}" in 17) echo 13.0.2 ;; 21) echo 21.0.12 ;; 25) echo 25.0.4 ;; esac ;;
        linuxarm64)
            case "${JDK_VERSION}" in 17) echo "" ;; 21) echo 21.0.1 ;; 25) echo 25.0.4 ;; esac ;;
        windows64)
            case "${JDK_VERSION}" in 17) echo 13.0.2 ;; 21) echo 21.0.12 ;; 25) echo 25.0.4 ;; esac ;;
        windowsarm64) echo "" ;;
        macosx64)
            case "${JDK_VERSION}" in 17) echo 13.0.2 ;; 21) echo 21.0.12 ;; 25) echo 25.0.4 ;; esac ;;
        macosarm64)
            case "${JDK_VERSION}" in 17) echo 17.0.15 ;; 21) echo 21.0.12 ;; 25) echo 25.0.4 ;; esac ;;
    esac
}
JAVAFX_VERSION=$(javafx_version)
JAVAFX_TESTS=false
[ -n "${JAVAFX_VERSION}" ] && JAVAFX_TESTS=true
JAVAFX_PLATFORM=""
[ "${CEF_PLATFORM}" = windowsarm64 ] && JAVAFX_PLATFORM=win
# XXX aarch64 runners OOM when the reactor links libcef4j.so while other modules compile
THREADS=""
[ "${ARCH}" = aarch64 ] && THREADS="-T1"
EXTRA_ARGS="--disable-gpu"
[ "${is_macos:-}" = 1 ] && EXTRA_ARGS="--disable-gpu,--use-mock-keychain"

retry() {
    local attempt
    for attempt in 1 2 3; do
        "$@" && return
        [ "${attempt}" -eq 3 ] && return 1
        echo "$1 attempt ${attempt} failed, retrying..."
        sleep 10
    done
}

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

non_javafx=$(./mvnw -q -N help:evaluate -Dexpression=cef4j.nonJavafxModules -DforceStdout -DskipTests)
properties=(
    "-Dcef.version=${CEF_VERSION}"
    "-Dcef.api.version=${CEF_API}"
)
# XXX An empty -Djavafx.version= overrides the parent pom property and breaks project reads;
# omit it when JavaFX is unavailable for the platform/JDK instead.
if [ -n "${JAVAFX_VERSION}" ]; then
    properties+=("-Djavafx.version=${JAVAFX_VERSION}" "-Djavafx.test.version=${JAVAFX_VERSION}")
fi
if [ "${is_linux:-}" = 1 ] && [ "${ARCH}" = aarch64 ] && [ "${CEF_API}" -lt 150 ]; then
    properties+=("-Dcef4j.test.ldPreload=${repo_root}/.cef-dist/cef_binary_${CEF_VERSION}_linuxarm64_minimal/Release/libcef.so")
fi
properties+=(
    "-Dcef4j.test.extraArgs=${EXTRA_ARGS}"
    "-Dcef4j.runtime.server.extraArgs=${EXTRA_ARGS}"
)
[ -z "${JAVAFX_PLATFORM:-}" ] || properties+=("-Djavafx.platform=${JAVAFX_PLATFORM}")

run_reactor() {
    if [ "${is_linux:-}" = 1 ] && [ "${JAVAFX_TESTS}" != true ]; then
        "$@" -pl "${non_javafx}" ${THREADS:-}
    else
        "$@" ${THREADS:-}
    fi
}

run_with_display() {
    if [ "${is_linux:-}" = 1 ]; then
        xvfb-run -a "$@"
    else
        "$@"
    fi
}

run_reactor retry ./mvnw -B dependency:go-offline \
    -DexcludeArtifactIds=cef4j-platform,cef4j-runtime-server "${properties[@]}"
retry ./mvnw -B dependency:get "-Dartifact=org.apache.maven.surefire:surefire-junit-platform:3.5.6"
retry ./mvnw -B -pl cef4j-platform spotless:check "${properties[@]}"

# XXX The checker is an annotation processor in the same reactor, but Maven's annotationProcessorPaths
# cannot resolve a reactor-built jar; install it first so downstream modules pick it up from the local repo.
retry ./mvnw -B -pl cef4j-code-policy install -DskipTests "${properties[@]}"

run_reactor ./mvnw -B clean install -DskipTests "${properties[@]}"
verify_thin_platform_jar
[ "${is_linux:-}" = 1 ] && verify_linux_abi

run_reactor run_with_display ./mvnw -B test "${properties[@]}"

if [ "${JAVA11_SMOKE:-false}" = true ]; then
    ./mvnw -B -pl cef4j-remote-core,cef4j-remote-frame,cef4j-cdp,cef4j-webdriver,cef4j-codecs-gson,cef4j-codecs-jackson,cef4j-remote-webdriver test \
        -Djava11.runtime.smoke=true
fi

if [ "${RELEASE_BUILD:-false}" = true ]; then
    ./mvnw -B verify -DskipTests -Dgpg.skip=true -Drelease.bundle=true -Prelease "${properties[@]}"
fi
