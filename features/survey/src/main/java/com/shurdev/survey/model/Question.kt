package com.shurdev.survey.model

abstract class Question(
    val id: Int,
    val question: String,
    open val answerOptions: Any
)