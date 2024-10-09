package com.shurdev.data.repositories

import com.shurdev.domain.models.Flower
import com.shurdev.domain.repositories.FlowerRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FlowerRepositoryImpl @Inject constructor() : FlowerRepository {
    override val flowers: Flow<List<Flower>> = flow {
        delay(2000)
        val link = "https://cdn.britannica.com/84/73184-050-05ED59CB/Sunflower-field-Fargo-North-Dakota.jpg"
        emit(
            (0..20).map {
                Flower("Flower${it}", link)
            }
        )
    }
}