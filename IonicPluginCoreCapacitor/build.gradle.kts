plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

group = "android-capacitor"
version = "0.0.1"

android {
    compileSdk = 31
    sourceSets {
        getByName("main") {
            manifest.srcFile ("src/androidMain/AndroidManifest.xml")
        }
    }
}

kotlin {

    android {

    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":IonicPluginCore"))
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(project(":IonicPluginCoreAndroid"))
                implementation(project(":capacitor-android"))
                implementation("androidx.appcompat:appcompat:1.4.1")
            }
        }
    }

}