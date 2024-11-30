package com.shurdev.data.remote.api

import com.shurdev.domain.models.survey.Answer
import retrofit2.http.Body
import retrofit2.http.POST

interface SurveyApi {

    @POST("/api/survey")
    suspend fun submitAnswers(@Body answers: List<Answer>)
}