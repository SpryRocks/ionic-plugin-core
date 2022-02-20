rootProject.name = "IonicPluginCore"

include(":capacitor-android")
project(":capacitor-android").projectDir = File("node_modules/@capacitor/android/capacitor")

include(":IonicPluginCore")

include(":IonicPluginCoreAndroid")

include(":IonicPluginCoreCapacitor")
