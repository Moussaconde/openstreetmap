plugins {
  java
  id("org.springframework.boot")
  kotlin("plugin.allopen")
  kotlin("plugin.noarg")
}

tasks.bootJar {
  archiveFileName.set("osm-boot.jar")
  manifest.attributes["Implementation-Version"] = project.version
}

tasks.named<Jar>("jar") {
  enabled = false
}

allOpen{
  annotation("org.springframework.context.annotation.Configuration")
}

dependencies {
  implementation(project(":osm-core"))
  implementation(kotlin("stdlib"))
  implementation(kotlin("reflect"))

  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.boot:spring-boot-configuration-processor")
  implementation("org.springframework.boot:spring-boot-starter-cache")

  implementation("com.github.ben-manes.caffeine:caffeine:${property("version.caffeine")}")

  implementation("mil.nga.sf:sf-geojson:${property("version.sf-geojson")}")
}
