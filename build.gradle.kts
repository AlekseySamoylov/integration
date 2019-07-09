import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("plugin.jpa") version "1.2.71"
  id("org.springframework.boot") version "2.1.6.RELEASE"
  id("io.spring.dependency-management") version "1.0.7.RELEASE"
  kotlin("jvm") version "1.2.71"
  kotlin("plugin.spring") version "1.2.71"
  kotlin("plugin.allopen") version "1.2.71"
  kotlin("kapt") version "1.2.71"

}

allOpen {
  annotation("org.springframework.ws.server.endpoint.annotation.Endpoint")
  annotation("javax.persistence.Entity")
  annotation("javax.persistence.Embeddable")
  annotation("javax.persistence.MappedSuperclass")
}

group = "com.alekseysamoylov"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.springframework.boot:spring-boot-starter-mustache")
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.boot:spring-boot-starter-web-services")

  implementation("org.springframework.cloud:spring-cloud-starter-netflix-hystrix")

  implementation("io.springfox:springfox-swagger2:2.9.2")
  implementation("io.springfox:springfox-swagger-ui:2.9.2")

  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

  implementation("wsdl4j:wsdl4j:1.6.3")

  runtimeOnly("com.h2database:h2:1.4.199")

  runtimeOnly("org.springframework.boot:spring-boot-devtools")
  testImplementation("org.springframework.boot:spring-boot-starter-test") {
    exclude(module = "junit")
    exclude(module = "mockito-core")
  }
  testImplementation("org.junit.jupiter:junit-jupiter-api")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
  testImplementation("com.ninja-squad:springmockk:1.1.2")

  kapt("org.springframework.boot:spring-boot-configuration-processor")
}

dependencyManagement {
  imports {
    mavenBom("org.springframework.cloud:spring-cloud-dependencies:Finchley.SR2")
  }
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs = listOf("-Xjsr305=strict")
    jvmTarget = "1.8"
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}

