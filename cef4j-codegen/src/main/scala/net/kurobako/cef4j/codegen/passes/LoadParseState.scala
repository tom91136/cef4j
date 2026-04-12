package net.kurobako.cef4j.codegen.passes

import net.kurobako.cef4j.codegen.Config
import net.kurobako.cef4j.codegen.HeaderInputs
import net.kurobako.cef4j.codegen.Naming
import net.kurobako.cef4j.codegen.ParseState

object LoadParseState {
  def apply(cfg: Config, headerInputs: HeaderInputs): ParseState = {
    val metadata            = IndexHeaderMetadata(cfg)
    val compoundSegments    = metadata.deriveCompoundSegments(cfg.cefInclude)
    val structHeaderMap     = IndexStructHeaders(headerInputs.capiDir, cfg.extraCapiDirs)
    val platformTypes       = IndexPlatformTypes(cfg)
    val subPackages         = Naming.buildSubPackages(structHeaderMap)
    val platformSubPackages =
      if (cfg.javaPlatformSubPackage.nonEmpty)
        platformTypes.map { cefName =>
          val pkg = subPackages.get(cefName) match {
            case Some(existing) if existing.nonEmpty => s"${cfg.javaPlatformSubPackage}.$existing"
            case _                                   => cfg.javaPlatformSubPackage
          }
          cefName -> pkg
        }.toMap
      else Map.empty[String, String]
    val allSubPackages = subPackages ++ platformSubPackages

    ParseState(
      namingContext = Naming.Context.fromCppClassNames(
        metadata.cppClassNames,
        compoundSegments,
        cfg.javaPackage,
        allSubPackages,
        platformCppMode = !cfg.emitCommonCpp && cfg.javaPlatformSubPackage.nonEmpty
      ),
      docContext = InitialiseDocContext(cfg.cefInclude),
      handlerNames = metadata.handlerNames,
      docs = metadata.docs,
      cppTypeInfo = metadata.cppTypeInfo,
      enumDocs = metadata.enumDocs,
      classDocs = metadata.classDocs,
      structHeaderMap = structHeaderMap,
      structFieldDocs = metadata.structFieldDocs,
      platformSpecificTypes = platformTypes
    )
  }
}
