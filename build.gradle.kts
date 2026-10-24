plugins {
    kotlin("jvm") version "2.3.20"
    kotlin("plugin.compose") version "2.3.20"
    id("org.jetbrains.compose") version "1.12.2"
}

group = "org.noamm"
version = "1.0"

repositories {
    mavenCentral()
    google()
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation("org.jetbrains.compose.material3:material3:1.9.0")
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(25)
}

compose.desktop {
    application {
        mainClass = "org.noamm.MainKt"
        jvmArgs += "--enable-native-access=ALL-UNNAMED"
        jvmArgs += "-Dskiko.vsync.enabled=false"
    }
}

tasks.test {
    useJUnitPlatform()
}