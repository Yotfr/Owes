package com.yotfr.owes.domain.usecase

import com.yotfr.owes.domain.model.DebtWithPerson
import com.yotfr.owes.domain.repository.DebtRepository
import kotlinx.coroutines.flow.Flow

class GetAllTakenDebtsUseCase(
    private val debtRepository: DebtRepository
) {
    operator fun invoke(): Flow<List<DebtWithPerson>> {
        return debtRepository.getAllTakenDebts()
    }
}