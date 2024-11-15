package com.srini.hiring.data.repository

import com.srini.hiring.data.model.HireModel
import com.srini.hiring.data.remote.ApiService
import com.srini.hiring.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : Repository {
    override suspend fun getHiring(): Map<Int, List<HireModel>> {
        val response = apiService.getHiring()
        return response.filter { it.name?.isNotBlank() == true }
            .sortedWith(compareBy({ it.listId }, { it.name }))
            .groupBy { it.listId }
    }
}
