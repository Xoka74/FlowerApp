package com.shurdev.domain.repositories

import com.shurdev.domain.models.Flower
import com.shurdev.domain.models.FlowerFilters

interface FlowerRepository {
    suspend fun getFlowers(): List<Flower>

    suspend fun getFlowersByFilters(filters: FlowerFilters): List<Flower>
}
