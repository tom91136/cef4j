#!/usr/bin/env bash
set -euo pipefail

if [ "$#" -ne 2 ]; then
  echo "usage: $0 <cef-version> <cache-directory>" >&2
  exit 2
fi

cef_version=$1
cache_root=$2
chromium_version=${cef_version##*chromium-}
if [ -z "$chromium_version" ] || [ "$chromium_version" = "$cef_version" ]; then
  echo "Could not derive Chromium version from CEF version: $cef_version" >&2
  exit 1
fi

schema_dir="$cache_root/cdp-$chromium_version"
if [ -s "$schema_dir/browser_protocol.json" ] &&
   [ -s "$schema_dir/js_protocol.json" ] &&
   [ -s "$schema_dir/schema.properties" ]; then
  exit 0
fi

mkdir -p "$cache_root"
work_dir=$(mktemp -d "$cache_root/cdp-$chromium_version.tmp.XXXXXX")
trap 'rm -rf "$work_dir"' EXIT
chromium_base="https://chromium.googlesource.com/chromium/src/+/refs/tags/$chromium_version"
if command -v python3 >/dev/null 2>&1; then
  python_cmd=python3
else
  python_cmd=python
fi

fetch_b64() {
  curl --fail --location --silent --show-error --retry 5 --retry-all-errors "$1?format=TEXT" \
    | base64 --decode > "$2"
}

fetch_b64 "$chromium_base/DEPS" "$work_dir/DEPS"
v8_revision=$(sed -n "s/.*'v8_revision': '\([^']*\)'.*/\1/p" "$work_dir/DEPS")
if [ -z "$v8_revision" ]; then
  echo "Could not derive V8 revision from Chromium DEPS" >&2
  exit 1
fi

fetch_b64 "$chromium_base/third_party/inspector_protocol/convert_protocol_to_json.py" "$work_dir/convert_protocol_to_json.py"
fetch_b64 "$chromium_base/third_party/inspector_protocol/pdl.py" "$work_dir/pdl.py"
fetch_b64 "https://chromium.googlesource.com/v8/v8/+/$v8_revision/include/js_protocol.pdl" "$work_dir/js_protocol.pdl"

curl --fail --location --silent --show-error --retry 5 --retry-all-errors \
  "https://chromium.googlesource.com/chromium/src/+archive/refs/tags/$chromium_version/third_party/blink/public/devtools_protocol.tar.gz" \
  | tar -xz -C "$work_dir"

"$python_cmd" "$work_dir/convert_protocol_to_json.py" --map_binary_to_string=true \
  "$work_dir/browser_protocol.pdl" "$work_dir/browser_protocol.json"
"$python_cmd" "$work_dir/convert_protocol_to_json.py" --map_binary_to_string=true \
  "$work_dir/js_protocol.pdl" "$work_dir/js_protocol.json"

printf '%s\n' \
  "chromium.version=$chromium_version" \
  "v8.revision=$v8_revision" \
  > "$work_dir/schema.properties"

# Publish the complete cache entry atomically. Concurrent builders may both do
# the acquisition work, but neither can observe a partially populated schema.
if [ ! -d "$schema_dir" ]; then
  mv "$work_dir" "$schema_dir" 2>/dev/null || true
fi

if [ ! -s "$schema_dir/browser_protocol.json" ] ||
   [ ! -s "$schema_dir/js_protocol.json" ] ||
   [ ! -s "$schema_dir/schema.properties" ]; then
  echo "Failed to populate CDP schema cache: $schema_dir" >&2
  exit 1
fi
