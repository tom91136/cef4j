// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Implement this interface for asynchronous task execution. If the task is posted successfully and if the associated
 * message loop is still running then the Execute() method will be called on the target thread. If the task fails to
 * post then the task object may be destroyed on the source thread instead of the target thread. For this reason be
 * cautious when performing work in the task object destructor.
 */
public interface CefTask {

    /**
     * Handle execution of the function identified by |name|. |object| is the receiver ('this' object) of the function.
     * |arguments| is the list of arguments passed to the function. If execution succeeds set |retval| to the function
     * return value. If execution fails set |exception| to the exception that will be thrown. Return true if execution
     * was handled.
     */
    default void execute() {}
}
