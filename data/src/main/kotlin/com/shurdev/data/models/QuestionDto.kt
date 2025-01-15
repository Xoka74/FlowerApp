package com.shurdev.data.models

data class QuestionDto(
    val id: Int,
    val text: String,
    val variants: List<String>,
)