// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Interface to implement to be notified of asynchronous completion via CefCookieManager::SetCookie(). */
public interface CefSetCookieCallback {

    /** Method that will be called upon completion. |num_deleted| will be the number of cookies that were deleted. */
    default void onComplete(int success) {}
}
