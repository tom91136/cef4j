#!/usr/bin/env bash
# Exercise the documented Maven, Gradle, Kotlin DSL, and sbt integrations with
# one tiny synthetic CEF archive. Native CI separately validates real CEF.
set -euo pipefail

repo_root=$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")/../.." && pwd)
cef_version=150.0.18+gdb11278+chromium-150.0.7871.213
packager_version=0.1.0-SNAPSHOT
cef4j_version=150.0.0-SNAPSHOT
fixture_root=$(mktemp -d "${RUNNER_TEMP:-/tmp}/cef4j-consumer-fixture.XXXXXX")
trap 'rm -rf "${fixture_root}"' EXIT

distribution="${fixture_root}/cef_binary_${cef_version}_linux64_minimal"
cache="${fixture_root}/cache"
archive="${cache}/cef_binary_${cef_version}_linux64_minimal.tar.bz2"
mkdir -p "${distribution}/Release" "${distribution}/Resources/locales" "${cache}"
printf 'fixture libcef\n' > "${distribution}/Release/libcef.so"
printf 'fixture v8\n' > "${distribution}/Release/v8_context_snapshot.bin"
printf 'fixture icu\n' > "${distribution}/Resources/icudtl.dat"
printf 'fixture resources\n' > "${distribution}/Resources/resources.pak"
printf 'fixture locale\n' > "${distribution}/Resources/locales/en-US.pak"
printf 'CEF fixture licence\n' > "${distribution}/LICENSE.txt"
tar -cjf "${archive}" -C "${fixture_root}" "${distribution##*/}"
sha1sum "${archive}" | awk '{print $1}' > "${archive}.sha1"

export CEF4J_CEF_CACHE=${cache}
cd "${repo_root}"
./mvnw -B -f cef4j-runtime-packager/pom.xml clean install

./mvnw -B -f examples/maven/pom.xml clean verify \
    "-Dcef4j.version=${cef4j_version}" \
    -Dcef4j.platform.artifact=cef4j-platform \
    -Dcef4j.platform.classifier=linux-x86_64 \
    "-Dcef4j.packager.version=${packager_version}"

gradle --no-daemon -p examples/gradle-groovy clean build run \
    "-Pcef4jVersion=${cef4j_version}" \
    -Pcef4jPlatformArtifact=cef4j-platform \
    -Pcef4jPlatformClassifier=linux-x86_64 \
    "-Pcef4jPackagerVersion=${packager_version}"

gradle --no-daemon -p examples/gradle-kotlin clean build run \
    "-Pcef4jVersion=${cef4j_version}" \
    -Pcef4jPlatformArtifact=cef4j-platform \
    -Pcef4jPlatformClassifier=linux-x86_64 \
    "-Pcef4jPackagerVersion=${packager_version}"

(
    cd examples/sbt
    sbt -batch \
        "-Dcef4j.version=${cef4j_version}" \
        -Dcef4j.platform.artifact=cef4j-platform \
        -Dcef4j.platform.classifier=linux-x86_64 \
        "-Dcef4j.packager.version=${packager_version}" \
        -Dsbt.supershell=false \
        -Dsbt.log.noformat=true \
        -J-Xmx2G \
        clean run package
)

artifacts=(
    examples/maven/target/cef4j-maven-consumer-1.0.0-SNAPSHOT.jar
    examples/gradle-groovy/build/libs/cef4j-gradle-groovy-consumer.jar
    examples/gradle-kotlin/build/libs/cef4j-gradle-kotlin-consumer.jar
    examples/sbt/target/scala-3.8.4/cef4j-sbt-consumer_3-1.0.0-SNAPSHOT.jar
)
for artifact in "${artifacts[@]}"; do
    test -f "${artifact}"
    jar tf "${artifact}" | grep -q '^cef-runtime/linux64/file-list.txt$'
    jar tf "${artifact}" | grep -q '^cef-runtime/linux64/cef-runtime.properties$'
    jar tf "${artifact}" | grep -q '^cef-runtime/linux64/libcef.so$'
done
