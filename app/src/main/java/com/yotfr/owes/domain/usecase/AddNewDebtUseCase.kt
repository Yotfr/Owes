package com.yotfr.owes.domain.usecase

import com.yotfr.owes.domain.model.Debt
import com.yotfr.owes.domain.model.DebtStatus
import com.yotfr.owes.domain.model.Person
import com.yotfr.owes.domain.repository.DebtRepository
import com.yotfr.owes.domain.repository.PersonRepository
import java.time.LocalDate

class AddNewDebtUseCase(
    private val debtRepository: DebtRepository,
    private val personRepository: PersonRepository
) {
    suspend operator fun invoke(
        debtId: Long?,
        debtAmount: Long,
        debtStatus: DebtStatus,
        takingDate: LocalDate?,
        repaymentDate: LocalDate?,
        commentaryMessage: String?,
        personName: String,
        phoneNumber: String
    ) {
        val person = personRepository.findPersonByName(
            name = personName
        )
        person?.let {
            debtRepository.createDebt(
                debt = Debt(
                    id = debtId,
                    amount = debtAmount,
                    debtStatus = debtStatus,
                    takingDate = takingDate,
                    repaymentDate = repaymentDate,
                    commentaryMessage = commentaryMessage,
                    personId = it.id ?: throw IllegalArgumentException(
                        "Person id cannot be null if person is not null"
                    )
                )
            )
        } ?: kotlin.run {
            val personId = personRepository.createPerson(
                person = Person(
                    id = null,
                    name = personName,
                    phoneNumber = phoneNumber
                )
            )
            debtRepository.createDebt(
                debt = Debt(
                    id = debtId,
                    amount = debtAmount,
                    debtStatus = debtStatus,
                    takingDate = takingDate,
                    repaymentDate = repaymentDate,
                    commentaryMessage = commentaryMessage,
                    personId = personId
                )
            )
        }
    }
}
