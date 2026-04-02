package net.kurobako.cef4j.codegen.passes

import net.kurobako.cef4j.codegen.CType
import net.kurobako.cef4j.codegen.CefDecl
import net.kurobako.cef4j.codegen.RefinedTree
import net.kurobako.cef4j.codegen.UntypedPtrWarning

object ValidateTypedPointers {
  def apply(refined: RefinedTree, handlerNames: Set[String]): List[UntypedPtrWarning] = {
    val warnings = (
      collectUntypedPtrWarnings(refined.decls, handlerNames) ++
        collectFreeFuncUntypedPtrWarnings(refined.freeFunctions, handlerNames)
    ).distinct

    if (warnings.nonEmpty) {
      System.err.println(s"\n====================================")
      System.err.println(
        s"ERROR: ${warnings.size} unresolved pointer parameter(s)/return(s) - all pointers must be typed:"
      )
      val byInner = warnings.groupBy(_.innerType).toList.sortBy(-_._2.size)
      byInner.foreach { case (inner, groupedWarnings) =>
        System.err.println(s"\n  $inner* (${groupedWarnings.size} occurrences):")
        groupedWarnings.sortBy(w => (w.structName, w.fnName)).foreach { w =>
          System.err.println(s"    ${w.structName} :: ${w.fnName} ${w.position}")
        }
      }
      System.err.println(s"====================================\n")
      throw new RuntimeException(s"${warnings.size} unresolved CType.Ptr remaining - codegen cannot continue")
    }

    warnings
  }

  private def collectUntypedPtrWarnings(
      decls: List[CefDecl],
      handlerNames: Set[String]
  ): List[UntypedPtrWarning] = {
    def isUntypedPtr(ct: CType): Option[String] = ct match {
      case CType.Ptr(inner) =>
        val stripped = inner.stripPrefix("const ").stripPrefix("struct ").trim
        if (stripped == "void" || stripped.isEmpty) None
        else if (handlerNames.contains(stripped.stripPrefix("_"))) None
        else Some(inner)
      case _ => None
    }

    def collectFromFns(structName: String, fns: List[net.kurobako.cef4j.codegen.FnPtr]): List[UntypedPtrWarning] =
      fns.flatMap { fn =>
        val retWarning = isUntypedPtr(fn.ret).map(inner =>
          UntypedPtrWarning(structName, fn.name, inner, "(return)")
        ).toList
        val paramWarnings = fn.params.flatMap { p =>
          isUntypedPtr(p.typ).map(inner =>
            UntypedPtrWarning(structName, fn.name, inner, s"(param: ${p.name})")
          )
        }
        retWarning ++ paramWarnings
      }

    decls.flatMap {
      case d: CefDecl.ObjectStruct  => collectFromFns(d.name, d.fns)
      case d: CefDecl.HandlerStruct => collectFromFns(d.name, d.fns)
      case _                        => Nil
    }
  }

  private def collectFreeFuncUntypedPtrWarnings(
      freeFunctions: List[CefDecl.FreeFunction],
      handlerNames: Set[String]
  ): List[UntypedPtrWarning] = {
    def isUntypedPtr(ct: CType): Option[String] = ct match {
      case CType.Ptr(inner) =>
        val stripped = inner.stripPrefix("const ").stripPrefix("struct ").trim
        if (stripped == "void" || stripped.isEmpty) None
        else if (handlerNames.contains(stripped.stripPrefix("_"))) None
        else Some(inner)
      case _ => None
    }

    freeFunctions.flatMap { ff =>
      val retWarning = isUntypedPtr(ff.ret).map(inner =>
        UntypedPtrWarning(s"FREE:${ff.cName}", ff.cName, inner, "(return)")
      ).toList
      val paramWarnings = ff.params.flatMap { p =>
        isUntypedPtr(p.typ).map(inner =>
          UntypedPtrWarning(s"FREE:${ff.cName}", ff.cName, inner, s"(param: ${p.name})")
        )
      }
      retWarning ++ paramWarnings
    }
  }
}
