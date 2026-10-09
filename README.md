# cef4j

[![CI](https://github.com/tom91136/cef4j/actions/workflows/main.yaml/badge.svg)](https://github.com/tom91136/cef4j/actions/workflows/main.yaml)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

Code-generated, off-screen-rendering Java bindings for the
[Chromium Embedded Framework](https://bitbucket.org/chromiumembedded/cef).

**Requires Java 11+.** Pre-release: APIs and coordinates may change before the first Maven Central release.

Features

* JavaFX (`CefWebView`, shaped like `javafx.scene.web.WebView`) and Swing (`CefBrowserPanel`) views
* In-process, or crash-isolated in a separate runtime server with restart
* The full CEF API, generated from CEF's own headers
* Typed Chrome DevTools Protocol and an embeddable W3C WebDriver endpoint
* Linux, Windows, and macOS on x86-64 and ARM64
* No installed Chrome or ChromeDriver; CEF is packaged with your application

cef4j is OSR-only: the host owns the window and cef4j supplies rendered pixels.

## Choose an integration

| Goal | Artifact |
| --- | --- |
| JavaFX in-process | `cef4j-inprocess-jfx` (`CefWebView`) |
| Swing in-process | `cef4j-inprocess-swing` (`CefBrowserPanel`) |
| Crash-isolated JavaFX/Swing | `cef4j-remote-jfx` / `cef4j-remote-swing` |
| Low-level in-process CEF API | `cef4j-api` |
| Remote CEF API and runtime server lifecycle | `cef4j-remote` |
| Typed Chrome DevTools Protocol | `cef4j-cdp` plus `cef4j-codecs-gson` or `cef4j-codecs-jackson` |
| W3C WebDriver endpoint | `cef4j-inprocess-webdriver` / `cef4j-remote` |
| `java.net.URL` over Chromium's network stack | `cef4j-http` (`CefHttp`) |

## Quick start

Add a UI artifact and the native bridge for your platform:

```xml
<dependency>
  <groupId>net.kurobako.cef4j</groupId>
  <artifactId>cef4j-inprocess-jfx</artifactId>
  <version>150.0.0</version>
</dependency>
<dependency>
  <groupId>net.kurobako.cef4j</groupId>
  <artifactId>cef4j-platform-linux</artifactId>
  <version>150.0.0</version>
  <classifier>x86_64</classifier>
</dependency>
```

Bridges are `cef4j-platform-{linux,windows,macos}` with an `x86_64` or `arm64` classifier; `cef4j-platform-all`
includes every bridge.

Initialise CEF from `Application.start(...)`, then create views. `noSandbox=1` is required: the direct-launch
distribution cannot use CEF's sandbox, so child processes run unsandboxed.

```java
CefSettings.Mutable settings = new CefSettings.Mutable();
settings.noSandbox = 1;
settings.cachePath = Files.createTempDirectory("cef4j-").toAbsolutePath().toString();
CefWebView.initialise(settings, List.of(), Optional.empty());

CefWebView view = new CefWebView();
view.getEngine().load("https://example.com");
stage.setScene(new Scene(new StackPane(view), 1280, 800));
stage.show();
```

Swing is the same with `CefBrowserPanel.initialise` and `new CefBrowserPanel()`. For direct lifecycle control use
`Cef.osrLaunchArgs()` with `Cef.INSTANCE.initialise`, registering `CefApp` handlers first. More examples are in
[`cef4j-sample`](cef4j-sample/src/main/java/net/kurobako/cef4j/sample).

## Supplying CEF

CEF itself is not on Maven Central. Pick one:

| Option | How |
| --- | --- |
| Package with the application | Run `cef4j-runtime-packager` during the build; cef4j loads its `cef-runtime/<platform>` output. |
| External installation | Set `LIBCEF_DIR` or `-Dcef4j.libcef.dir` to the unpacked `Release` directory. |
| Your own archive | Pass `--archive` (and optionally `--sha256`, `--offline`) to the packager. |

The packager downloads and verifies official builds, caches them under `${CEF4J_CEF_CACHE:-~/.cache/cef4j}`, and can
keep selected locales, drop SwiftShader, or strip the Linux library:

```bash
java -jar cef4j-runtime-packager-0.1.0.jar package \
  --cef-version=150.0.18+gdb11278+chromium-150.0.7871.213 \
  --platform=host --locales=en-US --strip --without-swiftshader \
  --output=src/main/resources
```

It is an ordinary Java 11 CLI dependency, so no build plugin is needed. Working, CI-tested setups:
[Maven](examples/maven/pom.xml), [Gradle Groovy](examples/gradle-groovy/build.gradle),
[Gradle Kotlin](examples/gradle-kotlin/build.gradle.kts), and [sbt](examples/sbt/build.sbt). Use `--build-type=debug`
for a Debug runtime when diagnosing native crashes.

## Compatibility

Each cef4j release builds its bridges against one pinned CEF API major (currently 150). From CEF 133, CEF versions its C
API: a bridge built against API N also loads on newer CEF releases that still support API N. You can update CEF for
security and stability fixes without a new cef4j release; newer CEF APIs arrive with a cef4j release built against a
newer major. Before 133 the runtime must match the bridge's major. Version-specific workarounds follow the loaded CEF,
not the bridge.

CI covers these representatives on every platform, plus Linux builds back to CEF 73:

| CEF API | Representative | Role |
| --- | --- | --- |
| 150 | `150.0.18+gdb11278+chromium-150.0.7871.213` | frontier and release pin |
| 144 | `144.0.19+g937f5c6+chromium-144.0.7559.246` | retained LTS |
| 138 | `138.0.62+g6981a09+chromium-138.0.7204.310` | LTS |
| 116 | `116.0.27+gd8c85ac+chromium-116.0.5845.190` | long-tail API |
| 109 | `109.1.18+gf1c41e4+chromium-109.0.5414.120` | compatibility floor |

The 138 and 150 bridges are also tested against the newest CEF release. Applications own the exact CEF patch they ship;
legacy lanes show compatibility, not security support.

## Runtime server

Remote mode runs CEF in `cef4j-runtime-server`, so native crashes stay out of the JVM:

```text
JavaFX / Swing / WebDriver / client
                 |
       Remote CEF API + CDP
                 |
              CefSession
                 |
    local | zmq | uds | websocket
                 |
       cef4j-runtime-server + CEF
```

`RuntimeServerProcess` launches a server; `RuntimeServerSupervisor` restarts it as a new generation, failing pending
calls so stale handles never cross generations. Control and frame transports are independent:

| Provider | Purpose |
| --- | --- |
| `local` | platform-default local control |
| `zmq` | portable local control |
| `uds` | Unix-domain socket |
| `websocket` | interoperable or remote control |
| `inline` | portable BGRA frames |
| `shared-file` | mapped local frames |

The runtime-server ZIP does not include CEF. Point it at one with `LD_LIBRARY_PATH` and `CEF_RESOURCES_DIR` (Linux),
`PATH` and `CEF_RESOURCES_DIR` (Windows), `CEF_FRAMEWORK_DIR` (macOS), or the packager's output directory.

Sessions can be recorded and replayed with `RecordingCefSession` and `ReplayCefSession` using a Gson or Jackson codec.
Traces can contain URLs, cookies, headers, scripts, and credentials; treat them as sensitive.

Endpoints bind locally by default. The Remote CEF API grants full browser authority: do not expose it to untrusted
networks without TLS, authentication, and resource limits.

## Automation

`cef4j-webdriver` is an embeddable W3C endpoint for in-process and remote hosting. Add a Gson or Jackson codec and point
a Selenium `RemoteWebDriver` at it. It covers sessions, navigation, scripts, screenshots, elements, input, cookies,
refresh, and timeouts.

`cef4j-cdp` is a typed Chrome DevTools Protocol API generated from the pinned Chromium schema, with a raw-command escape
hatch:

```java
CdpClient cdp = new CdpClient(devToolsSession, new GsonCdpCodec());
Runtime.EvaluateResult result = cdp.domains().runtime()
    .evaluate("document.title")
    .toCompletableFuture().join();
```

## HTTP through CEF

`cef4j-http` serves `http`/`https` `java.net.URL` connections through CEF's network stack. CEF must be initialised
in-process. Nothing is registered implicitly:

```java
URL url = new URL(null, "https://example.com/", CefHttp.handler("https"));
HttpURLConnection connection = (HttpURLConnection) url.openConnection();
```

`CefHttp.install()` routes every `http`/`https` URL in the JVM. The JDK allows one handler factory per JVM and caches
handlers on first use, so install it early, or compose `CefHttp.factory()` with an existing factory.

## Modules

| Module | Responsibility |
| --- | --- |
| `cef4j-api` | in-process CEF API |
| `cef4j-platform-{linux,windows,macos}` | native bridges (published from `cef4j-platform`) |
| `cef4j-inprocess-{jfx,swing}` | in-process JavaFX and Swing views |
| `cef4j-remote` | transports, sessions, supervision, generated Remote CEF API, remote WebDriver adapter |
| `cef4j-remote-frame` | frame transports, MJPEG, shared remote view binding |
| `cef4j-remote-{jfx,swing}` | remote JavaFX and Swing views |
| `cef4j-runtime-server-{linux,windows,macos}` | runtime server distributions (published from `cef4j-runtime-server`) |
| `cef4j-cdp` | typed Chrome DevTools Protocol |
| `cef4j-codecs-{gson,jackson}` | JSON codecs for CDP, WebDriver, and session traces |
| `cef4j-webdriver`, `cef4j-inprocess-webdriver` | W3C WebDriver endpoint and in-process adapter |
| `cef4j-http` | opt-in `java.net.URL` handlers |
| `cef4j-runtime-packager` | CEF download and packaging CLI, versioned independently |
| `cef4j-codegen`, `cef4j-code-policy` | build-only: binding generator and static-analysis rules |
| `cef4j-test-support`, `cef4j-test-shared`, `cef4j-integration-tests`, `cef4j-sample` | not published |

## Development

Prerequisites: JDK 17+, Ninja, and a C++ toolchain. The build downloads CEF into `.cef-dist/` and regenerates the
checked-in bindings from its headers.

    ./mvnw clean install                # build and test (use mvnw.cmd on Windows)
    xvfb-run -a ./mvnw clean install    # Linux: always run native/UI tests under Xvfb

Build against another CEF release with `-Dcef.version=<version> -Dcef.api.version=<major>`. Run the Swing sample with
`./mvnw exec:exec -pl cef4j-sample`, or the JavaFX one by adding
`-Dexec.mainClass=net.kurobako.cef4j.sample.JfxBrowserApp`.

[DEBUG.md](DEBUG.md) covers native crashes, the runtime server, IPC, and test isolation; [PLAN.md](PLAN.md) tracks
open work.

CI builds and tests 6 platforms against 5 CEF versions on JDK 17, 21, and 25, plus 24 Linux lanes covering CEF 73 to
152.

Publishing a GitHub release for a `vMAJOR.MINOR.PATCH` tag builds every platform and stages one Central deployment
for Java artifacts and one for the native bridges, gated by the protected `central` environment. The packager is
released separately with `runtime-packager-vX.Y.Z` tags.

## Licence

Copyright 2025-2026 WeiChen Lin

Licensed under the Apache License, Version 2.0: <https://www.apache.org/licenses/LICENSE-2.0>.
