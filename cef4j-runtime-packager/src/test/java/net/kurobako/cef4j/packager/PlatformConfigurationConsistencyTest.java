package net.kurobako.cef4j.packager;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.parsers.DocumentBuilderFactory;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

final class PlatformConfigurationConsistencyTest {
    private static final Pattern WORKFLOW_CEF_PLATFORM = Pattern.compile("cef-platform: ([a-z0-9]+)");

    @Test
    void platformDefinitionsStayAlignedAcrossPackagerMavenAndCi() throws Exception {
        Path root = repositoryRoot();
        Set<String> expectedCefNames = new HashSet<>();
        Set<String> expectedClassifiers = new HashSet<>();
        Arrays.stream(CefPlatform.values()).forEach(platform -> {
            expectedCefNames.add(platform.cefName());
            expectedClassifiers.add(platform.classifier());
        });

        Set<String> pomCefNames = new HashSet<>();
        Set<String> pomClassifiers = new HashSet<>();
        NodeList profiles = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .parse(root.resolve("pom.xml").toFile())
                .getElementsByTagName("profile");
        for (int index = 0; index < profiles.getLength(); index++) {
            Element profile = (Element) profiles.item(index);
            addFirstText(profile, "cef.platform", pomCefNames);
            addFirstText(profile, "cef.classifier", pomClassifiers);
        }

        Set<String> workflowCefNames = new HashSet<>();
        Matcher matcher = WORKFLOW_CEF_PLATFORM.matcher(
                Files.readString(root.resolve(".github/workflows/main.yaml"), StandardCharsets.UTF_8));
        while (matcher.find()) workflowCefNames.add(matcher.group(1));

        assertThat(pomCefNames).isEqualTo(expectedCefNames);
        assertThat(pomClassifiers).isEqualTo(expectedClassifiers);
        assertThat(workflowCefNames).isEqualTo(expectedCefNames);
    }

    @Test
    void nativeCodegenUsesTheMatchingJavaPlatformPackage() throws Exception {
        Path pom = repositoryRoot().resolve("cef4j-platform/pom.xml");
        NodeList executions = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .parse(pom.toFile())
                .getElementsByTagName("execution");
        Map<String, String> expected = Map.of(
                "run-codegen", "linux",
                "run-codegen-macos-platform", "mac",
                "run-codegen-windows-platform", "win");

        for (int index = 0; index < executions.getLength(); index++) {
            Element execution = (Element) executions.item(index);
            String id = firstText(execution, "id");
            if (!expected.containsKey(id)) continue;
            String argument = "--java-platform-subpackage=" + expected.get(id);
            assertThat(texts(execution, "argument")).contains(argument);
        }
    }

    private static void addFirstText(Element parent, String name, Set<String> values) {
        NodeList nodes = parent.getElementsByTagName(name);
        if (nodes.getLength() > 0) values.add(nodes.item(0).getTextContent().trim());
    }

    private static String firstText(Element parent, String name) {
        NodeList nodes = parent.getElementsByTagName(name);
        return nodes.getLength() == 0 ? "" : nodes.item(0).getTextContent().trim();
    }

    private static Set<String> texts(Element parent, String name) {
        Set<String> values = new HashSet<>();
        NodeList nodes = parent.getElementsByTagName(name);
        for (int index = 0; index < nodes.getLength(); index++) {
            values.add(nodes.item(index).getTextContent().trim());
        }
        return values;
    }

    private static Path repositoryRoot() {
        Path current = Path.of(System.getProperty("user.dir")).toAbsolutePath();
        while (current != null && !Files.isRegularFile(current.resolve(".github/workflows/main.yaml"))) {
            current = current.getParent();
        }
        if (current == null) throw new IllegalStateException("repository root not found");
        return current;
    }
}
