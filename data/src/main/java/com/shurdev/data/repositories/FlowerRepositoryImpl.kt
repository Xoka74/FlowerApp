package com.shurdev.data.repositories

import com.shurdev.domain.models.Flower
import com.shurdev.domain.repositories.FlowerRepository
import javax.inject.Inject

class FlowerRepositoryImpl @Inject constructor() : FlowerRepository {
    override suspend fun getFlowers(): List<Flower> {
        val link =
            "https://cdn.britannica.com/84/73184-050-05ED59CB/Sunflower-field-Fargo-North-Dakota.jpg"

        return (0..20).map {
            Flower("Flower${it}", "Description${it}", link)
        }
    }
}