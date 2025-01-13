import com.shurdev.flowerapp.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidHiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.devtools.ksp")
                apply("com.google.dagger.hilt.android")
            }


            dependencies {
                "ksp"(libs.findLibrary("hilt-android-compiler").get())
                "implementation"(libs.findLibrary("hilt-android").get())
            }
        }
    }
}