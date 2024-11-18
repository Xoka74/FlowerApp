package com.shurdev.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.shurdev.data.local.entity.SurveyResultsEntity

@Dao
interface SurveyResultsDao {

    @Query("SELECT * FROM survey_results")
    suspend fun getAllResults(): List<SurveyResultsEntity>

    @Insert
    suspend fun saveResults(surveyResults: List<SurveyResultsEntity>)

    @Query("DELETE FROM survey_results")
    suspend fun deleteAllResults()
}