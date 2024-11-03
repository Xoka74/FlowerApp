package com.shurdev.survey.model

class QuestionWithSingleAnswer(
    id: Int,
    question: String,
    override val answerOptions: List<String>,
) : Question(
    id = id,
    question = question,
    answerOptions = answerOptions
)