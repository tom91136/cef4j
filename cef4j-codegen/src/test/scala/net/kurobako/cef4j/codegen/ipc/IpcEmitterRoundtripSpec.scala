package net.kurobako.cef4j.codegen.ipc

import java.io.File
import java.io.StringWriter
import java.lang.reflect.InvocationTargetException
import java.net.URI
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import javax.tools.DiagnosticCollector
import javax.tools.JavaCompiler
import javax.tools.JavaFileObject
import javax.tools.SimpleJavaFileObject
import javax.tools.ToolProvider
import scala.jdk.CollectionConverters.*

import net.kurobako.cef4j.codegen.FileSystem
import net.kurobako.cef4j.codegen.Naming
import net.kurobako.cef4j.codegen.TempDirectorySuite

class IpcEmitterRoundtripSpec extends TempDirectorySuite {

  private def emit(spec: MessageSpec)(using Naming.Context): String =
    JavaEmitter.emitShared(SharedMessage(spec.className, spec.packageName, spec.fields))

  test("emitter produces a class that round-trips a mix of field types") {
    val spec = MessageSpec(
      className = "EvalScriptRequest",
      packageName = "test.gen",
      messageId = 100,
      fields = List(
        FieldSpec("script", FieldType.Utf8String),
        FieldSpec("timeoutMs", FieldType.I32),
        FieldSpec("isAsync", FieldType.Bool),
        FieldSpec("nonce", FieldType.I64),
        FieldSpec("digest", FieldType.Bytes),
        FieldSpec("browser", FieldType.RemoteHandle)
      )
    )

    val source      = emit(spec)
    val classes     = compileAll(spec.packageName + "." + spec.className -> source)
    val cls         = classes.get(spec.packageName + "." + spec.className)
    val handleClass = cls.getClassLoader.loadClass("net.kurobako.cef4j.ipc.session.RemoteHandle")

    val script   = "alert('hello')"
    val timeout  = 5000
    val isAsync  = true
    val nonce    = 0xdeadbeefcafebabeL
    val digest   = Array[Byte](1, 2, 3, 4, 5)
    val handleId = 17
    val handle   = handleClass.getConstructor(java.lang.Integer.TYPE).newInstance(handleId)
    val ctor     = cls.getConstructor(
      classOf[String],
      java.lang.Integer.TYPE,
      java.lang.Boolean.TYPE,
      java.lang.Long.TYPE,
      classOf[Array[Byte]],
      handleClass
    )
    val instance = ctor.newInstance(
      script,
      timeout,
      isAsync,
      nonce,
      digest,
      handle
    )

    val encodedSize = intOf(cls.getMethod("encodedSize").invoke(instance))
    val buf         = ByteBuffer.allocate(encodedSize)
    cls.getMethod("encodeInto", classOf[ByteBuffer]).invoke(instance, buf)
    assertEquals(buf.position(), encodedSize, "encodeInto must write exactly encodedSize bytes")
    buf.flip()

    val decoderField = staticValue(cls, "DECODER")
    val decoderIface = cls.getClassLoader.loadClass("net.kurobako.cef4j.ipc.session.CefMessageDecoder")
    val decodeMethod = decoderIface.getMethod("decode", classOf[ByteBuffer])
    val decoded      = decodeMethod.invoke(decoderField, buf)

    assertEquals(cls.getMethod("script").invoke(decoded), script)
    assertEquals(intOf(cls.getMethod("timeoutMs").invoke(decoded)), timeout)
    assertEquals(booleanOf(cls.getMethod("isAsync").invoke(decoded)), isAsync)
    assertEquals(longOf(cls.getMethod("nonce").invoke(decoded)), nonce)
    assertEquals(bytesOf(cls.getMethod("digest").invoke(decoded)), digest.toSeq)
    val decodedHandle = cls.getMethod("browser").invoke(decoded)
    assertEquals(intOf(handleClass.getMethod("id").invoke(decodedHandle)), handleId)
    assert(!cls.getFields.exists(_.getName == "MESSAGE_ID"), "the id lives in MessageIds")
    assert(!cls.getMethods.exists(_.getName == "messageId"), "the id belongs to the envelope")
  }

