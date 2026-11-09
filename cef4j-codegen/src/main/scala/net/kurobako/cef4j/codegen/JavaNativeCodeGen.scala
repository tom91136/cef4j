package net.kurobako.cef4j.codegen

object JavaNativeCodeGen {

  def renderInnerClass(
      decl: CefDecl.ObjectStruct,
      extraNativeDecls: List[String] = Nil,
      handlerNames: Set[String] = Set.empty,
      ancestorDecls: List[CefDecl.ObjectStruct] = Nil
  )(using Naming.Context): String = {
    val javaName = Naming.structToJavaName(decl.name)
    renderBody(Naming.InterfaceContext(javaName, decl.fns, handlerNames), extraNativeDecls, ancestorDecls)
  }

  private def renderBody(
      intf: Naming.InterfaceContext,
      extraNativeDecls: List[String],
      ancestorDecls: List[CefDecl.ObjectStruct]
  )(using Naming.Context): String = {
    val javaName     = intf.javaName
    val fns          = intf.fns
    val handlerNames = intf.handlerNames
    val overrides    =
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

    val nativeDecls = fns.map(renderNativeDecl(_)).mkString("\n\n")

    s"""    final class NativePeer extends net.kurobako.cef4j.peer.NativeObject implements $javaName {
        NativePeer(long ptr) {
            super("$javaName", ptr, NativePeer::release0);
        }

        private static native void release0(long ptr);

$overrides
${if (ancestorOverrides.nonEmpty) "\n" + ancestorOverrides else ""}

$nativeDecls
${
        if (extraNativeDecls.nonEmpty) "\n" + extraNativeDecls.mkString("\n") else ""
      }
    }"""
  }

  private def renderOverride(
      fn: FnPtr,
      handlerNames: Set[String],
      callExpr: String => String
  )(using Naming.Context): String = {
    val methodName     = Naming.javaMethodName(fn)
    val outColl        = OutCollection.forMethod(fn).map(_._2)
    val shape          = JavaMethods.shape(fn.ret, fn.visibleParams, fn.metaAttrs, outCollection = outColl)
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
    val outColl      = OutCollection.forMethod(fn).map(_._2)
    val shape        = JavaMethods.shape(fn.ret, fn.visibleParams, fn.metaAttrs, outCollection = outColl)
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
