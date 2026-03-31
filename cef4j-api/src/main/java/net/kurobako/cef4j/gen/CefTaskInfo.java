// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Structure representing task information provided by CefTaskManager. */
public final class CefTaskInfo {

    public final long size;
    public final long id;
    public final CefTaskType type;
    public final int isKillable;
    public final int title;
    public final double cpuUsage;
    public final int numberOfProcessors;
    public final long memory;
    public final long gpuMemory;
    public final int isGpuMemoryInflated;

    public CefTaskInfo(
            long size,
            long id,
            CefTaskType type,
            int isKillable,
            int title,
            double cpuUsage,
            int numberOfProcessors,
            long memory,
            long gpuMemory,
            int isGpuMemoryInflated) {
        this.size = size;
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
        return this.size == other.size
                && this.id == other.id
                && java.util.Objects.equals(this.type, other.type)
                && this.isKillable == other.isKillable
                && this.title == other.title
                && this.cpuUsage == other.cpuUsage
                && this.numberOfProcessors == other.numberOfProcessors
                && this.memory == other.memory
                && this.gpuMemory == other.gpuMemory
                && this.isGpuMemoryInflated == other.isGpuMemoryInflated;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(
                size,
                id,
                type,
                isKillable,
                title,
                cpuUsage,
                numberOfProcessors,
                memory,
                gpuMemory,
                isGpuMemoryInflated);
    }

    @Override
    public String toString() {
        return "CefTaskInfo{" + "size=" + size + ", " + "id=" + id + ", " + "type=" + type + ", " + "isKillable="
                + isKillable + ", " + "title=" + title + ", " + "cpuUsage=" + cpuUsage + ", " + "numberOfProcessors="
                + numberOfProcessors + ", " + "memory=" + memory + ", " + "gpuMemory=" + gpuMemory + ", "
                + "isGpuMemoryInflated=" + isGpuMemoryInflated + "}";
    }
}
