#!/usr/bin/env bash
set -euo pipefail

mode=${1:-}
case "${mode}" in
    linux|desktop) ;;
    *) echo "usage: $0 linux|desktop" >&2; exit 2 ;;
esac

repo_root=$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")/../.." && pwd)
cd "${repo_root}"

for name in CEF_VERSION CEF_API JAVAFX_VERSION JAVAFX_TEST_VERSION JAVAFX_TESTS; do
    [ -n "${!name:-}" ] || { echo "${name} is required" >&2; exit 1; }
done
if [ "${mode}" = linux ]; then
    : "${CMAKE_SYSROOT:?CMAKE_SYSROOT must point at a prepared Linux sysroot}"
    [ -d "${CMAKE_SYSROOT}/usr/include" ] || { echo "invalid CMAKE_SYSROOT: ${CMAKE_SYSROOT}" >&2; exit 1; }
fi

collect_reports() {
    local report_dir="ci-reports/cef-${CEF_API}" file safe_name
    mkdir -p "${report_dir}"
    while IFS= read -r file; do
        safe_name=${file#./}
        cp "${file}" "${report_dir}/${safe_name//\//__}"
    done < <(find . -path './.git' -prune -o -path './.cef-dist' -prune -o \
        -type f \( -path '*/target/surefire-reports/*' -o -name 'hs_err_pid*.log' \) -print)
}

on_exit() {
    local status=$?
    trap - EXIT
    [ "${status}" -eq 0 ] || collect_reports
    exit "${status}"
}
trap on_exit EXIT

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
    if ! jar tf "${artifact}" > "${entries}"; then
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
[ "${mode}" = desktop ] || clang++ --version

non_javafx='!cef4j-inprocess-jfx,!cef4j-remote-jfx,!cef4j-integration-tests,!cef4j-sample'
reactor_selection=()
if [ "${mode}" = linux ] && [ "${JAVAFX_TESTS}" != true ]; then
    reactor_selection=(-pl "${non_javafx}")
fi
properties=(
    "-Dcef.version=${CEF_VERSION}"
    "-Dcef.api.version=${CEF_API}"
    "-Djavafx.version=${JAVAFX_VERSION}"
    "-Djavafx.test.version=${JAVAFX_TEST_VERSION}"
)
if [ "${mode}" = desktop ]; then
    properties+=(
        "-Dcef4j.test.extraArgs=${CEF4J_TEST_EXTRA_ARGS:---disable-gpu}"
        "-Dcef4j.runtime.server.extraArgs=${CEF4J_TEST_EXTRA_ARGS:---disable-gpu}"
    )
    [ -z "${JAVAFX_PLATFORM:-}" ] || properties+=("-Djavafx.platform=${JAVAFX_PLATFORM}")
fi

retry ./mvnw -B dependency:go-offline "${reactor_selection[@]}" \
    -DexcludeArtifactIds=cef4j-platform,cef4j-runtime-server "${properties[@]}"
retry ./mvnw -B -pl cef4j-platform spotless:check "${properties[@]}"

build_options=()
if [ "${mode}" = linux ] || [ "${JAVAFX_TESTS}" != true ]; then
    build_options=(-DskipTests)
fi
./mvnw -B clean install "${build_options[@]}" "${reactor_selection[@]}" "${properties[@]}"
verify_thin_platform_jar
[ "${mode}" = desktop ] || verify_linux_abi

test_properties=("${properties[@]}")
test_prefix=()
if [ "${mode}" = linux ]; then
    test_prefix=(xvfb-run -a)
    test_properties+=(
        -Dcef4j.test.extraArgs=--disable-gpu
        -Dcef4j.runtime.server.extraArgs=--disable-gpu
    )
    if [ "${JAVAFX_TESTS}" = true ]; then
        "${test_prefix[@]}" ./mvnw -B install "${test_properties[@]}"
    fi
fi
"${test_prefix[@]}" ./mvnw -B test -pl "${non_javafx}" "${test_properties[@]}"

if [ "${JAVA11_SMOKE:-false}" = true ]; then
    ./mvnw -B -pl cef4j-remote-core,cef4j-remote-frame,cef4j-cdp,cef4j-webdriver,cef4j-codecs-gson,cef4j-codecs-jackson,cef4j-remote-webdriver test \
        -Djava11.runtime.smoke=true
fi

if [ "${RELEASE_BUILD:-false}" = true ]; then
    ./mvnw -B verify -DskipTests -Dgpg.skip=true -Drelease.bundle=true -Prelease "${properties[@]}"
fi
