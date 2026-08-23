#!/bin/sh
set -eu

examples_dir=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)
repo_dir=$(CDPATH= cd -- "$examples_dir/.." && pwd)

cmp "$examples_dir/gradle-groovy/src/main/java/example/Main.java" \
    "$examples_dir/gradle-kotlin/src/main/java/example/Main.java"
cmp "$examples_dir/gradle-groovy/src/main/java/example/Main.java" \
    "$examples_dir/maven/src/main/java/example/Main.java"

cef4j_version=$(sed -n '0,/<version>/{s:.*<version>\([^<]*\)-SNAPSHOT</version>.*:\1:p;}' "$repo_dir/pom.xml")
packager_version=$(sed -n '0,/<version>/{s:.*<version>\([^<]*\)-SNAPSHOT</version>.*:\1:p;}' \
    "$repo_dir/cef4j-runtime-packager/pom.xml")
cef_version=$(sed -n 's:.*<cef.version>\([^<]*\)</cef.version>.*:\1:p' "$repo_dir/pom.xml")

for build in \
    "$examples_dir/gradle-groovy/build.gradle" \
    "$examples_dir/gradle-kotlin/build.gradle.kts" \
    "$examples_dir/maven/pom.xml" \
    "$examples_dir/sbt/build.sbt"
do
    grep -F "$cef4j_version" "$build" >/dev/null
    grep -F "$packager_version" "$build" >/dev/null
    grep -F "$cef_version" "$build" >/dev/null
done

grep -F 'cef4j-platform:${cef4jVersion}:${runtimePlatform}' \
    "$examples_dir/gradle-groovy/build.gradle" >/dev/null
grep -F '"--platform=${runtimePlatform}"' "$examples_dir/gradle-groovy/build.gradle" >/dev/null
grep -F 'cef4j-platform:$cef4jVersion:$runtimePlatform' \
    "$examples_dir/gradle-kotlin/build.gradle.kts" >/dev/null
grep -F '"--platform=$runtimePlatform"' "$examples_dir/gradle-kotlin/build.gradle.kts" >/dev/null
grep -F '<classifier>${cef4j.runtime.platform}</classifier>' "$examples_dir/maven/pom.xml" >/dev/null
grep -F '<argument>--platform=${cef4j.runtime.platform}</argument>' "$examples_dir/maven/pom.xml" >/dev/null
