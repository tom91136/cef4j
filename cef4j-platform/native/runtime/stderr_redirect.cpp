// JNI methods to redirect stderr (fd 2) to a pipe and return a FileInputStream
// for the read end. This allows Java to capture CEF's native log output (and
// subprocess stderr, which inherits the redirected fd) and route it through SLF4J.
//
// redirectStderr0 returns a two-element jobjectArray:
//   [0] = FileInputStream for the pipe read end (CEF native stderr)
//   [1] = FileOutputStream for the original stderr (so Java can reassign System.err)

#include "jni_util.h"
#include "runtime_stubs.gen.h"

#include <stdio.h>
#include <string.h>

#if !defined(_WIN32)
#include <signal.h>
#include <unistd.h>
#if defined(__APPLE__) || defined(__linux__)
#include <execinfo.h>
#include <fcntl.h>
#endif

// Saved fd for the crash handler to write diagnostics to original stderr.
static int origStderrFd = -1;
// Read end retained by the Java logger thread. Tracked so tests can verify that
// neither private descriptor leaks into CEF subprocesses across exec().
static int stderrReadFd = -1;
// Set from Java after cef_initialize to hold the exact chrome_debug.log path.
static char crashLogPath[4096] = {0};

static void crashHandler(int sig) {
    if (origStderrFd >= 0) {
        // CEF's LOG(FATAL) writes to chrome_debug.log but NOT to stderr, so
        // the redirected pipe is empty at this point. Write the crash notice
        // and backtrace to the original stderr.
        if (crashLogPath[0] != '\0') {
            const char prefix[] = "\n[cef4j] Native crash detected. CEF log: ";
            write(origStderrFd, prefix, sizeof(prefix) - 1);
            write(origStderrFd, crashLogPath, strlen(crashLogPath));
            write(origStderrFd, "\n", 1);
        } else {
            const char msg[] =
                "\n[cef4j] Native crash detected. "
                "Check chrome_debug.log in the CEF cache directory for details.\n";
            write(origStderrFd, msg, sizeof(msg) - 1);
        }

#if defined(__APPLE__) || defined(__linux__)
        const char btMsg[] = "[cef4j] Native backtrace:\n";
        write(origStderrFd, btMsg, sizeof(btMsg) - 1);
        void* frames[64];
        int count = backtrace(frames, 64);
        backtrace_symbols_fd(frames, count, origStderrFd);
#endif
    }
    // Re-raise with default handler so the process terminates normally.
    signal(sig, SIG_DFL);
    raise(sig);
}

CEF4J_JNI_EXPORT_RT(void, NativeStderr, setCrashLogPath0)(JNIEnv* env, jclass, jstring jpath) {
    const char* path = env->GetStringUTFChars(jpath, nullptr);
    if (path) {
        snprintf(crashLogPath, sizeof(crashLogPath), "%s", path);
        env->ReleaseStringUTFChars(jpath, path);
    }
}

static jobject makeFdObject(JNIEnv* env, int fd) {
    jclass fdClass = env->FindClass("java/io/FileDescriptor");
    jmethodID fdCtor = env->GetMethodID(fdClass, "<init>", "()V");
    jobject fdObj = env->NewObject(fdClass, fdCtor);
    jfieldID fdField = env->GetFieldID(fdClass, "fd", "I");
    env->SetIntField(fdObj, fdField, fd);
    return fdObj;
}

static bool setCloseOnExec(int fd) {
    int flags = fcntl(fd, F_GETFD);
    return flags >= 0 && fcntl(fd, F_SETFD, flags | FD_CLOEXEC) == 0;
}

