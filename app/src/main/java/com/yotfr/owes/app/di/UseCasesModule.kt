package com.yotfr.owes.app.di

import com.yotfr.owes.domain.repository.DebtRepository
import com.yotfr.owes.domain.repository.PersonRepository
import com.yotfr.owes.domain.usecase.AddNewDebtUseCase
import com.yotfr.owes.domain.usecase.FindDebtByIdUseCase
import com.yotfr.owes.domain.usecase.GetAllGivenDebtsUseCase
import com.yotfr.owes.domain.usecase.GetAllTakenDebtsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCasesModule {
    @Provides
    fun provideGetAllTakenDebtsUseCase(
        debtRepository: DebtRepository
    ): GetAllTakenDebtsUseCase {
        return GetAllTakenDebtsUseCase(
            debtRepository = debtRepository
        )
    }

    @Provides
    fun provideGetAllGivenDebtsUseCase(
        debtRepository: DebtRepository
    ): GetAllGivenDebtsUseCase {
        return GetAllGivenDebtsUseCase(
            debtRepository = debtRepository
        )
    }

    @Provides
    fun provideAddNewDebtUseCase(
        debtRepository: DebtRepository,
        personRepository: PersonRepository
    ): AddNewDebtUseCase {
        return AddNewDebtUseCase(
            debtRepository = debtRepository,
            personRepository = personRepository
        )
    }

    @Provides
    fun provideFindDebtByIdUseCase(
        debtRepository: DebtRepository
    ): FindDebtByIdUseCase {
        return FindDebtByIdUseCase(
            debtRepository = debtRepository
        )
    }
}