  test("shared message writes the bytes of the fixed-id message and carries no id") {
    val fields = List(
      FieldSpec("self", FieldType.RemoteHandle),
      FieldSpec("script", FieldType.Utf8String),
      FieldSpec("digest", FieldType.Bytes),
      FieldSpec("count", FieldType.I32)
    )
    val fixed   = MessageSpec("EvalRequest", "test.gen", 321, fields)
    val shared  = SharedMessage("SelfHandleScriptStringDigestBytesCountIntMessage", "test.gen", fields)
    val classes = compileAll(
      "test.gen.EvalRequest"                                      -> emit(fixed),
      "test.gen.SelfHandleScriptStringDigestBytesCountIntMessage" -> JavaEmitter.emitShared(shared)
    )
    val fixedCls  = classes.get("test.gen.EvalRequest")
    val sharedCls = classes.get("test.gen.SelfHandleScriptStringDigestBytesCountIntMessage")
    val handleCls = fixedCls.getClassLoader.loadClass("net.kurobako.cef4j.ipc.session.RemoteHandle")
    val handle    = handleCls.getConstructor(java.lang.Integer.TYPE).newInstance(9)
    val digest    = Array[Byte](7, 8, 9)

    val fixedValue  = fixedCls.getConstructors.head.newInstance(handle, "go", digest, Int.box(5))
    val sharedValue = sharedCls.getConstructors.head.newInstance(handle, "go", digest, Int.box(5))

    def encode(cls: Class[?], value: Any): Seq[Byte] = {
      val buf = ByteBuffer.allocate(intOf(cls.getMethod("encodedSize").invoke(value)))
      cls.getMethod("encodeInto", classOf[ByteBuffer]).invoke(value, buf)
      buf.array().toSeq
    }
    assertEquals(encode(sharedCls, sharedValue), encode(fixedCls, fixedValue))
    assert(!sharedCls.getMethods.exists(_.getName == "messageId"))
    assert(!sharedCls.getFields.exists(_.getName == "MESSAGE_ID"))

    val iface   = sharedCls.getClassLoader.loadClass("net.kurobako.cef4j.ipc.session.CefMessageDecoder")
    val payload = ByteBuffer.wrap(encode(fixedCls, fixedValue).toArray)
    val decoded = iface.getMethod("decode", classOf[ByteBuffer]).invoke(staticValue(sharedCls, "DECODER"), payload)
    assertEquals(sharedCls.getMethod("script").invoke(decoded), "go")
    assertEquals(intOf(sharedCls.getMethod("count").invoke(decoded)), 5)
    assertEquals(bytesOf(sharedCls.getMethod("digest").invoke(decoded)), digest.toSeq)
  }

  test("generated classes leave byte order and payload framing to the wire helpers") {
    given Naming.Context = Naming.Context.empty.copy(typesPackage = "test.types")
    val fields           = List(
      FieldSpec("self", FieldType.RemoteHandle),
      FieldSpec("script", FieldType.Utf8String),
      FieldSpec("tags", FieldType.StringList)
    )
    val data = DataStructSpec(
      className = "Label",
      packageName = "test.gen",
      cefStructName = "cef_label_t",
      fields = List(FieldSpec("text", FieldType.Utf8String)),
      shared = Some(SharedLayout("test.types.CefLabel", List(SharedMember("text", MemberKind.Str))))
    )
    val sources = List(
      emit(MessageSpec("EvalRequest", "test.gen", 1, fields)),
      JavaEmitter.emitShared(SharedMessage("SelfHandleScriptStringTagsStringsMessage", "test.gen", fields)),
      JavaDataCodecEmitter.emit(data)
    )

    sources.foreach { source =>
      assert(!source.contains("finally"), source)
      assert(!source.contains("originalOrder"), source)
      assert(!source.contains("ByteOrder"), source)
      assert(!source.contains("duplicate()"), source)
      assert(!source.contains("requireFullyConsumed"), source)
    }
    assert(sources.head.contains("WireEncoder.littleEndian(__dst, __out -> {"), sources.head)
    assert(sources.head.contains("WireDecoder.message(\"EvalRequest\", __buf -> {"), sources.head)
    assert(sources(1).contains("WireEncoder.utf8List(__out, tags);"), sources(1))
    assert(sources(1).contains("WireEncoder.utf8ListSize(tags)"), sources(1))
    assert(sources(2).contains("WireEncoder.littleEndian(dst, __out -> {"), sources(2))
  }

