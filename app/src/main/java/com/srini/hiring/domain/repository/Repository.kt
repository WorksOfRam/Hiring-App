package com.srini.hiring.domain.repository

import com.srini.hiring.data.model.HireModel

interface Repository {
    suspend fun getHiring(): Map<Int, List<HireModel>>
}