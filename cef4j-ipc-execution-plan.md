# cef4j IPC Execution Plan

Companion to `cef4j-ipc-architecture.md`. That doc is the *design*; this doc is the
*work breakdown* and the durable record of cross-session decisions. Read both before
starting any IPC work.

---

## What this is

A new, parallel backend for cef4j: out-of-process JVM ↔ native CEF helper over ZMQ
with mmap'd frame buffers. The existing in-process JNI stack (`cef4j-api`,
`cef4j-native`, `cef4j-osr-jfx`, etc.) stays put, untouched. The IPC backend's
selling point is **survives native crashes** — the JVM keeps running when CEF dies.

End-state goal: existing render/DOM/JS tests pass against either backend with no
behavioural delta, selected via a `BrowserBackend` SPI.

---

## Locked decisions (do not re-litigate)

Decided 2026-04-27. If you need to change one of these, write down *why* in this
file before touching code.

1. **Parallel, not replacement.** New modules under `cef4j-ipc-*`; in-process stack
   keeps shipping unchanged.
2. **JDK 11 baseline.** Matches the rest of the project. Architecture doc says
   "Java 8 target" — that's stale; ignore. No `record`, no pattern-matching
   `instanceof`, no switch expressions in this code (per project memory).
3. **Module naming**: `cef4j-ipc-transport`, `cef4j-ipc-session`, `cef4j-ipc-protocol`,
   `cef4j-ipc-helper-native`, `cef4j-ipc-frame-mmap`, `cef4j-ipc-jfx`. Six modules.
4. **No bleed into existing types.** IPC peers live in `net.kurobako.cef4j.ipc.*`,
   never extend or pollute the existing `CefXxx.NativePeer`. Codegen produces a new
   `IpcPeer` family from the same CEF AST.
5. **Test parameterisation = `BrowserBackend` SPI.** A small interface implemented
   by both backends; existing tests get a `@ParameterizedTest` source providing
   both. No conditional dispatch inside generated types.
6. **Codegen is owned and modifiable.** Extend `cef4j-codegen` (Scala 3) with new
   emitters; do not fork.
