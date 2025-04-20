plugins {
    kotlin("jvm") version "2.1.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project.dependencies.platform("io.insert-koin:koin-bom:4.0.3"))
    implementation("io.insert-koin:koin-core")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}