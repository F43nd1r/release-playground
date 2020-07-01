import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.72"
    id("com.faendir.gradle.release") version "0.1"
}
dependencies {
    implementation(kotlin("stdlib-jdk8"))
}
repositories {
    mavenCentral()
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

tasks.register("fakePublish") {
    doLast {
        file("$buildDir/published.txt").writeText("Publish task was called for version ${project.version}")
    }
}

tasks["afterReleaseBuild"].dependsOn(tasks["fakePublish"])