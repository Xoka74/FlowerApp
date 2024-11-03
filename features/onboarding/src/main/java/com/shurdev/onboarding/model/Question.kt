package com.shurdev.onboarding.model

abstract class Question(
    val id: Int,
    val question: String,
    open val answerOptions: Any
)