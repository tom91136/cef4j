# CEF on macOS with JVM-based UI Toolkits

Reference notes on embedding Chromium Embedded Framework (CEF) in a JVM process on macOS,
with JavaFX and Swing off-screen rendering. These notes are general to any CEF/JVM embedding
but are written in the context of cef4j.

## Thread Model

### The JVM Thread 0 Problem

On macOS, the JVM reserves OS Thread 0 (the AppKit/Cocoa main thread) for its own internal use.
Java's `main()` method runs on a separate thread (typically Thread #3). `pthread_main_np()`
returns `false` for the Java "main" thread.

This matters because:
- JavaFX's Application Thread IS Thread 0 after `Application.launch()`
- Swing's EDT is NOT Thread 0, but AWT peer creation uses AppKit under the hood
- Thread 0 hosts the CFRunLoop needed for Mach port callbacks

### `-XstartOnFirstThread`

The JVM flag `-XstartOnFirstThread` makes Java main run on Thread 0. This works for
CEF initialization but deadlocks with `Application.launch()` because JavaFX also needs
Thread 0. It is useful for test processes (surefire forks) where JavaFX is not involved.

### Daemon Thread Solution

CEF does NOT require initialization on Thread 0. `cef_initialize()` and
`cef_run_message_loop()` must run on the **same** thread, but that thread can be any thread.

The cleanest approach is a dedicated daemon thread:
1. Spawn a daemon thread
2. Call `cef_initialize()` on it
3. Call `cef_run_message_loop()` on it (blocks until `cef_quit_message_loop()`)
4. On shutdown: `cef_quit_message_loop()` (thread-safe) unblocks the loop,
   then `cef_shutdown()` runs on the same daemon thread

This avoids all Thread 0 conflicts. The calling thread blocks on a latch until init
completes, then returns. JavaFX and Swing run their own event loops independently.

Browser creation via `cef_browser_host_create_browser()` (async) is thread-safe and
posts to CEF's message loop. All callbacks (`onPaint`, `onAfterCreated`, etc.) arrive
on the daemon thread; UI toolkit code must dispatch to its own thread (FX Platform
thread or Swing EDT).

### Previous Approach (Superseded)

The original port used `externalMessagePump=1` with a manual pump loop on the Java main
thread, dispatching CFRunLoop pumps to Thread 0 via GCD. This worked but required
platform-specific bootstrap code in every application. The daemon thread approach
eliminates this entirely.

## Mach Port Rendezvous / Bundle ID

CEF subprocess IPC on macOS uses Mach ports. The service name is derived from the main
bundle's `CFBundleIdentifier`:

    <bundle-id>.MachPortRendezvousServer.<pid>

The JVM runs inside the JDK bundle (`net.java.openjdk.java`), but a bare CEF helper
binary has no bundle, so `CFBundleGetIdentifier()` returns `""`. The browser registers
as `net.java.openjdk.java.MachPortRendezvousServer.<pid>`, but the subprocess looks for
`.MachPortRendezvousServer.<pid>` -- these never match.

### Fix

Patch the main bundle's info dictionary at the CoreFoundation level so both browser and
subprocess see the same bundle ID. In cef4j this is done in `bundle_fix_mac.mm`:

1. Sets `CFBundleIdentifier` in `CFBundleGetInfoDictionary` (what Chromium reads)
2. Swizzles `-[NSBundle bundleIdentifier]` for ObjC code paths

Both browser and subprocess call this at startup, producing:
`cef4j.MachPortRendezvousServer.<pid>`.

A more robust long-term approach (used by java-cef) is proper Helper `.app` bundles
with `Info.plist` for subprocess identification.

## Sandbox

CEF technically supports sandboxing on macOS (`cef_sandbox_mac.h`), but it requires
same-process control that the JVM cannot provide. The subprocess helper is a separate
executable, and sandbox initialization must happen before any other framework calls.
java-cef (the official Java CEF binding) also unconditionally disables sandbox on all
platforms for the same reason.

cef4j forces `noSandbox=1` and `--no-sandbox` in `Cef.initialise()` for all platforms.

## `multiThreadedMessageLoop`

macOS does not support `multiThreadedMessageLoop=1`. CEF's multi-threaded message loop
creates its own thread that expects to be Thread 0 for AppKit integration, which
conflicts with the JVM's use of Thread 0. This causes hangs or crashes.

Use `externalMessagePump=0` (daemon thread manages the loop) or `externalMessagePump=1`
(caller manages the loop) instead. cef4j validates this in `Cef.initialise()` and throws
`IllegalArgumentException` on macOS if `multiThreadedMessageLoop != 0`.

## SubprocessApp Lifetime

In the subprocess helper, the `CefApp` implementation must be heap-allocated.
CEF ref-counts it and may call release after `cef_execute_process()` returns. A
stack-allocated app object causes undefined behavior (delete on stack memory).
The process exits immediately after, so the leak is inconsequential.

## Test Infrastructure

- **`-XstartOnFirstThread`**: Required for surefire forks that initialize CEF on macOS.
  Set via `surefire.argLine` in the parent pom's macOS profile.
- **Headless detection**: UI tests (Swing/JFX) require macOS Window Server access.
  `GraphicsEnvironment.isHeadless()` and `Toolkit.getDefaultToolkit()` detect SSH/CI
  environments. Tests skip gracefully via JUnit `assumeTrue` rather than hanging.
- **`Platform.startup()` in tests**: JavaFX tests use `Platform.startup()` (non-blocking)
  rather than `Application.launch()` (blocking) to avoid taking over the test thread.
