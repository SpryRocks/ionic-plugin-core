buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        val kotlinVersion = "2.0.0"
        classpath(kotlin("gradle-plugin", version = kotlinVersion))
        classpath(kotlin("serialization", version = kotlinVersion))

        classpath("com.android.tools.build:gradle:7.4.2")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

extra.apply {
    set("kotlinxCoroutinesCoreVersion", "1.8.1")
    set("kotlinxSerializationJsonVersion", "1.7.0-RC")
    set("kotlinxAtomicfuVersion", "0.24.0")
    set("androidxAppcompatVersion", "1.6.1")
    set("mavenGroup", "com.github.SpryRocks.ionic-plugin-core")
    set("mavenVersion", "0.2.0-alpha.0")
}
