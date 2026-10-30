package net.kurobako.cef4j.codegen

case class ParsedTree(
    decls: List[CefDecl],
    structDecls: List[CefDecl],
    freeFunctions: List[CefDecl.FreeFunction],
    knownStructNames: Set[String]
)
