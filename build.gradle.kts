plugins {
    kotlin("jvm") version "2.2.0"
    application
    id("org.graalvm.buildtools.native") version "0.11.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.fasterxml.jackson.core:jackson-core:2.19.1")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.19.1")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.19.1")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

application {
    mainClass.set("demo.Fortune")
//    mainClass.set("demo.HelloKt")
}

graalvmNative {
    /*
    binaries {
        named("main") {
            buildArgs.add("--initialize-at-build-time=kotlin")
        }
    }
    */
    binaries.all {
        resources.autodetect()
    }
}

graalvmNative {
    toolchainDetection.set(true)
}
