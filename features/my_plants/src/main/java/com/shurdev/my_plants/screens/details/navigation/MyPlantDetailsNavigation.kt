import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.shurdev.domain.models.PlantId
import com.shurdev.my_plants.screens.details.MyPlantDetailsRoute
import kotlinx.serialization.Serializable

@Serializable
data class MyPlantDetails(val plantId: PlantId)

fun NavController.navigateToMyPlantDetailsScreen(plantId: PlantId) =
    navigate(MyPlantDetails(plantId))

fun NavGraphBuilder.myPlantDetailsScreen(
    onBackInvoked: () -> Unit,
) {
    composable<MyPlantDetails> { backStackEntry ->
        val plantDetails: MyPlantDetails = backStackEntry.toRoute<MyPlantDetails>()

        MyPlantDetailsRoute(
            plantId = plantDetails.plantId,
            onBackInvoked = onBackInvoked,
        )
    }
}
