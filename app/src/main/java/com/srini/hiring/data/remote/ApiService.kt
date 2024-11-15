package com.srini.hiring.data.remote

import com.srini.hiring.data.model.HireModel
import retrofit2.http.GET

interface ApiService {
    @GET("hiring.json")
    suspend fun getHiring(): List<HireModel>
}