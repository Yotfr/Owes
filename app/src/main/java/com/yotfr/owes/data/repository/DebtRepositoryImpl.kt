package com.yotfr.owes.data.repository

import com.yotfr.owes.data.datasource.local.DebtDao
import com.yotfr.owes.data.util.mapToDebtEntity
import com.yotfr.owes.data.util.mapToDebtWithPerson
import com.yotfr.owes.domain.model.Debt
import com.yotfr.owes.domain.model.DebtWithPerson
import com.yotfr.owes.domain.repository.DebtRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DebtRepositoryImpl @Inject constructor(
    private val debtDao: DebtDao
) : DebtRepository {

    override suspend fun createDebt(debt: Debt) {
        debtDao.insertDebt(
            debt = debt.mapToDebtEntity()
        )
    }

    override suspend fun deleteDebt(debt: Debt) {
        debtDao.deleteDebt(
            debt = debt.mapToDebtEntity()
        )
    }

    override fun getAllGivenDebts(): Flow<List<DebtWithPerson>> {
        return debtDao.getAllGivenDebts().map {
            it.map { it.mapToDebtWithPerson() }
        }
    }

    override fun getAllTakenDebts(): Flow<List<DebtWithPerson>> {
        return debtDao.getAllTakenDebts().map {
            it.map { it.mapToDebtWithPerson() }
        }
    }
}