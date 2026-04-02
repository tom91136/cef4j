package net.kurobako.cef4j;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import net.kurobako.cef4j.gen.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Integration tests for {@link CefXmlReader}, parameterised over file-backed and callback-backed
 * {@link CefStreamReader} factories.
 */
class CefXmlReaderTest {

    // XML fixtures
    private static final String SIMPLE_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
            + "<root>\n"
            + "  <item id=\"1\">first</item>\n"
            + "  <item id=\"2\">second</item>\n"
            + "  <empty/>\n"
            + "</root>\n";

    private static final String NS_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
            + "<r:root xmlns:r=\"http://example.com/r\">\n"
            + "  <r:child r:attr=\"val\">text</r:child>\n"
            + "</r:root>\n";

    @BeforeAll
    static void initCef() throws Exception {
        SystemBootstrap.load();
        // Use the singleton - if CefInteropTest already initialized CEF this is a no-op.
        // We never call dispose() so CEF stays alive across test classes in the same JVM fork.
        CefApp.INSTANCE.initialize();
    }

    @FunctionalInterface
    interface StreamFactory {
        CefStreamReader create(byte[] data, Path tmpDir) throws Exception;
    }

    private static final StreamFactory FILE_FACTORY = (data, tmpDir) -> {
        Path file = tmpDir.resolve("test-data.xml");
        Files.write(file, data);
        return CefStreamReader.createForFile(file.toAbsolutePath().toString())
                .orElseThrow(() -> new AssertionError("createForFile returned empty"));
    };

    private static final StreamFactory HANDLER_FACTORY =
            (data, tmpDir) -> CefStreamReader.createForHandler(new ByteArrayReadHandler(data))
                    .orElseThrow(() -> new AssertionError("createForHandler returned empty"));

    static Stream<Named<StreamFactory>> streamFactories() {
        return Stream.of(Named.of("file", FILE_FACTORY), Named.of("handler", HANDLER_FACTORY));
    }

    static class ByteArrayReadHandler implements CefReadHandler {
        private final byte[] data;
        private int pos = 0;

        ByteArrayReadHandler(byte[] data) {
            this.data = data;
        }

        @Override
        public long read(ByteBuffer ptr, long n) {
            // The JNI layer wraps exactly 'size' bytes (one element) into the ByteBuffer.
            // We can only fill one complete element per call; return 1 on success, 0 at EOF.
            if (ptr == null || n <= 0) return 0;
            int elementSize = ptr.capacity();
            if (elementSize <= 0 || data.length - pos < elementSize) return 0;
            ptr.clear();
            ptr.put(data, pos, elementSize);
            pos += elementSize;
            return 1;
        }

        @Override
        public int seek(long offset, int whence) {
            int newPos;
            switch (whence) {
                case 0:
                    newPos = (int) offset;
                    break; // SEEK_SET
                case 1:
                    newPos = pos + (int) offset;
                    break; // SEEK_CUR
                case 2:
                    newPos = data.length + (int) offset;
                    break; // SEEK_END
                default:
                    return -1;
            }
            if (newPos < 0 || newPos > data.length) return -1;
            pos = newPos;
            return 0;
        }

        @Override
        public long tell() {
            return pos;
        }

        @Override
        public int eof() {
            return pos >= data.length ? 1 : 0;
        }

        @Override
        public boolean mayBlock() {
            return false;
        }
    }

    private static CefXmlReader openXml(StreamFactory factory, String xmlString, Path tmpDir, String uri)
            throws Exception {
        byte[] bytes = xmlString.getBytes(StandardCharsets.UTF_8);
        CefStreamReader reader = factory.create(bytes, tmpDir);
        return CefXmlReader.create(reader, CefXmlEncodingType.of(CefXmlEncodingType.Kind.UTF8), uri)
                .orElseThrow();
    }

