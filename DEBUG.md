# Debugging cef4j

This is the operational reference for builds, native crashes, platform startup, the runtime server, IPC, and test
isolation. Planned engineering work belongs in [PLAN.md](PLAN.md); architecture and normal usage belong in
[README.md](README.md).

## First response

1. Reproduce at the narrowest test layer: Java unit, HTTP/fake backend, IPC loopback, packaged runtime server, then
   full UI.
2. On Linux, put every CEF/UI test under `xvfb-run`; never attach native tests to the active desktop session.
3. Record the JDK, OS/architecture, CEF version/API, control transport, frame provider, and whether CEF is in-process
   or in the runtime server.
4. Inspect `chrome_debug.log`, the JVM crash file (`hs_err_pid*.log`), runtime-server stderr, and Surefire reports.
5. Confirm the executable and libraries actually loaded are from the build being tested.

Useful baseline commands:

```bash
xvfb-run -a ./mvnw test
xvfb-run -a ./mvnw clean verify -Dspotless.check.skip=true
find /tmp -name chrome_debug.log -mmin -10 2>/dev/null
find . -name 'hs_err_pid*.log' -o -path '*/surefire-reports/*-output.txt'
```

## Native-crash symptoms

- JVM or runtime-server termination without a Java stack trace
- Surefire: `The forked VM terminated without properly saying goodbye`
- exit 133 (`SIGTRAP`), 134 (`SIGABRT`), or 139 (`SIGSEGV`)
- allocator errors such as `malloc(): invalid size` or `malloc(): corrupted top size`
- a supervisor generation disconnect followed by a server restart

CEF `LOG(FATAL)` writes to `chrome_debug.log` and may then trap without copying the fatal message to stderr. cef4j's
native crash handler prints the selected CEF log path and a native backtrace on macOS/Linux when possible.

```bash
find /tmp -name chrome_debug.log -mmin -10 -print
rg 'FATAL|ERROR' /tmp/cef4j-*/chrome_debug.log
```

Set `-Dcef4j.disableStderrRedirect=true` when direct native stderr is more useful than the normal SLF4J bridge.

### Crash classification

| Symptom | Likely cause |
| --- | --- |
| `invalid version -1` in CEF log | `cef_api_hash` was not reached; verify `JNI_OnLoad` and exports |
| crash after passing an object pointer | missing native `add_ref`; CEF consumed the caller's reference |
| allocator corruption | leaked/freed CEF string or collection, buffer overrun, or double free |
| crash in `release()` or shutdown | stale pointer, incorrect ownership, or duplicate release |
| `SIGTRAP` without allocator output | CEF `DCHECK`/`LOG(FATAL)`; inspect `chrome_debug.log` |
| generated JNI `SIGSEGV` | null/stale native pointer or incorrect generated cast/layout |
| client disconnect with server exit | inspect server logs before treating it as a transport failure |

## Confirm the native build in use

In-process tests extract embedded libraries from resources, so rebuilding a target library alone may not change the
copy loaded by a test. Rebuild and update the packaged resource or use the CMake output directly in a standalone
reproducer. Compare hashes before debugging stale behavior:

```bash
md5sum cef4j-platform/target/cmake-build/libcef4j.so \
  cef4j-platform/target/reactor-runtime/native/linux64/libcef4j.so \
  /tmp/cef4j-cache/linux64/*/libcef4j.so
nm -D cef4j-platform/target/cmake-build/libcef4j.so | rg 'JNI_OnLoad|cef_api'
```

Paths and suffixes differ on macOS and Windows. The runtime-server executable is under
`cef4j-runtime-server/target/cmake-build/runtime-server/`; its CEF resources are staged in
`cef4j-platform/target/reactor-runtime/cef-runtime/<platform>/`.

## Standalone and native debuggers

Prefer a small Java main that obtains `Cef.osrLaunchArgs()`, supplies a fresh cache path, initializes CEF, reproduces
one operation, and terminates. Run it outside Surefire with the exact module classpath. Do not use an old reproducer's
initialization API without comparing it to the current samples.

AddressSanitizer build pattern on Linux:

