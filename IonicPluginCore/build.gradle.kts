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

    iosX64 {

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
