package com.shurdev.data.repositories

import com.shurdev.data.mappers.toDomainModel
import com.shurdev.data.mappers.toDto
import com.shurdev.data.remote.api.MeApi
import com.shurdev.data.remote.api.SurveyApi
import com.shurdev.domain.models.plant.Plant
import com.shurdev.domain.models.survey.Answer
import com.shurdev.domain.models.survey.Question
import com.shurdev.domain.repositories.SurveyRepository
import javax.inject.Inject

class SurveyRepositoryImpl @Inject constructor(
    private val surveyApi: SurveyApi,
    private val meApi: MeApi,
) : SurveyRepository {

    override suspend fun getQuestions(): List<Question> =
        surveyApi.getQuestions().map { it.toDomainModel() }

    override suspend fun submitAnswers(answers: List<Answer>) {
        val userId = meApi.getUser().googleId

        return surveyApi.submitAnswers(answers.toDto(userId))
    }

    override suspend fun getRecommendedPlants(): List<Plant> =
        surveyApi.getRecommendations().map { it.toDomainModel() }
}