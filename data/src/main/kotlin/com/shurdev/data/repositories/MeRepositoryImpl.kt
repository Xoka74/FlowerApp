package com.shurdev.data.repositories

import com.shurdev.data.mappers.toDomainModel
import com.shurdev.data.remote.api.MeApi
import com.shurdev.domain.models.user.MeUser
import com.shurdev.domain.repositories.MeRepository
import javax.inject.Inject

class MeRepositoryImpl @Inject constructor(
    private val meApi: MeApi,
) : MeRepository {
    override suspend fun getUser(): MeUser = meApi.getUser().toDomainModel()
}