  test("emitter handles a fixed-size-only spec (no variable fields)") {
    val spec = MessageSpec(
      className = "Heartbeat",
      packageName = "test.gen",
      messageId = 101,
      fields = List(FieldSpec("seq", FieldType.I32), FieldSpec("ts", FieldType.I64))
    )
    val source  = emit(spec)
    val classes = compileAll(spec.packageName + "." + spec.className -> source)
    val cls     = classes.get(spec.packageName + "." + spec.className)

    val ctor     = cls.getConstructor(java.lang.Integer.TYPE, java.lang.Long.TYPE)
    val instance = ctor.newInstance(7, 1234567890L)
    val size     = intOf(cls.getMethod("encodedSize").invoke(instance))
    assertEquals(size, 12)

    val buf = ByteBuffer.allocate(size)
    cls.getMethod("encodeInto", classOf[ByteBuffer]).invoke(instance, buf)
    buf.flip()

    val decoderField = staticValue(cls, "DECODER")
    val decoderIface = cls.getClassLoader.loadClass("net.kurobako.cef4j.ipc.session.CefMessageDecoder")
    val decodeMethod = decoderIface.getMethod("decode", classOf[ByteBuffer])
    val decoded      = decodeMethod.invoke(decoderField, buf)
    assertEquals(intOf(cls.getMethod("seq").invoke(decoded)), 7)
    assertEquals(longOf(cls.getMethod("ts").invoke(decoded)), 1234567890L)
  }

  test("Java message decoder rejects negative, truncated, oversized, and trailing payloads") {
    val spec = MessageSpec(
      className = "HostileRequest",
      packageName = "test.gen",
      messageId = 102,
      fields = List(FieldSpec("name", FieldType.Utf8String))
    )
    val classes      = compileAll(spec.packageName + "." + spec.className -> emit(spec))
    val cls          = classes.get(spec.packageName + "." + spec.className)
    val decoder      = staticValue(cls, "DECODER")
    val decoderIface = cls.getClassLoader.loadClass("net.kurobako.cef4j.ipc.session.CefMessageDecoder")
    val decode       = decoderIface.getMethod("decode", classOf[ByteBuffer])

    def rejects(bytes: Array[Byte]): Unit = {
      val thrown = intercept[InvocationTargetException](decode.invoke(decoder, ByteBuffer.wrap(bytes)))
      assertIllegalArgument(thrown)
    }

    rejects(ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(-1).array())
    rejects(ByteBuffer.allocate(6).order(ByteOrder.LITTLE_ENDIAN).putInt(3).put(Array[Byte](1, 2)).array())
    rejects(ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(64 * 1024 * 1024 + 1).array())
    rejects(
      ByteBuffer.allocate(6).order(ByteOrder.LITTLE_ENDIAN).putInt(1).put(1.toByte).put(2.toByte).array()
    )
  }

  test("nested Java data struct validates lengths while parent enforces exact exhaustion") {
    given Naming.Context = Naming.Context.empty.copy(typesPackage = "test.types")
    val data             = DataStructSpec(
      className = "Label",
      packageName = "test.gen",
      cefStructName = "cef_label_t",
      fields = List(FieldSpec("text", FieldType.Utf8String)),
      shared = Some(SharedLayout("test.types.CefLabel", List(SharedMember("text", MemberKind.Str))))
    )
    val message = MessageSpec(
      className = "NestedRequest",
      packageName = "test.gen",
      messageId = 103,
      fields = List(FieldSpec("label", FieldType.DataStruct("cef_label_t")), FieldSpec("tail", FieldType.I32))
    )
    val labelType = "package test.types; public final class CefLabel { public final String text; " +
      "public CefLabel(String text) { this.text = text; } }"
    val classes = compileAll(
      "test.types.CefLabel"                                              -> labelType,
      data.packageName + "." + SpecDeriver.codecName(data.cefStructName) -> JavaDataCodecEmitter.emit(data),
      message.packageName + "." + message.className                      -> emit(message)
    )
    val cls          = classes.get(message.packageName + "." + message.className)
    val decoder      = staticValue(cls, "DECODER")
    val decoderIface = cls.getClassLoader.loadClass("net.kurobako.cef4j.ipc.session.CefMessageDecoder")
    val decode       = decoderIface.getMethod("decode", classOf[ByteBuffer])

    val valid = ByteBuffer
      .allocate(10)
      .order(ByteOrder.LITTLE_ENDIAN)
      .putInt(2)
      .put('o'.toByte)
      .put('k'.toByte)
      .putInt(7)
      .array()
    val decoded = decode.invoke(decoder, ByteBuffer.wrap(valid))
    assertEquals(intOf(cls.getMethod("tail").invoke(decoded)), 7)

    val truncated = ByteBuffer.allocate(5).order(ByteOrder.LITTLE_ENDIAN).putInt(2).put('o'.toByte).array()
    val thrown    = intercept[InvocationTargetException](decode.invoke(decoder, ByteBuffer.wrap(truncated)))
    assertIllegalArgument(thrown)
  }

