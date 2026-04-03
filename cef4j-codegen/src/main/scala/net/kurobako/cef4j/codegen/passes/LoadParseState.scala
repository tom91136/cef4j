package net.kurobako.cef4j.codegen.passes

import net.kurobako.cef4j.codegen.Config
import net.kurobako.cef4j.codegen.HeaderInputs
import net.kurobako.cef4j.codegen.Naming
import net.kurobako.cef4j.codegen.ParseState

object LoadParseState {
  def apply(cfg: Config, headerInputs: HeaderInputs): ParseState = {
    val metadata         = IndexHeaderMetadata(cfg)
    val compoundSegments = metadata.deriveCompoundSegments(cfg.cefInclude)

    ParseState(
      namingContext = Naming.Context.fromCppClassNames(metadata.cppClassNames, compoundSegments, cfg.javaPackage),
      docContext = InitialiseDocContext(cfg.cefInclude),
      handlerNames = metadata.handlerNames,
      docs = metadata.docs,
      cppTypeInfo = metadata.cppTypeInfo,
      enumDocs = metadata.enumDocs,
      classDocs = metadata.classDocs,
      structHeaderMap = IndexStructHeaders(headerInputs.capiDir, cfg.extraCapiDirs),
      structFieldDocs = metadata.structFieldDocs
    )
  }
}