```bash
cd cef4j-platform/target/cmake-build
cmake ../.. -DCMAKE_BUILD_TYPE=Debug \
  -DCMAKE_C_FLAGS='-fsanitize=address -fno-omit-frame-pointer' \
  -DCMAKE_CXX_FLAGS='-fsanitize=address -fno-omit-frame-pointer' \
  -DCMAKE_SHARED_LINKER_FLAGS='-fsanitize=address'
cmake --build . -j
```

Then preload the matching ASAN runtime when launching Java. For GDB:

```bash
gdb --args java -Dcef4j.disableStderrRedirect=true -cp '<classpath>' CrashTest
```

CEF and the JVM use signals internally. In GDB, it is often useful to pass these through until the actual crash:

```text
handle SIGSEGV nostop noprint pass
handle SIGUSR1 nostop noprint pass
run
bt
info registers
```

On macOS use LLDB; on Windows collect the runtime-server or JVM dump with WinDbg/Visual Studio. A runtime-server crash
is easier to diagnose than an in-process crash because the Java client and its logs survive.

## Known native ownership patterns

### Object-pointer parameters

CEF C API wrappers may create a `CefRefPtr` from an incoming raw pointer without incrementing it first. Generated JNI
must `add_ref` an `ObjectPtr` argument before passing it into a call that consumes the reference. A missing increment
can destroy the object during the call and crash at a later release.

### User-free strings

`JStringToCefString` allocates a `cef_string_userfree_t`. Store the result, pass it to CEF, and call
`cef_string_userfree_free()` afterward. Do not create it as an unowned inline temporary.

### String-collection writeback

Write native string lists/maps back into Java only for output parameters. Writing setter inputs back can duplicate
entries or throw when the caller supplied an immutable collection.

## Runtime-server startup and recovery

The first stdout protocol line must resemble:

```text
CEF4J_RUNTIME_SERVER protocol=1 api=remote-cef cef-api=15000 transport=zmq frame=shared-file endpoint=... capabilities=...
```

If startup fails:

- run the packaged executable directly with the same `--transport`, `--bind`, and `--frame-transport` arguments;
- ensure stdout has no banner or logging before the handshake;
- verify the handshake protocol/API and provider names match the launcher request;
- confirm the child environment points to the expected CEF libraries and resources;
- check the configured startup timeout and the server's stderr;
- use a fresh writable CEF cache/profile directory.

`RuntimeServerSupervisor` creates a new generation after a crash. Expected behavior is:

- the failed generation's `CefSession` closes;
- all pending futures fail promptly;
- old generated facades and native handles remain unusable;
- restart uses bounded exponential backoff;
- the application receives the new-generation callback and recreates browser state.

If old calls reach a restarted server, generation ownership has been bypassed and that is a correctness bug. Do not
work around it by replaying integer handles.

Shared-frame paths include server/browser generation identity. Windows can keep a mapped file locked after the server
exits; cleanup may be deferred, but the replacement generation must never reuse that path.

## IPC diagnosis

Start with in-memory `LoopbackTransport`, then the real provider. `RecordingTransport` and `ReplayTransport` can isolate
wire/correlation failures without CEF.

| Provider | Checks |
| --- | --- |
| `local` | endpoint scheme matches platform; Unix defaults to loopback ZMQ, Windows advertises a named pipe |
| `zmq` | endpoint is reachable and unique; no stale server owns the port |
| `uds` | junixsocket is present; socket path is short, writable, and removed after exit |
| `websocket` | `ws`/`wss` URI, bearer token agreement, TLS trust, binary-message framing, loopback policy |
| `inline` frames | negotiated message/frame limits can hold a complete BGRA snapshot |
| `shared-file` frames | file exists, header/sequence is current, map length and stride agree, path is generation-specific |

The fixed session envelope is 14 bytes, little-endian: payload length (4), kind (1), flags (1), correlation ID (4),
and message ID (4). A decode failure should log these fields before inspecting higher-level generated messages.

Transport reader callbacks must not block. Handlers should complete futures or enqueue work; synchronous intercepts
are the exception and must return promptly because the server is waiting.

## Frames and MJPEG

- Verify width, height, stride, pixel format, source sequence, key-frame flag, and base sequence together.
- A raw-frame callback owns its buffer only for the callback duration; copy it if it must outlive the call.
- A stateful decoder must reject a missing/broken base chain and request a key frame.
- Slow MJPEG clients receive the newest retained JPEG, not an unbounded queue of old frames.
- Non-loopback MJPEG requires explicit remote enablement, TLS, and bearer authentication.

