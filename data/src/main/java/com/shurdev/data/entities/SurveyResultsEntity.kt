package com.shurdev.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shurdev.domain.models.survey.Question

@Entity(tableName = "survey_results")
data class SurveyResultsEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    @Embedded(prefix = "question_")
    val question: Question,
    val answer: String
)
