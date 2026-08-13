// Per-browser double-buffered, file-backed shared paint store. Paints alternate between two regions. Each region
// starts with an atomic sequence header followed by BGRA pixels; odd means a write is in progress and even
// means published. OsrPaintEvent carries the published sequence, allowing the JVM to reject a stale slot or
// a slot overwritten during its copy instead of ever delivering torn pixels.
//
// A regular file is used deliberately: Java 11 can open and map it with FileChannel.map on Linux, macOS, and Windows
// without JNI/JNA or relying on platform-specific POSIX shared-memory namespaces. The name lives below the operating
// system temporary directory as "cef4j-paint-PID-handleId-G-slot.frame"
// where G is a per-browser generation counter that the runtime server bumps every time it reallocates the buffer (for
// example after a SetViewportSizeRequest grows the viewport past the current mapping capacity, or after a substantial
// shrink lets us release pages back to the kernel). Each name is unique across runtime-server processes (PID), across
// browsers within the runtime server (handle id), and across reallocations (generation), so re-mapping on the JVM side
// is a clean "open the new name, drop the old fd" — name change is the signal. The Java supervisor removes files left
// by a crash using the PID embedded in the name.
//
// Pixel layout: BGRA, packed (stride = width * 4). The buffer is sized to fit the requested (width, height) plus
// a small headroom; byteCount in the Event reports the *populated* rectangle (width * height * 4 of the most
// recent paint), so the JVM doesn't read stale tail bytes.
//
#pragma once

#include <atomic>
#include <cstdint>
#include <cstdio>
#include <cstring>
#include <filesystem>
#include <new>
#include <string>
#ifdef _WIN32
#include <windows.h>
#include <process.h>
#else
#include <fcntl.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <unistd.h>
#endif

namespace cef4j {
namespace ipc {

static_assert(std::atomic<std::uint64_t>::is_always_lock_free,
              "shared-memory frame sequence requires a lock-free 64-bit atomic");

class OsrPaintBuffer {
public:
    OsrPaintBuffer(std::int32_t handleId, std::size_t maxBytes, std::uint32_t generation = 0)
            : maxBytes_(maxBytes), generation_(generation), nextSlot_(0), nextSequence_(0) {
        for (int slot = 0; slot < 2; ++slot) {
            char name[96];
            std::snprintf(name, sizeof(name), "cef4j-paint-%d-%d-%u-%d.frame",
                          processId(), static_cast<int>(handleId),
                          static_cast<unsigned>(generation), slot);
            pathObjects_[slot] = std::filesystem::temp_directory_path() / name;
            paths_[slot] = pathObjects_[slot].u8string();
            const std::size_t mappedBytes = headerBytes() + maxBytes_;
#ifdef _WIN32
            HANDLE file = ::CreateFileW(pathObjects_[slot].c_str(), GENERIC_READ | GENERIC_WRITE,
                                        FILE_SHARE_READ | FILE_SHARE_WRITE | FILE_SHARE_DELETE, nullptr,
                                        CREATE_NEW, FILE_ATTRIBUTE_TEMPORARY, nullptr);
            if (file == INVALID_HANDLE_VALUE) return;
            LARGE_INTEGER size{};
            size.QuadPart = static_cast<LONGLONG>(mappedBytes);
            if (!::SetFilePointerEx(file, size, nullptr, FILE_BEGIN) || !::SetEndOfFile(file)) {
                ::CloseHandle(file);
                ::DeleteFileW(pathObjects_[slot].c_str());
                return;
            }
            HANDLE mapping = ::CreateFileMappingA(file, nullptr, PAGE_READWRITE, 0, 0, nullptr);
            if (!mapping) {
                ::CloseHandle(file);
                ::DeleteFileW(pathObjects_[slot].c_str());
                return;
            }
            void* mapped = ::MapViewOfFile(mapping, FILE_MAP_ALL_ACCESS, 0, 0, mappedBytes);
            ::CloseHandle(mapping);
            if (!mapped) {
                ::CloseHandle(file);
                ::DeleteFileW(pathObjects_[slot].c_str());
                return;
            }
            files_[slot] = file;
#else
            int fd = ::open(paths_[slot].c_str(), O_CREAT | O_RDWR | O_EXCL, 0600);
            if (fd < 0) return;
            if (::ftruncate(fd, static_cast<off_t>(mappedBytes)) != 0) {
                ::close(fd);
                ::unlink(paths_[slot].c_str());
                return;
            }
            void* mapped = ::mmap(nullptr, mappedBytes, PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);
            ::close(fd);
            if (mapped == MAP_FAILED) {
                ::unlink(paths_[slot].c_str());
                return;
            }
#endif
            data_[slot] = static_cast<std::uint8_t*>(mapped);
            new (data_[slot]) std::atomic<std::uint64_t>(0);
        }
    }

    ~OsrPaintBuffer() {
        for (int slot = 0; slot < 2; ++slot) {
            if (data_[slot]) {
#ifdef _WIN32
                ::UnmapViewOfFile(data_[slot]);
                if (files_[slot] != INVALID_HANDLE_VALUE) ::CloseHandle(files_[slot]);
                ::DeleteFileW(pathObjects_[slot].c_str());
#else
                ::munmap(data_[slot], headerBytes() + maxBytes_);
                ::unlink(paths_[slot].c_str());
#endif
            }
        }
    }

    OsrPaintBuffer(const OsrPaintBuffer&) = delete;
    OsrPaintBuffer& operator=(const OsrPaintBuffer&) = delete;

    bool ok() const { return data_[0] != nullptr && data_[1] != nullptr; }
    std::size_t capacity() const { return maxBytes_; }
    std::uint32_t generation() const { return generation_; }

    struct PublishedFrame {
        std::size_t byteCount = 0;
        std::string shmName;
        std::uint64_t sequence = 0;
    };

    // Publishes into the next slot using a sequence lock. Caller serialises paints (CEF's OnPaint runs on
    // the UI thread). A slow JVM may observe that a slot has advanced and drop that stale event; it cannot
    // mistake pixels from two writes for one frame.
    PublishedFrame writePixels(const void* src, std::size_t byteCount) {
        if (!ok() || !src) return {};
        std::size_t n = byteCount > maxBytes_ ? maxBytes_ : byteCount;
        const int slot = nextSlot_;
        nextSlot_ = (nextSlot_ + 1) % 2;
        const std::uint64_t published = (nextSequence_ += 2);
        auto* sequence = reinterpret_cast<std::atomic<std::uint64_t>*>(data_[slot]);
        sequence->store(published - 1, std::memory_order_release);
        std::memcpy(data_[slot] + headerBytes(), src, n);
        sequence->store(published, std::memory_order_release);
        return PublishedFrame{n, paths_[slot], published};
    }

private:
    static constexpr std::size_t headerBytes() { return sizeof(std::uint64_t); }
    static int processId() {
#ifdef _WIN32
        return ::_getpid();
#else
        return static_cast<int>(::getpid());
#endif
    }

    std::size_t maxBytes_;
    std::uint8_t* data_[2] = {nullptr, nullptr};
    std::filesystem::path pathObjects_[2];
    std::string paths_[2];
#ifdef _WIN32
    HANDLE files_[2] = {INVALID_HANDLE_VALUE, INVALID_HANDLE_VALUE};
#endif
    std::uint32_t generation_;
    int nextSlot_;
    std::uint64_t nextSequence_;
};

} // namespace ipc
} // namespace cef4j
