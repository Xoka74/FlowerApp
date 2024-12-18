enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "FlowerApp"
include(":app")
include(":domain")
include(":data")
include(":features:my_plants")
include(":ui_kit")
include(":features:gallery")
include(":utils")
include(":features:survey")
include(":features:onboarding")
include(":features:profile")
include(":features:recommended_plants")
include(":features:trade")
