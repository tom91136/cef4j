package net.kurobako.cef4j.codegen.passes

import java.nio.file.Path

import net.kurobako.cef4j.codegen.CHeaderParser
import net.kurobako.cef4j.codegen.CefDecl
import net.kurobako.cef4j.codegen.Config
import net.kurobako.cef4j.codegen.HeaderInputs
import net.kurobako.cef4j.codegen.ParseState
import net.kurobako.cef4j.codegen.ParsedTree
import net.kurobako.cef4j.codegen.namedStruct
import net.kurobako.cef4j.codegen.withSourceHeader

object ParseTree {
  def apply(
      preprocessed: List[(Path, String)],
      headerInputs: HeaderInputs,
      cfg: Config,
      parseState: ParseState
  ): ParsedTree = {
    val rawDecls = preprocessed.flatMap { case (_, src) =>
      CHeaderParser.parse(src, parseState.handlerNames).map(attachSourceHeader(_, parseState.structHeaderMap))
    }

    val knownStructNames = rawDecls.flatMap(_.namedStruct).toSet
    val dataStructNames  = rawDecls.collect { case d: CefDecl.DataStruct => d.name }.toSet
    val rawFreeFunctions =
      CHeaderParser.parseFreeExports(headerInputs.capiDir, knownStructNames, dataStructNames, cfg.extraCapiDirs)

    val decls = CHeaderParser.promoteBufferParams(
      CHeaderParser.promoteArrayParams(
        CHeaderParser.reclassifyPointers(rawDecls ++ rawFreeFunctions)
      )
    )

    ParsedTree(
      decls = decls,
      structDecls = decls.collect {
        case d: CefDecl.ObjectStruct  => d
        case d: CefDecl.HandlerStruct => d
        case d: CefDecl.DataStruct    => d
        case d: CefDecl.Enum          => d
      },
      freeFunctions = decls.collect { case d: CefDecl.FreeFunction => d },
      knownStructNames = knownStructNames
    )
  }

  private def attachSourceHeader(decl: CefDecl, structHeaderMap: Map[String, String]): CefDecl =
    decl.namedStruct
      .map(name => decl.withSourceHeader(structHeaderMap.getOrElse(name, "")))
      .getOrElse(decl)
}
