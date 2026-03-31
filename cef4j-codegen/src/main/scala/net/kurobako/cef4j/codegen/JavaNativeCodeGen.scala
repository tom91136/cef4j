package net.kurobako.cef4j.codegen

object JavaNativeCodeGen {

  /** Render the NativePeer inner class body (without file wrapper) for embedding in the interface. */
  def renderInnerClass(decl: CefDecl.ObjectStruct): String = {
    val javaName = Naming.structToJavaName(decl.name)
    renderBody(javaName, decl.fns)
  }

  private def renderBody(javaName: String, fns: List[FnPtr]): String = {
    val overrides = fns.map { fn =>
      val rawRetType  = Naming.javaType(fn.ret)
      val useOptional = JavaCodeGen.isOptionalReturn(fn)
      val retType     = if (useOptional) s"Optional<$rawRetType>" else rawRetType
      val methodName  = Naming.toCamelCase(fn.name)
      val nativeName  = s"N_${Naming.toPascalCase(fn.name)}"
      val params      = fn.params.map { p =>
        s"${Naming.javaType(p.typ)} ${Naming.toCamelCase(p.name)}"
      }.mkString(", ")
      val args    = fn.params.map(p => Naming.toCamelCase(p.name)).mkString(", ")
      val selfArg = "nativePtr"
      val allArgs = if (args.isEmpty) selfArg else s"$selfArg, $args"

      val body = if (retType == "void") {
        s"$nativeName($allArgs);"
      } else if (useOptional) {
        s"return Optional.ofNullable($nativeName($allArgs));"
      } else {
        s"return $nativeName($allArgs);"
      }

      s"""        @Override
        public $retType $methodName($params) {
            $body
        }"""
    }.mkString("\n\n")

    val nativeDecls = fns.map { fn =>
      val retType      = Naming.javaType(fn.ret)
      val nativeName   = s"N_${Naming.toPascalCase(fn.name)}"
      val nativeParams = ("long self" :: fn.params.map { p =>
        s"${Naming.javaType(p.typ)} ${Naming.toCamelCase(p.name)}"
      }).mkString(", ")
      s"        private native $retType $nativeName($nativeParams);"
    }.mkString("\n\n")

    s"""    static class NativePeer implements $javaName {
        private volatile long nativePtr;

$overrides

$nativeDecls

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
