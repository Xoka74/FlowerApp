plugins {
    alias(libs.plugins.flowerapp.android.module.convention)
    alias(libs.plugins.flowerapp.android.room.convention)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.shurdev.data"
}

dependencies {
    // Local dependencies
    api(projects.domain)

    // DI
    implementation(libs.javax.inject)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.datastore)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.logging.interceptor)
    implementation(libs.converter.gson)

    implementation(libs.androidx.core.ktx)
}