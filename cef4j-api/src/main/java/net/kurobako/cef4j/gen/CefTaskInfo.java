// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

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
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public final class CefTaskInfo {

    // Native struct size, populated by the JNI layer with sizeof(struct) as required by CEF. Not user-modifiable.
    @SuppressWarnings("FieldMayBeFinal")
    private volatile long size = -1;

    /** The task ID. */
    public final long id;
    /** The task type. */
    public final CefTaskType type;
    /** Set to {@code true} (1) if the task is killable. */
    public final int isKillable;
    /** The task title. */
    public final String title;
    /**
     * The CPU usage of the process on which the task is running. The value is in the range zero to number_of_processors
     * * 100%.
     */
    public final double cpuUsage;
    /** The number of processors available on the system. */
    public final int numberOfProcessors;
    /** The memory footprint of the task in bytes. A value of -1 means no valid value is currently available. */
    public final long memory;
    /** The GPU memory usage of the task in bytes. A value of -1 means no valid value is currently available. */
    public final long gpuMemory;
    /**
     * Set to {@code true} (1) if this task process' GPU resource count is inflated because it is counting other
     * processes' resources (e.g, the GPU process has this value set to {@code true} because it is the aggregate of all
     * processes).
     */
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
