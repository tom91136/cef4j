// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-types
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Resource type for a request. These constants match their equivalents in Chromium's ResourceType and should not be
 * renumbered.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   RT_MAIN_FRAME = 0,
 *   RT_SUB_FRAME = 1,
 *   RT_STYLESHEET = 2,
 *   RT_SCRIPT = 3,
 *   RT_IMAGE = 4,
 *   ...
 * } cef_resource_type_t;</pre>
 *
 * <p>Possible values: {@link Kind#MAIN_FRAME}, {@link Kind#SUB_FRAME}, {@link Kind#STYLESHEET}, {@link Kind#SCRIPT},
 * {@link Kind#IMAGE}, {@link Kind#FONT_RESOURCE}, {@link Kind#SUB_RESOURCE}, {@link Kind#OBJECT}, {@link Kind#MEDIA},
 * {@link Kind#WORKER}, {@link Kind#SHARED_WORKER}, {@link Kind#PREFETCH}, {@link Kind#FAVICON}, {@link Kind#XHR},
 * {@link Kind#PING}, {@link Kind#SERVICE_WORKER}, {@link Kind#CSP_REPORT}, {@link Kind#PLUGIN_RESOURCE},
 * {@link Kind#NAVIGATION_PRELOAD_MAIN_FRAME}, {@link Kind#NAVIGATION_PRELOAD_SUB_FRAME}, {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-types -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@NullableBoundary("generated external ABI")
public final class CefResourceType implements CefEnum<CefResourceType> {

    /** Known constants for {@link CefResourceType}. */
    public enum Kind {
        /** Top level page. */
        MAIN_FRAME(new EnumConstant(0, "0", "RT_MAIN_FRAME")),
        /** Frame or iframe. */
        SUB_FRAME(new EnumConstant(1, "1", "RT_SUB_FRAME")),
        /** CSS stylesheet. */
        STYLESHEET(new EnumConstant(2, "2", "RT_STYLESHEET")),
        /** External script. */
        SCRIPT(new EnumConstant(3, "3", "RT_SCRIPT")),
        /** Image (jpg/gif/png/etc). */
        IMAGE(new EnumConstant(4, "4", "RT_IMAGE")),
        /** Font. */
        FONT_RESOURCE(new EnumConstant(5, "5", "RT_FONT_RESOURCE")),
        /** Some other subresource. This is the default type if the actual type is unknown. */
        SUB_RESOURCE(new EnumConstant(6, "6", "RT_SUB_RESOURCE")),
        /** Object (or embed) tag for a plugin, or a resource that a plugin requested. */
        OBJECT(new EnumConstant(7, "7", "RT_OBJECT")),
        /** Media resource. */
        MEDIA(new EnumConstant(8, "8", "RT_MEDIA")),
        /** Main resource of a dedicated worker. */
        WORKER(new EnumConstant(9, "9", "RT_WORKER")),
        /** Main resource of a shared worker. */
        SHARED_WORKER(new EnumConstant(10, "10", "RT_SHARED_WORKER")),
        /** Explicitly requested prefetch. */
        PREFETCH(new EnumConstant(11, "11", "RT_PREFETCH")),
        /** Favicon. */
        FAVICON(new EnumConstant(12, "12", "RT_FAVICON")),
        /** XMLHttpRequest. */
        XHR(new EnumConstant(13, "13", "RT_XHR")),
        /** A request for a "&lt;ping&gt;". */
        PING(new EnumConstant(14, "14", "RT_PING")),
        /** Main resource of a service worker. */
        SERVICE_WORKER(new EnumConstant(15, "15", "RT_SERVICE_WORKER")),
        /** A report of Content Security Policy violations. */
        CSP_REPORT(new EnumConstant(16, "16", "RT_CSP_REPORT")),
        /** A resource that a plugin requested. */
        PLUGIN_RESOURCE(new EnumConstant(17, "17", "RT_PLUGIN_RESOURCE")),
        /** A main-frame service worker navigation preload request. */
        NAVIGATION_PRELOAD_MAIN_FRAME(new EnumConstant(19, "19", "RT_NAVIGATION_PRELOAD_MAIN_FRAME")),
        /** A sub-frame service worker navigation preload request. */
        NAVIGATION_PRELOAD_SUB_FRAME(new EnumConstant(20, "20", "RT_NAVIGATION_PRELOAD_SUB_FRAME")),
        NUM_VALUES(new EnumConstant(21, "21", "RT_NUM_VALUES"));

        private static final Kind[] VALUES = Kind.values();

        private final EnumConstant constant;

        Kind(EnumConstant constant) {
            this.constant = constant;
        }

        /** The underlying C enum numeric value. */
        public long value() {
            return constant.value();
        }

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public String expr() {
            return constant.expr();
        }

        /** The C constant name (e.g., {@code "cef_resource_type_t"}). */
        public String cName() {
            return constant.name();
        }

        @Override
        public String toString() {
            return cName() + "(expr=" + expr() + ", value=" + value() + ")";
        }
    }

    /** The underlying C enum numeric value. This may not correspond to any known {@link Kind}. */
    public final long value;

    private CefResourceType(long value) {
        this.value = value;
    }

    /** {@inheritDoc} */
    @Override
    public long value() {
        return value;
    }

    /** {@inheritDoc} */
    @Override
    public String expr() {
        return kind().map(Kind::expr).orElse(String.valueOf(value));
    }

    /** {@inheritDoc} */
    @Override
    public String name() {
        return kind().map(Kind::cName).orElse("UNKNOWN(" + value + ")");
    }

    /**
     * Returns the {@link Kind} matching this value, or empty for unknown/composite values. Use this for exhaustive
     * switch over known constants.
     */
    public java.util.Optional<Kind> kind() {
        for (Kind k : Kind.VALUES) {
            if (k.value() == value) return java.util.Optional.of(k);
        }
        return java.util.Optional.empty();
    }

    /** Returns an instance for the given raw value, use {@link #kind} to resolve to a concrete enum. */
    public static CefResourceType of(long v) {
        return new CefResourceType(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefResourceType of(Kind k) {
        return new CefResourceType(k.value());
    }

    @Override
    public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefResourceType)) return false;
        return this.value == ((CefResourceType) obj).value;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(value);
    }

    @Override
    public String toString() {
        return kind().map(Kind::toString).orElse("UNKNOWN(value=" + value + ")");
    }
}
