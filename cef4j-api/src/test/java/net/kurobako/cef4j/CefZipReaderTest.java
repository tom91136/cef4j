package net.kurobako.cef4j;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import net.kurobako.cef4j.gen.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class CefZipReaderTest extends CefTestBase {

    @BeforeAll
    static void initCef() throws Exception {
        CefTestBase.initCef(List.of());
    }

    static Stream<Named<BiFunction<byte[], Path, CefStreamReader>>> streamFactories() {
        return Stream.of(Named.of("file", CefZipReaderTest::createFileFactory), Named.of("data", (data, tmpDir) -> {
            ByteBuffer buf = ByteBuffer.allocateDirect(data.length);
            buf.put(data);
            return CefStreamReader.createForData(buf)
                    .orElseThrow(() -> new AssertionError("createForData returned empty"));
        }));
    }

    private static CefStreamReader createFileFactory(byte[] data, Path tmpDir) {
        try {
            Path file = tmpDir.resolve("test-data.zip");
            Files.write(file, data);
            return CefStreamReader.createForFile(file.toAbsolutePath().toString())
                    .orElseThrow(() -> new AssertionError("createForFile returned empty"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] makeZip(String... nameContentPairs) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ZipOutputStream zos = new ZipOutputStream(baos, StandardCharsets.UTF_8)) {
            for (int i = 0; i < nameContentPairs.length; i += 2) {
                ZipEntry entry = new ZipEntry(nameContentPairs[i]);
                zos.putNextEntry(entry);
                zos.write(nameContentPairs[i + 1].getBytes(StandardCharsets.UTF_8));
                zos.closeEntry();
            }
        }
        return baos.toByteArray();
    }

    private static String readEntryFully(CefZipReader zr) {
        ByteBuffer buf = ByteBuffer.allocateDirect(256);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int read;
        while ((read = zr.readFile(buf)) > 0) {
            byte[] tmp = new byte[read];
            buf.position(0);
            buf.get(tmp, 0, read);
            out.write(tmp, 0, read);
        }
        return out.toString(StandardCharsets.UTF_8);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("streamFactories")
    void readSingleEntry(BiFunction<byte[], Path, CefStreamReader> factory, @TempDir Path tmpDir) throws Exception {
        byte[] zipBytes = makeZip("hello.txt", "Hello, CEF!");
        try (CefStreamReader reader = factory.apply(zipBytes, tmpDir);
                CefZipReader zr = CefZipReader.create(reader).orElseThrow()) {
            assertThat(zr.moveToFirstFile()).isTrue();
            assertThat(zr.getFileName()).hasValue("hello.txt");

            assertThat(zr.openFile(null)).isTrue();
            String content = readEntryFully(zr);
            assertThat(zr.closeFile()).isTrue();

            assertThat(content).isEqualTo("Hello, CEF!");
            assertThat(zr.moveToNextFile()).isFalse();
            assertThat(zr.cefClose()).isTrue();
        }
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("streamFactories")
    void readMultipleEntries(BiFunction<byte[], Path, CefStreamReader> factory, @TempDir Path tmpDir) throws Exception {
        byte[] zipBytes = makeZip("a.txt", "alpha", "b.txt", "bravo", "c.txt", "charlie");
        try (CefStreamReader reader = factory.apply(zipBytes, tmpDir);
                CefZipReader zr = CefZipReader.create(reader).orElseThrow()) {
            List<String> names = new ArrayList<>();
            List<String> contents = new ArrayList<>();

            assertThat(zr.moveToFirstFile()).isTrue();
            do {
                zr.getFileName().ifPresent(names::add);
                assertThat(zr.openFile(null)).isTrue();
                contents.add(readEntryFully(zr));
                assertThat(zr.closeFile()).isTrue();
            } while (zr.moveToNextFile());

            assertThat(names).containsExactly("a.txt", "b.txt", "c.txt");
            assertThat(contents).containsExactly("alpha", "bravo", "charlie");
            assertThat(zr.cefClose()).isTrue();
        }
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("streamFactories")
    void moveToFileByName(BiFunction<byte[], Path, CefStreamReader> factory, @TempDir Path tmpDir) throws Exception {
        byte[] zipBytes = makeZip("first.txt", "one", "second.txt", "two", "third.txt", "three");
        try (CefStreamReader reader = factory.apply(zipBytes, tmpDir);
                CefZipReader zr = CefZipReader.create(reader).orElseThrow()) {
            assertThat(zr.moveToFile("second.txt", true)).isTrue();
            assertThat(zr.getFileName()).hasValue("second.txt");
            assertThat(zr.getFileSize()).isEqualTo(3L);

            assertThat(zr.openFile(null)).isTrue();
            assertThat(readEntryFully(zr)).isEqualTo("two");
            assertThat(zr.closeFile()).isTrue();
            assertThat(zr.cefClose()).isTrue();
        }
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("streamFactories")
    void eofAndTell(BiFunction<byte[], Path, CefStreamReader> factory, @TempDir Path tmpDir) throws Exception {
        byte[] zipBytes = makeZip("data.bin", "0123456789");
        try (CefStreamReader reader = factory.apply(zipBytes, tmpDir);
                CefZipReader zr = CefZipReader.create(reader).orElseThrow()) {
            assertThat(zr.moveToFirstFile()).isTrue();
            assertThat(zr.openFile(null)).isTrue();

            assertThat(zr.tell()).isEqualTo(0L);
            assertThat(zr.eof()).isFalse();

            readEntryFully(zr);

            assertThat(zr.eof()).isTrue();
            assertThat(zr.tell()).isEqualTo(10L);

            assertThat(zr.closeFile()).isTrue();
            assertThat(zr.cefClose()).isTrue();
        }
    }
}
