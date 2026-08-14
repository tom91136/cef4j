# cef4j

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![CI](https://github.com/tom91136/cef4j/actions/workflows/main.yaml/badge.svg)](https://github.com/tom91136/cef4j/actions/workflows/main.yaml)

Code-generated, off-screen-rendering Java bindings for the
[Chromium Embedded Framework](https://bitbucket.org/chromiumembedded/cef).

> **Project status:** active pre-release development. The public repository and CI matrix are being exercised before
> the first Maven Central release; APIs and artifact coordinates may still change until then.

**Runtime baseline: Java 11.** Raising it is an explicit project decision, not an incidental consequence of a dependency
upgrade. Build-only tooling may use a newer JDK when it does not enter distributable artifacts.

cef4j supports two ways to host CEF:

- **In process:** JNI bindings with JavaFX (`CefWebView`) and Swing (`CefBrowserPanel`) OSR surfaces.
- **Runtime server:** a packaged native `cef4j-runtime-server` owns CEF outside the JVM and exposes a generated Remote
  CEF API. This isolates native crashes, supports restart/recovery, and supplies a portable WebDriver endpoint without
  requiring an installed Chrome or ChromeDriver.

The project is OSR-only: the host toolkit owns the window and cef4j supplies rendered pixel buffers. CEF's renderer,
GPU, and utility subprocesses still exist as part of Chromium's normal multiprocess model.

## Highlights

- JavaFX and Swing OSR views
- generated, versioned Java/JNI and Remote CEF bindings
- Linux, Windows, and macOS native distributions for both x86-64 and ARM64
- CEF 109 through 146 compatibility lanes
- restartable native runtime server with a native-free Java client path
- transport-neutral IPC over local, ZMQ, Unix-domain socket, or WebSocket providers
- independent inline or shared-file frame delivery
- raw BGRA and JPEG codecs, a pluggable stateful codec SPI, and an MJPEG HTTP stream
- generated, version-aligned Chrome DevTools Protocol API over either hosting mode
- an embeddable W3C WebDriver Classic remote end tested with Selenium

## In-process embedding

For Maven, use the JavaFX artifact:

```xml
<dependency>
  <groupId>net.kurobako.cef4j</groupId>
  <artifactId>cef4j-inprocess-jfx</artifactId>
  <version>146.0.0</version>
</dependency>
```

Or the Swing artifact:

```xml
<dependency>
  <groupId>net.kurobako.cef4j</groupId>
  <artifactId>cef4j-inprocess-swing</artifactId>
  <version>146.0.0</version>
</dependency>
```

Both pull in the platform-neutral `cef4j-api`. Add one matching OS-family classifier from Maven Central to
supply JNI, the subprocess launcher, and CEF:

```xml
<dependency>
  <groupId>net.kurobako.cef4j</groupId>
  <artifactId>cef4j-platform-linux</artifactId>
  <version>146.0.0</version>
  <classifier>x86_64</classifier>
</dependency>
```

The coordinates are `cef4j-platform-{linux,windows,macos}` with `x86_64` or `arm64` classifiers. Use
`cef4j-platform-all` instead when one distribution intentionally supports every platform; selecting one classifier
avoids downloading, caching, and packaging the other CEF runtimes.
The artifact names describe the hosting mode; existing `net.kurobako.cef4j.osr.*` Java packages remain unchanged for
source compatibility.

JavaFX initialization and use:

Call this from `Application.start(...)` after JavaFX has initialized its platform integration. This ordering is
required on macOS and is portable to Linux and Windows.

```java
CefSettings.Mutable settings = new CefSettings.Mutable();
settings.cachePath = Files.createTempDirectory("cef4j-").toAbsolutePath().toString();
CefWebView.initialise(settings, List.of(), null);

CefWebView view = new CefWebView();
view.engine().load("https://example.com");

Stage stage = new Stage();
stage.setScene(new Scene(new StackPane(view), 1280, 800));
stage.show();
```

For direct lifecycle control, start from the platform-correct OSR defaults:

```java
Cef.LaunchArgs launch = Cef.osrLaunchArgs();
launch.settings().cachePath = Files.createTempDirectory("cef4j-").toString();
Cef.INSTANCE.initialise(launch.settings(), launch.args());
```

Register custom `CefApp` handlers with `Cef.INSTANCE.addAppHandler(...)` before initialization. CEF can be initialized
only once in a JVM; call `Cef.INSTANCE.terminate()` during orderly shutdown.

Runnable JavaFX, Swing, and CEF Views examples live in
[`cef4j-sample`](cef4j-sample/src/main/java/net/kurobako/cef4j/sample).

## Runtime-server architecture

```text
JavaFX / Swing / WebDriver / another client
                    |
        generated Remote CEF API + CDP
                    |
                 CefSession
                    |
       local | zmq | uds | websocket
                    |
          cef4j-runtime-server
                    |
                   CEF
```

The runtime server is CEF-aware but frontend-neutral. It owns native CEF objects, threads, callbacks, OSR painting,
DevTools observers, and generated request dispatch. JavaFX, Swing, WebDriver, and application policy stay on the Java
side. Server-side native objects cross the wire as typed handles tied to one `CefSession`.

`RuntimeServerProcess` launches one version-matched server. Its bootstrap line reports the protocol version, generated
CEF API version, control transport, frame provider, endpoint, and capabilities before a session is constructed.
`RuntimeServerSupervisor` treats every successful start as a new generation. If the server crashes, pending calls fail,
the old session closes, and the supervisor restarts with bounded backoff. Old facades can never send stale handles into
the replacement process; applications recreate browser state from the new-generation callback.

Portable client configurations load no cef4j JNI library:

| Provider | Intended use | Java-side implementation |
| --- | --- | --- |
| `local` | platform default | loopback JeroMQ on Unix; standard Java file I/O over named pipes on Windows |
| `zmq` | cross-platform local/compatible control | pure-Java JeroMQ |
| `uds` | explicit Unix local optimization | optional junixsocket provider, needed while Java 11 is the floor |
| `websocket` | remote/interoperable control | Java 11 `HttpClient`, bearer token and caller-supplied TLS trust |
| `inline` | portable complete BGRA frames | ordinary IPC messages |
| `shared-file` | fast local complete frames | ordinary files mapped with `FileChannel.map` |

Control and frame providers are negotiated independently. Frame consumers receive callback-scoped raw BGRA frames
with stride and source sequence before compression. Codecs are per-stream `ServiceLoader` providers, allowing custom
stateful delta codecs and key-frame recovery. Built-ins are raw BGRA and independently decodable JPEG;
`MjpegHttpServer` exposes JPEG frames as Chrome-compatible `multipart/x-mixed-replace`.

The runtime server is assembled at:

```text
cef4j-runtime-server/target/cmake-build/runtime-server/
```

That directory is the launchable unit and contains the server executable plus its matching CEF libraries and
resources.
On macOS, launch `cef4j-runtime-server.app/Contents/MacOS/cef4j-runtime-server`; CEF requires that application-bundle
identity, so the distribution intentionally omits a bare top-level executable.

### API middleware, recording, and replay

`CefSessionMiddleware` decorates the generated Remote CEF API above the control transport. Middleware can therefore be
composed once and used unchanged with ZMQ, WebSocket, UDS, named pipes, or a future transport. `RecordingCefSession`
writes a versioned `.cef4japi.jsonl` NDJSON trace containing logical requests/responses, failures,
subscription-specific events, intercepts, ordering, and relative timing—without transport envelopes or correlation
IDs. Each complete line is independently recoverable after a crash; opaque protocol payloads use standard Base64.
The bundled Draft 2020-12 schema is available at
`net/kurobako/cef4j/ipc/session/middleware/cef4j-session-api.schema.json` on the classpath.

For one session:

```java
Path trace = Path.of("failure.cef4japi.jsonl");
CefSession recorded = RecordingCefSession.toFile(realSession, trace);
```

The runtime factory accepts the same middleware as its final constructor argument. A restartable supervisor can record
each server generation separately:

```java
RuntimeServerSupervisor.Configuration configuration =
    RuntimeServerSupervisor.Configuration.defaults(serverBinary)
        .withSessionMiddleware(RecordingCefSession.rotatingMiddleware(
            generation -> traceDirectory.resolve("generation-" + generation + ".cef4japi.jsonl")));
```

Reproduction needs no runtime server, CEF distribution, native library, or control transport:

```java
ReplayCefSession replay = ReplayCefSession.fromFile(trace, ReplayMode.IMMEDIATE);
// Register the same event/intercept handlers and construct the usual generated facades.
replay.start();
// Run the recorded API flow, then:
replay.close();
replay.verifyComplete();
```

Replay is strict: message IDs, payloads, registration order, intercept replies, and concurrent completion order must
match. Divergence reports the trace entry and first differing byte. `TIMED` preserves recorded delays; `MANUAL` exposes
one-delivery-at-a-time debugging through `advance()`. A transport-level `RecordingTransport` remains available for raw
wire diagnosis; API traces are the portable reproducer format.

Add either `cef4j-remote-recording-gson` or `cef4j-remote-recording-jackson` to install the corresponding canonical
NDJSON codec through `ServiceLoader`; neither library appears in the public contract. Supply any `SessionTraceCodec` implementation to `RecordingCefSession.toFile(...)`,
`RecordingCefSession.middleware(...)`, `RecordingCefSession.rotatingMiddleware(...)`, or
`ReplayCefSession.fromFile(...)` to use Gson, JSON-P, another encoding, or custom storage. The codec SPI deals only in
JDK streams, metadata, `SessionTrace.Entry`, and `SessionTrace.Recording`. Overloads that accept a codec are fully
explicit; convenience overloads use the first installed provider.

If both adapters are present, inject `GsonNdjsonSessionTraceCodec.INSTANCE` or
`JacksonNdjsonSessionTraceCodec.INSTANCE`
explicitly; `ServiceLoader` ordering is intentionally not treated as configuration.

API traces can contain URLs, cookies, headers, JavaScript, form values, and credentials. Treat them as sensitive.
`TracePayloadFilter` supports diagnostic redaction, but a transformed trace remains replayable only when the filter
preserves the payload shape and values required by decoders and strict matching. Frames are deliberately excluded from
API traces; frame recording belongs at the independent frame-transport boundary.

### Remote security boundary

Local endpoints bind locally by default. The native WebSocket listener is loopback-only unless remote exposure is
explicitly enabled and supports bearer authentication. For WAN use, terminate TLS and authentication in a reverse
proxy or tunnel, connect with `wss://`, and enforce origin, message/frame-size, rate, and capability limits. The Remote
CEF API conveys broad browser authority and must not be exposed directly to an untrusted network. Exposing only an
authenticated WebDriver gateway is the narrower deployment boundary.

## WebDriver

`cef4j-webdriver` contains the embeddable W3C endpoint, shared CDP command engine, and JSON-neutral value/codec SPI.
Add either `cef4j-webdriver-gson` or `cef4j-webdriver-jackson`; the in-process and remote lifecycle modules themselves
depend on neither JSON library.
`cef4j-inprocess-webdriver` connects it to a JVM-owned `CefBrowser`; `cef4j-remote-webdriver` connects it to any
transport-neutral `RemoteBrowserRuntimeFactory`. Selenium is a test-only dependency: users point an ordinary
`RemoteWebDriver` at cef4j's endpoint, with no system browser or ChromeDriver discovery. Start the matching adapter
with `InProcessWebDriverServer.start(runtimeFactory)` or `RemoteWebDriverServer.start(runtimeFactory)`.
When both codecs are installed, use the overload accepting a `WebDriverJsonCodec` to select one explicitly.

The current vertical slice covers session lifecycle, navigation, URL/title/source, synchronous script, screenshots,
element lookup/state/staleness, native click, clear/send keys, cookies, refresh, and timeout configuration. Unsupported
commands return standard WebDriver errors. Remaining conformance work is tracked in [PLAN.md](PLAN.md).

## Chrome DevTools Protocol

`cef4j-cdp` provides a generated Java 11 API for the exact Chromium version in `cef.version`, plus the raw
`CdpTransport` escape hatch. Public models use ordinary immutable Java objects, collections, fluent accessors, builders, and
`CompletionStage`, with no JSON-library types; they are directly usable from Java, Scala 3, and other JVM languages.
`CdpCodec` lets applications choose their JSON library. The core artifact has no JSON-library dependency;
`cef4j-cdp-gson` and `cef4j-cdp-jackson` provide the optional `GsonCdpCodec` and `JacksonCdpCodec` adapters.
Applications select the codec explicitly when constructing `CdpClient`, so both adapters may safely coexist.

Use `ipc.devtools.gson.DevToolsSession` from `cef4j-remote-cdp-gson` or
`ipc.devtools.jackson.DevToolsSession` from `cef4j-remote-cdp-jackson` as the corresponding remote transport, or
`InProcessDevToolsSession.attach(browser)` for an existing in-process
browser. Neither path needs a remote-debugging port or system Chrome. `CdpSchema.requireExactVersion(client)` performs
an optional check through the already-attached channel; it never launches CEF. Raw commands remain available through
`client.raw()`.

```java
CdpClient cdp = new CdpClient(devToolsSession, new GsonCdpCodec()); // from cef4j-cdp-gson
Runtime.EvaluateResult evaluation = cdp.domains().runtime()
    .evaluate(Runtime.EvaluateParams.builder()
        .expression("document.title")
        .returnByValue(true)
        .build())
    .toCompletableFuture().join();
```

The full Chromium/V8 schema, its SHA-256 fingerprint, and generated sources are checked in, so builds are offline.
Maintainers run `scripts/update-cdp-schema.sh` when changing CEF; the updater derives Chromium and V8 revisions from
the pinned sources and does not launch CEF.

## Which module should I use?

| Module | Use it when you want… |
| --- | --- |
| `cef4j-inprocess-jfx` | in-process CEF in JavaFX (`CefWebView`) |
| `cef4j-inprocess-swing` | in-process CEF in Swing |
| `cef4j-remote-jfx` | crash-isolated CEF in JavaFX (`RemoteWebView`) |
| `cef4j-remote-swing` | crash-isolated CEF in Swing (`RemoteBrowserPanel`) |
| `cef4j-inprocess-webdriver` | Selenium against CEF hosted in your JVM |
| `cef4j-remote-webdriver` | portable Selenium target backed by the runtime server |
| `cef4j-remote-api` | headless generated Remote CEF control API |
| `cef4j-cdp` | typed, version-aligned CDP over any transport |
| `cef4j-cdp-gson` | Gson codec for `cef4j-cdp` |
| `cef4j-cdp-jackson` | Jackson codec for `cef4j-cdp` |
| `cef4j-remote-cdp-gson` | Gson-backed CDP session over Remote CEF |
| `cef4j-remote-cdp-jackson` | Jackson-backed CDP session over Remote CEF |
| `cef4j-remote-recording-gson` | Gson crash-tolerant NDJSON recording codec |
| `cef4j-remote-recording-jackson` | Jackson crash-tolerant NDJSON recording codec |
| `cef4j-remote-frame` | remote frames, custom codecs, MJPEG, or shared-file rendering |
| `cef4j-webdriver` | codec-neutral browser/CDP contracts for custom automation |
| `cef4j-webdriver-gson` / `cef4j-webdriver-jackson` | selectable WebDriver JSON codec |
| `cef4j-remote-core` | custom transports, sessions, or runtime-server supervision |
| `cef4j-api` | the low-level in-process CEF API without a UI toolkit |
| `cef4j-http` | CEF URL/stream handling utilities |
| `cef4j-platform-{linux,windows,macos}` classifier | one platform's in-process native runtime |
| `cef4j-platform-all` | every in-process native runtime in one portable distribution |
| `cef4j-runtime-server-{linux,windows,macos}` classifier | one platform's self-contained remote runtime ZIP |
| `cef4j-runtime-server-all` | every remote runtime ZIP in one portable distribution |

Remote clients also deploy and unpack the matching classified `cef4j-runtime-server-<os>` ZIP; in-process clients deploy the
matching classified `cef4j-platform-<os>` JAR. The `*-all` convenience POMs pull every classifier when portability matters
more than download and cache size.

## Repository modules

| Module | Responsibility |
| --- | --- |
| `cef4j-codegen` | Scala generators for JNI and Remote CEF bindings |
| `cef4j-native`, `cef4j-platform`, `cef4j-platform-all`, `cef4j-api` | in-process native build, platform bootstrap, optional all-platform aggregation, and Java API |
| `cef4j-inprocess-jfx`, `cef4j-inprocess-swing` | toolkit-specific in-process OSR surfaces |
| `cef4j-remote-core` | transports, envelopes, correlation, callbacks, process launch, and restart supervision |
| `cef4j-runtime-server`, `cef4j-runtime-server-all` | packaged native CEF runtime server and optional all-platform aggregation |
| `cef4j-remote-api` | generated Remote CEF facades and browser-runtime abstraction |
| `cef4j-cdp` | checked-in CDP schema, generated domain API, codec SPI, and raw transport contract |
| `cef4j-cdp-gson`, `cef4j-cdp-jackson` | optional Gson/Jackson implementations of the CDP codec SPI |
| `cef4j-remote-cdp-gson`, `cef4j-remote-cdp-jackson` | Gson/Jackson remote CDP correlation and event adapters |
| `cef4j-remote-recording-gson`, `cef4j-remote-recording-jackson` | interchangeable canonical NDJSON recording codecs |
| `cef4j-remote-frame` | frame transport SPI, codecs, shared files, inline frames, and MJPEG |
| `cef4j-remote-jfx`, `cef4j-remote-swing` | optional toolkit-specific remote frame consumers |
| `cef4j-webdriver`, `cef4j-webdriver-gson`, `cef4j-webdriver-jackson` | JSON-neutral W3C implementation plus selectable codecs |
| `cef4j-inprocess-webdriver`, `cef4j-remote-webdriver` | lifecycle adapters for in-process and Remote CEF |
| `cef4j-integration-tests` | packaged-runtime, provider, Selenium, and cross-backend verification |
| `cef4j-http`, `cef4j-test-shared`, `cef4j-sample` | URL/stream utility, shared test SPI, and examples |

Provider implementations are consolidated into their SPI modules and selected at runtime through `ServiceLoader`.

### API naming

cef4j-owned values and builders use fluent pairs such as `field()` and `field(value)`. Exact upstream operations keep
their protocol names (`Runtime.getProperties`, CEF `getViewRect`), and JavaFX/JDK overrides retain required bean names;
JavaFX-facing classes also expose fluent aliases. Generated CDP models preserve pinned PDL documentation, stability
markers, deprecations, and source links. Generated Remote CEF facades reuse the in-process CEF comment normalizer.
JavaFX remains separate so it cannot leak into the Remote CEF, WebDriver, or portable-client dependency graphs.

## Build and test

Building requires JDK 17+, while published Java bytecode and runtime dependencies retain the Java 11 floor. You also
need a C++ toolchain (GCC/clang on Linux, clang on macOS, or MSVC on Windows). The build downloads CEF minimal
distributions into `.cef-dist/` when needed.

```bash
./mvnw clean package
```

Windows:

```bat
mvnw.cmd clean package
```

Linux GUI/native tests **must** run in a private X server so they cannot interact with the desktop session:

```bash
xvfb-run -a ./mvnw clean verify -Dspotless.skip=true
```

The reusable browser contract exercises the same navigation, JavaScript value conversion, DOM, real-paint, and
viewport-resize scenarios against in-process JavaFX, in-process Swing, raw Remote CEF, `RemoteWebView`, and
`RemoteBrowserPanel`. Linux CI runs those surfaces under Xvfb. Windows and macOS CI run them only inside the
disposable GitHub-hosted VM's private WindowStation or WindowServer session; the project does not launch GUI tests
over SSH on an interactive machine.

To use another CEF line:

```bash
xvfb-run -a ./mvnw clean verify \
  -Dcef.version=144.0.19+g937f5c6+chromium-144.0.7559.246 \
  -Dcef.api.version=144 \
  -Dcef.dist.cache=.cef-dist-144
```

The non-default cache must contain the required platform distributions because code generation walks public headers
from each platform variant.

### Compatibility lanes

These are the maintained end-to-end verification representatives:

| CEF API | CEF version | Role |
| --- | --- | --- |
| 146 | `146.0.9+g3ca6a87+chromium-146.0.7680.165` | default edge |
| 144 | `144.0.19+g937f5c6+chromium-144.0.7559.246` | newest non-default stable lane |
| 116 | `116.0.27+gd8c85ac+chromium-116.0.5845.190` | long-tail API lane |
| 109 | `109.1.18+gf1c41e4+chromium-109.0.5414.120` | compatibility floor |

Runtime dependencies and generated output are checked for Java 11 bytecode. See [DEBUG.md](DEBUG.md) for native,
platform, IPC, and test troubleshooting.

### Continuous integration

Pushes and pull requests run a curated matrix rather than the full Cartesian product:

- Every Linux, Windows, and macOS x86-64/ARM64 target covers CEF 109/116/144/146.
- Linux x86-64 additionally covers JDK 17/21/25 and JavaFX 13/21/25; Windows x86-64 has an additional JDK/JavaFX 25 lane.
- Both Linux architectures compile against a cached AlmaLinux 8 sysroot (glibc 2.28) and run GUI tests through Xvfb.
- Windows ARM64 excludes JavaFX runtime tests because OpenJFX publishes no Windows ARM64 Maven natives; CEF/JNI,
  Swing, remote, and WebDriver modules still build and test natively there.
- Representative Linux, Windows, and macOS rows fork targeted runtime tests on an actual JDK 11.

JavaFX compile and test versions are overridden together, preventing mixed JavaFX module paths. Failed jobs retain
Surefire reports for seven days.

CI caches Maven dependencies, the two compressed Linux sysroots, and only the immutable compressed CEF archives. The
24 CEF archives occupy about 4.9 GiB; unpacked CEF trees are intentionally excluded from the 10 GiB repository cache.
Pull-request jobs restore default-branch caches but do not create branch-scoped copies.

The sysroot machinery is also usable locally. Docker is the default container engine; set `CONTAINER=podman` when
needed:

```bash
sysroot/manage.sh build x86_64       # or aarch64
export CMAKE_SYSROOT="$PWD/sysroot/out/$(uname -m)"
xvfb-run -a ./mvnw clean verify
```

`riscv64` sysroot and toolchain definitions are retained for native-only experimentation, but are not part of CI or
release because CEF does not publish a RISC-V binary distribution.

## Native cache

For in-process use, `SystemBootstrap` extracts native resources beneath
`${java.io.tmpdir}/cef4j-cache/<platform>/<fingerprint>`. The content fingerprint prevents stale libraries from being
reused across builds. Delete `${java.io.tmpdir}/cef4j-cache` to force a fresh extraction.

Runtime-server shared-frame files use generation-specific paths and are not reused after a crash, including on Windows
where a mapped file may not be immediately deletable.

## Release process

Ordinary Java JARs such as `cef4j-api` are platform-neutral. Public `cef4j-platform-<os>` JARs and
`cef4j-runtime-server-<os>` ZIPs use `x86_64`/`arm64` classifiers. The internal build asset classifiers and CEF directory IDs
(`linux64`, `linuxarm64`, `windows64`, `windowsarm64`, `macosx64`, and `macosarm64`) deliberately remain unchanged.

1. Run CI and locally validate release metadata with
   `./mvnw verify -Prelease -Drelease.bundle=true -Dgpg.skip=true -DskipTests`.
2. Run `./mvnw release:prepare -DdryRun=true -Darguments=-DskipTests`, inspect it, then
   `./mvnw release:clean`.
3. Run `./mvnw release:prepare -Darguments=-DskipTests`, inspect the version commits and `v…` tag, then push them.
4. Create a GitHub Release for that tag. The release workflow verifies the tag/POM version, builds Linux x86-64/ARM64
   against the AlmaLinux 8 sysroot, builds Windows x64/ARM64 and macOS Intel/ARM, and attaches the Java JARs plus classified
   native bundles.
5. The protected `central` environment creates one manual deployment for platform-neutral components and six for the
   OS-family native coordinates. The Linux ARM64 addition would push a single all-platform upload beyond Central's
   1 GB limit, so platform/runtime and Linux/Windows/macOS are deliberately independent deployments.
   Configure `CENTRAL_USERNAME`, `CENTRAL_TOKEN`, `GPG_PRIVATE_KEY`, and `GPG_PASSPHRASE` as environment secrets.
6. Inspect and publish all seven deployments in Central Portal; automatic publication is deliberately disabled.

Manual workflow dispatch builds and retains the same artifacts without modifying an existing GitHub Release or
uploading to Central.

## Licence

Copyright 2025-2026 WeiChen Lin

Licensed under the Apache License, Version 2.0: <https://www.apache.org/licenses/LICENSE-2.0>.
