// Per-type registry of CEF ref-counted struct pointers, keyed by an int32 id that travels over the wire as
// FieldType.RemoteHandle. Mirrors the JVM-side `RemoteHandle` value class. Insertion retains; `release(id)`
// decrements the CEF refcount and drops the entry. ID 0 is reserved as the null handle.
#pragma once

#include <atomic>
#include <cstdint>
#include <mutex>
#include <unordered_map>

#include "include/capi/cef_base_capi.h"

namespace cef4j {
namespace ipc {

template <typename T>
class HandleTable {
public:
    HandleTable() = default;

    HandleTable(const HandleTable&)            = delete;
    HandleTable& operator=(const HandleTable&) = delete;

    /** Insert {@code ptr} (must be non-null and ref-counted) and return its id. If {@code ptr} is already in
      * the table, the existing id is returned and no extra retain is taken — this lets a handler callback that
      * receives a non-owning pointer use the same code path as a method that returns a +1-retained pointer
      * without leaking refs. */
    std::int32_t insert(T* ptr) {
        if (!ptr) return 0;
        std::lock_guard<std::mutex> lk(mu_);
        auto rit = reverse_.find(ptr);
        if (rit != reverse_.end()) return rit->second;
        auto* base = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
        base->add_ref(base);
        std::int32_t id = next_.fetch_add(1, std::memory_order_relaxed);
        table_[id]     = ptr;
        reverse_[ptr]  = id;
        return id;
    }

    /** Look up a handle id. Returns {@code nullptr} for unknown / 0 ids. Caller must NOT release the returned
      * pointer — the table owns the retain. To get a referenced caller-side pointer, use {@link retain}. */
    T* find(std::int32_t id) const {
        if (id == 0) return nullptr;
        std::lock_guard<std::mutex> lk(mu_);
        auto it = table_.find(id);
        return (it != table_.end()) ? it->second : nullptr;
    }

    /** Look up + retain. Caller must release once done (mirrors {@code add_ref}). The lookup and the add_ref
      * happen under the same lock — otherwise another thread can call {@link release} between them, drop the
      * refcount to zero, and free the object before this add_ref runs. */
    T* retain(std::int32_t id) {
        if (id == 0) return nullptr;
        std::lock_guard<std::mutex> lk(mu_);
        auto it = table_.find(id);
        if (it == table_.end()) return nullptr;
        auto* base = reinterpret_cast<cef_base_ref_counted_t*>(it->second);
        base->add_ref(base);
        return it->second;
    }

    /** Drop the table entry and release the table's retain. Subsequent {@link find} returns null. */
    void release(std::int32_t id) {
        if (id == 0) return;
        T* ptr = nullptr;
        {
            std::lock_guard<std::mutex> lk(mu_);
            auto it = table_.find(id);
            if (it == table_.end()) return;
            ptr = it->second;
            table_.erase(it);
            reverse_.erase(ptr);
        }
        if (ptr) {
            auto* base = reinterpret_cast<cef_base_ref_counted_t*>(ptr);
            base->release(base);
        }
    }

    /** Releases every entry; intended for shutdown. */
    void clear() {
        std::unordered_map<std::int32_t, T*> snapshot;
        {
            std::lock_guard<std::mutex> lk(mu_);
            snapshot.swap(table_);
            reverse_.clear();
        }
        for (auto& kv : snapshot) {
            auto* base = reinterpret_cast<cef_base_ref_counted_t*>(kv.second);
            base->release(base);
        }
    }

private:
    std::atomic<std::int32_t> next_{1};
    mutable std::mutex mu_;
    std::unordered_map<std::int32_t, T*> table_;
    std::unordered_map<T*, std::int32_t> reverse_;
};

} // namespace ipc
} // namespace cef4j
