plugins {
  java
  id("war")
}

tasks.war {
  enabled = true
  archiveFileName.set("pizzeria-servlet.war")
}

dependencies {
  implementation(project(":pizzeria-core"))
  implementation("org.springframework:spring-web")
  implementation("org.springframework:spring-webmvc")
  implementation("org.springframework:spring-webflux")

  implementation("jakarta.ws.rs:jakarta.ws.rs-api:${property("version.jakarta")}")
  implementation("jstl:jstl:${property("version.jstl")}")
  implementation("com.fasterxml.jackson.core:jackson-databind:${property("version.jackson")}")
  compileOnly("jakarta.servlet:jakarta.servlet-api:${property("version.servlet")}")
  compileOnly("org.apache.tomcat.embed:tomcat-embed-core:${property("version.tomcat")}")
}
