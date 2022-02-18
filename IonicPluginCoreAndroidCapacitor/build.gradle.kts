
plugins {
    kotlin("android")// version "1.6.10"
    id("com.android.library")
}

group = "android-capacitor"
version = "0.0.1"

dependencies {
    implementation(project(":IonicPluginCore"))
    implementation(project(":IonicPluginCoreAndroid"))
    implementation(project(":capacitor-android"))
}

android {
    compileSdk = 31
    defaultConfig {
        minSdk = 26
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
