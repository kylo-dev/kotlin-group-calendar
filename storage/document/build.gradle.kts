import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    val kotlinVersion = "1.9.25"
    kotlin("kapt")
    kotlin("plugin.jpa") version kotlinVersion
}

allOpen {
    annotation("org.springframework.data.mongodb.core.mapping.Document")
}

tasks.named<BootJar>("bootJar") {
    enabled = false
}
tasks.named<Jar>("jar") {
    enabled = true
}

dependencies {
    // db
//    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")

//    // graphql
//    implementation("org.springframework.boot:spring-boot-starter-graphql")
//    testImplementation("org.springframework.graphql:spring-graphql-test")
//
//    // querydsl - mongodb
//    implementation("com.querydsl:querydsl-mongodb:5.0.0") {
//        exclude(group = "org.mongodb", module = "mongo-java-driver")
//    }
//    implementation("com.querydsl:querydsl-apt:5.0.0:jakarta")
//    kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")
}
