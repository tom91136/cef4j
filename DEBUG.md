# Debugging Native Crashes in cef4j

## Symptoms

Native crashes manifest as:
- JVM termination with no Java stack trace
- Surefire reporting "The forked VM terminated without properly saying goodbye"
- Exit codes like 133 (128 + SIGTRAP), 134 (128 + SIGABRT), 139 (128 + SIGSEGV)
- glibc errors: `malloc(): invalid size (unsorted)`, `malloc(): corrupted top size`

## Step 1: Check chrome_debug.log

CEF writes its own log file alongside the user data directory. FATAL errors that
kill the process appear here even when they don't appear in application logs.

Look for the most recent `chrome_debug.log`:

```sh
find /tmp -name 'chrome_debug.log' -mmin -5 2>/dev/null
```

Grep for FATAL:

```sh
grep FATAL /tmp/cef4j-test-*/chrome_debug.log
```

### Why FATAL errors don't appear in application logs

CEF's `LOG(FATAL)` writes to `chrome_debug.log` via its internal file logger but
does **not** write to stderr. It then calls `__builtin_trap()` (SIGTRAP on
Linux), killing the process immediately. Since the message never reaches the
stderr pipe, the SLF4J daemon reader thread has nothing to capture.

Normal CEF output (WARNING, ERROR) does go to stderr and appears in SLF4J.

The crash signal handler in `stderr_redirect.cpp` catches SIGTRAP/SIGABRT and
prints the crash notice with the exact `chrome_debug.log` path (if the cache
directory was set) plus a native backtrace on macOS/Linux:

```
[cef4j] Native crash detected. CEF log: /tmp/cef4j-cache/chrome_debug.log
[cef4j] Native backtrace:
0   libcef4j.dylib  0x...  crashHandler + 123
...
```

## Step 2: Disable the Stderr Redirect

For interactive debugging, bypass `NativeStderr` so all native output goes
straight to the terminal. In your test code, load the library manually instead
of calling `SystemBootstrap.load()`:

```java
// Skip NativeStderr.install() — load libs directly
Path cacheDir = Path.of("/tmp/cef4j-cache/linux64");
System.load(cacheDir.resolve("libcef.so").toAbsolutePath().toString());
System.load(cacheDir.resolve("libcef4j.so").toAbsolutePath().toString());
```

Or, if using surefire, add `-Dcef4j.disableStderrRedirect=true` — `NativeStderr.install()`
honours this flag and leaves native stderr unredirected. The simplest approach for
hard-to-reproduce crashes is a standalone reproducer (see Step 3).

## Step 3: Create a Standalone Reproducer

Maven surefire hides native crash output and forks a JVM that may crash before
producing any report. Create a standalone `.java` file:

```java
import net.kurobako.cef4j.gen.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class CrashTest {
    public static void main(String[] args) throws Exception {
        // Load without stderr redirect so FATAL output is visible
        Path cacheDir = Path.of("/tmp/cef4j-cache/linux64");
        System.load(cacheDir.resolve("libcef.so").toAbsolutePath().toString());
        System.load(cacheDir.resolve("libcef4j.so").toAbsolutePath().toString());

        Path dataDir = Files.createTempDirectory("cef4j-test-");
        dataDir.toFile().deleteOnExit();
        net.kurobako.cef4j.Cef.INSTANCE
            .cachePath(dataDir.toAbsolutePath().toString())
            .initialize();

        // ... reproduce the crash here ...

        net.kurobako.cef4j.Cef.INSTANCE.dispose();
        System.err.println("=== done");
    }
}
```

Run directly:

```sh
java -cp "cef4j-api/target/classes:$HOME/.m2/repository/javax/annotation/javax.annotation-api/1.3.2/javax.annotation-api-1.3.2.jar:$HOME/.m2/repository/org/slf4j/slf4j-api/2.0.17/slf4j-api-2.0.17.jar:$HOME/.m2/repository/org/slf4j/slf4j-simple/2.0.17/slf4j-simple-2.0.17.jar" \
  --source 21 CrashTest.java
```

## Step 4: Classify the Crash

| Symptom | Likely Cause |
|---|---|
| `invalid version -1` in chrome_debug.log | `cef_api_hash` not called — check `JNI_OnLoad` runs (symbol must be exported in `cef4j.map`) |
| Crash during `close()` after passing object as argument | Missing `add_ref` — CEF consumed a reference via `CppToC::Unwrap()` |
| `malloc(): invalid size` | Heap corruption, often from leaked `cef_string_userfree_t` or double-free |
| Crash in `release()` at shutdown | Object freed twice, or freed after CEF shutdown |
| SIGTRAP with no malloc error | CEF `DCHECK` / `LOG(FATAL)` assertion — check `chrome_debug.log` for the message |
| SIGSEGV in generated JNI code | Null native pointer, stale pointer, or wrong struct cast |

