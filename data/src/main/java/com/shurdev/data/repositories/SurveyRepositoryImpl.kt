package com.shurdev.data.repositories

import com.shurdev.domain.models.survey.Answer
import com.shurdev.domain.models.survey.Question
import com.shurdev.domain.repositories.SurveyRepository
import javax.inject.Inject

class SurveyRepositoryImpl @Inject constructor() : SurveyRepository {

    override suspend fun getQuestions(): List<Question> {
        return questionsMocks
    }

    override suspend fun submitAnswers(answers: List<Answer>) {
        // TODO send answers to server
    }

    private val answerMocks = listOf(
        "Да",
        "Скорее да",
        "Не знаю",
        "Скорее нет",
        "Нет"
    )

    private val questionsMocks = listOf(
        Question(
            id = 1,
            question = "Вы живете в теплом регионе?",
            answerOptions = answerMocks
        ),
        Question(
            id = 2,
            question = "У вас есть аллергия на цветы?",
            answerOptions = answerMocks
        ),
        Question(
            id = 3,
            question = "Сколько цветов вы собираетесь выращивать?",
            answerOptions = answerMocks
        ),
        Question(
            id = 4,
            question = "У вас есть дети?",
            answerOptions = answerMocks
        ),
    )
}