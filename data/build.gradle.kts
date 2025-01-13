plugins {
    alias(libs.plugins.flowerapp.android.module.convention)
    alias(libs.plugins.flowerapp.android.room.convention)
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

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.logging.interceptor)
    implementation(libs.converter.gson)

    implementation(libs.androidx.core.ktx)
}