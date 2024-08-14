import Presentation_android_common_gradle.FlavorsConfiguratorPlugin.configureFlavors

plugins {
    id(libs.plugins.comAndroidLibrary)
    id(libs.plugins.kotlinAndroid)
    kotlin(libs.plugins.pluginSerialization)
}

configureFlavors()

android {
    compileSdk = Tools.Android.compileSdkVersion
    namespace = "me.proton.core.drive.base.domain"

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

//    api(project(":drive:message-queue:domain"))
    api("me.proton.core:crypto-common:25.2.1")
    api("me.proton.core:domain:25.2.1")
    api("me.proton.core:user-domain:25.2.1")
    implementation("com.google.dagger:hilt-android:2.44")

//    implementation(libs.core.accountManager.domain)
//    implementation(libs.core.data)
//    implementation(libs.core.key.domain)
//    implementation(libs.core.network.domain)

}
