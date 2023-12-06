plugins {
    java
    idea
    id("org.springframework.boot") version "3.1.6"
    id("io.spring.dependency-management") version "1.1.4"
    id("com.github.ben-manes.versions") version "0.50.0"
    id("se.patrikerdes.use-latest-versions") version "0.2.18"
    id("com.gorylenko.gradle-git-properties") version "2.4.1"
}

group = "undecided"
version = "0.0.1-SNAPSHOT"


//java {
//    sourceCompatibility = JavaVersion.VERSION_17
//}
springBoot {
    buildInfo()
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
    implementation(project(":shared"))
    implementation(project(":common"))
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.cloud:spring-cloud-starter-config")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    runtimeOnly("org.postgresql:postgresql")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.slf4j:slf4j-api")
    implementation("io.zipkin.reporter2:zipkin-reporter-brave")
    implementation("io.micrometer:micrometer-tracing-bridge-brave")

}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}