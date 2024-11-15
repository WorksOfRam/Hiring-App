package com.srini.hiring.domain.usecase

import com.srini.hiring.data.model.HireModel
import com.srini.hiring.domain.repository.Repository
import javax.inject.Inject

class HiringUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun getHiring(): Map<Int, List<HireModel>> {
        return repository.getHiring()
    }
}