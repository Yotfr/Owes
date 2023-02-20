package com.yotfr.owes.app.di

import android.content.Context
import androidx.room.Room
import com.yotfr.owes.data.datasource.local.DebtDao
import com.yotfr.owes.data.datasource.local.DebtDatabase
import com.yotfr.owes.data.datasource.local.PersonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    @Singleton
    fun provideDebtDatabase(@ApplicationContext context: Context): DebtDatabase {
        return Room.databaseBuilder(
            context,
            DebtDatabase::class.java,
            "debt_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDebtDao(debtDatabase: DebtDatabase): DebtDao {
        return debtDatabase.debtDao
    }

    @Provides
    @Singleton
    fun providePersonDao(debtDatabase: DebtDatabase): PersonDao {
        return debtDatabase.personDao
    }
}
