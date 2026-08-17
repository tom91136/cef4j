# cef4j roadmap

Current architecture and usage are in [README.md](README.md); investigation procedures are in
[DEBUG.md](DEBUG.md). This file tracks unfinished work only.

## Constraints

- Java 11 remains the published runtime floor; build-only tooling may use newer JDKs.
- Keep the JavaFX compile baseline at 13.0.2 until deliberately changed.
- Remote clients must not load cef4j JNI. A restarted server always creates a new generation; native identities are
  never reused.
- Remote CEF remains a typed, transport-neutral API. CDP and WebDriver are additional surfaces.
- Control and frame providers remain independent behind their SPIs.
- Local endpoints bind locally. Remote exposure requires TLS, authentication, policy, and resource limits.
- Linux native/UI tests always run under Xvfb.

## WebDriver Classic

- Complete element lookup, identity, staleness, attribute/property/text/rect, and script-cloning semantics.
- Complete click interactability and implement W3C key, pointer, and wheel actions.
- Add windows, frames, prompts, history completion, and page-load strategies.
- Pin a WPT/wdspec revision with a machine-readable allowlist and documented exclusions.
- Test malformed and oversized input, limits, disconnects, timeouts, and runtime crashes.

BiDi waits until Classic has a stable conformance baseline.

## Recovery

- Add a coordinator for rebuilding application-owned browser state after a server restart.
- Keep native handles, JS object IDs, DOM nodes, callbacks, and codec dependency chains non-replayable.
- Define WebDriver runtime-loss behaviour; default to failing the active command and invalidating the session.
- Stress startup failure, crash loops, close-during-restart, cleanup, and backoff reset.
- Cover JavaFX/frame/MJPEG reattachment.

## Transport and frame hardening

- Benchmark latency, throughput, allocation, copies, and cleanup by provider and platform.
- Specify negotiated limits and backpressure.
- Load-test WebSocket fragmentation, reconnects, slow readers, and bounded queues.
- Harden Windows pipe cancellation and mapped-file cleanup.
- Compare explicit UDS with local ZMQ on Linux and macOS.
- Publish a stateful custom-codec example. Keep MJPEG as the built-in browser-compatible stream.

## Remote security

- Provide a reference reverse-proxy or tunnel configuration.
- Specify token rotation, TLS trust, origins, capabilities, profiles, and runtime arguments.
- Enforce request, result, frame, connection, queue, and rate limits.
- Fuzz envelope, WebSocket, and HTTP parsing and test degraded networks.

## Public API and maintenance

- Decide whether in-process initialization should remain explicit before changing toolkit compatibility entry points.
- Keep Maven bytecode enforcement on distributable modules and isolate newer build tooling.
- Evaluate dependency and JavaFX baseline changes separately.
- Keep the runtime packager independently versioned and bridge releases independent from CEF patch updates.

## Next

1. Create the WebDriver supported-command inventory and first wdspec allowlist.
2. Close element and interaction gaps, including W3C Actions.
3. Lock runtime-loss semantics and add recovery stress coverage.
4. Complete browsing contexts and prompts.
5. Benchmark and harden transports and frames.
6. Add the remote deployment boundary and adversarial tests.
7. Revisit in-process initialization and JavaFX only as explicit decisions.
