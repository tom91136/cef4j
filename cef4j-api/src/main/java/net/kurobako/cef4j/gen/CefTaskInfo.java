// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import javax.annotation.Nullable;

/**
 * Structure representing task information provided by CefTaskManager.
 * <p>Definition generated from internal/cef_types.h
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
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">internal/cef_types.h:4180</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public final class CefTaskInfo {

    // Native struct size, populated by the JNI layer with sizeof(struct) as required by CEF. Not user-modifiable.
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    private volatile long size = -1;

        /**
         * The task ID.
         */    public final long id;
        /**
         * The task type.
         */    public final @Nullable CefTaskType type;
        /**
         * Set to {@code true} (1) if the task is killable.
         */    public final int isKillable;
        /**
         * The task title.
         */    public final @Nullable String title;
        /**
         * The CPU usage of the process on which the task is running. The value is in the range zero to number_of_processors * 100%.
         */    public final double cpuUsage;
        /**
         * The number of processors available on the system.
         */    public final int numberOfProcessors;
        /**
         * The memory footprint of the task in bytes. A value of -1 means no valid value is currently available.
         */    public final long memory;
        /**
         * The GPU memory usage of the task in bytes. A value of -1 means no valid value is currently available.
         */    public final long gpuMemory;
        /**
         * Set to {@code true} (1) if this task process' GPU resource count is inflated because it is counting other processes' resources (e.g, the GPU process has this value set to {@code true} because it is the aggregate of all processes).
         */    public final int isGpuMemoryInflated;

    public CefTaskInfo(long id, @Nullable CefTaskType type, int isKillable, @Nullable String title, double cpuUsage, int numberOfProcessors, long memory, long gpuMemory, int isGpuMemoryInflated) {
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

    /** Create a mutable copy of this instance. */
    public Mutable toMutable() {
        return new Mutable(this.id, this.type, this.isKillable, this.title, this.cpuUsage, this.numberOfProcessors, this.memory, this.gpuMemory, this.isGpuMemoryInflated);
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
        return java.util.Objects.hash(id, type, isKillable, title, cpuUsage, numberOfProcessors, memory, gpuMemory, isGpuMemoryInflated);
    }

    @Override
    public String toString() {
        return "CefTaskInfo{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", " + "id=" + id + ", " + "type=" + type + ", " + "isKillable=" + isKillable + ", " + "title=" + title + ", " + "cpuUsage=" + cpuUsage + ", " + "numberOfProcessors=" + numberOfProcessors + ", " + "memory=" + memory + ", " + "gpuMemory=" + gpuMemory + ", " + "isGpuMemoryInflated=" + isGpuMemoryInflated + "}";
    }

    /**
     * Mutable variant of {@link CefTaskInfo}. Structure representing task information provided by CefTaskManager.
     * <p>Definition generated from internal/cef_types.h
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">internal/cef_types.h:4180</a>
     */
    public static final class Mutable {

    // Native struct size, populated by the JNI layer with sizeof(struct) as required by CEF. Not user-modifiable.
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    private volatile long size = -1;

            /**
             * The task ID.
             */        public long id;
            /**
             * The task type.
             */        public @Nullable CefTaskType type;
            /**
             * Set to {@code true} (1) if the task is killable.
             */        public int isKillable;
            /**
             * The task title.
             */        public @Nullable String title;
            /**
             * The CPU usage of the process on which the task is running. The value is in the range zero to number_of_processors * 100%.
             */        public double cpuUsage;
            /**
             * The number of processors available on the system.
             */        public int numberOfProcessors;
            /**
             * The memory footprint of the task in bytes. A value of -1 means no valid value is currently available.
             */        public long memory;
            /**
             * The GPU memory usage of the task in bytes. A value of -1 means no valid value is currently available.
             */        public long gpuMemory;
            /**
             * Set to {@code true} (1) if this task process' GPU resource count is inflated because it is counting other processes' resources (e.g, the GPU process has this value set to {@code true} because it is the aggregate of all processes).
             */        public int isGpuMemoryInflated;

        public Mutable() {}

        public Mutable(long id, @Nullable CefTaskType type, int isKillable, @Nullable String title, double cpuUsage, int numberOfProcessors, long memory, long gpuMemory, int isGpuMemoryInflated) {
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

        /** Create an immutable snapshot of this instance. */
        public CefTaskInfo toImmutable() {
            return new CefTaskInfo(this.id, this.type, this.isKillable, this.title, this.cpuUsage, this.numberOfProcessors, this.memory, this.gpuMemory, this.isGpuMemoryInflated);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Mutable)) return false;
            Mutable other = (Mutable) obj;
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
            return java.util.Objects.hash(id, type, isKillable, title, cpuUsage, numberOfProcessors, memory, gpuMemory, isGpuMemoryInflated);
        }

        @Override
        public String toString() {
            return "CefTaskInfo.Mutable{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", " + "id=" + id + ", " + "type=" + type + ", " + "isKillable=" + isKillable + ", " + "title=" + title + ", " + "cpuUsage=" + cpuUsage + ", " + "numberOfProcessors=" + numberOfProcessors + ", " + "memory=" + memory + ", " + "gpuMemory=" + gpuMemory + ", " + "isGpuMemoryInflated=" + isGpuMemoryInflated + "}";
        }
    }
}
