package com.devmasterteam.tasks.service.repository

import com.devmasterteam.tasks.service.model.PriorityModel
import com.devmasterteam.tasks.service.repository.remote.PriorityService
import com.devmasterteam.tasks.service.repository.remote.RetrofitClient
import retrofit2.Response

class PriorityRepository {
    private val remote = RetrofitClient.getService(PriorityService::class.java)

    suspend fun getList(): Response<List<PriorityModel>> {
        return remote.getList()
    }
}