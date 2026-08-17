package net.kurobako.cef4j.codegen

object JavaNativeCodeGen {

  def renderInnerClass(
      decl: CefDecl.ObjectStruct,
      extraNativeDecls: List[String] = Nil,
      handlerNames: Set[String] = Set.empty,
      ancestorDecls: List[CefDecl.ObjectStruct] = Nil
  )(using Naming.Context): String = {
    val javaName = Naming.structToJavaName(decl.name)
    renderBody(javaName, decl.fns, extraNativeDecls, handlerNames, ancestorDecls)
  }

  private def renderBody(
      javaName: String,
      fns: List[FnPtr],
      extraNativeDecls: List[String],
      handlerNames: Set[String],
      ancestorDecls: List[CefDecl.ObjectStruct]
  )(using Naming.Context): String = {
    val overrides =
      fns.map(fn => renderOverride(fn, handlerNames, javaName => s"${Naming.nativeMethodName(fn)}($javaName)"))
        .mkString("\n\n")

    val ancestorOverrides = ancestorDecls.flatMap { ancestor =>
      val ancestorJavaName = Naming.structToJavaName(ancestor.name)
      ancestor.fns.map(fn =>
        renderOverride(
          fn,
          handlerNames,
          allArgs => s"$ancestorJavaName.NativePeer.${Naming.nativeMethodName(fn)}($allArgs)"
        )
      )
    }.mkString("\n\n")

    val nativeDecls = fns.map(renderNativeDecl).mkString("\n\n")

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
${if (ancestorOverrides.nonEmpty) "\n" + ancestorOverrides else ""}

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

  private def renderOverride(
      fn: FnPtr,
      handlerNames: Set[String],
      callExpr: String => String
  )(using Naming.Context): String = {
    val methodName     = Naming.javaMethodName(fn)
    val shape          = JavaMethods.shape(fn.ret, fn.visibleParams, fn.metaAttrs)
    val allArgs        = prependNativePtr(shape.argsExpr)
    val body           = JavaMethods.renderCallBody(callExpr(allArgs), shape)
    val argChecks      = renderRequireOpenChecks(fn, handlerNames)
    val argChecksBlock = if (argChecks.isEmpty) "" else argChecks.mkString("\n") + "\n"

    s"""        @Override
      public ${shape.retType} $methodName(${shape.paramsDecl}) {
          checkNotClosed();
$argChecksBlock          $body
      }"""
  }

  private def renderNativeDecl(fn: FnPtr)(using Naming.Context): String = {
    val nativeName   = Naming.nativeMethodName(fn)
    val shape        = JavaMethods.shape(fn.ret, fn.visibleParams, fn.metaAttrs)
    val nativeParams = ("long self" :: Option.when(shape.nativeParamsDecl.nonEmpty)(shape.nativeParamsDecl).toList)
      .mkString(", ")
    s"        static native ${shape.nativeRetType} $nativeName($nativeParams);"
  }

  private def prependNativePtr(argsExpr: String): String =
    if (argsExpr.isEmpty) "nativePtr" else s"nativePtr, $argsExpr"

  private def renderRequireOpenChecks(fn: FnPtr, handlerNames: Set[String])(using Naming.Context): List[String] =
    fn.visibleParams.collect {
      case p @ Param(_, CType.ObjectPtr(cefName), _, _) if !handlerNames.contains(cefName) =>
        val paramName = Naming.toCamelCase(p.name)
        val typeName  = Naming.structToJavaName(cefName)
        s"""            CefLibraryObject.requireOpen($paramName, "$typeName");"""
    }
}
