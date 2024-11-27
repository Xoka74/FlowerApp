package com.shurdev.survey.view_model

import com.shurdev.domain.models.survey.Question

internal sealed class SurveyUiState

internal data object SurveyLoadingUiState : SurveyUiState()

internal data class SurveyLoadedUiState(
    val questions: List<Question>,
    val answersIndices: List<Int>,
    val currentQuestionIndex: Int,
    val isFinished: Boolean = false
) : SurveyUiState()

internal data object SurveyErrorUiState : SurveyUiState()
