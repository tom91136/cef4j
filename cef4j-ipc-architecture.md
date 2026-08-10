# cef4j IPC Architecture Plan

## Overview

cef4j is an out-of-process JavaFX embedding of the Chrome Embedded Framework (CEF), communicating
with a native CEF helper process over a local IPC channel. The frame buffer is handled separately
via a memory-mapped file. This document records all architectural decisions made during design.

---

## Constraints

- Java 8 target for the JVM-side library
- C++17 for the native CEF helper process
- OSR-only (off-screen rendering) — no Views API
- CEF helper is already a native binary — native deps there are acceptable
- Java side must be pure Java — no secondary JNI extraction alongside CEF
- Codegen pipeline: Scala 3, walking CEF C API headers to emit Java and C++ sources
- Library distribution — jar size and transitive deps matter to consumers

---

## Architecture Overview

```
┌─────────────────────────────────────────┐
│  Generated CEF API                      │  Scala 3 codegen from CEF headers
│  CefBrowser, CefClient, handlers        │
├─────────────────────────────────────────┤
│  CefSessionImpl                         │  ~200 lines, hand-written once
│  correlation, routing, intercept        │
├─────────────────────────────────────────┤
│  CefTransport (interface)               │  pluggable transport layer
├──────────────────────┬──────────────────┤
│  Control plane       │  Frame plane     │
│  ZMQ PAIR / TCP      │  mmap double buf │
│  jeromq (Java)       │  MappedByteBuffer│
│  libzmq (C++)        │  + ZMQ push sig  │
└──────────────────────┴──────────────────┘
```

---

## Decision 1: Transport Library — ZeroMQ

**Chosen:** ZeroMQ (`libzmq` C++, `jeromq` Java)

**Alternatives considered:**
- gRPC — heavy C++ build (~15–20MB), Netty shaded (~10MB Java), bidirectional model fights CEF
  callback architecture (requires dual servers or bidi streaming hack)
- Apache Thrift — ~1MB C++, ~400KB Java, battle-tested in JetBrains JCEF, but RPC layer is
  redundant given owned codegen; dual TNonblockingServer awkward for bidirectional
- Raw TCP socket — viable, eliminates all deps, but loses large message handling, multipart
  framing, and HWM backpressure
- nanomsg/nng — cleaner API than ZeroMQ but no pure Java transport impl (all JNI); ruled out
- RSocket — strong Java story but weak rsocket-cpp; C++ dep not justified

**Rationale:**
- `jeromq` is pure Java — no secondary native extraction problem alongside CEF
- ZeroMQ PAIR socket is semantically a 1:1 bidirectional pipe — exact match for the use case
- Large message handling: automatic framing, zero-copy send via `zmq_msg_init_data`, HWM
  backpressure, multipart envelope+payload separation without concatenation
- Transport pluggability: `CefTransport` interface allows swapping to raw TCP, UDS, or WebSocket
  without touching `CefSessionImpl`
- libzmq (~300KB) is significantly lighter than gRPC (~15–20MB) on the C++ side

**ZeroMQ socket pattern:** PAIR (not DEALER/ROUTER — controlled process lifecycle, 1:1 only)

**Known limitation:** PAIR does not handle reconnection robustly. Mitigation: detect disconnect
via `onDisconnect`, restart the helper process, rebind. Upgrade to DEALER/ROUTER only if
reconnection becomes a real requirement.

---

## Decision 2: Serialisation — Hand-rolled wire protocol

**Chosen:** Custom wire protocol with hand-rolled flyweight accessors, emitted by Scala 3 codegen

**Alternatives considered:**
- FlatBuffers — zero-copy read via offset arithmetic, good fit, but `flatc` build step adds
  dependency not owned; vtable overhead on fixed fields; accessor pattern reimplementable
- SBE (Simple Binary Encoding) — strongest zero-copy story, Agrona DirectBuffer uses Unsafe
  for near-C++ struct overlay performance, HPC/finance validated; worth revisiting for
  high-frequency V8 pool messages if profiling warrants
- Protobuf — excellent C++ and Java runtimes, but full deserialise/copy on every message; no
  zero-copy read path
- Thrift binary — same full-parse-copy problem; schema evolution worse than Protobuf
- MessagePack — schema-less, loses codegen story

**Rationale:**
- Codegen pipeline already walks CEF C API headers — emitting layout specs is incremental work
- Java flyweight: `ByteBuffer` accessor with offset arithmetic, no heap allocation on scalar/struct
  read path
- C++ struct overlay: `reinterpret_cast` on receive buffer, no runtime dependency
- Full wire ownership enables `RecordingTransport` / time-travel replay (see Decision 7)
- No `flatc` or `protoc` binary in the build chain — full codegen control