    private static Path makeTmpDir() throws Exception {
        Path dir = Files.createTempDirectory("cef4j-xml-test-");
        dir.toFile().deleteOnExit();
        return dir;
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("streamFactories")
    void parseElementsAndText(StreamFactory factory) throws Exception {
        try (CefXmlReader xr = openXml(factory, SIMPLE_XML, makeTmpDir(), "test://simple.xml")) {
            List<String> elementNames = new ArrayList<>();
            List<String> textValues = new ArrayList<>();

            while (xr.moveToNextNode()) {
                Optional<CefXmlNodeType.Kind> kind = xr.getType().kind();
                if (!kind.isPresent()) continue;
                if (kind.get() == CefXmlNodeType.Kind.ELEMENT_START) {
                    xr.getLocalName().ifPresent(elementNames::add);
                } else if (kind.get() == CefXmlNodeType.Kind.TEXT) {
                    xr.getValue().filter(s -> !s.trim().isEmpty()).ifPresent(textValues::add);
                }
            }

            assertThat(xr.hasError()).isFalse();
            assertThat(elementNames).containsExactly("root", "item", "item", "empty");
            assertThat(textValues).containsExactly("first", "second");
            assertThat(xr.cefClose()).isTrue();
        }
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("streamFactories")
    void parseAttributes(StreamFactory factory) throws Exception {
        try (CefXmlReader xr = openXml(factory, SIMPLE_XML, makeTmpDir(), "test://attrs.xml")) {
            List<String> attrValues = new ArrayList<>();

            while (xr.moveToNextNode()) {
                if (xr.getType().kind().orElse(null) == CefXmlNodeType.Kind.ELEMENT_START && xr.hasAttributes()) {
                    assertThat(xr.getAttributeCount()).isGreaterThan(0);
                    xr.getAttributeByindex(0).ifPresent(attrValues::add);
                }
            }

            assertThat(xr.hasError()).isFalse();
            assertThat(attrValues).containsExactly("1", "2");
            assertThat(xr.cefClose()).isTrue();
        }
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("streamFactories")
    void emptyElement(StreamFactory factory) throws Exception {
        try (CefXmlReader xr = openXml(factory, SIMPLE_XML, makeTmpDir(), "test://empty.xml")) {
            boolean foundEmpty = false;

            while (xr.moveToNextNode()) {
                if (xr.getType().kind().orElse(null) == CefXmlNodeType.Kind.ELEMENT_START
                        && xr.getLocalName().orElse("").equals("empty")) {
                    assertThat(xr.isEmptyElement()).isTrue();
                    assertThat(xr.hasValue()).isFalse();
                    assertThat(xr.hasAttributes()).isFalse();
                    foundEmpty = true;
                }
            }

            assertThat(foundEmpty).isTrue();
            assertThat(xr.hasError()).isFalse();
            assertThat(xr.cefClose()).isTrue();
        }
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("streamFactories")
    void depthAndLineNumbers(StreamFactory factory) throws Exception {
        try (CefXmlReader xr = openXml(factory, SIMPLE_XML, makeTmpDir(), "test://depth.xml")) {
            int maxDepth = 0;
            List<Integer> elementLines = new ArrayList<>();

            while (xr.moveToNextNode()) {
                maxDepth = Math.max(maxDepth, xr.getDepth());
                if (xr.getType().kind().orElse(null) == CefXmlNodeType.Kind.ELEMENT_START) {
                    elementLines.add(xr.getLineNumber());
                }
            }

            // 4 elements (root, item, item, empty); root at depth 0, items at depth 1
            assertThat(elementLines).hasSize(4);
            assertThat(elementLines).allSatisfy(l -> assertThat(l).isGreaterThan(0));
            assertThat(maxDepth).isGreaterThanOrEqualTo(1);
            assertThat(xr.hasError()).isFalse();
            assertThat(xr.cefClose()).isTrue();
        }
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("streamFactories")
    void namespacePrefixAndUri(StreamFactory factory) throws Exception {
        try (CefXmlReader xr = openXml(factory, NS_XML, makeTmpDir(), "test://ns.xml")) {
            boolean foundChild = false;

            while (xr.moveToNextNode()) {
                if (xr.getType().kind().orElse(null) == CefXmlNodeType.Kind.ELEMENT_START
                        && xr.getLocalName().orElse("").equals("child")) {
                    foundChild = true;
                    assertThat(xr.getPrefix()).hasValue("r");
                    assertThat(xr.getQualifiedName()).hasValue("r:child");
                    assertThat(xr.getNamespaceUri()).hasValue("http://example.com/r");
                    assertThat(xr.getAttributeBylname("attr", "http://example.com/r"))
                            .hasValue("val");
                }
            }

            assertThat(foundChild).isTrue();
            assertThat(xr.hasError()).isFalse();
            assertThat(xr.cefClose()).isTrue();
        }
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("streamFactories")
    void innerAndOuterXml(StreamFactory factory) throws Exception {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<root><child>text</child></root>\n";
        try (CefXmlReader xr = openXml(factory, xml, makeTmpDir(), "test://innerxml.xml")) {
            while (xr.moveToNextNode()) {
                if (xr.getType().kind().orElse(null) == CefXmlNodeType.Kind.ELEMENT_START
                        && xr.getLocalName().orElse("").equals("root")) {
                    assertThat(xr.getInnerXml()).isPresent().get().asString().contains("child");
                    assertThat(xr.getOuterXml()).isPresent().get().asString().contains("root");
                    break;
                }
            }
            assertThat(xr.hasError()).isFalse();
            assertThat(xr.cefClose()).isTrue();
        }
    }
}
