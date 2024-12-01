plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.3.5"
    id("io.spring.dependency-management") version "1.1.6"

    // jooq generator
    id("java")
    id("nu.studer.jooq") version "9.0"

    // Add ktlint plugin
    id("org.jlleitschuh.gradle.ktlint") version "11.0.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // spring
    implementation("org.springframework.boot:spring-boot-starter-jooq")
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // postgres
    jooqGenerator("org.postgresql:postgresql:42.7.2") // PostgreSQL driver
    runtimeOnly("org.postgresql:postgresql")

    // testcontainers
    //testImplementation("org.testcontainers:junit-jupiter")
    //testImplementation("org.testcontainers:postgresql")

    // jackson
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

val targetEnvironment: String = project.findProperty("targetEnvironment") as? String ?: "dev"
val dbUrl = System.getenv("DB_URL") ?: "jdbc:postgresql://localhost:5433/tracking_app"
val dbPassword = System.getenv("DB_PASSWORD") ?: "securepassword"
val dbUser = System.getenv("DB_USER") ?: "tracking_user"
jooq {
    configurations {
        create("main") {
            jooqConfiguration.apply {
                logging = org.jooq.meta.jaxb.Logging.WARN
                jdbc.apply {
                    driver = "org.postgresql.Driver"
                    url = dbUrl // Switch based on environment
                    user = dbUser
                    password = dbPassword
                }
                generator.apply {
                    name = "org.jooq.codegen.DefaultGenerator"
                    database.apply {
                        name = "org.jooq.meta.postgres.PostgresDatabase"
                        inputSchema = "public" // Change if using a different schema
                    }
                    generate.apply {
                        isDeprecated = false
                        isRecords = true
                        isImmutablePojos = true
                        isFluentSetters = true
                    }
                    target.apply {
                        packageName = "jooq"
                        directory = "src/main/generated" // Ensure this is in your source sets
                    }
                }
            }
        }
    }
}
