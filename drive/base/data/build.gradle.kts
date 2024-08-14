import Presentation_android_common_gradle.FlavorsConfiguratorPlugin.configureFlavors

plugins {
    id(libs.plugins.comAndroidLibrary)
    id(libs.plugins.kotlinAndroid)
    kotlin(libs.plugins.pluginSerialization)
}

configureFlavors()

android {
    compileSdk = Tools.Android.compileSdkVersion
    namespace = "me.proton.core.drive.base.data"

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

    api(project(":proton-android-drive-base-domain"))
    api("me.proton.core:account-data:25.2.1")
    api("me.proton.core:data:25.2.1")
    api("me.proton.core:network-data:25.2.1")
    implementation("androidx.datastore:datastore:1.0.0")
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("androidx.exifinterface:exifinterface:1.3.6")
    implementation("com.google.dagger:hilt-android:2.44")
//    implementation(libs.androidx.paging.common)
//    implementation(libs.retrofit)

}
