plugins {
    id("java")
    id("idea")
    id("org.springframework.boot") version "3.2.0"
    id("io.spring.dependency-management") version "1.1.4"
    id("com.github.ben-manes.versions") version "0.48.0"
    id("se.patrikerdes.use-latest-versions") version "0.2.18"
    id("com.gorylenko.gradle-git-properties") version "2.4.1"

}

version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":common"))
    implementation("org.springframework.boot:spring-boot-starter-actuator:3.2.0")
    testImplementation(platform("org.junit:junit-bom:5.9.2"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
    implementation("org.springframework.boot:spring-boot-starter:3.2.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.2.0")
    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")

}

tasks.test {
    useJUnitPlatform()
}