import Presentation_android_common_gradle.FlavorsConfiguratorPlugin.configureFlavors

plugins {
    id(libs.plugins.comAndroidLibrary)
    id(libs.plugins.kotlinAndroid)
    kotlin(libs.plugins.pluginSerialization)
}

configureFlavors()

android {
    compileSdk = Tools.Android.compileSdkVersion
    namespace = "me.proton.core.drive.share.data"

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    defaultConfig {
        minSdk = Tools.Android.minSdkVersion
        consumerProguardFiles("proguard-rules.pro")
    }
}

dependencies {
    implementation(libs.androidxCore.coreKtx)
    implementation(libs.orgJetbrainsKotlinx.kotlinxSerializationJson)

    api(project(":proton-android-drive-base-data"))
//    api(project(":drive:share:domain"))
//    implementation(libs.retrofit)
//    implementation(libs.androidx.dataStore.preferences)
}
