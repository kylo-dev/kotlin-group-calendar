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
    implementation(project(":common:monitoring"))
//    implementation(project(":common:security"))
    implementation(project(":storage:document"))

    // spring web
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")

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
//    implementation("org.springframework.boot:spring-boot-starter-security")

    // test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
//    testImplementation("org.springframework.security:spring-security-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
