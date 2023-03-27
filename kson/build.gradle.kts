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
        val kotlinxSerializationJsonVersion = project.findProperty("kotlinxSerializationJsonVersion") as String
        val kotlinxCoroutinesCoreVersion = project.findProperty("kotlinxCoroutinesCoreVersion") as String

        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxSerializationJsonVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutinesCoreVersion")
            }
        }
        val commonTest by getting {
            apply(plugin = "org.jetbrains.kotlin.plugin.serialization")
            dependencies {
                implementation(kotlin("test"))
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
