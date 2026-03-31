// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Class that facilitates managing the browser-related tasks. The methods of this class may only be called on the UI
 * thread.
 */
public interface CefTaskManager {

    /**
     * Returns the number of tasks currently tracked by the task manager. Returns 0 if the method was called from the
     * incorrect thread.
     */
    long getTasksCount();

    /**
     * Gets the list of task IDs currently tracked by the task manager. Tasks that share the same process id will always
     * be consecutive. The list will be sorted in a way that reflects the process tree: the browser process will be
     * first, followed by the gpu process if it exists. Related processes (e.g., a subframe process and its parent) will
     * be kept together if possible. Callers can expect this ordering to be stable when a process is added or removed.
     * The task IDs are unique within the application lifespan. Returns false if the method was called from the
     * incorrect thread.
     *
     * <p>The size of {@code task_ids} is determined by {@code GetTasksCount()}.
     */
    boolean getTaskIdsList(long taskIdsCount, long taskIds);

    /**
     * Gets information about the task with |task_id|. Returns true if the information about the task was successfully
     * retrieved and false if the |task_id| is invalid or the method was called from the incorrect thread.
     */
    boolean getTaskInfo(long taskId, long info);

    /**
     * Attempts to terminate a task with |task_id|. Returns false if the |task_id| is invalid, the call is made from an
     * incorrect thread, or if the task cannot be terminated.
     */
    boolean killTask(long taskId);

    /**
     * Returns the task ID associated with the main task for |browser_id| (value from CefBrowser::GetIdentifier).
     * Returns -1 if |browser_id| is invalid, does not currently have an associated task, or the method was called from
     * the incorrect thread.
     */
    long getTaskIdForBrowserId(int browserId);

    static class NativePeer implements CefTaskManager {
        private volatile long nativePtr;

        @Override
        public long getTasksCount() {
            return N_GetTasksCount(nativePtr);
        }

        @Override
        public boolean getTaskIdsList(long taskIdsCount, long taskIds) {
            return N_GetTaskIdsList(nativePtr, taskIdsCount, taskIds);
        }

        @Override
        public boolean getTaskInfo(long taskId, long info) {
            return N_GetTaskInfo(nativePtr, taskId, info);
        }

        @Override
        public boolean killTask(long taskId) {
            return N_KillTask(nativePtr, taskId);
        }

        @Override
        public long getTaskIdForBrowserId(int browserId) {
            return N_GetTaskIdForBrowserId(nativePtr, browserId);
        }

        private native long N_GetTasksCount(long self);

        private native boolean N_GetTaskIdsList(long self, long taskIdsCount, long taskIds);

        private native boolean N_GetTaskInfo(long self, long taskId, long info);

        private native boolean N_KillTask(long self, long taskId);

        private native long N_GetTaskIdForBrowserId(long self, int browserId);

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof NativePeer)) return false;
            return this.nativePtr == ((NativePeer) obj).nativePtr;
        }

        @Override
        public int hashCode() {
            return Long.hashCode(nativePtr);
        }

        @Override
        public String toString() {
            return "CefTaskManager{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
