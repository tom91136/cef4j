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
      throw IllegalStateException(s"${warnings.size} unresolved CType.Ptr remaining; codegen cannot continue")
    }

    warnings
  }

  private def isUntypedPtr(ct: CType, handlerNames: Set[String]): Option[String] = ct match {
    case CType.Ptr(inner) =>
      val stripped = inner.stripPrefix("const ").stripPrefix("struct ").trim
      if (stripped == "void" || stripped.isEmpty) None
      else if (handlerNames.contains(stripped.stripPrefix("_"))) None
      else Some(inner)
    case _ => None
  }

  private def warningsFor(
      owner: String,
      fnName: String,
      ret: CType,
      params: List[net.kurobako.cef4j.codegen.Param],
      handlerNames: Set[String]
  ): List[UntypedPtrWarning] = {
    val retWarning = isUntypedPtr(ret, handlerNames).map(inner =>
      UntypedPtrWarning(owner, fnName, inner, "(return)")
    ).toList
    val paramWarnings = params.flatMap { p =>
      isUntypedPtr(p.typ, handlerNames).map(inner =>
        UntypedPtrWarning(owner, fnName, inner, s"(param: ${p.name})")
      )
    }
    retWarning ++ paramWarnings
  }

  private def collectUntypedPtrWarnings(
      decls: List[CefDecl],
      handlerNames: Set[String]
  ): List[UntypedPtrWarning] = decls.flatMap {
    case d: CefDecl.ObjectStruct =>
      d.fns.flatMap(fn => warningsFor(d.name, fn.name, fn.ret, fn.params, handlerNames))
    case d: CefDecl.HandlerStruct =>
      d.fns.flatMap(fn => warningsFor(d.name, fn.name, fn.ret, fn.params, handlerNames))
    case _ => Nil
  }

  private def collectFreeFuncUntypedPtrWarnings(
      freeFunctions: List[CefDecl.FreeFunction],
      handlerNames: Set[String]
  ): List[UntypedPtrWarning] =
    freeFunctions.flatMap(ff => warningsFor(s"FREE:${ff.cName}", ff.cName, ff.ret, ff.params, handlerNames))
}
