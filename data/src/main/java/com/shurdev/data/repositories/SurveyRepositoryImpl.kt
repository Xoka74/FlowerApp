package com.shurdev.data.repositories

import com.shurdev.data.local.dao.SurveyResultsDao
import com.shurdev.data.local.entity.SurveyResultsEntity
import com.shurdev.domain.models.survey.Answer
import com.shurdev.domain.models.survey.Question
import com.shurdev.domain.repositories.SurveyRepository
import javax.inject.Inject

class SurveyRepositoryImpl @Inject constructor(
    private val surveyResultsDao: SurveyResultsDao
) : SurveyRepository {

    override suspend fun getQuestions(): List<Question> {
        return questionsMocks
    }

    override suspend fun submitAnswers(answers: List<Answer>) {
        // TODO send answers to server
    }


    override suspend fun saveResultsToDatabase(questions: List<Question>, answers: List<Answer>) {

        val results = questions.map { question ->
            val userAnswer = answers.first { answer -> answer.questionId == question.id }

            return@map SurveyResultsEntity(
                question = question,
                answer = userAnswer.answer
            )
        }

        surveyResultsDao.deleteAllResults()
        surveyResultsDao.saveResults(results)
    }

    override suspend fun getResultsFromDatabase(): List<Pair<Question, Answer>> {
        val results = surveyResultsDao.getAllResults()

        return results.map { result ->
            val question = result.question
            val answer = Answer(answer = result.answer, questionId = question.id)
            return@map Pair(question, answer)
        }
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