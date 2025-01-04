plugins {
    alias(libs.plugins.flowerapp.android.module.convention)
    alias(libs.plugins.flowerapp.android.module.compose)
}

android {
    namespace = "com.shurdev.ui_kit"
}

dependencies {
    api(projects.domain)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.androidx.activity.compose)

    // Coil
    implementation(libs.coil.compose)
}