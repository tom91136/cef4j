# Debugging Native Crashes in cef4j

## Symptoms

Native crashes manifest as:
- JVM termination with no Java stack trace
- Surefire reporting "The forked VM terminated without properly saying goodbye"
- Exit codes like 133 (128 + SIGTRAP), 134 (128 + SIGABRT), 139 (128 + SIGSEGV)
- glibc errors: `malloc(): invalid size (unsorted)`, `malloc(): corrupted top size`

## Step 1: Isolate the Crash

Maven surefire hides native crash output. Create a standalone `.java` reproducer:

```java
import net.kurobako.cef4j.gen.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class CrashTest {
    public static void main(String[] args) throws Exception {
        net.kurobako.cef4j.SystemBootstrap.load();
        Path cacheDir = Files.createTempDirectory("cef4j-test-");
        cacheDir.toFile().deleteOnExit();
        net.kurobako.cef4j.CefApp app = net.kurobako.cef4j.CefApp.getInstance(
            cacheDir.toAbsolutePath().toString(), null, true, null);
        app.initialize();

        // ... reproduce the crash here ...

        app.dispose();
        System.err.println("=== done");
    }
}
```

Run directly (outside maven) to see native error output:

```sh
java -cp "cef4j-api/target/classes:$HOME/.m2/repository/javax/annotation/javax.annotation-api/1.3.2/javax.annotation-api-1.3.2.jar:$HOME/.m2/repository/org/slf4j/slf4j-api/2.0.17/slf4j-api-2.0.17.jar" \
  -Djava.library.path=/tmp/cef4j-cache/linux64 \
  --source 21 CrashTest.java
```

## Step 2: Bisect with Print Statements

Add `System.err.println` + `System.err.flush()` before and after suspect operations.
The last printed line tells you which native call crashed.

Common pattern: the crash occurs NOT during the suspect call itself but during
a later `release()` or `close()` — this indicates heap corruption from an earlier operation.

## Step 3: Classify the Bug

| Symptom | Likely Cause |
|---|---|
| Crash during `close()` after passing object as argument | Missing `add_ref` — CEF consumed a reference via `CppToC::Unwrap()` |
| `malloc(): invalid size` | Heap corruption, often from leaked `cef_string_userfree_t` or double-free |
| Crash in `release()` at shutdown | Object freed twice, or freed after CEF shutdown |
| SIGTRAP with no malloc error | CEF `DCHECK` assertion failure (usually indicates API misuse) |
| SIGSEGV in generated JNI code | Null native pointer, stale pointer, or wrong struct cast |

## Step 4: ASAN / Valgrind

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

Or use Valgrind (slower but no rebuild needed):

```sh
valgrind --suppressions=cef4j-native/cef.supp \
  java -Djava.library.path=/tmp/cef4j-cache/linux64 \
  --source 21 CrashTest.java
```

## Step 5: GDB

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

## Updating the .so After Rebuilding

The standalone test runner loads `libcef4j.so` from `/tmp/cef4j-cache/linux64/`.
After rebuilding the native module, copy the new .so:

```sh
cp cef4j-native/target/cmake-build/libcef4j.so /tmp/cef4j-cache/linux64/
```

Maven surefire uses the .so from the installed artifact, so `mvn install -pl cef4j-native`
is needed before `mvn test -pl cef4j-api`.
