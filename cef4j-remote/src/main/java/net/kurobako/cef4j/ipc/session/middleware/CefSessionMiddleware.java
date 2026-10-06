package net.kurobako.cef4j.ipc.session.middleware;

import java.util.Objects;
import javax.annotation.Nonnull;
import net.kurobako.cef4j.ipc.session.CefSession;

/** Composable decorator applied at the transport-independent {@link CefSession} API boundary. */
@FunctionalInterface
public interface CefSessionMiddleware {
    @Nonnull
    CefSession wrap(@Nonnull CefSession delegate);

    /** Apply this middleware closest to the delegate, followed by {@code outer}. */
    default CefSessionMiddleware andThen(@Nonnull CefSessionMiddleware outer) {
        Objects.requireNonNull(outer, "outer");
        return delegate -> outer.wrap(wrap(delegate));
    }

    static CefSessionMiddleware identity() {
        return delegate -> delegate;
    }
}
