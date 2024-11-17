plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"

	id("org.springframework.boot") version "3.3.5"
	id("io.spring.dependency-management") version "1.1.6"

	// jooq generator
	id("java")
	id("nu.studer.jooq") version "9.0"
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
val dbUrl = if (targetEnvironment == "prod") {
	"jdbc:postgresql://192.168.0.101:5432/tracking_app"
} else {
	"jdbc:postgresql://192.168.0.101:5433/tracking_app"
}
jooq {
	configurations {
		create("main") {
			jooqConfiguration.apply {
				logging = org.jooq.meta.jaxb.Logging.WARN
				jdbc.apply {
					driver = "org.postgresql.Driver"
					url = "jdbc:postgresql://192.168.0.101:5433/tracking_app" // Switch based on environment
					user = "tracking_user"
					password = "securepassword"

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


