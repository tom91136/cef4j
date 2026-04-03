// JNI methods to redirect stderr (fd 2) to a pipe and return a FileInputStream
// for the read end. This allows Java to capture CEF's native log output (and
// subprocess stderr, which inherits the redirected fd) and route it through SLF4J.
//
// redirectStderr0 returns a two-element jobjectArray:
//   [0] = FileInputStream for the pipe read end (CEF native stderr)
//   [1] = FileOutputStream for the original stderr (so Java can reassign System.err)

#include "jni_util.h"
#include "runtime_stubs.gen.h"

#if !defined(_WIN32)
#include <signal.h>
#include <unistd.h>

// Saved fd for the crash handler to write diagnostics to original stderr.
static int origStderrFd = -1;

static void crashHandler(int sig) {
    if (origStderrFd >= 0) {
        // CEF's LOG(FATAL) writes to chrome_debug.log but NOT to stderr, so
        // the redirected pipe is empty at this point. Emit a hint on the
        // original stderr so the user knows where to look.
        const char msg[] =
            "\n[cef4j] Native crash detected. "
            "Check chrome_debug.log in the CEF user-data directory for details.\n"
            "[cef4j] Hint: find /tmp -name chrome_debug.log -mmin 1\n";
        write(origStderrFd, msg, sizeof(msg) - 1);
    }
    // Re-raise with default handler so the process terminates normally.
    signal(sig, SIG_DFL);
    raise(sig);
}

static jobject makeFdObject(JNIEnv* env, int fd) {
    jclass fdClass = env->FindClass("java/io/FileDescriptor");
    jmethodID fdCtor = env->GetMethodID(fdClass, "<init>", "()V");
    jobject fdObj = env->NewObject(fdClass, fdCtor);
    jfieldID fdField = env->GetFieldID(fdClass, "fd", "I");
    env->SetIntField(fdObj, fdField, fd);
    return fdObj;
}

CEF4J_JNI_EXPORT_RT(jobjectArray, NativeStderr, redirectStderr0)(JNIEnv* env, jclass) {
    // Save original stderr before redirect
    int savedStderr = dup(STDERR_FILENO);
    if (savedStderr < 0) return nullptr;

    int fds[2];
    if (pipe(fds) != 0) {
        close(savedStderr);
        return nullptr;
    }
    // fds[0] = read end, fds[1] = write end
    dup2(fds[1], STDERR_FILENO);
    close(fds[1]);

    // Install crash handler for fatal signals. CEF's LOG(FATAL) uses
    // __builtin_trap() (SIGTRAP) on Linux. Also handle SIGABRT for abort().
    origStderrFd = savedStderr;
    struct sigaction sa = {};
    sa.sa_handler = crashHandler;
    sigemptyset(&sa.sa_mask);
    sa.sa_flags = SA_RESETHAND; // one-shot: avoid re-entry loops
    sigaction(SIGABRT, &sa, nullptr);
    sigaction(SIGTRAP, &sa, nullptr);

    // FileInputStream wrapping the pipe read end
    jobject readFd = makeFdObject(env, fds[0]);
    jclass fisClass = env->FindClass("java/io/FileInputStream");
    jmethodID fisCtor = env->GetMethodID(fisClass, "<init>", "(Ljava/io/FileDescriptor;)V");
    jobject fis = env->NewObject(fisClass, fisCtor, readFd);

    // FileOutputStream wrapping the saved original stderr
    jobject savedFd = makeFdObject(env, savedStderr);
    jclass fosClass = env->FindClass("java/io/FileOutputStream");
    jmethodID fosCtor = env->GetMethodID(fosClass, "<init>", "(Ljava/io/FileDescriptor;)V");
    jobject fos = env->NewObject(fosClass, fosCtor, savedFd);

    // Return as Object[2]
    jclass objClass = env->FindClass("java/lang/Object");
    jobjectArray result = env->NewObjectArray(2, objClass, nullptr);
    env->SetObjectArrayElement(result, 0, fis);
    env->SetObjectArrayElement(result, 1, fos);
    return result;
}

