package net.kurobako.cef4j.codegen.passes

import net.kurobako.cef4j.codegen.DocComments
import net.kurobako.cef4j.codegen.Naming
import net.kurobako.cef4j.codegen.ParsedTree
import net.kurobako.cef4j.codegen.RefinedTree

object InitialiseDocComments {
  def apply(
      docContext: DocComments.Context,
      parsed: ParsedTree,
      refined: RefinedTree
  )(using Naming.Context): DocComments.Context =
    DocComments.withMethodSignatures(
      DocComments.withClassNames(
        DocComments.withEnumConstants(docContext, refined.allEnums),
        parsed.knownStructNames
      ),
      refined.decls
    )
}
