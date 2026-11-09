package net.kurobako.cef4j.codegen.ipc

case class DataStructSpec(
    className: String,
    packageName: String,
    cefStructName: String,
    fields: List[FieldSpec],
    shared: Option[SharedLayout] = None
) {
  shared.foreach(s => require(s.members.size == fields.size, s"$cefStructName members do not match its wire fields"))
}
