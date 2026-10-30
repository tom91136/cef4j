package net.kurobako.cef4j.codegen.ipc

case class DataStructSpec(
    className: String,
    packageName: String,
    cefStructName: String,
    fields: List[FieldSpec]
)
