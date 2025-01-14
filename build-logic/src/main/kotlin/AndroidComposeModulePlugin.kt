import com.android.build.gradle.LibraryExtension
import com.shurdev.flowerapp.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidComposeModulePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

            val extension = extensions.getByType<LibraryExtension>()

            with(extension) {
                buildFeatures {
                    compose = true
                }

                dependencies {
                    "implementation"(platform(libs.findLibrary("androidx-compose-bom").get()))
                    "implementation"(libs.findLibrary("androidx-compose-ui-tooling-preview").get())
                    "implementation"(libs.findLibrary("androidx-compose-ui-graphics").get())
                    "implementation"(libs.findLibrary("androidx-compose-material3").get())

                    "debugImplementation"(libs.findLibrary("androidx-compose-ui-tooling").get())
                }
            }
        }
    }
}