package net.kurobako.cef4j.codegen.cdp

/** AST for Chromium's Protocol Definition Language, shared by the PDL parser and the emitter. */
private[cdp] object PDL {
  final case class Flags(experimental: Boolean = false, deprecated: Boolean = false)

  enum TypeExpr {
    case Str
    case Integer
    case Number
    case Boolean
    case Obj
    case AnyType
    case Binary
    case Ref(domain: Option[String], name: String)
    case ArrayOf(element: TypeExpr)
    case InlineEnum(values: List[String])
  }

  enum TypeBody {
    case Enum(values: List[String])
    case Object(properties: List[SubItem])
  }

  enum Decl {
    case Type(
        name: String,
        alias: TypeExpr,
        body: Option[TypeBody],
        description: Option[String],
        flags: Flags
    )
    case Command(
        name: String,
        redirect: Option[String],
        params: List[SubItem],
        returns: List[SubItem],
        description: Option[String],
        flags: Flags
    )
    case Event(
        name: String,
        params: List[SubItem],
        description: Option[String],
        flags: Flags
    )
  }

  final case class SubItem(
      name: String,
      typeExpr: TypeExpr,
      optional: Boolean = false,
      description: Option[String] = None,
      flags: Flags = Flags()
  )

  final case class Domain(
      name: String,
      description: Option[String] = None,
      flags: Flags = Flags(),
      dependencies: List[String] = Nil,
      declarations: List[Decl]
  ) {
    def types: List[Decl.Type]       = declarations.collect { case t: Decl.Type => t }
    def commands: List[Decl.Command] = declarations.collect { case c: Decl.Command => c }
    def events: List[Decl.Event]     = declarations.collect { case e: Decl.Event => e }
  }

  final case class Version(major: String = "", minor: String = "")

  final case class Protocol(version: Version = Version(), domains: List[Domain] = Nil)

  def refString(ref: TypeExpr.Ref): String = ref.domain.fold(ref.name)(domain => s"$domain.${ref.name}")
}
