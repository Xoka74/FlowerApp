package com.shurdev.data.repositories

import com.shurdev.domain.models.Flower
import com.shurdev.domain.models.FlowerFilters
import com.shurdev.domain.repositories.FlowerRepository
import javax.inject.Inject

class FlowerRepositoryImpl @Inject constructor() : FlowerRepository {

    override suspend fun getFlowers(): List<Flower> {
        return getFlowersMocks()
    }

    override suspend fun getFlowersByFilters(filters: FlowerFilters): List<Flower> {
        return getFlowersMocks()
            .filter { doesFlowerMatchFilters(it, filters) }
    }

    private fun getFlowersMocks(): List<Flower> {
        val link =
            "https://cdn.britannica.com/84/73184-050-05ED59CB/Sunflower-field-Fargo-North-Dakota.jpg"

        return (0..20).map {
            Flower("Flower${it}", "Description${it}", link)
        }
    }

    private fun doesFlowerMatchFilters(flower: Flower, filters: FlowerFilters): Boolean {
        return flower.name.contains(filters.name ?: "", ignoreCase = true)
                || flower.description.contains(filters.description ?: "", ignoreCase = true)
    }
}