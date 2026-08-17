#!/usr/bin/env bash
# Run one CEF lane and preserve its diagnostics before the next pooled lane
# cleans the reactor. Usage: run-cef.sh linux|desktop
set -uo pipefail

: "${CEF_API:?CEF_API is required}"
repo_root=$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")/../.." && pwd)
cd "${repo_root}" || exit 1

case "${1:-}" in
    linux) .github/ci/linux-build.sh ;;
    desktop) .github/ci/desktop-build.sh ;;
    *) echo "usage: $0 linux|desktop" >&2; exit 2 ;;
esac
status=$?

if [ "${status}" -ne 0 ]; then
    report_dir="ci-reports/cef-${CEF_API}"
    mkdir -p "${report_dir}"
    while IFS= read -r file; do
        safe_name=${file#./}
        safe_name=${safe_name//\//__}
        cp "${file}" "${report_dir}/${safe_name}"
    done < <(find . \
        -path './.git' -prune -o \
        -path './.cef-dist' -prune -o \
        -type f \( -path '*/target/surefire-reports/*' -o -name 'hs_err_pid*.log' \) -print)
fi

exit "${status}"