When diagnosing recovery, attach the new raw source atomically and verify that a late MJPEG consumer receives a frame
from the new generation.

## WebDriver diagnosis

Use the lowest test rung that demonstrates the problem:

1. unit tests for capability merging, JSON/error mapping, and session state;
2. loopback HTTP with a fake `AutomationBackend`;
3. Selenium `RemoteWebDriver` against the real endpoint;
4. in-process CEF through `cef4j-inprocess-webdriver`;
5. packaged CEF through `cef4j-remote-webdriver`;
6. pinned WPT/wdspec cases.

The endpoint automates packaged CEF and does not use a system Chrome installation or ChromeDriver. A missing command
must return the W3C `unsupported operation` error; a silent success is a protocol defect. On runtime-server crash, fail
the active command and follow the WebDriver session policy tracked in [PLAN.md](PLAN.md).

## Platform notes

### Linux

Always isolate native/UI tests:

```bash
xvfb-run -a ./mvnw -pl '<modules>' -am test
```

cef4j's direct-launch distribution does not implement CEF's supported sandbox setup. Startup requires callers to set
`noSandbox=1` explicitly; cef4j does not inject sandbox-disabling command-line flags. Container/tool sandboxes can
still cause false negatives, so reproduce on the host's private Xvfb before changing code.

CI and release builds set `CMAKE_SYSROOT` to `sysroot/out/<arch>`. If CMake reports missing standard headers or links
against runner libraries, confirm the matching tarball was prepared with `sysroot/manage.sh prepare <arch>` and that
the configure log names `cmake/toolchains/linux-<arch>.cmake` plus a sysroot GCC install directory. Delete only the
affected module's `target/cmake-build` before reconfiguring; CMake does not change toolchains in an existing cache.

The cache key includes OS, runner architecture, target architecture, and `sysroot/Dockerfile` hash.

### macOS

The Java `main` thread is not necessarily OS Thread 0. cef4j runs initialization, the managed CEF message loop, and
shutdown on Thread 0. Do not pass `-XstartOnFirstThread`; it conflicts with JavaFX/AWT startup patterns and cef4j does
not require it.

CEF subprocess Mach-port rendezvous derives its service name from the bundle identifier. A bare helper executable and
the JVM bundle can otherwise disagree. `bundle_fix_mac.mm` makes both sides see the cef4j identifier. If subprocesses
start but cannot rendezvous, inspect that fix and the effective bundle identifiers before blaming transport IPC.

`multiThreadedMessageLoop=1` is invalid for this macOS embedding. Use the defaults returned by `Cef.osrLaunchArgs()`.
The subprocess `CefApp` must be heap allocated because CEF may release it after `cef_execute_process()` returns.

JavaFX/Swing tests need Window Server access. SSH-only sessions should skip UI tests rather than hang; use an attached
GUI agent for actual rendering coverage.

### Windows

The in-process CEF loop runs on a cef4j-managed daemon thread. The portable local runtime-server client uses standard
Java file I/O over `\\.\pipe\...` and does not load a JNI socket library. Verify pipe name agreement and access control
when launch succeeds but connection does not.

Headless SSH/RDP environments can deny DirectComposition or GPU access. Reproduce with software rendering and an
interactive desktop before classifying display-composition warnings as product failures.

## Warnings that may be environmental

Treat a warning as benign only when the relevant assertions, rendering, and shutdown all succeed. Examples seen in
headless/platform runs include Linux descriptor lookup messages, Windows DirectComposition access-denied messages,
macOS password-encryption/keychain availability messages, and newer-JDK native-access warnings. Capture them in a test
report; do not globally suppress a new warning until its source and Java 11 implications are understood.

## Cleanup after interrupted runs

First confirm no build or test process is active. Remove only narrowly identified stale artifacts: a dedicated test
profile/cache, an orphaned Unix socket, or generation-specific shared-frame files. In-process native cache removal is
recoverable because it is re-extracted on next use:

```bash
rm -rf /tmp/cef4j-cache
```

Do not recursively remove broad temp, workspace, or user-home paths. Preserve logs until the failure is understood.
