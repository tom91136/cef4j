#!/usr/bin/env bash
set -euo pipefail

repo_root=$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")/../.." && pwd)
cd "${repo_root}"
source "${repo_root}/.github/ci/build-env.sh"

# XXX Required env: CEF_VERSION CEF_API CEF_PLATFORM ARCH JDK_VERSION.
for name in CEF_VERSION CEF_API CEF_PLATFORM ARCH JDK_VERSION; do
    [ -n "${!name:-}" ] || { echo "${name} is required" >&2; exit 1; }
done
# setup-java emits a native Windows path, while this script runs under Git Bash.
if [ -n "${JAVA_HOME:-}" ]; then
    JAVA_HOME=$(normalize_java_home "${JAVA_HOME}")
fi
# Fall back to the Java on PATH for local invocations that do not set JAVA_HOME.
if [ -z "${JAVA_HOME:-}" ] || [ ! -d "${JAVA_HOME}/bin" ]; then
    JAVA_HOME="$(dirname "$(dirname "$(readlink -f "$(command -v java)")")")"
fi
[ -d "${JAVA_HOME}/bin" ] || { echo "invalid JAVA_HOME: ${JAVA_HOME}" >&2; exit 1; }
export JAVA_HOME
export PATH="${JAVA_HOME}/bin:${PATH}"
actual_jdk=$(java -XshowSettings:properties -version 2>&1 \
    | java_specification_version)
[ "${actual_jdk}" = "${JDK_VERSION}" ] || {
    echo "JDK_VERSION=${JDK_VERSION}, but JAVA_HOME selects Java ${actual_jdk:-unknown}" >&2
    exit 1
}

case "${CEF_PLATFORM}" in
    linux64|linuxarm64) is_linux=1 ;;
    windows64|windowsarm64) ;;
    macosx64|macosarm64) is_macos=1 ;;
    *) echo "unknown CEF_PLATFORM: ${CEF_PLATFORM}" >&2; exit 2 ;;
esac

static_tls_bytes=$(static_tls_reserve "${CEF_PLATFORM}" "${ARCH}" "${CEF_API}")
if [ -n "${static_tls_bytes}" ]; then
    # The reserve is fixed when glibc starts a process, so export it before
    # launching Maven and its test JVMs. Preloading libcef breaks old CEF's
    # subprocess ICU setup.
    export GLIBC_TUNABLES="glibc.rtld.optional_static_tls=${static_tls_bytes}"

    # Prove that this runner's loader supports the tunable and accepted the
    # requested value. A misspelled/unsupported tunable is otherwise ignored.
    dynamic_loader=$(LC_ALL=C readelf -l "${JAVA_HOME}/bin/java" \
        | sed -n 's/.*interpreter: \([^]]*\)].*/\1/p')
    [ -x "${dynamic_loader}" ] || { echo "unable to locate Java dynamic loader" >&2; exit 1; }
    effective_static_tls=$("${dynamic_loader}" --list-tunables \
        | glibc_tunable_value glibc.rtld.optional_static_tls)
    [ -n "${effective_static_tls}" ] && [ "$((effective_static_tls))" -ge "${static_tls_bytes}" ] || {
        echo "glibc did not accept optional_static_tls=${static_tls_bytes}: ${effective_static_tls:-missing}" >&2
        exit 1
    }
    echo "Verified glibc optional static TLS reserve: ${effective_static_tls}"
fi

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
EXTRA_ARGS=$(cef_extra_args "${CEF_PLATFORM}")
SUREFIRE_EXTRA_ARG=$(surefire_extra_arg "${CEF_PLATFORM}" "${JDK_VERSION}")
SPOTBUGS_EXTRA_ARG=$(spotbugs_extra_arg "${CEF_PLATFORM}")

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

mkdir -p target
non_javafx_file=target/ci-non-javafx-modules.txt
./mvnw -B -N help:evaluate -Dexpression=cef4j.nonJavafxModules -DforceStdout -DskipTests \
    -Doutput="${non_javafx_file}"
non_javafx=$(tr -d '\r\n' < "${non_javafx_file}")
[ -n "${non_javafx}" ] || { echo "cef4j.nonJavafxModules evaluated to an empty value" >&2; exit 1; }
properties=(
    "-Dcef.version=${CEF_VERSION}"
    "-Dcef.api.version=${CEF_API}"
)
# XXX An empty -Djavafx.version= overrides the parent pom property and breaks project reads;
# omit it when JavaFX is unavailable for the platform/JDK instead.
if [ -n "${JAVAFX_VERSION}" ]; then
    properties+=("-Djavafx.version=${JAVAFX_VERSION}" "-Djavafx.test.version=${JAVAFX_VERSION}")
fi
properties+=(
    "-Dcef4j.test.extraArgs=${EXTRA_ARGS}"
    "-Dcef4j.runtime.server.extraArgs=${EXTRA_ARGS}"
)
[ -z "${SUREFIRE_EXTRA_ARG}" ] || properties+=("-Dsurefire.argLine=${SUREFIRE_EXTRA_ARG}")
[ -z "${SPOTBUGS_EXTRA_ARG}" ] || properties+=("${SPOTBUGS_EXTRA_ARG}")
[ -z "${JAVAFX_PLATFORM:-}" ] || properties+=("-Djavafx.platform=${JAVAFX_PLATFORM}")

run_reactor() {
    if [ "${JAVAFX_TESTS}" != true ]; then
        "$@" -pl "${non_javafx}"
    else
        "$@"
    fi
}

run_with_display() {
    if [ "${is_linux:-}" = 1 ]; then
        xvfb-run -a --server-args="$(xvfb_server_args)" "$@"
    else
        "$@"
    fi
}

run_reactor retry ./mvnw -B dependency:go-offline \
    -DexcludeArtifactIds=cef4j-platform,cef4j-runtime-server "${properties[@]}"
retry ./mvnw -B dependency:get "-Dartifact=org.apache.maven.surefire:surefire-junit-platform:3.5.6"
retry ./mvnw -B -pl cef4j-platform spotless:check "${properties[@]}"

run_reactor ./mvnw -B clean install -DskipTests "${properties[@]}"
verify_thin_platform_jar
[ "${is_linux:-}" = 1 ] && verify_linux_abi

# Keep dependency resolution, compilation, and the outer platform matrix parallel,
# but do not initialize several native CEF runtimes at once inside one runner.
# DisplayLock only coordinates annotated display tests; it cannot isolate native
# runtime-server and in-process test modules from each other.
run_reactor run_with_display ./mvnw -B -T1 test "${properties[@]}"

if [ "${JAVA11_SMOKE:-false}" = true ]; then
    ./mvnw -B -pl cef4j-remote-core,cef4j-remote-frame,cef4j-cdp,cef4j-webdriver,cef4j-codecs-gson,cef4j-codecs-jackson,cef4j-remote-webdriver test \
        -Djava11.runtime.smoke=true
fi

if [ "${RELEASE_BUILD:-false}" = true ]; then
    ./mvnw -B verify -DskipTests -Dgpg.skip=true -Drelease.bundle=true -Prelease "${properties[@]}"
fi
