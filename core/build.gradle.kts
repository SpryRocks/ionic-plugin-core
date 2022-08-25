plugins {
    kotlin("multiplatform")
    id("maven-publish")
}

group = "com.github.SpryRocks"
version = rootProject.ext.get("mavenVersion") as String

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
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2")
                implementation(project(":kson"))
                implementation("org.jetbrains.kotlinx:atomicfu:0.18.0")
            }
        }
        if (enableJS == "true") {
            val jsMain by getting {
                dependsOn(commonMain)
                dependencies {
//                    implementation("org.jetbrains.kotlin:atomicfu:1.6.21") // not working without this line
                }
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

publishing {
    repositories {
        maven {
            name = "GitHub"
            url = uri("https://maven.pkg.github.com/SpryRocks/ionic-plugin-core")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
