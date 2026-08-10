package net.kurobako.cef4j.codegen.ipc

import java.io.ByteArrayOutputStream
import java.net.URI
import java.nio.ByteBuffer
import javax.tools.ForwardingJavaFileManager
import javax.tools.JavaCompiler
import javax.tools.JavaFileObject
import javax.tools.SimpleJavaFileObject
import javax.tools.StandardLocation
import javax.tools.ToolProvider
import scala.collection.mutable
import scala.jdk.CollectionConverters.*

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

    // Construct: matches generated constructor (String, int, boolean, long, byte[], RemoteHandle)
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

    // encode
    val encodedSize = cls.getMethod("encodedSize").invoke(instance).asInstanceOf[Int]
    val buf         = ByteBuffer.allocate(encodedSize)
    cls.getMethod("encodeInto", classOf[ByteBuffer]).invoke(instance, buf)
    assertEquals(buf.position(), encodedSize, "encodeInto must write exactly encodedSize bytes")
    buf.flip()

    // decode via static DECODER field
    val decoderField = cls.getField("DECODER").get(null)
    // The DECODER is a lambda; its synthetic runtime class is not reflectively accessible. Invoke through
    // the declared interface instead.
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
    // The DECODER is a lambda; its synthetic runtime class is not reflectively accessible. Invoke through
    // the declared interface instead.
    val decoderIface = cls.getClassLoader.loadClass("net.kurobako.cef4j.ipc.session.CefMessageDecoder")
    val decodeMethod = decoderIface.getMethod("decode", classOf[ByteBuffer])
    val decoded      = decodeMethod.invoke(decoderField, buf)
    assertEquals(cls.getMethod("seq").invoke(decoded).asInstanceOf[Int], 7)
    assertEquals(cls.getMethod("ts").invoke(decoded).asInstanceOf[Long], 1234567890L)
  }

  test("emitter rejects negative message ids") {
    intercept[IllegalArgumentException] {
      MessageSpec("X", "test.gen", -1, Nil)
    }
  }

  // ---------------------------------------------------------------------------
  // In-memory Java compilation harness.
  //
  // The generated code references {@code net.kurobako.cef4j.ipc.session.CefMessage*} and
  // {@code javax.annotation.Nonnull}. cef4j-codegen has no compile dep on cef4j-ipc-session (and can't
  // — codegen builds first in the reactor). Instead we co-compile minimal stub interfaces here so the
  // generated source is type-checkable in isolation.
  // ---------------------------------------------------------------------------

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
        |""".stripMargin
  )

  private final class StringSource(name: String, code: String)
      extends SimpleJavaFileObject(
        URI.create(s"string:///${name.replace('.', '/')}.java"),
        JavaFileObject.Kind.SOURCE
      ) {
    override def getCharContent(ignoreEncoding: Boolean): CharSequence = code
  }

  private final class InMemoryClass(name: String)
      extends SimpleJavaFileObject(
        URI.create(s"mem:///${name.replace('.', '/')}.class"),
        JavaFileObject.Kind.CLASS
      ) {
    private val baos                                      = new ByteArrayOutputStream()
    override def openOutputStream(): java.io.OutputStream = baos
    def bytes: Array[Byte]                                = baos.toByteArray
  }

  private final class CapturingFileManager(
      delegate: javax.tools.JavaFileManager,
      captured: mutable.Map[String, InMemoryClass]
  ) extends ForwardingJavaFileManager[javax.tools.JavaFileManager](delegate) {
    override def getJavaFileForOutput(
        location: javax.tools.JavaFileManager.Location,
        className: String,
        kind: JavaFileObject.Kind,
        sibling: javax.tools.FileObject
    ): JavaFileObject = {
      val obj = new InMemoryClass(className)
      captured.put(className, obj)
      obj
    }
  }

  private final class InMemoryClassLoader(blobs: Map[String, Array[Byte]], parent: ClassLoader)
      extends ClassLoader(parent) {
    override def findClass(name: String): Class[?] =
      blobs.get(name) match {
        case Some(bytes) => defineClass(name, bytes, 0, bytes.length)
        case None        => super.findClass(name)
      }
  }

  /** Compiles the given (FQN -> source) pairs together with the standing stub set, returns a class loader for the
    * resulting classes.
    */
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

    val captured = mutable.Map.empty[String, InMemoryClass]
    val baseFm   = javac.getStandardFileManager(null, null, null)
    val fm       = new CapturingFileManager(baseFm, captured)

    val task = javac.getTask(null, fm, null, java.util.Arrays.asList("-Xlint:none"), null, sources)
    val ok   = task.call()
    if (!ok) fail("javac failed for emitted sources")

    val blobs = captured.iterator.map { case (name, mem) => name -> mem.bytes }.toMap
    new CompiledClasses(new InMemoryClassLoader(blobs, getClass.getClassLoader))
  }
}
