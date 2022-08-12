package com.creativegrpcx.perawatcher.data.repository.dao

import androidx.room.*
import com.creativegrpcx.perawatcher.data.repository.entities.Transaction
import com.creativegrpcx.perawatcher.data.repository.entities.WalletTransaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(vararg transaction: Transaction) : List<Long>

    @Delete
    suspend fun deleteTransaction(vararg transaction: Transaction): Int

    @Update
    suspend fun updateTransaction(vararg transaction: Transaction): Int

    @Query("SELECT * FROM transactions ORDER BY transactionId DESC")
    fun getAllTransactions() : Flow<List<Transaction>>

    @Query("SELECT * FROM transactions WHERE transactionId == :transactionId")
    fun getTransaction(transactionId: Int) : Flow<Transaction>

}
