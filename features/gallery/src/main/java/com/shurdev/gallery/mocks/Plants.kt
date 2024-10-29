package com.shurdev.gallery.mocks

import com.shurdev.domain.models.Plant

val Plants = (1..10)
    .map {
        Plant(
            name = "Plant$it",
            description = "Description$it",
            imageLink = "https://cdn.britannica.com/84/73184-050-05ED59CB/Sunflower-field-Fargo-North-Dakota.jpg"
        )
    }