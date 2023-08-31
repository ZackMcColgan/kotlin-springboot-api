import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.1.3"
	id("io.spring.dependency-management") version "1.1.3"
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22"
	kotlin("plugin.serialization") version "1.9.10"
}

group = "com.cat-facts-api"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-rest")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
//	implementation("software.amazon.awssdk:s3:2.20.137")
//	implementation("aws.sdk.kotlin:s3:0.25.0-beta")
	implementation("aws.sdk.kotlin:s3:0.31.0-beta")
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
//	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
//	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.7.3")
//	implementation("org.reactivestreams:reactive-streams:1.0.4")
//	implementation("com.squareup.okhttp3:okhttp:4.10.0")
	implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.11")
//	implementation("software.amazon.awssdk:s3:2.20.137") {
//		exclude(group = "com.squareup.okhttp3", module = "okhttp")
//	}
//	implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.11")
}

//configurations.all {
//	resolutionStrategy.force("com.squareup.okhttp3:okhttp:5.0.0-alpha.11")
//}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
