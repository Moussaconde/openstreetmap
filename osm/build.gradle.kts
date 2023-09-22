plugins {
  java
  idea
  kotlin("jvm")
  kotlin("plugin.allopen")
  kotlin("plugin.noarg")
  id("com.github.ben-manes.versions")
  id("org.springframework.boot") apply false
  id("org.jetbrains.kotlin.plugin.spring") apply true
}

fun isNonStable(version: String): Boolean {
  val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.uppercase().contains(it) }
  val regex = "^[0-9,\\.v\\-]+(-r)?$".toRegex()
  val isStable = stableKeyword || regex.matches(version)
  return isStable.not()
}

tasks.dependencyUpdates {
  rejectVersionIf {
    isNonStable(candidate.version)
  }
}

val javaVersion = JavaVersion.VERSION_17

allprojects {
  apply(plugin = "idea")

  group = "io.github.joxit"
  version = "1.0-SNAPSHOT"

  repositories {
    mavenLocal()
    mavenCentral()
  }
}

subprojects {
  apply(plugin = "java")
  apply(plugin = "kotlin")

  tasks.compileKotlin {
    kotlinOptions.jvmTarget = javaVersion.toString()
  }

  java {
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
  }

  dependencies {
    implementation(platform("org.springframework.boot:spring-boot-dependencies:${property("version.spring.boot")}"))
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
  }
}
