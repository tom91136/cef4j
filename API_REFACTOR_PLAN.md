# Plan: Simplify CEF Initialization API

## Goal

Remove the static `initialise()` from `CefWebView` and `CefBrowserPanel`. Users interact
with `Cef.INSTANCE` directly for configuration, and the UI components lazy-init CEF on
first use with sensible defaults. Add a composable `CefApp` handler registration system
with codegen support.

## What's Already Done

- Daemon thread lifecycle in `Cef.java`: `cef_initialize()` + `cef_run_message_loop()`
  on a background daemon thread when `externalMessagePump=0`
- Settings validation in `Cef.initialise()`: rejects `multiThreadedMessageLoop` on macOS
- `Cef.initialiseUnsafe()` bypasses validation
- Sandbox forced globally: `noSandbox=1` + `--no-sandbox` in `Cef.initialise()`
- Redundant sandbox code removed from UI classes, tests, and samples
- API shape unified: `initialise(settings, extraArgs, appHandler)` across all classes
- `CefDaemonRenderTest` — 3 tests covering daemon thread render path
- All 140 tests passing on macOS

## Part 1: Lazy Init in UI Components

### Remove `CefWebView.initialise()` and `CefBrowserPanel.initialise()`

Delete the static `initialise` and `terminate` methods from both UI classes. All
initialization goes through `Cef.INSTANCE`.

### Lazy init in `CefWebView.maybeCreateBrowser()`

When a `CefWebView` is first attached to a showing window, check
`Cef.INSTANCE.getState()`. If not `INITIALISED`, call `Cef.INSTANCE.initialise()`
with OSR defaults:

```java
settings.windowlessRenderingEnabled = 1;
settings.externalMessagePump = 0;
settings.multiThreadedMessageLoop = 0;
```

Plus JFX-specific args: `--disable-popup-blocking`, `--ozone-platform=x11` (Linux).

Default `cachePath` to `${java.io.tmpdir}/cef4j-jfx-cache` if not configured.

### Lazy init in `CefBrowserPanel`

Same pattern. When the panel first needs a browser, check `Cef.INSTANCE.getState()`
and lazy-init with OSR defaults if needed.

### Compatibility validation

If CEF is already initialised (user called `Cef.INSTANCE.initialise()` first or another
UI component got there first), validate that the existing settings are compatible.
`Cef` must store the settings used at init time and expose them.

The critical check: `windowlessRenderingEnabled` must be `1` for OSR components.
If it's `0` (Views mode), throw `IllegalStateException`.

Both UI libs need the same CEF settings, so cross-toolkit (JFX + Swing in one app)
works automatically -- whoever lazy-inits first configures CEF in a way that works
for both.

### Remove constructor guard in `CefWebView`

The constructor currently throws if `activeSetup == null`. Remove this -- the view
will lazy-init CEF when it's first attached to a showing window.

### Remove `SetupState` from `CefWebView`

The `SetupState` class, the `SETUP_LOCK`, and the `activeSetup` field exist to track
whether `initialise()` was called. With lazy init, these are unnecessary. Idempotency
is already handled by `Cef.INSTANCE` (`if (state == INITIALISED) return`).

### Remove macOS groovy hack in `cef4j-osr-jfx/pom.xml`

The `preStartup`/`postStartup` two-phase init exists because `cef_initialize()` needed
to run before `Platform.startup()` took over Thread 0. With the daemon thread, this
constraint no longer exists. Remove the groovy script that generates patched test support
classes.

### Update sample apps

```java
// JFX
public static void main(String[] args) {
    Application.launch(JfxApp.class, args);
    Cef.INSTANCE.terminate();
}

// Swing
public static void main(String[] args) {
    SwingUtilities.invokeAndWait(SwingBrowserApp::createUI);
    shutdownLatch.await();
    Cef.INSTANCE.terminate();
}
```

If users need custom settings:
```java
Cef.INSTANCE.initialise(settings, extraArgs, null);
Application.launch(JfxApp.class, args);
Cef.INSTANCE.terminate();
```

### Update tests

Tests that need custom CEF settings call `Cef.INSTANCE.initialise()` directly.
Tests that just need a browser view don't need any init call.

## Part 2: CefApp Handler Registration

### `Cef.addAppHandler()` / `removeAppHandler()`

```java
// Thread-safe. Throws IllegalStateException if CEF is already INITIALISED.
public void addAppHandler(CefApp handler);

// Thread-safe. Can be called anytime.
public void removeAppHandler(CefApp handler);
```

Like `URL.setURLStreamHandlerFactory` -- must be registered before init. Registering
after init throws with a clear message: "CEF is already initialized -- register
handlers before calling initialise() or creating any browser view."

### Drop `appHandler` param from `initialise()`

