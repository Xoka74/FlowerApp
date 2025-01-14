package com.shurdev.data.remote.api

import com.shurdev.domain.models.survey.Answer
import com.shurdev.domain.models.survey.Question
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SurveyApi {

    @GET("/api/survey/questions")
    suspend fun getQuestions(): List<Question>

    @POST("/api/survey")
    suspend fun submitAnswers(@Body answers: List<Answer>)
}