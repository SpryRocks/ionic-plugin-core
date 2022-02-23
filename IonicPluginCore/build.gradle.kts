plugins {
    kotlin("multiplatform")
}

group = "core"
version = "0.0.1"

kotlin {
    iosSimulatorArm64 {

    }

    iosArm64 {

    }

    jvm {
    }

    js(IR) {
        browser()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
            }
        }
        val iosSimulatorArm64Main by getting
        val iosArm64Main by getting
        val jvmMain by getting
        val nativeMain by creating {
            dependsOn(commonMain)
            iosSimulatorArm64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            jvmMain.dependsOn(this)
        }
    }
}
