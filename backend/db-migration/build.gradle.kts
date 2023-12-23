plugins {
    id("idea")
    id("java")
    id("com.github.ben-manes.versions") version "0.48.0"
    id("se.patrikerdes.use-latest-versions") version "0.2.18"
    id("com.gorylenko.gradle-git-properties") version "2.4.1"
    id("org.flywaydb.flyway") version "10.4.1"
}

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.flywaydb:flyway-database-postgresql:10.4.0")
        /*MySQL5.7に対応してそうなバージョンをググッて適当に設定*/
        classpath("org.postgresql:postgresql:42.5.4")
    }
}

group = "decided"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

//dependencies {
//    implementation("org.flywaydb:flyway-database-postgresql:10.4.0")
//    runtimeOnly("org.postgresql:postgresql:42.5.4")
//
//}

flyway {
    driver = "org.postgresql.Driver"
    url = "jdbc:postgresql://localhost:5432/projectName"
    user = "user"
    password = "password"
    // schemas = ['public']
}
