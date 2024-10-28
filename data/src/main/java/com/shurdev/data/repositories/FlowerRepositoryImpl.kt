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

    override suspend fun getFlowerById(id: Int): Flower? {
        return getFlowersMocks()
            .firstOrNull { it.id == id }
    }

    private fun getFlowersMocks(): List<Flower> {
        val link =
            "https://cdn.britannica.com/84/73184-050-05ED59CB/Sunflower-field-Fargo-North-Dakota.jpg"

        return (0..20).map {
            Flower(
                id = it,
                name = "Flower${it}",
                description = "Description${it}",
                imageLink = link
            )
        }
    }

    private fun doesFlowerMatchFilters(flower: Flower, filters: FlowerFilters): Boolean {
        return flower.name.contains(filters.name ?: "", ignoreCase = true)
                || flower.description.contains(filters.description ?: "", ignoreCase = true)
    }
}