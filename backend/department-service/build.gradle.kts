plugins {
    java
    idea
    id("org.springframework.boot") version "3.2.0"
    id("io.spring.dependency-management") version "1.1.4"
    id("com.github.ben-manes.versions") version "0.50.0"
    id("se.patrikerdes.use-latest-versions") version "0.2.18"
    id("com.gorylenko.gradle-git-properties") version "2.4.1"
}

group = "undecided"
version = "0.0.1-SNAPSHOT"


java {
    sourceCompatibility = JavaVersion.VERSION_21
}
springBoot {
    buildInfo()
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

extra["springCloudVersion"] = "2023.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":shared"))
    implementation(project(":common"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.0.4")
    implementation("org.springframework.boot:spring-boot-starter-web:3.1.0")
    implementation("org.springframework.cloud:spring-cloud-starter-config:4.0.1")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:4.0.0")
    runtimeOnly("org.postgresql:postgresql:42.5.4")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.1.0")
    implementation("org.slf4j:slf4j-api:2.0.5")
    implementation("org.springframework.boot:spring-boot-starter-actuator:3.1.0")
    implementation("io.zipkin.reporter2:zipkin-reporter-brave:2.16.3")
    implementation("io.micrometer:micrometer-tracing-bridge-brave:1.0.3")

}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}