package com.shurdev.data.mappers

import com.google.gson.Gson
import com.shurdev.data.models.QuestionDto
import com.shurdev.domain.models.survey.Answer
import com.shurdev.domain.models.survey.Question


fun QuestionDto.toDomainModel(): Question {
    return Question(
        id = id,
        question = text,
        answerOptions = variants,
    )
}


fun List<Answer>.toDto(userId: String): Map<String, Any> {
    val gson = Gson()

    return mapOf(
        "userId" to userId,
        "answers" to map {
            mapOf(
                "questionId" to it.questionId,
                "questionsMask" to gson.toJsonTree(
                    it.options.map { option ->
                        if (it.answer == option) 1 else 0
                    }
                )
            )
        }
    )
}