// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Structure representing task information provided by CefTaskManager.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef struct _cef_task_info_t {
 *   size_t size;
 *   int64_t id;
 *   cef_task_type_t type;
 *   int is_killable;
 *   cef_string_t* title;
 *   double cpu_usage;
 *   int number_of_processors;
 *   int64_t memory;
 *   int64_t gpu_memory;
 *   int is_gpu_memory_inflated;
 * } cef_task_info_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h:4134</a>
 */
public final class CefTaskInfo {

    // Native struct size — set by JNI, not user-modifiable.
    @SuppressWarnings("FieldMayBeFinal")
    private volatile long size = -1;

    public final long id;
    public final CefTaskType type;
    public final int isKillable;
    public final String title;
    public final double cpuUsage;
    public final int numberOfProcessors;
    public final long memory;
    public final long gpuMemory;
    public final int isGpuMemoryInflated;

    public CefTaskInfo(
            long id,
            CefTaskType type,
            int isKillable,
            String title,
            double cpuUsage,
            int numberOfProcessors,
            long memory,
            long gpuMemory,
            int isGpuMemoryInflated) {
        this.id = id;
        this.type = type;
        this.isKillable = isKillable;
        this.title = title;
        this.cpuUsage = cpuUsage;
        this.numberOfProcessors = numberOfProcessors;
        this.memory = memory;
        this.gpuMemory = gpuMemory;
        this.isGpuMemoryInflated = isGpuMemoryInflated;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefTaskInfo)) return false;
        CefTaskInfo other = (CefTaskInfo) obj;
        return this.id == other.id
                && java.util.Objects.equals(this.type, other.type)
                && this.isKillable == other.isKillable
                && java.util.Objects.equals(this.title, other.title)
                && this.cpuUsage == other.cpuUsage
                && this.numberOfProcessors == other.numberOfProcessors
                && this.memory == other.memory
                && this.gpuMemory == other.gpuMemory
                && this.isGpuMemoryInflated == other.isGpuMemoryInflated;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(
                id, type, isKillable, title, cpuUsage, numberOfProcessors, memory, gpuMemory, isGpuMemoryInflated);
    }

    @Override
    public String toString() {
        return "CefTaskInfo{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", " + "id=" + id + ", "
                + "type=" + type + ", " + "isKillable=" + isKillable + ", " + "title=" + title + ", " + "cpuUsage="
                + cpuUsage + ", " + "numberOfProcessors=" + numberOfProcessors + ", " + "memory=" + memory + ", "
                + "gpuMemory=" + gpuMemory + ", " + "isGpuMemoryInflated=" + isGpuMemoryInflated + "}";
    }
}
