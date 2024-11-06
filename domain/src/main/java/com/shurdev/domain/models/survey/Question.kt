package com.shurdev.domain.models.survey

data class Question(
    val id: Int,
    val question: String,
    val answerOptions: List<String>,
)