7. **Transport baseline is ZMQ PAIR over TCP** (jeromq Java + libzmq C++). UDS
   optimisation deferred (Open Issue #5). MPL-2.0 licensing of jeromq accepted.

---

## Module map

| Module                    | Lang   | Depends on                                      | Status   |
|---------------------------|--------|-------------------------------------------------|----------|
| `cef4j-ipc-transport`     | Java   | jeromq, slf4j-api                               | shipped  |
| `cef4j-ipc-session`       | Java   | `cef4j-ipc-transport`                           | shipped  |
| `cef4j-ipc-protocol`      | Java   | `cef4j-ipc-session` (generated)                 | shipped  |
| `cef4j-ipc-helper-native` | C++17  | libzmq, CEF                                     | shipped  |
| `cef4j-ipc-frame-mmap`    | Java   | `cef4j-ipc-session`, `cef4j-ipc-protocol`       | shipped  |
| `cef4j-ipc-jfx`           | Java   | `cef4j-ipc-frame-mmap`, OpenJFX                 | shipped (v0; resize + DnD pending) |
| `cef4j-test-shared`       | Java   | (test SPI)                                      | shipped  |

Codegen extensions live inside the existing `cef4j-codegen` module — no new Scala
module.

---

## Slice plan

Each slice is "one or more sessions, ends in a green build with new tests."
Slices are ordered by hard dependency; do not reorder without revisiting Decision 4
(no codegen until at least one hand-written round-trip exists to validate the wire).

### Slice A — Transport ✅ done

**Module:** `cef4j-ipc-transport` (new)
**Estimated:** 1 session

Deliverables:
- `CefTransport` interface (locked shape — see "Cross-slice contracts" below)
- `ZmqTransport` — PAIR over TCP, jeromq-backed
- `LoopbackTransport` — in-memory pair for unit tests, no jeromq involvement
- `RecordingTransport` decorator + `MessageLog` on-disk format
- `ReplayTransport` reading a `MessageLog`
- Disconnect detection + `onDisconnect` callback
- Tests: round-trip, large messages (>1MB), disconnect, recording/replay equivalence

Exit criteria:
- All tests green; no jeromq in the test classpath for `LoopbackTransport` tests
- Recording → Replay produces byte-identical receive sequences

### Slice B — Session layer ✅ done

**Module:** `cef4j-ipc-session` (new)
**Estimated:** 1–2 sessions

Pre-work: **resolve the envelope size discrepancy.** Decision 3 says "14 bytes fixed
header" but the diagram totals to 10 (`len:4 + type:1 + flags:1 + corrId:4`). Decide
final layout in this slice's first commit and update the architecture doc.

Deliverables:
- `Envelope` codec
- `CefSession` interface (locked shape — see contracts)
- `CefSessionImpl` — correlation map, handler registry, intercept context, single-thread
  outbound queue, timeout scheduler
- Tests via `LoopbackTransport` (fast unit) and `ReplayTransport` (deterministic
  fixtures)

Exit criteria:
- Request/response, event broadcast, intercept (allow/block/respond) all covered
- Replay test proves session is deterministic given a fixed `MessageLog`

### Slice C — First end-to-end round-trip ✅ done

**Modules:** `cef4j-ipc-helper-native` (new), small additions to `cef4j-ipc-session`
**Estimated:** 2–3 sessions

Hand-written codec for ONE message pair: `LoadURL` request + `OnLoadEnd` event.
Proves the wire end-to-end before pouring effort into codegen.

Deliverables:
- C++17 helper executable, CMake build, links libzmq + CEF (use the existing
  `.cef-dist` cache — see `cef4j-native` for the download pattern)
- Helper inits CEF, opens browser to `about:blank`, listens on PAIR socket
- Hand-written `LoadUrlEncoder`, `OnLoadEndDecoder` (Java side)
- Hand-written matching C++ overlay structs
- Helper-process lifecycle: `HelperProcess.spawn(...) / kill() / waitFor()`,
  detects crash via transport disconnect

Exit criteria:
- Integration test (own surefire fork, xvfb-wrapped on Linux): spawn helper, send
  LoadURL, receive OnLoadEnd within 10s
- Crash test: `kill -9` the helper; `CefSession.close()` completes; pending requests
  fail with a clear `CefTransportException`

### Slice D — Codegen extensions ✅ done (with ongoing extensions)

**Modules:** `cef4j-ipc-protocol` (new, generated), extend `cef4j-codegen`
**Estimated:** 3–4 sessions
**Current state:** ~2174 emitted messages, 91 facades, 44 handlers, 20 data structs against CEF 146.
Renderer-process codegen path lives alongside the browser one (`CppRendererDispatcherEmitter`); 109 of
116 V8/DOM methods reachable transparently from the JVM (the remaining 7 use StringList output params,
which the wire doesn't model yet — `get_keys` etc.). `ProcessAffinity` tagging on facades, frame field
prepended to renderer requests, and `RendererReleaseHandleRequest` thread the release path through the
relay so renderer-affinity facades' `releaseHandle()` actually drops the entry.

Replace the hand-written codec from Slice C with generated code. After this, adding
a new IPC message type is a matter of pointing the generator at the corresponding
CEF header section.

Deliverables:
- Layout-spec IR in the codegen (per Decision 10)
- Java emitters: flyweight view, encoder, handler, dispatch table
- C++ emitters: struct overlay, handler bridge
- First batch: cover the messages used by the existing render/DOM/JS tests
- Slice C's hand-written codec deleted in favour of generated equivalents

Exit criteria:
- Generated bindings pass the same integration tests as Slice C
- Re-generating from a different CEF version (e.g. flip `cef.version`) recompiles
  cleanly; semver-incompatible CEF changes break the build at compile time, not
  at runtime

### Slice E — Frame transport + JFX ✅ done

**Modules:** `cef4j-ipc-frame-mmap`, `cef4j-ipc-jfx` (both new)
**Estimated:** 4–5 sessions
**Current state:** `FrameTransport` interface + `MmapFrameTransport` reading the helper's POSIX shm
(`OsrPaintEvent`) into a read-only `ByteBuffer`. `IpcWebView` is a JFX `Region` that copies frames into
a `PixelBuffer<ByteBuffer>` + `WritableImage`, forwards JFX mouse + keyboard events through the codegen
`BrowserHost.sendMouse{Click,Move,Wheel}Event` / `sendKeyEvent` wires, and drives helper-side viewport
resize via `SetViewportSizeRequest` from `layoutChildren`. The codegen unlocked the `MouseEvent` /
`KeyEvent` data-struct overlays via `ByValueIn` reclassification + char16→I32 in `toDataFieldType`.

Out of Slice E scope (deferred — neither backend currently supports them):
- Drag-and-drop. The codegen exposes `BrowserHost.dragTarget*` / `dragSource*` but neither
  `CefWebView` nor `IpcWebView` wires JFX `DragEvent` through. `cef_drag_data_t::Create()` is a
  CEF top-level static, not on a struct, so adding it needs a hand-written request type. Pull
  forward into a Slice G if we ship a real DnD-needing app.
- IME. CEF's `imeSetComposition`/`imeCommitText`/`imeFinishComposingText`/`imeCancelComposition`
  are exposed via codegen; JFX `InputMethodRequests` plumbing is the missing piece on both backends.
- Clipboard. CEF doesn't surface clipboard read/write via `cef_browser_host_t`; integration would go
  through `cef_clipboard_t` (currently not codegen-walked) or platform clipboard. Both backends rely
  on whatever CEF defaults to.

Pre-work: **resolve Open Issues #2, #3, #4, #7** from the architecture doc. These
gate frame plumbing and threading.

Deliverables:
- `FrameTransport` interface
- `MmapFrameTransport` — double-buffered mmap, signal byte over control channel
- Helper-side: render to mmap segment, push signal byte
- JFX backend: facade shaped like `cef4j-osr-jfx` `CefWebView`/`CefWebEngine`
  (mirroring `javafx.scene.web.WebView` per project memory), but routed through
  IPC session + mmap frames
- Sample app loading a real URL

Exit criteria:
- Sample: open JFX window, load page, interactions work (click, scroll, type)
- Crash test: `kill -9` helper while page is loading; JFX app stays alive,
  surfaces the crash via callback, can re-spawn helper

### Slice F — Test parameterisation ✅ done

**Cross-cutting** — touches `cef4j-api` test sources, new `cef4j-test-shared`
or similar
**Estimated:** 2–3 sessions
**Current state:** `cef4j-test-shared` ships the `BrowserBackend` / `BrowserSession` SPI with
`ServiceLoader` discovery. Two impls registered:
- `IpcBrowserBackend` (in `cef4j-ipc-frame-mmap`'s test scope) — spawns helper + ZMQ + session.
- `NativeBrowserBackend` (in `cef4j-osr-jfx`'s test scope) — wraps `CefWebView` + JFX scene.

Both impls drive a smoke test (`BrowserBackendSmokeTest` / `NativeBackendSmokeTest`) covering paint
delivery + JS eval. Each module's smoke test passes locally.

`cef4j-test-matrix` is the meaningful matrix module: it pulls both backends in via test-jar deps so
`ServiceLoader.load(BrowserBackend.class)` finds both. Three test classes drive the matrix, each
parameterised on `backends()` (so each scenario runs once per backend, gated by
`Assumptions.assumeTrue(backend.isAvailable())`):
- `CrossBackendMatrixTest` — `discoverFindsBothBackends` SPI sanity check, plus `deliversPaintAndEvaluatesJs`
  asserting paint width and a JS eval round-trip.
- `NavigationMatrixTest.secondLoadReplacesDocument` — load A, load B, poll DOM until the new document is
  the one being queried. Catches the regression where navigation acks but never swaps the page.
- `JsTypeRoundTripMatrixTest.scalarsCoerceConsistently` — int/bool/double/string round-trip;
  documents the existing string-quoting divergence between backends so future narrowing is intentional.

Each test class is its own surefire fork (`reuseForks=false`) because per-backend session lifecycle
state (jeromq cumulative ZContext on IPC side, JFX Stage state on native side) doesn't survive a second
open/close in the same JVM. All seven test rows pass under `xvfb-run`.

Lessons baked into the matrix pom (don't undo without reading):
- Align JFX versions (matrix uses 13.0.2 throughout). A graphics:11 + base:13.0.2 mix SIGSEGVs in
  glassgtk3 `staticScreen_getScreens` → `XInternAtom`.
- `java.awt.headless=false` is required even under xvfb (surefire defaults to true; gtk3 then NPEs).
- One session per backend per JVM. Two sessions trip jeromq cumulative state on the IPC side and
  JFX Stage state on the native side. `reuseForks=false` only forks per test class, so collapse
  multi-step scenarios into a single `@ParameterizedTest` method.
- `BrowserBackend.discover()` returns ALL registered backends, not the available ones — empty
  `@MethodSource` lists fail JUnit's parameterised-test contract, and the right outcome on a
  developer box without a helper binary built is "skipped" via `Assumptions.assumeTrue` in the
  test method, not "failed configuration".

CI: `.github/ci/linux-build.sh` now installs `cmake gcc-c++ make` so the helper subprocess actually
builds inside the manylinux_2_28 container, and grep-prints per-row matrix results from the surefire
reports so an aborted-via-Assumptions row doesn't get swallowed by JUnit's "aborted-as-success" path.
The IPC modules are unconditionally in the parent pom's `<modules>` list, so `mvn verify` already
runs the matrix in every CI leg without a separate workflow step.

Not migrated (out of scope — they exercise JVM-side init paths that the IPC backend doesn't have):
- `CefWebViewRenderTest.constructorThrowsClearErrorWhenCefNotInitialised` — asserts a JVM-side
  `IllegalStateException` from `CefWebView`'s ctor when `CefGlobals.initialise` hasn't been called.
  IPC's `IpcWebView` doesn't require a JVM-side `Cef.initialise`, so the message has no equivalent.
- `CefWebViewSchemeTest.classpathUrl_loadsInCefWebView` — uses `CefGlobals.registerSchemeHandlerFactory`
  which runs in-process. Cross-backend would need a wire-level scheme-handler protocol; flag for
  whoever ports the next custom-scheme app.

Deliverables:
- `BrowserBackend` SPI in shared test scope
- `NativeBrowserBackend`, `IpcBrowserBackend` impls
- Refactor existing render/DOM/JS tests as `@ParameterizedTest` over both backends
- CI matrix runs both

Exit criteria:
- Existing tests (`CefDaemonRenderTest`, `CefBrowserPanelDomTest`,
  `CefScriptEngineTest`, etc.) pass against both backends
- Behavioural deltas, if any, documented in this file

---

## Cross-slice contracts

Lock these *now* so later slices can build on them without breakage. Changing one
of these is a planned migration, not a drive-by edit.

### `CefTransport` (Slice A)

```java
public interface CefTransport extends Closeable {
    void send(ByteBuffer frame) throws CefTransportException;
    void onReceive(Consumer<ByteBuffer> handler);   // single subscriber
    void onDisconnect(Runnable handler);            // single subscriber
    boolean isConnected();
}
```

- `send` is thread-safe (transport implementation handles serialisation)
- `onReceive` callback fires on the transport's I/O thread; consumer copies if
  retaining the buffer
- `onDisconnect` fires once per connection lifetime; transport instance is dead
  after firing — caller constructs a new one to reconnect

### `Envelope` (Slice B)

Header layout TBD in Slice B (see Slice B pre-work). Wire payload is opaque to
the transport.

### `CefSession` (Slice B)

Per Decision 4 in the architecture doc. Lock the exact signatures in the first
Slice B commit.

### `FrameTransport` (Slice E)

Per Decision 6 in the architecture doc. The `FrameConsumer` threading contract
needs an explicit decision (Open Issue #7) before locking.

---

## Open issues (deferred)

Tracked here so they don't get lost. From the architecture doc + things added
during planning:

1. **PAIR reconnection** (arch #1) — Slice C will have to take a position. Default
   plan: detect disconnect, restart helper, rebind. Upgrade to DEALER/ROUTER only
   if real reconnection requirements emerge.
2. ~~**mmap segment allocation handshake** (arch #2) — gates Slice E.~~ **Resolved 2026-04-30,
   tightened 2026-05-01**: helper allocates per-browser POSIX shm at first paint, sized to fit
   the actual paint plus a 256x256 floor. Name is `/cef4j-paint-PID-handleId-G` where G is a
   per-browser generation counter; the helper bumps G and rotates the shm whenever a paint
   exceeds capacity OR is more than 4x smaller than capacity (shrink-to-fit hysteresis). The
   `OsrPaintEvent` always carries the current name; the JVM-side `MmapFrameTransport` already
   handles name change by re-mmapping. Verified by `OsrPaintIntegrationTest` (which still
   asserts `shmName.startsWith("/cef4j-paint-")`) and `MmapFrameTransportIntegrationTest`.
3. ~~**Viewport resize protocol** (arch #3) — gates the polished Slice E.~~ **Resolved
   2026-05-01**: hand-written `SetViewportSizeRequest{browser, w, h}` in Specs (msgId 25);
   helper maintains a per-browser viewport map (keyed by `cef_browser_t::get_identifier()`,
   not pointer — CEF passes different shim instances to different callbacks) that
   `cef_render_handler_t::get_view_rect` reads. The handler also calls
   `notify_screen_info_changed` + `was_resized` + `invalidate(PET_VIEW)` to force CEF
   to re-query and repaint. shm sized for 4K up-front so resizes don't overflow.
   `IpcWebView.layoutChildren` fires the request when JFX dimensions change. Validated
   end-to-end by `ViewportResizeIntegrationTest`.
4. **Intercept backpressure** (arch #4) — Slice C / Slice E. Synchronous handler-return
   wire works (Kind::Intercept + InterceptRegistry); a pre-filter chain for fast paths
   would be useful but isn't urgent.
5. **Java 8 UDS** (arch #5) — moot; we're on JDK 11. UDS support will require
   JDK 16+ NIO or junixsocket. Defer past Slice F.
6. **V8 pool message frequency** (arch #6) — premature; revisit only if profiling
   demands.
7. ~~**`FrameConsumer` threading contract** (arch #7)~~ **Resolved 2026-05-01**: contract
   is "transport-owned thread, never the caller's; UI consumers marshal themselves". Locked in
   `FrameTransport` javadoc with explicit prohibition on transport-side `Platform.runLater`. Pixel
   buffer lifetime is "callback duration only; copy if retaining". `MmapFrameTransport` honors this
   by delivering on the IPC IO reader thread; `IpcWebView` honors it by copying then
   `Platform.runLater`. Enforced by `MmapFrameTransportIntegrationTest.onFrameDeliversBgraPixelsAndDirtyRect`
   which asserts the consumer thread is not the test's main thread — catches future regressions
   where someone helpfully marshals onto an "obvious" thread inside the transport.
8. ~~**Envelope size: 10 vs 14 bytes** (this doc) — Slice B blocker.~~
   **Resolved 2026-04-27**: 14 bytes, with `messageId` field that the original
   diagram missed. Layout in the architecture doc.
9. ~~**CMake integration of helper binary** — Slice C decision~~ **Resolved 2026-04-27**:
   stand up a separate `cef4j-ipc-helper-native/CMakeLists.txt`. Cef4j-native keeps its
   JNI focus, helper is its own CMake build.

10. **libzmq dependency strategy** **Resolved 2026-04-27**: CMake `FetchContent` to
    download + build libzmq during configure. Matches the existing self-contained
    "downloads what it needs" character of the build (mirrors the CEF dist cache). Adds
    a one-time configure-time penalty; consumers don't need libzmq3-dev installed.

11. **MessageId numbering** **Resolved 2026-04-27**: 0-99 reserved for hand-written /
    system messages (Slice C). Codegen-assigned IDs start at 100 (Slice D). Documented
    in `MessageIds`.

12. **Helper bootstrap protocol** **Resolved 2026-04-27**: helper executable accepts
    {@code --bind tcp://host:port} (port 0 → OS-assigned), binds the PAIR socket, then
    writes a single line {@code ENDPOINT=tcp://host:resolved-port\n} to stdout before
    going into its event loop. JVM {@code HelperProcess} reads that line to learn where
    to connect.

13. **jeromq cumulative ZContext NPE** **Resolved 2026-04-29**: under churn, jeromq's
    iothread races on `Ctx.sendCommand` and surefire forks can hang indefinitely. Two
    layers of mitigation: (a) `cef4j-ipc-transport` and `cef4j-ipc-frame-mmap` set
    `reuseForks=false` so each test class gets a fresh JVM; (b) `ZmqTransport.close()`
    runs the whole `monitor.close()`/`ctx.close()` shutdown on a daemon thread with a
    5s budget — if jeromq wedges we abandon the leaked context. Tests use
    `@Timeout(value=60, threadMode=SEPARATE_THREAD)` so a hang produces a clean failure
    instead of stalling the JVM.

14. **Multi-helper singleton lock** **Resolved 2026-04-30**: helper now sets
    `CefSettings.root_cache_path` to `/tmp/cef4j-helper-{pid}` (overridable via
    `CEF4J_HELPER_CACHE_DIR`). Without it, two helpers racing for browser creation
    deadlocked on CEF's default `~/.config/cef_user_data` lock and tests timed out
    waiting for `LifeSpanHandlerOnAfterCreatedEvent`.

15. **DataStruct overlay name resolution** **Resolved 2026-04-30**: `SpecDeriver.derive*`
    methods now take a `knownDataStructs: Set[String]` parameter (computed in
    `Main.scala` from `deriveDataStructs(...)` output). Methods whose params reference
    a struct the overlay deriver couldn't emit (Cookie — nested data structs;
    KeyEvent — was char16 fields, now supported) get skipped instead of producing
    broken `#include` lines in the dispatcher header. The same plumbing unlocked
    `cef_mouse_event_t` / `cef_key_event_t` for `BrowserHost.sendMouse*Event` /
    `sendKeyEvent` once `CType.ByValueIn` was wired through `toFieldType`.

---

## How to resume cold (future-self instructions)

1. Read `cef4j-ipc-architecture.md` for design context.
2. Read this file for execution context.
3. Check `git log --oneline -- 'cef4j-ipc-*'` for what's actually shipped.
4. Find the lowest-numbered Slice with status ≠ "done"; that's where you are.
5. Re-read the locked decisions section. If you're tempted to violate one, write
   down the reason in this file's history first.
6. Test invocation patterns:
   - Pure-Java unit tests: `./mvnw -pl cef4j-ipc-<module> test`
   - Tests touching CEF: `xvfb-run --auto-servernum ./mvnw -pl cef4j-ipc-<module> test`
     (Linux only; macOS/Windows don't need the wrapper)
7. Spotless will rewrite formatting on first build — don't fight it.
8. NullAway covers test sources; fix tests, don't suppress.
9. Don't run the sample app — only compile (project memory).

---

## Slice status

Update this section as work lands.

- Slice A — Transport: **done** (2026-04-27). 27 tests, contract green for Loopback + Zmq, recording/replay byte-identical. Module: `cef4j-ipc-transport`.
- Slice B — Session: **done** (2026-04-27). 20 tests. Envelope codec (14-byte header decision recorded), CefSession + CefSessionImpl (correlation, event broadcast, intercept with default-allow), session-via-replay determinism test. Module: `cef4j-ipc-session`. ReplayTransport changed to two-phase (`onReceive` registers, `start()` drains) to make session-level replay race-free; Slice A's RecordingReplayTest updated accordingly.
- Slice C — First round-trip: **done on Linux** (2026-04-27).
  - JVM-side: hand-written wire codec for `LOAD_URL`+`OnLoadEnd` (`MessageIds` reserves 0-99, codegen base = 100), `HelperProcess` lifecycle (spawn → bootstrap → close).
  - Native side: `cef4j-ipc-helper-native/` module with CMake + libzmq via FetchContent (~40s first build, ~1s incremental). C++17 helper binary `cef4j-helper` that uses CEF's C API directly (no `libcef_dll_wrapper`), inits in multi-process mode, hosts an `about:blank` browser in windowless rendering, owns a single PAIR socket via `IpcServer`. Same binary handles renderer/GPU/utility subprocesses through `cef_execute_process` at entry.
  - Tests (58 IPC total): `RealHelperIntegrationTest` spawns the real helper, navigates to a `com.sun.net.httpserver` fixture, asserts `OnLoadEnd` arrives over the wire with status 200. Crash test: `kill -9` the helper while a request is outstanding — pending future fails with `CefTransportException`. `xvfb-run` required on Linux because CEF's GPU process touches X11 even in windowless mode.
  - Bootstrap protocol confirmed: helper takes `--bind tcp://host:port`, prints `ENDPOINT=<resolved>` to stdout. Env vars `CEF_RESOURCES_DIR` (pak files) and `LD_LIBRARY_PATH` (libcef.so) needed.
  - Side find: ZMQ inproc wake sockets needed `setLinger(0)` to make `ZContext.close()` non-blocking; otherwise `sendAfterCloseThrows` flaked.
  - Side find: CEF's CToCpp wrappers reject calls until `cef_api_hash(CEF_API_VERSION, 0)` is called once at process start — must be the first CEF API call.
  - Side find: `cef_window_info_t` requires `size = sizeof(...)` (it's the first field). Forgetting silently makes `cef_browser_host_create_browser` return 0 with no error message.
  - **Mac / Windows pending**: Mac needs framework dynamic loading via `libcef_dll_dylib.cc` and bundle ID fix (`bundle_fix_mac.mm` from cef4j-native). Windows needs `WinMain` entry + `.lib` link. Defer until JFX integration in Slice E surfaces a real cross-platform need.
- Slice D — Codegen: **done** (2026-04-27).
  - Layout-spec IR (`MessageSpec` / `FieldSpec` / `FieldType`) under `cef4j-codegen.ipc`.
  - **Java emitter**: produces `CefMessageView` + `CefMessageEncoder` + static `DECODER` lambda. In-memory roundtrip test via JDK's `JavaCompiler` covers all field types (I32, I64, Bool, Utf8String, Bytes), fixed-size and mixed.
  - **C++ emitter**: produces a self-contained header per spec with struct + `encodedSize` / `encodeInto` / `decode`. Spec-side test optionally invokes a system `g++`/`clang++` to compile + run an encode/decode roundtrip — when a compiler is on PATH, the emitted code is verified to compile-and-execute correctly.
  - **Single source of truth**: same `Specs.scala` drives both Java emit (in `cef4j-ipc-protocol`'s generate-sources) and C++ emit (in `cef4j-ipc-helper-native`'s generate-sources). Adding a message means one Scala entry; both sides regenerate.
  - **Slice C replacement**: hand-written `LoadUrlRequest` / `LoadUrlResponse` / `OnLoadEndEvent` / `MessageIds` in `cef4j-ipc-session/protocol/` deleted; codegen now produces these at the same wire IDs. Slice C tests (HelperProcessTest, RealHelperIntegrationTest, CodecTest, StubHelperMain) migrated from `cef4j-ipc-session/test` to `cef4j-ipc-protocol/test` (the natural place once their types live in the protocol module). Module reactor order updated so `cef4j-ipc-helper-native` builds before `cef4j-ipc-protocol` — protocol's tests need the helper binary, helper-native needs the codegen output. Both work.
  - **C++ helper integration**: `main.cpp` now calls `LoadUrlRequest::decode` and `OnLoadEndEvent::encodeInto` from the generated headers instead of inline byte-shoveling. The real-CEF integration test still passes (helper navigates a real Chromium browser to the local HttpServer, OnLoadEnd fires back via generated wire codec).
  - Final IPC scoreboard: 76 codegen + 27 transport + 20 session + 12 protocol = 135 tests green.
  - **AST-driven derivation now works**: `SpecDeriver` walks the existing `cef4j-codegen` parsed `CefDecl` tree and produces `MessageSpec` instances mechanically — `HandlerStruct` methods → events, `ObjectStruct` methods → requests. Type mapping: `CType.Int/Long/Bool/JString` → matching `FieldType`, every other `CType` (object pointers, callbacks, complex types) makes the whole method ineligible. IDs hash-derived in `[10000, Int.MaxValue)` to stay clear of hand-written. Dedupe by class name to handle CEF's version-gated re-declarations. `Main` accepts `--cef-include` to opt in. Against CEF 146 the pipe yields 773 unique specs (12,553 raw → 773 after dedup). Integration test gated on `cef.root` system property (run via `mvn verify`) confirms every derived spec emits valid Java + C++ and all ids stay above `AstIdBase`.
  - **`FieldType.RemoteHandle` landed (2026-04-27)**: object pointers (`cef_browser_t*`, `cef_frame_t*`, etc.) now wire-encode as int32 handle ids. JVM-side wrapper is `net.kurobako.cef4j.ipc.session.RemoteHandle` (final class with id/equals/hashCode). C++ side encodes as raw `int32_t`; the helper resolves it via a handle table to the live ref-counted ptr (table itself is the next session's chunk).
    - `SpecDeriver` now maps `ObjectPtr`/`OutObjectPtr`/`Ptr(cef_*_t)`/`ConstDataStructPtr` → `RemoteHandle`. Method-on-object specs get an explicit `self: RemoteHandle` first field; events on handlers don't (routing is by messageId).
    - Real CEF 146: 975 unique specs after dedupe (up from 773 before `RemoteHandle`). `LoadHandlerOnLoadEnd`, `BrowserGoBack`, `FrameLoadUrl`, etc. all derive cleanly with proper `RemoteHandle` fields.
  - Side find: lambdas-as-DECODER are not reflectively callable across modules; tests must invoke through the declared interface.
  - Side find: Scala 3's `val imports = imports(spec)` is read as recursive — name locals away from methods.
  - Side find: `LoadUrlResponse.INSTANCE` singleton from the hand-written codec became a per-call `new LoadUrlResponse()` in the generated equivalent. Tests asserting `isSameAs(INSTANCE)` had to be relaxed to `isNotNull()`.
- Slice E — Frames + JFX: **done** (mmap transport + IpcWebView shipping; drag/IME/clipboard deferred — neither backend has them)
- Slice F — Test parameterisation: **done** (3 cross-backend test classes, 7 rows; CI builds helper + runs matrix via `mvn verify`)

**Slice D follow-up chunks** (sequenced for future sessions; each is well-defined):
- ~~**Helper-side `HandleTable<T>`**~~ **landed 2026-04-27**: header-only template `HandleTable.h` in `cef4j-ipc-helper-native`. Monotonic ids → ref-counted CEF struct ptrs, retain on insert, mutex-guarded for concurrent UI-thread / IPC-worker access. Used by `OnAfterCreated` to allocate browser handles, by `onIpcFrame` to resolve incoming requests.
- **Helper-side dispatch table**: messageId → handler function that decodes a generated request type, resolves handles, calls into CEF, encodes the response. Currently hand-written switch in `onIpcFrame`; extending `CppEmitter` to auto-generate it is the next chunk. End-to-end remote-method invocation proven for `BrowserCanGoBack` (typed-bool response) and `BrowserGoBack` (void ack) — see `RemoteHandleIntegrationTest`.
- ~~**`MessageSpec` returnType**~~ **landed 2026-04-27**: `SpecDeriver` now emits paired `XxxRequest` + `XxxResponse` for every `ObjectStruct` method (sharing `messageId`, correlated by `corrId`). Void returns produce an empty Response; non-void primitive returns produce a Response with a `result` field. Methods with unsupported return types are dropped wholesale. Against CEF 146 the pipe yields **1590 unique specs after dedupe** (was 975 with single message per method). Hand-written specs win on name collisions, so `BrowserCanGoBackRequest`/`Response` keep their hand-allocated id=5.
- **Out-params**: CEF C API often returns values via `int*` / `cef_string_t*` out parameters. These belong in the response, not the request.
- **JVM-side facade classes**: per `ObjectStruct`, a Java class that wraps a `RemoteHandle` and exposes typed methods (`browser.canGoBack()`, `browser.loadUrl(url)`). Method body bundles args + handle into the matching generated request, calls `session.request`, decodes the response.
- **JVM-side handler interfaces**: per `HandlerStruct`, a Java interface (e.g. `CefLoadHandler`) plus a registration helper that wires generated event decoders to user code via `session.on(messageId, ...)`.
- **Refcounting across IPC**: every `RemoteHandle` materialised on the JVM side holds an implicit retain on the helper. JVM `RemoteHandle.close()`/finalize sends a `Release(handle)` message; helper drops its retain. Without this, every navigation leaks browser objects in the helper.
- **`cef_*_t*`-returning factory functions** (`cef_browser_host_create_browser` etc.): generated as static methods that take a session, send a request, return a fresh `RemoteHandle` from the response.
- **Enum field type**: emit Java enum + C++ enum-class side-by-side, agreeing on numeric values from `CefDecl.Enum` declarations.
- **By-value data struct field type**: `CefRect`, `CefSettings`, etc. — encode field-by-field rather than as opaque handle.

Each chunk is a focused session. Order matters: `HandleTable` and `returnType` block facade classes; refcounting can be retrofit but should land before any production use.
