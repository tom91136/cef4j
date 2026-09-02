# Runner telemetry

The CI jobs can stream lightweight runner health snapshots to the OCI VM over
an outbound SSH connection. Telemetry is disabled when the configuration is
missing and for pull requests.

Configure these repository variables:

```text
CI_TELEMETRY_HOST
CI_TELEMETRY_USER=tom
CI_TELEMETRY_PORT=2222
CI_TELEMETRY_REMOTE_ROOT=/home/tom/gha-telemetry
CI_TELEMETRY_INTERVAL=10
```

Configure these repository secrets:

```text
CI_TELEMETRY_SSH_KEY
CI_TELEMETRY_KNOWN_HOSTS
```

Use a dedicated key with write access only to the telemetry directory. The
known-hosts secret must contain the pinned output of `ssh-keyscan` for the OCI
host; telemetry stays disabled until all three connection values are present.

The remote layout is:

```text
<root>/<owner>/<repo>/run-<id>/attempt-<n>/job-<job>/context-<matrix>/runner-<name>/
  metadata.txt
  snapshots.log
```

Each snapshot contains timestamp, hostname, memory, disk, process, runner,
build-process, and established-TCP counts. A hard runner failure leaves the
already-streamed snapshots in OCI for inspection.
