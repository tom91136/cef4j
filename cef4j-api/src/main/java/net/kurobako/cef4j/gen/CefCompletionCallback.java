// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Generic callback interface used for asynchronous completion. */
public interface CefCompletionCallback {

    /** Method that will be called upon completion. |num_deleted| will be the number of cookies that were deleted. */
    default void onComplete() {}
}