  test("data codec writes a null string as empty and keeps the wire size field zero") {
    given Naming.Context = Naming.Context.empty.copy(typesPackage = "test.types")
    val data             = DataStructSpec(
      className = "Probe",
      packageName = "test.gen",
      cefStructName = "cef_probe_t",
      fields = List(
        FieldSpec("size", FieldType.I64),
        FieldSpec("kind", FieldType.I32),
        FieldSpec("letter", FieldType.I32),
        FieldSpec("name", FieldType.Utf8String)
      ),
      shared = Some(SharedLayout(
        "test.types.CefProbe",
        List(
          SharedMember("size", MemberKind.WireSize),
          SharedMember("kind", MemberKind.EnumOf("test.types.CefKind")),
          SharedMember("letter", MemberKind.Char),
          SharedMember("name", MemberKind.Str)
        )
      ))
    )
    val kind = "package test.types; public final class CefKind { public final long value; " +
      "private CefKind(long v) { this.value = v; } public static CefKind of(long v) { return new CefKind(v); } }"
    val probe =
      "package test.types; public final class CefProbe { public final CefKind kind; public final char letter; " +
        "public final String name; public CefProbe(CefKind kind, char letter, String name) " +
        "{ this.kind = kind; this.letter = letter; this.name = name; } }"
    val classes = compileAll(
      "test.types.CefKind"  -> kind,
      "test.types.CefProbe" -> probe,
      "test.gen.ProbeCodec" -> JavaDataCodecEmitter.emit(data)
    )
    val codec = classes.get("test.gen.ProbeCodec")
    val value = classes.get("test.types.CefProbe").getConstructors.head.newInstance(javaNull, Char.box('z'), javaNull)
    val size  = codec.getMethod("encodedSize", value.getClass).invoke(javaNull, value)
    assertEquals(intOf(size), 8 + 4 + 4 + 4)
    val dst = ByteBuffer.allocate(20)
    codec.getMethod("encode", classOf[ByteBuffer], value.getClass).invoke(javaNull, dst, value)
    assertEquals(dst.position(), 20)
    dst.flip().order(ByteOrder.LITTLE_ENDIAN)
    assertEquals((dst.getLong(), dst.getInt(), dst.getInt(), dst.getInt()), (0L, 0, 'z'.toInt, 0))
  }

  test("emitter rejects negative message ids") {
    intercept[IllegalArgumentException] {
      MessageSpec("X", "test.gen", -1, Nil)
    }
  }

  test("C++ emitter bounds every decode and rejects negative variable lengths") {
    val spec = MessageSpec(
      className = "HostileRequest",
      packageName = "test.gen",
      messageId = 102,
      fields = List(
        FieldSpec("name", FieldType.Utf8String),
        FieldSpec("payload", FieldType.Bytes),
        FieldSpec("items", FieldType.StringList),
        FieldSpec("tail", FieldType.I64)
      )
    )
    val source = CppEmitter.emit(spec)
    assert(source.contains("""out.name = reader.string("name");"""))
    assert(source.contains("""out.payload = reader.bytes("payload");"""))
    assert(source.contains("""out.items = reader.strings("items");"""))
    assert(source.contains("""out.tail = reader.i64("tail");"""))
    assert(!source.contains("(void)len"))
    assert(!source.contains("decode(const uint8_t* src, size_t len) noexcept"))
  }

