package net.kurobako.cef4j.codegen

object JavaNativeCodeGen {

  // Render the NativePeer inner class body without the surrounding file wrapper.
  def renderInnerClass(
      decl: CefDecl.ObjectStruct,
      extraNativeDecls: List[String] = Nil,
      handlerNames: Set[String] = Set.empty
  )(using Naming.Context): String = {
    val javaName = Naming.structToJavaName(decl.name)
    renderBody(javaName, decl.fns, extraNativeDecls, handlerNames)
  }

  private def renderBody(
      javaName: String,
      fns: List[FnPtr],
      extraNativeDecls: List[String] = Nil,
      handlerNames: Set[String] = Set.empty
  )(using Naming.Context): String = {
    val overrides = fns.map { fn =>
      val methodName = Naming.javaMethodName(fn)
      val nativeName = Naming.nativeMethodName(fn)
      val shape      = JavaMethods.shape(fn.ret, fn.visibleParams, fn.metaAttrs)
      val allArgs    = if (shape.argsExpr.isEmpty) "nativePtr" else s"nativePtr, ${shape.argsExpr}"
      val body       = JavaMethods.renderCallBody(s"$nativeName($allArgs)", shape)

      val argChecks = fn.visibleParams.collect {
        case p @ Param(_, CType.ObjectPtr(cefName), _, _) if !handlerNames.contains(cefName) =>
          val paramName = Naming.toCamelCase(p.name)
          val typeName  = Naming.structToJavaName(cefName)
          s"""            CefLibraryObject.requireOpen($paramName, "$typeName");"""
      }
      val argChecksBlock = if (argChecks.isEmpty) "" else argChecks.mkString("\n") + "\n"

      s"""        @Override
        public ${shape.retType} $methodName(${shape.paramsDecl}) {
            checkNotClosed();
$argChecksBlock            $body
        }"""
    }.mkString("\n\n")

    val nativeDecls = fns.map { fn =>
      val nativeName   = Naming.nativeMethodName(fn)
      val shape        = JavaMethods.shape(fn.ret, fn.visibleParams, fn.metaAttrs)
      val nativeParams = ("long self" :: Option.when(shape.nativeParamsDecl.nonEmpty)(shape.nativeParamsDecl).toList)
        .mkString(", ")
      s"        private static native ${shape.nativeRetType} $nativeName($nativeParams);"
    }.mkString("\n\n")

    s"""    final class NativePeer implements $javaName, AutoCloseable {
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
            if (closed) throw new IllegalStateException("$javaName has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger($javaName.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release $javaName 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

$overrides

$nativeDecls
${if (extraNativeDecls.nonEmpty) "\n" + extraNativeDecls.mkString("\n") else ""}

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
            return "$javaName{0x" + Long.toHexString(nativePtr) + "}";
        }
    }"""
  }
}
