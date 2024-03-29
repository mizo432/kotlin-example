plugins {
    id("idea")
    id("java")
    id("com.github.ben-manes.versions") version "0.51.0"
    id("se.patrikerdes.use-latest-versions") version "0.2.18"
    id("com.gorylenko.gradle-git-properties") version "2.4.1"
    id("org.flywaydb.flyway") version "10.7.1"
}

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.flywaydb:flyway-database-postgresql:10.7.1")
        /*MySQL5.7に対応してそうなバージョンをググッて適当に設定*/
        classpath("org.postgresql:postgresql:42.7.1")
    }
}

group = "decided"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

//dependencies {
//    implementation("org.flywaydb:flyway-database-postgresql:10.7.1")
//    runtimeOnly("org.postgresql:postgresql:42.7.1")
//
//}

flyway {
    driver = "org.postgresql.Driver"
    url = "jdbc:postgresql://localhost:5432/projectName"
    user = "user"
    password = "password"
    // schemas = ['public']
}
