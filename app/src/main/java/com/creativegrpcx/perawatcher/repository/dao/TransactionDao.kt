package com.creativegrpcx.perawatcher.repository.dao

import androidx.room.*
import com.creativegrpcx.perawatcher.repository.entities.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(vararg transaction: Transaction)

    @Delete
    suspend fun deleteTransaction(vararg transaction: Transaction)

    @Update
    suspend fun updateTransaction(vararg transaction: Transaction)

    @Query("SELECT * FROM transactions ORDER BY transactionId DESC")
    fun getAllTransactions() : Flow<List<Transaction>>

    @Query("SELECT * FROM transactions WHERE transactionId == :transactionId")
    fun getTransaction(transactionId: Int) : Flow<Transaction>


}