**Object field strategy:**
- Scalar fields: direct `ByteBuffer.getInt/getLong` — zero allocation
- Nested structs: flyweight view (`RectView`, etc.) wrapping the same buffer — zero allocation,
  ephemeral, annotated `@Ephemeral` as caller contract
- Strings: two tiers — `urlString()` allocates, `urlEquals()` compares directly in buffer
- Arrays of structs: indexed flyweight accessor — zero allocation, ephemeral
- Pooled flyweight instances via `ThreadLocal` for hot paths (SBE pattern)

---

## Decision 3: Protocol Shape — Three Primitives

**All IPC communication reduces to three message types:**

```
┌──────────────────────────────────────────────────────────────┐
│ Primitive    │ Direction  │ corrId │ Example                  │
├──────────────┼────────────┼────────┼──────────────────────────┤
│ REQUEST      │ JVM → CEF  │ yes    │ LoadURL, ExecuteJS        │
│ EVENT        │ CEF → JVM  │ no     │ OnTitleChange, OnLoadEnd  │
│ INTERCEPT    │ CEF → JVM  │ yes    │ OnBeforeResourceLoad      │
└──────────────────────────────────────────────────────────────┘
```

INTERCEPT is an EVENT that expects a REQUEST response keyed by the same corrId. The JVM-side
pending map handles both directions with the same machinery, direction inverted.

**Envelope layout (14 bytes fixed header, all little-endian):**

```
┌──────────┬──────┬───────┬─────────┬───────────┬───────────┐
│ len : 4  │ kind │ flags │ corrId  │ messageId │ payload   │
│          │  1   │   1   │   4     │     4     │     n     │
└──────────┴──────┴───────┴─────────┴───────────┴───────────┘

kind:  REQUEST=1  RESPONSE=2  EVENT=3
       INTERCEPT=4  INTERCEPT_RESPONSE=5

flags (bitmask): HAS_CONTINUATION=0x01  IS_CONTINUATION=0x02
                 (other bits reserved)

len:       payload byte count (excludes header)
corrId:    -1 if none (events); otherwise correlates request/response
           or intercept/intercept_response
messageId: dispatch key — identifies which generated message type the
           payload encodes (e.g. LoadURL=42, OnLoadEnd=43, ...)
```

`len` is redundant under ZMQ framing but kept so the same envelope works
unchanged over stream-oriented transports (raw TCP, UDS) without an
intermediate framer. Resolved 2026-04-27 — the original diagram showed
10 bytes; messageId was missing.

---

## Decision 4: Session Layer — CefSessionImpl

Hand-written once (~200 lines), sits above `CefTransport`, below generated API.

**Responsibilities:**
- Correlation ID allocation and pending request map (`ConcurrentHashMap<Integer, PendingRequest>`)
- Handler registry for persistent event subscriptions (`CopyOnWriteArrayList` per messageId —
  reads vastly outnumber writes)
- Intercept context lifecycle — expiry, double-resolution guard, default allow on no handler
- Outbound queue — ZMQ socket not thread-safe, single dedicated send thread with
  `LinkedBlockingQueue`
- Timeout scheduling via `ScheduledExecutorService`

**Key interfaces:**

```java
interface CefSession extends Closeable {
    <R extends CefMessageView> CompletableFuture<R>
        request(CefMessageEncoder msg, CefMessageDecoder<R> decoder);

    ByteBuffer requestSync(CefMessageEncoder msg);   // request(...).get() + timeout

    void send(CefMessageEncoder msg);                // fire and forget

    <E extends CefMessageView> HandlerRegistration
        on(int messageId, CefMessageDecoder<E> decoder, Consumer<E> handler);

    <E extends CefMessageView> HandlerRegistration
        intercept(int messageId, CefMessageDecoder<E> decoder, InterceptHandler<E> handler);
}
```

**Intercept contract:**
- CEF IO thread parks on a condition variable after sending INTERCEPT message
- JVM handler resolves via `InterceptContext.allow()` / `block()` / `respond()`
- `CefCallback` (returned-handle case) calls `ctx.allow()` internally — user never touches
  `InterceptContext` directly
- Default timeout: 2000ms — on expiry, `ctx.isExpired()` returns true, `CefCallback` becomes
  a no-op, CEF unparks with default action
- Default allow if no handler registered — CEF network stack never stalls
- First registered intercept handler wins — multiple registrations for same type undefined

---

## Decision 5: Generated API Shape — mirrors CEF C API

The public API surface mirrors CEF's C API exactly — same signatures, same callback patterns,
same handler abstractions. Wire protocol is invisible to the caller.

**Four generated patterns per CEF function:**

