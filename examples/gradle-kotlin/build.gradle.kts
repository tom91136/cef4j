plugins {
    java
    application
}

repositories {
    mavenLocal()
    mavenCentral()
}

val cef4jVersion = providers.gradleProperty("cef4jVersion").getOrElse("150.0.0")
val packagerVersion = providers.gradleProperty("cef4jPackagerVersion").getOrElse("0.1.0")
val runtimePlatform = providers.gradleProperty("cef4jRuntimePlatform").getOrElse("linux-x86_64")
val cefVersion = providers.gradleProperty("cefVersion").getOrElse("150.0.18+gdb11278+chromium-150.0.7871.213")

val cefPackager = configurations.create("cefPackager")

dependencies {
    implementation("net.kurobako.cef4j:cef4j-inprocess-swing:$cef4jVersion")
    implementation("net.kurobako.cef4j:cef4j-cdp:$cef4jVersion")
    implementation("net.kurobako.cef4j:cef4j-platform:$cef4jVersion:$runtimePlatform")
    cefPackager("net.kurobako.cef4j:cef4j-runtime-packager:$packagerVersion")
}

val generatedCefResources = layout.buildDirectory.dir("generated/cef4j-resources")
val packageCefRuntime = tasks.register<JavaExec>("packageCefRuntime") {
    classpath = cefPackager
    mainClass = "net.kurobako.cef4j.packager.CefPackager"
    outputs.dir(generatedCefResources)
    args(
        "package",
        "--cef-version=$cefVersion",
        "--platform=$runtimePlatform",
        "--locales=en-US",
        "--output=${generatedCefResources.get().asFile}"
    )
}

sourceSets.main {
    resources.srcDir(generatedCefResources)
}
tasks.processResources {
    dependsOn(packageCefRuntime)
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

application {
    mainClass = "example.Main"
}
