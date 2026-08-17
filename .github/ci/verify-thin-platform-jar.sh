#!/usr/bin/env bash
set -euo pipefail

set -- cef4j-platform/target/cef4j-platform-*.jar
if [ "$#" -ne 1 ] || [ ! -f "$1" ]; then
    echo "expected exactly one classified cef4j-platform JAR, found $#" >&2
    exit 1
fi

artifact=$1
entries=$(mktemp "${RUNNER_TEMP:-/tmp}/cef4j-platform-entries.XXXXXX")
trap 'rm -f "${entries}"' EXIT
jar tf "${artifact}" > "${entries}"

bridge_count=$(grep -Ec '^native/[^/]+/(libcef4j\.(so|dylib)|cef4j\.dll)$' "${entries}" || true)
launcher_count=$(grep -Ec '^native/[^/]+/cef4j_launcher(\.exe)?$' "${entries}" || true)
if [ "${bridge_count}" -ne 1 ] || [ "${launcher_count}" -ne 1 ]; then
    echo "${artifact} must contain exactly one JNI bridge and one launcher" >&2
    cat "${entries}" >&2
    exit 1
fi

unexpected=false
while IFS= read -r entry; do
    case "${entry}" in
        META-INF/|META-INF/MANIFEST.MF|native/|native/*/|native/*/libcef4j.so|native/*/libcef4j.dylib|native/*/cef4j.dll|native/*/cef4j_launcher|native/*/cef4j_launcher.exe) ;;
        *)
            echo "unexpected platform JAR entry: ${entry}" >&2
            unexpected=true
            ;;
    esac
done < "${entries}"

if [ "${unexpected}" = true ]; then
    exit 1
fi

echo "Verified thin platform bridge: ${artifact}"
