cef4j
=====

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

Code-generated, OSR-only Java bindings for the [Chromium Embedded Framework](https://bitbucket.org/chromiumembedded/cef).

**Requires Java 11+**

Features

 * Off-screen rendering (OSR) only - no native windowing, embed anywhere you can paint a pixel buffer
 * JavaFX drop-in view (`CefWebView` / `CefWebEngine`) that mirrors the `javafx.scene.web.WebView` API
 * Swing panel (`CefBrowserPanel`) for AWT/Swing applications
 * Scala-based codegen that walks CEF public headers and emits typed Java bindings with javadoc
 * Multi-version support: a single build supports CEF 109 through 146 via compile-time version selection
 * Linux, macOS, and Windows x64 with platform-specific native launchers bundled into the jar
 * Sandboxed native resource cache with per-build fingerprinting to avoid stale DSO reuse

Unlike [JCEF](https://github.com/chromiumembedded/java-cef), cef4j does not handle native windowing
or window-system integration; the host toolkit (JavaFX/Swing/your own) owns the surface and receives
rendered frames as pixel buffers. This keeps the binding small and lets the same CEF runtime embed
cleanly into any Java UI framework.

## Tested version matrix

Status below is for a fresh-cache host Linux/Xvfb run on JDK 21 using:

```bash
xvfb-run -a ./mvnw clean verify -Dspotless.skip=true
```

For non-default CEF versions the run also sets `-Dcef.version`, `-Dcef.api.version`, and a
version-specific `-Dcef.dist.cache`; that cache must contain the Linux, macOS, and Windows minimal
CEF bundles because codegen walks public headers for all three platform variants.

Role labels:

 - `Edge`   - default newest line in this repo
 - `Stable` - newest non-default line we keep explicitly green
 - `LTC`    - long-tail compatibility lane for materially older generated/public APIs
 - `Floor`  - oldest compatibility lane we still verify end-to-end

| CEF API | CEF version                                           | Role     | Significance                                                                             | `clean verify` |
|---------|-------------------------------------------------------|----------|------------------------------------------------------------------------------------------|:--------------:|
| 146     | `146.0.9+g3ca6a87+chromium-146.0.7680.165`            | `Edge`   | Default `pom.xml` line and reference for the newest generated API surface.               |       ✓        |
| 144     | `144.0.19+g937f5c6+chromium-144.0.7559.246`           | `Stable` | First `V144Plus` bucket representative; newest non-default verification lane.            |       ✓        |
| 116     | `116.0.27+gd8c85ac+chromium-116.0.5845.190`           | `LTC`    | Last line before the `V117Plus` test bucket begins; practical long-tail API lane.        |       ✓        |
| 109     | `109.1.18+gf1c41e4+chromium-109.0.5414.120`           | `Floor`  | Last Chromium line with official Windows 7/8/8.1 support; legacy compatibility floor.    |       ✓        |

## How to use

For Maven users, add the following to pom:

```xml
<dependency>
    <groupId>net.kurobako.cef4j</groupId>
    <artifactId>cef4j-osr-jfx</artifactId>
    <version>146.0.0</version>
</dependency>
```

Or for Swing:

```xml
<dependency>
    <groupId>net.kurobako.cef4j</groupId>
    <artifactId>cef4j-osr-swing</artifactId>
    <version>146.0.0</version>
</dependency>
```

The `cef4j-osr-*` artifacts pull in `cef4j-api` (Java bindings) and `cef4j-native` (JNI launcher +
bundled CEF runtime) transitively.

## Quick start

JavaFX - `CefWebView` is a drop-in for `javafx.scene.web.WebView`:

```java
CefSettings.Mutable settings = new CefSettings.Mutable();
settings.cachePath = Files.createTempDirectory("cef4j-").toAbsolutePath().toString();
CefWebView.initialise(settings, List.of(), null);

CefWebView view = new CefWebView();
view.getEngine().load("https://example.com");

Stage stage = new Stage();
stage.setScene(new Scene(new StackPane(view), 1280, 800));
stage.show();
```

Swing - `CefBrowserPanel` plugs into any AWT container:

```java
Cef.LaunchArgs launch = Cef.osrLaunchArgs();
launch.settings().cachePath = Files.createTempDirectory("cef4j-").toAbsolutePath().toString();
Cef.INSTANCE.initialise(launch.settings(), launch.args());

CefBrowserPanel panel = new CefBrowserPanel();
CefClient client = new CefClient() {
    @Override public Optional<CefRenderHandler> getRenderHandler() {
        return Optional.of(panel.createRenderHandler());
    }
    @Override public Optional<CefLifeSpanHandler> getLifeSpanHandler() {
        return Optional.of(new CefLifeSpanHandler() {
            @Override public void onAfterCreated(@Nullable CefBrowser b) {
                if (b != null) SwingUtilities.invokeLater(() -> panel.setBrowser(b));
            }
        });
    }
};

CefWindowInfo info = Cef.createWindowlessInfo(new CefRect(0, 0, 1280, 800));
CefBrowserHost.createBrowser(info, client, "https://example.com",
        new CefBrowserSettings.Mutable().toImmutable(), null, null);

JFrame frame = new JFrame("cef4j");
frame.add(panel);
frame.setSize(1280, 800);
frame.setVisible(true);
```

For more, see the [samples](cef4j-sample/src/main/java/net/kurobako/cef4j/sample).

## Samples

The `cef4j-sample` module contains three runnable demos:

 - `JfxBrowserApp`    - tabbed JavaFX browser using `CefWebView`
 - `SwingBrowserApp`  - tabbed Swing browser using `CefBrowserPanel`
 - `ViewsBrowserApp`  - CEF Views framework browser (native window toolkit)

Make sure you have at least JDK 11 installed. Clone the project and run:

    ./mvnw install
    ./mvnw exec:java -pl cef4j-sample -Dexec.mainClass=net.kurobako.cef4j.sample.JfxBrowserApp

This project uses the Maven wrapper, so you do not need to install Maven beforehand.

## How to build

Prerequisites:

 * JDK 11+ (tested on JDK 21)
 * A C++ toolchain (clang on Linux/macOS, MSVC on Windows) to build the native launcher
 * CEF minimal distributions for all three platforms in `.cef-dist/` (auto-downloaded on first build)

Clone the project and then in project root:

    # *nix:
    ./mvnw clean package
    # Windows:
    mvnw clean package

To build against a non-default CEF version:

    ./mvnw clean package \
        -Dcef.version=144.0.19+g937f5c6+chromium-144.0.7559.246 \
        -Dcef.api.version=144

On Linux CI, tests run under `xvfb-run` with software rendering:

    xvfb-run -a ./mvnw test

**NOTE: Running the tests headful will spawn actual windows; the test windows flicker while
individual unit tests are invoked.**

## Native cache

`SystemBootstrap` extracts `libcef4j`, `cef4j_launcher`, and the bundled CEF runtime into
`${java.io.tmpdir}/cef4j-cache/<platform>/<fingerprint>`.

The fingerprint is derived from the embedded native resources and the selected CEF runtime, which
avoids reusing stale DSOs when switching between CEF builds - a common source of startup failures
during cross-version testing.

To force a fresh extraction, remove `${java.io.tmpdir}/cef4j-cache` before the next run.

## Linux test notes

Linux UI tests run more reliably when cef4j disables the CEF sandbox layers in addition to setting
`settings.noSandbox = true`. The startup path appends these flags automatically:

 - `--no-sandbox`
 - `--disable-setuid-sandbox`
 - `--disable-seccomp-filter-sandbox`
 - `--disable-gpu-sandbox`

Tooling sandboxes can still produce false negatives that do not reproduce on the host machine. The
version matrix above is based on host-side `xvfb-run` verification, not sandboxed tool execution.

## Debugging

See [DEBUG.md](DEBUG.md) for native-crash troubleshooting notes.

## Release process

1. Commit all changes before release.
2. Make sure `${user.home}/.m2/settings.xml` exists with a `<server>` entry for the Central Portal token:

    ```xml
    <server>
      <id>central</id>
      <username>${token-username}</username>
      <password>${token-password}</password>
    </server>
    ```
    Generate the token at https://central.sonatype.com. Also make sure the machine has a GPG key and SSH access to GitHub.
3. Run `mvn verify -Prelease -DskipTests` to check that signing, sources, and javadoc generation all succeed before starting the release.
4. Run `mvn release:prepare -DdryRun=true -Darguments=-DskipTests`, make sure it succeeds.
5. Run `mvn release:clean` to clean up from the release dry run.
6. Run `mvn release:prepare -Darguments=-DskipTests`; Maven will tag and commit the new version.
7. Inspect the commits after `release:prepare` and push, including tags: `git push --tags`.
8. Finally, run `mvn clean release:perform -Darguments=-DskipTests` to create docs and sources and upload to Central Portal.
9. Verify and publish the deployment at https://central.sonatype.com/publishing.

## Licence

    Copyright 2025-2026 WeiChen Lin

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