CEF4J_JNI_EXPORT_RT(jobjectArray, NativeStderr, redirectStderr0)(JNIEnv* env, jclass) {
    // Save original stderr before redirect
    int savedStderr = dup(STDERR_FILENO);
    if (savedStderr < 0) return nullptr;
    if (!setCloseOnExec(savedStderr)) {
        close(savedStderr);
        return nullptr;
    }

    int fds[2];
    if (pipe(fds) != 0) {
        close(savedStderr);
        return nullptr;
    }
    if (!setCloseOnExec(fds[0]) || !setCloseOnExec(fds[1])) {
        close(fds[0]);
        close(fds[1]);
        close(savedStderr);
        return nullptr;
    }
    // fds[0] = read end, fds[1] = write end
    // dup2 clears FD_CLOEXEC on fd 2, so CEF subprocess stderr remains captured while the
    // logger's private read end and saved Java stderr are closed during exec.
    if (dup2(fds[1], STDERR_FILENO) < 0) {
        close(fds[0]);
        close(fds[1]);
        close(savedStderr);
        return nullptr;
    }
    close(fds[1]);

    // Install crash handler for fatal signals. CEF's LOG(FATAL) uses
    // __builtin_trap() (SIGTRAP) on Linux. Also handle SIGABRT for abort().
    origStderrFd = savedStderr;
    stderrReadFd = fds[0];
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

CEF4J_JNI_EXPORT_RT(jboolean, NativeStderr, internalDescriptorsCloseOnExec0)(JNIEnv*, jclass) {
    if (origStderrFd < 0 || stderrReadFd < 0) return JNI_FALSE;
    int stderrFlags = fcntl(origStderrFd, F_GETFD);
    int readFlags = fcntl(stderrReadFd, F_GETFD);
    return stderrFlags >= 0 && readFlags >= 0 && (stderrFlags & FD_CLOEXEC) != 0 && (readFlags & FD_CLOEXEC) != 0;
}

#else
#include <io.h>
#include <fcntl.h>
#include <signal.h>
#include <windows.h>

// Saved handle for the crash handler to write to original stderr.
static HANDLE origStderrHandle = INVALID_HANDLE_VALUE;
// Set from Java after cef_initialize to hold the exact chrome_debug.log path.
static char crashLogPath[4096] = {0};

CEF4J_JNI_EXPORT_RT(jboolean, NativeStderr, internalDescriptorsCloseOnExec0)(JNIEnv*, jclass) {
    return JNI_TRUE;
}

static void writeCrashMessage(const char* suffix) {
    if (origStderrHandle == INVALID_HANDLE_VALUE) return;
    DWORD written;
    if (crashLogPath[0] != '\0') {
        const char prefix[] = "\n[cef4j] Native crash detected";
        WriteFile(origStderrHandle, prefix, sizeof(prefix) - 1, &written, NULL);
        if (suffix) WriteFile(origStderrHandle, suffix, (DWORD)strlen(suffix), &written, NULL);
        const char mid[] = ". CEF log: ";
        WriteFile(origStderrHandle, mid, sizeof(mid) - 1, &written, NULL);
        WriteFile(origStderrHandle, crashLogPath, (DWORD)strlen(crashLogPath), &written, NULL);
        WriteFile(origStderrHandle, "\n", 1, &written, NULL);
    } else {
        const char msg[] =
            "\n[cef4j] Native crash detected. "
            "Check chrome_debug.log in the CEF cache directory for details.\n";
        WriteFile(origStderrHandle, msg, sizeof(msg) - 1, &written, NULL);
    }
}

static LONG WINAPI crashExceptionFilter(EXCEPTION_POINTERS* ep) {
    writeCrashMessage(NULL);
    return EXCEPTION_CONTINUE_SEARCH;
}

static void crashSignalHandler(int sig) {
    writeCrashMessage(" (SIGABRT)");
    signal(sig, SIG_DFL);
    raise(sig);
}

CEF4J_JNI_EXPORT_RT(void, NativeStderr, setCrashLogPath0)(JNIEnv* env, jclass, jstring jpath) {
    const char* path = env->GetStringUTFChars(jpath, nullptr);
    if (path) {
        snprintf(crashLogPath, sizeof(crashLogPath), "%s", path);
        env->ReleaseStringUTFChars(jpath, path);
    }
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
