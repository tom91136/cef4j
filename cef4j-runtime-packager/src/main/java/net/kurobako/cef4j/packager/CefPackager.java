package net.kurobako.cef4j.packager;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

/** Command-line entry point for producing embedded or external CEF runtime layouts. */
@Command(
        name = "cef4j-pack",
        versionProvider = CefPackager.VersionProvider.class,
        mixinStandardHelpOptions = true,
        description = "Download, verify, and package CEF runtimes for cef4j.",
        subcommands = {CefPackager.PackageCommand.class, CefPackager.VerifyCommand.class})
public final class CefPackager implements Runnable {
    CefPackager() {}

    static final class VersionProvider implements CommandLine.IVersionProvider {
        @Override
        public String[] getVersion() {
            String version = CefPackager.class.getPackage().getImplementationVersion();
            return new String[] {"cef4j-runtime-packager " + (version == null ? "development" : version)};
        }
    }

    /** Runs the command-line interface. */
    public static void main(String[] args) {
        int status = new CommandLine(new CefPackager()).execute(args);
        if (status != 0) System.exit(status);
    }

    @Override
    public void run() {
        CommandLine.usage(this, System.out);
    }

    @Command(name = "package", mixinStandardHelpOptions = true, description = "Create cef-runtime classpath resources.")
    static final class PackageCommand implements Callable<Integer> {
        @Option(names = "--cef-version", required = true, description = "Exact upstream CEF version.")
        String cefVersion;

        @Option(names = "--platform", required = true, split = ",", description = "Target platform(s), or 'all'.")
        List<String> platforms = new ArrayList<>();

        @Option(names = "--output", required = true, description = "Generated resources root.")
        Path output;

        @Option(names = "--archive", description = "Use a local archive (valid with one platform only).")
        Path archive;

        @Option(names = "--bridge-directory", description = "Add cef4j native bridge files from this directory.")
        Path bridgeDirectory;

        @Option(names = "--cache", description = "Download cache directory.")
        Path cache = defaultCache();

        @Option(names = "--locales", split = ",", description = "Keep only these locale families.")
        List<String> locales = new ArrayList<>();

        @Option(names = "--without-swiftshader", description = "Remove the optional SwiftShader fallback.")
        boolean withoutSwiftShader;

        @Option(names = "--sha256", description = "Require this archive SHA-256 (one platform only).")
        String sha256;

        @Option(names = "--offline", description = "Use only local/cached archives and checksum metadata.")
        boolean offline;

        @Option(names = "--skip-if-current", description = "Reuse a complete output matching these inputs.")
        boolean skipIfCurrent;

        @Option(names = "--base-url", hidden = true)
        URI baseUri = CefArchiveResolver.DEFAULT_BASE_URI;

        @Option(names = "--index-url", hidden = true)
        URI indexUri = CefArchiveResolver.DEFAULT_INDEX_URI;

        @Override
        public Integer call() throws Exception {
            List<CefPlatform> targets = parsePlatforms(platforms);
            if (archive != null && targets.size() != 1) {
                throw new CommandLine.ParameterException(new CommandLine(this), "--archive requires one platform");
            }
            if (sha256 != null && targets.size() != 1) {
                throw new CommandLine.ParameterException(new CommandLine(this), "--sha256 requires one platform");
            }
            if (bridgeDirectory != null && targets.size() != 1) {
                throw new CommandLine.ParameterException(new CommandLine(this), "--bridge-directory requires one platform");
            }
            CefArchiveResolver resolver = new CefArchiveResolver();
            CefRuntimePackager packager = new CefRuntimePackager();
            for (CefPlatform platform : targets) {
                CefArchiveResolver.ResolvedArchive resolved = resolver.resolve(
                        cefVersion, platform, cache, archive, sha256, offline, baseUri, indexUri);
                CefRuntimePackager.Request request = new CefRuntimePackager.Request(
                        cefVersion,
                        platform,
                        resolved.path,
                        output,
                        locales,
                        withoutSwiftShader,
                        resolved.sha1,
                        resolved.sha256,
                        resolved.upstreamVerified);
                if (skipIfCurrent && packager.isCurrent(request)) {
                    System.out.printf("Reusing packaged CEF %s for %s in %s%n", cefVersion, platform.externalName(), output);
                } else {
                    CefRuntimePackager.Result result = packager.packageArchive(request);
                    System.out.printf(
                            "Packaged CEF %s for %s: %d files in %s%n",
                            cefVersion, platform.externalName(), result.files().size(), result.runtimeRoot());
                }
                if (bridgeDirectory != null) stageBridge(bridgeDirectory, output, platform);
            }
            return 0;
        }

        private static void stageBridge(Path sourceDirectory, Path output, CefPlatform platform) throws IOException {
            String library = platform.isWindows()
                    ? "cef4j.dll"
                    : platform.isMacOS() ? "libcef4j.dylib" : "libcef4j.so";
            if (!Files.isRegularFile(sourceDirectory.resolve(library))) {
                throw new IOException("cef4j bridge is missing: " + sourceDirectory.resolve(library));
            }
            Path destination = Files.createDirectories(output.resolve("native").resolve(platform.cefName()));
            for (String name : List.of(library, "cef4j_launcher", "cef4j_launcher.exe")) {
                Path source = sourceDirectory.resolve(name);
                if (Files.isRegularFile(source)) {
                    Files.copy(source, destination.resolve(name), StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
    }

    @Command(name = "verify", mixinStandardHelpOptions = true, description = "Verify generated cef-runtime resources.")
    static final class VerifyCommand implements Callable<Integer> {
        @Option(names = "--platform", required = true)
        String platform;

        @Option(names = "--root", required = true)
        Path root;

        @Override
        public Integer call() throws Exception {
            List<String> problems = new CefRuntimeVerifier().verify(root, CefPlatform.parse(platform));
            problems.forEach(problem -> System.err.println("error: " + problem));
            return problems.isEmpty() ? 0 : 1;
        }
    }

    private static List<CefPlatform> parsePlatforms(List<String> values) {
        if (values.stream().anyMatch("all"::equalsIgnoreCase)) return List.of(CefPlatform.values());
        return values.stream().map(CefPlatform::parse).distinct().collect(java.util.stream.Collectors.toList());
    }

    private static Path defaultCache() {
        String configured = System.getenv("CEF4J_CEF_CACHE");
        return configured == null || configured.isBlank()
                ? Path.of(System.getProperty("user.home"), ".cache", "cef4j")
                : Path.of(configured);
    }
}
