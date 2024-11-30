package com.shurdev.data.repositories

import com.shurdev.data.local.dao.SurveyResultsDao
import com.shurdev.data.mappers.toDomainModel
import com.shurdev.data.mappers.toEntity
import com.shurdev.data.remote.api.SurveyApi
import com.shurdev.domain.models.survey.Answer
import com.shurdev.domain.models.survey.Question
import com.shurdev.domain.models.survey.AnsweredQuestion
import com.shurdev.domain.repositories.SurveyRepository
import javax.inject.Inject

class SurveyRepositoryImpl @Inject constructor(
    private val surveyResultsDao: SurveyResultsDao,
    private val surveyApi: SurveyApi
) : SurveyRepository {

    override suspend fun getQuestions(): List<Question> {
        return questionsMocks
    }

    override suspend fun submitAnswers(answers: List<Answer>) {
        surveyApi.submitAnswers(answers)
    }


    override suspend fun saveResultsToDatabase(results: List<AnsweredQuestion>) {
        surveyResultsDao.deleteAllResults()

        surveyResultsDao.saveResults(
            surveyResults = results.map { it.toEntity() }
        )
    }

    override suspend fun getResultsFromDatabase(): List<AnsweredQuestion> {
        val results = surveyResultsDao.getAllResults()

        return results.map { resultEntity -> resultEntity.toDomainModel() }
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
            answerOptions = answerMocks.plus("Конечно")
        ),
        Question(
            id = 2,
            question = "У вас есть аллергия на цветы?",
            answerOptions = answerMocks.plus(listOf("Разумеется", "Естественно"))
        ),
        Question(
            id = 3,
            question = "Сколько цветов вы собираетесь выращивать?",
            answerOptions = answerMocks.plus(listOf("1", "2"))
        ),
        Question(
            id = 4,
            question = "У вас есть дети?",
            answerOptions = answerMocks.plus(listOf("1", "2"))
        ),
    )
}