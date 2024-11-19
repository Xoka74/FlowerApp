package com.shurdev.data.mappers

import com.shurdev.data.local.entity.SurveyResultsEntity
import com.shurdev.domain.models.survey.Answer
import com.shurdev.domain.models.survey.AnsweredQuestion

fun SurveyResultsEntity.toDomainModel(): AnsweredQuestion {
    return AnsweredQuestion(
        id = id,
        answer = Answer(
            answer = answer,
            questionId = question.id
        ),
        question = question
    )
}

fun AnsweredQuestion.toEntity(): SurveyResultsEntity {
    return SurveyResultsEntity(
        question = question,
        answer = answer.answer
    )
}