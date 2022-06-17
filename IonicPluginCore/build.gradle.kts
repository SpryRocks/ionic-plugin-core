plugins {
    kotlin("multiplatform")
}

group = "core"
version = "0.0.1"

val enableIos: String by project
val enableJS: String by project

kotlin {
    if (enableIos == "true") {
        iosSimulatorArm64 {
        }

        iosArm64 {
        }
    }

    jvm {
    }

    if (enableJS == "true") {
        js(IR) {
            browser()
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
            }
        }
        val jvmMain by getting
        val nativeMain by creating {
            dependsOn(commonMain)
            jvmMain.dependsOn(this)
        }
        if (enableIos == "true") {
            val iosSimulatorArm64Main by getting {
                dependsOn(nativeMain)
            }
            val iosArm64Main by getting {
                dependsOn(nativeMain)
            }
        }
    }
}
