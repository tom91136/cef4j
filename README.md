# cef4j

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![CI](https://github.com/tom91136/cef4j/actions/workflows/main.yaml/badge.svg)](https://github.com/tom91136/cef4j/actions/workflows/main.yaml)

Code-generated, off-screen-rendering Java bindings for the
[Chromium Embedded Framework](https://bitbucket.org/chromiumembedded/cef).

> Active pre-release development. APIs and coordinates may change before the first Maven Central release.

Runtime baseline: Java 11. Building requires JDK 17+ and a platform C++ toolchain.

## Choose an integration

| Goal | Artifact |
| --- | --- |
| JavaFX in-process OSR | `cef4j-inprocess-jfx` (`CefWebView`) |
| Swing in-process OSR | `cef4j-inprocess-swing` (`CefBrowserPanel`) |
| Crash-isolated JavaFX/Swing | `cef4j-remote-jfx` / `cef4j-remote-swing` |
| Low-level in-process CEF API | `cef4j-api` |
| Generated remote CEF API | `cef4j-remote-api` |
| Typed Chrome DevTools Protocol | `cef4j-cdp` plus a Gson or Jackson codec |
| W3C WebDriver endpoint | `cef4j-inprocess-webdriver` / `cef4j-remote-webdriver` |

Both hosting modes support Linux, Windows, and macOS on x86-64 and ARM64. Remote mode runs CEF in
`cef4j-runtime-server`, isolating native crashes and allowing bounded restart. It does not require installed Chrome or
ChromeDriver.

cef4j is OSR-only: the host owns the window and cef4j supplies rendered pixels. Chromium still uses its normal renderer,
GPU, and utility subprocesses.

## In-process example

Add a UI artifact and the thin bridge for the target platform:

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

Bridge coordinates are `cef4j-platform-{linux,windows,macos}` with an `x86_64` or `arm64` classifier.
`cef4j-platform-all` includes every bridge.

Initialize JavaFX CEF from `Application.start(...)`. The direct-launch distribution cannot initialize CEF's
supported sandbox, so `noSandbox=1` is a required, explicit acceptance of unsandboxed child processes:

```java
CefSettings.Mutable settings = new CefSettings.Mutable();
settings.noSandbox = 1;
settings.cachePath = Files.createTempDirectory("cef4j-").toAbsolutePath().toString();
CefWebView.initialise(settings, List.of(), null);

CefWebView view = new CefWebView();
view.engine().load("https://example.com");
stage.setScene(new Scene(new StackPane(view), 1280, 800));
stage.show();
```

See [`cef4j-sample`](cef4j-sample/src/main/java/net/kurobako/cef4j/sample) for JavaFX, Swing, and CEF Views examples.
Use `Cef.osrLaunchArgs()` for direct lifecycle control and register `CefApp` handlers before initialization.

## Supplying CEF

CEF binaries are not published to Maven Central. Use a CEF build from the same major as cef4j:

| Option | Use |
| --- | --- |
| External installation | Set `LIBCEF_DIR` or `-Dcef4j.libcef.dir` to the unpacked `Release` directory. |
| Application resource | Run `cef4j-runtime-packager` during the build; cef4j loads its `cef-runtime/<platform>` output. |
| User-managed archive | Pass `--archive` and optionally `--sha256`; add `--offline` to prohibit network access. |

The packager validates official downloads, caches them under `${CEF4J_CEF_CACHE:-~/.cache/cef4j}`, supports all six
platforms, and can retain selected locales, omit SwiftShader, or strip the primary Linux library. Use
`--platform=host` for strict host detection: unknown operating systems and architectures fail instead of falling back
to Linux or x86-64. Complete CI-tested build integrations:

- [Maven](examples/maven/pom.xml)
- [Gradle Groovy](examples/gradle-groovy/build.gradle)
- [Gradle Kotlin](examples/gradle-kotlin/build.gradle.kts)
- [sbt](examples/sbt/build.sbt)

They invoke the ordinary Java 11 CLI dependency; no custom build plugin is required. CI runs each example against a
cached official archive and verifies the packaged resource layout. The CLI can also be invoked directly:

```bash
java -jar cef4j-runtime-packager-0.1.0.jar package \
  --cef-version=150.0.18+gdb11278+chromium-150.0.7871.213 \
  --platform=host \
  --locales=en-US \
  --strip \
  --without-swiftshader \
  --output=src/main/resources
```

The sbt example keeps the ordinary application jar platform-neutral. Set `-Dcef4j.embed-runtime=true` to build its
separate, platform-classified runtime jar; it skips acquisition when `LIBCEF_DIR` or `cef4j.libcef.dir` is set. sbt's
offline setting is forwarded to the child packager, and `-Dcef4j.packager.offline=true` can request that behavior
independently. Linux packaging strips by default; set `-Dcef4j.strip-runtime=false` to retain debug symbols.
For cross-packaging a Linux runtime, pass `--strip-command` with a target-capable strip executable.

## Runtime server

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

`RuntimeServerProcess` launches one version-matched server. `RuntimeServerSupervisor` can restart it with a new
generation; pending calls fail and stale native handles cannot cross generations. Control and frame providers are
independent:

| Provider | Purpose |
| --- | --- |
| `local` | platform-default local control |
| `zmq` | portable local control |
| `uds` | explicit Unix-domain socket |
| `websocket` | interoperable or remote control |
| `inline` | portable BGRA frame delivery |
| `shared-file` | mapped local frame delivery |

The classified runtime-server ZIP is thin. Supply CEF through the child environment: `LD_LIBRARY_PATH` and
`CEF_RESOURCES_DIR` on Linux, `PATH` and `CEF_RESOURCES_DIR` on Windows, or `CEF_FRAMEWORK_DIR` on macOS. The packager's
normalized platform directory is also accepted.

Remote sessions support versioned NDJSON recording and strict replay through `RecordingCefSession` and
`ReplayCefSession`. Add `cef4j-codecs-gson` or `cef4j-codecs-jackson` for a codec. Traces may contain
URLs, cookies, headers, scripts, form values, and credentials; treat them as sensitive.

Local endpoints bind locally by default. Remote exposure requires TLS, authentication, origin/capability policy, and
resource limits. The Remote CEF API conveys broad browser authority and should not be exposed directly to untrusted
networks.

## Automation

`cef4j-webdriver` is an embeddable W3C endpoint shared by in-process and remote hosting. Add its Gson or Jackson codec
artifact and point an ordinary Selenium `RemoteWebDriver` at it. Current coverage includes sessions, navigation,
scripts, screenshots, elements, native interaction, cookies, refresh, and timeouts.

`cef4j-cdp` is a generated Java 11 API for the exact pinned Chromium schema. It has a codec SPI, raw-command escape
hatch, and in-process and remote transports. Add `cef4j-codecs-gson` or `cef4j-codecs-jackson`:

```java
CdpClient cdp = new CdpClient(devToolsSession, new GsonCdpCodec());
Runtime.EvaluateResult result = cdp.domains().runtime()
    .evaluate("document.title")
    .toCompletableFuture().join();
```

The platform build caches the matching Chromium/V8 PDL sources beside CEF and regenerates the CDP bindings.

## Modules

| Module family | Responsibility |
| --- | --- |
| `cef4j-codegen`, `cef4j-platform` | binding generation and the native bridge build |
| `cef4j-api`, `cef4j-platform-*` | in-process API and published platform bridges |
| `cef4j-inprocess-{jfx,swing}` | toolkit-specific in-process OSR |
| `cef4j-remote-core`, `cef4j-remote-api` | transports, sessions, supervision, and generated remote API |
| `cef4j-runtime-server-*` | thin native runtime server distributions |
| `cef4j-remote-frame`, `cef4j-remote-{jfx,swing}` | frame providers, codecs, MJPEG, and remote UI surfaces |
| `cef4j-cdp` | typed CDP API and in-process/remote adapters |
| `cef4j-codecs-{gson,jackson}` | CDP, recording/replay, and WebDriver codecs |
| `cef4j-webdriver`, `cef4j-{inprocess,remote}-webdriver` | W3C endpoint and hosting adapters |
| `cef4j-runtime-packager` | independently versioned CEF acquisition and packaging CLI |
| `cef4j-test-support`, `cef4j-test-shared`, `cef4j-integration-tests` | reusable test infrastructure and cross-backend contracts |
| `cef4j-sample` | Swing and JavaFX examples |

## Build and test

```bash
./mvnw clean package
```

Linux native/UI tests must use a private X server:

```bash
xvfb-run -a ./mvnw clean verify -Dspotless.check.skip=true
```

Windows uses `mvnw.cmd`. The build requires Ninja and downloads minimal CEF archives into `.cef-dist/`. See
[DEBUG.md](DEBUG.md) for native, platform, IPC, and test troubleshooting.

### Compatibility

| CEF API | Pinned CI representative | Role |
| --- | --- | --- |
| 150 | `150.0.18+gdb11278+chromium-150.0.7871.213` | frontier |
| 144 | `144.0.19+g937f5c6+chromium-144.0.7559.246` | retained LTS |
| 138 | `138.0.62+g6981a09+chromium-138.0.7204.310` | LTS |
| 116 | `116.0.27+gd8c85ac+chromium-116.0.5845.190` | long-tail API |
| 109 | `109.1.18+gf1c41e4+chromium-109.0.5414.120` | compatibility floor |

Each row is one test representative, not support for every patch build. Applications own and should update the exact
CEF patch they ship. cef4j targets one CEF API major per bridge release; compatible patch updates do not require a new
bridge publication. Legacy lanes show compatibility, not security support.

CI runs 30 native platform/CEF jobs. Each job builds and tests with JDK 17, then reuses the generated and native output
for Java compatibility tests on JDK 21 and 25. Three host-native jobs test the standalone packager. The matrix also
covers JavaFX variants, actual JDK 11 runtime forks, and the four consumer builds above. Linux uses AlmaLinux 8
sysroots for a glibc 2.28 floor and Xvfb for all GUI tests.

## Release

Ordinary Java artifacts are platform-neutral. Thin bridge and server artifacts use OS-family coordinates with
`x86_64`/`arm64` classifiers. A `vMAJOR.MINOR.PATCH` release creates one Central deployment for Java artifacts and one
for all native bridges; CEF binaries are excluded. The protected `central` environment requires manual publication.

The standalone packager has its own `0.x` lifecycle and `runtime-packager-vX.Y.Z` tags. Detailed release commands live
in the corresponding GitHub Actions workflows.

## Licence

Copyright 2025-2026 WeiChen Lin

Licensed under the Apache License, Version 2.0: <https://www.apache.org/licenses/LICENSE-2.0>.
