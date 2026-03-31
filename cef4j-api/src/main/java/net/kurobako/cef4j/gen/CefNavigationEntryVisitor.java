// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Callback interface for CefBrowserHost::GetNavigationEntries. The methods of this class will be called on the browser
 * process UI thread.
 */
public interface CefNavigationEntryVisitor {

    /**
     * Method executed for visiting the DOM. The document object passed to this method represents a snapshot of the DOM
     * at the time this method is executed. DOM objects are only valid for the scope of this method. Do not keep
     * references to or attempt to access any DOM objects outside the scope of this method.
     */
    default int visit(long entry, int current, int index, int total) {
        return 0;
    }
}
