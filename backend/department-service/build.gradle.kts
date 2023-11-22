plugins {
    java
    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.1.3"
}

group = "undecided"
version = "0.0.1-SNAPSHOT"


java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

extra["springCloudVersion"] = "2022.0.4"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("io.micrometer:micrometer-tracing-bridge-brave")
//    implementation("io.zipkin.reporter2:zipkin-reporter-brave")
    implementation("org.springframework.cloud:spring-cloud-starter-config")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("de.codecentric:spring-boot-admin-starter-client:3.1.5")
    runtimeOnly("org.postgresql:postgresql:42.6.0")
    implementation("org.flywaydb:flyway-core")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.slf4j:slf4j-api:2.0.6")

}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}