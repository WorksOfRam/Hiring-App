package com.srini.hiring.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.srini.hiring.data.model.HireEntity

@Database(entities = [HireEntity::class], version = 1, exportSchema = true)
abstract class HireDatabase : RoomDatabase() {
    abstract fun hireDao(): HireDao
}
