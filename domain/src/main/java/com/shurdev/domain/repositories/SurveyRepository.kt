package com.shurdev.domain.repositories

import com.shurdev.domain.models.survey.Answer
import com.shurdev.domain.models.survey.Question
import com.shurdev.domain.models.survey.AnsweredQuestion

interface SurveyRepository {

    suspend fun getQuestions(): List<Question>

    suspend fun submitAnswers(answers: List<Answer>)

    suspend fun saveResultsToDatabase(results: List<AnsweredQuestion>)

    suspend fun getResultsFromDatabase(): List<AnsweredQuestion>
}