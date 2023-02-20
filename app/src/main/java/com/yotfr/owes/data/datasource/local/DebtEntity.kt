package com.yotfr.owes.data.datasource.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(
    tableName = "debt"
)
data class DebtEntity(
    @PrimaryKey(autoGenerate = true) val id: Long?,
    val amount: Long,
    val isDebtTaken: Boolean,
    val takingDate: LocalDate?,
    val repaymentDate: LocalDate?,
    val commentaryMessage: String?,
    val personId: Long
)
