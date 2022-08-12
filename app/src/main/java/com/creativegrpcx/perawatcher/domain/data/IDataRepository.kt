package com.creativegrpcx.perawatcher.domain.data

import com.creativegrpcx.perawatcher.data.repository.entities.Transaction
import com.creativegrpcx.perawatcher.data.repository.entities.Wallet
import com.creativegrpcx.perawatcher.data.repository.entities.WalletTransaction
import com.creativegrpcx.perawatcher.domain.types.CategoryType
import kotlinx.coroutines.flow.Flow

interface IDataRepository {

    interface ILocalDataSource {
        suspend fun insertTransaction(vararg transaction: Transaction): List<Long>
        suspend fun deleteTransaction(vararg transaction: Transaction): Int
        suspend fun updateTransaction(vararg transaction: Transaction): Int
        fun getTransactions(): Flow<List<Transaction>>
        fun getTransaction(transactionId: Int): Flow<Transaction>

        suspend fun insertWallet(vararg wallet: Wallet): List<Long>
        suspend fun deleteWallet(vararg wallet: Wallet): Int
        suspend fun updateWallet(vararg wallet: Wallet): Int
        fun getAllWallet(): Flow<List<WalletTransaction>>
        fun getWallet(walletId: Int): Flow<Wallet>
    }

    interface INetworkDataSource {

    }
}
