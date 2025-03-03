plugins {
    kotlin("jvm") version "2.1.10"
    application
    id("org.graalvm.buildtools.native") version "0.10.5"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.fasterxml.jackson.core:jackson-core:2.18.3")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.3")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.18.3")
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
