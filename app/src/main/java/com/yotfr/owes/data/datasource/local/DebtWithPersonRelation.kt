package com.yotfr.owes.data.datasource.local

import androidx.room.Embedded
import androidx.room.Relation

data class DebtWithPersonRelation(
    @Embedded val debt: DebtEntity,
    @Relation(
        parentColumn = "personId",
        entityColumn = "id"
    )
    val person: PersonEntity
)