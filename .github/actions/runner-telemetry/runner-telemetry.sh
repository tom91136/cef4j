#!/usr/bin/env bash
set -u -o pipefail

mode=${1:-start}
case "${mode}" in
    start|stop|stream) ;;
    *) exit 0 ;;
esac

if [ "${CI_TELEMETRY_ENABLED:-false}" != true ]; then
    exit 0
fi

host=${CI_TELEMETRY_HOST:-}
user=${CI_TELEMETRY_USER:-tom}
port=${CI_TELEMETRY_PORT:-22}
ssh_key=${CI_TELEMETRY_SSH_KEY:-}
known_hosts=${CI_TELEMETRY_KNOWN_HOSTS:-}
remote_root=${CI_TELEMETRY_REMOTE_ROOT:-/home/tom/gha-telemetry}
interval=${CI_TELEMETRY_INTERVAL:-30}

if [ -z "${host}" ] || [ -z "${ssh_key}" ] || [ -z "${known_hosts}" ]; then
    [ "${mode}" = start ] && echo "runner telemetry disabled: CI_TELEMETRY_HOST, CI_TELEMETRY_SSH_KEY, and CI_TELEMETRY_KNOWN_HOSTS are required" >&2
    exit 0
fi

case "${port}" in
    ''|*[!0-9]*) port=22 ;;
esac
case "${interval}" in
    ''|*[!0-9]*) interval=30 ;;
esac
[ "${interval}" -ge 5 ] 2>/dev/null || interval=5

state_base=${RUNNER_TEMP:-${TMPDIR:-/tmp}}
if [ "${RUNNER_OS:-}" = Windows ] && command -v cygpath >/dev/null 2>&1; then
    state_base=$(cygpath --unix "${state_base}" 2>/dev/null || printf '%s' "${state_base}")
fi
state_dir=${state_base%/}/cef4j-runner-telemetry
if ! mkdir -p "${state_dir}" 2>/dev/null; then
    state_dir=/tmp/cef4j-runner-telemetry
    mkdir -p "${state_dir}" 2>/dev/null || exit 0
fi
chmod 700 "${state_dir}" 2>/dev/null || true

script_path=$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")" && pwd)/runner-telemetry.sh
pid_file=${state_dir}/pid
transport_log=${state_dir}/transport.log
snapshot_file=${state_dir}/snapshot.txt
metadata_file=${state_dir}/metadata.txt
key_file=${state_dir}/oci_telemetry_key
known_hosts_file=${state_dir}/known_hosts
ssh_options=()

sanitize_component() {
    local value=${1:-unknown}
    value=$(printf '%s' "${value}" | LC_ALL=C sed 's/[^A-Za-z0-9._-]/_/g')
    [ -n "${value}" ] || value=unknown
    printf '%s' "${value}"
}

