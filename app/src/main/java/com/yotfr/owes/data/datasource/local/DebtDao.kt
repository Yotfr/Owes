package com.yotfr.owes.data.datasource.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface DebtDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDebt(debt: DebtEntity)

    @Delete
    suspend fun deleteDebt(debt: DebtEntity)

    @Transaction
    @Query("SELECT * FROM debt WHERE isDebtTaken = 0")
    fun getAllGivenDebts(): Flow<List<DebtWithPersonRelation>>

    @Transaction
    @Query("SELECT * FROM debt WHERE isDebtTaken = 1")
    fun getAllTakenDebts(): Flow<List<DebtWithPersonRelation>>
}
