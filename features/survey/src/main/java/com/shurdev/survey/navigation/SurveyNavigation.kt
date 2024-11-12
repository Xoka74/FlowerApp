package com.shurdev.survey.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.shurdev.survey.SurveyRoute
import kotlinx.serialization.Serializable

@Serializable
object SurveyNavGraph

fun NavController.navigateToSurveyGraph() = navigate(SurveyNavGraph)

fun NavGraphBuilder.surveyNavGraph(
    onFinishSurvey: () -> Unit
) {
    navigation<SurveyNavGraph>(
        startDestination = SurveyRoute,
    ) {
        surveyScreen(
            onFinishSurvey = onFinishSurvey
        )
    }
}


@Serializable
object SurveyRoute

fun NavGraphBuilder.surveyScreen(
    onFinishSurvey: () -> Unit
) {
    composable<SurveyRoute> {
        SurveyRoute(
            onFinishSurvey = onFinishSurvey
        )
    }
}