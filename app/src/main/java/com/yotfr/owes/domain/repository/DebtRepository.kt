package com.yotfr.owes.domain.repository

import com.yotfr.owes.domain.model.Debt
import com.yotfr.owes.domain.model.DebtWithPerson
import kotlinx.coroutines.flow.Flow

interface DebtRepository {
    suspend fun createDebt(debt: Debt)
    suspend fun deleteDebt(debt: Debt)
    fun getAllGivenDebts(): Flow<List<DebtWithPerson>>
    fun getAllTakenDebts(): Flow<List<DebtWithPerson>>
}