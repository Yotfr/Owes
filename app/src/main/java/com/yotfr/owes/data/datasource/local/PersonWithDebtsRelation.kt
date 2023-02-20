package com.yotfr.owes.data.datasource.local

import androidx.room.Embedded
import androidx.room.Relation

data class PersonWithDebtsRelation(
    @Embedded val person: PersonEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "personId"
    )
    val debts: List<DebtEntity>
)
