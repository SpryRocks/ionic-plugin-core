
plugins {
    kotlin("android")
    id("com.android.library")
    id("maven-publish")
}

group = "com.github.SpryRocks"

dependencies {
    implementation(project(":core"))
}

android {
    namespace = "android.core"
    compileSdk = 31
    defaultConfig {
        minSdk = 21
        targetSdk = 31
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    sourceSets {
        getByName("main") {
            java.srcDir("src/main/kotlin")
        }
    }
    dependencies {
        implementation("androidx.appcompat:appcompat:1.4.2")
    }
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
            }
        }
    }
}