**Simple call (scalar in/out):**
```java
double zoom = browser.getZoomLevel();
```

**Object in/out:**
```java
BrowserInfo info = cef.createBrowser(new BrowserSettings() {{
    url = "https://example.com"; width = 1920; height = 1080;
}});
```

**Call expecting a callback (persistent handler):**
```java
browser.addLoadHandler(new CefLoadHandler() {
    @Override public void onLoadEnd(int browserId, String url, int httpStatus) { ... }
});
```

**Call returning a callback handle:**
```java
browser.addRequestHandler(new CefRequestHandler() {
    @Override public ReturnValue onBeforeResourceLoad(
        int browserId, String url, String method, CefCallback callback
    ) {
        fetchAuthToken(url).thenAccept(token -> callback.cont());
        return ReturnValue.CONTINUE_ASYNC;
    }
});
```

**Sync and async variants both generated from same AST node:**
```java
// sync — generated
BrowserInfo info = cef.createBrowser(settings);

// async — generated
CompletableFuture<BrowserInfo> info = cef.createBrowserAsync(settings);
```

`requestSync` is `request(...).get(timeout)` — no separate code path.

---

## Decision 6: Frame Transport — Memory-Mapped File

**Chosen:** Double-buffered mmap with ZMQ push signal on the control socket

**Rationale:**
- OSR render callback writes directly into shared mmap segment — zero copy between CEF render
  thread and JVM consumer
- Completely orthogonal to control plane — `FrameTransport` is a separate interface
- No frame data on the control socket — keeps control plane latency predictable

**Double-buffer protocol:**
```
Segment A [width × height × 4 bytes]  ← CEF writing
Segment B [width × height × 4 bytes]  ← JVM reading

Signal (1 byte on control channel):
  CEF: "A ready" → JVM reads A, CEF writes B
  CEF: "B ready" → JVM reads B, CEF writes A
```

**Frame metadata per frame:**
```java
record FrameMetadata(
    int sequenceId,
    long timestampNanos,
    PixelFormat format,       // BGRA by default from CEF OSR
    List<Rect> dirtyRects     // provided by CEF for free — use for partial repaints
)
```

**Frame transport interface:**
```java
interface FrameTransport extends Closeable {
    void onFrame(FrameConsumer consumer);

    @FunctionalInterface
    interface FrameConsumer {
        void accept(int width, int height, ByteBuffer pixels, FrameMetadata meta);
        // ByteBuffer backed by mmap — DO NOT retain reference past callback
    }
}
```

**Open issues:**
- Segment allocation: CEF helper allocates (knows dimensions post `OnAfterCreated`), JVM attaches.
  Segment path/fd passed over control channel at handshake time.
- Resize: viewport resize invalidates segment. Requires `FRAME_RESIZE` control message and
  segment reallocation before next frame. Design this handshake explicitly.
- Backpressure: drop frames if JVM cannot consume fast enough — do not block CEF render thread.
  Drop with dirty rect accumulation for partial repaint efficiency.
- Threading: document which thread `FrameConsumer` is called on. JavaFX callers need
  `Platform.runLater`. Consider `@CalledOn` annotation in generated code.

---

## Decision 7: Transport Pluggability and Time-Travel Replay

**CefTransport interface:**
```java
interface CefTransport extends Closeable {
    void send(ByteBuffer frame) throws CefTransportException;
    void onReceive(Consumer<ByteBuffer> handler);
    void onDisconnect(Runnable handler);
    boolean isConnected();
}
```

**Provided implementations:**
- `ZmqTransport` — primary, PAIR socket over TCP
- `UnixSocketTransport` — local Linux/macOS, lower latency than ZMQ for pure local use
- `NamedPipeTransport` — local Windows
- `RecordingTransport` — decorator, records all frames to a `MessageLog`
- `ReplayTransport` — replays a `MessageLog` without a live CEF process

**Time-travel replay:**
Since the wire protocol is owned and the envelope is fully typed, a `RecordingTransport`
decorator can log every inbound and outbound frame with nanosecond timestamps. A
`ReplayTransport` replays the log deterministically — no CEF process required. Enables:
- Deterministic unit testing of JVM-side session logic
- Exact bug reproduction from production recordings
- Step-through debugging of recorded sessions

This is only possible because the wire format is owned. A third-party RPC library's opaque
wire format would prevent this.

---

## Decision 8: Local Transport Optimisation

For local-machine deployments (the primary use case), ZMQ can be replaced with a raw socket
transport with no behavioural change to `CefSessionImpl`.

**Platform recommendations:**

