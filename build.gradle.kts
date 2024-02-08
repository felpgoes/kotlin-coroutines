plugins {
    kotlin("jvm") version "1.9.22"
}

group = "org.coroutines-study"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.2")

    testImplementation("org.jetbrains.kotlin:kotlin-test:1.8.10")

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}