package com.shurdev.gallery.mocks

import com.shurdev.domain.models.plant.Plant

val Plants = (1..10)
    .map {
        Plant(
            name = "Подсолнух обыкновенный",
            description = "Description$it",
        )
    }