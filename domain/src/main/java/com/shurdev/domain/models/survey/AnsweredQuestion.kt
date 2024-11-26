package com.shurdev.domain.models.survey

data class AnsweredQuestion(
    val id: Int? = null,
    val question: Question,
    val answer: Answer
)