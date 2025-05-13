plugins {
    java
    id("org.springframework.boot") version "3.4.4"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "dev.staldvik"
version = "0.0.1-SNAPSHOT"

val springStateMachineVersion = "4.0.0"


java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.statemachine:spring-statemachine-starter")
    compileOnly("org.projectlombok:lombok")
    runtimeOnly("com.h2database:h2")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.statemachine:spring-statemachine-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

dependencyManagement {
    imports {
        // Import the Spring Statemachine BOM
        mavenBom("org.springframework.statemachine:spring-statemachine-bom:${springStateMachineVersion}")
        // The Spring Boot plugin usually handles its own BOM implicitly, but you could add it explicitly if needed:
        // mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
