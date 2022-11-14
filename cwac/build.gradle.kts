
plugins {
    kotlin("android")
    id("com.android.library")
    id("maven-publish")
}

group = rootProject.ext.get("mavenGroup") as String
version = rootProject.ext.get("mavenVersion") as String

dependencies {
    implementation(project(":core"))
}

android {
    namespace = "android.core"
    compileSdk = 31
    defaultConfig {
        minSdk = 21
        targetSdk = 31
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    sourceSets {
        getByName("main") {
            java.srcDir("src/main/kotlin")
        }
    }
    dependencies {
        implementation("androidx.appcompat:appcompat:1.5.1")
    }
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
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
