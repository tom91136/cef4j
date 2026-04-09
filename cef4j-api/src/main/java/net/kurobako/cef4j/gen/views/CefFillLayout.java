// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen.views;

import javax.annotation.processing.Generated;
import java.util.Optional;
import net.kurobako.cef4j.gen.CefLibraryObject;

/**
 * A simple Layout that causes the associated Panel's one child to be sized to match the bounds of its parent. Methods must be called on the browser process UI thread unless otherwise indicated.
 * <p>Definition generated from views/cef_fill_layout_capi.h
 * <pre>typedef struct _cef_fill_layout_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_fill_layout_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__fill__layout_8h.html">views/cef_fill_layout.h:43</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefFillLayout extends CefLayout {


    final class NativePeer implements CefFillLayout, AutoCloseable {
        private final long nativePtr;
        private final java.lang.ref.Cleaner.Cleanable cleanable;
        private volatile boolean closed;

        NativePeer(long ptr) {
            this.nativePtr = ptr;
            this.cleanable = net.kurobako.cef4j.NativeCleaner.INSTANCE.register(this, new Release(ptr));
        }

        @Override
        public void peerClose() {
            closed = true;
            cleanable.clean();
        }

        @Override
        public boolean peerIsClosed() {
            return closed;
        }

        private void checkNotClosed() {
            if (closed) throw new IllegalStateException("CefFillLayout has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefFillLayout.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefFillLayout 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
        }

        private static native void release0(long ptr);



        @Override
      public Optional<CefBoxLayout> asBoxLayout() {
          checkNotClosed();
          return Optional.ofNullable(CefLayout.NativePeer.asBoxLayout0(nativePtr));
      }

        @Override
      public Optional<CefFillLayout> asFillLayout() {
          checkNotClosed();
          return Optional.ofNullable(CefLayout.NativePeer.asFillLayout0(nativePtr));
      }

        @Override
      public boolean isValid() {
          checkNotClosed();
          return CefLayout.NativePeer.isValid0(nativePtr);
      }




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
            return "CefFillLayout{0x" + Long.toHexString(nativePtr) + "}";
        }
    }

}
