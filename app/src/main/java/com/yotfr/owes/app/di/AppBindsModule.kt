package com.yotfr.owes.app.di

import com.yotfr.owes.data.repository.DebtRepositoryImpl
import com.yotfr.owes.data.repository.PersonRepositoryImpl
import com.yotfr.owes.domain.repository.DebtRepository
import com.yotfr.owes.domain.repository.PersonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppBindsModule {

    @Binds
    @Singleton
    abstract fun bindDebtsRepository_to_debtsRepositoryImpl(
        debtRepositoryImpl: DebtRepositoryImpl
    ): DebtRepository

    @Binds
    @Singleton
    abstract fun bindPersonRepository_to_personRepositoryImpl(
        personRepositoryImpl: PersonRepositoryImpl
    ): PersonRepository
}