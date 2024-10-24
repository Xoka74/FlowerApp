package com.shurdev.gallery.mocks

import com.shurdev.domain.models.Flower

val FLOWERS = (1..10)
    .map {
        Flower(
            name = "Flower$it",
            description = "Description$it",
            imageLink = "https://cdn.britannica.com/84/73184-050-05ED59CB/Sunflower-field-Fargo-North-Dakota.jpg"
        )
    }