package com.shurdev.survey.model

class QuestionWithMultipleAnswers(
    id: Int,
    question: String,
    answerOptions: List<String>
) : Question(
    id = id,
    question = question,
    answerOptions = answerOptions
)