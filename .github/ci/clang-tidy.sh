#!/usr/bin/env bash
# Runs the .clang-tidy rules over hand-written native sources using each module's CMake compile database.
set -euo pipefail

repo_root=$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")/../.." && pwd)
cd "${repo_root}"

status=0
for build in cef4j-runtime-server/target/cmake-build cef4j-platform/target/cmake-build; do
    db="${build}/compile_commands.json"
    [ -f "${db}" ] || { echo "missing ${db}; build the native modules first" >&2; exit 1; }
    mapfile -t sources < <(python3 - "${db}" <<'PY'
import json, sys
entries = json.load(open(sys.argv[1]))
print("\n".join(sorted({e["file"] for e in entries if "/gen/" not in e["file"] and "/_deps/" not in e["file"]})))
PY
)
    [ "${#sources[@]}" -gt 0 ] || continue
    clang-tidy -p "${build}" --quiet "${sources[@]}" || status=1
done
exit "${status}"
