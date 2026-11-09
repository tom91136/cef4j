package net.kurobako.cef4j.codegen.ipc

import scala.util.hashing.MurmurHash3

import net.kurobako.cef4j.codegen.Naming

object SharedMessages {
  private val MaxNameLength = 96
  private val Suffix        = "Message"

  def derive(specs: List[MessageSpec])(using Naming.Context): SharedMessages = {
    val groups = specs.groupBy(_.fields).toList.map { case (fields, members) =>
      val name = members match {
        case single :: Nil => single.className
        case _             => shapeName(fields)
      }
      (name, SharedMessage(name, members.head.packageName, fields), members)
    }
    val clashes = groups.groupBy(_._1).filter(_._2.size > 1).keys
    require(clashes.isEmpty, s"distinct message shapes share the name ${clashes.mkString(", ")}")
    val ordered = groups.sortBy(_._1)
    val ids     = specs.map(s => MessageId(MessageId.constant(s.className), s.messageId)).distinct
    val badIds  = ids.groupBy(_.constant).filter(_._2.size > 1).keys
    require(badIds.isEmpty, s"message names map to conflicting ids: ${badIds.mkString(", ")}")
    val refs = for {
      (name, _, members) <- groups
      spec               <- members
    } yield spec.className -> name
    SharedMessages(ordered.map(_._2), ids.sortBy(_.constant), MessageRefs(refs.toMap))
  }

  def shapeName(fields: List[FieldSpec])(using Naming.Context): String = {
    val parts = fields.map(f => Naming.capitalise(f.name) + typeTag(f.ty))
    val full  = parts.mkString
    if (parts.isEmpty) "Empty" + Suffix
    else if (full.length <= MaxNameLength) full + Suffix
    else {
      val lead = parts.scanLeft(0)(_ + _.length).tail.zip(parts).view.takeWhile(_._1 <= MaxNameLength - 8).map(_._2)
      lead.mkString + "Etc" + f"${MurmurHash3.stringHash(full) & 0xffffff}%06x" + Suffix
    }
  }

  private def typeTag(ty: FieldType)(using Naming.Context): String = ty match {
    case FieldType.I32                 => "Int"
    case FieldType.I64                 => "Long"
    case FieldType.Bool                => "Bool"
    case FieldType.Utf8String          => "String"
    case FieldType.Bytes               => "Bytes"
    case FieldType.StringList          => "Strings"
    case FieldType.RemoteHandle        => "Handle"
    case FieldType.DataStruct(cefName) => Naming.structToJavaName(cefName)
  }
}

case class SharedMessages(messages: List[SharedMessage], ids: List[MessageId], refs: MessageRefs)
