#!/usr/bin/env bash
# Runs on a native Linux runner with CMAKE_SYSROOT pointing at a prepared
# AlmaLinux 8 sysroot. Env: CEF_VERSION, CEF_API, JAVAFX_VERSION,
# JAVAFX_TEST_VERSION, JAVAFX_TESTS, JAVA11_SMOKE.
set -euo pipefail

RELEASE_BUILD=${RELEASE_BUILD:-false}
: "${CMAKE_SYSROOT:?CMAKE_SYSROOT must point at a prepared Linux sysroot}"
[[ -d ${CMAKE_SYSROOT}/usr/include ]] || {
    echo "invalid CMAKE_SYSROOT: ${CMAKE_SYSROOT}" >&2
    exit 1
}

repo_root=$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")/../.." && pwd)
cd "${repo_root}"
java -version
clang++ --version

non_javafx_projects='!cef4j-inprocess-jfx,!cef4j-remote-jfx,!cef4j-integration-tests,!cef4j-sample'
reactor_selection=()
if [ "${JAVAFX_TESTS}" != "true" ]; then
    reactor_selection=(-pl "${non_javafx_projects}")
fi

# Pre-resolve dependencies (wrap in a retry loop — transient Central failures are common).
for attempt in 1 2 3; do
    if ./mvnw -B dependency:go-offline "${reactor_selection[@]}" \
            -DexcludeArtifactIds=cef4j-platform,cef4j-runtime-server \
            "-Dcef.version=${CEF_VERSION}" \
            "-Dcef.api.version=${CEF_API}" \
            "-Djavafx.version=${JAVAFX_VERSION}" \
            "-Djavafx.test.version=${JAVAFX_TEST_VERSION}"; then
        break
    fi
    [ "$attempt" = "3" ] && exit 1
    echo "dependency:go-offline attempt ${attempt} failed, retrying..."
    sleep 10
done

./mvnw -B clean package -DskipTests "${reactor_selection[@]}" \
    "-Dcef.version=${CEF_VERSION}" \
    "-Dcef.api.version=${CEF_API}" \
    "-Djavafx.version=${JAVAFX_VERSION}" \
    "-Djavafx.test.version=${JAVAFX_TEST_VERSION}"

# Native portability guard: only libcef + glibc core libs may be linked, and no symbol may
# require a glibc version above 2.28 (AlmaLinux 8 baseline).
while IFS= read -r LIB; do
    echo "=== $LIB ==="
    readelf -d "$LIB" | grep -E 'NEEDED|SONAME'
    bad=$(readelf -d "$LIB" | grep -E 'NEEDED' \
        | grep -vE '(libcef\.so|libc\.so\.6|libm\.so\.6|libdl\.so\.2|libpthread\.so\.0|librt\.so\.1|ld-linux[^]]*\.so)' || true)
    if [ -n "$bad" ]; then
        echo "ERROR: unexpected dynamic dep in $LIB:"
        echo "$bad"
        exit 1
    fi
    max=$(nm -D --with-symbol-versions "$LIB" 2>/dev/null \
        | grep -oE '@GLIBC_[0-9.]+' | sort -uV | tail -1 || true)
    echo "max glibc required: $max"
    if [ -n "$max" ] && [ "$(printf '%s\n2.28\n' "${max#@GLIBC_}" | sort -V | tail -1)" != "2.28" ]; then
        echo "ERROR: $LIB requires $max (above AlmaLinux 8 baseline 2.28)"
        exit 1
    fi
done < <(find cef4j-native/target cef4j-runtime-server/target \
    -type f \( -name 'libcef4j.so' -o -name 'cef4j_launcher' -o -name 'cef4j-runtime-server' \))

if [ "${JAVAFX_TESTS}" = "true" ]; then
    xvfb-run -a ./mvnw -B install \
        "-Dcef.version=${CEF_VERSION}" \
        "-Dcef.api.version=${CEF_API}" \
        "-Djavafx.version=${JAVAFX_VERSION}" \
        "-Djavafx.test.version=${JAVAFX_TEST_VERSION}" \
        "-Dcef4j.test.extraArgs=--disable-gpu" \
        "-Dcef4j.runtime.server.extraArgs=--disable-gpu"
fi

# Repeat the non-JavaFX reactor under the same native/JDK/CEF combination. Keeping
# this as an in-job build mode avoids doubling the workflow matrix while proving
# that core, Swing, remote, CDP, HTTP, recording, frame, and WebDriver modules do
# not acquire a JavaFX dependency.
xvfb-run -a ./mvnw -B test \
    -pl "${non_javafx_projects}" \
    "-Dcef.version=${CEF_VERSION}" \
    "-Dcef.api.version=${CEF_API}" \
    "-Djavafx.version=${JAVAFX_VERSION}" \
    "-Djavafx.test.version=${JAVAFX_TEST_VERSION}" \
    "-Dcef4j.test.extraArgs=--disable-gpu" \
    "-Dcef4j.runtime.server.extraArgs=--disable-gpu"

# Maven/Scala codegen runs on JDK 17+, but the distributable IPC transport must
# remain executable on Java 11. Exercise one representative matrix row with a
# JDK 11 Surefire fork without attempting to run Maven itself on Java 11.
if [ "${JAVA11_SMOKE}" = "true" ]; then
    ./mvnw -B -pl cef4j-remote-core,cef4j-remote-recording-gson,cef4j-remote-recording-jackson,cef4j-remote-frame,cef4j-cdp,cef4j-cdp-gson,cef4j-cdp-jackson,cef4j-remote-cdp-gson,cef4j-remote-cdp-jackson,cef4j-webdriver,cef4j-webdriver-gson,cef4j-webdriver-jackson,cef4j-remote-webdriver test \
        -Djava11.runtime.smoke=true
fi

# Surface which matrix legs actually ran (Assumptions skip the IPC leg if the runtime server binary is
# missing, which would otherwise show as "test passed" via JUnit's aborted-as-success path).
# This greps the surefire reports and prints per-row PASS/FAIL/skipped so failures in one backend
# don't get lost behind the other.
if [ -d cef4j-integration-tests/target/surefire-reports ]; then
    echo "=== cross-backend matrix results ==="
    for xml in cef4j-integration-tests/target/surefire-reports/TEST-*.xml; do
        grep -E "<testcase|<failure|<error|<skipped" "$xml" || true
    done
fi

if [ "${RELEASE_BUILD}" = "true" ]; then
    ./mvnw -B verify -DskipTests -Dgpg.skip=true -Drelease.bundle=true -Prelease \
        "-Dcef.version=${CEF_VERSION}" \
        "-Dcef.api.version=${CEF_API}" \
        "-Djavafx.version=${JAVAFX_VERSION}" \
        "-Djavafx.test.version=${JAVAFX_TEST_VERSION}"
fi
