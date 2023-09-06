buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        val kotlinVersion = "1.9.10"
        classpath(kotlin("gradle-plugin", version = kotlinVersion))
        classpath(kotlin("serialization", version = kotlinVersion))

        classpath("com.android.tools.build:gradle:7.2.2")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

extra.apply {
    set("kotlinxCoroutinesCoreVersion", "1.7.3")
    set("kotlinxSerializationJsonVersion", "1.6.0")
    set("kotlinxAtomicfuVersion", "0.22.0")
    set("androidxAppcompatVersion", "1.6.1")
    set("mavenGroup", "com.github.SpryRocks.ionic-plugin-core")
    set("mavenVersion", "0.1.2-alpha.8")
}
