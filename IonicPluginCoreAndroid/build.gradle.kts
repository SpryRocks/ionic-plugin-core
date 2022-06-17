
plugins {
    kotlin("android")
    id("com.android.library")
}

group = "android"
version = "0.0.1"
dependencies {
    implementation(project(":IonicPluginCore"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2")
}

android {
    compileSdk = 31
    defaultConfig {
        minSdk = 22
        targetSdk = 31
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
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
}
