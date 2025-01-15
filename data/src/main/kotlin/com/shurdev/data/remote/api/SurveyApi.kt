package com.shurdev.data.remote.api

import com.shurdev.data.models.PlantDto
import com.shurdev.data.models.QuestionDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SurveyApi {

    @GET("recommendation/questions")
    suspend fun getQuestions(): List<QuestionDto>

    @POST("recommendation/survey")
    @JvmSuppressWildcards
    suspend fun submitAnswers(@Body body: Map<String, Any>)

    @GET("recommendation?take=20")
    suspend fun getRecommendations() : List<PlantDto>
}