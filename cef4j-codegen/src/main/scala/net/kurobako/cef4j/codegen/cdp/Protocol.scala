package net.kurobako.cef4j.codegen.cdp

import upickle.default.ReadWriter
import upickle.implicits.key

private[cdp] final case class ProtocolVersion(major: String = "", minor: String = "") derives ReadWriter

private[cdp] final case class Protocol(
    version: ProtocolVersion = ProtocolVersion(),
    domains: List[Domain] = Nil
) derives ReadWriter

private[cdp] final case class Domain(
    domain: String,
    description: Option[String] = None,
    experimental: Boolean = false,
    deprecated: Boolean = false,
    dependencies: List[String] = Nil,
    types: List[Item] = Nil,
    commands: List[Item] = Nil,
    events: List[Item] = Nil
) derives ReadWriter

private[cdp] final case class Item(
    id: Option[String] = None,
    name: Option[String] = None,
    description: Option[String] = None,
    experimental: Boolean = false,
    deprecated: Boolean = false,
    optional: Boolean = false,
    @key("type") kind: Option[String] = None,
    @key("$ref") reference: Option[String] = None,
    items: Option[Item] = None,
    properties: List[Item] = Nil,
    parameters: List[Item] = Nil,
    returns: List[Item] = Nil,
    @key("enum") enumValues: List[String] = Nil,
    redirect: Option[String] = None
) derives ReadWriter
