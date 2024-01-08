plugins {
    java
    idea
    id("org.springframework.boot") version "3.2.1"
    id("io.spring.dependency-management") version "1.1.4"
    id("com.github.ben-manes.versions") version "0.48.0"
    id("se.patrikerdes.use-latest-versions") version "0.2.18"
    id("com.gorylenko.gradle-git-properties") version "2.4.1"

}

group = "undecided"
version = "0.0.1-SNAPSHOT"

springBoot {
    buildInfo()
}
java {
    sourceCompatibility = JavaVersion.VERSION_21
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

extra["springCloudVersion"] = "2023.0.0"

dependencies {
    implementation(project(":common"))
    implementation(project(":shared-webMvc"))
    implementation(project(":db-migration"))
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-resilience4j")
    implementation("org.springframework.cloud:spring-cloud-starter-config")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("io.zipkin.reporter2:zipkin-reporter-brave")
    implementation("io.micrometer:micrometer-tracing-bridge-brave")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    implementation("io.netty:netty-resolver-dns-native-macos:4.1.80.Final:osx-aarch_64")
    implementation("org.flywaydb:flyway-core")
    runtimeOnly("org.postgresql:postgresql")
    annotationProcessor("org.projectlombok:lombok")
    compileOnly("org.projectlombok:lombok")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
