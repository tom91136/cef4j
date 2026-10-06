#!/usr/bin/env bash
set -euo pipefail

assets=${1:?directory containing classified release artifacts is required}
version=${2:?release version is required}

: "${CENTRAL_USERNAME:?CENTRAL_USERNAME is required}"
: "${CENTRAL_TOKEN:?CENTRAL_TOKEN is required}"
: "${MAVEN_GPG_PASSPHRASE:?MAVEN_GPG_PASSPHRASE is required}"

classifiers=(linux-x86_64 linux-arm64 windows-x86_64 windows-arm64 macosx-x86_64 macosx-arm64)
work=$(mktemp -d "${RUNNER_TEMP:-/tmp}/cef4j-central-native.XXXXXX")
trap 'rm -rf "${work}"' EXIT

declared_version=$(./mvnw -q -N help:evaluate -Dexpression=project.version -DforceStdout)
if [[ ${declared_version} != "${version}" ]]; then
  echo "release version ${version} does not match checkout version ${declared_version}" >&2
  exit 1
fi

# Validate both complete families before uploading either one. This avoids leaving a
# partial Central deployment behind when one platform job produced no release asset.
for classifier in "${classifiers[@]}"; do
  test -f "${assets}/cef4j-platform-${version}-${classifier}.jar"
  test -f "${assets}/cef4j-runtime-server-${version}-${classifier}.zip"
done

sign_and_checksum() {
  local file=$1
  gpg --batch --yes --no-tty --pinentry-mode loopback \
    --passphrase "${MAVEN_GPG_PASSPHRASE}" --armor --detach-sign "${file}"
  md5sum "${file}" | awk '{print $1}' > "${file}.md5"
  sha1sum "${file}" | awk '{print $1}' > "${file}.sha1"
  md5sum "${file}.asc" | awk '{print $1}' > "${file}.asc.md5"
  sha1sum "${file}.asc" | awk '{print $1}' > "${file}.asc.sha1"
}

staging=${work}/staging
repository_path=${staging}/net/kurobako/cef4j
bundle=${work}/cef4j-native-${version}-central.zip

stage_component() {
  local source_artifact=$1
  local public_artifact=$2
  local extension=$3
  shift 3
  local component_classifiers=("$@")
  local component_path=${repository_path}/${public_artifact}/${version}

  mkdir -p "${component_path}"
  # Publish stable OS-family coordinates and consumer metadata, excluding the
  # reactor-only machinery used to compile and assemble these native artifacts.
  sed \
    -e "0,/<artifactId>${source_artifact}<\/artifactId>/s//<artifactId>${public_artifact}<\/artifactId>/" \
    -e '/    <properties>/,/    <\/properties>/d' \
    -e '/    <dependencies>/,/    <\/dependencies>/d' \
    -e '/    <build>/,/    <\/build>/d' \
    -e '/    <profiles>/,/    <\/profiles>/d' \
    "${source_artifact}/pom.xml" > "${component_path}/${public_artifact}-${version}.pom"

  local classifier source
  for classifier in "${component_classifiers[@]}"; do
    source=${assets}/${source_artifact}-${version}-${classifier}.${extension}
    test -f "${source}"
    cp "${source}" "${component_path}/${public_artifact}-${version}-${classifier#*-}.${extension}"
  done

}

stage_aggregate() {
  local public_artifact=$1
  local component=$2
  local type=$3
  local description=$4
  local component_path=${repository_path}/${public_artifact}/${version}
  local family arch

  mkdir -p "${component_path}"
  {
    cat <<POM
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>net.kurobako.cef4j</groupId>
        <artifactId>cef4j-parent</artifactId>
        <version>${version}</version>
    </parent>
    <artifactId>${public_artifact}</artifactId>
    <packaging>pom</packaging>
    <name>${public_artifact}</name>
    <description>${description}</description>
    <dependencies>
POM
    for family in linux windows macos; do
      for arch in x86_64 arm64; do
        cat <<POM
        <dependency>
            <groupId>net.kurobako.cef4j</groupId>
            <artifactId>${component}-${family}</artifactId>
            <version>${version}</version>
            <type>${type}</type>
            <classifier>${arch}</classifier>
        </dependency>
POM
      done
    done
    cat <<POM
    </dependencies>
</project>
POM
  } > "${component_path}/${public_artifact}-${version}.pom"
}

stage_component cef4j-platform cef4j-platform-linux jar linux-x86_64 linux-arm64
stage_component cef4j-platform cef4j-platform-windows jar windows-x86_64 windows-arm64
stage_component cef4j-platform cef4j-platform-macos jar macosx-x86_64 macosx-arm64
stage_component cef4j-runtime-server cef4j-runtime-server-linux zip linux-x86_64 linux-arm64
stage_component cef4j-runtime-server cef4j-runtime-server-windows zip windows-x86_64 windows-arm64
stage_component cef4j-runtime-server cef4j-runtime-server-macos zip macosx-x86_64 macosx-arm64
stage_aggregate cef4j-platform-all cef4j-platform jar 'Every supported in-process JNI bridge'
stage_aggregate cef4j-runtime-server-all cef4j-runtime-server zip 'Every supported packaged CEF runtime server'

while IFS= read -r -d '' file; do
  sign_and_checksum "${file}"
done < <(find "${repository_path}" -type f \( -name '*.pom' -o -name '*.jar' -o -name '*.zip' \) -print0)

(cd "${staging}" && zip -q -r "${bundle}" .)
size=$(stat -c '%s' "${bundle}")
if (( size >= 1000000000 )); then
  echo "native Central bundle is ${size} bytes; the limit is 1000000000" >&2
  exit 1
fi

token=$(printf '%s:%s' "${CENTRAL_USERNAME}" "${CENTRAL_TOKEN}" | base64 -w0)
deployment_id=$(curl --fail-with-body --silent --show-error --request POST \
  --header "Authorization: Bearer ${token}" \
  --form "bundle=@${bundle};type=application/octet-stream" \
  "https://central.sonatype.com/api/v1/publisher/upload?name=cef4j-native-${version}&publishingType=USER_MANAGED")
echo "Uploaded all native bridges (${size} bytes) as Central deployment ${deployment_id}"