#else
#include <io.h>
#include <fcntl.h>
#include <signal.h>
#include <windows.h>

// Saved handle for the crash handler to write to original stderr.
static HANDLE origStderrHandle = INVALID_HANDLE_VALUE;

static LONG WINAPI crashExceptionFilter(EXCEPTION_POINTERS* ep) {
    if (origStderrHandle != INVALID_HANDLE_VALUE) {
        const char msg[] =
            "\n[cef4j] Native crash detected. "
            "Check chrome_debug.log in the CEF user-data directory for details.\n";
        DWORD written;
        WriteFile(origStderrHandle, msg, sizeof(msg) - 1, &written, NULL);
    }
    return EXCEPTION_CONTINUE_SEARCH;
}

static void crashSignalHandler(int sig) {
    if (origStderrHandle != INVALID_HANDLE_VALUE) {
        const char msg[] =
            "\n[cef4j] Native crash detected (SIGABRT). "
            "Check chrome_debug.log in the CEF user-data directory for details.\n";
        DWORD written;
        WriteFile(origStderrHandle, msg, sizeof(msg) - 1, &written, NULL);
    }
    signal(sig, SIG_DFL);
    raise(sig);
}

static jobject makeFdObject(JNIEnv* env, int fd) {
    jclass fdClass = env->FindClass("java/io/FileDescriptor");
    jmethodID fdCtor = env->GetMethodID(fdClass, "<init>", "()V");
    jobject fdObj = env->NewObject(fdClass, fdCtor);

    HANDLE handle = reinterpret_cast<HANDLE>(_get_osfhandle(fd));
    jfieldID handleField = env->GetFieldID(fdClass, "handle", "J");
    if (handleField) {
        env->SetLongField(fdObj, handleField, reinterpret_cast<jlong>(handle));
    } else {
        env->ExceptionClear();
        jfieldID fdField = env->GetFieldID(fdClass, "fd", "I");
        env->SetIntField(fdObj, fdField, fd);
    }
    return fdObj;
}

CEF4J_JNI_EXPORT_RT(jobjectArray, NativeStderr, redirectStderr0)(JNIEnv* env, jclass) {
    // Save original stderr before redirect
    int savedStderr = _dup(_fileno(stderr));
    if (savedStderr < 0) return nullptr;

    int fds[2];
    if (_pipe(fds, 65536, _O_BINARY) != 0) {
        _close(savedStderr);
        return nullptr;
    }
    _dup2(fds[1], _fileno(stderr));
    _close(fds[1]);

    // Install crash handlers. SEH catches __debugbreak() (CEF's LOG(FATAL) on
    // Windows). signal() catches abort().
    origStderrHandle = reinterpret_cast<HANDLE>(_get_osfhandle(savedStderr));
    SetUnhandledExceptionFilter(crashExceptionFilter);
    signal(SIGABRT, crashSignalHandler);

    // FileInputStream wrapping the pipe read end
    jobject readFd = makeFdObject(env, fds[0]);
    jclass fisClass = env->FindClass("java/io/FileInputStream");
    jmethodID fisCtor = env->GetMethodID(fisClass, "<init>", "(Ljava/io/FileDescriptor;)V");
    jobject fis = env->NewObject(fisClass, fisCtor, readFd);

    // FileOutputStream wrapping the saved original stderr
    jobject savedFd = makeFdObject(env, savedStderr);
    jclass fosClass = env->FindClass("java/io/FileOutputStream");
    jmethodID fosCtor = env->GetMethodID(fosClass, "<init>", "(Ljava/io/FileDescriptor;)V");
    jobject fos = env->NewObject(fosClass, fosCtor, savedFd);

    // Return as Object[2]
    jclass objClass = env->FindClass("java/lang/Object");
    jobjectArray result = env->NewObjectArray(2, objClass, nullptr);
    env->SetObjectArrayElement(result, 0, fis);
    env->SetObjectArrayElement(result, 1, fos);
    return result;
}
#endif
