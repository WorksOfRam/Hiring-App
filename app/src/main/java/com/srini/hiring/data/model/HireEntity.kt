package com.srini.hiring.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "hire_table")
data class HireEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo("hireId")
    val hireId: Int,

    @ColumnInfo("listId")
    val listId: Int,

    @ColumnInfo("name")
    val name: String
)
