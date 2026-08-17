package net.kurobako.cef4j.packager;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;

final class TestArchives {
    private TestArchives() {}

    static Path create(Path destination, CefPlatform platform) throws IOException {
        String root = "cef_binary_150.0.0+fixture_chromium-150.0.0.0_" + platform.cefName() + "_minimal/";
        Map<String, byte[]> files = new LinkedHashMap<>();
        files.put("LICENSE.txt", bytes("license"));
        files.put("CREDITS.html", bytes("credits"));
        files.put("include/cef_version.h", bytes("build-only"));
        if (platform.isMacOS()) {
            String framework = "Release/Chromium Embedded Framework.framework/";
            files.put(framework + "Chromium Embedded Framework", bytes("cef"));
            files.put(framework + "Libraries/libEGL.dylib", bytes("egl"));
            files.put(framework + "Libraries/libvk_swiftshader.dylib", bytes("swiftshader"));
            files.put(framework + "Libraries/vk_swiftshader_icd.json", bytes("{}"));
            files.put(framework + "Resources/icudtl.dat", bytes("icu"));
            files.put(framework + "Resources/v8_context_snapshot.x86_64.bin", bytes("v8"));
            files.put(framework + "Resources/en.lproj/locale.pak", bytes("en"));
            files.put(framework + "Resources/fr.lproj/locale.pak", bytes("fr"));
            files.put(framework + "Resources/fr_FEMININE.lproj/locale.pak", bytes("fr-f"));
        } else {
            String cefBinary = platform.isWindows() ? "libcef.dll" : "libcef.so";
            files.put("Release/" + cefBinary, bytes("cef"));
            if (platform.isWindows()) {
                files.put("Release/chrome_elf.dll", bytes("elf"));
                files.put("Release/libcef.lib", bytes("link-only"));
                files.put("Release/vk_swiftshader.dll", bytes("swiftshader"));
            } else {
                files.put("Release/libcef.a", bytes("link-only"));
                files.put("Release/libvk_swiftshader.so", bytes("swiftshader"));
            }
            files.put("Release/vk_swiftshader_icd.json", bytes("{}"));
            files.put("Release/v8_context_snapshot.bin", bytes("v8"));
            files.put("Resources/icudtl.dat", bytes("icu"));
            files.put("Resources/resources.pak", bytes("resources"));
            files.put("Resources/locales/en-US.pak", bytes("en"));
            files.put("Resources/locales/fr.pak", bytes("fr"));
            files.put("Resources/locales/fr_FEMININE.pak", bytes("fr-f"));
        }
        try (OutputStream fileOutput = Files.newOutputStream(destination);
                BZip2CompressorOutputStream bzipOutput = new BZip2CompressorOutputStream(fileOutput);
                TarArchiveOutputStream tarOutput = new TarArchiveOutputStream(bzipOutput)) {
            tarOutput.setLongFileMode(TarArchiveOutputStream.LONGFILE_POSIX);
            for (Map.Entry<String, byte[]> file : files.entrySet()) {
                TarArchiveEntry entry = new TarArchiveEntry(root + file.getKey());
                entry.setSize(file.getValue().length);
                entry.setModTime(0);
                tarOutput.putArchiveEntry(entry);
                tarOutput.write(file.getValue());
                tarOutput.closeArchiveEntry();
            }
            tarOutput.finish();
        }
        return destination;
    }

    static Path createTraversal(Path destination) throws IOException {
        try (OutputStream fileOutput = Files.newOutputStream(destination);
                BZip2CompressorOutputStream bzipOutput = new BZip2CompressorOutputStream(fileOutput);
                TarArchiveOutputStream tarOutput = new TarArchiveOutputStream(bzipOutput)) {
            byte[] content = bytes("escape");
            TarArchiveEntry entry = new TarArchiveEntry("../escape");
            entry.setSize(content.length);
            tarOutput.putArchiveEntry(entry);
            tarOutput.write(content);
            tarOutput.closeArchiveEntry();
            tarOutput.finish();
        }
        return destination;
    }

    private static byte[] bytes(String value) {
        return value.getBytes(StandardCharsets.UTF_8);
    }
}
