package net.kurobako.cef4j.codegen

import java.nio.file.Path

object JavaInterfaceCodeGen {

  def emitObject(
      decl: CefDecl.ObjectStruct,
      outDir: Path,
      docs: Map[String, String] = Map.empty,
      classDoc: String = "",
      nativePeerBody: String = ""
  ): Unit = {
    val javaName = Naming.structToJavaName(decl.name)
    val content  =
      renderInterface(javaName, decl.fns, docs, isObject = true, classDoc = classDoc, nativePeerBody = nativePeerBody)
    JavaCodeGen.writeJavaFile(outDir, javaName, content)
  }

  def emitHandler(
      decl: CefDecl.HandlerStruct,
      outDir: Path,
      docs: Map[String, String] = Map.empty,
      classDoc: String = ""
  ): Unit = {
    val javaName = Naming.structToJavaName(decl.name)
    val content  = renderInterface(javaName, decl.fns, docs, isObject = false, classDoc = classDoc)
    JavaCodeGen.writeJavaFile(outDir, javaName, content)
  }

  private def renderInterface(
      javaName: String,
      fns: List[FnPtr],
      docs: Map[String, String],
      isObject: Boolean,
      classDoc: String = "",
      nativePeerBody: String = ""
  ): String = {
    val hasNullable = fns.exists { fn =>
      collectOptionalParams(fn).exists(pName =>
        fn.params.exists(p => p.name == pName && JavaCodeGen.isReferenceType(p.typ))
      )
    }
    val hasNonnull = fns.exists { fn =>
      val optionals = collectOptionalParams(fn)
      fn.params.exists(p => !optionals.contains(p.name) && JavaCodeGen.isReferenceType(p.typ))
    }

    val methods = fns.map { fn =>
      val methodName = Naming.toCamelCase(fn.name)
      val optionals  = collectOptionalParams(fn)

      val retType = if (isObject && JavaCodeGen.isOptionalReturn(fn)) {
        s"Optional<${Naming.javaType(fn.ret)}>"
      } else {
        Naming.javaType(fn.ret)
      }

      val params = fn.params.map { p =>
        val jType = Naming.javaType(p.typ)
        val pName = Naming.toCamelCase(p.name)
        if (optionals.contains(p.name) && JavaCodeGen.isReferenceType(p.typ))
          s"@Nullable $jType $pName"
        else if (JavaCodeGen.isReferenceType(p.typ))
          s"@Nonnull $jType $pName"
        else
          s"$jType $pName"
      }.mkString(", ")

      val javadoc = DocComments.forMethod(fn, docs)

      // Object interfaces: abstract methods. Handler interfaces: default methods with stub bodies.
      if (isObject) {
        s"$javadoc    $retType $methodName($params);"
      } else {
        val defaultReturn = fn.metaAttrs.collectFirst { case ("default_retval", v) => v } match {
          case Some(retVal) => defaultReturnFromMeta(fn.ret, retVal)
          case None         => defaultReturnForType(fn.ret)
        }
        s"$javadoc    default $retType $methodName($params) {$defaultReturn\n    }"
      }
    }.mkString("\n\n")

    val hasOptional = isObject && fns.exists(JavaCodeGen.isOptionalReturn)

    val imports = List(
      if (hasOptional) Some("import java.util.Optional;") else None,
      if (hasNullable) Some("import javax.annotation.Nullable;") else None,
      if (hasNonnull) Some("import javax.annotation.Nonnull;") else None
    ).flatten

    val nativePeerSection = if (nativePeerBody.nonEmpty) s"\n$nativePeerBody\n" else ""
    JavaCodeGen.renderJavaFile(
      declaration = s"public interface $javaName",
      body = s"$methods$nativePeerSection",
      imports = imports,
      classDoc = classDoc
    )
  }

  /** Collect all parameter names marked as optional from metacomment attributes. */
  private def collectOptionalParams(fn: FnPtr): Set[String] =
    fn.metaAttrs.collect { case ("optional_param", p) => p }.toSet

  /** Default return when --cef(default_retval=X)-- is present. */
  private def defaultReturnFromMeta(ret: CType, retVal: String): String = ret match {
    case CType.Enum(cefName) =>
      val javaEnum = Naming.structToJavaName(cefName)
      s"\n        return $javaEnum.$retVal;"
    case CType.Bool =>
      if (retVal == "true" || retVal == "1") "\n        return true;"
      else "\n        return false;"
    case CType.Int | CType.UInt =>
      s"\n        return $retVal;"
    case CType.Long | CType.SizeT =>
      s"\n        return ${retVal}L;"
    case _ => defaultReturnForType(ret)
  }

  private def defaultReturnForType(ret: CType): String = ret match {
    case CType.Void            => ""
    case CType.Bool            => "\n        return false;"
    case CType.Int             => "\n        return 0;"
    case CType.UInt            => "\n        return 0;"
    case CType.Long            => "\n        return 0L;"
    case CType.SizeT           => "\n        return 0L;"
    case CType.Float           => "\n        return 0.0f;"
    case CType.Double          => "\n        return 0.0;"
    case CType.Ptr(_)          => "\n        return 0L;"
    case CType.JString         => "\n        return null;"
    case CType.Enum(_)         => "\n        return null;"
    case CType.OutInt          => "\n        return null;"
    case CType.OutBool         => "\n        return null;"
    case CType.ByValueIn(_)    => "\n        return null;"
    case CType.ByValueOut(_)   => "\n        return null;"
    case CType.ByValueArray(_) => "\n        return null;"
    case CType.PixelBuffer     => "\n        return null;"
    case CType.DataStruct(_)   => "\n        return null;"
    case CType.StringList      => "\n        return null;"
    case CType.StringMap       => "\n        return null;"
    case CType.StringMultimap  => "\n        return null;"
  }
}
