// Per-browser double-buffered shared-memory paint store. Paints alternate between two regions. Each region
// starts with an atomic sequence header followed by BGRA pixels; odd means a write is in progress and even
// means published. OsrPaintEvent carries the published sequence, allowing the JVM to reject a stale slot or
// a slot overwritten during its copy instead of ever delivering torn pixels.
//
// POSIX shm_open is used on Linux/macOS; the name lives in the abstract "/cef4j-paint-PID-handleId-G" namespace
// where G is a per-browser generation counter that the helper bumps every time it reallocates the buffer (for
// example after a SetViewportSizeRequest grows the viewport past the current shm capacity, or after a substantial
// shrink lets us release pages back to the kernel). Each name is unique across helper processes (PID), across
// browsers within the helper (handle id), and across reallocations (generation), so re-mapping on the JVM side
// is a clean "open the new name, drop the old fd" — name change is the signal. The shm is unlinked at
// construction so the kernel reclaims it on process exit even if the helper crashes; we keep the fd open via
// mmap until the buffer is destroyed.
//
// Pixel layout: BGRA, packed (stride = width * 4). The buffer is sized to fit the requested (width, height) plus
// a small headroom; byteCount in the Event reports the *populated* rectangle (width * height * 4 of the most
// recent paint), so the JVM doesn't read stale tail bytes.
//
// Windows port (later): replaces shm_open/mmap with CreateFileMapping/MapViewOfFile and embeds the file mapping
// name in shmName. JVM-side opens via OpenFileMapping. Same wire shape.
#pragma once

#include <atomic>
#include <cstdint>
#include <cstdio>
#include <cstring>
#include <fcntl.h>
#include <new>
#include <string>
#include <sys/mman.h>
#include <sys/stat.h>
#include <unistd.h>

namespace cef4j {
namespace ipc {

static_assert(std::atomic<std::uint64_t>::is_always_lock_free,
              "shared-memory frame sequence requires a lock-free 64-bit atomic");

class OsrPaintBuffer {
public:
    OsrPaintBuffer(std::int32_t handleId, std::size_t maxBytes, std::uint32_t generation = 0)
            : maxBytes_(maxBytes), generation_(generation), nextSlot_(0), nextSequence_(0) {
        for (int slot = 0; slot < 2; ++slot) {
            char name[72];
            std::snprintf(name, sizeof(name), "/cef4j-paint-%d-%d-%u-%d",
                          static_cast<int>(::getpid()), static_cast<int>(handleId),
                          static_cast<unsigned>(generation), slot);
            shmNames_[slot] = name;
            ::shm_unlink(shmNames_[slot].c_str());
            int fd = ::shm_open(shmNames_[slot].c_str(), O_CREAT | O_RDWR | O_EXCL, 0600);
            if (fd < 0) return;
            const std::size_t mappedBytes = headerBytes() + maxBytes_;
            if (::ftruncate(fd, static_cast<off_t>(mappedBytes)) != 0) {
                ::close(fd);
                ::shm_unlink(shmNames_[slot].c_str());
                return;
            }
            void* mapped = ::mmap(nullptr, mappedBytes, PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);
            ::close(fd);
            if (mapped == MAP_FAILED) {
                ::shm_unlink(shmNames_[slot].c_str());
                return;
            }
            data_[slot] = static_cast<std::uint8_t*>(mapped);
            new (data_[slot]) std::atomic<std::uint64_t>(0);
        }
    }

    ~OsrPaintBuffer() {
        for (int slot = 0; slot < 2; ++slot) {
            if (data_[slot]) {
                ::munmap(data_[slot], headerBytes() + maxBytes_);
                ::shm_unlink(shmNames_[slot].c_str());
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
        return PublishedFrame{n, shmNames_[slot], published};
    }

private:
    static constexpr std::size_t headerBytes() { return sizeof(std::uint64_t); }

    std::size_t maxBytes_;
    std::uint8_t* data_[2] = {nullptr, nullptr};
    std::string shmNames_[2];
    std::uint32_t generation_;
    int nextSlot_;
    std::uint64_t nextSequence_;
};

} // namespace ipc
} // namespace cef4j
