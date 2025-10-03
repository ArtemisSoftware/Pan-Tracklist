plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.com.google.devtools.ksp)
    alias(libs.plugins.de.mannodermaus.android.junit5)
}

android {
    namespace = "com.artemissoftware.pantracklist.albums.data"
    compileSdk = 36

    defaultConfig {
        minSdk = 31

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(project(":core:database"))
    implementation(project(":core:domain"))
    implementation(project(":core:network"))


    implementation(project(":features:albums:domain"))

    implementation(libs.androidx.core.ktx)

    implementation(libs.retrofit)
    implementation(libs.converter.moshi)
    implementation(libs.moshi.kotlin)
    implementation(libs.okhttp.log.interceptor)

    implementation(libs.paging.compose)
    implementation(libs.paging.runtime)
    implementation(libs.paging.common)
    implementation(libs.paging.runtime.ktx)

    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    ksp(libs.hilt.android.compiler)

    testImplementation(libs.junit)
    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)
    testImplementation(libs.junit.jupiter.params)
    testImplementation(libs.assertk)

    testImplementation(libs.okhttp.mockwebserver)
    testImplementation(libs.kotlinx.coroutines.test)

}