Signature becomes:
```java
public synchronized void initialise(
        @Nonnull CefSettings.Mutable settings, @Nonnull List<String> extraArgs);
public synchronized void initialiseUnsafe(
        @Nonnull CefSettings.Mutable settings, @Nonnull List<String> extraArgs);
```

All CefApp registration goes through `addAppHandler()`.

### Internal composite delegate

`Cef` stores a `CopyOnWriteArrayList<CefApp>`. At init time, it creates a single
internal `CefApp` that delegates to all registered handlers. If no handlers are
registered, a minimal default is used (the existing Windows command-line handler).

### Usage pattern

```java
Cef.INSTANCE.addAppHandler(new CefApp() {
    @Override
    public void onRegisterCustomSchemes(CefSchemeRegistrar registrar) {
        registrar.addCustomScheme("myapp", ...);
    }
});
// Later, lazy init happens via CefWebView or explicit Cef.INSTANCE.initialise()
```

## Part 3: Codegen — `Delegating` Inner Classes

### Generate for every handler interface

For each generated handler interface (e.g. `CefApp`, `CefBrowserProcessHandler`,
`CefRenderProcessHandler`, `CefLoadHandler`, `CefClient`, etc.), generate a
`Delegating` inner class:

```java
public interface CefBrowserProcessHandler extends CefClientHandler {
    // ... existing default methods ...

    class Delegating implements CefBrowserProcessHandler {
        private final List<CefBrowserProcessHandler> delegates;
        public Delegating(List<CefBrowserProcessHandler> delegates) {
            this.delegates = List.copyOf(delegates);
        }
        // ... generated dispatch methods ...
    }
}
```

### Dispatch rules (derived from return type)

| Return type | Rule | Example |
|-------------|------|---------|
| `void` | Call all delegates | `onContextInitialized()` |
| `boolean` | First `true` short-circuits | `onAlreadyRunningAppRelaunch()` |
| `Optional<T>` where T is a handler interface | Collect all non-empty, wrap in `T.Delegating` | `getBrowserProcessHandler()` |
| `Optional<T>` otherwise | First non-empty wins | `getDefaultClient()` |

The codegen already knows which types are handler interfaces (they extend
`CefClientHandler`), so it can distinguish the `Optional` cases automatically.

### Recursive composition

For handler-returning methods, the generated code composes recursively:

```java
@Override
public Optional<CefBrowserProcessHandler> getBrowserProcessHandler() {
    var handlers = delegates.stream()
        .map(CefApp::getBrowserProcessHandler)
        .flatMap(Optional::stream)
        .toList();
    return handlers.isEmpty() ? Optional.empty()
        : Optional.of(new CefBrowserProcessHandler.Delegating(handlers));
}
```

This means the delegation pattern works at arbitrary depth without manual code.

### Where the codegen lives

The handler interfaces are generated via:
`mvn generate-sources -pl cef4j-native -Dcef.version=...`

The codegen templates need to be updated to emit the `Delegating` class alongside
each handler interface. The dispatch rule is derivable from the method signature.

## Files to Modify

### Part 1 (lazy init)
| File | Change |
|------|--------|
| `Cef.java` | Store settings at init time, expose for validation |
| `CefWebView.java` | Remove `initialise()`, `terminate()`, `SetupState`, `SETUP_LOCK`, constructor guard. Lazy init in `maybeCreateBrowser()` |
| `CefBrowserPanel.java` | Remove `initialise()`, `terminate()`. Lazy init on first browser need |
| `cef4j-osr-jfx/pom.xml` | Remove groovy `preStartup`/`postStartup` hack |
| Sample apps | Simplify to just `Application.launch()` + `Cef.INSTANCE.terminate()` |
| All tests | Update to call `Cef.INSTANCE.initialise()` directly or rely on lazy init |

### Part 2 (CefApp registration)
| File | Change |
|------|--------|
| `Cef.java` | Add `addAppHandler`/`removeAppHandler`, drop `appHandler` param, build composite at init time |
| `CefWebViewSchemeTest.java` | Use `Cef.INSTANCE.addAppHandler()` before creating views |
| `CefTestBase.java` | Drop `appHandler` param from `initCef()` |

### Part 3 (codegen)
| File | Change |
|------|--------|
| Codegen templates in `cef4j-native` | Emit `Delegating` inner class for each handler interface |
| Regenerate all handler interfaces | `mvn generate-sources -pl cef4j-native` |

## Verification

1. `./mvnw spotless:apply`
2. `./mvnw install -DskipTests`
3. `./mvnw test -pl cef4j-api` — all tests pass
4. JFX sample: `./mvnw exec:exec -pl cef4j-sample -Dexec.mainClass=...JfxBrowserApp`
5. Swing sample: `./mvnw exec:exec -pl cef4j-sample -Dexec.mainClass=...SwingBrowserApp`
6. Verify: page renders, close exits cleanly, no explicit `initialise()` call needed
