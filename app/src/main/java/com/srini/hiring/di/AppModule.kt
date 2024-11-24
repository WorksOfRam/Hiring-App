package com.srini.hiring.di

import android.content.Context
import androidx.room.Room
import com.srini.hiring.data.local.HireDao
import com.srini.hiring.data.local.HireDatabase
import com.srini.hiring.data.remote.ApiService
import com.srini.hiring.data.repository.RepositoryImpl
import com.srini.hiring.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level =
            HttpLoggingInterceptor.Level.BODY // Can also use LEVEL_Basic or LEVEL_HEADERS

        // Create OkHttpClient with the logging interceptor
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): HireDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            HireDatabase::class.java,
            "hire_database"
        ).build()
    }

    @Provides
    fun provideTaskDao(database: HireDatabase): HireDao {
        return database.hireDao()
    }

    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService, hireDao: HireDao): Repository {
        return RepositoryImpl(apiService, hireDao)
    }
}
