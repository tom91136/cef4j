package net.kurobako.cef4j.codegen.cdp

/** AST for Chromium's Protocol Definition Language, shared by the PDL parser and the emitter. */
object PDL {
  case class Flags(experimental: Boolean = false, deprecated: Boolean = false)

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

  // The analysis reads the parameters every case of a sum shares as fields to hoist into a parent; here the parent is
  // the enum itself, and the cases still name the parameters because a case can only pass them to the parent. Taking
  // the data out of the sum would make every reader match the form of a declaration before it reads its name.
  @SuppressWarnings(Array("baumkuchen:common-factor"))
  enum Decl(val name: String, val description: Option[String], val flags: Flags) {
    case Type(
        override val name: String,
        alias: TypeExpr,
        body: Option[TypeBody],
        override val description: Option[String],
        override val flags: Flags
    ) extends Decl(name, description, flags)
    case Command(
        override val name: String,
        params: List[SubItem],
        returns: List[SubItem],
        override val description: Option[String],
        override val flags: Flags
    ) extends Decl(name, description, flags)
    case Event(
        override val name: String,
        params: List[SubItem],
        override val description: Option[String],
        override val flags: Flags
    ) extends Decl(name, description, flags)
  }

  case class SubItem(
      name: String,
      typeExpr: TypeExpr,
      optional: Boolean = false,
      description: Option[String] = None,
      flags: Flags = Flags()
  )

  case class Domain(
      name: String,
      description: Option[String] = None,
      flags: Flags = Flags(),
      declarations: List[Decl]
  ) {
    def types: List[Decl.Type]       = declarations.collect { case t: Decl.Type => t }
    def commands: List[Decl.Command] = declarations.collect { case c: Decl.Command => c }
    def events: List[Decl.Event]     = declarations.collect { case e: Decl.Event => e }
  }

  case class Version(major: String = "", minor: String = "")

  case class Protocol(version: Version = Version(), domains: List[Domain] = Nil)
}
