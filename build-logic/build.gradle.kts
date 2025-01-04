plugins {
    `kotlin-dsl`
}

group = "com.shurdev.flowerapp.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("androidModuleConvention") {
            id = libs.plugins.flowerapp.android.module.convention.get().pluginId
            implementationClass = "AndroidModuleConventionPlugin"
        }

        register("androidComposeModule") {
            id = libs.plugins.flowerapp.android.module.compose.get().pluginId
            implementationClass = "AndroidComposeModulePlugin"
        }

        register("androidFeatureModule") {
            id = libs.plugins.flowerapp.android.module.feature.get().pluginId
            implementationClass = "AndroidFeaturePlugin"
        }

        register("androidHiltConvention") {
            id = libs.plugins.flowerapp.android.hilt.convention.get().pluginId
            implementationClass = "AndroidHiltConventionPlugin"
        }

        register("androidRoomConvention") {
            id = libs.plugins.flowerapp.android.room.convention.get().pluginId
            implementationClass = "AndroidRoomConventionPlugin"
        }
    }
}