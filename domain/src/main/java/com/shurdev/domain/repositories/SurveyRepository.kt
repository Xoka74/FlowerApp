package com.shurdev.domain.repositories

import com.shurdev.domain.models.survey.Answer
import com.shurdev.domain.models.survey.Question

interface SurveyRepository {

    suspend fun getQuestions(): List<Question>

    suspend fun submitAnswers(answers: List<Answer>)

    suspend fun saveResultsToDatabase(questions: List<Question>, answers: List<Answer>)

    suspend fun getResultsFromDatabase(): List<Pair<Question, Answer>>
}