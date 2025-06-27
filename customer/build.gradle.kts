import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    val kotlinVersion = "1.9.25"
    kotlin("kapt")
}
val springCloudVersion by extra("2023.0.5")

tasks.named<BootJar>("bootJar") {
    enabled = true
}
tasks.named<Jar>("jar") {
    enabled = false
}
dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
    }
}

dependencies {

    // dependency module
    implementation(project(":common"))
    implementation(project(":common:exception"))
    implementation(project(":common:logging"))
    implementation(project(":storage:document"))

    // spring web
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // graphql
    implementation("org.springframework.boot:spring-boot-starter-graphql")
    implementation("com.graphql-java:graphql-java-extended-scalars:22.0")
//    implementation("com.graphql-java-kickstart:graphql-spring-boot-starter:14.0.0")
    testImplementation("org.springframework.graphql:spring-graphql-test")

    // db
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.projectlombok:lombok")

    // querydsl - mongodb
    implementation("com.querydsl:querydsl-mongodb:5.0.0") {
        exclude(group = "org.mongodb", module = "mongo-java-driver")
    }
    implementation("com.querydsl:querydsl-apt:5.0.0:jakarta")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")

    // security
    implementation("org.springframework.boot:spring-boot-starter-security")

    // logging
    implementation("io.github.oshai:kotlin-logging-jvm:7.0.0")

    // jwt
    implementation("io.jsonwebtoken:jjwt-api:0.12.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.5")
    implementation("io.jsonwebtoken:jjwt-jackson:0.12.5")

    // test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.springframework.security:spring-security-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
