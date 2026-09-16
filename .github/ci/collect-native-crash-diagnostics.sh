#!/usr/bin/env bash
set -u

output=${1:-target/native-crash-diagnostics}
mkdir -p "${output}"

found=false
java_executable=$(command -v java || true)
workspace=${GITHUB_WORKSPACE:-${PWD}}

core_index=0
while IFS= read -r -d '' core; do
    found=true
    core_index=$((core_index + 1))
    name="${core_index}-$(basename "${core}")"
    {
        file "${core}"
        du -h "${core}"
    } > "${output}/${name}-info.txt" 2>&1
    if [ -n "${java_executable}" ] && command -v gdb >/dev/null 2>&1; then
        gdb --batch --quiet \
            -ex "set pagination off" \
            -ex "info sharedlibrary" \
            -ex "thread apply all bt full" \
            "${java_executable}" "${core}" > "${output}/${name}-gdb.txt" 2>&1 || true
    else
        echo "gdb or java executable unavailable" > "${output}/${name}-gdb.txt"
    fi
done < <(find "${workspace}" -type f \( -name core -o -name 'core.*' \) -print0)

if ! command -v coredumpctl >/dev/null 2>&1; then
    if [ "${found}" = false ]; then
        echo "No workspace core dumps found and coredumpctl is unavailable"
    fi
    exit 0
fi

index="${output}/coredumps.jsonl"
if ! coredumpctl --no-pager --json=short list > "${index}" 2> "${output}/coredumpctl-list.stderr"; then
    echo "coredumpctl could not list runner cores; hs_err files remain available"
    exit 0
fi

while IFS= read -r pid; do
    [ -n "${pid}" ] || continue
    found=true
    coredumpctl --no-pager info "${pid}" > "${output}/core-${pid}-info.txt" 2>&1 || true
    coredumpctl --no-pager --quiet \
        --debugger-arguments="-batch -ex 'set pagination off' -ex 'thread apply all bt full'" \
        debug "${pid}" > "${output}/core-${pid}-gdb.txt" 2>&1 || true
done < <(jq -r '
    (if type == "array" then .[] else . end)
    | select((.exe // .COREDUMP_EXE // "") | endswith("/java"))
    | (.pid // .COREDUMP_PID // empty)
' "${index}")

if [ "${found}" = false ]; then
    echo "No Java core dumps were found"
fi
