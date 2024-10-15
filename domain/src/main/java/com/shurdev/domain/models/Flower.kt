package com.shurdev.domain.models

data class Flower(
    val name: String,
    val description: String,
    val imageLink: String,
) {

    fun doesMathSearchQuery(query: String): Boolean {
        return name.contains(query, ignoreCase = true)
                || description.contains(query, ignoreCase = true)
    }
}