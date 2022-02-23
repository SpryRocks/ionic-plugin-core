plugins {
    kotlin("multiplatform")
    id("com.android.library")
//    kotlin("native.cocoapods")
}

group = "android-capacitor"
version = "0.0.1"

val enableIos: String by project

android {
    compileSdk = 31
    sourceSets {
        getByName("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
        }
    }
}

kotlin {
//    cocoapods {
//
//
//        summary = "IonicPluginCoreCapacitor"
//        homepage = "IonicPluginCoreCapacitor"
//
//        ios.deploymentTarget = "12"
//
//        pod("Capacitor") {
////            version = "1.0"
//            source = path(project.file("../node_modules/@capacitor/ios"))
//            packageName = "Capacitor"
//        }
//
//        useLibraries()
//
////        targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget>().all {
////            compilations["main"].cinterops["Capacitor"].extraOpts(
////                "-compiler-option",
////                "-fmodules",
//////                "-compiler-option",
//////                "-fcxx-modules"
////            )
////        }
//    }

    android {
    }

    js(IR) {
        browser()
    }

    if (enableIos == "true") {
        iosSimulatorArm64 {
        }

        iosArm64 {
        }
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
        val iosSimulatorArm64Main by sourceSets.getting
        val iosArm64Main by sourceSets.getting
        if (enableIos == "true") {
            val iosMain by sourceSets.creating {
                dependsOn(commonMain)
                iosSimulatorArm64Main.dependsOn(this)
                iosArm64Main.dependsOn(this)
            }
        }
    }

}