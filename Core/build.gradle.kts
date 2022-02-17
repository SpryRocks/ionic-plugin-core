plugins {
    kotlin("multiplatform") version "1.6.10"
}

group = "me.maksimzhemerenko"
version = "1.0-SNAPSHOT"

kotlin {
    iosSimulatorArm64 {

    }

    sourceSets {
        val commonMain by getting
    }
}