| Platform     | Transport               | Notes                              |
|--------------|-------------------------|------------------------------------|
| Linux/macOS  | `AF_UNIX` UDS           | ~2–5µs RTT, no TCP stack overhead  |
| Windows 10+  | `AF_UNIX` or named pipe | Same Java NIO API for UDS          |
| Remote/cloud | ZMQ TCP                 | Reconnection, HWM backpressure     |

**Java UDS (Java 16+):**
```java
var address = UnixDomainSocketAddress.of("/tmp/cef4j.sock");
var channel = SocketChannel.open(StandardProtocolFamily.UNIX);
channel.connect(address);
```

For Java 8 target: `junixsocket` library provides backport.

**Why keep ZMQ as default despite raw socket being viable:**
- Large message handling — automatic framing, zero-copy send via `zmq_msg_init_data`
- HWM backpressure — relevant for V8 pool high-frequency JS evaluation use case
- Multipart framing — envelope and payload as separate ZMQ frames, no concatenation
- Negligible control-plane overhead — ZMQ overhead only measurable at thousands of RPS

---

## Decision 9: Composition Root

```java
record CefConnection(
    CefSession               session,
    Optional<FrameTransport> frames    // absent for headless / V8 pool use
) {
    static Builder builder() { return new Builder(); }
}

// example
CefConnection conn = CefConnection.builder()
    .control(new ZmqTransport("tcp://127.0.0.1:5555"))
    .frames(new MmapFrameTransport(shmPath, width, height))
    .build();
```

---

## Decision 10: Codegen Pipeline

**Input:** CEF C API headers (`cef_*.h`)

**Pipeline:**
```
CEF header AST (Scala 3 parser)
  └─→ per-message layout spec (internal IR)
        └─→ Java flyweight emitter     → LoadUrlView.java, OnLoadEndView.java, ...
        └─→ Java encoder emitter       → LoadUrlEncoder.java, ...
        └─→ Java handler emitter       → CefLoadHandler.java, CefRequestHandler.java, ...
        └─→ C++ struct overlay emitter → LoadUrlRequest.h, OnLoadEndView.h, ...
        └─→ C++ handler bridge emitter → CefRequestHandlerBridge.cpp, ...
        └─→ dispatch table emitter     → MessageType → decoder mapping
```

**Layout spec (internal IR):**
```
primitive:   i8 | i16 | i32 | i64 | f32 | f64 | bool
fixed:       struct of primitives — known size, no indirection
variable:    length-prefixed bytes | utf8 string | fixed[]

layout rules:
  - fixed fields packed sequentially, natural alignment
  - variable fields: 4-byte length prefix + 4-byte offset at fixed position, data at end
  - all little-endian (matches x86, CEF's native byte order)
  - optional fields: present_mask i32 as first fixed field, bit N = field N present
```

**What is NOT generated (hand-written once):**
- `CefSessionImpl` (~200 lines)
- `Envelope` codec (14-byte header read/write)
- `CefTransport` implementations
- `FlatWriter` (trivial write-side cursor, ~30 lines)
- `FrameTransport` implementations

---

## Dependency Summary

**Java (library consumer sees):**

| Dependency | Size    | Native | Purpose              |
|------------|---------|--------|----------------------|
| jeromq     | ~550KB  | no     | ZMQ transport        |
| slf4j-api  | ~7KB    | no     | jeromq transitive    |

No other runtime dependencies. Pure Java throughout.

**C++ (CEF helper binary):**

| Dependency | Size    | Purpose              |
|------------|---------|----------------------|
| libzmq     | ~300KB  | ZMQ transport        |
| CEF        | ~1.3GB  | browser engine       |

---

## Open Issues

1. **PAIR reconnection** — define restart policy for CEF helper crash. Consider DEALER/ROUTER
   upgrade if reconnection becomes a real requirement.

2. **mmap segment allocation** — confirm CEF helper allocates, JVM attaches. Handshake message
   design (segment path, width, height, pixel format) needed.

3. **Viewport resize** — `FRAME_RESIZE` control message and double-buffer reallocation protocol
   needs explicit design before first OSR implementation.

4. **Backpressure on intercepts** — if JVM intercept handler is consistently slow, CEF IO thread
   stalls. Consider a pre-registered synchronous filter chain evaluated before async dispatch.

5. **Java 8 UDS** — `UnixDomainSocketAddress` requires Java 16+. Decide whether `junixsocket`
   is an acceptable dep for local transport optimisation, or defer UDS to a future release
   targeting Java 16+.

6. **V8 pool message frequency** — if JS evaluation round-trips exceed ~1000/sec, profile
   whether ZMQ overhead justifies switching local transport to raw UDS. Likely a non-issue
   until demonstrated by profiling.

7. **`FrameConsumer` threading contract** — document and enforce which thread the frame callback
   is called on. JavaFX integration requires `Platform.runLater` if not FX thread.
