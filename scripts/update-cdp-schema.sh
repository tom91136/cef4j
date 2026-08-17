#!/usr/bin/env bash
set -euo pipefail

repo_dir=$(cd "$(dirname "$0")/.." && pwd)
cef_version=${1:-$(sed -n 's|.*<cef.version>\([^<]*\)</cef.version>.*|\1|p' "$repo_dir/pom.xml")}
if [[ -z "$cef_version" ]]; then
  echo "Could not derive CEF version from pom.xml" >&2
  exit 1
fi

"$repo_dir/scripts/fetch-cdp-schema.sh" "$cef_version" "$repo_dir/.cef-dist"
chromium_version=${cef_version##*chromium-}
schema_dir="$repo_dir/.cef-dist/cdp-$chromium_version"
"$repo_dir/mvnw" -q -pl cef4j-codegen -am package -DskipTests
java -cp "$repo_dir/cef4j-codegen/target/cef4j-codegen-fat.jar" \
  net.kurobako.cef4j.codegen.cdp.Main \
  "--browser-schema=$schema_dir/browser_protocol.json" \
  "--javascript-schema=$schema_dir/js_protocol.json" \
  "--schema-metadata=$schema_dir/schema.properties" \
  "--out-java=$repo_dir/cef4j-cdp/src/main/java" \
  "--out-resources=$repo_dir/cef4j-cdp/src/main/resources" \
  "--java-package=net.kurobako.cef4j.cdp.generated"
