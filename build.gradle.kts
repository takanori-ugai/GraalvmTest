plugins {
    kotlin("jvm") version "2.4.0"
    application
    id("org.graalvm.buildtools.native") version "1.1.2"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(platform("tools.jackson:jackson-bom:3.1.4"))
    implementation("tools.jackson.core:jackson-databind")
    implementation("tools.jackson.core:jackson-core")
    implementation("com.fasterxml.jackson.core:jackson-annotations")
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