## Step 5: Updating the .so After Rebuilding

The test suite extracts `libcef4j.so` from the classpath JAR resource on every
run, overwriting any manual copy in `/tmp/cef4j-cache/linux64/`. To test a
rebuilt library:

```sh
# 1. Rebuild
mvn compile -pl cef4j-native

# 2. Copy to BOTH locations
cp cef4j-native/target/cmake-build/libcef4j.so \
   cef4j-api/src/main/resources/native/linux64/libcef4j.so
cp cef4j-native/target/cmake-build/libcef4j.so \
   /tmp/cef4j-cache/linux64/libcef4j.so

# 3. Run tests (recompiles cef4j-api, picking up the new resource)
mvn test -pl cef4j-api -Dtest='CefInteropTest#browserLifecycle_onAfterCreatedAndOnLoadEndFire'
```

For standalone reproducers that load from `/tmp/cef4j-cache/linux64/`, only the
second copy is needed.

### Verifying the right library is loaded

```sh
# Check all copies are the same build
md5sum cef4j-native/target/cmake-build/libcef4j.so \
       cef4j-api/src/main/resources/native/linux64/libcef4j.so \
       /tmp/cef4j-cache/linux64/libcef4j.so

# Verify key symbols are exported
nm -D /tmp/cef4j-cache/linux64/libcef4j.so | grep 'JNI_OnLoad\|cef_api'
```

## Step 6: ASAN / Valgrind

For heap corruption, build `libcef4j.so` with AddressSanitizer:

```sh
cd cef4j-native/target/cmake-build
cmake ../.. -DCMAKE_BUILD_TYPE=Debug \
  -DCMAKE_C_FLAGS="-fsanitize=address -fno-omit-frame-pointer" \
  -DCMAKE_CXX_FLAGS="-fsanitize=address -fno-omit-frame-pointer" \
  -DCMAKE_SHARED_LINKER_FLAGS="-fsanitize=address"
make -j$(nproc)
```

Then run with ASAN preloaded:

```sh
LD_PRELOAD=$(gcc -print-file-name=libasan.so) \
  java -Djava.library.path=cef4j-native/target/cmake-build \
  --source 21 CrashTest.java
```

## Step 7: GDB

For SIGTRAP / SIGSEGV, attach GDB:

```sh
gdb --args java -Djava.library.path=/tmp/cef4j-cache/linux64 \
  --source 21 CrashTest.java
```

In GDB:
```
handle SIGSEGV nostop noprint pass  # CEF uses SIGSEGV for sandboxing
handle SIGUSR1 nostop noprint pass  # JVM uses SIGUSR1
run
# When it crashes:
bt        # backtrace
info reg  # registers
```

## Known Bug Patterns

### Missing add_ref on ObjectPtr Parameters

**Root cause:** CEF's C API wrapper functions call `CppToC::Unwrap()` on pointer
arguments, which creates a `CefRefPtr` WITHOUT calling `AddRef()`. It consumes the
caller's reference. If the JNI code passes a raw pointer without first adding a
reference, the refcount drops to 0 and CEF destroys the object.

**Symptoms:** Object passed as argument to `isSame`, `isEqual`, `setValue`,
`setDictionary`, `setBinary`, `setList` etc. becomes invalid after the call.
Crash occurs later when `release()` is called on the destroyed object.

**Fix:** Call `add_ref` on every `ObjectPtr` parameter before passing it to a CEF
C API function. The codegen handles this automatically in `JniCppCodeGen.scala`.

### Leaked cef_string_userfree_t

**Root cause:** `JStringToCefString(env, jStr)` allocates a `cef_string_userfree_t`
via `cef_string_userfree_utf16_alloc()`. If the return value is used as an inline
temporary (e.g., `s->has_key(s, JStringToCefString(env, key))`), the allocated
string is never freed.

**Symptoms:** Memory leak, potential heap corruption under heavy string traffic.

**Fix:** Store the allocated string in a local variable, pass it to the CEF call,
then free it with `cef_string_userfree_free()` after the call.

### Unconditional String Collection Writeback

**Root cause:** String collection params (`cef_string_list_t`, `cef_string_map_t`,
`cef_string_multimap_t`) were always written back to the Java collection after the
CEF call. For `set_*` functions (where the collection is an in-param, not out-param),
this doubled the entries or overwrote an immutable collection.

**Symptoms:** `UnsupportedOperationException` when passing `List.of()` to a setter,
or doubled entries in mutable lists after setter calls.

**Fix:** Only write back for `get_*` functions. For `set_*` functions, just free
the temporary native collection.
