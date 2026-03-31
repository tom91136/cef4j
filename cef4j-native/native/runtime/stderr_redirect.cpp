// JNI methods to redirect stderr (fd 2) to a pipe and return a FileInputStream
// for the read end. This allows Java to capture CEF's native log output (and
// subprocess stderr, which inherits the redirected fd) and route it through SLF4J.
//
// N_RedirectStderr returns a two-element jobjectArray:
//   [0] = FileInputStream for the pipe read end (CEF native stderr)
//   [1] = FileOutputStream for the original stderr (so Java can reassign System.err)

#include <jni.h>

#if !defined(_WIN32)
#include <unistd.h>

static jobject makeFdObject(JNIEnv* env, int fd) {
    jclass fdClass = env->FindClass("java/io/FileDescriptor");
    jmethodID fdCtor = env->GetMethodID(fdClass, "<init>", "()V");
    jobject fdObj = env->NewObject(fdClass, fdCtor);
    jfieldID fdField = env->GetFieldID(fdClass, "fd", "I");
    env->SetIntField(fdObj, fdField, fd);
    return fdObj;
}

extern "C" JNIEXPORT jobjectArray JNICALL
Java_net_kurobako_cef4j_NativeStderr_N_1RedirectStderr(JNIEnv* env, jclass) {
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
#include <windows.h>

static jobject makeFdObject(JNIEnv* env, int fd) {
    jclass fdClass = env->FindClass("java/io/FileDescriptor");
    jmethodID fdCtor = env->GetMethodID(fdClass, "<init>", "()V");
    jobject fdObj = env->NewObject(fdClass, fdCtor);

    HANDLE handle = (HANDLE)_get_osfhandle(fd);
    jfieldID handleField = env->GetFieldID(fdClass, "handle", "J");
    if (handleField) {
        env->SetLongField(fdObj, handleField, (jlong)handle);
    } else {
        env->ExceptionClear();
        jfieldID fdField = env->GetFieldID(fdClass, "fd", "I");
        env->SetIntField(fdObj, fdField, fd);
    }
    return fdObj;
}

extern "C" JNIEXPORT jobjectArray JNICALL
Java_net_kurobako_cef4j_NativeStderr_N_1RedirectStderr(JNIEnv* env, jclass) {
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
