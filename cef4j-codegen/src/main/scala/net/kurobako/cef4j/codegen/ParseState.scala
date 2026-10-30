package net.kurobako.cef4j.codegen

case class ParseState(
    namingContext: Naming.Context,
    docContext: DocComments.Context,
    handlerNames: Set[String],
    docs: Map[String, String],
    cppTypeInfo: Map[String, CppMethodTypeInfo],
    enumDocs: Map[String, (String, Map[String, String])],
    classDocs: Map[String, String],
    structHeaderMap: Map[String, String],
    structFieldDocs: Map[String, Map[String, String]],
    platformSpecificTypes: Set[String] = Set.empty
)
