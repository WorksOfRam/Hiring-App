package com.srini.hiring.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.srini.hiring.data.model.HireEntity

@Dao
interface HireDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHireEntity(hireEntity: HireEntity): Long

    @Query("SELECT * FROM hire_table")
    suspend fun getAllHires(): List<HireEntity>
}
