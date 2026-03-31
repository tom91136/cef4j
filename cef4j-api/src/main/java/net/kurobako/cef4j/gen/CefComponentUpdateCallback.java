// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;

/** Callback interface for component update results. */
public interface CefComponentUpdateCallback {

    /** Method that will be called upon completion. |num_deleted| will be the number of cookies that were deleted. */
    default void onComplete(@Nonnull String componentId, @Nonnull CefComponentUpdateError error) {}
}
