package com.srini.hiring.data.repository

import com.srini.hiring.data.local.HireDao
import com.srini.hiring.data.model.HireModel
import com.srini.hiring.data.remote.ApiService
import com.srini.hiring.data.toEntity
import com.srini.hiring.data.toModel
import com.srini.hiring.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val hireDao: HireDao
) : Repository {
    override suspend fun getHiring(): Map<Int, List<HireModel>> {
        val localList = hireDao.getAllHires()

        val filteredList = if (localList.isEmpty()) {
            val response = apiService.getHiring()
            response.forEach {
                hireDao.insertHireEntity(it.toEntity())
            }
            response
        } else {
            localList.map { it.toModel() } // Map HireEntity to HireModel
        }

        return filteredList.filter { it.name?.isNotBlank() == true }
            .sortedWith(compareBy({ it.listId }, { it.name }))
            .groupBy { it.listId }
    }
}
