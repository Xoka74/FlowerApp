plugins {
    alias(libs.plugins.flowerapp.android.module.feature)
    alias(libs.plugins.flowerapp.android.module.compose)
    alias(libs.plugins.flowerapp.android.hilt.convention)
}

android {
    namespace = "com.shurdev.survey"
}

dependencies {
    // Local dependencies
    api(projects.domain)
    api(projects.utils)
    api(projects.uiKit)

    // Coil
    implementation(libs.coil.compose)

    implementation(libs.kotlinx.coroutines.core)
}