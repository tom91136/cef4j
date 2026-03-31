// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Implemented by the client to observe content and website setting changes and registered via
 * CefRequestContext::AddSettingObserver. The methods of this class will be called on the browser process UI thread.
 */
public interface CefSettingObserver {

    /**
     * Called when a content or website setting has changed. The new value can be retrieved using
     * CefRequestContext::GetContentSetting or CefRequestContext::GetWebsiteSetting.
     *
     * @param requestingUrl may be null
     * @param topLevelUrl may be null
     */
    default void onSettingChanged(
            @Nullable String requestingUrl,
            @Nullable String topLevelUrl,
            @Nonnull CefContentSettingTypes contentType) {}
}
