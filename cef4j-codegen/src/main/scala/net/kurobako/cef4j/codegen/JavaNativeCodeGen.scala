package net.kurobako.cef4j.codegen

object JavaNativeCodeGen {

  /** Render the NativePeer inner class body (without file wrapper) for embedding in the interface. */
  def renderInnerClass(
      decl: CefDecl.ObjectStruct,
      extraNativeDecls: List[String] = Nil
  ): String = {
    val javaName = Naming.structToJavaName(decl.name)
    renderBody(javaName, decl.fns, extraNativeDecls)
  }

  private def renderBody(javaName: String, fns: List[FnPtr], extraNativeDecls: List[String] = Nil): String = {
    val overrides = fns.map { fn =>
      val rawRetType    = Naming.javaType(fn.ret)
      val useOptional   = JavaCodeGen.isOptionalReturn(fn)
      val retType       = if (useOptional) s"Optional<$rawRetType>" else rawRetType
      val methodName    = Naming.javaMethodName(fn)
      val nativeName    = s"N_${Naming.javaPascalName(fn)}"
      val optionals     = fn.metaAttrs.collect { case ("optional_param", p) => p }.toSet
      val visibleParams = fn.params.filterNot(_.typ.isInstanceOf[CType.BufferSize])
      val params        = visibleParams.map { p =>
        val jType = Naming.javaType(p.typ)
        val pName = Naming.toCamelCase(p.name)
        if (optionals.contains(p.name) && JavaCodeGen.isReferenceType(p.typ))
          s"@Nullable $jType $pName"
        else if (JavaCodeGen.isReferenceType(p.typ))
          s"@Nonnull $jType $pName"
        else
          s"$jType $pName"
      }.mkString(", ")
      val args    = visibleParams.map(p => Naming.toCamelCase(p.name)).mkString(", ")
      val selfArg = "nativePtr"
      val allArgs = if (args.isEmpty) selfArg else s"$selfArg, $args"

      val useArraysAsList = fn.ret match {
        case CType.CountFuncArray(elem, _, _, _) => !Naming.isPrimitiveElement(elem)
        case _                                   => false
      }
      val body = if (retType == "void") {
        s"$nativeName($allArgs);"
      } else if (useOptional) {
        s"return Optional.ofNullable($nativeName($allArgs));"
      } else if (useArraysAsList) {
        s"return Arrays.asList($nativeName($allArgs));"
      } else {
        s"return $nativeName($allArgs);"
      }

      s"""        @Override
        public $retType $methodName($params) {
            $body
        }"""
    }.mkString("\n\n")

    val nativeDecls = fns.map { fn =>
      val retType      = Naming.jniNativeReturnType(fn.ret)
      val nativeName   = s"N_${Naming.javaPascalName(fn)}"
      val nativeParams = ("long self" :: fn.params.filterNot(_.typ.isInstanceOf[CType.BufferSize]).map { p =>
        s"${Naming.javaType(p.typ)} ${Naming.toCamelCase(p.name)}"
      }).mkString(", ")
      s"        private static native $retType $nativeName($nativeParams);"
    }.mkString("\n\n")

    s"""    final class NativePeer implements $javaName, AutoCloseable {
        private final long nativePtr;
        private final java.lang.ref.Cleaner.Cleanable cleanable;

        NativePeer(long ptr) {
            this.nativePtr = ptr;
            this.cleanable = net.kurobako.cef4j.NativeCleaner.INSTANCE.register(this, new Release(ptr));
        }

        @Override
        public void close() {
            cleanable.clean();
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
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

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
