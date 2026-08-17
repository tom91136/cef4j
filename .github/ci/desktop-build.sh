#!/usr/bin/env bash
# Runs one CEF version on a native Windows or macOS runner. Env: CEF_VERSION,
# CEF_API, JAVAFX_VERSION, JAVAFX_TEST_VERSION, JAVAFX_TESTS,
# JAVAFX_PLATFORM, CEF4J_TEST_EXTRA_ARGS, JAVA11_SMOKE.
set -euo pipefail

: "${CEF_VERSION:?CEF_VERSION is required}"
: "${CEF_API:?CEF_API is required}"
: "${JAVAFX_VERSION:?JAVAFX_VERSION is required}"
: "${JAVAFX_TEST_VERSION:?JAVAFX_TEST_VERSION is required}"
: "${JAVAFX_TESTS:?JAVAFX_TESTS is required}"

repo_root=$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")/../.." && pwd)
cd "${repo_root}"
java -version

non_javafx_projects='!cef4j-inprocess-jfx,!cef4j-remote-jfx,!cef4j-integration-tests,!cef4j-sample'
maven_properties=(
    "-Dcef.version=${CEF_VERSION}"
    "-Dcef.api.version=${CEF_API}"
    "-Djavafx.version=${JAVAFX_VERSION}"
    "-Djavafx.test.version=${JAVAFX_TEST_VERSION}"
    "-Dcef4j.test.extraArgs=${CEF4J_TEST_EXTRA_ARGS:---disable-gpu}"
    "-Dcef4j.runtime.server.extraArgs=${CEF4J_TEST_EXTRA_ARGS:---disable-gpu}"
)
if [ -n "${JAVAFX_PLATFORM:-}" ]; then
    maven_properties+=("-Djavafx.platform=${JAVAFX_PLATFORM}")
fi

for attempt in 1 2 3; do
    if ./mvnw -B dependency:go-offline \
            -DexcludeArtifactIds=cef4j-platform,cef4j-runtime-server \
            "${maven_properties[@]}"; then
        break
    fi
    [ "${attempt}" = "3" ] && exit 1
    echo "dependency:go-offline attempt ${attempt} failed, retrying..."
    sleep 10
done

for attempt in 1 2 3; do
    if ./mvnw -B -pl cef4j-platform spotless:check "${maven_properties[@]}"; then
        break
    fi
    [ "${attempt}" = "3" ] && exit 1
    echo "spotless:check attempt ${attempt} failed, retrying..."
    sleep 10
done

if [ "${JAVAFX_TESTS}" = "true" ]; then
    ./mvnw -B clean install "${maven_properties[@]}"
else
    # OpenJFX has no Windows ARM64 Maven natives. Compile the complete reactor,
    # then exercise every non-JavaFX module natively.
    ./mvnw -B clean install -DskipTests "${maven_properties[@]}"
fi

.github/ci/verify-thin-platform-jar.sh

./mvnw -B test -pl "${non_javafx_projects}" "${maven_properties[@]}"

if [ "${JAVA11_SMOKE:-false}" = "true" ]; then
    ./mvnw -B -pl cef4j-remote-core,cef4j-remote-recording-gson,cef4j-remote-recording-jackson,cef4j-remote-frame,cef4j-cdp,cef4j-cdp-gson,cef4j-cdp-jackson,cef4j-remote-cdp-gson,cef4j-remote-cdp-jackson,cef4j-webdriver,cef4j-webdriver-gson,cef4j-webdriver-jackson,cef4j-remote-webdriver test \
        -Djava11.runtime.smoke=true
fi
