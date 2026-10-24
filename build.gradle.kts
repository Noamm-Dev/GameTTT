import org.gradle.jvm.tasks.Jar

plugins {
    kotlin("jvm") version "2.3.20"
    kotlin("plugin.compose") version "2.3.20"
    id("org.jetbrains.compose") version "1.12.2"
}

repositories {
    mavenCentral()
    google()
}

group = "org.noamm"
version = "1.0"

dependencies {
    for (os in listOf("windows", "linux", "macos")) for (type in listOf("x64", "arm64")) {
        implementation("org.jetbrains.compose.desktop:desktop-jvm-$os-$type:1.12.2")
    }

    implementation("org.jetbrains.compose.material3:material3:1.9.0")
    testImplementation(kotlin("test"))
}

tasks.test { useJUnitPlatform() }
kotlin { jvmToolchain(25) }

compose.desktop {
    application {
        mainClass = "$group.MainKt"
        jvmArgs += "--enable-native-access=ALL-UNNAMED"
        jvmArgs += "-Dskiko.vsync.enabled=false"
    }
}

afterEvaluate {
    tasks.named<Jar>("packageUberJarForCurrentOS") {
        archiveFileName = "GameTTT-$version.jar"
        manifest.attributes("Enable-Native-Access" to "ALL-UNNAMED")
    }
}