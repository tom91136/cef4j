#!/usr/bin/env bash
set -euo pipefail

repo_dir=$(cd "$(dirname "$0")/.." && pwd)
chromium_version=${1:-$(sed -n 's|.*<cef.version>.*chromium-\([^<]*\)</cef.version>.*|\1|p' "$repo_dir/pom.xml")}
if [[ -z "$chromium_version" ]]; then
  echo "Could not derive Chromium version from pom.xml" >&2
  exit 1
fi

work_dir=$(mktemp -d)
trap 'rm -rf "$work_dir"' EXIT
chromium_base="https://chromium.googlesource.com/chromium/src/+/refs/tags/$chromium_version"

fetch_b64() {
  local temporary="$2.part"
  curl --fail --location --silent --show-error --retry 5 --retry-all-errors "$1?format=TEXT" \
    | base64 --decode > "$temporary"
  mv "$temporary" "$2"
}

fetch_b64 "$chromium_base/DEPS" "$work_dir/DEPS"
v8_revision=$(sed -n "s/.*'v8_revision': '\([^']*\)'.*/\1/p" "$work_dir/DEPS")
if [[ -z "$v8_revision" ]]; then
  echo "Could not derive V8 revision from Chromium DEPS" >&2
  exit 1
fi

fetch_b64 "$chromium_base/third_party/inspector_protocol/convert_protocol_to_json.py" "$work_dir/convert_protocol_to_json.py"
fetch_b64 "$chromium_base/third_party/inspector_protocol/pdl.py" "$work_dir/pdl.py"
fetch_b64 "https://chromium.googlesource.com/v8/v8/+/$v8_revision/include/js_protocol.pdl" "$work_dir/js_protocol.pdl"

# Chromium split the browser schema across per-domain PDL files. Gitiles can
# return that directory atomically, avoiding dozens of rate-limited requests.
curl --fail --location --silent --show-error --retry 5 --retry-all-errors \
  "https://chromium.googlesource.com/chromium/src/+archive/refs/tags/$chromium_version/third_party/blink/public/devtools_protocol.tar.gz" \
  | tar -xz -C "$work_dir"

python3 "$work_dir/convert_protocol_to_json.py" --map_binary_to_string=true \
  "$work_dir/browser_protocol.pdl" "$work_dir/browser_protocol.json"
python3 "$work_dir/convert_protocol_to_json.py" --map_binary_to_string=true \
  "$work_dir/js_protocol.pdl" "$work_dir/js_protocol.json"
python3 "$repo_dir/scripts/generate-cdp.py" \
  "$work_dir/browser_protocol.json" "$work_dir/js_protocol.json" \
  "$repo_dir/cef4j-cdp" "$chromium_version" "$v8_revision"

echo "Updated CDP schema and generated Java API for Chromium $chromium_version (V8 $v8_revision)"
