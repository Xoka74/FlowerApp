package com.shurdev.domain.repositories

import com.shurdev.domain.models.Flower

interface FlowerRepository {
    suspend fun getFlowers(): List<Flower>
}