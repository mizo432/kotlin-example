plugins {
    id("java")
    id("idea")
    id("org.springframework.boot") version "3.1.6"
    id("io.spring.dependency-management") version "1.1.4"
    id("com.github.ben-manes.versions") version "0.48.0"
    id("se.patrikerdes.use-latest-versions") version "0.2.18"
    id("com.gorylenko.gradle-git-properties") version "2.4.1"

}

group = "undefined"
version = "0.0.1-SNAPSHOT"
java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator:3.1.0")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.springframework.boot:spring-boot-starter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

}

tasks.test {
    useJUnitPlatform()
}