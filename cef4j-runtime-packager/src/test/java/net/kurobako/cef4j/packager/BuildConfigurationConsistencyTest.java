package net.kurobako.cef4j.packager;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.TreeSet;
import javax.xml.parsers.DocumentBuilderFactory;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

final class BuildConfigurationConsistencyTest {
    @Test
    void javaModulesUsingSharedCompilerDeclareCodePolicyDependency() throws Exception {
        Path root = repositoryRoot();
        Element reactor = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .parse(root.resolve("pom.xml").toFile())
                .getDocumentElement();
        NodeList modules = reactor.getElementsByTagName("module");
        Set<String> missing = new TreeSet<>();

        for (int index = 0; index < modules.getLength(); index++) {
            String module = modules.item(index).getTextContent().trim();
            Path moduleRoot = root.resolve(module);
            if (!Files.isDirectory(moduleRoot.resolve("src/main/java")) || module.equals("cef4j-code-policy")) {
                continue;
            }

            Element project = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder()
                    .parse(moduleRoot.resolve("pom.xml").toFile())
                    .getDocumentElement();
            NodeList parents = project.getElementsByTagName("parent");
            if (parents.getLength() == 0
                    || !firstText((Element) parents.item(0), "artifactId").equals("cef4j-parent")) {
                continue;
            }
            if (!texts(project, "artifactId").contains("cef4j-code-policy")) missing.add(module);
        }

        assertThat(missing).isEmpty();
    }

    private static String firstText(Element parent, String name) {
        NodeList nodes = parent.getElementsByTagName(name);
        return nodes.getLength() == 0 ? "" : nodes.item(0).getTextContent().trim();
    }

    private static Set<String> texts(Element parent, String name) {
        Set<String> values = new TreeSet<>();
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
