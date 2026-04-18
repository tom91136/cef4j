# cef4j

Code-generated, OSR-only Java bindings for CEF.

## Tested Version Matrix

Status below is for a fresh-cache host Linux/Xvfb run on JDK 21 using:

```bash
xvfb-run -a ./mvnw clean verify -Dspotless.skip=true
```

For non-default CEF versions, the run also sets `-Dcef.version`, `-Dcef.api.version`, and a
version-specific `-Dcef.dist.cache`, and that cache must contain the Linux, macOS, and Windows
minimal CEF bundles because codegen walks public headers for all three platform variants.

Role labels used here:

 - `Edge`: default newest line in this repo
 - `Stable`: newest non-default line we keep explicitly green
 - `LTC`: long-tail compatibility lane for materially older generated/public APIs
 - `Floor`: oldest compatibility lane we still verify end-to-end

The "significance" column mixes those repo-local roles with externally meaningful anchors like
Chrome/Chromium `109` for Windows 7/8/8.1.

| CEF API | CEF version | Significance | Fresh `clean verify` status | Notes |
| --- | --- | --- | --- | --- |
| 146 | `146.0.9+g3ca6a87+chromium-146.0.7680.165` | `Edge`. Default `pom.xml` line and reference for the newest generated API surface. | Pass | Full host-side `clean verify` green. |
| 144 | `144.0.19+g937f5c6+chromium-144.0.7559.246` | `Stable`. Oldest `V144Plus` bucket representative and newest non-default verification lane. | Pass | Full host-side `clean verify` green. |
| 116 | `116.0.27+gd8c85ac+chromium-116.0.5845.190` | `LTC`. Legacy pre-`117` lane that excludes the `V117Plus` tests and exercises the older generated API surface. | Pass | Full host-side `clean verify` green. |
| 109 | `109.1.18+gf1c41e4+chromium-109.0.5414.120` | `Floor`. Legacy floor; Chromium/Chrome `109` is the last line with official Windows 7/8/8.1 support. | Pass | Full host-side `clean verify` green. |

Notes on significance:

- `146` is the repo default, so it is the branch that keeps day-to-day development honest.
- `144` is not special upstream by itself; it is special in this repo because the newer API-only
  tests are bucketed as `V144Plus`, so it is the first meaningful non-default stability lane.
- `116` is the oldest line we actively keep green before the `V117Plus` test bucket begins. It is
  the practical long-tail Linux/API lane in this repo, but not a formal distro-support claim.
- `109` is the last Chrome-supported branch for Windows 7 / Windows 8 / Windows 8.1, which makes
  it the most useful legacy floor to keep in the matrix.

## Native Cache

`SystemBootstrap` extracts `libcef4j`, `cef4j_launcher`, and the bundled CEF runtime into
`${java.io.tmpdir}/cef4j-cache/<platform>/<fingerprint>`.

The fingerprint is derived from the embedded native resources and the selected CEF runtime. This
avoids reusing stale DSOs when switching between different CEF builds, which was a common source of
startup failures during cross-version testing.

If you need to force a fresh extraction anyway, it is safe to remove `${java.io.tmpdir}/cef4j-cache`
before the next run.

## Linux Test Notes

Linux UI tests currently run more reliably when cef4j disables the CEF sandbox layers in addition
to setting `settings.noSandbox = true`. The startup path appends these flags automatically:

- `--no-sandbox`
- `--disable-setuid-sandbox`
- `--disable-seccomp-filter-sandbox`
- `--disable-gpu-sandbox`

Tooling sandboxes can still produce false negatives that do not reproduce on the host machine. The
matrix above is based on host-side `xvfb-run` verification, not sandboxed tool execution.

## Debugging

See [DEBUG.md](DEBUG.md) for local troubleshooting notes.
