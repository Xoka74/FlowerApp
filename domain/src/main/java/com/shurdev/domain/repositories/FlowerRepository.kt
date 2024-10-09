package com.shurdev.domain.repositories

import com.shurdev.domain.models.Flower
import kotlinx.coroutines.flow.Flow

interface FlowerRepository {
    val flowers: Flow<List<Flower>>
}