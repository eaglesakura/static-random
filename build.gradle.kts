import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    extra["kotlin_version"] = "1.3.61"
    extra["kotlin_coroutines_version"] = "1.3.2"
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:0.9.18") // kotlin-docs
        classpath("com.jfrog.bintray.gradle:gradle-bintray-plugin:1.8.4")
    }
}

plugins {
    kotlin("jvm") version "${extra["kotlin_version"]}"
}

repositories {
    mavenLocal()
    google()
    jcenter()
    mavenCentral()
}

dependencies {
    /**
     * Kotlin support
     */
    api("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${extra["kotlin_version"]}")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:${extra["kotlin_coroutines_version"]}")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

val compileKotlin: KotlinCompile by tasks
val compileTestKotlin: KotlinCompile by tasks

compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

apply(from = "configure.gradle.kts")
apply(from = "dsl/ktlint.gradle")
apply(from = "dsl/bintray-jvm.gradle")