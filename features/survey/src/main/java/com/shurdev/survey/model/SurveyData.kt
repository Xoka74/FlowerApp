package com.shurdev.survey.model

import com.shurdev.domain.models.survey.Question

data class SurveyData(
    val id: Int,
    val imageSrc: String,
    val content: Question
)