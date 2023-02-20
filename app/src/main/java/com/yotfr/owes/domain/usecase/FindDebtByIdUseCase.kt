package com.yotfr.owes.domain.usecase

import com.yotfr.owes.domain.model.DebtWithPerson
import com.yotfr.owes.domain.repository.DebtRepository
import kotlinx.coroutines.flow.Flow

class FindDebtByIdUseCase(
    private val debtRepository: DebtRepository
) {
    operator fun invoke(debtId: Long): Flow<DebtWithPerson?> {
        return debtRepository.findDebtById(
            debtId = debtId
        )
    }
}
