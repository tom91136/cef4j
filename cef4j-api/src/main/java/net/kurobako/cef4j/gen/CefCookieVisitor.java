// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Interface to implement for visiting cookie values. The methods of this class will always be called on the UI thread.
 */
public interface CefCookieVisitor {

    /**
     * Method executed for visiting the DOM. The document object passed to this method represents a snapshot of the DOM
     * at the time this method is executed. DOM objects are only valid for the scope of this method. Do not keep
     * references to or attempt to access any DOM objects outside the scope of this method.
     */
    default int visit(long cookie, int count, int total, int[] deleteCookie) {
        return 0;
    }
}