if [[ "${remote_root}" != /* || "${remote_root}" == *..* || "${remote_root}" == *[!A-Za-z0-9_./-]* ]]; then
    remote_root=/home/tom/gha-telemetry
fi

repository=${GITHUB_REPOSITORY:-unknown/unknown}
repository_owner=$(sanitize_component "${repository%%/*}")
repository_name=$(sanitize_component "${repository#*/}")
run_id=$(sanitize_component "${GITHUB_RUN_ID:-unknown}")
attempt=$(sanitize_component "${GITHUB_RUN_ATTEMPT:-1}")
job=$(sanitize_component "${GITHUB_JOB:-unknown}")
context=$(sanitize_component "${CI_TELEMETRY_CONTEXT:-job}")
runner=$(sanitize_component "${RUNNER_NAME:-unknown}")
remote_dir=${remote_root%/}/${repository_owner}/${repository_name}/run-${run_id}/attempt-${attempt}/job-${job}/context-${context}/runner-${runner}

log_transport() {
    printf '%s %s\n' "$(date -u +%Y-%m-%dT%H:%M:%SZ)" "$*" >> "${transport_log}"
}

prepare_ssh() {
    if [ ! -s "${key_file}" ]; then
        printf '%s\n' "${ssh_key}" > "${key_file}"
        chmod 600 "${key_file}" 2>/dev/null || true
    fi
    if [ ! -s "${known_hosts_file}" ]; then
        printf '%s\n' "${known_hosts}" > "${known_hosts_file}"
        chmod 600 "${known_hosts_file}" 2>/dev/null || true
    fi
    ssh_options=(
        ssh
        -p "${port}"
        -i "${key_file}"
        -o BatchMode=yes
        -o ConnectTimeout=10
        -o ServerAliveInterval=5
        -o ServerAliveCountMax=2
        -o UserKnownHostsFile="${known_hosts_file}"
    )
    ssh_options+=(-o StrictHostKeyChecking=yes)
    ssh_options+=("${user}@${host}")
}

send_file() {
    local source=$1 destination=$2 replace=${3:-false}
    local command
    if [ "${replace}" = true ]; then
        command="umask 077; mkdir -p -- '${remote_dir}'; cat > '${remote_dir}/${destination}'"
    else
        command="umask 077; mkdir -p -- '${remote_dir}'; cat >> '${remote_dir}/${destination}'"
    fi
    if "${ssh_options[@]}" "${command}" < "${source}" 2>>"${transport_log}"; then
        return 0
    fi
    log_transport "OCI connection failed while writing ${destination}"
    return 1
}

compact_processes() {
    local pattern=$1 lower_pattern
    lower_pattern=$(printf '%s' "${pattern}" | tr '[:upper:]' '[:lower:]')
    ps -axo pid=,ppid=,state=,%cpu=,%mem=,rss=,etime=,command= 2>/dev/null \
        | awk -v pattern="${lower_pattern}" 'tolower($0) ~ pattern { $1=$1; printf "%s;", $0 }' \
        | sed 's/;$//' || true
}

snapshot_unix() {
    local memory_available memory_total disk_available process_count tcp_established load cpu_count
    if [ -r /proc/meminfo ]; then
        memory_available=$(awk '/MemAvailable:/ { print $2; exit }' /proc/meminfo)
        memory_total=$(awk '/MemTotal:/ { print $2; exit }' /proc/meminfo)
    else
        memory_available=unknown
        memory_total=unknown
    fi
    disk_available=$(df -Pk / 2>/dev/null | awk 'NR == 2 { print $4; exit }')
    process_count=$(ps -e 2>/dev/null | tail -n +2 | wc -l | tr -d ' ')
    cpu_count=$(getconf _NPROCESSORS_ONLN 2>/dev/null || sysctl -n hw.ncpu 2>/dev/null || true)
    if [ -r /proc/loadavg ]; then
        load=$(awk '{ print $1 "," $2 "," $3 }' /proc/loadavg)
    else
        load=$(uptime 2>/dev/null | sed 's/.*load average[s]*: *//' | tr -d '\r\n')
    fi
    if command -v ss >/dev/null 2>&1; then
        tcp_established=$(ss -tan state established 2>/dev/null | tail -n +2 | wc -l | tr -d ' ')
    else
        tcp_established=$(netstat -an 2>/dev/null | grep -c ESTABLISHED || true)
    fi
    printf 'timestamp=%s\n' "$(date -u +%Y-%m-%dT%H:%M:%SZ)"
    printf 'hostname=%s\n' "$(hostname 2>/dev/null || printf unknown)"
    printf 'os=%s\n' "$(uname -srm 2>/dev/null || printf unknown)"
    printf 'memory_available_kb=%s\n' "${memory_available:-unknown}"
    printf 'memory_total_kb=%s\n' "${memory_total:-unknown}"
    printf 'disk_available_kb=%s\n' "${disk_available:-unknown}"
    printf 'process_count=%s\n' "${process_count:-unknown}"
    printf 'cpu_count=%s\n' "${cpu_count:-unknown}"
    printf 'load=%s\n' "${load:-unknown}"
    printf 'tcp_established=%s\n' "${tcp_established:-unknown}"
    printf 'runner_processes=%s\n' "$(compact_processes 'Runner\.(Listener|Worker)')"
    printf 'build_processes=%s\n' "$(compact_processes 'java|mvn|cmake|ninja|cef4j')"
}

snapshot_windows() {
    powershell.exe -NoLogo -NoProfile -NonInteractive -ExecutionPolicy Bypass -Command - <<'POWERSHELL'
$os = Get-CimInstance Win32_OperatingSystem
$computer = Get-CimInstance Win32_ComputerSystem
$disk = Get-CimInstance Win32_LogicalDisk -Filter "DeviceID='C:'"
$runner = @(Get-Process -Name Runner.Listener,Runner.Worker -ErrorAction SilentlyContinue | ForEach-Object { "$($_.Id):$($_.ProcessName):$($_.WorkingSet64)" }) -join ';'
$build = @(Get-Process -ErrorAction SilentlyContinue | Where-Object { $_.ProcessName -match 'java|mvn|cmake|ninja|cef4j' } | ForEach-Object { "$($_.Id):$($_.ProcessName):$($_.WorkingSet64)" }) -join ';'
try { $tcp = @(Get-NetTCPConnection -State Established -ErrorAction Stop).Count } catch { $tcp = 'unknown' }
Write-Output ("timestamp=" + [DateTime]::UtcNow.ToString('yyyy-MM-ddTHH:mm:ssZ'))
Write-Output ("hostname=" + $env:COMPUTERNAME)
Write-Output ("os=Windows " + $computer.SystemType)
Write-Output ("memory_available_kb=" + $os.FreePhysicalMemory)
Write-Output ("memory_total_kb=" + $os.TotalVisibleMemorySize)
Write-Output ("disk_available_kb=" + [math]::Round($disk.FreeSpace / 1KB))
Write-Output ("process_count=" + @(Get-Process).Count)
Write-Output ("logical_processors=" + $computer.NumberOfLogicalProcessors)
Write-Output ("tcp_established=" + $tcp)
Write-Output ("runner_processes=" + $runner)
Write-Output ("build_processes=" + $build)
POWERSHELL
}

collect_snapshot() {
    local output=$1
    {
        printf '%s\n' "--- snapshot $(date -u +%Y-%m-%dT%H:%M:%SZ) ---"
        if [ "${RUNNER_OS:-}" = Windows ]; then
            snapshot_windows | tr -d '\r'
        else
            snapshot_unix
        fi
    } > "${output}" 2>&1
}

write_metadata() {
    {
        printf 'repository=%s\n' "${GITHUB_REPOSITORY:-unknown}"
        printf 'workflow=%s\n' "${GITHUB_WORKFLOW:-unknown}"
        printf 'run_id=%s\n' "${GITHUB_RUN_ID:-unknown}"
        printf 'run_attempt=%s\n' "${GITHUB_RUN_ATTEMPT:-1}"
        printf 'job=%s\n' "${GITHUB_JOB:-unknown}"
        printf 'context=%s\n' "${CI_TELEMETRY_CONTEXT:-job}"
        printf 'runner_name=%s\n' "${RUNNER_NAME:-unknown}"
        printf 'runner_os=%s\n' "${RUNNER_OS:-unknown}"
        printf 'runner_arch=%s\n' "${RUNNER_ARCH:-unknown}"
        printf 'runner_temp=%s\n' "${RUNNER_TEMP:-unknown}"
        printf 'started_at=%s\n' "$(date -u +%Y-%m-%dT%H:%M:%SZ)"
    } > "${metadata_file}"
}

stream() {
    prepare_ssh
    write_metadata
    local metadata_sent=false
    while :; do
        if [ "${metadata_sent}" = false ] && send_file "${metadata_file}" metadata.txt true; then
            metadata_sent=true
        fi
        collect_snapshot "${snapshot_file}"
        send_file "${snapshot_file}" snapshots.log false || true
        sleep "${interval}"
    done
}

start() {
    prepare_ssh
    if [ -f "${pid_file}" ]; then
        local existing_pid
        existing_pid=$(cat "${pid_file}" 2>/dev/null || true)
        if [ -n "${existing_pid}" ] && kill -0 "${existing_pid}" 2>/dev/null; then
            exit 0
        fi
        rm -f "${pid_file}"
    fi
    write_metadata
    nohup bash "${script_path}" stream > "${state_dir}/sidecar.log" 2>&1 < /dev/null &
    printf '%s\n' "$!" > "${pid_file}"
    echo "runner telemetry started (interval=${interval}s, remote=${remote_dir})"
}

stop() {
    local existing_pid
    prepare_ssh
    existing_pid=$(cat "${pid_file}" 2>/dev/null || true)
    if [ -n "${existing_pid}" ] && kill -0 "${existing_pid}" 2>/dev/null; then
        kill "${existing_pid}" 2>/dev/null || true
        for _ in 1 2 3 4 5; do
            kill -0 "${existing_pid}" 2>/dev/null || break
            sleep 1
        done
    fi
    rm -f "${pid_file}"
    collect_snapshot "${snapshot_file}"
    send_file "${snapshot_file}" snapshots.log false || true
    rm -f "${key_file}" "${known_hosts_file}"
    echo "runner telemetry stopped"
}

case "${mode}" in
    start) start ;;
    stop) stop ;;
    stream) stream ;;
esac
