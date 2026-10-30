package net.kurobako.cef4j.codegen.ipc

case class FacadeSpec(
    className: String,
    packageName: String,
    cefStructName: String,
    methods: List[FacadeMethod],
    affinity: ProcessAffinity = ProcessAffinity.Browser
)
