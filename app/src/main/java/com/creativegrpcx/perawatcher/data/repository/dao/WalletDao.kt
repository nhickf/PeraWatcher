package com.creativegrpcx.perawatcher.data.repository.dao

import androidx.room.*
import com.creativegrpcx.perawatcher.data.repository.entities.Wallet
import kotlinx.coroutines.flow.Flow

@Dao
interface WalletDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWallet(vararg transaction: Wallet) : List<Long>

    @Delete
    suspend fun deleteWallet(vararg transaction: Wallet) : Int

    @Update
    suspend fun updateWallet(vararg transaction: Wallet) : Int

    @Query("SELECT * FROM wallet ORDER BY walletId DESC")
    fun getAllWallet() : Flow<List<Wallet>>

    @Query("SELECT * FROM wallet WHERE walletId == :walletId")
    fun getWallet(walletId: Int) : Flow<Wallet>
}
