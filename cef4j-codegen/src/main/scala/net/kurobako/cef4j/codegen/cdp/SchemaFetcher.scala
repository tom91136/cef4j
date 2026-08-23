package net.kurobako.cef4j.codegen.cdp

import java.io.ByteArrayInputStream
import java.io.IOException
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.nio.charset.StandardCharsets
import java.nio.file.AtomicMoveNotSupportedException
import java.nio.file.DirectoryNotEmptyException
import java.nio.file.FileAlreadyExistsException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption
import java.time.Duration
import java.util.Base64
import scala.annotation.tailrec

import net.kurobako.cef4j.codegen.FileSystem
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream

/** Acquires the exact Chromium and V8 protocol schemas associated with a CEF build. */
object SchemaFetcher {
  final case class Schema(browser: Path, javascript: Path, metadata: Path)

  private val V8Revision  = """'v8_revision':\s*'([^']+)'""".r
  private val RetryDelays = List(1L, 2L, 4L, 8L, 16L, 30L).map(Duration.ofSeconds)
  private val client      = HttpClient.newBuilder()
    .connectTimeout(Duration.ofSeconds(30))
    .followRedirects(HttpClient.Redirect.ALWAYS)
    .build()

  def fetch(cefVersion: String, cacheRoot: Path): Schema = {
    val chromiumVersion = chromiumVersionOf(cefVersion)
    val schemaDir       = cacheRoot.resolve(s"cdp-pdl-$chromiumVersion")
    val schema          = paths(schemaDir)
    if (complete(schema)) schema
    else {
      FileSystem.createDirectories(cacheRoot)
      val work = Files.createTempDirectory(cacheRoot, s"cdp-$chromiumVersion.tmp.")
      try {
        val chromiumBase = s"https://chromium.googlesource.com/chromium/src/+/refs/tags/$chromiumVersion"
        val deps         = work.resolve("DEPS")
        fetchBase64(s"$chromiumBase/DEPS", deps)
        val v8Revision = V8Revision.findFirstMatchIn(Files.readString(deps, StandardCharsets.UTF_8))
          .map(_.group(1))
          .getOrElse(throw IOException("Could not derive V8 revision from Chromium DEPS"))

        extractTarGzip(
          s"https://chromium.googlesource.com/chromium/src/+archive/refs/tags/$chromiumVersion/third_party/blink/public/devtools_protocol.tar.gz",
          work
        )
        fetchBase64(
          s"https://chromium.googlesource.com/v8/v8/+/$v8Revision/include/js_protocol.pdl",
          work.resolve("js_protocol.pdl")
        )

        val _ = Files.writeString(
          work.resolve("schema.properties"),
          s"chromium.version=$chromiumVersion\nv8.revision=$v8Revision\n",
          StandardCharsets.UTF_8
        )

        retainSchemaOutputs(work)
        publish(work, schemaDir)
        if (!complete(schema)) throw IOException(s"Failed to populate CDP schema cache: $schemaDir")
        schema
      } finally FileSystem.deleteTree(work)
    }
  }

  private[cdp] def chromiumVersionOf(cefVersion: String): String = {
    val marker = "chromium-"
    val index  = cefVersion.lastIndexOf(marker)
    if (index < 0 || index + marker.length == cefVersion.length)
      throw IllegalArgumentException(s"Could not derive Chromium version from CEF version: $cefVersion")
    cefVersion.substring(index + marker.length)
  }

  private def paths(directory: Path): Schema = Schema(
    directory.resolve("browser_protocol.pdl"),
    directory.resolve("js_protocol.pdl"),
    directory.resolve("schema.properties")
  )

  private def complete(schema: Schema): Boolean =
    Seq(schema.browser, schema.javascript, schema.metadata).forall(path =>
      Files.isRegularFile(path) && Files.size(path) > 0
    )

  private def fetchBase64(url: String, destination: Path): Unit = {
    val _ = Files.write(destination, Base64.getMimeDecoder.decode(fetch(s"$url?format=TEXT")))
  }

  private def extractTarGzip(url: String, destination: Path): Unit = {
    val gzip = new GzipCompressorInputStream(new ByteArrayInputStream(fetch(url)))
    val tar  = new TarArchiveInputStream(gzip)
    try {
      @tailrec
      def extract(entry: org.apache.commons.compress.archivers.tar.TarArchiveEntry): Unit =
        if (entry != null) {
          val output = destination.resolve(entry.getName).normalize()
          if (!output.startsWith(destination))
            throw IOException(s"Archive entry escapes destination: ${entry.getName}")
          if (entry.isDirectory) FileSystem.createDirectories(output)
          else {
            FileSystem.createDirectories(output.getParent)
            val _ = Files.copy(tar, output)
          }
          extract(tar.getNextEntry)
        }
      extract(tar.getNextEntry)
    } finally tar.close()
  }

  private def fetch(url: String): Array[Byte] =
    retry(
      () =>
        try {
          val request  = HttpRequest.newBuilder(URI.create(url)).timeout(Duration.ofMinutes(2)).GET().build()
          val response = client.send(request, HttpResponse.BodyHandlers.ofByteArray())
          if (response.statusCode() >= 200 && response.statusCode() < 300) Right(response.body())
          else Left(IOException(s"HTTP ${response.statusCode()} while downloading $url"))
        } catch {
          case error: IOException          => Left(error)
          case error: InterruptedException => Thread.currentThread().interrupt(); throw error
        },
      sleep
    )

  private[cdp] def retry[A](operation: () => Either[IOException, A], pause: Duration => Unit): A = {
    @tailrec
    def attempt(delays: List[Duration]): A =
      operation() match {
        case Right(value) => value
        case Left(error)  =>
          delays match {
            case Nil                => throw error
            case delay :: remaining =>
              pause(delay)
              attempt(remaining)
          }
      }

    attempt(RetryDelays)
  }

  private def sleep(delay: Duration): Unit =
    try Thread.sleep(delay.toMillis)
    catch {
      case error: InterruptedException =>
        Thread.currentThread().interrupt()
        throw error
    }

  private def publish(work: Path, destination: Path): Unit =
    try { val _ = Files.move(work, destination, StandardCopyOption.ATOMIC_MOVE) }
    catch {
      case _: AtomicMoveNotSupportedException =>
        try { val _ = Files.move(work, destination) }
        catch {
          case _: FileAlreadyExistsException | _: DirectoryNotEmptyException => ()
        }
      case _: FileAlreadyExistsException | _: DirectoryNotEmptyException => ()
    }

  private def retainSchemaOutputs(directory: Path): Unit = {
    val keep = Set("browser_protocol.pdl", "js_protocol.pdl", "domains", "schema.properties")
    FileSystem.children(directory).filterNot(path => keep(path.getFileName.toString)).foreach(path =>
      if (Files.isDirectory(path)) FileSystem.deleteTree(path) else Files.delete(path)
    )
  }
}
