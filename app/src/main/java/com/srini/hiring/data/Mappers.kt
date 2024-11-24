package com.srini.hiring.data

import com.srini.hiring.data.model.HireEntity
import com.srini.hiring.data.model.HireModel

fun HireModel.toEntity(): HireEntity {
    return HireEntity(
        hireId = id,
        listId = listId,
        name = name ?: ""
    )
}

fun HireEntity.toModel(): HireModel {
    return HireModel(
        id = this.hireId,
        listId = this.listId,
        name = this.name
    )
}