  private val stubSources: Map[String, String] = Map(
    "org.jspecify.annotations.Nullable" ->
      "package org.jspecify.annotations; @java.lang.annotation.Target(java.lang.annotation.ElementType.TYPE_USE) public @interface Nullable {}",
    "net.kurobako.cef4j.ipc.session.RemoteHandle" ->
      """package net.kurobako.cef4j.ipc.session;
        |public final class RemoteHandle {
        |    private final int id;
        |    public RemoteHandle(int id) { this.id = id; }
        |    public int id() { return id; }
        |}
        |""".stripMargin,
    "net.kurobako.cef4j.ipc.session.CefMessageView" ->
      """package net.kurobako.cef4j.ipc.session;
        |public interface CefMessageView {}
        |""".stripMargin,
    "net.kurobako.cef4j.ipc.session.CefMessageEncoder" ->
      """package net.kurobako.cef4j.ipc.session;
        |public interface CefMessageEncoder {
        |    int encodedSize();
        |    void encodeInto(java.nio.ByteBuffer dst);
        |}
        |""".stripMargin,
    "net.kurobako.cef4j.ipc.session.CefMessageDecoder" ->
      """package net.kurobako.cef4j.ipc.session;
        |@FunctionalInterface
        |public interface CefMessageDecoder<T extends CefMessageView> {
        |    T decode(java.nio.ByteBuffer payload);
        |}
        |""".stripMargin,
    "net.kurobako.cef4j.ipc.session.WireEncoder" ->
      """package net.kurobako.cef4j.ipc.session;
        |public final class WireEncoder {
        |    public static void littleEndian(java.nio.ByteBuffer dst, java.util.function.Consumer<java.nio.ByteBuffer> body) {
        |        java.nio.ByteOrder original = dst.order();
        |        try {
        |            dst.order(java.nio.ByteOrder.LITTLE_ENDIAN);
        |            body.accept(dst);
        |        } finally {
        |            dst.order(original);
        |        }
        |    }
        |    public static void bytes(java.nio.ByteBuffer dst, byte[] value) {
        |        dst.putInt(value.length);
        |        dst.put(value);
        |    }
        |    public static void bool(java.nio.ByteBuffer dst, boolean value) {
        |        dst.put((byte) (value ? 1 : 0));
        |    }
        |    public static void utf8List(java.nio.ByteBuffer dst, String[] values) {
        |        dst.putInt(values.length);
        |        for (String value : values) bytes(dst, value.getBytes(java.nio.charset.StandardCharsets.UTF_8));
        |    }
        |    public static int utf8ListSize(String[] values) {
        |        int size = 0;
        |        for (String value : values) size += 4 + value.getBytes(java.nio.charset.StandardCharsets.UTF_8).length;
        |        return size;
        |    }
        |}
        |""".stripMargin,
    "net.kurobako.cef4j.ipc.session.WireDecoder" ->
      """package net.kurobako.cef4j.ipc.session;
        |public final class WireDecoder {
        |    private static final int MAX_FIELD_BYTES = 64 * 1024 * 1024;
        |    private static final int MAX_COLLECTION_ITEMS = 1_000_000;
        |    public static void requireRemaining(java.nio.ByteBuffer source, int count, String field) {
        |        if (count < 0 || count > source.remaining()) throw new IllegalArgumentException("truncated " + field);
        |    }
        |    public static int length(java.nio.ByteBuffer source, String field) {
        |        requireRemaining(source, 4, field);
        |        int value = source.getInt();
        |        if (value < 0 || value > MAX_FIELD_BYTES || value > source.remaining())
        |            throw new IllegalArgumentException("invalid length for " + field);
        |        return value;
        |    }
        |    public static int count(java.nio.ByteBuffer source, String field) {
        |        requireRemaining(source, 4, field);
        |        int value = source.getInt();
        |        if (value < 0 || value > MAX_COLLECTION_ITEMS || value > source.remaining() / 4)
        |            throw new IllegalArgumentException("invalid count for " + field);
        |        return value;
        |    }
        |    public static int int32(java.nio.ByteBuffer source, String field) {
        |        requireRemaining(source, 4, field);
        |        return source.getInt();
        |    }
        |    public static long int64(java.nio.ByteBuffer source, String field) {
        |        requireRemaining(source, 8, field);
        |        return source.getLong();
        |    }
        |    public static boolean bool(java.nio.ByteBuffer source, String field) {
        |        requireRemaining(source, 1, field);
        |        return source.get() != 0;
        |    }
        |    public static RemoteHandle handle(java.nio.ByteBuffer source, String field) {
        |        return new RemoteHandle(int32(source, field));
        |    }
        |    public static byte[] bytes(java.nio.ByteBuffer source, String field) {
        |        byte[] value = new byte[length(source, field)];
        |        source.get(value);
        |        return value;
        |    }
        |    public static String utf8(java.nio.ByteBuffer source, String field) {
        |        return new String(bytes(source, field), java.nio.charset.StandardCharsets.UTF_8);
        |    }
        |    public static String[] utf8List(java.nio.ByteBuffer source, String field) {
        |        String[] values = new String[count(source, field)];
        |        for (int i = 0; i < values.length; i++) values[i] = utf8(source, field + "[" + i + "]");
        |        return values;
        |    }
        |    public static <T extends CefMessageView> CefMessageDecoder<T> message(
        |            String type, java.util.function.Function<java.nio.ByteBuffer, T> body) {
        |        return payload -> {
        |            java.nio.ByteBuffer buf = payload.duplicate().order(java.nio.ByteOrder.LITTLE_ENDIAN);
        |            T value = body.apply(buf);
        |            requireFullyConsumed(buf, type);
        |            return value;
        |        };
        |    }
        |    public static void requireFullyConsumed(java.nio.ByteBuffer source, String type) {
        |        if (source.hasRemaining()) throw new IllegalArgumentException("trailing bytes for " + type);
        |    }
        |}
        |""".stripMargin
  )

