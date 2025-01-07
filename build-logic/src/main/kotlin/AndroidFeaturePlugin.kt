import com.shurdev.flowerapp.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeaturePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply("flowerapp.android.module.convention")

                // Required for type-safe navigation routes
                apply("org.jetbrains.kotlin.plugin.serialization")
            }


            dependencies {
                "implementation"(libs.findLibrary("androidx-navigation-compose").get())
                "implementation"(libs.findLibrary("androidx-hilt-navigation-compose").get())

                // Required for type-safe navigation routes
                "implementation"(libs.findLibrary("kotlinx-serialization-json").get())
            }
        }
    }
}