plugins {
    alias(libs.plugins.flowerapp.android.module.feature)
    alias(libs.plugins.flowerapp.android.module.compose)
    alias(libs.plugins.flowerapp.android.hilt.convention)
}

android {
    namespace = "com.shurdev.onboarding"
}

dependencies {
    // Local dependencies
    api(projects.domain)
    api(projects.utils)

    // Coil
    implementation(libs.coil.compose)

    implementation(libs.kotlinx.coroutines.core)

    // Splash API
    implementation(libs.androidx.core.splashscreen)
}