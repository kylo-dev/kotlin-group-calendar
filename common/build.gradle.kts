import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    val kotlinVersion = "1.9.25"
    kotlin("kapt")
    kotlin("plugin.jpa") version kotlinVersion
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.Embeddable")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("org.springframework.data.mongodb.core.mapping.Document")
}

tasks.named<BootJar>("bootJar") {
    enabled = false
}
tasks.named<Jar>("jar") {
    enabled = true
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.projectlombok:lombok")
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")

    // querydsl - mongodb
    implementation("com.querydsl:querydsl-mongodb:5.0.0") {
        exclude(group = "org.mongodb", module = "mongo-java-driver")
    }
    implementation("com.querydsl:querydsl-apt:5.0.0:jakarta")
    implementation("org.springframework.boot:spring-boot-starter-security")
    testImplementation("org.springframework.security:spring-security-test")
    kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
