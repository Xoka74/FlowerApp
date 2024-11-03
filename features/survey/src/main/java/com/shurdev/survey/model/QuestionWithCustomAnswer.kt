package com.shurdev.survey.model

class QuestionWithCustomAnswer(
    id: Int,
    question: String,
    answerOptions: String
) : Question(
    id = id,
    question = question,
    answerOptions = answerOptions,
)