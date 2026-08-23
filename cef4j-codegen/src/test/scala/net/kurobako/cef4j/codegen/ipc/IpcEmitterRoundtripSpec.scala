package net.kurobako.cef4j.codegen.ipc

import java.lang.reflect.InvocationTargetException
import java.net.URI
import java.nio.ByteBuffer
import java.nio.file.Files
import javax.tools.JavaCompiler
import javax.tools.JavaFileObject
import javax.tools.SimpleJavaFileObject
import javax.tools.ToolProvider
import scala.jdk.CollectionConverters.*

import net.kurobako.cef4j.codegen.FileSystem

class IpcEmitterRoundtripSpec extends munit.FunSuite {

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

    val source      = JavaEmitter.emit(spec)
    val classes     = compileAll(spec.packageName + "." + spec.className -> source)
    val cls         = classes.get(spec.packageName + "." + spec.className)
    val handleClass = cls.getClassLoader.loadClass("net.kurobako.cef4j.ipc.session.RemoteHandle")

    val script   = "alert('hello')"
    val timeout  = 5000
    val isAsync  = true
    val nonce    = 0xdeadbeefcafebabeL
    val digest   = Array[Byte](1, 2, 3, 4, 5)
    val handleId = 17
    val handle   = handleClass.getConstructor(java.lang.Integer.TYPE).newInstance(java.lang.Integer.valueOf(handleId))
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
      java.lang.Integer.valueOf(timeout),
      java.lang.Boolean.valueOf(isAsync),
      java.lang.Long.valueOf(nonce),
      digest,
      handle
    )

    val encodedSize = cls.getMethod("encodedSize").invoke(instance).asInstanceOf[Int]
    val buf         = ByteBuffer.allocate(encodedSize)
    cls.getMethod("encodeInto", classOf[ByteBuffer]).invoke(instance, buf)
    assertEquals(buf.position(), encodedSize, "encodeInto must write exactly encodedSize bytes")
    buf.flip()

    val decoderField = cls.getField("DECODER").get(null)
    // Synthetic lambda classes must be invoked through their declared interface.
    val decoderIface = cls.getClassLoader.loadClass("net.kurobako.cef4j.ipc.session.CefMessageDecoder")
    val decodeMethod = decoderIface.getMethod("decode", classOf[ByteBuffer])
    val decoded      = decodeMethod.invoke(decoderField, buf)

    assertEquals(cls.getMethod("script").invoke(decoded), script)
    assertEquals(cls.getMethod("timeoutMs").invoke(decoded).asInstanceOf[Int], timeout)
    assertEquals(cls.getMethod("isAsync").invoke(decoded).asInstanceOf[Boolean], isAsync)
    assertEquals(cls.getMethod("nonce").invoke(decoded).asInstanceOf[Long], nonce)
    assertEquals(cls.getMethod("digest").invoke(decoded).asInstanceOf[Array[Byte]].toSeq, digest.toSeq)
    val decodedHandle = cls.getMethod("browser").invoke(decoded)
    assertEquals(handleClass.getMethod("id").invoke(decodedHandle).asInstanceOf[Int], handleId)
    assertEquals(cls.getField("MESSAGE_ID").getInt(null), 100)
    assertEquals(cls.getMethod("messageId").invoke(decoded).asInstanceOf[Int], 100)
  }

  test("emitter handles a fixed-size-only spec (no variable fields)") {
    val spec = MessageSpec(
      className = "Heartbeat",
      packageName = "test.gen",
      messageId = 101,
      fields = List(FieldSpec("seq", FieldType.I32), FieldSpec("ts", FieldType.I64))
    )
    val source  = JavaEmitter.emit(spec)
    val classes = compileAll(spec.packageName + "." + spec.className -> source)
    val cls     = classes.get(spec.packageName + "." + spec.className)

    val ctor     = cls.getConstructor(java.lang.Integer.TYPE, java.lang.Long.TYPE)
    val instance = ctor.newInstance(java.lang.Integer.valueOf(7), java.lang.Long.valueOf(1234567890L))
    val size     = cls.getMethod("encodedSize").invoke(instance).asInstanceOf[Int]
    assertEquals(size, 12) // 4 + 8

    val buf = ByteBuffer.allocate(size)
    cls.getMethod("encodeInto", classOf[ByteBuffer]).invoke(instance, buf)
    buf.flip()

    val decoderField = cls.getField("DECODER").get(null)
    // Synthetic lambda classes must be invoked through their declared interface.
    val decoderIface = cls.getClassLoader.loadClass("net.kurobako.cef4j.ipc.session.CefMessageDecoder")
    val decodeMethod = decoderIface.getMethod("decode", classOf[ByteBuffer])
    val decoded      = decodeMethod.invoke(decoderField, buf)
    assertEquals(cls.getMethod("seq").invoke(decoded).asInstanceOf[Int], 7)
    assertEquals(cls.getMethod("ts").invoke(decoded).asInstanceOf[Long], 1234567890L)
  }

  test("Java message decoder rejects negative, truncated, oversized, and trailing payloads") {
    val spec = MessageSpec(
      className = "HostileRequest",
      packageName = "test.gen",
      messageId = 102,
      fields = List(FieldSpec("name", FieldType.Utf8String))
    )
    val classes      = compileAll(spec.packageName + "." + spec.className -> JavaEmitter.emit(spec))
    val cls          = classes.get(spec.packageName + "." + spec.className)
    val decoder      = cls.getField("DECODER").get(null)
    val decoderIface = cls.getClassLoader.loadClass("net.kurobako.cef4j.ipc.session.CefMessageDecoder")
    val decode       = decoderIface.getMethod("decode", classOf[ByteBuffer])

    def rejects(bytes: Array[Byte]): Unit = {
      val thrown = intercept[InvocationTargetException](decode.invoke(decoder, ByteBuffer.wrap(bytes)))
      assert(thrown.getCause.isInstanceOf[IllegalArgumentException], thrown.getCause.toString)
    }

    rejects(ByteBuffer.allocate(4).order(java.nio.ByteOrder.LITTLE_ENDIAN).putInt(-1).array())
    rejects(ByteBuffer.allocate(6).order(java.nio.ByteOrder.LITTLE_ENDIAN).putInt(3).put(Array[Byte](1, 2)).array())
    rejects(ByteBuffer.allocate(4).order(java.nio.ByteOrder.LITTLE_ENDIAN).putInt(64 * 1024 * 1024 + 1).array())
    rejects(
      ByteBuffer.allocate(6).order(java.nio.ByteOrder.LITTLE_ENDIAN).putInt(1).put(1.toByte).put(2.toByte).array()
    )
  }

  test("nested Java data struct validates lengths while parent enforces exact exhaustion") {
    val data = DataStructSpec(
      className = "Label",
      packageName = "test.gen",
      cefStructName = "cef_label_t",
      fields = List(FieldSpec("text", FieldType.Utf8String))
    )
    val message = MessageSpec(
      className = "NestedRequest",
      packageName = "test.gen",
      messageId = 103,
      fields = List(FieldSpec("label", FieldType.DataStruct("cef_label_t")), FieldSpec("tail", FieldType.I32))
    )
    val classes = compileAll(
      data.packageName + "." + data.className       -> JavaDataStructEmitter.emit(data),
      message.packageName + "." + message.className -> JavaEmitter.emit(message)
    )
    val cls          = classes.get(message.packageName + "." + message.className)
    val decoder      = cls.getField("DECODER").get(null)
    val decoderIface = cls.getClassLoader.loadClass("net.kurobako.cef4j.ipc.session.CefMessageDecoder")
    val decode       = decoderIface.getMethod("decode", classOf[ByteBuffer])

    val valid = ByteBuffer
      .allocate(10)
      .order(java.nio.ByteOrder.LITTLE_ENDIAN)
      .putInt(2)
      .put('o'.toByte)
      .put('k'.toByte)
      .putInt(7)
      .array()
    val decoded = decode.invoke(decoder, ByteBuffer.wrap(valid))
    assertEquals(cls.getMethod("tail").invoke(decoded).asInstanceOf[Int], 7)

    val truncated = ByteBuffer.allocate(5).order(java.nio.ByteOrder.LITTLE_ENDIAN).putInt(2).put('o'.toByte).array()
    val thrown    = intercept[InvocationTargetException](decode.invoke(decoder, ByteBuffer.wrap(truncated)))
    assert(thrown.getCause.isInstanceOf[IllegalArgumentException], thrown.getCause.toString)
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
    assert(source.contains("requireAvailable"))
    assert(source.contains("negative length for name"))
    assert(source.contains("negative length for payload"))
    assert(source.contains("negative count for items"))
    assert(source.contains("negative string length for items"))
    assert(!source.contains("(void)len"))
    assert(!source.contains("decode(const std::uint8_t* src, std::size_t len) noexcept"))
  }

  // Co-compile stubs because codegen precedes remote-core in the reactor.

  private val stubSources: Map[String, String] = Map(
    "javax.annotation.Nonnull"                    -> "package javax.annotation; public @interface Nonnull {}",
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
        |public interface CefMessageView { int messageId(); }
        |""".stripMargin,
    "net.kurobako.cef4j.ipc.session.CefMessageEncoder" ->
      """package net.kurobako.cef4j.ipc.session;
        |public interface CefMessageEncoder {
        |    int messageId();
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
        |    public static void requireFullyConsumed(java.nio.ByteBuffer source, String type) {
        |        if (source.hasRemaining()) throw new IllegalArgumentException("trailing bytes for " + type);
        |    }
        |}
        |""".stripMargin
  )

  private final class StringSource(name: String, code: String)
      extends SimpleJavaFileObject(
        URI.create(s"string:///${name.replace('.', '/')}.java"),
        JavaFileObject.Kind.SOURCE
      ) {
    override def getCharContent(ignoreEncoding: Boolean): CharSequence = code
  }

  private final class InMemoryClassLoader(blobs: Map[String, Array[Byte]], parent: ClassLoader)
      extends ClassLoader(parent) {
    override def findClass(name: String): Class[?] =
      blobs.get(name) match {
        case Some(bytes) => defineClass(name, bytes, 0, bytes.length)
        case None        => super.findClass(name)
      }
  }

  private final class CompiledClasses(loader: ClassLoader) {
    def get(fqn: String): Class[?] = loader.loadClass(fqn)
  }

  private def compileAll(extra: (String, String)*): CompiledClasses = {
    val javac: JavaCompiler = ToolProvider.getSystemJavaCompiler
    require(javac != null, "no system Java compiler available; ensure tests run on JDK not JRE")

    val all                                     = (stubSources ++ extra.toMap).toList
    val sources: java.util.List[JavaFileObject] = all.iterator
      .map { case (name, code) => new StringSource(name, code).asInstanceOf[JavaFileObject] }
      .toList
      .asJava

    val output = Files.createTempDirectory("cef4j-codegen-javac-")
    try {
      val fileManager = javac.getStandardFileManager(null, null, null)
      val options     = List("-Xlint:none", "-d", output.toString).asJava
      val ok          =
        try javac.getTask(null, fileManager, null, options, null, sources).call()
        finally fileManager.close()
      if (!ok) fail("javac failed for emitted sources")

      val blobs = FileSystem.descendants(output).filter(_.toString.endsWith(".class")).map { path =>
        val relative = output.relativize(path).toString.stripSuffix(".class").replace(java.io.File.separatorChar, '.')
        relative -> Files.readAllBytes(path)
      }.toMap
      new CompiledClasses(new InMemoryClassLoader(blobs, getClass.getClassLoader))
    } finally FileSystem.deleteTree(output)
  }
}