  private class StringSource(name: String, code: String)
      extends SimpleJavaFileObject(
        URI.create(s"string:///${name.replace('.', '/')}.java"),
        JavaFileObject.Kind.SOURCE
      ) {
    override def getCharContent(ignoreEncoding: Boolean): CharSequence = code
  }

  private class InMemoryClassLoader(blobs: Map[String, Array[Byte]], parent: ClassLoader)
      extends ClassLoader(parent) {
    override def findClass(name: String): Class[?] =
      blobs.get(name) match {
        case Some(bytes) => defineClass(name, bytes, 0, bytes.length)
        case None        => super.findClass(name)
      }
  }

  private class CompiledClasses(loader: ClassLoader) {
    def get(fqn: String): Class[?] = loader.loadClass(fqn)
  }

  private def compileAll(extra: (String, String)*): CompiledClasses = {
    val javac: JavaCompiler = ToolProvider.getSystemJavaCompiler
    val compiler = Option(javac).getOrElse(fail("no system Java compiler available; ensure tests run on JDK not JRE"))

    val all                                     = (stubSources ++ extra.toMap).toList
    val sources: java.util.List[JavaFileObject] = all.view
      .map { case (name, code) => StringSource(name, code) }
      .toList
      .asJava

    val output = tempDirectory("cef4j-codegen-javac-")
    try {
      val diagnostics = DiagnosticCollector[JavaFileObject]
      val fileManager =
        compiler.getStandardFileManager(diagnostics, java.util.Locale.getDefault, StandardCharsets.UTF_8)
      val options = List("-Xlint:none", "-d", output.toString).asJava
      val ok      =
        try compiler.getTask(
            StringWriter(),
            fileManager,
            diagnostics,
            options,
            java.util.List.of[String](),
            sources
          ).call()
        finally fileManager.close()
      if (!ok) fail("javac failed for emitted sources")

      val blobs = FileSystem.descendants(output).filter(_.toString.endsWith(".class")).map { path =>
        val relative = output.relativize(path).toString.stripSuffix(".class").replace(File.separatorChar, '.')
        relative -> Files.readAllBytes(path)
      }.toMap
      CompiledClasses(InMemoryClassLoader(blobs, getClass.getClassLoader))
    } finally FileSystem.deleteTree(output)
  }

  /** The receiver of a static field, which reflection ignores. */
  private def javaNull: AnyRef = Option.empty[AnyRef].orNull

  private def staticValue(owner: Class[?], name: String): Any =
    owner.getField(name).get(javaNull)

  private def intOf(value: Any): Int = value match {
    case number: Int => number
    case other       => fail(s"not an int: $other")
  }

  private def longOf(value: Any): Long = value match {
    case number: Long => number
    case other        => fail(s"not a long: $other")
  }

  private def booleanOf(value: Any): Boolean = value match {
    case boolean: Boolean => boolean
    case other            => fail(s"not a boolean: $other")
  }

  private def bytesOf(value: Any): Seq[Byte] = value match {
    case bytes: Array[?] => bytes.view.collect { case byte: Byte => byte }.toSeq
    case other           => fail(s"not an array: $other")
  }

  private def assertIllegalArgument(thrown: InvocationTargetException): Unit =
    thrown.getCause match {
      case _: IllegalArgumentException => ()
      case other                       => fail(s"expected IllegalArgumentException, got $other")
    }
}
