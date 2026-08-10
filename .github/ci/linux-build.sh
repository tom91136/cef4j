#!/usr/bin/env bash
# Runs inside quay.io/pypa/manylinux_2_28_x86_64 via docker run.
# Env:  JAVA_VER, CEF_VERSION, CEF_API, JAVAFX_VERSION
set -euo pipefail

yum install -y -q \
    xorg-x11-server-Xvfb mesa-dri-drivers \
    gtk3 libXScrnSaver nss alsa-lib cups-libs \
    libXcomposite libXdamage libXrandr libXtst \
    pango at-spi2-atk libdrm mesa-libgbm \
    cmake gcc-c++ make
# cmake/gcc-c++/make: needed by cef4j-ipc-helper-native, which builds the helper subprocess via
# CMake (libzmq fetched + built in-tree via FetchContent). Without these, the cef4j-test-matrix
# module's IPC leg has no helper binary and IpcBrowserBackend.isAvailable() returns false.

mkdir -p /opt/java
curl -fsSL "https://corretto.aws/downloads/latest/amazon-corretto-${JAVA_VER}-x64-linux-jdk.tar.gz" \
    | tar -xz -C /opt/java
export JAVA_HOME=$(ls -d /opt/java/amazon-corretto-*-linux-x64 | head -1)
export PATH="${JAVA_HOME}/bin:${PATH}"
java -version

cd /work

# Pre-resolve dependencies (wrap in a retry loop — transient Central failures are common).
for attempt in 1 2 3; do
    if ./mvnw -B dependency:go-offline \
            "-Dcef.version=${CEF_VERSION}" \
            "-Dcef.api.version=${CEF_API}" \
            "-Djavafx.version=${JAVAFX_VERSION}"; then
        break
    fi
    [ "$attempt" = "3" ] && exit 1
    echo "dependency:go-offline attempt ${attempt} failed, retrying..."
    sleep 10
done

./mvnw -B clean package -DskipTests \
    "-Dcef.version=${CEF_VERSION}" \
    "-Dcef.api.version=${CEF_API}" \
    "-Djavafx.version=${JAVAFX_VERSION}"

# Native portability guard: only libcef + glibc core libs may be linked, and no symbol may
# require a glibc version above 2.28 (manylinux_2_28 baseline).
for LIB in $(find cef4j-native/target -name 'libcef4j.so' -o -name 'cef4j_launcher'); do
    echo "=== $LIB ==="
    readelf -d "$LIB" | grep -E 'NEEDED|SONAME'
    bad=$(readelf -d "$LIB" | grep -E 'NEEDED' \
        | grep -vE '(libcef\.so|libc\.so\.6|libm\.so\.6|libdl\.so\.2|libpthread\.so\.0)' || true)
    if [ -n "$bad" ]; then
        echo "ERROR: unexpected dynamic dep in $LIB:"
        echo "$bad"
        exit 1
    fi
    max=$(nm -D --with-symbol-versions "$LIB" 2>/dev/null \
        | grep -oE '@GLIBC_[0-9.]+' | sort -uV | tail -1 || true)
    echo "max glibc required: $max"
    if [ -n "$max" ] && [ "$(printf '%s\n2.28\n' "${max#@GLIBC_}" | sort -V | tail -1)" != "2.28" ]; then
        echo "ERROR: $LIB requires $max (above manylinux_2_28 baseline 2.28)"
        exit 1
    fi
done

Xvfb :99 -screen 0 1280x1024x24 -ac &
XVFB_PID=$!
sleep 1
export DISPLAY=:99
trap 'kill ${XVFB_PID} 2>/dev/null || true' EXIT

./mvnw -B verify \
    "-Dcef.version=${CEF_VERSION}" \
    "-Dcef.api.version=${CEF_API}" \
    "-Djavafx.version=${JAVAFX_VERSION}"

# Surface which matrix legs actually ran (Assumptions skip the IPC leg if the helper binary is
# missing, which would otherwise show as "test passed" via JUnit's aborted-as-success path).
# This greps the surefire reports and prints per-row PASS/FAIL/skipped so failures in one backend
# don't get lost behind the other.
if [ -d cef4j-test-matrix/target/surefire-reports ]; then
    echo "=== cross-backend matrix results ==="
    for xml in cef4j-test-matrix/target/surefire-reports/TEST-*.xml; do
        grep -E "<testcase|<failure|<error|<skipped" "$xml" || true
    done
fi
