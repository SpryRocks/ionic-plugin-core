plugins {
    kotlin("multiplatform")
    id("maven-publish")
}

group = rootProject.ext.get("mavenGroup") as String
version = rootProject.ext.get("mavenVersion") as String

val enableIos: String by project
val enableJS: String by project

kotlin {
    if (enableIos == "true") {
        ios {
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
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
                implementation(project(":kson"))
                implementation("org.jetbrains.kotlinx:atomicfu:0.18.0")
            }
        }
        if (enableJS == "true") {
            val jsMain by getting {
                dependsOn(commonMain)
            }
        }
        val jvmMain by getting
        val nativeMain by creating {
            dependsOn(commonMain)
            jvmMain.dependsOn(this)
        }
        if (enableIos == "true") {
            val iosMain by getting {
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
