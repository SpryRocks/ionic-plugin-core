plugins {
    kotlin("multiplatform") version "1.6.10"
}

group = "core"
version = "0.0.1"

kotlin {
    iosSimulatorArm64 {

    }

    jvm {

    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
            }
        }
    }
}
