package com.shurdev.survey.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.shurdev.survey.SurveyRoute
import kotlinx.serialization.Serializable

@Serializable
object SurveyNavGraph

fun NavGraphBuilder.surveyNavGraph() {
    navigation<SurveyNavGraph>(
        startDestination = SurveyRoute,
    ) {
        surveyScreen()
    }
}


@Serializable
object SurveyRoute

fun NavGraphBuilder.surveyScreen() {
    composable<SurveyRoute> {
        SurveyRoute()
    }
}